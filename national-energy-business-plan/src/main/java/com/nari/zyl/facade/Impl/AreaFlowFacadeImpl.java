package com.nari.zyl.facade.Impl;

import com.nari.zyl.facade.AreaFlowFacade;
import com.nari.zyl.model.*;
import com.nari.zyl.service.AreaFlowService;
import com.nari.zyl.service.SkyLightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Repository
public class AreaFlowFacadeImpl implements AreaFlowFacade {

    @Value("${service-paramter.areainoroutId}")
    private String areainoroutId;
    @Resource
    private SkyLightService skyLightService;
    @Resource
    private AreaFlowService areaFlowService;

    //第一步：区内、特殊流向分配：
    @Override
    public int compute(String wPId)
    {
        int result = 1;
        //获取区内流向数据
        List<SplmwdiversionSum> splSumList = areaFlowService.getSplmwdiversionSumData(wPId,areainoroutId);
        if (splSumList.size() == 0)
        {
            return 0;
        }

        //标记流向表数据
        for(int i=0; i<splSumList.size(); i++)
        {
            SplmwdiversionSum splSum = splSumList.get(i);
//            //在流向表里，获取优先级最大的一条数据
            Splmwdirpath splmwdirpath = areaFlowService.getSplmwdirpathTopDataByCondition(splSum);
            if (splmwdirpath == null) {
                log.info("数据表缺少关联数据,getSplmwdirpathTopDataByCondition方法未查到流向路径数据！");
                continue;
            } else {
                //更新流向数据：管段，流向字段
                areaFlowService.updateSplmwdiversionSumData(splSum);
            }

            //获得流向分解计划量数据 (天窗和非天窗)
            Splmmsplitedit splmmsplitedit = areaFlowService.getSplmmspliteditByCondition(splSum);
            if(splmmsplitedit == null) {
                continue;
            }

            //根据w_p_id匹配计划值
            List<SkyLightDay> skyLightDayList = skyLightService.getSkyLightList(splmmsplitedit.getWpid());
            //1.匹配上的话
            if(skyLightDayList.size() != 0)
            {
                for (SkyLightDay s1 : skyLightDayList)
                {
                    if (s1.getSkyLight() == true) {
                        s1.setPlanNum(splmmsplitedit.getSkyNum());
                    } else {
                        s1.setPlanNum(splmmsplitedit.getNoskyNum());
                    }
                }
            }else{
                //2.匹配匹配不上的话，使用分流表里面的7天原始值
                skyLightDayList = areaFlowService.getSplmwdiversionSumSenvenData(splmmsplitedit);
                if(skyLightDayList.size() == 0){
                    continue;
                }
            }

            //获取实际产能数据(煤源申报量数据)
            List<SplmwcoalloadSum> splmwcoalloadSumList = areaFlowService.getSplmwcoalloadSumListByCondition(splSum);
            if (splmwcoalloadSumList.size() == 0) {
                log.info("数据表缺少关联数据, 导致getSplmwcoalloadSumListByCondition方法未查到数据！");
                continue;
            }

            //算出产能计划运力数据
            List<Splmwprodmktmatch> splprodmktmatches = areaFlowService.compareSplmmspliteditData(splSum,skyLightDayList,splmwcoalloadSumList);
            if (splprodmktmatches.size() == 0) {
                continue;
            }

            //根据条件删除s_plm_w_prodmktmatch对应的数据。
            int num =0;
            num = areaFlowService.deleteSplmwprodmktmatchByCondition(splSum);

            //将得出的产能计划运力结果，写入s_plm_w_prodmktmatch
            num = areaFlowService.insertSplmwprodmktmatch(splSum,splprodmktmatches);
            if (num == 0) {
                log.error("insertSplmwprodmktmatch！error");
                continue;
            }

            //源数据处理
            num = areaFlowService.manageResourceData(splmwcoalloadSumList, splprodmktmatches);
            if (num == 0) {
                log.info("不需要处理！");
                continue;
            }
        }
        log.info("区内，特殊流向分配结束...");
        return result;
    }

