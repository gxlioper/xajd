/**
 * @部门:学工产品事业部
 * @日期：2016-5-23 下午06:00:24 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优(校外获奖)
 * @类功能描述: 参数设置 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-23 下午06:00:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszDao extends SuperDAOImpl<XwhjCsszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XwhjCsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XwhjCsszForm t, User user)
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
		super.setClass(XwhjCsszForm.class);
		super.setKey("id");
		super.setTableName("XG_PJPY_XWHJCSSZ");
	}
	
	public XwhjCsszForm getModel() throws Exception{
		String sql = "select * from XG_PJPY_XWHJCSSZ where rownum=1";
		return super.getModel(sql, new String[]{});
	}
	
	public boolean deleteJcsz() throws Exception{
		String sql = "delete from XG_PJPY_XWHJCSSZ";
		return dao.runUpdate(sql, new String[]{});
	}
	
	/**
	 * 
	 * @描述:获取申请审核开关
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-23 下午06:22:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getSqShKg() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg");
		sql.append(" from XG_PJPY_XWHJCSSZ t where 1=1");
		return dao.getOneRs(sql.toString(),new String[]{},new String[]{"sqkg"});
	}

}
