package xsgzgl.xszz.jhzy.gjlzjxj;

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

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmInit;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.xszz.jhzy.XszzJhzyPrint;
import xsgzgl.xszz.jhzy.cssz.XszzCsszActionForm;
import xsgzgl.xszz.jhzy.gjzxj.GjzxjForm;
import xsgzgl.xszz.jhzy.gjzxj.GjzxjService;
import xsgzgl.xszz.jhzy.jtqkdz.XszzJtqkdzActionForm;
import xsgzgl.xszz.jhzy.jtqkdz.XszzJtqkdzService;
import xsgzgl.xszz.jhzy.knsrd.KnsrdService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 学生资助_国家励志奖学金_金华职业_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */

public class GjlzjxjAction extends BasicAction {

	// ===============頁面跳轉 begin=====================

	/**
	 * 国家励志奖学金申请【查询】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxGjlzjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();
		XszzCsszActionForm csszForm = XszzCsszActionForm.getCsszForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSqSearch(rForm, myForm, user, request);
		// =================== end ===================
		
		//===============导出表设置===================
		request.setAttribute("tableName", "view_xg_xszz_jhzy_gjlzjxjsqb");
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		request.setAttribute("czqx", csszForm.getGjlzjxjsqzt());
		return mapping.findForward("cxGjlzjxjsq");
	}
	
	/**
	 * 查询资助学年的困难生申请记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchGjlzjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjModel model = new GjlzjxjModel();
		GjlzjxjInit init = new GjlzjxjInit();

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
		List<HashMap<String, String>> topTr = service.getGjlzjxjTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getGjlzjxjsqList(myForm, model,
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
	 * 删除国家励志奖学金申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteGjlzjxjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjModel model = new GjlzjxjModel();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ==================构建前台页面====================

		boolean flag = service.deleteGjlzjxjSq(model, user);

		String message = flag ? "删除成功" : "删除失败，请联系相关人员";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 国家励志奖学金申请【增加】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsqInsert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		myForm.setXn(XszzCsszActionForm.getCsszForm().getXn());
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSqInsert(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		GjlzjxjModel model = new GjlzjxjModel();
		BeanUtils.copyProperties(model, myForm);
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			boolean flag = service.saveGjlzjxjSq(model, user);
			String message = flag ? "保存成功" : "保存失败，请联系相关人员";
			request.setAttribute("message", message);
		}
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		if (StringUtils.isNotNull( myForm.getXh())) {
			request.setAttribute("bjzrs", service.cxBjrs(myForm.getXh()));
			XszzCsszActionForm cform = new XszzCsszActionForm();
			request.setAttribute("isKns", cform.getIsKns(myForm.getXh(), myForm.getXn()));
		}
		
		
		request.setAttribute("zzxn", XszzCsszActionForm.getCsszForm().getXn());
		return mapping.findForward("gjlzjxjsqInsert");
	}

	/**
	 * 国家励志奖学金申请【修改】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSqUpdate(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		GjlzjxjModel model = new GjlzjxjModel();
		BeanUtils.copyProperties(model, myForm);
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			boolean flag = service.saveGjlzjxjSq(model, user);
			String message = flag ? "保存成功" : "保存失败，请联系相关人员";
			request.setAttribute("message", message);
		}
		if (StringUtils.isNotNull( rForm.getRs().get("xh"))) {
			request.setAttribute("bjzrs", service.cxBjrs(rForm.getRs().get("xh")));
			XszzCsszActionForm cform = new XszzCsszActionForm();
			request.setAttribute("isKns", cform.getIsKns(rForm.getRs().get("xh"), rForm.getRs().get("xn")));
		}
		request.setAttribute("pkValue", request.getParameter("pkValue"));
		return mapping.findForward("gjlzjxjsqUpdate");
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
			return cxGjlzjxjsh(mapping, form, request, response);
		}
	}

	/**
	 * 国家励志奖学金审核【查询】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxGjlzjxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initShSearch(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("gjlzjxjshSearch");
	}
	
	/**
	 * 国家励志奖学金审核【修改】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjshUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjModel model = new GjlzjxjModel();
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initShUpdate(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			BeanUtils.copyProperties(model, myForm);
			model.setPkValue(new String[]{myForm.getPkValue()});
			boolean flag = service.saveGjlzjxjSh(model, user);
			String message = flag ? "保存成功" : "保存失败，请联系相关人员";
			request.setAttribute("message", message);
		}
		request.setAttribute("pkValue", request.getParameter("pkValue"));
		return mapping.findForward("gjlzjxjshUpdate");
	}

	

	// ===============頁面跳轉 end=====================

	// ===============国家励志奖学金申请begin=====================

	/**
	 * 查询资助学年的国家励志奖学金审核记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchGjlzjxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjModel model = new GjlzjxjModel();
		GjlzjxjInit init = new GjlzjxjInit();

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
		List<HashMap<String, String>> topTr = service.getGjlzjxjTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getGjlzjxjshList(myForm, model,
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
	 * 保存国家励志奖学金申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveGjlzjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjModel model = new GjlzjxjModel();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.saveGjlzjxjSq(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 查看国家励志奖学金信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  gjlzjxjsqView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();
		
		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSqUpdate(rForm, myForm, user, request);
		// =================== end ===================
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		GjlzjxjModel model = new GjlzjxjModel();
		BeanUtils.copyProperties(model, myForm);
		
		return mapping.findForward("gjlzjxjsqView");
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

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		GjlzjxjModel model = new GjlzjxjModel();

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

	// ===============国家励志奖学金申请end=====================

	// ===============国家励志奖学金审核begin=====================

	/**
	 * 保存国家励志奖学金审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveGjlzjxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjModel model = new GjlzjxjModel();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.saveGjlzjxjSh(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===============国家励志奖学金审核end=====================

	// ===============国家励志奖学金结果begin=====================

	/**
	 * 查询资助学年的国家励志奖学金审核记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchGjlzjxjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjModel model = new GjlzjxjModel();
		GjlzjxjInit init = new GjlzjxjInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initjgSearch(rForm, myForm, user, request);

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
		List<HashMap<String, String>> topTr = service.getGjlzjxjjgTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getGjlzjxjjgList(myForm, model,
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
	 * 国家励志奖学金【查询】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxGjlzjxjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();
		request.setAttribute("patn", "xszz_jhzy_gjlzjxjCx.do");
		SearchModel searchModel=new SearchModel();
		XszzCsszActionForm csszForm = XszzCsszActionForm.getCsszForm();
		searchModel.setSearch_tj_xn(new String[]{csszForm.getXn()});
		request.setAttribute("searchTj", searchModel);
		// ============= 初始化各变量的值 ============
		// 初始化
		init.initjgSearch(rForm, myForm, user, request);
		// =================== end ===================
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_xszz_jhzy_gjlzjxjb");

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("cxGjlzjxjjg");
	}

	/**
	 * 国家励志奖学金结果【详细】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjjgDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initJgDetail(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("gjlzjxjjgDetail");
	}

	/**
	 * 国家励志奖学金打印表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		KnsrdService knsService = new KnsrdService();
		GjlzjxjService service = new GjlzjxjService();
		XszzJtqkdzService jtqkdcSerivce = new XszzJtqkdzService();
		
		GjlzjxjForm myForm = (GjlzjxjForm) form;
		XszzJtqkdzActionForm jtqkdcModel=new XszzJtqkdzActionForm();
		
		XszzTyForm model = new XszzTyForm();
		
		XszzJhzyPrint jhzyPrint=new XszzJhzyPrint();
		// user对象
		User user=getUser(request);
		// 用户名
 		String xh=request.getParameter("xh");
 		String xn=request.getParameter("xn");
 		jtqkdcModel.setPkStr(xh+xn);
 		model.setXh(xh);
 		
		HashMap<String,String>xsxxInfo=new HashMap<String,String>();
		
		List<HashMap<String, String>> jtcyList = jtqkdcSerivce.cxJtcyxxList(jtqkdcModel);
		//学生基本信息
		jhzyPrint.getStuInfo(model,xsxxInfo);
		
		HashMap<String,String>knsInfo=knsService.getKnsrdInfo(xh,xn);
		xsxxInfo.putAll(knsInfo);
		
		HashMap<String, String> jtqkdcInfo = jtqkdcSerivce.ckJtqkdzxx(jtqkdcModel);
		
		xsxxInfo.putAll(jtqkdcInfo);
		
		xsxxInfo.putAll(jhzyPrint.getSfzhArr(xsxxInfo.get("sfzh")));
		
		xsxxInfo.putAll(service.getGjlzjxjInfo(xh, xn));
		
		request.setAttribute("rs", xsxxInfo);
		request.setAttribute("cyList", jtcyList);
		return mapping.findForward("gjlzjxjb");
	}
	// ===============国家励志奖学金审核end=====================
}
