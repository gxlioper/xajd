package xsgzgl.xszz.jhzy.gjzxj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.xszz.XszzTyForm;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmInit;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.xszz.jhzy.XszzJhzyPrint;
import xsgzgl.xszz.jhzy.cssz.XszzCsszActionForm;
import xsgzgl.xszz.jhzy.jtqkdz.XszzJtqkdzActionForm;
import xsgzgl.xszz.jhzy.jtqkdz.XszzJtqkdzService;
import xsgzgl.xszz.jhzy.knsrd.KnsrdService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 学生资助_国家助学金_金华职业_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 偉大的駱
 * @version 1.0
 */

public class GjzxjAction extends BasicAction {

	// ===============頁面跳轉 begin=====================

	/**
	 * 国家助学金申请【查询】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjsqSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSqSearch(rForm, myForm, user, request);
		// =================== end ===================

		//===============导出表设置===================
		request.setAttribute("tableName", "view_xg_xszz_jhzy_gjzxjsqb");
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zxjsqSearch");
	}

	/**
	 * 国家助学金申请【增加】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjsqInsert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSqInsert(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zxjsqInsert");
	}

	/**
	 * 国家助学金申请【修改】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSqUpdate(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zxjsqUpdate");
	}

	/**
	 * 用户权限确定
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhqxDecision(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);// 用户对象

		if ("jd".equalsIgnoreCase(user.getUserStatus())) {
			HttpSession session = request.getSession();
			Boolean sfjryx = session.getAttribute("sfjryx") != null ? Boolean
					.valueOf(session.getAttribute("sfjryx").toString()) : false;
			String userType = (String) session.getAttribute("userType");
			boolean isFdy = (Boolean) session.getAttribute("isFdy");
			boolean isBzr = (Boolean) session.getAttribute("isBzr");

			request.setAttribute("userType", userType);
			request.setAttribute("isFdy", isFdy);
			request.setAttribute("isBzr", isBzr);
			return mapping.findForward("yhqxDecision");
		} else {
			return zxjshSearch(mapping, form, request, response);
		}
	}

	/**
	 * 国家助学金审核【查询】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjshSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initShSearch(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zxjshSearch");
	}

	/**
	 * 国家助学金审核【修改】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjshUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initShUpdate(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zxjshUpdate");
	}

	/**
	 * 国家助学金结果【查询】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjjgSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();
		SearchModel searchModel=new SearchModel();
		XszzCsszActionForm csszForm = XszzCsszActionForm.getCsszForm();
		searchModel.setSearch_tj_xn(new String[]{csszForm.getXn()});
		request.setAttribute("searchTj", searchModel);
		// ============= 初始化各变量的值 ============
		// 初始化
		init.initJgSearch(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		//===============导出表设置===================
		request.setAttribute("tableName", "view_xg_xszz_jhzy_gjzxjb");

		return mapping.findForward("zxjjgSearch");
	}

	/**
	 * 国家助学金结果【详细】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjjgDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initJgDetail(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zxjjgDetail");
	}

	// ===============頁面跳轉 end=====================

	// ===============过奖助学金申请begin=====================

	/**
	 * 查询资助学年国家助学金申请记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchZxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjModel model = new GjzxjModel();
		GjzxjInit init = new GjzxjInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initSqSearch(rForm, myForm, user, request);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getZxjsqTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getZxjsqList(myForm, model,
				user);
		// 构建结果集
		String spHtml = "";
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 保存国家助学金申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveGjzxjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjService service = new GjzxjService();
		GjzxjModel model = new GjzxjModel();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 执行保存操作 ============
		boolean flag = service.saveGjzxjSq(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 删除国家助学金申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteGjzxjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjService service = new GjzxjService();
		GjzxjModel model = new GjzxjModel();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ==================执行删除操作====================

		boolean flag = service.deleteGjzxjSq(model, user);

		String message = flag ? "删除成功" : "删除失败，请联系相关人员";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		// ==================执行删除操作 end================

		return null;
	}

	/**
	 * 创建流程跟踪HTML
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createLcgzHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		GjzxjModel model = new GjzxjModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initSqInsert(rForm, myForm, user, request);
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createLcgzHtml(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}

	// ===============国家助学金申请end=====================

	// ===============国家助学金审核begin=====================

	/**
	 * 查询资助学年的国家助学金审核记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchZxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjModel model = new GjzxjModel();
		GjzxjInit init = new GjzxjInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initShSearch(rForm, myForm, user, request);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getZxjshTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getZxjshList(myForm, model,
				user);
		// 构建结果集
		String spHtml = "";
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 保存国家助学金审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveGjzxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjService service = new GjzxjService();
		GjzxjModel model = new GjzxjModel();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.saveGjzxjSh(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===============国家助学金审核end=====================

	// ===============国家助学金结果begin=====================

	/**
	 * 查询资助学年的国家助学金结果记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchZxjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjModel model = new GjzxjModel();
		GjzxjInit init = new GjzxjInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initJgSearch(rForm, myForm, user, request);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getZxjjgTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getZxjjgList(myForm, model,
				user);
		// 构建结果集
		String spHtml = "";
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	// ===============国家助学金结果end=====================
	
	/**
	 * 家庭情况调查信息打印表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		KnsrdService knsService = new KnsrdService();
		GjzxjService service = new GjzxjService();
		XszzJtqkdzService jtqkdcSerivce = new XszzJtqkdzService();
		
		GjzxjForm myForm = (GjzxjForm) form;
		XszzJtqkdzActionForm jtqkdcModel=new XszzJtqkdzActionForm();
		
		XszzTyForm model = new XszzTyForm();
		
		XszzJhzyPrint jhzyPrint=new XszzJhzyPrint();
		// user对象
		User user=getUser(request);
		// 用户名
 		String xh=request.getParameter("xh");
 		String xn=request.getParameter("xn");
 		jtqkdcModel.setPkStr(xh+xn);
 		jtqkdcModel.setXh(xh);
 		model.setXh(xh);
 		
		HashMap<String,String>xsxxInfo=new HashMap<String,String>();
		
		List<HashMap<String, String>> jtcyList = jtqkdcSerivce.cxJtcyxxList(jtqkdcModel);
		//学生基本信息
		jhzyPrint.getStuInfo(model,xsxxInfo);
		
		HashMap<String, String> jtqkdcInfo = jtqkdcSerivce.ckJtqkdzxx(jtqkdcModel);
		
		xsxxInfo.putAll(jtqkdcInfo);
		
		HashMap<String,String>knsInfo=knsService.getKnsrdInfo(xh,xn);
		xsxxInfo.putAll(knsInfo);
		// 国家助学金
		xsxxInfo.putAll(service.getGjzxjInfo(xh, xn));

		xsxxInfo.putAll(jhzyPrint.getSfzhArr(xsxxInfo.get("sfzh")));
		
		request.setAttribute("rs", xsxxInfo);
		request.setAttribute("cyList", jtcyList);
		return mapping.findForward("gjzxjb");
	}
}
