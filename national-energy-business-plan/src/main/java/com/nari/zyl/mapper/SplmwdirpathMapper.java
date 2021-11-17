package com.nari.zyl.mapper;

import com.nari.zyl.model.Splmwdirpath;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public interface SplmwdirpathMapper {

    //流向路径表查询优先级最大的一条数据，
    Splmwdirpath getSplmwdirpathTopDataByCondition(Map<String,Object> paramMap);

}
