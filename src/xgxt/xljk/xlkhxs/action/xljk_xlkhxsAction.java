
package xgxt.xljk.xlkhxs.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.gdby.WjcfGdbyService;
import xgxt.xljk.lrh_Util.model.stu_info_model;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.xljk.lrh_Util.util.stu_info_util;
import xgxt.xljk.xlkhxs.form.xljk_xlkhxs_form;
import xgxt.xljk.xlkhxs.util.*;

/** 
 * MyEclipse Struts
 * Creation date: 08-15-2007
 * 
 * XDoclet definition:
 * @struts.action path="/xljk_xlkhxs" name="xljk_xlkhxs_form" scope="request" validate="true"
 */
public class xljk_xlkhxsAction extends Action {
	/**
	 * 公用方法:在REQUEST中存放页面所要加载的LIST属性
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @param xq
	 * @throws Exception
	 */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		WjcfGdbyService service = new WjcfGdbyService();
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm;
		if (StringUtils.isNull(xy)) {
			xy = "";
		}
		if (StringUtils.isNull(zy)) {
			zy = "";
		}
		njLocal = nj==null ? "": nj;
		if (StringUtils.isNull(njLocal)) {
			njLocal = "";
		}
//		String zyKey = xy==null ? "":xy; 
//		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String userType = request.getSession().getAttribute("userType") == null ? ""
				: request.getSession().getAttribute("userType").toString();
		request.setAttribute("writeAble", "yes");//判断用户读写权
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", service.getZyList(xydm));//专业列表
		request.setAttribute("bjList", service.getBjList(xydm, zydm, nj));//班级列表
		//request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		//request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
		request.setAttribute("userType", userType);//用户类型
		if (Fdypd.isFdy(userName) && flg) {
			// 辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// 发送专业列表
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));// 发送班级列表
		}
	}
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		xljk_xlkhxs_form xljk_xlkhxs_form = (xljk_xlkhxs_form) form;// TODO Auto-generated method stub
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
			//	    String userName=session.getAttribute("userName").toString();
			//		String userDep=session.getAttribute("userDep").toString();
			//		String userNameReal=session.getAttribute("userNameReal").toString();
			//		String userOnLine=session.getAttribute("userOnLine").toString();
			if (act.equals("xljk_xlkhxs")) {
				if (doType != null && !doType.equalsIgnoreCase("")) {
					if (doType.equals("xlkhxs_add")) {
						myActFwd = xlkhxs_add(mapping, form, request, response);
					} else if (doType.equals("stu_info")) {
						myActFwd = stu_info(mapping, form, request, response);
					} else if (doType.equals("xlkhxs_search")) {
						myActFwd = xlkhxs_search(mapping, form, request,response);
					} else if (doType.equals("xlkhxs_view")) {
						myActFwd = xlkhxs_view(mapping, form, request, response);
					} else if (doType.equals("xlkhxs_modi")) {
						myActFwd = xlkhxs_modi(mapping, form, request, response);
					} else if (doType.equals("xlkhxs_del")) {
						myActFwd = xlkhxs_del(mapping, form, request, response);
					}
					//xlkhxs_pcjg_view
					else if (doType.equals("xlkhxs_pcjg_view")) {   //查看心理困惑学生信息普测情况
						myActFwd = xlkhxs_pcjg_view(mapping, form, request,response);
					} else if (doType.equals("xlkhxs_ftjl_view")) {
						myActFwd = xlkhxs_ftjl_view(mapping, form, request,response);
					}
				} else {
					myActFwd = index_to_jsp(mapping, form, request, response);
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
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		myform.deal_gbk();
		boolean writeFlag = commen_util.checkUsrPower(userName);
		String xydm = myform.getXydm();
		String zydm = myform.getZydm();
		List zdgcdxList = commen_util.get_dmwhb_dmList("sfxx");
		request.setAttribute("zdgcdxList", zdgcdxList);
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		if (writeFlag == false) {
			request.setAttribute("writeAble", "no");
		} else {
			request.setAttribute("writeAble", "yes");
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myform.setXydm(userDep);
		}
		request.setAttribute("path", "xljk_xlkhxs.do?act=xljk_xlkhxs");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward xlkhxs_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//			HttpSession session = request.getSession();
		//			String userName=session.getAttribute("userName").toString();
		//			String userDep=session.getAttribute("userDep").toString();
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		xljk_xlkhxs_util myutil = new xljk_xlkhxs_util();
		myutil.xljk_xlkhxs_util1(request);
		lrh_commen_util commen_util = new lrh_commen_util();
		List zdgcdxList = commen_util.get_dmwhb_dmList("sfxx");

		myform.deal_gbk();
		String add_flag = request.getParameter("add_flag");
		if (add_flag != null && !add_flag.equalsIgnoreCase("")) {
			if (add_flag.equals("yes")) {
				String message = myutil.xlkhxs_add(myform);
				request.setAttribute("message", message);
			}
		}
		request.setAttribute("zdgcdxList", zdgcdxList);
		return mapping.findForward("xlkhxs_add");
	}

	private ActionForward stu_info(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		myform.deal_gbk();
		String url = "/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=stu_info";
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
			List zdgcdxList = commen_util.get_dmwhb_dmList("sfxx");
			request.setAttribute("zdgcdxList", zdgcdxList);
			return mapping.findForward("xlkhxs_add");
		} else {
			request.setAttribute("url", url);
			return mapping.findForward("stu_info");
		}
	}

	private ActionForward xlkhxs_search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		lrh_commen_util commen_util = new lrh_commen_util();
		xljk_xlkhxs_util myutil = new xljk_xlkhxs_util();
		myform.deal_gbk();
		boolean writeFlag = commen_util.checkUsrPower(userName);
		String xydm = myform.getXydm();
		String zydm = myform.getZydm();
		List zdgcdxList = commen_util.get_dmwhb_dmList("sfxx");
		List rs = myutil.xlkhxs_search(myform);
		String[] zdm = { "XH", "XM", "XB", "XYMC", "BJMC", "DMMC" };
		List topTr = commen_util.Get_Table_Title("VIEW_XLJK_XLKHXSXXB", zdm);
		String rsNum = String.valueOf(rs.size());
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("zdgcdxList", zdgcdxList);
//		request.setAttribute("xyList", commen_util.get_xyList());
//		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
//		request.setAttribute("xyList", Base.getXyList());
		appendProperties(request, xydm, zydm, myform.getNj());

		if (writeFlag == false) {
			request.setAttribute("writeAble", "no");
		} else {
			request.setAttribute("writeAble", "yes");
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myform.setXydm(userDep);
		}
		
		request.setAttribute("path", "xljk_xlkhxs.do?act=xljk_xlkhxs");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward xlkhxs_view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//				HttpSession session = request.getSession();
		//				String userName=session.getAttribute("userName").toString();
		//				String userDep=session.getAttribute("userDep").toString();
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		xljk_xlkhxs_util myutil = new xljk_xlkhxs_util();
		myform.deal_gbk();
		String xn_id = request.getParameter("xn_id");
		myform.setXljk_xlkhxs_xnid(xn_id);
		myform = myutil.xlkhxs_view(myform);
		return mapping.findForward("xlkhxs_view");
	}

	private ActionForward xlkhxs_modi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//				HttpSession session = request.getSession();
		//				String userName=session.getAttribute("userName").toString();
		//				String userDep=session.getAttribute("userDep").toString();
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		xljk_xlkhxs_util myutil = new xljk_xlkhxs_util();
		myutil.xljk_xlkhxs_util1(request);
		lrh_commen_util commen_util = new lrh_commen_util();
		myform.deal_gbk();
		List zdgcdxList = commen_util.get_dmwhb_dmList("sfxx");
		String modi_flag = request.getParameter("modi_flag");
		String xn_id = request.getParameter("xn_id");
		if (modi_flag != null && !modi_flag.equalsIgnoreCase("")) {
			if (modi_flag.equals("yes")) {
				xn_id = request.getParameter("xn_id");
				myform.setXljk_xlkhxs_xnid(xn_id);
				String message = myutil.xlkhxs_modi(myform);
				request.setAttribute("message", message);
			}
		} else {
			myform.setXljk_xlkhxs_xnid(xn_id);
			myform = myutil.xlkhxs_view(myform);
		}
		request.setAttribute("xn_id", xn_id);
		request.setAttribute("zdgcdxList", zdgcdxList);
		return mapping.findForward("xlkhxs_modi");
	}

	private ActionForward xlkhxs_del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
