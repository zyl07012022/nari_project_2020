package com.nari.zyl.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SkyLightDayPlan {

    private String wPId;

    private String stationId;

    private String skySum;

    private String noSkySum;
}
