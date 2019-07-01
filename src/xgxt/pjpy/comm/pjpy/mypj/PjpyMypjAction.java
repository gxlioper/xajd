package xgxt.pjpy.comm.pjpy.mypj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
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
 * Description: 评奖评优_我的评奖-action类
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
	 * 评奖评优_我的评奖
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward mypjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyMypjService service = new PjpyMypjService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		String userType = user.getUserType();// 用户类型
		String path = "";
		String forward = "pjpy_mypj.do";
		// =================== end ===================

		if ("stu".equalsIgnoreCase(userType)) {
			forward = "myPjForStu";
		} else {
			forward = "myPjForTea";
		}

		rForm.setPath(path);
		service.setRequestValue(rForm, request);
		
		return mapping.findForward(forward);
	}
	
	/**
	 * 评奖评优_我的评奖_老师
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward myPjForTea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyMypjForm myForm = (PjpyMypjForm) form;
		PjpyMypjService service = new PjpyMypjService();
		PjpyMypjInit init = new PjpyMypjInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		RequestForm rForm = new RequestForm();
		init.getMypjForTeaRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		String userType = user.getUserType();// 用户类型
		String userStatus = user.getUserStatus();// 用户身份
		String message = "";// 提示信息	
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		return mapping.findForward("myPjForTea");
	}	
	
	/**
	 * 评奖评优_我的评奖_学生
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward myPjForStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyMypjForm myForm = (PjpyMypjForm) form;
		PjpyMypjService service = new PjpyMypjService();
		PjpyMypjInit init = new PjpyMypjInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		RequestForm rForm = new RequestForm();
		init.getMypjForStuRForm(rForm, myForm, request);
		
		String userType = user.getUserType();// 用户类型
		// =================== end ===================

		// ============= 非学生用户不可操作 ============
		if (!"stu".equalsIgnoreCase(userType)) {
			String message = "本模块只能由学生用户进行操作，请确认！";
			request.setAttribute("yhInfo", message);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================== end ===================
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		return mapping.findForward("myPjForStu");
	}	
	
	/**
	 * 获得我的评奖统计列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMypjTjList(ActionMapping mapping, ActionForm form,
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
		// 版本类型
		String bblx = otherValue[0];
		myForm.setBblx(bblx);
		
		if ("tea".equalsIgnoreCase(bblx)) {//老师版
			init.getMypjForTeaRForm(rForm, myForm, request);
		}else if("stu".equalsIgnoreCase(bblx)){//学生版
			init.getMypjForStuRForm(rForm, myForm, request);
		}
		// =================== end ===================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================
		
		// ==================显示内容========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		String spHtml = "";
		if ("tea".equalsIgnoreCase(bblx)) {// 老师版
			spHtml = service.getMypjTeaHtml(rsModel, myForm, user);
		} else if ("stu".equalsIgnoreCase(bblx)) {// 学生版
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
	
	/**
	 * 存储过程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward MD5Transfer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		boolean res = false;
		String sql = "{call pro_changeEncryptMode()}";
		res = dao.runProcuder(sql, new String[] {});
		if (res) {
			request.setAttribute("autoChk", "ok");
		} else {
			request.setAttribute("autoChk", "no");
		}
		return mapping.findForward("MD5Transfer");
	}
}
