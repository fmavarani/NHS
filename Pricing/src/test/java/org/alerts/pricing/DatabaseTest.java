package org.alerts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class DatabaseTest {
    @Test
    void basicDatabaseTest() {
        assertTrue(true);
    }
}
