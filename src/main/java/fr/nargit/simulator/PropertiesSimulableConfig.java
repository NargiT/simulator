package fr.nargit.simulator;

import fr.nargit.simulator.core.SimulableConfig;

import java.util.Properties;

/**
 * (c) Swissquote 21-juin-2018
 *
 * @author Tigran
 */
public class PropertiesSimulableConfig implements SimulableConfig {


	private Properties properties;

	public PropertiesSimulableConfig(Properties properties) {
		this.properties = properties;
	}

	/**
	 * @param identifier identify a given simulation
	 * @return true if the simulation is active or identifier not found, otherwise false
	 */
	@Override
	public boolean isUnderSimulation(String identifier) {
		String property = properties.getProperty(identifier, "true");
		return Boolean.valueOf(property);
	}
}
