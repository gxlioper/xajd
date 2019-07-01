/**
 * @部门:学工产品事业部
 * @日期：2013-7-22 下午02:45:33 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcxm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块-
 * @类功能描述: 综测项目管理 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-22 下午02:45:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcxmDao extends SuperDAOImpl<ZcxmModel> {

	protected void setTableInfo() {
		super.setTableName("xg_zhcp_zcxmb");
		super.setKey("xmdm");
		super.setClass(ZcxmModel.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	public List<HashMap<String, String>> getPageList(ZcxmModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String, String>> getPageList(ZcxmModel t, User user)
			throws Exception {
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 查询综测项目列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 下午04:32:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getZcxmList(String xn,String xq){
		
		String sql = "select xmdm,xmmc,fjdm,xmlx,qzbl,mrfs,jktb,nvl(zdfs,' ') zdfs,zxfs from xg_zhcp_zcxmb where xn=? and xq=? order by xssx,jktb nulls last,xmmc";
		
		return dao.getListNotOut(sql, new String[]{xn,xq});
	}
	
	public List<HashMap<String,String>> getZcxmList2(String xn,String xq){
		
		String sql = "select xmdm,xmmc,fjdm,xmlx,qzbl,mrfs,jktb,nvl(zdfs,' ') zdfs,zxfs from xg_zhcp_zcxmb where xn=? and xq=? and (fjdm='N' or fjdm =(select xmdm from xg_zhcp_zcxmb t where t.xn=? and t.xq=? and fjdm='N'))";
		
		return dao.getListNotOut(sql, new String[]{xn,xq,xn,xq});
	}
	
	
	/**
	 * 
	 * @描述:删除学年综测项目
	 * @作者：cq [工号：785]
	 * @日期：2014-8-6 下午04:19:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delXnZcxm(String xn) throws Exception {
		String sql = "delete from xg_zhcp_zcxmb where xq = 'on' and xn = ? ";
		return dao.runUpdate(sql, new String[]{xn});
	}
	
	
	
	
	
	/**
	 * 
	 * @描述: 批量初始化综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 下午05:01:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型
	 */
	public boolean initZcxmList(List<String[]> params) throws SQLException{
		
		String sql = "insert into xg_zhcp_zcxmb(xmdm,xn,xq,xmmc,fjdm,xmlx,qzbl,mrfs,jktb,zdfs,zxfs) values (?,?,?,?,?,?,?,?,?,?,?)";
		
		int[] result = dao.runBatch(sql, params);
		
		return dao.checkBatchResult(result);
	}
	
	
	
	/**
	 * 
	 * @描述: 删除综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 上午10:40:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * int 返回类型
	 * @throws Exception 
	 */
	public int delZcxm(String xmdm) throws Exception{
		
		String sql = "delete from xg_zhcp_zcxmb where xmdm=? or fjdm=?";
		
		return dao.runDelete(sql, new String[]{xmdm,xmdm});
	}

	
	/**
	 * 
	 * @描述: 查询系统中的综测项目数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 下午02:31:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * int 返回类型
	 */
	public int getZcxmCount() throws SQLException{
		
		String sql = "select count(1) num from xg_zhcp_zcxmb";
		
		return dao.getOneRsint(sql);
	}
		
	/**
	 * 
	 * @描述: 查询最近周期的综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 下午02:45:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param isXqzc true 学期综测  false 学年综测
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZjzqZcxm(boolean isXqzc){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb where substr(xn,6,9)||xq=(select max(substr(xn,6,9)||xq) from xg_zhcp_zcxmb");
		//学期综测
		if (isXqzc){
			sql.append(" where xq <> 'on' ");
		}
		sql.append(" )");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @描述:根据学年获取最大学期综测项目
	 * @作者：cq [工号：785]
	 * @日期：2014-8-6 下午04:08:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXnzcxmForXn(String xn){
		
		String sql = "select * from xg_zhcp_zcxmb where xn||xq = (select ?||max(xq) from xg_zhcp_zcxmb where xq <> 'on' and xn = ?)";
		
		return dao.getListNotOut(sql, new String[]{xn,xn});
	}
	
	
	
	/**
	 * 
	 * @描述: 查询允许录入综测分的综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 上午11:47:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getAllowEditZcfxm(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm");
		sql.append(") and jktb is null and (xmlx='1' or xmlx='2' or xmlx='4')  ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) order by xssx,fjdm,xmmc");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @描述: 查询允许录入综测分的综测项目  （支持多选
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 上午11:47:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getAllowEditZcfxmByNj(List<HashMap<String,String>> bjxxMap, ZcfsModel model, User user){
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm");
		sql.append(") and jktb is null and (xmlx='1' or xmlx='2' or xmlx='4')  ");
		sql.append("and not exists (select 1 from (select xmdm,sum(qzbl) qzbl from xg_zhcp_zcblb t2 where ");
		sql.append("t2.bmdm in (select nj from view_njxyzybj t1 where 1=1 ");
		
		if(getIsblank(bjxxMap,model)){
			sql.append(" and t1.bjdm in (");
			for (int i = 0; i < bjxxMap.size(); i++) {
				if(0==i){
					sql.append("?");
				}else{
					sql.append(",?");
				}
				params.add(bjxxMap.get(i).get("bjdm"));
			}
			sql.append(")");
		}
		sql.append(searchTjByUser);
		sql.append(" ) group by xmdm ) t2 where t1.xmdm=t2.xmdm and t2.qzbl='0')");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) order by fjdm,xmmc");
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	
	/**
	 * 
	 * @描述: 查询允许录入综测分的综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 上午11:47:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getAllowEditZcfxmByXy(String bjdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm");
		sql.append(") and jktb is null and (xmlx='1' or xmlx='2' or xmlx='4')  ");
		sql.append("and not exists (select 1 from xg_zhcp_zcblb t2 where t1.xmdm=t2.xmdm and ");
		sql.append("t2.bmdm=(select xydm from view_njxyzybj where bjdm=? and rownum=1) and t2.qzbl='0')");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) order by fjdm,xmmc");
		
		return dao.getListNotOut(sql.toString(), new String[]{bjdm});
	}
	
	/**
	 * 
	 * @描述: 查询允许录入综测分的综测项目	(支持多选
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 上午11:47:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getAllowEditZcfxmByXy(List<HashMap<String,String>> bjxxMap, ZcfsModel model, User user){
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t3", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm");
		sql.append(") and jktb is null and (xmlx='1' or xmlx='2' or xmlx='4')  ");
		sql.append("and not exists (select 1 from (select xmdm,sum(qzbl)qzbl from xg_zhcp_zcblb t2 where ");
		sql.append("t2.bmdm in (select xydm from view_njxyzybj t3 where 1=1 ");
		
		if(getIsblank(bjxxMap, model)){
			sql.append(" and t3.bjdm in (");
			for (int i = 0; i < bjxxMap.size(); i++) {
				if(0==i){
					sql.append("?");
				}else{
					sql.append(",?");
				}
				params.add(bjxxMap.get(i).get("bjdm"));
			}
			sql.append(") ");
		}
		sql.append(searchTjByUser);
		sql.append(" ) group by xmdm) t2 where t1.xmdm=t2.xmdm and t2.qzbl='0')");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) order by fjdm,xmmc");
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	
	/**
	 * 
	 * @描述: 无子级项目的综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-26 上午11:42:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getNoChildZcfxm(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append(" order by xssx,jktb nulls last,fjdm,xmmc ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @描述: 按学院、班级查询可录入分数的综测项目
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-4-1 上午10:27:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getEditZcxmByXy(String bjdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append("and not exists (select 1 from xg_zhcp_zcblb t2 where t1.xmdm=t2.xmdm and ");
		sql.append("t2.bmdm=(select xydm from view_njxyzybj where bjdm=? and rownum=1) and t2.qzbl='0')");
		sql.append("order by jktb nulls last,fjdm,xmmc  ");
		return dao.getListNotOut(sql.toString(), new String[]{bjdm});
	}
	
	
	/**
	 * 
	 * @描述: 按学院、班级查询可录入分数的综测项目s
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-4-1 上午10:27:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getEditZcxmByXy(List<HashMap<String, String>> bjList, ZcfsModel model, User user)throws Exception{
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append("and not exists (select 1 from (select xmdm,sum(qzbl)qzbl from xg_zhcp_zcblb t2 where ");
		sql.append("t2.bmdm in (select xydm from (select a.xn,a.xq,a.tjzt,b.* from xg_zhcp_fstjjlb a left join view_njxyzybj b on a.bjdm=b.bjdm ");
		sql.append("where a.xn||a.xq = (select xn || xq from xg_pjpy_new_csszb where rownum = 1)) where 1=1 ");
		if(null!=model.getId()&&0!=model.getId().length()){
			
			sql.append(" and bjdm in ('");
			
			for (int i = 0; i < bjList.size(); i++) {
				if(i==0){
					sql.append(bjList.get(i).get("bjdm"));
				}else{
					sql.append("','"+bjList.get(i).get("bjdm"));
				}
			}
			sql.append("')");
		}
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		sql.append(" ) group by xmdm) t2 where t1.xmdm=t2.xmdm and t2.qzbl = '0') order by xssx,jktb nulls last,fjdm,xmmc  ");
		return dao.getListNotOut(sql.toString(), inputV);
		
	}
	
	
	/**
	 * 
	 * @描述: 按年级、班级查询可录入分数的综测项目
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-4-1 上午10:27:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getEditZcxmByNj(String bjdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append("and not exists (select 1 from xg_zhcp_zcblb t2 where t1.xmdm=t2.xmdm and ");
		sql.append("t2.bmdm=(select nj from view_njxyzybj where bjdm=? and rownum=1) and t2.qzbl='0')");
		sql.append("order by jktb nulls last,fjdm,xmmc ");
		return dao.getListNotOut(sql.toString(), new String[]{bjdm});
	}
	
	
	/**
	 * 
	 * @描述: 按年级、班级查询可录入分数的综测项目 
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-4-1 上午10:27:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getEditZcxmByNj(List<HashMap<String, String>> bjList, ZcfsModel model, User user) throws Exception{
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append("and not exists (select 1 from (select xmdm,sum(qzbl) qzbl from xg_zhcp_zcblb t2 where ");
		sql.append("t2.bmdm in (select a.nj from (select a.id,a.xn,a.xq,a.cpzdm,a.tjr,a.tjsj,a.tjzt,b.* from xg_zhcp_fstjjlb a left join view_njxyzybj b on a.bjdm=b.bjdm ) a ");
		sql.append("where a.xn||a.xq = (select xn || xq from xg_pjpy_new_csszb where rownum = 1) ");
		
		//如果ID为空就说明是全选，不做班级验证
		if(null!=model.getId()&&0!=model.getId().length()){
			sql.append(" and a.bjdm in ('");
			
			for (int i = 0; i < bjList.size(); i++) {
				if(i==0){
					sql.append(bjList.get(i).get("bjdm"));
				}else{
					sql.append("','"+bjList.get(i).get("bjdm"));
				}
			}
			sql.append("')");
			
		}
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		sql.append(" ) group by xmdm ) t2 where t1.xmdm=t2.xmdm and t2.qzbl = '0') order by xssx,jktb nulls last,fjdm,xmmc ");
		
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	
	
	
	
	/**
	 * 
	 * @描述:查询前两级综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-30 下午02:47:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getFirstAndSecondZcxm(){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_zhcp_zcxmb t1 where (fjdm='N' ");
		sql.append(" or exists (select 1 from xg_zhcp_zcxmb t2 where t1.fjdm=t2.xmdm and t2.fjdm='N'))");
		sql.append(" and t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) order by xmlx desc");
		List<HashMap<String,String>> rs = dao.getListNotOut(sql.toString(), new String[]{});
		if ("12861".equals(Base.xxdm)){
			rs.add(this.getGxhxm_cur_xnxq_12861());
		}
		return rs;
	}
	
	
	public List<HashMap<String,String>> getFirstAndSecondZcxm(ZcfsModel t){

		StringBuilder sql = new StringBuilder();
		
		List<String> params = new ArrayList<String>();

		sql.append(" select distinct xmmc,xmlx from xg_zhcp_zcxmb t1 where ( ");
		if(!"10335".equals(Base.xxdm)){
			sql.append(" fjdm='N' or ");
		}
		sql.append(" exists (select 1 from xg_zhcp_zcxmb t2 where t1.fjdm=t2.xmdm and t2.fjdm='N')) and ");
		sql.append(getXnxqSql(t, params));
		sql.append(" order by xmlx desc ");
		List<HashMap<String,String>> rs = dao.getListNotOut(sql.toString(), params.toArray(new String[] {}));
		if ("12861".equals(Base.xxdm)){
			rs.add(this.getGxhxm_searchTj_xnxq_12861(t));
		}
		return rs;
	}
	
	/** 
	 * 浙江机电职业技术学院 个性化项目（高级查询）
	 */
	public HashMap<String, String> getGxhxm_searchTj_xnxq_12861(ZcfsModel t){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_zhcp_zcxmb t1 where t1.xmmc like '%加权%' ");
		sql.append("and ");
		sql.append(getXnxqSql(t, params));
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[] {}));
	}
	/** 
	 * 浙江机电职业技术学院 个性化项目（手动传人学年、学期）
	 */
	public HashMap<String, String> getGxhxm_xnxq_12861(String xn, String xq){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_zhcp_zcxmb t1 where t1.xmmc like '%加权%' ");
		sql.append("and t1.xn||t1.xq=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xn+xq});
	}
	/** 
	 * 浙江机电职业技术学院 个性化项目（当前学年学期）
	 */
	public HashMap<String, String> getGxhxm_cur_xnxq_12861(){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_zhcp_zcxmb t1 where t1.xmmc like '%加权%' ");
		sql.append("and t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	public List<HashMap<String,String>> getZcxm_12861(ZcfsModel t){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		// ============ 综测项目（第二、三级） sql begin ===============
		sql.append(" select * from ( ");
		sql.append(" select a.* from ( ");
		sql.append(" select a.xmdm, ");
		sql.append(" decode(b.xmmc, 'N1', '999', a.xssx) xssx, ");
		sql.append(" a.xmmc xmmc, ");
		sql.append(" b.xmmc zfflag, ");
		sql.append(" a.xmlx, ");
		sql.append(" a.qzbl, ");
		sql.append(" decode(b.xmmc, 'N1', a.xssx, b.xssx) fxssx ");
		sql.append(" from xg_zhcp_zcxmb a ");
		sql.append(" left join ( ");
		sql.append(" select t1.xmdm,t1.xssx,decode(t1.fjdm,'N','N1',t1.xmmc) xmmc,t1.qzbl from xg_zhcp_zcxmb t1 where (t1.fjdm='N' or  ");
		sql.append(" exists (select 1 from xg_zhcp_zcxmb t2 where t1.fjdm=t2.xmdm and t2.fjdm='N')) and ");
		sql.append(getXnxqSql(t, params));
		sql.append(" ) b on a.fjdm = b.xmdm ");
		sql.append(" where b.xmdm is not null and a.xmmc not like '%加权%' ");
		sql.append(" ) a order by a.fxssx, a.xssx ");
		sql.append(" )  ");
		// ============ 综测项目（第二、三级） sql end ===============
		sql.append(" union all ");
		// ============ 综测项目（第一级） sql begin ===============
		sql.append(" select xmdm,xssx,xmmc,'N0' zfflag,'1' xmlx,qzbl,xssx fxssx from xg_zhcp_zcxmb t1 where t1.fjdm='N' and ");
		sql.append(getXnxqSql(t, params));
		// ============ 综测项目（第一级） sql end ===============
		return dao.getListNotOut(sql.toString(), params.toArray(new String[] {}));
	}
	public String getXnxqSql(ZcfsModel t, List<String> params){
		StringBuilder xnxqSql = new StringBuilder();
		if(StringUtils.isNull(t.getZczq())||t.getZczq().equals("xq")){
			if(null==t.getSearchModel().getSearch_tj_xq()||""==t.getSearchModel().getSearch_tj_xq()[0]){
				xnxqSql.append("t1.xn in ( ");
			}else{
				xnxqSql.append("t1.xn||t1.xq in ( ");
			}
		}else{
			xnxqSql.append("t1.xn||t1.xq in ( ");
		}
		for(int i=0; i< t.getSearchModel().getSearch_tj_xn().length; i++){
			if(StringUtils.isNull(t.getZczq())||t.getZczq().equals("xq")){
				//如果学期为空就不拼接学期
				if(null==t.getSearchModel().getSearch_tj_xq()){
					if(i==0){
						xnxqSql.append("?");
					}else{
						xnxqSql.append(",?");
					}
					params.add(t.getSearchModel().getSearch_tj_xn()[i]);
				}else{
					for(int j=0; j<t.getSearchModel().getSearch_tj_xq().length;j++){
						if(i==0&&j==0){
							xnxqSql.append("?");
						}else{
							xnxqSql.append(",?");
						}
						params.add(t.getSearchModel().getSearch_tj_xn()[i]+t.getSearchModel().getSearch_tj_xq()[j]);
					}
				}
				
			}else{
				if(i==0){
					xnxqSql.append("?");
				}else{
					xnxqSql.append(",?");
				}
				params.add(t.getSearchModel().getSearch_tj_xn()[i]+"on");
				}
 			}
		xnxqSql.append(" ) ");
		return xnxqSql.toString();
	}
	
	public List<HashMap<String,String>> getFirstAndSecondZcxm(String xn, String xq){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_zhcp_zcxmb t1 where (fjdm='N' ");
		sql.append(" or exists (select 1 from xg_zhcp_zcxmb t2 where t1.fjdm=t2.xmdm and t2.fjdm='N'))");
		sql.append(" and t1.xn||t1.xq='"+xn+xq+"' order by xmlx desc");
		List<HashMap<String,String>> rs = dao.getListNotOut(sql.toString(), new String[]{});
		if ("12861".equals(Base.xxdm)){
			rs.add(this.getGxhxm_xnxq_12861(xn, xq));
		}
		return rs;
	}
	
	
	/**
	 * 
	 * @描述: 综测条件项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-21 上午10:01:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZhcpTjxm(){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.xmdm dm,t1.xn, t1.xn||'学年'||t3.xqmc||t1.xmmc mc ");
		sql.append(" from xg_zhcp_zcxmb t1 left join xqdzb t3 on t1.xq = t3.xqdm");
		sql.append(" where (t1.fjdm='N' or exists (select 1 from xg_zhcp_zcxmb t2 where t1.fjdm=t2.xmdm and t2.fjdm='N'))");
		sql.append(" order by t1.xn desc,t3.xqmc desc  ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}



	/**
	 * 
	 * @描述: 查询当前周期的总分项目
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-9-27 上午11:54:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCurrentZfxm(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where fjdm = 'N' and exists (");
		sql.append("select 1 from xg_pjpy_new_csszb t2 where t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(")");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	
	
	/**
	 * 
	 * @描述:按年级保存综测比例
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 上午09:48:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertZcblByNj(ZcxmModel t) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_zhcp_zcblb(xmdm,bmdm,qzbl) ");
		sql.append("select distinct ?,nj,? from view_njxyzybj ");
		
		return dao.runUpdate(sql.toString(), new String[]{t.getXmdm(),t.getQzbl()});
	}
	
	
	/**
	 * 
	 * @描述:按学院保存综测比例
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 上午09:48:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertZcblByXy(ZcxmModel t) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_zhcp_zcblb(xmdm,bmdm,qzbl) ");
		sql.append("select distinct ?,xydm,? from view_njxyzybj ");
		
		return dao.runUpdate(sql.toString(), new String[]{t.getXmdm(),t.getQzbl()});
	}
	
	
	/**
	 * 
	 * @描述: 按项目代码查询修改过详细比例的条数
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 上午10:13:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getConutByUpdate(String xmdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) c from xg_zhcp_zcblb where ");
		sql.append("qzbl <> (select qzbl from xg_zhcp_zcxmb where xmdm=?)  and xmdm=?");
		return dao.getOneRs(sql.toString(), new String[]{xmdm,xmdm}, "c");
	}
	
	
	/**
	 * @throws Exception 
	 * @描述: 更新各年级或院系设置的详细综测比例
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 上午10:51:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 */
	public boolean updateXxbl(ZcxmModel t) throws Exception{
		
		String sql = "update xg_zhcp_zcblb set qzbl = ? where xmdm=?";
		
		return dao.runUpdate(sql, new String[]{t.getQzbl(),t.getXmdm()});
	}
	
	
	
	/**
	 * 
	 * @描述: 删除综测详细比例
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 上午10:57:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delXxbl(String xmdm) throws Exception{
		
		String sql = "delete from xg_zhcp_zcblb where xmdm=?";
		
		return dao.runDelete(sql, new String[]{xmdm}) > 0;
	}
	
	
	
	/**
	 * 
	 * @描述: 按年级初始化综测详细比例
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 上午11:09:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean initXxblByNj() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_zhcp_zcblb(xmdm,bmdm,qzbl) ");
		sql.append("select distinct t1.xmdm,t2.nj,t1.qzbl from ");
		sql.append("xg_zhcp_zcxmb t1,view_njxyzybj t2 where t1.fjdm <> 'N' ");
		sql.append("and not exists (select 1 from xg_zhcp_zcblb t3 where t1.xmdm=t3.xmdm)");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	
	/**
	 * 
	 * @描述: 按院系初始化综测详细比例
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 上午11:10:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean initXxblByXy() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_zhcp_zcblb(xmdm,bmdm,qzbl) ");
		sql.append("select distinct t1.xmdm,t2.xydm,t1.qzbl from ");
		sql.append("xg_zhcp_zcxmb t1,view_njxyzybj t2 where t1.fjdm <> 'N' ");
		sql.append("and not exists (select 1 from xg_zhcp_zcblb t3 where t1.xmdm=t3.xmdm)");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @描述: 查询设置的年级详细比例个数
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 下午02:02:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCountXxblByNj(String xn,String xq){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from xg_zhcp_zcblb t1 ");
		sql.append("left join xg_zhcp_zcxmb t2 on t1.xmdm = t2.xmdm where ");
		sql.append("exists (select 1 from view_njxyzybj t3 where t1.bmdm = t3.nj) ");
		sql.append("and t2.xn=? and xq=?");
		
		return dao.getOneRs(sql.toString(), new String[]{xn,xq}, "count");
	}
	
	
	/**
	 * 
	 * @描述: 查询设置的学院详细比例个数
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 下午02:02:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCountXxblByXy(String xn,String xq){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from xg_zhcp_zcblb t1 ");
		sql.append("left join xg_zhcp_zcxmb t2 on t1.xmdm = t2.xmdm where ");
		sql.append("exists (select 1 from view_njxyzybj t3 where t1.bmdm = t3.xydm) ");
		sql.append("and t2.xn=? and t2.xq=?");
		
		return dao.getOneRs(sql.toString(), new String[]{xn,xq}, "count");
	}
	
	
	
	/**
	 * 
	 * @描述: 删除学年、学期内详细比例
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 下午02:09:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delXxbl(String xn,String xq) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_zhcp_zcblb t1 where exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where ");
		sql.append("t1.xmdm = t2.xmdm and t2.xn=? and t2.xq=?");
		sql.append(")");
		
		return dao.runDelete(sql.toString(), new String[]{xn,xq}) > 0;
	}
	
	
	
	/**
	 * 
	 * @描述: 查询可调整详细比例的综测项目
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 下午03:38:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcxmListByXxbl(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.xmdm,t1.xmmc,t1.fjdm,t2.fjdm ffjdm,t1.qzbl,");
		sql.append("'zcxm'|| rownum name from xg_zhcp_zcxmb t1 ");
		sql.append("left join xg_zhcp_zcxmb t2 on t1.fjdm = t2.xmdm ");
		sql.append("where t1.fjdm <> 'N' and t1.xmlx <> '3' ");
		sql.append("and t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb)");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	public List<HashMap<String,String>> getSzyfList(){
		StringBuilder sql = new StringBuilder();
		 sql.append("select case when a.xq = '01' then (substr(b.xn, 1, 4)) || yf else (substr(b.xn, 6, 9)) || yf");
         sql.append(" end szyf, case  when a.xq = '01' then (substr(b.xn, 1, 4)) || '年' || yf || '月' else");
         sql.append(" (substr(b.xn, 6, 9)) || '年' || yf || '月' end szyfmc from xg_pjpy_new_yfpz a, xg_pjpy_new_csszb b");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	


	/**
	 * 
	 * @描述: 按年级查询详细比例设置
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-31 上午10:34:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param zcxmList
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXxblListByNj(ZcxmModel t, List<HashMap<String,Object>> zcxmList) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.tjzt from (select nj bmdm,");
		
		for (int i = 0 , j = zcxmList.size(); i < j ; i++){
			
			sql.append("sum(");
			sql.append(zcxmList.get(i).get("name"));
			sql.append(") ");
			sql.append(zcxmList.get(i).get("name"));
			
			List<HashMap<String,String>> sjxmList = (List<HashMap<String, String>>) zcxmList.get(i).get("sjxmList");
			
			for (int m = 0 , n = sjxmList.size() ; m < n ; m++){
				sql.append(",sum(");
				sql.append(sjxmList.get(m).get("name"));
				sql.append(") ");
				sql.append(sjxmList.get(m).get("name"));
			}
			
			if (i != j -1){
				sql.append(",");
			}
		}
		
		sql.append(" from (select distinct t2.nj,");
		
		for (int i = 0 , j = zcxmList.size(); i < j ; i++){
			
			sql.append("case when t1.xmdm='");
			sql.append(zcxmList.get(i).get("xmdm"));
			sql.append("' then t1.qzbl else '0' end ");
			sql.append(zcxmList.get(i).get("name"));
			
			List<HashMap<String,String>> sjxmList = (List<HashMap<String, String>>) zcxmList.get(i).get("sjxmList");
			
			for (int m = 0 , n = sjxmList.size() ; m < n ; m++){
				sql.append(",case when t1.xmdm='");
				sql.append(sjxmList.get(m).get("xmdm"));
				sql.append("' then t1.qzbl else '0' end ");
				sql.append(sjxmList.get(m).get("name"));
			}
			
			if (i != j -1){
				sql.append(",");
			}
		}
		
		sql.append(" from xg_zhcp_zcblb t1");
		sql.append(" left join view_njxyzybj t2 on t1.bmdm = t2.nj");
		sql.append(" ) group by nj ) t1 left join (");
		sql.append(" select max(tjzt) tjzt,nj from (");
		sql.append(" select t2.nj,case when t1.tjzt='1' then 1 else 0 end tjzt from xg_zhcp_fstjjlb t1");
		sql.append(" left join view_njxyzybj t2 on t1.bjdm=t2.bjdm");
		sql.append(" where t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb)");
		sql.append(" ) group by nj ) t2 on t1.bmdm = t2.nj");
		
		return super.getPageList(t, sql.toString(), new String[]{});
	}



	/**
	 * 
	 * @描述: 按学院查询详细比例设置
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-31 上午10:36:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param zcxmList
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXxblListByXy(ZcxmModel t, List<HashMap<String,Object>> zcxmList) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.tjzt from (select xydm bmdm,xymc,");
		
		for (int i = 0 , j = zcxmList.size(); i < j ; i++){
			
			sql.append("sum(");
			sql.append(zcxmList.get(i).get("name"));
			sql.append(") ");
			sql.append(zcxmList.get(i).get("name"));
			
			List<HashMap<String,String>> sjxmList = (List<HashMap<String, String>>) zcxmList.get(i).get("sjxmList");
			
			for (int m = 0 , n = sjxmList.size() ; m < n ; m++){
				sql.append(",sum(");
				sql.append(sjxmList.get(m).get("name"));
				sql.append(") ");
				sql.append(sjxmList.get(m).get("name"));
			}
			
			if (i != j -1){
				sql.append(",");
			}
		}
		
		sql.append(" from (select distinct t2.xymc,t2.xydm,");
		
		for (int i = 0 , j = zcxmList.size(); i < j ; i++){
			
			sql.append("case when t1.xmdm='");
			sql.append(zcxmList.get(i).get("xmdm"));
			sql.append("' then t1.qzbl else '0' end ");
			sql.append(zcxmList.get(i).get("name"));
			
			List<HashMap<String,String>> sjxmList = (List<HashMap<String, String>>) zcxmList.get(i).get("sjxmList");
			
			for (int m = 0 , n = sjxmList.size() ; m < n ; m++){
				sql.append(",case when t1.xmdm='");
				sql.append(sjxmList.get(m).get("xmdm"));
				sql.append("' then t1.qzbl else '0' end ");
				sql.append(sjxmList.get(m).get("name"));
			}
			
			if (i != j -1){
				sql.append(",");
			}
		}
		
		sql.append(" from xg_zhcp_zcblb t1");
		sql.append(" left join view_njxyzybj t2 on t1.bmdm = t2.xydm");
		sql.append(" ) where xydm is not null group by xymc,xydm) t1 left join (");
		sql.append(" select max(tjzt) tjzt,xydm from (");
		sql.append(" select t2.xydm,case when t1.tjzt='1' then 1 else 0 end tjzt ");
		sql.append(" from xg_zhcp_fstjjlb t1 ");
		sql.append(" left join view_njxyzybj t2 on t1.bjdm=t2.bjdm ");
		sql.append(" where t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb) ");
		sql.append(" ) group by xydm ) t2 on t1.bmdm = t2.xydm ");
		
		return super.getPageList(t, sql.toString(), new String[]{});
	}



	/**
	 * 
	 * @描述: 按年级或学院更新项目详细比例
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-31 下午04:58:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXxblByBmdm(ZcxmModel t) throws Exception{
		
		String sql = "update xg_zhcp_zcblb set qzbl=? where bmdm=? and xmdm=?";
		return dao.runUpdate(sql, new String[]{t.getQzbl(),t.getBmdm(),t.getXmdm()});
	}

	
	/**
	 * 
	 * @描述:查询当前综测学期最大值
	 * @作者：cq [工号：785]
	 * @日期：2014-8-6 下午04:46:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getMaxZcxq(String xn) throws Exception {
		String sql = "select max(xq) xq from xg_zhcp_zcxmb where xn = ? and xq <> 'on'"	;
		
		return dao.getOneRs(sql, new String[]{xn}, "xq");
	}
	
	/**
	 * 
	 * @描述:根据项目代码查询项目信息
	 * @作者：cq [工号：785]
	 * @日期：2014-8-6 下午05:34:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getZcxm(String xmdm) throws Exception{
		String sql = "select * from xg_zhcp_zcxmb where xmdm = ?";
			
		return dao.getMapNotOut(sql, new String[]{xmdm});
	} 
	
	
	/**
	 * 
	 * @描述:查询名称是否存在
	 * @作者：cq [工号：785]
	 * @日期：2014-8-8 上午11:49:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String xmmcExist(ZcxmModel model){
		
		String sql = "select count(1) count from xg_zhcp_zcxmb where xn = ? and xq= ? and xmmc = ? ";
		
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXmmc()}, "count");
		
		return num;
	}
	
	
	/**
	 * 
	 * 判断是否为空
	 * 
	 */
	public Boolean getIsblank(List<HashMap<String, String>> bjxxMap,ZcfsModel model){
		
		boolean isBlank = false;
		
		if(!org.apache.commons.lang.StringUtils.isBlank(model.getId())&&null!=bjxxMap&&0!=bjxxMap.size()){
			isBlank = true;
		}
		
		return isBlank;
	}
	
	public boolean delZcyf(String xn,String xq) throws Exception{
		String sql = "delete from XG_PJPY_NEW_YFSZ where xn = ? and xq= ? ";
		
		return dao.runUpdate(sql, new String[]{xn,xq});
	}
	public boolean insertZcyf(List<String[]> params) throws Exception{
		String sql = "insert into XG_PJPY_NEW_YFSZ values(?,?,?) ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	public List<String> getYszYf(String xn,String xq) throws Exception{
		String sql = "select yf from XG_PJPY_NEW_YFSZ where xn=? and xq=?";
		return dao.getList(sql, new String[]{xn,xq}, "yf");
	}
	
	public List<HashMap<String,String>> getZcYf(String xn,String xq) throws Exception{
		String sql = "select yf zcyf,substr(yf,0,4)||'年'||substr(yf,5,6)||'月' zcyfmc from XG_PJPY_NEW_YFSZ where xn=? and xq=?";
		return dao.getListNotOut(sql, new String[]{xn,xq});
	}
	
	
	/**
	 * 
	 * @描述: 获取当前综测年的所有信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-11-25 下午02:51:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws SQLException
	 * List<String> 返回类型 
	 * @throws
	 */
	
	public List<HashMap<String, String>> getJktbS(String xmdm, String type) throws SQLException{
		
		List<String> params = new ArrayList<String>();
		String[] xmdms = xmdm.split(",");
				
		StringBuffer sql = new StringBuffer(); 
		sql.append("select * from xg_zhcp_zcxmb where xmdm in (");
		for (int i = 0; i < xmdms.length; i++) {
			if(i==0){
				sql.append("?");
			}else{
				sql.append(",?");
			}
			params.add(xmdms[i]);
		}
		
		sql.append(")");
		if(!"12688".equals(type) && !"13431".equals(type))
		sql.append(" and jktb is not null ");
		//青岛滨海学院个性化、青岛酒店管理学院个性化
		if(!Base.xxdm.equals("10868") && !Base.xxdm.equals("13011")){			
			if("01".equals(type)){
				sql.append(" and xssx in ('01','02') ");
			} else if("02".equals(type)){
				sql.append(" and xssx in ('03','04','05','06') ");
			}
		}
		sql.append(" order by xssx");
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	/*
	 * 取所有需要同步的综测分数
	 */
	public List<HashMap<String,String>> getJktbSHHYAll(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append(" and jktb is not null ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append(" order by xssx,fjdm,xmmc ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

}
