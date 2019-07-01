/**
 * @部门:学工产品事业部
 * @日期：2015-6-5 上午09:00:17 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-5 上午09:00:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExtendUtility {

	public static final String DATA_MODULE_TAG = "DataModule";
	
	public static final String DATA_MODULE_ELEMENT_TAG = "DataModuleElement";
	
	/**
	 * 
	 * 获取字段配置的数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-8 上午10:20:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param source
	 * @return
	 * List<HashMap<String,String>> 返回类型 
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
