package xgxt.gygl.qsgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.gygl.jbsz.GyglJbszForm;
import xgxt.gygl.jbsz.GyglJbszService;

import com.zfsoft.basic.BasicAction;
import common.exception.SystemException;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_寝室管理-action类
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

public class GyglQsglAction extends BasicAction {

	/**
	 * 公寓管理_寝室管理_寝室自动分配
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qszdfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		String sfsz = jbszModel.getSfsz();

		// 未进入基本设置模块对公寓进行基本设置
		if ("否".equalsIgnoreCase(sfsz)) {
			String msg = "公寓基本设置尚未完成，无法进行接下去的操作，请联系管理员或相关人员对其进行设置，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			// 存在校区
			String czxq = jbszModel.getCzxq();
			// 存在园区
			String czyq = jbszModel.getCzyq();
			// 从属关系
			String csgx = jbszModel.getCsgx();

			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);
			request.setAttribute("csgx", csgx);
		}

		RequestForm rForm = new RequestForm();

		// 分配对象
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);

		init.getQszdfpRForm(rForm, myForm, request);

		// 是否查询操作
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 结果集显示字段
		String[] colList = rForm.getColList();
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		// =============== 执行保存操作 ===============
		if ("save".equalsIgnoreCase(doType)) {
			// 根据寝室信息创建床位信息
			//service.creatGyglCwxx(myForm, user);
			// 执行自动分配寝室
			String message = service.saveQsfpAuto(myForm, user);
			// service.saveQszdfp(myForm, user);

			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============== 执行查询操作 ===========
		if (search) {
			// 结果集
			rsArrList = service.getZdfpBmList(myForm, user, colList, request);
			request.setAttribute("searchTj", myForm.getSearchModel());

			// 未分配寝室数
			String wfpQsNum = service.getWfpQsNum();
			request.setAttribute("wfpQsNum", wfpQsNum);
		} else {
			myForm.getPages().setMaxPage(1);
		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qszdfp");
	}

	/**
	 * 公寓管理_寝室管理_寝室自动分配(结果)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qszdfpjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		String sfsz = jbszModel.getSfsz();

		// 未进入基本设置模块对公寓进行基本设置
		if ("否".equalsIgnoreCase(sfsz)) {
			String msg = "公寓基本设置尚未完成，无法进行接下去的操作，请联系管理员或相关人员对其进行设置，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			// 存在校区
			String czxq = jbszModel.getCzxq();
			// 存在园区
			String czyq = jbszModel.getCzyq();
			// 从属关系
			String csgx = jbszModel.getCsgx();

			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);
			request.setAttribute("csgx", csgx);
		}

		RequestForm rForm = new RequestForm();

		// 分配对象
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);

		init.getQszdfpjgRForm(rForm, myForm, request);

		// 是否查询操作
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 结果集显示字段
		String[] colList = rForm.getColList();
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		// =============== 执行取消分配操作 ===============
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.cancelQsfp(myForm,request);
			String message = flag ? MessageInfo.MESSAGE_CANCEL_SUCCESS
					: MessageInfo.MESSAGE_CANCEL_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================

		service.setSearchModel(myForm, request);
		
		// =============== 执行查询操作 ===========
		if (search) {
			// 结果集
			rsArrList = service.getZdfpjgList(myForm, user, colList, request);
			request.setAttribute("searchTj", myForm.getSearchModel());
			
			if(rsArrList!=null && rsArrList.size()>0){
				rsArrList=service.getResultList(rsArrList, myForm.getPages());
			}
		} else {
			myForm.getPages().setMaxPage(1);
		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qszdfpjg");
	}

	/**
	 * 公寓管理_寝室管理_寝室手动分配
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qssdfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		String sfsz = jbszModel.getSfsz();

		// 未进入基本设置模块对公寓进行基本设置
		if ("否".equalsIgnoreCase(sfsz)) {
			String msg = "公寓基本设置尚未完成，无法进行接下去的操作，请联系管理员或相关人员对其进行设置，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			// 存在校区
			String czxq = jbszModel.getCzxq();
			// 存在园区
			String czyq = jbszModel.getCzyq();
			// 从属关系
			String csgx = jbszModel.getCsgx();

			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);
			request.setAttribute("csgx", csgx);
		}

		RequestForm rForm = new RequestForm();

		// 分配对象
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		
		init.getQssdfpRForm(rForm, myForm, request);

		// 是否查询操作
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 结果集显示字段
		String[] colList = rForm.getColList();
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		// =============== 执行取消分配操作 ===============
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.cancelQsfp(myForm,request);
			String message = flag ? MessageInfo.MESSAGE_CANCEL_SUCCESS
					: MessageInfo.MESSAGE_CANCEL_FALSE;
			rForm.setMessage(message);
			myForm.getSearchModel().setSearch_tj_rzzt(null);
			search = true;
		}
		// ================= end =====================
		
		// =============== 判断统计页面的情况 ===============
		// 统计页面的勾选部门
		String[] pk = myForm.getPrimarykey_checkVal();
		if (pk != null && pk.length > 0 
				&& !"del".equalsIgnoreCase(doType)
				&& !"return".equalsIgnoreCase(doType)) {
			service.setSearchModelValue(fpdx, myForm);
			search = true;
		}
		// ================= end =====================
		
		// =============== 执行查询操作 ===========
		//if (search) {
			// 结果集
			rsArrList = service.getSdfpjgList(myForm, user, colList, request);
			
			// 入住状态（单独处理）
			myForm.getSearchModel().setSearch_tj_rzzt(myForm.getRzzt());
			request.setAttribute("searchTj", myForm.getSearchModel());
			
//			if (rsArrList != null && rsArrList.size() > 0) {
//				rsArrList = service.getResultList(rsArrList, myForm.getPages());
//			}
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qssdfp");
	}
	
	/**
	 * 公寓管理_寝室管理_寝室手动分配(详细)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qssdfpdDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
		RequestForm rForm = new RequestForm();

		// 分配对象
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		// 从属关系
		String csgx = jbszModel.getCsgx();

		init.getQssdfpRForm(rForm, myForm, request);

		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();

		// 宿舍编号
		String[] ssbh = myForm.getPrimarykey_checkVal();
		request.setAttribute("ssbh", ssbh);
		// ================= end =====================

		// =============== 保存手动分配 ===============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveQssdfp(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =============== 获得分配对象列表 ===============
		List<HashMap<String, Object>> fpdxList = service.getFpdxList(myForm,
				jbszModel, user);
		request.setAttribute("fpdxList", fpdxList);
		// ================= end =====================

		// =============== 获得公寓结构列表 ===============
		List<HashMap<String, Object>> ldInfoList = service.getGyjgList(myForm,
				user);
		request.setAttribute("csgx", csgx);
		request.setAttribute("ldInfoList", ldInfoList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qssdfpdDetail");
	}

	/**
	 * 公寓管理_寝室管理_寝室分配结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qsfpjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
		String sfsz = jbszModel.getSfsz();

		// 未进入基本设置模块对公寓进行基本设置
		if ("否".equalsIgnoreCase(sfsz)) {
			String msg = "公寓基本设置尚未完成，无法进行接下去的操作，请联系管理员或相关人员对其进行设置，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			// 存在校区
			String czxq = jbszModel.getCzxq();
			// 存在园区
			String czyq = jbszModel.getCzyq();
			// 从属关系
			String csgx = jbszModel.getCsgx();

			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);
			request.setAttribute("csgx", csgx);
		}

		RequestForm rForm = new RequestForm();

		// 分配对象
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);

		init.getQsfpjgRForm(rForm, myForm, request);

		// 是否查询操作
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 结果集显示字段
		String[] colList = rForm.getColList();
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================
		
		// =============== 判断统计页面的情况 ===============
		// 统计页面的勾选部门
		String[] pk = myForm.getPrimarykey_checkVal();
		if (pk != null && pk.length > 0 && !"del".equalsIgnoreCase(doType)) {
			service.setSearchModelValue(fpdx, myForm);
			search = true;
		}
		// ================= end =====================
		
		// =============== 执行取消分配操作 ===============
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.cancelQsfp(myForm, request);
			String message = flag ? MessageInfo.MESSAGE_CANCEL_SUCCESS
					: MessageInfo.MESSAGE_CANCEL_FALSE;
			rForm.setMessage(message);
			myForm.getSearchModel().setSearch_tj_rzzt(null);
			search = true;
		}
		// ================= end =====================
		
		// =============== 执行查询操作 ===========
		//if (search) {
			// 结果集
			rsArrList = service.getSdfpjgList(myForm, user, colList, request);
			// 入住状态（单独处理）
			myForm.getSearchModel().setSearch_tj_rzzt(myForm.getRzzt());
			request.setAttribute("searchTj", myForm.getSearchModel());
			
			//if(rsArrList!=null && rsArrList.size()>0){
				//rsArrList=service.getResultList(rsArrList, myForm.getPages());
			//}else{
			//	myForm.getPages().setMaxPage(1);
			//}
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qsfpjg");
	}

	/**
	 * 公寓管理_寝室管理_寝室分配统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qsfptj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
		String sfsz = jbszModel.getSfsz();

		// 未进入基本设置模块对公寓进行基本设置
		if ("否".equalsIgnoreCase(sfsz)) {
			String msg = "公寓基本设置尚未完成，无法进行接下去的操作，请联系管理员或相关人员对其进行设置，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			// 存在校区
			String czxq = jbszModel.getCzxq();
			// 存在园区
			String czyq = jbszModel.getCzyq();
			// 从属关系
			String csgx = jbszModel.getCsgx();
			
			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);
			request.setAttribute("csgx", csgx);
		}

		RequestForm rForm = new RequestForm();

		// 分配对象
		String fpdx = jbszModel.getFpdx();
		// 分配方式
		String fpfs = jbszModel.getFpfs();
		request.setAttribute("fpfs", fpfs);
		
		// 分配方式为：学校->学院->班级
		if ("2".equalsIgnoreCase(fpfs)
				&& "xx".equalsIgnoreCase(user.getUserStatus())) {
			fpdx = "xy";
			myForm.setKffp("no");
		}else{
			myForm.setKffp("yes");
		}
		myForm.setFpdx(fpdx);

		init.getQsfpRForm(rForm, myForm, request);
		
		// 公寓管理员权限
		rForm.setGyglyQx(user.getGyglyQx());
		rForm.setUserName(user.getUserName());
		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 结果集显示字段
		String[] colList = rForm.getColList();
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================
		
		// =============== 判断是否分配页面返回 ===============
		// 返回
		String mklx = request.getParameter("mklx");
		if ("fh".equalsIgnoreCase(mklx)) {
			SearchModel searchModel = new SearchModel();
			myForm.setSearchModel(searchModel);
		}
		// ================= end =====================

		// =============== 执行保存操作 ===============
		if ("save".equalsIgnoreCase(doType)) {
			// 执行自动分配寝室
			String message = service.saveQsfpAuto(myForm, user);

			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =============== 执行查询操作 ===========
		//if (search) {
			// 结果集
			try {
				rsArrList = service.getQsfpTjList(myForm, user, colList, request);
			} catch (SystemException e) {
				// TODO: handle exception
				catchSystemException(request, e);
			}
			
			request.setAttribute("searchTj", myForm.getSearchModel());

		//} else {
		//	myForm.getPages().setMaxPage(1);
		//}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		List<HashMap<String, String>> ldQsList = service.getLdForWfpQssList(user);
		request.setAttribute("ldQsList", ldQsList);
		// =================== end ===================
		
		return mapping.findForward("qsfptj");
	}
	
	/**
	 * 公寓管理_寝室管理_寝室手动分配
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qssdfpByHand(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
		RequestForm rForm = new RequestForm();

		// 分配对象
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		// 从属关系
		String csgx = jbszModel.getCsgx();

		init.getQssdfpRForm(rForm, myForm, request);

		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();

		// 宿舍编号
		String[] ssbh = myForm.getPrimarykey_checkVal();
		request.setAttribute("ssbh", ssbh);
		
		// 分配方式
		String fpfs = jbszModel.getFpfs();
		// 分配方式为：学校->学院->班级
		if ("2".equalsIgnoreCase(fpfs)
				&& "xx".equalsIgnoreCase(user.getUserStatus())) {
			fpdx = "xy";
			myForm.setKffp("no");
		} else {
			myForm.setKffp("yes");
		}
		myForm.setFpdx(fpdx);
		request.setAttribute("fpfs", fpfs);
		request.setAttribute("fpdx", fpdx);
		// ================= end =====================

		// =============== 保存手动分配 ===============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveQssdfp(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =============== 获得分配对象列表 ===============
		List<HashMap<String, Object>> fpdxList = service.getFpdxByQssdfp(myForm);
		request.setAttribute("fpdxList", fpdxList);
		// ================= end =====================

		// =============== 获得公寓结构列表 ===============
		myForm.setWfpqs("yes");
		myForm.setPrimarykey_checkVal(null);
		List<HashMap<String, Object>> ldInfoList = service.getGyjgList(myForm,
				user);
		request.setAttribute("csgx", csgx);
		request.setAttribute("ldInfoList", ldInfoList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qssdfpByHand");
	}
}
