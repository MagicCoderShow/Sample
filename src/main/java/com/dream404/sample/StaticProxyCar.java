package com.dream404.sample;

/**
 * @description 静态代理类
 * @author dream404
 * @date 2016年8月21日
 *
 */
public class StaticProxyCar extends Car {
	@Override
	public void move() {
		// 开始时间
		long startTime = System.currentTimeMillis();
		System.out.println("汽车开始行驶：" + startTime);

		super.move();

		// 结束时间
		long endTime = System.currentTimeMillis();
		// 行驶时间
		long moveTime = endTime - startTime;

		System.out.println("汽车行驶结束：" + endTime);
		System.out.println("汽车共行驶了：" + moveTime + "毫秒");
	}

	public static void main(String[] args) {
		StaticProxyCar spc = new StaticProxyCar();
		spc.move();
	}
}
