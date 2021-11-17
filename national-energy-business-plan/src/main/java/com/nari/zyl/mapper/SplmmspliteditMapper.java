package com.nari.zyl.mapper;

import com.nari.zyl.model.SkyLightDayPlan;
import com.nari.zyl.model.Splmmsplitedit;
import com.nari.zyl.model.SplmwdiversionSum;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SplmmspliteditMapper {

    //查询 s_plm_m_splitedit 获取天窗和非天窗数据
    Splmmsplitedit getSplmmspliteditListByCondition(SplmwdiversionSum splSum);

    //根据站点,单位，任务id, 查询天窗数据
    SkyLightDayPlan getSplmmspliteditByCondition(Map<String,Object> paramMap);

}
