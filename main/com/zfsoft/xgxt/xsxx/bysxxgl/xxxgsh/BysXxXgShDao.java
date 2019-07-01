/**
 * @部门:学工产品事业部
 * @日期：2014-7-10 上午11:14:47 
 */  
package com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-7-10 上午11:14:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BysXxXgShDao extends SuperDAOImpl<BysXxXgShForm> {

	
	@Override
	public List<HashMap<String, String>> getPageList(BysXxXgShForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(BysXxXgShForm model, User user)
			throws Exception {
		// ====================过滤条件===================================

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		// query += searchTjByUser;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb
				.append("select b.*,c.*,a.xgsj,a.sqid,e.mc,row_number() over (partition by b.ywid order by b.shsj desc) as rn  ");
		sb
				.append(" from xg_bysxx_xxxgsqb a,xg_xtwh_shztb b,view_xsjbxx c,xg_xtwh_spgwyh d,xg_xtwh_spgw e ");
		sb
				.append(" where a.xh=c.xh and a.sqid=b.ywid and b.gwid=d.spgw   and e.id=b.gwid ");
		String shzt = model.getShzt();

		if (shzt != null && shzt.equals("tg")) {
			sb.append(" and b.shzt='1' ");
		} else if (shzt != null && !shzt.equals("dsh")) {
			sb.append(" and (b.shzt!='0' and b.shzt!='4')");
		} else {
			sb.append(" and (b.shzt='0' or b.shzt='4')");
		}

		sb.append(" and d.spyh='");
		sb.append(user.getUserName());
		sb.append("' ) a where rn=1 ");
		sb.append(searchTjByUser);
		sb.append(searchTj);
		return getPageList(model, sb.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("");
		super.setKey("");
	}

}
