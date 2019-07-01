package xgxt.jygl.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.jygl.form.BdzblForm;
import xgxt.jygl.service.BdzblService;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;


public class JyglBdzblAction extends DispatchAction {
	/***
	 * 加载页面参数
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj, String bjdm) throws Exception {
		request.setAttribute("writeAble", "yes");//判断用户读写权
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		FormModleCommon.setNjXyZyBjListForFdyBzr(request, xydm, zydm, bjdm, nj);
	}
	
	/** 
	 * 报到证办理申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward bdzblsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		StudentInfoService xsxx = new StudentInfoService();
		BdzblForm model = (BdzblForm)form;
		BdzblService service = new BdzblService();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String xh = request.getParameter("xh");
		
		if("stu".equalsIgnoreCase(userType)){//学生登陆
			xh = userName;
		}
		HashMap<String, String> map = xsxx.getStuInfo(xh);
		map.put("nd", Base.currNd);
		if(StringUtils.isNotNull(xh) && "stu".equalsIgnoreCase(userType)){
			model.setXh(xh);
			model.setFdysh("未审核");
			model.setXysh("未审核");
			request.setAttribute("exists", service.checkModify(model));
		}
		request.setAttribute("rs", map);
		return mapping.findForward("bdzblsq");
	}
	
	/** 
	 * 保存报到证办理申请信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward saveBdzblsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BdzblForm model = (BdzblForm)form;
		BdzblService service = new BdzblService();
		
		request.setAttribute("result", service.saveBdzblsqb(model,request));
		request.setAttribute("rs", service.queryBdzblsqbOne(model));
		return mapping.findForward("bdzblsq");
	}
	
	/** 
	 * 报到证办理申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward bdzblsqModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BdzblService service = new BdzblService();
		String pkValue = request.getParameter("pkValue");
		BdzblForm model = (BdzblForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		
		model.setXh(pkValue);
		if(StringUtils.isNotNull(model.getXh()) && "stu".equalsIgnoreCase(userType)){
			BdzblForm bdzblForm = new BdzblForm();
			bdzblForm.setXh(model.getXh());
			bdzblForm.setFdysh("未审核");
			bdzblForm.setXysh("未审核");
			request.setAttribute("exists", service.checkModify(bdzblForm));
		}
		request.setAttribute("rs", service.queryBdzblsqbOne(model));
		return mapping.findForward("bdzblsq");
	}
	
	/** 
	 * 保存报到证办理申请信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward delBdzblsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BdzblForm model = (BdzblForm)form;
		BdzblService service = new BdzblService();
		
		request.setAttribute("result", service.deleteBdzblsqb(model));
		return new ActionForward("/bdzbl.do?method=bdzblsqSearch&go=go");
	}
	
	/** 
	 * 报到证办理申请结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward bdzblsqSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BdzblForm model = (BdzblForm)form;
		BdzblService service = new BdzblService();
		String go = request.getParameter("go");
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		
		if("xy".equalsIgnoreCase(userType) 
				&& !session.getAttribute("fdyQx").equals(true)
				&& ! session.getAttribute("bzrQx").equals(true)){//学院用户
			model.setXydm(session.getAttribute("userDep").toString());
		}
		if("stu".equalsIgnoreCase(userType)){
			model.setXh(userName);
			go = "go";
		}
		
		if(StringUtils.isNotNull(go)){
			String[] colList = {"xh","xm","nj","xymc","zymc","bjmc","bdzlx","bdzkwdwmc","dajwdwmc","sjhm","fdysh","xysh"};
			String tableName = "view_bdzblsqb";
			
			model.setUserName(userName);
			model.setIsBzr(session.getAttribute("bzrQx").toString());
			model.setIsFdy(session.getAttribute("fdyQx").toString());
			List<String[]> rs =  service.queryBdzblsqb(model,colList);
			request.setAttribute("rs",rs);
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", service.getTopTr(tableName,colList));
		}
		
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj(),model.getBjdm());
		request.setAttribute("realTable", "bdzblsqb");
		request.setAttribute("tableName", "view_bdzblsqb");
		return mapping.findForward("bdzblsqSearch");
	}
	
	/** 
	 * 报到证办理申请结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward exportBdzblsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BdzblForm model = (BdzblForm)form;
		BdzblService service = new BdzblService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		
		model.setXh(request.getParameter("xh"));
		model.setXm(request.getParameter("xm"));
		model.setNj(request.getParameter("nj"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setNd(request.getParameter("nd"));
		model.setBdzlx(request.getParameter("bdzlx"));
		model.setBdzkwdwmc(request.getParameter("bdzkwdwmc"));
		model.setSjhm(request.getParameter("sjhm"));
		model.setFdysh(request.getParameter("fdysh"));
		model.setXysh(request.getParameter("xysh"));
		
		if("xy".equalsIgnoreCase(userType) 
				&& !session.getAttribute("fdyQx").equals(true)
				&& ! session.getAttribute("bzrQx").equals(true)){//学院用户
			model.setXydm(session.getAttribute("userDep").toString());
		}
		
		String[] colList = {"xh","xb","xm","nj","xz","xydm","xymc","zydm","zymc","bjdm","bjmc","nd","bdzlx","bdzh","bdzkwdwmc","dajwdwmc","dajwdwbm","sfklq","bdzkcsj","lqr","lqsj","lxdz","lxfs","lxyb","sjhm","fdysh","xysh"};
		String[] colListCN = service.getColumnNameCN(colList, "view_bdzblsqb");
		
		model.setUserName(userName);
		model.setIsBzr(session.getAttribute("bzrQx").toString());
		model.setIsFdy(session.getAttribute("fdyQx").toString());
		List<String[]> rs = service.queryBdzblsqForExport(model,colList);//查询学生报到证办理申请信息
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		
		return mapping.findForward("");
	}
	
	/** 
	 * 报到证办理审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward bdzblAudi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BdzblForm model = (BdzblForm)form;
		HttpSession session = request.getSession();
		BdzblService service = new BdzblService();
		String go = request.getParameter("go");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		boolean isFdy = (Boolean) session.getAttribute("isFdy");
		
		
		if("xy".equalsIgnoreCase(userType) 
				&& !isFdy
				&& ! session.getAttribute("bzrQx").equals(true)){//学院用户
			model.setXydm(session.getAttribute("userDep").toString());
		}
		
		if(!isFdy){
			model.setFdysh("通过");
		}
		if(StringUtils.isNotNull(go)){
			String[] colList = {"xh","xm","nj","xymc","zymc","bjmc","bdzlx","bdzkwdwmc","dajwdwmc","sjhm","fdysh","xysh"};
			String tableName = "view_bdzblsqb";
			
			model.setUserName(userName);
			model.setIsBzr(session.getAttribute("bzrQx").toString());
			model.setIsFdy(session.getAttribute("fdyQx").toString());
			List<String[]> rs =  service.queryBdzblsqb(model,colList);
			request.setAttribute("rs",rs);
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", service.getTopTr(tableName,colList));
		}
		
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj(), model.getBjdm());		
		return mapping.findForward("bdzblAudi");
	}
	
	/** 
	 * 显示报到证办理单个审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward bdzblAudiOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BdzblForm model = (BdzblForm)form;
		BdzblService service = new BdzblService();
		String pkValue = request.getParameter("pkValue");
		
		model.setXh(pkValue);		
		request.setAttribute("rs", service.queryBdzblsqbOne(model));
		return mapping.findForward("bdzblAudiOne");
	}
	
	/** 
	 * 报到证办理单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward audiBdzblOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BdzblForm model = (BdzblForm)form;
		BdzblService service = new BdzblService();
		model.setPkV(model.getPkV() == null || model.getPkV().length<1 ? new String[]{model.getXh()} : model.getPkV());
		
		request.setAttribute("result", service.audiBdzbl(model,request));
		request.setAttribute("rs", service.queryBdzblsqbOne(model));
		return mapping.findForward("bdzblAudiOne");
	}
	
	/** 
	 * 报到证办理批量审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward audiBdzbl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		BdzblForm model = (BdzblForm)form;
		BdzblService service = new BdzblService();
		String shjg = request.getParameter("shjg");
		//判断是否是辅导员用户
		boolean isFdy = (Boolean)session.getAttribute("isFdy");
		model.setFdysh(isFdy ? shjg : null);
		model.setXysh(isFdy ? null : shjg);
		
		request.setAttribute("result", service.audiBdzbl(model,request));
		return new ActionForward("/bdzbl.do?method=bdzblAudi&go=go");
	}
	
	
	/** 
	 * 报到证查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward bdzblSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BdzblForm model = (BdzblForm)form;
		BdzblService service = new BdzblService();
		String go = request.getParameter("go");
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		
		if("xy".equalsIgnoreCase(userType) 
				&& ! session.getAttribute("fdyQx").equals(true)
				&& ! session.getAttribute("bzrQx").equals(true)){//学院用户
			model.setXydm(session.getAttribute("userDep").toString());
		}
		if("stu".equalsIgnoreCase(userType)){
			model.setXh(userName);
			go = "go";
		}
		
		if(StringUtils.isNotNull(go)){
			String[] colList =  {"xh","xm","nj","xymc","zymc","bjmc","bdzh","bdzkwdwmc","bdzkcsj","sfklq","lqr","lqsj"};
			String tableName  = "view_bdzblsqb";
			
			model.setFdysh("通过");
			model.setXysh("通过");
			model.setUserName(userName);
			model.setIsBzr(session.getAttribute("bzrQx").toString());
			model.setIsFdy(session.getAttribute("fdyQx").toString());
			List<String[]> rs =  service.queryBdzblsqb(model, colList);
			request.setAttribute("rs",rs);
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", service.getTopTr(tableName,colList));
		}
		
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj(), model.getBjdm());		
		return mapping.findForward("bdzblSearch");
	}
	
	/** 
	 * 显示报到证办理信息修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward bdzblModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BdzblForm model = (BdzblForm)form;
		BdzblService service = new BdzblService();
		String pkValue = request.getParameter("pkValue");
		
		model.setXh(pkValue);
		request.setAttribute("rs", service.queryBdzblsqbOne(model));
		return mapping.findForward("bdzblModi");
	}
	
	/** 
	 * 修改报到证办理信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward modiBdzblxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BdzblForm model = (BdzblForm)form;
		BdzblService service = new BdzblService();
		
		request.setAttribute("result", service.modifyBdzblsqb(model,request));
		request.setAttribute("rs", service.queryBdzblsqbOne(model));
		return mapping.findForward("bdzblModi");
	}
	
	/** 
	 * 报到证导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward exportBdzbl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BdzblForm model = (BdzblForm)form;
		BdzblService service = new BdzblService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		
		model.setXh(request.getParameter("xh"));
		model.setXm(request.getParameter("xm"));
		model.setNj(request.getParameter("nj"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setNd(request.getParameter("nd"));
		model.setBdzlx(request.getParameter("bdzlx"));
		model.setBdzkwdwmc(request.getParameter("bdzkwdwmc"));
		model.setSjhm(request.getParameter("sjhm"));
		model.setBdzkcsj(request.getParameter("bdzkcsj"));
		model.setSfklq(request.getParameter("sfklq"));
		model.setLqr(request.getParameter("lqr"));
		model.setLqsj(request.getParameter("lqsj"));
		model.setFdysh("通过");
		model.setXysh("通过");
		if("xy".equalsIgnoreCase(userType)){//学院用户
			model.setXydm(session.getAttribute("userDep").toString());
		}
		
		String[] colList = {"xh","xb","xm","nj","xz","xydm","xymc","zydm","zymc","bjdm","bjmc","nd","bdzlx","bdzh","bdzkwdwmc","dajwdwmc","dajwdwbm","sfklq","bdzkcsj","lqr","lqsj","lxdz","lxfs","lxyb","sjhm"};
		String[] colListCN = service.getColumnNameCN(colList, "view_bdzblsqb");
		List<String[]> rs = service.queryBdzblsqForExport(model,colList);//查询学生报到证办理申请信息
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
}