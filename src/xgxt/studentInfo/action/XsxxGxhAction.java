package xgxt.studentInfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.XsxxGxhService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_个性化_action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */
public class XsxxGxhAction extends BasicExtendAction{
	/**
	 * 用户维护管理
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cwbhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGxhService service = new XsxxGxhService();
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		User user = getUser(request);
		
		StudentInfoForm myForm = (StudentInfoForm)form;
		
		String userStatus = user.getUserStatus(); 
		
		if("stu".equalsIgnoreCase(userStatus)){
			myForm.setXh(user.getUserName());
		}else if("xy".equalsIgnoreCase(userStatus)){
			myForm.setXydm(user.getUserDep());
		}
		
		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			String[] pkValus = request.getParameterValues("primarykey_cbv");
			String message = service.delRs(pkValus) ? "删除成功！" : "删除失败！";
			
			request.setAttribute("message", message);
			go = "go";
		}
		
		if("go".equalsIgnoreCase(go)){
			request.setAttribute("rs", service.getRs(myForm));
		}
		
		String[] topTr = new String[]{"学号","姓名",Base.YXPZXY_KEY+"名称","专业名称","班级名称","财务编号"};
		// 表头
		request.setAttribute("topTr", topTr);
		// 表头和访问权限
		setWriteAbleAndTitle(request, "stuCwbh.do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("realTable", "xg_xsxx_xscwbhb");
		request.setAttribute("user", user);
		
		return mapping.findForward("cwbhManage");
	}
	
	/**
	 * 用户维护管理
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cwbhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 用户
		User user = getUser(request);
		// 操作
		String doType = request.getParameter("doType");
		
		String xh = user.getUserStatus().equalsIgnoreCase("stu") ? user.getUserName() : request.getParameter("xh");
		
		XsxxGxhService service = new XsxxGxhService();
		
		// 保存操作
		if("save".equalsIgnoreCase(doType)){
			// 提示语
			String message = service.saveCwbh((StudentInfoForm)form) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}else if("del".equalsIgnoreCase(doType)){
			
		}
		
		// 获取学生信息
		if(StringUtils.isNotNull(xh)){
			request.setAttribute("rs",service.getInfo(xh));
		}
		
		request.setAttribute("doType", doType);
		return mapping.findForward("cwbhUpdate");
	}
}
