package xsgzgl.xsxx.general;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Configuration;
import xgxt.comm.CommForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.xsxx.general.dljc.XsxxDljcInit;
import xsgzgl.xsxx.general.dljc.XsxxDljcModel;
import xsgzgl.xsxx.general.jcsz.XsxxJcszService;
import xsgzgl.xsxx.general.xjyd.XsxxXjydInit;
import xsgzgl.xsxx.general.xxxg.xgjg.XgjgInit;
import xsgzgl.xsxx.general.xxxg.xgjg.XgjgModel;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshInit;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshModel;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsInit;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_通用_Action类
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

public class XsxxGeneralAction extends BasicAction {

	// 斜杠
	public static String slashes;

	static {
		if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
			slashes = "/";
		} else {
			slashes = "\\";
		}
	}

	// =================学生信息 begin ===================

	/**
	 * 学生信息_在校学生管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward zxxsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		RequestForm rForm = new RequestForm();
		XsxxZxxsInit init = new XsxxZxxsInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initZxxs(rForm, myForm, user, request);

		System.out
				.println("-----------------------------this way begin--------------------------------------------");
		System.out.println("slashes：" + slashes);
		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		System.out.println("xxpymc：" + xxpymc);
		// 工程路径
		String pro_path = Configuration.PRO_PATH.substring(0,
				Configuration.PRO_PATH.lastIndexOf(slashes));
		System.out.println("pro_path：" + pro_path);
		// 跳转路径
		String url = "/xsgzgl/xsxx/" + xxpymc + "/zxxs/zxxsManage.jsp";
		System.out.println("url：" + url);
		// 文件路径
		String file_paht = pro_path + url;
		System.out.println("file_paht：" + file_paht);
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		System.out
				.println("-----------------------------this way end--------------------------------------------");

		// ============= 判断是否通用 begin ============
		// File file = new File(file_paht);
		// if (!file.exists()) {
		// return mapping.findForward("zxxsManage");
		// }
		// ============= 判断是否通用 end ============

		return new ActionForward(url, false);
	}

	/**
	 * 学生信息_学生信息修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xsxxModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		RequestForm rForm = new RequestForm();
		XsxxZxxsInit init = new XsxxZxxsInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initZxxs(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 工程路径
		String pro_path = Configuration.PRO_PATH.substring(0,
				Configuration.PRO_PATH.lastIndexOf(slashes));
		// 跳转路径
		String url = "/xsgzgl/xsxx/" + xxpymc + "/zxxs/xsxxUpdate.jsp";
		// 文件路径
		String file_paht = pro_path + url;
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		// ============= 判断是否通用 begin ============
		// File file = new File(file_paht);
		// if (!file.exists()) {
		// return mapping.findForward("xsxxUpdate");
		// }
		// ============= 判断是否通用 end ============

		return new ActionForward(url, false);
	}

	/**
	 * 学生信息_学生信息详细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xsxxDetailed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
//		XsxxGeneralService service = new XsxxGeneralService();
//		RequestForm rForm = new RequestForm();
//		XsxxZxxsInit init = new XsxxZxxsInit();
//		User user = getUser(request);// 用户对象
//
//		// ============= 初始化各变量的值 begin ============
//		init.initZxxs(rForm, myForm, user, request);
//
//		// 学校拼音名称
//		String xxpymc = myForm.getXxpymc();
//		// 跳转路径
//		String url = "/xsgzgl/xsxx/" + xxpymc + "/zxxs/xsxxDetail.jsp";
//		// ============= 初始化各变量的值 end ==============
//
//		// ============= 设置request的值 =============
//		CommForm model = new CommForm();
//		service.setRequestValue(rForm, request);
//		service.setList(model, rForm, request);
		// =================== end ===================
		
		//2013-1-31 qph 将查看 学生详细跳转到新路径
		return new ActionForward("/xsxx_tygl.do?method=ckZxsxx&xh="+myForm.getXh(),false);
		//return new ActionForward(url, false);
	}

	/**
	 * 学生信息_历史学生管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward lsxsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		RequestForm rForm = new RequestForm();
		XsxxZxxsInit init = new XsxxZxxsInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initZxxs(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 工程路径
		String pro_path = Configuration.PRO_PATH.substring(0,
				Configuration.PRO_PATH.lastIndexOf(slashes));
		// 跳转路径
		String url = "/xsgzgl/xsxx/" + xxpymc + "/lsxs/lsxsManage.jsp";
		// 文件路径
		String file_paht = pro_path + url;
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		// ============= 判断是否通用 begin ============
		// File file = new File(file_paht);
		// if (!file.exists()) {
		// return mapping.findForward("lsxsManage");
		// }
		// ============= 判断是否通用 end ============

		return new ActionForward(url, false);
	}

	// =================学生信息 end ===================

	// =================学籍异动 begin ===================

	/**
	 * 学生信息_学籍异动管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xjydManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XsxxXjydInit init = new XsxxXjydInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initXjyd(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/xsxx/" + xxpymc + "/xjyd/xjydManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	// =================学籍异动 end ===================

	// =================登录检测 begin ===================

	/**
	 * 学生信息_登录检测查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward dljcSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XsxxDljcInit init = new XsxxDljcInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initDljcSearch(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/xsxx/" + xxpymc + "/dljc/dljcSearch.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * 学生信息_学生登录信息完善
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward dljcInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XsxxDljcModel model = new XsxxDljcModel();
		XsxxDljcInit init = new XsxxDljcInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initDljcInput(rForm, myForm, model, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "";
		// 是否信息完善
		boolean isXxws = model.isXxws();

		if (isXxws) {
			url = "/stuPage.jsp";
		} else {
			url = "/xsgzgl/xsxx/" + xxpymc + "/dljc/dljcInput.jsp";
		}
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	// =================登录检测 end ===================

	// =================個人信息修改 begin ===================

	/**
	 * 個人信息修改_修改審核【查詢】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xgshSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XsxxJcszService jcszService = new XsxxJcszService();
		XgshInit init = new XgshInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initXgshSearch(rForm, myForm, user, request);
		init.initParameter(myForm);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/xsxx/general/xxxg/" + xxpymc + "/xgsh/xgshSearch.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 判斷用戶權限相關 begin ============

		// 審核相關信息
		HashMap<String, Object> shxgInfo = jcszService.getShlcByYh(user
				.getUserName());
		// 審批流程
		String splc = (String) shxgInfo.get("splc");
		// 審批崗位列表
		List<HashMap<String, String>> spgwList = (List<HashMap<String, String>>) shxgInfo
				.get("spgwList");

		String msg = "";

		if ("wxsh".equalsIgnoreCase(splc)) {
			msg = "本次个人信息修改无需审核，请确认";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else if (spgwList == null || spgwList.size() == 0) {
			msg = "您不在本次个人信息修改审核的人员名单中，如有异议，请您联系管理员";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("splc", splc);
		request.setAttribute("gwid", spgwList.get(0).get("spgw"));
		request.setAttribute("spgwList", spgwList);
		// ===================== end ======================

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * 個人信息修改_修改審核【詳細】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xgshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XgshModel model = new XgshModel();
		XgshInit init = new XgshInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initXgshDetail(rForm, myForm, model, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/xsxx/general/xxxg/" + xxpymc + "/xgsh/xgshDetail.jsp";
		/*if(Base.xxdm.equals("12036")){
			 url = "/xsgzgl/xsxx/general/xxxg/zjzyjt/xgsh/xgshDetail.jsp";
		}*/
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * 個人信息修改_修改結果【查詢】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xgjgSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XgjgInit init = new XgjgInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initXgjgSearch(rForm, myForm, user, request);
		init.initParameter(myForm);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/xsxx/general/xxxg/" + xxpymc + "/xgjg/xgjgSearch.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * 個人信息修改_修改結果【詳細】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xgjgDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XgjgModel model = new XgjgModel();
		XgjgInit init = new XgjgInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initXgjgDetail(rForm, myForm, model, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/xsxx/general/xxxg/" + xxpymc + "/xgjg/xgjgDetail.jsp";
		if(Base.xxdm.equals("12036")){
			 url = "/xsgzgl/xsxx/general/xxxg/zjzyjt/xgjg/xgjgDetail.jsp";
		}
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================個人信息修改 end ===================
}
