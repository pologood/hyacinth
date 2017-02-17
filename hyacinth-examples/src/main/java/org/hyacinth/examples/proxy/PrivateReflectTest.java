package org.hyacinth.examples.proxy;

import java.lang.reflect.Field;

public class PrivateReflectTest {

	public static void main(String[] args) throws Exception {
		Class<Car> clazz = Car.class;
		Car car = (Car) clazz.newInstance();
		Field colorField = clazz.getDeclaredField("color");
		colorField.setAccessible(true);
		colorField.set(car, "red");
		System.out.println(car);

	}

}
