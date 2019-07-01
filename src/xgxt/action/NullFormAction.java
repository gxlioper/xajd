package xgxt.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * @author ChenHuamao E-MAIL:chhuma@sohu.com
 * @describe 这个类主要用于处理插入数据时的空表单的转发
 */
public class NullFormAction extends DispatchAction {

	/* （非 Javadoc）
	 * @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
		    HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = Base.chgNull(request.getParameter("method"), "", 1);
		Map map = new HashMap();
		request.setAttribute("map", map);
		return mapping.findForward(method);
	}
		
}
