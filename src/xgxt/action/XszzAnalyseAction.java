/*
 * �������� 2007-1-12 bat_zzj
 *
 */
package xgxt.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.form.XszzForm;


/**
 * @author ChenHuamao <p>E-MAIL: chhuma@hotmail.com</p>
 * @describe ѧ�������еĲ�ѯͳ�ƹ���ʵ�� 
 */
public class XszzAnalyseAction extends BaseAction {

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe ������Ϣְҵ����ѧԺ��У��ѧ�����걨���ܱ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward jsxxzyjsxyGjzxdkhz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String url = "xszzAnalyse.do?method=jsxxzyjsxyGjzxdkhz";
		if (this.power(mapping, request, url) != null) {// Ȩ�޿��ƴ���
			return this.power(mapping, request, url);
		} else {
			// �����Ĺ���ʵ��������.
			XszzForm temp = (XszzForm) form;
			String sql = "select * from view_jsxx_gjzxdk1 where nd=?";
			List list = dao.getListNotOut(sql, new String[]{temp.getNd()});
			request.setAttribute("list", list);
			return mapping.findForward("jsxxzyjsxyGjzxdkhz");
		}

	}

}
