/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-10-9 ����03:15:51</p>
 */
package xgxt.pjpy.jhzy.rych;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.pjpy.jhzy.JhzyPjpyForm;
import xgxt.pjpy.jhzy.JhzyPjpyUnit;
import xgxt.pjpy.jhzy.pjsz.PjszService;
import xgxt.utils.Fdypd;

public class RychAction extends DispatchAction {
	/*
	 * ������Ŀ������
	 */
	public List<HashMap<String, String>> getCdList() {
		DAO dao = DAO.getInstance();
		return dao.getList(" select rychdm en,rychmc cn from  rychdmb   ", new String[]{},new String[]{"en","cn"});
//		String[] listEn = new String[] { "001", "002", "003", "004" };
//		String[] listCn = new String[] { "����ѧ��", "��������ѧ��", "ʡ�������ҵ��",
//				"У�������ҵ��" };
//		return dao.arrayToList(listEn, listCn);
	}

	/**
	 * �����ƺ�����˵�ѡ��
	 */
	public ActionForward rychSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdmV = request.getParameter("xmdm");
		int xmdm = Integer.parseInt(Base.isNull(xmdmV) ? "0" : xmdmV);
		switch (xmdm) {
		case 1: {
			return new ActionForward("/jhzy_rych.do?method=shxsRychSq",false);
		}
		case 2: {
			return new ActionForward("/jhzy_rych.do?method=dxyxxsRychSq",false);
		}
		case 3: {
			return new ActionForward("/jhzy_rych.do?method=sjyxbysRychSq",false);
		}
		case 4: {
			return new ActionForward("/jhzy_rych.do?method=xjyxbysRychSq",false);
		}
		}
		request.setAttribute("list", getCdList());
		return mapping.findForward("rychSq");
	}

	/**
	 * ����ѧ�������ƺ�����
	 */
	public ActionForward shxsRychSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RychService service = new RychService();
		PjszService szService = new PjszService(); 
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String act = DealString.toGBK(request.getParameter("act"));
		if ("student".equalsIgnoreCase(userOnLine)) {
			xh = userName;
		}
		if (xn == null) {
			xn = Base.getJxjsqxn();
		}
		myForm.setXn(xn);
		if(!Base.isNull(xh)&&!Base.isNull(xn)){
			if(!szService.isTjsz(xh, xn, "", "rych","001")){
				request.setAttribute("noPass","noPass");
			}
		}
		if ("save".equalsIgnoreCase(doType)) {
			ShsRychModel model = new ShsRychModel();
			BeanUtils.copyProperties(model, myForm);
			boolean done = service.serv_shsSqSave(model);
			request.setAttribute("done", done);			
		}
		request.setAttribute("rs", service.serv_getStuInfo(xh));
		request.setAttribute("rsRych", service.serv_getShsInfo(xh, xn));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("act", act);
		return mapping.findForward("shxsRychSq");
	}
	/**
	 * ����ѧ�������ƺ�����
	 */
	public ActionForward dxyxxsRychSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RychService service = new RychService();
		PjszService szService = new PjszService(); 
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userOnLine = request.getSession().getAttribute("userOnLine")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String act = DealString.toGBK(request.getParameter("act"));
		if ("student".equalsIgnoreCase(userOnLine)) {
			xh = userName;
		}
		if (xn == null) {
			xn = Base.getJxjsqxn();
		}
		myForm.setXn(xn);
		if(!Base.isNull(xh)&&!Base.isNull(xn)){
			if(!szService.isTjsz(xh, xn, "", "rych","002")){
				request.setAttribute("noPass","noPass");
			}
		}
		if ("save".equalsIgnoreCase(doType)) {
			DxyxxsModel model = new DxyxxsModel();
			BeanUtils.copyProperties(model, myForm);
			boolean done = service.serv_dxyxxsSqSave(model);
			request.setAttribute("done", done);
		}
		request.setAttribute("rs", service.serv_getStuInfo(xh));
		request.setAttribute("rsRych", service.serv_getDxyxsInfo(xh, xn));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("act", act);
		return mapping.findForward("dxyxxsRychSq");
	}
	/**
	 * ʡ�������ҵ�������ƺ�����
	 */
	public ActionForward sjyxbysRychSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RychService service = new RychService();
		PjszService szService = new PjszService(); 
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String act = DealString.toGBK(request.getParameter("act"));
		if ("student".equalsIgnoreCase(userOnLine)) {
			xh = userName;
		}
		if (xn == null) {
			xn = Base.getJxjsqxn();
		}
		myForm.setXn(xn);
		if(!Base.isNull(xh)&&!Base.isNull(xn)){
			if(!szService.isTjsz(xh, xn, "", "rych","003")){
				request.setAttribute("noPass","noPass");
			}
		}
		if ("save".equalsIgnoreCase(doType)) {
			sjyxbysRychModel model = new sjyxbysRychModel();
			BeanUtils.copyProperties(model, myForm);
			boolean done = service.serv_sjyxbysSqSave(model);			
			request.setAttribute("done", done);
		}
		request.setAttribute("rs", service.serv_getStuInfo(xh));
		request.setAttribute("rsRych", service.serv_getSjyxbysInfo(xh, xn));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("act", act);
		return mapping.findForward("sjyxbysRychSq");
	}
	/**
	 * У�������ҵ�������ƺ�����
	 */
	public ActionForward xjyxbysRychSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RychService service = new RychService();
		PjszService szService = new PjszService(); 
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userOnLine = request.getSession().getAttribute("userOnLine")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String act = DealString.toGBK(request.getParameter("act"));
		XjyxbysRychModel model = new XjyxbysRychModel();
		if ("student".equalsIgnoreCase(userOnLine)) {
			xh = userName;
		}
		if (xn == null) {
			xn = Base.getJxjsqxn();
		}
		myForm.setXn(xn);
		if(!Base.isNull(xh)&&!Base.isNull(xn)){
			if(!szService.isTjsz(xh, xn, "", "rych","004")){
				request.setAttribute("noPass","noPass");
			}
		}
		if ("save".equalsIgnoreCase(doType)) {
			BeanUtils.copyProperties(model, myForm);
			boolean done = service.serv_xjyxbysSqSave(model);
			request.setAttribute("done", done);
		}
		request.setAttribute("rs", service.serv_getStuInfo(xh));
		request.setAttribute("rsRych", service.serv_getXjyxbysInfo(xh, xn));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("act", act);		
		return mapping.findForward("xjyxbysRychSq");
	}

	/**
	 * �����ƺŲ�ѯ�˵�ѡ��
	 */
	public ActionForward rychCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//String userName = request.getSession().getAttribute("userName").toString();
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		if("student".equalsIgnoreCase(userOnline)){
			return new ActionForward("/jhzy_rych.do?method=rychXsSqJg",false);
		}
		
		String xmdmV = request.getParameter("xmdm");
		int xmdm = Integer.parseInt(Base.isNull(xmdmV) ? "0" : xmdmV);
		switch (xmdm) {
		case 1: {
			return new ActionForward("/jhzy_rych.do?method=shxsRychQuery",
					false);
		}
		case 2: {
			return new ActionForward("/jhzy_rych.do?method=dxyxxsRychQuery",
					false);
		}
		case 3: {
			return new ActionForward("/jhzy_rych.do?method=sjyxbysRychQuery",
					false);
		}
		case 4: {
			return new ActionForward("/jhzy_rych.do?method=xjyxbysRychQuery",
					false);
		}
		}
		request.setAttribute("list", getCdList());
		return mapping.findForward("rychCx");
	}

	/**
	 * ʡ�������ҵ�������ƺŲ�ѯ
	 */
	public ActionForward sjyxbysRychQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		RychService service = new RychService();
		HttpSession session = request.getSession();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String userOnline = request.getSession().getAttribute("userOnLine")
				.toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String xn = request.getParameter("xn");
		unit.setNjXyZyBjList(request, myForm);
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
			userType = "isFdy";
			myForm.setXydm("");
		}
		if (xn == null) {
			xn = Base.getJxjsqxn();
			myForm.setXn(xn);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			sjyxbysRychModel model = new sjyxbysRychModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getSjyxbysSHTopTr();
			rs = service.serv_getSjyxbysCxList(userType, userName, model);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("tableName","view_sjyxbysxx");
		request.setAttribute("realTable","jhzy_sjyxbysb");
		// ��дȨ�ж�
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "credit_result.do", userOnline
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");
		return mapping.findForward("sjyxbysRychQuery");
	}

	/**
	 * �����ƺ���˲˵�ѡ��
	 */
	public ActionForward rychSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		if (userType.equalsIgnoreCase("stu")) {// ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ���û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		String xmdmV = request.getParameter("xmdm");
		int xmdm = Integer.parseInt(Base.isNull(xmdmV) ? "0" : xmdmV);
		switch (xmdm) {
		case 1: {
			return new ActionForward("/jhzy_rych.do?method=shxsRychShQuery",
					false);
		}
		case 2: {
			return new ActionForward("/jhzy_rych.do?method=dxyxxsRychShQuery",
					false);
		}
		case 3: {
			return new ActionForward("/jhzy_rych.do?method=sjyxbysRychShQuery",
					false);
		}
		case 4: {
			return new ActionForward("/jhzy_rych.do?method=xjyxbysRychShQuery",
					false);
		}
		}
		request.setAttribute("list", getCdList());
		return mapping.findForward("rychSh");
	}

	/**
	 * ʡ�������ҵ�������ƺ���˲�ѯ
	 */
	public ActionForward sjyxbysRychShQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		RychService service = new RychService();
		HttpSession session = request.getSession();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String xn = request.getParameter("xn");

		unit.setNjXyZyBjList(request, myForm);
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
			userType = "isFdy";
			myForm.setXydm("");
		}
		if (xn == null) {
			xn = Base.getJxjsqxn();
			myForm.setXn(xn);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			sjyxbysRychModel model = new sjyxbysRychModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getSjyxbysSHTopTr();
			rs = service.serv_getSjyxbysList(userType, userName, model);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		return mapping.findForward("sjyxbysRychShQuery");
	}

	/**
	 * �����ƺ��������
	 */
	public ActionForward rychCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RychService service = new RychService();
		HttpSession session  = request.getSession();
		String check = request.getParameter("check");
		String userType = session.getAttribute("userType").toString();
		String pkVStr = request.getParameter("pkVStr");
		String xmk = request.getParameter("xmk");
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			userType = "isFdy";
		}
		service.serv_rychCk(userType, check, xmk, pkVStr);
		if ("sjyxbys".equalsIgnoreCase(xmk)) {			
			return sjyxbysRychShQuery(mapping, form, request, response);
		} else if("xjyxbys".equalsIgnoreCase(xmk)) {
			return xjyxbysRychShQuery(mapping, form, request, response);
		}if("shxs".equalsIgnoreCase(xmk)){
			return shxsRychShQuery(mapping, form, request, response);
		}if("dxyxxs".equalsIgnoreCase(xmk)){
			return dxyxxsRychShQuery(mapping, form, request, response);
		}else {
			return null;
		}
	}

	/**
	 * �����ƺ�����ɾ��
	 * 
	 * @throws Exception
	 */
	public ActionForward rychDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RychService service = new RychService();
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String pkVStr = request.getParameter("pkVStr");
		String xmk = request.getParameter("xmk");
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			userType = "isFdy";
		}
		service.serv_rychDel(userType, xmk, pkVStr);
		if ("sjyxbys".equalsIgnoreCase(xmk)) {			
			return sjyxbysRychQuery(mapping, form, request, response);
		} else if("xjyxbys".equalsIgnoreCase(xmk)) {			
			return xjyxbysRychQuery(mapping, form, request, response);
		}else if("shxs".equalsIgnoreCase(xmk)){
			return shxsRychQuery(mapping, form, request, response);
		}else if("dxyxxs".equalsIgnoreCase(xmk)){
			return dxyxxsRychQuery(mapping, form, request, response);
		}else{
			return null;
		}
	}

	/**
	 * �����ƺŵ������
	 */
	public ActionForward rychChek(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		RychService service = new RychService();
		
		String pkValue = request.getParameter("pkValue");
		String xmk = request.getParameter("xmk");
		HashMap<String, String> map = new HashMap<String, String>();
		String findForward = "";
//		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userType = (String) session.getAttribute("userType");
		String doType = request.getParameter("doType");
		
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			userType = "isFdy";
		}
        if("save".equalsIgnoreCase(doType)){//������˽��
        	boolean done=false;
        	String fdyshyj = DealString.toGBK(request.getParameter("fdyshyj"));
        	 String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
        	 String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));
        	 String yesNo = DealString.toGBK(request.getParameter("yesNo"));      	 
        	 done=service.serv_rychChk(userType, pkValue, xmk, fdyshyj, xyshyj, xxshyj, yesNo);
        	 request.setAttribute("done",done);
        }				
		if ("sjyxbys".equalsIgnoreCase(xmk)) {
			map = service.serv_getSjyxbysInfo(pkValue);
			findForward = "sjyxbysChek";
		}else if ("xjyxbys".equalsIgnoreCase(xmk)){
			map = service.serv_getXjyxbysInfo(pkValue);
			findForward = "xjyxbysChek";
		}else if("shxs".equalsIgnoreCase(xmk)){
			map = service.serv_getShsInfo(pkValue);
			findForward = "shxsChek";
		}else if("dxyxxs".equalsIgnoreCase(xmk)){
			map = service.serv_getDxyxsInfo(pkValue);
			findForward = "dxyxxsChek";
		}
		if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			map.put("yesNo",map.get("xxsh"));
		}else if("xy".equalsIgnoreCase(userType)){
			map.put("yesNo",map.get("xysh"));
		}else{
			map.put("yesNo",map.get("fdysh"));
		}
		request.setAttribute("shDjList",service.serv_getShDjList(map.get("xh")));
		request.setAttribute("shJxjList",service.serv_getShJxjList(map.get("xh")));
		request.setAttribute("cjList",service.serv_getCjList(map.get("xh")));
		request.setAttribute("rs", service.serv_getStuInfo(map.get("xh")));
		request.setAttribute("rsRych", map);
		request.setAttribute("chkList",service.serv_getChkList());
		request.setAttribute("userType",userType);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("xmk",xmk);
		return mapping.findForward(findForward);
	}
	/**
	 * ������ӡ
	 */
	public ActionForward sqbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RychService service = new RychService();
		String findForward="";
		String xmk = request.getParameter("xmk");
