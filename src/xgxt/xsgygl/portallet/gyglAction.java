
package xgxt.xsgygl.portallet;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.Base;
public class gyglAction extends DispatchAction {
	
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return super.execute(mapping, form, request, response);
	}
	public ActionForward xsGyXxQuerry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		DAO dao = DAO.getInstance();
		gyglForm myForm = (gyglForm)form;
		String xh = myForm.getXh();
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		String querry = Base.isNull(xh)?" 1=2 ":" xh='"+xh+"'";
		String sql = "select a.xh Ö÷¼ü, a.* from view_xszsxx a where "+querry;
		String[] colList = new String[]{"Ö÷¼ü","xh","xm","xymc","zymc","bjmc","ldmc","ssbh"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_xszsxx");
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			vector.addAll(dao.rsToVator(sql, new String[]{}, colList));
			if (vector == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(vector.size());
			}
		}	
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", vector);
		request.setAttribute("rsNum", rsNum);
		return mapping.findForward("xsGyXxQuerry");
	}
	public ActionForward xsGyXxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		DAO dao = DAO.getInstance();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, Object> map = new HashMap<String, Object>();
		String[] colList = new String[]{"xh","ssbh","cwh","rzrq","jzrq","qsh","qsdh","ldmc","xqmc","zsf","dsjssf","xm","xb","nj","xymc","zymc","bjmc"};
		String sql = "select * from view_xszsxx where xh=?";
		String[] ryValue = dao.getOneRs(sql,new String[]{pkValue}, colList);
		if(ryValue!=null){
			for(int i=0;i<colList.length;i++){
				map.put(colList[i].toString(),Base.isNull(ryValue[i])?"":ryValue[i]);
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("xsGyXxView");
	}
}
