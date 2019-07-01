package xsgzgl.pjpy.szgyyq.mypj.tea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.FileManage;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xsgzgl.pjpy.szgyyq.model.FivesModel;
import xsgzgl.pjpy.szgyyq.model.XsssModel;
import xsgzgl.pjpy.szgyyq.model.XstsModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjInit;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(老师)_Action类
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

public class PjpyTeaAction extends BasicAction {

	/**
	 * 评奖评优_苏州工业园区_5S分维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fivesManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initFiveS(rForm, myForm, request);

		String[] xn = new String[] { Base.currXn };
		String[] xq = new String[] { Base.currXq };

		myForm.getSearchModel().setPath("pjpy_szgyyq_fives.do");
		myForm.getSearchModel().setSearch_tj_xn(xn);
		myForm.getSearchModel().setSearch_tj_xq(xq);
		// =================== end ===================

		// ============= 设置request的值 =============
		request.setAttribute("searchTj", myForm.getSearchModel());
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("fivesManage");
	}

	/**
	 * 获得5S分列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getFivesInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		PjpyMypjInit init = new PjpyMypjInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initFiveS(rForm, myForm, request);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// 学号
		String xh = user.getUserName();
		myForm.setXh(xh);

		// 查询类型
		String mhcx_lx = otherValue[0];

		// 模糊查询
		String input_mhcx = service.unicode2Gbk(otherValue[1]);

		// 学年
		String[] xn = null;

		if (!Base.isNull(otherValue[2].trim())) {
			xn = otherValue[2].split("!!##!!");
		} else {
			xn = new String[] { Base.currXn };
		}

		// 学期
		String[] xq = null;

		if (!Base.isNull(otherValue[3].trim())) {
			xq = otherValue[3].split("!!##!!");
		} else {
			xq = new String[] { Base.currXq };
		}

		// 操作
		// String[] cz = null;
		// if(!Base.isNull(otherValue[3].trim())){
		// cz = otherValue[3].split("!!##!!");
		// }
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setSearch_tj_xn(xn);
		searchModel.setSearch_tj_xq(xq);
		if("bz".equalsIgnoreCase(myForm.getYhlx())){
			searchModel.setMhcx_lx(mhcx_lx);
			searchModel.setInput_mhcx(input_mhcx);
		}
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		ArrayList<String[]> rsArrList = service.getFivesInfoList(myForm, user);
		String spHtml = service.getFivesHtml(rsModel, myForm, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("yes");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 评奖评优_苏州工业园区_5S分详细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fivesDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		XsxxglService stuService = new XsxxglService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initFiveSDetail(rForm, myForm, request);

		String xh = myForm.getXh();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		request.setAttribute("stuInfo", stuInfo);

		// 申请信息
		List<HashMap<String, String>> infoList = service.getFivesList(myForm);
		request.setAttribute("infoList", infoList);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);

		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", xqmc);
		request.setAttribute("xqmc", xqmc);
		// =================== end ===================

		return mapping.findForward("fivesDetail");
	}

	/**
	 * 保存5S分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveFives(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// 用户对象
		FivesModel fivesModel = new FivesModel();

		// 学号
		String xh = request.getParameter("xh");
		fivesModel.setXh(xh);

		// 学年
		String xn = Base.currXn;
		fivesModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		fivesModel.setXq(xq);

		// ID
		String[] id = request.getParameter("id").split("!!@@!!");
		fivesModel.setId(id);

		// 分值项目
		String[] fzxm = request.getParameter("fzxm").split("!!@@!!");
		fivesModel.setFzxm(fzxm);

		// 加减分
		String[] jjf = request.getParameter("jjf").split("!!@@!!");
		fivesModel.setJjf(jjf);

		// 申请分
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		fivesModel.setSqf(sqf);

		// 加分日期
		String[] jfrq = request.getParameter("jfrq").split("!!@@!!");
		fivesModel.setJfrq(jfrq);

		// 原因
		String[] yy = request.getParameter("yy").split("!!@@!!");
		fivesModel.setYy(yy);

		// 加分原因
		String[] jfyy = request.getParameter("jfyy").split("!!@@!!");
		fivesModel.setJfyy(jfyy);

		// ==================执行保存操作========================
		myForm.setFivesModel(fivesModel);
		boolean flag = service.saveFives(myForm, user, request);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 删除5S分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delFives(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// 用户对象
		FivesModel fivesModel = new FivesModel();

		// ID
		String[] id = request.getParameter("id").split("!!@@!!");
		fivesModel.setId(id);

		// ==================执行保存操作========================
		myForm.setFivesModel(fivesModel);
		boolean flag = service.delFives(myForm, user);
		String message = flag ? "删除成功" : "删除失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 评奖评优_苏州工业园区_分数审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();
		SearchModel searchModel = myForm.getSearchModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initFssh(rForm, myForm, request);

		// 初始化项目列表
		List<HashMap<String, String>> cshXmList = service.getFsshXmList(myForm);
		request.setAttribute("cshXmList", cshXmList);

		// 学年
		searchModel.setPath("pjpy_szgyyq_fssh.do");
		String[] xn = new String[] { Base.currXn };
		searchModel.setSearch_tj_xn(xn);

		// 学期
		String[] xq = new String[] { Base.currXq };
		searchModel.setSearch_tj_xq(xq);
		
		//审核条件
		String shtj = request.getParameter("shtj");
		
		if ("xsh".equalsIgnoreCase(shtj)) {// 需审核
			// 审核状态类型
			String[] shztlx = new String[] { "存在未审核", "存在需重审" };
			searchModel.setSearch_tj_shztlx(shztlx);
		} else if ("ss".equalsIgnoreCase(shtj)) {
			// 审核状态类型
			String[] ss = new String[] { "是" };
			searchModel.setSearch_tj_sf(ss);
		} else if ("ts".equalsIgnoreCase(shtj)) {
			// 审核状态类型
			String[] ts = new String[] { "是" };
			searchModel.setSearch_tj_sftj(ts);
		}
		// =================== end ===================

		// ============= 设置request的值 =============
		request.setAttribute("searchTj", searchModel);
		service.setRequestValue(rForm, request);
		
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		// =================== end ===================

		return mapping.findForward("fsshManage");
	}

	/**
	 * 获得各个项目的分数审核列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getFsshInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		PjpyMypjInit init = new PjpyMypjInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// 查询类型
		String mhcx_lx = otherValue[0];

		// 模糊查询
		String input_mhcx = service.unicode2Gbk(otherValue[1]);

		// 项目代码
		String xmdm = otherValue[2];
		myForm.setXmdm(xmdm);

		// 学号
		String xh = user.getUserName();
		myForm.setXh(xh);

		// 学年
		String xn = otherValue[3];

		if (!Base.isNull(otherValue[3].trim())) {
			xn = otherValue[3].split("!!##!!")[0];
		} else {
			xn = Base.currXn;
		}

		// 学期
		String xq = otherValue[4];

		if (!Base.isNull(otherValue[4].trim())) {
			xq = otherValue[4].split("!!##!!")[0];
		} else {
			xq = Base.currXq;
		}

		// 初始化
		init.initFssh(rForm, myForm, request);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setSearch_tj_xn(new String[] { xn });
		searchModel.setSearch_tj_xq(new String[] { xq });

		if ("bz".equalsIgnoreCase(myForm.getYhlx())) {
			searchModel.setMhcx_lx(mhcx_lx);
			searchModel.setInput_mhcx(input_mhcx);
		}
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		ArrayList<String[]> rsArrList = service.getFsshList(myForm, user);
		request.setAttribute("searchTj", searchModel);
		String spHtml = service.getFsshHtml(rsModel, myForm, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("yes");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 评奖评优_苏州工业园区_分数审核详细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		XsxxglService stuService = new XsxxglService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initFsshDetail(rForm, myForm, request);

		// 学号
		String xh = myForm.getXh();

		// 学年
		String xn = Base.currXn;
		myForm.setXn(xn);

		// 学期
		String xq = Base.currXq;
		myForm.setXq(xq);

		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		request.setAttribute("stuInfo", stuInfo);

		// 申请信息
		List<HashMap<String, String>> infoList = service.getFsshInfoList(
				myForm, user);
		request.setAttribute("infoList", infoList);
		request.setAttribute("zhcpsd", service.getZhcpsdInfo(myForm));
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("fsshDetail");
	}

	/**
	 * 保存审核分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveShf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// 用户对象

		// 项目代码
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// 用户类型
		String yhlx = request.getParameter("yhlx");
		myForm.setYhlx(yhlx);

		// ID
		String[] id = request.getParameter("id").split("!!@@!!");
		myForm.setId(id);

		// 班主任审核分
		String[] bzrshf = null;
		if (!Base.isNull(request.getParameter("bzrshf"))) {
			bzrshf = request.getParameter("bzrshf").split("!!@@!!");
		}
		myForm.setBzrshf(bzrshf);

		// 学院审核分
		String[] xyshf = null;
		if (!Base.isNull(request.getParameter("xyshf"))) {
			xyshf = request.getParameter("xyshf").split("!!@@!!");
		}
		myForm.setXyshf(xyshf);

		// 学校审核分
		String[] xxshf = null;
		if (!Base.isNull(request.getParameter("xxshf"))) {
			xxshf = request.getParameter("xxshf").split("!!@@!!");
		}
		myForm.setXxshf(xxshf);

		// ==================执行保存操作========================
		boolean flag = service.saveShf(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 保存审核状态
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveShzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// 用户对象

		// 项目代码
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// 用户类型
		String yhlx = request.getParameter("yhlx");
		myForm.setYhlx(yhlx);

		// ID
		String[] id = request.getParameter("id").split("!!@@!!");
		myForm.setId(id);

		// 班主任审核分
		String[] bzrshf = null;
		if (!Base.isNull(request.getParameter("bzrshf"))) {
			bzrshf = request.getParameter("bzrshf").split("!!@@!!");
		}
		myForm.setBzrshf(bzrshf);

		// 学院审核分
		String[] xyshf = null;
		if (!Base.isNull(request.getParameter("xyshf"))) {
			xyshf = request.getParameter("xyshf").split("!!@@!!");
		}
		myForm.setXyshf(xyshf);

		// 学校审核分
		String[] xxshf = null;
		if (!Base.isNull(request.getParameter("xxshf"))) {
			xxshf = request.getParameter("xxshf").split("!!@@!!");
		}
		myForm.setXxshf(xxshf);

		// 审核状态
		String shzt = request.getParameter("shzt");
		myForm.setShzt(shzt);
		// ==================执行审核操作========================
		boolean flag = service.saveShzt(myForm, user);
		String message = flag ? "审核成功" : "审核失败，请联系相关人员";
		// ==================执行审核操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 保存批量审核状态
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savePlShzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// 用户对象

		// 项目代码
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// 用户类型
		String yhlx = request.getParameter("yhlx");
		myForm.setYhlx(yhlx);

		// 审核学号
		String[] shxh = request.getParameter("xh").split("!!@@!!");
		myForm.setShxh(shxh);

		// 审核状态
		String shzt = request.getParameter("shzt");
		myForm.setShzt(shzt);
		// ==================执行审核操作========================
		boolean flag = service.savePlShzt(myForm, user);
		String message = flag ? "审核成功<br/>注：仅处理后一级别<font color='blue'>未审核</font>的记录。" : "审核失败，请联系相关人员";
		// ==================执行审核操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 保存申诉处理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSscl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// 用户对象
		XsssModel xsssModel = new XsssModel();

		// 学年
		String xn = request.getParameter("xn");
		xsssModel.setXn(xn);

		// 学期
		String xq = request.getParameter("xq");
		xsssModel.setXq(xq);

		// 项目类型
		String xmlx = request.getParameter("xmlx");
		xsssModel.setXmlx(xmlx);

		// 学号
		String xh = request.getParameter("xh");
		xsssModel.setXh(xh);

		// 项目ID
		String xmid = request.getParameter("xmid");
		xsssModel.setXmid(xmid);

		// 处理意见
		String clyj = service.unicode2Gbk(request.getParameter("clyj"));
		xsssModel.setClyj(clyj);

		// ==================执行保存操作========================
		myForm.setXsssModel(xsssModel);
		boolean flag = service.saveSscl(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 保存投诉处理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTscl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// 用户对象
		XstsModel xstsModel = new XstsModel();

		// 学年
		String xn = request.getParameter("xn");
		xstsModel.setXn(xn);

		// 学期
		String xq = request.getParameter("xq");
		xstsModel.setXq(xq);

		// 项目类型
		String xmlx = request.getParameter("xmlx");
		xstsModel.setXmlx(xmlx);

		// 学号
		String xh = request.getParameter("tsr");
		xstsModel.setXh(xh);

		// 被投诉人
		String btsr = request.getParameter("btsr");
		xstsModel.setBtsr(btsr);

		// 处理意见
		String clyj = service.unicode2Gbk(request.getParameter("clyj"));
		xstsModel.setClyj(clyj);

		// ==================执行保存操作========================
		myForm.setXstsModel(xstsModel);
		boolean flag = service.saveTscl(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ==================以上Made By 伟大的骆======================

	/**
	 * 综合测评
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author gaobb
	 */
	public ActionForward zhcpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		PjpyTeaService service = new PjpyTeaService();
		PjpyTeaForm myForm = (PjpyTeaForm) form;
		User user=getUser(request);

