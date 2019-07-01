/**
 * @部门:学工产品事业部
 * @日期：2016-2-24 下午02:53:31 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdkjm.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.sq.XnwxdkjmsqModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-2-24 下午02:53:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnwxdkjmJgDao extends SuperDAOImpl<XnwxdkjmJgModel> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkjmJgModel t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkjmJgModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.nj,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,t1.sfzh,t1.xz,t1.sjhm,t1.xmsj sjdh,t1.dzyx,");
		sql.append(" t2.xqmc");
		sql.append(" from xg_zdgxh_wxjkjm_jgb t");
		sql.append(" left join view_xsbfxx t1");
		sql
				.append(" on t.xh = t1.xh "
						+ " left join xqdzb t2 on t.xq = t2.xqdm "
						+ ") t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(XnwxdkjmJgModel.class);
		super.setKey("jgid");
		super.setTableName("XG_ZDGXH_WXJKJM_JGB");
	}

	
	// 获取审批流
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_zdgxh_wxjkjm_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}

//	// 获取审批流
//	public String getJesx() {
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select jesx from xg_zdgxh_wxjk_cssz ");
//		return dao.getOneRs(sql.toString(), new String[] {}, "jesx");
//	}

	// 获取spid
	public String getSqbh(XnwxdkjmsqModel model) {
		StringBuffer sql = new StringBuffer();
		sql
				.append(" select sqid from xg_zdgxh_wxjkjm_sqb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[] { model.getXh()}, "sqid");
	}
	
	//申请流过来覆盖原来结果表中的数据时先删除原来的数据，在进行插入
	public boolean delDkjg(String xh)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_zdgxh_wxjkjm_jgb where xh = ?");
		return dao.runUpdate(sql.toString(),new String[]{xh});
	}
	
	public boolean delDkjgByID(String id)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_zdgxh_wxjkjm_jgb where jgid = ? ");
		return dao.runUpdate(sql.toString(),new String[]{id});
	}
}
