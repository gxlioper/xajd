/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-2-20 ����09:27:51 
 */  
package com.zfsoft.xgxt.comm.export.service;

import java.util.List;

import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�xiaxia[����:1104]
 * @ʱ�䣺 2017-2-20 ����09:27:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public abstract class QueryDataService {
	
	private Object model;
	private User user;
	
	public QueryDataService(Object model, User user){
		this.model = model;
		this.user = user;
	}
	
	public abstract List queryData(Object model, User user) throws Exception;

	
	public List queryDataList()throws Exception{
		return queryData(model, user);
	}
	
	/**
	 * @return the model
	 */
	public Object getModel() {
		return model;
	}

	/**
	 * @param modelҪ���õ� model
	 */
	public void setModel(Object model) {
		this.model = model;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param userҪ���õ� user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	
	
}
