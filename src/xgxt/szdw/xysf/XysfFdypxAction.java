package xgxt.szdw.xysf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;

public class XysfFdypxAction extends BasicExtendAction {
	
	public ActionForward fdypxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String userType = (String)session.getAttribute("userType");
		String userName = (String)session.getAttribute("userName");
	
		FdypxService service = new FdypxService();
		boolean isSzdw = Fdypd.isSzdw(userName);
		
		if("stu".equalsIgnoreCase(userType) && !isSzdw){
		    String msg = "您不能对本模块进行操作，请确认！";
		    request.setAttribute("yhInfo", msg);
		    return new ActionForward("/yhInfo.do", false);
		}
		
		String zgh = isSzdw ? userName : request.getParameter("zgh");
		Map<String, String> teaInfo = service.getTeaInfo(zgh, null); 
		
		setWriteAbleAndTitle(request, "xysf_fdypxwh.do");
		
		request.setAttribute("rs", teaInfo);
		request.setAttribute("isSzdw", isSzdw);
		
		return mapping.findForward("fdypxUpdate");
	}
	
	public ActionForward fdypxSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XysfDyjstglForm myForm = (XysfDyjstglForm) form;
		DyjstglModel model = new DyjstglModel();
		BeanUtils.copyProperties(model, myForm);
		
		FdypxService service = new FdypxService();
		String message = service.savePx(model) ? "操作成功！" : "操作失败！";

		request.setAttribute("message", message);
		
		return fdypxUpdate(mapping, myForm, request, response);
	}
	
	public ActionForward fdypxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "szdw_xysf_fdypxb";
		String viewName = "view_szdw_xysf_fdypx";
		
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, tableName);
		}
		
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		
		request.setAttribute("doType", doType);
		return mapping.findForward("fdypxView");
	}
	
	public ActionForward fdypxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String userName = (String)session.getAttribute("userName");
		String userDep = (String)session.getAttribute("userDep");
		String user = getUserType(session);
		String tableName = "szdw_xysf_fdypxb";
		String viewName = "view_szdw_xysf_fdypx";
		
		boolean isSzdw = Fdypd.isSzdw(userName);
		XysfDyjstglForm myForm = (XysfDyjstglForm)form;

		if(isSzdw){
			myForm.setQuerylike_zgh(userName);
		} else if("xy".equalsIgnoreCase(user)){
			myForm.setQueryequals_bmdm(userDep);
		} else if("stu".equalsIgnoreCase(user)){
			   String msg = "您不能对本模块进行操作，请确认！";
			   request.setAttribute("yhInfo", msg);
			   return new ActionForward("/yhInfo.do", false);
		}
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tableName);
		}
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"pkValue","zgh","xm","xb","bmmc","pxmc","pxsj","pxdd"};
			this.selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		}
		
		setWriteAbleAndTitle(request, "xysf_fdypxcx.do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		request.setAttribute("isSzdw", isSzdw);
		request.setAttribute("xyList", DAO.getInstance().getBmListAll());
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		return mapping.findForward("fdypxManage");
	}
	
	public ActionForward fdypxExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] output = new String[]{"zgh","xm","xb","xl","zwmc","bmmc","pxmc","pxdd","pxsj",
				"pxnr","bz"};
		expPageData(request, response, "szdw_xysf_fdypxb","view_szdw_xysf_fdypx", output);
		return mapping.findForward("");
	}
}
