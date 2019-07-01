package xsgzgl.xtwh.general.homepage;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.customForm.demo.DemoFormForm;
import xsgzgl.customForm.demo.DemoFormInit;
import xsgzgl.customForm.demo.DemoFormModel;
import xsgzgl.customForm.demo.DemoFormService;
import xsgzgl.customForm.gnmk.CustomGnmkForm;
import xsgzgl.customForm.gnmk.CustomGnmkService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系統維護_首頁_通用_Action类
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

public class HomePageAction extends BasicAction {

	/**
	 * 初始化我的工作數據
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultWdgzInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HomePageService service = new HomePageService();
		HomePageModel model = new HomePageModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		boolean flag = service.defaultWdgzInfo(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 顯示我的工作信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showWdgzInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HomePageService service = new HomePageService();
		HomePageModel model = new HomePageModel();
		User user = getUser(request);// 用户对象

		//取我的申请列表
		List<HashMap<String,String>> list = service.getWdgzList(model, user);
		
		JSONArray wdsqList = JSONArray.fromObject(list);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(wdsqList);

		return null;
	}
}
