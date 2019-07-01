package xgxt.dtjs.ntzy.xyhd;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

/**
 * Title: 学生工作管理系统
 * Description:南通职业-校园活动Action
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-9-21
 */
public class NtzyXyhdAction extends BasicExtendAction{
	/**
	 * 校园活动申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xyhdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String user = getUserType2(session);
		String doType = request.getParameter("doType");
		String tableName = "ntzy_xyhdb";
		NtzyXyhdService service = new NtzyXyhdService();
		
		if("save".equalsIgnoreCase(doType)){
			NtzyXyhdForm myForm = (NtzyXyhdForm)form;
			String pkValue = myForm.getSave_sqdw() + myForm.getSave_kssj();
			
			if(service.checkExists(tableName, pkValue)){
				request.setAttribute("message", "增加的培训信息已存在！");
			}else{
				this.insertOperation(request, tableName);
			}
		}
		
		setWriteAbleAndTitle(request, "xyhdsq.do");
		
		request.setAttribute("sqsj", GetTime.getNowTime());
		
		request.setAttribute("userType", user);
		return mapping.findForward("xyhdsq");
	}
	
	/**
	 * 校园活动审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xyhdsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();

		String tableName = "ntzy_xyhdb";
		String viewName = "view_ntzy_xyhd";
		// 需要进行的操作
		String doType = request.getParameter("doType");
		
		// 用户部门
		String userDep = session.getAttribute("userDep").toString();

		String go = request.getParameter("go");

		// 获取userType
		String user = getUserType2(session);

		NtzyXyhdForm myForm = (NtzyXyhdForm) form;

		// 如果是学院，学院部门就确定
		if ("xy".equalsIgnoreCase(user)) {
			myForm.setQueryequals_ssbm(userDep);
		}

		// 审核操作
		if ("sh".equalsIgnoreCase(doType)) {
			// 审核字段
			String shzd = request.getParameter("shzd");
			
			// 审核结果
			String shjg = request.getParameter("shjg");
			
			if (!StringUtils.isNull(shjg)) {
				shjg = "tg".equalsIgnoreCase(shjg) ? "通过" : "不通过";
			}
			
			// 获取页面中以primarykey_为开始的数据
			HashMap<String, String[]> primaryMap = getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY);
			HashMap<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(shzd, shjg);
	
			// 通用审核方法
			auditingBatchOperation(request, primaryMap, valueMap, tableName);
		}
		// 根据页面中的条件获取数据
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[]{"pkValue","disabled","sqdw","zfzr","kssj","bmmc","dd","hdnr","cyrs","xysh","xxsh"};
			setDisabledField(request, user);
			selectPageDataByPagination(request, myForm, tableName, viewName,
					outputColumn);
		}

		setWriteAbleAndTitle(request, "xyhdsh.do");
		request.setAttribute("bmList", DAO.getInstance().getBmListAll());
		request.setAttribute("userType", user);
		return mapping.findForward("xyhdsh");
	}
	
	/**
	 * 校园活动单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xyhdshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String tableName = "ntzy_xyhdb";
		String user = getUserType(session);
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, tableName);
		}
		
		NtzyXyhdService service = new NtzyXyhdService();
		Map<String, String> map = service.getXyhdInfo(pkValue);
		
		request.setAttribute("rs", map);
		request.setAttribute("userType", user);
		return mapping.findForward("xyhdshone");
	}
	
	public ActionForward xyhdViewAndModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "ntzy_xyhdb";
		// 这里pkValue在表里为sqdw||kssj
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		// 区分页面属于什么操作
		String operation = "modi";
		NtzyXyhdService service = new NtzyXyhdService();
		
		if("view".equalsIgnoreCase(doType)){
			operation = "view";
		}
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, tableName);
		}
		
		Map<String, String> map = service.getXyhdInfo(pkValue);
		
		request.setAttribute("operation", operation);
		request.setAttribute("rs", map);
		return mapping.findForward("xyhdview");
	}
	
	/**
	 * 校园活动查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xyhdcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();

		String tableName = "ntzy_xyhdb";
		String viewName = "view_ntzy_xyhd";
		// 需要进行的操作
		String doType = request.getParameter("doType");
		
		// 用户部门
		String userDep = session.getAttribute("userDep").toString();

		String go = request.getParameter("go");

		// 获取userType
		String user = getUserType2(session);

		NtzyXyhdForm myForm = (NtzyXyhdForm) form;

		// 如果是学院，学院部门就确定
		if ("xy".equalsIgnoreCase(user)) {
			myForm.setQueryequals_ssbm(userDep);
		}
		
		if ("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tableName);
		}

		// 根据页面中的条件获取数据
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[]{"pkValue","disabled","sqdw","zfzr","kssj","bmmc","dd","hdnr","cyrs","xysh","xxsh"};
			setDisabledField(request, user);
			selectPageDataByPagination(request, myForm, tableName, viewName,
					outputColumn);
		}

		setWriteAbleAndTitle(request, "xyhdcx.do");
		request.setAttribute("bmList", DAO.getInstance().getBmListAll());
		request.setAttribute("userType", user);
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		return mapping.findForward("xyhdcx");
	}
	
	/**
	 * 校园活动报表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xyhdPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		NtzyXyhdService service = new NtzyXyhdService();
		
		Map<String, String> map = service.getXyhdInfo(pkValue);
		
		request.setAttribute("rs", map);
		return mapping.findForward("xyhdprint");
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
	public ActionForward xyhdExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] output = new String[]{"sqdw","kssj","sqsj","bmmc","zfzr","xcfzr1","fzr1dh","xcfzr2",
				"fzr2dh","dd","hdnr","cyrs","xysh","xxsh","hdfa","sqdwyj"};
		
		expPageData(request, response, "ntzy_xyhdb","view_ntzy_xyhd", output);
		return mapping.findForward("");
	}
}
