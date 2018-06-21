package fr.nargit.spring;

import fr.nargit.simulator.Calculator;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * (c) Swissquote 21-juin-2018
 *
 * @author Tigran
 */
public class SimulationFactoryBean<T> extends AbstractFactoryBean<T> {

	private InvocationHandler invocationHandler;
	private Class<T> clazz;

	public SimulationFactoryBean(InvocationHandler invocationHandler, Class<T> clazz) {
		this.invocationHandler = invocationHandler;
		this.clazz = clazz;
	}

	@Override
	public Class<?> getObjectType() {
		return Calculator.class;
	}

	@Override
	protected T createInstance() {
		return (T) Proxy.newProxyInstance(
				this.getClass().getClassLoader(),
				new Class[]{clazz},
				invocationHandler);
	}
}
