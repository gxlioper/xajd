/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.qgzx.zgmsxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块中国美术学院Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-11-04</p>
 */
public class QgzxZgmsxyAction extends DispatchAction {
	
	/** 
	 * 按勤工助学考勤支付酬金
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward disbursePay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pkValue = request.getParameter("pkValue");
		String pk = request.getParameter("pk");
		QgzxZgmsxyService service = new QgzxZgmsxyService();
		
		request.setAttribute("gwInfo", service.getGwInfo(pk,pkValue));//岗位信息
		request.setAttribute("stuInfoList", service.getStuPayInfo(pk,pkValue));//岗位的学生酬金信息
		return mapping.findForward("disbursePay");
	}
}