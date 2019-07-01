/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.mdqr;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.xszz.comm.XszzCommService;

/**
 * MyEclipse Struts Creation date: 12-23-2010
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class MdqrAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/**
	 * 增加项目 Method mdqrXmsz
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward mdqrXmsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		MdqrForm myForm = (MdqrForm) form;
		MdqrService service = new MdqrService();
		XszzCommService xszzComm = new XszzCommService();

		String doType = request.getParameter("doType");
		String gnmk = myForm.getGnmk();
		String tabName = "mdqr_xmszb";

		// 项目保存
		if ("save".equalsIgnoreCase(doType)) {
			this.insertOperation(request, tabName);
			doType = "add";
		}

		// 项目类别列表
		request.setAttribute("doType", doType);
		request.setAttribute("xmdm", xszzComm.getXmbh(tabName, "xmdm"));
		request.setAttribute("xmlbList", service.getXmlbList(gnmk));
		request.setAttribute("path", service.getPath("mdqr.do?method=mdqrWh",
				gnmk));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("mdqrXmsz");
	}

	/**
	 * 修改项目 Method mdqrXmsz
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward mdqrXmUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		MdqrService service = new MdqrService();
		XszzCommService xszzComm = new XszzCommService();

		String doType = request.getParameter("doType");
		String gnmk = request.getParameter("gnmk");

		String pkValue = request.getParameter("pkValue");
		String tabName = "mdqr_xmszb";
		// 项目保存
		if ("modi".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			this.selectPageDataByOne(request, tabName, tabName, pkValue);

		}
		HashMap<String, String> hashMap = (HashMap<String, String>) request
				.getAttribute("rs");
		if ("update".equalsIgnoreCase(doType)) {
			this.updateOperation(request, tabName);
			doType = "modi";
		}

		// 项目类别列表
		request.setAttribute("doType", doType);
		request.setAttribute("xmdm", xszzComm.getXmbh(tabName, "xmdm"));
		request.setAttribute("xmlbList", service.getXmlbList(gnmk));
		request.setAttribute("path", service.getPath("mdqr.do?method=mdqrWh",
				gnmk));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("mdqrXmsz");
	}

	/**
	 * 查询项目信息 Method mdqrXmsz
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward mdqrXmCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		MdqrService service = new MdqrService();
		XszzCommService xszzComm = new XszzCommService();
		MdqrForm myForm = (MdqrForm) form;

		String doType = request.getParameter("doType");
		String gnmk = request.getParameter("gnmk");
		String isFdy = session.getAttribute("isFdy").toString();
		String userType = session.getAttribute("userType").toString();

		myForm.setIsFdy(isFdy);
		myForm.setUserType(userType);
		String tabName = "mdqr_xmszb";
		String viewName = "view_mdqr_xmszb";

		// 访问权限判断
		if ("stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起，学生用户不能访问本页面!");
			return new ActionForward("/errMsg.do", false);
		}

		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, tabName);
			boolean result = Boolean.parseBoolean(request
					.getAttribute("result").toString());
			if (result) {
				result = service.deleteXmxx(myForm);
			}
			request.setAttribute("result", result);
			doType = "query";
		}

		// 项目查询
		if ("query".equalsIgnoreCase(doType)) {
			String[] outputColumn = { "pkValue", "xmdm", "xmmc", "xmlbmc",
					"sqzq", "mdsz", "mdqr", "shjb", "kfsz", "kfsh", "kfqr" };
			request
					.setAttribute(
							"annexTerm",
							" and exists (select xmlbdm from mdqr_xmlbb b where a.xmlbdm=b.xmlbdm and gnmk='"
									+ gnmk + "') ");
			this.selectPageDataByPagination(request, form, tabName, viewName,
					outputColumn);

		}

		service.initXmList(request);
		// 项目类别列表
		request.setAttribute("xmdm", xszzComm.getXmbh(tabName, "xmdm"));
		request.setAttribute("xmlbList", service.getXmlbList(gnmk));
		request.setAttribute("xmList", service.getXmList(myForm
				.getQueryequals_xmlbdm()));
		request.setAttribute("path", service.getPath("mdqr.do?method=mdqrXmCx",
				gnmk));
		request.setAttribute("gnmk", gnmk);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("mdqrXmCx");
	}

	/**
	 * 确认名单维护 Method mdqrWh
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward mdqrWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		MdqrService service = new MdqrService();
		MdqrForm myForm = (MdqrForm) form;
		myForm = service.setFormXx(myForm);

		String doType = request.getParameter("doType");
		String gnmk = request.getParameter("gnmk");
		String tabName = "mdqr_xmszb";
		String isFdy = session.getAttribute("isFdy").toString();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		myForm.setIsFdy(isFdy);
		myForm.setUserType(userType);
		myForm.setUserName(userName);

		myForm.setNd(Base.currNd);
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		myForm.setSbsj(GetTime.getNowTime2());
		myForm.setSave_nd(Base.currNd);
		myForm.setSave_xn(Base.currXn);
		myForm.setSave_xq(Base.currXq);

		// 访问权限判断
		if ("stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起，学生用户不能访问本页面!");
			return new ActionForward("/errMsg.do", false);
		}
		// 名单设置(批量)
		if ("mdsz".equalsIgnoreCase(doType)) {
			service.mdsz(request, myForm);
			// 插入后查询
			doType = "query";
		}

		// 项目维护查询
		if ("query".equalsIgnoreCase(doType)) {
			myForm.setTabName(tabName);
			service.getXmxx(request, myForm);
		}

		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		// 申报时间
		request.setAttribute("nowTime", GetTime.getNowTime2());
		// 用户权限
		request.setAttribute("yhqx", service.getYhqx(myForm));
		// 项目类别
		request.setAttribute("xmlbList", service.getXmlbList(gnmk));
		// 项目
		request.setAttribute("xmList", service.getXmListBySzqx(myForm
				.getXmlbdm(), service.getYhqx(myForm)));
		request.setAttribute("path", service.getPath("mdqr.do?method=mdqrWh",
				gnmk));
		request.setAttribute("gnmk", gnmk);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("mdqrWh");
	}

	/**
	 * 确认名单查询 Method mdqrCx
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward mdqrCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		MdqrService service = new MdqrService();
		MdqrForm myForm = (MdqrForm) form;

		String isFdy = session.getAttribute("isFdy").toString();
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		String tabName = "mdqr_xmnrb";
		String viewName = "view_mdqr_xmnrb";
		String gnmk = request.getParameter("gnmk");

		myForm.setIsFdy(isFdy);
		myForm.setUserType(userType);

		// 访问权限判断
		if ("stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起，学生用户不能访问本页面!");
			return new ActionForward("/errMsg.do", false);
		}
		// 名单设置(批量)
		if ("mdsz".equalsIgnoreCase(doType)) {
			service.mdsz(request, myForm);
			// 插入后查询
			doType = "query";
		}

		// 项目维护查询
		if ("query".equalsIgnoreCase(doType)) {
			// 根据周期获取结果集字段
			String[] outputColumn = service.getOutPut(myForm);

			this.selectPageDataByPagination(request, myForm, tabName, viewName,
					outputColumn);
		}

		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("xmlbList", service.getXmlbList(gnmk));
		request.setAttribute("xmList", service.getXmList(myForm
				.getQueryequals_xmlbdm()));
		request.setAttribute("path", service.getPath("mdqr.do?method=mdqrCx",
				gnmk));
		request.setAttribute("gnmk", gnmk);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("mdqrCx");
	}

	/**
	 * 名单设置审核 Method mdqrCx
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward mdqrSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		MdqrService service = new MdqrService();
		MdqrForm myForm = (MdqrForm) form;

		String isFdy = session.getAttribute("isFdy").toString();
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		String tabName = "mdqr_xmnrb";
		String viewName = "view_mdqr_xmnrb";
		String gnmk = request.getParameter("gnmk");

		myForm.setIsFdy(isFdy);
		myForm.setUserType(userType);

		if ("true".equalsIgnoreCase(isFdy) || "stu".equalsIgnoreCase(userType)) {
			request.setAttribute("errMsg", "对不起，您没有访问该页面的权限!");
			return new ActionForward("/errMsg.do", false);
		}

		// 名单设置审核(批量)
		if ("mdsz".equalsIgnoreCase(doType)) {
			// 将项目级别存如FORM中
			HashMap<String, String[]> primaryMap = getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY);
			// 审核时保存的值
			HashMap<String, String> valueMap = service.getShValMap(myForm);
			this.auditingBatchOperation(request, primaryMap, valueMap, tabName);
			// 插入后查询
			doType = "query";
		}

		// 项目审核查询
		if ("query".equalsIgnoreCase(doType)) {
			// CHECKBOX的DISABLED属性
			service.setDisabled(request, myForm);
			// 获取查询时显示字段
			String[] outputColumn = service.getOutputArr(myForm);
			// 审核级别
			service.checkShjb(request, myForm);
			// 审核条件
			service.getShTj(request, myForm);
			this.selectPageDataByPagination(request, myForm, tabName, viewName,
					outputColumn);
		}

		myForm.setQueryequals_xn(Base.currXn);
		myForm.setQueryequals_xq(Base.currXq);
		myForm.setQueryequals_nd(Base.currNd);
		myForm.setSave_nd(Base.currNd);
		myForm.setSave_xn(Base.currXn);
		myForm.setSave_xq(Base.currXq);

		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);

		request.setAttribute("xmlbList", service.getXmlbList(gnmk));
		// 获取需要审核的项目列表
		request.setAttribute("xmList", service.getXmListByShqx(myForm
				.getQueryequals_xmlbdm(), service.getYhqx(myForm)));
		request.setAttribute("gnmk", gnmk);
		request.setAttribute("yhqx", service.getYhqx(myForm));
		request.setAttribute("path", service.getPath("mdqr.do?method=mdqrSh",
				gnmk));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("mdqrSh");
	}

	/**
	 * mdqrQrcx 名单确认信息查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mdqrQrcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		MdqrService service = new MdqrService();
		MdqrForm myForm = (MdqrForm) form;

		String isFdy = session.getAttribute("isFdy").toString();
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		String tabName = "mdqr_xmnrb";
		String viewName = "view_mdqr_xmnrb";
		String gnmk = request.getParameter("gnmk");

		myForm.setIsFdy(isFdy);
		myForm.setUserType(userType);

		// 名单确认查询
		if ("query".equalsIgnoreCase(doType)) {
			// 根据周期获取显示结果
			String[] outputColumn = service.getOutPut(myForm);

			request.setAttribute("annexTerm", " and mdqr='checked' ");
			this.selectPageDataByPagination(request, form, tabName, viewName,
					outputColumn);
		}

		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("xmlbList", service.getXmlbList(gnmk));
		request.setAttribute("xmList", service.getXmList(myForm
				.getQueryequals_xmlbdm()));
		request.setAttribute("path", service.getPath("mdqr.do?method=mdqrCx",
				gnmk));
		request.setAttribute("gnmk", gnmk);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("mdqrQrcx");
	}

	/**
	 * 名单确认 Method mdqrQr
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward mdqrQr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		MdqrService service = new MdqrService();
		MdqrForm myForm = (MdqrForm) form;
		myForm = service.setFormXx(myForm);

		String mdqr = request.getParameter("mdqr");
		String doType = request.getParameter("doType");
		String tabName = "mdqr_xmnrb";
		String gnmk = request.getParameter("gnmk");
		String userType = session.getAttribute("userType").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		String xmdm = request.getParameter("xmdm");
		xmdm = ("".equalsIgnoreCase(xmdm) || xmdm == null) ? (myForm
				.getQueryequals_xmdm()) : xmdm;
		myForm.setNd(Base.currNd);
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		myForm.setUserType(userType);
		myForm.setIsFdy(isFdy);
		myForm.setXmdm(xmdm);

		// 访问权限判断
		if ("stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起，学生用户不能访问本页面!");
			return new ActionForward("/errMsg.do", false);
		}
		
		if("".equalsIgnoreCase(doType) || doType==null){
			myForm.setGnmk(gnmk);
			service.initXmdm(myForm);
			xmdm=myForm.getXmdm();
			myForm.setTabName(tabName);
			service.getMdqrXx(request, myForm);
		}
		
		// 名单确认(批量)
		if ("mdqr".equalsIgnoreCase(doType)) {
			// 将项目级别存如FORM中
			HashMap<String, String[]> primaryMap = getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY);
			// 审核时保存的值
			HashMap<String, String> valueMap = service.getQrValMap(mdqr);
			this.auditingBatchOperation(request, primaryMap, valueMap, tabName);
			// 插入后查询
			doType = "query";
		}

		// 项目维护查询
		if ("query".equalsIgnoreCase(doType)) {
			myForm.setTabName(tabName);
			service.getMdqrXx(request, myForm);
		} else {
			// 没有查询时,补全数据行数
			service.getXx(request);
		}

		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		myForm.setNd(Base.currNd);
		myForm.setSave_nd(Base.currNd);
		myForm.setSave_xn(Base.currXn);
		myForm.setSave_xq(Base.currXq);

		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);

		// 每页显示记录数
		request.setAttribute("pageSize", myForm.pages.getPageSize());
		// 项目类别代码
		request.setAttribute("xmlbList", service.getXmlbList(gnmk));
		// 获取需要审核的项目列表
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", service.getXqmc(Base.currXq));
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("xmList", service.getXmShList(myForm
				.getQueryequals_xmlbdm(), service.getYhqx(myForm)));
		request.setAttribute("yhqx", service.getYhqx(myForm));
		request.setAttribute("path", service.getPath("mdqr.do?method=mdqrQr",
				gnmk));
		request.setAttribute("gnmk", gnmk);
		request.setAttribute("xmdm", xmdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("mdqrQr");
	}

	/**
	 * 信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward expDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MdqrForm myForm = (MdqrForm) form;

		try {
			this.expPageData(request, response, myForm.getTabName(), myForm
					.getViewName(), null);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return mapping.findForward("success");
	}
}