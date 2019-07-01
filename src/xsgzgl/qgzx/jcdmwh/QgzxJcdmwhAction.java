package xsgzgl.qgzx.jcdmwh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

/**
 * �ڹ���ѧ-��������-��������ά��
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJcdmwhAction extends BasicExtendAction{
	/**
	 * ��λ����ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxzWh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxJcdmwhService service = new QgzxJcdmwhService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_jcdmwh_gwxzwh.do");
		// ----------------����PATH end-----------------------
		CommService commService = new CommService();
		commService.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "xg_qgzx_gwxzdmb");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_qgzx_gwxzdmb");
//		request.setAttribute("path","qgzx_jcdmwh_gwxzwh.do");
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("gwxzWh");
		}
	}
	
	/**
	 * ���˵�λά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yrdwWh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute("path","qgzx_jcdmwh_yrdwwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yrdwWh");
	}
}
