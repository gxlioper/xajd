package com.zfsoft.xgxt.comm.zdybd.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.zdybd.model.ZddyModel;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 自定义表单
 * @类功能描述: 字段定义
 * @作者： ligl
 * @时间： 2013-11-26 下午03:56:07
 * @版本： V1.0
 * @修改记录:
 */
public class ZddyDao extends SuperDAOImpl<ZddyModel> {

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
	public List<HashMap<String, String>> getPageList(ZddyModel t)
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
	public List<HashMap<String, String>> getPageList(ZddyModel t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 
	 * @描述:根据功能代码获取所有分类下的字段列表
	 * @作者：ligl
	 * @日期：2013-11-26 下午04:07:46
	 * @修改记录:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndm(String gndm)
			throws Exception {
		String sql = "select a.*,b.bdms from xg_zdybd_zddyb a,xg_zdybd_flszb b  where a.flszid=b.flszid and b.gndm=? ";
		sql += " and b.sfqy='1' and (a.xxdm = ? or a.xxdm = ?)";
		sql += " order by a.flszid,a.zbwz ";
		String[] input = { gndm,Base.xxdm,"public" };
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}

	/**
	 * 
	 * @描述:根据功能代码获取所有分类下的学生字段列表
	 * @作者：ligl
	 * @日期：2013-12-5 下午01:48:09
	 * @修改记录:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndmOfXszdsz(String gndm)
			throws Exception {
		String sql = "select c.* from xg_zdybd_zddyb a,xg_zdybd_flszb b,xg_zdybd_xszdszb c where a.flszid=b.flszid and b.gndm=? ";
		sql += " and b.sfqy='1' and a.zddyid=c.zddyid";
		sql += " order by a.flszid,a.zbwz ";
		String[] input = { gndm };
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}

	/**
	 * 
	 * @描述:获取字段定义，简单显示，包含学生定义
	 * @作者：ligl
	 * @日期：2013-12-10 下午04:22:12
	 * @修改记录:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getSimpleListByGndm(String gndm)
			throws Exception {
		String sql = "select a.*,c.yxwk stuyxwk,c.yxxg stuyxxg  from xg_zdybd_zddyb a  left join xg_zdybd_xszdszb c on a.zddyid=c.zddyid ,xg_zdybd_flszb b  where a.flszid=b.flszid and b.gndm=? ";
		sql += " and (a.xxdm = ? or a.xxdm = ?) and b.sfqy='1' and a.zdlx is not null and a.zdlx!='1' order by a.zbwz";
		String[] input = { gndm,Base.xxdm,"public" };
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}

	public boolean updateSimple(List<ZddyModel> list) throws Exception {
		int[] result = null;
		String sql = "update xg_zdybd_zddyb ";//
		sql += " set yxxg=?,yxwk=? where zddyid=? ";
		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
		for (ZddyModel model : list) {
			param = new String[] { model.getYxxg(), model.getYxwk(),
					model.getZddyid() };
			params.add(param);
		}
		result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	public boolean insertSimpleForXs(List<ZddyModel> list) throws Exception {
		int[] result = null;
		String sql = "insert into xg_zdybd_xszdszb(zddyid,yxwk,yxxg) ";//
		sql += " values(?,?,?) ";
		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
		for (ZddyModel model : list) {
			param = new String[] { model.getZddyid(), model.getYxwk(),
					model.getYxxg() };
			params.add(param);
		}
		result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	public boolean deleteStuByGnmk(String gndm) throws Exception {
		String sql = "delete from xg_zdybd_xszdszb where zddyid in (select a.zddyid from xg_zdybd_zddyb a,xg_zdybd_flszb b where a.flszid=b.flszid and b.gndm=?) ";
		String[] input = { gndm };
		dao.runDelete(sql, input);
		return true;
	}

	protected void setTableInfo() {
		super.setTableName("xg_zdybd_zddyb");
		super.setKey("zddyid");
	}

}
