package xsgzgl.gygl.hzsf;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
/**
 * 公寓管理-湖州师范-楼栋考核管理
 * @author yeyipin
 * @since 2012.12.25 merry christmas
 */
public class LdkhglAction extends BasicExtendAction{

	/**
	 * 楼栋考核管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ldkhgl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// write和titile
		setWriteAbleAndTitle(request, "gygl_ldkhgl_ldkhgl.do");
		User user = getUser(request);
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setPath("gygl_ldkhgl_ldkhgl.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------页面首次加载参数设置为1----------------
		request.setAttribute("scjz", 1);
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("ldkhgl");
		}
	}
	
	
	/**
	 * 考核成绩维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward khcjwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LdkhglService service = new LdkhglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_nd(new String[]{pkValue.substring(0, 4)});
		searchModel.setSearch_tj_yf(new String[]{pkValue.substring(5, 7)});
		searchModel.setPath("gygl_ldkhgl.do?method=khcjwh");
		request.setAttribute("searchTj", searchModel);
		// ----------------页面首次加载参数设置为1----------------
		request.setAttribute("scjz", 1);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		request.setAttribute("path", "gygl_ldkhgl_ldkhgl.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setPath("gygl_ldkhgl.do?method=khcjwh");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("khcjwh");
	}
	
}
