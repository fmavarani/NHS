package org.alerts.config;

import org.alerts.PricingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PricingApplication.class)
public class AppConfigTest {
    @Test
    void contextLoads() { // No assertion needed if context loads
    }
}