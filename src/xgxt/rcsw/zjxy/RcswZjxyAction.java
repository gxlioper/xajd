package xgxt.rcsw.zjxy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.rcsw.RcswForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.basic.BasicService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 浙工大之江学院日常事务-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */

public class RcswZjxyAction extends BasicAction {


	/**
	 * 日常事务_实物发放_项目管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffXmwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();

		// ---------------- 赋初值 ----------------
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_xg_rcsw_swffxmwh";
		// 表名
		String realTable = "rcsw_swffxmwhb";
		// 访问路径
		String path = "rcsw_swff_xmwh.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// -----------------end-----------------------

		//BeanUtils.copyProperties(model, myForm);
	
		// ----------------执行删除操作 ----------------
		if ("del".equalsIgnoreCase(doType)) {
			String[] pkValue = myForm.getPrimarykey_checkVal();
			if (pkValue != null && pkValue.length > 0) {
				boolean result = service.delSwffXmwh(model, pkValue, realTable);
				//删除项目时如果项目中已经存在数据就不能删除项目
//				if(result){
//					service.delSwffXmwh(pkValue);
//				}
				request.setAttribute("result", result);
			}
		}
		
		
		// -----------------end-----------------------

		// ----------------执行查询操作 ----------------
		if (search) {
			StringBuilder query=new StringBuilder();
			
			//发放结束时间条件
			if(!"".equalsIgnoreCase(myForm.getKssj())){
				query.append(" and ffjssj>='"+myForm.getKssj()+"' ");
			}
			if(!"".equalsIgnoreCase(myForm.getJssj())){
				query.append(" and ffjssj<='"+myForm.getJssj()+"' ");
			}
			
			request.setAttribute("annexTerm", query);
			String[] outputColumn = { "pk","isDis","xmlxmc" ,"xmmc", "xn", "xqmc","nd", "ffsj","ffjssj",
					"xlqrs"};
			this.selectPageDataByPagination(request, myForm, realTable,
					tableName, outputColumn);
		}else{
			//初始化页面属性
			myForm.setQueryequals_xn(Base.currXn);
			myForm.setQueryequals_xq(Base.currXq);
			myForm.setQueryequals_nd(Base.currNd);
		}
		// -----------------end-----------------------
		
		// ----------------初始化request的值 ----------------
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		

		service.setRequestValue(rForm, request);
		// ----------------end ----------------

		// ----------------初始化request的值 ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------
		
		return mapping.findForward("swffXmwhManage");
	}
	
	
	/**
	 * 日常事务_实物发放_项目维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffXmwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();
		// ---------------- 赋初值 ----------------
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = "view_rcsw_swffxmwh";
		// 表名
		String realTable = "rcsw_swffxmwhb";
		// 访问路径
		String path = "rcsw_swff_xmwh.do";
		// 主键
		String pk = request.getParameter("pk");
		// -----------------end-----------------------

		HashMap<String, String> rs = new HashMap<String, String>();	

		BeanUtils.copyProperties(model, myForm);
		
		//	-----------------保存实物发放项目维护-----------------------
		if ("save".equalsIgnoreCase(doType)) {
			
			this.insertOperation(request, realTable);
			String xn=myForm.getSave_xn();
			String xq=myForm.getSave_xq();
			String nd=myForm.getSave_nd();
			String xmlx=myForm.getSave_xmlx();
			String ffsj=myForm.getSave_ffsj();
			pk=xn+xq+nd+xmlx+ffsj;
			rs = service.getSwffXmwhInfo(pk);
			rs.put("pk", rs.get("xmlx")+rs.get("xn")+rs.get("xq")+rs.get("nd")+rs.get("ffrq").split("!!@@!!")[0]+rs.get("ffsj"));
		}
		
		if ("modi".equalsIgnoreCase(doType)) {
			
			this.updateOperation(request, realTable);
			
		}
		
		//	-----------------end-----------------------
		
		// ----------------执行修改或者查看操作 ----------------
		if (("update".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType))
				&& !Base.isNull(pk)) {
			rs = service.getSwffXmwhInfo(pk);
			rs.put("pk", rs.get("xmlx")+rs.get("xn")+rs.get("xq")+rs.get("nd")+rs.get("ffrq").split("!!@@!!")[0]+rs.get("ffsj"));
		}
		// -----------------end-----------------------
		
		// ----------------初始化request的值 ----------------
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);
		myForm.setSave_xn(Base.currXn);
		myForm.setSave_xq(Base.currXq);
		myForm.setSave_nd(Base.currNd);
		
		service.setRequestValue(rForm, request);
		
		request.setAttribute("rs", rs);
		request.setAttribute("xmPkValue", pk);
		// ----------------end ----------------

		// ----------------初始化request的值 ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------

		return mapping.findForward("swffXmwhUpdate");
	}

	/**
	 * 日常事务_实物发放_学生发放
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward swffXsffManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ---------------- 赋初值 ----------------
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String userName = (String) request.getSession()
				.getAttribute("userName");
		String doType = request.getParameter("doType");
		String tableName = "view_rcsw_swffxsff";
		String realTable = "rcsw_swffrqwhb";
		String path = "rcsw_swff_xsff.do";
		String mklx = "xs";

		RcswForm myForm = (RcswForm) form;

		RequestForm rForm = new RequestForm();
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		// ----------------end----------------
		return swffRqwhManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 日常事务_实物发放_老师发放
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward swffLsffManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ---------------- 赋初值 ----------------
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String userName = (String) request.getSession()
				.getAttribute("userName");
		String doType = request.getParameter("doType");
		String tableName = "view_rcsw_swfflsff";
		String realTable = "rcsw_swffrqwhb";
		String path = "rcsw_swff_lsff.do";
		String mklx = "ls";

		RcswForm myForm = (RcswForm) form;

		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		// ----------------end----------------

		return swffRqwhManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 日常事务_实物发放_发放人员管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffRqwhManage(ActionMapping mapping, RcswForm myForm,
			RequestForm rForm, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();

		// ---------------- 赋初值 ----------------
		// 登陆用户类型
		String userType = rForm.getUserType();
		// 登陆用户名
		String userName = rForm.getUserName();
		// 操作类型（增加，修改，删除等）
		String doType = rForm.getDoType();
		// 视图名
		String tableName = rForm.getTableName();
		// 表名
		String realTable = rForm.getRealTable();
		// 访问路径
		String path = rForm.getDoType();
		// 模块类型
		String mklx = rForm.getMklx();
		// 主键;
		String pk = request.getParameter("pk");
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// -----------------end-----------------------
		
		// ----------------执行提交名单操作 ----------------
		if ("tj".equalsIgnoreCase(doType)) {
			boolean result = service.setSwffmd(pk, userName);
			request.setAttribute("result", result);
		}
		// -----------------end-----------------------
		
		// ----------------执行查询操作 ----------------
		if (search) {
			StringBuilder query=new StringBuilder();
			if(!"".equalsIgnoreCase(myForm.getKssj())){
				query.append(" and ffjssj>='"+myForm.getKssj()+"' ");
			}
			if(!"".equalsIgnoreCase(myForm.getJssj())){
				query.append(" and ffjssj<='"+myForm.getJssj()+"' ");
			}
			String[] outputColumn = { "pk","xmlxmc", "xmmc", "xn", "xqmc", "nd", "ffsj","ffjssj",
					"ffrs" };
			request.setAttribute("annexTerm", query);
			this.selectPageDataByPagination(request, myForm, "",
					tableName, outputColumn);
		}
		// -----------------end-----------------------
		
		// ----------------初始化request的值 ----------------
		service.setRequestValue(rForm, request);
		// ----------------end ----------------

		// ----------------初始化request的值 ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------

		return mapping.findForward("swffRqwhManage");
	}
	
	/**
	 * 日常事务_实物发放_发放人员维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffRqwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();

		// ---------------- 赋初值 ----------------
		String userDep = (String)request.getSession().getAttribute("userDep");
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = "view_xg_rcsw_swffxmwh";
		// 表名
		String realTable = "rcsw_swffrqwhb";
		// 访问路径
		String path = "rcsw_swff_xmwh.do";
		// 主键
		String pk = request.getParameter("pk");
		// 模块类型
		String mklx = request.getParameter("mklx");
		// 学号职工号
		String xhzgh = request.getParameter("stuInfo");
		// 清空发放者信息
		if (Base.isNull(xhzgh)) {
			service.delffzxx();
		}
		// -----------------end-----------------------

		HashMap<String, String> rs = new HashMap<String, String>();	

		BeanUtils.copyProperties(model, myForm);
		
		//	-----------------保存发放对象-----------------------
		if ("save".equalsIgnoreCase(doType) && model.getXhzgh().length>0) {
			
			model.setFfr(userName);
			model.setLx(mklx);
			boolean result = service.saveSwffFfdx(model, realTable);
			request.setAttribute("result", result);
			doType = "ff";
			
		}else if("save".equalsIgnoreCase(doType) && !(model.getXhzgh().length>0)){
			request.setAttribute("result", true);
			doType = "ff";
		}
		//	-----------------end-----------------------
		
		// ----------------执行修改或者查看操作 ----------------
		List<HashMap<String, String>> topTr = null;
		ArrayList<String[]> rsList = null;
		
		if (("ff".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType))
				&& !Base.isNull(pk)) {
			rs = service.getSwffXmwhInfo(pk);
			
			//主键
			String zj = "xn||xq||nd||xmlx||ffsj||lx";
			//主键值
			String zjValue=pk+mklx;
			//发放备注
			String ffbz = service.getOneValue("rcsw_swffrqwhb", "ffbz", zj, zjValue);
			rs.put("ffbz", ffbz);
			
			model.setXn(rs.get("xn"));
			model.setXq(rs.get("xq"));
			model.setNd(rs.get("nd"));
			model.setXmlx(rs.get("xmlx"));
			model.setFfsj(rs.get("ffsj"));
			model.setLx(mklx);
			model.setUserType(userType);
			model.setUserDep(userDep);
			//发放对象列表
			if("xy".equalsIgnoreCase(userType)){
				model.setUserDep(userDep);
			}
			
			if ("xs".equalsIgnoreCase(mklx)) {
				topTr = service.getTopTr("ffdx_xs");
				rsList = service.getXsFfdxList(model);
			} else if ("ls".equalsIgnoreCase(mklx)) {
				topTr = service.getTopTr("ffdx_ls");
				rsList = service.getLsFfdxList(model);
			}	
		}
		// -----------------end-----------------------
		
		// ----------------初始化request的值 ----------------
		
		String[] qtzd = new String[] { "pk" };
		String[] qtzdz = new String[] { pk };
		
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setMklx(mklx);
		
		service.setRequestValue(rForm, request);
		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsList", rsList);
		request.setAttribute("xhzgh", xhzgh);
		// ----------------end ----------------

		// ----------------初始化request的值 ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------

		return mapping.findForward("swffRqwhUpdate");
	}
	
	/**
	 * 学生基本信息管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bffrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();

		// ---------------- 赋初值 ----------------
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 登陆部门代码
		String userDep = (String) request.getSession().getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_rcsw_swffxmwh";
		// 表名
		String realTable = "rcsw_swffxmwhb";
		// 访问路径
		String path = "rcsw_swff_xmwh.do";
		// 模块类型
		String mklx = request.getParameter("mklx");
		// 发放人群
		String ffrq = request.getParameter("ffrq");
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// ---------------- end ----------------
		
		if (userType.equalsIgnoreCase("xy")) {
			model.setXydm(userDep);
			myForm.setXydm(userDep);
		}
		
		BeanUtils.copyProperties(model, myForm);
		// ------------------执行保存操作---------------------
		if ("ff".equalsIgnoreCase(doType)) {
			String pkValue = request.getParameter("stuInfo");
			String[] xhzgh = pkValue.split("!!@@!!");
			if (xhzgh != null && xhzgh.length > 0) {
				model.setXhzgh(xhzgh);
				model.setLx(mklx);
				service.saveBffzxx(model);
			}
			return swffRqwhUpdate(mapping, form, request, response);
		}
		// ------------------end---------------------
		
		// ------------------执行查询操作---------------------
		
		ArrayList<String[]> rsList = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		if (search) {
			model.setUserDep(userDep);
			if("xs".equalsIgnoreCase(mklx)){
				topTr = service.getTopTr("ffdx_xs");
				rsList = service.getXsFfdxList(model, ffrq);
			}else if ("ls".equalsIgnoreCase(mklx)){
				topTr = service.getTopTr("ffdx_ls");
				rsList = service.getLsFfdxList(model,ffrq);
			}
		}
		// ------------------end---------------------
		
		// ----------------初始化request的值 ----------------

		String[] qtzd = new String[] { "ffrq" };
		String[] qtzdz = new String[] { ffrq };
		
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		request.setAttribute("xn", myForm.getXn());
		request.setAttribute("xq", myForm.getXq());
		request.setAttribute("nd", myForm.getNd());
		request.setAttribute("xmlx", myForm.getXmlx());
		request.setAttribute("ffsj", myForm.getFfsj());
		
		service.setRequestValue(rForm, request);

		if(rsList!=null && rsList.size()>0){
			request.setAttribute("rsNum", rsList.size());
		}
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsList", rsList);
		
		// ----------------end ----------------

		// ----------------初始化request的值 ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------
		
		return mapping.findForward("bffrManage");
	}

	/**
	 * 日常事务_实物发放_结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffJgcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();
		
		// ---------------- 赋初值 ----------------
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_rcsw_swffxmwh";
		// 表名
		String realTable = "rcsw_swffxmwhb";
		// 访问路径
		String path = "rcsw_swff_jgcx.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 是否管理员
		boolean isAdmin = true;
		
		User user = getUser(request);

		CommService commService = new CommService();
		
		boolean isXy = commService.isXy(user);
		
		List<HashMap<String, String>> topTr = null;
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		BeanUtils.copyProperties(model, myForm);
		
		// -----------------end-----------------------
	
		// ----------------判断权限 ----------------
		if (!"xx".equalsIgnoreCase(userType)&&!"admin".equalsIgnoreCase(userType)) {
			if(!isXy){
				myForm.setZgh(userName);
			}
			isAdmin = false;;
		}
		// -----------------end-----------------------
		
		// ----------------执行查询操作 ----------------
		if (search) {
			topTr = service.getTopTr("swff_jg");
			rs = service.getFfjgList(model);
		}
		// -----------------end-----------------------
		
		// ----------------初始化request的值 ----------------
		
		String[] qtzd = new String[] { "isAdmin" };
		String[] qtzdz = new String[] { String.valueOf(isAdmin) };
		
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		service.setRequestValue(rForm, request);
		request.setAttribute("isXy", isXy);
		
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		
		// ----------------end ----------------

		// ----------------初始化request的值 ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------
		
		
		return mapping.findForward("swffJgcxManage");
	}
	
	/**
	 * 日常事务_实物发放_发放评价
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffFfpjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		
		String tabName="rcsw_swffrqwhb";
		String doType=request.getParameter("doType");
		
		//批量评价实物发放
		if("savePj".equalsIgnoreCase(doType)){
			//将项目级别存如FORM中
			HashMap<String, String[]> primaryMap = getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY);
			//评价意见
			HashMap<String, String> valueMap = service.setPlpj(myForm);
			this.auditingBatchOperation(request, primaryMap, valueMap, tabName);
			//批量评价后查询
			doType="query";
		}
		
		//查询
		if("query".equalsIgnoreCase(doType)){
			service.getFfxxByZgh(request, myForm);
		}
		
		service.setList(myForm, request, "swff");
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("path", "rcsw_swff_ffpj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("swffFfpjManage");
	}
	
	public ActionForward swffOnePjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswZjxyService service = new RcswZjxyService();
		
		String doType=request.getParameter("doType");
		String pkValue=request.getParameter("pkValue");

		String tabName="rcsw_swffrqwhb";
	
		if("pj".equalsIgnoreCase(doType)){		
			this.updateOperation(request, tabName);
		}
		
		
		if(!"".equalsIgnoreCase(doType)){
			HashMap<String,String>hashMap=(HashMap<String,String>)service.getOneFfjl(pkValue);
			hashMap.put("save_mycd", hashMap.get("mycd"));
			hashMap.put("save_pjyj", hashMap.get("pjyj"));
			request.setAttribute("rs", hashMap);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "rcsw_swff_ffpj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("swffOnePjManage");
	}
	
	/**
	 * 事物办理信息统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * author qlj
	 */
	public ActionForward swffPjTjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		RcswZjxyService service = new RcswZjxyService();
		BasicService basicService=new BasicService();
		CommService commService=new CommService();
		
