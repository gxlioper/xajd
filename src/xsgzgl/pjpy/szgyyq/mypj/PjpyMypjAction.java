package xsgzgl.pjpy.szgyyq.mypj;

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

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖_Action类
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

public class PjpyMypjAction extends BasicAction {

	/**
	 * 评奖评优_我的评奖_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mypjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyMypjForm myForm = (PjpyMypjForm) form;
		PjpyMypjService service = new PjpyMypjService();
		PjpyMypjInit init = new PjpyMypjInit();
		User user = getUser(request);// 用户对象
		HttpSession session = request.getSession();
		
		// ============= 初始化各变量的值 ============
		// 类型
		String yhlx = service.getMypjYhlx(user);
		request.setAttribute("yhlx", yhlx);
		session.setAttribute("yhlx", yhlx);
		// =================== end ===================

		// ============= 根据用户类型判断跳转 ============
		String forward = "";

		if ("stu".equalsIgnoreCase(yhlx)) {//普通学生
			forward = "mypjForStu";
		} else if ("bz".equalsIgnoreCase(yhlx)) {//班长
			forward = "mypjForBz";
		} else if ("bzr".equalsIgnoreCase(yhlx)) {//班主任
			List<HashMap<String,String>> rs=service.getBzrMyPjTjList(user);
			request.setAttribute("rs", rs);
			forward = "mypjForBzr";
		} else if ("xy".equalsIgnoreCase(yhlx) || "xx".equalsIgnoreCase(yhlx)) {//学校或院系
			List<HashMap<String,String>> rs=service.getXxMyPjTjList(user);
			request.setAttribute("rs", rs);
			forward = "mypjForXx";
		}
		
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		// =================== end ===================

		return mapping.findForward(forward);
	}

	/**
	 * 获得我的评奖统计列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuTjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyMypjForm myForm = (PjpyMypjForm) form;
		PjpyMypjService service = new PjpyMypjService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// 用户类型
		String yhlx = otherValue[0];
		myForm.setYhlx(yhlx);

		// 初始化
		init.initMypj(rForm, myForm, request);
		// =================== end ===================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================分页相关============================
		Pages pages = service.setPages("8", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================显示内容============================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		String spHtml = "";

		if ("stu".equalsIgnoreCase(yhlx)) {// 普通学生
			spHtml = service.getMypjStuHtml(rsModel, myForm, user);
		} else if ("bz".equalsIgnoreCase(yhlx)) {// 班长
			spHtml = service.getMypjStuHtml(rsModel, myForm, user);
		}

		// ==================显示内容 end========================

		// ==================构建前台页面========================
		int rows = Base.isNull(myForm.getRows()) ? 0 : Integer.parseInt(myForm
				.getRows());

		rsModel.setTopTr(topTr);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setLeft("no");
		rsModel.setRows(String.valueOf(rows));

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
		
}
