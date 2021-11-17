package com.nari.zyl.model;

import lombok.Data;

import java.util.List;

@Data
public class RailwayInfo {
    private Long orderId;
    private Long userIdApply;
    private String  userNameApply;
    private String  orderType;
    private String  orderState;
    private String  planLoad;
    private String  isConstruct;
    private String  consignorId;
    private String  consignorName;
    private String  consignorCode;
    private String  consignorShortName;
    private String  consignorAgentId;
    private String  consigneeId;
    private String  consigneeName;
    private String  consigneeAgentId;
    private String  stationIdDep;
    private String  stationNameDep;
    private String  stationIdArr;
    private String  stationNameArr;
    private String  stationIdArrEnd;
    private String  stationNameArrEnd;
    private String  selfOutType;
    private String  trainNumRequest;
    private String  trainNumApprove;
    private String  trainNumConstruct;
    private String  trainNumNoConstruct;
    private String  trainNumWeekPlan;
    private String  trainNumWeekPlanCompare;
    private String  trainNumPlanCompare;
    private String  remark;
    private String  specialRequest;
    private String  carModel;
    private String  carModelTypeApprove;
    private String  siteNames;
    private String  goodsNames;
    private String  flowDirectionNames;
    private List<OrderSpecialRequest>  orderSpecialRequestList;
    private List<OrderGoods>  orderGoodsList;
    private List<OrderSite>  orderSiteList;
    private List<OrderFlowDirection> orderFlowDirectionList;
    private Integer  lineSeq;
    private Integer  stationSeq;
    private String  payerId;
    private String  paymentType;
    private String  payCode;
    private String  stationLoadStatisticInfo;
    private String  coalCompany;
    private String  consigneeSiteId;
    private String  consigneeSiteName;
    private Integer  clientSeq;
    private Boolean  isManage;
}
