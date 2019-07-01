package xgxt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zfsoft.basic.BasicAction;

/**
 * @author ChenHuamao E-MAIL:chhuma@sohu.com
 * @describe ������ǻ������࣬����Action�������淶�̳�����ࡣ
 */
public class BaseAction extends BasicAction{

	/* ���� Javadoc��
	 * @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return super.execute(mapping, form, request, response);
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe Ȩ�޿���
	 * @param mapping
	 * @param request
	 * @param url
	 * @return
	 */
	protected final ActionForward power(ActionMapping mapping, HttpServletRequest request, String url) {
		int p = Base.chkUPower(request.getSession().getAttribute("userName").toString(), url, request.getSession().getAttribute("userType").toString() .equalsIgnoreCase("stu"));
		if (p == -1) {
			//mapping. findForward(��noPower��);	//�������û�
			return new ActionForward("/errMsg.do", false);	//�����ò���
		} else {
			return null;
		}
	}


}
