package com.dream404.sample.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.dream404.sample.User;

/**
 * @description
 * @author dream404
 * @date 2016年8月20日
 *
 */
public class BeanUtil {

	/**
	 * 拷贝一个新对象
	 * 
	 * @param obj
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Object copy(Object source) throws Exception {
		// 判断对象是否为空
		if (null != source) {
			// 获取类
			Class<?> classType = source.getClass();
			// 获取构造
			Constructor<?> cons = classType.getConstructor(new Class[] {});
			// 创建对象
			Object dest = cons.newInstance(new Object[] {});

			// 获取对象的所有属性
			Field[] fields = source.getClass().getDeclaredFields();
			for (Field field : fields) {
				try {// 方式一，这种方式属性必须要有get和set方法
						// 获取属性名
					String fieldName = field.getName();
					// 将属性的首字母大写
					String firstName = fieldName.substring(0, 1).toUpperCase();
					// 获取get和set方法的名字
					String getMethodName = "get" + firstName + fieldName.substring(1);
					String setMethodName = "set" + firstName + fieldName.substring(1);
					// 获取get和set方法
					Method getMethod = source.getClass().getMethod(getMethodName, new Class[] {});
					Method setMethod = source.getClass().getMethod(setMethodName, new Class[] { field.getType() });
					// 赋值
					setMethod.invoke(dest, new Object[] { getMethod.invoke(source, new Object[] {}) });
				} catch (Exception e) { // 方法二
					// 对象的属性没有get或set方法，所以不能调用get和set的方式进行拷贝。
					field.setAccessible(true);
					field.set(dest, field.get(source));
				}
			}
			return dest;
		}
		return null;
	}

	/**
	 * 同类对象拷贝属性
	 * 
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 * @param copyNull
	 *            是否拷贝空属性
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static void copyProperty(Object source, Object target, boolean copyNull) throws Exception {
		// 判断是否同类对象
		if (source.getClass() != target.getClass()) {
			throw new Exception("两个对象不是同一种类型");
		}
		if (null == source) {
			target = null;
		}
		// 获取对象的所有属性
		Field[] fields = source.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {// 方式一，这种方式属性必须要有get和set方法
					// 获取属性名
				String fieldName = field.getName();
				// 将属性的首字母大写
				String firstName = fieldName.substring(0, 1).toUpperCase();
				// 获取get和set方法的名字
				String getMethodName = "get" + firstName + fieldName.substring(1);
				String setMethodName = "set" + firstName + fieldName.substring(1);
				// 获取get和set方法
				Method getMethod = source.getClass().getMethod(getMethodName, new Class[] {});
				Method setMethod = source.getClass().getMethod(setMethodName, new Class[] { field.getType() });
				Object value = getMethod.invoke(source, new Object[] {});
				
				//在不拷贝null的时候判断是否为null
				if (!copyNull && null == value)
					continue;
				
				// 赋值
				setMethod.invoke(target, new Object[] {});
			} catch (Exception e) { // 方法二
				// 对象的属性没有get或set方法，所以不能调用get和set的方式进行拷贝。
				field.setAccessible(true);
				Object value = field.get(source);
				
				//在不拷贝null的时候判断是否为null
				if (!copyNull && null == value)
					continue;
				
				//赋值
				field.set(target, value);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		User source = new User("1", "dream404", 24, "北京");
		source.sex = "男";
		// 拷贝对象
		User dest = (User) BeanUtil.copy(source);
		// 修改源数据属性
		source.setId("2");
		// 打印两个对象
		System.out.println(source);
		System.out.println(dest);
		
		System.out.println("==========================================");

		// 拷贝属性
		source.setAge(null);
		BeanUtil.copyProperty(source, dest, false);
		System.out.println(source);
		System.out.println(dest);
		System.out.println("==========================================");
		
		// 拷贝属性
		BeanUtil.copyProperty(source, dest, true);
		System.out.println(source);
		System.out.println(dest);

	}
}
