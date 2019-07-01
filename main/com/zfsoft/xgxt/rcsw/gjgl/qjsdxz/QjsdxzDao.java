package com.zfsoft.xgxt.rcsw.gjgl.qjsdxz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class QjsdxzDao extends SuperDAOImpl<QjsdxzForm> {

	@Override
	public List<HashMap<String, String>> getPageList(QjsdxzForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(QjsdxzForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(QjsdxzForm.class);
		this.setKey("id");
		this.setTableName("XG_RCSW_QJSDCSSZB");
	}
	
	/**
	 * @throws Exception 
	 *
	 * @描述: getModel重写
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-11 下午02:47:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * QjsdxzForm 返回类型 
	 * @throws
	 */
	@Override
	public QjsdxzForm getModel(QjsdxzForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_RCSW_QJSDCSSZB ");
		return getModel(sql.toString(), new String[]{});
		
	}
	
	/**
	 * 
	 * @描述: 删除表记录
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-11 下午02:58:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteRow() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_RCSW_QJSDCSSZB ");
		return dao.runUpdateNotCommit(sql.toString(), new String[]{});
	}

}
