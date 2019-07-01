/**
 * @部门:学工产品事业部
 * @日期：2016-3-10 下午04:46:11 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsftj;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-10 下午04:46:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsftjDao extends SuperDAOImpl<WsftjForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsftjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsftjForm t, User user)
			throws Exception {
		//String username = user.getUserName();
		//String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String nd = t.getSearchModel().getSearch_tj_nd()[0];
		String yf = t.getSearchModel().getSearch_tj_yf()[0];
		String[] inputV = new String[]{nd,yf};		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,round(good/totalnum*100,2) hao,round(bad/totalnum*100,2) huai from (");
		sql.append(" select a.ssxy,count(1) totalnum, sum(case when a.dj = '优秀' then 1 else 0 end) good,");
		sql.append(" sum(case when a.dj = '不合格' then 1 else 0 end) bad,f.bmmc,a.ccny");
		sql.append(" from view_wsftj a  left join zxbz_xxbmdm f on a.ssxy = f.bmdm");
		sql.append(" where a.nd = ? and a.yf = ?");
		sql.append(" group by ssxy,f.bmmc,a.ccny) t");
		//sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(WsftjForm.class);
		super.setKey("wsfid");
		super.setTableName("xg_zjcm_gygl_wsjc_wsf");
	}
	
	public boolean sfkcx(WsftjForm t) {
		String sql = "select count(1) num from view_wsftj where tjzt <> '1' and nd = ? and yf = ?";
		String num = dao.getOneRs(sql, new String[]{t.getSearchModel().getSearch_tj_nd()[0],t.getSearchModel().getSearch_tj_yf()[0]}, "num");
		return Integer.valueOf(num)< 1;
	}
	
}
