package xgxt.pjpy.zjkjxy;

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
import xgxt.base.Excel2Oracle;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.zjcm.cssz.PjpyZjcmCsszModel;
import xgxt.pjpy.zjcm.cssz.PjpyZjcmCsszService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

import common.Globals;
import common.GlobalsVariable;

public class PjpyZjkjxyAction extends CommonAction{
	
	/**
	 * 人数设置管理
	 * */
	public ActionForward rsszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm thisForm = (PjpyZjkjxyActionForm) form;
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		String userType = getUserType(request);
		String msg = "";//提示信息
		//评奖周期
		String pjzq = zjkjService.getPjpySqzq();
		//用于判断学生用户不能进入该模块
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(userType)) {
			msg = "当前用户无权访问该页面！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//用户判断该功能是否开放
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_RSTZ)
				&& zjkjService.checkKgflag()){
			msg = "该功能暂时不开放操作！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		setXydm(request, thisForm);
		setPjxnndxq(thisForm);
		
		if (StringUtils.isNull(thisForm.getKey())) {
			thisForm.setKey(GlobalsVariable.PJPY_JXJ);
		}
		
		//查询数据操作
		String key = thisForm.getKey();
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if (QUERY.equalsIgnoreCase(thisForm.getAct())) {
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, thisForm);
			topTr = service.queryJxjrsTitle(key);
			rs = (ArrayList<String[]>) service.queryJxjrsResult(model, key);
		}
		
		//设置相关作用域数据
		request.setAttribute("path", "pjpyrssz.do");
		request.setAttribute("pjzq", pjzq);//评奖周期
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("dmList", service.getDmList(key,
				thisForm.getJxjlb()));
		request.setAttribute("lbList", zjkjService.getJxjlbList(true));
		request.setAttribute("cpfwList", service.getCpfwList(false));
		return mapping.findForward("rsszManage");
	}

	/**
	 * 荣誉称号人数比例设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychRsszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm thisForm = (PjpyZjkjxyActionForm) form;
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		String userType = getUserType(request);
		String msg = "";//提示信息
		//用于判断学生用户不能进入该模块
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(userType)) {
			msg = "当前用户无权访问该页面！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//用户判断该功能是否开放
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_RSTZ)
				&& zjkjService.checkKgflag()){
			msg = "该功能暂时不开放操作！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
			
		
		setXydm(request, thisForm);
		setPjxnndxq(thisForm);
		
		if (StringUtils.isNull(thisForm.getKey())) {
			thisForm.setKey(GlobalsVariable.PJPY_RYCH);
		}
		
		//查询数据操作
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if (QUERY.equalsIgnoreCase(thisForm.getAct())) {
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, thisForm);
			topTr = service.queryJxjrsTitle(thisForm.getKey());
			rs = (ArrayList<String[]>) service.queryJxjrsResult(model, thisForm.getKey());
		}
		
		//设置相关作用域数据
		request.setAttribute("path", "pjpyrssz.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("dmList", service.getDmList(thisForm.getKey(),
				thisForm.getJxjlb()));
		request.setAttribute("lbList", zjkjService.getJxjlbList(true));
		request.setAttribute("cpfwList", service.getCpfwList(false));
		request.setAttribute("pjzq", zjkjService.getPjpySqzq());
		return mapping.findForward("rychRsszManage");
	}
	
	/**
	 * 基础数据初始化
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward baseDataInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String lb = request.getParameter("lb");
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		appendOperResult(request, service.baseDataInit(lb));
		//根据传过来的类别来判断跳转页面
		if (GlobalsVariable.PJPY_JXJ.equalsIgnoreCase(lb) 
				|| GlobalsVariable.PJPY_ZHCPJXJ.equalsIgnoreCase(lb)) {
			return rsszManage(mapping, form, request, response);
		}else {
			return rychRsszManage(mapping, form, request, response);
		}
		
	}
	
	/**
	 * 奖学金比例批量设置功能
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjblPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm myForm = (PjpyZjkjxyActionForm) form;
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		HashMap<String, String> rs = new HashMap<String, String>();
		//设置默认的评奖学年，学期，年度
		rs.put("xn", Base.getJxjsqxn());
		rs.put("xq", Base.getJxjsqxq());
		rs.put("nd", Base.getJxjsqnd());
		
		//保存数据操作
		String key = GlobalsVariable.PJPY_JXJ;
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, myForm);
			model.setKey(key);
			appendOperResult(request, zjkjService.updateJxjrs(model));
		}
		
		request.setAttribute("rs", rs);
		//获取参评范围列表
		request.setAttribute("cpfwList", service.getCpfwList(false));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		//获取奖学金代码列表
		request.setAttribute("dmList", service.getDmList(key,myForm.getJxjlb()));
		//获取奖学金类别代码列表
		request.setAttribute("lbList", zjkjService.getJxjlbList(true));
		request.setAttribute("path", "pjpyrssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxjblPlsz");
	}
	
	/**
	 * 荣誉称号比例批量设置功能
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychblPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm myForm = (PjpyZjkjxyActionForm) form;
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		HashMap<String, String> rs = new HashMap<String, String>();
		//设置默认的评奖学年，学期，年度
		rs.put("xn", Base.getJxjsqxn());
		rs.put("xq", Base.getJxjsqxq());
		rs.put("nd", Base.getJxjsqnd());
		
		//保存数据操作
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, myForm);
			model.setKey(GlobalsVariable.PJPY_RYCH);
			appendOperResult(request, zjkjService.updateJxjrs(model));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("cpfwList", service.getCpfwList(false));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("dmList", service.getDmList(GlobalsVariable.PJPY_RYCH,myForm.getJxjlb()));
		request.setAttribute("lbList", zjkjService.getJxjlbList(true));
		request.setAttribute("path", "pjpyrssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rychblPlsz");
	}
	
	/**
	 * 奖学金人数调整
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjrstz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm myForm = (PjpyZjkjxyActionForm)form;
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		String pk = "xn||bmdm||nd||jxjdm||xqdm||key||nj";
		
		myForm.setPk(pk);		
		myForm.setKey(GlobalsVariable.PJPY_JXJ);
		
		PjpyZjkjxyActionForm model = new PjpyZjkjxyActionForm();
		BeanUtils.copyProperties(model, myForm);
		request.setAttribute("message", service.jxjrstz(model));
		return rsszManage(mapping, form, request, response);
	}
	
	/**
	 * 荣誉称号人数调整
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychrstz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm myForm = (PjpyZjkjxyActionForm)form;
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		String pk = "xn||bmdm||nd||jxjdm||xqdm||key||nj";
		
		myForm.setPk(pk);		
		myForm.setKey(GlobalsVariable.PJPY_RYCH);
		request.setAttribute("message", service.jxjrstz(myForm));
		return rychRsszManage(mapping, form, request, response);
	}
	
	/**
	 * 奖学金申请学生管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqxsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm myForm = (PjpyZjkjxyActionForm)form;
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		String pjzq = service.getPjpySqzq();//评奖周期
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		String tableName = "pjpy_jxjsqxsb";
		String viewName = "view_pjpy_jxjsqxs";//视图名称
		String[] zqzd = service.getPjzqzd(pjzq);//评奖周期字段
		String[] outputColumn = StringUtils.joinStrArr(new String[] { "pkValue","xh","xn","nd","xq","xmdm","xh"}, zqzd, new String[]{"xm", "xymc",
				"bjmc", "xmmc", "sfksq" });
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		} else if ("stu".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			myForm.setQuerylike_xh(userName);
		}
		
		myForm.setQueryequals_nd(Base.getJxjsqnd());
		myForm.setQueryequals_xn(Base.getJxjsqxn());
		myForm.setQueryequals_xq(Base.getJxjsqxq());
		myForm.setKey(GlobalsVariable.PJPY_JXJ);//奖学金
		
		//用户判断该功能是否开放
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_JXJSQRSZ)
				&& service.checkKgflag()){
			String msg = "申请人设置功能暂时不开放操作，仅限查询！";
			request.setAttribute("yhInfo", msg);
			//return new ActionForward("/yhInfo.do", false);
		}
		
		//查询
		if (!Base.isNull(doType) && "query".equals(doType)) {		
			List<String[]> rs = service.queryJxjsqrssz(myForm,outputColumn);//selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
			
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", service.getTopTr("jxjsqxs",outputColumn));
		}
		//保存
		else if (!Base.isNull(doType) && "save".equals(doType)) {
			String message = service.saveJxjsqrssz(myForm);
			request.setAttribute("message", message);
		}		
		//导出
		else if (!Base.isNull(doType) && "exp".equals(doType)) {
			outputColumn = StringUtils.joinStrArr(new String[] { "xh"}, 
					                              zqzd, 
					                              new String[]{"xm", "xydm","xymc",
												  "zydm","zymc","bjdm","bjmc","nj",
												  "xmdm", "xmmc", "sfksq" });
			List<String[]> list = service.queryJxjsqrsszNotP(myForm,outputColumn);
			List<HashMap<String, String>> topTr = service.getTopTr("jxjsqxs",outputColumn);
			String[] colListCN = service.getCn(topTr);
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");		
			Excel2Oracle.exportData(list,outputColumn,colListCN, response.getOutputStream());
			return mapping.findForward("");
		}
		
		//学院专业班级年级
		FormModleCommon.setNjXyZyBjListForFdyBzr(request, 
				myForm.getQueryequals_xydm(), 
				myForm.getQueryequals_zydm(), 
				myForm.getQueryequals_bjdm(), 
				myForm.getQueryequals_nj());
		//学年年度学期
		FormModleCommon.setNdXnXqList(request);
		//荣誉称号列表
		service.setList(new PjpyTyForm(), request, "rych");
		//奖学金列表
		request.setAttribute("jxjList", service.getJxjList());
		//是否列表
		request.setAttribute("flagList", service.getChkList(2));
		request.setAttribute("path", "pjpysqrsz.do");
		request.setAttribute("realTable", tableName);
		request.setAttribute("sqzqLength", zqzd.length);//申请周期字段的长度
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxjsqxsManage");
	}
	
	/**
	 * 荣誉称号申请学生管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsqxsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm myForm = (PjpyZjkjxyActionForm)form;
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		String pjzq = service.getPjpySqzq();//评奖周期
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		String tableName = "pjpy_jxjsqxsb";
		String viewName = "view_pjpy_jxjsqxs";
		String[] zqzd = service.getPjzqzd(pjzq);//评奖周期字段
		String[] outputColumn = StringUtils.joinStrArr(new String[] { "pkValue","xh","xn","nd","xq","xmdm","xh"}, zqzd, new String[]{"xm", "xymc",
				"bjmc", "xmmc", "sfksq" });
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		} else if ("stu".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			myForm.setQuerylike_xh(userName);
		}
		
		myForm.setQueryequals_nd(Base.getJxjsqnd());
		myForm.setQueryequals_xn(Base.getJxjsqxn());
		myForm.setQueryequals_xq(Base.getJxjsqxq());
		myForm.setKey(GlobalsVariable.PJPY_RYCH);//荣誉称号
		
		//用户判断该功能是否开放
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_JXJSQRSZ)
				&& service.checkKgflag()){
			String msg = "申请人设置功能暂时不开放操作，仅限查询！";
			request.setAttribute("yhInfo", msg);
			//return new ActionForward("/yhInfo.do", false);
		}
		//查询
		if (!Base.isNull(doType) && "query".equals(doType)) {		
			List<String[]> rs = service.queryJxjsqrssz(myForm,outputColumn);//selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", service.getTopTr("jxjsqxs",outputColumn));
		}
		//保存
		else if (!Base.isNull(doType) && "save".equals(doType)) {
			String message = service.saveJxjsqrssz(myForm);
			request.setAttribute("message", message);
		}		
		//导出
		else if (!Base.isNull(doType) && "exp".equals(doType)) {
			outputColumn = StringUtils.joinStrArr(new String[] { "xh"}, 
						                    zqzd, 
						                    new String[]{"xm", "xydm","xymc",
											  "zydm","zymc","bjdm","bjmc","nj",
											  "xmdm", "xmmc", "sfksq" });
			List<String[]> list = service.queryJxjsqrsszNotP(myForm,outputColumn);
			List<HashMap<String, String>> topTr = service.getTopTr("jxjsqxs",outputColumn);
			String[] colListCN = service.getCn(topTr);
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");		
			Excel2Oracle.exportData(list,outputColumn,colListCN, response.getOutputStream());
			return mapping.findForward("");
		}
		
		//学院专业班级年级
		FormModleCommon.setNjXyZyBjListForFdyBzr(request, 
				myForm.getQueryequals_xydm(), 
				myForm.getQueryequals_zydm(), 
				myForm.getQueryequals_bjdm(), 
				myForm.getQueryequals_nj());
		//学年年度学期
		FormModleCommon.setNdXnXqList(request);
		//荣誉称号列表		
		request.setAttribute("rychList", service.getRychList());
		//是否列表
		request.setAttribute("flagList", service.getChkList(2));
		request.setAttribute("path", "pjpysqrsz.do");
		request.setAttribute("realTable", tableName);
		request.setAttribute("sqzqLength", zqzd.length);//申请周期字段的长度
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rychsqxsManage");
	}
	
	
	/**
	 * 学院用户设置默认部门代码
	 * @param request
	 * @param form
	 * */
	public void setXydm(HttpServletRequest request, PjpyZjkjxyActionForm form) throws Exception {
		String userType = getUserType(request);
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(userType)) {
			form.setXydm(getUserDep(request));
			form.setQueryequals_xydm(getUserDep(request));
		}
	}
	
	/**
	 * 设置评奖学年，年度，学期
	 * */
	public void setPjxnndxq(PjpyZjkjxyActionForm form) {
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		String pjzq = service.getPjpySqzq();
		if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			//pjzq = service.getZhcpSqzq();
		}
		
		if (StringUtils.isNull(form.getXn())  
				&& ("xn".equalsIgnoreCase(pjzq) 
						|| "xq".equalsIgnoreCase(pjzq))) {
			form.setXn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(form.getNd()) 
				&& "nd".equalsIgnoreCase(pjzq)) {
			form.setNd(Base.getJxjsqnd());
		}
		if (StringUtils.isNull(form.getXq())
				&& "xq".equalsIgnoreCase(pjzq)) {
			form.setXq(Base.getJxjsqxq());
		}
		if (StringUtils.isNull(form.getQueryequals_xn())
				&& ("xn".equalsIgnoreCase(pjzq) 
						|| "xq".equalsIgnoreCase(pjzq))) {
			form.setQueryequals_xn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(form.getQueryequals_nd())
				&& "nd".equalsIgnoreCase(pjzq)) {
			form.setQueryequals_nd(Base.getJxjsqnd());
		}
		if (StringUtils.isNull(form.getQueryequals_xq())
				&& "xq".equalsIgnoreCase(pjzq)) {
			form.setQueryequals_xq(Base.getJxjsqxq());
		}
	}
	
}
