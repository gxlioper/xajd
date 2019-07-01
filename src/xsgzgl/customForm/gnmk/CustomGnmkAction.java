package xsgzgl.customForm.gnmk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 自定义表单_自定义功能_Action类
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

public class CustomGnmkAction extends BasicAction {

	/**
	 * 自定义表单_功能模块_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customGnmkManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		CustomGnmkInit init = new CustomGnmkInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initCustomGnmk(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("customGnmkManage");
	}

	/**
	 * 自定义表单_功能模块_详细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customGnmkDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		CustomGnmkInit init = new CustomGnmkInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initCustomGnmk(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		service.setList(model, rForm, request);
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("customGnmkDetail");
	}

	// =======================以下方法==============================

	/**
	 * 获得功能模块查询列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getGnmkManageList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		User user = getUser(request);// 用户对象
		CustomGnmkInit init = new CustomGnmkInit();
		SearchRsModel rsModel = new SearchRsModel();
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============	
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// 功能模块代码
		String gnmkdm = otherValue[0];
		myForm.setGnmkdm(gnmkdm);
		
		init.initCustomGnmk(rForm, myForm, user, request);
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
		ArrayList<String[]> rsArrList = service.getGnmkManageList(myForm, user);
		String spHtml = service.getGnmkManageHtml(rsModel, myForm, rsArrList,
				user);
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
	 * 显示功能模块详细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showGnmkDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 功能模块代码
		String gnmkdm = request.getParameter("gnmkdm");
		myForm.setGnmkdm(gnmkdm);
		// ============= 初始化各变量的值 end============

		// ==================构建前台页面========================
		service.createGnmkDetail(myForm, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 获得学生信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		String nj = request.getParameter("nj");// 年级
		myForm.setNj(nj);

		String xydm = request.getParameter("xydm");// 学院代码
		myForm.setXydm(xydm);

		String zydm = request.getParameter("zydm");// 专业代码
		myForm.setZydm(zydm);

		String bjdm = request.getParameter("bjdm");// 班级代码
		myForm.setBjdm(bjdm);

		String xh = request.getParameter("xh");// 学号
		myForm.setXh(xh);

		String xm = request.getParameter("xm");// 姓名
		if(!Base.isNull(xm)){
			xm = service.unicode2Gbk(xm);
		}
		myForm.setXm(xm);
		
		String ryfw = request.getParameter("ryfw");// 人员范围
		myForm.setRyfw(ryfw);
		
		String xsnum = request.getParameter("xsnum");// 学生数量
		myForm.getPages().setCurrentPage(Integer.parseInt(xsnum));
		// ============= 初始化各变量的值 end============

		// ==================构建前台页面========================
		service.getXsInfo(myForm, user, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 获得学生详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXsInfoDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		XsxxglService stuService = new XsxxglService();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		String xh = request.getParameter("xh");// 学号
		myForm.setXh(xh);
		// ============= 初始化各变量的值 end============

		// ==================构建前台页面========================
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		list.add(stuInfo);
		// ==================构建前台页面 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}
	
	/**
	 * 保存功能模块
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveGnmk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		String xmb = request.getParameter("xmb");// 项目表;
		myForm.setXmb(xmb);

		String gnmkdm = request.getParameter("gnmkdm");// 功能模块代码;
		myForm.setGnmkdm(gnmkdm);

		String zd = request.getParameter("zd");// 字段;
		if (!Base.isNull(zd)) {
			myForm.setZd(zd.split("!!@@!!"));
		}

		String zdz = request.getParameter("zdz");// 字段值;
		if (!Base.isNull(zdz)) {
			myForm.setZdz(zdz.split("!!@@!!"));
		}
		// ============= 初始化各变量的值 end============

		// ==================执行保存操作========================
		boolean flag = service.saveGnmk(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
