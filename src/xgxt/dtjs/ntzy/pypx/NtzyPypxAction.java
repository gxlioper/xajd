package xgxt.dtjs.ntzy.pypx;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

public class NtzyPypxAction extends BasicExtendAction{
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param respons
	 * @return
	 */
	public ActionForward pypxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons){
		
		NtzyPypxService service = new NtzyPypxService();
		
		String lx = request.getParameter("lx");
		String path = null;
		
		if("sq".equalsIgnoreCase(lx)){
			path = "pypxsq.do";
		}else if("sh".equalsIgnoreCase(lx)){
			path = "pypxsh.do";
		}else{
			path = "pypxcx.do";
		}
		
		setWriteAbleAndTitle(request, path);
		request.setAttribute("xmsqList", service.getPath(lx));
		
		return mapping.findForward("pypxgl");
	}
	
	/**
	 * 南通职业优秀团支部申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param respons
	 * @return
	 */
	public ActionForward yxtzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons){
		String doType = request.getParameter("doType");
		request.setAttribute("xn", Base.currXn);
		
		if("save".equalsIgnoreCase(doType)){
			this.insertOperation(request, "dtjs_ntzy_yxtzbsqb");
		}
		
		setWriteAbleAndTitle(request, "pypxsq.do");
		request.setAttribute("userType", getUserType2(request.getSession()));
		return mapping.findForward("yxtzbsq");
	}
	
	public ActionForward yxtzbshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons){
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		NtzyPypxService service = new NtzyPypxService();
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, "dtjs_ntzy_yxtzbsqb");
		}
		
		Map<String, String> map = service.getYxtzbInfo(pkValue);
		
		request.setAttribute("rs", map);
		
		request.setAttribute("doType", doType);
		
		request.setAttribute("userType", getUserType2(request.getSession()));
		return mapping.findForward("yxtzbshone");
	}
	
	/**
	 * 南通职业优秀团支部审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param respons
	 * @return
	 */
	public ActionForward yxtzbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons){
		HttpSession session = request.getSession();
		
		String userDep = (String)session.getAttribute("userDep");
		String doType = request.getParameter("doType");
		String go = request.getParameter("go");
		String tableName = "dtjs_ntzy_yxtzbsqb";
		String viewName = "view_dtjs_ntzy_yxtzbsq";
		
		String user = getUserType2(session);
		
		NtzyPypxForm myForm = (NtzyPypxForm)form;
		
		if("xy".equalsIgnoreCase(user)){
			myForm.setQueryequals_ssbm(userDep);
		}
		
		if("sh".equalsIgnoreCase(doType)){
			// 审核字段
			String shzd = "xy".equalsIgnoreCase(user) ? "xysh" : "xxsh";	
			
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
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"pkValue","xn","tzbmc","szyxtzz","sqr","bmmc","xysh","xxsh"};
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		setWriteAbleAndTitle(request, "pypxsh.do");
		
		request.setAttribute("userType", user);
		request.setAttribute("bmList", DAO.getInstance().getBmListAll());
		request.setAttribute("xn", Base.currXn);
		return mapping.findForward("yxtzbsh");
	}
	
	/**
	 * 南通职业优秀团支部查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param respons
	 * @return
	 */
	public ActionForward yxtzbcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons){
		HttpSession session = request.getSession();
		
		String userName = (String)session.getAttribute("userName");
		String userDep = (String)session.getAttribute("userDep");
		String doType = request.getParameter("doType");
		String go = request.getParameter("go");
		String tableName = "dtjs_ntzy_yxtzbsqb";
		String viewName = "view_dtjs_ntzy_yxtzbsq";
		
		String user = getUserType2(session);
		
		NtzyPypxForm myForm = (NtzyPypxForm)form;
		
		if("xy".equalsIgnoreCase(user)){
			myForm.setQueryequals_ssbm(userDep);
		}else if("stu".equalsIgnoreCase(user)){
			myForm.setQuerylike_sqr(userName);
		}
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tableName);
		}
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"pkValue","xn","tzbmc","szyxtzz","sqr","bmmc","xysh","xxsh"};
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		setWriteAbleAndTitle(request, "pypxcx.do");
		
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("userType", user);
		request.setAttribute("bmList", DAO.getInstance().getBmListAll());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		return mapping.findForward("yxtzbcx");
	}
	
	public ActionForward yxtzbView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons){
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String dest = null;
		
		NtzyPypxService service = new NtzyPypxService();
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, "dtjs_ntzy_yxtzbsqb");
		}
		
		if("print".equalsIgnoreCase(doType)){
			dest = "yxtzbprint";
		}else{
			dest = "yxtzbview";
		}
		
		Map<String, String> map = service.getYxtzbInfo(pkValue);
		
		request.setAttribute("rs", map);
		
		request.setAttribute("doType", doType);
		return mapping.findForward(dest);
	}
	
	public ActionForward yxtzbExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String[] output = new String[]{"tzbmc","xn","sqr","szyxtzz","sqsj","zygz","xysh","xxsh"};
	
		expPageData(request, response, "dtjs_ntzy_yxtzbsqb","view_dtjs_ntzy_yxtzbsq", output);
		return mapping.findForward("");
	}
	
	/**
	 * 优秀团员团干部申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param respons
	 * @return
	 */
	public ActionForward tytgbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons){
		HttpSession session = request.getSession();
		
		String tableName = "dtjs_ntzy_pypxb";
		
		String userType = (String)session.getAttribute("userType");
		String userName = (String)session.getAttribute("userName");
		
		String doType = request.getParameter("doType");
		String mk = request.getParameter("mk");
		
		String xh = "stu".equalsIgnoreCase(userType) ? userName : request.getParameter("xh");
		String xn = Base.currXn;
		
		// 是否可申请，非团员学生不能申请
		
		NtzyPypxService service = new NtzyPypxService();
	
		// 非团员不能申请
		if("stu".equalsIgnoreCase(userType) && !service.checkSfty(xh)){
			String msg = "只有团员才能申请该项目！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if(StringUtils.isNotNull(xh)){
			request.setAttribute("rs", service.getXsInfo(xh));
			
			if("yxtgb".equalsIgnoreCase(mk)){
				request.setAttribute("zw", service.getZw(xh));
			}
		}
		
		if("save".equalsIgnoreCase(doType)){
			this.insertOperation(request, tableName);
		}
		
		request.setAttribute("xn", xn);
		setWriteAbleAndTitle(request, "pypxsq.do");
		
		// 保存模块，该方法主要负责优秀团员，优秀团干部两个模块的评选
		request.setAttribute("mk", mk);
		return mapping.findForward("tytgbsq");
	}
	
	/**
	 * 优秀团员团干部审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param respons
	 * @return
	 */
	public ActionForward tytgbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons){
		HttpSession session = request.getSession();
		String userDep = (String)session.getAttribute("userDep");
		
		String tableName = "dtjs_ntzy_pypxb";
		String viewName = "view_dtjs_ntzy_pypx";
		
		String doType = request.getParameter("doType");
		String go = request.getParameter("go");
		String user = getUserType2(session);
		NtzyPypxForm myForm = (NtzyPypxForm)form;
		
		if("xy".equalsIgnoreCase(user)){
			myForm.setQueryequals_xydm(userDep);
		}
		
		if("sh".equalsIgnoreCase(doType)){
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
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"pkValue", "disabled", "xh", "xm",
					 "xymc", "zymc", "bjmc", "xysh", "xxsh"};
			setDisabledField1(request, user);
			selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		setWriteAbleAndTitle(request, "pypxsh.do");
		
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("userType", user);
		request.setAttribute("mk", request.getParameter("mk"));
		return mapping.findForward("tytgbsh");
	}
	
	/**
	 * 优秀团员团干部查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param respons
	 * @return
	 */
	public ActionForward tytgbcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons){
		HttpSession session = request.getSession();
		
		String tableName = "dtjs_ntzy_pypxb";
		String viewName = "view_dtjs_ntzy_pypx";
		
		String userName = (String)session.getAttribute("userName");
		String userDep = (String)session.getAttribute("userDep");
		
		String doType = request.getParameter("doType");
		String go = request.getParameter("go");
		String mk = request.getParameter("mk");
		
		String user = getUserType2(session);
		NtzyPypxForm myForm = (NtzyPypxForm)form;
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tableName);
		}
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"pkValue", "disabled", "xh", "xn", "xm",
					 "xymc", "zymc", "bjmc", "xysh", "xxsh"};
			setDisabledField1(request, user);
			selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		}
		
		if("stu".equalsIgnoreCase(user)){
			myForm.setQuerylike_xh(userName);
		}else if("xy".equalsIgnoreCase(user)){
			myForm.setQueryequals_xydm(userDep);
		}

		
		setWriteAbleAndTitle(request, "pypxcx.do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("mk", mk);
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		return mapping.findForward("tytgbcx");
	}
	
	/**
	 * 优秀团员团干部单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param respons
	 * @return
	 */
	public ActionForward tytgbshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons){
		String pkValue = request.getParameter("pkValue");
		String mk = request.getParameter("mk");
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		
		String user = getUserType2(request.getSession());
		
		String dest = "yxty".equalsIgnoreCase(mk) || "yxtgb".equalsIgnoreCase(mk) ? 
				"tytgbshone" : "tzzxshshone";
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, "dtjs_ntzy_pypxb");
		}
		
		NtzyPypxService service = new NtzyPypxService();
		
		request.setAttribute("zw", service.getZw(xh));
		request.setAttribute("rs", service.getPypxInfo(pkValue));
		request.setAttribute("userType", user);
		request.setAttribute("mk", mk);
		
		return mapping.findForward(dest);
	}
	
	public ActionForward pypxview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons){
		String pkValue = request.getParameter("pkValue");
		String mk = request.getParameter("mk");
		String xh = request.getParameter("xh");
		String dest = null;
		String doType = request.getParameter("doType");
		
		if("print".equalsIgnoreCase(doType)){
			dest = "yxty".equalsIgnoreCase(mk) || "yxtgb".equalsIgnoreCase(mk) ? 
					"tytgbprint" : "tzzxshprint";
		}else {
			dest = "yxty".equalsIgnoreCase(mk) || "yxtgb".equalsIgnoreCase(mk) ? 
				"tytgbview" : "tzzxshview";
		}
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, "dtjs_ntzy_pypxb");
		}
		
		NtzyPypxService service = new NtzyPypxService();
		
		request.setAttribute("zw", service.getZw(xh));
		request.setAttribute("rs", service.getPypxInfo(pkValue));
		request.setAttribute("mk", mk);
		request.setAttribute("doType", doType);
		return mapping.findForward(dest);
	}
	
	/**
	 * 团总支干部，学生会干部
	 * @param mapping
	 * @param form
	 * @param request
	 * @param respons
	 * @return
	 */
	public ActionForward tzzxshsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons){
		HttpSession session = request.getSession();
		
		String tableName = "dtjs_ntzy_pypxb";
		
		String userType = (String)session.getAttribute("userType");
		String userName = (String)session.getAttribute("userName");
		
		String doType = request.getParameter("doType");
		String mk = request.getParameter("mk");
		
		String xh = "stu".equalsIgnoreCase(userType) ? userName : request.getParameter("xh");
		String xn = Base.currXn;
		
		// 是否可申请，非团员学生不能申请
		
		NtzyPypxService service = new NtzyPypxService();
	
		// 非团员不能申请
		if("stu".equalsIgnoreCase(userType) && !service.checkSfty(xh)){
			String msg = "只有团员才能申请该项目！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if(StringUtils.isNotNull(xh)){
			request.setAttribute("rs", service.getXsInfo(xh));
			request.setAttribute("zw", service.getZw(xh));
		}
		
		if("save".equalsIgnoreCase(doType)){
			this.insertOperation(request, tableName);
		}
		
		request.setAttribute("xn", xn);
		setWriteAbleAndTitle(request, "pypxsq.do");
		
		// 保存模块，该方法主要负责优秀团员，优秀团干部两个模块的评选
		request.setAttribute("mk", mk);
		return mapping.findForward("tzzxshsq");
	}
	
	public ActionForward pypxExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String[] output = new String[]{"xh","xm","xb","nj","xn","xymc","zymc","bjmc",
				"mk","sqsj","sqsm","grjl","xysh","xxsh"};
	
		expPageData(request, response, "dtjs_ntzy_pypxb","view_dtjs_ntzy_pypx", output);
		return mapping.findForward("");
	}
}
