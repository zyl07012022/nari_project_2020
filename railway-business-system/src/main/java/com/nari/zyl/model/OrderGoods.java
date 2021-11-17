package com.nari.zyl.model;

import lombok.Data;

@Data
public class OrderGoods {
    private String orderGoodsId;
    private String orderId;
    private String goodsTypeId;
    private String goodsId;
    private String goodsTypeName;
    private String goodsName;
    private String coalId;
    private String coalName;
    private String coalType;
}
