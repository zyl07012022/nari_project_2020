package com.nari.zyl.service;

import com.nari.zyl.model.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface AreaFlowService {

    //第一步：获取区内流向数据。
    List<SplmwdiversionSum> getSplmwdiversionSumData(String wPId, String areainoroutId);

    //获取优先级最大的流向表的数据
    Splmwdirpath getSplmwdirpathTopDataByCondition(SplmwdiversionSum splSum);

    //更新流向数据
    int updateSplmwdiversionSumData(SplmwdiversionSum splSum);

    //获取流向分解计划量数据(天窗非天窗) 只会获得一条
    Splmmsplitedit getSplmmspliteditByCondition(SplmwdiversionSum splSum);

    List<SkyLightDay> getSplmwdiversionSumSenvenData(Splmmsplitedit s);

    //获取煤源计划量数据
    List<SplmwcoalloadSum> getSplmwcoalloadSumListByCondition(SplmwdiversionSum splSum);

    //实际产能数据和流向分解计划量数据进行比较并取小
    List<Splmwprodmktmatch> compareSplmmspliteditData(SplmwdiversionSum splSum,List<SkyLightDay> skyLightDayList, List<SplmwcoalloadSum> splmwcoalloadSumList);

    //根据条件删除s_plm_w_prodmktmatch对应的数据,
    int deleteSplmwprodmktmatchByCondition(SplmwdiversionSum splSum);

    //将得出的产能计划运力结果，写入s_plm_w_prodmktmatch
    int insertSplmwprodmktmatch(SplmwdiversionSum splSum,List<Splmwprodmktmatch> splprodmktmatches);

    //写入源数据(源申报数据减去-写入产销匹配数据表的数据),重新生成新的id
    int manageResourceData(List<SplmwcoalloadSum> splmwcoalloadSumList, List<Splmwprodmktmatch> splprodmktmatches);

    //第二步:
    List<StationTotalplanSum> getStationTotalplanSumByStationId();

    SplmwdiversionStationSum getSplmwdiversionSumDataByCondition(String station_id, String org_id);

    ScoaltloaddayCompleteSum getScoaltloaddayCompleteSum(String org_id, String station_id, String startDate);

    ////天窗日、非天窗日均计划量
    SkyLightDayPlan getSkyLightDayPlan(StationTotalplanSum stationTotalplan);

    //任务开始时间所在月份，共有几天天窗日、非天窗日
    SkyLightDays getSkyLightDays(String wpId, String startDate) throws ParseException;

    List<SplmwcoalloadSum> getNoSatisfiedSplmwcoalloadSumList(StationTotalplanSum stationTotalplanSum);

    StationTotalplanSum setScoalCompleteTotalOver(StationTotalplanSum totalplan, SkyLightDays skyLightDays, SkyLightDayPlan skyLightDayPlan, ScoaltloaddayCompleteSum scoalCompleteSum);

    //将得出的产能计划运力结果，更新s_plm_w_prodmktmatch表
    int updateSplmwProdmktmatchData(List<Splmwprodmktmatch> prodmktmatchList);

    List<Splmwprodmktmatch> compareAndGetMoreOrLessValueList(StationTotalplanSum totalplan,List<SkyLightDay> skyLightDayList, List<SplmwcoalloadSum> coalloadList);

}