//		String pkValue="";
		String xh=request.getParameter("xh");
		String xn=request.getParameter("xn");		
		if ("sjyxbys".equalsIgnoreCase(xmk)) {
			request.setAttribute("rsShyj",service.serv_getSjyxbysInfo(xh, xn));
			request.setAttribute("brjl",DealString.toGBK(request.getParameter("brjl")));
			request.setAttribute("zysj",DealString.toGBK(request.getParameter("zysj")));
			request.setAttribute("zxqjhjqk",DealString.toGBK(request.getParameter("zxqjhjqk")));
			findForward = "sjyxbysSqb";
		}else if("xjyxbys".equalsIgnoreCase(xmk)){
			findForward = "xjyxbysSqb";
			request.setAttribute("cjList",service.serv_getCjList(xh));
		}
		request.setAttribute("rs", service.serv_getStuInfo(xh));
		request.setAttribute("xxmc",Base.xxmc);
		return mapping.findForward(findForward);
	}
	/**
	 * �����ƺ�������ѧ����ѯ
	 */
	public ActionForward rychXsSqJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RychService service = new RychService();
		String userName = request.getSession().getAttribute("userName").toString();
		ArrayList<String[]> rsShxs = new ArrayList<String[]>();
		rsShxs = service.serv_getShxsList_stu(userName);
		ArrayList<String[]> rstDxyxbys = new ArrayList<String[]>();
		rstDxyxbys = service.serv_getDxyxbysList_stu(userName);
		ArrayList<String[]> rsSjyxbys = new ArrayList<String[]>();
		rsSjyxbys = service.serv_getSjyxbysList_stu(userName);
		ArrayList<String[]> rsXjyxbys = new ArrayList<String[]>();
		rsXjyxbys = service.serv_getXjyxbysList_stu(userName);
		request.setAttribute("rsShxs",rsShxs);//����ѧ����������
		request.setAttribute("rsShxsNum",rsShxs.size());
		request.setAttribute("rstDxyxbys",rstDxyxbys);//���������ҵ����������
		request.setAttribute("rstDxyxbysNum",rstDxyxbys.size());
		request.setAttribute("rsSjyxbys",rsSjyxbys);//ʡ�������ҵ����������
		request.setAttribute("rsSjyxbysNum",rsSjyxbys.size());
		request.setAttribute("rsXjyxbys",rsXjyxbys);//У�������ҵ����������
		request.setAttribute("rsXjyxbysNum",rsXjyxbys.size());				
		request.setAttribute("numCout", rsSjyxbys.size()+rsXjyxbys.size()+rsShxs.size()+rstDxyxbys.size());
		return mapping.findForward("rychXsSqJg");
	}
	/**
	 * У�������ҵ�������ƺŲ�ѯ
	 */
	public ActionForward xjyxbysRychQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		RychService service = new RychService();
		HttpSession session = request.getSession();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String xn = request.getParameter("xn");
		unit.setNjXyZyBjList(request, myForm);
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
			userType = "isFdy";
			myForm.setXydm("");
		}
		if (xn == null) {
			xn = Base.getJxjsqxn();
			myForm.setXn(xn);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			XjyxbysRychModel model = new XjyxbysRychModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getXjyxbysSHTopTr();
			rs = service.serv_getXjyxbysCxList(userType, userName, model);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("realTable", "jhzy_xjyxbysb");
		request.setAttribute("tableName", "view_xjyxbysxx");
//		 ��дȨ�ж�
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "credit_result.do", userOnline
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");

		return mapping.findForward("xjyxbysRychQuery");
	}
	/**
	 * У�������ҵ�������ƺ���˲�ѯ
	 */
	public ActionForward xjyxbysRychShQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		RychService service = new RychService();
		HttpSession session = request.getSession();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String xn = request.getParameter("xn");

		unit.setNjXyZyBjList(request, myForm);
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
			userType = "isFdy";
			myForm.setXydm("");
		}
		if (xn == null) {
			xn = Base.getJxjsqxn();
			myForm.setXn(xn);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			XjyxbysRychModel model = new XjyxbysRychModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getXjyxbysSHTopTr();
			rs = service.serv_getXjyxbysList(userType, userName, model);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		return mapping.findForward("xjyxbysRychShQuery");
	}
	/**
	 * ����ѧ�������ƺŲ�ѯ
	 */
	public ActionForward shxsRychQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		RychService service = new RychService();
		HttpSession session = request.getSession();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String userOnline = request.getSession().getAttribute("userOnLine")
				.toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String xn = request.getParameter("xn");
		unit.setNjXyZyBjList(request, myForm);
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
			userType = "isFdy";
			myForm.setXydm("");
		}
		if (xn == null) {
			xn = Base.getJxjsqxn();
			myForm.setXn(xn);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			ShsRychModel model = new ShsRychModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getShsTopTr();
			rs = service.serv_getShsCxList(userType, userName, model);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("tableName","view_shsxx");
		request.setAttribute("realTable","jhzy_shsb");
		// ��дȨ�ж�
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "credit_result.do", userOnline
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");
		return mapping.findForward("shxsRychQuery");
	}
	/**
	 * ����ѧ�������ƺ���˲�ѯ
	 */
	public ActionForward shxsRychShQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		RychService service = new RychService();
		HttpSession session = request.getSession();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String xn = request.getParameter("xn");

		unit.setNjXyZyBjList(request, myForm);
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
			userType = "isFdy";
			myForm.setXydm("");
		}
		if (xn == null) {
			xn = Base.getJxjsqxn();
			myForm.setXn(xn);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			ShsRychModel model = new ShsRychModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getShsTopTr();
			rs = service.serv_getShxsList(userType, userName, model);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		return mapping.findForward("shxsRychShQuery");
	}
	/**
	 * ��������ѧ�������ƺŲ�ѯ
	 */
	public ActionForward dxyxxsRychQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		RychService service = new RychService();
		HttpSession session = request.getSession();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String xn = request.getParameter("xn");
		unit.setNjXyZyBjList(request, myForm);
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
			userType = "isFdy";
			myForm.setXydm("");
		}
		if (xn == null) {
			xn = Base.getJxjsqxn();
			myForm.setXn(xn);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			DxyxxsModel model = new DxyxxsModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getDxyxxsTopTr();
			rs = service.serv_getDxyxxsCxList(userType, userName, model);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("tableName","view_dxyxxsxx");
		request.setAttribute("realTable","jhzy_dxyxxsb");
		// ��дȨ�ж�
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "credit_result.do", userOnline
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");
		return mapping.findForward("dxyxxsRychQuery");
	}
	/**
	 * ��������ѧ�������ƺ���˲�ѯ
	 */
	public ActionForward dxyxxsRychShQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		RychService service = new RychService();
		HttpSession session = request.getSession();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String xn = request.getParameter("xn");

		unit.setNjXyZyBjList(request, myForm);
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
			userType = "isFdy";
			myForm.setXydm("");
		}
		if (xn == null) {
			xn = Base.getJxjsqxn();
			myForm.setXn(xn);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			DxyxxsModel model = new DxyxxsModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getDxyxxsTopTr();
			rs = service.serv_getDxyxxsList(userType, userName, model);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		return mapping.findForward("dxyxxsRychShQuery");
	}
}
