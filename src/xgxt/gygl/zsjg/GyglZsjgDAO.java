package xgxt.gygl.zsjg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.gygl.gywh.GyglGywhDAO;
import xgxt.gygl.gywh.GyglGywhForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_住宿结果_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class GyglZsjgDAO extends CommDAO {
	
	/**
	 * 获取学生住宿历史信息列表
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZslsxxList(GyglZsjgForm myForm,
			HttpServletRequest request) throws Exception {
		SearchService searchService = new SearchService();
		StringBuilder sql = new StringBuilder();
		StringBuilder query = new StringBuilder();
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// 学院、辅导员权限
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "bjdm");

		String[] colList = { "guid", "xh", "xm", "nj", "bjmc", "zymc", "xymc",
				"xb", "ldmc", "qsh", "cwh" };
		sql.append(" select rownum r,guid,xh,xm,nj,bjmc,zymc,xymc,xb,ldmc,qsh,cwh from xszslsxxb where 1=1 ");
		query.append(searchTj);
		query.append(searchTjByUser);
		return CommonQueryDAO.commonQuery(sql.toString(), query.toString(),
				inputV, colList, myForm);
	}
	
	/**
	 * 获取学生住宿信息列表
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZsxxList(GyglZsjgForm myForm, User user,
			String[] colList, String query, String[] inPutList) throws Exception {
		
		StringBuilder sql = new StringBuilder();

		sql.append(" select rownum r,xh pkValue,xh,xm,nj,bjmc,zymc,xymc,xydm,zydm,ssbh,");
		sql.append(" bjdm,xb,ldmc,lddm,xqdm,xqmc,yqdm,yqmc,qsh,cs,cwh,rzrq from xg_view_gygl_xszsxx a where 1=1  ");

		return CommonQueryDAO.commonQuery(sql.toString(), query,
				inPutList, colList, myForm);
	}
	
	/**
	 * 获取学生住宿信息列表
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getYdtjList(GyglZsjgForm myForm,
			HttpServletRequest request) throws Exception {
		SearchService searchService = new SearchService();
		StringBuilder sql = new StringBuilder();
		StringBuilder query = new StringBuilder();
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询条件值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// 学院、辅导员权限
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "bjdm");

		String[] colList = { "pkValue", "xh", "xm", "xb", "nj", "xymc", "zymc",
				"bjmc", "ssbh", "ldmc", "cs","qsh","cwh","rzrq" };
		sql.append(" select rownum r,xh pkValue,xh,xm,nj,bjmc,zymc,xymc,xydm,zydm,ssbh,");
		sql.append(" bjdm,xb,ldmc,lddm,xqdm,xqmc,yqdm,yqmc,qsh,cs,cwh,rzrq from xg_view_gygl_xszsxx a where 1=1  ");
		query.append(searchTj);
		query.append(searchTjByUser);
		return CommonQueryDAO.commonQuery(sql.toString(), query.toString(),
				inputV, colList, myForm);
	}
	
	public List<String[]>getXyCwxx(GyglZsjgForm myForm, User user,
			String[] colList, String query, String[] inPutList) throws Exception{
		StringBuilder sql=new StringBuilder();	
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
	
		// ======================学院人数==========================
		sql.append(" select rownum r,a.xydm pkValue,a.xydm,a.xymc,nvl(a.xyrs, 0)xyrs, nvl(b.rzbss, 0)rzbss,nvl(c.rzqtss, 0)rzqtss from ( ");
		sql.append(" select xydm,xymc  ");
		sql.append(" ,count(1)xyrs  ");
		sql.append(" from view_xsbfxx a where 1 = 1  ");
		sql.append(query);
		sql.append("  group by  ");
		sql.append(" xydm,xymc )a left join  ");
		
		// ======================学院 入住本部门宿舍==========================
		sql.append(" ( select a.xydm,count(1)rzbss  from( ");
		sql.append(" select c.xydm  from ");                                                            
		sql.append(" (  select b.*,a.xh from xszsxxb a left join xg_gygl_qsfpb  b "); 
		sql.append(" on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh )a ");
		sql.append(" left join view_xsjbxx c on a.xh=c.xh  where  a.bmdm=c.xydm   )a group by a.xydm");
		sql.append(" )b on a.xydm=b.xydm left join ");
		
		// ======================学院 入住其它宿舍==========================
		sql.append(" ( select a.xydm,count(1)rzqtss  from( ");
		sql.append(" select c.xydm from ");                                                            
		sql.append(" (  select b.*,a.xh from xszsxxb a left join xg_gygl_qsfpb  b "); 
		sql.append(" on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh )a ");
		sql.append(" left join view_xsjbxx c on a.xh=c.xh  where  a.bmdm<>c.xydm   )a group by a.xydm");
		sql.append(" )c on a.xydm=c.xydm ");
		                 
		sql.append(" where 1=1  ");
		return CommonQueryDAO.commonQuery(sql.toString(),"",inputV,colList, myForm);
	} 
	
	public List<String[]>getNjXyCwxx(GyglZsjgForm myForm, User user,
			String[] colList, String query, String[] inPutList) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
		
	
		// ======================年级+学院 人数统计==========================
		sql.append(" select rownum r,a.nj||'!!@@!!'||a.xydm pkValue,a.nj,a.xydm,a.xymc, nvl(a.xyrs, 0)xyrs, nvl(b.rzbss, 0)rzbss,nvl(c.rzqtss, 0)rzqtss from ( ");
		sql.append(" select nj,xydm,xymc  ");
		sql.append(" ,count(1)xyrs  ");
		sql.append(" from view_xsbfxx a where 1 = 1  ");
		sql.append(query);
		sql.append("  group by  ");
		sql.append(" nj,xydm,xymc )a left join  ");
		
		// ======================年级+学院 入住本部门宿舍==========================
		sql.append(" ( select a.nj,a.xydm,count(1)rzbss  from( ");
		sql.append(" select c.xydm,c.nj from ");                                                            
		sql.append(" (  select b.*,a.xh from xszsxxb a left join xg_gygl_qsfpb  b "); 
		sql.append(" on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh )a ");
		sql.append(" left join view_xsjbxx c on a.xh=c.xh  where a.nj=c.nj and a.bmdm=c.xydm   )a group by a.nj,a.xydm");
		sql.append(" )b on a.xydm=b.xydm and a.nj=b.nj  left join ");
		
		// ======================年级+学院 入住其它宿舍==========================
		sql.append(" ( select a.nj,a.xydm,count(1)rzqtss  from( ");
		sql.append(" select c.xydm,c.nj from ");                                                            
		sql.append(" (  select b.*,a.xh from xszsxxb a left join xg_gygl_qsfpb  b "); 
		sql.append(" on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh )a ");
		sql.append(" left join view_xsjbxx c on a.xh=c.xh  where  (a.nj<>c.nj or a.bmdm<>c.xydm )   )a group by a.nj,a.xydm");
		sql.append(" )c on a.xydm=c.xydm and a.nj=c.nj  ");
		              
		sql.append("  where 1=1   ");
		
		return CommonQueryDAO.commonQuery(sql.toString(),"",inputV,colList, myForm);
	} 
	
	public List<String[]>getNjZyCwxx(GyglZsjgForm myForm, User user,
			String[] colList, String query, String[] inPutList) throws Exception{
		
		StringBuilder sql=new StringBuilder();	
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
	
		// ======================年级专业人数==========================
		sql.append(" select rownum r,a.nj||'!!@@!!'||a.zydm pkValue,a.nj,a.zydm,a.zymc,nvl(a.xyrs, 0)xyrs, nvl(b.rzbss, 0)rzbss,nvl(c.rzqtss, 0)rzqtss from ( ");
		sql.append(" select nj,zydm,zymc  ");
		sql.append(" ,count(1)xyrs  ");
		sql.append(" from view_xsbfxx a where 1 = 1  ");
		sql.append(query);
		sql.append("  group by  ");
		sql.append(" nj,zydm,zymc )a left join  ");
		
		// ======================年级+专业 入住本部门宿舍==========================
		sql.append(" ( select a.nj,a.zydm,count(1)rzbss  from( ");
		sql.append(" select c.zydm,c.nj from ");                                                            
		sql.append(" (  select b.*,a.xh from xszsxxb a left join xg_gygl_qsfpb  b "); 
		sql.append(" on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh )a ");
		sql.append(" left join view_xsjbxx c on a.xh=c.xh  where a.nj=c.nj and a.bmdm=c.zydm   )a group by a.nj,a.zydm");
		sql.append(" )b on a.zydm=b.zydm and a.nj=b.nj  left join ");
		
		// ======================年级+专业 入住其它宿舍==========================
		sql.append(" ( select a.nj,a.zydm,count(1)rzqtss  from( ");
		sql.append(" select c.zydm,c.nj from ");                                                            
		sql.append(" (  select b.*,a.xh from xszsxxb a left join xg_gygl_qsfpb  b "); 
		sql.append(" on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh )a ");
		sql.append(" left join view_xsjbxx c on a.xh=c.xh  where (a.nj<>c.nj or a.bmdm<>c.zydm )  )a group by a.nj,a.zydm");
		sql.append(" )c on a.zydm=c.zydm and a.nj=c.nj  ");
		
		sql.append("  where 1=1  ");
		return CommonQueryDAO.commonQuery(sql.toString(),"",inputV,colList, myForm);
	} 
	
	public List<String[]>getNjBjCwxx(GyglZsjgForm myForm, User user,
			String[] colList, String query, String[] inPutList) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
	
		// ======================年级专业人数==========================
		sql.append(" select rownum r,a.bjdm pkValue,a.nj,a.xydm,a.xymc,a.zydm,a.zymc, a.bjdm,a.bjmc,nvl(a.xyrs, 0)xyrs, nvl(b.rzbss, 0)rzbss,nvl(c.rzqtss, 0)rzqtss from (");
		sql.append("  select  nj,xydm,xymc,zydm,zymc,bjdm,bjmc ");
		sql.append("  ,count(1)xyrs ");
		sql.append("  from view_xsbfxx a where 1 = 1 ");
		sql.append(query);
		sql.append("   group by ");
		sql.append("  nj,xydm,xymc,zydm,zymc,bjdm,bjmc )a left join ");
		
		// ======================年级+专业 入住本部门宿舍==========================
		sql.append(" ( select a.bjdm,count(1)rzbss  from( ");
		sql.append(" select c.bjdm from ");                                                            
		sql.append(" (  select b.*,a.xh from xszsxxb a left join xg_gygl_qsfpb  b "); 
		sql.append(" on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh )a ");
		sql.append(" left join view_xsjbxx c on a.xh=c.xh  where  a.bmdm=c.bjdm   )a group by a.bjdm ");
		sql.append(" )b on a.bjdm=b.bjdm  left join ");
		
		// ======================年级+专业 入住其它宿舍==========================
		sql.append(" ( select a.bjdm,count(1)rzqtss  from( ");
		sql.append(" select c.bjdm from ");                                                            
		sql.append(" (  select b.*,a.xh from xszsxxb a left join xg_gygl_qsfpb  b "); 
		sql.append(" on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh )a ");
		sql.append(" left join view_xsjbxx c on a.xh=c.xh  where  a.bmdm<>c.bjdm   )a group by a.bjdm ");
		sql.append(" )c on a.bjdm=c.bjdm  ");
		
		sql.append("  where 1=1   ");
		return CommonQueryDAO.commonQuery(sql.toString(),"",inputV,colList, myForm);
	} 
	
	
	public List<String[]>getSsydList(GyglZsjgForm myForm, User user,
			String[] colList, String query, String[] inPutList) throws Exception{
		
		GyglZsjgService service=new GyglZsjgService();
		StringBuilder sql=new StringBuilder();
		StringBuilder caseSql=new StringBuilder();
		StringBuilder tjSql=new StringBuilder();
		StringBuilder xszd=new StringBuilder();
		StringBuilder tjzd=new StringBuilder();
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
	
		List<String>ssydxn=service.getYdxnList(myForm);
		
	       
	    sql.append("  select a.*,rownum r from ( ");
	    
	    if("xy".equalsIgnoreCase(myForm.getFpdx())){
			
	    	sql.append(" select a.xydm pkValue,a.xymc,a.xydm ");
		
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(" select a.xydm||'!!@@!!'||a.nj pkValue,a.xymc,a.xydm,a.nj ");;
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(" select a.zydm||'!!@@!!'||a.nj pkValue,a.xymc,a.xydm,a.zydm,a.zymc,a.nj ");
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(" select a.bjdm pkValue,a.xymc,a.xydm,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj ");
		}
	    
		
		ArrayList<String>output=new ArrayList<String>();
		
		output.add("pkValue");
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			output.add("xymc");
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			output.add("nj");
			output.add("xymc");
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			output.add("nj");
			output.add("xymc");
			output.add("zymc");
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			output.add("nj");
			output.add("xymc");
			output.add("zymc");
			output.add("bjmc");
		}
		
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			caseSql.append(" select xydm   ");
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			caseSql.append(" select xydm,nj   ");
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			caseSql.append(" select zydm,nj   ");
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			caseSql.append(" select bjdm   ");
		}
		
		for(int i=0;i<ssydxn.size();i++){
			
				String xn=ssydxn.get(i).replace("-", "_");
				caseSql.append(" ,case when xn='"+ssydxn.get(i)+"'  then rs end xn_"+xn+"_rs ");
				caseSql.append(" ,case when xn='"+ssydxn.get(i)+"'  then rc end xn_"+xn+"_rc ");
				
				xszd.append(",sum(xn_"+xn+"_rs)xn_"+xn+"_rs,sum(xn_"+xn+"_rc)xn_"+xn+"_rc");
				tjzd.append(",nvl(xn_"+xn+"_rs,0)xn_"+xn+"_rs,nvl(xn_"+xn+"_rc,0)xn_"+xn+"_rc");
				output.add("xn_"+xn+"_rs");
				output.add("xn_"+xn+"_rc");
		}
		sql.append(tjzd);
		
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(" from(select distinct(xydm)xydm,xymc from  view_njxyzybj) a left join  ( ");
			sql.append(" select xydm ");
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(" from(select distinct(xydm||nj)njxy,nj,xydm,xymc from  view_njxyzybj) a left join  ( ");
			sql.append(" select nj,xydm ");
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(" from(select distinct(nj||zydm)njzy,nj,zydm,zymc,xydm,xymc from  view_njxyzybj) a left join  ( ");
			sql.append(" select nj,zydm ");
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(" from(select distinct(bjdm)bj,nj,zydm,zymc,xydm,xymc,bjdm,bjmc from  view_njxyzybj) a left join  ( ");
			sql.append(" select bjdm ");
		}
	
		
		sql.append(xszd);
		sql.append(" from (");
		
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" select a.xydm,rs,rc,a.xn from ( ");
			tjSql.append(" select xydm,count(1)rc,xn from( ");
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" select a.xydm,a.nj,rs,rc,a.xn from ( ");
			tjSql.append(" select xydm,nj,count(1)rc,xn from( ");
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" select a.zydm,a.nj,rs,rc,a.xn from ( ");
			tjSql.append(" select zydm,nj,count(1)rc,xn from( ");
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" select a.bjdm,rs,rc,a.xn from ( ");
			tjSql.append(" select bjdm,count(1)rc,xn from( ");
		}
		
		
	
		tjSql.append(" select a.xn,b.xh,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj ");
		tjSql.append(" from ssydxxb a , view_xsjbxx b  where a.xh=b.xh ");
		
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" ) group by xydm,xn)a left join ");
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" ) group by xydm,nj,xn)a left join ");
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" ) group by zydm,nj,xn)a left join ");
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" ) group by bjdm,xn)a left join ");
		}
		
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" (select xydm,count(1)rs,xn from ( ");
			tjSql.append(" select xydm,xh,count(1)c,xn from( ");
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" (select xydm,nj,count(1)rs,xn from ( ");
			tjSql.append(" select xydm,nj,xh,count(1)c,xn from( ");
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" (select zydm,nj,count(1)rs,xn from ( ");
			tjSql.append(" select zydm,nj,xh,count(1)c,xn from( ");
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" (select bjdm,count(1)rs,xn from ( ");
			tjSql.append(" select bjdm,xh,count(1)c,xn from( ");
		}
		
		tjSql.append(" select a.xn,b.xh,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,");
		tjSql.append(" b.nj ");
		tjSql.append(" from ssydxxb a , view_xsjbxx b  where a.xh=b.xh ");
		
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" ) group by xydm,xh,xn)group by xydm,xn)b on a.xydm=b.xydm and a.xn=b.xn");
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" ) group by xydm,nj,xh,xn)group by xydm,nj,xn)b on a.xydm=b.xydm and a.nj=b.nj and a.xn=b.xn");
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" ) group by zydm,nj,xh,xn)group by zydm,nj,xn)b on a.zydm=b.zydm and a.nj=b.nj and a.xn=b.xn");
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			tjSql.append(" ) group by bjdm,xh,xn)group by bjdm,xn)b on a.bjdm=b.bjdm and a.xn=b.xn");
		}
		
		
		tjSql.append("  where 1=1   ");
		sql.append(caseSql);
		sql.append(" from(   ");
		sql.append(tjSql);
		
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(")) group by xydm)b on a.xydm=b.xydm) a ");
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(")) group by xydm,nj)b on a.xydm=b.xydm and a.nj=b.nj) a ");
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(")) group by zydm,nj)b on a.zydm=b.zydm and a.nj=b.nj) a ");
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(")) group by bjdm)b on a.bjdm=b.bjdm) a ");
		}
		sql.append(" where 1=1 ");
		return CommonQueryDAO.commonQuery(sql.toString(),query,inputV,output.toArray(new String[]{}), myForm);
	} 
	
	/**
	 * 获取宿舍异动信息
	 * @param myForm
	 * @param user
	 * @param colList
	 * @param query
	 * @param inPutList
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getSsydcxList(GyglZsjgForm myForm, User user,
			String[] colList, String query, String[] inPutList) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("  select  rownum r,a.*,(select ldmc from sslddmb b where a.ydqlddm=b.lddm)ydqldmc, ");
		sql.append(" (select ldmc from sslddmb b where a.ydhlddm=b.lddm)ydhldmc  ");
		sql.append(" from ( select b.xh,b.xm,b.xb,b.xydm,b.xymc,b.zydm,b.zymc, ");
		sql.append(" bjdm,b.bjmc,b.nj,a.ydqlddm,a.ydqcs,a.ydqqsh,a.ydhlddm,a.ydhcs, ");
		sql.append(" a.ydhqsh from ssydxxb a,view_xsjbxx b where a.xh=b.xh)a where 1=1 ");
		return  CommonQueryDAO.commonQuery(sql.toString(),query,inPutList,colList, myForm);
	}
	
	/**
	 * 保存床位异动信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean insertYdxx(GyglZsjgForm myForm) throws Exception{
			
		int len = myForm.getYdxsxhArr().length;
		String[] xhArr=myForm.getXhArr();
		String[] ydxsxhArr=myForm.getYdxsxhArr();
		String[] sql=new String[len];
		String nowTime=GetTime.getNowTime2();
		for(int i=0;i<len;i++){
			for(int j=0;j<xhArr.length;j++){
				if(xhArr[j].equalsIgnoreCase(ydxsxhArr[i])){
					sql[i]=" insert into ssydxxb (xh,xn,xq,ydsj,ydqrzsj,ydhrzsj,ydqlddm,ydhlddm, ";
					sql[i]+=" ydqcs,ydhcs,ydqqsh,ydhqsh,ydqcwh,ydhcwh) ";
					sql[i]+=" select xh,'"+Base.currXn+"','"+Base.currXq+"','"+nowTime+"',rzrq,'"+nowTime+"',";
					sql[i]+="lddm,'"+myForm.getLddmArr()[j]+"',cs,'"+myForm.getCsArr()[j]+"',qsh,'"+myForm.getQshArr()[j]+"',cwh,'"+myForm.getCwhArr()[j]+"' ";
					sql[i]+=" from xszsxxb where xh='"+myForm.getYdxsxhArr()[i]+"' ";
					break;
				}
			}
		}
		return saveArrDate(sql);
	} 
	
	/**
	 * 获取空闲寝室统计信息
	 * 
	 * @param myForm
	 * @param colList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKxqsList(GyglZsjgForm myForm, String[] colList,
			HttpServletRequest request, String lx, User user) throws Exception {

		SearchService searchService = new SearchService();
		GyglGywhForm gywhForm=new GyglGywhForm();
		GyglGywhDAO gywhDAO=new GyglGywhDAO();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		// 权限控制
		String searchTjByUser = searchService.getSearchTjByUser("a", user);

//		searchTj += searchTjByUser;	
		
		StringBuilder sql=new StringBuilder();
		
		//角色权限判断
	    gywhForm.setUser(user);
		gywhForm.setFpdx(myForm.getFpdx());
		sql.append(" select rownum r,a.* from(select b.* from(" );
		
		sql.append(" select distinct(lddm)lddm from( ");
		sql.append(gywhDAO.userStatusSql(gywhForm));
		sql.append(" ))a  ");
		sql.append(" left join (");
		
		sql.append("  select  b.* from ( ");
		sql.append(" select a.xqdm,a.xqmc,a.yqdm,a.yqmc,a.lddm,a.ldmc,nvl(b.kqs,0)kqs,nvl(b.xqs,0)xqs,a.xbxd from xg_view_gygl_sslddm a left join( ");
		sql.append("  select lddm,sum(nvl(kqs,0))kqs,sum(nvl(xqs,0))xqs from ( ");
		sql.append("  select lddm,case when rzzt='空的' then qss end kqs,case when rzzt='闲的' then qss end xqs from( ");
		sql.append("  select lddm, rzzt,count(1)qss  from (select lddm,cs,qsh,cws, ");
		sql.append("  case when to_number(cws) = rzrs then '满的' ");
		sql.append("  when rzrs = 0 then  '空的' ");
		sql.append("  when to_number(cws) > rzrs then '闲的' end rzzt ");
		sql.append("  from (select lddm, cs, qsh, cws, rzrs ");
		sql.append("  from (select f.xqdm,f.yqdm,a.lddm, a.cs, a.qsh, a.cws, b.cwh,  nvl(c.rzrs, 0) rzrs from ssxxb a ");
		sql.append("  left join xg_gygl_cwxxb b on a.lddm = b.lddm and a.cs = b.cs  and a.qsh = b.qsh ");
		sql.append("  left join (select lddm, cs, qsh, count(1) rzrs  from xszsxxb  group by lddm, cs, qsh)c ");
		sql.append("  on a.lddm = c.lddm and a.cs = c.cs and a.qsh =  c.qsh ");
		sql.append("  left join xg_gygl_qtcwxxb e on a.lddm=e.lddm and a.cs=e.cs and a.qsh=e.qsh  ");
		sql.append(" left join xg_view_gygl_sslddm  f on a.lddm=f.lddm  ");
		sql.append(" ) ");
		sql.append(" group by lddm, cs, qsh, cws, rzrs)) ");
		sql.append("  group by lddm, rzzt))group by lddm ");
		sql.append(" )  b on a.lddm=b.lddm )b)b   ");
		
		sql.append("  on a.lddm=b.lddm )a where 1=1 ");
		sql.append(searchTj);
		sql.append(" order by lddm ");
	
		if(!lx.equalsIgnoreCase("dc")){
			return CommonQueryDAO.commonQuery(sql.toString(), "", inputV,colList, myForm);
		}else {
			return CommonQueryDAO.commonQueryNotFy("", "", inputV, colList, sql.toString(), myForm);
		}
	}
	
	/**
	 * 获取空闲寝室统计信息
	 * @param myForm
	 * @param colList
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getKqsxx(GyglZsjgForm myForm,String[]colList,HttpServletRequest request) throws Exception{
		StringBuilder sql=new StringBuilder();
		String fpdx=myForm.getFpdx();
		
		sql.append(" select * from ( ");
		sql.append(" select a.lddm,a.ldmc,a.cs,a.qsh,a.rzzt,a.cws,a.xbxd,nvl(c.bmmc,'未分配')bmmc from  ");
		sql.append(" (select a.ldmc,a.lddm,a.cs,a.qsh,a.rzzt,a.cws,a.xbxd from(  ");
		sql.append("  select b.ldmc,a.cws,a.lddm,a.cs,a.qsh,b.xbxd,case when to_number(cws)=rzrs then '满的'    ");
		sql.append("  when rzrs=0 then '空的' else '闲的' end rzzt from( ");
		sql.append(" select lddm,cs,qsh,cws,rzrs from(   ");
		sql.append("  select a.lddm,a.cs,a.qsh,nvl(b.rzrs,0)rzrs,a.cws from ssxxb a left join  ");
		sql.append("  (select a.lddm,a.cs,a.qsh,count(1)rzrs from xszsxxb  a left join ssxxb b ");
		sql.append(" on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh ");
		sql.append("   group by a.lddm,a.cs,a.qsh)b on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh  ");
		sql.append("   ) )a left join sslddmb b on a.lddm=b.lddm)a)a left join  (    ");
		
		
		if("xy".equalsIgnoreCase(fpdx)){
			sql.append(" select  a.lddm,a.cs,a.qsh,b.xymc bmmc ");
			sql.append("  from xg_gygl_qsfpb a left join(select distinct(xydm),xymc from  view_njxyzybj) b on a.bmdm =b.xydm ");
		}else if("njxy".equalsIgnoreCase(fpdx)){
			sql.append(" select  a.lddm,a.cs,a.qsh,b.xymc,b.nj,b.xymc||'('||a.nj||')' bmmc ");
			sql.append("  from xg_gygl_qsfpb a left join(select distinct(xydm||nj),xydm,nj,xymc from  view_njxyzybj) ");
			sql.append("   b on a.bmdm =b.xydm and a.nj=b.nj ");
		}else if("njzy".equalsIgnoreCase(fpdx)){
			sql.append(" select  a.lddm,a.cs,a.qsh,b.zymc,b.nj,b.zymc||'('||a.nj||')' bmmc ");
			sql.append(" from xg_gygl_qsfpb a left join(select distinct(zydm||nj),zydm,nj,zymc from  view_njxyzybj) ");
			sql.append(" b on a.bmdm =b.zydm and a.nj=b.nj ");
		}else if("bj".equalsIgnoreCase(fpdx)){
			sql.append(" select  a.lddm,a.cs,a.qsh,b.bjmc bmmc ");
			sql.append(" from xg_gygl_qsfpb a left join(select distinct(bjdm),bjmc from  view_njxyzybj) b on a.bmdm =b.bjdm ");
		}
		
		sql.append("  )c on a.lddm=c.lddm and a.cs=c.cs and a.qsh=c.qsh ) where 1=1");
		if(!Base.isNull(myForm.getLddm())){
			sql.append(" and lddm= '"+myForm.getLddm()+"' ");
		}
		sql.append(" and rzzt='空的' ");
		sql.append(" order by cs,qsh ");
		
		return CommonQueryDAO.commonQueryNotFy("", "", new String[]{}, colList, sql.toString(), myForm);
	}
	
	/**
	 * 获取闲寝室统计信息
	 * @param myForm
	 * @param colList
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXqsxx(GyglZsjgForm myForm,String[]colList,HttpServletRequest request) throws Exception{
		StringBuilder sql=new StringBuilder();
		String fpdx=myForm.getFpdx();
		
		sql.append(" select * from( ");
		sql.append(" select a.*,b.cwh,nvl(c.bmmc,'未分配')bmmc from( ");
		sql.append(" select b.ldmc,nvl(b.sfbz,'未设置')sfbz,a.cws,a.lddm,a.cs,a.qsh,b.xbxd,b.xb,case when to_number(a.cws)=rzrs then '满的'   ");
		sql.append(" when rzrs=0 then '空的' else '闲的' end rzzt from(  ");
		sql.append(" select lddm,cs,qsh,cws,rzrs from(   ");
		sql.append("  select a.lddm,a.cs,a.qsh,nvl(b.rzrs,0)rzrs,a.cws from ssxxb a left join   ");
		sql.append("  (select a.lddm,a.cs,a.qsh,count(1)rzrs from xszsxxb  a left join ssxxb b   ");
		sql.append("   on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh    ");
		sql.append("   group by a.lddm,a.cs,a.qsh)b on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh    ");
		sql.append("   ) )a left join xg_view_gygl_ssxx b on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh ) a    ");
		sql.append("   left join (select * from xg_gygl_cwxxb a where not exists(select 1 from xszsxxb b    ");
		sql.append("   where a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh and a.cwh=b.cwh))b ");
		sql.append("      on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh  left join ( ");
		
		if("xy".equalsIgnoreCase(fpdx)){
			sql.append(" select  a.lddm,a.cs,a.qsh,b.xymc bmmc ");
			sql.append("  from xg_gygl_qsfpb a left join(select distinct(xydm),xymc from  view_njxyzybj) b on a.bmdm =b.xydm ");
		}else if("njxy".equalsIgnoreCase(fpdx)){
			sql.append(" select  a.lddm,a.cs,a.qsh,b.xymc,b.nj,b.xymc||'('||a.nj||')' bmmc ");
			sql.append("  from xg_gygl_qsfpb a left join(select distinct(xydm||nj),xydm,nj,xymc from  view_njxyzybj) ");
			sql.append("   b on a.bmdm =b.xydm and a.nj=b.nj ");
		}else if("njzy".equalsIgnoreCase(fpdx)){
			sql.append(" select  a.lddm,a.cs,a.qsh,b.zymc,b.nj,b.zymc||'('||a.nj||')' bmmc ");
			sql.append(" from xg_gygl_qsfpb a left join(select distinct(zydm||nj),zydm,nj,zymc from  view_njxyzybj) ");
			sql.append(" b on a.bmdm =b.zydm and a.nj=b.nj ");
		}else if("bj".equalsIgnoreCase(fpdx)){
			sql.append(" select  a.lddm,a.cs,a.qsh,b.bjmc bmmc ");
			sql.append(" from xg_gygl_qsfpb a left join(select distinct(bjdm),bjmc from  view_njxyzybj) b on a.bmdm =b.bjdm ");
		}
		
		sql.append(" )c on a.lddm=c.lddm and a.cs=c.cs and a.qsh=c.qsh ");
		sql.append(" )where 1=1 ");
		
		if(!Base.isNull(myForm.getLddm())){
			sql.append(" and lddm= '"+myForm.getLddm()+"' ");
		}
		sql.append(" and rzzt='闲的' ");
		sql.append(" order by lddm,cs,qsh,cwh ");
		return CommonQueryDAO.commonQueryNotFy("", "", new String[]{}, colList, sql.toString(), myForm);
	}
	
	/**
	 * 获取楼栋详细信息
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getLdMap(GyglZsjgForm myForm){
		
		DAO dao=DAO.getInstance();
		
		List<String>inputV=new ArrayList<String>();
		StringBuilder sql=new StringBuilder();
		
		sql.append("select lddm,ldmc,xqdm,xqmc,yqdm,yqmc,cs,xbxd from xg_view_gygl_sslddm where 1=1 ");
		if(!Base.isNull(myForm.getLddm())){
			sql.append(" and lddm=? ");
			inputV.add(myForm.getLddm());
		}
		return dao.getMap(sql.toString(), inputV.toArray(new String[]{}), new String[]{"ldmc","xqmc","yqmc","cs","xbxd"});
		
	}
	
	
	// ================以下 Made By 伟大的骆==========================

	/**
	 * 获得寝室最大床位数
	 * 
	 * @author 伟大的骆
	 */
	public String getMaxCws() {

		DAO dao = DAO.getInstance();

		String sql = "select max(to_number(cws)) maxCws from ssxxb";

		String maxCws = dao.getOneRs(sql, new String[] {}, "maxCws");

		maxCws = Base.isNull(maxCws) ? "0" : maxCws;

		return maxCws;
	}

	/**
	 * 获得寝室入住情况寝室列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getRzqsList(GyglZsjgForm model, User user,
			HttpServletRequest request) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		GyglGywhDAO gywhDAO=new GyglGywhDAO();
		GyglGywhForm gywhForm=new GyglGywhForm();
		SearchService searchService = new SearchService();

		StringBuilder sql=new StringBuilder();
		
		
		gywhForm.setUser(user);
		gywhForm.setFpdx(model.getFpdx());
		sql.append(" select rownum r,a.* from(select b.* from(" );
		
		sql.append(" select lddm,cs,qsh from( ");
		sql.append(gywhDAO.userStatusSql(gywhForm));
		sql.append(" ))a  ");
		sql.append(" left join xg_view_gygl_ssxx b");
		sql.append(" on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh)a ");
		
		
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// 权限控制
//		HashMap<String, String> notCtrlStatus = new HashMap<String, String>();
//		user.setNotCtrlStatus(notCtrlStatus);
//		String searchTjByUser = searchService.getSearchTjByUser("a", user);
//
//		searchTj += searchTjByUser;

		String[] colList = new String[] { "lddm", "ldmc", "cs", "qsh", "xb" };
		StringBuilder query = new StringBuilder();
		query.append(" where 1 = 1 ");
		query.append(searchTj);
		query.append(" and exists (select 1 from xszsxxb c ");
		query.append(" where a.lddm = c.lddm ");
		query.append(" and a.cs = c.cs ");
		query.append(" and a.qsh = c.qsh) ");
		query.append(" order by lddm,cs,qsh ");

		return CommonQueryDAO.commonQuery(sql.toString(), query.toString(),inputV, colList, model);
	}

	/**
	 * 获得寝室入住情况寝室列表(不分页)
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getRzqsListNoFy(GyglZsjgForm model, User user,
			HttpServletRequest request) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		SearchService searchService = new SearchService();

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// 权限控制
		// String searchTjByUser = searchService.getSearchTjByUser(request, "a",
		// "xydm", "bjdm");
		// searchTj += searchTjByUser;

		String tableName = "xg_view_gygl_ssxx a";
		String[] colList = new String[] { "lddm", "ldmc", "cs", "qsh", "xb" };
		StringBuilder query = new StringBuilder();
		query.append(" where 1 = 1 ");
		query.append(searchTj);
		query.append(" and exists (select 1 from xszsxxb b ");
		query.append(" where a.lddm = b.lddm ");
		query.append(" and a.cs = b.cs ");
		query.append(" and a.qsh = b.qsh) ");
		query.append(" order by lddm,cs,qsh ");

		return commonQueryNotFy(tableName, query.toString(), inputV,
				colList, "");
	}
	
	/**
	 * 获得寝室入住信息
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getQsrzInfoList(
			ArrayList<String[]> qsList) {

		StringBuilder sql = new StringBuilder();
		sql
				.append("select a.lddm, a.ldmc, a.cs, a.qsh, a.xh, a.xm, a.xb, a.cwh, a.bjdm, a.bjmc ");
		sql.append("from xg_view_gygl_xszsxx a ");
		sql.append("where 1 = 1 ");

		if (qsList != null && qsList.size() > 0) {
			sql.append("and( ");
			for (int i = 0; i < qsList.size(); i++) {
				String[] value = qsList.get(i);
				if (i != 0) {
					sql.append("or ");
				}
				sql.append(" (a.lddm =  '" + value[0] + "'");
				sql.append(" and a.cs =  '" + value[2] + "'");
				sql.append(" and a.qsh =  '" + value[3] + "')");
			}
			sql.append(")");
		}
		//辽宁机电职业技术学院 床位号存在中文，个性化修改
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "a.cwh";
		}else{
			sb = "to_number(a.cwh)";
		}
		sql.append("order by a.lddm, a.cs, a.qsh, "+sb+" ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "lddm", "ldmc", "cs", "qsh",
						"xh", "xm", "cwh", "bjmc" });

		return list;
	}

	//	 =================以上 Made By 伟大的骆==================
}
	

