/**
 * @部门:学工产品事业部
 * @日期：2015-3-23 上午10:55:58 
 */  
package com.zfsoft.xgxt.xsxx.bbzc.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-3-23 上午10:55:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszDao extends SuperDAOImpl<CsszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
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

	public CsszForm getModel() throws Exception{
		String sql = "select * from xg_xsxx_bdzc_csszb where rownum=1";
		return super.getModel(sql, new String[]{});
	}
	/**
	 * 
	 * @描述:报到注册开放状态
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 下午01:44:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCsszParam(){
		StringBuilder sql = new StringBuilder();
		sql.append("select case when t.zckg = 1 and sysdate between to_date(nvl(t.zckssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.zcjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end zczt ");
		sql.append(" from xg_xsxx_bdzc_csszb t  where 1=1");
		return dao.getOneRs(sql.toString(), new String[]{}, "zczt");
		
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(CsszForm.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_bdzc_csszb");
		
	}

}
