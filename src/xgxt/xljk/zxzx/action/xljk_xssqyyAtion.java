package xgxt.xljk.zxzx.action;

import java.util.List;

import common.Globals;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.FormModleCommon;
import xgxt.xljk.zxzx.form.*;
import xgxt.xljk.zxzx.util.*;

/** 
 * Creation date: 06-15-2007
 */
public class xljk_xssqyyAtion extends Action {
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

			String act = request.getParameter("act");
			String doType = request.getParameter("doType");
			ActionForward myActFwd = null;
			if (act.equals("xljk_xssqyy")) {
				if (doType != null && !doType.equalsIgnoreCase("")) {
					if (doType.equals("Search")) {
						myActFwd = Index_To_Jsp(mapping, form, request,response);
					}
					if (doType.equals("Apply_For")) {
						myActFwd = ApplyFor(mapping, form, request, response);
					}
					if (doType.equals("student_check")) {
						myActFwd = Student_Check(mapping, form, request,
								response);
					}
				} else {
					myActFwd = Index_To_Jsp(mapping, form, request, response);
				}
			}

			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			myform.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward Index_To_Jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		xljk_zxsxx_Form myform = new xljk_zxsxx_Form();
		myform.validate(mapping, request);
		myform.setTableName("xljk_zxsxxb");
		myform.setRealTable("");
		myform.setPk(myform.getZxxbh());
		List li = myutil.Find_By_SqlComment(myform);
		String rsNum = "";
		rsNum = String.valueOf(li.size());
		List titles = myutil.Get_xljk_zxsxxb_Title();
		request.setAttribute("topTr", titles);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("tableName", "xljk_zxsxxb");
		if(StandardOperation.getXxdm().equals(Globals.XXDM_ZGMSXY)){
			request.setAttribute("tableName", "xljk_zxsxxb2");
		}
		request.setAttribute("pk", myform.getZxxbh());
		request.setAttribute("realTable", "");
		request.setAttribute("sexList", myutil.get_sexList());
		request.setAttribute("rs", li);
		
		request.setAttribute("path", "XLJK_ZXZX.do?act=xljk_xssqyy");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ReturnToJsp_xssqyy"); 
	}

	private ActionForward ApplyFor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return null;
	}

	private ActionForward Student_Check(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xssqyy_Form myform = new xljk_xssqyy_Form();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		//myutil.XLJK_ZXZX_Util(request);
		myform.setXh(request.getParameter("xh"));
		myform.setXssrmm(request.getParameter("xsmm"));

		myutil.xssqyy_check_student(myform);

		return null;
	}

}