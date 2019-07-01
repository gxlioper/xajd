/**
 * @部门:学工产品事业部
 * @日期：2014-3-24 上午11:43:44 
 */  
package com.zfsoft.xgxt.gygl.gyyjx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-24 上午11:43:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyyjxdmwhDao extends SuperDAOImpl<GyyjxForm> {

	
	/**
	 * 获取 最大代码
	 * @return
	 * @throws SQLException
	 */
	public int getMaxdm() throws SQLException{
		
		String sql = "select nvl(max(to_number(YJFLDM)),0) YJFLDM from XG_GYGL_GYYJX_YJFL";
		
		return dao.getOneRsint(sql);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyyjxForm t)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* , decode(t.txs , '0' , 'w'  , 'y') as txsmc from (select to_number(a.yjfldm) yjfldm , a.yjflmc , (select count(1) from XG_GYGL_GYYJX_GYYJ b where b.yjfldm = a.yjfldm) txs from XG_GYGL_GYYJX_YJFL a ) t where 1=1 ").append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	//检查唯一性
	public boolean checkExist(String dmmc) throws Exception {
		String sql = "select * from XG_GYGL_GYYJX_YJFL where yjflmc = ? ";
		return dao.getListNotOut(sql, new String[]{dmmc}).size() > 0 ? true : false;
	}
	
	//检查唯一性修改
	public boolean checkExist(String dmmc , String dm) throws Exception {
		String sql = "select * from XG_GYGL_GYYJX_YJFL where yjflmc = ? and yjfldm <> ?";
		return dao.getListNotOut(sql, new String[]{dmmc , dm}).size() > 0 ? true : false;
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyyjxForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setClass(GyyjxForm.class);
		setKey("YJFLDM");
		setTableName("XG_GYGL_GYYJX_YJFL");
	}

}
