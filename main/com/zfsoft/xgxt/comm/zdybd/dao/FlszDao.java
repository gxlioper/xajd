package com.zfsoft.xgxt.comm.zdybd.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.zdybd.model.FlszModel;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 自定义表单
 * @类功能描述: 分类设置
 * @作者： ligl
 * @时间： 2013-11-26 下午03:56:07
 * @版本： V1.0
 * @修改记录:
 */
public class FlszDao extends SuperDAOImpl<FlszModel> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 * 
	 * @param t
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FlszModel t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 * 
	 * @param t
	 * 
	 * @param user
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FlszModel t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 
	 * @描述:根据功能代码获取分类列表数据
	 * @作者：ligl
	 * @日期：2013-11-26 下午04:07:46
	 * @修改记录: 
	 * @param gndm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndm(String gndm)
			throws Exception {
		String sql = "select a.*,b.bhmk,b.gnlx,b.yzsz from xg_zdybd_flszb a,xg_zdybd_gnszb b  where a.gndm=b.gndm and a.gndm=? ";
		sql += " and a.sfqy='1' ";
		sql += " order by a.flflszid,to_number(a.xsxx) ";
		String[] input = { gndm };
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}

	protected void setTableInfo() {
		super.setTableName("xg_zdybd_flszb");
		super.setKey("flszid");
	}
	
	/**
	 * 
	 * @描述: 查出flflszid为空的分类列表大项数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-17 下午05:35:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gndm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndmDx(String gndm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,");
		sql.append(" b.bhmk,");
		sql.append(" b.gnlx,");
		sql.append(" b.yzsz");
		sql.append(" from (select *");
		sql.append(" from xg_zdybd_flszb");
		sql.append(" where gndm = ?");
		sql.append(" and flflszid is null");
		sql.append(" order by to_number(xsxx)) a,");
		sql.append(" xg_zdybd_gnszb b");
		sql.append(" where a.gndm = b.gndm");
		return dao.getListNotOut(sql.toString(), new String[]{gndm});
	}
	
	/**
	 * 
	 * @描述: 查出flflszid为空的分类列表小项数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-17 下午05:35:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gndm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndmXx(String gndm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,");
		sql.append(" b.bhmk,");
		sql.append(" b.gnlx,");
		sql.append(" b.yzsz");
		sql.append(" from (select *");
		sql.append(" from xg_zdybd_flszb");
		sql.append(" where gndm = 'xsxx_query'");
		sql.append(" and flflszid is not null");
		sql.append(" order by flflszid,to_number(xsxx)) a,");
		sql.append(" xg_zdybd_gnszb b");
		sql.append(" where a.gndm = b.gndm");
		return dao.getListNotOut(sql.toString(), new String[]{gndm});
	}

}
