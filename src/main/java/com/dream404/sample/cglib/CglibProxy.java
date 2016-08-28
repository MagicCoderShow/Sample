package com.dream404.sample.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @description	cglib代理类
 * @author	dream404
 * @date	2016年8月21日
 *
 */
public class CglibProxy implements MethodInterceptor {
	
	private Enhancer enhacer = new Enhancer();
	
	/**
	 * 获取代理对象
	 * @param clazz 被代理的类
	 * @return 返回被代理对象
	 */
	public Object getProxy(Class<Train> clazz){
		//设置创建子类的类
		enhacer.setSuperclass(clazz);
		enhacer.setCallback(this);
		return enhacer.create();
	}

	/**
	 * 拦截所有目标类方法的调用
	 * object 	目标类的实例
	 * method 	目标方法的反射对象
	 * args		方法的参数
	 * proxy	代理类的实例
	 */
	public Object intercept(Object object, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
		// 开始时间
		long startTime = System.currentTimeMillis();
		System.out.println("汽车开始行驶：" + startTime);

		// 代理类调用父类的方法
		Object result = proxy.invokeSuper(object, arg);

		// 结束时间
		long endTime = System.currentTimeMillis();
		// 行驶时间
		long moveTime = endTime - startTime;

		System.out.println("汽车行驶结束：" + endTime);
		System.out.println("汽车共行驶了：" + moveTime + "毫秒");
		return result;
	}
	
	
	public static void main(String[] args) {
		CglibProxy cp = new CglibProxy();
		Train train = (Train)cp.getProxy(Train.class);
		train.move();
		train.stop();
	}

}
