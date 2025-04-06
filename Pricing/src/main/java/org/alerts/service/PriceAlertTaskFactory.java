package org.alerts.service;

import org.alerts.entity.Alert;
import org.springframework.stereotype.Component;

@Component
public class PriceAlertTaskFactory {
    private final Fetcher priceFetcher;

    public PriceAlertTaskFactory(Fetcher priceFetcher) {
        this.priceFetcher = priceFetcher;
    }

    public PriceAlertTask create(Alert alert) {
        return new PriceAlertTask(alert, priceFetcher);
    }
}