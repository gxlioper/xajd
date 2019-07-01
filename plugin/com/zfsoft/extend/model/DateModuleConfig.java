/**
 * @部门:学工产品事业部
 * @日期：2015-6-5 下午02:21:07 
 */  
package com.zfsoft.extend.model;

import java.util.List;

import net.sf.json.JSONObject;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-5 下午02:21:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DateModuleConfig {

	private ExtendModule extendModule;
	
	private List<ExtendModuleElement> extendModuleElementList;

	public JSONObject generateJSONObject(){
		if(extendModule == null || 
				extendModuleElementList == null || 
				extendModuleElementList.size() == 0){
			return null;
		}
		JSONObject jo = new JSONObject();
		jo.put("DataModule", extendModule);
		jo.put("DataModuleElement", extendModuleElementList);
		return jo;
	}
	
	
	/**
	 * @描述 ：TODO描述下当前构造方法
	 */
	public DateModuleConfig() {
		super();
	}

	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param extendModule
	 * @param extendModuleElementList
	 */
	public DateModuleConfig(ExtendModule extendModule,
			List<ExtendModuleElement> extendModuleElementList) {
		super();
		this.extendModule = extendModule;
		this.extendModuleElementList = extendModuleElementList;
	}

	/**
	 * @return the extendModule
	 */
	public ExtendModule getExtendModule() {
		return extendModule;
	}

	/**
	 * @param extendModule要设置的 extendModule
	 */
	public void setExtendModule(ExtendModule extendModule) {
		this.extendModule = extendModule;
	}

	/**
	 * @return the extendModuleElementList
	 */
	public List<ExtendModuleElement> getExtendModuleElementList() {
		return extendModuleElementList;
	}

	/**
	 * @param extendModuleElementList要设置的 extendModuleElementList
	 */
	public void setExtendModuleElementList(
			List<ExtendModuleElement> extendModuleElementList) {
		this.extendModuleElementList = extendModuleElementList;
	}
	
	
	
}
