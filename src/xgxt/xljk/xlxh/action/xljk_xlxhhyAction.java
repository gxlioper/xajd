/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.xljk.xlxh.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.lrh_Util.model.stu_info_model;
import xgxt.xljk.lrh_Util.util.stu_info_util;
import xgxt.xljk.xlxh.Form.xljk_xlxhhy_from;
import xgxt.xljk.xlxh.model.XLJK_XLXH_MODEL;
import xgxt.xljk.xlxh.util.XLJK_XLXH_UTIL;

/** 
 * MyEclipse Struts
 * Creation date: 06-19-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class xljk_xlxhhyAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xlxhhy_from myform = (xljk_xlxhhy_from) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				myform.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			ActionForward myActFwd = null;
			String act = request.getParameter("act");
			String doType = request.getParameter("doType");
			if (act.equals("xljk_xlxhhy")) {
				if (doType != null && !doType.equalsIgnoreCase("")) {
					if (doType.equals("Modi")) {
						myActFwd = XLJK_XLXHHD_Modi(mapping, form, request,
								response);
					} else if (doType.equals("Del")) {
						myActFwd = XLJK_XLXHHD_Del(mapping, form, request,
								response);
					} else if (doType.equals("Insert")) {
						myActFwd = XLJK_XLXHHD_Insert(mapping, form, request,
								response);
					} else if (doType.equals("Search")) {
						myActFwd = XLJK_XLXHHD_Search(mapping, form, request,
								response);
					} else if (doType.equals("Menmber_Detail")) {  //会员详细信息
						myActFwd = XLJK_XLXHHD_Detail(mapping, form, request,
								response);
					} else if (doType.equals("stu_info")) {
						myActFwd = Stu_Info(mapping, form, request, response);
					}
				} else {
					myActFwd = index_to_jsp(mapping, form, request, response);  //index
				}
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			myform.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward index_to_jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xlxhhy_from myform = (xljk_xlxhhy_from) form;
		DAO dao = DAO.getInstance();
		String xydm = myform.getXydm();
		String zydm = myform.getZydm();
		String tableName = "xljk_xlxhhyb";
		String realTable = "VIEW_XLXHHYXX";
		String pk = "XN_ID";
		request.setAttribute("writeAble", "yes");
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("pk", pk);
		request.setAttribute("sexList", dao.getSexList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward XLJK_XLXHHD_Search(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XLJK_XLXH_UTIL myutil = new XLJK_XLXH_UTIL();
		xljk_xlxhhy_from myform = (xljk_xlxhhy_from) form;
		myform.deal_gbk();
		DAO dao = DAO.getInstance();
		String xb = myform.getXb();
		String xydm = myform.getXydm();
		String zydm = myform.getZydm();
		String tableName = "VIEW_XLXHHY";
		String realTable = "";
		String pk = "XN_ID";
		List rss = myutil.xljk_xlxhhy_findbysql(myform);
		String[] zdm = { "HYBH", "XM", "XB", "HYXH", "CSRQ", "XYMC", "BJMC" };
		List tr = myutil.Get_Table_Title("VIEW_XLXHHY", zdm);
		String rsNum = String.valueOf(rss.size());
		Object message = request.getAttribute("del_message");
		try {
			String mes = StringUtils.isNull((String)message) ? "" : message.toString();
			if (mes != null && !mes.equalsIgnoreCase("")) {
				request.setAttribute("message", mes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		myform.setXb(xb);
		request.setAttribute("writeAble", "yes");
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rss);
		request.setAttribute("topTr", tr);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("pk", pk);
		request.setAttribute("sexList", dao.getSexList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward XLJK_XLXHHD_Detail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XLJK_XLXH_UTIL myutil = new XLJK_XLXH_UTIL();
		myutil.XLJK_XLXH_UTIL1(request);
		//		 		xljk_xlxhhy_from myform=(xljk_xlxhhy_from)form;
		String xlxhhyb_xnid = request.getParameter("xn_id");
		XLJK_XLXH_MODEL mymodel = new XLJK_XLXH_MODEL();
		mymodel = myutil.xljk_xlxhhy_detail(xlxhhyb_xnid);
		request.setAttribute("rs", mymodel);
		return mapping.findForward("member_detail");
	}

	private ActionForward XLJK_XLXHHD_Insert(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XLJK_XLXH_UTIL myutil = new XLJK_XLXH_UTIL();
		myutil.XLJK_XLXH_UTIL1(request);
		stu_info_model stu_mod = new stu_info_model();
		stu_info_util stu_util = new stu_info_util();
		String xh = request.getParameter("stu_id");
		xljk_xlxhhy_from myform = (xljk_xlxhhy_from) form;
		myform.deal_gbk();
		String add_flag = request.getParameter("add_flag");
		String message = "";
		if (add_flag != null && !add_flag.equalsIgnoreCase("")) {
			if (add_flag.equals("yes")) {
				message = myutil.xljk_xlxh_add(myform);
				request.setAttribute("message", message);
				request.setAttribute("rs", myform);
			} else {
				if (xh != null && !xh.equalsIgnoreCase("")) {
					stu_mod = stu_util.stu_find_byxh(xh);
					myform = myutil.stu_info_fill2(stu_mod);
					request.setAttribute("rs", myform);
				} else {
					XLJK_XLXH_MODEL rsModel = new XLJK_XLXH_MODEL();
					request.setAttribute("rs", rsModel);
				}
			}
		} else {
			request.setAttribute("rs", myform);
		}
		return mapping.findForward("member_insert");
	}

	private ActionForward Stu_Info(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		xljk_xlxhhy_from sif = (xljk_xlxhhy_from) form;
		String xh = DealString.toGBK(sif.getXh());
		String xm = DealString.toGBK(sif.getXm());
		String nj = sif.getNj();
		String xydm = sif.getXydm();
		String zydm = sif.getZydm();
		sif.setXm(xm);
		sif.setXh(xh);
		String go = request.getParameter("go");
		stu_info_util stu_util = new stu_info_util();
		if ("go".equalsIgnoreCase(go)) {
			List li = stu_util.stus_find_by(sif);
			request.setAttribute("rs", li);
			if (li != null) {
				int rsNum = li.size();
				request.setAttribute("rsNum", rsNum);
			}
		}
		String[] zdm = { "xh", "xm", "xymc", "zymc", "bjmc" };
		List toptr = stu_util.Get_Table_Title("VIEW_XSJBXX", zdm);
		request.setAttribute("topTr", toptr);
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));
		return mapping.findForward("stu_info");
	}

	private ActionForward XLJK_XLXHHD_Del(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xljk_xlxhhy_from sif = (xljk_xlxhhy_from) form;
		String xlshhy_xnid = request.getParameter("xlxhhy_xnid");
		sif.setXlxhhyb_xnid(xlshhy_xnid);
		XLJK_XLXH_UTIL myutil = new XLJK_XLXH_UTIL();
		myutil.XLJK_XLXH_UTIL1(request);
		String message = myutil.xljk_xlxh_del(sif);
		request.setAttribute("del_message", message);
		return this.XLJK_XLXHHD_Search(mapping, form, request, response);
	}

	private ActionForward XLJK_XLXHHD_Modi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xljk_xlxhhy_from myform = (xljk_xlxhhy_from) form;
		XLJK_XLXH_UTIL myutil = new XLJK_XLXH_UTIL();
		myutil.XLJK_XLXH_UTIL1(request);
		XLJK_XLXH_MODEL mymodel = new XLJK_XLXH_MODEL();
		String xlshhy_xnid = request.getParameter("xlxhhy_xnid");
		myform.deal_gbk();
		String modi_flag = request.getParameter("modi_flag");
		if (modi_flag != null && !modi_flag.equalsIgnoreCase("")) {
			if (modi_flag.equals("yes")) {
				String message = myutil.xjlk_xlxhhy_modi(xlshhy_xnid, myform);
				request.setAttribute("message", message);
				request.setAttribute("rs", myform);
			}
		} else {
			mymodel = myutil.xljk_xlxhhy_detail(xlshhy_xnid);
			request.setAttribute("rs", mymodel);
		}

		request.setAttribute("xlxhhy_xnid", xlshhy_xnid);
		return mapping.findForward("member_modi");
	}

}