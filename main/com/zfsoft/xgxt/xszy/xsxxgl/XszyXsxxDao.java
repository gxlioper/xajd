package com.zfsoft.xgxt.xszy.xsxxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新生之友
 * @类功能描述: 新生信息管理
 */
public class XszyXsxxDao extends SuperDAOImpl<XszyXsxxForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszyXsxxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszyXsxxForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchQxTjByUser = SearchService.getSearchQxTjByUser(user, "t", "xydm", "");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select b.jg,b.mzdm,b.lxdh,b.dh,b.bzrxm,b.bzrlxdh,b.fdyxm,b.fdylxdh,b.bzryx,b.fdyyx,b.bz,b.zd1,b.zd2,b.zd3,b.dzyx, ");
		sql.append(" a.xh,a.xm,a.nj,a.xb,a.zydm dldm,a.zymc dl,a.bjdm,a.bjmc, a.sfzx,");
		sql.append(" t1.lddm,t1.qsh,a.xydm,t4.ssyxdm,t4.ssyxmc,t5.ldmc,t6.bmmc xymc, ");
		sql.append(" (select mzmc from mzdmb where mzdm=b.mzdm) mzdmmc ");
		sql.append(" from view_xsxxb a ");
		sql.append(" left join xg_xszy_xsxxb b on a.xh = b.xh ");
		sql.append(" left join XG_XSZY_XSZSXXB t2 on a.xh=t2.xh and a.nj=t2.nj ");
		sql.append(" left join xg_gygl_new_qsxxb t1 on t2.lddm=t1.lddm and t2.qsh=t1.qsh ");
		sql.append(" left join xg_xszy_qsfpb t4 on t4.lddm=t1.lddm and t4.qsh=t1.qsh and t4.nj='"+t.getNj()+"'");
		sql.append(" left join xg_gygl_new_ldxxb t5 on t1.lddm=t5.lddm ");
		sql.append(" left join zxbz_xxbmdm t6 on a.xydm=t6.bmdm ");
		sql.append(" ) t where 1=1 and nj='"+t.getNj()+"' and (sfzx='在校' or sfzx is null)");
		sql.append(searchQxTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setClass(XszyXsxxForm.class);
		super.setTableName("xg_xszy_xsxxb");
		super.setKey("xh");
	}

	@Override
	public XszyXsxxForm getModel(XszyXsxxForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select b.jg,b.mzdm,b.lxdh,b.dzyx,b.dh,b.bzrxm,b.bzrlxdh,b.fdyxm,b.fdylxdh,b.bzryx,b.fdyyx,b.bz,b.zd1,b.zd2,b.zd3, ");
		sql.append(" a.xh,a.xm,a.nj,a.xb,a.zydm dldm,a.zymc dl,a.bjdm,a.bjmc, ");
		sql.append(" t1.lddm,t1.qsh,t1.xydm,t4.ssyxdm,t4.ssyxmc,t5.ldmc,t6.bmmc xymc, ");
		sql.append(" (select mzmc from mzdmb where mzdm=b.mzdm) mzdmmc ");
		sql.append(" from view_xsxxb a ");
		sql.append(" left join xg_xszy_xsxxb b on a.xh = b.xh ");
		sql.append(" left join XG_XSZY_XSZSXXB t2 on a.xh=t2.xh and a.nj=t2.nj ");
		sql.append(" left join xg_gygl_new_qsxxb t1 on t2.lddm=t1.lddm and t2.qsh=t1.qsh ");
		sql.append(" left join xg_xszy_qsfpb t4 on t4.lddm=t1.lddm and t4.qsh=t1.qsh ");
		sql.append(" left join xg_gygl_new_ldxxb t5 on t1.lddm=t5.lddm ");
		sql.append(" left join zxbz_xxbmdm t6 on t1.xydm=t6.bmdm ");
		sql.append(" where a.xh=? ");
		return super.getModel(t, sql.toString(), new String[]{t.getXh()});
	}
	
	/** 
	 * 民族
	 */
	public List<HashMap<String, String>> queryMzList() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select distinct mzdm dm,mzmc mc from mzdmb ");
		sb.append(" where mzdm is not null order by mzdm ");
		return dao.getListNotOut(sb.toString(), new String[]{ });
	}
	
}
