package org.hyacinth.examples.proxy.cglibProxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{
	
	private Enhancer enhancer = new Enhancer();
	
	/* 设置创建子类的类 */
	public Object getProxy(Class clazz) {
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		
		return enhancer.create();
	}
	
	/**
	 * 拦截所有目标类的方法调用
	 * @param obj 目标类实例
	 * @param m 目标方法的反射对象
	 * @param args 方法的参数
	 * @param proxy 代理类的实例
	 */
	@Override
	public Object intercept(Object obj, Method m, Object[] args, MethodProxy proxy) throws Throwable {

		System.out.println("模拟开始...");
		proxy.invokeSuper(obj, args);
		System.out.println("模拟结束...");
		return null;
	}

}
