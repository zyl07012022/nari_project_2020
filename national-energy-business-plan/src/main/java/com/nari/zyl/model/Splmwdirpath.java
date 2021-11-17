package com.nari.zyl.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Splmwdirpath {

    private String categoriesId;

    private String loadstationId;

    private String destinationId;

    private String trptdirecId;    //首段流向

    private String etrptdirecId;    //终端流向

    private String srailwayrangeId;  //首段管段

    private String erailwayrangeId;     //终端管段

}
