package xgxt.szdw.zjlg;

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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;

public class XjchAction  extends DispatchAction {
	XjchService myService = new XjchService();
	
	public ActionForward sqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("sqManage");
	}
	
	public ActionForward shManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("shManage");
	}
	
	public ActionForward cxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("cxManage");
	}
	
	
	public ActionForward xjchSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xjchdm = request.getParameter("xjchdm");
		request.setAttribute("xjchdm", xjchdm);
		XjchService service = new XjchService();
		XjchForm myForm = (XjchForm) form;
		String userDep  = request.getSession().getAttribute("userDep")
				.toString();
		String userType  = request.getSession().getAttribute("userType")
				.toString();
		String doType = request.getParameter("doType");
		String zgh = request.getParameter("zgh");
		
		String xn = request.getParameter("xn");
		String act = DealString.toGBK(request.getParameter("act"));
		
		String bmdm = "";
		if(!userType.equalsIgnoreCase("admin")){
			bmdm = userDep;
		}
		myForm.setBmdm(bmdm);
		if (xn == null||xn.equalsIgnoreCase("")) {
			xn = Base.currXn;
		}
		myForm.setXn(xn);
		XjchModel model = new XjchModel();
		request.setAttribute("xn", xn);
		request.setAttribute("bmList", service.getBmlist());
		request.setAttribute("fdyList", service.getFdylist(bmdm));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("act", act);
		request.setAttribute("pk", DealString.toGBK(request.getParameter("pk")));
		request.setAttribute("xjchdm", xjchdm);
		
		if(xjchdm.equalsIgnoreCase("mffdy")){
			if ("save".equalsIgnoreCase(doType)) {
				BeanUtils.copyProperties(model, myForm);
				boolean done = service.xjchMffdySave(model,request);
				request.setAttribute("done", done);
			}
			HashMap<String,String> rs = service.serv_getFdyInfo(zgh);
			if(!userType.equalsIgnoreCase("admin")){
				rs.put("bmdm", userDep);
			}
			request.setAttribute("rs", rs);
			request.setAttribute("rsxjch", service.serv_getMffdyInfo(request.getParameter("pk")));
			return mapping.findForward("mffdySq");
		}else if(xjchdm.matches("yxbzr")){
			if ("save".equalsIgnoreCase(doType)) {
				BeanUtils.copyProperties(model, myForm);
				boolean done = service.xjchYxbzrSave(model,request);
				request.setAttribute("done", done);
			}
			HashMap<String,String> rs = service.serv_getFdyInfo(zgh);
			if(!userType.equalsIgnoreCase("admin")){
				rs.put("bmdm", userDep);
			}
			request.setAttribute("rs", rs);
			request.setAttribute("rsxjch", service.serv_getYxbzrInfo(request.getParameter("pk")));
			return mapping.findForward("yxbzrSq");
		}
		return null;
	}
	
	
	public ActionForward xjchsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	XjchService service = new XjchService();
	HttpSession session = request.getSession();
	XjchForm myForm = (XjchForm) form;
	String userType = (String) session.getAttribute("userType");
	String userDep = (String) session.getAttribute("userDep");
	ArrayList<String[]> rs = new ArrayList<String[]>();
	List<HashMap<String, String>> topTr = null;
	String xn = request.getParameter("xn");
	String xjchdm = request.getParameter("xjchdm");
	request.setAttribute("xjchdm", xjchdm);
	if(xjchdm.equalsIgnoreCase("mffdy")){
		request.setAttribute("xjchmc", "模范辅导员");
	}else{
		request.setAttribute("xjchmc", "优秀班主任");
	}
	if (xn == null) {
		xn = Base.currXn;
		myForm.setXn(xn);
	}
	if (!("admin").equalsIgnoreCase(userType)) {
		myForm.setBmdm(userDep);
	}
	if ("go".equalsIgnoreCase(request.getParameter("go"))) {
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		XjchModel model = new XjchModel();
		BeanUtils.copyProperties(model, myForm);
		topTr = service.getXjchshTopTr(xjchdm);
		rs = service.getXjchshList(model,xjchdm);
		FormModleCommon.formToGBK(model);
	}
	request.setAttribute("bmList", service.getBmlist());
	request.setAttribute("xnList", Base.getXnndList());
	request.setAttribute("rs", rs);
	request.setAttribute("topTr", topTr);
	request.setAttribute("rsNum", rs.size());
	return mapping.findForward("xjchSh");
	}
	
	/**
	 * 先进称号单个审核
	 */
	public ActionForward xjchChek(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		XjchService service = new XjchService();
		String pkValue = request.getParameter("pkValue");
		String xjchdm = request.getParameter("xjchdm");
		HashMap<String, String> map = new HashMap<String, String>();
		String findForward = "";
		String userType = (String) session.getAttribute("userType");
		String doType = request.getParameter("doType");
		XjchForm myForm = (XjchForm) form;
		XjchModel myModel = new XjchModel();
        if("save".equalsIgnoreCase(doType)){//保存审核结果
        	boolean done=false;
        	BeanUtils.copyProperties(myModel, myForm); 	 
        	 done=service.serv_XjchChk(pkValue, xjchdm,myModel,request);
        	 request.setAttribute("done",done);
        }
		if(xjchdm.equalsIgnoreCase("mffdy")){
			map = service.serv_getMffdyInfo(pkValue);
			findForward = "mffdyChek";
		}else if(xjchdm.matches("yxbzr")){
			map = service.serv_getYxbzrInfo(pkValue);
			findForward = "yxbzrChek";
		}
		request.setAttribute("act", doType);
		request.setAttribute("rs", map);
		request.setAttribute("chkList",service.serv_getChkList());
		request.setAttribute("userType",userType);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("xjchdm",xjchdm);
		return mapping.findForward(findForward);
	}
	
	/**
	 * 先进称号批量审核
	 */
	public ActionForward xjchCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjchService service = new XjchService();
		String check = request.getParameter("check");
		String pkVStr = request.getParameter("pkVStr");
		String xjchdm = request.getParameter("xjchdm");
		service.serv_XjchCk(check, xjchdm, pkVStr);
		return xjchsh(mapping, form, request, response);
	}
	
	/**
	 * 先进称号查询
	 */
	public ActionForward xjchQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XjchService service = new XjchService();
		HttpSession session = request.getSession();
		XjchForm myForm = (XjchForm) form;
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String xn = request.getParameter("xn");
		String xjchdm = request.getParameter("xjchdm");
		request.setAttribute("xjchdm", xjchdm);
		if(xjchdm.equalsIgnoreCase("mffdy")){
			request.setAttribute("xjchmc", "模范辅导员");
		}else{
			request.setAttribute("xjchmc", "优秀班主任");
		}
		if (xn == null) {
			xn = Base.currXn;
			myForm.setXn(xn);
		}
		if (!("admin").equalsIgnoreCase(userType)) {
			myForm.setBmdm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			XjchModel model = new XjchModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getXjchshTopTr(xjchdm);
			rs = service.getXjchshList(model,xjchdm);
			FormModleCommon.formToGBK(model);
		}
		request.setAttribute("bmList", service.getBmlist());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("chkList",service.serv_getChkList());
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		return mapping.findForward("xjchJg");
	}
