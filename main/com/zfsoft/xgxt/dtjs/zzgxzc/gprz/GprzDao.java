/**
 * @部门:学工产品事业部
 * @日期：2017年2月14日 下午3:34:11 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.gprz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-组织关系转出-改派日志模块
 * @类功能描述: Dao
 * @作者： xuwen[工号:1426]
 * @时间： 2017年2月14日 下午3:34:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GprzDao extends SuperDAOImpl<GprzForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_dtjs_zzgxzc_zprzb");
		super.setKey("id");
		super.setClass(GprzForm.class);
		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GprzForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GprzForm t, User user) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT  t1.*,t2.xm,t2.nj,t2.xymc,t2.zymc,t2.bjmc,t2.zzmmmc");
		sql.append(" FROM xg_dtjs_zzgxzc_zprzb t1  ");
		sql.append(" LEFT JOIN VIEW_XSJBXX t2 ON t1.xh=t2.xh) t WHERE 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @throws SQLException  
	 * @描述:批量保存改派日志
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月15日 下午5:34:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gprzFormList
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveGprzFormList(List<GprzForm> gprzFormList) throws SQLException {
		String sql = "insert into xg_dtjs_zzgxzc_zprzb (xh,xgsj,xgr,xgqjl) values(?,?,?,?)";
		List<String[]> paramList = new ArrayList<String[]>();
		
		for(GprzForm gprzForm:gprzFormList){
			String [] param = {gprzForm.getXh(),gprzForm.getXgsj(),gprzForm.getXgr(),gprzForm.getXgqjl()};
			paramList.add(param);
		}
		int [] results = dao.runBatch(sql, paramList);
		
		return results.length>0;
	}


}
