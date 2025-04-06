package org.alerts.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = FetcherTest.Config.class)
public class FetcherTest {

    @Configuration
    static class Config {
        @Bean
        public RestTemplate restTemplate() {
            RestTemplate template = mock(RestTemplate.class);
            when(template.getForObject("http://api.com", Map.class))
                .thenReturn(Map.of("price", 99.99));
            return template;
        }

        @Bean
        public Fetcher fetcher(RestTemplate template) {
            return new Fetcher(template);
        }
    }

    @Test
    void shouldReturnPriceFromApi() {
        Fetcher fetcher = new Config().fetcher(new Config().restTemplate());
        double price = fetcher.fetchCurrentPrice("http://api.com");
        assertEquals(99.99, price);
    }
}
