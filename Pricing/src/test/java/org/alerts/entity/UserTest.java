package org.alerts.entity;

import org.alerts.entity.Alert;
import org.alerts.entity.User;
import org.alerts.repository.AlertsRepository;
import org.alerts.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserTest {

    @Autowired
    private AlertsRepository alertsRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldMaintainUserRelationship() {
        User user = new User();
        user.setFirstName("John"); // Required
        user.setLastName("Doe");   // Required
        user.setEmail("rel@example.com");
        user = userRepository.saveAndFlush(user); // Immediate flush

        Alert alert = new Alert();
        alert.setProductUrl("http://linked.com");
        alert.setDesiredPrice(12.34);
        alert.setCron("0 * * * * *");
        alert.setUser(user);
        alertsRepository.saveAndFlush(alert);

        User fetched = userRepository.findById(user.getId()).orElseThrow();
        assertEquals(1, fetched.getListAlerts().size());
    }
}
