package xgxt.szdw.ntzy;

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
import xgxt.utils.Fdypd;

public class NtzyFdypxAction extends BasicExtendAction{
	
	/**
	 * 南通职业-辅导员培训反馈
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward fdypxfk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String tableName = "szdw_ntzy_fdypxb";
		
		if(!Fdypd.isSzdw(userName)){
			String msg = "本模块只能由教师用户进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		NtzyFdypxService service = new NtzyFdypxService();
		String[] output = new String[]{"zgh","xm","bmmc","bmdm","xb","mzmc","zzmm",
				"csrq","sfmc","byyx","zwmc","xl","fdyzmc"};
		
		Map<String,String> teaInfo = service.getTeaInfo(userName, output);
		
		if("save".equalsIgnoreCase(doType)){
			this.insertOperation(request, tableName);
		}
		
		setWriteAbleAndTitle(request, "fdyfk.do");
		request.setAttribute("rs", teaInfo);
		request.setAttribute("pxfwList", NtzyFdypxService.pxfw);
		request.setAttribute("pxlxList", NtzyFdypxService.pxlx);
		
		return mapping.findForward("fdypxfk");
	}
	
	/**
	 * 南通职业-辅导员培训审核
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward fdypxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String tableName = "szdw_ntzy_fdypxb";
		String viewName = "view_szdw_ntzy_fdypx";
		String doType = request.getParameter("doType");
		String go = request.getParameter("go");
		
		NtzyFdypxForm myForm = (NtzyFdypxForm)form;
		
		if("sh".equalsIgnoreCase(doType)){
			String shzd = "xxsh";
			String shjg = "tg".equalsIgnoreCase(request.getParameter("shjg")) ? "通过" : "不通过";
			HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
			HashMap<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(shzd, shjg);
			auditingBatchOperation(request, primaryMap, valueMap, tableName);
		}
		
		if("go".equalsIgnoreCase(go)){
		   String[] outputColumn = new String[]{"pkValue","zgh","xm","bmmc","pxfw","pxlx","kssj",
				   "jssj","xxsh"};
		   this.selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		}
		
		request.setAttribute("bmList", DAO.getInstance().getBmListAll());
		
		setWriteAbleAndTitle(request, "fdypxsh.do");
		request.setAttribute("pxfwList", NtzyFdypxService.pxfw);
		request.setAttribute("pxlxList", NtzyFdypxService.pxlx);
		return mapping.findForward("fdypxsh");
	}
	
	/**
	 * 南通职业-辅导员单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward fdypxshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String tableName = "szdw_ntzy_fdypxb";
		
		NtzyFdypxService service = new NtzyFdypxService();
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, tableName);
		}
		
		Map<String, String> map = service.getPxInfo(pkValue);
		
		request.setAttribute("rs", map);
		
		return mapping.findForward("fdypxshone");
	}
	
	/**
	 * 南通职业-辅导员培训查询
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward fdypxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		String userDep = (String)session.getAttribute("userDep");
		
		String tableName = "szdw_ntzy_fdypxb";
		String viewName = "view_szdw_ntzy_fdypx";
		String doType = request.getParameter("doType");
		String go = request.getParameter("go");
		String user = getUserType2(session);
		
		NtzyFdypxForm myForm = (NtzyFdypxForm)form;
		
		if(Fdypd.isSzdw(userName)){
			myForm.setQuerylike_zgh(userName);
			request.setAttribute("isSzdw", "yes");
		}else if("xy".equalsIgnoreCase(user)){
			myForm.setQueryequals_bmdm(userDep);
		}
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tableName);
		}
		
		if("export".equalsIgnoreCase(doType)){
			String[] output = new String[]{"zgh","xm","bmmc","zwmc","pxfw","pxlx","kssj",
					   "jssj","xxsh"};
			
			this.expPageData(request, response, tableName, viewName, output);
			return mapping.findForward("");
		}
		
		if("go".equalsIgnoreCase(go)){
		   String[] outputColumn = new String[]{"pkValue","disabled","zgh","xm","bmmc","pxfw","pxlx","kssj",
				   "jssj","xxsh"};
		   setDisabledField1(request, user);
		   this.selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		}		
		
		request.setAttribute("bmList", DAO.getInstance().getBmListAll());
		setWriteAbleAndTitle(request, "fdypxcx.do");
		
		request.setAttribute("pxfwList", NtzyFdypxService.pxfw);
		request.setAttribute("pxlxList", NtzyFdypxService.pxlx);
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		request.setAttribute("userType", user);
		return mapping.findForward("fdypxcx");
	}
	
	/**
	 * 辅导员培训查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward fdypxview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String tableName = "szdw_ntzy_fdypxb";
		String operation = "view".equalsIgnoreCase(doType) ? "view" : "modi";
		
		NtzyFdypxService service = new NtzyFdypxService();
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, tableName);
		}
		
		Map<String, String> map = service.getPxInfo(pkValue);
		
		request.setAttribute("rs", map);
		request.setAttribute("pxfwList", NtzyFdypxService.pxfw);
		request.setAttribute("pxlxList", NtzyFdypxService.pxlx);
		request.setAttribute("operation", operation);
		return mapping.findForward("fdypxview");
	}
}
