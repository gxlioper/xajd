package xgxt.xljk.zxzx.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.xljk.zxzx.form.*;
import xgxt.xljk.zxzx.util.*;
import xgxt.xsxx.cslg.xsjl.XsxxCslgXsjlForm;
import xgxt.xsxx.cslg.xsjl.XsxxCslgXsjlService;

import java.io.File;
import java.util.*;

import jxl.write.WritableWorkbook;

/**
 * MyEclipse Struts Creation date: 07-11-2007
 */
public class xljk_yycxAtion extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xssqyy_Form xljk_xssqyy_Form = (xljk_xssqyy_Form) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				xljk_xssqyy_Form.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}

			String act = request.getParameter("act");
			String doType = request.getParameter("doType");
			ActionForward myActFwd = null;
			if (act.equals("zycx")) {
				if (doType != null && !doType.equalsIgnoreCase("")) {
					if (doType.equals("zycx_stu")) {
						myActFwd = Zxszy_Yych_Stu(mapping, form, request,
								response);
					} else if (doType.equals("zycx_tec")) {
						myActFwd = Zxszy_Yych_Tec(mapping, form, request,
								response);
					}
				} else {
					myActFwd = Index_To_Jsp(mapping, form, request, response);
				}
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			xljk_xssqyy_Form.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward Index_To_Jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		lrh_commen_util com_uti = new lrh_commen_util();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		HttpSession session = request.getSession();
		String userOnLine = session.getAttribute("userOnLine").toString();
		List sjdList = com_uti.get_dmwhb_dmList("zxszy_sjd");
		List ddList = com_uti.get_dmwhb_dmList("zxszy_dd");
		List zxxList = myutil.get_zxxList();
//		DAO dao = DAO.getInstance();
//		String username = (String)session.getAttribute("userName");
//		String[] userxm = dao.getOneRs("SELECT zxxxm FROM xljk_zxsxxb WHERE zxxbh=?", new String[]{username}, new String[]{"zxxxm"});
//		request.setAttribute("userxm", "yes");
//		if(userxm != null){
//			request.setAttribute("userxm", "yes");
//			request.ser
//		}
		String forward = "";
		if (userOnLine.equals("student")) {
			request.setAttribute("tableName", "xljk_zxszyb");
			request.setAttribute("pk", "XN_ID");
			request.setAttribute("realTable", "");
			request.setAttribute("ddList", ddList);
			request.setAttribute("sjdList", sjdList);
			request.setAttribute("zxxList", zxxList);
			forward = "IndexToJsp";
		} else if (userOnLine.equals("teacher")) {
			request.setAttribute("tableName", "xljk_zxszyb");
			request.setAttribute("pk", "XN_ID");
			request.setAttribute("realTable", "");
			request.setAttribute("ddList", ddList);
			request.setAttribute("sjdList", sjdList);
			request.setAttribute("zxxList", zxxList);
			request.setAttribute("writeAble", "yes");
			forward = "IndexToJsp2";
		}
		request.setAttribute("xxdm", StandardOperation.getXxdm());
		
		request.setAttribute("path", "XLJK_ZXZX.do?act=xljk_yycx");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward(forward);
	}

	private ActionForward Zxszy_Yych_Stu(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		lrh_commen_util com_uti = new lrh_commen_util();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		// myutil.XLJK_ZXZX_Util(request);
		xljk_xssqyy_Form myform = (xljk_xssqyy_Form) form;
		xljk_zxszy_Form zyform = new xljk_zxszy_Form();
		zyform.validate(mapping, request);
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		// String userDep=session.getAttribute("userDep").toString();
		// String userNameReal=session.getAttribute("userNameReal").toString();
		// String userOnLine=session.getAttribute("userOnLine").toString();
		zyform.setTableName(myform.getTableName());
		zyform.setPk(myform.getPk());
		zyform.setXh(userName);

		List sjdList = com_uti.get_dmwhb_dmList("zxszy_sjd");
		List ddList = com_uti.get_dmwhb_dmList("zxszy_dd");

		List zxxList = myutil.get_zxxList();

		List rs = null;
		rs = myutil.Zxszy_findby_sql4(zyform);
		String rsNum ;
		if(rs!=null){
		rsNum = String.valueOf(rs.size());
		}else{
			rsNum="";
		}
		List topTr = myutil.Get_xljk_zxszy_Title2();
		request.setAttribute("rs", rs);

		request.setAttribute("tableName", myform.getTableName());
		request.setAttribute("pk", myform.getPk());
		request.setAttribute("realTable", myform.getRealTable());
		request.setAttribute("ddList", ddList);
		request.setAttribute("sjdList", sjdList);
		request.setAttribute("zxxList", zxxList);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);

		request.setAttribute("path", "XLJK_ZXZX.do?act=xljk_yycx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("IndexToJsp");
	}

	private ActionForward Zxszy_Yych_Tec(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
   		lrh_commen_util com_uti = new lrh_commen_util();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		// myutil.XLJK_ZXZX_Util(request);
		xljk_xssqyy_Form myform = (xljk_xssqyy_Form) form;
		xljk_zxszy_Form zyform = new xljk_zxszy_Form();
		zyform.validate(mapping, request);
		zyform.setXh(myform.getXh());
		String xxdm = (String) request.getSession().getAttribute("xxdm");
		String[] zdm = null;
		List topTr = null;
		List sjdList = com_uti.get_dmwhb_dmList("zxszy_sjd");
		List ddList = com_uti.get_dmwhb_dmList("zxszy_dd");
		List zxxList = myutil.get_zxxList();
		
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			zdm = new String[]{"RQ", "SJD", "DD", "ZXXBH", "XH","XM","XYMC"};
			topTr = myutil.Get_Table_Title("view_zxszy", zdm);
		}else{
			zdm = new String[]{"RQ", "SJD", "DD", "ZXXBH", "XH"};
			topTr = myutil.Get_Table_Title("xljk_zxszyb", zdm);
		}
		List rs = myutil.Zxszy_findby_sql5(zyform);
		String rsNum = String.valueOf(rs.size());

		request.setAttribute("rs", rs);
		request.setAttribute("writeAble", "yes");
		request.setAttribute("tableName", myform.getTableName());
		request.setAttribute("pk", myform.getPk());
		request.setAttribute("realTable", myform.getRealTable());
		request.setAttribute("ddList", ddList);
		request.setAttribute("sjdList", sjdList);
		request.setAttribute("zxxList", zxxList);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);

		request.setAttribute("path", "XLJK_ZXZX.do?act=xljk_yycx");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("IndexToJsp2");
	}
	
	
}