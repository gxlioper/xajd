/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-27 ����10:03:39 
 */
package com.zfsoft.xgxt.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @�๦������: ��json����
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-27 ����10:03:39
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JsonUtil {

	/**
	 * ��jsonת��Ϊlist����,json��ʽҪ�������dataΪkey,valueΪjson���飬�����ڲ�Ϊ��ת�����ɵĶ���
	 * �磺{data:[{tjdm:'wjqk',tjgx:'='},{tjdm:'pjcj',tjgx:'='}]},����Ϊ��XmwhTjszForm
	 * ��ʽ����ת��ʧ�ܣ�ֱ�ӷ��ؿ�
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List jsonToList(String json, Class clazz) {
		Object[] array = null;
		JSONObject str = null;
		JSONArray jsonArray = null;
		try {
			str = JSONObject.fromString(json);
			jsonArray = str.getJSONArray("data");
			array = JSONArray.toArray(jsonArray, clazz);
		} catch (Exception e) {
			e.printStackTrace();// ///////�쳣��ӡ���� ///////
			return null;
		}
		List list = new ArrayList();
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null) {
					list.add(array[i]);
				}
			}
		}
		return list;
	}

	/**
	 * ��jsonת��Ϊlist����,json��ʽҪ�������Ϊjson���飬�����ڲ�Ϊ��ת�����ɵĶ���
	 * �磺[{tjdm:'wjqk',tjgx:'='},{tjdm:'pjcj',tjgx:'='}],����Ϊ��XmwhTjszForm
	 * ��ʽ����ת��ʧ�ܣ�ֱ�ӷ��ؿ�
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List jsonArrToList(String json, Class clazz) {
		json = "{data:" + json + "}";
		return jsonToList(json,clazz);
	}

	/**
	 * ��jsonת��Ϊlist����,json��ʽҪ�������dataΪkey,valueΪjson���飬�����ڲ�Ϊ��ת�����ɵĶ���
	 * �磺{data:[{tjdm:'wjqk',tjgx:'='},{tjdm:'pjcj',tjgx:'='}]},����Ϊ��XmwhTjszForm
	 * ��ʽ����ת��ʧ�ܣ�ֱ�ӷ��ؿ�
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List jsonToList(String json) {
		Object[] array = null;
		JSONObject str = null;
		JSONArray jsonArray = null;
		try {
			str = JSONObject.fromString(json);
			jsonArray = str.getJSONArray("data");
			array = JSONArray.toArray(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();// ///////�쳣��ӡ���� ///////
			return null;
		}
		List list = new ArrayList();
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null) {
					list.add(array[i]);
				}
			}
		}
		return list;
	}
	

	/**
	 * ��jsonת��Ϊlist����,json��ʽҪ�����Ϊjson���飬�����ڲ�Ϊ��ת�����ɵĶ���
	 * �磺[{tjdm:'wjqk',tjgx:'='},{tjdm:'pjcj',tjgx:'='}],����Ϊ��XmwhTjszForm
	 * ��ʽ����ת��ʧ�ܣ�ֱ�ӷ��ؿ�
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List jsonArrToList(String json) {
		json = "{data:" + json + "}";
		return jsonToList(json);
	}

	public static void main(String[] args) {
		String json = "[{xn:'2012-2-13',xq:'01',lxdm:'01'},{xn:'2012-2-13',xq:'01',lxdm:'02'}]";
		List list = jsonArrToList(json);
		for (Object object : list) {
			net.sf.ezmorph.bean.MorphDynaBean bean = (net.sf.ezmorph.bean.MorphDynaBean) object;

			System.out.println(bean.get("xn"));
		}
	}
	
	/**
	 * 
	 * @����:List<HashMap<String, String>>תJSONArray[{"key":""value},{"key":""value}]
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-10-12 ����08:51:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * JSONArray �������� 
	 * @throws
	 */
	public static JSONArray ListToJSON(List<HashMap<String, String>> list) {
		JSONArray jsonarry = null;
        if(list!=null && list.size()>0){  
            try {  
            	jsonarry = JSONArray.fromObject(list);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return jsonarry;
    }  


}