		RcswForm myForm = (RcswForm) form;
		
		String doType=request.getParameter("doType");
		String pkValue=request.getParameter("pkValue");
		String userType=session.getAttribute("userType").toString();
		
		//访问权限判断 只允许管理员和学校用户访问
		if (!"admin".equalsIgnoreCase(userType) && !"xx".equalsIgnoreCase(userType)) {
			request.setAttribute("yhInfo", "对不起，您没有访问本页面的权限!");
			return new ActionForward("/yhInfo.do",false);
		}
		
		//查询统计信息
		if("query".equalsIgnoreCase(doType)){		
			List<String[]> rs = (ArrayList<String[]>) commService
					.getResultList((ArrayList<String[]>) service
							.getPjtjXx(myForm), myForm.getPages());
			request.setAttribute("rs", rs);
			//显示表头
			request.setAttribute("topTr", basicService.getTopTr("mdqr_xmszb",
					new String[] {"项目类型","项目名称","学年","学期","年度","办理开始时间","办理结束时间","非常满意",
					"比较满意","满意","不满意","未评价"}));
		}
		
		//事物办理评价统计
		if("tj".equalsIgnoreCase(doType)){
			String modelPath = servlet.getServletContext().getRealPath("")+"/print/pjpy/hhgxy_pjtj.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.pjxxTj(wwb,myForm);
			
		}
		
