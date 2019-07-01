package xgxt.gygl.cwgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.gygl.GyglCommDAO;
import xgxt.gygl.gywh.GyglGywhDAO;
import xgxt.gygl.gywh.GyglGywhForm;
import xgxt.utils.CommonQueryDAO;

public class GyglCwglDAO extends GyglCommDAO {
	

	/**
	 * 获得自动分配部门列表(学院)
	 * 
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getZdfpXyList(GyglCwglForm model, User user,
			String[] colList, String query, String[] inPutList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		StringBuilder tableSql = new StringBuilder();

		tableSql.append("(select * from (select a.xydm, a.xymc,a.bmrs,a.fpqss,a.kzrcws, ( a.kzrcws-nvl(c.yzrcws, 0)) wzrcws  ");
		tableSql.append(", nvl(c.yzrcws, 0)yzrcws  from (select a.xydm,a.xymc,nvl(b.bmrs, 0) bmrs, ");
		tableSql.append("nvl(c.fpqss, 0) fpqss,nvl(c.kzrcws, 0) kzrcws from");

		tableSql.append("(select distinct a.xydm, a.xymc from view_njxyzybj a ");
		tableSql.append(" where 1 = 1 ");
		tableSql.append(query);
		tableSql.append("order by a.xydm) a ");

		tableSql.append("left join (select xydm, count(1) bmrs from ");
		tableSql.append("view_xsjbxx b group by xydm) b on a.xydm = b.xydm ");

		tableSql.append("left join (select bmdm xydm, count(1) fpqss, nvl(sum(cws),0) kzrcws ");
		tableSql.append("from (select c.bmdm, c.lddm, c.cs, c.qsh, d.cws ");
		tableSql.append("from xg_gygl_qsfpb c left join (select lddm, cs, qsh, count(1) cws ");
		tableSql.append("from xg_gygl_cwxxb d where ");
		tableSql.append("not exists(select 1 from xg_gygl_qtcwxxb e where d.lddm = e.lddm and d.cs=e.cs and d.qsh = e.qsh)");
		tableSql.append("group by lddm, cs, qsh) d on c.lddm = d.lddm ");
		tableSql.append("and c.cs = d.cs and c.qsh = d.qsh)c group by bmdm)c  on a.xydm = c.xydm )a ");

		tableSql.append("left join (select bmdm xydm, count(1) fpqss, nvl(sum(cws), 0) yzrcws ");
		tableSql.append("from (select c.bmdm, c.lddm, c.cs, c.qsh, d.cws ");
		tableSql.append("from xg_gygl_qsfpb c left join (select count(1) cws, a.cwh, a.qsh, a.lddm, a.cs ");
		tableSql.append("from xszsxxb a  where exists (select 1 from xg_gygl_cwxxb b where a.lddm = b.lddm ");
		tableSql.append("and a.cs = b.cs  and a.qsh = b.qsh  and a.cwh = b.cwh ) group by a.lddm, a.qsh, a.cwh, a.cs ");
		tableSql.append(" ) d on c.lddm = d.lddm and c.cs = d.cs and c.qsh = d.qsh) a ");

		tableSql.append(" group by bmdm) c on a.xydm = c.xydm) a )");
		return getRsArrList(tableSql.toString(), "", inPutList, colList, "",
				model);

	}

	/**
	 * 获得自动分配部门列表(年级+学院)
	 * 
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getZdfpNjXyList(GyglCwglForm model, User user,
			String[] colList, String query, String[] inPutList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		StringBuilder tableSql = new StringBuilder();

		tableSql
				.append("(select * from (select a.nj,a.xydm, a.xymc,a.bmrs,a.fpqss,a.kzrcws,( a.kzrcws-nvl(c.yzrcws, 0)) wzrcws  ");
		tableSql
				.append(", nvl(c.yzrcws, 0)yzrcws from (select a.nj,a.xydm,a.xymc,nvl(b.bmrs, 0) bmrs, ");
		tableSql.append("nvl(c.fpqss, 0) fpqss,nvl(c.kzrcws, 0) kzrcws from");

		tableSql
				.append("(select distinct a.nj,a.xydm, a.xymc from view_njxyzybj a ");
		tableSql.append(" where 1 = 1 ");
		tableSql.append(query);
		tableSql.append("order by a.nj,a.xydm) a ");

		tableSql.append("left join (select b.nj,b.xydm, count(1) bmrs from ");
		tableSql.append("view_xsjbxx b group by b.nj,b.xydm) b ");
		tableSql.append("on a.xydm = b.xydm and a.nj = b.nj ");

		tableSql
				.append("left join (select nj,bmdm xydm, count(1) fpqss, nvl(sum(cws),0) kzrcws ");
		tableSql
				.append("from (select c.nj,c.bmdm, c.lddm, c.cs, c.qsh, d.cws ");
		tableSql
				.append("from xg_gygl_qsfpb c left join (select lddm, cs, qsh, count(1) cws ");
		tableSql.append("from xg_gygl_cwxxb d where ");
		tableSql
				.append("not exists(select 1 from xg_gygl_qtcwxxb e where d.lddm = e.lddm and d.cs=e.cs and d.qsh = e.qsh)");
		tableSql.append("group by lddm, cs, qsh) d on c.lddm = d.lddm ");
		tableSql
				.append("and c.cs = d.cs and c.qsh = d.qsh)c group by bmdm,nj)c  on a.xydm = c.xydm and a.nj=c.nj )a ");

		tableSql
				.append("left join (select nj,bmdm xydm, count(1) fpqss, nvl(sum(cws), 0) yzrcws ");
		tableSql
				.append("from (select c.nj, c.bmdm, c.lddm, c.cs, c.qsh, d.cws ");
		tableSql
				.append("from xg_gygl_qsfpb c left join (select count(1) cws, a.cwh, a.qsh, a.lddm, a.cs ");
		tableSql
				.append("from xszsxxb a  where exists (select 1 from xg_gygl_cwxxb b where a.lddm = b.lddm ");
		tableSql
				.append("and a.cs = b.cs  and a.qsh = b.qsh  and a.cwh = b.cwh ) group by a.lddm, a.qsh, a.cwh, a.cs ");
		tableSql
				.append(" ) d on c.lddm = d.lddm and c.cs = d.cs and c.qsh = d.qsh) a ");

		tableSql.append(" group by bmdm,nj) c on a.xydm = c.xydm and a.nj=c.nj) a )");
	
		return getRsArrList(tableSql.toString(), "", inPutList, colList, "",
				model);
	}

	/**
	 * 获得自动分配部门列表(年级+专业)
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getZdfpNjZyList(GyglCwglForm model, User user,
			String[] colList, String query, String[] inPutList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		StringBuilder tableSql = new StringBuilder();

		tableSql.append("(select * from(select a.nj,a.xymc,a.zydm,a.zymc,a.bmrs,a.fpqss,a.kzrcws,( a.kzrcws-nvl(c.yzrcws, 0)) wzrcws  ");
		tableSql.append(", nvl(c.yzrcws, 0)yzrcws from (select a.nj,a.xydm,a.xymc,zydm,zymc,nvl(b.bmrs, 0) bmrs, ");
		tableSql.append("nvl(c.fpqss, 0) fpqss,nvl(c.kzrcws, 0) kzrcws from");

		tableSql.append("(select distinct a.nj,a.xydm,a.xymc,a.zydm,a.zymc from view_njxyzybj a ");
		tableSql.append(" where 1 = 1 ");
		tableSql.append(query);
		tableSql.append("order by a.nj,a.xydm,a.zydm) a ");

		tableSql.append("left join (select b.nj,b.zydm, count(1) bmrs from ");
		tableSql.append("view_xsjbxx b group by b.nj,b.zydm) b ");
		tableSql.append("on a.zydm = b.zydm and a.nj = b.nj ");

		tableSql.append("left join (select nj,bmdm zydm, count(1) fpqss, nvl(sum(cws),0) kzrcws ");
		tableSql.append("from (select c.nj,c.bmdm, c.lddm, c.cs, c.qsh, d.cws ");
		tableSql.append("from xg_gygl_qsfpb c left join (select lddm, cs, qsh, count(1) cws ");
		tableSql.append("from xg_gygl_cwxxb d where ");
		tableSql.append("not exists(select 1 from xg_gygl_qtcwxxb e where d.lddm = e.lddm and d.cs=e.cs and d.qsh = e.qsh)");
		tableSql.append("group by lddm, cs, qsh) d on c.lddm = d.lddm ");
		tableSql.append("and c.cs = d.cs and c.qsh = d.qsh)c group by bmdm,nj)c  on a.zydm = c.zydm and a.nj=c.nj )a ");

		tableSql.append("left join (select nj,bmdm zydm, count(1) fpqss, nvl(sum(cws), 0) yzrcws ");
		tableSql.append("from (select c.nj, c.bmdm, c.lddm, c.cs, c.qsh, d.cws ");
		tableSql.append("from xg_gygl_qsfpb c left join (select count(1) cws, a.cwh, a.qsh, a.lddm, a.cs ");
		tableSql.append("from xszsxxb a  where exists (select 1 from xg_gygl_cwxxb b where a.lddm = b.lddm ");
		tableSql.append("and a.cs = b.cs  and a.qsh = b.qsh  and a.cwh = b.cwh ) group by a.lddm, a.qsh, a.cwh, a.cs ");
		tableSql.append(" ) d on c.lddm = d.lddm and c.cs = d.cs and c.qsh = d.qsh) a ");

		tableSql.append(" group by bmdm,nj) c on a.zydm = c.zydm) a )");
		return getRsArrList(tableSql.toString(), "", inPutList, colList, "",
				model);

	}

	/**
	 * 获得自动分配部门列表(班级)
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getZdfpBjList(GyglCwglForm model, User user,
			String[] colList, String query, String[] inPutList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		StringBuilder tableSql = new StringBuilder();

		tableSql.append("(select * from(select a.nj,a.xydm,a.xymc,a.zydm,a.zymc, ");
		tableSql.append("a.bjdm,a.bjmc,a.bmrs,a.fpqss,a.kzrcws,( a.kzrcws-nvl(c.yzrcws, 0)) wzrcws, ");
		tableSql.append("nvl(c.yzrcws, 0)yzrcws from (select a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,nvl(b.bmrs, 0) bmrs, ");
		tableSql.append("nvl(c.fpqss, 0) fpqss,nvl(c.kzrcws, 0) kzrcws from");

		tableSql.append("(select a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc from view_njxyzybj a ");
		tableSql.append(" where 1 = 1 ");
		tableSql.append(query);
		tableSql.append("order by a.nj,a.xydm,a.zydm,a.bjdm) a ");

		tableSql.append("left join (select bjdm, count(1) bmrs from ");
		tableSql.append("view_xsjbxx b group by bjdm) b on a.bjdm = b.bjdm ");

		tableSql.append("left join (select bmdm bjdm, count(1) fpqss, nvl(sum(cws),0) kzrcws ");
		tableSql.append("from (select c.bmdm, c.lddm, c.cs, c.qsh, d.cws ");
		tableSql.append("from xg_gygl_qsfpb c left join (select lddm, cs, qsh, count(1) cws ");
		tableSql.append("from xg_gygl_cwxxb d where ");
		tableSql.append("not exists(select 1 from xg_gygl_qtcwxxb e where d.lddm = e.lddm and d.cs=e.cs and d.qsh = e.qsh)");
		tableSql.append("group by lddm, cs, qsh) d on c.lddm = d.lddm ");
		tableSql.append("and c.cs = d.cs and c.qsh = d.qsh)c group by bmdm)c  on a.bjdm = c.bjdm )a ");

		tableSql.append("left join (select bmdm bjdm, count(1) fpqss, nvl(sum(cws), 0) yzrcws ");
		tableSql.append("from (select c.bmdm, c.lddm, c.cs, c.qsh, d.cws ");
		tableSql.append("from xg_gygl_qsfpb c left join (select count(1) cws, a.cwh, a.qsh, a.lddm, a.cs ");
		tableSql.append("from xszsxxb a  where exists (select 1 from xg_gygl_cwxxb b where a.lddm = b.lddm ");
		tableSql.append("and a.cs = b.cs  and a.qsh = b.qsh  and a.cwh = b.cwh ) group by a.lddm, a.qsh, a.cwh, a.cs ");
		tableSql.append(" ) d on c.lddm = d.lddm and c.cs = d.cs and c.qsh = d.qsh) a ");

		tableSql.append(" group by bmdm) c on a.bjdm = c.bjdm) a )");
		return getRsArrList(tableSql.toString(), "", inPutList, colList, "",
				model);

	}

	/**
	 * 获取未分配床
	 * 
	 * @param model
	 * @param user
	 * @param colList
	 * @param query
	 * @param inPutList
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWfpcwList(GyglCwglForm myForm,User user)
			throws Exception {
		DAO dao = DAO.getInstance();
		SearchService searchService=new SearchService();
		SearchModel searchModel=myForm.getSearchModel();
		String fpdx=myForm.getFpdx();
		String searchTjByUser = searchService.getSearchTjByUser("b", user);	
		
		StringBuilder sql = new StringBuilder();
		String query = myForm.getQuery();
		ArrayList<String>colList=(ArrayList<String>) myForm.getColList();
		
		String bmdm="";
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			bmdm="xydm";
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			bmdm="xydm";
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			bmdm="zydm";
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			bmdm="bjdm";
		}
		sql.append(" select * from ( ");
		sql.append("select b.lddm,b.cs,b.qsh,a.fpdx,a.cwh,b.xb,b.kfhz,bmdm,bmdm "+bmdm+",nj from(");
		sql.append(" select a.lddm, a.cs, a.qsh, a.fpdx, b.cwh,bmdm,nj ");
		sql.append("from (select * from xg_gygl_qsfpb where fpdx = '"+myForm.getFpdx()+"') a, ");
		sql.append(" (select *  from xg_gygl_cwxxb a  where not exists (select 1 ");
		sql.append("  from xszsxxb b  where a.cwh = b.cwh and a.lddm = b.lddm and a.cs = b.cs ");
		sql.append("   and a.qsh = b.qsh)");
		sql.append(") b  where a.lddm = b.lddm ");
		sql.append("  and a.cs = b.cs and a.qsh = b.qsh and b.cwbj = '可分配'");
		sql.append(query);
		sql.append(")a left join ssxxb b on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh");
		sql.append(" )a where 1=1 ");
		sql.append("  and exists (select 1 from view_njxyzybj b where 1=1 "+searchTjByUser+" and a."+bmdm+"=b."+bmdm+") ");
		//辽宁机电职业技术学院 床位号存在中文，个性化修改
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "cwh";
		}else{
			sb = "to_number(cwh)";
		}
		sql.append(" order by lddm,cs,qsh,"+sb+" ");
		String[] input = colList.toArray(new String[]{});
		String[] outPut = { "lddm", "cs", "qsh","fpdx", "cwh","xb","kfhz" ,"bmdm","nj"};
	
		return dao.getList(sql.toString(), input, outPut);
		
	}
	
	/**
	 * 获取部门为分配床位学生
	 * 
	 * @param model
	 * @param user
	 * @param colList
	 * @param query
	 * @param inPutList
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBmWfpxsList(GyglCwglForm myForm)
			throws Exception {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		ArrayList<String>colList=(ArrayList<String>) myForm.getColList();
		String query = myForm.getQuery();
		sql.append(" select xh,nj,xydm,zydm,bjdm,xb from view_xsjbxx a where not exists(select 1 from xszsxxb b where a.xh=b.xh) ");
		sql.append(query);
		sql.append(" order by xydm,zydm,bjdm,nj");
		String[] input =colList.toArray(new String[]{});
		String[] outPut = { "xh", "nj", "xydm", "zydm", "bjdm","xb" };
	
		return dao.getList(sql.toString(), input, outPut);
	}
	
	/**
	 * 获取手动分配对象(寝室信息)
	 * 
	 * @param model
	 * @param user
	 * @param colList
	 * @param query
	 * @param inPutList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFpcwList(GyglCwglForm myForm, User user,
			String[] colList,  String[] inPutList,HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		GyglGywhDAO gywhDAO=new GyglGywhDAO();
		GyglGywhForm gywhForm=new GyglGywhForm();
		
		gywhForm.setFpdx(myForm.getFpdx());
		gywhForm.setUser(user);
		
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		StringBuilder query=new StringBuilder();
		StringBuilder sql = new StringBuilder();
		
		query.append(searchTj);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
		
		String xszd="";
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			xszd=" a.xydm,a.xymc, ";
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			xszd=" a.xydm,a.xymc,a.nj, ";
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			xszd=" a.zydm,a.zymc,a.xydm,a.xymc,a.nj, ";
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			xszd=" a.bjdm,a.bjmc,a.zydm,a.zymc,a.xydm,a.xymc,a.nj, ";
		}
		
		sql.append(" select rownum r,b.* from  ");
		
		sql.append(" (select a.* from (");
		sql.append(" select lddm||'-'||cs||'-'||qsh||'-'||cwh pkValue,");
		sql.append(" (case when xh is not null then '' else 'disabled' end)disabled, ");
		sql.append(" xh,lddm,cs,qsh,cwh,ldmc,xqdm,yqdm,xb, ");
		sql.append(xszd);
		sql.append(" (case when xh is null then '未分配' else  xm end)rzxs,");
		sql.append(" (case when xh is null then '可分配' else  '已入住' end)cwfp,");
		sql.append(" (case when qsfpdx is null then '未分配' else qsfpdx end)qsfpdx from ");
		sql.append(" (select a.cwh,a.xh,a.xm,b.*,ldmc,xqdm,yqdm,xb, ");
		sql.append(getFpdx(myForm)); 
		sql.append(" qsfpdx from ");
		sql.append(" (select * from ( select a.cs,a.qsh,a.cwh,d.xh,d.xm,c.xqdm,c.yqdm,c.ldmc,a.lddm,c.xb,b.cwh qtcwh " +
				" from xg_gygl_cwxxb a " +
				" left join xg_gygl_qtcwxxb b on a.lddm = b.lddm and a.cs = b.cs and a.qsh = b.qsh and a.cwh = a.cwh" +
				" left join xg_view_gygl_ssxx c on a.lddm = c.lddm and a.cs = c.cs and a.qsh = c.qsh " +
				" left join xg_view_gygl_xszsxx d on a.lddm = d.lddm and a.cs = d.cs and a.qsh = d.qsh and a.cwh = b.cwh " +
				" ) where qtcwh is null )a left join (");
		sql.append(getBmxxByFpdx(myForm));
		sql.append(" )b ");
		sql.append(" on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh where fpdx is not null )a  " );
		sql.append(")a where 1=1 ");
		sql.append(query);
		sql.append(" )b left join ( ");
		sql.append(gywhDAO.userStatusSql(gywhForm));
		sql.append(" )a  ");
		
		sql.append("  on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh ");
		//辽宁机电职业技术学院 床位号存在中文，个性化修改
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "b.cwh";
		}else{
			sb = "to_number(b.cwh)";
		}
		sql.append(" order by b.lddm,b.cs,b.qsh,"+sb+" ");
	
		return CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, myForm);
	}
	
	/**
	 * 获取未分配学生列表
	 * 
	 * @param model
	 * @param user
	 * @param colList
	 * @param query
	 * @param inPutList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWzsxsList(GyglCwglForm myForm, User user,
			String query,String[] colList,  String[] inPutList,HttpServletRequest request)
			throws Exception{
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		StringBuilder queryB=new StringBuilder();
		StringBuilder sql = new StringBuilder();
		
		queryB.append(searchTj);
		queryB.append(query);
		
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
		
		sql.append(" select rownum r,a.xh pkValue,a.xh,a.xm,a.xb,a.nj,a.bjdm,a.bjmc,a.xydm,a.xymc,a.zydm,a.zymc from view_xsjbxx");
		sql.append(" a where not exists(select 1 from xszsxxb b where a.xh=b.xh) ");
	
		return CommonQueryDAO.commonQuery(sql.toString(), queryB.toString(), inputV, colList, myForm);
	}
	
	/**
	 * 获取床位详细信息
	 * @param myForm
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String>getCwxxMap(GyglCwglForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.ldmc,a.xqdm,a.xqmc,a.yqdm,a.yqmc,b.lddm,b.qsh,b.cs,b.cwh,a.xb, ");
		sql.append(" a.fpdx,a.nj,a.bmdm,a.xbxd,a.kfhz from xg_view_gygl_ssxx a left join xg_gygl_cwxxb b  ");
		sql.append(" on a.lddm = b.lddm  and a.cs = b.cs  and a.qsh = b.qsh ");
		sql.append(" where a.lddm || '-' || a.cs || '-' || a.qsh || '-' || b.cwh = ? ");
		String[]outPut={"lddm","ldmc","cs","qsh","cwh","xqdm","xqmc","yqdm","yqmc","fpdx",
				"nj","bmdm","xb","fpdx","nj","bmdm","xbxd","kfhz"};
		return dao.getMap(sql.toString(), new String[]{myForm.getPkValue()}, outPut);
	}
	
	
	public StringBuilder getBmxxByFpdx(GyglCwglForm myForm){
		
		StringBuilder sql=new StringBuilder();
		
		//分配对象为学院
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(getTjxxByXy());
		//分配对象为年级加学院
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(getTjxxByNjXy());
		//分配对象为年级加专业
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(getTjxxByNjZy());
		//分配对象为班级
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(getTjxxByBj());
		}
		
		return sql;
	}
	
	/**
	 * 获取统计信息（手动分配 对象：学院）
	 * @return
	 */
	public StringBuilder getTjxxByXy(){
		StringBuilder xyTjxx=new StringBuilder();
		xyTjxx.append(" select lddm,cs,qsh,fpdx,b.* ");
		xyTjxx.append(" from (select lddm,cs,qsh,fpdx,nj,bmdm from xg_gygl_qsfpb a ");
		xyTjxx.append(" where fpdx =(select fpdx from xg_gygl_jbszb where rownum = 1))a ");
		xyTjxx.append(" left join ( select distinct(xydm)xydm,xymc from view_njxyzybj) b on a.bmdm =  b.xydm ");
		return xyTjxx;
	}
	
