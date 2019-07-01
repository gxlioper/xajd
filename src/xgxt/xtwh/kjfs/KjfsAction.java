package xgxt.xtwh.kjfs;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.xtwh.XtwhInit;
import xgxt.xtwh.sysz.SyszService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_快捷方式_action类
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

public class KjfsAction extends BasicAction {

	/**
	 * 评奖评优_基本设置_评奖基本设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kjfsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KjfsForm myForm = (KjfsForm) form;
		KjfsService service = new KjfsService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getXtwhKjfsInit(rForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		// =================== end ===================

		// ============= 执行保存操作 ============
		if ("save".equalsIgnoreCase(doType)) {

			boolean flag = service.saveYhKjfs(myForm, user);

			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;

			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== 初始化页面显示值 ===========
		List<HashMap<String, String>> rsList = service
				.setKjfsList(myForm, user);
		if (rsList != null && rsList.size() > 0) {
			myForm.getPages().setPageSize(18);
		}
		rForm.setRsList(service.getResultList(rsList, myForm.getPages()));
		// 用户权限List
		List<HashMap<String, String>> yhqxList = service.getKjfsList(user);
		// ================= end =====================

		// ============= 设置request的值 =============
		
		//服务列表
		request.setAttribute("fwlbList", service.getFwlb(myForm, user));
		service.setRequestValue(rForm, request);
		request.setAttribute("yhqxList", yhqxList);
		// =================== end ===================

		return mapping.findForward("kjfsManage");
	}
	
	/**
	 * 快捷方式跳转（学生用户）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stuKjfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KjfsService service = new KjfsService();
		User user = getUser(request);

		// ================= 赋初值 ==================
		// 快捷方式路径
		String path = request.getParameter("path");
		path = path.replace("'", "");
		// 返回路径
		String url = "/main1.jsp";
		// 是否被分配该路径
		boolean flag = service.hadQx(user, path);
		// =================end ===================

		// ==================是否能够访问检测 ==================
		if (!flag) {
			String msg = "对不起，您没有访问该模块的权限，请联系管理员！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			String menu = service
					.getOneValue("gnmkdmb", "gnmkdm", "dyym", path);
			if (!Base.isNull(menu) && menu.length() == 7) {
				String menuTop = menu.substring(0, 3);
				String menuLeft = menu.substring(0, 5);

				url += "?menuTop=" + menuTop + "";
				url += "&menuLeft=" + menuLeft + "";
				url += "&gnmkpath=" + path + "";
			}
		}
		// =================end ===================

		return new ActionForward(url, false);
	}
}