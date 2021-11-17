package com.nari.zyl.controller;

import cn.hutool.json.JSONObject;
import com.nari.zyl.entities.CommonResult;
import com.nari.zyl.service.DayDeclareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dayDeclare")
public class DayDeclareController {

    //铁路系统对接接口:
    @Autowired
    private DayDeclareService dayDeclareService;

    //kafka->获取所有待审批的一体化日计划->处理
    @PostMapping("/manageAllAuditDayPlanInfo")
    public CommonResult manageAllAuditDayPlanInfo(@RequestBody JSONObject jsonObject) {
        return dayDeclareService.manageAllAuditDayPlanInfo(jsonObject);
    }
}
