/**
 * @部门:学工产品事业部
 * @日期：2017-2-20 上午09:27:51 
 */  
package com.zfsoft.xgxt.comm.export.service;

import java.util.List;

import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：xiaxia[工号:1104]
 * @时间： 2017-2-20 上午09:27:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
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
	 * @param model要设置的 model
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
	 * @param user要设置的 user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	
	
}
