/**
 * @部门:学工产品事业部
 * @日期：2015-6-10 下午05:56:26 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.rzkh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-6-10 下午05:56:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RzkhDao extends SuperDAOImpl<rzkhjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(rzkhjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(rzkhjgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t2.*,t1.xm,t1.xymc,t1.xydm,t1.bjdm,t1.bjmc,t1.zydm,t1.zymc,t1.nj,to_char(to_date(t2.rzsj,'yyyy-mm-dd hh24:mi:ss'),'yyyymmdd') rzsjname,t3.xqmc xqmc," +
				" t4.zwmc zwmcname   ");
		sql.append("from XG_SZDW_XSGBRZKHJG t2 , VIEW_XSJBXX t1,xqdzb t3,xg_szdw_xsgb_zwb t4 where t2.xh = t1.xh and t3.xqdm = t2.xq and t4.zwid = t2.zwmc)");
		sql.append(" t where 1= 1  ");
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
		super.setClass(rzkhjgForm.class);
		super.setKey("khjgid");
		super.setTableName("XG_SZDW_XSGBRZKHJG");
	}
	
	//获取职务学生干部职位信息list,用于增加或修改考核结果时的职务名称下拉框
	public List<HashMap<String, String>> getZwxxList(){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_szdw_xsgb_zwb ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	   
	//判断该学生干部在结果表中是否有记录，如果有不让其保存信息并给出提示
	public boolean checkExistForSave(rzkhjgForm model) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select t.khjgid from XG_SZDW_XSGBRZKHJG t where t.xh = ? and t.xn = ? and t.xq = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXq()}, "khjgid");
		if (num != null && ! num.equals("") ){
			flag = true;
		}
		return flag;
	}
	
	//获取查看时所需要的职位名称（保存时职位名称保存的是职位代码）
	public HashMap<String, String> getZwmc(String zwdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select zwmc from xg_szdw_xsgb_zwb where zwid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{zwdm});
	}
	
	//获取导出考核表所需的字段信息
	public HashMap<String, String> getKhjgxxMap(rzkhjgForm model, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xm,t1.xb,t1.zzmmmc zzmm,t1.xymc || t1.bjmc ddqd," +"t1.bjmc,"+
				"to_char(to_date(t1.csrq,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') csrq,");
		sql.append("(select t3.zwmc  from  xg_szdw_xsgb_zwb t3 where t3.zwid = t2.zwmc) zwmc,");
		sql.append(" to_char(to_date(t2.rzsj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') rzsj,  ");
		sql.append(" t2.grsz,t2.zgdwyj khdj,t2.qdyj,t2.szdwyj,t2.ddyj,t2.xsgzcyj from   ");
		sql.append(" View_Xsbfxx t1,XG_SZDW_XSGBRZKHJG t2 where t1.xh = t2.xh  ");
		sql.append(" and t2.khjgid = ?  ");
		return dao.getMapNotOut(sql.toString(),new String[]{model.getKhjgid()});
	}
	
	//获取考核汇总表所需字段信息
	public List<HashMap<String, String>> getKhjgxxList(rzkhjgForm model ) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.zwmc,to_char(to_date(t2.rzsj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') rzsj,t2.grzp,t2.zgdwyj, t2.xsgzcyj,t2.bz,t2.xn ||' '||t2.xq || ':'  xnxq  ");
		sql.append("from xg_szdw_xsgb_zwb t1 ,XG_SZDW_XSGBRZKHJG t2 ");
		sql.append(" where t1.zwid=t2.zwmc and t2.xh = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{model.getXh()});
	}
	
	//获取学期名称
	public HashMap<String, String> getxqdz(String xqdm){
		StringBuilder sql = new StringBuilder();
		sql.append("   select xqmc from xqdzb where xqdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xqdm});
	}
}
