/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.pjpy.ghxy.stxf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.utils.FormModleCommon;

/** 
 * MyEclipse Struts
 * Creation date: 08-27-2010
 * 硅湖学院社团分
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class GhxyStxfAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * 硅湖学院社团分维护
	 * Method stxfWh
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward stxfWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession();
		String userType=session.getAttribute("userType").toString();
		String userDep=session.getAttribute("userDep").toString();
		GhxyStxfService service=new GhxyStxfService();
		GhxyStxfForm stxfForm=(GhxyStxfForm)form;
		
		String tabName="ghxy_stfb";
		String viewName="view_ghxy_stxf";
		String doType=request.getParameter("doType");
		String lx=request.getParameter("lx");
		String xn=Base.currXn;

		if(Base.isNull(lx)){
			lx="wh";
		}
		if("save".equalsIgnoreCase(doType)){
			service.stxfWh(request,stxfForm);
			doType="qry";
		}
		
		if("qry".equalsIgnoreCase(doType)){
			if("wh".equalsIgnoreCase(lx)){
				String[]outputColumn={"xh","xh","xm","xn","nj","xy","zymc","bjmc"};
				this.selectPageDataByPagination(request, form, tabName, "view_ghxy_stfwh", outputColumn);
			}else if("cx".equalsIgnoreCase(lx)){
				String[]outputColumn={"xh","xh","xm","xn","nj","xy","zymc","bjmc","zf"};
				this.selectPageDataByPagination(request, form, tabName, viewName, outputColumn);
			}
		}
		
		if("wh".equalsIgnoreCase(lx)){
			stxfForm.setXn(xn);
			request.setAttribute("xn", xn);
		}
		
		if("xy".equalsIgnoreCase(userType)){
			stxfForm.setXydm(userDep);
		}
		
		request.setAttribute("path", "ghxyStxf.do?method=stxfWh");
		request.setAttribute("lx", lx);
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("stxfWh");
	}
}