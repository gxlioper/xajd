package xgxt.gygl.tjfx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.basic.BasicService;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_统计分析_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author zhanghui
 * @version 1.0
 */

public class GyglTjfxDAO extends CommDAO {
	
	
	//==========================start=====*寝室床位统计*=====start==============================//
	/**
	 * 获取寝室床位统计列表(按楼栋)
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getQscwtjListByld(GyglTjfxForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
				
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		String[] colList = { "lddm", "xydm", "yfp", "wfp", "bkfp","cwnum","qsnum" };
		sql.append(" select lddm,xydm,count(distinct(cs||qsh)) qsnum," +
				"sum(case when xh is not null then 1 else 0 end) yfp," +
				"sum(case when cwbj='可分配' then 1 else 0 end)-sum(case when xh is not null then 1 else 0 end) wfp," +
				"sum(case when cwbj='不可分配' then 1 else 0 end) bkfp,count(*) cwnum " +
				"from (select a.*,c.xh,d.bmdm xydm,d.nj,d.fpdx,e.ldmc,e.yqdm,f.yqmc,e.xqdm,g.xqmc,h.xb,x.xm from xg_gygl_cwxxb a " +
				"left join xszsxxb c on a.lddm = c.lddm and a.cs = c.cs and a.qsh = c.qsh and a.cwh = c.cwh and rownum =1 " +
				"left join xg_gygl_qsfpb d on a.lddm = d.lddm and a.cs = d.cs and a.qsh = d.qsh and rownum =1 " +
				"left join ssxxb h on a.lddm = h.lddm and a.cs = h.cs and a.qsh = h.qsh " +
				"left join sslddmb e on a.lddm = e.lddm " +
				"left join yqdmb f on e.yqdm = f.yqdm " +
				"left join dm_zju_xq g on e.xqdm = g.dm " +
				"left join view_xsjbxx x on c.xh = x.xh ) " +
				"where fpdx=(select fpdx from xg_gygl_jbszb where rownum=1) and xydm is not null ");
		sql.append(searchTj).append(" group by lddm,xydm");

		DAO dao=DAO.getInstance();
		return dao.getList(sql.toString(), inputV, colList);
	}
	
	/**
	 * 获取寝室床位统计列表(按性别)
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getQscwtjListByxb(GyglTjfxForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
				
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		String[] colList = { "xb", "xydm", "yfp", "wfp", "bkfp","cwnum","qsnum"};
		sql.append(" select xb,xydm,count(distinct(cs||qsh)) qsnum," +
				"sum(case when xh is not null then 1 else 0 end) yfp," +
				"sum(case when cwbj='可分配' then 1 else 0 end)-sum(case when xh is not null then 1 else 0 end) wfp," +
				"sum(case when cwbj='不可分配' then 1 else 0 end) bkfp,count(*) cwnum " +
				"from (select a.*,c.xh,d.bmdm xydm,d.nj,d.fpdx,e.ldmc,e.yqdm,f.yqmc,e.xqdm,g.xqmc,h.xb,x.xm from xg_gygl_cwxxb a " +
				"left join xszsxxb c on a.lddm = c.lddm and a.cs = c.cs and a.qsh = c.qsh and a.cwh = c.cwh and rownum =1 " +
				"left join xg_gygl_qsfpb d on a.lddm = d.lddm and a.cs = d.cs and a.qsh = d.qsh and rownum =1 " +
				"left join ssxxb h on a.lddm = h.lddm and a.cs = h.cs and a.qsh = h.qsh " +
				"left join sslddmb e on a.lddm = e.lddm " +
				"left join yqdmb f on e.yqdm = f.yqdm " +
				"left join dm_zju_xq g on e.xqdm = g.dm " +
				"left join view_xsjbxx x on c.xh = x.xh ) " +
				"where fpdx=(select fpdx from xg_gygl_jbszb where rownum=1) and (xb ='男' or xb='女') ");
		sql.append(searchTj).append(" group by xb,xydm");

		DAO dao=DAO.getInstance();
		return dao.getList(sql.toString(), inputV, colList);
	}
	
	/**
	 * 获取全部楼栋列表
	 */
	public List<HashMap<String,String>> getLdlist(GyglTjfxForm myForm) throws Exception {
		DAO dao=DAO.getInstance();
		
		//楼栋列表根据查询条件中的校区、园区、楼栋条件加载
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_lddm(myForm.getSearchModel().getSearch_tj_lddm());	
		searchModel.setSearch_tj_yqdm(myForm.getSearchModel().getSearch_tj_yqdm());	
		searchModel.setSearch_tj_xqdm(myForm.getSearchModel().getSearch_tj_xqdm());	
		searchModel.setPath(myForm.getSearchModel().getPath());
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		// 高级查询条件值
		String[] inputV = SearchService.getTjInput(searchModel);
		
		return dao.getList("select * from (select a.*,b.yqmc,c.xqmc," +
				"(select count(distinct(cs||qsh)) from xg_gygl_cwxxb x where x.lddm = a.lddm) qsnum," +
				"(select count(*) from xg_gygl_cwxxb x where x.lddm = a.lddm) cwnum," +
				"(select count(*) from xg_gygl_qsfpb x where x.lddm = a.lddm) yfpqsnum," +
				"(select count(*) from xg_gygl_cwxxb x left join xg_gygl_qsfpb d on x.lddm = d.lddm and x.cs = d.cs and x.qsh = d.qsh " +
				"where x.lddm = a.lddm and d.bmdm is not null) yfpcwnum " +
				"from sslddmb a left join yqdmb b on a.yqdm = b.yqdm left join dm_zju_xq c on a.xqdm = c.dm) where 1=1"+searchTj, 
				inputV, new String[]{"lddm","ldmc","xqmc","yqmc","qsnum","cwnum","yfpqsnum","yfpcwnum","cs"});
	}
	
