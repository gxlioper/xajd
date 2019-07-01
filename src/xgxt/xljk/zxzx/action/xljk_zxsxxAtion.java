package xgxt.xljk.zxzx.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.xljk.zxzx.form.xljk_zxsxx_Form;
import xgxt.xljk.zxzx.util.*;

/**
 * MyEclipse Struts Creation date: 06-14-2007
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class xljk_zxsxxAtion extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		xljk_zxsxx_Form myform = new xljk_zxsxx_Form();
		try {
		    int i = Base.chkTimeOut(request.getSession());
		    if (i <= 2) {
		    myform.setErrMsg("登陆超时，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		    }

		    HttpSession session = request.getSession();
		    if (session == null) {
			request.setAttribute("errMsg", "sadfsdf");
			return mapping.findForward("false");
		    } 
		
		ActionForward myActFwd = null;
		String act = request.getParameter("act");
		String doType=request.getParameter("doType");
		
		if (act.equals("xljk_zxsxx")) {
			if(doType!=null&&!doType.equalsIgnoreCase("")){
				if(doType.equals("Modi")){				
				}else if(doType.equals("Del")){
					myActFwd=mapping.findForward("del");
				}else if(doType.equals("Insert")){
					myActFwd=mapping.findForward("insert");
				}else if(doType.equals("View"))	{
					myActFwd=mapping.findForward("View");
				}else if(doType.equals("Search")){
					myActFwd = SearchForZXX(mapping,form,request,response);	
				}else if(doType.equals("forward")){
					myActFwd = JustForward(mapping,form,request,response);	
				}else if(doType.equals("update")){
					myActFwd =mapping.findForward("update");
				}
			}else{
				myActFwd = SearchForZXX(mapping,form,request,response);	
			}
		} else if (act.equals("xljk_xssqyy")) {
			request.setAttribute("act", act);
			return mapping.findForward("ApplyFor");
		}
		
		return myActFwd;
		}
		catch(Exception e){
			e.printStackTrace();
			myform.setErrMsg("数据连接中断，请重新登陆！");
	    	return new ActionForward("/login.jsp", false);
		}
	}
	
	 private ActionForward SearchForZXX(ActionMapping mapping, ActionForm form,
	    	    HttpServletRequest request, HttpServletResponse response)
	    	    throws Exception {
		 
		 	HttpSession session=request.getSession();
		    XLJK_ZXZX_Util myutil= new XLJK_ZXZX_Util(request);
		 	xljk_zxsxx_Form myform = new xljk_zxsxx_Form();
		 	String userTp=session.getAttribute("userType").toString();
			String userType = myform.getUserType();
			String tableName = myform.getTableName();
			String realTable = myform.getRealTable();
			String pk=myform.getPk();
			String xxdm=Base.xxdm;
			myform.validate(mapping, request);
			DAO dao = DAO.getInstance();
			List rs=null;
			List titles= myutil.Get_xljk_zxsxxb_Title();
			rs=myutil.Find_By_SqlComment(myform);
			String rsNum=String.valueOf(rs.size());
			String writeAble="yes";
			//南宁职业学院 咨询师匹配
			if(Globals.XXDM_NNZYJSXY.equalsIgnoreCase(xxdm)
					&&("xx".equalsIgnoreCase(userTp) || "admin".equalsIgnoreCase(userTp))){
				request.setAttribute("showzxspp", "showzxspp");
			}
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("topTr", titles);
			request.setAttribute("rsNum", rsNum);
			request.setAttribute("rs", rs);
			request.setAttribute("userType", userType);
			request.setAttribute("tableName", tableName);
			request.setAttribute("pk", pk);	
			request.setAttribute("sexList", dao.getSexList());
			request.setAttribute("realTable", realTable);
			return mapping.findForward("ReturnToJsp_zxsxx");	
	 }
	 
	 private ActionForward JustForward(ActionMapping mapping, ActionForm form,
	    	    HttpServletRequest request, HttpServletResponse response)
	    	    throws Exception {
		 		xljk_zxsxx_Form myform = new xljk_zxsxx_Form();
		 		myform.validate(mapping, request);
		 		
		 		return null;
	 }
	 
}