    //第二步：是否满足销售需求，数据补充。
    @Override
    public int confirmSaleDemand() throws Exception
    {
        int result = 1;
        //获取已经入库的数据，按站点，二级单位计算合计值
        List<StationTotalplanSum> totalplanSumList = areaFlowService.getStationTotalplanSumByStationId();

        if (totalplanSumList.size() == 0)
        {
            return 0;
        }

        //1.获取不满足销售的集合  (用totalplanList 装)
        List<StationTotalplanSum> totalplanList = new ArrayList<>();
        for(int i=0; i<totalplanSumList.size(); i++)
        {
            StationTotalplanSum totalplanObj = totalplanSumList.get(i);
            //过滤脏,乱数据
            if(totalplanObj.getOrgId() == null || totalplanObj.getStationId() == null){
                continue;
            }

            //参数
            String orgId = totalplanObj.getOrgId();
            String stationId = totalplanObj.getStationId();

            //获取分流销售数据
            SplmwdiversionStationSum splStationSum = areaFlowService.getSplmwdiversionSumDataByCondition(stationId,orgId);
            if(splStationSum == null){
                log.info("数据表缺少关联数据, 导致getSplmwdiversionSumDataByCondition方法未查到数据！");
                continue;
            }
            int num1 = Integer.parseInt(totalplanObj.getTotalPlanSum());        //煤源数据
            int num2 = Integer.parseInt(splStationSum.getTotalPlan());          //销售数据

            //满足时，不做处理
            if (num1 < num2) {
                log.info("num1:" + num1 + "----"+"num2:" + num2);
                continue;
            }

            //实际完成量
            String startDate = splStationSum.getPlanTime();
            ScoaltloaddayCompleteSum scoalCompleteSum = areaFlowService.getScoaltloaddayCompleteSum(orgId, stationId, startDate);
            if(scoalCompleteSum == null){
                log.info("数据表缺少关联数据, 导致getScoaltloaddayCompleteSum方法未查到数据！");
                continue;
            }
            //天窗日、非天窗日均计划量
            SkyLightDayPlan skyLightDayPlan = areaFlowService.getSkyLightDayPlan(totalplanObj);
            if(skyLightDayPlan == null){
                log.info("数据表缺少关联数据, 导致getSkyLightDayPlan方法未查到数据！");
                continue;
            }
            //任务开始时间所在月份，共有几天天窗日、非天窗日
            SkyLightDays skyLightDays = areaFlowService.getSkyLightDays(totalplanObj.getWPId(), totalplanObj.getStartDate());
            if(skyLightDays == null){
                log.info("数据表缺少关联数据, 导致getSkyLightDays方法未查到数据！");
                continue;
            }
            //计算计划完成量(给数据匹配超欠量)
            StationTotalplanSum totalplanSum = areaFlowService.setScoalCompleteTotalOver(totalplanObj, skyLightDays, skyLightDayPlan, scoalCompleteSum);
            if(totalplanSum == null){
                log.info("数据表缺少关联数据, 导致setScoalCompleteTotalOver方法未查到数据！");
                continue;
            }
            totalplanList.add(totalplanSum);
        }

        //2.针对不满足的集合进行倒序排序(根据超欠量的大小，从大到小)
        Collections.sort(totalplanList, new Comparator<StationTotalplanSum>(){
            @Override
            public int compare(StationTotalplanSum arg0, StationTotalplanSum arg1){
                return arg1.getTotalOver().compareTo(arg0.getTotalOver());
            }
        });

        //3.遍历不满足的集合
        for(int i=0; i< totalplanList.size(); i++)
        {
            StationTotalplanSum totalplansum = totalplanList.get(i);

            //根据不满足的数据,依次获取煤源申报数据list
            List<SplmwcoalloadSum> coalloadList = areaFlowService.getNoSatisfiedSplmwcoalloadSumList(totalplansum);

            //获取当前任务一周的天窗非天窗日期
            List<SkyLightDay> skyLightDayList = skyLightService.getSkyLightList(totalplansum.getWPId());

            //一周的煤源数据和超欠量数据进行比对
            List<Splmwprodmktmatch> prodmktmatchList = areaFlowService.compareAndGetMoreOrLessValueList(totalplansum, skyLightDayList, coalloadList);

            //将得出的产能结果，更新s_plm_w_prodmktmatch表
            int state = 0;
            state = areaFlowService.updateSplmwProdmktmatchData(prodmktmatchList);

            //处理源数据
            if(state != 0) {
                state = areaFlowService.manageResourceData(coalloadList, prodmktmatchList);
                if (state == 0) {
                    continue;
                }
            }
        }
        log.info("先判断是否满足销售需求，再进行数据补充结束！");
        return result;
    }
}