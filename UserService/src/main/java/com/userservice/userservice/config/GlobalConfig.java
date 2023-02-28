package com.userservice.userservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GlobalConfig {

    @Bean
    @LoadBalanced //Annotation to mark a RestTemplate or WebClient bean to be configured to use a LoadBalancerClient.
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
