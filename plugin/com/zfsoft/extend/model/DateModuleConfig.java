/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-5 ����02:21:07 
 */  
package com.zfsoft.extend.model;

import java.util.List;

import net.sf.json.JSONObject;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-5 ����02:21:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public DateModuleConfig() {
		super();
	}

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
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
	 * @param extendModuleҪ���õ� extendModule
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
	 * @param extendModuleElementListҪ���õ� extendModuleElementList
	 */
	public void setExtendModuleElementList(
			List<ExtendModuleElement> extendModuleElementList) {
		this.extendModuleElementList = extendModuleElementList;
	}
	
	
	
}
