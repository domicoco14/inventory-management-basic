package com.inventory.frontend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
class BackendConfig {

    /** One client for the reporting backend, pointed at the address in application.properties. */
    @Bean
    RestClient backendRestClient(@Value("${inventory.backend.base-url}") String baseUrl) {
        SimpleClientHttpRequestFactory requests = new SimpleClientHttpRequestFactory();
        requests.setConnectTimeout(Duration.ofSeconds(2));
        requests.setReadTimeout(Duration.ofSeconds(8));
        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(requests)
                .build();
    }
}
