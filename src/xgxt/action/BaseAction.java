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
 * @describe 这个类是基本的类，所有Action必须依规范继承这个类。
 */
public class BaseAction extends BasicAction{

	/* （非 Javadoc）
	 * @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return super.execute(mapping, form, request, response);
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 权限控制
	 * @param mapping
	 * @param request
	 * @param url
	 * @return
	 */
	protected final ActionForward power(ActionMapping mapping, HttpServletRequest request, String url) {
		int p = Base.chkUPower(request.getSession().getAttribute("userName").toString(), url, request.getSession().getAttribute("userType").toString() .equalsIgnoreCase("stu"));
		if (p == -1) {
			//mapping. findForward(“noPower”);	//尽量配置化
			return new ActionForward("/errMsg.do", false);	//这句最好不用
		} else {
			return null;
		}
	}


}
