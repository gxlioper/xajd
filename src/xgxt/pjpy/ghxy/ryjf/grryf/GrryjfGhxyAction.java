package xgxt.pjpy.ghxy.ryjf.grryf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.pjpy.ghxy.ryjf.PjpyRyjfForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

/**
 * Title: 学生工作管理系统
 * Description:硅湖学院个人荣誉加分Action
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-8-05
 */
public class GrryjfGhxyAction extends BasicExtendAction{
	/**
	 * 硅湖学院个人加分申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		
		PjpyRyjfForm pForm = (PjpyRyjfForm)form;
		RyjfModel model = new RyjfModel();
		BeanUtils.copyProperties(model, pForm);
		
		Map<String, String> stuInfo = null;
		GrryjfGhxyService service = new GrryjfGhxyService();
		
		//获得学年，学号，学期
		String xh = "stu".equalsIgnoreCase(userType) ? userName : request.getParameter("xh");
		String xn = Base.currXn;
		String xq = Base.currXq;
		
		boolean isApply = true;
		
		if(StringUtils.isNotNull(xh)){
			stuInfo = service.getStuInfo(xh);
			
			// 如果已经被审核，不能修改
			if(service.isAuditing(xh, xn, xq)){
				isApply = false;
				request.setAttribute("msg", "您的数据已经被上级审核，不能申请");
			}
		}
		
		if("add".equalsIgnoreCase(doType)){
			// 把申请数据插入表中
			String msg = service.insertRecord(model) ? "申请成功" : "申请失败"; 
			request.setAttribute("msg", msg);
		}
		
		// 获取writeAble和title
		setWriteAbleAndTitle(request, "pjpyryjfsq.do");
		request.setAttribute("xn", xn);
		request.setAttribute("xqdm", xq);
		request.setAttribute("xq", BasicExtendService.xqMap.get(xq));
		request.setAttribute("isApply", isApply);
		request.setAttribute("rs", stuInfo);
		return mapping.findForward("grjfsq");
	}
	
	/**
	 * 硅湖学院个人加分审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String tableName = "ghxy_grjfb";
		String viewName = "view_ghxy_grjf";
		// 需要进行的操作
		String doType = request.getParameter("doType");

		String go = request.getParameter("go");

		// 获取userType
		String user = getUserType(request);

		if(!("fdy".equalsIgnoreCase(user) || "njbzr".equalsIgnoreCase(user)||"xx".equalsIgnoreCase(user))){
			request.setAttribute("errMsg", "对不起,您没有该模块权限！");
			return mapping.findForward("error");
		}
		
		PjpyRyjfForm gForm = (PjpyRyjfForm) form;

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
			String[] outputColumn = new String[]{"pkValue","disabled","xh","xm","nj","xymc","zymc","bjmc","fdysh","njbzrsh","xxsh"};
			setDisabledField(request, user);
			selectPageDataByPagination(request, gForm, tableName, viewName,
					outputColumn);
		}

		setWriteAbleAndTitle(request, "pjpyryjfsh.do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xq", BasicExtendService.xqMap.get(Base.currXq));
		request.setAttribute("userType", user);
		
		return mapping.findForward("grjfsh");
	}
	
	/**
	 * 硅湖学院个人加分单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String user = getUserType(request);
		String doType = request.getParameter("doType");
		// 表ghxy_grjfb的主键：xh||xn||xq
		String pkValue = request.getParameter("pkValue");
		String xh = request.getParameter("xh");
		String xn = Base.currXn;
		String xq = Base.currXq;
		
		GrryjfGhxyService service = new GrryjfGhxyService();
		Map<String, String> stuInfo = service.getStuInfo(xh); 
		Map<String, String> grjfInfo = service.getGrjfInfo(pkValue);
		
		if("save".equalsIgnoreCase(doType)){
			PjpyRyjfForm pForm = (PjpyRyjfForm)form;
			RyjfModel model = new RyjfModel();
			BeanUtils.copyProperties(model, pForm);
			String msg = service.updateRecord(model) ? "审核成功" : "审核失败"; 
			request.setAttribute("msg", msg);
		}
		
		request.setAttribute("rs", stuInfo);
		request.setAttribute("grjfInfo", grjfInfo);
		request.setAttribute("xn", xn);
		request.setAttribute("xqdm", xq);
		request.setAttribute("xq", BasicExtendService.xqMap.get(xq));
		request.setAttribute("nowtime", GetTime.getNowTime());
		request.setAttribute("userType", user);
		return mapping.findForward("grjfshone");
	}
	
	/**
	 * 硅湖学院个人加分单个查看和修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfViewAndModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String user = getUserType(request);
		String doType = request.getParameter("doType");
		// 表ghxy_grjfb的主键：xh||xn||xq
		String pkValue = request.getParameter("pkValue");
		String xh = request.getParameter("xh");

		
		GrryjfGhxyService service = new GrryjfGhxyService();
		Map<String, String> stuInfo = service.getStuInfo(xh); 
		Map<String, String> grjfInfo = service.getGrjfInfo(pkValue);
		
		String operation = "modi".equalsIgnoreCase(doType) ? "modi" : "view";
		
		if("save".equalsIgnoreCase(doType)){
			PjpyRyjfForm pForm = (PjpyRyjfForm)form;
			RyjfModel model = new RyjfModel();
			BeanUtils.copyProperties(model, pForm);
			
			String msg = service.insertRecord(model) ? "修改成功" : "修改失败"; 
			request.setAttribute("msg", msg);
		}
		
		request.setAttribute("rs", stuInfo);
		request.setAttribute("grjfInfo", grjfInfo);
		request.setAttribute("operation", operation);
		request.setAttribute("userType", user);
		return mapping.findForward("grjfview");
	}
	
	/**
	 * 硅湖学院个人加分查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String tableName = "ghxy_grjfb";
		String viewName = "view_ghxy_grjf";
		
		// 需要进行的操作
		String doType = request.getParameter("doType");

		String go = request.getParameter("go");

		// 获取userType
		String user = getUserType(request);

		PjpyRyjfForm gForm = (PjpyRyjfForm) form;
		
		GrryjfGhxyService service = new GrryjfGhxyService();
		
		setWriteAbleAndTitle(request, "pjpyryjfcx.do");

		if ("stu".equalsIgnoreCase(user)) {
			String[] output = new String[]{"xh","xm","xn","nj","xq","xymc","zymc","bjmc","ryjf","fdysh","njbzrsh","xxsh"};
			
			// 获得中文字段名
			request.setAttribute("topTr", DAO.getInstance().getColumnNameCN(output, viewName));
			// 记录
			request.setAttribute("rs", service.getGrjfInfoForStu(userName, output));
			
			return mapping.findForward("grjfcxforstu");
		}
		
		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			PjpyRyjfForm pForm = (PjpyRyjfForm)form;
			String msg = service.delRecord(pForm.getPkValues()) ? "删除成功" : "删除失败"; 
			request.setAttribute("msg", msg);
		}

		// 根据页面中的条件获取数据
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[]{"pkValue","disabled","xh","xm","xn","nj","xq","xymc","zymc","bjmc","ryjf","fdysh","njbzrsh","xxsh"};
			setDisabledField(request, user);
			selectPageDataByPagination(request, gForm, tableName, viewName,
					outputColumn);
		}
		
		if("xy".equalsIgnoreCase(user)){
			gForm.setQueryequals_xydm(userDep);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("userType", user);
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		return mapping.findForward("grjfcx");
	}
	
	/**
	 * 硅湖学院荣誉加分汇总
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjfhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String viewName = "view_ghxy_xxjfhz";
		
		String go = request.getParameter("go");

		// 获取userType
		String user = getUserType(request);

		PjpyRyjfForm gForm = (PjpyRyjfForm) form;

		
		setWriteAbleAndTitle(request, "pjpyjfhz.do");

		if ("stu".equalsIgnoreCase(user)) {
			gForm.setQuerylike_xh(userName);
		}else if("xy".equalsIgnoreCase(user)){
			gForm.setQueryequals_xydm(userDep);
		}


		// 根据页面中的条件获取数据
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[]{"xh","xm","xn","xqmc","xymc","zymc","bjmc","grhjf",
					"bjbzf","qsryf","bjryf","ryjf","ryszf","zf"};
			selectPageDataByPagination(request, gForm, "", viewName,
					outputColumn);
		}
		
		setWriteAbleAndTitle(request, "pjpyhzcx." +
				"do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("userType", user);
		request.setAttribute("tableName", viewName);
		return mapping.findForward("ryjfhz");
	}
	
	/**
	 * 荣誉积分汇总导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjfhzExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = "view_ghxy_xxjfhz";
		// 需要导出的字段
			String[] output = {"xh","xm","xn","xqmc","xymc","zymc","bjmc","grhjf",
					"bjbzf","qsryf","bjryf","ryjf","ryszf","zf"};
		
		expPageData(request, response, "",viewName, output);
		return mapping.findForward("");
	}
	
	 /** 硅湖学院个人加分导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "ghxy_grjfb";
		String viewName = "view_ghxy_grjf";
		// 需要导出的字段
			String[] output = { "xh","xm","xn","nj","xq","xymc","zymc","bjmc","fdysh","njbzrsh","xxsh",
					"fdyshsj","njbzrshsj","xxshsj","ryjf"};
		
		expPageData(request, response, tableName,viewName, output);
		return mapping.findForward("");
		 
	 }
	 
	/**
	 * 获取用户类型
	 * @param request
	 * @return
	 */
	private String getUserType(HttpServletRequest request){
		HttpSession session = request.getSession();
		GhxyNjzrwhService service = new GhxyNjzrwhService();
		
		List<HashMap<String, String>> list = service.getFdyNj(session.getAttribute("userName").toString());
		String userType = session.getAttribute("userType").toString();
		
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());
		if(isFdy){
			userType = "fdy";
		}else if(list != null && list.size()>0){
			userType = "njbzr";
			// 放入该年级部主任可查询的年级
			request.setAttribute("njListForBzr", list);
		}else if("xy".equalsIgnoreCase(userType)){
			userType = "xy";
		}else if("admin".equalsIgnoreCase(userType) || ("xx".equalsIgnoreCase(userType))){
			userType = "xx";
		}
		return userType;
	}
	
	/**
	 * 显示需要获取数据库的字段包括特殊字段,用于三级审核
	 * @param request
	 * @param user
	 * @return
	 */
	protected void setDisabledField(HttpServletRequest request, String user){
		// 获取上级是否审核通过信息，返回个页面
		if("fdy".equalsIgnoreCase(user) || "xy".equalsIgnoreCase(user)){
			request.setAttribute("clientColumns", "(case njbzrsh when '通过' then 'disabled' else '' end)disabled,");
		}else if("njbzr".equalsIgnoreCase(user)){
			request.setAttribute("clientColumns", "(case xxsh when '通过' then 'disabled' else '' end)disabled,");
		}else{
			request.setAttribute("clientColumns", "'' disabled,");
		}
	}
}
