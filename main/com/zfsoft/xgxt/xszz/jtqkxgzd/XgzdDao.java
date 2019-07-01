/**
 * @部门:学工产品事业部
 * @日期：2016-7-4 上午10:35:48 
 */  
package com.zfsoft.xgxt.xszz.jtqkxgzd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-4 上午10:35:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XgzdDao extends SuperDAOImpl<XgzdForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XgzdForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XgzdForm t, User user)
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
		this.setClass(XgzdForm.class);
		this.setKey("guid");
		this.setTableName("xg_xszz_new_zdcjpzb");
	}
	
	/**
	 * 
	 * @描述: 家庭情况调查字段设置信息查询,按xssx字段顺序排列
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-4 下午01:42:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnmk
	 * @param xxdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJtqkdcZdxx(String gnmk,String xxdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select guid,zddm,zdmc,sfbt from xg_xszz_new_zdcjpzb");
		sql.append(" where gnmk = ?");
		sql.append(" and xxdm = ?");
		sql.append(" order by to_number(xssx)");
		return dao.getListNotOut(sql.toString(), new String[]{gnmk,xxdm});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存字段必填非必填信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-5 下午01:46:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveData(XgzdForm model) throws Exception{
		String[] guids = model.getGuids();
		String[] sfbts = model.getSfbts();
		List<String[]> paralist = new ArrayList<String[]>();
		for (int i=0;i<guids.length;i++) {
			List<String> paralslist = new ArrayList<String>();
			paralslist.add(sfbts[i]);
			paralslist.add(guids[i]);
			paralist.add(paralslist.toArray(new String[]{}));
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xszz_new_zdcjpzb set sfbt = ? ");
		sql.append(" where guid = ?");
		int[] len = dao.runBatch(sql.toString(), paralist);
		return len.length > 0 ? true:false;
	}

}
