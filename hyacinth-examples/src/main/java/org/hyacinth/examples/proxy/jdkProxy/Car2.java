package org.hyacinth.examples.proxy.jdkProxy;

/**
 * 使用继承方法实现代理
 * @author baironglin
 *
 */
public class Car2 extends Car {
	public void move() {
		long st = System.currentTimeMillis();
		System.out.println("开始行驶...");
		super.move();
		long et = System.currentTimeMillis();
		System.out.println("行驶完成...耗时" + (et-st));
		
	}
}
