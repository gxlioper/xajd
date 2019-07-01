package xgxt.dtjs.czxx.tyxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.comm.MessageInfo;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.dtjs.czxx.dyxx.DyxxService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.SearchUtils;
import xgxt.xszz.zgmsxy.XszzZgmsxyActionForm;
import xgxt.xszz.zgmsxy.XszzZgmsxyService;

public class TyxxAction extends BasicAction {
	
	/***
	 * 加载页面参数
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": (xydm==null ? "" : xydm); 
		zy = zy==null ? "": (zydm==null ? "" : zydm); 
		njLocal = nj==null ? "": nj;
		
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		
				
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表	
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
	}
	
	/**
	 * 团员信息管理
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward tyxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		TyxxService service = new TyxxService();
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		TyxxModel model = new TyxxModel();

		String tableName = "view_czxx_tyxx";
		String realTable = "tyxxb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		boolean reslut = false;
		
		// 批量保存团员
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			// 团员学号
			String[] tyxh = myForm.getTyxh();
			String[] rtrq = myForm.getRtrq();
			String[] rtdd = myForm.getRtdd();
			
			// 页面所有学号
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xh";
				String[] arrzd = new String[] {"xh", "rtrq", "rtdd"};
				
				String[] rqarr = null;
				String[] ddarr = null;
				if(tyxh !=null && tyxh.length>0){
					rqarr = new String[tyxh.length];
					ddarr = new String[tyxh.length];
					
					for(int i=0;i<tyxh.length;i++){
						for(int j=i;j<checkVal.length;j++){
							if(tyxh[i].equalsIgnoreCase(checkVal[j])){
								rqarr[i] = rtrq[j];
								ddarr[i] = rtdd[j];
								break;
							}
						}
					}
				}
				
				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);
				saveForm.setArrzd(arrzd);
				
				TyxxbcModel tyxxbc = new TyxxbcModel();
				tyxxbc.setXh(tyxh);
				tyxxbc.setRtrq(rqarr);
				tyxxbc.setRtdd(ddarr);
				reslut = service.saveTyxx(saveForm, tyxxbc);
				request.setAttribute("result", reslut);
			}
		}
		if(!Base.isNull(userType) && "xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
			myForm.setXydm(userDep);
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| reslut) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "xymc", "zymc", "bjmc", "sfty", "rtrq", "rtdd"};
			if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CDTYXY)){
				
				colList = new String[] { "pk","xh", "xm", "xb", "xymc", "zymc", "bjmc", "sfty", "nd", "rtrq", "rtdd"};
			}
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.queryTyxxList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "dtjs_tyxx.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// 设置列表值
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,	topTr);
		if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CDTYXY)){
			request.setAttribute("xntj", "xntj");
			
		}
		return mapping.findForward("tyxxManage");
	}
	
	/**
	 * 团员信息修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tyxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		TyxxService service = new TyxxService();
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, "tyxxb");
		}
		
		request.setAttribute("rs", service.getTyxxOne(pkValue));
		return mapping.findForward("tyxxUpdate");
	}
	
	/**
	 * 团员信息查询导出
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward tyxxExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TyxxModel model = new TyxxModel();
		TyxxService service = new TyxxService();
		model.setXh(request.getParameter("xh"));
		model.setXm(request.getParameter("xm"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setNj(request.getParameter("nj"));
		model.setSfty(request.getParameter("sfty"));
		
		String[] colList = {"xh","xm","xb","xydm","xymc","zydm","zymc","bjdm","bjmc","nj","sfty"};
		String[] colListCN = service.getColumnNameCN(colList, "view_czxx_tyxx");
		ArrayList<String[]> rs = service.queryTyxxForExport("view_czxx_tyxx",model,colList);//查询团员信息
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());		
		return mapping.findForward("");
	}
	
	/**
	 * 非团员培训信息管理
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward ftypxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		String doType = request.getParameter("doType");

		TyxxService service = new TyxxService();
		CzxxDtjsForm model = (CzxxDtjsForm) form;

		String tableName = "view_czxx_ftypxxxb";
		String realTable = "ftypxxxb";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		boolean result = false;
		
		// 批量删非党员培训信息
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = model.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xh||pxxmdm||pxsj";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delFtypxxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		if(!Base.isNull(userType) && "xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
			//myForm.setXydm(userDep);
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk","xh", "xm", "xb", "xymc", "zymc", "bjmc", "nd","xn","xqmc","pxxmmc","pxsj","pxcj" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.queryFtypxxxList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "dtjs_ftypx.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// 设置列表值
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,	topTr);
		return mapping.findForward("ftypxManage");
	}
	
	/**
	 * 非团员培训信息维护
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 */
	public ActionForward ftypxxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title = "党团建设 - 常州团建 - 非团员培训  - 信息维护";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_czxx_ftypxxxb";
		String realTable = "ftypxxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm model = (CzxxDtjsForm) form;
		TyxxService service = new TyxxService();
		StudentInfoService xsxxService = new StudentInfoService();
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = xsxxService.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "xn", "xq","xqmc",
					"nd", "pxxmdm", "pxxmmc","pxcj","pxsj","bz" };
			rs = service.queryFtypxInfo(tableName, pk, pkValue, colList);
			model.setXydm(rs.get("xydm"));
		}

		// 保存非团员培训信息
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "pxxmdm",
					"pxsj","pxcj","bz" };
			pk = "xh||pxxmdm||pxsj";
			pkValue = model.getXh()+model.getPxxmdm()+model.getPxsj();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveFtypxxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		request.setAttribute("pxxmList", service.queryFtypxxmList());
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		return mapping.findForward("ftypxxxUpdate");
	}
	
	/**
	 * 团员培训信息管理
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward typxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		String doType = request.getParameter("doType");

		TyxxService service = new TyxxService();
		CzxxDtjsForm model = (CzxxDtjsForm) form;

		String tableName = "view_czxx_typxxxb";
		String realTable = "typxxxb";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		boolean result = false;
		
		// 批量删非党员培训信息
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = model.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xh||pxxmdm||pxsj";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delFtypxxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		if(!Base.isNull(userType) && "xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
			//myForm.setXydm(userDep);
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk","xh", "xm", "xb", "xymc", "zymc", "bjmc", "nd","xn","xqmc","pxxmmc","pxsj","pxcj" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.queryFtypxxxList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "dtjs_typx.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// 设置列表值
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,	topTr);
		return mapping.findForward("typxManage");
	}
	
	/**
	 * 团员培训信息维护
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 */
	public ActionForward typxxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_czxx_typxxxb";
		String realTable = "typxxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm model = (CzxxDtjsForm) form;
		TyxxService service = new TyxxService();
		StudentInfoService xsxxService = new StudentInfoService();
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = xsxxService.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "xn", "xq","xqmc",
					"nd", "pxxmdm", "pxxmmc","pxcj","pxsj","bz" };
			rs = service.queryFtypxInfo(tableName, pk, pkValue, colList);
			model.setXydm(rs.get("xydm"));
		}

		// 保存非团员培训信息
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "pxxmdm",
					"pxsj","pxcj","bz" };
			pk = "xh||pxxmdm||pxsj";
			pkValue = model.getXh()+model.getPxxmdm()+model.getPxsj();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveFtypxxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		request.setAttribute("path", "dtjs_typx.do");
		FormModleCommon.commonRequestSet(request);
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		request.setAttribute("pxxmList", service.queryTypxxmList());
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		return mapping.findForward("typxxxUpdate");
	}
	
	/**
	 * 团内推优信息管理
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward tntyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		String doType = request.getParameter("doType");
		String fdyQx = session.getAttribute("fdyQx").toString();
		boolean isBzr = !Base.isNull(fdyQx) && "true".equalsIgnoreCase(fdyQx) ? true : false;//Fdypd.isBzr(userName, "");
		String shjg = request.getParameter("shjg");

		TyxxService service = new TyxxService();
		CzxxDtjsForm model = (CzxxDtjsForm) form;

		String tableName = "view_yxtyxxb";
		String realTable = "yxtyxxb";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		boolean result = false;
		
		if(!Base.isNull(userType) && "xy".equalsIgnoreCase(userType) && !isBzr){
			model.setXydm(userDep);
		}
		
		// 批量删出优秀团员信息
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = model.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xh||xn||nd||xq";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delFtypxxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		// 批量审核优秀团员信息
		if (!Base.isNull(doType) && "auditing".equalsIgnoreCase(doType)) {
			String[] checkVal = model.getCheckVal();
			String message = service.checkTy(checkVal);
			String[] shjd = null;			
			
			if(!Base.isNull(message) && "通过".equalsIgnoreCase(shjg)){//不是团员 且审核通过
				request.setAttribute("error", message+"审核失败！");
				result = true;
			}else{
				if(isBzr){
					model.setBzrsh(shjg);
					shjd = new String[]{"bzrsh"};
				}else if(!isBzr && "xy".equalsIgnoreCase(userType)){
					model.setXysh(shjg);
					shjd = new String[]{"xysh"};
				}else if(!isBzr && ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType))){
					model.setXxsh(shjg);
					shjd = new String[]{"xxsh"};
				}
				if (checkVal != null && checkVal.length > 0) {
					String pk = "xh||xn||nd||xq";
	
					SaveForm saveForm = new SaveForm();
					saveForm.setTableName(realTable);
					saveForm.setPk(pk);
					saveForm.setPkValue(checkVal);
					saveForm.setOnezd(shjd);
	
					result = service.auditingTnty(saveForm, model);
					request.setAttribute("result", result);
					
					model.setBzrsh("");
					model.setXysh("");
					model.setXxsh("");
				}
			}
		}
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			
			String[] colList = null;
			String xxdm = Base.xxdm;
			
			if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
				model.setIsBzr(isBzr ? "true" : "false");
				model.setUserName(userName);
				colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
						"zymc", "bjmc", "nd", "xn", "xqmc", "bzrsh", "xysh",
						"xxsh" };
				if (!isBzr && "xy".equalsIgnoreCase(userType)) {// 学院用户
					model.setBzrsh("通过");
				}
				if (!isBzr && "xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {// 学校用户
					model.setBzrsh("通过");
					model.setXysh("通过");
				}
			} else {
				colList = new String[] { "pk", "nd", "xn", "xqmc", "xh", "xm", "xb", "xymc",
						"zymc", "bjmc"};
			}
			
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.queryFtypxxxList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "dtjs_tnty.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("isBzr", isBzr);
		// 设置列表值
		DyxxService dtService = new DyxxService();
		CzxxDtjsForm dtModel = new CzxxDtjsForm();
		dtService.setList(dtModel, request);
		//appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,	topTr);
		if(isBzr){
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
		}
		return mapping.findForward("tntyManage");
	}
	
	/**
	 * 团内推优信息维护
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 */
	public ActionForward tntyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title = "党团建设 - 常州团建 - 团内推优  - 信息维护";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_yxtyxxb";
		String realTable = "yxtyxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm model = (CzxxDtjsForm) form;
		TyxxService service = new TyxxService();
		StudentInfoService xsxxService = new StudentInfoService();
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = xsxxService.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "xn", "xq","xqmc",
					"nd", "bzrsh", "xysh","xxsh","bz" };
			rs = service.queryFtypxInfo(tableName, pk, pkValue, colList);
			model.setXydm(rs.get("xydm"));
		}

		// 保存非团员培训信息
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd","bz" };
			pk = "xh||xn||nd||xq";
			pkValue = model.getXh()+model.getXn()+model.getNd()+model.getXq();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveFtypxxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		request.setAttribute("pxxmList", service.queryTypxxmList());
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);		
		return mapping.findForward("tntyUpdate");
	}	
	
	/**
	 * 团员注册管理
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 */
	public ActionForward tyzcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//获取user对象
		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		//forms
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		TyxxService service = new TyxxService();
		CommService commService=new CommService();

		String doType=request.getParameter("doType");
		
		String message="";
		
		//删除操作
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.delTyzc(myForm);
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", message);
		}
		