		String yhlx=(String)session.getAttribute("yhlx");
		
		myForm.setYhlx(yhlx);
		SearchModel searchModel = myForm.getSearchModel();
		searchModel.setPath("pjpy_szgyyq_zhcp.do");
		// 学年
		if (searchModel.getSearch_tj_xn() == null
				|| searchModel.getSearch_tj_xn().length == 0) {
			String[] xn = new String[] { Base.currXn };
			searchModel.setSearch_tj_xn(xn);
		}
		// 学期
		if (searchModel.getSearch_tj_xq() == null
				|| searchModel.getSearch_tj_xq().length == 0) {
			String[] xq = new String[] { Base.currXq };
			searchModel.setSearch_tj_xq(xq);
		}
		String doType = request.getParameter("doType");
		if ("jsfspm".equals(doType)) {// 计算分数排名
		// Long s=System.currentTimeMillis();
			boolean b = service.jsfspm(myForm, request);
			if (b) {
				request.setAttribute("message", "计算成功！");
			} else {
				request.setAttribute("message", "计算失败！");
			}
			// Long e=System.currentTimeMillis();
			// System.out.println((e-s)/1000);
		} else if ("export".equals(doType)) {// 导出综合测评班级汇总表数据
			String[] bjdm = searchModel.getSearch_tj_bj();
			if (bjdm != null && bjdm.length == 1) {
				myForm.setBjdm(bjdm[0]);
			}
			boolean b = service.exportZhcpBjhzb(myForm, response);
			if(b){
				return null;
			}else{
				request.setAttribute("yhInfo", "请选择班级后再进行导出！");
				return new ActionForward("/yhInfo.do");
			}
		}