	/**
	 * 获取统计信息（手动分配 对象：年级学院）
	 * @return
	 */
	public StringBuilder getTjxxByNjXy(){
		StringBuilder njxyTjxx=new StringBuilder();
		njxyTjxx.append(" select lddm,cs,qsh,fpdx,b.* ");
		njxyTjxx.append(" from (select lddm,cs,qsh,fpdx,nj,bmdm from xg_gygl_qsfpb a ");
		njxyTjxx.append(" where fpdx =(select fpdx from xg_gygl_jbszb where rownum = 1))a ");
		njxyTjxx.append(" left join ( select distinct(xydm||nj)njxy,nj,xydm,xymc from view_njxyzybj) b on a.bmdm =  b.xydm and a.nj=b.nj");
		return njxyTjxx;
	}
	
	/**
	 * 获取统计信息（手动分配 对象：年级专业）
	 * @return
	 */
	public StringBuilder getTjxxByNjZy(){
		StringBuilder njzyTjxx=new StringBuilder();
		njzyTjxx.append(" select lddm,cs,qsh,fpdx,b.* ");
		njzyTjxx.append(" from (select lddm,cs,qsh,fpdx,nj,bmdm from xg_gygl_qsfpb a ");
		njzyTjxx.append(" where fpdx =(select fpdx from xg_gygl_jbszb where rownum = 1))a ");
		njzyTjxx.append(" left join ( select distinct(zydm||nj)njzy,nj,xydm,xymc,zydm,zymc from view_njxyzybj) b on a.bmdm =  b.zydm and a.nj=b.nj");
		return njzyTjxx;
	}
	