//		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		xljk_xlkhxs_util myutil = new xljk_xlkhxs_util();
		myutil.xljk_xlkhxs_util1(request);
		lrh_commen_util commen_util = new lrh_commen_util();

		myform.deal_gbk();
		List zdgcdxList = commen_util.get_dmwhb_dmList("sfxx");
		String xn_id = request.getParameter("xn_id");
		myform.setXljk_xlkhxs_xnid(xn_id);
		String message = myutil.xlkhxs_del(myform);
		request.setAttribute("message", message);
		request.setAttribute("zdgcdxList", zdgcdxList);
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("bjList", commen_util.get_bjList(myform.getXydm(),
				myform.getZydm()));
		if ("xy".equalsIgnoreCase(userType)) {
			myform.setXydm(userDep);
		}
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward xlkhxs_pcjg_view(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//				HttpSession session = request.getSession();
		//				String userName=session.getAttribute("userName").toString();
		//				String userDep=session.getAttribute("userDep").toString();
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		xljk_xlkhxs_util myutil = new xljk_xlkhxs_util();
		//				lrh_commen_util commen_util= new lrh_commen_util();
		myform.deal_gbk();
		String xn_id = request.getParameter("xn_id");
		myform.setXljk_xlkhxs_xnid(xn_id);
		myform = myutil.xlkhxs_view(myform);
		request.setAttribute("xljk_xlkhxs_form", myform);
		return mapping.findForward("xlkhxs_pcjg_view");
	}

	private ActionForward xlkhxs_ftjl_view(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//				HttpSession session = request.getSession();
		//				String userName=session.getAttribute("userName").toString();
		//				String userDep=session.getAttribute("userDep").toString();
		xljk_xlkhxs_form myform = (xljk_xlkhxs_form) form;
		xljk_xlkhxs_util myutil = new xljk_xlkhxs_util();
		//				lrh_commen_util commen_util= new lrh_commen_util();
		myform.deal_gbk();
		String xn_id = request.getParameter("xn_id");
		myform.setXljk_xlkhxs_xnid(xn_id);
		myform = myutil.xlkhxs_view(myform);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("xljk_xlkhxs_form", myform);
		
		request.setAttribute("path", "xljk_xlkhxs.do?act=xljk_xlkhxs");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xlkhxs_ftjl_view");
	}

}