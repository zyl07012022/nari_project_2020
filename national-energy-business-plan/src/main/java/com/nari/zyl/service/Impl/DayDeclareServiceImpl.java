package com.nari.zyl.service.Impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.nari.zyl.entities.CommonResult;
import com.nari.zyl.service.DayDeclareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DayDeclareServiceImpl implements DayDeclareService {

    @Autowired
    private RestTemplate restTemplate;

    //外部接口
    public static final String DECLARE_URL = "http://localhost:8088";

    //处理接口返回数据,解析,将相关数据导入相关申报表里
    @Override
    public CommonResult manageAllAuditDayPlanInfo(JSONObject jsonObject)
    {
        CommonResult result= restTemplate.postForObject(DECLARE_URL + "/RailwayBusiness/getAllAuditDayPlanInfo", jsonObject, CommonResult.class);
        //解析数据
        if(result.getCode() == 200)
        {
            JSONArray a = new JSONArray(result.getData());
            result.setCode(1);
            result.setMessage("成功！");
        }else{
            result.setCode(0);
            result.setMessage("失败！");
        }
        return result;
    }
}
