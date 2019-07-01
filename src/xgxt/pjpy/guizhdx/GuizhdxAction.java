package xgxt.pjpy.guizhdx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.DtjsForm;
import xgxt.dtjs.DtjsTyService;
import xgxt.form.User;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.lsxy.LsxyPjpyService;
import xgxt.pjpy.tginterface.PjpyCommonInterface;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyService;
import xgxt.szdw.server.common.CommonService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

import com.zfsoft.basic.BasicAction;
import common.Globals;
import common.GlobalsVariable;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 屈朋辉
 * @version 1.0
 */
public class GuizhdxAction extends BasicAction {

	/**
	 * 先进班级管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());
		
		GuizhdxForm myForm = (GuizhdxForm) form;
		GuizhdxService service = new GuizhdxService();

		String yhlx = "";
		String realTable = "gzdx_xjbj";
		String tableName = "view_gzdx_xjbj";
		String[] colList = new String[] { "disabled", "bgcolor", "pkValue",
				"xn", "xymc", "bjmc", "bzrxm", "tyrs", "bjrs", "dyrs", "fdysh",
				"xysh", "xxsh" };
		String doType = request.getParameter("doType");
		String shjb = "3";//审核级别

		if (isFdy) {
			yhlx="fdy";
			myForm.setQueryequals_xydm(userDep);
		} else if ("xy".equalsIgnoreCase(userType)) {
			yhlx = "xy";
			myForm.setQueryequals_xydm(userDep);
		} else if ("stu".equals(userType)) {
			yhlx = "stu";
		} else {
			yhlx = "xx";
		}

		//查询
		if (!Base.isNull(doType) && "query".equalsIgnoreCase(doType)) {
			service.getCustomAudiColumn(request,yhlx,String.valueOf(shjb));
			this.selectPageDataByPagination(request, myForm, realTable,
					tableName, colList);
		}

		//导出
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			this.expPageData(request, response, realTable, tableName, colList);
			return mapping.findForward("");
		}
		
		//删除
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {

			this.deleteOperation(request, realTable);
		}

		service.setList(request, "");
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		request.setAttribute("path", "guizdx_xjbjManage.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);

		return mapping.findForward("xjbjManage");
	}

	
	/**
	 * 先进班级维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward xjbjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");
		String userDep = (String) session.getAttribute("userDep");

		GuizhdxService service = new GuizhdxService();
		
		String realTable = "gzdx_xjbj";
		String tableName = "view_gzdx_xjbj";
		String xn = Base.getJxjsqxn();
		String bjdm = request.getParameter("bjdm");
		String doType = request.getParameter("doType");
		HashMap<String, String> bjxx = new HashMap<String, String>();
		
		if ("stu".equals(userType)) {
			bjxx.putAll(service.getStuInfo(userName));
		} else if ("xy".equals(userType)) {
			bjxx.put("xydm", userDep);
		}  else {
			bjxx.put("xydm", request.getParameter("xydm"));
		}
		
		if (!Base.isNull(bjdm)) {
			bjxx.put("bjdm", bjdm);
			bjxx.put("nj", request.getParameter("nj"));
			bjxx.put("zydm", request.getParameter("zydm"));
		}
		
		bjxx.put("xn", xn);
		bjxx.put("nd", Base.getJxjsqnd());
		bjxx.put("xqmc", Base.getJxjsqxqmc());
		bjxx.put("xq", Base.getJxjsqxq());
		
		// 保存
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			insertOperation(request, realTable);
			selectPageDataByOne(request, realTable, tableName, request.getParameter("save_bjdm")+xn);
			bjxx = (HashMap<String, String>) request.getAttribute("rs");
		}
		
		if (!bjxx.isEmpty()) {
			/* 加载基本数据 */
			
			//班主任
			bjxx.put("bzrxm", service.getBzr(bjxx.get("bjdm")));
			
			// 党团相关
			DtjsTyService dtService = new DtjsTyService();
			DtjsForm dtForm = new DtjsForm();

			dtForm.setXn(xn);
			dtForm.setBjdm(bjxx.get("bjdm"));
			//班级团员数
			String tyrs = dtService.getDtjsRsJk(dtForm, "ty").get("tyrs");
			//班级党员数
			HashMap<String, String> dy = dtService.getDtjsRsJk(dtForm, "dy");
			//入党和入党积极分子的人数、比例
			HashMap<String, String> dyb = dtService.getDtjsRsJk(dtForm, "dybl");

			String dyrs = dy.get("dyrs");
			String jjfzrs = dy.get("jjfzrs");
			String dybl = dyb.get("dybl");
			String jjfzbl = dyb.get("jjfzbl");
			String bjrs = dyb.get("bjrs");
			
			bjxx.put("tyrs", tyrs);
			bjxx.put("dyrs", dyrs);
			bjxx.put("dybl", dybl);
			bjxx.put("jjfzrs", jjfzrs);
			bjxx.put("jjfzbl", jjfzbl);
			bjxx.put("bjrs", bjrs);

			// 学生干部人数
			CommonService gbService = new CommonService();
			String gbrs = gbService.getBjgbRsForBjdm(bjxx.get("bjdm"));

			bjxx.put("gbrs", gbrs);

			// 评奖相关
			PjpyCommonInterface pjpy = new PjpyCommonInterface();

			List<String[]> cfList = pjpy.queryBjStuCfxx(bjxx); // 学生处分信息
			//班级学生补考人数及补考率
			List<HashMap<String, String>> bkxx = pjpy.queryBjStuBjgl(bjxx);
			//学生评奖评优信息
			List<String[]> pjpyxx = service.getStuPjpyxx(bjxx);

