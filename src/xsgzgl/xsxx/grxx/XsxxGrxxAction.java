package xsgzgl.xsxx.grxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.xsxx.cssz.grxx.XsxxCsszService;
import xsgzgl.xsxx.model.CsszModel;
import xsgzgl.xsxx.model.ZdxgModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_个人信息_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxGrxxAction extends BasicAction {
	
	/**
	 * 学生信息_个人信息_修改申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxInit init = new XsxxGrxxInit();
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		CsszModel csszModel = csszService.getGrxxCssz();
		  
		// ============= 初始化各变量的值 ============
		myForm.setCsszModel(csszModel);
		
		// 初始化
		init.initGrxxSq(rForm, myForm, user, request);
		HashMap<String, String> rs = null;

		if (!Base.isNull(myForm.getXh())) {
			rs = service.getXsxxInfo(myForm, user);
		}
		// =================== end ===================

		// ==================登陆用户检测 ==================
		String msg = service.checkSqzg(csszModel);
		String sqjg = myForm.getSqjg();

		if (!"stu".equalsIgnoreCase(user.getUserType())) {
			request.setAttribute("yhInfo", "仅能由学生进行申请");
			return new ActionForward("/yhInfo.do", false);
		} 
		
		if (!"退回".equalsIgnoreCase(sqjg) && !Base.isNull(msg)) {
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================
		
		// ============= 设置request的值 =============
		rForm.setRs(rs);
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("editSq");
	}
	
	/**
	 * 学生信息_个人信息_修改申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxInit init = new XsxxGrxxInit();
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		CsszModel csszModel = csszService.getGrxxCssz();
		
		// ==================登陆用户检测 ==================
		String msg = service.checkShzg(csszModel);
		
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			request.setAttribute("yhInfo", "学生用户不能进行审核，请确认");
			return new ActionForward("/yhInfo.do", false);
		} else if (!Base.isNull(msg)) {
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================
		  
		// ============= 初始化各变量的值 ============
		myForm.setCsszModel(csszModel);
		// 初始化
		init.initGrxxSh(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("editSh");
	}

	/**
	 * 学生信息_个人信息_修改审核详细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxInit init = new XsxxGrxxInit();
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		CsszModel csszModel = csszService.getGrxxCssz();

		// ============= 初始化各变量的值 ============
		myForm.setCsszModel(csszModel);

		// 初始化
		init.initGrxxShDetail(rForm, myForm, user, request);
		HashMap<String, String> rs = service.getXgxxInfo(myForm, user);
		// =================== end ===================

		// ============= 设置request的值 =============
		rForm.setRs(rs);
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("editDetail");
	}
	
	/**
	 * 学生信息_个人信息_修改结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jgcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxInit init = new XsxxGrxxInit();
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		  
		// ============= 初始化各变量的值 ============
		// 初始化
		init.initGrxxJg(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("jgcxManage");
	}
	
	/**
	 * 学生信息_个人信息_修改结果详细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jgcxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxInit init = new XsxxGrxxInit();
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		CsszModel csszModel = csszService.getGrxxCssz();

		// ============= 初始化各变量的值 ============
		myForm.setCsszModel(csszModel);

		String stylePath = request.getParameter("stylePath");
		rsModel.setStylePath(stylePath);

		// 初始化
		init.initGrxxShDetail(rForm, myForm, user, request);
		HashMap<String, String> rs = service.getXgxxInfo(myForm, user);
		List<HashMap<String, String>> shrList = service.getShrList(myForm,
				rsModel);
		// =================== end ===================

		// ============= 设置request的值 =============
		rForm.setRs(rs);
		
		if(shrList!=null && shrList.size()>0){
			rForm.setRsList(shrList);
		}
		
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("jgcxDetail");
	}
	
	// =================以下方法=======================
	
	/**
	 * 显示字段修改Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showZdxgDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 用户类型
		String zd = request.getParameter("zd");
		// =================== end ===================

		// ==================构建前台页面========================
		service.showZdxgDiv(zd, user, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 保存字段修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZdxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		User user = getUser(request);// 用户对象
		ZdxgModel zdxgModel = new ZdxgModel();
		
		// ============= 初始化各变量的值 ============
		String xh = request.getParameter("xh");// '学号';
		zdxgModel.setXh(xh);

		String sqid = request.getParameter("sqid");// '申请ID';
		zdxgModel.setSqid(sqid);

		String xgzd = request.getParameter("xgzd");// '修改字段';
		zdxgModel.setXgzd(xgzd);

		String xgz = request.getParameter("xgz");// '修改值';
		xgz = Base.isNull(xgz) ? xgz : service.unicode2Gbk(xgz);
		zdxgModel.setXgz(xgz);
		
		myForm.setZdxgModel(zdxgModel);
		// ============= 初始化各变量的值 end============

		// ==================执行保存操作========================
		boolean flag = service.saveZdxg(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 获得所在部门
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSzbm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		String bjdm = request.getParameter("bjdm");// '学号';
		// ============= 初始化各变量的值 end============

		// ==================执行保存操作========================
		String szbm = service.getSzbm(bjdm);
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(szbm);

		return null;
	}
	
	/**
	 * 获得省市县
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSsx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		String ssx = request.getParameter("ssx");// '学号';
		// ============= 初始化各变量的值 end============

		// ==================执行保存操作========================
		String szbm = service.getSsx(ssx);
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(szbm);

		return null;
	}
	
	/**
	 * 保存字段修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		CsszModel csszModel = csszService.getGrxxCssz();
		
		// ============= 初始化各变量的值 ============
		String xh = request.getParameter("xh");// '学号';
		myForm.setXh(xh);
		
		String sqid = request.getParameter("sqid");// '申请ID';
		myForm.setSqid(sqid);
		
		myForm.setCsszModel(csszModel);
		// ============= 初始化各变量的值 end============

		// ==================执行保存操作========================
		String message = service.saveXgsq(myForm, user);
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 提交个人信息修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitGrxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		CsszModel csszModel = csszService.getGrxxCssz();

		// ============= 初始化各变量的值 ============
		String xh = request.getParameter("xh");// '学号';
		myForm.setXh(xh);

		myForm.setCsszModel(csszModel);
		// ============= 初始化各变量的值 end============

		// ==================执行保存操作========================
		boolean flag = service.submitGrxx(myForm);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 获得审核情况
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getShInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		CsszModel csszModel = csszService.getGrxxCssz();
		
		// ============= 初始化各变量的值 ============
		List<HashMap<String, String>> shInfoList = null;
			
		//学号
		String xh = request.getParameter("xh");
		myForm.setXh(xh);
		
		//申请id
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);
		
		//是否审核
		String sfsh = csszModel.getSfsh();
		
		myForm.setCsszModel(csszModel);
		// ============= 初始化各变量的值 end============

		// ============= 查询目前的审核情况 ============
		if ("是".equalsIgnoreCase(sfsh)) {
			shInfoList = service.getShInfoList(myForm);
		}
		// ============= 查询目前的审核情况 end============
		
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(shInfoList));
		
		return null;
	}
	
	/**
	 * 获得修改情况
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXgInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		
		// ============= 初始化各变量的值 ============	
		//学号
		String xh = request.getParameter("xh");
		myForm.setXh(xh);
		
		//申请id
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);
		// ============= 初始化各变量的值 end============

		// ============= 查询目前的字段修改 ============
		List<HashMap<String, String>> xgInfoList = service
				.getXgInfoList(myForm);
		// ============= 查询目前的字段修改 end============
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(xgInfoList));
		
		return null;
	}
	
	/**
	 * 获得修改审核列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXgshList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		XsxxGrxxInit init = new XsxxGrxxInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();
		CsszModel csszModel = csszService.getGrxxCssz();
		
		// ============= 初始化各变量的值 ============
		// 参数
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE
		String ie = otherValue[0];
		rsModel.setIe(ie);

		// 样式地址
		String stylePath = otherValue[1];
		rsModel.setStylePath(stylePath);
		
		// 审核岗位
		String shgw = otherValue[2];
		myForm.setShgw(shgw);
		
		myForm.setCsszModel(csszModel);
		
		// 初始化
		init.initGrxxSh(rForm, myForm, user, request);
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
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		ArrayList<String[]> rsArrList = service.getXgshList(myForm, user);
		String spHtml = service.getXgshHtml(rsModel, myForm, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("yes");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 保存审核状态
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveShzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		CsszModel csszModel = csszService.getGrxxCssz();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		String xh = request.getParameter("xh");// '学号';
		myForm.setXh(xh);
		
		String sqid = request.getParameter("sqid");// '申请ID';
		myForm.setSqid(sqid);

		String shgw = request.getParameter("shgw");// '审核岗位';
		myForm.setShgw(shgw);

		String shzt = request.getParameter("shzt");// '审核状态';
		shzt = Base.isNull(shzt) ? shzt : service.unicode2Gbk(shzt);
		myForm.setShzt(shzt);
		
		String shyj = request.getParameter("shyj");// '审核意见';
		shyj = Base.isNull(shyj) ? shyj : service.unicode2Gbk(shyj);
		myForm.setShyj(shyj);
		
		myForm.setCsszModel(csszModel);
		// ============= 初始化各变量的值 end============

		// ==================执行保存操作========================
		boolean flag = service.saveShzt(myForm, user);
		String message = flag ? "审核成功" : "审核失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 获得修改结果列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJgcxList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxGrxxInit init = new XsxxGrxxInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();
		
		// ============= 初始化各变量的值 ============
		// 参数
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE
		String ie = otherValue[0];
		rsModel.setIe(ie);

		// 样式地址
		String stylePath = otherValue[1];
		rsModel.setStylePath(stylePath);
		
		// 初始化
		init.initGrxxJg(rForm, myForm, user, request);
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
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		ArrayList<String[]> rsArrList = service.getJgcxList(myForm, user);
		String spHtml = service.getJgcxHtml(rsModel, myForm, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("yes");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
}