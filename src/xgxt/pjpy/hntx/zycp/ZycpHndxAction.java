package xgxt.pjpy.hntx.zycp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.pjpy.hntx.dypy.DypyForm;
import xgxt.utils.FormModleCommon;

/**
 * Title: 学生工作管理系统
 * Description:海南大学智育测评分Action
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-8-25
 */
public class ZycpHndxAction extends BasicExtendAction{
	
	/**
	 * 海南大学智育测评维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zycpwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		// 需要同步的学年
		String tbxn = request.getParameter("tbxn");
		String viewName = "view_hndx_xszycpf";
		// 需要进行的操作
		String doType = request.getParameter("doType");
		
		// 用户部门
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		
		String go = request.getParameter("go");

		// 获取userType
		String user = getUserType(session);

		DypyForm gForm = (DypyForm) form;

		ZycpHndxService service = new ZycpHndxService();
		if("tb".equalsIgnoreCase(doType)){
			String msg = service.tbsj(tbxn) ? "同步成功" : "同步失败";
			request.setAttribute("msg", msg);
		}
		
		// 如果是学院，学院部门就确定
		if("stu".equalsIgnoreCase(user)){
			gForm.setQuerylike_xh(userName);
			request.setAttribute("xh", userName);
		} else if ("xy".equalsIgnoreCase(user)) {
			gForm.setQueryequals_xydm(userDep);
		}
		
		// 根据页面中的条件获取数据
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[]{"xh","xm","xn","xymc","zymc","bjmc","pjxfjd","zyzf"};
			selectPageDataByPagination(request, gForm, "", viewName,
					outputColumn);
		}

		setWriteAbleAndTitle(request, "hndx_zycp.do?method=zycpwh");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("userType", user);
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", "");
		return mapping.findForward("zycpwh");
	}
	
	/**
	 * 通用导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zycpfExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] output = new String[]{"xh","xm","xn","xymc","zymc","bjmc","pjxfjd","zyzf"};
		
		expPageData(request, response, "","view_hndx_xszycpf", output);
		return mapping.findForward("");
	}
}