		// =================== end ===================

		// ============= 设置request的值 =============
		request.setAttribute("rs", service.getZhcpList(myForm, user));

		request.setAttribute("searchTj", searchModel);

		// write和titile

		request.setAttribute("topTr", service.getTopTr("zhcpManage"));
		request.setAttribute("realTable", "szgy_zhszcphzlsb"); // 导入表
		request.setAttribute("tableName", "xg_view_szgy_zhszcphzlsb"); // 导出视图

		request.setAttribute("path", "pjpy_szgyyq_zhcp.do");
		FormModleCommon.setNdXnXqList(request);

		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjList(request);
		
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);

		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", xqmc);
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("yhlx", yhlx);

		return mapping.findForward("zhcpManage");
	}

	/**
	 * 评奖评优_苏州工业园区_结果查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jgcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = new PjpyStuForm();
		BeanUtils.copyProperties(myForm, form);
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();
		SearchModel searchModel = myForm.getSearchModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initJgcx(rForm, myForm, request);

		// 初始化项目列表
		List<HashMap<String, String>> cshXmList = service.getJgcxXmList(myForm);
		// cshXmList.remove(cshXmList.size()-1);
		// cshXmList.remove(cshXmList.size()-1);//移除5S
		// 5S分
		// HashMap<String,String> map = new HashMap<String, String>();
		// map.put("xmdm", "szyc_5sb");
		// map.put("xmmc", "5S");
		// cshXmList.remove(map);//移除5S
		//
		// // 综合素质测分
		// map = new HashMap<String, String>();
		// map.put("xmdm", "szgy_zhszcphzlsb");
		// map.put("xmmc", "综合素质测分");
		// cshXmList.remove(map);//移除综测

		request.setAttribute("cshXmList", cshXmList);

		// 学年
		String[] xn = new String[] { Base.currXn };
		searchModel.setSearch_tj_xn(xn);

		// 学期
		String[] xq = new String[] { Base.currXq };
		searchModel.setSearch_tj_xq(xq);
		// =================== end ===================

		// ============= 设置request的值 =============
		request.setAttribute("searchTj", searchModel);
		service.setRequestValue(rForm, request);
		// =================== end ===================
		request.setAttribute("realTable", "szyq_ivtltb");
		request.setAttribute("tableName", "szyq_ivtltb");
		request.setAttribute("path", "pjpy_szgyyq_teajgcx.do");

		return mapping.findForward("jgcxManage");
	}

	/**
	 * 获得各个项目的结果查询列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJgcxInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyStuForm myForm = new PjpyStuForm();
		BeanUtils.copyProperties(myForm, form);
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		String path = "pjpy_szgyyq_teajgcx.do";
		searchModel.setPath(path);
		// ==================高级查询相关 end========================

		// ============= 初始化各变量的值 ============
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// 项目代码
		String xmdm = otherValue[0];
		myForm.setXmdm(xmdm);

		// 初始化
		init.initJgcx(rForm, myForm, request);

		// 学年
		String[] xn = searchModel.getSearch_tj_xn();

		if (xn != null && xn.length > 0) {

		} else {
			xn = new String[] { Base.currXn };
		}
		searchModel.setSearch_tj_xn(xn);

		// 学期
		String[] xq = searchModel.getSearch_tj_xq();

		if (xq != null && xq.length > 0) {

		} else {
			xq = new String[] { Base.currXq };
		}
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================显示内容========================
		myForm.setSearchModel(searchModel);
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		ArrayList<String[]> rsArrList = service.getJgcxList(myForm, user);
		String spHtml = service.getJgcxHtml(rsModel, myForm, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 导出Ivt论坛
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author gaobb
	 */
	public ActionForward exportIvtlt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTeaForm model = (PjpyTeaForm)form;
		SearchModel searchModel=model.getSearchModel();
		searchModel.setSearch_tj_xn(new String[]{});
		searchModel.setSearch_tj_xq(new String[]{});
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(searchModel);
		
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
		response.setContentType("application/vnd.ms-excel");
		
