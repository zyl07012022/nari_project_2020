package com.nari.zyl.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SplmwcoalloadSum {
    private String tId;
    private String wpId;
    private String splmwtaskId;
    private String declareId;
    private String plantypeId;
    private String areainoroutId;
    private String datastatus;
    private String orgId;
    private String coalsourId;

    private String mineId;
    private String stationId;
    private String loadlineId;
    private String loadorconnId;
    private String coaltypeId;
    private String traintypeId;
    private String forbakwardId;
    private String trptdirecId;

    private String demarcationId;
    private String destinationId;
    private String loadCost;
    private String planTime;
    private String totalPlan;
    private String evaluationVal;
    private String railwayline;
}
