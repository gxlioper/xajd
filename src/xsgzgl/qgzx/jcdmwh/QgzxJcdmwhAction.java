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
 * 勤工助学-基础设置-基础代码维护
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJcdmwhAction extends BasicExtendAction{
	/**
	 * 岗位性质维护
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
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_jcdmwh_gwxzwh.do");
		// ----------------设置PATH end-----------------------
		CommService commService = new CommService();
		commService.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "xg_qgzx_gwxzdmb");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_qgzx_gwxzdmb");
//		request.setAttribute("path","qgzx_jcdmwh_gwxzwh.do");
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("gwxzWh");
		}
	}
	
	/**
	 * 用人单位维护
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
