package xgxt.xljk.xyxljkgl.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.xljk.xyxljkgl.form.xljk_xyxljkjyry_from;
import xgxt.xljk.xyxljkgl.util.xljk_xsxlgcy_util;
import xgxt.xljk.lrh_Util.util.*;
import xgxt.xljk.xyxljkgl.util.*;

/** 
 * MyEclipse Struts
 * Creation date: 08-02-2007
 * 
 * XDoclet definition:
 * @struts.action path="/xljk_xljkfdy" name="xljk_xyxljkjyry_from" scope="request" validate="true"
 */
public class xljk_xljkfdyAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xyxljkjyry_from xljk_xyxljkjyry_from = (xljk_xyxljkjyry_from) form;// TODO Auto-generated method stub

		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				xljk_xyxljkjyry_from.setErrMsg("登陆超时，请重新登陆！");
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
			if (act.equals("xljk_xljkfdy")) {
				if (doType != null && !doType.equalsIgnoreCase("")) {
					if (doType.equals("fdy_view")) {
						myActFwd = fdy_view(mapping, form, request, response);
					} else if (doType.equals("fdy_search")) {
						myActFwd = fdy_search(mapping, form, request, response);
					} else if (doType.equals("fdy_add")) {
						myActFwd = fdy_add(mapping, form, request, response);
					} else if (doType.equals("fdy_modi")) {
						myActFwd = fdy_modi(mapping, form, request, response);
					} else if (doType.equals("fdy_del")) {
						myActFwd = fdy_del(mapping, form, request, response);
					}
				} else {
					myActFwd = index_to_jsp(mapping, form, request, response);
				}
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			xljk_xyxljkjyry_from.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward index_to_jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		xljk_xyxljkjyry_from myform = (xljk_xyxljkjyry_from) form;
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
		xljk_xsxlgcy_util myutil = new xljk_xsxlgcy_util();
		List sexList = myutil.get_dmwhb_dmList("sex");
		request.setAttribute("userType", userType);
		request.setAttribute("sexList", sexList);
		request.setAttribute("xyList", myutil.get_xyList());
		request.setAttribute("path", "xljk_xljkfdy.do?act=xljk_xljkfdy");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("userDep", userDep);
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward fdy_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		xljk_xyxljkjyry_from myform = (xljk_xyxljkjyry_from) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		xljk_xljkfdy_util myutil = new xljk_xljkfdy_util();
		myutil.xljk_xljkfdy_util1(request);
		myform.deal_gbk();
		String xydm = myform.getXydm();
		String userType = commen_util.get_userType(userDep);
		if ("xx".equalsIgnoreCase(userType)) {
			xydm = myform.getXydm();
		} else {
			xydm = userDep;
			myform.setXydm(xydm);
		}
		String add_flag = request.getParameter("add_flag");
		if (add_flag != null && !add_flag.equalsIgnoreCase("")) {
			if (add_flag.equals("yes")) {
				String message = myutil.fdy_add(myform);
				request.setAttribute("message", message);
			}
		}
		List sexList = commen_util.get_dmwhb_dmList("sex");
		request.setAttribute("userType", userType);
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("sexList", sexList);
		request.setAttribute("userDep", userDep);
		request.setAttribute("path", "xljk_xljkfdy.do?act=xljk_xljkfdy");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fdy_add");
	}

	private ActionForward fdy_search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		xljk_xyxljkjyry_from myform = (xljk_xyxljkjyry_from) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		xljk_xljkfdy_util myutil = new xljk_xljkfdy_util();
		myform.deal_gbk();
		String xydm = myform.getXydm();
		String userType = commen_util.get_userType(userDep);
		if ("xx".equalsIgnoreCase(userType)) {
			xydm = myform.getXydm();
		} else {
			xydm = userDep;
			myform.setXydm(xydm);
		}
		List fdyList = myutil.fdy_search(myform);
		List sexList = commen_util.get_dmwhb_dmList("sex");
		String rsNum = String.valueOf(fdyList.size());
		String[] zdm = { "FDYBH", "XM", "XB", "XYMC", "RZSJ", "CSNY", "SJHM" };
		List topTr = commen_util.Get_Table_Title("xljk_fdyb", zdm);
		request.setAttribute("rs", fdyList);
		request.setAttribute("userType", userType);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("sexList", sexList);
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("path", "xljk_xljkfdy.do?act=xljk_xljkfdy");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("userDep", userDep);
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward fdy_view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xyxljkjyry_from myform = (xljk_xyxljkjyry_from) form;
		//		 		lrh_commen_util commen_util= new lrh_commen_util();
		xljk_xljkfdy_util myutil = new xljk_xljkfdy_util();
		myform.deal_gbk();
		String xn_id = request.getParameter("xn_id");
		myform.setXljk_fdyb_xnid(xn_id);
		myform = myutil.fdy_view(myform);
		request.setAttribute("rs", myform);
		request.setAttribute("path", "xljk_xljkfdy.do?act=xljk_xljkfdy");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fdy_view");
	}

	private ActionForward fdy_modi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		xljk_xyxljkjyry_from myform = (xljk_xyxljkjyry_from) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		xljk_xljkfdy_util myutil = new xljk_xljkfdy_util();
		myutil.xljk_xljkfdy_util1(request);
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
				myform.setXljk_fdyb_xnid(request.getParameter("fdy_xnid"));
				String message = myutil.fdy_modi(myform);
				request.setAttribute("message", message);
			}
		} else {
			String xn_id = request.getParameter("xn_id");
			myform.setXljk_fdyb_xnid(xn_id);
			myform = myutil.fdy_view(myform);
			myform.setXb(commen_util.xljk_dmwhb_dmzh2(myform.getXb(), "sex"));
		}
		List sexList = commen_util.get_dmwhb_dmList("sex");
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("sexList", sexList);
		request.setAttribute("userDep", userDep);
		request.setAttribute("userType", userType);
		request.setAttribute("xnid", myform.getXljk_fdyb_xnid());
		request.setAttribute("path", "xljk_xljkfdy.do?act=xljk_xljkfdy");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fdy_modi");
	}

	private ActionForward fdy_del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		xljk_xyxljkjyry_from myform = (xljk_xyxljkjyry_from) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		xljk_xljkfdy_util myutil = new xljk_xljkfdy_util();
		myutil.xljk_xljkfdy_util1(request);
		myform.deal_gbk();
		String xn_id = request.getParameter("xn_id");
		myform.setXljk_fdyb_xnid(xn_id);
		String message = myutil.fdy_del(myform);
		String xydm = myform.getXydm();
		String userType = commen_util.get_userType(userDep);
		if ("xx".equalsIgnoreCase(userType)) {
			xydm = myform.getXydm();
		} else {
			xydm = userDep;
			myform.setXydm(xydm);
		}
		request.setAttribute("message", message);
		List sexList = commen_util.get_dmwhb_dmList("sex");
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("sexList", sexList);
		request.setAttribute("userDep", userDep);
		request.setAttribute("userType", userType);
		return mapping.findForward("index_to_jsp");
	}

}