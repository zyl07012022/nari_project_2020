package com.nari.zyl.service.Impl;

import com.nari.zyl.mapper.*;
import com.nari.zyl.model.*;
import com.nari.zyl.service.AreaFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AreaFlowServiceImpl implements AreaFlowService {

    //第一步：
    //获取区内流向数据。
    @Override
    public List<SplmwdiversionSum> getSplmwdiversionSumData(String wPId,String areainoroutId) {
        List<SplmwdiversionSum> splmwdiversionSumList= new ArrayList<>();
        try {
            Map<String,Object> splMap = new HashMap<>();
            splMap.put("wPId", wPId);
            splMap.put("areainoroutId", areainoroutId);
            splmwdiversionSumList = splmwdiversionSumMapper.getSplmwdiversionSumData(splMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return splmwdiversionSumList;
    }

    //获取优先级最大的流向表的数据
    @Override
    public Splmwdirpath getSplmwdirpathTopDataByCondition(SplmwdiversionSum splSum) {
        Splmwdirpath splmwdirpath = null;
        try {
            Map<String,Object> splMap = new HashMap<>();
            String stationId = splSum.getStationId();                    //装车站点
            String destinationId = splSum.getDestinationId();            //目标站点

            String srailwayrangeId = "";    //pipeFeign.getPipeByStationId(stationId);      //调方法获取起始管段
            String erailwayrangeId = "";    //pipeFeign.getPipeByStationId(destinationId);  //调方法获取目标管段

            splMap.put("srailwayrangeId", srailwayrangeId);
            splMap.put("erailwayrangeId", erailwayrangeId);
            splmwdirpath = splmwdirpathMapper.getSplmwdirpathTopDataByCondition(splMap);
            if(splmwdirpath != null) {
                //给流向数据匹配设置相关参数(管段和流向)
                splSum.setSrailwayrangeId(srailwayrangeId);
                splSum.setErailwayrangeId(erailwayrangeId);
                splSum.setTrptdirecId(splmwdirpath.getTrptdirecId());
                splSum.setEtrptdirecId(splmwdirpath.getEtrptdirecId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return splmwdirpath;
    }

    //更新流向数据
    @Override
    public int updateSplmwdiversionSumData(SplmwdiversionSum splSum) {
        int result =0;
        try {
            result = splmwdiversionSumMapper.updateSplmwdiversionSumData(splSum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //获取流向分解计划量数据(天窗非天窗，计划值)
    @Override
    public Splmmsplitedit getSplmmspliteditByCondition(SplmwdiversionSum splSum) {
        Splmmsplitedit splmmsplitedit = null;
        try {
            //测试数据
            splmmsplitedit = splmmspliteditMapper.getSplmmspliteditListByCondition(splSum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return splmmsplitedit;
    }

    @Override
    public List<SkyLightDay> getSplmwdiversionSumSenvenData(Splmmsplitedit splsplitObj) {
        List<SkyLightDay> skyLightDayList = new ArrayList<>();
        try {
            SplmwdiversionSum sum = null;
            List<SplmwdiversionSum> splmwdiversionSumList = splmwdiversionSumMapper.getSplmwdiversionSenvenDaysData(splsplitObj);
            if(splmwdiversionSumList.size() !=0)
            {
                for (int i = 0; i < splmwdiversionSumList.size(); i++)
                {
                    sum = splmwdiversionSumList.get(i);
                    SkyLightDay skyLightDay = new SkyLightDay();
                    skyLightDay.setPlanNum(sum.getTotalPlan());
                    skyLightDay.setMonthAndDay(sum.getPlanTime());
                    skyLightDayList.add(skyLightDay);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return skyLightDayList;
    }

    //获取实际产能数据
    @Override
    public List<SplmwcoalloadSum> getSplmwcoalloadSumListByCondition(SplmwdiversionSum splSum) {
        List<SplmwcoalloadSum> splmwcoalloadSums= new ArrayList<>();
        try {
            splmwcoalloadSums = splmwcoalloadsumMapper.getSplmwcoalloadSumListByCondition(splSum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return splmwcoalloadSums;
    }

    //实际产能数据和流向分解计划量数据进行比较并取小
    @Override
    public List<Splmwprodmktmatch> compareSplmmspliteditData(SplmwdiversionSum splSum,List<SkyLightDay> skyList,List<SplmwcoalloadSum> splcoalList){
        List<Splmwprodmktmatch> goalList = new ArrayList<>();
        Splmwprodmktmatch splmwprodmktmatch = null;

        try {
            if(skyList.size() == splcoalList.size())
            {
                for (int i = 0; i < skyList.size(); i++)
                {
                    SplmwcoalloadSum coalloadSum = splcoalList.get(i);
                    splmwprodmktmatch = new Splmwprodmktmatch();
                    Integer temp = 0;
                    int num1 = Integer.parseInt(skyList.get(i).getPlanNum());
                    int num2 = Integer.parseInt(splcoalList.get(i).getTotalPlan());

                    if (num1 < num2) {
                        temp = num1;
                    } else {
                        temp = num2;
                    }
                    splmwprodmktmatch.setWpId(coalloadSum.getWpId());
                    splmwprodmktmatch.setPlantypeId(coalloadSum.getPlantypeId());
                    splmwprodmktmatch.setCoalsourId(coalloadSum.getCoalsourId());
                    splmwprodmktmatch.setAreainoroutId(coalloadSum.getAreainoroutId());
                    splmwprodmktmatch.setOrgId(coalloadSum.getOrgId());

                    splmwprodmktmatch.setStationId(coalloadSum.getStationId());
                    splmwprodmktmatch.setLoadlineId(coalloadSum.getLoadlineId());
                    splmwprodmktmatch.setDestinationId(coalloadSum.getDestinationId());
                    splmwprodmktmatch.setTotalPlan(String.valueOf(temp));
                    splmwprodmktmatch.setTrptdirecId(coalloadSum.getTrptdirecId());

                    splmwprodmktmatch.setErailwayrangeId(splSum.getErailwayrangeId());
                    splmwprodmktmatch.setPlanTime(coalloadSum.getPlanTime());
                    goalList.add(splmwprodmktmatch);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return goalList;
    }

    //根据条件删除s_plm_w_prodmktmatch对应的数据,
    @Override
    public int deleteSplmwprodmktmatchByCondition(SplmwdiversionSum splSum) {
        int result =0;
        try {
            Map<String,Object> splMap = new HashMap<>();
            splMap.put("wpId",splSum.getWpId());
            splMap.put("stationId",splSum.getStationId());
            splMap.put("srailwayrangeId",splSum.getSrailwayrangeId());    //起始管段

            splMap.put("destinationId",splSum.getDestinationId());
            splMap.put("orgId",splSum.getOrgId());
            splMap.put("trptdirecId",splSum.getTrptdirecId());
            result = splmwprodmktmatchMapper.deleteSplmwprodmktmatchByCondition(splMap);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    //将得出的产能计划运力结果，写入s_plm_w_prodmktmatch
    @Override
    public int insertSplmwprodmktmatch(SplmwdiversionSum splSum,List<Splmwprodmktmatch> splprodmktmatches) {
        int result =0;
        try {
            Map<String, Object> splMap = new HashMap<>();
            for(Splmwprodmktmatch splmatch : splprodmktmatches) {
                UUID uuid = UUID.randomUUID();
                splmatch.setTId(uuid.toString());
                splmatch.setSrailwayrangeId(splSum.getSrailwayrangeId());
                result = splmwprodmktmatchMapper.insertSplmwprodmktmatch(splmatch);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //将得出的结果，更新s_plm_w_prodmktmatch表
    @Override
    public int updateSplmwProdmktmatchData(List<Splmwprodmktmatch> prodmktmatchList) {
        int result = 0;
        try {
            Map<String, Object> splMap = new HashMap<>();
            for(Splmwprodmktmatch splmatch : prodmktmatchList) {
                UUID uuid = UUID.randomUUID();
                splMap.put("tId",uuid.toString());
                splMap.put("wpId", splmatch.getWpId());
                splMap.put("stationId", splmatch.getStationId());

                splMap.put("orgId", splmatch.getOrgId());
                splMap.put("dValue", splmatch.getTotalPlan());
                splMap.put("planTime", splmatch.getPlanTime());
                result = splmwprodmktmatchMapper.updateSplmwprodmktmatch(splMap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Splmwprodmktmatch> compareAndGetMoreOrLessValueList(StationTotalplanSum totalplan, List<SkyLightDay> skyLightDayList, List<SplmwcoalloadSum> coalloadList) {
        List<Splmwprodmktmatch> goalList = new ArrayList<>();
        try {
            //1.先设置超欠量
            if(skyLightDayList.size() == coalloadList.size()) {
                int overtotal = totalplan.getTotalOver();
                int temp = overtotal / 7;
                for (int i = 0; i < skyLightDayList.size(); i++) {
                    SkyLightDay skyLightDay = skyLightDayList.get(i);
                    if (temp < 1) {
                        if (skyLightDay.getSkyLight()) {
                            skyLightDay.setPlanNum(String.valueOf(0));
                        } else {
                            skyLightDay.setPlanNum(String.valueOf(1));
                        }
                    } else {
                        skyLightDay.setPlanNum(String.valueOf(temp));
                    }
                }

                //2.把煤源数据和超欠量进行比对，取小，然后封装至一个list,返回
                Splmwprodmktmatch splmwprodmktmatch = null;
                SplmwcoalloadSum splmwcoal = null;
                int totalPlan = 0,overtotalDay = 0;
                for (int i = 0; i < coalloadList.size(); i++) {
                    splmwcoal = coalloadList.get(i);
                    splmwprodmktmatch = new Splmwprodmktmatch();
                    totalPlan = Integer.parseInt(splmwcoal.getTotalPlan());
                    overtotalDay =Integer.parseInt(skyLightDayList.get(i).getPlanNum());

                    if(totalPlan <= overtotalDay){
                        temp = totalPlan;
                    }else{
                        temp = overtotalDay;
                    }
                    splmwprodmktmatch.setTotalPlan(String.valueOf(temp));

                    splmwprodmktmatch.setWpId(splmwcoal.getWpId());
                    splmwprodmktmatch.setStationId(splmwcoal.getStationId());

                    splmwprodmktmatch.setOrgId(splmwcoal.getOrgId());
                    splmwprodmktmatch.setTrptdirecId(splmwcoal.getTrptdirecId());
                    splmwprodmktmatch.setDestinationId(splmwcoal.getDestinationId());
                    splmwprodmktmatch.setPlanTime(splmwcoal.getPlanTime());
                    //splmwprodmktmatch.setSrailwayrange_id(splcoalList.get(i).getS);
                    //splmwprodmktmatch.setAreainorout_id("01");
                    //splmwprodmktmatch.setErailwayrange_id();
                    goalList.add(splmwprodmktmatch);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return goalList;
    }

    //处理源数据
    @Override
    public int manageResourceData(List<SplmwcoalloadSum> coalloadList, List<Splmwprodmktmatch> splprodmktmatches){
        int result =0;
        try {
            String status = "";
            SplmwcoalloadSum coalloadSum = coalloadList.get(0);
            //0:表示全部小  1:表示全部大 3:部分大于，部分不大于
            int compareValue = judgeIsSmallerThanPlan(coalloadList, splprodmktmatches);
            if (compareValue == 0) {
                status = "02";
                result = updateSplmwcoalloadSumStatusByCondition(coalloadSum,status);
            }else{
                status = "03";
                result = updateSplmwcoalloadSumStatusByCondition(coalloadSum,status);
                if(result !=0) {
                    result = insertSplmwcoalloadSumByCondition(coalloadList, splprodmktmatches);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //判断
    public int judgeIsSmallerThanPlan(List<SplmwcoalloadSum> splcoalList,List<Splmwprodmktmatch> splprodmkts){
        int result = 3;
        try {
            int temp1 = 0; int temp2 = 0;
            for (int i = 0; i < splcoalList.size(); i++) {
                int num1 = Integer.parseInt(splcoalList.get(i).getTotalPlan());
                int num2 = Integer.parseInt(splprodmkts.get(i).getTotalPlan());

                if(num1 <= num2){
                    temp1 ++;
                }
                if(num1 > num2){
                    temp2 ++;
                }
            }
            if(temp1 == 5){
                result = 0;
            }
            if(temp2 == 5){
                result = 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //更新数据(修改状态标记)
    public int updateSplmwcoalloadSumStatusByCondition(SplmwcoalloadSum coalloadSum,String status) {
        int result = 0;
        try {
            Map<String, Object> splMap = new HashMap<>();
            splMap.put("wPId",coalloadSum.getWpId());
            splMap.put("orgId",coalloadSum.getOrgId());
            splMap.put("stationId",coalloadSum.getStationId());

            splMap.put("destinationId",coalloadSum.getDestinationId());
            splMap.put("trptdirecId",coalloadSum.getTrptdirecId());
            splMap.put("status",status);
            splmwcoalloadsumMapper.updateSplmwcoalloadSumStatusByCondition(splMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //写入数据(源申报数据减去-写入产销匹配数据表的数据),重新生成新的id
    public int insertSplmwcoalloadSumByCondition(List<SplmwcoalloadSum> splmwcoalloadSumList,List<Splmwprodmktmatch> splprodmktmatches) {
        int result = 0;
        try {
            for(int i=0;i< splprodmktmatches.size();i++) {
                Map<String, Object> splMap = new HashMap<>();
                UUID tId = UUID.randomUUID();

                SplmwcoalloadSum splmwcoalloadSum = splmwcoalloadSumList.get(i);
                Splmwprodmktmatch splmwprodmktmatch = splprodmktmatches.get(i);

                int num1 = Integer.parseInt(splmwcoalloadSum.getTotalPlan());
                int num2 = Integer.parseInt(splmwprodmktmatch.getTotalPlan());
                Integer planNum = num1 - num2;

                splmwcoalloadSum.setTId(tId.toString());
                splmwcoalloadSum.setDatastatus("04");
                splmwcoalloadSum.setTotalPlan(planNum.toString());
                result = splmwcoalloadsumMapper.insertSplmwcoalloadSumByCondition(splmwcoalloadSum);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //第二步: 获取已经入库的数据，按站点计算合计值
    @Override
    public List<StationTotalplanSum> getStationTotalplanSumByStationId() {
        List<StationTotalplanSum> stationTotalplanSums = new ArrayList<>();
        try {
            stationTotalplanSums = splmwcoalloadsumMapper.getStationTotalplanSumByStationId();
        }catch (Exception e){
            e.printStackTrace();
        }
        return stationTotalplanSums;
    }

    //上面得到一个list
    @Override
    public SplmwdiversionStationSum getSplmwdiversionSumDataByCondition(String stationId, String orgId) {
        SplmwdiversionStationSum splmwdiversionStationSum = null;
        try {
            Map<String, Object> splMap = new HashMap<>();
            splMap.put("stationId",stationId);
            splMap.put("orgId",orgId);
            splMap.put("areainoroutId","QNCQ_01");
            splmwdiversionStationSum = splmwdiversionSumMapper.getSplmwdiversionSumDataByCondition(splMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return splmwdiversionStationSum;
    }

    //实际完成量：根据二级单位、站点，取【日装车完成量S_COAL_T_LOADDAYCOMPLETE】表，任务开始时间所在月份下，已完成量并sum，
    @Override
    public ScoaltloaddayCompleteSum getScoaltloaddayCompleteSum(String org_id, String station_id, String startDate) {
        ScoaltloaddayCompleteSum scoaltloaddayCompleteSum = null;
        try {
            Map<String, Object> splMap = new HashMap<>();
            splMap.put("stationId",station_id);
            splMap.put("orgId",org_id);

            String [] dateStr = startDate.split("-");
            StringBuffer date_str =new StringBuffer();
            String dyear = dateStr[0];
            String dmouth = dateStr[1];

            splMap.put("tDate",dyear+"-"+dmouth);
            scoaltloaddayCompleteSum = scoaltloaddayCompleteMapper.getScoaltloaddayCompleteSum(splMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return scoaltloaddayCompleteSum;
    }

    //天窗日、非天窗日均计划量
    @Override
    public SkyLightDayPlan getSkyLightDayPlan(StationTotalplanSum stationTotalplan) {
        SkyLightDayPlan skyLightDayPlan = null;
        //先获取天窗数据获取天窗和非天窗日均量
        try {
            Map<String, Object> splMap = new HashMap<>();
            splMap.put("wPId",stationTotalplan.getWPId());
            splMap.put("orgNo",stationTotalplan.getOrgId());
            splMap.put("stationId",stationTotalplan.getStationId());

            String startTime = stationTotalplan.getStartDate();
//            String [] dateStr = startTime.split(" ");
//            String sDate = dateStr[0];
            splMap.put("sDate",stationTotalplan.getStartDate());
            skyLightDayPlan = splmmspliteditMapper.getSplmmspliteditByCondition(splMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return skyLightDayPlan;
    }

    //任务开始时间所在月份，共有几天天窗日、非天窗日
    @Override
    public SkyLightDays getSkyLightDays(String wpId, String startDate) {
        SkyLightDays skyLightDay = null;
        try{
            //1.先获取月天数
            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM");
            String [] dateStr = startDate.split("-");
            StringBuffer date_str =new StringBuffer();
            String dyear = dateStr[0];
            String dmouth = dateStr[1];
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(simpleDate.parse(dyear + "-" + dmouth));
            int days = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);//根据年月 获取月份天数

            //2.根据起始时间获取该任务的月份天窗多少天
            int skyDays = skyLightMapper.getskyDaysByMonth(dyear+"-"+dmouth);
            skyLightDay.setWPId(wpId);
            skyLightDay.setSkyDays(skyDays);
            skyLightDay.setNoSkyDays(days - skyDays);
        }catch(Exception e){
            e.printStackTrace();
        }
        return skyLightDay;
    }

    //获取不满足销售的,针对其煤源数据,进行补充
    @Override
    public List<SplmwcoalloadSum> getNoSatisfiedSplmwcoalloadSumList(StationTotalplanSum stationTotalplanSum) {
        List<SplmwcoalloadSum> splmwcoalloadSumList = new ArrayList<>();
        try {
            Map<String, Object> splMap = new HashMap<>();
            splMap.put("orgId",stationTotalplanSum.getOrgId());
            splMap.put("stationId",stationTotalplanSum.getStationId());
            splmwcoalloadSumList = splmwcoalloadsumMapper.getNoSatisfiedSplmwcoalloadSumList(splMap);
        }catch (Exception e){

        }
        return splmwcoalloadSumList;
    }

    @Override
    public StationTotalplanSum setScoalCompleteTotalOver(StationTotalplanSum totalplan, SkyLightDays skyLightDays, SkyLightDayPlan skyLightDayPlan, ScoaltloaddayCompleteSum scoalCompleteSum) {
        try {
            Integer skySum = Integer.parseInt(skyLightDayPlan.getSkySum());
            Integer noSkySum = Integer.parseInt(skyLightDayPlan.getNoSkySum());
            Integer skyDays = skyLightDays.getSkyDays();
            Integer noSkyDays = skyLightDays.getNoSkyDays();

            Integer planCompleteNum = skySum*skyDays + noSkySum*noSkyDays;
            Integer totalPlanSum = Integer.parseInt(scoalCompleteSum.getLoadCompleteSum());
            totalplan.setTotalOver(totalPlanSum - planCompleteNum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return totalplan;
    }

    @Autowired
    private SkyLightMapper skyLightMapper;
    @Autowired
    private SplmwdirpathMapper splmwdirpathMapper;
    @Autowired
    private SplmmspliteditMapper splmmspliteditMapper;
    @Autowired
    private SplmwcoalloadsumMapper splmwcoalloadsumMapper;
    @Autowired
    private SplmwdiversionSumMapper splmwdiversionSumMapper;
    @Autowired
    private SplmwprodmktmatchMapper splmwprodmktmatchMapper;
    @Autowired
    private ScoaltloaddayCompleteMapper scoaltloaddayCompleteMapper;
}