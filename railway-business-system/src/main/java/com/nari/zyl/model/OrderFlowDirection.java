package com.nari.zyl.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderFlowDirection {

    private Integer flowDirectionId;
    private String flowDirectionName;
    private Integer stationIdArr;
    private Integer trainCount;
    private String userId;
    private String userName;
    private String approve;
    private String pathId;
    private String pathName;
    private List<OrderCarModelType> orderCarModelTypeList;

}
