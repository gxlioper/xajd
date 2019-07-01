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
 * 军训管理-基础设置-代码维护
 * @author yeyipin
 * @since 2012.7.16
 */
public class JxglDmwhAction extends BasicExtendAction{
	/**
	 * 个人荣誉代码维护
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
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("jxgl_jcsz_dmwh.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "xg_jxgl_hzsf_grrydmb");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_jxgl_hzsf_grrydmb");
		return mapping.findForward("grryWh");
	}
	/**
	 * 团队荣誉代码维护
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
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("jxgl_jcsz_dmwh.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "xg_jxgl_hzsf_tdrydmb");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_jxgl_hzsf_tdrydmb");
		return mapping.findForward("tdryWh");
	}
}