//		String sql="select xh,xn,xq,jztm,xthdrq,jcdj,ccdj,'1' pf from " +
//				"(select a.*,b.nj,b.xydm,b.zydm,b.bjdm from szyq_ivtltb a,view_xsjbxx b where a.xh=b.xh) where 1=1 "+searchTj;
//		String[] outputValue={"xn","xq","xh","jztm","xthdrq","jcdj","ccdj","pf"};
//		String[] topTr=new String[]{"学年","学期","学号","讲座题目","日期","进场登记","出场登记","评分"};
		
		String sql="select '"+Base.currXn+"' xn,'"+Base.currXq+"' xq,xh,xm,xymc,zymc,bjmc,'' jztm,'' xthdrq,'' jcdj,'' ccdj,'1' pf from view_xsjbxx where 1=1 "+searchTj;
		String[] outputValue={"xn","xq","xh","xm","xymc","zymc","bjmc","jztm","xthdrq","jcdj","ccdj","pf"};
		String[] topTr=new String[]{"学年","学期","学号","姓名","学院","专业","班级","讲座题目","日期","进场登记","出场登记","评分"};
		
		new Excel2Oracle().exportData(sql, inputV, outputValue, topTr, response.getOutputStream(), "export.xls");
		
		return null;
	}

	/**
	 * 导入Ivt论坛
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author gaobb
	 */
	public ActionForward importIvtlt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = request.getParameter("tableName");// 视图名
		String realTable = request.getParameter("realTable");// 表名

		request.setAttribute("dzyDdTab", request.getParameter("dzyDdTab"));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);

		String act = request.getParameter("act");
		// 导入数据
		if ("import".equals(act)) {
			uploadFile(mapping, form, request, response);
			String filepath = (String) request.getAttribute("filepath");

			PjpyTeaService service = new PjpyTeaService();
			String back = service.importIvtlt(filepath, getUser(request));
			request.setAttribute("back", back);

		}
		return mapping.findForward("importIvtlt");
	}

	/**
	 * 文件上传
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	private ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 该处需要验证超级管理员权限
		String dir = servlet.getServletContext().getRealPath("/upload");
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		HttpSession session = request.getSession();
		String fName = (String) session.getAttribute("userName");
		String tabName = request.getParameter("tabName");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		fName += date.toString().substring(0, 19);
		fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":",
				"");
		PjpyTeaForm hff = (PjpyTeaForm) form;
		FormFile file = hff.getImpFilePath();
		// if (file == null || (file.getFileName() == null ||
		// file.getFileName().trim().equals(""))) {
		// file = hff.getCheckFilePath();
		if (file == null) {
			return mapping.findForward("false");
		}
		// }
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = dir + "/" + fName + ".xls";
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		in.close();
		request.setAttribute("tabName", tabName);
		request.setAttribute("filepath", filePath);
		request.setAttribute("moditag", request.getParameter("moditag"));
		return mapping.findForward("success");
	}

}
