package xgxt.action.csmz;

/*
 * �������� 2006-9-16
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
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
				chkUser.setErrMsg("��½��ʱ�������µ�½��");
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
				myActFwd = rckhQueryCsmz(mapping, form, request, response); // �ճ�����-�ճ����˲�ѯ-��ɳ����
			} else if(myAct.equalsIgnoreCase("addRckh")) {
				myActFwd = addRckh(mapping, form, request, response); // �ճ�����-�����ճ�����-��ɳ����
			} else if(myAct.equalsIgnoreCase("updateRckh")) {
				myActFwd = updateRckh(mapping, form, request, response); // �ճ�����-�޸��ճ�����-��ɳ����
			} else if(myAct.equalsIgnoreCase("rckhMoreQuery")) {
				myActFwd = rckhMoreQuery(mapping, form, request, response); // �ճ�����-�ճ����˲鿴-��ɳ����
			} else if(myAct.equalsIgnoreCase("gzzbQueryCsmz")) {
				myActFwd = gzzbQueryCsmz(mapping, form, request, response); // �ճ�����-�����ܱ���ѯ-��ɳ����
			} else if(myAct.equalsIgnoreCase("addGzzb")) {
				myActFwd = addGzzb(mapping, form, request, response); // �ճ�����-���ӹ����ܱ�-��ɳ����
			} else if(myAct.equalsIgnoreCase("updateGzzb")) {
				myActFwd = updateGzzb(mapping, form, request, response); // �ճ�����-�޸Ĺ����ܱ�-��ɳ����
			} else if(myAct.equalsIgnoreCase("gzzbMoreQuery")) {
				myActFwd = gzzbMoreQuery(mapping, form, request, response); // �ճ�����-�����ܱ��鿴-��ɳ����
			} 
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("���������жϣ������µ�½��");
		}
		return new ActionForward("/login.jsp", false);
	}

	// ��ɳ���� �ճ����˲�ѯ
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
		// [�ж�ӵ������
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
		sql = "select count(*) count from csmz_rcgl_rckhgzzbb a where 1=1 and type='�ճ�����' "
				+ query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ѧ��������Ϣ
		sql = "select a.r �к�,a.* from (select a.*,rownum r from (select distinct a.rowid rid, a.bt,a.fbr,a.fbsj from csmz_rcgl_rckhgzzbb a where 1=1 and type='�ճ�����'   "
				+ query1
				+ " order by fbsj desc ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		String[] colList = new String[] {"rid","�к�", "bt","fbr","fbsj" };
		if (xxdm.equals(Globals.XXDM_CSMZZYJSXY) && act == null) {
			act = "go";
		}
		if ("go".equalsIgnoreCase(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
			rsNum=String.valueOf(rs.size());
		}


//		sql = "select count(*) from csmz_rcgl_rckhgzzbb where 1=1 and type='�ճ�����' " + query1;
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
    
	//�����ճ�����
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
						new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��
		
		if("save".equalsIgnoreCase(doType)){
			boolean judge = false;
			judge= StandardOperation.insert("csmz_rcgl_rckhgzzbb", new String[]{ "bt","nr","fbr","fbsj","type" },
					new String[]{bt,nr,fbr,fbsj,"�ճ�����"}, request);
			if(judge){
				request.setAttribute("save", "ok");
			}else{
				request.setAttribute("save", "no");
			}
		}
		return mapping.findForward("success");
	}
	
	//�޸��ճ�����
	private ActionForward updateRckh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		HashMap<String, String> rs = new HashMap<String, String>(); //������ݼ� 
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
	
	
	
	//�ճ����˾������ݲ�ѯ
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
	
//	 ��ɳ���� �����ܱ���ѯ
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
		// [�ж�ӵ������
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
		sql = "select count(*) count from csmz_rcgl_rckhgzzbb a where 1=1 and type='�����ܱ�' "
				+ query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ѧ��������Ϣ
		sql = "select a.r �к�,a.* from (select a.*,rownum r from (select distinct a.rowid rid, a.bt,a.fbr,a.fbsj from csmz_rcgl_rckhgzzbb a where 1=1 and type='�����ܱ�'   "
				+ query1
				+ " order by fbsj desc ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		String[] colList = new String[] {"rid","�к�", "bt","fbr","fbsj" };
		if (xxdm.equals(Globals.XXDM_CSMZZYJSXY) && act == null) {
			act = "go";
		}
		if ("go".equalsIgnoreCase(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
			rsNum = String.valueOf(rs.size());
		}


//		sql = "select count(*) from csmz_rcgl_rckhgzzbb where 1=1 and type='�����ܱ�' " + query1;
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
	
    //���ӹ����ܱ�
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
						new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��
		
		if("save".equalsIgnoreCase(doType)){
			boolean judge = false;
			judge= StandardOperation.insert("csmz_rcgl_rckhgzzbb", new String[]{ "bt","nr","fbr","fbsj","type" },
					new String[]{bt,nr,fbr,fbsj,"�����ܱ�"}, request);
			if(judge){
				request.setAttribute("save", "ok");
			}else{
				request.setAttribute("save", "no");
			}
		}
		return mapping.findForward("success");
	}
	
	//�޸Ĺ����ܱ�
	private ActionForward updateGzzb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		HashMap<String, String> rs = new HashMap<String, String>(); //������ݼ� 
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
	
//	�����ܱ��������ݲ�ѯ
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
