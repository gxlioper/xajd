package xgxt.xsxx.comm.sjy.jcsjsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_数据源_基础数据维护action类
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

public class SjyJcsjszAction extends BasicAction {

	/** 
	 * 功能模块选择
	 * Method xsxxwh
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward jcsjszChoose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward("jcsjszChoose");
	}
	
	/**
	 * 学生信息_数据源_基础数据设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcsjszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SjyJcsjszForm myForm = (SjyJcsjszForm) form;
		SjyJcsjszService service = new SjyJcsjszService();
		SjyJcsjszInit init = new SjyJcsjszInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getJcsjszRForm(rForm, myForm, request);

		// 是否查询操作
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 结果集显示字段
		String[] colList = rForm.getColList();
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		// =============== 执行创建视图操作 ===============
		if ("create".equalsIgnoreCase(doType)) {
			boolean flag = service.createNewProcedure(myForm);
			String message = flag ? MessageInfo.MESSAGE_CREATE_SUCCESS
					: MessageInfo.MESSAGE_CREATE_FALSE;
			message+="\n（注：可以在plsql等工具，中查\n看存储过程pro_xg_jcsj_stuInfo_tj的结构）";
			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =============== 执行查询操作 ===========
		if (search) {
			// 结果集
			rsArrList = service.getZdszRsList(myForm, user, colList);
		} else {
			myForm.getPages().setMaxPage(1);
		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("jcsjszManage");
	}
	
	/**
	 * 学生信息_数据源_基础数据设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcsjszGuide(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SjyJcsjszForm myForm = (SjyJcsjszForm) form;
		SjyJcsjszService service = new SjyJcsjszService();
		SjyJcsjszInit init = new SjyJcsjszInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getJcsjszGuideRForm(rForm, myForm, request);

		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		//草组步骤
		String step = request.getParameter("step");
		myForm.setStep(step);
		// ================= end =====================

		// =============== 构建操作步骤 ===============
		List<HashMap<String, String>> stepList = service.getStepList(myForm);
		request.setAttribute("stepList", stepList);
		
		String[] ch_zd = myForm.getCh_zd();
		String[] ch_zdm = myForm.getCh_zdm();
		request.setAttribute("ch_zd", ch_zd);
		request.setAttribute("ch_zdm", ch_zdm);
		
		//可操作字段列表
		List<HashMap<String, String>> kczzdList = service.getKczzdList(myForm);
		request.setAttribute("kczzdList", kczzdList);
		
		if(kczzdList!=null && kczzdList.size()>0){
			int num = kczzdList.size();
			int rowNum = 14;
			if(num <rowNum){
				rowNum = rowNum-num;
			}
			request.setAttribute("rowNum", rowNum);
		}
		// ================= end =====================
		
		// =============== 具体操作步骤 ===============
		if ("step1".equalsIgnoreCase(step)) {// 第一步
			// 待分配字段列表
			List<HashMap<String, Object>> dszZdList = service
					.getDszZdList(myForm);
			request.setAttribute("dszZdList", dszZdList);
		} else {
			// 需设置字段列表
			List<HashMap<String, Object>> xszZdList = service.getXszZdList(
					myForm, kczzdList);
			request.setAttribute("xszZdList", xszZdList);
		} 

		// ================= end =====================
		
		// =============== 执行保存操作 ===============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZdsz(myForm, user);

			// 创建存储过程
			if (flag) {
				service.createNewProcedure(myForm);
			}

			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("jcsjszGuide");
	}
	
	/**
	 * 学生信息_数据源_基础数据设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcsjszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SjyJcsjszForm myForm = (SjyJcsjszForm) form;
		SjyJcsjszService service = new SjyJcsjszService();
		SjyJcsjszInit init = new SjyJcsjszInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getJcsjszRForm(rForm, myForm, request);

		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 待分配字段列表
		String zd = request.getParameter("zd");
		// ================= end =====================
		
		// =============== 获得该字段的各项信息 ===============
		HashMap<String, Object> rs = service.getZdszXgInfo(zd);
		request.setAttribute("rs", rs);
		// ================= end =====================
		
		// =============== 执行保存操作 ===============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZdsz(myForm, user);
			
			//创建存储过程
			if(flag){
				service.createNewProcedure(myForm);
			}
			
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("jcsjszUpdate");
	}
	
	//==============================以下方法by qlj=========================================
	/**
	 * 学生信息_数据源_学生信息详细页面配置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxypzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SjyJcsjszForm myForm = (SjyJcsjszForm) form;
		SjyJcsjszService service = new SjyJcsjszService();
		SjyJcsjszInit init = new SjyJcsjszInit();
		User user = getUser(request);// 用户对象
		String doType=request.getParameter("doType");
		
		//保存详细页配置
		if("save".equalsIgnoreCase(doType)){
			
			boolean flag=service.saveXxysz(myForm);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}
		
		//获取显示项目列表
		request.setAttribute("xsxmList", service.getXsmkList(myForm));
		
		request.setAttribute("xxypzList", service.getXxypz(myForm));
		// =================== end ===================
		
		request.setAttribute("path", "xsxx_sjy_xxypz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xxypzManage");
	}
	//============================== end =========================================
}
