package xsgzgl.pjpy.szgyyq.mypj.stu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.FileManage;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.mypj.PjpyMypjForm;
import xgxt.pjpy.comm.pjpy.mypj.PjpyMypjService;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrInit;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrService;

import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Pages;
import xgxt.xsxx.comm.ajax.XsxxAjaxService;
import xsgzgl.pjpy.szgyyq.model.DshdModel;
import xsgzgl.pjpy.szgyyq.model.IvtltModel;
import xsgzgl.pjpy.szgyyq.model.WthdModel;
import xsgzgl.pjpy.szgyyq.model.XsssModel;
import xsgzgl.pjpy.szgyyq.model.XstsModel;
import xsgzgl.pjpy.szgyyq.model.YybdModel;
import xsgzgl.pjpy.szgyyq.model.ZznlModel;
import xsgzgl.pjpy.szgyyq.model.ShsjModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjInit;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.DshdService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.IvtltService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.ShsjService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.WthdService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.YybdService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.ZznlService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(学生)_Action类
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

public class PjpyStuAction extends BasicAction {

	/**
	 * 评奖评优_苏州工业园区_项目分数申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fssqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		XsxxglService stuService = new XsxxglService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initMypj(rForm, myForm, request);

		// 初始化项目列表
		List<HashMap<String, String>> cshXmList = service.getFssqXmList(myForm);
		request.setAttribute("cshXmList", cshXmList);

		String xh = user.getUserName();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		request.setAttribute("stuInfo", stuInfo);
		// =================== end ===================

		// ============= 设置request的值 =============
		request.setAttribute("searchTj", myForm.getSearchModel());
		service.setRequestValue(rForm, request);

		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		// =================== end ===================

		return mapping.findForward("fssqManage");
	}

	// =====================读书活动===================================

	/**
	 * 保存读书活动申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveDshdSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		DshdService service = new DshdService();
		User user = getUser(request);// 用户对象
		DshdModel dshdModel = new DshdModel();

		// 读书名称
		String[] dsmc = request.getParameter("dsmc").split("!!@@!!");
		dshdModel.setDsmc(dsmc);

		// 读书日期
		String[] dsrq = request.getParameter("dsrq").split("!!@@!!");
		dshdModel.setDsrq(dsrq);

		// 读书心得
		String[] dsxd = request.getParameter("dsxd").split("!!@@!!");
		dshdModel.setDsxd(dsxd);

		// 是否获奖
		String[] sfhj = request.getParameter("sfhj").split("!!@@!!");
		dshdModel.setSfhj(sfhj);

		// 申请分
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		dshdModel.setSqf(sqf);

		// 学年
		String xn = Base.currXn;
		dshdModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		dshdModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		dshdModel.setXh(xh);

		// ==================执行保存操作========================
		myForm.setDshdModel(dshdModel);
		boolean flag = service.saveDshdSqf(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 删除读书活动申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delDshdSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		DshdService service = new DshdService();
		User user = getUser(request);// 用户对象
		DshdModel dshdModel = new DshdModel();

		// 读书名称
		String dsmc = request.getParameter("dsmc");
		dshdModel.setDsmc(new String[] { dsmc });

		// 读书日期
		String dsrq = request.getParameter("dsrq");
		dshdModel.setDsrq(new String[] { dsrq });

		// 学年
		String xn = Base.currXn;
		dshdModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		dshdModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		dshdModel.setXh(xh);

		// ==================执行删除操作========================
		myForm.setDshdModel(dshdModel);
		boolean flag = service.delDshdSqf(myForm, user);
		// ==================执行删除操作 end========================

		return null;
	}

	/**
	 * 获得读书活动信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getDshdInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		DshdService service = new DshdService();
		User user = getUser(request);// 用户对象

		// 申请ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		List<HashMap<String, String>> list = service.getDshdInfo(myForm, user);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * 保存读书活动申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editDshdInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		DshdService service = new DshdService();
		User user = getUser(request);// 用户对象
		DshdModel dshdModel = new DshdModel();

		// 申请ID
		String id = request.getParameter("sqid");
		dshdModel.setId(id);

		// 读书名称
		String dsmc = request.getParameter("dsmc");
		dshdModel.setDsmc(new String[] { service.unicode2Gbk(dsmc) });

		// 读书日期
		String dsrq = request.getParameter("dsrq");
		dshdModel.setDsrq(new String[] { dsrq });

		// 读书心得
		String dsxd = request.getParameter("dsxd");
		dshdModel.setDsxd(new String[] { service.unicode2Gbk(dsxd) });

		// 是否获奖
		String sfhj = request.getParameter("sfhj");
		dshdModel.setSfhj(new String[] { service.unicode2Gbk(sfhj) });

		// 申请分
		String sqf = request.getParameter("sqf");
		dshdModel.setSqf(new String[] { sqf });

		// 学年
		String xn = Base.currXn;
		dshdModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		dshdModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		dshdModel.setXh(xh);

		// ==================执行保存操作========================
		myForm.setDshdModel(dshdModel);
		boolean flag = service.editDshdInfo(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// =====================读书活动 end===================================

	// =====================语言表达===================================

	/**
	 * 保存语言表达申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveYybdSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		YybdService service = new YybdService();
		User user = getUser(request);// 用户对象
		YybdModel yybdModel = new YybdModel();

		// 语言表达内容
		String[] yybdnr = request.getParameter("yybdnr").split("!!@@!!");
		yybdModel.setYybdnr(yybdnr);

		// 日期
		String[] xthdrq = request.getParameter("xthdrq").split("!!@@!!");
		yybdModel.setXthdrq(xthdrq);

		// 申请分
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		yybdModel.setSqf(sqf);

		// 学年
		String xn = Base.currXn;
		yybdModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		yybdModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		yybdModel.setXh(xh);

		// ==================执行保存操作========================
		myForm.setYybdModel(yybdModel);
		boolean flag = service.saveYybdSqf(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 删除语言表达申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delYybdSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		YybdService service = new YybdService();
		User user = getUser(request);// 用户对象
		YybdModel yybdModel = new YybdModel();

		// 语言表达内容
		String yybdnr = request.getParameter("yybdnr");
		yybdModel.setYybdnr(new String[] { yybdnr });

		// 日期
		String xthdrq = request.getParameter("xthdrq");
		yybdModel.setXthdrq(new String[] { xthdrq });

		// 学年
		String xn = Base.currXn;
		yybdModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		yybdModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		yybdModel.setXh(xh);

		// ==================执行删除操作========================
		myForm.setYybdModel(yybdModel);
		boolean flag = service.delYybdSqf(myForm, user);
		// ==================执行删除操作 end========================

		return null;
	}

	/**
	 * 获得读书表达信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getYybdInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		YybdService service = new YybdService();
		User user = getUser(request);// 用户对象

		// 申请ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		List<HashMap<String, String>> list = service.getYybdInfo(myForm, user);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * 保存语言表达申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editYybdInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		YybdService service = new YybdService();
		User user = getUser(request);// 用户对象
		YybdModel yybdModel = new YybdModel();

		// 申请ID
		String id = request.getParameter("sqid");
		yybdModel.setId(id);

		// 语言表达内容
		String yybdnr = request.getParameter("yybdnr");
		yybdModel.setYybdnr(new String[] { service.unicode2Gbk(yybdnr) });

		// 读书日期
		String xthdrq = request.getParameter("xthdrq");
		yybdModel.setXthdrq(new String[] { xthdrq });

		// 申请分
		String sqf = request.getParameter("sqf");
		yybdModel.setSqf(new String[] { sqf });

		// 学年
		String xn = Base.currXn;
		yybdModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		yybdModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		yybdModel.setXh(xh);

		// ==================执行保存操作========================
		myForm.setYybdModel(yybdModel);
		boolean flag = service.editYybdInfo(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// =====================语言表达 end ===================================

	// =====================Ivt论坛===================================

	/**
	 * 保存Ivt论坛申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveIvtltSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		IvtltService service = new IvtltService();
		User user = getUser(request);// 用户对象
		IvtltModel ivtltModel = new IvtltModel();

		// 讲座题目
		String[] jztm = request.getParameter("jztm").split("!!@@!!");
		ivtltModel.setJztm(jztm);

		// 日期
		String[] xthdrq = request.getParameter("xthdrq").split("!!@@!!");
		ivtltModel.setXthdrq(xthdrq);

		// 进场登记
		String[] jcdj = request.getParameter("jcdj").split("!!@@!!");
		ivtltModel.setJcdj(jcdj);

		// 出场登记
		String[] ccdj = request.getParameter("ccdj").split("!!@@!!");
		ivtltModel.setCcdj(ccdj);

		// 申请分
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		ivtltModel.setSqf(sqf);

		// 学年
		String xn = Base.currXn;
		ivtltModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		ivtltModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		ivtltModel.setXh(xh);

		// ==================执行保存操作========================
		myForm.setIvtltModel(ivtltModel);
		boolean flag = service.saveIvtltSqf(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 删除Ivt论坛申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delIvtltSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		IvtltService service = new IvtltService();
		User user = getUser(request);// 用户对象
		IvtltModel ivtltModel = new IvtltModel();

		// 讲座题目
		String jztm = request.getParameter("jztm");
		ivtltModel.setJztm(new String[] { jztm });

		// 日期
		String xthdrq = request.getParameter("xthdrq");
		ivtltModel.setXthdrq(new String[] { xthdrq });

		// 学年
		String xn = Base.currXn;
		ivtltModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		ivtltModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		ivtltModel.setXh(xh);

		// ==================执行删除操作========================
		myForm.setIvtltModel(ivtltModel);
		boolean flag = service.delIvtltSqf(myForm, user);
		// ==================执行删除操作 end========================

		return null;
	}

	/**
	 * 获得Ivt论坛信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getIvtltInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		IvtltService service = new IvtltService();
		User user = getUser(request);// 用户对象

		// 申请ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		List<HashMap<String, String>> list = service.getIvtltInfo(myForm, user);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * 保存Ivt论坛申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editIvtltInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		IvtltService service = new IvtltService();
		User user = getUser(request);// 用户对象
		IvtltModel ivtltModel = new IvtltModel();

		// 申请ID
		String id = request.getParameter("sqid");
		ivtltModel.setId(id);

		// 讲座题目
		String jztm = request.getParameter("jztm");
		ivtltModel.setJztm(new String[] { service.unicode2Gbk(jztm) });

		// 日期
		String xthdrq = request.getParameter("xthdrq");
		ivtltModel.setXthdrq(new String[] { xthdrq });

		// 进场登记
		String jcdj = request.getParameter("jcdj");
		if (jcdj != null) {
			ivtltModel.setJcdj(new String[] { service.unicode2Gbk(jcdj) });
		}

		// 出场登记
		String ccdj = request.getParameter("ccdj");
		if (ccdj != null) {
			ivtltModel.setCcdj(new String[] { service.unicode2Gbk(ccdj) });
		}

		// 申请分
		String sqf = request.getParameter("sqf");
		ivtltModel.setSqf(new String[] { sqf });

		// ==================执行保存操作========================
		myForm.setIvtltModel(ivtltModel);
		boolean flag = service.editIvtltInfo(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// =====================Ivt论坛 end===================================

	// =====================文体活动===================================

	/**
	 * 保存文体活动申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveWthdSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		WthdService service = new WthdService();
		User user = getUser(request);// 用户对象
		WthdModel wthdModel = new WthdModel();

		// 活动内容
		String[] hdnr = request.getParameter("hdnr").split("!!@@!!");
		wthdModel.setHdnr(hdnr);

		// 日期
		String[] xthdrq = request.getParameter("xthdrq").split("!!@@!!");
		wthdModel.setXthdrq(xthdrq);

		// 奖励等级
		String[] jldj = request.getParameter("jldj").split("!!@@!!");
		wthdModel.setJldj(jldj);

		// 申请分
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		wthdModel.setSqf(sqf);

		// 学年
		String xn = Base.currXn;
		wthdModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		wthdModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		wthdModel.setXh(xh);

		// ==================执行保存操作========================
		myForm.setWthdModel(wthdModel);
		boolean flag = service.saveWthdSqf(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 删除文体活动申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delWthdSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		WthdService service = new WthdService();
		User user = getUser(request);// 用户对象
		WthdModel wthdModel = new WthdModel();

		// 语言表达内容
		String hdnr = request.getParameter("hdnr");
		wthdModel.setHdnr(new String[] { hdnr });

		// 日期
		String xthdrq = request.getParameter("xthdrq");
		wthdModel.setXthdrq(new String[] { xthdrq });

		// 学年
		String xn = Base.currXn;
		wthdModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		wthdModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		wthdModel.setXh(xh);

		// ==================执行删除操作========================
		myForm.setWthdModel(wthdModel);
		boolean flag = service.delWthdSqf(myForm, user);
		// ==================执行删除操作 end========================

		return null;
	}

	/**
	 * 获得文体活动信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getWthdInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		WthdService service = new WthdService();
		User user = getUser(request);// 用户对象

		// 申请ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		List<HashMap<String, String>> list = service.getWthdInfo(myForm, user);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * 保存文体活动申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editWthdInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		WthdService service = new WthdService();
		User user = getUser(request);// 用户对象
		WthdModel wthdModel = new WthdModel();

		// 申请ID
		String id = request.getParameter("sqid");
		wthdModel.setId(id);

		// 讲座题目
		String hdnr = request.getParameter("hdnr");
		wthdModel.setHdnr(new String[] { service.unicode2Gbk(hdnr) });

		// 日期
		String xthdrq = request.getParameter("xthdrq");
		wthdModel.setXthdrq(new String[] { xthdrq });

		// 进场登记
		String jldj = request.getParameter("jldj");
		if(jldj != null){
			wthdModel.setJldj(new String[] { service.unicode2Gbk(jldj) });
		}

		// 申请分
		String sqf = request.getParameter("sqf");
		wthdModel.setSqf(new String[] { sqf });

		// ==================执行保存操作========================
		myForm.setWthdModel(wthdModel);
		boolean flag = service.editWthdInfo(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// =====================文体活动 end ===================================

	// =====================组织能力===================================

	/**
	 * 保存组织能力申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZznlSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ZznlService service = new ZznlService();
		User user = getUser(request);// 用户对象
		ZznlModel zznlModel = new ZznlModel();

		// 活动主题
		String[] hdzt = request.getParameter("hdzt").split("!!@@!!");
		zznlModel.setHdzt(hdzt);

		// 活动日期
		String[] hdrq = request.getParameter("hdrq").split("!!@@!!");
		zznlModel.setHdrq(hdrq);

		// 活动等级
		String[] hddj = request.getParameter("hddj").split("!!@@!!");
		zznlModel.setHddj(hddj);

		// 申请分
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		zznlModel.setSqf(sqf);

		// 学年
		String xn = Base.currXn;
		zznlModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		zznlModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		zznlModel.setXh(xh);

		// ==================执行保存操作========================
		myForm.setZznlModel(zznlModel);
		boolean flag = service.saveZznlSqf(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 删除组织能力申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delZznlSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ZznlService service = new ZznlService();
		User user = getUser(request);// 用户对象
		ZznlModel zznlModel = new ZznlModel();

		// 活动主题
		String[] hdzt = request.getParameter("hdzt").split("!!@@!!");
		zznlModel.setHdzt(hdzt);

		// 活动日期
		String[] hdrq = request.getParameter("hdrq").split("!!@@!!");
		zznlModel.setHdrq(hdrq);

		// 学年
		String xn = Base.currXn;
		zznlModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		zznlModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		zznlModel.setXh(xh);
		// ==================执行删除操作========================
		myForm.setZznlModel(zznlModel);
		boolean flag = service.delZznlSqf(myForm, user);
		// ==================执行删除操作 end========================

		return null;
	}

	/**
	 * 获得组织能力信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getZznlInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ZznlService service = new ZznlService();
		User user = getUser(request);// 用户对象

		// 申请ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		List<HashMap<String, String>> list = service.getZznlInfo(myForm, user);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * 保存组织能力申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editZznlInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ZznlService service = new ZznlService();
		User user = getUser(request);// 用户对象
		ZznlModel zznlModel = new ZznlModel();

		// 申请ID
		String id = request.getParameter("sqid");
		zznlModel.setId(id);

		// 活动主题
		String hdzt = request.getParameter("hdzt");
		zznlModel.setHdzt(new String[] { service.unicode2Gbk(hdzt) });

		// 日期
		String hdrq = request.getParameter("hdrq");
		zznlModel.setHdrq(new String[] { hdrq });

		// 活动等级
		String hddj = request.getParameter("hddj");
		if(hddj != null){
			zznlModel.setHddj(new String[] { service.unicode2Gbk(hddj) });
		}

		// 申请分
		String sqf = request.getParameter("sqf");
		zznlModel.setSqf(new String[] { sqf });

		// ==================执行保存操作========================
		myForm.setZznlModel(zznlModel);
		boolean flag = service.editZznlInfo(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// =====================组织能力 end ===================================

	// =====================社会实践===================================

	/**
	 * 保存社会实践申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveShsjSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ShsjService service = new ShsjService();
		User user = getUser(request);// 用户对象
		ShsjModel shsjModel = new ShsjModel();

		// 活动地点
		String[] hddd = request.getParameter("hddd").split("!!@@!!");
		shsjModel.setHddd(hddd);

		// 活动日期
		String[] hdrq = request.getParameter("hdrq").split("!!@@!!");
		shsjModel.setHdrq(hdrq);

		// 活动内容
		String[] hdnr = request.getParameter("hdnr").split("!!@@!!");
		shsjModel.setHdnr(hdnr);

		// 活动时间
		String[] hdsj = request.getParameter("hdsj").split("!!@@!!");
		shsjModel.setHdsj(hdsj);

		// 申请分
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		shsjModel.setSqf(sqf);

		// 学年
		String xn = Base.currXn;
		shsjModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		shsjModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		shsjModel.setXh(xh);

		// ==================执行保存操作========================
		myForm.setShsjModel(shsjModel);
		boolean flag = service.saveShsjSqf(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 删除社会实践申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delShsjSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ShsjService service = new ShsjService();
		User user = getUser(request);// 用户对象
		ShsjModel shsjModel = new ShsjModel();

		// 活动地点
		String[] hddd = request.getParameter("hddd").split("!!@@!!");
		shsjModel.setHddd(hddd);

		// 活动日期
		String[] hdrq = request.getParameter("hdrq").split("!!@@!!");
		shsjModel.setHdrq(hdrq);

		// 学年
		String xn = Base.currXn;
		shsjModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		shsjModel.setXq(xq);

		// 学号
		String xh = user.getUserName();
		shsjModel.setXh(xh);

		// ==================执行删除操作========================
		myForm.setShsjModel(shsjModel);
		boolean flag = service.delShsjSqf(myForm, user);
		// ==================执行删除操作 end========================

		return null;
	}

	/**
	 * 获得社会实践信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getShsjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ShsjService service = new ShsjService();
		User user = getUser(request);// 用户对象

		// 申请ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		List<HashMap<String, String>> list = service.getShsjInfo(myForm, user);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * 保存社会实践申请分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editShsjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ShsjService service = new ShsjService();
		User user = getUser(request);// 用户对象
		ShsjModel shsjModel = new ShsjModel();

		// 申请ID
		String id = request.getParameter("sqid");
		shsjModel.setId(id);

		// 活动地点
		String hddd = request.getParameter("hddd");
		shsjModel.setHddd(new String[] { service.unicode2Gbk(hddd) });

		// 活动日期
		String hdrq = request.getParameter("hdrq");
		shsjModel.setHdrq(new String[] { hdrq });

		// 活动内容
		String hdnr = request.getParameter("hdnr");
		shsjModel.setHdnr(new String[] { service.unicode2Gbk(hdnr) });

		// 活动时间
		String hdsj = request.getParameter("hdsj");
		if(hdsj != null){
			shsjModel.setHdsj(new String[] { hdsj });
		}

		// 申请分
		String sqf = request.getParameter("sqf");
		shsjModel.setSqf(new String[] { sqf });

		// ==================执行保存操作========================
		myForm.setShsjModel(shsjModel);
		boolean flag = service.editShsjInfo(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// =====================社会实践 end===================================

	/**
	 * 获得申请项目信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSqxmInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		User user = getUser(request);// 用户对象

		// 项目代码
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// 学号
		String xh = user.getUserName();
		myForm.setXh(xh);

		String sqxnIbfo = service.getSqxmInfo(myForm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(sqxnIbfo);

		return null;
	}

	/**
	 * 评奖评优_苏州工业园区_项目分数申请详细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sqxxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSqxxDetail(rForm, myForm, request);

		// 申请信息
		List<HashMap<String, String>> infoList = service.getSqxxList(myForm);
		request.setAttribute("infoList", infoList);

		String yhlx = myForm.getYhlx();
		if ("bz".equalsIgnoreCase(yhlx) || "stu".equalsIgnoreCase(yhlx)) {
			request.setAttribute("canCz", "yes");
		}
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("sqxxDetail");
	}

	/**
	 * 保存学生申诉
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXsss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		User user = getUser(request);// 用户对象
		XsssModel xsssModel = new XsssModel();

		// 项目类型
		String xmlx = request.getParameter("xmlx");
		xsssModel.setXmlx(xmlx);

		// 项目ID
		String xmid = request.getParameter("xmid");
		xsssModel.setXmid(xmid);

		// 学号
		String xh = request.getParameter("xh");
		xsssModel.setXh(xh);

		// 申诉内容
		String ssnr = service.unicode2Gbk(request.getParameter("ssnr"));
		xsssModel.setSsnr(ssnr);

		// 学年
		String xn = Base.currXn;
		xsssModel.setXn(xn);

		// 学期
		String xq = Base.currXq;
		xsssModel.setXq(xq);

		// ==================执行保存操作========================
		myForm.setXsssModel(xsssModel);
		boolean flag = service.saveXsss(myForm, user, request);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
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

		PjpyStuForm myForm = (PjpyStuForm) form;
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

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// 项目代码
		String xmdm = otherValue[0];
		myForm.setXmdm(xmdm);

		// 初始化
		init.initJgcx(rForm, myForm, request);

		// 学号
		String xh = user.getUserName();
		myForm.setXh(xh);

		// 学年
		String xn = otherValue[1];

		if (!Base.isNull(otherValue[1].trim())) {
			xn = otherValue[1].split("!!##!!")[0];
		} else {
			xn = Base.currXn;
		}

		// 学期
		String xq = otherValue[2];

		if (!Base.isNull(otherValue[2].trim())) {
			xq = otherValue[2].split("!!##!!")[0];
		} else {
			xq = Base.currXq;
		}

		// 操作
		String[] cz = null;
		if (!Base.isNull(otherValue[3].trim())) {
			cz = otherValue[3].split("!!##!!");
		}

		// 查询类型
		String mhcx_lx = otherValue[4];

		// 模糊查询
		String input_mhcx = service.unicode2Gbk(otherValue[5]);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setSearch_tj_xn(new String[] { xn });
		searchModel.setSearch_tj_xq(new String[] { xq });
		searchModel.setSearch_tj_cz(cz);
		searchModel.setMhcx_lx(mhcx_lx);
		searchModel.setInput_mhcx(input_mhcx);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
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
	 * 保存学生投诉
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXsts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
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
		String xh = user.getUserName();
		xstsModel.setXh(xh);

		// 被投诉人
		String btsr = request.getParameter("btsr");
		xstsModel.setBtsr(btsr);

		// 投诉内容
		String tsnr = service.unicode2Gbk(request.getParameter("tsnr"));
		xstsModel.setTsnr(tsnr);

		// ==================执行保存操作========================
		myForm.setXstsModel(xstsModel);
		boolean flag = service.saveXsts(myForm, user, request);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 评奖评优_苏州工业园区_我的申诉
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myssManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();
		SearchModel searchModel = myForm.getSearchModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initMySs(rForm, myForm, request);

		// 初始化项目列表
		List<HashMap<String, String>> cshXmList = service.getJgcxXmList(myForm);
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

		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);

		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", xqmc);
		request.setAttribute("xqmc", xqmc);
		// =================== end ===================

		return mapping.findForward("myssManage");
	}

	/**
	 * 获得我的申诉查询列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMyssInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initMySs(rForm, myForm, request);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// 项目代码
		String[] xmdm = null;
		if (!Base.isNull(otherValue[0].trim())) {
			xmdm = otherValue[0].split("!!##!!");
		}

		// 学号
		String xh = user.getUserName();
		myForm.setXh(xh);

		// 学年
		String[] xn = null;

		if (!Base.isNull(otherValue[1].trim())) {
			xn = otherValue[1].split("!!##!!");
		} else {
			xn = new String[] { Base.currXn };
		}

		// 学期
		String[] xq = null;

		if (!Base.isNull(otherValue[2].trim())) {
			xq = otherValue[2].split("!!##!!");
		} else {
			xq = new String[] { Base.currXq };
		}

		// 类型
		String[] lx = null;
		if (!Base.isNull(otherValue[3].trim())) {
			lx = otherValue[3].split("!!##!!");
		}

		// 查询类型
		String mhcx_lx = otherValue[4];

		// 模糊查询
		String input_mhcx = service.unicode2Gbk(otherValue[5]);

		// IE版本
		// String ie = otherValue[1];
		// rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setSearch_tj_xmdm(xmdm);
		searchModel.setSearch_tj_xn(xn);
		searchModel.setSearch_tj_xq(xq);
		searchModel.setSearch_tj_lx(lx);
		searchModel.setMhcx_lx(mhcx_lx);
		searchModel.setInput_mhcx(input_mhcx);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		ArrayList<String[]> rsArrList = service.getMyssList(myForm, user);
		String spHtml = service.getMyssHtml(rsModel, myForm, rsArrList, user);
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
	 * 评奖评优_苏州工业园区_我的投诉
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mytsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();
		SearchModel searchModel = myForm.getSearchModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initMyTs(rForm, myForm, request);

		// 初始化项目列表
		List<HashMap<String, String>> cshXmList = service.getJgcxXmList(myForm);
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

		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);

		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", xqmc);
		request.setAttribute("xqmc", xqmc);
		// =================== end ===================

		return mapping.findForward("mytsManage");
	}

	/**
	 * 获得我的投诉查询列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMytsInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initMyTs(rForm, myForm, request);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// 项目代码
		String[] xmdm = null;
		if (!Base.isNull(otherValue[0].trim())) {
			xmdm = otherValue[0].split("!!##!!");
		}

		// 学号
		String xh = user.getUserName();
		myForm.setXh(xh);

		// 学年
		String[] xn = null;

		if (!Base.isNull(otherValue[1].trim())) {
			xn = otherValue[1].split("!!##!!");
		} else {
			xn = new String[] { Base.currXn };
		}

		// 学期
		String[] xq = null;

		if (!Base.isNull(otherValue[2].trim())) {
			xq = otherValue[2].split("!!##!!");
		} else {
			xq = new String[] { Base.currXq };
		}

		// 类型
		String[] lx = null;
		if (!Base.isNull(otherValue[3].trim())) {
			lx = otherValue[3].split("!!##!!");
		}

		// 查询类型
		String mhcx_lx = otherValue[4];

		// 模糊查询
		String input_mhcx = service.unicode2Gbk(otherValue[5]);

		// IE版本
		// String ie = otherValue[1];
		// rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setSearch_tj_xmdm(xmdm);
		searchModel.setSearch_tj_xn(xn);
		searchModel.setSearch_tj_xq(xq);
		searchModel.setSearch_tj_lx(lx);
		searchModel.setMhcx_lx(mhcx_lx);
		searchModel.setInput_mhcx(input_mhcx);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		ArrayList<String[]> rsArrList = service.getMytsList(myForm, user);
		String spHtml = service.getMytsHtml(rsModel, myForm, rsArrList, user);
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
	 * 检验可否操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkKfcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		User user = getUser(request);// 用户对象

		// 申请ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		// 项目ID
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// ==================执行保存操作========================
		boolean flag = service.checkKfcl(myForm);
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(flag);

		return null;
	}

	/**
	 * 删除申请记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteSqjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		User user = getUser(request);// 用户对象

		// 申请ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		// 项目ID
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// ==================执行保存操作========================
		boolean flag = service.deleteSqjl(myForm, user);
		String message = flag ? "删除成功" : "删除失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
