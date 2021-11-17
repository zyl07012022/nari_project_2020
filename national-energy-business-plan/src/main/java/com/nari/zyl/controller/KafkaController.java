package com.nari.zyl.controller;

import cn.hutool.extra.tokenizer.Result;
import com.alibaba.fastjson.JSONObject;
import com.nari.zyl.entities.CommonResult;
import com.nari.zyl.kafka.producer.KafkaProducer;
import com.sun.net.httpserver.Authenticator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @ResponseBody
    @RequestMapping(value = "/send",method = RequestMethod.POST)
    public CommonResult send()
    {
        CommonResult commonResult =new CommonResult();
        kafkaProducer.send();
        commonResult.setCode(0);
        return commonResult;
    }
}
