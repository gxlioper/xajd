/*
 * �������� 2006-11-15
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;

public class TestPrintAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	DAO dao = DAO.getInstance();
	String sql = "";
	sql = "select rownum �к�,a.* from czrzb a where rownum<3";
	String[] tabTitle = dao
		.getColumnName("select rownum �к�,a.* from czrzb a");
	String[] tabTitleCN = dao.getColumnNameCN(tabTitle, "czrzb");
	List titles = dao.arrayToList(tabTitle, tabTitleCN);
	// Vector vector = new Vector();
	// vector.addAll(dao.rsToVator(sql,new
	// String[]{yhm,dateF,dateT,ipT,ipF},tabTitle));
	List vector;
	vector = dao.getList(sql, new String[] {}, tabTitle);
	request.setAttribute("topTr", titles);
	request.setAttribute("rs", vector);
	return mapping.findForward("success");
    }

}
