package org.alerts.service;

import org.alerts.entity.Alert;
import org.alerts.utilities.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceAlertTask implements Runnable {
    private final Alert alert;
    private final Fetcher priceFetcher;

    @Autowired
    public PriceAlertTask(Alert alert, Fetcher priceFetcher) {
        this.alert = alert;
        this.priceFetcher = priceFetcher;
    }

    @Override
    public void run() {
        checkPrice();
    }

    private void checkPrice() {
        double currentPrice = priceFetcher.fetchCurrentPrice(alert.getProductUrl());

        if (currentPrice <= alert.getDesiredPrice()) {
            sendNotification(alert.getProductUrl(), currentPrice, alert.getDesiredPrice());
        }
    }

    private void sendNotification(String productUrl, Double currentPrice, Double desiredPrice) {

        new Email().sendPriceAlert(productUrl, currentPrice, desiredPrice);
    }
}