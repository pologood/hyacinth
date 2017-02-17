package org.hyacinth.examples.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class CarCheckAspect {
	
	@Before("execution(* move(..))")
	public void carCheckBeforeMove() {
		System.out.println("Car check before move using AspectJ...");
	}
}