			request.setAttribute("cfList", cfList);
			request.setAttribute("bkxx", bkxx);
			request.setAttribute("pjpyxx", pjpyxx);
		}

		request.setAttribute("rs", bjxx);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("xjbjUpdate");
	}

	
	/**
	 * 先进班级批量审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());

		GuizhdxForm myForm = (GuizhdxForm) form;
		GuizhdxService service = new GuizhdxService();

		String realTable = "gzdx_xjbj";
		String tableName = "view_gzdx_xjbj";
		String[] colList = null;
		String doType = request.getParameter("doType");
		String yhlx = "";
		String shjb = "3";//审核级别

		//三级审核
		if (isFdy) {
			 yhlx = "fdy";
			colList = new String[] { "disabled", "bgcolor", "pkValue", "xn",
					"bjmc", "bzrxm", "tyrs","bjrs", "dyrs", "fdysh" };
			request.setAttribute("shzd", "fdysh");
			myForm.setQueryequals_xydm(userDep);
		} else if ("xy".equalsIgnoreCase(userType)) {
			 yhlx = "xy";
			request.setAttribute("shzd", "xysh");
			colList = new String[] { "disabled", "bgcolor", "pkValue", "xn",
					"bjmc", "bzrxm", "tyrs", "bjrs", "dyrs", "xysh" };
			myForm.setQueryequals_xydm(userDep);
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			yhlx = "xx";
			request.setAttribute("shzd", "xxsh");
			colList = new String[] { "disabled", "bgcolor", "pkValue", "xn",
					"bjmc", "bzrxm", "tyrs", "bjrs", "dyrs", "xxsh" };
		} else {
			request.setAttribute("errMsg", "对不起，您无权访问此页！");
			return new ActionForward("/errMsg.do", false);
		}

		//查询
		if (!Base.isNull(doType) && "query".equalsIgnoreCase(doType)) {
			service.getCustomAudiColumn(request,yhlx,String.valueOf(shjb));
			this.selectPageDataByPagination(request, myForm, realTable,
					tableName, colList);
		}

		//批量审核
		if (!Base.isNull(doType) && "sh".equalsIgnoreCase(doType)) {
			this.auditingBatchOperation(request, realTable);
		}

		if ("xy".equals(userType) && !isFdy) {
			request.setAttribute("syme", service.getXjbjSyme(request.getParameter("queryequals_xn"), userDep));
		}
		
		service.setList(request, "");
		request.setAttribute("xn", Base.getJxjsqxn());
		
		request.setAttribute("path", "guizdx_xjmjsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjbjSh");
	}

	
	/**
	 * 先进班级单条维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward xjbjOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse reponse)
			throws Exception {

		String realTable = "gzdx_xjbj";
		String tableName = "view_gzdx_xjbj";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");

		GuizhdxService service = new GuizhdxService();

		//加载数据
		this.selectPageDataByOne(request, realTable, tableName, pkValue);
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		//	党团相关
		DtjsTyService dtService = new DtjsTyService();
		DtjsForm dtForm = new DtjsForm();

		dtForm.setXn(rs.get("xn"));
		dtForm.setBjdm(rs.get("bjdm"));

		//班级党员数
		HashMap<String, String> dy = dtService.getDtjsRsJk(dtForm, "dy");
		//入党和入党积极分子的人数、比例
		HashMap<String, String> dyb = dtService.getDtjsRsJk(dtForm, "dybl");

		String jjfzrs = dy.get("jjfzrs");
		String dybl = dyb.get("dybl");
		String jjfzbl = dyb.get("jjfzbl");

		rs.put("jjfzrs", jjfzrs);
		rs.put("dybl", dybl);
		rs.put("jjfzbl", jjfzbl);

		//评奖相关
		PjpyCommonInterface pjpy = new PjpyCommonInterface();

		List<String[]> cfList = pjpy.queryBjStuCfxx(rs); //学生处分信息
		//班级学生补考人数及补考率
		List<HashMap<String, String>> bkxx = pjpy.queryBjStuBjgl(rs);
		//学生评奖评优信息
		List<String[]> pjpyxx = pjpy.queryBjStuPjpyxx(rs);

		request.setAttribute("cfList", cfList);
		request.setAttribute("bkxx", bkxx);
		request.setAttribute("pjpyxx", pjpyxx);

		//修改
		if (!Base.isNull(doType) && "update".equalsIgnoreCase(doType)) {
			this.updateOperation(request, realTable);
		}

		//单个审核
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			this.updateOperation(request, realTable);
		}

		service.setList(request, "");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		return mapping.findForward("xjbjOne");
	}

	
	/**
	 * 先进班级打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward xjbjDy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GuizhdxService service = new GuizhdxService();
		String realTable = "gzdx_xjbj";
		String tableName = "view_gzdx_xjbj";
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		
		if (!Base.isNull(pkValue)) {
			this.selectPageDataByOne(request, realTable, tableName, pkValue);
			rs = (HashMap<String, String>) request.getAttribute("rs");
	
			//			党团相关
			DtjsTyService dtService = new DtjsTyService();
			DtjsForm dtForm = new DtjsForm();
	
			dtForm.setXn(rs.get("xn"));
			dtForm.setBjdm(rs.get("bjdm"));
	
			//班级党员数
			HashMap<String, String> dy = dtService.getDtjsRsJk(dtForm, "dy");
			//入党和入党积极分子的人数、比例
			HashMap<String, String> dyb = dtService.getDtjsRsJk(dtForm, "dybl");
	
			String jjfzrs = dy.get("jjfzrs");
			String dybl = dyb.get("dybl");
			String jjfzbl = dyb.get("jjfzbl");
	
			rs.put("jjfzrs", jjfzrs);
			rs.put("dybl", dybl);
			rs.put("jjfzbl", jjfzbl);
	
			//评奖相关
			PjpyCommonInterface pjpy = new PjpyCommonInterface();
	
			List<String[]> cfList = pjpy.queryBjStuCfxx(rs); //学生处分信息
			//班级学生补考人数及补考率
			List<HashMap<String, String>> bkxx = pjpy.queryBjStuBjgl(rs);
			List<String[]> pjpyxx = service.getStuPjpyxx(rs);//学生评奖评优信息
	
			request.setAttribute("cfList", cfList);
			request.setAttribute("bkxx", bkxx);
			request.setAttribute("pjpyxx", pjpyxx);
		}
		
		request.setAttribute("rs", rs);
		return mapping.findForward("xjbjDy");
	}

	
	/**
	 * 通用评奖奖学金审核级别设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GuizhdxService service = new GuizhdxService();
		
		String tableName = "typj_tjsz";
		String viewName = "view_typj_tjsz";
		String[] outputColumn = new String[] {"pkValue","jxjmc","jlje","fjsh"};
		String doType = request.getParameter("doType");
		
		//查询
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		//保存
		if (!Base.isNull(doType) && "save".equals(doType)) {
			this.savePageDataBatch(request, tableName, viewName);
		}
		
		
		//批量保存
		if (!Base.isNull(doType) && "saveBatch".equals(doType)) {
			String[] jxjdm = request.getParameterValues("primarykey_cbv");
			String shjb = request.getParameter("shjb");
			
			if ( null != jxjdm && jxjdm.length > 0) {
				request.setAttribute("result", service.saveBatchShjb(jxjdm, shjb, "jxj"));
			}
		}
		
		//导出
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			
			String[] colList = new String[] {"jxjdm","jxjmc","jlje","fjsh"};
			this.expPageData(request, response, tableName, viewName, colList);
			
			return mapping.findForward("");
		}
		
		service.setList(request, "tjsz");
		request.setAttribute("tname", "jxj");
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "typj_tjsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tjsz");
	}
	

	
	/**
	 * 奖学金申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward jxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		GuizhdxService service = new GuizhdxService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyTyService tyService = new PjpyTyService();
		String tableName = "typj_jxjsqb";
		String viewName = "view_typj_jxjsqb";
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		
		//---------2010.10.9 by lr------------
		//判断是否开放申请
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_JXJSQ)
				&& tyService.checkKgflag()){
			String msg = "该功能暂时不开放操作！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}		
		//---------end2010.10.9 by lr------------
		
		//学生用户
		if ("stu".equals(userType)) {
			xh = userName;
		}
		
		//---------2010.10.9 by lr------------
		//判断学生是否可申请
		if(StringUtils.isNotNull(request.getParameter("save_xh"))
				&& StringUtils.isNotNull(request.getParameter("save_jxjdm"))
				&& Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)
				&& !service.xssqFlag(request.getParameter("save_xh"),request.getParameter("save_jxjdm"),GlobalsVariable.PJPY_JXJ)){
			String msg = "你当前未被指定申请该奖学金！";
			request.setAttribute("yhInfo", msg);
		}
		//---------end 2010.10.9 by lr------------
		
		//保存
		//---------2010.10.9 by lr 增加了yhInfo信息的判断------------
		if (!Base.isNull(doType) 
				&& "save".equals(doType) 
				&& (request.getAttribute("yhInfo")== null 
				|| StringUtils.isNull(request.getAttribute("yhInfo").toString()))) {
			xh = request.getParameter("save_xh");
			//-----------2010.10.13 by lr--------
			JxjpdxxModel jxjpdModel = new JxjpdxxModel();
			jxjpdModel.setXh(xh);
			jxjpdModel.setXn(request.getParameter("save_xn"));
			jxjpdModel.setXq(request.getParameter("save_xq"));
			jxjpdModel.setNd(request.getParameter("save_nd"));
			jxjpdModel.setJxjdm(request.getParameter("save_jxjdm"));
			//-----------end2010.10.13 by lr--------
			
			String pkValue = xh
							 +jxjpdModel.getXn()
							 +jxjpdModel.getJxjdm()
							 +request.getParameter("save_sqsj")
							 +jxjpdModel.getXq()
							 +jxjpdModel.getNd();
			
			//-----------2010.10.13 by lr 增加条件判断------------
			boolean result = true;
			HashMap<String, String> tjInfo = new HashMap<String, String>();
			tjInfo = service.checkSqtj(jxjpdModel, GlobalsVariable.PJPY_JXJ);
			result = "true".equalsIgnoreCase(tjInfo.get("result")) ? true : false;
			if(!result){
				request.setAttribute("message", tjInfo.get("message"));
				request.setAttribute("result", result);
			}
			//-----------end 2010.10.13 by lr------------
			if(result){
				this.insertOperation(request, tableName);
			}
			this.selectPageDataByOne(request, tableName, viewName, pkValue);
			
			HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
			rs.putAll(service.getStuInfo(rs.get("xh")));
			request.setAttribute("rs",rs );
			request.setAttribute("pkValue", pkValue);
			
		}
		
		//违纪、成绩、综测分及排名、学生基本信息
		if (!Base.isNull(xh)) {
			request.setAttribute("cfxx", service.getStuCfxx(xh,xn ));
			request.setAttribute("cjxx", service.getStuCjxx(xh, xn));
			request.setAttribute("zcpm", service.getZcfPm(xh, xn));
			
		
			HashMap<String, String> stuInfo = service.getStuInfo(xh);
			stuInfo.putAll(service.getXsfzxx(xh));
			if(Globals.XXDM_GUIZHDX.equals(Base.xxdm)){
				stuInfo.putAll(service.getCjpmbXx(xh));
			}
			stuInfo.put("sqly", request.getParameter("save_sqly"));
			stuInfo.put("yhkh", request.getParameter("save_yhkh"));
			stuInfo.put("sfqf", request.getParameter("save_sfqf"));
			request.setAttribute("rs",stuInfo );	
		}
		
		
		/*
		 * 宁波城市
		 */
		if (Globals.XXDM_NBCSZYJSXY.equals(Base.xxdm) 
				&& !Base.isNull(xh) 
				&& null == doType) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("xh", xh);
			map.put("xn", xn);
			
			//品行等级
			request.setAttribute("pxdj", service.getPxdj(map));
			//学绩分及排名
			request.setAttribute("xjf", service.getXjf(map));
			//班级人数
			request.setAttribute("bjrs", service.getBjrs(xh));
		}
		
		service.setList(request, "tjsz");
		
		//=========2010.10.11 by lr=========
		//根据申请周期获取奖学金申请时间信息
		request.setAttribute("sqsjInfo",service.getJxjSqsjMap());
		//=========end 2010.10.11 by lr=========
		
		request.setAttribute("path", "typj_jxjsq.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("lx", request.getParameter("lx"));
		return mapping.findForward("jxjsq");

	}
	
	
	
	/**
	 * 奖学金审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		GuizhdxForm myForm = (GuizhdxForm) form;
		GuizhdxService service = new GuizhdxService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyZjkjxyService zjkjService = new  PjpyZjkjxyService();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String jxjdm = request.getParameter("queryequals_jxjdm");
		
		String tableName = "typj_jxjsqb";
		String viewName = "view_typj_jxjsqb";

		String[] shOneList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "nj", "xymc", "bjmc", "xn", "jxjmc", "jlje", "shzt" };
		String[] fdyshList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "nj", "xymc", "bjmc", "xn", "jxjmc", "jlje",
				"fdysh" };
		String[] xyshList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "nj", "xymc", "bjmc", "xn", "jxjmc", "jlje", "xysh" };
		String[] xxshList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "nj", "xymc", "bjmc", "xn", "jxjmc", "jlje", "xxsh" };
		
		
		// 权限控制
		if ("stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起,您无权访问此页！");
			return new ActionForward("/errMsg.do",false);
		} else if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		//---------2010.10.9 by lr------------
		//判断是否可审核
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_JXJSH)
				&& zjkjService.checkKgflag()){
			String msg = "审核功能暂时不开放操作，仅限查询！";
			request.setAttribute("yhInfo", msg);
		}
		//---------end2010.10.9 by lr------------
		
		
		//根据奖学金取审核级别
		if (!Base.isNull(jxjdm)) {
			
			int shjb = Integer.parseInt(service.getShjb(jxjdm));
			
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("tableName", tableName);
			map.put("viewName", viewName);
			map.put("shjb", shjb);
			map.put("shOneList", shOneList);
			map.put("fdyshList", fdyshList);
			map.put("xyshList", xyshList);
			map.put("xxshList", xxshList);
			
			this.typjSh(form, request, map);
		}
		//==========2010.10.9 by lr===========
		String bzrSh = service.getBzrshFlag();//班主任是否审核,默认不审核
		request.setAttribute("bzrSh", bzrSh);
		//==========end 2010.10.9 by lr===========
		
		service.setList(request, "tjsz");
		request.setAttribute("xn", Base.getJxjsqxn());
		request.setAttribute("path", "typj_jxjsh.do");
		//==========2010.10.9 by lr===========
		request.setAttribute("nd", Base.getJxjsqnd());
		request.setAttribute("xq", Base.getJxjsqxq());
		request.setAttribute("pjzq", Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm) 
				                           ? zjkjService.getPjpySqzq() : "xn");//评奖周期
		//==========end2010.10.9 by lr===========
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxjsh");

	}
		

	
	/**
	 * 荣誉称号审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		GuizhdxForm myForm = (GuizhdxForm) form;
		GuizhdxService service = new GuizhdxService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String rychdm = request.getParameter("queryequals_rychdm");
		
		String tableName = "typj_rychsq";
		String viewName = "view_typj_rychsq";		
		
		String[] shOneList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "xymc", "bjmc", "rychmc", "sqsj", "shzt" };
		String[] fdyshList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "xymc", "bjmc", "rychmc", "sqsj", "fdysh" };
		String[] xyshList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "xymc", "bjmc", "rychmc", "sqsj", "xysh" };
		String[] xxshList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "xymc", "bjmc", "rychmc", "sqsj", "xxsh" };
		
		
		//权限控制
		if ("stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起,您无权访问此页！");
			return new ActionForward("/errMsg.do",false);
		} else if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		//---------2010.10.9 by lr------------
		//判断是否可审核
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_JXJSH)
				&& zjkjService.checkKgflag()){
			String msg = "审核功能暂时不开放操作，仅限查询！";
			request.setAttribute("yhInfo", msg);
		}
		//---------end 2010.10.9 by lr------------
		
		//根据奖学金取审核级别
		if (!Base.isNull(rychdm)) {
			
			int shjb = Integer.parseInt(service.getShjbForRych(rychdm));
			
			
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("tableName", tableName);
			map.put("viewName", viewName);
			map.put("shjb", shjb);
			map.put("shOneList", shOneList);
			map.put("fdyshList", fdyshList);
			map.put("xyshList", xyshList);
			map.put("xxshList", xxshList);
			
			this.typjSh(form, request, map);
		}
		
		//==========2010.10.9 by lr===========
		String bzrSh = service.getBzrshFlag();//班主任是否审核,默认不审核
		request.setAttribute("bzrSh", bzrSh);//班主任是否审核
		//==========end 2010.10.9 by lr===========
		
		service.setList(request, "rych");
		request.setAttribute("xn", Base.getJxjsqxn());
		//==========2010.10.9 by lr===========
		request.setAttribute("nd", Base.getJxjsqnd());
		request.setAttribute("xq", Base.getJxjsqxq());
		request.setAttribute("pjzq", Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm) 
				                           ? zjkjService.getPjpySqzq() : "xn");//评奖周期
		//==========end2010.10.9 by lr===========
		request.setAttribute("path", "typj_yrchsh.do");		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rychsh");

	}
	
	
	
	/**
	 * 审核
	 * @param form
	 * @param request
	 * @param tableName
	 * @param viewName
	 * @param colList
	 */
	public void typjSh(ActionForm form, HttpServletRequest request,
			HashMap<String,Object> values) {
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = (String) session.getAttribute("userName");
		String isFdy = session.getAttribute("isFdy").toString();
		//===============2010.10.9 by lr=========
		if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			isFdy = "true".equalsIgnoreCase(isFdy) ? isFdy :  session.getAttribute("isBzr").toString();
		}
		//===============end 2010.10.9 by lr=========
		
		GuizhdxForm myForm = (GuizhdxForm) form;
		GuizhdxService service = new GuizhdxService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		HashMap<String,String> valueMap = new HashMap<String, String>();
		
		
		int shjb = (Integer)values.get("shjb");
		String tableName = (String) values.get("tableName");
		String viewName = (String) values.get("viewName");
		String[] shOneList = (String[]) values.get("shOneList");
		String[] fdyshList = (String[]) values.get("fdyshList");
		String[] xyshList = (String[]) values.get("xyshList");
		String[] xxshList = (String[]) values.get("xxshList");
		
		String doType = request.getParameter("doType");
		String zydm = request.getParameter("queryequals_zydm");
		String jxjdm = request.getParameter("queryequals_jxjdm");
		String rychdm = request.getParameter("queryequals_rychdm");
		String nj = request.getParameter("queryequals_nj");
		String xn = Base.getJxjsqxn();
		String yhlx = "";
		String shzd = "";//审核字段
		String[] colList = null;
		
		myForm.setQueryequals_xn(xn);
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if (!Base.isNull(jxjdm)) {
			if ("true".equals(isFdy)) {
				map.put("bmlx", "zy");
				map.put("bmdm", zydm);
			} else if("xy".equals(userType)){
				map.put("bmlx", "xy");
				map.put("bmdm", userDep);
			}
		} else if (!Base.isNull(rychdm)) {
			map.put("bmdm", userDep);
		}
		map.put("rychdm", rychdm);
		map.put("jxjdm", jxjdm);
		map.put("xn", xn);
		map.put("nj", nj);
		
		if (1==shjb) {
			colList = shOneList;
			valueMap.put("shr", userName);
			valueMap.put("shsj", GetTime.getSystemTime());
			valueMap.put("shzt", request.getParameter("shjg"));
			shzd = "shzt";
			
		} else if (2==shjb) {
			if ("xy".equals(userType)) {
				yhlx = "xy";
				colList = xyshList;
				valueMap.put("xysh", request.getParameter("shjg"));
				valueMap.put("xyshr", userName);
				valueMap.put("xyshsj", GetTime.getSystemTime());
				shzd = "xysh";
				
			} else {
				yhlx = "xx";
				colList = xxshList;
				valueMap.put("xxsh", request.getParameter("shjg"));
				valueMap.put("xxshr", userName);
				valueMap.put("xxshsj", GetTime.getSystemTime());
				shzd = "xxsh";
			}
		} else if (3==shjb) {
			if ("true".equals(isFdy)) {
				colList = fdyshList;
				yhlx = "fdy";
				
				valueMap.put("fdysh", request.getParameter("shjg"));
				valueMap.put("fdyshr", userName);
				valueMap.put("fdyshsj", GetTime.getSystemTime());
				shzd = "fdysh";
				
			} else if ("xy".equals(userType)) {
				colList = xyshList;
				yhlx = "xy";
				
				valueMap.put("xysh", request.getParameter("shjg"));
				valueMap.put("xyshr", userName);
				valueMap.put("xyshsj", GetTime.getSystemTime());
				shzd = "xysh";
			} else {
				yhlx = "xx";
				colList = xxshList;
				
				valueMap.put("xxsh", request.getParameter("shjg"));
				valueMap.put("xxshr", userName);
				valueMap.put("xxshsj", GetTime.getSystemTime());
				shzd = "xxsh";
			}
		}
		
		//查询
		if (!Base.isNull(doType) && "query".equals(doType)) {
			service.getCustomAudiColumn(request,yhlx,String.valueOf(shjb));
			this.selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		//批量审核
		if ("sh".equals(doType)) {
			
			//============2010.10.11 by lr======
			String shjg = request.getParameter("shjg");
			if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm) &&
					"通过".equalsIgnoreCase(shjg)){
				//浙江科技，判断人数限制是否超出。
				boolean result = false;
				
				//奖学金审核
				String message = zjkjService.auditingJxj(getValueArrayMap(request, PRIFIX_PRIMARY_KEY),
						                shzd,
						                tableName);
				if(StringUtils.isNull(message)){
					message = "操作成功！";
					result = true;
				}
				request.setAttribute("result", result);
				request.setAttribute("message", message);
			}
			//============end 2010.10.11 by lr======
			else{
				this.auditingBatchOperation(request, getValueArrayMap(request,
						PRIFIX_PRIMARY_KEY), valueMap, tableName);
			}
		}
		
		request.setAttribute("shjb", shjb);
		map.put("shjb", String.valueOf(shjb));
		if (Globals.XXDM_GUIZHDX.equals(Base.xxdm) && "xy".equals(userType)) {
			request.setAttribute("syme", service.getSyme(map));
		}
	}
	
	
	
	/**
	 * 奖学金维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward jxjView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GuizhdxService service = new GuizhdxService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		
		String tableName = "typj_jxjsqb";
		String viewName = "view_typj_jxjsqb";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		
		//加载数据
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		String shjb = service.getShjb(rs.get("jxjdm"));
		
		if (null != rs) {
			//处分、成绩
			request.setAttribute("cfxx", service.getStuCfxx(rs.get("xh"), rs.get("xn")));
			request.setAttribute("cjxx", service.getStuCjxx(rs.get("xh"), rs.get("xn")));
			request.setAttribute("zcpm", service.getZcfPm(rs.get("xh"), rs.get("xn")));
		}
		
		//修改
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			boolean result = false;
			//============2010.10.11 by lr======
			HashMap<String, String> shMap = getShMap(request);
			String shjg = shMap.get("shjg");
			String shzd = shMap.get("shzd");
			
			if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm) &&
					"通过".equalsIgnoreCase(shjg)){				
				//浙江科技，判断人数限制是否超出。
				HashMap<String, String[]> map = new HashMap<String, String[]>();
				map.put("cbv", new String[]{request.getParameter("pkValue")});
				
				//奖学金审核
				String message = zjkjService.auditingJxj(map,
						                shzd,
						                tableName);
				if(StringUtils.isNull(message)){
					message = "操作成功！";
					result = true;
				}
				request.setAttribute("result", result);
				request.setAttribute("message", message);
			}else{
				result = true;
			}
			//============end 2010.10.11 by lr======
			if(result){
				this.updateOperation(request, tableName);
			}
		}
		request.setAttribute("path", "typj_jxjsh.do");
		service.setList(request, "tjsz");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("shjb", shjb);
		request.setAttribute("doType", doType);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxjView");
	}
	
	
	
	/**
	 * 奖学金管理 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		GuizhdxForm myForm = (GuizhdxForm) form;
		GuizhdxService service = new GuizhdxService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		
		String tableName = "typj_jxjsqb";
		String viewName = "view_typj_jxjsqb";
		//==========2010.10.11 by lr===========
		String pjzq = Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm) ? zjkjService.getPjpySqzq() : "xn";//评奖周期
		String[] sqzqzd = zjkjService.getPjzqzd(pjzq);
		String[] outputColumn = StringUtils.joinStrArr(new String[] { "pkValue", "xh", "xm"}, 
													   sqzqzd,
				                                       new String[]{"xymc", "bjmc", "jxjmc", "jlje", "shjg" });
		String[] expColumn =StringUtils.joinStrArr(new String[] { "xh", "xm", "xb"}, 
						   sqzqzd,
		                   new String[]{ "nj", "xydm","xymc","zydm","zymc","bjdm",
				                         "bjmc", "jxjdm","jxjmc", "jlje",
				                         "fdysh", "fdyyj", "xysh", "xyyj", 
				                         "xxsh", "xxyj"});
		//==========2010.10.11 by lr===========
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		} else if ("stu".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			myForm.setQuerylike_xh(userName);
		}
		
		//查询
		if (!Base.isNull(doType) && "query".equals(doType)) {
			
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		//删除
		if (!Base.isNull(doType) && "del".equals(doType)) {
			this.deleteOperation(request, tableName);
		}
		
		//导出
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			outputColumn = new String[]{};
			this.expPageData(request, response, tableName, viewName, expColumn);
			return mapping.findForward("");
		}
		
		service.setList(request, "tjsz");
		request.setAttribute("path", "typj_jxjcx.do");
		request.setAttribute("realTable", tableName);
		request.setAttribute("pjzq", pjzq);//评奖周期
		FormModleCommon.setNdXnXqList(request);//学年年度学期列表
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxjcx");
	}
	


	/**
	 * 荣誉称号审核级别设置 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychShsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GuizhdxService service = new GuizhdxService();
		
		String tableName = "typj_rychshjb";
		String viewName = "view_typj_rychshjb";
		String[] outputColumn = new String[] {"pkValue","rychmc","shjb"};
		String doType = request.getParameter("doType");
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			this.savePageDataBatch(request, tableName, viewName);
		}
		
		
		//批量保存
		if (!Base.isNull(doType) && "saveBatch".equals(doType)) {
			String[] jxjdm = request.getParameterValues("primarykey_cbv");
			String shjb = request.getParameter("shjb");
			
			if ( null != jxjdm && jxjdm.length > 0) {
				request.setAttribute("result", service.saveBatchShjb(jxjdm, shjb, "rych"));
			}
		}
		
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			
			String[] colList = new String[] {"rychdm","rychmc","shjb"};
			this.expPageData(request, response, tableName, viewName, colList);
			
			return mapping.findForward("");
		}
		
		service.setList(request, "rych");
		request.setAttribute("tname", "rych");
		request.setAttribute("path", "typj_tjsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rychShsz");
	}
	
	

	/**
	 * 荣誉称号申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		GuizhdxService service = new GuizhdxService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		
		String tableName = "typj_rychsq";
		String viewName = "view_typj_rychsq";
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String xn = Base.getJxjsqxn();
		
		//---------2010.10.9 by lr------------
		//判断是否可申请
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_RYCHSQ)
				&& zjkjService.checkKgflag()){
			String msg = "该功能暂时不开放操作！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//---------end 2010.10.9 by lr------------
		
		//学生用户
		if ("stu".equals(userType)) {
			xh=userName;
			
		}
		
		//---------2010.10.9 by lr------------
		//判断学生是否可申请
		if(StringUtils.isNotNull(request.getParameter("save_xh"))
				&& StringUtils.isNotNull(request.getParameter("save_rychdm"))
				&& Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)
				&& !service.xssqFlag(request.getParameter("save_xh"),request.getParameter("save_rychdm"),GlobalsVariable.PJPY_RYCH)){
			String msg = "你当前未被指定申请该荣誉称号！";
			request.setAttribute("yhInfo", msg);
		}
		//---------end 2010.10.9 by lr------------
		HashMap<String, String> rs = new HashMap<String, String>();
		//---------2010.10.9 by lr 增加了是否有yhInfo信息的判断------------
		//保存
		if (!Base.isNull(doType) 
				&& "save".equals(doType)
				&&(request.getAttribute("yhInfo") == null 
						|| StringUtils.isNull(request.getAttribute("yhInfo").toString()))) {
			xh=request.getParameter("save_xh");	
			//-----------2010.10.13 by lr--------
			JxjpdxxModel jxjpdModel = new JxjpdxxModel();
			jxjpdModel.setXh(xh);
			jxjpdModel.setXn(request.getParameter("save_xn"));
			jxjpdModel.setXq(request.getParameter("save_xq"));
			jxjpdModel.setNd(request.getParameter("save_nd"));
			jxjpdModel.setJxjdm(request.getParameter("save_rychdm"));
			//-----------end2010.10.13 by lr--------
			
			String pkValue = xh+xn+jxjpdModel.getJxjdm() + "无" + "无";
			
			//-----------2010.10.13 by lr 浙江科技增加条件判断------------
			boolean result = true;
			if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
				
				LsxyPjpyService lsxyService = new LsxyPjpyService();
				jxjpdModel.setXq("");//评奖周期为学年
				HashMap<String, String> tjInfo = lsxyService.pdStuTjFlag(jxjpdModel, 
						                                 GlobalsVariable.PJPY_RYCH);
				result = "true".equalsIgnoreCase(tjInfo.get("result")) ? true : false;
				if(!result){
					request.setAttribute("message", tjInfo.get("message"));
					request.setAttribute("result", result);
				}
				
				
			}
			//-----------end 2010.10.13 by lr------------
			if(result){
				this.insertOperation(request, tableName);
			}			
			this.selectPageDataByOne(request, tableName, viewName, pkValue);
			request.setAttribute("pkValue", pkValue);
			
			rs = (HashMap<String, String>) request.getAttribute("rs");
			rs.putAll(service.getStuInfo(rs.get("xh")));
			request.setAttribute("rs",rs );
			
		}
		
		//违纪、成绩、综测分及排名
		if (!Base.isNull(xh)) {
			request.setAttribute("cfxx", service.getStuCfxx(xh,xn ));
			request.setAttribute("cjxx", service.getStuCjxx(xh, xn));
			request.setAttribute("zcpm", service.getZcfPm(xh, xn));
			rs.putAll(service.getStuInfo(xh));
			rs.putAll(service.getXsfzxx(xh));
			rs.put("sqly", request.getParameter("save_sqly"));
			rs.put("yhkh", request.getParameter("save_yhkh"));
			rs.put("sfqf", request.getParameter("save_sfqf"));
			
			request.setAttribute("rs",rs );	
		}
		
		if(Globals.XXDM_GUIZHDX.equals(Base.xxdm)){
			request.setAttribute("cjpm",service.getCjpmbXx(xh));
		}
			
		service.setList(request, "rych");
		
		//=========2010.10.11 by lr=========
		//根据申请周期获取荣誉称号申请时间信息
		request.setAttribute("sqsjInfo",service.getJxjSqsjMap());
		//=========end 2010.10.11 by lr=========
		
		request.setAttribute("path", "typj_yrchsq.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("lx", request.getParameter("lx"));
		return mapping.findForward("rychsq");

	}
	

	
	/**
	 * 荣誉称号查询、删除、导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		GuizhdxForm myForm = (GuizhdxForm) form;
		GuizhdxService service = new GuizhdxService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		
		
		String tableName = "typj_rychsq";
		String viewName = "view_typj_rychsq";
		//==========2010.10.11 by lr===========
		String pjzq = Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm) ? zjkjService.getPjpySqzq() : "xn";//评奖周期
		String[] sqzqzd = zjkjService.getPjzqzd(pjzq);
		String[] outputColumn = StringUtils.joinStrArr(new String[] { "pkValue", "xh", "xm"}, 
													   sqzqzd,
				                                       new String[]{ "xymc", "bjmc", "rychmc", "sqsj", "shjg" });
		
		//==========2010.10.11 by lr===========
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		} else if ("stu".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			myForm.setQuerylike_xh(userName);
		}
		
		//查询
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		//删除
		if (!Base.isNull(doType) && "del".equals(doType)) {
			this.deleteOperation(request, tableName);
		}
		
		//导出
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			String[] expColumn = StringUtils.joinStrArr(new String[] { "xh", "xm", "xb"}, 
								 sqzqzd,
			                     new String[]{ "nj", "xydm", "xymc",
					                          "bjdm", "bjmc", "rychmc", "sqsj", 
					                          "fdysh", "fdyyj", "xysh", "xyyj",
					                          "xxsh", "xxyj"});
			this.expPageData(request, response, tableName, viewName, expColumn);
			return mapping.findForward("");
		}
		
		service.setList(request, "rych");
		request.setAttribute("path", "typj_yrchcx.do");
		request.setAttribute("realTable", tableName);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rychcx");
	}
	
	

	/**
	 * 荣誉称号单条维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward rychView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GuizhdxService service = new GuizhdxService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		
		String tableName = "typj_rychsq";
		String viewName = "view_typj_rychsq";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		
		//加载数据
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		String shjb = service.getShjbForRych(rs.get("rychdm"));
		
		if (null != rs ) {
			//处分、成绩
			request.setAttribute("cfxx", service.getStuCfxx(rs.get("xh"), rs.get("xn")));
			request.setAttribute("cjxx", service.getStuCjxx(rs.get("xh"), rs.get("xn")));
			request.setAttribute("zcpm", service.getZcfPm(rs.get("xh"), rs.get("xn")));
		}
		
		//修改
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			boolean result = false;
			//============2010.10.11 by lr======
			HashMap<String, String> shMap = getShMap(request);
			String shjg = shMap.get("shjg");
			String shzd = shMap.get("shzd");
			
			if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm) &&
					"通过".equalsIgnoreCase(shjg)){				
				//浙江科技，判断人数限制是否超出。
				HashMap<String, String[]> map = new HashMap<String, String[]>();
				map.put("cbv", new String[]{request.getParameter("pkValue")});
				
				//奖学金审核
				String message = zjkjService.auditingJxj(map,
						                shzd,
						                tableName);
				if(StringUtils.isNull(message)){
					message = "操作成功！";
					result = true;
				}
				request.setAttribute("result", result);
				request.setAttribute("message", message);
			}else{
				result = true;
			}
			//============end 2010.10.11 by lr======
			if(result){
				this.updateOperation(request, tableName);
			}
		}
		
		//=========2010.10.11 by lr=========
		//根据申请周期获取荣誉称号申请时间信息
		request.setAttribute("sqsjInfo",service.getJxjSqsjMap());
		//=========end 2010.10.11 by lr=========
		service.setList(request, "rych");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("shjb", shjb);
		request.setAttribute("doType", doType);
		return mapping.findForward("rychView");
	}
	
	
	
	/**
	 * 来自SubConfAction的奖学金审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward priseCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		GuizhdxForm myForm = (GuizhdxForm) form;
		GuizhdxService service = new GuizhdxService();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());
		
		String tableName = "xsjxjb";
		String viewName = "view_xsjxjb";
		String[] outputColumn = null;
		String yhlx = "";
		String doType = request.getParameter("doType");
		String shjb = "3";
		
		if (isFdy) {
			yhlx = "fdy";
			request.setAttribute("shzd", "fdysh");
			outputColumn = new String[] { "disabled", "bgcolor", "pkValue",
					"xh", "xm", "xn", "nd", "xymc", "bjmc", "jxjmc", "fdysh" };
			myForm.setQueryequals_xydm(userDep);
		} else if ("xy".equals(userType)) {
			yhlx = "xy";
			request.setAttribute("shzd", "xysh");
			outputColumn = new String[] { "disabled", "bgcolor", "pkValue",
					"xh", "xm", "xn", "nd", "xymc", "bjmc", "jxjmc", "xysh" };
			myForm.setQueryequals_xydm(userDep);
		} else if ("xx".equals(userType) || "admin".equals(userType)) {
			yhlx = "xx";
			request.setAttribute("shzd", "xxsh");
			outputColumn = new String[] { "disabled", "bgcolor", "pkValue",
					"xh", "xm", "xn", "nd", "xymc", "bjmc", "jxjmc", "xxsh" };
		} else {
			request.setAttribute("errMsg", "对不起,您无权访问此页!");
			return new ActionForward("/errMsg.do",false);
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			service.getCustomAudiColumn(request,yhlx,String.valueOf(shjb));
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		if (!Base.isNull(doType) && "sh".equals(doType)) {
			this.auditingBatchOperation(request, tableName);
		}
		
		service.setList(request, "priseCheck");
		request.setAttribute("path", "prise_check.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("priseCheck");
	}
	
	
	
	/**
	 * 奖学金结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward priseResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		GuizhdxForm myForm = (GuizhdxForm) form;
		GuizhdxService service = new GuizhdxService();
		
		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");
		String userDep = (String) session.getAttribute("userDep");
		
		String tableName = "xsjxjb";
		String viewName = "view_xsjxjb";
		String[] outputColumn = new String[] { "pkValue", "xh", "xm", "xn",
				"nd", "xymc", "bjmc", "jxjmc", "fdysh", "xysh", "xxsh" };
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		} else if ("stu".equals(userType)) {
			myForm.setQuerylike_xh(userName);
		}
		
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		if (!Base.isNull(doType) && "del".equals(doType)) {
			this.deleteOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			this.expPageData(request, response, tableName, viewName, outputColumn);
			return mapping.findForward("");
		}
		
		service.setList(request, "priseCheck");
		request.setAttribute("path", "prise_result.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("priseResult");
	}
	
	
	
	/**
	 * 来自SubConfAction的奖学金单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward priseCheckOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GuizhdxService service = new GuizhdxService();
		
		String tableName = "xsjxjb";
		String viewName = "view_xsjxjb";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		
		if (null != rs) {
			//处分、成绩
			request.setAttribute("wjList", service.getStuCfxx(rs.get("xh"), rs.get("xn")));
			request.setAttribute("cjList", service.getStuCjxx(rs.get("xh"), rs.get("xn")));
		}
		
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			this.updateOperation(request, tableName);
		}
		
		service.setList(request, "");
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "prise_check.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("priseCheckOne");
	}
	
	/**
	 * 来自SubConfAction的荣誉称号审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creditCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();		
		GuizhdxForm myForm = (GuizhdxForm) form;
		GuizhdxService service = new GuizhdxService();
		User user = getUser(request);
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());
		String xxdm = StandardOperation.getXxdm();
		
		String tableName = "xsrychb";
		String viewName = "view_xsrychb";
		String[] outputColumn = null;
		String doType = request.getParameter("doType");
		String yhlx = "";
		String shjb="3";//审核级别
		String[] xnnd = {"xn", "nd"};
		String[] befArr = {"disabled", "bgcolor", "pkValue", "xh", "xm"};
		String[] infoArr = {"xymc", "bjmc", "rychmc"};
		String[] audiArr = {"xxsh"};
		
		xnnd = service.getXnndArr(xxdm,xnnd);//评优周期字段
		shjb = service.getRychShjb(xxdm);//审核级别
		if (isFdy && "3".equalsIgnoreCase(shjb)) {//三级辅导员审核
			yhlx = "fdy";
			request.setAttribute("shzd", "fdysh");
			audiArr = new String[]{"fdysh"};
			myForm.setQueryequals_xydm(userDep);
		} else if ("xy".equals(userType)) {
			yhlx = "xy";
			request.setAttribute("shzd", "xysh");
			audiArr = new String[]{"xysh"};
			myForm.setQueryequals_xydm(userDep);
			//三级审核，学院查询到的数据为辅导员审核通过的记录
			if("3".equalsIgnoreCase(shjb)){
				request.setAttribute("annexTerm", " and fdysh='通过' ");
			}
		} else if ("xx".equals(userType) || "admin".equals(userType)) {
			yhlx = "xx";
			request.setAttribute("shzd", "xxsh");	
			//非一级审核，学校查询到的数据为学院审核通过的记录
			if(!"1".equalsIgnoreCase(shjb)){
				request.setAttribute("annexTerm", " and xysh='通过' ");
			}
		} else {
			request.setAttribute("errMsg", "对不起,您无权访问此页!");
			return new ActionForward("/errMsg.do",false);
		}
		outputColumn = StringUtils.joinStrArr(befArr,xnnd,infoArr,audiArr);
		
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			service.getCustomAudiColumn(request,yhlx,String.valueOf(shjb));
			
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		if (!Base.isNull(doType) && "sh".equals(doType)) {
			boolean tjFlag = service.hasShtj(xxdm); 
			if(tjFlag){//有条件判断
				String[] pkValue = request.getParameterValues(PRIFIX_PRIMARY_KEY + "cbv");		
				String message = service.checkRychTj(xxdm,pkValue,request.getAttribute("shzd").toString(),request.getParameter("shjg"),user);//判断荣誉称号审核条件
				request.setAttribute("message", message);
				request.setAttribute("result", "result");
			} else {//无条件直接更改审核结果
				this.auditingBatchOperation(request, tableName);
			}
		}
		
		service.setList(request,"creditCheck");
		request.setAttribute("path", "credit_check.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("shjb", shjb);
		return mapping.findForward("creditCheck");
	}
	
	
	/**
	 * 荣誉称号结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creditResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		GuizhdxForm myForm = (GuizhdxForm) form;
		GuizhdxService service = new GuizhdxService();
		
		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");
		String userDep = (String) session.getAttribute("userDep");
		
		String tableName = "xsrychb";
		String viewName = "view_xsrychb";
		String[] outputColumn = new String[] { "pkValue", "xh", "xm", "xn",
				"nd", "xymc", "bjmc", "rychmc", "fdysh", "xysh", "xxsh" };
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		} else if ("stu".equals(userType)) {
			myForm.setQuerylike_xh(userName);
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		if (!Base.isNull(doType) && "del".equals(doType)) {
			this.deleteOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			this.expPageData(request, response, tableName, viewName, outputColumn);
			return mapping.findForward("");
		}
		
		service.setList(request,"creditCheck");
		request.setAttribute("path", "credit_result.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("creditResult");
	}
	
	
	
	/**
	 * 来自SubConfAction的荣誉称号单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward creditCheckOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GuizhdxService service = new GuizhdxService();
		String page = "creditCheckOne";;
		
		String tableName = "xsrychb";
		String viewName = "view_xsrychb";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			this.updateOperation(request, tableName);
		}
		
		service.setList(request, "");
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "credit_check.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward(page);
	}


	
	/**
	 * 奖学金报表打印
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward jxjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		GuizhdxService service = new GuizhdxService();
		
		String tableName = "typj_jxjsqb";
		String viewName = "view_typj_jxjsqb";
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		
		if (!Base.isNull(pkValue)) {
			this.selectPageDataByOne(request, tableName, viewName, pkValue);
			rs = (HashMap<String, String>) request.getAttribute("rs");
			String bkkms = service.getBkkms(rs);//补考科目数
			
			HashMap cjMap = service.getGuizhdxCj(rs);
			
			HashMap<String, String> stuMap = service.getStuInfo(rs.get("xh"));
			stuMap.put("bjrs", service.getBjrs(rs.get("xh")));
			stuMap.put("bkkms", bkkms);
			stuMap.putAll(cjMap);
			request.setAttribute("stuMap", stuMap);
			
			//处分、成绩
			rs.remove("xq");
			request.setAttribute("cjxx", service.getStuCjxx(rs));
			
			rs.put("xq", "1");
			request.setAttribute("cjList1", service.getStuCjxx(rs));
			rs.put("xq", "2");
			request.setAttribute("cjList2", service.getStuCjxx(rs));
			
			request.setAttribute("cfxx", service.getStuCfxx(rs.get("xh"), rs.get("xn")));
			request.setAttribute("rs", service.getZcf(rs));
			
		}
		
		if (Globals.XXDM_GUIZHDX.equals(Base.xxdm)) {
			return mapping.findForward("guizhdx_jxjPrint");
		} else if (Globals.XXDM_NNZYJSXY.equals(Base.xxdm)) {
			return mapping.findForward("nnzy_jxjPrint");
		} else if (Globals.XXDM_NBCSZYJSXY.equals(Base.xxdm)) {
			String jxjmc = request.getParameter("jxjmc");
			if ("国家奖学金".equals(jxjmc) || "国家奖学金".equals(rs.get("jxjmc"))) {
				return mapping.findForward("nbcs_gjjxj");
			} else if ("国家励志奖学金".equals(jxjmc) 
					|| "国家励志奖学金".equals(rs.get("jxjmc"))) {
				return mapping.findForward("nbcs_lzjxj");
			} else if ("素质与能力拓展成就奖学金".equals(jxjmc) 
					|| "素质与能力拓展成就奖学金".equals(rs.get("jxjmc"))) {
				return mapping.findForward("nbcs_szynljxj");
			}
			
		}
		
		request.setAttribute("xxmc", Base.xxmc);
		return mapping.findForward("jxjPrint");
	}

	
	
	/**
	 * 荣誉称号报表打印
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward rychPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		GuizhdxService service = new GuizhdxService();
		
		String tableName = "typj_rychsq";
		String viewName = "view_typj_rychsq";
		String pkValue = request.getParameter("pkValue");
		
		
		
		if (!Base.isNull(pkValue)) {
			this.selectPageDataByOne(request, tableName, viewName, pkValue);
			HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
			
			 HashMap cjMap = service.getGuizhdxCj(rs);
			 String bkkms = service.getBkkms(rs);//补考科目数
			 List<String[]> cfxx = service.getStuCfxx(rs.get("xh"),rs.get("xn") );
			 
			 if (null != cfxx && !cfxx.isEmpty()) {
				 rs.put("iscf", "yes");
			 } else {
				 rs.put("iscf", "no");
			 }
			
			HashMap<String, String> stuMap = service.getStuInfo(rs.get("xh"));
			stuMap.put("bkkms", bkkms);
			stuMap.putAll(cjMap);
			stuMap.put("bjrs", service.getBjrs(rs.get("xh")));
			request.setAttribute("stuMap", stuMap);
			
			rs.remove("xq");
			request.setAttribute("cjxx", service.getStuCjxx(rs));
			
			rs.put("xq", "1");
			request.setAttribute("cjList1", service.getStuCjxx(rs));
			rs.put("xq", "2");
			request.setAttribute("cjList2", service.getStuCjxx(rs));
			request.setAttribute("rs", service.getZcf(rs));
			
		}
		
		if (Globals.XXDM_GUIZHDX.equals(Base.xxdm)) {
			return mapping.findForward("guizhdx_rychPrint");
		}
		
		
		request.setAttribute("xxmc", Base.xxmc);
		return mapping.findForward("rychPrint");
	}

	public ActionForward zcfscx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		String viewName = "view_pjpy_guizhdx_cjpm";
		
		// 用户部门
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		GuizhdxForm gForm = (GuizhdxForm) form;
		
		if("stu".equalsIgnoreCase(userType)){
			gForm.setQuerylike_xh(userName);
		}
		
		if("xy".equalsIgnoreCase(userType)){
			gForm.setQueryequals_xydm(userDep);
		}
		
		String go = request.getParameter("go");

		// 根据页面中的条件获取数据
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[]{"xh","xm","bjmc","xn","nj","fs","zyjd","zpm","zpmbl","zypm","zypmbl"};
			selectPageDataByPagination(request, gForm, "", viewName,
					outputColumn);
		}

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
				
		request.setAttribute("path", "guizhdx_zcfscx.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("tableName", viewName);
		
		return mapping.findForward("zcfscx");
	}
	
	public ActionForward zcfsExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] output = new String[]{"xh","xm","xymc","zymc","bjmc","xn","nj","fs","zyjd",
				"zycj","zpm","zpmbl","zypm","zypmbl"};
		expPageData(request, response, "","view_pjpy_guizhdx_cjpm", output);
		return mapping.findForward("");
	}
	
	/**
	 * 奖学金学院上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * **/
	public ActionForward jxjsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GuizhdxForm model =(GuizhdxForm)form;
		GuizhdxService service = new GuizhdxService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		User user = getUser(request);//用户信息
		String act = request.getParameter("act");
		
		if("stu".equalsIgnoreCase(user.getUserType())){//学生用户不可访问
        	request.setAttribute("errMsg", "对不起,您无权访问此页！");
			return new ActionForward("/errMsg.do",false);
        }
		//用户判断该功能是否开放
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(user.getUserType()) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_JXJSB)
				&& zjkjService.checkKgflag()){
			String msg = "该功能暂时不开放操作！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if("xy".equalsIgnoreCase(user.getUserType()) && !"true".equalsIgnoreCase(user.getIsFdy())){
			model.setXydm(user.getUserDep());
			model.setQueryequals_xydm(user.getUserDep());
		}
		//上报
		if ("sb".equals(act)) {
			model.setJxjdm(request.getParameter("sb_jxjdm"));
			model.setJxjje(request.getParameter("sb_jxjje"));
			HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
    		String[] primaryKeys = {};
    		int i = 0;
    		for(String key : primaryMap.keySet()){
    			if(i++ == 0 ){
    				primaryKeys = primaryMap.get(key);
    			}
    		}
    		boolean result = service.jxjSb(primaryKeys,model,user);
    		request.setAttribute("result", result);
    		request.setAttribute("message", result ? MESSAGE_SUCCESS : MESSAGE_FAIL);
			act = "qry";
		}
		
		//取消上报
		if ("qxsb".equals(act)) {
			HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
    		String[] primaryKeys = {};
    		int i = 0;
    		for(String key : primaryMap.keySet()){
    			if(i++ == 0 ){
    				primaryKeys = primaryMap.get(key);
    			}
    		}
    		boolean result = service.jxjQxsb(primaryKeys,user);
    		request.setAttribute("result", result);
    		request.setAttribute("message", result ? MESSAGE_SUCCESS : MESSAGE_FAIL);
			act = "qry";
		}
		//查询
		if ("qry".equals(act)) {			
			List<String[]> rs = service.queryJxjsb(model,user);
			List<HashMap<String, String>> topTr = service.getJxjsbTitle();
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs!= null && rs.size()>0 ? rs.size() : 0);
			request.setAttribute("topTr", topTr);
		}
		
		String pjzq = zjkjService.getPjpySqzq();
		service.setList(request, "tjsz");
		request.setAttribute("path", "typj_jxjsb.do");
		request.setAttribute("pjzq", pjzq);//评奖周期
		FormModleCommon.setNdXnXqList(request);//学年年度学期列表
		FormModleCommon.commonRequestSet(request);
		//设置默认的学年、年度、学期
		setDefaultValue(model);
		return mapping.findForward("jxjsb");
	}
	
	public HashMap<String, String> getShMap(HttpServletRequest request){
		HashMap<String, String> map = new HashMap<String, String>();
		String shzd = "";//审核字段
		String shjg = "";//审核结果
		
		if(StringUtils.isNotNull(request.getParameter("save_fdysh"))){
			shzd = "fdysh";
			shjg = request.getParameter("save_fdysh");
		}else if(StringUtils.isNotNull(request.getParameter("save_xysh"))){
			shzd = "xysh";
			shjg = request.getParameter("save_xysh");
		}else if(StringUtils.isNotNull(request.getParameter("save_xxsh"))){
			shzd = "xxsh";
			shjg = request.getParameter("save_xxsh");
		}else if(StringUtils.isNotNull(request.getParameter("save_shzt"))){
			shzd = "shzt";
			shjg = request.getParameter("save_shzt");
		}
		map.put("shzd", shzd);
		map.put("shjg", shjg);
		return map;
	}
	
	/**
	 * 设置默认的学年，学期，年度选项
	 * @param tybForm
	 * */
	private void setDefaultValue(GuizhdxForm tybForm) {
		if (StringUtils.isNull(tybForm.getQueryequals_xn())) {
        	tybForm.setQueryequals_xn(Base.getJxjsqxn());
        }
        if (StringUtils.isNull(tybForm.getQueryequals_nd())) {
        	tybForm.setQueryequals_nd(Base.getJxjsqnd());
        }
        if (StringUtils.isNull(tybForm.getQueryequals_xq())) {
        	tybForm.setQueryequals_xq(Base.getJxjsqxq());
        }
        if (StringUtils.isNull(tybForm.getXn())) {
        	tybForm.setXn(Base.getJxjsqxn());
        }
        if (StringUtils.isNull(tybForm.getXq())) {
        	tybForm.setXq(Base.getJxjsqxq());
        }
        if (StringUtils.isNull(tybForm.getNd())) {
        	tybForm.setNd(Base.getJxjsqnd());
        }
	}
}
