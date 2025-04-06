package org.alerts.controller;

import org.alerts.entity.Alert;
import org.alerts.service.AlertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.alerts.utilities.Utilities;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertsService alertsService;

    @PostMapping
    public ResponseEntity<String> createAlert(
            @RequestParam String productUrl,
            @RequestParam Double desiredPrice,
            @RequestParam String checkTime,
            @RequestParam Long userId) {

        String cronExpression = Utilities.timeStringToCron(checkTime);
        Alert alert = Alert.builder()
                .productUrl(productUrl)
                .desiredPrice(desiredPrice)
                .cron(cronExpression)
                .build();

        Alert savedAlert = alertsService.createOrUpdate(alert);
        return ResponseEntity.ok("Alert created successfully with ID: " + savedAlert.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alert> getAlert(@PathVariable Long id) {
        Alert alert = alertsService.getAlertById(id);
        return ResponseEntity.ok(alert);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlert(@PathVariable Long id) {
        alertsService.deleteAlert(id);
        return ResponseEntity.ok("Alert deleted successfully");
    }
}