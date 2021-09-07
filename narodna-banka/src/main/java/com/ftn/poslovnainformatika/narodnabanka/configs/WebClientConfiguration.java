package com.ftn.poslovnainformatika.narodnabanka.configs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.ftn.poslovnainformatika.narodnabanka.security.SecurityConfiguration;

import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfiguration {
	
private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);
	
	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.filter(logRequest())
				        .filter(logResponse())
				        .build();
	}
	
	private static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("Response status: {}", clientResponse.statusCode());
            clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientResponse);
        });
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }
    
    @Bean(name = "poslovneBankeServices")
    public Map<Integer, String> poslovneBankeServices() {
    	Map<Integer, String> map = new HashMap<>();
    	map.put(100, "http://localhost:8081/api");
    	map.put(120, "http://localhost:8082/api");
    	return Collections.unmodifiableMap(map);
    }

}
