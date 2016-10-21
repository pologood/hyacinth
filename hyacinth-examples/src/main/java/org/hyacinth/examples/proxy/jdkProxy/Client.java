package org.hyacinth.examples.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.hyacinth.examples.proxy.Car;

public class Client {

	/**
	 * @param args
	 * @throws Exception
	 */
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		Class<?> c = Class.forName("tests.Car2");
//		Object car = c.newInstance();
//		Method[] mes = c.getDeclaredMethods();
//		for(Method me : mes) {
//			System.out.println(me.getName());
//			Class<?>[] params = me.getParameterTypes();
//			if(params.length > 0) {
//				Object[] arg = new Object[params.length];
//				for(int i = 0; i < params.length; i++) {
//					arg[i] = params[i].newInstance();
//				}
//				me.invoke(car, arg);
//			}			
//			me.invoke(car);
//		}
		
		Car car = new Car();
		
		Moveable m1 = (Moveable)(new TimeHandler<Car>().bind(car));
		m1.move();
//		System.out.println("m1:" + m1.hashCode());
		
		InvocationHandler h = new TimeHandler<Car>(car); 
		Class<?> cls = car.getClass();
		/**
		 * loder:被代理类的类加载器
		 * interfaces 实现的接口
		 * h InvocationHandler
		 */
		Moveable m = (Moveable)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), h);
		m.move();
		
		System.out.println(m == m1);
	}

}
