package com.nari.zyl.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class KafkaConsumer {

    //1.主题(topic-ideal)消息接收
    @KafkaListener(topics = {"topic-ideal"})
    public void consumer(ConsumerRecord<?, ?> record)
    {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent())
        {
            Object message = kafkaMessage.get();
            log.info("------------------ topic-ideal------------------- " );
            log.info("----------------- record =" + record);
            log.info("------------------ message =" + message);
        }
    }

    //2.主题(test)消息接收
    @KafkaListener(topics = {"test"})
    public void receive(ConsumerRecord<?, ?> record)
    {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent())
        {
            Object message = kafkaMessage.get();
            log.info("------------------ test ----------------------------");
            log.info("----------------- record =" + record);
            log.info("------------------ message =" + message);
        }
    }
}