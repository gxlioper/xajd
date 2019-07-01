package xgxt.gygl.zsjg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.gygl.cwgl.GyglCwglForm;
import xgxt.gygl.cwgl.GyglCwglInit;
import xgxt.gygl.cwgl.GyglCwglService;
import xgxt.gygl.gywh.GyglGywhForm;
import xgxt.gygl.gywh.GyglGywhService;
import xgxt.gygl.jbsz.GyglJbszForm;
import xgxt.gygl.jbsz.GyglJbszService;
import xgxt.gygl.qsgl.GyglQsglForm;
import xgxt.gygl.qsgl.GyglQsglInit;
import xgxt.gygl.qsgl.GyglQsglService;
import xgxt.gygl.tjfx.GyglTjfxForm;
import xgxt.gygl.tjfx.GyglTjfxService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_住宿结果-action类
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

public class GyglZsjgAction extends BasicAction {

	/**
	 * 公寓管理_住宿结果_历史信息维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglZsjgForm myForm = (GyglZsjgForm) form;
		GyglZsjgService service = new GyglZsjgService();
		GyglZsjgInit init = new GyglZsjgInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getLsxxRForm(rForm, myForm, request);

		// 是否查询操作
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		// =============== 执行删除操作 ===========
		if("del".equalsIgnoreCase(doType)){
			
			request.setAttribute("result", service.deleteLsxx(myForm));
			search=true;
		}
		
		// =============== 执行查询操作 ===========
		if (search) {
			rsArrList = (ArrayList<String[]>)service.getZslsxxList(myForm, request);
			request.setAttribute("searchTj", myForm.getSearchModel());
		} else {
			myForm.getPages().setMaxPage(1);
		}
		
		
		request.setAttribute("path", "gygl_zsjg_lsxx.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("lsxxManage");
	}
		
	/**
	 * 公寓管理_住宿结果_住宿信息统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zsxxTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglZsjgForm myForm=(GyglZsjgForm)form;
		GyglZsjgService service=new GyglZsjgService();
		GyglJbszService jbszService = new GyglJbszService();
		RequestForm rForm = new RequestForm();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgInit init=new GyglZsjgInit();
		User user = getUser(request);// 用户对象
		
		//分配对象
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		//结果集显示字段
		init.iniZstjRForm(rForm, myForm, request);
		String[] colList = rForm.getColList();
		
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		//是否查询操作
		
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		
		
		//if (search) {
			// 结果集
			myForm.getSearchModel().setPath("gygl_zsjg_zstj.do");
			rsArrList = (ArrayList<String[]>)service.getZdfpBmTjList(myForm, user, colList, request);
			request.setAttribute("searchTj", myForm.getSearchModel());
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}
		
		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("rsArrList", rsArrList);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zsxxTj");
	}
	
	/**
	 * 公寓管理_住宿结果_住宿信息维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglZsjgForm myForm = (GyglZsjgForm) form;
		GyglZsjgService service = new GyglZsjgService();
		GyglZsjgInit init = new GyglZsjgInit();
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getZsxxRForm(rForm, myForm, request);

		// 是否查询操作
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		
		// 存在校区
		String czxq = jbszModel.getCzxq();
		// 存在园区
		String czyq = jbszModel.getCzyq();
		// 从属关系
		String csgx = jbszModel.getCsgx();

		request.setAttribute("czxq", czxq);
		request.setAttribute("czyq", czyq);
		request.setAttribute("csgx", csgx);
		// ================= end =====================
		String[] colList = rForm.getColList();
		
		// =============== 执行勾选退宿操作 ===========
		if("xsts".equalsIgnoreCase(doType)){
			boolean result=service.xsts(myForm);
			request.setAttribute("result", result);
			search=true;
		}
		

		// =============== 执行批量退宿操作 ===========
		if("plxsts".equalsIgnoreCase(doType)){
			boolean result=service.plxsts(myForm);
			request.setAttribute("result", result);
			search=true;
		}
		
		
		//统计页面的勾选部门
		String[] pk = myForm.getPrimarykey_checkVal();
		if (pk != null && pk.length > 0) {
			service.setSearchModelValue(jbszModel.getFpdx(), myForm);
			search = true;
		}
		
		
		// =============== 执行查询操作 ===========
		//if (search) {
			myForm.getSearchModel().setPath("gygl_zsjg_zsxx.do");
			rsArrList = (ArrayList<String[]>)service.getZsxxList(myForm, user, colList, request);
			request.setAttribute("searchTj", myForm.getSearchModel());
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}
			
		request.setAttribute("path", "gygl_zsjg_zsxx.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("zsxxManage");
	}
	
	/**
	 * 公寓管理_住宿结果_寝室异动管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgForm myForm = (GyglZsjgForm) form;
		GyglZsjgService service = new GyglZsjgService();
		GyglZsjgInit init = new GyglZsjgInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getQsydRForm(rForm, myForm, request);
		String[] colList = rForm.getColList();
		// 是否查询操作
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		// =============== 执行删除操作 ===========
		if("del".equalsIgnoreCase(doType)){
			
			request.setAttribute("result", service.deleteLsxx(myForm));
			search=true;
		}
		
		//学生退宿
		if("xsts".equalsIgnoreCase(doType)){
			boolean result=service.xsts(myForm);
			request.setAttribute("result", result);
			search=true;
		}
		
		//统计页面的勾选部门
		String[] pk = myForm.getPrimarykey_checkVal();
		if (pk != null && pk.length > 0) {
			service.setSearchModelValue(jbszModel.getFpdx(), myForm);
			search = true;
		}
		// =============== 执行查询操作 ===========
		//if (search) {
			myForm.getSearchModel().setPath("gygl_zsjg_ydgl.do");
			rsArrList = (ArrayList<String[]>)service.getZsxxList(myForm, user, colList, request);
			request.setAttribute("searchTj", myForm.getSearchModel());
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}
		
		request.setAttribute("path", "gygl_zsjg_ydgl.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= 设置request的值 =============
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("ydglManage");
	}
	
	/**
	 * 公寓管理_住宿结果_学生床位异动
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscwyd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglCwglForm cwglForm=new GyglCwglForm();
		GyglZsjgForm myForm = (GyglZsjgForm) form;
		GyglZsjgService service = new GyglZsjgService();
		GyglCwglService cwglService=new GyglCwglService();
		GyglGywhService gywhService=new GyglGywhService();
		GyglZsjgInit init = new GyglZsjgInit();
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getQsydRForm(rForm, myForm, request);
		
		//分配对象
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		if("save".equalsIgnoreCase(rForm.getDoType())){
			
			boolean result=service.saveCwyd(myForm);
			request.setAttribute("result", result);
			rForm.setDoType("cwyd");
		}
		
		// =============== 执行床位异动 ===========
		//需要分配床位的学生
		if ("cwyd".equalsIgnoreCase(rForm.getDoType())){
			//需要分配床位的学生
			
			
			if(myForm.getCheckVal()==null || myForm.getCheckVal().length==0){
				myForm.setCheckVal(myForm.getXsxhArr());
			}
			
			cwglForm.setPkvArr(myForm.getCheckVal());
			List<HashMap<String,String>>fpcwxsList=cwglService.getFpcwXs(cwglForm);
			//楼栋信息列表
			List<HashMap<String,Object>>ldxxxxList=cwglService.getSsxxList(cwglForm);
			//所在楼栋信息
			GyglGywhForm gywhForm=new GyglGywhForm();
			gywhForm.setCs(null);
			gywhForm.setFpbj("一般");
			gywhForm.setFpbm("未分配");
			gywhForm.setFpdx(fpdx);
			gywhForm.setUser(user);
			List<HashMap<String,String>>qsxxList=gywhService.getQsfpList(gywhForm);
			request.setAttribute("qsxxList", qsxxList);
			request.setAttribute("ldxxxxList", ldxxxxList);
			request.setAttribute("fpcwxsList", fpcwxsList);
		}
		
		request.setAttribute("path", "gygl_zsjg_ydgl.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setRsArrList(rsArrList);
		// ================= end =====================
		
		request.setAttribute("fpdx", jbszModel.getFpdx());
		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("xscwyd");
	}
	
	/**
	 * 公寓管理_住宿结果_寝室异动统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ssydTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglZsjgForm myForm=(GyglZsjgForm)form;
		GyglZsjgService service=new GyglZsjgService();
		GyglJbszService jbszService = new GyglJbszService();
		RequestForm rForm = new RequestForm();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgInit init=new GyglZsjgInit();
		User user = getUser(request);// 用户对象
		
		//分配对象
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		//结果集显示字段
		init.iniSsydTjRForm(rForm, myForm, request);
		String[] colList = rForm.getColList();
		
		// 结果列表
		ArrayList<String[]> rsArrList = null;
			
		// 结果集
		myForm.getSearchModel().setPath("gygl_zsjg_ydtj.do");
		rsArrList = (ArrayList<String[]>) service.getSsydList(myForm, user,
				colList, request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		// } else {
		// myForm.getPages().setMaxPage(1);
		//		}
		
		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("rsArrList", rsArrList);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ssydTj");
	}
	
	/**
	 * 公寓管理_住宿结果_寝室异动查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ssydCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglZsjgForm myForm=(GyglZsjgForm)form;
		GyglZsjgService service=new GyglZsjgService();
		GyglJbszService jbszService = new GyglJbszService();
		RequestForm rForm = new RequestForm();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgInit init=new GyglZsjgInit();
		User user = getUser(request);// 用户对象
		
		//分配对象
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		//结果集显示字段
		init.iniSsydCxRForm(rForm, myForm, request);
		String[] colList = rForm.getColList();
		
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		//是否查询操作
		
		
		//统计页面的勾选部门
		String[] pk = myForm.getPrimarykey_checkVal();
		if (pk != null && pk.length > 0) {
			service.setSearchModelValue(jbszModel.getFpdx(), myForm);
		}
		
		// if (search) {
		// 结果集
		myForm.getSearchModel().setPath("gygl_zsjg_ydcx.do");
		rsArrList = (ArrayList<String[]>) service.getSsydcxList(myForm, user,
				colList, request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		// } else {
		// myForm.getPages().setMaxPage(1);
		//		}
		
		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("rsArrList", rsArrList);
		
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ssydCx");
	}
	
	/**
	 * 公寓管理_住宿结果_寝室异动管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kxssTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MessageResources message = getResources(request);
		
		GyglZsjgForm myForm=(GyglZsjgForm)form;
		GyglZsjgService service=new GyglZsjgService();
		//公寓基本设置
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		
		RequestForm rForm = new RequestForm();
		
		GyglZsjgInit init=new GyglZsjgInit();
		//用户对象
		User user = getUser(request);
		
		//分配对象
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		myForm.setCzxq(jbszModel.getCzxq());
		myForm.setCzyq(jbszModel.getCzyq());
		//结果集显示字段
		init.iniKxssTjForm(rForm, myForm, request,message);
		
		// 结果列表
		ArrayList<String[]> rs = null;
		String[]colList=rForm.getColList();
		
		//是否查询操作
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		
		//查询操作
		//if (search) {
			myForm.getSearchModel().setPath("gygl_zsjg_kxss.do");
			rs = (ArrayList<String[]>)service.getKxqsList(myForm, user, colList, request,"cx");
			request.setAttribute("searchTj", myForm.getSearchModel());
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}
		
		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("rs", rs);
		request.setAttribute("czxq", myForm.getCzxq());
		request.setAttribute("czyq", myForm.getCzyq());
		request.setAttribute("path", "gygl_zsjg_kxss.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("kxssTj");
	}
	
	/**
	 * 公寓管理_住宿结果_空寝室信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kqsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MessageResources message = getResources(request);
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgService service=new GyglZsjgService();
		GyglZsjgForm myForm=(GyglZsjgForm)form;
		GyglZsjgInit init=new GyglZsjgInit();
		RequestForm rForm = new RequestForm();
		User user=new User();
		
		
		String lddm=request.getParameter("lddm");
		myForm.setLddm(lddm);
		//结果集显示字段
		init.iniKqsxxForm(rForm, myForm, request,message);
		
		myForm.setFpdx(jbszModel.getFpdx());
		
		List<String[]>rs=new ArrayList<String[]>();
		rs=service.getKqsxx(myForm, user, rForm.getColList(), request);
		
		request.setAttribute("lddm", lddm);
		request.setAttribute("ldMap", service.getLdMap(myForm));
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", rForm.getTopTr());
		return mapping.findForward("kqsxx");
	}
	
	/**
	 * 公寓管理_住宿结果_空寝室信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xqsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MessageResources message = getResources(request);
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgService service=new GyglZsjgService();
		GyglZsjgForm myForm=(GyglZsjgForm)form;
		GyglZsjgInit init=new GyglZsjgInit();
		RequestForm rForm = new RequestForm();
		User user=new User();
		
		String lddm=request.getParameter("lddm");
		myForm.setLddm(lddm);
		//结果集显示字段
		init.iniXqsxxForm(rForm, myForm, request,message);
		
		myForm.setFpdx(jbszModel.getFpdx());
		
		List<String[]>rs=new ArrayList<String[]>();
		rs=service.getXqsxx(myForm, user, rForm.getColList(), request);
		
		request.setAttribute("lddm", lddm);
		request.setAttribute("ldMap", service.getLdMap(myForm));
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", rForm.getTopTr());
		return mapping.findForward("xqsxx");
	}
	
	/** 
	 * Method printNjfbtj
	 * 宿舍年级分布情况统计导出表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * 
	 */
	public ActionForward printKxss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageResources message = getResources(request);
		GyglZsjgService service=new GyglZsjgService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgForm myForm = (GyglZsjgForm) form;
		
