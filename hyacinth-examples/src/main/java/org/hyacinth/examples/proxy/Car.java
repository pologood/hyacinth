package org.hyacinth.examples.proxy;

import java.util.Random;

import org.hyacinth.examples.proxy.jdkProxy.Moveable;

public class Car implements Moveable{

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
}
