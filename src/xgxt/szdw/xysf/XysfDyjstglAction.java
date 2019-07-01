package xgxt.szdw.xysf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.dtjs.ntzy.pypx.NtzyPypxService;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

/**
 * Title: 学生工作管理系统 Description:信阳师范德育讲师团 Action Module:思政队伍 - 德育讲师团管理 Copyright:
 * Copyright (c) 2010 Company: zfsoft Author: sjf Version: 1.0 Time: 2010-9-1
 */
public class XysfDyjstglAction extends BasicExtendAction{
	
	/**
	 * 德育讲师维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dyjswh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String go = request.getParameter("go");
		String tableName = "xysf_dyjsb";
		String viewName = "view_xysf_dyjs";
		String doType = request.getParameter("doType");
		
		String user = getUserType(session);
		
		XysfDyjstglForm myForm = (XysfDyjstglForm)form;
		
		if("xy".equalsIgnoreCase(user)){
			myForm.setQueryequals_bmdm((String)session.getAttribute("userDep"));
		}
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tableName);
		}
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"pkValue","zgh","xm","xb","xl","zwmc","bmmc"};
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		setWriteAbleAndTitle(request, "szdydyjswh.do");
		
		request.setAttribute("xyList", DAO.getInstance().getBmListAll());
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("userType", user);
		return mapping.findForward("dyjswh");
	}
	
	/**
	 * 增加德育教师，数据源来自于fdyxxb
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addDyjs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zgh = request.getParameter("save_zgh");
		String doType = request.getParameter("doType");
		String tableName = "xysf_dyjsb";
		
		XysfDyjstglService service = new XysfDyjstglService();
		
		if("save".equalsIgnoreCase(doType)){
			if(service.checkExists(tableName, zgh)){
				request.setAttribute("message", "该数据已存在");
			}else{
				this.insertOperation(request, tableName);
			}
		}
		
		Map<String, String> map = service.getTeaInfo(zgh, null);
		map.put("csrq", DateUtils.getDayOfCn(map.get("csrq")));
		
		request.setAttribute("rs", map);
		return mapping.findForward("dyjsadd");
	}
	
	/**
	 * 德育讲师查看修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dyjsViewAndModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String operation = "view".equalsIgnoreCase(doType) ? "view" : "modi";
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, "xysf_dyjsb");
		}
		
		XysfDyjstglService service= new XysfDyjstglService();
		Map<String, String> jsInfo = service.getJsInfo(pkValue);
		
		request.setAttribute("operation", operation);
		request.setAttribute("rs", jsInfo);
		
		return mapping.findForward("dyjsglview");
	}
	
	public ActionForward jskcViewAndModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String operation = "view".equalsIgnoreCase(doType) ? "view" : "modi";
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, "xysf_dyjskcb");
		}
		
		XysfDyjstglService service= new XysfDyjstglService();
		Map<String, String> kcInfo = service.getOnekcInfo(pkValue);
		
		request.setAttribute("operation", operation);
		request.setAttribute("rs", kcInfo);
		
		return mapping.findForward("jskcview");
	}
	
	/**
	 * 讲师课程安排
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dyjskcap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 主键为职工号
		String pkValue = request.getParameter("pkValue");
		
		String doType = request.getParameter("doType");
		// =========================== 讲师基本信息 =======================
		XysfDyjstglService service = new XysfDyjstglService();
		Map<String, String> teaInfo = service.getTeaInfo(pkValue, null);
		request.setAttribute("rs", teaInfo);
		//============================ 讲师基本信息end ===================
		
		if("save".equalsIgnoreCase(doType)){
			XysfDyjstglForm myForm = (XysfDyjstglForm)form;
			DyjstglModel model = new DyjstglModel();
			BeanUtils.copyProperties(model, myForm);
			
			String message = service.saveKc(model) ? "操作成功" : "操作失败";
			
			request.setAttribute("message", message);
		}
		
		return mapping.findForward("jskcap");
	}
	
	public ActionForward jskcapwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		XysfDyjstglService service = new XysfDyjstglService();
		
		String go = request.getParameter("go");
		String tableName = "xysf_dyjskcb";
		String viewName = "view_xysf_dyjskc";
		String doType = request.getParameter("doType");
		XysfDyjstglForm myForm = (XysfDyjstglForm)form;
		
		if(service.checkExists("xysf_dyjsb", userName)){
			myForm.setQuerylike_zgh(userName);
			request.setAttribute("isJs", "yes");
		}else if("xy".equalsIgnoreCase(userType)){
			myForm.setQueryequals_bmdm(userDep);
		}
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tableName);
		}
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"pkValue","zgh","xm","sksj","skdd","zt","bmmc"};;
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		setWriteAbleAndTitle(request, "szdykcap.do");
		
		request.setAttribute("xyList", DAO.getInstance().getBmListAll());
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		return mapping.findForward("jskcapwh");
	}
	
	/**
	 * 通用导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dyjsExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] output = new String[]{"zgh","xm","xb","xl","zwmc","bmmc","bz"};
		expPageData(request, response, "xysf_dyjsb","view_xysf_dyjs", output);
		return mapping.findForward("");
	}
	
	/**
	 * 通用导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jskcExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] output = new String[]{"zgh","xm","xb","xl","zwmc","bmmc","sksj",
				"skdd","zt","hdgm","kcbz"};
		expPageData(request, response, "xysf_dyjskcb","view_xysf_dyjskc", output);
		return mapping.findForward("");
	}
	
	/**
	 * 辅导员科研信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kyxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		String doType = request.getParameter("doType");
		String tableName = "szdw_xysf_fdykyb";
		
		boolean isTea = Fdypd.isSzdw(userName);
		// 获取职工号
		String zgh = isTea ? userName : request.getParameter("save_zgh");

		if("save".equalsIgnoreCase(doType)){
			this.insertOperation(request, tableName);
		}
		
		if(StringUtils.isNotNull(zgh)){
			BasicExtendService service = new BasicExtendService();
			Map<String, String> map = service.getTeaInfo(zgh, null);
			request.setAttribute("rs", map);
		}
		
		setWriteAbleAndTitle(request, "kyxxwh.do");
		request.setAttribute("doType", doType);
		request.setAttribute("isTea", isTea);
		return mapping.findForward("fdykywh");
	}
	
	public ActionForward kyxxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		String userType = (String)session.getAttribute("userType");
		String userDep = (String)session.getAttribute("userDep");
		XysfDyjstglForm myForm = (XysfDyjstglForm)form;
		
		String tableName = "szdw_xysf_fdykyb";
		String viewName = "view_szdw_xysf_fdyky";
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		
		boolean isSzdw = Fdypd.isSzdw(userName);
		if(isSzdw){
			myForm.setQuerylike_zgh(userName);
		}else if("xy".equalsIgnoreCase(userType)){
			myForm.setQueryequals_bmdm(userDep);
		}
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tableName);
		}
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"pkValue","zgh","xm","bmmc","wzmc"};
			this.selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		}
		
		setWriteAbleAndTitle(request, "kyxxcx.do");
		request.setAttribute("bmList", DAO.getInstance().getBmListAll());
		request.setAttribute("isSzdw", isSzdw);
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		return mapping.findForward("fdykycx");
	}
	
	public ActionForward kyxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "szdw_xysf_fdykyb";
		String viewName = "view_szdw_xysf_fdyky";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		XysfDyjstglForm myForm = (XysfDyjstglForm)form;
		String title = "view".equalsIgnoreCase(doType) ? "科研信息单个查看" : "科研信息单个修改";
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, tableName);
			pkValue = myForm.getSave_zgh() + myForm.getSave_wzmc() + myForm.getSave_fbqk();
		}
		
		if(StringUtils.isNotNull(pkValue)){
			this.selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("title", title);
		return mapping.findForward("fdykyview");
	}
	
	public ActionForward qngczywh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		String tableName = "szdw_xysf_qngczyb";
		
		String xh = request.getParameter("save_xh");
		
		if("save".equalsIgnoreCase(doType)){
			this.insertOperation(request, tableName);
		}
		
		if(StringUtils.isNotNull(xh)){
			BasicExtendService service = new BasicExtendService();
			String[] output = new String[]{"xh","xm","xb","xymc","zymc","bjmc","nj"};
			request.setAttribute("rs", service.getStuInfo(xh, output));
		}
		setWriteAbleAndTitle(request, "gczywh.do");
		return mapping.findForward("qngczywh");
	}
	
	public ActionForward qngczycx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		String user = getUserType(session);
		String userDep = (String)session.getAttribute("userDep");
		XysfDyjstglForm myForm = (XysfDyjstglForm)form;
		
		String tableName = "szdw_xysf_qngczyb";
		String viewName = "view_szdw_xysf_qngczy";
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		
		
		if("stu".equalsIgnoreCase(user)){
			myForm.setQuerylike_xh(userName);
		}else if("xy".equalsIgnoreCase(user)){
			myForm.setQueryequals_xydm(userDep);
		}
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tableName);
		}
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"pkValue","xh","xm","xymc","zymc","bjmc","pxpc"};
			this.selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		setWriteAbleAndTitle(request, "gczycx.do");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("userType", user);
		return mapping.findForward("qngczycx");
	}
	
	public ActionForward qngczyView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "szdw_xysf_qngczyb";
		String viewName = "view_szdw_xysf_qngczy";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		String title = "view".equalsIgnoreCase(doType) ? "青年共产主义理论单个查看" : "青年共产主义理论单个修改";
		XysfDyjstglForm myForm = (XysfDyjstglForm)form;
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, tableName);
			pkValue = myForm.getSave_xh() + myForm.getSave_pxpc();
		}
		
		if(StringUtils.isNotNull(pkValue)){
			this.selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("title", title);
		return mapping.findForward("qngczyview");
	}
	
	public ActionForward dateExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = request.getParameter("realTable");
		String viewName = request.getParameter("tableName");
		
		String mk = request.getParameter("mk");
		
		if("fdyky".equalsIgnoreCase(mk)){
			String[] output = new String[]{"zgh","xm","xb","zwmc","xl","wzmc","fbqk","qkqs","qkjb","bmmc"};
			expPageData(request, response, tableName, viewName, output);
		}else if("qngczy".equalsIgnoreCase(mk)){
			String[] output = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","pxpc","xxpj","kscj","bz"};
			expPageData(request, response, tableName, viewName, output);
		}
		
		return mapping.findForward("");
	}
}
