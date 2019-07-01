package xgxt.jxgl.comm.jxjg;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.form.CommanForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.wjsc.WjscDataAccessAction;
import xgxt.wjsc.wjff.WjffModel;
import xgxt.wjsc.wjff.WjffService;
import xsgzgl.jxgl.hzsf.cssz.JxglCsszService;


import com.zfsoft.basic.BasicAction;
import com.zfsoft.basic.BasicService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_军训结果_Action类
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

public class JxglJxjgAction extends BasicAction {

	/**
	 * 军训管理_军训名单_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxmdManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		JxglJxjgForm myForm = (JxglJxjgForm) form;
		JxglJxjgService service = new JxglJxjgService();
		User user=getUser(request);
		myForm.setUser(user);
		List<HashMap<String,String>>topTr=service.getTopTr(myForm);
		
		service.getJxjzList(request);
		
		
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			String msg = "学生用户没有权限访问该模块，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//===============结果集补空行 begin=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rs";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum =String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);
		rForm.setCommSetting(commSetting);
		service.setRequestValue(rForm, user, request);
		//  ===============结果集补空行 end=================
		
		request.setAttribute("rs", service.getJxmdList(myForm,topTr));
		request.setAttribute("topTr",topTr);
		request.setAttribute("path", "jxgl_jxjg_jxmd.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		//湖州师范学院
		if("10347".equalsIgnoreCase(Base.xxdm)){
			JxglCsszService csszService = new JxglCsszService();
			String lx = csszService.getCssz().get("lx");
			request.setAttribute("lx", lx);
		}
		//湖州师范学院end
		return mapping.findForward("jxmdManage");
	}	
	

	/**
	 * 军训管理_军训名单_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxmdOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxjgForm myForm = (JxglJxjgForm) form;
		JxglJxjgService service = new JxglJxjgService();
		XsxxglService stuService = new XsxxglService();
		String pkValue=request.getParameter("pkValue");
		HashMap<String,String>stuInfo=stuService.selectStuinfo(pkValue);
		User user=getUser(request);
		myForm.setUser(user);
		myForm.setXh(pkValue);
		request.setAttribute("xsjxbz", service.getXsJxbz(myForm));
		request.setAttribute("stuInfo", stuInfo);
		request.setAttribute("path", "jxgl_jxjg_jxmd.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxmdOne");
	}	
	
	/**
	 * 军训管理_军训名单_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxjgService service = new JxglJxjgService();
		JxglJxjgForm myForm = (JxglJxjgForm) form;
		User user=getUser(request);
		myForm.setUser(user);
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.expExcel(myForm, response.getOutputStream());
		
		return mapping.findForward("");
	}	
}
