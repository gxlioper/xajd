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
				myform.setErrMsg("��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}
			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			String act = request.getParameter("act");
			if (act.equals("xljk_zxsxx")) {
				return mapping.findForward("SearchForZXX");//��ת�������ѯ��ѯʦ��action
			} else if (act.equals("xljk_aljl")) {
				return mapping.findForward("CaseRecord");//��ת����������¼��action
			} else if (act.equals("xljk_xssqyy")) { //ѧ������ԤԼ
				return mapping.findForward("ApplyFor");//��ת����������¼��action
			} else if (act.equals("xljk_zxszygl")) //��ѯʦ��Դ����
			{
				return mapping.findForward("Yyzygl");//��ת����������¼��action
			} else if (act.equals("xljk_yycx")) {
				return mapping.findForward("Yyzycx");
			} 
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			myform.setErrMsg("���������жϣ������µ�½��");
			return new ActionForward("/login.jsp", false);
		}

	}
}