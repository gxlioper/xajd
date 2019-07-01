/**
 * @部门:学工产品事业部
 * @日期：2016-2-22 上午09:05:43 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：柳俊[工号:1282]
 * @时间： 2016-2-22 上午09:05:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszDao extends SuperDAOImpl<CsszForm>{
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(CsszForm.class);
		super.setKey("id");
		super.setTableName("xg_zdgxh_ypzl_cssz");		
	}
	
	public CsszForm getModel() throws Exception{
		String sql = "select * from xg_zdgxh_ypzl_cssz where rownum=1";
		return super.getModel(sql, new String[]{});
	}
	
	/** 
	 * @描述:获取申请审核开关
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-22 上午09:08:55
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
		sql.append(" from xg_zdgxh_ypzl_cssz t where 1=1");
		return dao.getOneRs(sql.toString(),new String[]{},new String[]{"sqkg"});
	}
	
	public boolean deleteJcsz() throws Exception{
		String sql = "delete from xg_zdgxh_ypzl_cssz";
		return dao.runUpdate(sql, new String[]{});
	}
	
}
