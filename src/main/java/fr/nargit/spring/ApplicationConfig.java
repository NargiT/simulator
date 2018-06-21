package fr.nargit.spring;

import fr.nargit.simulator.Calculator;
import fr.nargit.simulator.CalculatorDefault;
import fr.nargit.simulator.PropertiesSimulableConfig;
import fr.nargit.simulator.core.SimulableConfig;
import fr.nargit.simulator.core.SimulableInvocationHandler;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.util.Properties;

/**
 * (c) Swissquote 21-juin-2018
 *
 * @author Tigran
 */
@Configuration
public class ApplicationConfig {

	@Bean
	public Calculator defaultCalculator() {
		return new CalculatorDefault();
	}

	@Autowired
	ResourceLoader resourceLoader;

	@Bean
	public SimulableConfig fileSimulationConfig() throws IOException {
		Resource resource = resourceLoader.getResource("classpath:simulation.properties");
		Properties properties = new Properties();
		properties.load(resource.getInputStream());
		return new PropertiesSimulableConfig(properties);
	}

/*
	@Bean
	public ITConfig itConfigSimulationConfig() {
		return new ITConfig();
	}
*/

	@Bean
	public InvocationHandler calculatorInvocationHandler() throws IOException {
		return new SimulableInvocationHandler<>(defaultCalculator(), fileSimulationConfig());
	}

	@Bean
	public FactoryBean<Calculator> calculatorFactoryBean() throws IOException {
		return new SimulationFactoryBean<>(calculatorInvocationHandler(), Calculator.class);
	}

	@Bean
	public Calculator withSimulationCalculator() throws Exception {
		return calculatorFactoryBean().getObject();
	}

}
