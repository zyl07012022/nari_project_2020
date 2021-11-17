package com.nari.zyl.kafka.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nari.zyl.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    private Gson gson = (Gson) new GsonBuilder().create();

    public void send()
    {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
        //topic-ideal为主题
        kafkaTemplate.send("topic-ideal", gson.toJson(message));
    }

}