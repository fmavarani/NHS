package org.alerts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PricingApplication.class) // Explicitly load context
public class PricingApplicationTest {
    @Test
    void contextLoads() {} // Passes if context initializes

    @Test
    void applicationStarts() {
        PricingApplication.main(new String[]{}); // No assertion needed
    }
}