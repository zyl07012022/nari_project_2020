package com.nari.zyl.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class SkyLightDay {

    private Boolean skyLight;

    private Date day;

    private String week;

    private String monthAndDay;

    private String planNum;

}
