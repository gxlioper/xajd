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
 * ���༰���Խ��в����Ĺ�����
 */
public class ClassCopyUtils {

	/**
	 * �÷����Ǽ���ʹ�� request.getParameter() ��ҳ��ȡֵ��ͳһ��ҳ�������ֵ��MODEL������
	 * ���� request�л�ȡ��getParameterMap �����е����в�����ֵ���Ƶ�ָ����MODEL��
	 * @param map    ͨ��request.getParameterMap() ��ȡ�����ݻ������ݿ��ѯ����������
	 * @param model  ָ��Ҫ��ֵ��MODEL���� Ҳ������form����
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
			throw new ObjectNullException("����Ķ�����Ϊ��");
		}
	}
	
	/**
	 * ��ָ�������ָ�������趨ֵ
	 * ����������Ҫ�����˻������������е�int/float/double��String���ͣ������Ķ�������û�п���
	 * ע��:��� targetObject �����м̳������࣬���޷��Լ̳е����Խ�����ֵ��ֻ�ܶ�targetObject�౾���е����Խ�����ֵ
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
			} else if(f.getType().isArray()){//����ֶ��������ʽ
				if(value != null && value.getClass().isArray()){
					f.set(targetObject, value);
				} else if(value != null && value.getClass().getName().equalsIgnoreCase("java.lang.String")){
					f.set(targetObject, new String[]{value.toString()});
				}
			} else if(f.getType().getName().equalsIgnoreCase("java.lang.String")){//����ֶν�����һ���ַ��������Ǵ����ֵ�����Ǹ�����
				if(value != null && value.getClass().isArray() && Array.getLength(value)>0){
					f.set(targetObject, Array.get(value, 0));//ȡ�����һ��ֵ
				} else {
					f.set(targetObject, value);
				}
			} else {
				f.set(targetObject, value);
			}
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			System.out.println("����COPY�������������");
			//e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
