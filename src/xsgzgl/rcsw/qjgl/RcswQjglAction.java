package xsgzgl.rcsw.qjgl;

import java.io.File;
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
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 日常事务_请假管理_Action类
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

public class RcswQjglAction extends BasicAction {

	/**
	 * 日常事务_请假管理_参数设置_请假流程（维护）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjlcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initQjlc(rForm, myForm, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("qjlcManage");
	}

	/**
	 * 日常事务_请假管理_参数设置_请假流程（详细）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjlcDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initQjlc(rForm, myForm, request);
		String id = myForm.getId();
		// =================== end ===================

		// ============= 获得请假流程信息 ============
		HashMap<String, String> rs = service.getQjlcInfo(myForm);
		rForm.setRs(rs);

		List<HashMap<String, String>> splcList = service.getQjlcList();
		request.setAttribute("splcList", splcList);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		//request.setAttribute("qjlxList", service.getQjlbList());
		// =================== end ===================

		return mapping.findForward("qjlcDetail");
	}
	
	/**
	 * 查看请假信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewDetial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initMyqj(rForm, myForm, request);
		// =================== end ===================

		// ============= 获得请假流程信息 ============
		String userType=user.getUserType();
//		HashMap<String, String> rs = new HashMap<String,String>();
//		if("stu".equalsIgnoreCase(userType)){
//			rs.putAll(service.getStuInfo(user.getUserName()));
//		}else{
//			String xh=request.getParameter("xh");
//			rs.putAll(service.getStuInfo(xh));
//		}
		
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);
		HashMap<String, String> rs = service.getMyqjInfo(myForm, user);
		rs.put("sqid", sqid);
		List<HashMap<String, String>> lctLists = service.getLctList(myForm);
		String qstz="0";
		for(HashMap<String, String> map:lctLists){
			if(map.get("shzt").toString().equals("不通过")){
				qstz="1";
				break;
			}
		}
		request.setAttribute("lctLists", lctLists);
		request.setAttribute("lsszie",lctLists.size());
		request.setAttribute("qstz",qstz);
		
//		request.setAttribute("qjshList", service.getQjshInfo(myForm, user));
		rForm.setRs(rs);
		
		List<HashMap<String, String>> splcList = service.getQjlcList();
		request.setAttribute("splcList", splcList);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		
		myForm.setQjlx("sj");
		request.setAttribute("qjlxList", service.getQjlbList());
		request.setAttribute("tableName", "view_xsjbxx");
		request.setAttribute("rs",rs);
		return mapping.findForward("viewdetail");
	}

	/**
	 * 获得请假流程信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQjlcList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// 类型名称
		String lxmc = service.unicode2Gbk(otherValue[0]);
		myForm.setLxmc(lxmc);

		// 可否修改
		String kfxg = otherValue[1];
		myForm.setKfxg(kfxg);

		// 初始化
		init.initQjlc(rForm, myForm, request);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		ArrayList<String[]> rsArrList = service.getQjlcList(myForm, user);
		String spHtml = service.getQjlcHtml(rsModel, myForm, rsArrList, user);
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
	 * 保存请假流程
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveQjlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ID
		String id = request.getParameter("id");
		myForm.setId(id);

		// 类型名称
		String lxmc = service.unicode2Gbk(request.getParameter("lxmc"));
		myForm.setLxmc(lxmc);

		// 最小天数
		String mints = request.getParameter("mints");
		myForm.setMints(mints);

		// 最大天数
		String maxts = request.getParameter("maxts");
		myForm.setMaxts(maxts);

		// 流程ID
		String lcid = request.getParameter("lcid");
		myForm.setLcid(lcid);
		
		// 请假类型
		String qjlx = request.getParameter("qjlx");
		myForm.setQjlx(qjlx);
		
		// ==================执行保存操作========================
		String message = ""; 
		String qjs = service.minDaysIsLegality(myForm);
		if(Integer.valueOf(qjs).intValue()>0){
			message="-999";
		}else{
			boolean flag = service.saveQjlc(myForm, user, request);
			message = flag ? "保存成功" : "保存失败，请联系相关人员";
		}
		
		
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 删除请假流程
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delQjlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ID
		String[] checkVal = request.getParameter("id").split("!!@@!!");
		myForm.setCheckVal(checkVal);
		
		// ==================执行保存操作========================
		boolean flag = service.delQjlc(myForm, user);
		String message = flag ? "删除成功" : "删除失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	//===================以上请假流程设置======================================
	
	/**
	 * 日常事务_请假管理_我的工作_我的申請（维护）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myqjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initMyqj(rForm, myForm, request);
		// =================== end ===================

		// ==================登陆用户检测 ==================
		if (!"stu".equalsIgnoreCase(user.getUserType())) {
			String msg = "本模块只能由学生用户进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================
		  
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		FormModleCommon.setNdXnXqList(request);// 年度学年学期
		// =================== end ===================

		return mapping.findForward("myqjManage");
	}
	
	/**
	 * 日常事务_请假管理_我的工作_我的请假（详细）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myqjDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initMyqj(rForm, myForm, request);
		// =================== end ===================

		// ============= 获得请假流程信息 ============
		String userType=user.getUserType();
		HashMap<String, String> rs = new HashMap<String,String>();
		if("stu".equalsIgnoreCase(userType)){
			rs.putAll(service.getQjStudentInfo(user.getUserName()));
		}else{
			String xh=request.getParameter("xh");
			rs.putAll(service.getQjStudentInfo(xh));
		}
		
		List<HashMap<String, String>> splcList = service.getQjlcList();
		request.setAttribute("splcList", splcList);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		
		myForm.setQjlx("sj");
		request.setAttribute("qjlxList", service.getQjlbList());
		request.setAttribute("tableName", "view_xsjbxx");
		request.setAttribute("rs",rs);
		// =================== end ===================

		return mapping.findForward("myqjDetail");
	}
	
	/**
	 * 获得我的请假申请列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMyqjList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// 学年
		String xn = otherValue[0];
		myForm.setXn(xn);

		// 学期
		String xq = otherValue[1];
		myForm.setXq(xq);

		// 开始时间
		String kssj = otherValue[2];
		myForm.setKssj(kssj);

		// 结束时间
		String jssj = otherValue[3];
		myForm.setJssj(jssj);
		
		// 初始化
		init.initMyqj(rForm, myForm, request);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		ArrayList<String[]> rsArrList = service.getMyqjList(myForm, user);
		String spHtml = service.getMyqjHtml(rsModel, myForm, rsArrList, user);
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
	 * 删除我的请假
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delMyqj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ID
		String[] checkVal = request.getParameter("id").split("!!@@!!");
		myForm.setCheckVal(checkVal);
		
		// ==================执行保存操作========================
		boolean flag = service.delMyqjSq(myForm, user);
		String message = flag ? "删除成功" : "删除失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 设置请假类型名称
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		User user = getUser(request);// 用户对象

		// 申请天数
		String sqts = request.getParameter("sqts");
		myForm.setSqts(sqts);
		
		String qjlx = request.getParameter("qjlx");
		myForm.setQjlx(qjlx);

		// ==================获得请假项目========================
		//String qjxm = service.getQjxm(myForm, user);
//		response.setContentType("text/html;charset=gbk");
//		response.getWriter().print(qjxm);
		// ==================获得请假项目 end========================
		List<HashMap<String, String>> map = service.getQjxm(myForm, user);
		JSONArray qjlcsList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(qjlcsList);
		return null;
	}
	
	/**
	 * 保存请假流程
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveQjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// 请假ID
		String qjid = request.getParameter("qjid");
		myForm.setQjid(qjid);

		// 学号
		String xh = request.getParameter("xh");
		myForm.setXh(xh);

		// 请假天数
		String sqts = request.getParameter("sqts");
		myForm.setSqts(sqts);

		// 开始时间
		String kssj = request.getParameter("kssj");
		myForm.setKssj(kssj);

		// 结束时间
		String jssj = request.getParameter("jssj");
		myForm.setJssj(jssj);
		
		String qjlx = request.getParameter("qjlx");
		myForm.setKzzd1(qjlx);

		// 联系电话
		String lxdh = request.getParameter("lxdh");
		myForm.setLxdh(lxdh);

		// 家庭电话
		String jtdh = request.getParameter("jtdh");
		myForm.setJtdh(jtdh);

		// 家庭地址
		String jtdz = service.unicode2Gbk(request.getParameter("jtdz"));
		myForm.setJtdz(jtdz);

		// 申请理由
		String sqly = service.unicode2Gbk(request.getParameter("sqly"));
		myForm.setSqly(sqly);

		// 备注
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		myForm.setBz(bz);
		
		// 请假材料
		String qjcl = service.unicode2Gbk(request.getParameter("qjcl"));
		myForm.setQjcl(qjcl);

		// ==================执行保存操作========================
		boolean flag = service.saveMysq(myForm, user, request);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	//	===================以上我的请假======================================
	
	/**
	 * 日常事务_请假管理_我的工作_我的工作（首页）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mygzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();
	

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initMygz(rForm, myForm, request);
		// =================== end ===================
		  
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		FormModleCommon.setNdXnXqList(request);// 年度学年学期
	
		// =================== end ===================
		
		return mapping.findForward("mygzManage");
	}
	
	/**
	 * 日常事务_请假管理_我的工作_我的工作（维护）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initMysh(rForm, myForm, request);
		
		String czxm = request.getParameter("czxm");
		request.setAttribute("czxm", czxm);
		
		// 初始化项目列表
		List<HashMap<String, String>> cshXmList = service.getCshXmList(myForm,user);
		request.setAttribute("cshXmList", cshXmList);
		
		// =================== end ===================
		  
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		FormModleCommon.setNdXnXqList(request);// 年度学年学期
		// =================== end ===================

		return mapping.findForward("myshManage");
	}
	
	/**
	 * 日常事务_请假管理_结果查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mycxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		String tableName="xg_view_rcsw_qjgl_qjsq";

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initMycx(rForm, myForm, request);
		
		// =================== end ===================
		  
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		FormModleCommon.setNdXnXqList(request);// 年度学年学期
		request.setAttribute("tableName", tableName);
		// =================== end ===================

		return mapping.findForward("mycxManage");
	}
		
	/**
	 * 日常事务_请假管理_我的工作_我的请假（详细）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initQjlc(rForm, myForm, request);
		
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);
		
		String shgw = request.getParameter("shgw");
		myForm.setShgw(shgw);
		// =================== end ===================

		// ============= 获得我的审核信息 ============
		HashMap<String, String> rs = service.getMyqjInfo(myForm, user);
		rs.put("sqid", sqid);
		rs.put("shgw", shgw);
		rForm.setRs(rs);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		List<HashMap<String, String>> shList = service.getShList(myForm,user);
		request.setAttribute("shList", shList);

		return mapping.findForward("myshDetail");
	}

	/**
	 * 获得我的工作统计列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMygzList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 学年
		String xn = Base.currXn;
		myForm.setXn(xn);

		// 学期
		String xq = Base.currXq;
		myForm.setXq(xq);
		
		// 初始化
		init.initMygz(rForm, myForm, request);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		String spHtml = service.getMygzHtml(rsModel, myForm, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(null);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 获得我的审核列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMyshList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 参数
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// 操作项目
		String czxm = otherValue[0];
		myForm.setCzxm(czxm);

		// 审核岗位
		String shgw = otherValue[1];
		myForm.setShgw(shgw);
		
		// IE
		String ie = otherValue[2];
		rsModel.setIe(ie);

		// 路径
		String stylePath = otherValue[3];
		rsModel.setStylePath(stylePath);
		
		// 学年
		String xn = Base.currXn;
		myForm.setXn(xn);

		// 学期
		String xq = Base.currXq;
		myForm.setXq(xq);

		// 初始化
		init.initMysh(rForm, myForm, request);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		ArrayList<String[]> rsArrList = service.getMyshList(myForm, user);
		String spHtml = service.getMyshHtml(rsModel, myForm, rsArrList, user);
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
	 * 获得结果查询列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMycxList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 参数
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE
		String ie = otherValue[0];
		rsModel.setIe(ie);

		// 学年
		String xn = Base.currXn;
		myForm.setXn(xn);

		// 学期
		String xq = Base.currXq;
		myForm.setXq(xq);

		// 初始化
		init.initMycx(rForm, myForm, request);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		ArrayList<String[]> rsArrList = service.getMycxList(myForm, user);
		String spHtml = service.getMycxHtml(rsModel, myForm, rsArrList, user);
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
	 * 获得岗位信息情况
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getShgwInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();
		
		String czxm = request.getParameter("czxm");
		myForm.setCzxm(czxm);

		List<HashMap<String, String>> list = service.getShgwInfoList(myForm, user);
		request.setAttribute("gwList", list);
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));
		
		return mapping.findForward("shgwInfo");
	}

	/**
	 * 获得审核信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getShInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		//申请ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		//审核岗位
		String shgw = request.getParameter("shgw");
		myForm.setShgw(shgw);
		// ============= 初始化各变量的值 end============

		// ==================构建前台页面========================
		service.createShInfoHtml(myForm,response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 日常事务_请假管理_我的工作_我的请假（详细）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jgcxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		String doType=request.getParameter("doType");
		
		String forward="print".equalsIgnoreCase(doType)?"qjglPrint" : "jgcxDetail";
		// ============= 初始化各变量的值 ============
		// 初始化
		init.initQjlc(rForm, myForm, request);
		
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);
		
		// =================== end ===================

		// ============= 获得我的审核信息 ============
		HashMap<String, String> rs = service.getMyqjInfo(myForm, user);
		rs.put("sqid", sqid);
		
		List<HashMap<String, String>> qjshList = service.getQjshInfo(myForm, user);
		request.setAttribute("qjshList",qjshList);
		request.setAttribute("qjshlength",qjshList.size());
		
		rForm.setRs(rs);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward(forward);
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

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// 主键
		String[] pk = null;	
		if (!Base.isNull(request.getParameter("pk"))) {
			pk = request.getParameter("pk").split("!!@@!!");
			myForm.setCheckVal(pk);
		}
		
		// 审核状态
		String shzt = "";
		if (!Base.isNull(request.getParameter("shzt"))) {
			shzt = service.unicode2Gbk(request.getParameter("shzt"));
			myForm.setShzt(shzt);
		}
		
		// 申请ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		// 审核岗位
		String shgw = request.getParameter("shgw");
		myForm.setShgw(shgw);

		// 审核意见
		String shyj = "";
		if (!Base.isNull(request.getParameter("shyj"))) {
			shyj = service.unicode2Gbk(request.getParameter("shyj"));
			myForm.setShyj(shyj);
		}
		
		// ==================执行保存操作========================
		boolean flag = service.saveShzt(myForm, user);
		String message = flag ? "审核成功" : "审核失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 教师照片上传
	 */
	public ActionForward uploadQjcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		
		String result = service.saveQjcl(myForm);
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * 日常事务结果查询自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward jgcxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		RcswQjglForm model = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = service.getMycxExportList(model,user);
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
