package xgxt.xtwh.portallet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import xgxt.form.CommanForm;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ϵͳά���ṩ��Portallet������Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-07-078</p>
 */
public class XtwhForPortalletAction extends DispatchAction {
	
	/**
	 * ���Ų�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 **/
	public ActionForward queryNews(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response){	
		CommanForm model = (CommanForm)form;
		XtwhForPortalletService service = new XtwhForPortalletService();
		List list = null;
		
		list = service.queryNews(model);
		request.setAttribute("rs", list);
		request.setAttribute("rsNum", list.size());
		request.setAttribute("topTr", service.getQueryNewTitle());
		return mapping.findForward("success");
	}
}
