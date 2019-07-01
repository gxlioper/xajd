package xgxt.dtjs.gdby.tygl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

/**
 * Title: 学生工作管理系统 Description:广东白云 推优管理 Action Module:党团建设 - 团员信息 Copyright:
 * Copyright (c) 2010 Company: zfsoft Author: sjf Version: 1.0 Time: 2010-7-30
 */
public class TyglAction extends BasicExtendAction {

	/**
	 * 获取填写了入党申请书的学生
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String tableName = "czxx_rdsqb";
		String viewName = "view_czxx_rdsq";
		String go = request.getParameter("go");
		TyglForm tForm = (TyglForm)form;
		
		if ("go".equalsIgnoreCase(go)) {
			String[] output = new String[] { "xh", "xm", "xb", "nj", "xymc",
					"xymc", "bjmc" };
			this.selectPageDataByPagination(request, form, tableName, viewName,
					output);
		}

		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		String user = getUserType(session);
		String isFdy = session.getAttribute("isFdy").toString();
		String isBzr = session.getAttribute("isBzr").toString();

		if("xy".equalsIgnoreCase(user)){
			tForm.setQueryequals_xydm(userDep);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("userName", userName);
		request.setAttribute("userType", user);
		request.setAttribute("isFdy", isFdy);
		request.setAttribute("isBzr", isBzr);
		return mapping.findForward("sturdsqinfo");
	}

	/**
	 * 团员推优申请,只有填写入党申请的学生有资格申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		String tableName = "gdby_tytysqb";
		String xn = Base.currXn;
		
		TyglGdbyService service = new TyglGdbyService();
		String xh = "stu".equalsIgnoreCase(userType) ? userName : request
				.getParameter("save_xh");

		boolean isApply = true;

	
		if (StringUtils.isNotNull(xh)) {
			Map<String, String> map = service.getTyStuInfo(xh);
			String isExists = map.get("isExists");

			// 已经被审核的学生不能申请,提交入党申请书的学生才能申请推优团员
			if(service.isAuditing(xh, xn)){
				isApply = false;
				request.setAttribute("msg", "正在被审核，不能申请");
			} else if ("false".equalsIgnoreCase(isExists)) {
				isApply = false;
				request.setAttribute("msg", "未提交入党申请书，不能申请");
			}
			request.setAttribute("rs", map);

		}

		if ("add".equalsIgnoreCase(doType)) {
			// 判断要增加的数据是否存在
			BaseDAO baseDao = new BaseDAO();
			String pkVal = xh+xn;
			boolean isExists = baseDao.checkExists(tableName, "xh||xn", pkVal);
			
			// 如果存在执行update操作，不存在执行插入操作
			if(isExists){
				updateOperation(request, tableName);
			}else{
				insertOperation(request, tableName);
			}
		}

		setWriteAbleAndTitle(request, "gdby_tygl.do?method=tysq");
		request.setAttribute("sqsj", GetTime.getNowTime());
		request.setAttribute("isApply", isApply);
		request.setAttribute("xn", xn);
		request.setAttribute("xq", BasicExtendService.xqMap.get(Base.currXq));
		return mapping.findForward("tysq");
	}

	/**
	 * 审核申请了推优团员的学生，三级审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();

		String tableName = "gdby_tytysqb";
		String viewName = "view_gdby_tytysq";
		// 需要进行的操作
		String doType = request.getParameter("doType");
		
		// 用户部门
		String userDep = session.getAttribute("userDep").toString();

		String go = request.getParameter("go");

		// 获取userType
		String user = getUserType(session);

		TyglForm gForm = (TyglForm) form;

		// 如果是学院，学院部门就确定
		if ("xy".equalsIgnoreCase(user)) {
			gForm.setQueryequals_xydm(userDep);
		}

		// 审核操作
		if ("sh".equalsIgnoreCase(doType)) {
			// 审核字段
			String shzd = request.getParameter("shzd");
			
			// 审核结果
			String shjg = request.getParameter("shjg");
			
			// 审核时间
			String shsj = request.getParameter("shsj");
			
			if (!StringUtils.isNull(shjg)) {
				shjg = "tg".equalsIgnoreCase(shjg) ? "通过" : "不通过";
			}
			
			// 获取页面中以primarykey_为开始的数据
			HashMap<String, String[]> primaryMap = getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY);
			HashMap<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(shzd, shjg);
			valueMap.put(shsj, GetTime.getNowTime());

			// 通用审核方法
			auditingBatchOperation(request, primaryMap, valueMap, tableName);
		}
		// 根据页面中的条件获取数据
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[]{"pkValue","disabled","xh","xm","xymc","zymc","bjmc","fdysh","xysh","xxsh"};
			setDisabledField(request, user);
			selectPageDataByPagination(request, gForm, tableName, viewName,
					outputColumn);
		}

		setWriteAbleAndTitle(request, "gdby_tygl.do?method=tysh");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("userType", user);
		return mapping.findForward("tysh");
	}

	/**
	 * 单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tyshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String tableName = "gdby_tytysqb";
		String user = getUserType(session);
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		TyglGdbyService service = new TyglGdbyService();
		Map<String, String> map = service.getTysqxx(pkValue);
		
		if("update".equalsIgnoreCase(doType)){
			this.updateOperation(request, tableName);
		}
		
		request.setAttribute("rs", map);
		request.setAttribute("nowtime", GetTime.getNowTime());
		request.setAttribute("userType", user);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("tyshone");
	}

	/**
	 * 推优查看和修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tyViewAndModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String tableName = "gdby_tytysqb";
		String user = getUserType(session);
		// 这里pkValue在表里为xh||xn
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		// 区分页面属于什么操作
		String operation = "modi";
		TyglGdbyService service = new TyglGdbyService();
		
		Map<String, String> map = service.getTysqxx(pkValue);
		
		if("view".equalsIgnoreCase(doType)){
			operation = "view";
		}
		
		if("update".equalsIgnoreCase(doType)){
			this.updateOperation(request, tableName);
		}
		
		request.setAttribute("operation", operation);
		request.setAttribute("rs", map);
		request.setAttribute("userType", user);
		return mapping.findForward("tyviewandmodi");
	}
	
	/**
	 * 查询审核结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tycx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();

		String tableName = "gdby_tytysqb";
		String viewName = "view_gdby_tytysq";
		// 需要进行的操作
		String doType = request.getParameter("doType");
		
		// 用户部门
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		
		String go = request.getParameter("go");

		// 获取userType
		String user = getUserType(session);

		TyglForm gForm = (TyglForm) form;

		// 如果是学院，学院部门就确定
		if("stu".equalsIgnoreCase(user)){
			gForm.setQuerylike_xh(userName);
			request.setAttribute("xh", userName);
		} else if ("xy".equalsIgnoreCase(user)) {
			gForm.setQueryequals_xydm(userDep);
		}

		if("del".equalsIgnoreCase(doType)){
			deleteOperation(request, tableName);
		}
		
		// 根据页面中的条件获取数据
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[]{"pkValue","disabled","xh","xm","xn","xymc","zymc","bjmc","fdysh","xysh","xxsh"};
			setDisabledField(request, user);
			selectPageDataByPagination(request, gForm, tableName, viewName,
					outputColumn);
		}

		setWriteAbleAndTitle(request, "gdby_tygl.do?method=tycx");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("userType", user);
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		return mapping.findForward("tycx");
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
	public ActionForward tyExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] output = new String[]{"xh","xm","xymc","zymc","bjmc","fdysh","xysh","xxsh",
				"sqsj","fdyshsj","xyshsj","xxshsj"};
		
		expPageData(request, response, "gdby_tytysqb","view_gdby_tytysq", output);
		return mapping.findForward("");
	}
}
