package org.hyacinth.examples.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class CheckBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object obj)
			throws Throwable {
		System.out.println("Car check before move...");

	}

}
