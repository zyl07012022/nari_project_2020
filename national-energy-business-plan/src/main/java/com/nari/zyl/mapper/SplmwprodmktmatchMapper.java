package com.nari.zyl.mapper;

import com.nari.zyl.model.Splmwprodmktmatch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SplmwprodmktmatchMapper
{
    //根据条件删除s_plm_w_prodmktmatch对应的数据
    int deleteSplmwprodmktmatchByCondition(Map<String,Object> paramMap);

    //将得出的产能计划计划运力结果，写入s_plm_w_prodmktmatch
    int insertSplmwprodmktmatch(Splmwprodmktmatch splmatch);

    int updateSplmwprodmktmatch(Map<String, Object> splMap);
}