		service.setList(myForm, request, "swff");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "rcsw_swff_pjtj.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("swffPjTjManage");
	}
	
	/**
	 * 实物发放人员确定
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffXmffManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		RcswZjxyService service = new RcswZjxyService();
		RcswForm myForm = (RcswForm) form;
		RcswZjxyModel model=new RcswZjxyModel();
		
		String xmPkValue=request.getParameter("xmPkValue");
		String userName=session.getAttribute("userName").toString();
		String doType=request.getParameter("doType");
		String goType=request.getParameter("goType");
		//区分是统计还是发放
		String cs=request.getParameter("cs");
		
		if("".equalsIgnoreCase(doType)){
			doType="query";
		}
		
		String pkValue=request.getParameter("pkValue");
		
		String tableName="xsxxb";
		String viewName="view_xg_swff_ffxmtj";
		
		//保存发放人群信息
		if("save".equalsIgnoreCase(doType)){
			tableName="xg_swff_ffryb";
			BeanUtils.copyProperties(model, myForm);
			boolean result=service.saveSwffRyxx(model, tableName);
			request.setAttribute("result", result);
			request.setAttribute("dxtzdxList", myForm.getCheckVal());
			doType="view";
		}
		
		//保存成功后 发送短信通知
		if("fsdxtz".equalsIgnoreCase(doType)){
			myForm.setUserName(userName);
			myForm.setDxtzsj(GetTime.getNowTime4());
			request.setAttribute("dxResult",service.saveLqtz(myForm));
			doType="view";
		}
		
		//直接选择数据发送短信通知
		if("zjfsdxtz".equalsIgnoreCase(doType)){
			
			request.setAttribute("dxResult", service.saveDxtz(myForm));
			doType="view";
		}
		
		//选择要发放的项目
		if("view".equalsIgnoreCase(doType)){
			tableName="rcsw_swffxmwhb";
			viewName="view_rcsw_swffxmwh";
			this.selectPageDataByOne(request, tableName, viewName, pkValue);
			HashMap<String,String>rsMap=(HashMap<String,String>)request.getAttribute("rs");
			request.setAttribute("xmxxMap", rsMap);
			myForm.setXn(rsMap.get("xn"));
			myForm.setXq(rsMap.get("xq"));
			myForm.setNd(rsMap.get("nd"));
			myForm.setFfsj(rsMap.get("ffsj"));
			myForm.setXmlx(rsMap.get("xmlx"));
			
			//高级查询统计条件
			if("tj".equalsIgnoreCase(cs)){
				//如果从统计人数进入,发放人员拟定默认为"是"
				myForm.setSfqd("是");
			}else if("ff".equalsIgnoreCase(cs)){
				//如果从预定发放名单进入,发放人员拟定默认为"否"
				myForm.setSfqd("否");
			}else {
				myForm.setSfqd(null);
			}
			
			request.setAttribute("mrtj", cs);
			request.setAttribute("searchTj", myForm.getSearchModel());
			request.setAttribute("rs", service.getYffry(myForm, request));
		}
		
		//模块访问类型
		request.setAttribute("xmPkValue", xmPkValue);
		request.setAttribute("goType", goType);
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		service.setList(myForm, request, "swff");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "rcsw_swff_xmff.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("swffFfryManage");
	}
	
	/**
	 * 实物发放人员确定
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffFfryManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		RcswZjxyService service = new RcswZjxyService();
		RcswForm myForm = (RcswForm) form;
		RcswZjxyModel model=new RcswZjxyModel();
		
		String userName=session.getAttribute("userName").toString();
		String doType=request.getParameter("doType");
		
		String pkValue=request.getParameter("pkValue");
		
		String tableName="xsxxb";
		String viewName="view_xg_swff_ffxmtj";
		
		
		//统计查询
		if("query".equalsIgnoreCase(doType)){	
			tableName="xsxxb";
			viewName="view_xg_swff_ffxmtj";
			String[]outputColumn={"pkValue","xh","xm","nj","xymc","zymc","bjmc","ffxm","lqxm"};
			//判断 查询范围
			if(!"".equalsIgnoreCase(myForm.getCxfw())){
				request.setAttribute("annexTerm", " and lqcs > ffcs ");
			}
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		
		request.setAttribute("lqcsList", service.getLqcsList());
		request.setAttribute("ffcsList", service.getFfcsList());
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		service.setList(myForm, request, "swff");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "rcsw_swff_ffry.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("swffFfrytjManage");
	}
	
	

	/**
	 * 实物发放人员确定
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffFfrytjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswZjxyService service = new RcswZjxyService();
		RcswForm myForm = (RcswForm) form;
		BasicService basicService=new BasicService();
		
		String doType=request.getParameter("doType");
		
		String pkValue=request.getParameter("pkValue");
		
		
	
		//统计查询
		myForm.setXh(pkValue);
		if("query".equalsIgnoreCase(doType)){	
			request.setAttribute("topTr", basicService.getTopTr("xg_sysz_tpszb",
					new String[] { "学年", "学期", "年度","发放时间","项目名称","是否领取","是否通知" }));
			if(service.getFfryTjxx(myForm).size()==0){
					request.setAttribute("yhInfo", "该学生没有发放项目可供查询！");
					return new ActionForward("/yhInfo.do",false);
			}
			request.setAttribute("rs", service.getFfryTjxx(myForm));
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
	
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "rcsw_swff_ffry.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("swffFfrytjUpdate");
	}
	
	
	/**
	 * 实物发放 发放人员项目选择
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return  ActionForward
	 * @throws Exception
	 * author 潇洒的裘
	 */
	public ActionForward swffXmxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswZjxyService service = new RcswZjxyService();
		RcswForm myForm = (RcswForm) form;
		
		String doType=request.getParameter("doType");
		
		String tableName="rcsw_swffxmwhb";
		String viewName="view_xg_rcsw_swffxmwh";
		
		//实物发放项目查询
		if ("query".equalsIgnoreCase(doType)){
			StringBuilder query=new StringBuilder();
			if(!"".equalsIgnoreCase(myForm.getKssj())){
				query.append(" and ffjssj>='"+myForm.getKssj()+"' ");
			}
			if(!"".equalsIgnoreCase(myForm.getJssj())){
				query.append(" and ffjssj<='"+myForm.getJssj()+"' ");
			}
			
			request.setAttribute("annexTerm", query);
			String[] outputColumn = { "pkValue","xmlxmc" ,"xmmc", "xn", "xqmc","nd", "ffsj","ffjssj",
					"xlqrs"};//,"lqtz"
			this.selectPageDataByPagination(request, myForm, tableName,
					viewName, outputColumn);
		}else{
			
			myForm.setQueryequals_xn(Base.currXn);
			myForm.setQueryequals_xq(Base.currXq);
			myForm.setQueryequals_nd(Base.currNd);
			
		}
		
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		
	
		service.setList(myForm, request, "swff");
		
		request.setAttribute("path", "rcsw_swff_xmff.do");
		FormModleCommon.commonRequestSet(request);
		
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("swffXmxz");
	}
	
	/**
	 * 日常事务_实物发放_发放结果查询
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward swffFfjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ---------------- 赋初值 ----------------
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String userName = (String) request.getSession()
				.getAttribute("userName");
		String doType = request.getParameter("doType");
		String tableName = "view_rcsw_swffxsff";
		String realTable = "rcsw_swffrqwhb";
		String path = "rcsw_swff_ffjg.do";
		String mklx = "xs";

		RcswForm myForm = (RcswForm) form;

		RequestForm rForm = new RequestForm();
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		// ----------------end----------------
		return swffRqwhManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * 学生基本信息管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward ffrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();

		// ---------------- 赋初值 ----------------
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 登陆部门代码
		String userDep = (String) request.getSession().getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_rcsw_swffxmwh";
		// 表名
		String realTable = "rcsw_swffxmwhb";
		// 访问路径
		String path = "rcsw_swff_xmwh.do";
		// 模块类型
		String mklx = request.getParameter("mklx");
		// 发放人群
		String ffrq = request.getParameter("ffrq");
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// ---------------- end ----------------
		
		if (userType.equalsIgnoreCase("xy")) {
			model.setXydm(userDep);
			myForm.setXydm(userDep);
		}
		
		BeanUtils.copyProperties(model, myForm);
		// ------------------执行保存操作---------------------
		if ("ff".equalsIgnoreCase(doType)) {
			String pkValue = request.getParameter("stuInfo");
			String[] xhzgh = pkValue.split("!!@@!!");
			if (xhzgh != null && xhzgh.length > 0) {
				model.setXhzgh(xhzgh);
				model.setLx(mklx);
				service.saveBffzxx(model);
			}
			return swffRqwhUpdate(mapping, form, request, response);
		}
		// ------------------end---------------------
		
		// ------------------执行查询操作---------------------
		
		ArrayList<String[]> rsList = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		if (search) {
			model.setUserDep(userDep);
			topTr = service.getTopTr("ffdx_xs");
			rsList = service.getFfxsList(model);
		}
		// ------------------end---------------------
		
		// ----------------初始化request的值 ----------------

		String[] qtzd = new String[] { "ffrq" };
		String[] qtzdz = new String[] { ffrq };
		
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		request.setAttribute("xn", myForm.getXn());
		request.setAttribute("xq", myForm.getXq());
		request.setAttribute("nd", myForm.getNd());
		request.setAttribute("xmlx", myForm.getXmlx());
		request.setAttribute("ffsj", myForm.getFfsj());
		
		service.setRequestValue(rForm, request);

		if(rsList!=null && rsList.size()>0){
			request.setAttribute("rsNum", rsList.size());
		}
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsList", rsList);
		
		// ----------------end ----------------

		// ----------------初始化request的值 ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------
		
		return mapping.findForward("bffrManage");
	}


	/**
	 * 日常事务_实物发放_发放人员维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffFfjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();

		String pkValue=request.getParameter("pkValue");
		// ---------------- 赋初值 ----------------
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆部门代码
		String userDep = (String) request.getSession().getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "rcsw_swff_xmwh.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// ---------------- end ----------------
		
		if (userType.equalsIgnoreCase("xy")) {
			model.setXydm(userDep);
			myForm.setXydm(userDep);
		}
		
		BeanUtils.copyProperties(model, myForm);
		
		// ------------------执行保存操作---------------------
		if ("ff".equalsIgnoreCase(doType)) {
			service.saveYffry(request,myForm);
		}
			
		
		HashMap<String,String>xmzj=(HashMap<String,String>)service.getSwffxm(pkValue);
		request.setAttribute("xmzj", xmzj);
		model.setPkValue(pkValue);
		
		// ================查询操作================
		ArrayList<String[]> rsList = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		topTr = service.getTopTr("ffdx_xs");
		rsList = service.getFfxsList(model);
		// ================查询操作end================
		
		RequestForm rForm = new RequestForm();
		service.setRequestValue(rForm, request);

		//==================页面结果集显示===================
		//记录数
		if(rsList!=null && rsList.size()>0){
			request.setAttribute("rsNum", rsList.size());
		}
		//表头
		request.setAttribute("topTr", topTr);
		//结果集
		request.setAttribute("rsList", rsList);
		
		// ==================页面结果集显示end===================

		// ----------------初始化request的值 ----------------
		request.setAttribute("pkValue", pkValue);
		service.setList(myForm, request, "swff");
		// ----------------end ----------------
		
		return mapping.findForward("ffrManage");
	}
	
	/**
	 * 日常事务_实物发放_发放人员维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffDxtzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		RcswForm myForm=(RcswForm)form;
		RcswZjxyService service=new RcswZjxyService();
		String tableName="xg_rcsw_swfflq";
		String viewName="view_xg_rcsw_swfflq";
		String doType=request.getParameter("doType");
		String userName=session.getAttribute("userName").toString();
		
		myForm.setUserName(userName);
		if("dxtzfk".equalsIgnoreCase(doType)){
			service.getDxhz(myForm);
			doType="query";
		}
		
		if("query".equalsIgnoreCase(doType)){
			String[]outputColumn={"pkValue","xhzgh","xm","fsnr","hznr"};
			this.selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		}
		
		request.setAttribute("path", "rcsw_swff_dxtz.do");
		FormModleCommon.commonRequestSet(request);
		//
		service.setList(myForm, request, "dxtz");
		return mapping.findForward("swffDxtzManage");
	}
	
	/**
	 * 以学生为主体的项目发放
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * author qlj
	 */
	public ActionForward swffFfryqrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		BasicService basicService=new BasicService();
		XsxxglService xsxxglService = new XsxxglService();
		RcswZjxyService service=new RcswZjxyService();
		String doType=request.getParameter("doType");
		RcswForm myForm=(RcswForm)form;
		
		RcswZjxyModel model=new RcswZjxyModel();
		BeanUtils.copyProperties(model, myForm);
		
		String userName=session.getAttribute("userName").toString();
		
		myForm.setUserName(userName);
		//查询
		if("query".equalsIgnoreCase(doType)){
			request.setAttribute("topTr", basicService.getTopTr("xg_swff_ffryb",
					new String[] { "项目类型", "项目名称", "学年","学期","年度","办理开始时间","办理结束时间"}));
			request.setAttribute("rs",service.getXsFfxm(myForm));
		}
		
		//保存
		if("save".equalsIgnoreCase(doType)){
			
			request.setAttribute("result", service.saveFfxm(myForm));
		}
		
		HashMap<String,String>xsxx=new HashMap<String,String>();
		if(!Base.isNull(myForm.getZgh())){
			myForm.setZgh(myForm.getZgh());
			xsxx= xsxxglService.selectStuinfo(myForm.getZgh());
		
			if(xsxx==null || Base.isNull(xsxx.get("xh"))){
				request.setAttribute("message", "输入的学号不存在,请使用身份证号再试!");
			}
		}else if(!Base.isNull(myForm.getSfzh())){
			
			xsxx=service.getXhBySfzh(myForm.getSfzh());
			myForm.setZgh(xsxx.get("xh"));
			if(xsxx==null || xsxx.size()==0){
				request.setAttribute("message", "输入的身份证号不存在,请使用学号再试!");
			}
		}
		request.setAttribute("xsxx", xsxx);
		request.setAttribute("path", "rcsw_swff_ffryqr.do");
		request.setAttribute("xmmcList", service.getXmmcList());
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("swffFfryqrManage");
	}
	
	
	
	
	/**
	 * 数据导出
	 * method expDate
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward expDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RcswForm myForm = (RcswForm) form;
		String tableName=myForm.getTableName();
		String viewName=myForm.getViewName();
		
		String[]outPut=null;
		if("view_rcsw_swffrqwh".equalsIgnoreCase(viewName)){
			
			String pk=request.getParameter("pk")==null ? "" : request.getParameter("pk");
			if(!"".equalsIgnoreCase(pk) && pk!=null ){
				request.setAttribute("annexTerm", " and xn||xq||nd||xmlx||ffsj='"+pk+"'" );
			}
			outPut=new String[]{"zgh","xm","xn","xqmc","nd","xmmc","ffsj","xymc","zymc","bjmc"};
			
		}else if("view_xg_swff_ffryb".equalsIgnoreCase(viewName)){
				String query=" and sfqd='是' ";
				String xmlx=myForm.getXmlx();
				if(!"".equalsIgnoreCase(xmlx)){
					query+="  and xmlx= '"+xmlx+"' ";
				}
				outPut=new String[]{"xhzgh","xydm","zydm","xymc","zymc","bjdm","bjmc","xm","nj","xmmc","xqmc",
							"xn","xq","nd","ffsj","xmlx"};
				request.setAttribute("annexTerm",query);
		}
		
		
		try {
			this.expPageData(request, response, tableName, viewName, outPut);
		} catch (Exception e) {
			e.printStackTrace();
		}			
		
		return mapping.findForward("success");
	}
	
	/**
	 * 日常事务_实物发放_结果统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffJgTjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();
		
		// ---------------- 赋初值 ----------------
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_rcsw_swffxmwh";
		// 表名
		String realTable = "rcsw_swffxmwhb";
		// 访问路径
		String path = "zjxy_rcsw_swff_jgtj.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 是否管理员
		boolean isAdmin = true;
		
		User user = getUser(request);
		
		myForm.setUser(user);
		
		CommService commService = new CommService();
		
		boolean isXy = commService.isXy(user);
		
		List<HashMap<String, String>> topTr = null;
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		BeanUtils.copyProperties(model, myForm);
		
		// -----------------end-----------------------
	
		// ----------------判断权限 ----------------
		if (!"xx".equalsIgnoreCase(userType)&&!"admin".equalsIgnoreCase(userType)) {
			if(!isXy){
				myForm.setZgh(userName);
			}
			isAdmin = false;;
		}
		// -----------------end-----------------------
		
		// ----------------执行查询操作 ----------------
		if (search) {
			topTr = service.getTopTr("swff_jgtj");
			rs = service.getFfxxList(myForm);
		}
		// -----------------end-----------------------
		
		// ----------------初始化request的值 ----------------
		
		String[] qtzd = new String[] { "isAdmin" };
		String[] qtzdz = new String[] { String.valueOf(isAdmin) };
		
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		service.setRequestValue(rForm, request);
		request.setAttribute("isXy", isXy);
		
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		
		// ----------------end ----------------

		// ----------------初始化request的值 ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------
		
		
		return mapping.findForward("swffJgTjManage");
	}
	
	/**
	 * 日常事务_实物发放_结果统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffJgTjOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswForm myForm = (RcswForm) form;
		User user = getUser(request);
		myForm.setUser(user);
		DAO dao=DAO.getInstance();
		String cxxx=request.getParameter("cxxx");
		String pk=request.getParameter("pk");
		RcswZjxyService service=new RcswZjxyService();
		String[]colList={"学号","姓名","年级",Base.YXPZXY_KEY,"专业","班级"};
		
		request.setAttribute("cxxx", cxxx);
		request.setAttribute("pk", pk);
		request.setAttribute("topTr", dao.arrayToList(colList, colList));
		request.setAttribute("rsArrList",service.getTjxxOne(myForm));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("swffJgTjOne");
	}
}
	
