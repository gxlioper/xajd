package xsgzgl.jxgl.general.jxcjgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;


/**
 * 军训管理-军训考核管理-军训成绩管理
 * @author yeyipin
 * @since 2012.10.13
 */
public class JxglJxcjglAction extends BasicAction{

	/**
	 * 军训成绩管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxcjCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxcjglService jxglJxcjglService = new JxglJxcjglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		String jxid = request.getParameter("jxid");
		HashMap<String,String> rs = jxglJxcjglService.getJxxx(jxid);
		//验证军训是否存在
		if(rs == null || rs.isEmpty()){
			String msg = "请建立并运行军训！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("jxxxList", jxglJxcjglService.getJxxxList());
		rForm.setPath("jxgl_jxkhgl_jxcjgl.do");
		// ----------------设置PATH end-----------------------
		jxglJxcjglService.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("jxcjCx");
		}
		
	}
}
