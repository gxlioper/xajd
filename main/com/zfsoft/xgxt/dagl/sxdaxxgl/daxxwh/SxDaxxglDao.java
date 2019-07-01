/**
 * @部门:学工产品事业部
 * @日期：2016-8-17 下午02:06:17 
 */  
package com.zfsoft.xgxt.dagl.sxdaxxgl.daxxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 三峡档案管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： caopei[工号:1352]
 * @时间： 2016-8-25 下午06:49:51 
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class SxDaxxglDao extends SuperDAOImpl<SxDaxxglForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SxDaxxglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	@Override
	public List<HashMap<String, String>> getPageList(SxDaxxglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select* from(select t.*," +
				"case when f.count != '0' then f.count else 0 end ywhrs, " +
				"case when  f.count != '0' then cast(t.bjrs as int)-cast(f.count as int ) else t.bjrs end wwhrs " +
				" from (");
		sql.append(" select to_char(t1.nj) nj,t1.xymc," +
				"t1.xydm,t1.zymc,t1.zydm,t1.bjmc,t1.bjdm,t2.bjrs bjrs");
		sql.append(" from view_njxyzybj t1");
		sql.append(" left join (select count(1) bjrs,bjdm  from xsxxb a  where a.sfzx is null or a.sfzx = '在校'  group by a.bjdm) t2");
		sql.append(" on t1.bjdm = t2.bjdm)t");
		sql.append(" left join (select t2.bjdm, count(1) count from xg_xsxx_cqsxdaxxb t1 " +
				"left join view_xsjbxx t2 on t1.xh = t2.xh left join view_njxyzybj t3 on t2.bjdm = t3.bjdm " +
				" group by t2.bjdm) f on t.bjdm = f.bjdm )");
		sql.append(" where 1=1");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(),inputV);
	}

	
	@Override
	protected void setTableInfo() {
		super.setKey("daxxid");
		super.setTableName("XG_XSXX_CQSXDAXXB");
		super.setClass(SxDaxxglForm.class);
	}
	public SxDaxxglForm getModel() throws Exception{
		String sql = "select * from XG_XSXX_CQSXDAXXB ";
		return super.getModel(sql, new String[]{});
	}

	
	public List<HashMap<String, String>> getXsPageList(SxDaxxglForm t, User user) 
	throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] bjdm =t.getBjdms().split(",");
		sql.append(" select * from (select t.*,q.kddh,q.yjdz,q.yjr," +
				"case when q.kddh is not null then '0' else '1' end ywh," +
				"q.sj,q.bz from(");
		sql.append(" select t2.bjdm,");
		sql.append(" t2.xh,");
		sql.append(" t2.xm,");
		sql.append(" t2.syd,");
		sql.append(" t2.bjmc,");
		sql.append(" t2.nj,");
		sql.append(" t2.xydm,");
		sql.append(" t2.xymc,");
		sql.append(" t2.zydm,");
		sql.append(" t2.zymc ");
		sql.append(" from view_xsjbxx t2");
		sql.append(" left join view_njxyzybj b ");
		sql.append(" on b.bjdm = t2.bjdm ");
		if(t.getFlag().equals("gx")){
			sql.append(" where t2.bjdm in(");
			for(int i=0;i<bjdm.length;i++){
				sql.append("'"+bjdm[i]+"'");
				if(i != bjdm.length-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(") t left join  XG_XSXX_CQSXDAXXB q  on q.xh = t.xh)  where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
	    if(StringUtils.isNotNull(t.getXh())){
	    	sql.append(" and xh like '%"+t.getXh()+"%'");
	    	sql.append(" or ");
	    	sql.append(" xm like '%"+t.getXh()+"%'");
		}
	    if(StringUtils.isNotNull(t.getYwh())){
	    	sql.append(" and ywh = '"+t.getYwh()+"' ");
		}
		return getPageList(t, sql.toString(),inputV);
	}
	
	
	//添加或更新档案信息
	public boolean saveDataXs(SxDaxxglForm t, String type)throws Exception {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> parameter = new ArrayList<String>();
		if(type.equalsIgnoreCase("insert")){
			sql.append(" insert into xg_xsxx_cqsxdaxxb(xh,kddh,yjdz,yjr,sj,bz)");
			sql.append(" values(?,?,?,?,?,?)");
			parameter.add(t.getXh());
			parameter.add(t.getKddh());
			parameter.add(t.getYjdz());
			parameter.add(t.getYjr());
			parameter.add(t.getSj());
			parameter.add(t.getBz());
		}else{
			sql.append(" update xg_xsxx_cqsxdaxxb set kddh=?,yjdz=?,yjr=?,sj=?,bz=?  where xh =? ");
			parameter.add(t.getKddh());
			parameter.add(t.getYjdz());
			parameter.add(t.getYjr());
			parameter.add(t.getSj());
			parameter.add(t.getBz());
			parameter.add(t.getXh());
		}
		return dao.runUpdate(sql.toString(),parameter.toArray(new String[]{}));
	}


	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2016-8-25 下午03:52:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(SxDaxxglForm myForm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_xsxx_cqsxdaxxb ");
		sql.append(" where xh = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{myForm.getXh()}, "num");
		return num;
	}
	public HashMap<String, String> getBjxx(String bjdm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select xymc,zymc,bjmc,nj from view_njxyzybj where bjdm=?");
		return dao.getMap(sql.toString(), new String[]{ bjdm }, new String[]{"xymc","zymc","bjmc","nj"});
	}
//	public String getxymc(String bjdm) {
//		StringBuffer sb = new StringBuffer();
//		sb.append(" select xymc from view_njxyzybj where bjdm=?");
//		return dao.getOneRs(sb.toString(), new String[] { bjdm }, "xymc");
//	}
//	public String getzymc(String bjdm) {
//		StringBuffer sb = new StringBuffer();
//		sb.append(" select zymc from view_njxyzybj where bjdm=?");
//		return dao.getOneRs(sb.toString(), new String[] { bjdm }, "zymc");
//	}
//	public String getbjmc(String bjdm) {
//		StringBuffer sb = new StringBuffer();
//		sb.append(" select bjmc from view_njxyzybj where bjdm=?");
//		return dao.getOneRs(sb.toString(), new String[] { bjdm }, "bjmc");
//	}
//	
//	public String getnj(String bjdm) {
//		StringBuffer sb = new StringBuffer();
//		sb.append(" select nj from view_njxyzybj where bjdm=?");
//		return dao.getOneRs(sb.toString(), new String[] { bjdm }, "nj");
//	}

	public String getCount(String bjdm) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) count from view_njxyzybj t1 left join view_xsjbxx t2 on t1.bjdm=t2.bjdm where t1.bjdm=?");
		return dao.getOneRs(sb.toString(), new String[] { bjdm }, "count");
	}
	
	public String getywhCount(String bjdm) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(t4.daxxid) ywhcount from (select * from view_njxyzybj t1 left join view_xsjbxx t2 on t1.bjdm=t2.bjdm where t1.bjdm=?) t3" +
				" left join XG_XSXX_CQSXDAXXB t4 on t3.xh = t4.xh");
		return dao.getOneRs(sb.toString(), new String[] { bjdm }, "ywhcount");
	}
	
	
	
	
	
}
