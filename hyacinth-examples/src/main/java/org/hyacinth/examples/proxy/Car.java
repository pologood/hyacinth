package org.hyacinth.examples.proxy;

import java.util.Random;

import org.hyacinth.examples.proxy.jdkProxy.Moveable;

public class Car implements Moveable{
	
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
	
		try {
			Thread.sleep(new Random().nextInt(1000));
			System.out.println("行驶中...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void move(String a) {
		System.out.println("测试重载..." + a);
	}
	
	public void moveLikeTrain() {
		System.out.println("模拟火车行驶...");
	}
	
	public void getClassLoader() {
		ClassLoader loader = this.getClass().getClassLoader();
		System.out.println("current loader " + loader);
		System.out.println("parent loader " + loader.getParent());
		System.out.println("grantParent loader " + loader.getParent().getParent());
	}
	
	@Override
	public String toString() {
		return this.color;
	}
}
