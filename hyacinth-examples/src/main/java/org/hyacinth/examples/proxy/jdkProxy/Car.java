package org.hyacinth.examples.proxy.jdkProxy;

import java.util.Random;

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
	public void move(String a) {
		System.out.println("Just for test" + a);
	}

}
