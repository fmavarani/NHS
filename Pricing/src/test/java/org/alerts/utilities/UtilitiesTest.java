package org.alerts.utilities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UtilitiesTest { // ❌ Remove @SpringBootTest
    @Test
    void testPriceFormat() {
        double result = Utilities.roundToTwoDecimalPlaces(123.4567);
        assertEquals(123.46, result);
    }

    @Test
    void timeStringToCron() {
        String result = Utilities.timeStringToCron("09:30");
        assertEquals("0 30 9 * * ?", result);
    }
}

