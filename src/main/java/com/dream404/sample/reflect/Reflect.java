package com.dream404.sample.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @description
 * @author xuping
 * @date 2016年5月8日
 *
 */
public class Reflect {
	public static void main(String[] args) throws Exception {
		Class<?> classType = getClassType();
		
//		//无参
//		Object object = instanceObjectParams(classType,new Class[]{},new Object[]{});
		//有参
		Object object = instanceObjectParams(classType,new Class[]{String.class,String.class,Integer.class,String.class},new Object[]{"1","dream404",24,"北京"});
		
		getClassFields(object);
		
		getClassMethods(object);
		
		reflectMethod(object, "setId", new Class[]{String.class}, new Object[]{"2"});
		System.out.println(reflectMethod(object, "getId", new Class[]{}, new Object[]{}));
		System.out.println(object.toString());
	}

	/**
	 * 获取类
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Class<?> getClassType() throws ClassNotFoundException {
		// //获取类的三种方式
		Class<?> classType = Class.forName("com.dream404.learn.User");
		// Class classType = User.class;
		// Class classType = new User().getClass();
		return classType;
	}

	/**
	 * 对象初始化
	 * @param classType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Object instanceObjectParams(Class<?> classType,Class[] type,Object[] value) throws Exception{
		Constructor<?> cons = classType.getConstructor(type);
		Object object = cons.newInstance(value);
		System.out.println(object.toString());
		return object;
	}

	/**
	 * 获取对象的方法
	 * @throws Exception
	 */
	public static void getClassMethods(Object object) throws Exception {
		// 获取所有的方法
		Method[] methods = object.getClass().getDeclaredMethods();
		// 遍历方法
		for (Method method : methods) {
			System.out.println("方法名:"+method.getName());
		}

	}
	
	/**
	 * 获取对象的所有属性
	 * @param object
	 */
	public static void getClassFields(Object object){
		//获取所有的属性
		Field[] fields = object.getClass().getDeclaredFields();
		//遍历属性
		for (Field field : fields) {
			System.out.println("属性名:"+field.getName());
		}
	}
	
	/**
	 * 通过反射调用方法
	 * @param object		对象
	 * @param methodName	方法名
	 * return Object
	 */
	@SuppressWarnings("rawtypes")
	public static Object reflectMethod(Object object,String methodName,Class[] type,Object[] value) throws Exception{
		Method method = object.getClass().getDeclaredMethod(methodName,type);
		Object obj = method.invoke(object, value);
		return obj;
	}

}
