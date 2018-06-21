package fr.nargit.simulator.core;

/**
 * Tells if for a given {@link Simulable#identifier()} the simulation is active or not
 *
 * @author Tigran
 */
public interface SimulableConfig {

	boolean isUnderSimulation(String identifier);
}
