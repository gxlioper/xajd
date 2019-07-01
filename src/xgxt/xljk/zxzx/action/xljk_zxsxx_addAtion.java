package xgxt.xljk.zxzx.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import java.util.*;

import xgxt.action.Base;
import xgxt.xljk.zxzx.form.xljk_zxsxx_Form;
import xgxt.xljk.zxzx.util.XLJK_ZXZX_Util;

/** 
 * MyEclipse Struts
 * Creation date: 06-18-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class xljk_zxsxx_addAtion extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
			String doType = request.getParameter("doType");
			if (act.equals("xljk_zxsxx")) {
				if (doType != null && !doType.equalsIgnoreCase("")) {
					if (doType.equals("Insert")) {
						myActFwd = Add_zxsxx(mapping, form, request, response);
					}
				} else {
					myActFwd = Add_zxsxx_jsp(mapping, form, request, response);
				}
			}

			return myActFwd;

		} catch (Exception e) {
			e.printStackTrace();
			myform.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward Add_zxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_zxsxx_Form myform = new xljk_zxsxx_Form();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		//myutil.XLJK_ZXZX_Util(request);
		myform.validate(mapping, request);
		boolean flag = myutil.insert_into_zxsxxb(myform);
		if (flag == true) {
			myform.setMessage("ok");
		} else if (flag == false) {
			myform.setMessage("no");
		}
		List sexList = myutil.get_sexList();
		request.setAttribute("sexList", sexList);
		request.setAttribute("message", myform.getMessage());
		return mapping.findForward("zxsxx_add");
	}

	private ActionForward Add_zxsxx_jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_zxsxx_Form myform = new xljk_zxsxx_Form();
		myform.validate(mapping, request);
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		List sexList = myutil.get_sexList();
		List<HashMap<String,String>>zxszcList=myutil.getZxszcList();
		String xxdm=Base.xxdm;
		//闽江学院
		if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)){
			//显示咨询师专长
			request.setAttribute("showZxszc", "showZxszc");
			//咨询师专长列表
			request.setAttribute("zxszcList", zxszcList);
		}
		
		request.setAttribute("sexList", sexList);
		return mapping.findForward("zxsxx_add");
	}
}