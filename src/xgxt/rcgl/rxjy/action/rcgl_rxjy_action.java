
package xgxt.rcgl.rxjy.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.rcgl.rxjy.rcgl_rxjy_form;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;

/**
 * @author LP 2007-11-14
 *
 */
public class rcgl_rxjy_action extends Action{
    
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		rcgl_rxjy_form rcgl_xspd_Form = (rcgl_rxjy_form)form;
		try{
			int i = Base.chkTimeOut(request.getSession());
			if(i<=2){
				rcgl_xspd_Form.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
	        HttpSession session = request.getSession();
	        if(session==null){
	        	request.setAttribute("errMsg","发生错误！");
	        	return mapping.findForward("false");
	        }
	        ActionForward myActFwd = null;
		    String doType=request.getParameter("doType");	
		    String userName=session.getAttribute("userName").toString();
		    String userType=session.getAttribute("userOnLine").toString();
			boolean isStu = (userType.equalsIgnoreCase("student"));
		  	String writeAble = "" ;
	    	String power = "rcgl_rxjy.do";
	    	int p = Base.chkUPower(userName, power, isStu);
		    /***/
		    if(doType!=null&&!doType.equalsIgnoreCase("")){
//		    	if(doType.equalsIgnoreCase("xspd_search")){
//		    		myActFwd = xspd_search(mapping,form,request,response);
//		    	}else if(doType.equalsIgnoreCase("xspd_add")){
//		    		myActFwd = xspd_add(mapping,form,request,response);
//		    	}else if(doType.equalsIgnoreCase("stu_info")){
//		    		myActFwd = stu_info(mapping,form,request,response);
//		    	}else if(doType.equalsIgnoreCase("xspd_view")){
//		    		myActFwd = xspd_view(mapping,form,request,response);
//		    	}else if(doType.equalsIgnoreCase("xspd_modi")){
//		    		myActFwd = xspd_modi(mapping,form,request,response);
//		    	}else if(doType.equalsIgnoreCase("xspd_del")){
//		    		myActFwd = xspd_del(mapping,form,request,response);
//		    	}
		    }else{
		    	myActFwd = index_to_jsp(mapping,form,request,response);	
		    }	
			writeAble = (p == 1) ? "yes" : "no";
		    request.setAttribute("writeAble", writeAble); 
		    return myActFwd;
	        }catch(Exception e){
	        	e.printStackTrace();
				rcgl_xspd_Form.setErrMsg("数据连接中断，请重新登陆！");
		    	return new ActionForward("/login.jsp", false);
	        }	        
	 }
	public ActionForward index_to_jsp(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		String userName=session.getAttribute("userName").toString();
//		String userDep=session.getAttribute("userDep").toString();
		String tableName = "xsrxjy";
		String realTable = "xsrxjy";		
		lrh_commen_util commen_util= new lrh_commen_util();
		boolean writeFlag=commen_util.checkUsrPower(userName);
		if(writeFlag==false)
		{
			request.setAttribute("writeAble", "no");
		}
		else
		{
			request.setAttribute("writeAble", "yes");
		}
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		return mapping.findForward("index_to_jsp");
	}

}
