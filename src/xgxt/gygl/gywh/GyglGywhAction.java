package xgxt.gygl.gywh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.action.init.InitGygl;
import xgxt.comm.MessageInfo;
import xgxt.form.User;
import xgxt.gygl.jbsz.GyglJbszForm;
import xgxt.gygl.jbsz.GyglJbszService;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_公寓维护-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class GyglGywhAction extends BasicAction {

	/**
	 * 公寓管理_公寓维护_基础数据维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcsjwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		User user = getUser(request);// 用户对象
		
		// 文本配置
		MessageResources message = getResources(request);
		
		String doType=request.getParameter("doType");
		myForm.setCzxq(jbszModel.getCzxq());
		myForm.setCzyq(jbszModel.getCzyq());

		//模块类型
		String mklx = request.getParameter("mklx");
		if(!"是".equalsIgnoreCase(jbszModel.getCzxq())
				){
			mklx = ("".equalsIgnoreCase(mklx) || Base.isNull(mklx)) ? "yq" : mklx;
		}
		if(!"是".equalsIgnoreCase(jbszModel.getCzxq())
				&&!"是".equalsIgnoreCase(jbszModel.getCzyq())){
			mklx = "yq".equalsIgnoreCase(mklx)? "ld" : mklx;
		}
		mklx = ("".equalsIgnoreCase(mklx) || Base.isNull(mklx)) ? "xq" : mklx;
		myForm.setMklx(mklx);
		
		//删除操作
		if("del".equalsIgnoreCase(doType)){
			boolean result=service.delJcsj(myForm,request);
			request.setAttribute("result", result);
		}
		
		//判断数据库中是否已有设置
		String sfsz = jbszModel.getSfsz();
		if ("否".equalsIgnoreCase(sfsz)) {
			
			String msg = "公寓基本设置尚未完成，无法进行接下去的操作，请联系管理员或相关人员对其进行设置，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (!"是".equalsIgnoreCase(jbszModel.getCzxq())
				&& !"是".equalsIgnoreCase(jbszModel.getCzyq())) {
			
			String msg = "公寓校区、园区不存在,无需维护信息,请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//删除检测加载条件
		if("scjc".equalsIgnoreCase(doType)){
			service.setSearchModel(myForm, request);
		}
		//初始化导入导出表
		service.JcmkxxInit(myForm);
		request.setAttribute("realTable", myForm.getRealTable());
		request.setAttribute("tableName", myForm.getTableName());
		// 高级查询
		request.setAttribute("searchType", mklx);
		// 页签数据加载
		request.setAttribute("pageCard", service.getJcsjCard(jbszModel));
		// 高级查询页面返回值
		// 结果集
		List<String[]> rs = service.getJcsjList(myForm, user, request);
		String rsNum = (rs != null && rs.size() > 0) ? String
				.valueOf(rs.get(0).length - 1) : "0";

		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", service.getJcsjTopTr(myForm,message));
		request.setAttribute("mklx", mklx);

		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		request.setAttribute("path", "gygl_gywh_jcsj.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("jcsjwh");
	}
	
	/**
	 * 公寓管理_公寓维护_业务数据维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ywsjwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		String doType=request.getParameter("doType");
		String mklx = request.getParameter("mklx");
		mklx = ("".equalsIgnoreCase(mklx) || Base.isNull(mklx)) ? "qsfp" : mklx;
		myForm.setMklx(mklx);
		
		myForm.setFpdx(jbszModel.getFpdx());
		if("del".equalsIgnoreCase(doType)){
			boolean result=service.delYwsj(myForm,request);
			request.setAttribute("result", result);
		}
		
		//判断数据库中是否已有设置
		String sfsz = jbszModel.getSfsz();
		if ("否".equalsIgnoreCase(sfsz)) {
			String msg = "公寓基本设置尚未完成，无法进行接下去的操作，请联系管理员或相关人员对其进行设置，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} 
		
		//删除检测加载条件
		if("scjc".equalsIgnoreCase(doType)){
			service.setSearchModel(myForm, request);
		}
		
		service.YwmkxxInit(myForm);
		// 结果集
		List<String[]> rs = service.getYwsjList(myForm, request);
		String rsNum = (rs != null && rs.size() > 0) ? String
				.valueOf(rs.get(0).length-1) : "0";

		request.setAttribute("searchType", mklx);
		request.setAttribute("realTable", myForm.getRealTable());
		request.setAttribute("tableName", myForm.getTableName());
		request.setAttribute("pageCard", service.getYwsjCard());
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", service.getYwsjTopTr(myForm));
		request.setAttribute("mklx", mklx);
		request.setAttribute("path", "gygl_gywh_ywsj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ywsjwh");
	}

	/**
	 * 公寓管理_公寓维护_校区信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xqxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglGywhService service = new GyglGywhService();
		GyglGywhForm myForm = (GyglGywhForm) form;
		String doType = request.getParameter("doType");
		String xqdm = request.getParameter("xqdm");
		boolean result = false;
		HashMap<String, String> xqxxMap = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(doType)) {
			result = service.addXqxx(myForm);
			request.setAttribute("result", result);
		} else if ("modi".equalsIgnoreCase(doType)) {
			myForm.setXqdm(xqdm);
			result = service.modiXqxx(myForm);
			request.setAttribute("result", result);
		} else if ("update".equalsIgnoreCase(doType)) {
			myForm.setXqdm(xqdm);
			xqxxMap = service.getXqxx(myForm);
		}

		request.setAttribute("rs", xqxxMap);
		request.setAttribute("doType", doType);
		return mapping.findForward("xqxxwh");
	}

	/**
	 * 公寓管理_公寓维护_园区信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yqxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglGywhService service = new GyglGywhService();
		GyglGywhForm myForm = (GyglGywhForm) form;
		String doType = request.getParameter("doType");
		String yqdm = request.getParameter("yqdm");
		boolean result = false;
		HashMap<String, String> yqxxMap = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(doType)) {
			result = service.addYqxx(myForm);
			request.setAttribute("result", result);
		} else if ("modi".equalsIgnoreCase(doType)) {
			myForm.setYqdm(yqdm);
			result = service.modiYqxx(myForm);
			request.setAttribute("result", result);
		} else if ("update".equalsIgnoreCase(doType)) {
			myForm.setYqdm(yqdm);
			yqxxMap = service.getYqxx(myForm);
		}
		
		request.setAttribute("xqList", service.getXqList());
		request.setAttribute("rs", yqxxMap);
		request.setAttribute("doType", doType);
		return mapping.findForward("yqxxwh");
	}

	/**
	 * 公寓管理_公寓维护_楼栋信息维护管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ldwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		String doType=request.getParameter("doType");
		User user = getUser(request);// 用户对象
		
		//文本配置
		MessageResources message = getResources(request);
		
		//模块类型
		String mklx = "ldwh";
		myForm.setMklx(mklx);
		
		if ("del".equalsIgnoreCase(doType)) {
			boolean result = service.delJcsj(myForm, request);
			request.setAttribute("result", result);
		}

		// 判断数据库中是否已有设置
		String sfsz = jbszModel.getSfsz();
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
		
		service.JcmkxxInit(myForm);
		request.setAttribute("tableName", myForm.getTableName());
		request.setAttribute("realTable", myForm.getRealTable());
		request.setAttribute("rs", service.getJcsjList(myForm,user,request));
		request.setAttribute("topTr", service.getJcsjTopTr(myForm,message));
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("mklx", mklx);
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		request.setAttribute("path", "gygl_gywh_ldwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ldwhManage");
	}
	
	/**
	 * 公寓管理_公寓维护_楼栋信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ldxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String,String>ldxxMap=new HashMap<String,String>();
		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		
		String lddm = request.getParameter("lddm");
		String doType=request.getParameter("doType");
		// 默认性别
		String xbxd = myForm.getXbxd();
		
		if(Base.isNull(xbxd)){
			ldxxMap.put("xbxd", "混合");
		}
		
		String sfsz = jbszModel.getSfsz();
		if ("否".equalsIgnoreCase(sfsz)) {
			String msg = "公寓基本设置尚未完成，无法进行接下去的操作，请联系管理员或相关人员对其进行设置，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} 
		
		boolean result=false;
		//增加
		if ("save".equalsIgnoreCase(doType)) {
			result = service.addLdxx(myForm);
			request.setAttribute("result", result);
		//修改
		} else if ("modi".equalsIgnoreCase(doType)) {
			myForm.setLddm(lddm);
			result = service.modiLdxx(myForm);
			request.setAttribute("result", result);
		//读取选中记录信息
		}else if ("update".equalsIgnoreCase(doType)) {
			myForm.setLddm(lddm);
			ldxxMap = service.getLdxx(myForm);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("lcsz",myForm.getLcsz());
		request.setAttribute("xqList",service.getXqList());
		
		if(!Base.isNull(ldxxMap.get("xqdm"))){
			myForm.setXqdm(ldxxMap.get("xqdm"));
		}else{
			List<HashMap<String,String>> xqList = service.getXqList();
			if(xqList!=null && xqList.size()>0){
				myForm.setXqdm(service.getXqList().get(0).get("dm"));
			}
		}
		
		request.setAttribute("yqList",service.getYqList(ldxxMap.get("xqdm")));
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		request.setAttribute("rs", ldxxMap);
		request.setAttribute("path", "gygl_gywh_ldwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ldxxwh");
	}
	
	/**
	 * 公寓管理_公寓维护_显示楼栋详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ldxxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String,String>ldxxMap=new HashMap<String,String>();
		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		User user= getUser(request);// 用户对象
		
		String pkValue=request.getParameter("pkValue");
		
		//楼栋信息
		myForm.setLddm(pkValue);
		ldxxMap = service.getLdxx(myForm);
		
		//所在楼栋信息
		myForm.setCs(null);
		//分配标记为保留
		myForm.setFpbj("保留");
		myForm.setFpdx(jbszModel.getFpdx());
		
		user.setUserStatus("xx");
		myForm.setUser(user);
		List<HashMap<String,String>>qsxxList=service.getQsfpList(myForm);
		//楼层详细信息
		List<HashMap<String,String>>ldlcxxList=service.getLdcsList(myForm);
	
		request.setAttribute("lcsz",myForm.getLcsz());
		request.setAttribute("xqList",service.getXqList());
		request.setAttribute("qsxxList", qsxxList);
		request.setAttribute("ldlcxxList", ldlcxxList);
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		request.setAttribute("ldxx", ldxxMap);
		request.setAttribute("path", "gygl_gywh_ldwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ldxxxx");
	}
	
	/**
	 * 公寓管理_公寓维护_寝室信息维护管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qswhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglGywhDAO dao=new GyglGywhDAO();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		String doType=request.getParameter("doType");
		String mklx = request.getParameter("mklx");
		User user = getUser(request);// 用户对象
		
		//文本配置
		MessageResources message = getResources(request);
		mklx = "qswh";
		myForm.setMklx(mklx);
		
		//删除操作
		if("del".equalsIgnoreCase(doType)){
			boolean result=service.delJcsj(myForm,request);
			String messages = result ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", messages);
		}
		
		//判断数据库中是否已有设置
		String sfsz = jbszModel.getSfsz();
		if ("否".equalsIgnoreCase(sfsz)) {
			String msg = "公寓基本设置尚未完成，无法进行接下去的操作，请联系管理员或相关人员对其进行设置，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} 
		
		//床位生成
		if("cwsc".equalsIgnoreCase(doType)){
			GyglGywhModel model=new GyglGywhModel();
			model.setSfplcz(true);
			service.creatGyglCwxx(model,user);
			boolean flag =true;
			String messages = flag ? MessageInfo.MESSAGE_CREATE_SUCCESS
					: MessageInfo.MESSAGE_CREATE_FALSE;
			request.setAttribute("message", messages);
		}
		
		//寝室信息批量设置
		if("plsz".equalsIgnoreCase(doType)){
			boolean flag =service.updateQsxx(myForm,user);
			String messages = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", messages);
		}
		
		service.JcmkxxInit(myForm);
		request.setAttribute("tableName", myForm.getTableName());
		request.setAttribute("realTable", myForm.getRealTable());
		service.getJcColList(myForm);
		String path = "gygl_gywh_qswh.do";
		myForm.getSearchModel().setPath(path);
		
		//批量设置
		myForm.setXb("不修改");
		myForm.setKfhz("不修改");
		myForm.setFpbj("不修改");
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		
		//传递中的楼栋代码隐藏域非空自动填充到高级查询
		if(!Base.isNull(myForm.getLdHid())){
			myForm.getSearchModel().setSearch_tj_lddm(new String[]{myForm.getLdHid()});
		}
		request.setAttribute("rs", dao.getQssjList(myForm, user, request));
		request.setAttribute("topTr", service.getJcsjTopTr(myForm,message));
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("mklx", mklx);
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qswhManage");
	}
	
	/**
	 * 寝室信息维护(增加、修改)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qsxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HashMap<String,String>qsxxMap=new HashMap<String,String>();
		List<HashMap<String,String>>cwxxList=new ArrayList<HashMap<String,String>>();
		
		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		User user = getUser(request);// 用户对象
		String pkValue= request.getParameter("pkValue");
		String doType=request.getParameter("doType");
		String page="qsxxwh";
		
		boolean result=false;
		//增加
		if ("save".equalsIgnoreCase(doType)) {
			result = service.addQsxx(myForm,user);
			request.setAttribute("result", result);
		} 
		

		//修改
		if ("modi".equalsIgnoreCase(doType)) {
			result = service.modiQsxx(myForm,user);
			request.setAttribute("result", result);
			page="updateQsxx";	
		}
		
		
		String cws=InitGygl.initGygl.getCws();
		myForm.setCws(cws);
		//读取选在中记录信息
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			
			myForm.setPkValue(pkValue);
			//获取寝室详细信息
			qsxxMap = service.getQsxx(myForm);
			//获取寝室床位信息
			cwxxList = service.getCwxx(myForm);
			
			page="updateQsxx";
			
			//已有人入住
			String yyrrz="false";
			if(!"0".equalsIgnoreCase(qsxxMap.get("yzrcws"))){
				yyrrz="true";
			}
			request.setAttribute("yyrrz",yyrrz);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("lcsz",myForm.getLcsz());
		
		request.setAttribute("xqList",service.getXqList());
		request.setAttribute("yqList",service.getYqxxList());
		request.setAttribute("ldList",service.getLdList(myForm));
		
		if("add".equalsIgnoreCase(doType)){
			//默认值
			qsxxMap.put("fpbj", "一般");
			qsxxMap.put("xb", "男");
			qsxxMap.put("kfhz", "不可");
			
		}
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("cwxxList", cwxxList);
		request.setAttribute("cwsList", service.getCwsList(myForm));
		request.setAttribute("rs", qsxxMap);
		request.setAttribute("path", "gygl_gywh_qswh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward(page);
	}
	
	public ActionForward zsxxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MessageResources message = getResources(request);
		GyglGywhService service=new GyglGywhService();
		GyglGywhForm myForm=(GyglGywhForm)form;
		
		String pkValue=request.getParameter("pkValue");
		String doType=request.getParameter("doType");
		String xh=request.getParameter("xh");
		myForm.setXh(xh);
		String[]colList={"xh","xm","xb","xymc","zymc","bjmc","nj","ldmc","cs","qsh","cwh"};
		HashMap<String, String> stuInfo =service.getRsInfo("xg_view_gygl_xszsxx", "xh", xh, colList);
		request.setAttribute("xsxx", stuInfo);
		ArrayList<String[]>rs=(ArrayList<String[]>)service.getXszsxxInfo(myForm);
		request.setAttribute("rs",rs);
		myForm.setMklx("zsxx");
		request.setAttribute("topTr", service.getJcsjTopTr(myForm,message ));
		//	-------------------自动补空行功能数据设置----------------------
		// 结果集名称
		String rsName = "rs";
		// 是否需要checkbox
		String isCheckBox = "no";
		// 显示的起始列
		String startNum = "0";
		// 显示列数
		String showNum =String.valueOf(4);
		
		
		if(rs!=null){
			if(rs.size()>7){
				myForm.getPages().setPageSize(rs.size());
			}else{
				
				myForm.getPages().setPageSize(7);
			}
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rsName", rsName);
		request.setAttribute("isCheckBox", isCheckBox);
		request.setAttribute("startNum", startNum);
		request.setAttribute("showNum", showNum);
		request.setAttribute("pObj",myForm.getPages());
		
		// -------------------自动补空行功能数据设置end----------------------
		return mapping.findForward("zsxxInfo");
	}
	
	/**
	 * 寝室自动生成
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zdscManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HashMap<String,String>qsxxMap=new HashMap<String,String>();
		User user = getUser(request);// 用户对象
		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form; 
		String tzPath=request.getParameter("tzPath");
		String doType=request.getParameter("doType");
		
		String[] pkValue=myForm.getCheckVal();
		
		if(pkValue!=null && pkValue.length>0){
			qsxxMap.put("lddm", pkValue[0]);
		}
		
		if(!Base.isNull(tzPath)){
			if("qswh".equalsIgnoreCase(tzPath)){
				tzPath="gygl_gywh_qswh.do";
			}else if("ldwh".equalsIgnoreCase(tzPath)){
				tzPath="gygl_gywh_ldwh.do";
			}
			request.setAttribute("tzPath", tzPath);
		}
		
		if("zdsc".equalsIgnoreCase(doType)){
			
			boolean blog=service.zdscQs(myForm,user);
			request.setAttribute("ldHid", myForm.getLddm());
			myForm.setLddm(null);
			myForm.setYqdm(null);
			myForm.setXqdm(null);
			request.setAttribute("result", blog);
		}
		
		// =================自动生成寝室的编号规则========================
		String bhgzxz=InitGygl.initGygl.getBhgz();
		String[]bhgzxzArr=bhgzxz.split("!!@@!!");
		//设置默认编号规则
		service.setQsBhgz(myForm);
		request.setAttribute("bhgzxzArr", bhgzxzArr);
		//  ===================end ======================
		
		request.setAttribute("doType", doType);
		request.setAttribute("lcsz",myForm.getLcsz());
		
		request.setAttribute("xqList",service.getXqList());
		request.setAttribute("yqList",service.getYqxxList());
		request.setAttribute("ldList",service.getLdList(myForm));
		
		request.setAttribute("message", myForm.getQss());
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		request.setAttribute("rs", qsxxMap);
		request.setAttribute("path", "gygl_gywh_zdsc.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zdscManage");
	}
}
