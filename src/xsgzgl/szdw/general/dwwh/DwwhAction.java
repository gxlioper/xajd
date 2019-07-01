package xsgzgl.szdw.general.dwwh;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;
import xsgzgl.szdw.teainfo.TeaInfoService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_队伍维护_通用_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class DwwhAction extends BasicAction {

	/**
	 * 查询思政隊伍維護
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public ActionForward searchDwwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		DwwhInit init = new DwwhInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		String path = request.getParameter("path");
		myForm.setPath(path);

		// ============= 初始化各变量的值 ============
		init.initDwwhSearch(rForm, myForm, user, request);
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue").split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);

		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getDwwhTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getDwwhList(myForm, model, user);
		// 构建结果集An internal error has occurred.

		String spHtml = service.createDwwhHTML(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 创建队伍维护DIV
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public ActionForward createDwwhDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		// service.createDwwhDiv(model, user, response);
		// ==================构建前台页面 end================
		String html = service.createDwwhDivStr(model, user, response);
		request.setAttribute("html", html);
		return mapping.findForward("dwwhzj");
	}

	/**
	 * 保存思政队伍维护
	 * 
	 * @date 2013-01-10
	 * @author 伟大的骆
	 */
	@SystemLog(description = "访问思政队伍-思政队伍-辅导员信息维护-增加ZGH:{zgh},XM:{xm},XB:{xb},BMDM:{bmdm}")
	public ActionForward saveDwwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= 执行保存操作 begin ============
		boolean flag = service.saveDwwh(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 删除思政队伍维护
	 * 
	 * @date 2013-01-10
	 * @author 伟大的骆
	 */
	@SystemLog(description = "访问思政队伍-思政队伍-辅导员信息维护-删除values:{pkValue}")
	public ActionForward deleteDwwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
//		myService.getModelValue(model, request);
		model.setPkValue(myForm.getZghs().split(","));
		// =================== end ===================

		// ============= 执行删除操作 begin ============
		boolean flag = service.deleteDwwh(model, user);
		String message = flag ? "删除成功" : "删除失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 创建用户库DIV
	 * 
	 * @date 2013-01-10
	 * @author 伟大的骆
	 */
	public ActionForward createYhkDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createYhkDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 保存用户到用户库
	 * 
	 * @date 2013-01-10
	 * @author 伟大的骆
	 */
	public ActionForward saveYhk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= 执行保存操作 begin ============
		boolean flag = service.saveYhk(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 创建院系兼任DIV
	 * 
	 * @date 2013-01-10
	 * @author 伟大的骆
	 */
	public ActionForward createYxjrDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		// service.createYxjrDiv(model, user, response);
		// ==================构建前台页面 end================
		request.setAttribute("html", service.createYxjrDiv(model, user, response));
		return mapping.findForward("yxjr");
	}

	/**
	 * 保存用户到用户库
	 * 
	 * @date 2013-01-10
	 * @author 伟大的骆
	 */
	public ActionForward saveYxjr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= 执行保存操作 begin ============
		boolean flag = service.saveYxjr(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 创建班级信息DIV
	 * 
	 * @date 2013-01-10
	 * @author 伟大的骆
	 */
	public ActionForward createBjxxDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createBjxxDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 
	 * @描述:修改弹出层页面
	 * @作者：dlq [工号：445]
	 * @日期：2013-8-21 下午12:21:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward createBjxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================
		// ==================修改前台页面====================
		String lx = request.getParameter("lx");
		String zgh = request.getParameter("zgh");
		model.setLx(lx);
		model.setZgh(zgh);
		HashMap<String, String> map = service.getDwwh(model, user);

		// 类型名称
		String lxmc = "fdy".equalsIgnoreCase(lx) ? "辅导员" : "班主任";
		request.setAttribute("map", map);
		request.setAttribute("lxmc", lxmc);
		request.setAttribute("rsArrList", service.createBjxx(model, user));
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("lx", lx);
		request.setAttribute("zgh", zgh);
		// ==================修改前台页面 end================

		return mapping.findForward("viewDbs");
	}

	/**
	 * 创建年级Div
	 * 
	 * @date 2013-01-11
	 * @author 伟大的骆
	 */
	public ActionForward createNjLvDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= 创建Html ============
		service.createNjLvDiv(model, user, response);
		// ============= 创建Html end ============

		return null;
	}

	/**
	 * 创建其他级别Div
	 * 
	 * @date 2013-01-11
	 * @author 伟大的骆
	 */
	public ActionForward createOtherLvDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= 创建Html ============
		service.createOtherLvDiv(model, user, response);
		// ============= 创建Html end ============

		return null;
	}

	/**
	 * 保存分配班级
	 * 
	 * @date 2013-01-14
	 * @author 伟大的骆
	 */
	public ActionForward saveFpbj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= 执行保存操作 begin ============
		boolean flag = service.saveFpbj(model, user);
		String message = flag ? "执行成功" : "执行失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 保存分配班级
	 * 
	 * @date 2013-01-24
	 * @author qlj
	 */
	public ActionForward disfrockFpbj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= 执行保存操作 begin ============
		boolean flag = service.disfrockFpbj(model, user);
		String message = flag ? "执行成功" : "执行失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 创建教职工Option
	 * 
	 * @date 2013-01-18
	 * @author 伟大的骆
	 */
	public ActionForward createJzgOption(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
 		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= 获取option begin ============
		String option = service.createJzgOption(model);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(option);

		return null;
	}

	/**
	 * 查询班级信息
	 * 
	 * @date 2013-01-18
	 * @author 伟大的骆
	 */
	public ActionForward searchSetup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		DwwhInit init = new DwwhInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initDwwhSearch(rForm, myForm, user, request);
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================显示内容========================

		// 标题
		List<HashMap<String, String>> topTr = service.getSetupTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getSetupList(myForm, model, user);
		// 构建结果集
		String spHtml = service.createSetupHTML(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("yes");
		rsModel.setShowTitle("yes");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 创建教职工信息DIV
	 * 
	 * @date 2013-01-10
	 * @author 伟大的骆
	 */
	public ActionForward createJzgxxDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createJzgxxDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 查询教职工信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJzgxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhJzgModle model = new DwwhJzgModle();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		String zgh = request.getParameter("zgh");

		User user = getUser(request);// 用户对象
		if (zgh == null || "".equals(zgh)) {
			zgh = user.getUserName();
			// 辅导员个人信息修改和管理员修改区别标示
			request.setAttribute("flag", "true");
		}
		if ("xy".equalsIgnoreCase(user.getUserType())) {
			request.setAttribute("xy", "true");
		}
		// 职务列表
		request.setAttribute("zwList", new TeaInfoService().getZwList());
		// 加载岗位类别列表
		request.setAttribute("gwlbList", new TeaInfoService().getGwlbList());
		//加载职称列表
		request.setAttribute("zcList", new TeaInfoService().getZcList());
		model.setZgh(zgh);
		model = service.cxJzgxx(model, user,request);
		request.setAttribute("model", model);

		String path1 = "szdw_dwwh.do?method=cxJzgxx&zgh=";
		request.setAttribute("path", path1);
		request.setAttribute("bmList", new DwwhService().getBmList());
		
		// 江西师范大学辅导员信息个性化显示
		if ("11318".equals(Base.xxdm)) {
			// 加载编制类型
			request.setAttribute("bzlxList", new TeaInfoService().getBzlxList());
			// 加载职称类型
			request.setAttribute("zcList", new TeaInfoService().getZcList());
			// 加载身份具体类型
			request.setAttribute("sfjtlxList", new TeaInfoService().getZzFdyList());
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("szdwwhjzgxx_11318");
		} else if ("13023".equals(Base.xxdm)) {
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("szdwwhjzgxx_13023");
		} else {
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("szdwwhjzgxx");
		}

	}

	/**
	 * 查看教职工信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ckJzgxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		String zgh = request.getParameter("zgh");

		if ("11318".equals(Base.xxdm)) {
			HashMap<String, String> map = service.ckJzgxxJxsf(zgh);
			request.setAttribute("map", map);
			return mapping.findForward("ckJzgxx_11318");
		} else if ("13023".equals(Base.xxdm)) {
			HashMap<String, String> map = service.ckJzgxx_13023(zgh);
			request.setAttribute("map", map);
			return mapping.findForward("ckJzgxx_13023");
		} else {
			HashMap<String, String> map = service.ckJzgxx(zgh);
			request.setAttribute("map", map);
			return mapping.findForward("ckJzgxx");
		}
	}

	/**
	 * 修改教职工想信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateDwwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		boolean flag = service.updateJzgxx(request);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().println(message);
		return null;
	}

	/**
	 * 辅导员信息维护自定义导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward fdyxxwhExportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SzdwGeneralForm model = (SzdwGeneralForm) form;

		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(model);

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);

		List<HashMap<String, String>> resultList = service.getDwwhExportList(model, user);

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		insertLog(mapping, form, request, response);//插入导出操作日志
		return null;
	}

	/**
	 * 
	 * @描述:江西科技师范大学思政队伍辅导员信息导出个性化
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-8 下午02:20:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward fdyxxwhExportJxsf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		DwwhService service = new DwwhService();
		SzdwGeneralForm exporModel = (SzdwGeneralForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("szdw_dwwh.do?method=searchDwwh");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.fdyxxwhExportJxsf(exporModel, response.getOutputStream(), user);

		// ============= end ============

		return null;
	}

	/**
	 * 
	 * @描述:江西科技师范大学思政队伍辅导员配备表导出个性化
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-8 下午02:20:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward pbbExportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		DwwhService service = new DwwhService();
		SzdwGeneralForm exporModel = (SzdwGeneralForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("szdw_dwwh.do?method=searchDwwh");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.pbbExportData(exporModel, response.getOutputStream(), user);

		// ============= end ============

		return null;
	}

	/**
	 * 
	 * @描述:江西科技师范大学思政队伍辅导员带班情况导出个性化
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-8 下午02:20:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward dbqkbData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		DwwhService service = new DwwhService();
		SzdwGeneralForm exporModel = (SzdwGeneralForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("szdw_dwwh.do?method=searchDwwh");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.dbqkbData(exporModel, response.getOutputStream(), user);

		// ============= end ============

		return null;
	}
	
	/**
	 * @描述: 思政队伍设置
	 * @作者：江水才[工号：1150]
	 * @日期：2014-12-10 下午02:39:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward szdwSz_10352(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		request.setAttribute("ids", request.getParameter("ids"));
		return mapping.findForward("szdwSz_10352");
	}
	
	/**
	 * @描述: 思政队伍设置保存
	 * @作者：江水才[工号：1150]
	 * @日期：2014-12-10 下午02:39:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward szdwSzSave_10352(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		String sf = request.getParameter("sf"); // 是否思政队伍
		DwwhService service = new DwwhService();
		
		boolean flag = service.szdwSzSave(ids, sf);
		String message = flag ? "保存成功！":"保存失败！";
		request.setAttribute("message", message);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 导出带班学生信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-10-12 下午01:59:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward exportDbStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String lx = request.getParameter("lx");
		String zgh = request.getParameter("zgh");
		User user = getUser(request);
		List<HashMap<String, String>> resultList = new DwwhService().exportDbStu(zgh, lx);
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = new ExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
