/**
 * @部门:学工产品（1）部
 * @日期：2017-7-7 上午09:49:56 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.cprytzjl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 参评人员调整记录 
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-7-7 上午09:50:08 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CprytzjlDao extends SuperDAOImpl<CprytzjlForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CprytzjlForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CprytzjlForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select * from (select a.xh,b.xm, ");
		sql.append(" (case b.xb when '1' then '男' when '2' then '女' else b.xb end) xb, ");
		sql.append(" c.nj,c.xydm,c.xymc,c.zydm,c.zymc,c.bjdm,c.bjmc,");
		sql.append(" (select xm from yhb b where a.tzr=b.yhm)tzrxm,a.tzr,a.tzsj,a.tzbz ");
		sql.append(" from xg_pjpy_new_cpmdtzjlb a ");
		sql.append(" left join xsxxb b on a.xh=b.xh ");
		sql.append(" left join view_njxyzybj_all c on b.bjdm=c.bjdm ");
		sql.append(" where a.xn=(select xn from xg_zjdx_pjpy_csszb where rownum=1) ");
		sql.append(" ) t1 where tzr ='" + user.getUserName()+ "' or (1=1");
		sql.append(searchTjByUser);
		sql.append(" )) where 1=1 ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
	}

}
