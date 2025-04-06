package org.alerts;

import org.alerts.entity.Alert;
import org.alerts.entity.User;
import org.alerts.service.AlertsService;
import org.alerts.service.PriceAlertTask;
import org.alerts.service.PriceAlertTaskFactory;
import org.alerts.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = PricingApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@Import(SchedulerTest.Config.class)
public class SchedulerTest {

    @Autowired
    private Scheduler scheduler;

    @MockBean // 🔧 Replace @Autowired with @MockBean
    private AlertsService alertsService;

    @MockBean
    private PriceAlertTaskFactory factory;

    @BeforeEach
    void setup() {
        when(factory.create(any(Alert.class))).thenReturn((PriceAlertTask) mock(Runnable.class));
    }

    @Test
    void shouldScheduleAndCancelTasks() {
        User user = User.builder().lastName("dd").firstName("ss").email("<EMAIL>").build();
        Alert alert = Alert.builder().desiredPrice(1.1).productUrl("www.google.com").user(user).cron("0 * * * * *").build();
        scheduler.createAlert(alert);
        assertFalse(scheduler.getScheduledTasks().isEmpty());
        scheduler.stopTask(alert.getId());
        assertTrue(scheduler.getScheduledTasks().isEmpty());
    }

    @Test
    void shouldHandleDuplicateAlertCreation() {
        User user = User.builder().lastName("dd").firstName("ss").email("<EMAIL>").build();
        Alert alert = Alert.builder().desiredPrice(1.1).productUrl("www.google.com").user(user).cron("0 * * * * *").build();
        scheduler.createAlert(alert);
        scheduler.createAlert(alert);
        assertEquals(1, scheduler.getScheduledTasks().size());
    }

    @TestConfiguration
    static class Config {

        @Bean
        public AlertsService alertsService() {
            return mock(AlertsService.class);
        }

        @Bean
        public UserService userService() {
            return mock(UserService.class);
        }

        @Bean
        public PriceAlertTaskFactory factory() {
            return mock(PriceAlertTaskFactory.class);
        }

        @Bean
        public Scheduler scheduler(PriceAlertTaskFactory factory) {
            Scheduler s = new Scheduler(factory);
            s.setAlertsService(alertsService());
            return s;
        }
    }
}
