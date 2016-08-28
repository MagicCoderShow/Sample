package com.dream404.sample;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @description	
 * @author	xuping
 * @date	2016年8月26日
 *
 */
public class Test {
	public static void main(String[] args) throws InterruptedException {
		HashMap<Object,Object> hm = new HashMap<Object,Object>();
		hm.put(null, 1);
		
		Hashtable<Object,Object> ht =  new Hashtable<Object,Object>();
		ht.put(null, 1);
	}
}
