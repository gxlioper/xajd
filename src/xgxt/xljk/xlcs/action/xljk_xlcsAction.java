
package xgxt.xljk.xlcs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.xljk.xlcs.form.xilk_xlcsTkwh_form;

/** 
 * MyEclipse Struts
 * Creation date: 07-17-2007
 * 
 * XDoclet definition:
 * @struts.action path="/xljk_xlcs" name="xljk_xlcs_form" scope="request" validate="true"
 */
public class xljk_xlcsAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		xilk_xlcsTkwh_form xilk_xlcsTkwh_form = (xilk_xlcsTkwh_form) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				xilk_xlcsTkwh_form.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			ActionForward myActFwd = null;
			String act = request.getParameter("act");
			if (act.equals("tkwh")) {
				myActFwd = mapping.findForward("tkwh");
			}else if (act.equals("zxpc")) {
				myActFwd = mapping.findForward("zxpc");
			} else if (act.equals("pcjgcx")) {
				myActFwd = mapping.findForward("pcjgcx");
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			xilk_xlcsTkwh_form.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}
}