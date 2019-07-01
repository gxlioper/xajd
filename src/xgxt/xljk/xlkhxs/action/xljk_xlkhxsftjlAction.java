package xgxt.xljk.xlkhxs.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.xljk.lrh_Util.model.stu_info_model;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.xljk.lrh_Util.util.stu_info_util;
import xgxt.xljk.xlkhxs.form.xljk_xlkhxs_form;
import xgxt.xljk.xlkhxs.util.xljk_xlkhxsftjl_util;

/** 
 * MyEclipse Struts
 * Creation date: 08-16-2007
 * 
 * XDoclet definition:
 * @struts.action path="/xljk_xlkhxsftjl" name="xljk_xlkhxs_form" scope="request" validate="true"
 */
public class xljk_xlkhxsftjlAction extends CommonAction {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		xljk_xlkhxs_form xljk_xlkhxs_form = (xljk_xlkhxs_form) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				xljk_xlkhxs_form.setErrMsg("登陆超时，请重新登陆！");
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

			if (act.equals("xljk_xlkhxsftjl")) {
				if (doType != null && !doType.equalsIgnoreCase("")) {
					if (doType.equals("xlkhxsftjl_add")) {
						myActFwd = xlkhxsftjl_add(mapping, form, request,
								response);
					} else if (doType.equals("stu_info")) {
						myActFwd = stu_info(mapping, form, request, response);
					} else if (doType.equals("xlkhxsftjl_search")) {
						myActFwd = xlkhxsftjl_search(mapping, form, request,
								response);
					} else if (doType.equals("xlkhxsftjl_view")) { 
						myActFwd = xlkhxsftjl_view(mapping, form, request,
								response);
					} else if (doType.equals("xlkhxsftjl_modi")) {
						myActFwd = xlkhxsftjl_modi(mapping, form, request,
								response);
					} else if (doType.equals("xlkhxsftjl_del")) {
						myActFwd = xlkhxsftjl_del(mapping, form, request,
								response);
					} else if (doType.equals("xlkhxs_ftjl_view")) { //心理困惑学生——访谈记录 查看
						myActFwd = xlkhxs_ftjl_view(mapping, form, request,
								response);
					}
				} else {
					myActFwd = index_to_jsp(mapping, form, request, response);  //index
				}
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			xljk_xlkhxs_form.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward index_to_jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		myform.deal_gbk();
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("bjList", commen_util.get_bjList(myform.getXydm(),
				myform.getZydm()));
		boolean writeFlag = commen_util.checkUsrPower(userName);
		if ("yes".equals(myform.getFtjl_view_flag())) {
			request.setAttribute("writeAble", "no");
		} else {
			if (writeFlag == false) {
				request.setAttribute("writeAble", "no");
			} else {
				request.setAttribute("writeAble", "yes");
			}
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myform.setXydm(userDep);
		}
		request.setAttribute("path", "xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl");
		FormModleCommon.commonRequestSet(request);
		appendProperties(request, myform.getXydm(), myform.getZydm(), myform.getNj());
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward xlkhxsftjl_add(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		//			lrh_commen_util commen_util= new lrh_commen_util();
		xljk_xlkhxsftjl_util myutil = new xljk_xlkhxsftjl_util();
		myutil.xljk_xlkhxsftjl_util1(request);
		//			HttpSession session = request.getSession();
		//			String userName=session.getAttribute("userName").toString();
		//			String userDep=session.getAttribute("userDep").toString();
		myform.deal_gbk();
		String add_flag = request.getParameter("add_flag");
		if (add_flag != null && !add_flag.equalsIgnoreCase("")) {
			if (add_flag.equals("yes")) {
				String message = myutil.xlkhxsftjl_add(myform);
				request.setAttribute("message", message);
			}
		}
		request.setAttribute("path", "xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xlkhxsftjl_add");
	}

	private ActionForward stu_info(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		//		 		lrh_commen_util commen_util= new lrh_commen_util();
		myform.deal_gbk();
		String url = "/xgxt/xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl&doType=stu_info";
		String xh = request.getParameter("stu_id");
		if (xh != null && !xh.equalsIgnoreCase("")) {
			myform.setXh(xh);
			stu_info_util stu_info = new stu_info_util();
			stu_info_model stu_mod = new stu_info_model();
			stu_mod = stu_info.stu_find_byxh(xh);
			myform.setXb(stu_mod.getXB());
			myform.setXm(stu_mod.getXM());
			myform.setXydm(stu_mod.getXYDM());
			myform.setXymc(stu_mod.getXYMC());
			myform.setBjdm(stu_mod.getBJDM());
			myform.setBjmc(stu_mod.getBJMC());
			myform.setSjhm(stu_mod.getSJHM());
			return mapping.findForward("xlkhxsftjl_add");
		} else {
			request.setAttribute("url", url);
			return mapping.findForward("stu_info");
		}
	}

	private ActionForward xlkhxsftjl_search(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		xljk_xlkhxsftjl_util myutil = new xljk_xlkhxsftjl_util();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		myform.deal_gbk();
		boolean writeFlag = commen_util.checkUsrPower(userName);
		if ("yes".equals(myform.getFtjl_view_flag())) {
			request.setAttribute("writeAble", "no");
		} else {
			if (writeFlag == false) {
				request.setAttribute("writeAble", "no");
			} else {
				request.setAttribute("writeAble", "yes");
			}
		}
//		request.setAttribute("xyList", commen_util.get_xyList());
//		request.setAttribute("bjList", commen_util.get_bjList(myform.getXydm(),
//				myform.getZydm()));
		List rs = myutil.xlkhxsftjl_search(myform);
		String[] zdm = { "XH", "XM", "XB", "XYMC", "BJMC", "FTR", "FTSJ",
				"FTDD" };
		List topTr = commen_util.Get_Table_Title("VIEW_XLJK_XLKHXSFTB", zdm);
		String rsNum = String.valueOf(rs.size());
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		if ("xy".equalsIgnoreCase(userType)) {
			myform.setXydm(userDep);
		}
		
		request.setAttribute("nobutton", myform.getNobutton());
		request.setAttribute("path", "xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl");
		FormModleCommon.commonRequestSet(request);
		appendProperties(request, myform.getXydm(), myform.getZydm(), myform.getNj());
		return mapping.findForward("index_to_jsp");

	}

	private ActionForward xlkhxsftjl_view(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		//				lrh_commen_util commen_util= new lrh_commen_util();
		xljk_xlkhxsftjl_util myutil = new xljk_xlkhxsftjl_util();
		//				HttpSession session = request.getSession();
		//				String userName=session.getAttribute("userName").toString();
		//				String userDep=session.getAttribute("userDep").toString();
		myform.deal_gbk();
		String xn_id = request.getParameter("xn_id");
		myform.setXljk_xlkhxsftjl_xnid(xn_id);
		myform = myutil.xlkhxsftjl_view(myform);
		request.setAttribute("path", "xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xlkhxsftjl_view");
	}

	private ActionForward xlkhxsftjl_modi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		//				lrh_commen_util commen_util= new lrh_commen_util();
		xljk_xlkhxsftjl_util myutil = new xljk_xlkhxsftjl_util();
		myutil.xljk_xlkhxsftjl_util1(request);
		//				HttpSession session = request.getSession();
		//				String userName=session.getAttribute("userName").toString();
		//				String userDep=session.getAttribute("userDep").toString();
		myform.deal_gbk();
		String xn_id = request.getParameter("xn_id");
		String modi_flag = request.getParameter("modi_flag");
		if (modi_flag != null && !modi_flag.equalsIgnoreCase("")) {
			if (modi_flag.equals("yes")) {
				myform.setXljk_xlkhxsftjl_xnid(xn_id);
				String message = myutil.xlkhxsftjl_modi(myform);
				request.setAttribute("message", message);
			}
		} else {
			myform.setXljk_xlkhxsftjl_xnid(xn_id);
			myform = myutil.xlkhxsftjl_view(myform);
		}
		request.setAttribute("path", "xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xn_id", xn_id);
		return mapping.findForward("xlkhxsftjl_modi");
	}

