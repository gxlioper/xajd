/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.xslx.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.exceptions.NotEnoughPowerException;

/** 
 * MyEclipse Struts
 * Creation date: 11-06-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class XslxAction extends Action {
	/**
	 * 学生离校信息
	 */

//	private int flag=0;	
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{

		ActionForward af = new ActionForward();
		try{
			String parameter = mapping.getParameter();
//			HttpSession session = request.getSession();
//			String userOnLine = session.getAttribute("userOnLine").toString();
//			String usrName = session.getAttribute("userName").toString();
			if("listingQuery".equalsIgnoreCase(parameter)){//离校信息查询
				af=XslxOperationAction.SearchInfo(mapping, form, request, response);
			}else if("leaveCheck".equalsIgnoreCase(parameter)){//离校审核
				af=XslxOperationAction.LeaveCheck(mapping, form, request, response);
			}else if("expDate".equalsIgnoreCase(parameter)){
				af=XslxOperationAction.expData(mapping, form, request, response);
			}else if("xdzsj".equalsIgnoreCase(parameter)){
				af=XslxOperationAction.dzbsjInfo(mapping, form, request, response);
			}else if("bybjfp".equalsIgnoreCase(parameter)){
				af=XslxOperationAction.bybjfpInfo(mapping, form, request, response);
			}else {
				throw new NotEnoughPowerException();
			}
		}catch(NotEnoughPowerException e){
			e.printStackTrace();
			return new ActionForward("/login.jsp",false);
		}
		return af;
	}
}