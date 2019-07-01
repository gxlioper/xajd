package xgxt.action.csmz;

/*
 * 创建日期 2006-9-16
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.sql.CLOB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.ApplyAction;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;

/**
 * @author bat_zzj
 */

public class RcswAction extends ApplyAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm chkUser = (CommanForm) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}

			ActionForward myActFwd = null;
			String myAct = mapping.getParameter();

			if (myAct.equalsIgnoreCase("rckhQueryCsmz")) {
				myActFwd = rckhQueryCsmz(mapping, form, request, response); // 日常管理-日常考核查询-长沙民政
			} else if(myAct.equalsIgnoreCase("addRckh")) {
				myActFwd = addRckh(mapping, form, request, response); // 日常管理-增加日常考核-长沙民政
			} else if(myAct.equalsIgnoreCase("updateRckh")) {
				myActFwd = updateRckh(mapping, form, request, response); // 日常管理-修改日常考核-长沙民政
			} else if(myAct.equalsIgnoreCase("rckhMoreQuery")) {
				myActFwd = rckhMoreQuery(mapping, form, request, response); // 日常管理-日常考核查看-长沙民政
			} else if(myAct.equalsIgnoreCase("gzzbQueryCsmz")) {
				myActFwd = gzzbQueryCsmz(mapping, form, request, response); // 日常管理-工作周报查询-长沙民政
			} else if(myAct.equalsIgnoreCase("addGzzb")) {
				myActFwd = addGzzb(mapping, form, request, response); // 日常管理-增加工作周报-长沙民政
			} else if(myAct.equalsIgnoreCase("updateGzzb")) {
				myActFwd = updateGzzb(mapping, form, request, response); // 日常管理-修改工作周报-长沙民政
			} else if(myAct.equalsIgnoreCase("gzzbMoreQuery")) {
				myActFwd = gzzbMoreQuery(mapping, form, request, response); // 日常管理-工作周报查看-长沙民政
			} 
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("数据连接中断，请重新登陆！");
		}
		return new ActionForward("/login.jsp", false);
	}

	// 长沙民政 日常考核查询
	private ActionForward rckhQueryCsmz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String xxdm = dao.getXxdm();
		String rsNum="0";
		String sql = "";
		// [判断拥护类型
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
//    	String userName = session.getAttribute("userName").toString();
		String bt = DealString.toGBK(request.getParameter("bt"));
		map.put("bt", bt);
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));

		if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			request.setAttribute("xx", "xx");
		}
		
		if("del".equalsIgnoreCase(doType)){
			
			boolean judge = false;
			judge= StandardOperation.delete("csmz_rcgl_rckhgzzbb", "rowid", pkValue, request);
			if(judge){
				request.setAttribute("delete", "ok");
			}else{
				request.setAttribute("delete", "no");
			}
		}
		
		
		
		StringBuffer query = new StringBuffer();

		if (!("".equals(bt))) {
			query.append(" and bt like '%");
			query.append(bt);
			query.append("%'");
		}


		String query1 = query.toString();

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from csmz_rcgl_rckhgzzbb a where 1=1 and type='日常考核' "
				+ query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// 学生基本信息
		sql = "select a.r 行号,a.* from (select a.*,rownum r from (select distinct a.rowid rid, a.bt,a.fbr,a.fbsj from csmz_rcgl_rckhgzzbb a where 1=1 and type='日常考核'   "
				+ query1
				+ " order by fbsj desc ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		String[] colList = new String[] {"rid","行号", "bt","fbr","fbsj" };
		if (xxdm.equals(Globals.XXDM_CSMZZYJSXY) && act == null) {
			act = "go";
		}
		if ("go".equalsIgnoreCase(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
			rsNum=String.valueOf(rs.size());
		}


//		sql = "select count(*) from csmz_rcgl_rckhgzzbb where 1=1 and type='日常考核' " + query1;
//		int rsNuminfo = dao.getOneRsint(sql);
//		String rsNum = String.valueOf(rsNuminfo);
//		String[] colListCN = dao.getColumnNameCN(colList,
//				"csmz_rcgl_rckhgzzbb");
//		List topTr = dao.arrayToList(colList, colListCN);
	

		
		request.setAttribute("querry", query1);
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
//		request.setAttribute("topTr", topTr);

		return mapping.findForward("success");
	}
    
	//增加日常考核
	private ActionForward addRckh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String fbr=session.getAttribute("userName").toString();  
		String doType = request.getParameter("doType");
		request.setAttribute("fbr", fbr);
		
