/**
 * @部门:学工产品事业部
 * @日期：2016-7-22 上午10:43:46 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-22 上午10:43:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TttzxmDao extends SuperDAOImpl<TttzxmForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TttzxmForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 查询
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TttzxmForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select ");
		sql.append(" t.*,");
		sql.append(" nvl(t1.tdnum,0) tdnum,");
		sql.append(" t2.xm dzxm,");
		sql.append(" t2.xb,");
		sql.append(" t2.xymc,");
		sql.append(" t2.xydm,");
		sql.append(" t2.nj,");
		sql.append(" t2.zydm,");
		sql.append(" t2.zymc,");
		sql.append(" t2.bjdm,");
		sql.append(" t2.bjmc,");
		sql.append(" t3.xmmc,");
		sql.append(" t3.xmkssj,");
		sql.append(" t4.xmjbmc,");
		sql.append(" t3.xfrdsqzt,");
		sql.append(" t3.xfrdjgzt,");
		sql.append(" t3.xn,");
		sql.append(" t3.xq,");
		sql.append(" t3.jcxf,");
		sql.append(" t3.sskmdm,");
		sql.append(" t3.xmjbdm,");
		sql.append(" t5.sskmmc,");
		sql.append(" t.dzxh xh,");
		sql.append(" decode(t.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',t.shzt) shztmc,");
		sql.append(" case when t3.sqkg = 1 and sysdate between to_date(nvl(t3.sqkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and");
		sql.append("    to_date(nvl(t3.sqjssj, '9999-01-01 00:00'), 'yyyy-mm-dd hh24:mi') + 1 then  '1'  else '0' end sqkg,");
		sql.append(" t6.xqmc");
		sql.append(" from xg_sztz_ttxmsq t ");
		sql.append(" left join (select count(1) tdnum,xmdm,ttsqid from xg_sztz_ttcy group by xmdm,ttsqid) t1");
		sql.append(" on t.ttsqid = t1.ttsqid");
		sql.append(" left join  view_xsjbxx t2");
		sql.append(" on t.dzxh = t2.xh");
		sql.append(" left join xg_sztz_xmjg t3");
		sql.append(" on t.xmdm = t3.xmdm");
		sql.append(" left join xg_sztz_xmjb t4");
		sql.append(" on t3.xmjbdm = t4.xmjbdm");
		sql.append(" left join xg_sztz_sskm t5");
		sql.append(" on t3.sskmdm = t5.sskmdm");
		sql.append(" left join xqdzb t6");
		sql.append(" on t3.xq = t6.xqdm");
		sql.append(") t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(TttzxmForm.class);
		this.setTableName("xg_sztz_ttxmsq");
		this.setKey("ttsqid");
	}
	
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午04:09:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShlcID(String xmdm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_sztz_xmjg where xmdm = ? ");
		return dao.getOneRs(sql.toString(), new String[] {xmdm}, "splc");
	}
	

	
	

}
