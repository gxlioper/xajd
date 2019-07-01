/**
 * @部门:学工产品事业部
 * @日期：2013-6-19 下午03:43:00 
 */  
package com.zfsoft.xgxt.rcsw.cwsjcx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(财务数据查询) 
 * @作者： cmj [工号：913]
 * @时间： 2013-6-19 下午03:43:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CwsjDao extends SuperDAOImpl<CwsjForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CwsjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: 高级查询
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CwsjForm model, User user)
			throws Exception {
		// TODO 自动生成方法存根
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		//StringBuilder sql = new StringBuilder(" select * from (select a.*,(select xqmc from xqdzb e where e.xqdm=a.xq) xqmc,b.xm,b.xymc,b.xydm,b.zymc,b.zydm,b.bjmc,bjdm from rcsw_cwsjb a left join view_xsjbxx b on a.xh=b.xh) a where 1=1 ");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from VIEW_NEW_DC_RCSW_CWSJCX a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}


	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("rcsw_cwsjb");
		super.setKey("id");
		super.setClass(CwsjForm.class);
		
	}

	/** 
	 * @描述:(获取学生财务数据)
	 * @作者：cmj[工号：913]
	 * @日期：2013-8-28 下午07:26:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<String[]> 返回类型 
	 * @throws 
	 */
	public List<String[]> getStuCwsjList(String xh) {
		// TODO 自动生成方法存根
		String sql="select xn,(select xqmc from XQDZB b where b.xqdm=a.xq)xq,zd1,zd2,zd3 from rcsw_cwsjb a where xh=?";
		return dao.rsToVator(sql, new String[]{xh}, new String[]{"xn","xq","zd2","zd3","zd1"});
	}

	
	/**
	 * 
	 * @描述:根据学号查询学生财务数据
	 * @作者：ligl
	 * @日期：2013-11-30 下午03:07:04
	 * @修改记录: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCwsjList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xqmc ");
		sql.append("from rcsw_cwsjb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.xn,xq");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}	
	
}
