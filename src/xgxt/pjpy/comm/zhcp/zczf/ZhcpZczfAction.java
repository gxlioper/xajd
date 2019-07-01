package xgxt.pjpy.comm.zhcp.zczf;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.comm.xml.XMLReader;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.jbsz.PjpyJbszService;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszInit;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszService;
import xgxt.pjpy.comm.zhcp.pdbx.ZhcpPdbxForm;
import xgxt.pjpy.comm.zhcp.pdbx.ZhcpPdbxService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xsgzgl.pjpy.general.PjpyGeneralForm;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_综测总分_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class ZhcpZczfAction extends BasicAction {
	
	/**
	 * 综合测评_基本设置_综测基本设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 
		PjpyJbszService jbszService = new PjpyJbszService();
		ZhcpZczfService service=new ZhcpZczfService();
		ZhcpZczfForm myForm=(ZhcpZczfForm)form;
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		
		String doType=request.getParameter("doType");
		String pkValue=request.getParameter("pkValue");
		myForm.setPath("zhcp_zczf_zccx.do");
		//综测排名设置
		String zcpm = XMLReader.getFlowControl("pjpy", "zcpm");
		//智育排名设置
		String zypm = XMLReader.getFlowControl("pjpy", "zypm");
		
		// ==============综测周期========================
		HashMap<String, String> jbszMap = jbszService.getPjpyJbsz();

		myForm.setPjxn(jbszMap.get("pjxn"));
		myForm.setPjxq(jbszMap.get("pjxq"));
		myForm.setPjnd(jbszMap.get("pjnd"));
		// ==============综测周期 end========================
		
		String message = "";// 提示信息
		
		myForm.setZypm(zypm);
		if("zcjs".equalsIgnoreCase(doType)){
			String[]jslx=myForm.getJslx();
			boolean flag=true;
			for(int i=0;i<jslx.length;i++){
				//总分计算
				if ("zfjs".equalsIgnoreCase(jslx[i]) && flag ) {
					flag = service.zcxmjs(myForm, user);
				
				} 
				
				if ("pmjs".equalsIgnoreCase(jslx[i]) && flag) {
					flag = service.getPlace(myForm, user);
				}
				
				if("zypmjs".equalsIgnoreCase(jslx[i]) && flag){
					flag = service.getZyPlace(myForm, user);
				}
			}
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		
		if("zfpm".equalsIgnoreCase(doType)){
			boolean flag=service.getPlace(myForm, user);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		//结果集
		List<String[]>rs=service.getZhcpZczf(myForm, user);
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		// ============request==============
		request.setAttribute("zypm", zypm);
		request.setAttribute("zcpm", zcpm);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("path", "zhcp_zczf_zccx.do");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
//		request.setAttribute("stuInfo", stuInfo);
		request.setAttribute("topTr", service.getToptr(myForm, user));
		service.setRequestValue(rForm, request);
		FormModleCommon.commonRequestSet(request);
		// ==========request end==============
		return mapping.findForward("zcfManage");
	}
	
	/**
	 * 综合测评_基本设置_综测基本设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kindChoose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpZczfService service=new ZhcpZczfService();
		ZhcpZczfForm myForm=(ZhcpZczfForm)form;
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		
		String zcpm = XMLReader.getFlowControl("pjpy", "zcpm");
		String zypm = XMLReader.getFlowControl("pjpy", "zypm");
		
		myForm.setZcpm(zcpm);
		myForm.setZypm(zypm);
		
		String doType=request.getParameter("doType");
//		request.setAttribute("stuInfo", stuInfo);
		myForm.setPath("zhcp_zczf_zccx.do");
		request.setAttribute("topTr", service.getKindChoose(myForm, user));
		
		String message = "";// 提示信息
		if("save".equalsIgnoreCase(doType)){
			
			boolean flag=service.saveKindChoose(myForm, user);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
			
		}
//		service.setRequestValue(rForm, request);
//		FormModleCommon.commonRequestSet(request);
		
		//是否已经有列选设置
		request.setAttribute("checkKind", service.getCheckKind(myForm, user));
		service.setRequestValue(rForm, request);
		// ==========request end==============
		return mapping.findForward("kindChoose");
	}

	
	
	/**
	 * 江苏信息综测报表打印
	 */
	public ActionForward getZhcpPrintData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		String xn = jbszModel.getPjxn();
		String xq = jbszModel.getPjxq();
		String bjdm = request.getParameter("bjdm");
//		String bjdm = request.getParameter("bjdm");
		
		ZhcpZczfService service=new ZhcpZczfService();
		
		Object[] data = service.getPrintData(xn, xq, bjdm);
		
		request.setAttribute("rs", data[0]);
		request.setAttribute("map", data[1]);
		request.setAttribute("rskc", data[2]);
		return mapping.findForward("getZhcpPrintData");
	}
	
	
	
	
}
