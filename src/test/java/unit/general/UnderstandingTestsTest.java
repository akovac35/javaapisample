package unit.general;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import unit.UnitTestConfiguration;

/*
 * In JUnit, each test method is executed in a new instance of the test class,
 * so each test is initialized from scratch and does not share any context with other tests
 */

@SpringBootTest
@ContextConfiguration(classes = UnitTestConfiguration.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class UnderstandingTestsTest {
    
    private int value = 0;

    @Test
    public void increaseValue1() {
        value++;
        Assert.isTrue(value == 1, "value should be 1");
    }

    @Test
    public void increaseValue2() {
        value++;
        Assert.isTrue(value == 1, "value should be 1");
    }
}
