package org.alerts.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class PriceAlertTaskFactoryTest {
    @Autowired
    private PriceAlertTaskFactory factory;

    @Test
    void testFactoryInitialization() {
        assertNotNull(factory);
        Fetcher fetcher = new Fetcher(mock(RestTemplate.class));
        PriceAlertTaskFactory factory = new PriceAlertTaskFactory(fetcher);
        assertNotNull(factory);
    }
}