	/**
	 * 获取统计信息（手动分配 对象：年级专业）
	 * @return
	 */
	public StringBuilder getTjxxByBj(){
		StringBuilder bjTjxx=new StringBuilder();
		bjTjxx.append(" select lddm,cs,qsh,fpdx,b.* ");
		bjTjxx.append(" from (select lddm,cs,qsh,fpdx,nj,bmdm from xg_gygl_qsfpb a ");
		bjTjxx.append(" where fpdx =(select fpdx from xg_gygl_jbszb where rownum = 1))a ");
		bjTjxx.append(" left join view_njxyzybj b on a.bmdm =  b.bjdm ");
		return bjTjxx;
	}
	
	public StringBuilder getFpdx(GyglCwglForm myForm){
		
		StringBuilder sql=new StringBuilder();
		
		//分配对象为学院
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(" xymc ");
		//分配对象为年级加学院
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(" nj||xymc ");
		//分配对象为年级加专业
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(" nj||zymc ");
		//分配对象为班级
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			sql.append(" bjmc ");
		}
		
		return sql;
	}
	
	/**
	 * 分配床位给学生
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getFpcwXs(GyglCwglForm myForm) {

		DAO dao = DAO.getInstance();
		String[] pkValue = myForm.getPkvArr();
		StringBuilder sql = new StringBuilder();

		sql.append(" select xh,xm,bjdm,bjmc,xb,xydm,xymc,zydm,zymc,nj from view_xsjbxx where 1=1 ");
		if(pkValue!=null && pkValue.length>0){
			for (int i = 0; i < pkValue.length; i++) {
				if (i == 0) {
					sql.append(" and ( xh=? ");
				} else {
					sql.append(" or xh=? ");
				}
			}
		}else{
			return null;
		}
		sql.append(") order by xb ");
		String[] output = { "xh", "xm", "bjdm", "bjmc", "xb","xydm","xymc","zydm","zymc","nj" };
		return dao.getList(sql.toString(), pkValue, output);
	}
	
	/**
	 * 获取楼栋详细信息
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getLdxxxx(GyglCwglForm myForm){
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select lddm,ldmc,xqdm,xqmc,yqdm,yqmc,cs,xbxd from xg_view_gygl_sslddm a where exists(select 1 from ssxxb b where a.lddm=b.lddm) ");
		sql.append(" and exists (select 1 from xg_gygl_qsfpb b where a.lddm=b.lddm)");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"lddm","ldmc","xqdm","xqmc","yqdm","yqmc","cs","xbxd"});
	}
	
	/**
	 * 获取楼栋楼层信息
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getLdlcxx(GyglCwglForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select * from(select a.lddm,a.cs,count(1)qss from ssxxb a where not exists (select 1 ");
		sql.append(" from xszsxxb c where a.lddm = c.lddm ");
		sql.append("  and a.cs = c.cs and a.qsh = c.qsh) ");
		sql.append(" and exists (select 1  from xg_gygl_qsfpb c ");
		sql.append("   where a.lddm = c.lddm  and a.cs = c.cs ");
		sql.append("  and a.qsh = c.qsh)group by lddm,cs ) ");
		sql.append(" where 1=1 ");
		if(!Base.isNull(myForm.getLddm())){
			sql.append(" and lddm='"+myForm.getLddm()+"' ");
		}
		sql.append(" order by lddm,cs ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"lddm","cs","qss"});
	}
	
	/**
	 * 获取楼栋楼层信息(寝室分配)
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getLdlcByQsfp(GyglCwglForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select lddm,cs from ssxxb where 1=1 ");
		if(!Base.isNull(myForm.getLddm())){
			sql.append(" and lddm='"+myForm.getLddm()+"' ");
		}
		sql.append(" group by lddm,cs order by lddm,cs  ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"lddm","cs"});
	}
	
	/**
	 * 寝室详细信息
	 * @param myForm
	 * @return List<HashMap<String,String>>getQsxx
	 */
	public List<HashMap<String,String>>getQsxx(GyglCwglForm myForm){
		
		DAO dao =DAO.getInstance();
		ArrayList<String> inputList=new ArrayList<String>();
		StringBuilder sql= new StringBuilder();
		StringBuilder query=new StringBuilder();
		
		if(Base.isNull(myForm.getLddm())){
			query.append(" and lddm=? ");
	        inputList.add(myForm.getLddm());
	    }
	        
	    if(Base.isNull(myForm.getLddm())){
	    	query.append(" and cs=? ");
	        inputList.add(myForm.getLddm());
	    }
		
		sql.append(" select lddm,cs,qsh,cwh,cws,xh,zrs,xymc,xydm,zydm,zymc,bjdm,bjmc,nj, ");
		sql.append(" (case when xh is null then  '未分配' else xm end) xm, xb, kfhz ");
		sql.append(" from (select a.lddm, a.cs,a.qsh,a.cws,a.zrs,b.xh,b.xm,b.nj, b.xymc,b.xydm, ");
		sql.append(" b.zymc,b.zydm,b.bjmc,b.bjdm,a.xb,a.kfhz,a.cwh ");
		sql.append(" from (select b.lddm, b.cs, b.qsh, b.zrs, b.cws, b.xb, b.kfhz, a.cwh ");
		sql.append(" from (select * from xg_gygl_cwxxb where 1=1");
		sql.append( query );
		sql.append(" ) a ");
		sql.append(" left join (select b.lddm,b.cs,b.qsh,count(1) zrs,a.cws,a.xb, a.kfhz ");
		sql.append(" from (select a.cws,a.ssbh,a.lddm,a.cs,a.qsh,a.xb,a.kfhz ");
		sql.append(" from ssxxb a  where 1=1  ");
		sql.append( query );
		sql.append(" ) a left join xg_gygl_cwxxb b on a.lddm = b.lddm ");
		sql.append("  and a.cs = b.cs  and a.qsh = b.qsh ");
		sql.append("  group by b.lddm,b.cs,b.qsh,a.xb,a.kfhz,a.cws) b on a.lddm = b.lddm");
		sql.append(" and a.cs = b.cs and a.qsh = b.qsh) a ");
		sql.append(" left join xg_view_gygl_xszsxx b on a.lddm = b.lddm  ");
		sql.append("  and a.cs = b.cs  and a.qsh = b.qsh and a.cwh = b.cwh) a  ");
		//辽宁机电职业技术学院 床位号存在中文，个性化修改
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "cwh";
		}else{
			sb = "to_number(cwh)";
		}
		sql.append(" order by lddm, cs, qsh, "+sb+"  ");

		String[]outPut={"lddm","cs","qsh","cwh","cws","xh","zrs","xm","xb","kfhz","xydm","xymc","zydm","zymc","bjdm","bjmc","nj"};
        return dao.getList(sql.toString(), inputList.toArray(new String[]{}), outPut);
	}
	
	/**
	 * 寝室详细信息
	 * @param myForm
	 * @return List<HashMap<String,String>>getCwxxxx
	 */
	public List<HashMap<String,String>>getCwxxxx(GyglCwglForm myForm,GyglGywhForm model){
		
		DAO dao =DAO.getInstance();
		GyglGywhDAO gywhDAO=new GyglGywhDAO();
		
		ArrayList<String> inputList=new ArrayList<String>();
		StringBuilder sql= new StringBuilder();
		StringBuilder query=new StringBuilder();
		
		if(!Base.isNull(myForm.getLddm())){
			query.append(" and lddm=? ");
	        inputList.add(myForm.getLddm());
	    }
	        
	    if(!Base.isNull(myForm.getLddm())){
	    	query.append(" and cs=? ");
	        inputList.add(myForm.getCs());
	    }
		
	    inputList.addAll(inputList);
	    
	    sql.append(" select a.* from ( ");
	   
	  
	    sql.append(" select a.lddm,a.cs, a.qsh,a.cwh,a.xh,a.kfhz,a.xb,b.xydm, b.xymc, ");
	    sql.append(" a.xm,b.zydm,b.zymc, b.bjdm, b.bjmc,b.nj,'' zrs,a.cws from ( ");
		sql.append(" select a.lddm, a.cs,a.qsh,a.cwh,a.xh,a.kfhz,a.xb,b.bjdm,''zrs,a.cws, ");
		sql.append("  case when cwbj is not null then cwbj else ");
		sql.append(" case when xm is null then '未分配' else b.xm end end xm");
		sql.append(" from (select a.lddm, a.cs, a.qsh,a.kfhz,a.xb, a.cwh,a.cws, b.xh,cwbj ");
		sql.append(" from (select a.lddm, a.cs, a.qsh,a.kfhz,a.xb, a.cws, b.cwh,c.cwbj ");
		sql.append(" from (select lddm,cs,qsh,kfhz,cws,xb from ssxxb where 1=1 and fpbj='一般'");
		sql.append(query);
		sql.append(" ) a  ");
		sql.append("  left join xg_gygl_cwxxb b on a.lddm = b.lddm ");
		sql.append(" and a.cs = b.cs and a.qsh = b.qsh ");
		sql.append("  left join xg_gygl_qtcwxxb c on c.lddm=b.lddm and c.cs = b.cs and c.qsh = b.qsh  and b.cwh=c.cwh ) a ");
		sql.append(" left join (select xh,lddm,qsh,cs,cwh from xszsxxb where 1 = 1 ");
		sql.append(query);
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<myForm.getYdxh().length;i++){
			if(i==0){
				sb.append(" and ( ");
			}else{
				sb.append(" or ");
			}
			sb.append(" xh<> ");
			sb.append("'"+myForm.getYdxh()[i]+"'");
		}
		
		if(myForm.getYdxh().length>0){
			sb.append(")");
		}
		
		sql.append(sb);
		sql.append(" ) b on a.lddm = b.lddm  and a.cs = b.cs ");
		sql.append(" and a.qsh = b.qsh  and a.cwh = b.cwh) a ");
		sql.append(" left join (select xh, xm, bjdm from xsxxb  ");
		sql.append("  union  select xh, xm, bjdm from bks_xsjbxx b  ");
		sql.append("  where not exists (select 1 from xsxxb c where b.xh = c.xh)) b on a.xh = b.xh) a  ");
		sql.append("  left join view_njxyzybj_all b on a.bjdm = b.bjdm)a ");
		
		sql.append(" left join ( "); 
		sql.append(gywhDAO.userStatusSql(model));
		sql.append(" )b "); 
		
		sql.append("  on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh  ");
		sql.append("  order by a.lddm, a.cs, a.qsh, a.cwh  ");
		
		String[]outPut={"lddm","cs","qsh","cwh","cws","xh","zrs","xm","xb","kfhz","xydm","xymc","zydm","zymc","bjdm","bjmc","nj"};
        return dao.getList(sql.toString(), inputList.toArray(new String[]{}), outPut);
	}
	
	/**
	 * 获取学生住宿信息
	 * @param myForm
	 * @param rForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXszsxxList(GyglCwglForm myForm,RequestForm rForm,
			User user,HttpServletRequest request) throws Exception{
		GyglGywhDAO gywhDAO=new GyglGywhDAO();
		SearchService searchService = new SearchService();
		
		StringBuilder sql=new StringBuilder();
		GyglGywhForm gyglForm=new GyglGywhForm();
		StringBuilder query=new StringBuilder();
		String searchTjByUser = searchService.getSearchTjByUser("a", user);
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		searchTj += searchTjByUser;		
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
		String[]colList=rForm.getColList();
		
		gyglForm.setUser(user);
		gyglForm.setFpdx(myForm.getFpdx());
		
		sql.append(" select rownum r,a.* from(select b.* from ( ");
		sql.append(" select xh pkValue,xh,xm,xb,nj,xymc,zymc,bjmc,xydm,zydm,bjdm,ssbh,lddm,xqdm,yqdm,ldmc,cs,qsh,cwh,rzrq from xg_view_gygl_xszsxx ");
		
		sql.append(" )b left join (");
		
		sql.append(gywhDAO.userStatusSql(gyglForm));
		sql.append(" )a on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh )a where 1=1 ");
		query.append(searchTj);
		return CommonQueryDAO.commonQuery(sql.toString(),query.toString(),inputV,colList, myForm);
	}
	
	public List<String[]>getXyCwxx(GyglCwglForm myForm, User user,
			String[] colList, String query, String[] inPutList) throws Exception{
		StringBuilder sql=new StringBuilder();	
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
	
		// ======================学院人数==========================
		sql.append(" select rownum r,a.xydm pkValue,a.xydm,a.xymc,nvl(a.xyrs, 0)xyrs, nvl(b.rzbss, 0)rzbss,nvl(c.rzqtss, 0)rzqtss,nvl(d.wzsrs, 0)wzsrs from ( ");
		sql.append(" select xydm,xymc  ");
		sql.append(" ,count(1)xyrs  ");
		sql.append(" from view_xsjbxx a where 1 = 1  ");
		sql.append(query);
		sql.append("  group by  ");
		sql.append(" xydm,xymc )a left join  ");
		
//		 ======================学院 入住本部门宿舍==========================
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
		sql.append(" left join view_xsjbxx c on a.xh=c.xh  where a.bmdm<>c.xydm   )a group by a.xydm");
		sql.append(" )c on a.xydm=c.xydm left join ");
		                   
		
		sql.append("  (select xydm,count(1)wzsrs from view_xsjbxx a where not exists  ");                                                           
		sql.append("  (select 1 from xszsxxb b where a.xh=b.xh)group by xydm )d on a.xydm=d.xydm  ");
		
		sql.append(" where 1=1 order by a.xydm ");
		return CommonQueryDAO.commonQuery(sql.toString(),"",inputV,colList, myForm);
	} 
	
	public List<String[]>getNjXyCwxx(GyglCwglForm myForm, User user,
			String[] colList, String query, String[] inPutList) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
		
	
		// ======================学院人数==========================
		sql.append(" select rownum r,a.nj||'!!@@!!'||a.xydm pkValue,a.nj,a.xydm,a.xymc, nvl(a.xyrs, 0)xyrs, nvl(b.rzbss, 0)rzbss,nvl(c.rzqtss, 0)rzqtss,nvl(d.wzsrs, 0)wzsrs from ( ");
		sql.append(" select nj,xydm,xymc  ");
		sql.append(" ,count(1)xyrs  ");
		sql.append(" from view_xsjbxx a where 1 = 1  ");
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
		sql.append(" left join view_xsjbxx c on a.xh=c.xh  where (a.nj<>c.nj or a.bmdm<>c.xydm)   )a group by a.nj,a.xydm");
		sql.append(" )c on a.xydm=c.xydm and a.nj=c.nj  left join ");
		                   
		
		sql.append("  (select nj,xydm,count(1)wzsrs from view_xsjbxx a where not exists  ");                                                           
		sql.append("  (select 1 from xszsxxb b where a.xh=b.xh)group by xydm,nj )d on a.xydm=d.xydm and a.nj=d.nj ");
			
		sql.append("  where 1=1 order by a.nj,a.xydm  ");
		return CommonQueryDAO.commonQuery(sql.toString(),"",inputV,colList, myForm);
	} 
	
	public List<String[]>getNjZyCwxx(GyglCwglForm myForm, User user,
			String[] colList, String query, String[] inPutList) throws Exception{
		
		StringBuilder sql=new StringBuilder();	
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());	
	
		// ======================年级专业人数==========================
		sql.append(" select rownum r,a.nj||'!!@@!!'||a.zydm pkValue,a.nj,a.zydm,a.zymc,nvl(a.xyrs, 0)xyrs, nvl(b.rzbss, 0)rzbss,nvl(c.rzqtss, 0)rzqtss,nvl(d.wzsrs, 0)wzsrs from ( ");
		sql.append(" select nj,zydm,zymc  ");
		sql.append(" ,count(1)xyrs  ");
		sql.append(" from view_xsjbxx a where 1 = 1  ");
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
		sql.append(" left join view_xsjbxx c on a.xh=c.xh  where (a.nj<>c.nj or a.bmdm<>c.zydm  ) )a group by a.nj,a.zydm");
		sql.append(" )c on a.zydm=c.zydm and a.nj=c.nj left join  ");
		                   
		
		sql.append("  (select nj,zydm,count(1)wzsrs from view_xsjbxx a where not exists  ");                                                           
		sql.append("  (select 1 from xszsxxb b where a.xh=b.xh)group by nj,zydm )d on a.zydm=d.zydm and a.nj=d.nj ");
		
		sql.append("  where 1=1  order by a.nj,a.zydm ");
		return CommonQueryDAO.commonQuery(sql.toString(),"",inputV,colList, myForm);
	} 
	
	public List<String[]>getNjBjCwxx(GyglCwglForm myForm, User user,
			String[] colList, String query, String[] inPutList) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
	
		// ======================年级专业人数==========================
		sql.append(" select rownum r,a.bjdm pkValue,a.nj,a.xydm,a.xymc,a.zydm,a.zymc, a.bjdm,a.bjmc,nvl(a.xyrs, 0)xyrs, nvl(b.rzbss, 0)rzbss,nvl(c.rzqtss, 0)rzqtss,nvl(d.wzsrs, 0)wzsrs from (");
		sql.append("  select  nj,xydm,xymc,zydm,zymc,bjdm,bjmc ");
		sql.append("  ,count(1)xyrs ");
		sql.append("  from view_xsjbxx a  where 1 = 1 ");
		sql.append(query);
		sql.append("   group by ");
		sql.append("  nj,xydm,xymc,zydm,zymc,bjdm,bjmc )a left join ");
		
//		 ======================年级+专业 入住本部门宿舍==========================
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
		sql.append(" )c on a.bjdm=c.bjdm left join  ");
		                   
		//======================年级专业 未入住人数==========================
		sql.append("  (select bjdm,count(1)wzsrs from view_xsjbxx a where not exists ");                                                          
		sql.append("  (select 1 from xszsxxb b where a.xh=b.xh)group by bjdm  )d on a.bjdm=d.bjdm  ");
		
		sql.append("  where 1=1  order by a.nj,a.xydm,a.zydm,a.bjdm ");
		return CommonQueryDAO.commonQuery(sql.toString(),"",inputV,colList, myForm);
	} 
	
	
	public List<String[]>getBmxxList(GyglCwglForm myForm,User user,String[] colList,HttpServletRequest request) throws Exception{
		
		GyglCwglService cwglService=new GyglCwglService();
		SearchService searchService=new SearchService();
		//构建过滤条件
		HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		
		SearchModel searchModel=myForm.getSearchModel();
		String fpdx=myForm.getFpdx();
		String searchTjByUser = searchService.getSearchTjByUser("a", user);	
		searchModel.setPath("gygl_cwgl_cwfp.do&searchType="+fpdx);
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		StringBuilder query=new StringBuilder();
		query.append(searchTj);
		String[] inputSearch = SearchService.getTjInput(myForm.getSearchModel());
		
		StringBuilder sql=new StringBuilder();
		StringBuilder nansSql=new StringBuilder();
		StringBuilder nvsSql=new StringBuilder();
		
		
		//显示字段
		String xszd="";
		//distinct字段
		String dist="";
		String pk="";
		if("xy".equalsIgnoreCase(fpdx)){
			pk="a.xydm pkValue";
			xszd=" a.xydm,a.xymc ";
			dist=" distinct (xydm) disxy,";
		}else if("njxy".equalsIgnoreCase(fpdx)){
			pk=" a.nj||'!!@@!!'||a.xydm pkValue";
			xszd=" a.nj,a.xydm,a.xymc ";
			dist=" distinct (nj||xydm) disnjxy,";
		}else if("njzy".equalsIgnoreCase(fpdx)){
			pk=" a.nj||'!!@@!!'||a.zydm pkValue";
			xszd=" a.nj,a.xydm,a.xymc,a.zydm,a.zymc ";
			dist=" distinct (nj||zydm) disnjzy,";
		}else if("bj".equalsIgnoreCase(fpdx)){
			pk=" a.bjdm pkValue";
			xszd=" a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc ";
			dist=" distinct (bjdm) disbj,";
		}
		
		
		sql.append(" select rownum r,a.* from (select ");
		sql.append(pk+",");
		sql.append(xszd);
		sql.append(" ,bmnanrs+bmnvrs ||'（'||bmnanrs||'/'||bmnvrs||'）' bmrs,fpnanqss+fpnvqss||'（'||fpnanqss||'/'||fpnvqss||'）' fpqss, ");
		sql.append(" kzrnancws+kzrnvcws ||'（'|| kzrnancws||'/'||kzrnvcws||'）' kzrcws,wzrnancws+wzrnvcws||'（'||wzrnancws||'/'||wzrnvcws||'）' wzrcws, ");
		sql.append(" yzrnancws+yzrnvcws ||'（'|| yzrnancws||'/'||yzrnvcws||'）' yzrcws from  ( ");
		
		
		nansSql.append(" from (select "+xszd+",nvl(b.bmrs, 0) bmrs,  ");
		nansSql.append(" nvl(c.fpqss, 0) fpqss,nvl(c.kzrcws, 0) kzrcws ");
		nansSql.append(" from (select "+dist+xszd);
		nansSql.append(" from view_njxyzybj a where 1 = 1 "+searchTjByUser+") a left join  ");
		
		nansSql.append(" (select "+xszd+", count(1) bmrs  ");
		nansSql.append(" from view_xsjbxx a where a.xb=? group by "+xszd+") b on  ");
		if("xy".equalsIgnoreCase(fpdx)){
			nansSql.append(" a.xydm=b.xydm left join (select bmdm xydm,nj, ");
		}else if("njxy".equalsIgnoreCase(fpdx)){
			nansSql.append(" a.xydm = b.xydm and a.nj = b.nj left join (select bmdm xydm, nj, ");
		}else if("njzy".equalsIgnoreCase(fpdx)){
			nansSql.append(" a.zydm = b.zydm and a.nj = b.nj left join (select bmdm zydm, nj, ");
		}else if("bj".equalsIgnoreCase(fpdx)){
			nansSql.append(" a.bjdm=b.bjdm  left join (select bmdm , nj, ");
		}
		nansSql.append(" count(1) fpqss,nvl(sum(cws), 0) kzrcws ");
		nansSql.append(" from (select c.bmdm,c.nj,c.lddm,c.cs,c.qsh,d.cws ");
		nansSql.append(" from (select * from xg_gygl_qsfpb c where exists(select 1 from ssxxb z where c.lddm=z.lddm ");
		nansSql.append(" and c.cs=z.cs and c.qsh=z.qsh and z.xb=? ))c left join (select lddm, ");
		nansSql.append(" cs,qsh,count(1) cws  from xg_gygl_cwxxb d where not exists ");
		nansSql.append(" (select 1 from xg_gygl_qtcwxxb e where d.lddm = e.lddm  and d.cs = e.cs ");
		nansSql.append("  and d.qsh = e.qsh)group by lddm, cs, qsh) d on c.lddm =d.lddm and c.cs = d.cs ");
		nansSql.append("  and c.qsh = d.qsh ) c  group by bmdm, nj) c  ");
		if("xy".equalsIgnoreCase(fpdx)){
			nansSql.append(" on a.xydm = c.xydm ) a ");
		}else if("njxy".equalsIgnoreCase(fpdx)){
			nansSql.append(" on a.xydm = c.xydm and a.nj = c.nj) a  ");
		}else if("njzy".equalsIgnoreCase(fpdx)){
			nansSql.append(" on a.zydm = c.zydm and a.nj = c.nj) a  ");
		}else if("bj".equalsIgnoreCase(fpdx)){
			nansSql.append(" on a.bjdm = c.bmdm ) a  ");
		}
		
		
		nansSql.append(" left join (select bmdm ,nj, count(1) fpqss,nvl(sum(cws), 0) yzrcws ");
		nansSql.append(" from (select c.bmdm, c.nj, c.lddm, c.cs, c.qsh, d.cws  from xg_gygl_qsfpb c ");
		nansSql.append(" left join (select count(1) cws,a.cwh,a.qsh,a.lddm,a.cs from xszsxxb a  ");
		nansSql.append("  where exists  (select 1 from xg_gygl_cwxxb b where a.lddm = b.lddm  ");
		nansSql.append(" and a.cs = b.cs and a.qsh = b.qsh  and a.cwh = b.cwh) ");
		nansSql.append(" and exists(select 1 from ssxxb b where a.lddm=b.lddm  and a.cs=b.cs ");
		nansSql.append(" and a.qsh=b.qsh and b.xb=? ) group by a.lddm, a.qsh, a.cwh, a.cs) d on c.lddm =d.lddm ");
		nansSql.append(" and c.cs = d.cs and c.qsh =d.qsh) a group by bmdm, nj) c ");
		if("xy".equalsIgnoreCase(fpdx)){
			nansSql.append(" on a.xydm = c.bmdm ) a  ");
		}else if("njxy".equalsIgnoreCase(fpdx)){
			nansSql.append(" on a.xydm = c.bmdm and a.nj = c.nj) a  ");
		}else if("njzy".equalsIgnoreCase(fpdx)){
			nansSql.append(" on a.zydm = c.bmdm and a.nj = c.nj) a  ");
		}else if("bj".equalsIgnoreCase(fpdx)){
			nansSql.append(" on a.bjdm = c.bmdm ) a  ");
		}
		
		nvsSql.append(" select  a.* ");
		nvsSql.append(" from (select "+xszd+",a.bmrs bmnvrs,a.fpqss fpnvqss,a.kzrcws kzrnvcws, ");
		nvsSql.append(" a.kzrcws - nvl(c.yzrcws, 0) wzrnvcws,nvl(c.yzrcws, 0) yzrnvcws ");
		nvsSql.append(nansSql);
		
		//男生信息统计
		sql.append(" select  a.* ");
		sql.append(" from (select "+xszd+",a.bmrs bmnanrs,a.fpqss fpnanqss,a.kzrcws kzrnancws, ");
		sql.append(" a.kzrcws - nvl(c.yzrcws, 0) wzrnancws,nvl(c.yzrcws, 0) yzrnancws ");
		sql.append(nansSql);
		sql.append(" )a left join ( ");
		//女生信息统计
		sql.append(nvsSql);
		
		String[]outString=null;
		if("xy".equalsIgnoreCase(fpdx)){
			outString=new String[]{"xymc","bmrs","fpqss","kzrcws","wzrcws","yzrcws"};
			sql.append(" )b on  a.xydm=b.xydm where 1=1)a  "+query+" order by a.xydm ");
		}else if("njxy".equalsIgnoreCase(fpdx)){
			outString=new String[]{"xymc","bmrs","fpqss","kzrcws","wzrcws","yzrcws"};
			sql.append(" )b on a.nj=b.nj and a.xydm=b.xydm)a where 1=1  "+query+" order by a.nj, a.xydm ");
		}else if("njzy".equalsIgnoreCase(fpdx)){
			outString=new String[]{"xymc","bmrs","fpqss","kzrcws","wzrcws","yzrcws"};
			sql.append(" )b on a.nj=b.nj and a.zydm=b.zydm)a where 1=1  "+query+" order by a.nj, a.xydm ");
		}else if("bj".equalsIgnoreCase(fpdx)){
			outString=new String[]{"xymc","bmrs","fpqss","kzrcws","wzrcws","yzrcws"};
			sql.append(" )b on a.bjdm=b.bjdm)a where 1=1 "+query+"order by a.bjdm ");
		}
		
		
		String[]inputV=new String[]{"男","男","男","女","女","女"};
		DAO dao=DAO.getInstance();
		String[]inputArr=dao.unionArray(inputV, inputSearch);
		
		return CommonQueryDAO.commonQuery(sql.toString(),"",inputArr,colList, myForm);
	}
	
	/**
	 * 根据部门获取选择部门学生列表
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getBmxsList(GyglCwglForm myForm){
		
		DAO dao=DAO.getInstance();
		
		String[] primaryKey=myForm.getPrimarykey_checkVal();
		StringBuilder sql=new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		String fpdx=myForm.getFpdx();
		String cxzd="";
		sql.append(" select xh from view_xsjbxx  a where 1=1 ");
		
		if("xy".equalsIgnoreCase(fpdx)){
			cxzd=" xydm ";
		}else if("njxy".equalsIgnoreCase(fpdx)){
			cxzd=" nj||'!!@@!!'||xydm ";
		}else if("njzy".equalsIgnoreCase(fpdx)){
			cxzd=" nj||'!!@@!!'||zydm ";
		}else if("bj".equalsIgnoreCase(fpdx)){
			cxzd=" bjdm ";
		}
		
		if(primaryKey!=null && primaryKey.length>0){
			for(int i=0;i<primaryKey.length;i++){
				if(i==0){
				sql.append(" and ( ");
				}else {
					sql.append(" or ");
				}
				sql.append(cxzd);
				sql.append(" = ? ");
				inputV.add(primaryKey[i]);
			}
			sql.append(" ) ");
		}
		
		sql.append(" and not exists(select 1 from xszsxxb b where a.xh=b.xh)");
		sql.append(" order by a.xb ");
		String[]outputValue=new String[]{"xh"};
		return dao.getList(sql.toString(), inputV.toArray(new String[]{}), outputValue);
		
	}
	
	/**
	 * 筛选需要分配床位学生信息
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 * @throws Exception 
	 */
	public List<String[]>getSxxsList(GyglCwglForm myForm,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		SearchService searchService=new SearchService();
		
		//构建过滤条件
		HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
		// 不需要控制数据范围的权限
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		StringBuilder queryB=new StringBuilder();
		String searchTjByUser = searchService.getSearchTjByUser("a", user);	
		queryB.append(searchTj+searchTjByUser);
		
		String[] inputSearch = SearchService.getTjInput(myForm.getSearchModel());
		String[] primaryKey=myForm.getPrimarykey_checkVal();
		StringBuilder sql=new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		String fpdx=myForm.getFpdx();
		String cxzd="";
		sql.append(" select rownum r,a.xh pkValue,a.xh,a.xm,a.xb,a.nj,a.bjdm,a.bjmc,a.xydm,a.xymc,a.zydm,a.zymc ");
		sql.append("from view_xsjbxx  a where 1=1 ");
		
		if("xy".equalsIgnoreCase(fpdx)){
			cxzd=" xydm ";
		}else if("njxy".equalsIgnoreCase(fpdx)){
			cxzd=" nj||'!!@@!!'||xydm ";
		}else if("njzy".equalsIgnoreCase(fpdx)){
			cxzd=" nj||'!!@@!!'||zydm ";
		}else if("bj".equalsIgnoreCase(fpdx)){
			cxzd=" bjdm ";
		}
		
		if(primaryKey!=null && primaryKey.length>0){
			for(int i=0;i<primaryKey.length;i++){
				if(i==0){
				sql.append(" and ( ");
				}else {
					sql.append(" or ");
				}
				sql.append(cxzd);
				sql.append(" = ? ");
				inputV.add(primaryKey[i]);
			}
			sql.append(" ) ");
		}
		
		sql.append(" and not exists(select 1 from xszsxxb b where a.xh=b.xh)");
		
		String[]outputValue=new String[]{"pkValue","xh","xm","xb","nj","bjmc"};
		return CommonQueryDAO.commonQuery(sql.toString(), queryB.toString(), 
				dao.unionArray(inputV.toArray(new String[]{}), inputSearch), outputValue, myForm);
		
	}
	
	
}
