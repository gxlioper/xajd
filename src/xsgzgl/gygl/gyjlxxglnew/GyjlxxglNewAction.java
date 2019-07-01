package xsgzgl.gygl.gyjlxxglnew;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.gyjlxxgl.GyjlxxglForm;
import xsgzgl.gygl.gyjlxxgl.GyjlxxglService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

public class GyjlxxglNewAction extends BasicAction{
	
	/**
	 * 公寓纪律信息维护
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxwh(ActionMapping mapping, ActionForm from,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		searchModel.setPath("gygl_gyjlglnew_gyjlxxwh.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gygl_gyjlglnew_gyjlxxwh.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "xg_view_gygl_new_gyjlb");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_gygl_new_gyjlb");
		String userType=user.getUserType();
		request.setAttribute("userType", userType);
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{		
			return mapping.findForward("gyjlxxwh");
		}
	}
	
	
	/**
	 * 获得学生列表
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxscx(ActionMapping mapping, ActionForm from,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		// ----------------设置PATH begin-----------------------
		request.setAttribute("title","添加学生");
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gyjl_gyjlglnew_gyjlxscx.do");
		// ----------------设置PATH end-----------------------
		String pkValue = request.getParameter("pkValue");
		service.setRequestValue(rForm, user, request);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("gyjlxscx");	
	}
	
	/**
	 * 学生纪律信息查询
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjlxxcx(ActionMapping mapping, ActionForm from,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gygl_gyjlglnew_xsjlxxcx.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "");
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			return mapping.findForward("xsjlxxcx");
		}else{		
			String msg = "本模块只能由<font color='blue'>学生用户</font>进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}	
	}
	
	
	/**
	 * 增加公寓纪律信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gygl_gyjlglnew_gyjlxxwh.do");
		// ----------------设置PATH end-----------------------
		//----------------增加默认页面参数-----------------------
		HashMap<String,String> rs = service.setZjmrCs(request);
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("jldlList", service.getJldlList(request));
		request.setAttribute("jllbList", new ArrayList<HashMap<String,String>>());
		//重庆邮电大学
		if("13627".equals(Base.xxdm)){
			String currDay = DateUtils.getYear()+"-"+DateUtils.getMonth()+"-"+DateUtils.getDayOfMonth();
			//前一天时间
			String lastDay = DateUtils.getLastDayOrNextDay(currDay, -3);
			request.setAttribute("currDay", currDay);
			request.setAttribute("lastDay", lastDay);
		}
		
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gyjlZj");
	}
	
	/**
	 * 删除公寓纪律信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息维-删除PK:{str}")
	public ActionForward gyjlSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		String message = "";
		boolean flag = false;
		GyjlxxglForm myForm = new GyjlxxglForm();
		User user = getUser(request);
		String username = user.getUserName();
		String[] valArr = service.unicode2Gbk(request.getParameter("str")).split("!!@@");
		flag = service.gyjlSc(myForm, valArr, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 撤销公寓纪律处理
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息处理-撤销PK:{str}")
	public ActionForward gyjlCancelCl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		String message = "";
		boolean flag = false;
		GyjlxxglForm myForm = new GyjlxxglForm();
		User user = getUser(request);
		String username = user.getUserName();
		String[] valArr = service.unicode2Gbk(request.getParameter("str")).split("!!@@");
		flag = service.gyjlCancelCl(myForm, valArr, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_CANCEL_NEW_SUCCESS : MessageInfo.MESSAGE_CANCEL_NEW_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 撤销公寓纪律审核
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息审核-撤销PK:{str}")
	public ActionForward gyjlCancelSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		String message = "";
		boolean flag = false;
		GyjlxxglForm myForm = new GyjlxxglForm();
		User user = getUser(request);
		String username = user.getUserName();
		String[] valArr = service.unicode2Gbk(request.getParameter("str")).split("!!@@");
		flag = service.gyjlCancelSh(myForm, valArr, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_CANCEL_NEW_SUCCESS : MessageInfo.MESSAGE_CANCEL_NEW_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 公寓纪律信息处理
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward gyjlxxcl(ActionMapping mapping, ActionForm from,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		searchModel.setPath("gygl_gyjlglnew_gyjlxxcl.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gygl_gyjlglnew_gyjlxxcl.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "");
		String userType=user.getUserType();
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("cflbList", new GyjlxxglService().getCflbList());
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{		
			return mapping.findForward("gyjlxxcl");
		}
	
	}
	
	
	/**
	 * 公寓纪律信息审核
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxsh(ActionMapping mapping, ActionForm from,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		searchModel.setPath("gygl_gyjlglnew_gyjlxxsh.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gygl_gyjlglnew_gyjlxxsh.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "");
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("gyjlxxsh");
	}
	
	
}
