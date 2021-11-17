package com.nari.zyl.controller;

import com.alibaba.fastjson.JSONObject;
import com.nari.zyl.entities.CommonResult;
import com.nari.zyl.facade.AreaFlowFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/areaflow")
public class AreaFlowController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private AreaFlowFacade areaFlowFacade ;

    //1.第一步,区内、特殊流向分配
    @ResponseBody
    @RequestMapping(value = "/compute",method = RequestMethod.POST)
    public CommonResult compute(@RequestBody JSONObject jsonObject){
        CommonResult commonResult =new CommonResult();
        int result = 0;
        try {
            String wPId = jsonObject.getString("wPId");
            result = areaFlowFacade.compute(wPId);
        }catch (Exception e){
            e.printStackTrace();
        }

        commonResult.setCode(result);
        if(result == 1) {
            commonResult.setMessage("成功！");
        }else{
            commonResult.setMessage("失败！");
        }

        return commonResult;
    }

    //第二步, 判断是否满足销售需求，数据补充
    @ResponseBody
    @RequestMapping(value = "/confirmSaleDemand",method = RequestMethod.POST)
    public CommonResult confirmSaleDemand(){
        CommonResult commonResult =new CommonResult();
        int result = 0;
        try {
            result = areaFlowFacade.confirmSaleDemand();
        }catch (Exception e){
            e.printStackTrace();
        }
        commonResult.setCode(result);
        if(result == 1) {
            commonResult.setMessage("成功！");
        }else{
            commonResult.setMessage("失败！");
        }
        return commonResult;
    }

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(serverURL+"/payment/nacos/"+id,String.class);
    }
}
