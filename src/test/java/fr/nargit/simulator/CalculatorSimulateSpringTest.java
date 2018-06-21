package fr.nargit.simulator;

import fr.nargit.spring.ApplicationConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * (c) Swissquote 21-juin-2018
 *
 * @author Tigran
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class CalculatorSimulateSpringTest {

	@Autowired
	@Qualifier("withSimulationCalculator")
	private Calculator calculator;

	@Test
	@DisplayName("Simulated 1 + 1 = 0")
	void addWithSpring() {
		int actual = calculator.add(1, 2);
		Assertions.assertEquals(0, actual);
	}
}
