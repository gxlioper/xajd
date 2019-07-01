package xgxt.xljk.zxzx.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.xljk.zxzx.form.xljk_zxzx_Form;

/** 
 * MyEclipse Struts
 * Creation date: 06-14-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class xljk_zxzxAtion extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_zxzx_Form myform = new xljk_zxzx_Form();
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
			if (act.equals("xljk_zxsxx")) {
				return mapping.findForward("SearchForZXX");//跳转到处理查询咨询师的action
			} else if (act.equals("xljk_aljl")) {
				return mapping.findForward("CaseRecord");//跳转到处理案例记录的action
			} else if (act.equals("xljk_xssqyy")) { //学生申请预约
				return mapping.findForward("ApplyFor");//跳转到处理案例记录的action
			} else if (act.equals("xljk_zxszygl")) //咨询师资源管理
			{
				return mapping.findForward("Yyzygl");//跳转到处理案例记录的action
			} else if (act.equals("xljk_yycx")) {
				return mapping.findForward("Yyzycx");
			} 
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			myform.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}

	}
}