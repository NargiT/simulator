package fr.nargit.simulator.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * (c) Swissquote 20-juin-2018
 *
 * @author Tigran
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Simulable {

	/**
	 * @return Level of logging {@link Level}
	 * @see SimulableInvocationHandler
	 */
	Level display() default Level.OBFUSCATE;

	/**
	 * @return unique identifier for simulation across all application
	 */
	String identifier();

}