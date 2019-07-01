package xsgzgl.xszz.jhzy.cssz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description:学生资助-参数设置
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */
public class XszzCsszAction extends BasicAction {

	/**
	 * 参数设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XszzCsszActionForm csszForm = (XszzCsszActionForm) form;
		XszzCsszService service = new XszzCsszService();
		HashMap<String, String> rs = new HashMap<String, String>();
		
		
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			rs = service.getCsszxx();
			String dqxn=rs.get("xn");
			String xn=csszForm.getXn();
			service.copyJtqk(dqxn,xn);
			boolean result = service.bcCsszxx(csszForm);
			if (result) {XszzCsszActionForm.getCsszForm().setXn(csszForm.getXn());};
			request.setAttribute("result", result);
		}
		rs = service.getCsszxx();
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("rs", rs);
		return mapping.findForward("cssz");
	}
	
}
