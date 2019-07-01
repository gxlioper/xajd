/**
 * @部门:学工产品事业部
 * @日期：2015-4-17 下午04:08:34 
 */
package com.zfsoft.xgxt.zxdk.tyxs.shjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2015-4-17 下午04:08:34
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TyxsZzjgDao extends SuperDAOImpl<TyxsZzjg> {

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(TyxsZzjg.class);
		setKey("id");
		setTableName("xg_tyxsrx_xfzzjgb");

	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TyxsZzjg t)
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
	public List<HashMap<String, String>> getPageList(TyxsZzjg t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");

		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql
				.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.zybj,t2.zybjmc,x1.sydm,x1.symc,t2.nj,t2.xb,t2.sfzh,t2.lxdh, t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,t2.mz, t3.csrq, t3.xxszd,t3.jtdz, t3.jtyb,t4.*");
		
		sql
				.append(" from xg_tyxsrx_xfzzjgb t1 left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join XG_XTWH_SYBJGLB x on t2.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql
		.append(" left join  xsxxb t3 on t1.xh =t3.xh  left join zzmmdmb t4 on t3.zzmm = t4.zzmmdm ");

		sql.append(") t where 1=1 ");
		
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		logger.info("sql=" + sql.toString());

		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:根据学号和学年判断
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-23 下午05:03:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCountByXhAndXn(TyxsZzjg t) {
		StringBuilder sql = new StringBuilder(
				"select count(1) c from xg_tyxsrx_xfzzjgb where xh = ? and xn = ?");
		String[] params = null;

		if (!StringUtil.isNull(t.getId())) {
			sql.append(" and id <> ?");
			params = new String[] { t.getXh(), t.getXn(), t.getId() };
		} else {
			params = new String[] { t.getXh(), t.getXn() };
		}

		return dao.getOneRs(sql.toString(), params, "c");
	}

	/**
	 * 
	 * @描述:根据ｉｄ查看结果详情
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-23 下午05:04:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getZzjgById(String id) {
		String sql = "select * from xg_tyxsrx_xfzzjgb where id = ? ";
		return dao.getMapNotOut(sql, new String[] { id });
	}
	
	
	public HashMap<String, String> getDjbById(String id) {
		String sql = "select * from xg_tyxsrx_xfzzjgb t1 left join xg_tyxsrx_xfzzsqb t2 on t1.xh = t2.xh and t1.xn = t2.xn where t1.id = ? ";
		return dao.getMapNotOut(sql, new String[] { id });
	}
	

}
