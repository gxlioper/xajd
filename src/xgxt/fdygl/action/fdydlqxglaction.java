/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.fdygl.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.fdygl.util.fdygl_util;
import xgxt.form.FdyglForm;

/** 
 * MyEclipse Struts
 * Creation date: 10-13-2007
 * 
 * XDoclet definition:
 * @struts.action path="fdydlqxgl" name="fdyglForm" scope="request" validate="true"
 */
public class fdydlqxglaction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		FdyglForm fdyglForm = (FdyglForm) form;// TODO Auto-generated method stub
		ActionForward myActFwd = null;
		HttpSession session = request.getSession();
		try
		{
			/** 在线检测 */
			int i = Base.chkTimeOut(session);
			if (i <= 2) {
				request.setAttribute("errMsg", "登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
		    String doType=request.getParameter("doType");
//		    String userName=session.getAttribute("userName").toString();
//		    String userDep=session.getAttribute("userDep").toString();
//		    String userNameReal=session.getAttribute("userNameReal").toString();
//		    String userOnLine=session.getAttribute("userOnLine").toString();
		    if(null!=doType&&""!=doType)
		    {
		    	if("search".equalsIgnoreCase(doType))
		    	{
		    		myActFwd = fdyqxgl_search(mapping,form,request,response);	
		    	}
		    	else if("update_fdydlqxxxb".equalsIgnoreCase(doType))
		    	{
		    		myActFwd = update_fdydlqxxxb(mapping,form,request,response);
		    	}
		    	else if("fpqx_fdyqlqxxxb".equalsIgnoreCase(doType))
		    	{
		    		myActFwd = fpqx_fdyqlqxxxb(mapping,form,request,response);
		    	}
		    	else if("shqx_fdyqlqxxxb".equalsIgnoreCase(doType))
		    	{
		    		myActFwd = shqx_fdyqlqxxxb(mapping,form,request,response);
		    	}
		    	else
		    	{
		    		
		    	}
		    }
		    else
		  	{
		  		myActFwd = index_to_jsp(mapping,form,request,response);	
		  	}   
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			request.setAttribute("errMsg", "出现故障，" + ex.toString());
			return new ActionForward("/errMsg.do", false);
		}
		return myActFwd;
	}
	
	
	 private ActionForward index_to_jsp(ActionMapping mapping, ActionForm form,
	    	    HttpServletRequest request, HttpServletResponse response)
	    	    throws Exception {
			 	HttpSession session = request.getSession();
//		 		String userName=session.getAttribute("userName").toString();
		 		String userDep=session.getAttribute("userDep").toString();
		 		String userType = session.getAttribute("userType").toString();
		 		FdyglForm myform = (FdyglForm)form;
		 		if (userType.equalsIgnoreCase("xy")) {
		 			myform.setXydm(userDep);
		 		}
		 		fdygl_util myutil=new fdygl_util();
//		 		boolean writeFlag=myutil.commen_util.checkUsrPower(userName);
		 		boolean writeFlag = true;
		 		
		 		String xydm=myform.getXydm();
		 		String zydm=myform.getZydm();
		 		request.setAttribute("xyList", myutil.commen_util.get_xyList());
		 		request.setAttribute("bjList", myutil.commen_util.get_bjList(xydm, zydm));
		 		request.setAttribute("sfyqxList", myutil.get_sfyqxList());
		 		if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
					request.setAttribute("userType", "xx");// 用户类型
				} else {
					request.setAttribute("userType", "xy");// 用户类型
				}
		 		if(writeFlag==false)
		 		{
		 			request.setAttribute("writeAble", "no");
		 		}
		 		else
		 		{
		 			request.setAttribute("writeAble", "yes");
		 		}
		 		return mapping.findForward("index_to_jsp");
	 }

	 //fdyqxgl_search
	 private ActionForward fdyqxgl_search(ActionMapping mapping, ActionForm form,
	    	    HttpServletRequest request, HttpServletResponse response)
	    	    throws Exception {
			 	HttpSession session = request.getSession();
//		 		String userName=session.getAttribute("userName").toString();
		 		String userDep=session.getAttribute("userDep").toString();
		 		String userType = session.getAttribute("userType").toString();
		 		FdyglForm myform = (FdyglForm)form;
		 		fdygl_util myutil=new fdygl_util();
//		 		boolean writeFlag=myutil.commen_util.checkUsrPower(userName);
		 		boolean writeFlag = true;
		 		myform=myutil.deal_gbk(myform);
		 		if (userType.equalsIgnoreCase("xy")) {
		 			myform.setXydm(userDep);
		 		}
		 		List li=myutil.fdyqxgl_search(myform);
		 		List topTr=myutil.get_TopTr();
		 		String xydm=myform.getXydm();
		 		String zydm=myform.getZydm();
		 		String rsNum=String.valueOf(li.size());
		 		request.setAttribute("rsNum",rsNum);
		 		request.setAttribute("topTr", topTr);
		 		request.setAttribute("rs", li);
		 		if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
					request.setAttribute("userType", "xx");// 用户类型
				} else {
					request.setAttribute("userType", "xy");// 用户类型
				}
		 		request.setAttribute("xyList", myutil.commen_util.get_xyList());
		 		request.setAttribute("bjList", myutil.commen_util.get_bjList(xydm, zydm));
		 		request.setAttribute("sfyqxList", myutil.get_sfyqxList());
		 		if(writeFlag==false)
		 		{
		 			request.setAttribute("writeAble", "no");
		 		}
		 		else
		 		{
		 			request.setAttribute("writeAble", "yes");
		 		}
		 		return mapping.findForward("index_to_jsp");
	 }
	 
	 private ActionForward update_fdydlqxxxb(ActionMapping mapping, ActionForm form,
	    	    HttpServletRequest request, HttpServletResponse response)
	    	    throws Exception {
//			 	HttpSession session = request.getSession();
//		 		String userName=session.getAttribute("userName").toString();
//		 		String userDep=session.getAttribute("userDep").toString();
		 		FdyglForm myform = (FdyglForm)form;
		 		fdygl_util myutil=new fdygl_util();
//		 		boolean writeFlag=myutil.commen_util.checkUsrPower(userName);
		 		boolean writeFlag = true;
		 		myutil.update_fdyqxxxb(request);
		 		myform=myutil.deal_gbk(myform);
		 		List li=myutil.fdyqxgl_search(myform);
		 		List topTr=myutil.get_TopTr();
		 		String xydm=myform.getXydm();
		 		String zydm=myform.getZydm();
		 		String rsNum=String.valueOf(li.size());
		 		request.setAttribute("rsNum",rsNum);
		 		request.setAttribute("topTr", topTr);
		 		request.setAttribute("rs", li);
		 		request.setAttribute("xyList", myutil.commen_util.get_xyList());
		 		request.setAttribute("bjList", myutil.commen_util.get_bjList(xydm, zydm));
		 		request.setAttribute("sfyqxList", myutil.get_sfyqxList());
		 		if(writeFlag==false)
		 		{
		 			request.setAttribute("writeAble", "no");
		 		}
		 		else
		 		{
		 			request.setAttribute("writeAble", "yes");
		 		}
		 		return mapping.findForward("index_to_jsp");
	 }
	 
	 private ActionForward fpqx_fdyqlqxxxb(ActionMapping mapping, ActionForm form,
	    	    HttpServletRequest request, HttpServletResponse response)
	    	    throws Exception {
		 		fdygl_util myutil=new fdygl_util();
			 	FdyglForm myform = (FdyglForm)form;
		 		String xzstr=request.getParameter("xzstr");
		 		String strtmp=request.getParameter("strtmp");
		 		xzstr=strtmp;
//		 		boolean flag=myutil.fpqx_fdyqxgl(xzstr);
		 		myutil.fpqx_fdyqxgl(xzstr,request);
		 		myutil.update_fdyqxxxb(request);
		 		return this.fdyqxgl_search(mapping, myform, request, response);
	 }
	 
	 private ActionForward shqx_fdyqlqxxxb(ActionMapping mapping, ActionForm form,
	    	    HttpServletRequest request, HttpServletResponse response)
	    	    throws Exception {
		 		fdygl_util myutil=new fdygl_util();
			 	FdyglForm myform = (FdyglForm)form;
		 		String xzstr=request.getParameter("xzstr");
		 		String strtmp=request.getParameter("strtmp");
		 		xzstr=strtmp;
//		 		boolean flag=myutil.shqx_fdyqlqxxxb(xzstr);
		 		myutil.shqx_fdyqlqxxxb(xzstr,request);
		 		myutil.update_fdyqxxxb(request);
		 		return this.fdyqxgl_search(mapping, myform, request, response);
	 }
}