//		 ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		// 显示列数
		String showNum = String.valueOf(service.getTopTr(rForm).size());
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);
		myForm.setUser(user);
		rForm.setRsArrList((ArrayList<String[]>)service.getTyzcList(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============通用设置 end ============
		
		
		request.setAttribute("path", "dtjs_tyzc.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		//表头
		request.setAttribute("topTr", service.getTopTr(rForm));
		return mapping.findForward("tyzcManage");
	}	
	
	/**
	 * 团员注册
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 */
	public ActionForward tyzcAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		TyxxService service = new TyxxService();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		
		
		// 生源地贷款保存
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveTyzc(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}

		
		request.setAttribute("path",  "dtjs_tyzc.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("zcsj", GetTime.getNowTime2());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("rs", stuInfo);
		request.setAttribute("tableName", "view_tyxxb");
		request.setAttribute("doType", "add");
		return mapping.findForward("tyzcUpdate");
	}	
	
	/**
	 * 团员注册修改
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 */
	public ActionForward tyzcUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		TyxxService service = new TyxxService();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		myForm.setPkV(new String[]{pkValue});
		
		// 生源地贷款保存
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.updateTyzc(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
			doType="update";
		}

		request.setAttribute("path", "dtjs_tyzc.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("rs", service.getOneTyzc(myForm));
		
		request.setAttribute("doType", doType);
		return mapping.findForward("tyzcUpdate");
	}	
	
}
