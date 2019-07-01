package xgxt.xtwh.comm.splc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommSetting;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.db.GetSysData;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 通用版本学生资助-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 裘力俊
 * @version 1.0
 */

public class XtwhShlcAction extends BasicAction {

	/**
	 * 审批流程管理模块
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward splcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RequestForm rForm = new RequestForm();
		XtwhShlcForm myForm = (XtwhShlcForm) form;
		XtwhShlcService service = new XtwhShlcService();
		User user = getUser(request);

		String doType = request.getParameter("doType");
		String message = "";// 提示信息
		if ("del".equalsIgnoreCase(doType)) {

			boolean blog = service.delShlc(myForm);
			message = blog ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", message);
		}

		// ===============结果集补空行 begin=================
		List<HashMap<String, String>> topTr = service.getTopTr(myForm);
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rs";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = String.valueOf(topTr.size() - 1);
		commSetting.setShowNum(showNum);
		rForm.setCommSetting(commSetting);
		service.setRequestValue(rForm, user, request);
		// ===============结果集补空行 end=================

		request.setAttribute("rs", service.getSplcList(myForm));
		request.setAttribute("topTr", topTr);
		request.setAttribute("ssmkList", service.getSsmkList());
		request.setAttribute("path", "splcNew.do?method=splcManage");
		request.setAttribute("doType", doType);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("splcManage");
	}

	// -------------------------------这是今天要中更多 的方法大师写下面
	// begin----------------------------------
	/**
	 * 审批流程用户设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward splcYhsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhShlcForm myForm = (XtwhShlcForm) form;
		XtwhShlcService service = new XtwhShlcService();

		User user = getUser(request);
		myForm.setUser(user);

		String message = "";// 提示信息
		String doType = request.getParameter("doType");
		// 如果是流程设置页面过来的，则返回到流程设置界面
		String lcid = request.getParameter("lcid");

		if (lcid != null && !lcid.equalsIgnoreCase("")) {
			request.setAttribute("lcid", lcid);
			myForm.setId(lcid);
			myForm.setPkValue(lcid);
		}
		// ========== 保存按钮已删除 此代码已无用 start ==========
		if ("save".equalsIgnoreCase(doType)) {

			// ========== 个性化 用户授权 begin ============
			boolean blog = true;
			String yhszlx = myForm.getYhszlx();
			if("rcxwwh".equals(yhszlx)){
				// 日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别
				blog = service.saveSpgwRcxwwh(myForm);
			}else{
				// 系统维护-审批流程维护-审批流程
				blog = service.saveSpgw(myForm);
			}
			// ========== 个性化 用户授权 end ============
			message = blog ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
			if (lcid != null && !lcid.equalsIgnoreCase("")) {
				return new ActionForward("/splcNew.do?method=splcZs&lcid="
						+ lcid, false);
			}
		}
		// ========== 保存按钮已删除 此代码已无用 end ==========
		
		// 审批岗位
		String gwdm = request.getParameter("spgw");

		// 鲁宁新增，获取岗位所属流程及岗位名称
		String gwlx = request.getParameter("gwlx");
		String[] gwxx = service.getGwxx(gwlx, gwdm,lcid);
		List<HashMap<String, String>> gwxxList =service.getSpgwByShl(myForm);
		request.setAttribute("gwxxList", gwxxList);
		request.setAttribute("gwxx", gwxx);
		request.setAttribute("gwlx", gwlx);// 大师老是做出BUG，神强烈鄙视他 2012.6.29
		request.setAttribute("spgw", gwdm);
		String yhszlx = request.getParameter("yhszlx");
		request.setAttribute("yhszlx", yhszlx);
		return mapping.findForward("splcYhsz");
	}

	// -------------------------------这是今天要中更多 的方法大师写下面
	// end----------------------------------

	// -------------------------------这是今天要中的比楼上更多的方法
	// begin----------------------------------

	/**
	 * 审批流程增加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward splcAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title = "流程信息";
		request.setAttribute("title", title);
		XtwhShlcService service = new XtwhShlcService();
		request.setAttribute("ssmkList", service.getSsmkList());
		request.setAttribute("gdgwList", service.getGdgwList());
		return mapping.findForward("splcAdd");
	}

	/**
	 * 审批流程增加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward splcSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XtwhShlcForm myForm = (XtwhShlcForm) form;
		XtwhShlcModel model = new XtwhShlcModel();
		XtwhShlcService service = new XtwhShlcService();
		BeanUtils.copyProperties(model, myForm);
		String lcid = GetSysData.getGuid();
		model.setId(lcid);
		String lcmc = myForm.getLcmc();

		model.setMc(lcmc);
		Boolean update = service.savelcxx(model, request);

		// 保存成功则进入下一个预览页面
		if (update){
			String type = request.getParameter("type");//得到是否需要通用岗位用户初始化，
			if(null != type){//如果是
				//通用功能改造（增加根据选择的数据范围库增加岗位用户）
				//service.insertGwyhBySjfw(lcid);	
				service.gwCsh(lcid);	
			}			
			// 获取流程信息
			// 用户信息
			HashMap<String, String> rs = service.getLcxx(lcid);
			// 流程所属岗位信息
			List<String[]> lcgwrs = service.getLcgwxx(lcid);
			String title = "流程展示";
			request.setAttribute("title", title);
			request.setAttribute("rs", rs);
			request.setAttribute("lcgwrs", lcgwrs);
			request.setAttribute("ssmkList", service.getSsmkList());
			myForm.setMaxSize(new Integer(lcgwrs.size()).toString()); 
			return mapping.findForward("splcLczs");
		} else {
			request.setAttribute("result", "false");
			return mapping.findForward("splcAdd");
		}

	}

	/**
	 * 审批流程展示
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward splcZs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取流程信息
		// 用户信息
		XtwhShlcService service = new XtwhShlcService();
		XtwhShlcForm myForm = (XtwhShlcForm) form;
		String lcid = request.getParameter("lcid");
		HashMap<String, String> rs = service.getLcxx(lcid);
		// 流程所属岗位信息
		List<String[]> lcgwrs = service.getLcgwxx(lcid);
		String title = "流程展示";
		myForm.setMaxSize(new Integer(lcgwrs.size()).toString());
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("lcgwrs", lcgwrs);
		request.setAttribute("ssmkList", service.getSsmkList());
		return mapping.findForward("splcLczs");
	}

	// -------------------------------这是今天要中的比楼上更多的方法
	// end----------------------------------

	/**
	 * 修改审批流程
	 */
	public ActionForward splcUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhShlcService service = new XtwhShlcService();
		String lcid = request.getParameter("lcid");
		XtwhShlcForm myForm = (XtwhShlcForm) form;
		// User user=getUser(request);

