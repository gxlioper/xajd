/**
 * @部门:学工产品(1)部
 * @日期：2018-1-31 下午04:12:21 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.zsdysj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 证书打印
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-1-31 下午04:12:21 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZsdysjDao extends SuperDAOImpl<ZsdysjForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZsdysjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZsdysjForm t, User user)
			throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select a.id,a.xh,b.xm,b.xb,b.xz,b.mz,b.mzmc,b.zzmm,b.zzmmmc,b.xmpy, ");
		sql.append("nvl(a.cpnj,b.nj) nj, ");
		sql.append("nvl(a.cpxydm,b.xydm) xydm, ");
		sql.append("nvl(a.cpxymc,b.xymc) xymc, ");
		sql.append("nvl(a.cpzydm,b.zydm) zydm, ");
		sql.append("nvl(a.cpzymc,b.zymc) zymc, ");
		sql.append("nvl(a.cpbjdm,b.bjdm) bjdm, ");
		sql.append("nvl(a.cpbjmc,b.bjmc) bjmc, ");
		sql.append("a.xn,c.xmdm,a.xmmc,a.xmje,a.sqsj,c.ywmc xmywmc,");
		sql.append("(select lxmc from xg_zjdx_pjpy_xmlx d where a.lxdm = d.lxdm) xmlxmc,a.lxdm, ");
		sql.append("(select xzmc from xg_zjdx_pjpy_xmxz f where a.xzdm = f.xzdm) xmxzmc,a.xzdm ");
		sql.append("from xg_zjdx_pjpy_pjjgb a ");
		sql.append("left join view_xsxxb b on a.xh = b.xh ");
		sql.append("left join xg_zjdx_pjpy_pjxmb c on a.xmmc = c.xmmc and a.xn = c.xn ");
		//sql.append(") t where 1 = 1 and xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1) ");
		sql.append(") t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t,sql.toString(),inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
	}

}
