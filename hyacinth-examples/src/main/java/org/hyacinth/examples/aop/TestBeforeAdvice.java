package org.hyacinth.examples.aop;

import org.hyacinth.examples.proxy.Car;
import org.springframework.aop.framework.ProxyFactory;

public class TestBeforeAdvice {
	public static void main(String[] args) {
		Car car = new Car();
		CheckBeforeAdvice ccar = new CheckBeforeAdvice();
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(car);
		pf.addAdvice(ccar);
		Car proxy = (Car)pf.getProxy();
		proxy.move("biubiu....");
		proxy.moveLikeTrain();
	}
}
