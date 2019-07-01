package xsgzgl.jxgl.hzsf.dmwh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
/**
 * ��ѵ����-��������-����ά��
 * @author yeyipin
 * @since 2012.7.16
 */
public class JxglDmwhAction extends BasicExtendAction{
	/**
	 * ������������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grryWh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("jxgl_jcsz_dmwh.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "xg_jxgl_hzsf_grrydmb");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_jxgl_hzsf_grrydmb");
		return mapping.findForward("grryWh");
	}
	/**
	 * �Ŷ���������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tdryWh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("jxgl_jcsz_dmwh.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "xg_jxgl_hzsf_tdrydmb");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_jxgl_hzsf_tdrydmb");
		return mapping.findForward("tdryWh");
	}
}
