package com.nari.zyl.mapper;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SplmcycleMapper {

    //判断是否为天窗非天窗数据
    Boolean IsSkyOrNoSkyData(Map<String,Object> paramMap);

}
