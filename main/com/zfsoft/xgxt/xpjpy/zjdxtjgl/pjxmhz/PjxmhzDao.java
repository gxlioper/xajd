/**
 * @部门:学工产品事业部
 * @日期：2018-1-5 上午11:22:18 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.pjxmhz;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新评奖评优
 * @类功能描述:  浙江大学新评奖评优-统计查询-评奖项目汇总
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-1-5 上午10:26:12 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjxmhzDao extends SuperDAOImpl<PjxmhzForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(PjxmhzForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	
	/**
	 * 使用高级查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(PjxmhzForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from ( select t.xn,t.lxdm,t.xzdm, ");
		sql.append(" (select xzmc from xg_zjdx_pjpy_xmxz where xzdm = t.xzdm) xmxzmc, ");
		sql.append(" (select lxmc from xg_zjdx_pjpy_xmlx where lxdm = t.lxdm) xmlxmc, ");
		sql.append(" t.xmmc, ");
		sql.append(" count(*) hjrs ");
		sql.append(" from (select * ");
		sql.append(" from (select t1.xn, t1.xmdm, t1.xmmc, t1.lxdm, t1.xzdm, t2.xydm ");
		sql.append(" from xg_zjdx_pjpy_pjjgb t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(" ) t ");
		sql.append(" group by t.xmmc, t.xzdm, t.lxdm, t.xn ");
		sql.append(" order by xn desc ) t3");
		sql.append(" where 1 = 1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 点击总人数的超链接，查询此项目所有的人数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-9 上午10:23:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @param fyFlag
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 	 * @throws
	 */
	public List<HashMap<String, String>> viewRs(PjxmhzForm t, User user,Boolean fyFlag)
		throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = " and xn = ? and lxdm = ? and xzdm = ? and xmmc = ? ";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = {t.getXn(),t.getLxdm(),t.getXzdm(),t.getXmmc()};
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select t1.xn,t1.xh,t5.xm,t5.xb,t1.xmmc,t1.lxdm,t1.xzdm,t1.xmje,t6.lxmc xmlx,t7.xzmc xmxz, ");
		sql.append("nvl(t3.nj, t5.nj) nj, ");
		sql.append("nvl(t3.xydm, t5.xydm) xydm, ");
		sql.append("nvl(t3.xymc, t5.xymc) xymc, ");
		sql.append("nvl(t3.zydm, t5.zydm) zydm, ");
		sql.append("nvl(t3.zymc, t5.zymc) zymc, ");
		sql.append("nvl(t3.bjdm, t5.bjdm) bjdm, ");
		sql.append("nvl(t3.bjmc, t5.bjmc) bjmc  ");
		sql.append("from xg_zjdx_pjpy_pjjgb t1 ");
		sql.append("left join xg_zjdx_pjpy_cpmdb t2 on t1.xh = t2.xh and t1.xn = t2.xn ");
		sql.append("left join view_njxyzybj_all t3 on t2.bjdm = t3.bjdm ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t4 on t1.xmdm = t4.xmdm ");
		sql.append("left join view_xsbfxx t5 on t1.xh = t5.xh ");
		sql.append("left join xg_zjdx_pjpy_xmlx t6 on t4.lxdm = t6.lxdm ");
		sql.append("left join xg_zjdx_pjpy_xmxz t7 on t1.xzdm = t7.xzdm ");
		sql.append(") a where 1 = 1  ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		if(fyFlag){
			return getPageList(t, sql.toString(), inputV);
		}else{
			return dao.getListNotOut(sql.toString(), inputV);
		}
	}
}
