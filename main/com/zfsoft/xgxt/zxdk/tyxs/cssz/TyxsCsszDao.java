package com.zfsoft.xgxt.zxdk.tyxs.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 退役学生资助管理模块
 * @类功能描述:参数设置 
 * @作者： 冯兰英[工号:1177]
 * @时间： 2015-4-8 上午10:17:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TyxsCsszDao extends SuperDAOImpl<TyxsCssz> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(TyxsCssz.class);
		super.setKey("id");
		super.setTableName("xg_tyxsrx_xstycsb");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */


	public TyxsCssz getModel() throws Exception{
		String sql = "select * from xg_tyxsrx_xstycsb where rownum=1";
		return super.getModel(sql, new String[]{});
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TyxsCssz t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TyxsCssz t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/**
	 * 
	 * @描述:获取学历层次
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-24 下午05:52:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>>  getXlccList(){
		String sql = "select xlccmc from xlccdmb";
		return dao.getListNotOut(sql, null);
	}
}