//		fbr = DealString.toGBK(request.getParameter("fbr"));
		String bt = DealString.toGBK(request.getParameter("bt"));
		String nr = DealString.toGBK(request.getParameter("content1"));
		String fbsj = (dao
				.getOneRs(
						"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
						new String[] {}, new String[] { "sdate" }))[0]; // 提交时间
		
		if("save".equalsIgnoreCase(doType)){
			boolean judge = false;
			judge= StandardOperation.insert("csmz_rcgl_rckhgzzbb", new String[]{ "bt","nr","fbr","fbsj","type" },
					new String[]{bt,nr,fbr,fbsj,"日常考核"}, request);
			if(judge){
				request.setAttribute("save", "ok");
			}else{
				request.setAttribute("save", "no");
			}
		}
		return mapping.findForward("success");
	}
	
	//修改日常考核
	private ActionForward updateRckh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		HashMap<String, String> rs = new HashMap<String, String>(); //相关数据集 
		String doType = request.getParameter("doType");
		String rowid = request.getParameter("pkValue");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}
		
//		fbr = DealString.toGBK(request.getParameter("fbr"));
		String bt = DealString.toGBK(request.getParameter("bt"));
		String nr = DealString.toGBK(request.getParameter("content1"));

		if("update".equalsIgnoreCase(doType)){
			boolean judge = false;
			judge= StandardOperation.update("csmz_rcgl_rckhgzzbb", new String[]{ "bt","nr" },
					new String[]{bt,nr}, "rowid", rowid, request);
			if(judge){
				request.setAttribute("update", "ok");
			}else{
				request.setAttribute("update", "no");
			}
		}
		

		String[] tit = new String[] { "rowid", "bt", "fbr", "fbsj", "nr",
				"type" };
		String sql = "select rowid,bt,fbr,fbsj,nr,type from "
				+ "csmz_rcgl_rckhgzzbb where rowid=?";
		rs = dao.getMap(sql, new String[]{ rowid }, tit);
		
		if(null!=rs.get("fbsj")){
			rs.put("fbsj", dao.doForTime(rs.get("fbsj")));
		}

		
		CLOB clob = dao.getOneClob(sql, new String[] { rowid }, "nr");
		nr = clob.getSubString(1L, (int) clob.length());
		request.setAttribute("nr", nr);
		request.setAttribute("rs", rs);
		
		
		return mapping.findForward("success");
	}
	
	
	
	//日常考核具体内容查询
	public ActionForward rckhMoreQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> rs = new HashMap<String, String>();
		String rowid = request.getParameter("pkValue");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}

		String[] tit = new String[] { "rowid", "bt", "fbr", "fbsj", "nr",
				"type" };
		String sql = "select rowid,bt,fbr,fbsj,nr,type from "
				+ "csmz_rcgl_rckhgzzbb where rowid=?";
		rs = dao.getMap(sql, new String[]{ rowid }, tit);
		
		if(null!=rs.get("fbsj")){
			rs.put("fbsj", dao.doForTime(rs.get("fbsj")));
		}

		
		CLOB clob = dao.getOneClob(sql, new String[] { rowid }, "nr");
		String nr = clob.getSubString(1L, (int) clob.length());
		request.setAttribute("nr", nr);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}
	
