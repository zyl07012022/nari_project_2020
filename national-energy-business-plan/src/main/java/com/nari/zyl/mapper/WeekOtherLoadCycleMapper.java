package com.nari.zyl.mapper;

import com.nari.zyl.model.Cycle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface WeekOtherLoadCycleMapper {

    Cycle getPlanById(@Param("wPId") String wPId);

}
