package com.zfsoft.xgxt.base.export.util;

/**
 * Copyright (c) 2005-2010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: ReflectionUtils.java 1211 2010-09-10 16:20:45Z calvinxiu $
 */

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

/**
 * 反射工具类.
 * 
 * 提供访问私有变量,获取泛型类型Class, 提取集合中元素的属性, 转换字符串到对象等Util函数.
 * 
 * @author calvin
 */
public class ReflectionUtils {

	protected static Log logger = LogFactory.getLog(ReflectionUtils.class);

	/**
	 * 调用Getter方法.
	 */
	public static Object invokeGetterMethod(Object obj, String propertyName) {
		String getterMethodName = "get" + StringUtils.capitalize(propertyName);
		return invokeMethod(obj, getterMethodName, new Class[] {},
				new Object[] {});
	}

	/**
	 * 调用Setter方法.使用value的Class来查找Setter方法.
	 */
	public static void invokeSetterMethod(Object obj, String propertyName,
			Object value) {
		invokeSetterMethod(obj, propertyName, value, null);
	}

	/**
	 * 调用Setter方法.
	 * 
	 * @param propertyType
	 *            用于查找Setter方法,为空时使用value的Class替代.
	 */
	public static void invokeSetterMethod(Object obj, String propertyName,
			Object value, Class<?> propertyType) {
		Class<?> type = propertyType != null ? propertyType : value.getClass();
		String setterMethodName = "set" + StringUtils.capitalize(propertyName);
		invokeMethod(obj, setterMethodName, new Class[] { type },
				new Object[] { value });
	}

	/**
	 * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 */
	public static Object getFieldValue(final Object obj, final String fieldName) {
		Field field = getAccessibleField(obj, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + obj + "]");
		}

		Object result = null;
		try {
			result = field.get(obj);
		} catch (IllegalAccessException e) {
			logger.error(e);
		}
		return result;
	}

	/**
	 * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	 */
	public static void setFieldValue(final Object obj, final String fieldName,
			final Object value) {
		Field field = getAccessibleField(obj, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + obj + "]");
		}

		try {
			field.set(obj, value);
		} catch (IllegalAccessException e) {
			logger.error(e);
		}
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问.
	 * 
	 * 如向上转型到Object仍无法找到, 返回null.
	 */
	public static Field getAccessibleField(final Object obj,
			final String fieldName) {
		Assert.notNull(obj, "object不能为空");
		Assert.hasText(fieldName, "fieldName");
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException e) {// NOSONAR
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 直接调用对象方法, 无视private/protected修饰符. 用于一次性调用的情况.
	 */
	public static Object invokeMethod(final Object obj,
			final String methodName, final Class<?>[] parameterTypes,
			final Object[] args) {
		Method method = getAccessibleMethod(obj, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method ["
					+ methodName + "] on target [" + obj + "]");
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问. 如向上转型到Object仍无法找到, 返回null.
	 * 
	 * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object...
	 * args)
	 */
	public static Method getAccessibleMethod(final Object obj,
			final String methodName, final Class<?>... parameterTypes) {
		Assert.notNull(obj, "object不能为空");

		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				Method method = superClass.getDeclaredMethod(methodName,
						parameterTypes);

				method.setAccessible(true);

				return method;

			} catch (NoSuchMethodException e) {// NOSONAR
				// Method不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class. eg. public UserDao
	 * extends HibernateDao<User>
	 * 
	 * @param clazz
	 *            The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be
	 *         determined
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
	 * 
	 * 如public UserDao extends HibernateDao<User,Long>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be
	 *         determined
	 */
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz,
			final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of "
					+ clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger
					.warn(clazz.getSimpleName()
							+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class) params[index];
	}

	/**
	 * 将反射时的checked exception转换为unchecked exception.
	 */
	public static RuntimeException convertReflectionExceptionToUnchecked(
			Exception e) {
		if (e instanceof IllegalAccessException
				|| e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException) {
			return new IllegalArgumentException("Reflection Exception.", e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException("Reflection Exception.",
					((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException("Unexpected Checked Exception.", e);
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数), 组合成List.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertyName
	 *            要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static List fetchElementPropertyToList(final Collection collection,
			final String propertyName) {
		List list = new ArrayList();

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数), 组合成由分割符分隔的字符串.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertyName
	 *            要提取的属性名.
	 * @param separator
	 *            分隔符.
	 */
	@SuppressWarnings("unchecked")
	public static String fetchElementPropertyToString(
			final Collection collection, final String propertyName,
			final String separator) {
		List list = fetchElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * 得到所有的Field
	 * 
	 * @param clazz
	 *            Class
	 * @return
	 */
	public static List<Field> getAllDeclaredField(Class clazz) {
		List<Field> list = new LinkedList<Field>();
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			Field[] declaredFields = superClass.getDeclaredFields();
			for (Field f : declaredFields)
				list.add(f);
		}
		return list;
	}

	/**
	 * 判断clazz类是否实现了某个接口
	 * 
	 * @param clazz
	 *            Class
	 * @param targetInterface
	 *            是否实现的接口
	 * @return
	 */
	public static boolean isInterface(Class clazz, Class targetInterface) {
		Class[] face = clazz.getInterfaces();
		for (int i = 0, j = face.length; i < j; i++) {
			if (face[i].getName().equals(targetInterface.getName())) {
				return true;
			} else {
				Class[] face1 = face[i].getInterfaces();
				for (int x = 0; x < face1.length; x++) {
					if (face1[x].getName().equals(targetInterface.getName())) {
						return true;
					} else if (isInterface(face1[x], targetInterface)) {
						return true;
					}
				}
			}
		}
		if (null != clazz.getSuperclass()) {
			return isInterface(clazz.getSuperclass(), targetInterface);
		}
		return false;
	}

	/**
	 * 得到clazz类中的所有集合属性,包括属性的嵌套
	 * 
	 * @param clazz
	 *            Class
	 * @return
	 */
	// public static List<String> getAllCollectionNames(Class clazz) {
	// List<String> list = new ArrayList<String>();
	// // 定义临时map,用于放入class标志,防止死循环递归
	// Map<Class, Object> tempMap = new HashMap<Class, Object>();
	// tempMap.put(clazz, null);
	//
	// List<Field> allDeclaredField =
	// ReflectionUtils.getAllDeclaredField(clazz);
	// for (Field f : allDeclaredField) {
	// Class typeClass = f.getType();
	// // 如果属性类型是IHibernateEntity接口
	// if (ReflectionUtils.isInterface(typeClass, IHibernateEntity.class)) {
	// if (!tempMap.containsKey(typeClass)) {
	// // 递归调用
	// list.addAll(getAllCollectionNames(typeClass));
	// tempMap.put(typeClass, null);
	// }
	// }
	// if (ReflectionUtils.isInterface(typeClass, Collection.class)) {
	// list.add(f.getName());
	// }
	// }
	// return list;
	// }

}
