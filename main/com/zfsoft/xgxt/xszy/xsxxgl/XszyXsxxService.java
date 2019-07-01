package com.zfsoft.xgxt.xszy.xsxxgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新生之友
 * @类功能描述: 新生信息管理
 */
public class XszyXsxxService extends SuperServiceImpl<XszyXsxxForm, XszyXsxxDao> {
	
	private XszyXsxxDao dao = new XszyXsxxDao();
	
	public XszyXsxxService() {
		super.setDao(dao);
	}
	
	/**
	 * 修改
	 */
	public boolean updateXszyXsxx(XszyXsxxForm model) throws Exception {
		boolean updateResult =true;
		XszyXsxxForm myForm = dao.getModel(model.getXh());
		if(null!=myForm){
			updateResult = super.runUpdate(model);
		}else{
			updateResult = super.runInsert(model);
		}
		  
		return updateResult;
	}
	
	/** 
	 * 民族
	 */
	public List<HashMap<String, String>> queryMzList() throws Exception {
		return dao.queryMzList();
	}
	
}
