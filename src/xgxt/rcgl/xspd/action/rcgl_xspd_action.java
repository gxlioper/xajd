
package xgxt.rcgl.xspd.action;

import java.util.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.rcgl.xspd.form.rcgl_xspd_form;
import xgxt.rcgl.xspd.util.*;
import xgxt.xljk.lrh_Util.model.stu_info_model;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.xljk.lrh_Util.util.stu_info_util;

/**
 * @author LP 2007-11-14
 *
 */
public class rcgl_xspd_action extends Action{
             
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
	        rcgl_xspd_form rcgl_xspd_Form = (rcgl_xspd_form)form;
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
	        String act = request.getParameter("act");
		    String doType=request.getParameter("doType");
			String userName=session.getAttribute("userName").toString();
			String userType=session.getAttribute("userOnLine").toString();		  	
			boolean isStu = (userType.equalsIgnoreCase("student"));
		  	String writeAble = "" ;
	    	String power = "rcgl_xspd.do?act=xspd";
	    	int p = Base.chkUPower(userName, power, isStu);
		    if(act==null||"".equalsIgnoreCase(act)){
		    	request.setAttribute("errMsg", "发生错误！");
		    	return mapping.findForward("false");
		    }else if(act.equalsIgnoreCase("xspd")){
		    	if(doType!=null&&!doType.equalsIgnoreCase("")){
		    		if(doType.equalsIgnoreCase("xspd_search")){
		    			myActFwd = xspd_search(mapping,form,request,response);
		    		} else if(doType.equalsIgnoreCase("xspd_add")){
		    			myActFwd = xspd_add(mapping,form,request,response);
		    		}else if(doType.equalsIgnoreCase("stu_info")){
		    			myActFwd = stu_info(mapping,form,request,response);
		    		}else if(doType.equalsIgnoreCase("xspd_view")){
		    			myActFwd = xspd_view(mapping,form,request,response);
		    		}else if(doType.equalsIgnoreCase("xspd_modi")){
		    			myActFwd = xspd_modi(mapping,form,request,response);
		    		}else if(doType.equalsIgnoreCase("xspd_del")){
		    			myActFwd = xspd_del(mapping,form,request,response);
		    		}
		    	}else{
		    		myActFwd = index_to_jsp(mapping,form,request,response);	
		    	}
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
//		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
//		String userName=session.getAttribute("userName").toString();
//		String userDep=session.getAttribute("userDep").toString();
		rcgl_xspd_form myform = (rcgl_xspd_form)form;
		lrh_commen_util commen_util= new lrh_commen_util();
		myform.DealString_gbk();		
		String xydm=myform.getXydm();
		String zydm=myform.getZydm();
		List rcxwList = commen_util.get_rcgl_getList("rcgl_rcxw");
		List pdList=commen_util.get_rcgl_getList("rcgl_xspd");
		request.setAttribute("rcxwList", rcxwList);

		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		request.setAttribute("pdList",pdList);
		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		request.setAttribute("xqList", dao.getXqList());// 发送学期列表			
		//request.setAttribute("writeAble", writeAble);
		return mapping.findForward("index_to_jsp");
	}
	public ActionForward xspd_search(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		DAO dao = DAO.getInstance();
		rcgl_xspd_form myform = (rcgl_xspd_form)form;
		lrh_commen_util commen_util = new lrh_commen_util();
		rcgl_xspd_util myutil = new rcgl_xspd_util();
		myutil.rcgl_xspd_util1(request);
		String xydm = myform.getXydm();
		String zydm = myform.getZydm();
		List rs=myutil.xspd_search(myform);
		List rcxwList=commen_util.get_rcgl_getList("rcgl_rcxw");
		List pdList=commen_util.get_rcgl_getList("rcgl_xspd");
 		String rsNum=String.valueOf(rs.size());
		String []zdm={"XH","XM","XB","MKMC","PDMKMC","XYMC","BJMC","JLR","RQ"};
 		List topTr=commen_util.Get_Table_Title("view_rcgl_xspdxx", zdm);
 		
 		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
	    request.setAttribute("xqList", dao.getXqList());// 发送学期列表    
 		
 		request.setAttribute("rs",rs);
 		request.setAttribute("rsNum", rsNum);
 		request.setAttribute("topTr", topTr);
 		request.setAttribute("rcxwList", rcxwList);
 		request.setAttribute("xyList", commen_util.get_xyList());
 		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		request.setAttribute("pdList",pdList);
 		//request.setAttribute("writeAble", writeAble);
 	    return mapping.findForward("index_to_jsp");
	}
   public ActionForward xspd_add(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	    DAO dao = DAO.getInstance();
	    rcgl_xspd_form myform = (rcgl_xspd_form) form;
	    lrh_commen_util commen_util = new lrh_commen_util();
	    rcgl_xspd_util myutil = new rcgl_xspd_util();
	    myutil.rcgl_xspd_util1(request);
	    List rcxwList=commen_util.get_rcgl_getList("rcgl_rcxw");
		List pdList=commen_util.get_rcgl_getList("rcgl_xspd");
	    myform.DealString_gbk();
	    String add_flag=request.getParameter("add_flag");
		if(add_flag!=null&&!add_flag.equalsIgnoreCase(""))
		{
			if(add_flag.equals("yes"))
			{
				String message=myutil.xspd_add(myform);
				request.setAttribute("message", message);
			}
		}		
	    
	    request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		request.setAttribute("xqList", dao.getXqList());// 发送学期列表
		myform.setXn(dao.getConf(0));//获得系统设置表中当前学年
		myform.setXq(dao.getConf(1));//获得系统设置表中当前学期
   	
		request.setAttribute("pdList", pdList);
		request.setAttribute("rcxwList", rcxwList);
		return mapping.findForward("xspd_add");
   }
   private ActionForward stu_info(ActionMapping mapping, ActionForm form,
	   	    HttpServletRequest request, HttpServletResponse response)
	   	    throws Exception {
		        DAO dao = DAO.getInstance();
		 		rcgl_xspd_form myform = (rcgl_xspd_form)form;
		 		lrh_commen_util commen_util= new lrh_commen_util();
		 		myform.DealString_gbk();
		 		String url="/xgxt/rcgl_xspd.do?act=xspd&doType=stu_info";
				String xh=request.getParameter("stu_id");
				if(xh!=null&&!xh.equalsIgnoreCase(""))
				{
					List rcxwList=commen_util.get_rcgl_getList("rcgl_rcxw");
					List pdList = commen_util.get_rcgl_getList("rcgl_xspd");
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
					request.setAttribute("pdList", pdList);
			    	request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
			    	request.setAttribute("xqList", dao.getXqList());// 发送学期列表
			    	myform.setXn(dao.getConf(0));//获得系统设置表中当前学年
			    	myform.setXq(dao.getConf(1));//获得系统设置表中当前学期
			    	
					return mapping.findForward("xspd_add");
				}
				else
				{
					request.setAttribute("url", url);
					return mapping.findForward("stu_info");
				}
	} 
   private ActionForward xspd_view(ActionMapping mapping,ActionForm form,
		   HttpServletRequest request,HttpServletResponse response)throws Exception{
	    DAO dao = DAO.getInstance();
		rcgl_xspd_form myform = (rcgl_xspd_form)form;
		rcgl_xspd_util myutil = new rcgl_xspd_util();
		myutil.rcgl_xspd_util1(request);
		myform.DealString_gbk();

		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		request.setAttribute("xqList", dao.getXqList());// 发送学期列表
		String xn_id=request.getParameter("xn_id");
		myform.setRcgl_xspd_xnid(xn_id);
		myform=myutil.xspd_view(myform);
		return mapping.findForward("xspd_view");
   } 
   private ActionForward xspd_modi(ActionMapping mapping,ActionForm form,
		   HttpServletRequest request,HttpServletResponse response)throws Exception{
	    DAO dao = DAO.getInstance();
		rcgl_xspd_form myform = (rcgl_xspd_form)form;
		rcgl_xspd_util myutil = new rcgl_xspd_util();
		myutil.rcgl_xspd_util1(request);
		lrh_commen_util commen_util= new lrh_commen_util();
		myform.DealString_gbk();
		String xn_id=request.getParameter("xn_id");
		myform.setRcgl_xspd_xnid(xn_id);
		String modi_flag=request.getParameter("modi_flag");
		List rcxwList=commen_util.get_rcgl_getList("rcgl_rcxw");
		List pdList = commen_util.get_rcgl_getList("rcgl_xspd");
		if(modi_flag!=null&&!modi_flag.equalsIgnoreCase(""))
		{
			if(modi_flag.equals("yes"))
			{
				xn_id=request.getParameter("xn_id");
				myform.setRcgl_xspd_xnid(xn_id);
				String message=myutil.xspd_modi(myform);
				request.setAttribute("message", message);
			}
		}
		else
		{
			myform=myutil.xspd_view(myform);
		}		 		 		 		
		request.setAttribute("pdList", pdList);
		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		request.setAttribute("xqList", dao.getXqList());// 发送学期列表
		request.setAttribute("xn_id", xn_id);
		request.setAttribute("rcxwList", rcxwList);
		return mapping.findForward("xspd_modi");
	    	    
   }
   private ActionForward xspd_del(ActionMapping mapping, ActionForm form,
	   	    HttpServletRequest request, HttpServletResponse response)
	   	    throws Exception {
		        DAO dao = DAO.getInstance();
		 		rcgl_xspd_form myform = (rcgl_xspd_form)form;
		 		rcgl_xspd_util myutil = new rcgl_xspd_util();
		 		myutil.rcgl_xspd_util1(request);
		 		lrh_commen_util commen_util= new lrh_commen_util();
		 		myform.DealString_gbk();
		 		String xn_id=request.getParameter("xn_id");
		 		myform.setRcgl_xspd_xnid(xn_id);
		 		String message=myutil.xspd_del(myform);
		 		String xydm=myform.getXydm();
		 		String zydm=myform.getZydm();
		 		List rcxwList=commen_util.get_rcgl_getList("rcgl_rcxw");	 		
		  		List pdList=commen_util.get_rcgl_getList("rcgl_xspd");
		 		
		 		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		    	request.setAttribute("xqList", dao.getXqList());// 发送学期列表    
		 		request.setAttribute("message", message);
				request.setAttribute("rcxwList", rcxwList);
				request.setAttribute("pdList", pdList);
		 		request.setAttribute("xyList", commen_util.get_xyList());
		 		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		 		request.setAttribute("writeAble", "yes");
		 		return mapping.findForward("index_to_jsp");
	} 
}
