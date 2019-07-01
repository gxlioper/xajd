/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-5 ����09:00:17 
 */  
package com.zfsoft.extend.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.extend.model.ExtendModule;
import com.zfsoft.extend.model.ExtendModuleElement;
import com.zfsoft.extend.service.DataSourceQuery;
import com.zfsoft.extend.service.ZDSource;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-5 ����09:00:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExtendUtility {

	public static final String DATA_MODULE_TAG = "DataModule";
	
	public static final String DATA_MODULE_ELEMENT_TAG = "DataModuleElement";
	
	/**
	 * 
	 * ��ȡ�ֶ����õ�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-8 ����10:20:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param source
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> retrieveDataSource(ZDSource source){
		DataSourceQuery dataSouceQueryer = source.getDataSouceQueryer();
		return dataSouceQueryer == null ? null : dataSouceQueryer.getData();
	}
	
	public static ExtendModule parseModule(JSONObject jo){
		if(jo == null){
			return null;
		}
		ExtendModule module = new ExtendModule(); 
		JSONObject jsonObject = jo.getJSONObject(DATA_MODULE_TAG);
		module.setId(jsonObject.getString("id"));
		module.setSfqy(jsonObject.getString("sfqy"));
		return module;
	}
	
	public static ExtendModuleElement parseModuleElement(JSONObject jo){
		if(jo == null){
			return null;
		}
		ExtendModuleElement moduleElement = new ExtendModuleElement();
		moduleElement.setId(jo.getString("id"));
		moduleElement.setSfsh(jo.getString("sfsh"));
		moduleElement.setShlc(jo.getString("shlc"));
		return moduleElement;
	}
	
	public static List<ExtendModuleElement> parseModuleElements(JSONArray joa){
		if(joa != null && joa.length() > 0){
			List<ExtendModuleElement> els = new ArrayList<ExtendModuleElement>();
			for (int i = 0; i < joa.length(); i++) {
				JSONObject jsonObject = joa.getJSONObject(i);
				ExtendModuleElement parseModuleElement = parseModuleElement(jsonObject);
				if(parseModuleElement != null)
					els.add(parseModuleElement);
			}
			return els;
		}
		
		return null;
	}
	
	public static List<ExtendModuleElement> obtainModuleElements(String jsonString){
		if(StringUtils.isBlank(jsonString)){
			return null;
		}
		JSONObject fromString = JSONObject.fromString(jsonString);
		JSONArray jsonArray = fromString.getJSONArray(DATA_MODULE_ELEMENT_TAG);
		List<ExtendModuleElement> parseModuleElements = parseModuleElements(jsonArray);
		return parseModuleElements;
	}
	
	public static ExtendModule obtainModule(String jsonString){
		if(StringUtils.isBlank(jsonString)){
			return null;
		}
		JSONObject jsonObject = JSONObject.fromString(jsonString).getJSONObject(DATA_MODULE_TAG);
		return parseModule(jsonObject);
	}
	
}
