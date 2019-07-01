package xgxt.xtwh.comm.splc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

public class XtwhShlcAjax extends BasicAction {

	/**
	 * 审批流程管理模块
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward spgwLoad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String pkValue = request.getParameter("pkValue");
		
		XtwhShlcService service = new XtwhShlcService();

		XtwhShlcForm model = new XtwhShlcForm();
		model.setPkValue(pkValue);
		List<HashMap<String, String>> list = service.getSpgwByShl(model);
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));
		
		return null;
	}
	
	/**
	 * 用户组信息加载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadYhzInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		XtwhShlcService service = new XtwhShlcService();

		List<HashMap<String, String>> list = service.getYhzInfo();
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));
		
		return null;
	}

	/**
	 * 用户组信息加载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadKxyhInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		XtwhShlcService service = new XtwhShlcService();
		XtwhShlcForm myForm=(XtwhShlcForm)form;
		
		String spgw=request.getParameter("spgw");
		String zdm=request.getParameter("zdm");
		String page=request.getParameter("kxyhPage");
		String strArr=request.getParameter("strArr");
		if(!Base.isNull(strArr)){	
			strArr=service.unicode2Gbk(strArr);
		}
		String[] yhm=null;
		if(!Base.isNull(strArr)){
			yhm=strArr.split("!!@@!!");
		}
		
		myForm.setSpgw(spgw);
		myForm.setZdm(zdm);
		myForm.setPage(page);
		myForm.setYhm(yhm);
		
		List<HashMap<String, String>> list = service.getKxyhInfo(myForm);
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));
		
		return null;
	}

	/**
	 * 用户组信息加载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadYxyhInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		XtwhShlcService service = new XtwhShlcService();
		XtwhShlcForm myForm=(XtwhShlcForm)form;
		
		String spgw=request.getParameter("spgw");
		String zdm=request.getParameter("zdm");
		String page=request.getParameter("yxyhPage");
		String strArr=request.getParameter("strArr");
		if(!Base.isNull(strArr)){	
			strArr=service.unicode2Gbk(strArr);
		}
		
		String[] yhm=null;
		if(!Base.isNull(strArr)){
			yhm=strArr.split("!!@@!!");
		}
		
		myForm.setSpgw(spgw);
		myForm.setZdm(zdm);
		myForm.setPage(page);
		myForm.setYhm(yhm);
		List<HashMap<String, String>> list = service.getYxyhInfo(myForm);
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));
		
		return null;
	}
	
	/**
	 * 用户组信息加载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadYxyhByDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		XtwhShlcService service = new XtwhShlcService();
		XtwhShlcForm myForm=(XtwhShlcForm)form;
		
		String spgw=request.getParameter("spgw");
		String zdm=request.getParameter("zdm");
		String page=request.getParameter("yxyhPage");
		String strArr=request.getParameter("strArr");
		if(!Base.isNull(strArr)){	
			strArr=service.unicode2Gbk(strArr);
		}
		
		String[] yhm=null;
		if(!Base.isNull(strArr)){
			yhm=strArr.split("!!@@!!");
		}
		
		myForm.setSpgw(spgw);
		myForm.setZdm(zdm);
		myForm.setPage(page);
		myForm.setYhm(yhm);
		List<HashMap<String, String>> list = service.getYxyhByDel(myForm);
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));
		
		return null;
	}

	/**
	 * 用户组信息加载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadKxyhByDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		XtwhShlcService service = new XtwhShlcService();
		XtwhShlcForm myForm=(XtwhShlcForm)form;
		
		String spgw=request.getParameter("spgw");
		String zdm=request.getParameter("zdm");
		String page=request.getParameter("yxyhPage");
		String strArr=request.getParameter("strArr");
		if(!Base.isNull(strArr)){	
			strArr=service.unicode2Gbk(strArr);
		}
		String searchUser=request.getParameter("searchUserName");
		
		String[] yhm=null;
		if(!Base.isNull(strArr)){
			yhm=strArr.split("!!@@!!");
		}
		
		myForm.setUserName(searchUser);
		myForm.setSpgw(spgw);
		myForm.setZdm(zdm);
		myForm.setPage(page);
		myForm.setYhm(yhm);
		List<HashMap<String, String>> list = service.getKxyhByDel(myForm);
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));
		
		return null;
	}
	
	/**
	 * 用户组信息加载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadAllYxyhInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		XtwhShlcService service = new XtwhShlcService();
		XtwhShlcForm myForm=(XtwhShlcForm)form;
		
		String spgw=request.getParameter("spgw");
		String zdm=request.getParameter("zdm");
		String page=request.getParameter("yxyhPage");
		String strArr=request.getParameter("strArr");
		if(!Base.isNull(strArr)){	
			strArr=service.unicode2Gbk(strArr);
		}
		
		String[] yhm=null;
		if(!Base.isNull(strArr)){
			yhm=strArr.split("!!@@!!");
		}
		
		myForm.setSpgw(spgw);
		myForm.setZdm(zdm);
		myForm.setPage(page);
		myForm.setYhm(yhm);

		// ========== 个性化 用户授权 begin ============
		List<HashMap<String, String>> list = null;
		String yhszlx = myForm.getYhszlx();
		if("rcxwwh".equals(yhszlx)){
			// 日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别
			list = service.loadAllYxyhInfoRcxwwh(myForm);
		}else{
			// 系统维护-审批流程维护-审批流程
			list = service.loadAllYxyhInfo(myForm);
		}
		// ========== 个性化 用户授权 end ============
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));
		
		return null;
	}
	
	/**
	 * 用户组信息加载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateLcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		XtwhShlcService service = new XtwhShlcService();

		service.updateLcsz();
		
		response.setContentType("text/html;charset=gbk");
		
		return null;
	}
	
	/**
	 * 特殊岗位信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsgwLoad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XtwhShlcService service = new XtwhShlcService();
		
		XtwhShlcForm model = new XtwhShlcForm();
		List<HashMap<String, String>> list = service.getTsgwByShl(model);
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));
		
		return null;
	}
	
	/**
	 * 创建通用岗位Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createTygwDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XtwhShlcService service = new XtwhShlcService();
		XtwhShlcForm model = new XtwhShlcForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		//service.createTygwDiv(model, user, response);
		// ==================构建前台页面 end================
		String html=service.createTygwDivStr(model, user, response);
		request.setAttribute("html", html);
		return mapping.findForward("tygw");
	}
	
	/**
	 * 创建特殊岗位Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createTsgwDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XtwhShlcService service = new XtwhShlcService();
		XtwhShlcForm model = new XtwhShlcForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		//service.createTsgwDiv(model, user, response);
		// ==================构建前台页面 end================
		String html=service.createTsgwDivStr(model, user, response);
		request.setAttribute("html", html);
		request.setAttribute("gwlx", request.getParameter("gwlx"));
		request.setAttribute("spgw", request.getParameter("spgw"));

		return mapping.findForward("tsgw");
	}
}
