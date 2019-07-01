package xgxt.xljk.xlxh.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.*;

import xgxt.DAO.DAO;
import xgxt.xljk.xlxh.Form.*;
import xgxt.xljk.xlxh.util.*;

/**
 * MyEclipse Struts Creation date: 06-12-2007
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class MemberSearch extends Action {
	boolean isStu = false;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		ActionForward myActFwd = null;
		if (act.equals("MemberSearch")) {
			if (doType != null && !doType.equalsIgnoreCase("")) {
				if (doType.equals("Search")) {
					myActFwd = SearchForMember2(mapping, form, request,
							response);
				}
			} else {
				myActFwd = SearchForMember2(mapping, form, request, response);
			}
		} 
		return myActFwd;

	}

	private ActionForward SearchForMember2(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AssociationForm myform = new AssociationForm();
		XLJK_XLXH_UTIL myutil = new XLJK_XLXH_UTIL();
		myutil.XLJK_XLXH_UTIL1(request);
		DAO dao = DAO.getInstance();
		String xydm = myform.getXydm();
		String zydm = myform.getZydm();
		myform.validate(mapping, request);
		Collection rs = null;
		rs = myutil.Find_By_SqlComment(myform);
		String rsNum = String.valueOf(rs.size());
		String tableName = "VIEW_XLXHHYXX";
		String realTable = "";
		String pk = "xn_id";
		myform.setTableName(tableName);
		myform.setRealTable(realTable);
		myform.setPk(pk);
		List titles = myutil.Get_xljk_xlxh_Title();
		request.setAttribute("topTr", titles);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("pk", pk);
		request.setAttribute("rs", rs);
		request.setAttribute("sexList", dao.getSexList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));
		return mapping.findForward("ReturnToJsp");
	}

}