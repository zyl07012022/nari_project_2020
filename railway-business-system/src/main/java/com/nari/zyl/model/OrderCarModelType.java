package com.nari.zyl.model;

import lombok.Data;

@Data
public class OrderCarModelType {
    private String orderId;
    private Integer flowDirectionId;
    private String  carModelType;
    private Integer  trainCountRequest;
    private Integer  trainCountApprove;
    private String  planApprove;
    private String  userIdApprove;
    private String  userNameApprove;
}
