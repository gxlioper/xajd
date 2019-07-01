/**
 * @部门:学工产品事业部
 * @日期：2016-6-21 上午09:33:58 
 */  
package com.zfsoft.xgxt.xszz.sqlydmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助-困难生认定
 * @类功能描述: 申请理由代码维护 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-6-21 上午09:33:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SqlyDmwhDao extends SuperDAOImpl<SqlyDmwhForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SqlyDmwhForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select a.sqlydm,a.sqlymc from xg_xszz_xbmz_dmwh a where 1=1 ");
		
		if (!StringUtil.isNull(t.getSqlymc())){
			params.add(t.getSqlymc());
			sql.append(" and sqlymc like '%'||?||'%'");
		}
			
		sql.append(" order by to_number(sqlydm) ");
			
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SqlyDmwhForm t, User user)
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
		super.setClass(SqlyDmwhForm.class);
		super.setTableName("xg_xszz_xbmz_dmwh");
		super.setKey("sqlydm");
	}
	
	/**
	 * 
	 * @描述: 重复性验证
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-21 下午01:37:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String sqlywhCheckExist(SqlyDmwhForm t) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_xszz_xbmz_dmwh where sqlymc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{t.getSqlymc()}, "num");
		
		return num;
		
	}
	

}
