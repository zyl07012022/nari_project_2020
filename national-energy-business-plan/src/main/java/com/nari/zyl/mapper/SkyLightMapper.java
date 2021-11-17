package com.nari.zyl.mapper;

import com.nari.zyl.model.SkyLight;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface SkyLightMapper {

    List<SkyLight> getSkylightDate(Timestamp timestamp);

    int getskyDaysByMonth(String month);
}