	private ActionForward xlkhxsftjl_del(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		xljk_xlkhxsftjl_util myutil = new xljk_xlkhxsftjl_util();
		myutil.xljk_xlkhxsftjl_util1(request);
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		myform.deal_gbk();
		String xn_id = request.getParameter("xn_id");
		myform.setXljk_xlkhxsftjl_xnid(xn_id);
		String message = myutil.xlkhxsftjl_del(myform);
		request.setAttribute("message", message);
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("bjList", commen_util.get_bjList(myform.getXydm(),
				myform.getZydm()));
		boolean writeFlag = commen_util.checkUsrPower(userName);

		if ("yes".equals(myform.getFtjl_view_flag())) {
			request.setAttribute("writeAble", "no");
		} else {
			if (writeFlag == false) {
				request.setAttribute("writeAble", "no");
			} else {
				request.setAttribute("writeAble", "yes");
			}
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myform.setXydm(userDep);
		}
		appendProperties(request, myform.getXydm(), myform.getZydm(), myform.getNj());
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward xlkhxs_ftjl_view(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		xljk_xlkhxsftjl_util myutil = new xljk_xlkhxsftjl_util();
		HttpSession session = request.getSession();
		//				String userName=session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String xn_id = request.getParameter("xn_id");
		String flg = request.getParameter("flg");
		myform.setXljk_xlkhxsftjl_xnid(xn_id);
		boolean writeFlag = false;
		try {
			Object ob = request.getAttribute("xljk_xlkhxs_form");
			myform = (xljk_xlkhxs_form) ob;
			myform.setFtjl_view_flag("yes");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		List rs = myutil.xlkhxsftjl_search(myform);
		String[] zdm = { "XH", "XM", "XB", "XYMC", "BJMC", "FTR", "FTSJ",
				"FTDD" };
		List topTr = commen_util.Get_Table_Title("VIEW_XLJK_XLKHXSFTB", zdm);
		String rsNum = String.valueOf(rs.size());
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("bjList", commen_util.get_bjList(myform.getXydm(),
				myform.getZydm()));
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		if (writeFlag == false) {
			request.setAttribute("writeAble", "no");
		} else {
			request.setAttribute("writeAble", "yes");
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myform.setXydm(userDep);
		}
		
		request.setAttribute("nobutton", myform.getNobutton());
		
		request.setAttribute("path", "xljk_xlkhxs.do?act=xljk_xlkhxs");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("index_to_jsp");
	}

}