package fr.nargit.simulator;

import fr.nargit.simulator.core.SimulableInvocationHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorSimulateTest {

	private Calculator calculator;

	@BeforeEach
	void setUp() {
		SimulableInvocationHandler simulableInvocationHandler = new SimulableInvocationHandler<Calculator>(
				new CalculatorDefault(),
				identifier -> false);
		calculator = (Calculator) Proxy.newProxyInstance(
				this.getClass().getClassLoader(),
				new Class[]{Calculator.class},
				simulableInvocationHandler);
	}

	@AfterEach
	void tearDown() {
		calculator = null;
	}

	@Test
	@DisplayName("Simulated 1 + 1 = 2")
	void addsTwoNumbers() {
		int actual = calculator.add(1, 1);
		assertEquals(0, actual, "1 + 1 is simulated");
	}

	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvSource({
			"0,    1,   0",
			"1,    2,   0",
			"49,  51, 0",
			"1,  100, 0"
	})
	void add(int first, int second, int expectedResult) {
		assertEquals(expectedResult, calculator.add(first, second),
				() -> first + " + " + second + " should equal " + expectedResult);
	}
}