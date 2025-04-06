package org.alerts.service;

import org.alerts.entity.Alert;
import org.alerts.entity.User;
import org.alerts.repository.AlertsRepository;
import org.alerts.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AlertsServiceTest {

    @MockBean
    private AlertsRepository alertsRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private AlertsService alertsService;

    @Test
    void shouldCreateAlertAndPersist() {
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test@example.com");

        Alert alert = new Alert();
        alert.setUser(user);
        alert.setCron("0 0 * * * *");
        alert.setProductUrl("http://example.com");
        alert.setDesiredPrice(99.99);

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(alertsRepository.save(any(Alert.class))).thenReturn(alert);

        Alert saved = alertsService.createOrUpdate(alert);
        assertNotNull(saved);
    }


}