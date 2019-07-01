package xgxt.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.zfsoft.utils.exceptions.classutil.ObjectNullException;

/**
 * @time 2010.5.4 
 * @author lt 
 * 对类及属性进行操作的工具类
 */
public class ClassCopyUtils {

	/**
	 * 该方法是减少使用 request.getParameter() 从页面取值，统一将页面参数赋值到MODEL对象中
	 * 将从 request中获取的getParameterMap 对象中的所有参数及值复制到指定的MODEL中
	 * @param map    通过request.getParameterMap() 获取的数据或是数据库查询出来的数据
	 * @param model  指定要赋值的MODEL对象 也可以是form对象
	 */
	public static void setModelValue(Map map, Object model) throws ObjectNullException{
		if (map != null && model != null) {
			try {
				for (Iterator<Entry<String, String[]>> it = map.entrySet().iterator(); it
				.hasNext();) {
					Map.Entry entry = (Map.Entry) it.next();	
					String filed = (String) entry.getKey();
					String values = (String) entry.getValue();
					setFieldValue(model, filed, values);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new ObjectNullException("传入的对象不能为空");
		}
	}
	
	/**
	 * 给指定对象的指定属性设定值
	 * 我们这里主要考虑了基本数据类型中的int/float/double及String类型，其他的对象类型没有考虑
	 * 注意:如果 targetObject 对象有继承其它类，则无法对继承的属性进行设值。只能对targetObject类本身有的属性进行设值
	 * @param targetObject
	 * @param fieldName
	 * @param value
	 */
	public static void setFieldValue(Object targetObject,String fieldName,Object value){
		try {
			Field f = targetObject.getClass().getDeclaredField(fieldName);
			if(!f.isAccessible()){
				f.setAccessible(true);
			}
			if(f.getType().getName().equalsIgnoreCase("int")){
				f.set(targetObject, Integer.parseInt(value != null ? value.toString() : "0"));
			} else if(f.getType().getName().equalsIgnoreCase("float")){
				f.set(targetObject, Float.parseFloat(value != null ? value.toString() : "0"));
			} else if(f.getType().getName().equalsIgnoreCase("double")){
				f.set(targetObject, Double.parseDouble(value != null ? value.toString() : "0"));
			} else if(f.getType().isArray()){//这个字段是数组格式
				if(value != null && value.getClass().isArray()){
					f.set(targetObject, value);
				} else if(value != null && value.getClass().getName().equalsIgnoreCase("java.lang.String")){
					f.set(targetObject, new String[]{value.toString()});
				}
			} else if(f.getType().getName().equalsIgnoreCase("java.lang.String")){//这个字段仅仅是一个字符串，但是传入的值对象是个数组
				if(value != null && value.getClass().isArray() && Array.getLength(value)>0){
					f.set(targetObject, Array.get(value, 0));//取数组第一个值
				} else {
					f.set(targetObject, value);
				}
			} else {
				f.set(targetObject, value);
			}
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			System.out.println("不能COPY到父类的属性中");
			//e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
