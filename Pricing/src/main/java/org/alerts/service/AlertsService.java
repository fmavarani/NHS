package org.alerts.service;

import org.alerts.entity.Alert;
import org.alerts.repository.AlertsRepository;
import org.alerts.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertsService {

    @Autowired
    private AlertsRepository alertsRepository;


    public Alert getAlertById(Long id) {
        return alertsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found with id: " + id));
    }

    public Alert createOrUpdate(Alert alert) {
        Alert savedAlert = alertsRepository.save(alert);
        return savedAlert;
    }

    public void deleteAlert(Long id) {
        alertsRepository.deleteById(id);
    }
}