//	 长沙民政 工作周报查询
	private ActionForward gzzbQueryCsmz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String xxdm = dao.getXxdm();
		String rsNum= "0"; 
		String sql = "";
		// [判断拥护类型
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
//		String userName = session.getAttribute("userName").toString();
		String bt = DealString.toGBK(request.getParameter("bt"));
		map.put("bt", bt);
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		
		if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			request.setAttribute("xx", "xx");
		}

		if("del".equalsIgnoreCase(doType)){
			
			boolean judge = false;
			judge= StandardOperation.delete("csmz_rcgl_rckhgzzbb", "rowid", pkValue, request);
			if(judge){
				request.setAttribute("delete", "ok");
			}else{
				request.setAttribute("delete", "no");
			}
		}
		
		
		
		StringBuffer query = new StringBuffer();

		if (!("".equals(bt))) {
			query.append(" and bt like '%");
			query.append(bt);
			query.append("%'");
		}


		String query1 = query.toString();

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from csmz_rcgl_rckhgzzbb a where 1=1 and type='工作周报' "
				+ query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// 学生基本信息
		sql = "select a.r 行号,a.* from (select a.*,rownum r from (select distinct a.rowid rid, a.bt,a.fbr,a.fbsj from csmz_rcgl_rckhgzzbb a where 1=1 and type='工作周报'   "
				+ query1
				+ " order by fbsj desc ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		String[] colList = new String[] {"rid","行号", "bt","fbr","fbsj" };
		if (xxdm.equals(Globals.XXDM_CSMZZYJSXY) && act == null) {
			act = "go";
		}
		if ("go".equalsIgnoreCase(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
			rsNum = String.valueOf(rs.size());
		}


//		sql = "select count(*) from csmz_rcgl_rckhgzzbb where 1=1 and type='工作周报' " + query1;
//		int rsNuminfo = dao.getOneRsint(sql);
//		rsNum = String.valueOf(rsNuminfo);
//		String[] colListCN = dao.getColumnNameCN(colList,
//				"csmz_rcgl_rckhgzzbb");
//		List topTr = dao.arrayToList(colList, colListCN);

		
		request.setAttribute("querry", query1);
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
//		request.setAttribute("topTr", topTr);

		return mapping.findForward("success");
	}
	
    //增加工作周报
	private ActionForward addGzzb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String fbr=session.getAttribute("userName").toString();  
		String doType = request.getParameter("doType");
		request.setAttribute("fbr", fbr);
		
//		fbr = DealString.toGBK(request.getParameter("fbr"));
		String bt = DealString.toGBK(request.getParameter("bt"));
		String nr = DealString.toGBK(request.getParameter("content1"));
		String fbsj = (dao
				.getOneRs(
						"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
						new String[] {}, new String[] { "sdate" }))[0]; // 提交时间
		
		if("save".equalsIgnoreCase(doType)){
			boolean judge = false;
			judge= StandardOperation.insert("csmz_rcgl_rckhgzzbb", new String[]{ "bt","nr","fbr","fbsj","type" },
					new String[]{bt,nr,fbr,fbsj,"工作周报"}, request);
			if(judge){
				request.setAttribute("save", "ok");
			}else{
				request.setAttribute("save", "no");
			}
		}
		return mapping.findForward("success");
	}
	
	//修改工作周报
	private ActionForward updateGzzb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		HashMap<String, String> rs = new HashMap<String, String>(); //相关数据集 
		String doType = request.getParameter("doType");
		String rowid = request.getParameter("pkValue");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}
		
//		fbr = DealString.toGBK(request.getParameter("fbr"));
		String bt = DealString.toGBK(request.getParameter("bt"));
		String nr = DealString.toGBK(request.getParameter("content1"));

		if("update".equalsIgnoreCase(doType)){
			boolean judge = false;
			judge= StandardOperation.update("csmz_rcgl_rckhgzzbb", new String[]{ "bt","nr" },
					new String[]{bt,nr}, "rowid", rowid, request);
			if(judge){
				request.setAttribute("update", "ok");
			}else{
				request.setAttribute("update", "no");
			}
		}
		

		String[] tit = new String[] { "rowid", "bt", "fbr", "fbsj", "nr",
				"type" };
		String sql = "select rowid,bt,fbr,fbsj,nr,type from "
				+ "csmz_rcgl_rckhgzzbb where rowid=?";
		rs = dao.getMap(sql, new String[]{ rowid }, tit);
		
		if(null!=rs.get("fbsj")){
			rs.put("fbsj", dao.doForTime(rs.get("fbsj")));
		}

		
		CLOB clob = dao.getOneClob(sql, new String[] { rowid }, "nr");
		nr = clob.getSubString(1L, (int) clob.length());
		request.setAttribute("nr", nr);
		request.setAttribute("rs", rs);
		
		
		return mapping.findForward("success");
	}
	
//	工作周报具体内容查询
	public ActionForward gzzbMoreQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> rs = new HashMap<String, String>();
		String rowid = request.getParameter("pkValue");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}

		String[] tit = new String[] { "rowid", "bt", "fbr", "fbsj", "nr",
				"type" };
		String sql = "select rowid,bt,fbr,fbsj,nr,type from "
				+ "csmz_rcgl_rckhgzzbb where rowid=?";
		rs = dao.getMap(sql, new String[]{ rowid }, tit);
		
		if(null!=rs.get("fbsj")){
			rs.put("fbsj", dao.doForTime(rs.get("fbsj")));
		}

		
		CLOB clob = dao.getOneClob(sql, new String[] { rowid }, "nr");
		String nr = clob.getSubString(1L, (int) clob.length());
		request.setAttribute("nr", nr);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}
	

}
