package com.dream404.sample;

import java.util.Random;

/**
 * @description 汽车
 * @author dream404
 * @date 2016年8月21日
 *
 */
public class Car implements Moveable {

	/**
	 * 实现移动方法
	 */
	public void move() {
		try {
			// 模拟行使过程
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
