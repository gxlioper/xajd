package com.zfsoft.xgxt.comm.zdybd.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.zdybd.model.FlszModel;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 自定义表单
 * @类功能描述: 通用类
 * @作者： ligl
 * @时间： 2013-11-26 下午03:56:07
 * @版本： V1.0
 * @修改记录:
 */
public class ZdybdBaseDao extends SuperDAOImpl<FlszModel> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 * 
	 * @param t
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FlszModel t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 * 
	 * @param t
	 * 
	 * @param user
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FlszModel t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 
	 * @描述:通过sql获取列表
	 * @作者：ligl
	 * @日期：2013-11-28 下午05:02:05
	 * @修改记录:
	 * @param sql
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getListBySql(String sql)
			throws Exception {
		return dao.getListNotOut(sql, null);
	}


	/**
	 * 
	 * @描述:获得省份列表
	 * @作者：ligl
	 * @日期：2013-12-2 下午04:54:59
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getShList() throws Exception {
		String sql = "select distinct sydqdm dm,sydq mc from dmk_sydq";
		sql += "   order by sydqdm";
		String[] input = {};
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}	
	
	/**
	 * 
	 * @描述:获得区县列表
	 * @作者：ligl
	 * @日期：2013-12-2 下午04:54:59
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getQxList() throws Exception {
		String sql = "select distinct qxdm dm,qxmc mc from dmk_qx";
		sql += "   order by qxdm";
		String[] input = {};
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}

	protected void setTableInfo() {
		super.setTableName("");
		super.setKey("");
	}

}
