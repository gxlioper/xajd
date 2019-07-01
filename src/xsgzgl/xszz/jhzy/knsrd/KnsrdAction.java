package xsgzgl.xszz.jhzy.knsrd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.xszz.XszzTyForm;
import xsgzgl.xszz.jhzy.XszzJhzyPrint;
import xsgzgl.xszz.jhzy.cssz.XszzCsszActionForm;
import xsgzgl.xszz.jhzy.gjlzjxj.GjlzjxjForm;
import xsgzgl.xszz.jhzy.gjzxj.GjzxjService;
import xsgzgl.xszz.jhzy.jtqkdz.XszzJtqkdzActionForm;
import xsgzgl.xszz.jhzy.jtqkdz.XszzJtqkdzService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 学生资助_困难生认定_金华职业_Action类
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

public class KnsrdAction extends BasicAction {

	// ===============頁面跳轉 begin=====================

	/**
	 * 困难生申请【查询】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssqSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdInit init = new KnsrdInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSqSearch(rForm, myForm, user, request);
		// =================== end ===================
		//===============导出表设置===================
		request.setAttribute("tableName", "view_xg_xszz_jhzy_knsrdsqb");
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("knssqSearch");
	}

	/**
	 * 困难生申请【增加】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssqInsert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdInit init = new KnsrdInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSqInsert(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("knssqInsert");
	}

	/**
	 * 困难生申请【修改】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdInit init = new KnsrdInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSqUpdate(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("knssqUpdate");
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
			return knsshSearch(mapping, form, request, response);
		}
	}

	/**
	 * 困难生审核【查询】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsshSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdInit init = new KnsrdInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();


		// ============= 初始化各变量的值 ============
		// 初始化
		init.initShSearch(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("knsshSearch");
	}

	/**
	 * 困难生审核【修改】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsshUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdInit init = new KnsrdInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initShUpdate(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("knsshUpdate");
	}

	/**
	 * 困难生结果【查询】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsjgSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdInit init = new KnsrdInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();
		SearchModel searchModel=new SearchModel();
		XszzCsszActionForm csszForm = XszzCsszActionForm.getCsszForm();
		searchModel.setSearch_tj_xn(new String[]{csszForm.getXn()});
		request.setAttribute("searchTj", searchModel);
		// ============= 初始化各变量的值 ============
		// 初始化
		init.initjgSearch(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_xszz_jhzy_knsrdb");

		return mapping.findForward("knsjgSearch");
	}

	/**
	 * 困难生结果【详细】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsjgDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdInit init = new KnsrdInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initJgDetail(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("knsjgDetail");
	}

	// ===============頁面跳轉 end=====================

	// ===============困难生申请begin=====================

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
	public ActionForward searchKnssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdModel model = new KnsrdModel();
		KnsrdInit init = new KnsrdInit();

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
		List<HashMap<String, String>> topTr = service.getKnssqTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getKnssqList(myForm, model,
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
	 * 创建困难生类别HTML
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createKnslbHtml(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdInit init = new KnsrdInit();
		KnsrdModel model = new KnsrdModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initSqInsert(rForm, myForm, user, request);
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createKnslbHtml(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 保存困难生申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveKnsrdSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdService service = new KnsrdService();
		KnsrdModel model = new KnsrdModel();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.saveKnsrdSq(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 删除困难生申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteKnsrdSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdService service = new KnsrdService();
		KnsrdModel model = new KnsrdModel();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ==================构建前台页面====================

		boolean flag = service.deleteKnsrdSq(model, user);

		String message = flag ? "删除成功" : "删除失败，请联系相关人员";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		// ==================构建前台页面 end================

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

		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdInit init = new KnsrdInit();
		KnsrdModel model = new KnsrdModel();

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

	// ===============困难生申请end=====================

	// ===============困难生审核begin=====================

	/**
	 * 查询资助学年的困难生审核记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchKnssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdModel model = new KnsrdModel();
		KnsrdInit init = new KnsrdInit();

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
		List<HashMap<String, String>> topTr = service.getKnsshTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getKnsshList(myForm, model,
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
	 * 保存困难生审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveKnsrdSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdService service = new KnsrdService();
		KnsrdModel model = new KnsrdModel();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.saveKnsrdSh(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===============困难生审核end=====================

	// ===============困难生结果begin=====================

	/**
	 * 查询资助学年的困难生审核记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchKnsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdModel model = new KnsrdModel();
		KnsrdInit init = new KnsrdInit();

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
		List<HashMap<String, String>> topTr = service.getKnsjgTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getKnsjgList(myForm, model,
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
	 * 保存困难生推荐档次
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveKnsrdTjdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdService service = new KnsrdService();
		KnsrdModel model = new KnsrdModel();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.saveKnsrdTjdc(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 困难生认定打印表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		KnsrdService service = new KnsrdService();
		XszzJtqkdzService jtqkdcSerivce = new XszzJtqkdzService();
		
		KnsrdForm  myForm = (KnsrdForm) form;
		XszzJtqkdzActionForm jtqkdcModel=new XszzJtqkdzActionForm();
		
		XszzTyForm model = new XszzTyForm();
		
		XszzJhzyPrint jhzyPrint=new XszzJhzyPrint();
		// user对象
		User user=getUser(request);
		// 用户名
 		String xh=request.getParameter("xh");
 		String xn=request.getParameter("xn");
 		jtqkdcModel.setXh(xh);
 		jtqkdcModel.setPkStr(xh+xn);
 		model.setXh(xh);
 		
		HashMap<String,String>xsxxInfo=new HashMap<String,String>();
		
		List<HashMap<String, String>> jtcyList = jtqkdcSerivce.cxJtcyxxList(jtqkdcModel);
		//学生基本信息
		jhzyPrint.getStuInfo(model,xsxxInfo);
		
		//xsxxInfo.putAll(service.getGjzxjInfo(xh, xn));
		
		HashMap<String, String> jtqkdcInfo = jtqkdcSerivce.ckJtqkdzxx(jtqkdcModel);
		
		xsxxInfo.putAll(jtqkdcInfo);
		
		HashMap<String,String>knsInfo=service.getKnsrdInfo(xh,xn);
		xsxxInfo.putAll(knsInfo);
		
		List<HashMap<String, String>> knslbList = service.getKnslbList(knsInfo.get("sqlb")
				.split("luojw"));
		
		
		xsxxInfo.putAll(jhzyPrint.getSfzhArr(xsxxInfo.get("sfzh")));
		request.setAttribute("rs", xsxxInfo);
		request.setAttribute("cyList", jtcyList);
		request.setAttribute("knslbList", knslbList);
		return mapping.findForward("knsrdb");
	}
	// ===============困难生结果end=====================
}
