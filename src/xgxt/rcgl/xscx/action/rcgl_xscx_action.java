
package xgxt.rcgl.xscx.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.rcgl.xscx.form.rcgl_xscx_form;
import xgxt.xljk.lrh_Util.model.stu_info_model;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.xljk.lrh_Util.util.stu_info_util;
import xgxt.rcgl.xscx.util.*;

import xgxt.DAO.DAO;

/**
 * @author LP 2007-11-12
 *
 */

public class rcgl_xscx_action extends Action{
	
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {	
		    rcgl_xscx_form rcgl_xscx_Form = (rcgl_xscx_form) form;
		try {
		    int i = Base.chkTimeOut(request.getSession());
		    if (i <= 2) {
		    	rcgl_xscx_Form.setErrMsg("登陆超时，请重新登陆！");
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
		    String userName=session.getAttribute("userName").toString();
//		    String userDep=session.getAttribute("userDep").toString();
//		    String userNameReal=session.getAttribute("userNameReal").toString();
		    String userType=session.getAttribute("userOnLine").toString();		  	
			boolean isStu = (userType.equalsIgnoreCase("student"));
		  	String writeAble = "" ;
	    	String power = "rcgl_xscx.do?act=xscx";
	    	int p = Base.chkUPower(userName, power, isStu);
		    if(act==null||"".equalsIgnoreCase(act)){
		    	request.setAttribute("errMsg", "发生错误！");
				return mapping.findForward("false");
		    }else if (act.equalsIgnoreCase("xscx")){
		    	if(doType!=null&&!doType.equalsIgnoreCase("")){
		    		if(doType.equalsIgnoreCase("xscx_search")){
		    			myActFwd = xscx_search(mapping,form,request,response);
		    		}else if(doType.equalsIgnoreCase("xscx_add")){
		    			myActFwd = xscx_add(mapping,form,request,response);
		    		}else if(doType.equalsIgnoreCase("stu_info")){
		    			myActFwd = stu_info(mapping,form,request,response);
		    		}else if(doType.equalsIgnoreCase("xscx_view")){
		    			myActFwd = xscx_view(mapping,form,request,response);
		    		}else if(doType.equalsIgnoreCase("xscx_modi")){
		    			myActFwd = xscx_modi(mapping,form,request,response);
		    		}else if(doType.equalsIgnoreCase("xscx_del")){
                        myActFwd = xscx_del(mapping,form,request,response);		    			
		    		}
		    	}else{
		    		myActFwd = index_to_jsp(mapping,form,request,response);	
		    	}
		    }
		    writeAble = (p == 1) ? "yes" : "no";
		    request.setAttribute("writeAble", writeAble); 
		    return myActFwd;    
		}catch (Exception e){
			e.printStackTrace();
			rcgl_xscx_Form.setErrMsg("数据连接中断，请重新登陆！");
	    	return new ActionForward("/login.jsp", false);
		}
		
	}
	
	private ActionForward index_to_jsp(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
 		String userName=session.getAttribute("userName").toString();
// 		String userDep=session.getAttribute("userDep").toString();
 		rcgl_xscx_form myform = (rcgl_xscx_form)form;
 		lrh_commen_util commen_util= new lrh_commen_util();
 		myform.DealString_gbk();
 		boolean writeFlag=commen_util.checkUsrPower(userName);
 		String xydm=myform.getXydm();
 		String zydm=myform.getZydm();
 	    List rcxwList = commen_util.get_rcgl_getList("rcgl_rcxw");
 		List cxList=commen_util.get_rcgl_getList("rcgl_xscx");
		request.setAttribute("rcxwList", rcxwList);

 		request.setAttribute("xyList", commen_util.get_xyList());
 		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
 		request.setAttribute("cxList",cxList);
 		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		request.setAttribute("xqList", dao.getXqList());// 发送学期列表	
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
	private ActionForward  xscx_search(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		DAO dao = DAO.getInstance();
		rcgl_xscx_form myform = (rcgl_xscx_form)form;
		lrh_commen_util commen_util = new lrh_commen_util();
		rcgl_xscx_util myutil = new rcgl_xscx_util();
		String xydm = myform.getXydm();
		String zydm = myform.getZydm();
		List rs=myutil.xscx_search(myform);
		List rcxwList=commen_util.get_rcgl_getList("rcgl_rcxw");
		List cxList=commen_util.get_rcgl_getList("rcgl_xscx");
 		String rsNum=String.valueOf(rs.size());
		String []zdm={"XH","XM","XB","MKMC","CXMKMC","XYMC","BJMC","JLR","RQ"};
 		List topTr=commen_util.Get_Table_Title("view_rcgl_xscxxx", zdm);
 		
 		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
	    request.setAttribute("xqList", dao.getXqList());// 发送学期列表    
 		
 		request.setAttribute("rs",rs);
 		request.setAttribute("rsNum", rsNum);
 		request.setAttribute("topTr", topTr);
 		request.setAttribute("rcxwList", rcxwList);
 		request.setAttribute("xyList", commen_util.get_xyList());
 		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
 		request.setAttribute("writeAble", "yes");
 		
 		request.setAttribute("cxList",cxList);
 	    return mapping.findForward("index_to_jsp");
	}
   private ActionForward xscx_add(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
	    DAO dao = DAO.getInstance();
	    rcgl_xscx_form myform = (rcgl_xscx_form) form;
	    lrh_commen_util commen_util = new lrh_commen_util();
	    rcgl_xscx_util myutil = new rcgl_xscx_util();
	    myutil.rcgl_xscx_util1(request);
	    List rcxwList=commen_util.get_rcgl_getList("rcgl_rcxw");
	    List cxList = commen_util.get_rcgl_getList("rcgl_xscx");
	    myform.DealString_gbk();
	    String add_flag=request.getParameter("add_flag");
 		if(add_flag!=null&&!add_flag.equalsIgnoreCase(""))
 		{
 			if(add_flag.equals("yes"))
 			{
 				String message=myutil.xscx_add(myform);
 				request.setAttribute("message", message);
 			}
 		}		
	    
	    request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
 		request.setAttribute("xqList", dao.getXqList());// 发送学期列表
 		myform.setXn(dao.getConf(0));//获得系统设置表中当前学年
 		myform.setXq(dao.getConf(1));//获得系统设置表中当前学期
    	
 		request.setAttribute("cxList", cxList);
 		request.setAttribute("rcxwList", rcxwList);
 		return mapping.findForward("xscx_add");
   }
   private ActionForward stu_info(ActionMapping mapping, ActionForm form,
   	    HttpServletRequest request, HttpServletResponse response)
   	    throws Exception {
	        DAO dao = DAO.getInstance();
	 		rcgl_xscx_form myform = (rcgl_xscx_form)form;
	 		lrh_commen_util commen_util= new lrh_commen_util();
	 		myform.DealString_gbk();
	 		String url="/xgxt/rcgl_xscx.do?act=xscx&doType=stu_info";
			String xh=request.getParameter("stu_id");
			if(xh!=null&&!xh.equalsIgnoreCase(""))
			{
				List rcxwList=commen_util.get_rcgl_getList("rcgl_rcxw");
				List cxList = commen_util.get_rcgl_getList("rcgl_xscx");
				request.setAttribute("rcxwList", rcxwList);
				myform.setXh(xh);
				stu_info_util stu_info= new stu_info_util();
				stu_info_model stu_mod=new stu_info_model();
				stu_mod=stu_info.stu_find_byxh(xh);
				myform.setXb(stu_mod.getXB());
				myform.setXm(stu_mod.getXM());
				myform.setXydm(stu_mod.getXYDM());
				myform.setXymc(stu_mod.getXYMC());
				myform.setBjdm(stu_mod.getBJDM());
				myform.setBjmc(stu_mod.getBJMC());
				request.setAttribute("cxList", cxList);
		    	request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		    	request.setAttribute("xqList", dao.getXqList());// 发送学期列表
		    	myform.setXn(dao.getConf(0));//获得系统设置表中当前学年
		    	myform.setXq(dao.getConf(1));//获得系统设置表中当前学期
		    	
				return mapping.findForward("xscx_add");
			}
			else
			{
				request.setAttribute("url", url);
				return mapping.findForward("stu_info");
			}
   }
   private ActionForward xscx_view(ActionMapping mapping,ActionForm form,
		   HttpServletRequest request,HttpServletResponse response)throws Exception{
	    DAO dao = DAO.getInstance();
		rcgl_xscx_form myform = (rcgl_xscx_form)form;
		rcgl_xscx_util myutil = new rcgl_xscx_util();
		myform.DealString_gbk();

		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		request.setAttribute("xqList", dao.getXqList());// 发送学期列表
		String xn_id=request.getParameter("xn_id");
		myform.setRcgl_xscx_xnid(xn_id);
		myform=myutil.xscx_view(myform);
		return mapping.findForward("xscx_view");
   }
   private ActionForward xscx_modi(ActionMapping mapping,ActionForm form,
		   HttpServletRequest request,HttpServletResponse response)throws Exception{
	    DAO dao = DAO.getInstance();
		rcgl_xscx_form myform = (rcgl_xscx_form)form;
		rcgl_xscx_util myutil = new rcgl_xscx_util();
		myutil.rcgl_xscx_util1(request);
		lrh_commen_util commen_util= new lrh_commen_util();
		myform.DealString_gbk();
		String xn_id=request.getParameter("xn_id");
		myform.setRcgl_xscx_xnid(xn_id);
		String modi_flag=request.getParameter("modi_flag");
		List rcxwList=commen_util.get_rcgl_getList("rcgl_rcxw");
		List cxList = commen_util.get_rcgl_getList("rcgl_xscx");
		if(modi_flag!=null&&!modi_flag.equalsIgnoreCase(""))
		{
			if(modi_flag.equals("yes"))
			{
				xn_id=request.getParameter("xn_id");
				myform.setRcgl_xscx_xnid(xn_id);
				String message=myutil.xscx_modi(myform);
				request.setAttribute("message", message);
			}
		}
		else
		{
			myform=myutil.xscx_view(myform);
		}		 		 		 		
		request.setAttribute("cxList", cxList);
		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		request.setAttribute("xqList", dao.getXqList());// 发送学期列表
		request.setAttribute("xn_id", xn_id);
		request.setAttribute("rcxwList", rcxwList);
		return mapping.findForward("xscx_modi");
	    	    
   }
   private ActionForward xscx_del(ActionMapping mapping, ActionForm form,
   	    HttpServletRequest request, HttpServletResponse response)
   	    throws Exception {
	        DAO dao = DAO.getInstance();
	 		rcgl_xscx_form myform = (rcgl_xscx_form)form;
	 		rcgl_xscx_util myutil = new rcgl_xscx_util();
	 		myutil.rcgl_xscx_util1(request);
	 		lrh_commen_util commen_util= new lrh_commen_util();
	 		myform.DealString_gbk();
	 		String xn_id=request.getParameter("xn_id");
	 		myform.setRcgl_xscx_xnid(xn_id);
	 		String message=myutil.xscx_del(myform);
	 		String xydm=myform.getXydm();
	 		String zydm=myform.getZydm();
	 		List rcxwList=commen_util.get_rcgl_getList("rcgl_rcxw");	 		
	  		List cxList=commen_util.get_rcgl_getList("rcgl_xscx");
	 		
	 		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
	    	request.setAttribute("xqList", dao.getXqList());// 发送学期列表    
	 		request.setAttribute("message", message);
			request.setAttribute("rcxwList", rcxwList);
			request.setAttribute("cxList", cxList);
	 		request.setAttribute("xyList", commen_util.get_xyList());
	 		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
	 		request.setAttribute("writeAble", "yes");
	 		return mapping.findForward("index_to_jsp");
   } 
}
