package xgxt.pjpy.bjlhly.zhcp;

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

public class BjlhlyZhcpAction extends BasicAction{

	
	
	public ActionForward zcjgManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjlhlyZhcpService service =new BjlhlyZhcpService();
		BjlhlyZhcpForm myForm=(BjlhlyZhcpForm)form;
		
		RequestForm rForm = new RequestForm();
		String doType=request.getParameter("doType");
		BjlhlyZhcpInit init=new BjlhlyZhcpInit();
		User user = getUser(request);// 用户对象
//		String doType=request.getParameter("doType");
		
		//结果集显示字段
		init.initZcjgInfo(rForm, myForm, request);
		String[] colList = rForm.getColList();
		myForm.setUser(user);
		String message="";
		if("save".equalsIgnoreCase(doType)){
			
			boolean result=service.zhfjs(myForm);
			message = result ? MessageInfo.MESSAGE_ACCOUNT_SUCCESS
					: MessageInfo.MESSAGE_ACCOUNT_FALSE;
			request.setAttribute("result", result);
			request.setAttribute("message", message);
			doType=null;
		}
		
		
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		
		rsArrList = (ArrayList<String[]>)service.getZcjgList(myForm, colList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("path", rForm.getPath());
		request.setAttribute("realTable", "xg_pjpy_bjlhly_zsbxscjb");
		request.setAttribute("tableName", "xg_view_pjpy_bjlhly_zsbxscj");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("success");
	}
	
	/**
	 * 综合分成绩查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcjgCx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BjlhlyZhcpService service =new BjlhlyZhcpService();
		BjlhlyZhcpForm myForm=(BjlhlyZhcpForm)form;

		XsxxglService stuService = new XsxxglService();
		
		String doType=request.getParameter("doType");
		String act=request.getParameter("act");
		String xh=request.getParameter("xh");
		
		
		HashMap<String, String> rs = stuService.selectStuinfo(xh);
		List<HashMap<String,String>> cjList=service.getCjList(myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("cjList", cjList);
		request.setAttribute("xnxqList", service.getGroupList(myForm));
		request.setAttribute("path", "zhcp_bjlhly_zcjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zcjgCx");
	}
	

}