	/**
	 * 获取全部学院列表
	 */
	public List<HashMap<String,String>> getXylist(GyglTjfxForm myForm,
			HttpServletRequest request) throws Exception {
		DAO dao=DAO.getInstance();
		
		
		//学院列表根据条件中的学院加载，不选择默认全部
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xy(myForm.getSearchModel().getSearch_tj_xy());	
		searchModel.setPath(myForm.getSearchModel().getPath());
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		// 高级查询条件值
		String[] inputV = SearchService.getTjInput(searchModel);
		// 权限控制
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "bjdm");
		searchTj += searchTjByUser;
		return dao.getList("select distinct(xydm),xymc from view_njxyzybj a where 1=1 "+searchTj+" order by xymc", 
				inputV, new String[]{"xydm","xymc"});
	}
	
	/**
	 * 获取性别列表
	 */
	public List<HashMap<String,String>> getXblist(GyglTjfxForm myForm) throws Exception {
		DAO dao=DAO.getInstance();
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_lddm(myForm.getSearchModel().getSearch_tj_lddm());	
		searchModel.setSearch_tj_yqdm(myForm.getSearchModel().getSearch_tj_yqdm());	
		searchModel.setSearch_tj_xqdm(myForm.getSearchModel().getSearch_tj_xqdm());	
		searchModel.setPath(myForm.getSearchModel().getPath());
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		// 高级查询条件值
		String[] inputV = SearchService.getTjInput(searchModel);
		
		return dao.getList("select a.*,b.qsnum,b.yfpqsnum from ( " +
				"select xb,count(*) cwnum,sum(case when xydm is not null then 1 else 0 end) yfpcwnum from " +
				"(select * from " +
				"(select a.*,c.yqdm,c.yqmc,d.dm xqdm,d.xqmc,h.xb,x.bmdm xydm from xg_gygl_cwxxb a " +
				"left join sslddmb b on a.lddm = b.lddm " +
				"left join yqdmb c on b.yqdm = c.yqdm " +
				"left join dm_zju_xq d on c.xqdm = d.dm " +
				"left join ssxxb h on a.lddm = h.lddm and a.cs = h.cs and a.qsh = h.qsh " +
				"left join xg_gygl_qsfpb x on a.lddm = x.lddm and a.cs = x.cs and a.qsh = x.qsh " +
				") where (xb='男' or xb='女')  "+searchTj+" )group by xb) a " +
				"left join (" +
				"select xb,count(*) qsnum,sum(case when xydm is not null then 1 else 0 end) yfpqsnum from " +
				"(select * from " +
				"(select a.*,c.yqdm,c.yqmc,d.dm xqdm,d.xqmc,x.bmdm xydm	from ssxxb a " +
				"left join sslddmb b on a.lddm = b.lddm " +
				"left join yqdmb c on b.yqdm = c.yqdm " +
				"left join dm_zju_xq d on c.xqdm = d.dm " +
				"left join xg_gygl_qsfpb x on a.lddm = x.lddm and a.cs = x.cs and a.qsh = x.qsh " +
				") where (xb='男' or xb='女')  "+searchTj+" )group by xb ) b on a.xb = b.xb ",
				dao.unionArray(inputV, inputV), new String[]{"xb","qsnum","cwnum","yfpqsnum","yfpcwnum"});
	}
	
	//===========================end======*寝室床位统计*======end===============================//
	
	
	//==========================start=====*寝室人员统计*=====start==============================//
	/**
	 * 获取寝室床位统计列表(按楼栋)
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getQsrytjListByld(GyglTjfxForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
				
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		//结果集：楼栋代码、层数、房间数、人数、在校生
		String[] colList = { "lddm", "cs", "qsnum", "num", "zxs" };
		sql.append(" select lddm,cs,count(distinct(cs || qsh)) qsnum," +
				"sum(case when xh is not null then 1 else 0 end) num," +
				"sum(case when xh is not null and (sfyby = '否' or sfyby is null) " +
				"and (sfzx = '在校' or sfzx is null) then 1 else 0 end) zxs " +
				"from (select a.*,c.xh,d.bmdm xydm,d.nj,d.fpdx,e.ldmc,e.yqdm,f.yqmc,e.xqdm," +
				"g.xqmc,h.xb,x.xm,x.sfyby,x.sfzx from xg_gygl_cwxxb a " +
				"left join xszsxxb c on a.lddm = c.lddm and a.cs = c.cs and a.qsh = c.qsh and a.cwh = c.cwh and rownum =1 " +
				"left join xg_gygl_qsfpb d on a.lddm = d.lddm and a.cs = d.cs and a.qsh = d.qsh and rownum =1 " +
				"left join ssxxb h on a.lddm = h.lddm and a.cs = h.cs and a.qsh = h.qsh " +
				"left join sslddmb e on a.lddm = e.lddm " +
				"left join yqdmb f on e.yqdm = f.yqdm " +
				"left join dm_zju_xq g on e.xqdm = g.dm " +
				"left join view_xsbfxx x on c.xh = x.xh ) " +
				"where fpdx=(select fpdx from xg_gygl_jbszb where rownum=1) and cs is not null ");
		sql.append(searchTj).append(" group by lddm,cs");

		DAO dao=DAO.getInstance();
		return dao.getList(sql.toString(), inputV, colList);
	}
	
	/**
	 * 获取寝室床位统计列表(按性别)
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getQsrytjListByxb(GyglTjfxForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
				
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// 结果集：性别、层数、房间数、人数、在校生
		String[] colList = { "xb", "cs", "qsnum", "num", "zxs" };
		sql.append(" select xb,cs,count(distinct(cs || qsh)) qsnum," +
				"sum(case when xh is not null then 1 else 0 end) num," +
				"sum(case when xh is not null and (sfyby = '否' or sfyby is null) " +
				"and (sfzx = '在校' or sfzx is null) then 1 else 0 end) zxs " +
				"from (select a.*,c.xh,d.bmdm xydm,d.nj,d.fpdx,e.ldmc,e.yqdm,f.yqmc,e.xqdm," +
				"g.xqmc,h.xb,x.xm,x.sfyby,x.sfzx from xg_gygl_cwxxb a " +
				"left join xszsxxb c on a.lddm = c.lddm and a.cs = c.cs and a.qsh = c.qsh and a.cwh = c.cwh and rownum =1 " +
				"left join xg_gygl_qsfpb d on a.lddm = d.lddm and a.cs = d.cs and a.qsh = d.qsh and rownum =1 " +
				"left join ssxxb h on a.lddm = h.lddm and a.cs = h.cs and a.qsh = h.qsh " +
				"left join sslddmb e on a.lddm = e.lddm " +
				"left join yqdmb f on e.yqdm = f.yqdm " +
				"left join dm_zju_xq g on e.xqdm = g.dm " +
				"left join view_xsbfxx x on c.xh = x.xh ) " +
				"where fpdx=(select fpdx from xg_gygl_jbszb where rownum=1) and (xb ='男' or xb='女') ");
		sql.append(searchTj).append(" group by xb,cs");

		DAO dao=DAO.getInstance();
		return dao.getList(sql.toString(), inputV, colList);
	}
	
	/**
	 * 获取全部楼栋层数最大值
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public int getMaxCs(GyglTjfxForm myForm) throws Exception {
		
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_lddm(myForm.getSearchModel().getSearch_tj_lddm());	
		searchModel.setSearch_tj_yqdm(myForm.getSearchModel().getSearch_tj_yqdm());	
		searchModel.setSearch_tj_xqdm(myForm.getSearchModel().getSearch_tj_xqdm());	
		searchModel.setPath(myForm.getSearchModel().getPath());
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		// 高级查询条件值
		String[] inputV = SearchService.getTjInput(searchModel);
		
		DAO dao=DAO.getInstance();		
		return Integer.valueOf(dao.getOneRs("select max(cs) num from sslddmb where 1=1 "+searchTj,inputV, "num"));
	}
	//===========================end======*寝室人员统计*======end===============================//	
	
	
// ======================年级分布统计 qlj=============================
	public List<String[]>getNjfbtjList(GyglTjfxForm myForm,
			HttpServletRequest request) throws Exception {
		
		List<HashMap<String,String>>xyxxList=getNjfbtjXyxx(myForm,request);
		List<HashMap<String,String>>njxxList=getNjfbtjNjxx(myForm,request);
		ArrayList<String>colList=new ArrayList<String>();
		
		StringBuilder sql=new StringBuilder();
		StringBuilder firstFloor=new StringBuilder();
		StringBuilder secoundFloor=new StringBuilder();
		StringBuilder rowsToCols=new StringBuilder();
		StringBuilder xszd=new StringBuilder();
		
		SearchService searchService = new SearchService();	
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());	
		//高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
				
		//学院、辅导员权限	
		String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");	
		colList.add("xqmc");
		colList.add("yqmc");
		colList.add("lddm");
		colList.add("ldmc");
		for(int i=0;i<xyxxList.size();i++){
			HashMap<String,String>xyxxMap=xyxxList.get(i);
			for(int j=0;j<njxxList.size();j++){
				HashMap<String,String>njxxMap=njxxList.get(j);
				rowsToCols.append(" ,case when nj='"+njxxMap.get("nj")+"' and xydm='"+xyxxMap.get("xydm")+"' then cwrz end cwxx_"+xyxxMap.get("xydm")+"_"+njxxMap.get("nj"));
				
				xszd.append(" ,sum(cwxx_"+xyxxMap.get("xydm")+"_"+njxxMap.get("nj")+") cwxx_"+xyxxMap.get("xydm")+"_"+njxxMap.get("nj"));
				
				colList.add("cwxx_"+xyxxMap.get("xydm")+"_"+njxxMap.get("nj"));
			}
		}
		secoundFloor.append(" select xqmc,yqmc,lddm,ldmc ");
		secoundFloor.append(xszd);
		secoundFloor.append(" from (");
		secoundFloor.append(" select xqmc,yqmc,lddm,ldmc ");
		secoundFloor.append(rowsToCols);
		secoundFloor.append(" from ");
		// =========================第一层统计查询=======================
		firstFloor.append("( select  a.lddm,a.ldmc,a.xqmc,a.yqmc,b.xydm,b.nj,b.cwrz from xg_view_gygl_sslddm a left join  ");
		firstFloor.append("( select count(1)cwrz,a.lddm,b.xydm,b.nj from xszsxxb a ");
		firstFloor.append(" left join ");
		firstFloor.append(" (select xydm,nj,xh from view_xsjbxx a where 1=1 ");
		firstFloor.append(searchTjByUser);
		firstFloor.append(" )b on a.xh=b.xh group by lddm,xydm,nj )b on a.lddm=b.lddm) ) group by lddm,ldmc,xqmc,yqmc");
		// =========================第一层统计查询end=======================
		sql.append(secoundFloor);
		sql.append(firstFloor);
		System.out.println(sql);
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", new String[]{}, colList.toArray(new String[]{}), myForm);
	}
	
	public List<String[]>getNjfbzjList(GyglTjfxForm myForm,
			HttpServletRequest request) throws Exception {
		
		List<HashMap<String,String>>njxxList=getNjfbtjNjxx(myForm,request);
		ArrayList<String>colList=new ArrayList<String>();
		
		StringBuilder sql=new StringBuilder();
		StringBuilder firstFloor=new StringBuilder();
		StringBuilder secoundFloor=new StringBuilder();
		StringBuilder rowsToCols=new StringBuilder();
		StringBuilder xszd=new StringBuilder();
		SearchService searchService = new SearchService();	
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());	
		//高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
				
		//学院、辅导员权限	
		String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");	
		
		colList.add("xqmc");
		colList.add("yqmc");
		colList.add("lddm");
		colList.add("ldmc");
			for(int j=0;j<njxxList.size();j++){
				HashMap<String,String>njxxMap=njxxList.get(j);
				rowsToCols.append(" ,case when nj='"+njxxMap.get("nj")+"' then cws end cwxx_"+njxxMap.get("nj"));
				xszd.append(" ,sum(cwxx_"+njxxMap.get("nj")+") cwxx_"+njxxMap.get("nj"));
				
				colList.add("cwxx_"+njxxMap.get("nj"));
			}
		
		secoundFloor.append(" select xqmc,yqmc,lddm,ldmc ");
		secoundFloor.append(xszd);
		secoundFloor.append(" from (");
		secoundFloor.append(" select xqmc,yqmc,lddm,ldmc,nj ");
		secoundFloor.append(rowsToCols);
		secoundFloor.append(" from ");
		// =========================第一层统计查询=======================
		firstFloor.append("( select a.xqmc,a.yqmc,a.lddm,a.ldmc,b.cws,b.nj from xg_view_gygl_sslddm a left join  ");
		firstFloor.append(" (select count(1)cws,nj,lddm from xg_view_gygl_xszsxx a where 1=1 ");
		firstFloor.append(searchTjByUser);
		firstFloor.append(" group by nj,lddm)b on a.lddm=b.lddm))group by lddm,ldmc,xqmc,yqmc ");
		
		// =========================第一层统计查询end=======================
		sql.append(secoundFloor);
		sql.append(firstFloor);
		sql.append(" order by xqmc,yqmc,ldmc ");
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", new String[]{}, colList.toArray(new String[]{}), myForm);
	}
	
	/**
	 * 获取年级分布统计(学院信息)
	 * @param myForm
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws Exception 
	 */
	public List<HashMap<String,String>>getNjfbtjXyxx(GyglTjfxForm myForm,
			HttpServletRequest request) throws Exception{
		SearchService searchService = new SearchService();	
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());	
		//高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
				
		//学院、辅导员权限	
		String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");	
		
		DAO dao=DAO.getInstance();		
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct(xymc)xymc,xydm from view_njxyzybj a where 1=1 ");
		sql.append(searchTj+searchTjByUser);
		sql.append(" order by xydm ");
		StringBuilder query = new StringBuilder();
		
		return dao.getList(sql.toString(), inputV, new String[]{"xydm","xymc"});
	}
	
	/**
	 * 获取年级分布统计(年级)
	 * @param myForm
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws Exception 
	 */
	public List<HashMap<String,String>>getNjfbtjNjxx(GyglTjfxForm myForm
			,HttpServletRequest request) throws Exception{
		SearchService searchService = new SearchService();	
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());	
		//高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
				
		//学院、辅导员权限	
		String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");	
		DAO dao=DAO.getInstance();		
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct(nj) from view_njxyzybj a where 1=1 ");
		sql.append(searchTj+searchTjByUser);
		sql.append(" order by nj ");
		
		return dao.getList(sql.toString(), inputV, new String[]{"nj"});
	}
	
	/**
	 * 楼栋信息获取(年级)
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>>getNjfbtjLdxx(GyglTjfxForm myForm){
		
		DAO dao=DAO.getInstance();		
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select lddm from sslddmb where 1=1  order by lddm  ");
		
		StringBuilder query = new StringBuilder();
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"lddm"});
	}
	
	/**
	 * 获取年级分布统计(年级)
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>>getXqYqLd(GyglTjfxForm myForm){
		
		DAO dao=DAO.getInstance();		
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select xqmc,yqmc,ldmc,lddm from xg_view_gygl_sslddm where 1=1 order by xqmc,yqmc,ldmc ");
		
		StringBuilder query = new StringBuilder();
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xqmc","yqmc","ldmc","lddm"});
	}
	// ======================年级分布统计 end=============================
	
	
}
