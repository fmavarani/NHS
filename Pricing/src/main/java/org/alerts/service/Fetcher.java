package org.alerts.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.HashMap;

@Service
public class Fetcher {
    private final RestTemplate restTemplate;

    public Fetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double fetchCurrentPrice(String apiUrl) {
        try {

            Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class);

            if (response != null && response.containsKey("price")) {
                return Double.parseDouble(response.get("price").toString());
            }
        } catch (Exception e) {
            System.err.println("Failed to fetch price from API for " + apiUrl + ": " + e.getMessage());
        }

        // Fallback to mock data
        return Double.MAX_VALUE;
    }

}