package com.dream404.sample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description JDK动态代理
 * @author dream404
 * @date 2016年8月21日
 *
 */
public class MoveHandler implements InvocationHandler {

	private Object object;

	/**
	 * 构造
	 * 
	 * @param object
	 */
	public MoveHandler(Object object) {
		super();
		this.object = object;
	}

	/**
	 * @proxy 被代理对象
	 * @method 被代理对象的方法
	 * @args 被代理对象方法的参数
	 * @return 被代理对象方法的返回值
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 开始时间
		long startTime = System.currentTimeMillis();
		System.out.println("汽车开始行驶：" + startTime);

		Object result = method.invoke(object, args);

		// 结束时间
		long endTime = System.currentTimeMillis();
		// 行驶时间
		long moveTime = endTime - startTime;

		System.out.println("汽车行驶结束：" + endTime);
		System.out.println("汽车共行驶了：" + moveTime + "毫秒");
		return result;
	}

	public static void main(String[] args) {
		Car car = new Car();
		MoveHandler mv = new MoveHandler(car);
		Moveable ma = (Moveable) Proxy.newProxyInstance(car.getClass().getClassLoader(), car.getClass().getInterfaces(),mv);
		ma.move();
	}

}
