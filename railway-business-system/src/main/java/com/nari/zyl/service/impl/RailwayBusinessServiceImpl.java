package com.nari.zyl.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nari.zyl.entities.CommonResult;
import com.nari.zyl.model.RailwayInfo;
import com.nari.zyl.service.RailwayBusinessService;
import com.nari.zyl.utils.JsonUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
@JsonIgnoreProperties
public class RailwayBusinessServiceImpl implements RailwayBusinessService {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public CommonResult getAllAuditDayPlanInfo()
    {
        CommonResult commonResult = new CommonResult();
        try {
            BufferedReader br = new BufferedReader(new FileReader("E:/IdeaWorkSpace/nari-project-2020/railway-business-system/src/main/resources/json/RailwayInfo.json"));//存放读进来的数据
            StringBuffer message = new StringBuffer();
            String line = null;
            while((line = br.readLine()) != null)
            {
                message.append(line);
            }
            System.out.println(message);
            // 创建一个包含原始json串的jsondate(Json数据)对象
            JSONConfig jsonConfig = JSONConfig.create().setIgnoreNullValue(false);
            JSONObject res = new JSONObject(message,jsonConfig);

            commonResult.setCode(200);
            commonResult.setMessage("返回数据成功!");
            String arr = res.get("data").toString();
            List<RailwayInfo> railwayInfoList = JsonUtil.jsonToList(arr, RailwayInfo.class);
            commonResult.setData(railwayInfoList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commonResult;
    }
}
