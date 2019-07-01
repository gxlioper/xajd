package xgxt.xsxx.dagl.guizdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

public class XsxxGuizdxDaglAction extends BasicAction{
	
	XsxxGuizdxDaglService service=new XsxxGuizdxDaglService();
	/**
	 * 档案类型维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dalxwhManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XsxxGuizdxDaglForm myForm=(XsxxGuizdxDaglForm)form;
		
		RequestForm rForm = new RequestForm();
		
		XsxxGuizdxDaglInit init=new XsxxGuizdxDaglInit();
		User user = getUser(request);// 用户对象
		String doType=request.getParameter("doType");
		
		//初始化
		init.initDalxwhInfo(rForm, myForm, request);
		String[] colList = rForm.getColList();
		String message="";
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.delDadmb(myForm);
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("result", flag);
		}
		
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		
		rsArrList = (ArrayList<String[]>)service.getDadmbInfo(myForm, colList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("message", message);
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("path", rForm.getPath());
		request.setAttribute("realTable", "xg_pjpy_bjlhly_zsbxscjb");
		request.setAttribute("tableName", "xg_pjpy_bjlhly_zsbxscjb");
		request.setAttribute("lxList", service.getLxList());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dalxwhManage");
	}
	
	/**
	 * 档案类型维护修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dalxwhUpdate(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XsxxGuizdxDaglForm myForm=(XsxxGuizdxDaglForm)form;
		
		String doType=request.getParameter("doType");
		String act=request.getParameter("act");
		String []pkValue=request.getParameterValues("pkValue");
		
		if(pkValue!=null && pkValue.length>0){
			
			HashMap<String,String> dadmList=service.getOnDadmb(myForm);
			request.setAttribute("rs", dadmList);
		}
		
		boolean result=false;
		String message="";
		if("update".equalsIgnoreCase(doType)){
			
			result=service.updateDadmb(myForm);
			message = result ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("result", result);
		}else if("add".equalsIgnoreCase(doType)){
			
			result=service.addDadmb(myForm);
			message = result ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("result", result);
		}
		request.setAttribute("message", message);
		request.setAttribute("act", act);
		request.setAttribute("doType", doType);
		request.setAttribute("lxList", service.getLxList());
		request.setAttribute("path", "guizdx_dagl_dalxwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dalxwhUpdate");
	}
	

	/**
	 * 新生档案信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsdawhManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsxxGuizdxDaglForm myForm=(XsxxGuizdxDaglForm)form;
		
		RequestForm rForm = new RequestForm();
		
		XsxxGuizdxDaglInit init=new XsxxGuizdxDaglInit();
		User user = getUser(request);// 用户对象
		String doType=request.getParameter("doType");
		myForm.setUser(user);
		//初始化
		init.initXsdawhInfo(rForm, myForm, request);
		String[] colList = rForm.getColList();
		String message="";
		if("save".equalsIgnoreCase(doType)){
			boolean flag=service.saveXsdaByCheck(request, myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("result", flag);
		}
		
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		rsArrList = (ArrayList<String[]>)service.getXsrxdaInfo(myForm, colList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("message", message);
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("path", rForm.getPath());
		request.setAttribute("realTable", "xg_pjpy_bjlhly_zsbxscjb");
		request.setAttribute("tableName", "xg_pjpy_bjlhly_zsbxscjb");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		myForm.setLx("001");
		request.setAttribute("lxList", service.getDaxxByLx(myForm));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsdawhManage");
	}
	
	/**
	 * 档案类型维护修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsdawhUpdate(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String xh=request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);

		XsxxGuizdxDaglForm myForm=(XsxxGuizdxDaglForm)form;
		
		String doType=request.getParameter("doType");

		request.setAttribute("rs", stuInfo);
		
		User user=getUser(request);
		myForm.setUser(user);
		boolean result=false;
		String message="";
		
		
		myForm.setXh(xh);
		if("save".equalsIgnoreCase(doType)){
//			myForm.setXsdaInfo(request.getParameterValues("xsdaInfo"));
			result=service.saveXsdaByOne(myForm);
			message = result ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			
			
			request.setAttribute("result", result);
		}
		request.setAttribute("xsdadmInfo", service.getXsdaByOne(myForm));
		myForm.setLx("001");
		request.setAttribute("lxList", service.getDaxxByLx(myForm));
		request.setAttribute("message", message);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "guizdx_dagl_xsda.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsdawhUpdate");
	}
	
	
	/**
	 * 新生档案信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdawhManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsxxGuizdxDaglForm myForm=(XsxxGuizdxDaglForm)form;
		
		RequestForm rForm = new RequestForm();
		
		XsxxGuizdxDaglInit init=new XsxxGuizdxDaglInit();
		User user=getUser(request);
		myForm.setUser(user);
		String doType=request.getParameter("doType");
		
		//初始化
		init.initZxdawhInfo(rForm, myForm, request);
		String[] colList = rForm.getColList();
		String message="";
		if("save".equalsIgnoreCase(doType)){
			boolean flag=service.saveZxdaByCheck(request, myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("result", flag);
		}
		
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		rsArrList = (ArrayList<String[]>)service.getZxdaInfo(myForm, colList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("message", message);
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("path", rForm.getPath());
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		myForm.setLx("002");
		request.setAttribute("lxList", service.getDaxxByLx(myForm));
		request.setAttribute("path", "guizdx_dagl_zxda.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zxdawhManage");
	}
	
	
	/**
	 * 档案类型维护修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdawhUpdate(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		String xh=request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);

		XsxxGuizdxDaglForm myForm=(XsxxGuizdxDaglForm)form;
		
		String doType=request.getParameter("doType");

		request.setAttribute("rs", stuInfo);
		
		
		boolean result=false;
		String message="";
		
		myForm.setUser(user);
		myForm.setXh(xh);
		if("save".equalsIgnoreCase(doType)){
//			myForm.setXsdaInfo(request.getParameterValues("xsdaInfo"));
			result=service.saveZxdaByOne(myForm);
			message = result ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("result", result);
		}
		
		request.setAttribute("xsdadmInfo", service.getZxdaByOne(myForm));
		myForm.setLx("002");
		request.setAttribute("lxList", service.getDaxxByLx(myForm));
		request.setAttribute("message", message);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "guizdx_dagl_zxda.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zxdawhUpdate");
	}
	
	/**
	 * 新生档案信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward byzdwhManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XsxxGuizdxDaglForm myForm=(XsxxGuizdxDaglForm)form;
		
		RequestForm rForm = new RequestForm();
		
		XsxxGuizdxDaglInit init=new XsxxGuizdxDaglInit();
		User user=getUser(request);
		myForm.setUser(user);
		String doType=request.getParameter("doType");
		
		//初始化
		init.initByzdwhInfo(rForm, myForm, request);
		String[] colList = rForm.getColList();
		String message="";
		if("save".equalsIgnoreCase(doType)){
			boolean flag=service.saveByzdByCheck(request, myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("result", flag);
		}
		
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		
		rsArrList = (ArrayList<String[]>)service.getBysdaInfo(myForm, colList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("message", message);
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("path", rForm.getPath());
		request.setAttribute("realTable", "xg_pjpy_bjlhly_zsbxscjb");
		request.setAttribute("tableName", "xg_pjpy_bjlhly_zsbxscjb");
		myForm.setLx("003");
		request.setAttribute("lxList", service.getDaxxByLx(myForm));
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("byzdwhManage");
	}
	
	/**
	 * 档案类型维护修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward byzdwhUpdate(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		XsxxGuizdxDaglForm myForm=(XsxxGuizdxDaglForm)form;
		HashMap<String,String>rs=new HashMap<String,String>();
		String doType=request.getParameter("doType");
		String act=request.getParameter("act");
		
		String xh=request.getParameter("xh");
		myForm.setUser(user);
		
		if(!Base.isNull(xh)){
			myForm.setXh(xh);
			XsxxglService stuService = new XsxxglService();
			HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
			HashMap<String,String> dadmList=service.getOneBysda(myForm);
			rs= stuInfo;
			rs.putAll(dadmList);
		}
		
		
		boolean result=false;
		String message="";
		if("save".equalsIgnoreCase(doType)){
			
			result=service.addBysda(request,myForm);
			message = result ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("result", result);
		}
		request.setAttribute("xsdadmInfo", service.getBydaByOne(myForm));
		myForm.setLx("003");
		request.setAttribute("lxList", service.getDaxxByLx(myForm));
		request.setAttribute("message", message);
		request.setAttribute("act", act);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "guizdx_dagl_byzd.do");
		FormModleCommon.commonRequestSet(request);
//		request.setAttribute("lxList", service.getLxList());
		request.setAttribute("rs", rs);
		return mapping.findForward("byzdwhUpdate");
	}
	
	
	
	
	/**
	 * 新生档案信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dacxManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XsxxGuizdxDaglForm myForm=(XsxxGuizdxDaglForm)form;
		
		RequestForm rForm = new RequestForm();
		
		XsxxGuizdxDaglInit init=new XsxxGuizdxDaglInit();
		User user=getUser(request);
		myForm.setUser(user);
		String doType=request.getParameter("doType");
		
		//初始化
		init.initDacxInfo(rForm, myForm, request);
		String[] colList = rForm.getColList();
		String message="";
		
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		
		rsArrList = (ArrayList<String[]>)service.getDacxInfo(myForm, colList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("message", message);
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("path", rForm.getPath());
		request.setAttribute("realTable", "xg_pjpy_bjlhly_zsbxscjb");
		request.setAttribute("tableName", "xg_pjpy_bjlhly_zsbxscjb");
		request.setAttribute("lxList", service.getLxList());
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("dacxManage");
	}
	
	/**
	 * 档案类型维护修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dacxUpdate(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		XsxxGuizdxDaglForm myForm=(XsxxGuizdxDaglForm)form;
		HashMap<String,String>rs=new HashMap<String,String>();
		String doType=request.getParameter("doType");
		String act=request.getParameter("act");
		
		String xh=request.getParameter("xh");
		myForm.setUser(user);
		
		if(!Base.isNull(xh)){
			myForm.setXh(xh);
			XsxxglService stuService = new XsxxglService();
			HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
			HashMap<String,String> dadmList=service.getOneBysda(myForm);
			rs= stuInfo;
			rs.putAll(dadmList);
		}
		
		
		boolean result=false;
		String message="";
		if("save".equalsIgnoreCase(doType)){
			
			result=service.addBysda(request,myForm);
			message = result ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("result", result);
		}
		// ---------------新生档案---------------------
		List<HashMap<String,String>>xsdadmInfo= service.getXsdaByOne(myForm);
		if(xsdadmInfo!=null && xsdadmInfo.size()>0){
			request.setAttribute("xsdadmInfo", xsdadmInfo);
		}
		// ---------------在校档案---------------------
		List<HashMap<String,String>>zxdadmInfo= service.getZxdaByOne(myForm);
		if(zxdadmInfo!=null && zxdadmInfo.size()>0){
			request.setAttribute("zxdadmInfo", zxdadmInfo);
		}
		// ---------------毕业档案---------------------
		List<HashMap<String,String>>bydadmInfo= service.getBydaByOne(myForm);
		if(bydadmInfo!=null && bydadmInfo.size()>0){
			request.setAttribute("bydadmInfo", bydadmInfo);
		}
		
		request.setAttribute("message", message);
		request.setAttribute("act", act);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "guizdx_dagl_dacx.do");
		FormModleCommon.commonRequestSet(request);
//		request.setAttribute("lxList", service.getLxList());
		request.setAttribute("rs", rs);
		return mapping.findForward("dacxUpdate");
	}
}
