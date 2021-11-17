package com.nari.zyl.controller;

import cn.hutool.json.JSONObject;
import com.nari.zyl.entities.CommonResult;
import com.nari.zyl.service.RailwayBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/RailwayBusiness")
public class RailwayBusinessController {

    @Autowired
    private RailwayBusinessService railwayBusinessService;

    //1.模拟铁路系统获取json数据
    @PostMapping("/getAllAuditDayPlanInfo")
    public CommonResult getAllAuditDayPlanInfo(@RequestBody JSONObject jsonObject) {
        return railwayBusinessService.getAllAuditDayPlanInfo();
    }
}
