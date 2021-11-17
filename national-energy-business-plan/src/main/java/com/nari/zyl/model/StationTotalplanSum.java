package com.nari.zyl.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StationTotalplanSum {

    private String wPId;

    private String stationId;

    private String orgId;

    private String totalPlanSum;

    private String startDate;

    private Integer totalOver;      //合计超欠量
}
