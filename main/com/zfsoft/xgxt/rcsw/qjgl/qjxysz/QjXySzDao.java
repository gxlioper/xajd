/**
 * @部门:学工产品事业部
 * @日期：2015-11-3 上午10:59:52 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.qjxysz;

import java.util.HashMap;
import java.util.List;

import oracle.sql.CLOB;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.znxgl.znxgl.ZnxglForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-11-3 上午10:59:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QjXySzDao extends SuperDAOImpl<QjXySzForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QjXySzForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QjXySzForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(QjXySzForm.class);
		super.setKey("id");
		super.setTableName("xg_rcsw_new_qjgl_qjxyszb");
	}
	
	/**
	 * 
	 * @描述:理论上这张表应该只有一条记录，所以不加查询条件
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-3 上午11:10:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getQjXySzDada() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select id,mc,content from xg_rcsw_new_qjgl_qjxyszb");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
//	//获取clob字段（协议设置内容）
//	public String getContent_Clob() throws Exception{
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select content from xg_rcsw_new_qjgl_qjxyszb ");
//		return dao.getOneRs(sql, inputValue, outputValue);
//	}
	
	//先删一遍表中的数据
	public boolean del_Table_content() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_rcsw_new_qjgl_qjxyszb");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
   
	/**
	 * 
	 * @描述:保存数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-7 下午02:20:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean save(QjXySzForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_rcsw_new_qjgl_qjxyszb (mc,content,bjsj,bjr) values(?,?,?,?)");
		return dao.runUpdate(sql.toString(), new String[]{t.getMc(),t.getContent(),t.getBjsj(),t.getBjr()});
	}
}