//	
//	public ActionForward xjchDel(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		XjchService service = new XjchService();
//		HttpSession session = request.getSession();
//		String userType = (String) session.getAttribute("userType");
//		String pkVStr = request.getParameter("pkVStr");
//		String xjchdm = request.getParameter("xjchdm");
//		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
//			userType = "isFdy";
//		}
//		boolean result = service.serv_xjchDel(userType, xjchdm, pkVStr);
//		request.setAttribute("result", result);
//		return xjchQuery(mapping, form, request, response);
//	}
	
	public ActionForward xxFksqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("xxFksqManage");
	}
	
	public ActionForward xxFkshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("xxFkshManage");
	}
	
	public ActionForward xxFkcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("xxFkcxManage");
	}
	
	
	public ActionForward xxFkSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xxfkdm = request.getParameter("xxfkdm");
		request.setAttribute("xxfkdm", xxfkdm);
		XjchService service = new XjchService();
		XjchForm myForm = (XjchForm) form;
		String userDep  = request.getSession().getAttribute("userDep")
				.toString();
		String userType  = request.getSession().getAttribute("userType")
				.toString();
		String userName  = request.getSession().getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String zgh = request.getParameter("zgh");
		
		String xn = request.getParameter("xn");
		String act = DealString.toGBK(request.getParameter("act"));
		
		String bmdm = "";
		if (Fdypd.isSzdw(userName)) {
			zgh = userName;
			bmdm = userDep;
			request.setAttribute("isSzdw", "on");
		} else if ("xy".equalsIgnoreCase(userType)) {
			bmdm = userDep;
		}
		myForm.setBmdm(bmdm);
		XjchModel model = new XjchModel();
		request.setAttribute("xn", xn);
		request.setAttribute("bmList", service.getBmlist());
		request.setAttribute("fdyList", service.getFdylist(bmdm));
		request.setAttribute("act", act);
		request.setAttribute("pk", DealString.toGBK(request.getParameter("pk")));
		if (xxfkdm.equalsIgnoreCase("pxxxfk")) {

			HashMap<String, String> rs = service.serv_getFdyInfo(zgh);
			if (!userType.equalsIgnoreCase("admin")) {
				rs.put("bmdm", userDep);
			}
			request.setAttribute("rs", rs);
			request.setAttribute("xxfkrs", service.serv_getPxxxfkInfo(request
					.getParameter("pk")));

			if ("save".equalsIgnoreCase(doType)) {
				BeanUtils.copyProperties(model, myForm);
				boolean done = service.xjchPxxxfkSave(model, request);
				request.setAttribute("done", done);
				
				String pkValue =  model.getZgh()+model.getPxxm()+model.getKssj();
				HashMap<String, String> map = service.serv_getPxxxfkInfo(pkValue);
				request.setAttribute("xxfkrs", map);
			}	
			
			return mapping.findForward("pxxxfkSq");
			
		} else if (xxfkdm.matches("gzxxfk")) {

			HashMap<String, String> rs = service.serv_getFdyInfo(zgh);
			if (!userType.equalsIgnoreCase("admin")) {
				rs.put("bmdm", userDep);
			}
			request.setAttribute("rs", rs);
			request.setAttribute("xxfkrs", service.serv_getGzxxfkInfo(request
					.getParameter("pk")));

			if ("save".equalsIgnoreCase(doType)) {
				BeanUtils.copyProperties(model, myForm);
				boolean done = service.xjchGzxxfkSave(model, request);
				request.setAttribute("done", done);
				
				String pkValue =  model.getZgh()+model.getXxzt();
				HashMap<String, String> map = service.serv_getPxxxfkInfo(pkValue);
				request.setAttribute("xxfkrs", map);
			}
			
			return mapping.findForward("gzxxfkSq");
		}
		return null;
	}
	
	
	public ActionForward xxFkSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	XjchService service = new XjchService();
	HttpSession session = request.getSession();
	XjchForm myForm = (XjchForm) form;
	String userType = (String) session.getAttribute("userType");
	String userDep = (String) session.getAttribute("userDep");
	ArrayList<String[]> rs = new ArrayList<String[]>();
	List<HashMap<String, String>> topTr = null;
	String xn = request.getParameter("xn");
	String xxfkdm = request.getParameter("xxfkdm");
	request.setAttribute("xxfkdm", xxfkdm);
	if(xxfkdm.equalsIgnoreCase("pxxxfk")){
		request.setAttribute("xxfkmc", "培训信息");
	}else{
		request.setAttribute("xxfkmc", "工作信息");
	}
	if (xn == null) {
		xn = Base.currXn;
		myForm.setXn(xn);
	}
	if (!("admin").equalsIgnoreCase(userType)) {
		myForm.setBmdm(userDep);
	}
	if ("go".equalsIgnoreCase(request.getParameter("go"))) {
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		XjchModel model = new XjchModel();
		BeanUtils.copyProperties(model, myForm);
		topTr = service.getXxfkshTopTr(xxfkdm);
		rs = service.getXxfkshList(model,xxfkdm);
		FormModleCommon.formToGBK(model);
	}
	request.setAttribute("bmList", service.getBmlist());
	request.setAttribute("xnList", Base.getXnndList());
	request.setAttribute("rs", rs);
	request.setAttribute("topTr", topTr);
	request.setAttribute("rsNum", rs.size());
	return mapping.findForward("xxFkSh");
	}
	
	/**
	 * 反馈信息单个审核
	 */
	public ActionForward xxFkChek(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		XjchService service = new XjchService();
		
		String pkValue = request.getParameter("pkValue");
		String xxfkdm = request.getParameter("xxfkdm");
		HashMap<String, String> map = new HashMap<String, String>();
		String findForward = "";
		String userType = (String) session.getAttribute("userType");
		String doType = request.getParameter("doType");
		XjchForm myForm = (XjchForm) form;
		XjchModel myModel = new XjchModel();
        if("save".equalsIgnoreCase(doType)){//保存审核结果
        	boolean done=false;
        	BeanUtils.copyProperties(myModel, myForm); 	 
        	 done=service.serv_XxfkChk(pkValue,xxfkdm,myModel,request);
        	 request.setAttribute("done",done);
        }
		if(xxfkdm.equalsIgnoreCase("pxxxfk")){
			map = service.serv_getPxxxfkInfo(pkValue);
			findForward = "pxxxfkChek";
		}else if(xxfkdm.matches("gzxxfk")){
			map = service.serv_getGzxxfkInfo(pkValue);
			findForward = "gzxxfkChek";
		}
		request.setAttribute("act", doType);
		HashMap<String,String> rs = service.serv_getFdyInfo(map.get("zgh"));
		request.setAttribute("rs", rs);
		request.setAttribute("xxfkrs", map);
		request.setAttribute("chkList",service.serv_getChkList());
		request.setAttribute("userType",userType);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("xxfkdm",xxfkdm);
		return mapping.findForward(findForward);
	}
	
	/**
	 * 反馈信息批量审核
	 */
	public ActionForward xxFkCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjchService service = new XjchService();
		String check = request.getParameter("check");
		String pkVStr = request.getParameter("pkVStr");
		String xxfkdm = request.getParameter("xxfkdm");
		service.serv_XxfkCk(check, xxfkdm, pkVStr);
		return xxFkSh(mapping, form, request, response);
	}
	
	/**
	 * 反馈信息查询
	 */
	public ActionForward xxFkQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XjchService service = new XjchService();
		HttpSession session = request.getSession();
		XjchForm myForm = (XjchForm) form;
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String xn = request.getParameter("xn");
		String xxfkdm = request.getParameter("xxfkdm");
		request.setAttribute("xxfkdm", xxfkdm);
		if(xxfkdm.equalsIgnoreCase("pxxxfk")){
			request.setAttribute("xxfkmc", "模范辅导员");
		}else{
			request.setAttribute("xxfkmc", "优秀班主任");
		}
		if (xn == null) {
			xn = Base.currXn;
			myForm.setXn(xn);
		}
		if (!("admin").equalsIgnoreCase(userType)) {
			myForm.setBmdm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			XjchModel model = new XjchModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getXxfkshTopTr(xxfkdm);
			rs = service.getXxfkshList(model,xxfkdm);
			FormModleCommon.formToGBK(model);
		}
		request.setAttribute("bmList", service.getBmlist());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("chkList",service.serv_getChkList());
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		return mapping.findForward("xxfkJg");
	}
}
