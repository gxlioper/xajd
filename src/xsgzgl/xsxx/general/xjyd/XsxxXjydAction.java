package xsgzgl.xsxx.general.xjyd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.XsxxXjydInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_学籍异动_通用_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxXjydAction extends BasicAction {

	/**
	 * 查询学籍异动结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchXjydResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxXjydInit init = new XsxxXjydInit();
		XsxxXjydModel model = new XsxxXjydModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initXjyd(rForm, myForm, user, request);
		XsxxXjydInterface service = myService.getXsxxXjydService(myForm);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getXsxxXjydTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getXsxxXjydList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createXsxxXjydHTML(rsModel, model, rsArrList,
				user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 同步
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXjyd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxXjydInit init = new XsxxXjydInit();
		XsxxXjydModel model = new XsxxXjydModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initXjyd(rForm, myForm, user, request);
		XsxxXjydInterface service = myService.getXsxxXjydService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.submitXjyd(model, user);
		String message = flag ? "同步成功" : "同步失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 获取学生学号
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsxxGeneralService service = new XsxxGeneralService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("general_xsxx_xjyd.do?searchType=getXh");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("xsxhInfo");
	}
	
	/**
	 * 获取学生学号ajax
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXhAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsxxGeneralService service = new XsxxGeneralService();
		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("general_xsxx_xjyd.do?searchType=getXh");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getXhAjax(myForm,user);
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 加载学生信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXsxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		XsxxGeneralService service = new XsxxGeneralService();
		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		String xh = request.getParameter("xh");
		//获取由学号到学生信息结果集
		List<HashMap<String, String>> xsxxRs = service.getXsxx(myForm,xh);
		JSONArray jsonArr = JSONArray.fromArray(xsxxRs.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
}
