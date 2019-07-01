package xgxt.xljk.xyxljkgl.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.Globals;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.czxx.dyxx.DyxxDAO;
import xgxt.utils.FormModleCommon;
import xgxt.xljk.xyxljkgl.form.xljk_xyxljkjyhd_from;
import xgxt.xljk.lrh_Util.util.*;
import xgxt.xljk.xyxljkgl.util.*;

/** 
 * MyEclipse Struts
 * Creation date: 08-02-2007
 * 
 * XDoclet definition:
 * @struts.action path="/xljk_xyxljkjyhd" name="xljk_xyxljkjyhd_from" scope="request" validate="true"
 */
public class xljk_xyxljkjyhdAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xyxljkjyhd_from xljk_xyxljkjyhd_from = (xljk_xyxljkjyhd_from) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				xljk_xyxljkjyhd_from.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			ActionForward myActFwd = null;
			String act = request.getParameter("act");
			String doType = request.getParameter("doType");
			//	    String userName=session.getAttribute("userName").toString();
			//		String userDep=session.getAttribute("userDep").toString();
			//		String userNameReal=session.getAttribute("userNameReal").toString();
			//		String userOnLine=session.getAttribute("userOnLine").toString();
			if (act.equals("xyxljkjyhd")) {
				if (doType != null && !doType.equalsIgnoreCase("")) {
					if (doType.equals("xyxljkjyhd_view")) {
						myActFwd = xyxljkjyhd_view(mapping, form, request,
								response);
					} else if (doType.equals("xyxljkjyhd_search")) {
						myActFwd = xyxljkjyhd_search(mapping, form, request,
								response);
					} else if (doType.equals("xyxljkjyhd_add")) {
						myActFwd = xyxljkjyhd_add(mapping, form, request,
								response);
					} else if (doType.equals("xyxljkjyhd_modi")) {
						myActFwd = xyxljkjyhd_modi(mapping, form, request,
								response);
					} else if (doType.equals("xyxljkjyhd_del")) {
						myActFwd = xyxljkjyhd_del(mapping, form, request,
								response);
					}
				} else {
					myActFwd = index_to_jsp(mapping, form, request, response);
				}
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			xljk_xyxljkjyhd_from.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward index_to_jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		xljk_xyxljkjyhd_from myform = (xljk_xyxljkjyhd_from) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		myform.deal_gbk();
		String xydm = myform.getXydm();
		String userType = commen_util.get_userType(userDep);
		if ("xx".equalsIgnoreCase(userType)) {
			xydm = myform.getXydm();
		} else {
			xydm = userDep;
			myform.setXydm(xydm);
		}
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("path", "xljk_xyxljkjyhd.do?act=xyxljkjyhd");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("userDep", userDep);
		request.setAttribute("userType", userType);
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward xyxljkjyhd_add(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		xljk_xyxljkjyhd_from myform = (xljk_xyxljkjyhd_from) form;
		xljk_xyxljkjyhd_util myutil = new xljk_xyxljkjyhd_util();
		myutil.xljk_xljkfdy_util(request);
		lrh_commen_util commen_util = new lrh_commen_util();
		myform.deal_gbk();
		String xydm = myform.getXydm();
		String userType = commen_util.get_userType(userDep);
		String message ="";
		String xxdm=Base.xxdm;
		if ("xx".equalsIgnoreCase(userType)) {
			xydm = myform.getXydm();
		} else {
			xydm = userDep;
			myform.setXydm(xydm);
		}
		String add_flag = request.getParameter("add_flag");
		if (add_flag != null && !add_flag.equalsIgnoreCase("")) {
			if (add_flag.equals("yes")) {
				
				//闽江学院增加 文件上传功能 2010.12.6 qlj
				if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)){
					String filePath = null;
		    		String dir = request.getRealPath("/") + "upload";
		    	    File f = new File(dir);
		    	    if (!f.exists()) {
		    		   f.mkdir();
		    	    }
		    	    String dateStr = "";
		    	    Timestamp date = new Timestamp(System.currentTimeMillis());
		    	    dateStr += date.toString().substring(0, 19);
		    	    dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "").replaceAll(":","");
		    	    FormFile file = myform.getUploadFile();
		    	    if(file!=null){
		    	    	 String fName = dateStr+file.getFileName();
		    	    	 filePath = dir + "/" + fName;
		    	    }
		    	    myform.setWjdz(filePath);
		    	    boolean flog=myutil.xyxljkjyhdadd(myform);

			    	    if(flog){
			    	    	//插入成功进行上传
			    		   int size = file.getFileSize();
			    		   if(size>0){//有文件上传
			    			   InputStream in = file.getInputStream();
			    			   OutputStream out = new FileOutputStream(filePath);
			    			   int bytesRead = 0;
			    			   byte[] buffer = new byte[size];
			    			   while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			    				   out.write(buffer, 0, bytesRead);
			    			   }
			    			   out.close();
			    			   in.close();
			    	    }				
					}
			    	message =(flog ? "insert_success" : "insert_fail");
			    	request.setAttribute("message", message);
				}else{
					//非闽江学院不带 文件上传功能
					message = myutil.xyxljkjyhd_add(myform);
					request.setAttribute("message", message);
				}
			}
		}
		List hdxsList = commen_util.get_dmwhb_dmList("xlxhhd_hdxs");
		request.setAttribute("path", "xljk_xyxljkjyhd.do?act=xyxljkjyhd");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("hdxsList", hdxsList);
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("userDep", userDep);
		request.setAttribute("userType", userType);
		return mapping.findForward("xyxljkjyhd_add");
	}

	private ActionForward xyxljkjyhd_search(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		xljk_xyxljkjyhd_from myform = (xljk_xyxljkjyhd_from) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		xljk_xyxljkjyhd_util myutil = new xljk_xyxljkjyhd_util();
		myform.deal_gbk();
		String xydm = myform.getXydm();
		String userType = commen_util.get_userType(userDep);
		if ("xx".equalsIgnoreCase(userType)) {
			xydm = myform.getXydm();
		} else {
			xydm = userDep;
			myform.setXydm(xydm);
		}
		List xyxljkjyhdList = myutil.xyxljkjyhd_search(myform);
		String tableName = "view_xljk_xyhd";
		String[] zdm = { "RQ", "XYMC", "ZT", "CYXS", "RS", "ZCR", "XS","HDMC" };
		List topTr = commen_util.Get_Table_Title(tableName, zdm);
		String rsNum = String.valueOf(xyxljkjyhdList.size());
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", xyxljkjyhdList);
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("path", "xljk_xyxljkjyhd.do?act=xyxljkjyhd");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("userDep", userDep);
		request.setAttribute("userType", userType);
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward xyxljkjyhd_view(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xljk_xyxljkjyhd_from myform = (xljk_xyxljkjyhd_from) form;
		//		 		lrh_commen_util commen_util= new lrh_commen_util();
		xljk_xyxljkjyhd_util myutil = new xljk_xyxljkjyhd_util();
		myform.deal_gbk();
		String xn_id = request.getParameter("xn_id");
		myform.setXlxhhd_xnid(xn_id);
		myform = myutil.xyxljkjyhd_view(myform);
		//闽江学院文件地址
		request.setAttribute("wjdz", myform.getWjdz());
		
		request.setAttribute("path", "xljk_xyxljkjyhd.do?act=xyxljkjyhd");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xyxljkjyhd_view");
	}

	private ActionForward xyxljkjyhd_modi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		xljk_xyxljkjyhd_from myform = (xljk_xyxljkjyhd_from) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		xljk_xyxljkjyhd_util myutil = new xljk_xyxljkjyhd_util();
		myutil.xljk_xljkfdy_util(request);
		myform.deal_gbk();
		String xydm = myform.getXydm();
		String userType = commen_util.get_userType(userDep);
		if ("xx".equalsIgnoreCase(userType)) {
			xydm = myform.getXydm();
		} else {
			xydm = userDep;
			myform.setXydm(xydm);
		}
		String modi_flag = request.getParameter("modi_flag");
		if (modi_flag != null && !modi_flag.equalsIgnoreCase("")) {
			if (modi_flag.equals("yes")) {
				String xn_id = request.getParameter("xn_id");
				myform.setXlxhhd_xnid(xn_id);
				String message = myutil.xyxljkjyhd_modi(myform);
				request.setAttribute("message", message);
			}
		} else {
			String xn_id = request.getParameter("xn_id");
			myform.setXlxhhd_xnid(xn_id);
			myform = myutil.xyxljkjyhd_view(myform);
//			修改时不应该保存MC
//			myform.setHdxs(commen_util.xljk_dmwhb_dmzh2(myform.getHdxs(),
//					"xlxhhd_hdxs"));
		}
		List hdxsList = commen_util.get_dmwhb_dmList("xlxhhd_hdxs");
		request.setAttribute("hdxsList", hdxsList);
		//闽江学院文件地址
		request.setAttribute("wjdz", myform.getWjdz());
		request.setAttribute("path", "xljk_xyxljkjyhd.do?act=xyxljkjyhd");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xn_id", myform.getXlxhhd_xnid());
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("userDep", userDep);
		request.setAttribute("userType", userType);
		return mapping.findForward("xyxljkjyhd_modi");
	}

	private ActionForward xyxljkjyhd_del(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		xljk_xyxljkjyhd_from myform = (xljk_xyxljkjyhd_from) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		xljk_xyxljkjyhd_util myutil = new xljk_xyxljkjyhd_util();
		myutil.xljk_xljkfdy_util(request);
		myform.deal_gbk();
		String xn_id = request.getParameter("xn_id");
		myform.setXlxhhd_xnid(xn_id);
		String xxdm = Base.xxdm;
		String xydm = myform.getXydm();
		String userType = commen_util.get_userType(userDep);
		if ("xx".equalsIgnoreCase(userType)) {
			xydm = myform.getXydm();
		} else {
			xydm = userDep;
			myform.setXydm(xydm);
		}
		if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)){
			DyxxDAO dyxxDao = new DyxxDAO();
			dyxxDao.fileDel("XLJK_XYHD", "wjdz", "XN_ID", myform.getXlxhhd_xnid());
		}
		String message = myutil.xyxljkjyhd_del(myform);
		request.setAttribute("message", message);
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("writeAble", "yes");
		request.setAttribute("userDep", userDep);
		request.setAttribute("userType", userType);
		return mapping.findForward("index_to_jsp");
	}
}