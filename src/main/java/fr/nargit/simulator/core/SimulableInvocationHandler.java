package fr.nargit.simulator.core;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * <p>
 * Invocation handler allow to treat {@link Simulable} annotation with the help of {@link SimulableConfig}
 * <br />
 * By default if no {@link SimulableConfig} is provided, all identifiers are simulated
 * </p>
 *
 * @author Tigran
 */
@Slf4j
@Setter
public class SimulableInvocationHandler<T> implements InvocationHandler {

	private SimulableConfig config;
	private T targetImpl;
	private Runnable runnable;

	public SimulableInvocationHandler(T targetImpl) {
		this(targetImpl, identifier -> true);
	}

	public SimulableInvocationHandler(T targetImpl, SimulableConfig config) {
		this.config = config;
		this.targetImpl = targetImpl;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		boolean annotationPresent = method.isAnnotationPresent(Simulable.class);
		if (annotationPresent) {
			Simulable annotation = method.getAnnotation(Simulable.class);
			if (config.isUnderSimulation(annotation.identifier())) {
				log.info(String.format("[SIMULATION ACTIVE] call %s with arguments names %s values %s",
						method.getName(),
						Arrays.toString(method.getParameters()),
						Arrays.toString(args)));
				log.debug(String.format("[SIMULATION CONFIG] display mode [%s] ", annotation.display()));
				log.debug(String.format("[SIMULATION CONFIG] identifier [%s] ", annotation.identifier()));
			}
			// TODO: find a way to inject handle neutral value for all
			if (method.getReturnType().isPrimitive()) {
				return 0;
			}
			return null;
		} else {
			return method.invoke(targetImpl, args);
		}
	}
}
