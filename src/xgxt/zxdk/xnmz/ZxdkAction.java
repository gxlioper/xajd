package xgxt.zxdk.xnmz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;

import common.Globals;
import common.GlobalsVariable;

public class ZxdkAction extends BaseAction {

	/**
	 * 助学贷款设置 Method zxdkSz
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward author qlj
	 * @throws Exception
	 */
	public ActionForward zxdkSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZxdkService service = new ZxdkService();
		ZxdkForm myForm = (ZxdkForm) form;

		User user = getUser(request);

		String doType = request.getParameter("doType");

		String message = "";
		myForm.setUser(user);
		if ("save".equalsIgnoreCase(doType)) {

			boolean blog = service.saveZxdkSz(myForm, request);
			message = blog ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}

		// ---------标题 begin--------
		request.setAttribute("path", "zxdk_xnmz.do?method=zxdkSz");
		FormModleCommon.commonRequestSet(request);
		// ---------标题 end--------
		request.setAttribute("shlcList", service.getShlcList());
		request.setAttribute("rs", service.getSzMap());
		myForm.setSplc(service.getSzMap().get("splc"));
		return mapping.findForward("zxdkSz");
	}

	/**
	 * 助学贷款申请 Method zxdkSq
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward author qlj
	 * @throws Exception 
	 */
	public ActionForward zxdkSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		ZxdkService service = new ZxdkService();
		ZxdkForm myForm = (ZxdkForm) form;
	
		String sfysq=request.getParameter("sfysq");
		// 判断是否在申请时间内
		if(!service.checkZxdkSqsj()){
			String msg = "不在申请时间内，如有问题请与管理员联系！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		User user=getUser(request);
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");

		HashMap<String, String> stuInfo = new HashMap<String, String>();

		String userType =(String)session.getAttribute("userType");
		String userName =(String)session.getAttribute("userName");

		myForm.setUser(user);
		
		
		String message="";
		if ("save".equalsIgnoreCase(doType) && !"ysq".equalsIgnoreCase(sfysq)) {
			myForm.setShzt("未审核");
			boolean blog=service.saveZxdkxx(myForm, request);
			if(blog){
				blog=service.saveZxdkShInfo(myForm);
			}
			message = blog ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}

		if ("stu".equalsIgnoreCase(userType)) {

			xh = userName;
		}

		if (!Base.isNull(xh)) {
			myForm.setXh(xh);
			stuInfo = service.getStuInfo(myForm);
			stuInfo.putAll(service.getZxdkInfo(myForm));
			
		}
		
		request.setAttribute("zjlxList", service.getZjlx(myForm));
		request.setAttribute("doType", "add");
		request.setAttribute("tableName", "view_xsjbxx");
		request.setAttribute("rs", stuInfo);
		request.setAttribute("path", "zxdk_xnmz.do?method=zxdkSq");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zxdkSq");
	}
	
	/**
	 * 助学贷款申请 Method zxdkSq
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward author qlj
	 * @throws Exception 
	 */
	public ActionForward zxdkModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		ZxdkService service = new ZxdkService();
		ZxdkForm myForm = (ZxdkForm) form;

		User user=getUser(request);
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");

		HashMap<String, String> stuInfo = new HashMap<String, String>();

		String userType = (String)session.getAttribute("userType");
		String userName = (String)session.getAttribute("userName");

		myForm.setUser(user);
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			
			boolean blog=service.saveZxdkxx(myForm, request);
			message = blog ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
			doType="update";
		}

		if ("stu".equalsIgnoreCase(userType)) {

			xh = userName;
		}

		if (!Base.isNull(xh)) {
			if (xh.split("!!@@!!").length>1) {
				myForm.setXh(xh.split("!!@@!!")[1]);
				myForm.setGuid(xh.split("!!@@!!")[0]);
			} else {
				myForm.setXh(xh);
				myForm.setGuid(request.getParameter("guid"));
			}
			
			stuInfo = service.getStuInfo(myForm);
			stuInfo.putAll(service.getZxdkInfo(myForm));
		}

		request.setAttribute("zjlxList", service.getZjlx(myForm));
		request.setAttribute("tableName", "view_xsjbxx");
		request.setAttribute("rs", stuInfo);
		request.setAttribute("path", "zxdk_xnmz.do?method=zxdkSq");
		request.setAttribute("doType", doType);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zxdkSq");
	}

	/**
	 * 助学贷款查询 Method zxdkcxManage
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward author qlj
	 * @throws Exception 
	 */
	public ActionForward zxdkcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		ZxdkForm myForm = (ZxdkForm) form;
		ZxdkService service = new ZxdkService();

		String doType=request.getParameter("doType");
		
		String message="";
		if("del".equalsIgnoreCase(doType)){
			boolean blog=service.delZxdkInfo(myForm);
			message = blog ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		
		ZxdkInit init = new ZxdkInit();

		init.initZxdkCx(rForm, myForm, request);
		// 模块标题
		// ============= 设置request的值 =============
		ArrayList<String[]> list = (ArrayList<String[]>) service.getZxdkInfo(
				request, rForm.getColList(), myForm);
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		rForm.setRsArrList(list);
		service.setRequestValue(rForm, user, request);
		// =================== end ==================
		return mapping.findForward("zxdkcxManage");
	}

	public ActionForward zxdkShlcgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxdkForm myForm=(ZxdkForm)form;
		ZxdkService service=new ZxdkService();
		
		String pkValue=request.getParameter("pkValue");
		
		myForm.setGuid(pkValue.split("!!@@!!")[0]);
		
		request.setAttribute("rs",service.zxdkLcgz(myForm));
		return mapping.findForward("zxdkShlcgz");
	} 
	
	
	
	/**
	 * 助学贷款审核 Method zxdkshManage
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward author qlj
	 * @throws Exception 
	 */
	public ActionForward zxdkshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		ZxdkForm myForm = (ZxdkForm) form;
		ZxdkService service = new ZxdkService();

		if(!service.checkZxdkShsj()){
			String msg = "不在审核时间内，如有问题请与管理员联系！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		myForm.setUser(user);
		
		myForm.setSplc(service.getSzMap().get("splc"));
		
		List<HashMap<String, String>> spgwList = service.getUserSpgw(myForm);
		//一人存在多审核岗位时控制
		service.shgwKz(request, myForm, spgwList);
		
		ZxdkInit init = new ZxdkInit();

		init.initZxdkSh(rForm, myForm, request);
		String message="";
		if("plsh".equalsIgnoreCase(rForm.getDoType())){
			String[]pkValue=new String[myForm.getPrimary_key().length];
			for(int i=0;i<myForm.getPrimary_key().length;i++){
				pkValue[i]=myForm.getPrimary_key()[i].split("!!@@!!")[0];
				
			}
			myForm.setPrimary_key(pkValue);
			boolean blog=service.updateShzt(myForm);
			message = blog ? MessageInfo.MESSAGE_SH_SUCCESS
					: MessageInfo.MESSAGE_SH_FALSE;
			request.setAttribute("message", message);
			rForm.setSearch("true");
		}
		
		// 模块标题
		// ============= 设置request的值 =============
		
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		if(search){
			ArrayList<String[]> list = (ArrayList<String[]>) service.getZxdkSh(
					request, rForm.getColList(), myForm);
			request.setAttribute("searchTj", myForm.getSearchModel());
			rForm.setRsArrList(appengList(list));
		}
		
		
		request.setAttribute("shgw", myForm.getShgw());
		service.setRequestValue(rForm, user, request);
		// =================== end ==================
		return mapping.findForward("zxdkshManage");
	}
	
	public ArrayList<String[]> appengList(List<String[]> list) {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		if (list != null && list.size() > 0) {
			for (String[] array : list) {
				String[] one = array;
				if (one.length>2) {
					one[1] = one[1]+one[2];
				}
				rs.add(one);
			}
		}
		return rs;
	}
	
	/**
	 * 助学贷款审核 Method zxdkshManage
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward author qlj
	 * @throws Exception 
	 */
	public ActionForward zxdkDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		ZxdkService service=new ZxdkService();
		ZxdkForm myForm=(ZxdkForm)form;
		HashMap<String,String>stuInfo=new HashMap<String,String>();
		
		String doType=request.getParameter("doType");
		String pkVs=request.getParameter("xh");
		
		String[]pkArr=pkVs.split("!!@@!!");
		String xh="";
		String guid="";
		if(pkArr.length>1){
			xh=pkVs.split("!!@@!!")[1];
			guid=pkVs.split("!!@@!!")[0];
			myForm.setGuid(guid);
		}else{
			xh=pkVs;
		}
		
		String[]pkValue={myForm.getGuid()};
		
		myForm.setPrimary_key(pkValue);
		myForm.setUser(user);
		
		if (!Base.isNull(xh)) {
			myForm.setXh(xh);
			stuInfo = service.getStuInfo(myForm);
			stuInfo.putAll(service.getZxdkInfo(myForm));
		}
		
		String message="";
		if("save".equalsIgnoreCase(doType)){
			
			boolean blog=service.updateShzt(myForm);
			message = blog ? MessageInfo.MESSAGE_SH_SUCCESS
					: MessageInfo.MESSAGE_SH_FALSE;
			request.setAttribute("message", message);
		}
		request.setAttribute("shxxList", service.getShList(myForm));
		request.setAttribute("rs", stuInfo);
		request.setAttribute("guid", guid);
		request.setAttribute("shgw", myForm.getShgw());
		return mapping.findForward("zxdkDgsh");
	}
	
	/**
	 * 助学贷款审核 
	 * Method zxdkshManage
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward author qlj
	 * @throws Exception 
	 */
	public ActionForward zxdkbbdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ZxdkService service =new ZxdkService();
		ZxdkForm myForm=(ZxdkForm)form;
		String pkValue=request.getParameter("pkValue");
		String dyblx = request.getParameter("dyblx");
		myForm.setGuid(pkValue.split("!!@@!!")[0]);
		myForm.setXh(pkValue.split("!!@@!!")[1]);
		HashMap<String, String> stuInfo = service.getStuInfo(myForm);
		
		
		XsxxglService xsxxglService = new XsxxglService();
		request.setAttribute("pyccList", xsxxglService.getList(GlobalsVariable.XTWH_PYCC_LIST));//培养层次
		
		stuInfo.putAll(service.getZxdkInfo(myForm));
		
		request.setAttribute("rs", stuInfo);
		
		if(Base.xxdm.equals(Globals.XXDM_ZYMZDX)&&"zhgjzxdk".equals(dyblx)){
			//中央民族大学个性化跳转
			return mapping.findForward("zyjzdx");
		}else{
			return mapping.findForward(dyblx);
		}
	}
	
}