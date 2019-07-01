/**
 * @部门:学工产品事业部
 * @日期：2017-8-9 下午03:42:28 
 */  
package com.zfsoft.xgxt.szdw.xfjs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学风建设维护模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： CP[工号:1352]
 * @时间： 2017-8-9 下午03:42:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class xfjsDao extends SuperDAOImpl<xfjsForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(xfjsForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(xfjsForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select a.*,round(a.sjcqrs / a.ydxsrs, 4) * 100 || '%' cql,b.bjmc,b.xydm,b.xymc, b.zydm,b.zymc, b.nj,");
		sql.append(" c.fdy,d.pycc,d.pyccmc,d.yxdm,d.xqmc yxmc,e.xqmc");
		sql.append(" from xg_szdw_xfjsb a left join view_njxyzybj_all b on a.bjdm = b.bjdm");
		sql.append(" left join (select a.bjdm, WM_CONCAT(b.xm) fdy from fdybjb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) c on a.bjdm = c.bjdm left join xqdzb e on a.xq=e.xqdm ");
		sql.append(" left join (select distinct b.bjdm,b.pycc, b.yxdm,c.xqmc,e.pyccmc from (select bjdm,max(yxdm) yxdm,max(pycc) pycc from view_xsjbxx group by bjdm) b");
		sql.append("  left join dm_zju_xq c on b.yxdm = c.dm  left join XG_XSXX_PYCCDMB e");
		sql.append(" on b.pycc = e.pyccdm) d on a.bjdm = d.bjdm");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by xydm,fdy,nj,zydm,bjdm ");
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_szdw_xfjsb");
		super.setKey("id");
		super.setClass(xfjsForm.class);
		
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-10 下午02:40:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(xfjsForm myForm) {
		String sql = "select count(1) num from xg_szdw_xfjsb where xn = ? and xq = ? and bjdm=? and jcsj=? and jcjc=?" ;
		String num = dao.getOneRs(sql, new String[]{myForm.getXn(),myForm.getXq(),myForm.getBjdm(),myForm.getJcsj(),myForm.getJcjc()}, "num");
		return Integer.valueOf(num)>0;
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-10 下午02:44:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveDataXf(xfjsForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> parameter = new ArrayList<String>();
			sql.append(" insert into xg_szdw_xfjsb(xn,xq,bjdm,ydxsrs,sjcqrs,jcsj,jcjc,lrr,bz)");
			sql.append(" values(?,?,?,?,?,?,?,?,?)");
			parameter.add(t.getXn());
			parameter.add(t.getXq());
			parameter.add(t.getBjdm());
			parameter.add(t.getYdxsrs());
			parameter.add(t.getSjcqrs());
			parameter.add(t.getJcsj());
			parameter.add(t.getJcjc());
			parameter.add(t.getLrr());
			parameter.add(t.getBz());
		return dao.runUpdate(sql.toString(),parameter.toArray(new String[]{}));
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-10 下午03:36:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateData(xfjsForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> parameter = new ArrayList<String>();
			sql.append(" update xg_szdw_xfjsb set xn=?, xq=?, bjdm=?, ydxsrs=?, sjcqrs=?, jcsj=?, jcjc=?, lrr=?,bz=?  where id =? ");
			parameter.add(t.getXn());
			parameter.add(t.getXq());
			parameter.add(t.getBjdm());
			parameter.add(t.getYdxsrs());
			parameter.add(t.getSjcqrs());
			parameter.add(t.getJcsj());
			parameter.add(t.getJcjc());
			parameter.add(t.getLrr());
			parameter.add(t.getBz());
			parameter.add(t.getId());
			return dao.runUpdate(sql.toString(),parameter.toArray(new String[]{}));
	}

	/**
	 * @param string  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-10 下午04:35:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getbjmc(String bjdm) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select bjmc from view_njxyzybj_all where bjdm=? ");
		return dao.getOneRs(sb.toString(), new String[] { bjdm}, "bjmc");
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-11 上午09:34:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getxqmc(String xq) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select xqmc from xqdzb where xqdm=? ");
		return dao.getOneRs(sb.toString(), new String[] { xq}, "xqmc");
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-11 上午09:46:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delXf(String[] ids) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from xg_szdw_xfjsb where id in (");
		for(int i = 0;i<ids.length;i++){
			if(i != ids.length - 1){
				sb.append("?,");
			}else{
				sb.append("?)");
			}
		}
		return dao.runUpdate(sb.toString(), ids);
	}

	/**
	 * @throws Exception  
	 * @描述:获取班级列表
	 * @作者：张昌路[工号：982]
	 * @日期：2017-8-11 下午04:02:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjList(xfjsForm t,
			User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1","xydm", "bjdm");
	
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*from (select distinct a.nj,a.bjdm, a.bjmc, a.zydm, a.zymc,a.xydm,a.xymc, nvl(b.bjrs, 0) bjrs,e.xqmc,e.pyccmc, c.fdy  ");
		sql.append(" from view_njxyzybj_all a left join (select count(xh) bjrs, bjdm from view_xsjbxx group by bjdm) b on a.bjdm = b.bjdm ");
		sql.append(" left join (select a.bjdm, WM_CONCAT(b.xm) fdy from fdybjb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) c on a.bjdm = c.bjdm ");
		sql.append(" left join (select distinct a.bjdm, c.xqmc, d.pyccmc from (select bjdm,max(yxdm) yxdm,max(pycc) pycc from view_xsjbxx group by bjdm) a left join dm_zju_xq c on a.yxdm = c.dm left join XG_XSXX_PYCCDMB d on a.pycc = d.pyccdm) e");
		sql.append(" on a.bjdm = e.bjdm ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputValue);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2017-8-11 下午05:26:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getxqxslbfdy(String bjdm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*from (select distinct a.nj,a.bjdm, a.bjmc, a.zydm, a.zymc,a.xydm,a.xymc, nvl(b.bjrs, 0) bjrs,e.xqmc,e.pyccmc, c.fdy  ");
		sql.append(" from view_njxyzybj_all a left join (select count(xh) bjrs, bjdm from view_xsjbxx group by bjdm) b on a.bjdm = b.bjdm ");
		sql.append(" left join (select a.bjdm, WM_CONCAT(b.xm) fdy from fdybjb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) c on a.bjdm = c.bjdm ");
		sql.append(" left join (select distinct a.bjdm, c.xqmc, d.pyccmc from (select bjdm,max(yxdm) yxdm,max(pycc) pycc from view_xsjbxx group by bjdm) a left join dm_zju_xq c on a.yxdm = c.dm left join XG_XSXX_PYCCDMB d on a.pycc = d.pyccdm) e");
		sql.append(" on a.bjdm = e.bjdm ");
		sql.append(" )t1 where t1.bjdm=?");
		return dao.getMapNotOut(sql.toString(), new String[] { bjdm });
	}

	
}
