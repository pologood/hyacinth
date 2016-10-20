package org.hyacinth.examples.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TimeHandler<T> implements InvocationHandler {

	private T target;
	
	public TimeHandler(T target) {
		super();
		this.target = target;
	}
	
	public TimeHandler() {
		
	}

	/**
	 * proxy 被代理的对象
	 * method 被代理的方法
	 * args 方法的参数
	 * 
	 * 返回值：
	 * Object 方法的返回值
	 *
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		long st = System.currentTimeMillis();
		System.out.println("开始行驶...");
		method.invoke(target,args);
		long et = System.currentTimeMillis();
		System.out.println("行驶完成...耗时" + (et-st) + "ms");
		return null;
	}
	
	public Object bind(T target) {
		this.target = target;
		Class<?> cls = target.getClass();
		return Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), this);
	}
}
