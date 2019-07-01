
package xgxt.xljk.xlxh.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * MyEclipse Struts Creation date: 06-12-2007
 *  
 * @struts.action path="Association_action" name="AssociationForm"
 *                parameter="Association_MeberSearch" scope="request"
 *                validate="true"
 * @struts.action-forward name="success"
 *                        path="/shgc/PsycologyHealth/Association/Association_Menber_index.jsp"
 */
public class Association_action extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			ActionForward myActFwd = null;
			String act = request.getParameter("act");
			System.out.println(act);
			if (act.equals("MemberSearch")) {
				request.setAttribute("act", act);
				return mapping.findForward("MemberSearch");
			} else if (act.equals("ActivityRecord")) {
				request.setAttribute("act", act);
				return null;
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			return new ActionForward("/login.jsp", false);
		}
	}

}