		HashMap<String, String> rs = service.getLcxx(lcid);
		// 流程所属岗位信息
		List<String[]> lcgwrs = service.getLcgwxx(lcid);
		String doType = request.getParameter("doType");
		String message = "";// 提示信息
		if ("update".equalsIgnoreCase(doType)) {
			myForm.setLcmc(request.getParameter("mc"));
			String type = request.getParameter("type");				
			List<HashMap<String, String>> oldSpgwList = service.getSpgwAndGwz(lcid);//获取旧的审批岗位list			
			
			boolean blog = service.updateShlc(myForm,request,lcgwrs);			
		 //如果要初始化，通用功能改造（增加根据选择的数据范围库修改岗位人员）
			if(null != type){				
				if(blog){
					List<HashMap<String, String>> newSpgwList = service.getSpgwAndGwz(lcid);//获取旧的审批岗位list
					service.updateGwzyh(oldSpgwList, newSpgwList, lcid);
				}
			}
			message = blog ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		String title = "流程展示";
		request.setAttribute("title", title);

		myForm.setMaxSize(new Integer(lcgwrs.size()).toString());

		request.setAttribute("rs", rs);
		request.setAttribute("lcgwrs", lcgwrs);
		request.setAttribute("ssmkList", service.getSsmkList());

		return mapping.findForward("splcUpdate");

	}

}