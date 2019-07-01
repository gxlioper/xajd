/**
 * @部门:学工产品(1)部
 * @日期：2018-2-6 下午05:41:25 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.fwmddc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-2-6 下午05:41:25 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FwmddcDao extends SuperDAOImpl<FwmddcForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FwmddcForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FwmddcForm t, User user) throws Exception {
		/**生成高级查询相关条件、条件值 */
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select d.*,f.sx from (select a.id,a.xn,c.xssx xsxh,a.xn pjzq,a.xh,b.xm,");
		sql.append(" nvl(a.cpnj,b.nj) nj,");
		sql.append(" nvl(a.cpxydm,b.xydm) xydm,");
		sql.append(" nvl(a.cpxymc,b.xymc) xymc,");
		sql.append(" nvl(a.cpzydm,b.zydm) zydm,");
		sql.append(" nvl(a.cpzymc,b.zymc) zymc,");
		sql.append(" nvl(a.cpbjdm,b.bjdm) bjdm,");
		sql.append(" nvl(a.cpbjmc,b.bjmc) bjmc,");
		sql.append(" a.xmmc,a.sqsj hdsj,c.xmje,a.lxdm xxmlx,a.xzdm xxmxz ,");
		sql.append(" (select xzmc from xg_zjdx_pjpy_xmxz c where a.xzdm = c.xzdm) xmxzmc,");
		sql.append(" (select lxmc from xg_zjdx_pjpy_xmlx c where a.lxdm = c.lxdm) xmlxmc");
		sql.append(" from xg_zjdx_pjpy_pjjgb a");
		sql.append(" left join view_xsxxb b on a.xh = b.xh");
		sql.append(" left join xg_zjdx_pjpy_pjxmb c on a.xmmc = c.xmmc and a.xn = c.xn) d ");
		sql.append("left join xg_xtwh_bmsxb f on d.xydm = f.bmdm ");
		sql.append("order by to_number(f.sx) asc,to_number(d.xsxh) asc,d.xn desc");
		sql.append(")t where 1= 1");
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
	
	/**
	 * @描述: 学院获奖人数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-25 下午05:02:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public List<String[]> getXyList_10335(String xn, String xq ,String[] xmlx, String[] xydm,String[] xmxz, String[] xmmc ,User user) {
		DAO dao = DAO.getInstance();
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		sql.append("select b.xymc xymc, b.xmmc xmmc, b.rs rs");
		sql.append(" from (select a.xmmc, nvl(a.cpxymc,a.xymc)xymc, nvl(a.cpxydm, a.xydm) cpxydm, count(1) rs, d.xssx xsxh ");
		sql.append(" from (select * from (select a.*,nvl(a.cpbjdm,(select bjdm from xsxxb t2 where a.xh=t2.xh))");
		sql.append(" cpbj from xg_zjdx_pjpy_pjjgb a) t1 left join view_njxyzybj_all t2 on t1.cpbj = t2.bjdm where 1 = 1  ");
		sql.append(searchTjByUser);
		sql.append(" )a");
		sql.append(" inner join xg_zjdx_pjpy_xmlx b on a.lxdm = b.lxdm");
		sql.append(" inner join xg_zjdx_pjpy_xmxz c on a.xzdm = c.xzdm");
		sql.append(" left join xg_zjdx_pjpy_pjxmb d  on a.xmmc = d.xmmc ");
		sql.append(" where a.xn = ? ");
		
		sql.append("and  b.lxdm in( ");
		
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmlx[i]);
		}
		sql.append(")");
		
		
		if (xydm != null && xydm.length > 0) {
			sql.append(" and a.cpxydm in (");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}
		
		if (xmxz != null && xmxz.length > 0) {
			sql.append(" and c.xzdm in (");
			for (int i = 0; i < xmxz.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmxz[i]);
			}
			sql.append(")");
		}
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append(" group by nvl(a.cpxymc,a.xymc), a.xmmc,nvl(a.cpxydm, a.xydm),d.xssx) b left join xg_xtwh_bmsxb c on b.cpxydm = c.bmdm ");
		sql.append(" where rs is not null and rs<>0 order by to_number(c.sx) asc,to_number(b.xsxh) asc");
		
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}), new String[] { "xymc" , "xmmc", "rs"});
	}
	
	
	/**
	 * @描述: 项目类型获奖人数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-25 下午05:03:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param xmlx
	 * @param xydm
	 * @param xmxz
	 * @param xmmc
	 * @param user
	 * @return
	 * List<String[]> 返回类型 
	 * @throws
	 */
	public List<String[]> getXmleList(String xn , String xq , String[] xmlx ,  String[] xydm, String[] xmxz, String[] xmmc, User user){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		sql.append("select b.xmmc xmmc ,xsxh, sum(b.rs) rs from  (");
		sql.append("select a.xmmc,a.lxdm,d.xssx xsxh,");
		sql.append(" count(1) rs ");
	 	sql.append(" from (select t1.*,t2.xymc from ");
		sql.append(" (select a.*,nvl(a.cpbjdm,(select bjdm from xsxxb t2 where a.xh=t2.xh))");
		sql.append(" bjdm from xg_zjdx_pjpy_pjjgb a) t1 left join view_njxyzybj_all t2 on t1.bjdm =t2.bjdm where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" )a");
		sql.append(" inner join xg_zjdx_pjpy_xmlx b on a.lxdm = b.lxdm");
		sql.append(" inner join xg_zjdx_pjpy_xmxz c on a.xzdm = c.xzdm");
		sql.append(" left join (select * from xg_zjdx_pjpy_pjxmb where xn = (select xn from xg_zjdx_pjpy_csszb)) d on  a.xmmc = d.xmmc ");
		sql.append(" where a.xn = ? ");
		
		sql.append("and  b.lxdm in( ");
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmlx[i]);
		}
		sql.append(")");
		
		if (xmxz != null && xmxz.length > 0) {
			sql.append(" and c.xzdm in (");
			for (int i = 0; i < xmxz.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmxz[i]);
			}
			sql.append(")");
		}
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append("group by a.xmmc,d.xssx,a.lxdm) b  group by xmmc,xsxh order by to_number(xsxh) ");

		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}), new String[] { "xmmc", "rs"});
	}
	
	
	/**
	 * @描述: 获奖人员姓名
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-25 下午05:20:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param xmlx
	 * @param xydm
	 * @param xmxz
	 * @param xmmc
	 * @return
	 * List<String[]> 返回类型 
	 * @throws
	 */
	public List<String[]> getHjmdList_10335(String xn , String xq , String[] xmlx , String[] xydm, String[] xmxz, String[] xmmc){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		
		sql.append("select b.xymc xymc, b.xmmc xmmc,b.xm,");
		sql.append("case when length(b.xm)= 2 then substr(b.xm,1,1)||'  '|| substr(b.xm,2) else b.xm end ||'('||b.xh||')'xmxh, ");
		sql.append("case when length(b.xm)= 2 then substr(b.xm,1,1)||'  '|| substr(b.xm,2) else b.xm end xmxm");
		sql.append(" from (select a.xmmc, nvl(a.cpxymc, b.xymc) xymc, a.xh, b.xm");
		sql.append(" from xg_zjdx_pjpy_pjjgb a");
		sql.append(" left join view_xsxxb b on a.xh = b.xh");
		sql.append(" inner join xg_zjdx_pjpy_xmlx c on a.lxdm = c.lxdm");
		sql.append(" inner join xg_zjdx_pjpy_xmxz d on a.xzdm = d.xzdm");
		sql.append(" where a.xn = ? ");
		
		sql.append("and  c.lxdm in( ");
			for (int i = 0; i < xmlx.length; i++) {
				if(i != 0)
					sql.append(",");
				sql.append("?");
				params.add(xmlx[i]);
			}
			sql.append(")");
		
		
		if (xydm != null && xydm.length > 0) {
			sql.append(" and a.cpxydm in (");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}
		
		if (xmxz != null && xmxz.length > 0) {
			sql.append(" and d.xzdm in (");
			for (int i = 0; i < xmxz.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmxz[i]);
			}
			sql.append(")");
		}
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		sql.append(" order by length(b.xm),xm ) b  ");
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}), new String[] {"xymc" , "xmmc", "xm","xmxh","xmxm"});
	}
	
	/**
	 * @描述: 根据类型代码获得名称
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-25 下午05:31:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmlxdm
	 * @return
	 * @throws SQLException
	 * List<String> 返回类型 
	 * @throws
	 */
	public List<String> getXmlxmc(String[] xmlxdm) throws SQLException{
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();	
		
		sql.append("select lxmc xmlxmc from xg_zjdx_pjpy_xmlx where lxdm in (");
		for (int i = 0; i < xmlxdm.length; i++) {
			if(i==0){
				sql.append("?");
			}else{
				sql.append(",?");
			}
			params.add(xmlxdm[i]);
		}
		sql.append(")");
		return dao.getList(sql.toString(), params.toArray(new String[]{}), "xmlxmc");
	}
}
