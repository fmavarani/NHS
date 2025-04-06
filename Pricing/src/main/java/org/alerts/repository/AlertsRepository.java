package org.alerts.repository;

import org.alerts.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertsRepository extends JpaRepository<Alert, Long> {
}