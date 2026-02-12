package com.lol.stats.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RiotApiConfig {

    @Value("${riot.api.key}")
    private String apiKey;

    @Value("${riot.api.base.url}")
    private String baseUrl;

    @Value("${riot.api.americas.url}")
    private String americasUrl;

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getAmericasUrl() {
        return americasUrl;
    }
}
