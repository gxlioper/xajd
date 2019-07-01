/**
 * @部门:学工产品事业部
 * @日期：2016-2-24 下午02:47:20 
 */
package com.zfsoft.xgxt.zxdk.xnwxdkjm.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2016-2-24 下午02:47:20
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XnwxdkjmsqDao extends SuperDAOImpl<XnwxdkjmsqModel> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkjmsqModel t)
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
	public List<HashMap<String, String>> getPageList(XnwxdkjmsqModel t, User user)
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
		sql.append(" t1.zymc,t1.sfzh,t1.xz,t1.sjhm,t1.xmsj sjdh,t1.dzyx, ");
		sql.append(" decode(t.shzt,");
		sql.append(" '0',");
		sql.append(" '未提交',");
		sql.append(" '1',");
		sql.append(" '通过',");
		sql.append(" '2',");
		sql.append(" '不通过',");
		sql.append(" '3',");
		sql.append(" '已退回',");
		sql.append(" '5',");
		sql.append(" '审核中',");
		sql.append(" t.shzt) shztmc,");
		sql.append(" t2.xqmc");
		sql.append(" from xg_zdgxh_wxjkjm_sqb t");
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
		super.setClass(XnwxdkjmsqModel.class);
		super.setKey("sqid");
		super.setTableName("xg_zdgxh_wxjkjm_sqb");
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
}
