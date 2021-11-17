package com.nari.zyl.config;

import com.nari.zyl.kafka.producer.KafkaProducer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }

}
