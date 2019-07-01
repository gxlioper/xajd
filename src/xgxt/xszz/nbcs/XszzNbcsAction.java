package xgxt.xszz.nbcs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.xszz.XszzTyForm;

import com.zfsoft.basic.BasicAction;

public class XszzNbcsAction extends BasicAction {
	private static final String QUERY = "query";
	private static final String VIEW = "view";
	private static final String SAVE = "save";
	private static final String EXPDATA = "expData";
	private static final String UPDATE = "update";
	private static final String DELETE = "delete";
	private static final String SH = "sh";
	
	/**
	 * 助学贷款审核
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzNbcsService service = new XszzNbcsService();
		XszzTyForm myForm = (XszzTyForm) form;
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		String tableName = "gjzxdkb";
		String viewName = "view_gjzxdkb";
		String[] outputColumn = null;
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			outputColumn = new String[] {"disabled", "bgcolor", "pkValue",
					"xh","xm","nj","xymc","zymc","bjmc","hdbh","xysh"};
			request.setAttribute("shzd", "xysh");
		} else if ("xx".equals(userType) || "admin".equals(userType)) {
			outputColumn = new String[] {"disabled", "bgcolor", "pkValue",
					"xh","xm","nj","xymc","zymc","bjmc","hdbh","xxsh"};
			request.setAttribute("shzd", "xxsh");
		} else {
			request.setAttribute("errMsg", "对不起，您无权访问此页！");
			return new ActionForward("/errMsg.do",false);
		}
		
		if (!Base.isNull(doType) && QUERY.equals(doType)) {
			service.getCustomAudiColumn(request, userType);
			selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		if (!Base.isNull(doType) && SH.equals(doType)) {
			this.auditingBatchOperation(request, tableName);
		}
		
		service.setList(request, "");
		request.setAttribute("path", "xszz_nbcs_zxdksh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zxdksh");
	}
	
	/**
	 * 助学贷款结果查询
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdkcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNbcsService service = new XszzNbcsService();
		XszzTyForm myForm = (XszzTyForm) form;
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		String tableName = "gjzxdkb";
		String viewName = "view_gjzxdkb";
		String[] outputColumn = new String[] {"disabled", "bgcolor", "pkValue",
				"xh","xm","nj","xymc","zymc","bjmc","hdbh","xysh","xxsh"};
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		if (!Base.isNull(doType) && QUERY.equals(doType)) {
			service.getCustomAudiColumn(request, userType);
			selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		if (!Base.isNull(doType) && DELETE.equals(doType)) {
			deleteOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) && EXPDATA.equals(doType)) {
			String[] colList = new String[] {"xh","xm","nj","xymc","zymc","bjmc",
					"hdbh","zje","sqsj","xysh","xxsh"};
			expPageData(request, response, tableName, viewName, colList);
			return mapping.findForward("");
		}
		
		service.setList(request, "");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "xszz_nbcs_zxdkcx.do");
		FormModleCommon.commonRequestSet(request);
		
		
		return mapping.findForward("zxdkcx");
	}
	
	/**
	 * 助学贷款单条维护
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdkUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNbcsService service = new XszzNbcsService();
		
		String tableName = "gjzxdkb";
		String viewName = "view_gjzxdkb";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		selectPageDataByOne(request, tableName, viewName, pkValue);
		
		if (VIEW.equals(doType)) {
			return new ActionForward("/xszz/nbcs/zxdkView.jsp",false);
		}
		
		if (SAVE.equals(doType)) {
			updateOperation(request, tableName);
		}
		
		service.setList(request, "");
		return mapping.findForward("zxdkUpdate");
	}
	
	
	/**
	 * 发放贷款查询
	 * @return
	 * @throws Exception
	 */
	public ActionForward ffdkManager(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		XszzNbcsService service = new XszzNbcsService();
		XszzTyForm myForm = (XszzTyForm) form;
		
		String tableName = "zxdk_nbcs_dkff";
		String viewName = "view_gjzxdkb";
		String doType = request.getParameter("doType");
		String[] outputColumn = new String[] {"pkValue","xh","xm","nj","xymc","zymc","bjmc","zje","hdbh"};
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		if (!Base.isNull(doType) && QUERY.equals(doType)) {
			selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		service.setList(request, "");
		request.setAttribute("path", "xszz_nbcs_ffdk.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ffdkManager");
	}
	
	/**
	 * 发放贷款
	 * @return
	 * @throws Exception
	 */
	public ActionForward ffdkUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzNbcsService service = new XszzNbcsService();
		
		String realTable = "zxdk_nbcs_dkff";
		String viewName = "view_gjzxdkb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pk");
		String xh = request.getParameter("save_xh");
		
		selectPageDataByOne(request, realTable, viewName, pkValue);
		
		if (SAVE.equals(doType)) {
			if (service.getIsFfdk(xh)) {
				updateOperation(request, realTable);
			} else {
				insertOperation(request, realTable);
			}
		}
		
		request.setAttribute("doType", doType);
		return mapping.findForward("ffdkUpdate");
	}
}
