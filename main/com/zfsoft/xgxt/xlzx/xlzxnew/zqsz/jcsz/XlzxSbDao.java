package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XlzxSbDao extends SuperDAOImpl<XlzxSbJcszForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XlzxSbJcszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XlzxSbJcszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_xlzxnew_csszb");
		return dao.getListNotOut(sql.toString(),new String[]{});
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setTableName("xg_xlzxnew_csszb");
		this.setKey("id");
		this.setClass(XlzxSbJcszForm.class);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 参数设置保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-28 下午05:25:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJcsz(List<String[]> paraList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xlzxnew_csszb(splc,lx)values(?,?)");
		return dao.runBatchBoolean(sql.toString(), paraList);
	}
	
	public boolean delJcsz() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xlzxnew_csszb");
		return dao.runUpdate(sql.toString(),new String[]{});
	}
	
	/**
	 * getModel重写
	 * @throws Exception 
	 */
	public XlzxSbJcszForm getModel(String lx) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_xlzxnew_csszb where lx = ?");
		return getModel(sql.toString(),new String[]{lx});
	}
}
