/**
 * @部门:学工产品事业部
 * @日期：2017年1月25日 上午9:05:05 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.jcsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgForm;

import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设组织关系转出管理模块
 * @类功能描述: 基础设置Dao
 * @作者： xuwen[工号:1426]
 * @时间： 2017年1月25日 上午9:05:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcszDao extends SuperDAOImpl<JcszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setTableName("xg_dtjs_zzgxzc_csszb");
		this.setKey("id");
		this.setClass(JcszForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * @throws Exception  
	 * @描述:获取基础设置信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月25日 上午11:00:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * JcszForm 返回类型 
	 * @throws 
	 */
	public JcszForm getModel() throws Exception {
		String sql = "select * from xg_dtjs_zzgxzc_csszb where rownum=1";
		return super.getModel(sql, new String[]{});
	}
	
	/**
	 * 
	 * @描述: 获取申请开关
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-8 上午11:37:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String getSqKg() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg ");
		sql.append(" from xg_dtjs_zzgxzc_csszb t where 1=1");
		return dao.getOneRs(sql.toString(),new String[]{},"sqkg");
	}
}
