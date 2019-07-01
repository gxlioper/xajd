package xgxt.pjpy.gzdx;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyGzdxAction extends CommonAction {
	
	/**
	 * 处分限制信息设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfxzsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		String act = request.getParameter("act");
		PjpyGzdxService service = new PjpyGzdxService();
		PjpyGzdxModel model = null;
		
		if ("save".equalsIgnoreCase(act)) {
			model = new PjpyGzdxModel();
			BeanUtils.copyProperties(model, gzdxForm);
			boolean result = service.saveCfszxx(model);
			request.setAttribute("result", result);
		} else {
			model = service.queryCfszxx();
			BeanUtils.copyProperties(gzdxForm, model);
		}
		appendCflbCfyy(request);
		return mapping.findForward("cfxzsz");
	}
	
	/**
	 * 综合素质测评维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		//学院用户
		if ("xy".equalsIgnoreCase(userType)) {
			gzdxForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		} else if ("stu".equalsIgnoreCase(userType) || "student".equalsIgnoreCase(userType)) {
			return stuQueryZhszcpxx(mapping, form, request, response);
		}
		
		List<HashMap<String, String>> topTr = 
									new ArrayList<HashMap<String, String>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		PjpyGzdxService service = new PjpyGzdxService();
		PjpyGzdxModel model = null;
		//查询数据
		if ("query".equalsIgnoreCase(request.getParameter("operType"))) {
			model = new PjpyGzdxModel();
			BeanUtils.copyProperties(model, gzdxForm);
			rs = (ArrayList<String[]>)service.queryZhszcpxx(model);
			topTr = service.queryZhszcpTitle();
		}
		if ("account".equalsIgnoreCase(request.getParameter("act"))) {
			model = new PjpyGzdxModel();
			model.setXn(gzdxForm.getXn());
			model.setXydm(gzdxForm.getXydm());
			boolean result = service.zhszcpAccount(model);
			request.setAttribute("result", result);
		}
		
		//载下拉列表
		request.setAttribute("path", "data_search.do?act=zhsz");
		FormModleCommon.commonRequestSet(request, "view_gzdx_zhszcp", "gzdx_zhszcpb", rs, topTr);
		setNjXyZyBjList(request, gzdxForm);
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * 删除综测数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpwhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] keys = request.getParameterValues("cbv");
		PjpyGzdxService service = new PjpyGzdxService();
		boolean result = false;
		if (keys != null) {
			result = service.deleteZhszcpxx(keys);
			request.setAttribute("result", result);
		}
		return zhszcpwh(mapping, form, request, response);
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addZhszcpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		PjpyGzdxService service = new PjpyGzdxService();
		String xh = request.getParameter("xh");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = CommonQueryDAO.getStuInfo(xh);
		}
		
		//保存数据
		if ("save".equalsIgnoreCase(request.getParameter("operType"))) {
			PjpyGzdxModel model = new PjpyGzdxModel();
			BeanUtils.copyProperties(model, gzdxForm);
			boolean result = service.addZhszcpxx(model);
			appendOperResult(result, request);
		}
		request.setAttribute("rs", rs);
		setNjXyZyBjList(request, gzdxForm);
		return mapping.findForward("addGzdxZhszcpxx");
	}
	
	/**
	 * 修改综合素质测评信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiZhszcpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();
		PjpyGzdxService service = new PjpyGzdxService();
		String pkValue = request.getParameter("pkValue");
		//保存数据
		if ("save".equalsIgnoreCase(request.getParameter("operType"))) {
			PjpyGzdxModel model = new PjpyGzdxModel();
			BeanUtils.copyProperties(model, gzdxForm);
			model.setPkValue(pkValue);
			boolean result = service.updateZhszcpxx(model);
			appendOperResult(result, request);
		} else if ("view".equalsIgnoreCase(request.getParameter("operType"))) {
			request.setAttribute("view", "yes");
		}
		rs = service.queryZhszcpOnexx(pkValue);
		if (rs != null) {
			gzdxForm.setXycpf(rs.get("xycpf"));
			gzdxForm.setZhbxf(rs.get("zhbxf"));
			gzdxForm.setTcbxf(rs.get("tcbxf"));
			gzdxForm.setXwbxf(rs.get("xwbxf"));
			gzdxForm.setZf(rs.get("zf"));
			gzdxForm.setXn(rs.get("xn"));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		setNjXyZyBjList(request, gzdxForm);
		return mapping.findForward("modiZhszcpxx");
	}

	/**
	 * 加载学院，年级，专业，班级下拉列表值
	 * @param request
	 * @param myForm
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void setNjXyZyBjList(HttpServletRequest request,
			PjpyGzdxActionForm myForm) throws Exception{
		// 在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		//
		if ("xy".equalsIgnoreCase(userType)) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if (session.getAttribute("fdyQx").toString() != null
				&& "true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())) {
			// 辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// 发送班级列表
		}
	}
	/**
	 * 综合表现分申报
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SQLException 
	 */
	public ActionForward  xsZhbxfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, SQLException{
		PjpyGzdxActionForm myForm = (PjpyGzdxActionForm)form;
		PjpyGzdxService service = new PjpyGzdxService();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String act   = DealString.toString(request.getParameter("act"));
		String strV = request.getParameter("strVal");
		myForm.setXn(Base.getJxjsqxn());
		String xn = myForm.getXn();
		if("student".equalsIgnoreCase(userOnLine)){
			xh = userName;
		}
		String clin = "";
		if("view".equalsIgnoreCase(act)){
			clin = "当前所在位置：评奖评优 - 综测信息维护 - 综合表现查看";
		}else if("modi".equalsIgnoreCase(act)){
			clin = "当前所在位置：评奖评优 - 综测信息维护 - 综合表现修改";
		}else{
			clin = "当前所在位置：评奖评优 - 综测信息维护 - 综合表现申报";
		}
		if("save".equalsIgnoreCase(doType)){
			ZhbxxfModel model = new ZhbxxfModel();
			BeanUtils.copyProperties(model, myForm);
			boolean done =  service.serv_xsZhbxfsb(model,act,strV);
			request.setAttribute("done",done);
		}
		StringBuffer strVal = new StringBuffer("");
		if(Base.isNull(pkValue)){
			pkValue = (Base.isNull(xh))?"":xh+xn;
		}else{
			xh = xh.trim();
			xn = xn.trim();
		}
		List<HashMap<String,String>> valList = CommonQueryDAO.dao_getInfotoList("zhszbxfb", null," where xh||xn='"+pkValue+"'");
		if(valList!=null&&valList.size()>0){
			for(int i=0;i<valList.size();i++){
				strVal.append(valList.get(i).get("lb")).append("!!");
				strVal.append(valList.get(i).get("dm")).append("!!");
				strVal.append(Base.isNull(valList.get(i).get("nr"))?"":valList.get(i).get("nr")).append("!!");
				strVal.append(Base.isNull(valList.get(i).get("fslb"))?"":valList.get(i).get("fslb")).append(Base.isNull(valList.get(i).get("fs"))?"":valList.get(i).get("fs")).append("#");
			}
			if(!Base.isNull(strVal.toString())){
				strVal.deleteCharAt(strVal.length()-1);
			}
		}
		request.setAttribute("rsVal", strVal.toString());
		request.setAttribute("xn",xn);
		HashMap<String, String> xsMap = CommonQueryDAO.getStuInfo(xh);
		request.setAttribute("rs",xsMap);
		request.setAttribute("act",act);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("clin",clin);
		return mapping.findForward("xsZhbxfsb");
	}
	
	public void appendOperResult(boolean result, HttpServletRequest request) {
		if (result) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
	}
	
	/**
	 * 导出综合素质测评数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expZhszcp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		PjpyGzdxModel model = new PjpyGzdxModel();
		BeanUtils.copyProperties(model, gzdxForm);
		PjpyGzdxService service = new PjpyGzdxService();
		String modelPath = "";
		modelPath = servlet.getServletContext().getRealPath("")+"/print/gzdx/gzdx_zhszcpb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.exportZhszcpData(wwb, model);
		return mapping.findForward("");
	}
	
	/**
	 * 学院申报获奖数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xysbHjdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
													.toString();
		gzdxForm.setXn(Base.getJxjsqxn());
		PjpyGzdxService service = new PjpyGzdxService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = 
										new ArrayList<HashMap<String,String>>();
		PjpyGzdxModel model = null;
		//学院用户
		if ("xy".equalsIgnoreCase(userType)) {
			gzdxForm.setXydm(request.getSession().getAttribute("userDep")
													.toString());
		}
		//查询数据操作
		if ("query".equalsIgnoreCase(request.getParameter("operType"))) {
			model = new PjpyGzdxModel();
			BeanUtils.copyProperties(model, gzdxForm);
			//rs = (ArrayList<String[]>)service.queryHjsbdata(model, userType);
			rs = service.queryHjsbStuList(model, userType);
			topTr = service.queryHjsbTitle();
//			if (!StringUtils.isNull(request.getParameter("dm"))) {
//				//学院单个奖学金的限制人数
//				String jyrs = service.getXyxzrs(model);
//				request.setAttribute("jyrs", 5);
//			}
		}
		
		//加载下拉列表
		request.setAttribute("path", "pjpy_gzdx_xysbHjdata.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		request.setAttribute("dmList", service.getDmList(request
				.getParameter("lb")));
		setNjXyZyBjList(request, gzdxForm);
		return mapping.findForward("xysbHjdata");
	}
	
		
	/**
	 * 综合表现查询
	 * @throws Exception 
	 */
	public ActionForward xsZhbxfQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyGzdxActionForm myForm = (PjpyGzdxActionForm)form;
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userName   = request.getSession().getAttribute("userName").toString();
		String userType   = request.getSession().getAttribute("userType").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		String xn = myForm.getXn();
		String search=request.getParameter("go");
		if(xn==null){
			//myForm.setXn(Base.getJxjsqxn());
		}
		if("student".equalsIgnoreCase(userOnLine)){
			HashMap<String, String> xsMap = CommonQueryDAO.getStuInfo(userName);
			myForm.setNj(xsMap.get("nj"));
			myForm.setXydm(xsMap.get("xydm"));
			myForm.setZydm(xsMap.get("zydm"));
			myForm.setBjdm(xsMap.get("bjdm"));
			myForm.setXh(userName);
			myForm.setXm(xsMap.get("xm"));
			search = "go";
		}
		List<HashMap<String, String>> topTr = 
			new ArrayList<HashMap<String, String>>();
        ArrayList<String[]> rs = new ArrayList<String[]>();
		if("go".equalsIgnoreCase(search)){			
			ZhbxxfModel model = new ZhbxxfModel();
			BeanUtils.copyProperties(model, myForm);
			PjpyGzdxService service = new PjpyGzdxService();
			rs = (ArrayList<String[]>)service.serv_xsZhbxfQuery(model);
			topTr = service.queryxsZhbxfTitle();
		}
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		//读写权判断		
		String writeAble = (Base.chkUPower(userName,"pjpy_gzdx_xsZhbxfQuery.do", userOnLine.equalsIgnoreCase("student"))==1)?"yes":"no";
		if("student".equalsIgnoreCase(userOnLine)){
			writeAble = "no";
		}
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("userType",userType);
		return mapping.findForward("xsZhbxfQuery");
	}
	/**
	 * 批量删除综合表现
	 */
	public ActionForward plDelZhbx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkV = request.getParameter("delPk");
		PjpyGzdxService service = new PjpyGzdxService();
		service.serv_delZhbx(pkV);
		return xsZhbxfQuery(mapping, form, request, response);
	}
	/**
	 * 综合表现评分查询
	 */
	public ActionForward xsZhbxpfQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		PjpyGzdxActionForm myForm = (PjpyGzdxActionForm)form;
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userName   = request.getSession().getAttribute("userName").toString();
		String userType   = request.getSession().getAttribute("userType").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		String xn = myForm.getXn();
		if(xn==null){
			//myForm.setXn(Base.getJxjsqxn());
		}
		List<HashMap<String, String>> topTr = 
			new ArrayList<HashMap<String, String>>();
        ArrayList<String[]> rs = new ArrayList<String[]>();
		if("go".equalsIgnoreCase(request.getParameter("go"))){			
			ZhbxxfModel model = new ZhbxxfModel();
			BeanUtils.copyProperties(model, myForm);
			PjpyGzdxService service = new PjpyGzdxService();
			rs = (ArrayList<String[]>)service.serv_xsZhbxfQuery(model);
			topTr = service.queryxsZhbxfTitle();
		}
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		//读写权判断		
		String writeAble = (Base.chkUPower(userName,"pjpy_gzdx_xsZhbxpf.do", userOnLine.equalsIgnoreCase("student"))==1)?"yes":"no";
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("xsZhbxpfQuery");
	}
	/**
	 * 综合表现评评分
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SQLException 
	 */
	public ActionForward zhBxPf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, SQLException{
//		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
//		String userName = request.getSession().getAttribute("userName").toString();
		PjpyGzdxActionForm myForm = (PjpyGzdxActionForm)form;
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String act = DealString.toString(request.getParameter("act"));
	    if("save".equalsIgnoreCase(doType)){
			ZhbxxfModel model = new ZhbxxfModel();
			BeanUtils.copyProperties(model, myForm);
			PjpyGzdxService service = new PjpyGzdxService();
			boolean done =  service.ser_zhbxpf(model);
			request.setAttribute("done",done);
	    }
		List<HashMap<String,String>> valList = CommonQueryDAO.dao_getInfotoList("view_zhszbxf", new String[]{"r","lb","lbmc","dm","mc","nr","fslb","fs"}," where xh||xn='"+pkValue+"' ");
		HashMap<String, String> xsMap = CommonQueryDAO.dao_getInfo("view_zhszbxf", null," where xh||xn='"+pkValue+"' ");
		request.setAttribute("rs",xsMap);
		request.setAttribute("rsVal",valList);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("xn",xsMap.get("xn"));
		request.setAttribute("act",act);
		return mapping.findForward("zhBxPf");
	}
		
	/**
	 * 申报获奖名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sbhjmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		PjpyGzdxService service = new PjpyGzdxService();
		PjpyGzdxModel model = new PjpyGzdxModel();
		BeanUtils.copyProperties(model, gzdxForm);
		model.setXq(Base.getJxjsqxq());
		boolean result = service.saveHjmdData(model);
		request.setAttribute("result", result);
		return xysbHjdata(mapping, form, request, response);
	}
	
	/**
	 * 取消获奖名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteHjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		PjpyGzdxService service = new PjpyGzdxService();
		PjpyGzdxModel model = new PjpyGzdxModel();
		BeanUtils.copyProperties(model, gzdxForm);
		model.setCbv(request.getParameterValues("cbv"));
		boolean result = service.deleteHjmdData(model);
		request.setAttribute("result", result);
		return xysbHjdata(mapping, form, request, response);
	}
	
	/**
	 * 单个申报获奖数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewHjsbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String userType = request.getSession().getAttribute("userType").toString();
		String[] xhxnList = StringUtils.isNull(pkValue) ? new String[3]
				: pkValue.split("!@");
		PjpyGzdxService service = new PjpyGzdxService();
		String lb = request.getParameter("lb");
		PjpyGzdxModel model = new PjpyGzdxModel();
		model.setXh(xhxnList[0]);
		model.setXn(xhxnList[1]);
		model.setDm(xhxnList.length > 2 ? xhxnList[2] : "");
		model.setLb(lb);
		HashMap<String, String> rs = service.queryStuZhszcpxx(model);
		if ("save".equalsIgnoreCase(request.getParameter("operType"))) {
			model = new PjpyGzdxModel();
			BeanUtils.copyProperties(gzdxForm, model);
			boolean result = service.saveHjsbData(model, pkValue);
			appendOperResult(result, request);
		}
		if ("xy".equalsIgnoreCase(userType)
				&& "通过".equalsIgnoreCase(rs.get("xxsh"))) {
			request.setAttribute("view", "yes");
		}
		rs.put("xn", model.getXn());
		rs.put("dm", model.getDm());
		gzdxForm.setDm(model.getDm());
		//request.setAttribute("key", StringUtils.isNull(model.getDm()) ? "add" : "modi");
		request.setAttribute("rs", rs);
//		request.setAttribute("cj", StringUtils.isNull(request.getParameter("cj")) ? "" : request.getParameter("cj").trim());
//		request.setAttribute("wj", StringUtils.isNull(request.getParameter("wj")) ? "" : request.getParameter("wj").trim());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("dmList", service.getDmList(lb));
		request.setAttribute("lb", lb);
		return mapping.findForward("viewHjsbxx");
	}
	/**
	 * 奖学金结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjjgQuery (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyGzdxActionForm myForm = (PjpyGzdxActionForm)form;
		String xxdm = StandardOperation.getXxdm();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userName   = request.getSession().getAttribute("userName").toString();
		String userType   = request.getSession().getAttribute("userType").toString();
		boolean isFdy = Boolean.valueOf(request.getSession().getAttribute("isFdy").toString());
		String userDep   = request.getSession().getAttribute("userDep").toString();
		String xn = myForm.getXn();
		String search=request.getParameter("go");
		if(xn==null){
			//myForm.setXn(Base.currXn);
		}
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
			if(isFdy) {
				userType = "fdy";
			}
		}
		if("student".equalsIgnoreCase(userOnLine)){
			HashMap<String, String> xsMap = CommonQueryDAO.getStuInfo(userName);
			myForm.setNj(xsMap.get("nj"));
			myForm.setXydm(xsMap.get("xydm"));
			myForm.setZydm(xsMap.get("zydm"));
			myForm.setBjdm(xsMap.get("bjdm"));
			myForm.setXh(userName);
			myForm.setXm(xsMap.get("xm"));
			search = "go";
		}
		List<HashMap<String, String>> topTr = 
			new ArrayList<HashMap<String, String>>();
		PjpyGzdxService service = new PjpyGzdxService();
		List<String[]> rs = new ArrayList<String[]>();
		if("go".equalsIgnoreCase(search)){	
			PjpyGzdxModel model = new PjpyGzdxModel();
			BeanUtils.copyProperties(model,myForm);
			topTr = service.queryJxjRychTitle("jxj");
			
			if (Globals.XXDM_CQDZKJ.equals(xxdm)
					 ||Globals.XXDM_CQGYZY.equals(xxdm)) {
				rs = service.queryJxjRychResultForCq("jxj",userType,model);
			}else {
				rs = service.queryJxjRychResult("jxj",userType,model);
			}	
			
			
		}
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		//读写权判断		
		String writeAble = (Base.chkUPower(userName,"pjpy_gzdx_jxjjgQuery.do", userOnLine.equalsIgnoreCase("student"))==1)?"yes":"no";
		if("student".equalsIgnoreCase(userOnLine)){
			writeAble = "no";
		}
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("userType",userType);
		request.setAttribute("jxjList", service.getDmList("jxj"));
		return mapping.findForward("jxjjgQuery");
	}
	/**
	 * 删除容易称号或奖学金结果数据
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		PjpyGzdxService service = new PjpyGzdxService();
		PjpyGzdxModel model = new PjpyGzdxModel();
		BeanUtils.copyProperties(model, gzdxForm);
		String lb = request.getParameter("lb");
		model.setCbv(request.getParameterValues("cbv"));
		model.setLb(lb);
		boolean result = service.serv_deleteData(model);
		request.setAttribute("result", result);
		if("jxj".equalsIgnoreCase(lb)){
			return jxjjgQuery(mapping, form, request, response);
		}else{
			return rychjgQuery(mapping, form, request, response);
		}
	}
	/**
	 * 奖学金结果修改
	 * @throws Exception 
	 */
	public ActionForward jxjjgModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkValue = request.getParameter("pkValue");
		String doType  = request.getParameter("doType");
		PjpyGzdxActionForm myForm = (PjpyGzdxActionForm) form;
		String act    = request.getParameter("act");
		String userType   = request.getSession().getAttribute("userType").toString();
		PjpyGzdxService service = new PjpyGzdxService();
		
		if("save".equalsIgnoreCase(doType)){
			PjpyGzdxModel model = new PjpyGzdxModel();
			BeanUtils.copyProperties(model,myForm);
			model.setLb("jxj");
			boolean done = service.modiData(model, pkValue);
			request.setAttribute("done",done);
			if(done){
				pkValue = model.getXh()+model.getXn()+model.getJxjdm();
			}
		}
		HashMap<String, String> xsMap = CommonQueryDAO.dao_getInfo("view_xsjxjb", null," where xh||xn||jxjdm='"+pkValue+"' ");
		request.setAttribute("rs",xsMap);
		request.setAttribute("xn",Base.isNull(xsMap.get("xn"))?"":xsMap.get("xn"));
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("dmList", service.getDmList("jxj"));
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("act",act);
		request.setAttribute("userType",userType);
		return mapping.findForward("jxjjgModi");
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
	public ActionForward rychjgQuery (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyGzdxActionForm myForm = (PjpyGzdxActionForm)form;
		String xxdm = StandardOperation.getXxdm();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userName   = request.getSession().getAttribute("userName").toString();
		String userType   = request.getSession().getAttribute("userType").toString();
		String userDep   = request.getSession().getAttribute("userDep").toString();
		String xn = myForm.getXn();
		String search=request.getParameter("go");
		if(xn==null){
			//myForm.setXn(Base.currXn);
		}
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		if("student".equalsIgnoreCase(userOnLine)){
			HashMap<String, String> xsMap = CommonQueryDAO.getStuInfo(userName);
			myForm.setNj(xsMap.get("nj"));
			myForm.setXydm(xsMap.get("xydm"));
			myForm.setZydm(xsMap.get("zydm"));
			myForm.setBjdm(xsMap.get("bjdm"));
			myForm.setXh(userName);
			myForm.setXm(xsMap.get("xm"));
			search = "go";
		}
		PjpyGzdxService service = new PjpyGzdxService();
		List<HashMap<String, String>> topTr = 
			new ArrayList<HashMap<String, String>>();
		List<String[]> rs = new ArrayList<String[]>();
		if("go".equalsIgnoreCase(search)){	
			PjpyGzdxModel model = new PjpyGzdxModel();
			BeanUtils.copyProperties(model,myForm);
			topTr = service.queryJxjRychTitle("rych");
			
			if (Globals.XXDM_CQDZKJ.equals(xxdm)
					 ||Globals.XXDM_CQGYZY.equals(xxdm)) {
				rs = service.queryJxjRychResultForCq("rych",userType,model);
			}else {
				rs = service.queryJxjRychResult("rych",userType,model);
			}	
		}
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		//读写权判断		
		String writeAble = (Base.chkUPower(userName,"pjpy_gzdx_rychjgQuery.do", userOnLine.equalsIgnoreCase("student"))==1)?"yes":"no";
		if("student".equalsIgnoreCase(userOnLine)){
			writeAble = "no";
		}
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("userType",userType);
		request.setAttribute("rychList", service.getDmList("rych"));
		return mapping.findForward("rychjgQuery");
	}
	/**
	 * 荣誉称号结果修改
	 * @throws Exception 
	 */
	public ActionForward rychjgModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkValue = request.getParameter("pkValue");
		String doType  = request.getParameter("doType");
		PjpyGzdxActionForm myForm = (PjpyGzdxActionForm) form;
		String act    = request.getParameter("act");
		PjpyGzdxService service = new PjpyGzdxService();
		
		if("save".equalsIgnoreCase(doType)){
			PjpyGzdxModel model = new PjpyGzdxModel();
			BeanUtils.copyProperties(model,myForm);
			model.setLb("rych");
			boolean done = service.modiData(model, pkValue);
			request.setAttribute("done",done);
			if(done){
				pkValue = model.getXh()+model.getXn()+model.getRychdm();
			}
		}
		HashMap<String, String> xsMap = CommonQueryDAO.dao_getInfo("view_xsrychb", null," where xh||xn||rychdm='"+pkValue+"' ");
		request.setAttribute("rs",xsMap);
		request.setAttribute("xn",Base.isNull(xsMap.get("xn"))?"":xsMap.get("xn"));
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("dmList", service.getDmList("rych"));
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("act",act);
		return mapping.findForward("rychjgModi");
	}
	
	/**
	 * 奖学金，荣誉称号单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewJxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		String lb = request.getParameter("lb");
		String pkValue = request.getParameter("pkValue");
		PjpyGzdxService service = new PjpyGzdxService();
		HashMap<String, String> rs = service.viewJxjRychresult(pkValue, lb);
		if ("save".equalsIgnoreCase(request.getParameter("act"))) {
			PjpyGzdxModel model = new PjpyGzdxModel();
			BeanUtils.copyProperties(model, gzdxForm);
			model.setLb(lb);
			boolean result = service.saveJxjRychshxx(model, pkValue);
			appendOperResult(result, request);
		}
		if (rs != null) {
			gzdxForm.setSh(rs.get("xxsh"));
			gzdxForm.setYj(rs.get("xxyj"));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("lb", lb);
		request.setAttribute("title", "jxj".equalsIgnoreCase(lb) ? "奖学金审核" : "荣誉称号审核");
		request.setAttribute("shList", service.getShList());
		return mapping.findForward("viewJxjSh");
	}
	
	/**
	 * 学生查询综测评信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stuQueryZhszcpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xh = request.getSession().getAttribute("userName") != null ? request
				.getSession().getAttribute("userName").toString()
				: "";
		PjpyGzdxModel model = new PjpyGzdxModel();
		model.setXh(xh);
		PjpyGzdxService service = new PjpyGzdxService();
		List<HashMap<String, String>> topTr = service.queryZhszcpTitle();
		List<String[]> rs = service.queryZhszcpxx(model);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		return mapping.findForward("stuQueryZhszcpxx");
	}
	
	/**
	 * 统计汇总
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		setNjXyZyBjList(request, gzdxForm);
		return mapping.findForward("tjhz");
	}
	
	/**
	 * 导出统计数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expTjhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		PjpyGzdxService service = new PjpyGzdxService();
		PjpyGzdxModel model = new PjpyGzdxModel();
		BeanUtils.copyProperties(model, gzdxForm);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		service.expData(model, wwb);
		return mapping.findForward("");
	}
	
	
	/**
	 * 参数设置查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		PjpyGzdxService service = new PjpyGzdxService();
		
		String tableName = "view_gzdx_xyjxjrs";
		String realTable = "gzdx_xyjxjrsb";
		PjpyGzdxModel model = new PjpyGzdxModel();
		String key = gzdxForm.getKey();
		if(key==null||key.equalsIgnoreCase("")){
			key="jxj";
		}
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userDep = (String)session.getAttribute("userDep");
		if(userType.equalsIgnoreCase("xy")){
			gzdxForm.setXydm(userDep); 
		}
		gzdxForm.setKey(key); 
		gzdxForm.setXn(Base.getJxjsqxn());
		request.setAttribute("nd", Base.getJxjsqnd());
		request.setAttribute("xq", Base.getJxjsqxq());
		BeanUtils.copyProperties(model, gzdxForm);
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			topTr = service.getRsszTopTr();
			rs = service.getRsszList(model);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("path", "prise_conf_rs.do");
		request.setAttribute("topTr", topTr);
		request.setAttribute("shztList", service.queryShList());
		FormModleCommon.requestSetList(new String[]{"xy","xn","jxj","rych"}, request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("rssz");
	}
	
	/**
	 * 人数批量设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsblsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		PjpyGzdxService service = new PjpyGzdxService();
		
		String xydm = DealString.toGBK(request.getParameter("xydm"));
		String dm = DealString.toGBK(request.getParameter("dm"));
		String key = DealString.toGBK(request.getParameter("key"));
		String bl = DealString.toGBK(request.getParameter("szbl"));
		
	    boolean update = service.rsblsz(xydm,dm,key,bl);
	    
	    if(update){
	    	request.setAttribute("updateOK", "ok");
	    }else{
	    	request.setAttribute("updateOK", "no");
	    }
	    
		return rssz(mapping,form,request,response);
	}
	
	/**
	 * 初始化人数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cshrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		PjpyGzdxService service = new PjpyGzdxService();
		String key = request.getParameter("key");
	    boolean update = service.cshrs(key);
	    
	    if(update){
	    	request.setAttribute("initOK", "ok");
	    }else{
	    	request.setAttribute("initOK", "no");
	    }
	    
		return rssz(mapping,form,request,response);
	}		
	
	/**
	 * 单个人数调整
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dgrstz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");
		PjpyGzdxService service = new PjpyGzdxService();
		String pk = request.getParameter("pk");
		HashMap<String, String> rs  = service.getDgrsxx(pk);
	    request.setAttribute("rs", rs);
	    request.setAttribute("pk", pk);
	    if(userType.equalsIgnoreCase("xy")){
	    	return mapping.findForward("rsszXyOne");
	    }else{
	    	request.setAttribute("shztList", service.queryShList());
	    	return mapping.findForward("rsszXxOne");
	    }
	}
	
	/**
	 * 单个人数调整保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dgrsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyGzdxService service = new PjpyGzdxService();
		String pk = request.getParameter("pk");
		PjpyGzdxActionForm gzdxForm = (PjpyGzdxActionForm) form;
		PjpyGzdxModel model = new PjpyGzdxModel();
		BeanUtils.copyProperties(model, gzdxForm);
		boolean updata = service.dgrsUpdate(pk,model,request);
		if(updata){
			request.setAttribute("updata", "true");
		}else{
			request.setAttribute("updata", "false");
		}
	    return dgrstz(mapping, form,request,response);
	}
}
