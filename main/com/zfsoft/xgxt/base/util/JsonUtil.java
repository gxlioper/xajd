/**
 * @部门:学工产品事业部
 * @日期：2013-4-27 上午10:03:39 
 */
package com.zfsoft.xgxt.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @系统名称: 学生工作管理系统
 * @类功能描述: 对json操作
 * @作者： ligl
 * @时间： 2013-4-27 上午10:03:39
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JsonUtil {

	/**
	 * 把json转化为list对象,json格式要求：外层以data为key,value为json数组，数组内部为可转化生成的对象
	 * 如：{data:[{tjdm:'wjqk',tjgx:'='},{tjdm:'pjcj',tjgx:'='}]},对象为：XmwhTjszForm
	 * 格式错误，转化失败，直接返回空
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
			e.printStackTrace();// ///////异常打印处理 ///////
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
	 * 把json转化为list对象,json格式要求：外层以为json数组，数组内部为可转化生成的对象
	 * 如：[{tjdm:'wjqk',tjgx:'='},{tjdm:'pjcj',tjgx:'='}],对象为：XmwhTjszForm
	 * 格式错误，转化失败，直接返回空
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
	 * 把json转化为list对象,json格式要求：外层以data为key,value为json数组，数组内部为可转化生成的对象
	 * 如：{data:[{tjdm:'wjqk',tjgx:'='},{tjdm:'pjcj',tjgx:'='}]},对象为：XmwhTjszForm
	 * 格式错误，转化失败，直接返回空
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
			e.printStackTrace();// ///////异常打印处理 ///////
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
	 * 把json转化为list对象,json格式要求：外层为json数组，数组内部为可转化生成的对象
	 * 如：[{tjdm:'wjqk',tjgx:'='},{tjdm:'pjcj',tjgx:'='}],对象为：XmwhTjszForm
	 * 格式错误，转化失败，直接返回空
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
	 * @描述:List<HashMap<String, String>>转JSONArray[{"key":""value},{"key":""value}]
	 * @作者：yxy[工号：1206]
	 * @日期：2015-10-12 上午08:51:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * JSONArray 返回类型 
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
