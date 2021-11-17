package com.nari.zyl.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ScoaltloaddayComplete {
    private String tId;
    private String organizationId;
    private String loadTypeId;
    private String coalSourceId;
    private String tDate;
    private String loadLineId;
    private String updateTime;
    private String loadComplete;
    private String stationId;
}
