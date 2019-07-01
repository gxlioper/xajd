package xsgzgl.rwgl.rwtw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;

public class RwglRwtwAction extends BasicExtendAction{

	/**
	 * 入伍登记查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwdjCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// write和titile
		setWriteAbleAndTitle(request, "rwgl_rwtwgl_rwdjgl.do");
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_rwgl_rwdjb");
		User user = getUser(request);
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("rwdjCx");
		}
	}
	
	
	/**
	 * 入伍登记增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwdjZj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service = new RwglRwtwService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		RwglRwtwForm model = (RwglRwtwForm) form;
		//学年list
		request.setAttribute("xnList", Base.getXnndList());
		//入伍方式类别
		List<HashMap<String, String>> rwfsList = service.rwfsList();
		request.setAttribute("rwfsList", rwfsList);
		//入伍途径
		List<HashMap<String, String>> rwtjList = service.rwtjList();
		request.setAttribute("rwtjList", rwtjList);
		
		//默认将学年设置为系统当前学年
		if(StringUtil.isNull(model.getRwxn())){
        	model.setRwxn(Base.currXn);
        }
		String xh=request.getParameter("xh");
		rForm.setPath("rwgl_rwtwgl_rwdjgl.do");
		if(!StringUtil.isNull(xh)){
			HashMap<String,String> rs = service.xsxxCk(model);
			request.setAttribute("rs", rs);
		}
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("rwdjZj");
	}
	
	
	/**
	 * 在校学生查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxxsCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service = new RwglRwtwService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("rwgl_rwtwgl.do?method=zxxsCx");
		// write和titile
		setWriteAbleAndTitle(request, "rwgl_rwtwgl_rwdjgl.do");
		service.setRequestValue(rForm, user, request);
		request.setAttribute("gotoPath", request.getParameter("goto"));
		return mapping.findForward("zxxsCx");
	}
	
	
	/**
	 * 入伍登记修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwdjXg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
		//学年list
		request.setAttribute("xnList", Base.getXnndList());
		//入伍方式类别
		List<HashMap<String, String>> rwfsList = service.rwfsList();
		request.setAttribute("rwfsList", rwfsList);
		//入伍途径
		List<HashMap<String, String>> rwtjList = service.rwtjList();
		request.setAttribute("rwtjList", rwtjList);
		//默认将学年设置为系统当前学年
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		model.setPkValue(pkValue);
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("rwgl_rwtwgl_rwdjgl.do");
		HashMap<String,String> rs = service.getRwxx(model);
		request.setAttribute("rs", rs);
		service.setRequestValue(rForm, user, request);
		if(StringUtil.isNull(model.getRwxn())){
        	model.setRwxn(Base.currXn);
        }
		if("view".equalsIgnoreCase(doType)){
			return mapping.findForward("rwdjCk");
		}
		return mapping.findForward("rwdjXg");
	}
	
	
	/**
	 * 退伍登记查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward twdjCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// write和titile
		setWriteAbleAndTitle(request, "rwgl_rwtwgl_twdjgl.do");
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_rwgl_twdjb");
		User user = getUser(request);
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("twdjCx");
		}
	}
	
	
	/**
	 * 退伍登记增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward twdjZj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service = new RwglRwtwService();
		RequestForm rForm = new RequestForm();
		RwglRwtwForm model = (RwglRwtwForm) form;
		User user = getUser(request);
		rForm.setPath("rwgl_rwtwgl_twdjgl.do");
		String xh=request.getParameter("xh");
		if(!StringUtil.isNull(xh)){
			model.setPkValue(xh);
			HashMap<String,String> rs = service.getRwxx(model);
			request.setAttribute("rs", rs);
		}

		service.setRequestValue(rForm, user, request);
		return mapping.findForward("twdjZj");
	}
	
	
	/**
	 * 入伍学生查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwxsCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service = new RwglRwtwService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("rwgl_rwtwgl.do?method=rwxsCx");
		setWriteAbleAndTitle(request, "rwgl_rwtwgl_twdjgl.do");
		request.setAttribute("gotoPath", request.getParameter("goto"));
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("rwxsCx");
	}
	
	
	/**
	 * 退伍登记修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward twdjXg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		model.setPkValue(pkValue);
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("rwgl_rwtwgl_twdjgl.do");
		HashMap<String,String> rs = service.getRwxx(model);
		request.setAttribute("rs", rs);
		service.setRequestValue(rForm, user, request);
		if("view".equalsIgnoreCase(doType)){
			return mapping.findForward("twdjCk");
		}
		return mapping.findForward("twdjXg");
	}
	
}
