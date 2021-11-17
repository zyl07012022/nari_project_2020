package com.nari.zyl.service;

import cn.hutool.json.JSONObject;
import com.nari.zyl.entities.CommonResult;

public interface DayDeclareService {

    CommonResult manageAllAuditDayPlanInfo(JSONObject jsonObject);

}
