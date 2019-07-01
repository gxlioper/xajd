package xsgzgl.qgzx.glygl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;
/**
 * �ڹ���ѧ-������-�����Ϣ����
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxGlyglAction extends BasicAction {
	/**
	 * ����Աά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyWh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGlyglService service = new QgzxGlyglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_glygl_glywh.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "XG_QGZX_QGGLYB");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "XG_QGZX_QGGLYB");
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("glyWh");
		}
	}
	
	
	/**
	 * ����Ա����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyZj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGlyglService service = new QgzxGlyglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_glygl_glywh.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("glyZj");
	}
	
}
