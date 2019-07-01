package xgxt.jxgl.comm.jxbz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.jxgl.comm.JxglCommForm;
import xgxt.jxgl.comm.jxjg.JxglJxjgService;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrInit;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrService;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_军训编制_Action类
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

public class JxglJxbzAction extends BasicAction {

	/**
	 * 军训管理_军训编制_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxbzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxbzInit init = new JxglJxbzInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getJxbzRForm(rForm, myForm, request);
		// =================== end ===================
		
		// ============= 设置request的值 =============
		request.setAttribute("searchTj", myForm.getSearchModel());
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("jxbzManage");
	}
	
	/**
	 * 军训管理_军训编制_维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxbzUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxbzInit init = new JxglJxbzInit();
		JxglCommForm jxszModel = JxglJxbzForm.jxglCommForm;
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getJxbzRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		String userType = user.getUserType();// 用户类型
		String userStatus = user.getUserStatus();// 用户身份
		String message = "";// 提示信息
		HashMap<String, String> rs = service.getJxbzInfo(myForm, user);
		// =================== end ===================

		// =================== 初始化列表值 ===========
		// 军训编制等级列表
		List<HashMap<String, String>> jxbzdjList = jxszModel.getJxbzdjList();
		// 军训编制列表
		List<HashMap<String, String>> jxbzList = service.getJxbzList(myForm,
				user);
		request.setAttribute("jxbzList", jxbzList);
		request.setAttribute("jxbzdjList", jxbzdjList);
		// ================= end =====================
		
		// =================== 初始化列表值 ===========
		service.setJxglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= 设置request的值 =============
		rForm.setRs(rs);
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		return mapping.findForward("jxbzUpdate");
	}	
	
	/**
	 * 军训管理_军训编制_班级分配
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxbzBjfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxbzInit init = new JxglJxbzInit();
		JxglCommForm jxszModel = JxglJxbzForm.jxglCommForm;
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getJxbzRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		String userType = user.getUserType();// 用户类型
		String userStatus = user.getUserStatus();// 用户身份
		String message = "";// 提示信息
		HashMap<String, String> rs = service.getJxbzInfo(myForm, user);
		// =================== end ===================

		// =================== 初始化列表值 ===========
		// 军训编制等级列表
		List<HashMap<String, String>> jxbzdjList = jxszModel.getJxbzdjList();
		// 军训编制列表
		List<HashMap<String, String>> jxbzList = service.getJxbzList(myForm,
				user);
		request.setAttribute("jxbzList", jxbzList);
		request.setAttribute("jxbzdjList", jxbzdjList);
		// ================= end =====================
		
		// =================== 初始化列表值 ===========
		service.setJxglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= 设置request的值 =============
		rForm.setRs(rs);
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		return mapping.findForward("jxbzBjfp");
	}
	
	/**
	 * 构建军训编制HTML（最大级别）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMaxbzHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		User user = getUser(request);// 用户对象

		// 选中的部门
		String checkedBzdm = request.getParameter("checkedBzdm");
		myForm.setCheckedBzdm(checkedBzdm);
		// ============= 创建Html ============
		service.createMaxbzHtml(myForm,user,response);
		// ============= 创建Html end ============

		return null;
	}
	
	/**
	 * 构建军训编制HTML（次级别）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getNextbzHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		User user = getUser(request);// 用户对象

		//选中的部门
		String checkedBzdm = request.getParameter("checkedBzdm");
		myForm.setCheckedBzdm(checkedBzdm);
		// ============= 创建Html ============
		service.createNextbzHtml(myForm, user, response);
		// ============= 创建Html end============

		return null;
	}
	
	/**
	 * 获得班级分配列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBjfpList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxbzInit init = new JxglJxbzInit();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();
		
		// ============= 初始化各变量的值 ============
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue").split("!!@@!!");
		// 学年
		String xn = otherValue[0];
		myForm.setXn(xn);
		// 上级代码
		String sjdm = otherValue[1];
		myForm.setSjdm(sjdm);
		// 年级
		String nj = otherValue[2];
		myForm.setNj(nj);
		// 学院代码
		String xydm = otherValue[3];
		myForm.setXydm(xydm);
		// 专业代码
		String zydm = otherValue[4];
		myForm.setZydm(zydm);
		// 班级代码
		String bjdm = otherValue[5];
		myForm.setBjdm(bjdm);
		
		RequestForm rForm = new RequestForm();
		init.getJxbzRForm(rForm, myForm, request);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("",request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		ArrayList<String[]> rsArrList = service.getBjfpList(myForm, user);
		// ==================显示内容 end========================
		
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================
		
		return null;
	}
	
	/**
	 * 构建军训编制JS
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJxbzJs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();

		// ============= 设置js ============
		service.createJxbzJs(myForm, response);
		//service.createMinJxbzJs(myForm, response);
		// ============= 设置js end ============

		return null;
	}
	
	/**
	 * 保存军训编制
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveJxbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxjgService jxjgService= new JxglJxjgService();
		User user = getUser(request);// 用户对象

		// ============= 保存军训编制 ============
		boolean flag = service.saveJxbz(myForm, user, request);
		// 提示信息
		String message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
				: MessageInfo.MESSAGE_DO_FALSE;
		
		if(flag){
			//jxjgService.jxmdIni(user);
		}
		// ============= 保存军训编制 end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 删除军训编制
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delJxbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxjgService jxjgService= new JxglJxjgService();
		JxglJxbzInit init = new JxglJxbzInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getJxbzRForm(rForm, myForm, request);
		// =================== end ===================
		
		// ============= 删除军训编制 ============
		boolean flag = service.delJxbz(myForm, user);
		// 提示信息
		String message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
				: MessageInfo.MESSAGE_DEL_FALSE;
		
		if(flag){
			jxjgService.jxmdIni(user);
		}
		// ============= 删除军训编制 end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 保存班级分配
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBjfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxjgService jxjgService= new JxglJxjgService();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		// 主键
		String[] pk = request.getParameter("pk").split("!!@@!!");
		myForm.setPk(pk);
		// 上级代码
		String sjdm = request.getParameter("sjdm");
		myForm.setSjdm(sjdm);
		// ============= 初始化各变量的值 end ============
		
		// ============= 保存军训编制 ============
		boolean flag = service.saveBjfp(myForm, user);
		// 提示信息
		String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		if(flag){
			//jxjgService.jxmdIni(user);
		}
		// ============= 保存军训编制 end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
