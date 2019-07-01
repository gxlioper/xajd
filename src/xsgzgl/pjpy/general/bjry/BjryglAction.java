package xsgzgl.pjpy.general.bjry;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.pjpy.general.PjpyGeneralForm;

public class BjryglAction extends BasicAction {
	
	/**
	 * 班级荣誉查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		rForm.setPath("pjpy_bjry_bjrygl.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_pjpy_bjryb");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_pjpy_bjryb");
		return mapping.findForward("bjryglCx");
	}
	
	/**
	 * 班级荣誉增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		RequestForm rForm = new RequestForm();
		// 获取荣誉下拉列表框
		request.setAttribute("hdryList", service.getHdryList(request));
		HashMap<String, String> bjryglxx = new HashMap<String, String>();
		bjryglxx.put("xn", jbszModel.getPjxn());
		request.setAttribute("rs", bjryglxx);
		User user = getUser(request);
		rForm.setPath("pjpy_bjry_bjrygl.do");
		// 获取學年學期年度下拉列表框
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request);
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("bjryglZj");
	}
	
	/**
	 * 获取班级名称
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBjmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("pjpy_bjry_bjrygl.do?searchType=getBjmc");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("bjmcInfo");
	}
	
	/**
	 * 班级荣誉查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		myForm.setGuid(pkValue);
		HashMap<String, String> bjryglxx = service.getBjryglMap(myForm);
		request.setAttribute("rs", bjryglxx);
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("pjpy_bjry_bjrygl.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("bjryglCk");
	}
	
	/**
	 * 班级荣誉维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		myForm.setGuid(pkValue);
		// 获取获得荣誉下拉列表框
		request.setAttribute("hdryList", service.getHdryList(request));
		HashMap<String, String> bjryglxx = service.getBjryglMap(myForm);
		request.setAttribute("rs", bjryglxx);
		User user = getUser(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request);
		// ----------------显示title，判断读写权----------------
		rForm.setPath("pjpy_bjry_bjrygl.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("bjryglWh");
	}
	
	/**
	 * 班级荣誉删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		String message = "";
		boolean flag = false;
		User user = getUser(request);
		String username = user.getUserName();
		String[] valArr = service.unicode2Gbk(request.getParameter("str")).split("!!@@");
		flag = service.bjryglSc(myForm, valArr, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}