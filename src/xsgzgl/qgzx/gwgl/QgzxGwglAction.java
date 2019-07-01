package xsgzgl.qgzx.gwgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.jcdmwh.QgzxJcdmwhService;

import com.zfsoft.basic.BasicAction;
/**
 * 勤工助学-勤工岗位管理-岗位信息管理
 * @author yeyipin
 * @since 2012.7.17
 */
public class QgzxGwglAction extends BasicAction {
	
	/**
	 * 岗位申请查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setPath("qgzx_gwgl_gwsq.do");
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("rs", service.setZjmrCs(request));
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_gwgl_gwsq.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_qgzx_gwxxsqb");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_qgzx_gwxxsqb");
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("gwsqCx");
		}
	}
	
	
	/**
	 * 岗位申请增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxJcdmwhService jcdmservice = new QgzxJcdmwhService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_gwgl_gwsq.do");
		HashMap<String,String> rs = service.setZjmrCs(request);
		request.setAttribute("rs", rs);
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
//		request.setAttribute("gwxzList", jcdmservice.getGwxzdmList());
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gwsqZj");
	}
	
	
	/**
	 * 岗位申请修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxJcdmwhService jcdmservice = new QgzxJcdmwhService();
		QgzxGwglForm model = (QgzxGwglForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		rForm.setPath("qgzx_gwgl_gwsq.do");
		HashMap<String,String> rs = service.getGwsqMap(model);
		request.setAttribute("rs", StringUtils.formatData(rs));
		//酬金上限
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
		//request.setAttribute("gwxzList", jcdmservice.getGwxzdmList());
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gwsqXg");
	}
	
	/**
	 * 岗位审核查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwshCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_qgshzt(new String[]{"未审核"});
		searchModel.setPath("qgzx_gwgl_gwsh.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_gwgl_gwsh.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_qgzx_gwxxsqb");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_qgzx_gwxxsqb");
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("gwshCx");
		}
	}
	
	
	/**
	 * 岗位信息单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwshDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		rForm.setPath("qgzx_gwgl_gwsh.do");
		HashMap<String,String> rs = service.getGwsqMap(model);
		request.setAttribute("rs", StringUtils.formatDataView(rs));
		//酬金上限
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
		
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gwshDgsh");
	}
	
	
	/**
	 * 岗位信息批量审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwshPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		
		rForm.setPath("qgzx_gwgl_gwsh.do");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gwshPlsh");
	}
	
	/**
	 * 岗位信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setPath("qgzx_gwgl_gwxxgl.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_gwgl_gwxxgl.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_qgzx_gwxxb");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_qgzx_gwxxb");
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("gwxxCx");
		}
	}
	
	
	/**
	 * 岗位信息增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxJcdmwhService jcdmservice = new QgzxJcdmwhService();
		QgzxGwglForm model = (QgzxGwglForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_gwgl_gwxxgl.do");
		HashMap<String,String> rs = service.setZjmrCs(request);
		request.setAttribute("rs", rs);
		//酬金上限
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
		
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("yrbmList", service.getYrbm(model));
//		request.setAttribute("gwxzList", jcdmservice.getGwxzdmList());
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gwxxZj");
	}
	
	
	/**
	 * 岗位信息操作（修改，查看，添加人员，退岗人员）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxJcdmwhService jcdmservice = new QgzxJcdmwhService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		myForm.setPkValue(pkValue);
		myForm.setDoType(doType);
		HashMap<String,String> rs = service.getGwryxx(myForm,request);
		request.setAttribute("rs", rs);
		//酬金上限
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
		
		User user = getUser(request);
		rForm.setPath("qgzx_gwgl_gwxxgl.do");
		service.setRequestValue(rForm, user, request);
//		request.setAttribute("gwxzList", jcdmservice.getGwxzdmList());
		return mapping.findForward(doType);
	}
	
	
	/**
	 * 获得学生列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QgzxGwglService service = new QgzxGwglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_gwgl_getStu.do");
		// ----------------设置PATH end-----------------------
		String pkValue = request.getParameter("pkValue");
		service.setRequestValue(rForm, user, request);
		request.setAttribute("pkValue", pkValue);
		//勤工个性化参数
		request.setAttribute("xn", request.getParameter("xn"));
		//资格审查开关开启，只有勤工助学库中的学生可以添加
		if ("yes".equals(new QgzxCsszService().getCssz().get("sfxyzgsc"))){
			request.setAttribute("sfxyzgsc", "yes");
		}
		return mapping.findForward("getStu");
	}
	
	

}
