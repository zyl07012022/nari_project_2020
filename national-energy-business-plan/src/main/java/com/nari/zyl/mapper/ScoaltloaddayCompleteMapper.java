package com.nari.zyl.mapper;

import com.nari.zyl.model.ScoaltloaddayCompleteSum;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ScoaltloaddayCompleteMapper {

    ScoaltloaddayCompleteSum getScoaltloaddayCompleteSum(Map<String,Object> paramMap);

}
