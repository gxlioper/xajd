/**
 * @部门:学工产品事业部
 * @日期：2015-12-21 下午07:10:02 
 */  
package com.zfsoft.xgxt.pjpy.zyjgl.grzyjwh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评价评优-专业奖管理-个人专业奖维护
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2015-12-21 下午07:10:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GrzyjwhDao extends SuperDAOImpl<GrzyjwhForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GrzyjwhForm t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GrzyjwhForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t4.yhmc,t4.yhdm,t2.xz,t2.jtdz,t2.sfzh,t2.rxrq,t7.mzmc,t8.zzmmmc,t1.id,t1.xh,t1.bsmc,t1.zbdw,t1.hjsj,t1.rddjdm,(case nvl(t1.rddjdm,'0') when '0' then '未认定' else t5.rddjmc end) rddjmc, ");
		sql.append(" t2.xm, (case t2.xb when '1' then  '男' when '2' then '女' else t2.xb end) xb,t6.bjmc,t2.lxdh,t6.xydm,t6.xymc,t6.zymc,t6.zydm,t2.bjdm,t2.nj ");
		sql.append(" from xg_pjpy_grzyjxx t1");
		sql.append(" left join xsxxb t2 on t1.xh = t2.xh left join view_njxyzybj_all t6 on t2.bjdm = t6.bjdm ");
		sql.append(" left join dmk_yh t4 on t2.yhdm = t4.yhdm ");
		sql.append(" left join xg_pjpy_zyjrddjdmb t5 on t1.rddjdm = t5.rddjdm ");
		sql.append(" left join mzdmb t7 on t2.mz = t7.mzdm ");
		sql.append(" left join zzmmdmb t8 on t2.zzmm = t8.zzmmdm ");
		sql.append(" ) a where 1 = 1 ");	
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setKey("id");
		this.setTableName("xg_pjpy_grzyjxx");
		this.setClass(GrzyjwhForm.class);
		
	}
	
	public List<HashMap<String, String>> getRddjList() {
		String sql = "select * from xg_pjpy_zyjrddjdmb";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	public GrzyjwhForm getModel(GrzyjwhForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,(case nvl(a.rddjdm,'0') when '0' then '未认定' else b.rddjmc end) rddjmc from xg_pjpy_grzyjxx a ");
		sql.append(" left join xg_pjpy_zyjrddjdmb b on a.rddjdm = b.rddjdm ");
		sql.append(" where a.id=? ");
		return super.getModel(t, sql.toString(), new String[]{ t.getId() });
	}
	
}