		//分配对象
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/gygl/gygl_ssnjfbtj.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		
		RequestForm rForm = new RequestForm();
		myForm.getSearchModel().setPath("gygl_zsjg_kxss.do");
		GyglZsjgInit init=new GyglZsjgInit();
		
		String dclx=request.getParameter("dclx");
		myForm.setDclx(dclx);
		if("kxqs".equalsIgnoreCase(myForm.getDclx())){
			init.iniKxssTjForm(rForm, myForm, request,message);
		}else if("kqs".equalsIgnoreCase(myForm.getDclx())){
			init.iniKqsxxForm(rForm, myForm, request,message);
		}else if("xqs".equalsIgnoreCase(myForm.getDclx())){
			init.iniXqsxxForm(rForm, myForm, request,message);
		}
		
		service.printKxss(myForm, request, wwb,rForm);
		return null;
	}
		
	// ================以下 Made By 伟大的骆=====================
	/**
	 * 公寓管理_住宿结果_寝室入住情况
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qsrzqkCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglZsjgForm myForm = (GyglZsjgForm) form;
		GyglZsjgService service = new GyglZsjgService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglZsjgInit init = new GyglZsjgInit();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		// 获得公寓设置相关信息
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		String sfsz = jbszModel.getSfsz();
		
		myForm.setFpdx(jbszModel.getFpdx());
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
		init.getQsrzqkRForm(rForm, myForm, request);

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

		// =============== 执行导出操作 ===============
		if ("exp".equalsIgnoreCase(doType)) {
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.expQsrzqkToExcel(myForm, user, request, response.getOutputStream());

			return null;
		}
		// ================= end =====================
		
		// =============== 执行查询操作 ===========

		// 床位信息列表
		List<HashMap<String, String>> cwInfoList = service
				.getQsrzCwList(service.getMaxCws());
		request.setAttribute("cwInfoList", cwInfoList);

		// 结果集
		rsArrList = service.getQsInfoList(myForm, user, colList, request);
		List<HashMap<String, Object>> qsrzInfoList = service
				.getQsrzInfoList(myForm,rsArrList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("qsrzInfoList", qsrzInfoList);

		rForm.setRsArrList(rsArrList);

		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qsrzqkCx");
	}

	// =================以上 Made By 伟大的骆==================
}
