package xsgzgl.rwgl.mbxx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
/**
 * 人武管理-湖州师范-民兵信息管理
 * @author yeyipin
 * @since 2012.12.25 merry christmas
 */
public class RwglMbxxAction extends BasicExtendAction{
	
	
	/**
	 * 民兵信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mbxxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// write和titile
		setWriteAbleAndTitle(request, "rwgl_mbxxgl_mbxxgl.do");
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_rwgl_mbxxb");
		User user = getUser(request);
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("mbxxCx");
		}
	}
	
	
	/**
	 * 民兵信息增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mbxxZj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglMbxxService service = new RwglMbxxService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("rwgl_mbxxgl_mbxxgl.do");
		request.setAttribute("zzmmList", service.getZzmmList());
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("mbxxZj");
	}
	
	
	/**
	 * 民兵信息修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mbxxXg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglMbxxService service = new RwglMbxxService();
		RwglMbxxForm model = (RwglMbxxForm)form;
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		model.setPkValue(pkValue);
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("rwgl_mbxxgl_mbxxgl.do");
		HashMap<String,String> rs = service.getMbxx(model);
		request.setAttribute("zzmmList", service.getZzmmList());
		request.setAttribute("rs", rs);
		service.setRequestValue(rForm, user, request);
		if("view".equalsIgnoreCase(doType)){
			return mapping.findForward("mbxxCk");
		}
		return mapping.findForward("mbxxXg");
	}
	
}
