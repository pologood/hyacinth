package org.hyacinth.examples.proxy.cglibProxy;

import org.hyacinth.examples.proxy.Car;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CglibProxy proxy = new CglibProxy();
		Car car = (Car)proxy.getProxy(Car.class);
		car.moveLikeTrain();
		car.move("wuwuwu....");
		car.getClassLoader();
	}

}
