
package xgxt.xljk.zxzx.action;

import java.util.List;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.xljk.zxzx.form.xljk_zxszy_Form;
import xgxt.xljk.zxzx.util.XLJK_ZXZX_Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.xljk.lrh_Util.util.*;

/** 
 * MyEclipse Struts
 * Creation date: 06-28-2007
 * 
 * XDoclet definition:
 * @struts.action path="/xljk_zxszy_add" name="xljk_zxszy_Form" scope="request" validate="true"
 * @struts.action-forward name="xljk_zxszy_add" path="/shgc/Consultation/Zxszy_Add.jsp"
 */
public class xljk_zxszy_addAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_zxszy_Form xljk_zxszy_Form = (xljk_zxszy_Form) form;// TODO Auto-generated method stub
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				xljk_zxszy_Form.setErrMsg("登陆超时，请重新登陆！");
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
			if (act.equals("xljk_zxszygl")) {
				if (doType != null && !doType.equalsIgnoreCase("")) {
					if (doType.equals("Add_pl")) {   //批量生成预约资源
						myActFwd = Add_Zxszy(mapping, form, request, response);
					} else if (doType.equals("Add_xx")) {  //生成一条资源
						myActFwd = Add_Zxszy(mapping, form, request, response);
					} else if (doType.equals("Add_mt")) { //生成某天资源
						myActFwd = Add_Zxszy(mapping, form, request, response);
					} else if (doType.equals("Pipei_Zxs")) {   //进行咨询师匹配
						myActFwd = Pipei_Zxszy(mapping, form, request, response);
					}
				} else {
					myActFwd = Index_To_Jsp(mapping, form, request, response);
				}
			} else {
				myActFwd = Index_To_Jsp(mapping, form, request, response);
			}

			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			xljk_zxszy_Form.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward Index_To_Jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		lrh_commen_util com_uti = new lrh_commen_util();
		xljk_zxszy_Form myform = new xljk_zxszy_Form();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		List sjdList = com_uti.get_dmwhb_dmList("zxszy_sjd");
		List ddList = com_uti.get_dmwhb_dmList("zxszy_dd");
		List zxxList = myutil.get_zxxList();
		myform.validate(mapping, request);
		myform.setPk("XN_ID");
		myform.setTableName("XLJK_ZXSZYB");
		myform.setRealTable("");
		request.setAttribute("zxxList", zxxList);  //咨询师列表
		request.setAttribute("ddList", ddList); //地段类表
		request.setAttribute("sjdList", sjdList);//时间段列表
		return mapping.findForward("Add_Zxszy");
	}

	private ActionForward Add_Zxszy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_zxszy_Form xljk_zxszy_Form = (xljk_zxszy_Form) form;
		lrh_commen_util com_uti = new lrh_commen_util();
		String doType = request.getParameter("doType");
		xljk_zxszy_Form myform = new xljk_zxszy_Form();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		List zxxList = myutil.get_zxxList();
		List sjdList = com_uti.get_dmwhb_dmList("zxszy_sjd");
		List ddList = com_uti.get_dmwhb_dmList("zxszy_dd");
		myform.validate(mapping, request);
		myform.setPk("XN_ID");
		myform.setTableName("XLJK_ZXSZYB");
		myform.setRealTable("");
		myform.setMonthday(xljk_zxszy_Form.getMonthday());
		if (doType != null && !doType.equalsIgnoreCase("")) {
			if (doType.equals("Add_pl")) {   //批量生成预约资源 
				request.setAttribute("message", myutil.Add_zxszy_pl(myform,doType));
			} else if (doType.equals("Add_mt")) {  //批量生成当天资源 
				request.setAttribute("message", myutil.Add_zxszy_mt(myform,doType));
			} else if (doType.equals("Add_xx")) {  // 生成预约资源 
				request.setAttribute("message", myutil.Add_zxszy_xx(myform));
			}
		}
		request.setAttribute("zxxList", zxxList);
		request.setAttribute("ddList", ddList);
		request.setAttribute("sjdList", sjdList);
		return mapping.findForward("Add_Zxszy");
	}

	private ActionForward Pipei_Zxszy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_zxszy_Form myform = new xljk_zxszy_Form();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		String xn_id = request.getParameter("xn_id");
		String zxxbh = request.getParameter("zxxbh");
		// ---------2010/5/24 edit by luojw -----------
		boolean flag = false;
		String xxdm = StandardOperation.getXxdm();
		// 批量删除
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// 广州大学
			flag = myutil.savePpzxs(xn_id, zxxbh);
		} else {
			flag = myutil.Pipei_Zxs(xn_id, zxxbh);
		}
		// --------------end----------------------
		String act = "";
		if (flag == true) {
			request.setAttribute("message", "pipei_seccess");
			act = "pipei_succeess";
		} else {
			request.setAttribute("message", "pipei_fail");
			act = "pipei_fail";
		}
		///xgxt/xljk_zxsxx_view.do?doType=View_Zxszy
		myform.setAct(act);

		request.setAttribute("xn_id", xn_id);
		request.setAttribute("zxxbh", zxxbh);

		return mapping.findForward("pipei_seccess");
	}

}