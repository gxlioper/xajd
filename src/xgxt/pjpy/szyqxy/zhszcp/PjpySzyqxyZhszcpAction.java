package xgxt.pjpy.szyqxy.zhszcp;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.SaveForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpySzyqxyZhszcpAction extends CommonAction {

	private PjpySzyqxyZhszcpService service = PjpySzyqxyZhszcpService
			.getInstance();

	/**
	 * 组织能力管理
	 * 
	 * @throws SQLException
	 */
	public ActionForward szyc_zznlManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		// String userName =
		// request.getSession().getAttribute("userName").toString();
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;
		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		if (flg) {
			userType = "bzr";
		}
		if ("bzr".equalsIgnoreCase(userType)) {// 班主任
			return new ActionForward("/pjpyszyqwh.do?method=szyc_zznlQuery",
					false);
		} else {
			return new ActionForward("/pjpyszyqwh.do?method=szyc_zznlChk",
					false);
		}
	}

	/**
	 * 组织能力活动审核查询页面
	 * 
	 * @throws Exception
	 */
	public ActionForward szyc_zznlChk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String doType = request.getParameter("doType");
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			xydm = userDep;
		}
		
		if (xn == null) {
			myForm.setXn(Base.currXn);
		}
		if (xq == null) {
			myForm.setXq(Base.currXq);
		}
		
		boolean result = false;
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] key = myForm.getCheckVal();
			String shzt = request.getParameter("sh");
			result = service.saveSh("szyc_zznlfzb", key, shzt);
			request.setAttribute("result", result);
		}
		
		if ("go".equalsIgnoreCase(request.getParameter("go"))||result) {
			ZhszcpModel model = new ZhszcpModel();
			BeanUtils.copyProperties(model, myForm);
			List<HashMap<String, String>> topTr = service.serv_zznlChkTitle("szyc_zznlfzb");
			ArrayList<String[]> rs = service.serv_zznlChkSearch(model);
			request.setAttribute("rsNum", rs != null ? rs.size() : 0);
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
		}
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("userType", userType);
		
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		
		return mapping.findForward("zznlChk");
	}

	/**
	 * 组织能力活动审核
	 * 
	 * @throws Exception
	 */
	public ActionForward szyc_zznlViewAndChk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String xh = DealString.toGBK(request.getParameter("xh")).trim();
		String doType = request.getParameter("doType");
		if ("save".equalsIgnoreCase(doType)) {
			String shType = request.getParameter("check");
			String pkVStr = request.getParameter("pkVStr");
			boolean done = service.serv_zznlChk(pkVStr, shType);
			request.setAttribute("done", done);
		}
		ArrayList<String[]> rs = service.serv_zznlViewAndChkSearch(xh, xn, xq);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		request.setAttribute("rsxs", service.serv_getXsInfo(xh));
		request.setAttribute("rs", rs);
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		request.setAttribute("xh", xh);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("pkValue", xh + xn + xq);
		return mapping.findForward("zznlViewAndChk");
	}

	/**
	 * 组织能力活动查询页面 .
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_zznlQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;

		ZznlModel model = new ZznlModel();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		ArrayList<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String go = request.getParameter("go");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		if (myForm.getXn() == null) {
			myForm.setXn(Base.currXn);
		}
		if (myForm.getXq() == null) {
			myForm.setXq(Base.currXq);
		}
		if (flg) {
			userType = "bzr";
		}
		if ("go".equals(go)) {
			BeanUtils.copyProperties(model, myForm);
			PjpySzyqxyZhszcpService.formToGBK(model);
			rs = service.service_QueryZznl(model, userType, userName);
			topTr = service.getjxjTitle("szyc_zznlfzb");
			// count = service.jxj_queryrsunm(myForm,model,usertype);
		}
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", rs);
		if(rs!=null&&rs.size()>0){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("topTr", topTr);
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		
		return mapping.findForward("zznlQuery");
	}

	/**
	 * 组织能力活动增加页面 .
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_zznlAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		ZznlModel model = new ZznlModel();
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		String doType = request.getParameter("doType");
		String addResult;
		BeanUtils.copyProperties(model, myForm);
		if (StringUtils.isNotNull(xh)) {
			map = service.getXsxxInfo(xh);
		}
		if ("save".equals(doType)) {
			PjpySzyqxyZhszcpService.formToGBK(model);
			model.setXq(Base.currXq);
			addResult = service.service_addZznl(model, request);
			if (StringUtils.isNull(addResult)) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
		}
		map.put("xn", Base.currXn);
		map.put("xq", service.getXqmc(Base.currXq));
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", map);

		return mapping.findForward("zznlAdd");
	}

	/**
	 * 组织能力班主任增加页面 .
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public ActionForward szyc_zznlBzrAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userName = request.getSession().getAttribute("userName")
				.toString();
		String doType = request.getParameter("doType");
		String tableName = "szyc_zznlfzb";

		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		PjpySzyqxyZhszcpService service = new PjpySzyqxyZhszcpService();
		ZznlModel model = new ZznlModel();

		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String xh = myForm.getXh();
		String xm = myForm.getXm();
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);

		String bjdm = myForm.getBjdm();
		
		String[] colList = new String[] { "xh", "xm", "xb", "bjmc" };
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"view_xsjbxx", colList, null);
		List<HashMap<String, String>> list = service.getBzrXsList(tableName,
				userName, Base.currXn, Base.currXq, nj, xh, xm,
				xydm, zydm, bjdm);

		BeanUtils.copyProperties(model, myForm);

		if ("save".equalsIgnoreCase(doType)) {
			String xn = myForm.getXn();
			String xq = myForm.getXq();
			String[] hdxh = myForm.getHdxh();
			String[] arrzd = new String[] { "xh", "hdzt", "jjf", "shfz", "hddj","hdrq" };
			String[] onezd = new String[] { "xn", "xq" };
			String[] notnull = new String[] { "shfz" };
			String pk = "id";
			String[] pkValue = null;
			if (hdxh != null && hdxh.length > 0) {
				pkValue = new String[hdxh.length];
				for (int i = 0; i < hdxh.length; i++) {
					pkValue[i] = hdxh[i] + xn + xq;
				}
			}

			SaveForm bzrForm = new SaveForm();
			bzrForm.setTableName(tableName);
			bzrForm.setArrzd(arrzd);
			bzrForm.setOnezd(onezd);
			bzrForm.setNotnull(notnull);
			bzrForm.setPk(pk);
			bzrForm.setPkValue(pkValue);
			model.setXh(hdxh);
			boolean result = service.saveBzrXs(bzrForm, model);
			request.setAttribute("result", result);
		}
		FormModleCommon.setNdXnXqList(request);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("userName", userName);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsList", list);
		request.setAttribute("yyList", service.getYyList());
		request.setAttribute("bjList", service.getBjList(userName));

		return mapping.findForward("zznlBzrAdd");
	}
	
	/**
	 * 组织能力活动修改页面 .
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_zznlUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		ZznlModel model = new ZznlModel();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String xh = request.getParameter("xh");
		String pkValue = request.getParameter("pkValue");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String isview = request.getParameter("isview");
		String nj = myForm.getNj();
		String addResult;
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		String doType = request.getParameter("doType");
		PjpySzyqxyZhszcpService.formToGBK(myForm);
		BeanUtils.copyProperties(model, myForm);
		if ("update".equals(doType)) {
			if (StringUtils.isNotNull(model.getXq())) {
				model.setXq(service.getXqdm(model.getXq()));
			}
			xh = model.getXh()[0];
			addResult = service.service_addZznl(model, request);
			if (StringUtils.isNull(addResult)) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
		}
		if (StringUtils.isNotNull(xh)) {
			map = service.getXsxxInfo(xh);
			pkValue = StringUtils.isNull(pkValue) ? new StringBuffer().append(
					xh).append(model.getXn()).append(model.getXq()).toString()
					: pkValue;
			rs = service.getXszznl(pkValue);
		}
		HashMap<String, String> map1 = service.dao_getStuInfo(xh, pkValue,
				"szyc_zznlfzb");
		map.put("xn", map1.get("xn"));
		map.put("xq", map1.get("xq"));
		request.setAttribute("isview", isview);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", map);
		request.setAttribute("rs1", rs);
		if ("isview".equals(isview)) {
			return mapping.findForward("zznlView");
		} else {
			return mapping.findForward("zznlupdate");
		}
	}

	/**
	 * 组织能力活动删除页面 .
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_zznlDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		String[] key = myForm.getCheckVal();
		@SuppressWarnings("unused")
		String whichpk = service.getZznlDel(key);
		return szyc_zznlChk(mapping, form, request, response);
	}

	/**
	 * 学团活动.查询管理
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_xthdManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		XthdModel model = new XthdModel();

		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		String tableName = "szyq_dshdjzb";
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;

		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		if (myForm.getXn() == null) {
			myForm.setXn(Base.currXn);
		}
		if (myForm.getXq() == null) {
			myForm.setXq(Base.currXq);
		}
		if (flg) {
			userType = "bzr";
		}
		if (!"bzr".equalsIgnoreCase(userType)) {// 班主任
			return new ActionForward("/pjpyszyqwh.do?method=szyc_XthdChk",
					false);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		ArrayList<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String go = request.getParameter("go");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String forwardName = "dshdQuery";
		String xxk = request.getParameter("xxk");

		if ("ivlt".equals(xxk)) {
			forwardName = "ivltQuery";
			tableName = "szyq_ivtltb";
		} else if ("wthd".equals(xxk)) {
			forwardName = "xthdQuery";
			tableName = "szyq_xthddjb";
		} else if ("yybd".equals(xxk)) {
			forwardName = "yybdQuery";
			tableName = "szyq_yybdjzb";
		}

		if ("go".equals(go)) {
			BeanUtils.copyProperties(model, myForm);
			PjpySzyqxyZhszcpService.formToGBK(model);
			model.setUserName(userName);
			model.setUserType(userType);
			rs = service.service_QueryXthd(model, xxk);
			topTr = service.getjxjTitle(tableName);
			// count = service.jxj_queryrsunm(myForm,model,usertype);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs!=null&&rs.size()>0){
			request.setAttribute("rsNum", rs.size());
		}
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		// myForm.setXn(Base.currXn);
		// myForm.setXq(Base.currXq);
		appendProperties(request, xydm, zydm, nj);

		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		return mapping.findForward(forwardName);
	}

	/**
	 * 学团活动.增加 .
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_dshdAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		XthdModel model = new XthdModel();
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		String doType = request.getParameter("doType");
		String forwardName = "dshdAdd";
		String xxk = request.getParameter("xxk");
		String addResult;
		BeanUtils.copyProperties(model, myForm);
		if (StringUtils.isNotNull(xh)) {
			map = service.getXsxxInfo(xh);
		}
		if ("save".equals(doType)) {
			PjpySzyqxyZhszcpService.formToGBK(model);
			model.setXq(Base.currXq);
			addResult = service.service_addXthd(model, request, xxk);
			if (StringUtils.isNull(addResult)) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
		}
		map.put("xn", Base.currXn);
		map.put("xq", service.getXqmc(Base.currXq));
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", map);
		if ("ivlt".equals(xxk)) {
			forwardName = "ivltAdd";
		} else if ("wthd".equals(xxk)) {
			forwardName = "xthdAdd";
		} else if ("yybd".equals(xxk)) {
			forwardName = "yybdAdd";
		}
		return mapping.findForward(forwardName);
	}

	/**
	 * 团活动班主任增加页面 .
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public ActionForward szyc_xthdBzrAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String userName = request.getSession().getAttribute("userName")
				.toString();
		String doType = request.getParameter("doType");
		String tableName = request.getParameter("tbName");

		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		PjpySzyqxyZhszcpService service = new PjpySzyqxyZhszcpService();
		XthdModel model = new XthdModel();

		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String xh = myForm.getXh();
		String xm = myForm.getXm();
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);

		String bjdm = myForm.getBjdm();
		
		String[] colList = new String[] { "xh", "xm", "xb", "bjmc" };
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"view_xsjbxx", colList, null);
		List<HashMap<String, String>> list = service.getBzrXsList(tableName,
				userName, Base.currXn, Base.currXq, nj, xh, xm,
				xydm, zydm, bjdm);

		BeanUtils.copyProperties(model, myForm);

		if ("save".equalsIgnoreCase(doType)) {
			String xn = myForm.getXn();
			String xq = myForm.getXq();
			String[] hdxh = myForm.getHdxh();
			String[] arrzd = null;
			String[] onezd = null;
			String[] notnull = null;
			String pk = "id";
			String[] pkValue = null;
			if (hdxh != null && hdxh.length > 0) {
				pkValue = new String[hdxh.length];
				for (int i = 0; i < hdxh.length; i++) {
					pkValue[i] = hdxh[i] + xn + xq;
				}
			}

			if ("szyq_dshdjzb".equalsIgnoreCase(tableName)) {
				arrzd = new String[] { "xh", "dsmc", "dsxd", "jjf", "pf",
						"sfhj", "dsrq" };
				onezd = new String[] { "xn", "xq" };
				notnull = new String[] { "pf" };
			} else if ("szyq_yybdjzb".equalsIgnoreCase(tableName)) {
				arrzd = new String[] { "xh", "yybdnr", "xthdrq", "jjf", "pf" };
				onezd = new String[] { "xn", "xq" };
				notnull = new String[] { "pf" };
			} else if ("szyq_ivtltb".equalsIgnoreCase(tableName)) {
				arrzd = new String[] { "xh", "jztm", "xthdrq", "jcdj", "ccdj",
						"jjf", "pf" };
				onezd = new String[] { "xn", "xq" };
				notnull = new String[] { "pf" };
			} else if ("szyq_xthddjb".equalsIgnoreCase(tableName)) {
				arrzd = new String[] { "xh", "hdnr", "xthdrq", "jldj", "jjf",
						"pf" };
				onezd = new String[] { "xn", "xq" };
				notnull = new String[] { "pf" };
			}

			SaveForm bzrForm = new SaveForm();
			bzrForm.setTableName(tableName);
			bzrForm.setArrzd(arrzd);
			bzrForm.setOnezd(onezd);
			bzrForm.setNotnull(notnull);
			bzrForm.setPk(pk);
			bzrForm.setPkValue(pkValue);
			model.setXh(hdxh);
			boolean result = service.saveBzrXs(bzrForm, model);
			request.setAttribute("result", result);
		}
		FormModleCommon.setNdXnXqList(request);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("userName", userName);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsList", list);
		request.setAttribute("bjList", service.getBjList(userName));

		return mapping.findForward(tableName);
	}

	/**
	 * 学团活动.修改页面 .
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_dshdUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		XthdModel model = new XthdModel();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String xh = request.getParameter("xh");
		String pkValue = request.getParameter("pkValue");
		String isview = request.getParameter("isview");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String forwardName = "";
		String xxk = request.getParameter("xxk");
		String addResult;
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		String doType = request.getParameter("doType");
		PjpySzyqxyZhszcpService.formToGBK(myForm);
		BeanUtils.copyProperties(model, myForm);
		String tableName = "szyq_dshdjzb";
		if ("ivlt".equals(xxk)) {
			tableName = "szyq_ivtltb";
		} else if ("wthd".equals(xxk)) {
			tableName = "szyq_xthddjb";
		} else if ("yybd".equals(xxk)) {
			tableName = "szyq_yybdjzb";
		} else {
			tableName = "szyq_dshdjzb";
		}
		if ("update".equals(doType)) {
			if (StringUtils.isNotNull(model.getXq())) {
				model.setXq(service.getXqdm(model.getXq()));
			}
			xh = model.getXh()[0];
			addResult = service.service_addXthd(model, request, xxk);
			if (StringUtils.isNull(addResult)) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
		}
		if (StringUtils.isNotNull(xh)) {
			map = service.getXsxxInfo(xh);
			pkValue = StringUtils.isNull(pkValue) ? new StringBuffer().append(
					xh).append(model.getXn()).append(model.getXq()).toString()
					: pkValue;
			rs = service.getXsXthd(pkValue, xxk);
		}
		HashMap<String, String> map1 = service.dao_getStuInfo(xh, pkValue,
				tableName);
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		appendProperties(request, xydm, zydm, nj);
		map.put("xn", map1.get("xn"));
		map.put("xq", map1.get("xq"));
		request.setAttribute("rs", map);
		request.setAttribute("rs1", rs);
		if ("isview".equals(isview)) {
			if ("ivlt".equals(xxk)) {
				forwardName = "ivltView";
			} else if ("wthd".equals(xxk)) {
				forwardName = "xthdView";
			} else if ("yybd".equals(xxk)) {
				forwardName = "yybdView";
			} else {
				forwardName = "dshdView";
			}
		} else {
			if ("ivlt".equals(xxk)) {
				forwardName = "ivltupdate";
			} else if ("wthd".equals(xxk)) {
				forwardName = "xthdupdate";
			} else if ("yybd".equals(xxk)) {
				forwardName = "yybdupdate";
			} else {
				forwardName = "dshdupdate";
			}
		}
		return mapping.findForward(forwardName);
	}

	/**
	 * 学团活动.删除页面 .
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_dshdDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		ZznlModel model = new ZznlModel();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String xh = request.getParameter("xh");
		String pkValue = request.getParameter("pkValue");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		// String addResult;
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		String doType = request.getParameter("doType");
		String xxk = request.getParameter("xxk");
		PjpySzyqxyZhszcpService.formToGBK(myForm);
		BeanUtils.copyProperties(model, myForm);
		if ("del".equals(doType)) {
			String[] key = myForm.getCheckVal();
			String whichpk = service.getXthdDel(key, xxk);
			if (StringUtils.isNull(whichpk)) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
				request.setAttribute("whichpk", whichpk);
			}
		}
		if (StringUtils.isNotNull(xh)) {
			map = service.getXsxxInfo(xh);
			rs = service.getXszznl(pkValue);
		}
		map.put("xn", Base.currXn);
		map.put("xq", service.getXqmc(Base.currXq));
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", map);
		request.setAttribute("rs1", rs);
		return szyc_XthdChkcx(mapping, form, request, response);
	}

	/**
	 * 5s管理
	 * 
	 * @throws SQLException
	 */
	public ActionForward szyc_5sManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		// String userName = request.getSession().getAttribute("userName")
		// .toString();
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;

		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		if (flg) {
			userType = "bzr";
		}
		if ("bzr".equalsIgnoreCase(userType)) {// 班主任
			return new ActionForward("/pjpyszyqwh.do?method=szyc_5sQuery",
					false);
		} else {//
			return new ActionForward("/pjpyszyqwh.do?method=szyc_5sView", false);
		}
	}

	/**
	 * 5s查询页面 .
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_5sQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;

		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		FiveSModel model = new FiveSModel();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		ArrayList<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String go = request.getParameter("go");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();

		if (myForm.getXn() == null) {
			myForm.setXn(Base.currXn);
		}
		if (myForm.getXq() == null) {
			myForm.setXq(Base.currXq);
		}

		if (flg) {
			userType = "bzr";
		}
		if ("go".equals(go)) {
			BeanUtils.copyProperties(model, myForm);
			model.setFivexh(myForm.getXh());
			rs = service.dao_Query5S(model, userType, userName);
			topTr = service.getjxjTitle("szyc_5sb");
		}

		appendProperties(request, xydm, zydm, nj);

		request.setAttribute("userType", userType);
		request.setAttribute("rs", rs);
		if(rs!=null&&rs.size()>0){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("topTr", topTr);
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);

		return mapping.findForward("5sQuery");
	}

	/**
	 * 5s审核查询页面 .
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_5sView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
		.toString();
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		FiveSModel model = new FiveSModel();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		ArrayList<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String go = request.getParameter("go");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String doType = request.getParameter("doType");

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			xydm = userDep;
		}
		
		if (myForm.getXn() == null) {
			myForm.setXn(Base.currXn);
		}
		if (myForm.getXq() == null) {
			myForm.setXq(Base.currXq);
		}

		boolean result = false;
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] key = myForm.getCheckVal();
			String shzt = (request.getParameter("sh")).equalsIgnoreCase("tg") ? "通过" : "不通过";
			result = service.saveSh("szyc_5sb", key, shzt);
			request.setAttribute("result", result);
		}

		if ("go".equals(go) || result) {
			BeanUtils.copyProperties(model, myForm);
			model.setFivexh(myForm.getXh());
			rs = service.dao_View5S(model);
			topTr = service.getjxjTitle("szyc_5sb");
		}

		appendProperties(request, xydm, zydm, nj);

		request.setAttribute("userType", userType);
		request.setAttribute("rs", rs);
		if(rs!=null&&rs.size()>0){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("topTr", topTr);
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);

		return mapping.findForward("5sView");
	}

	/**
	 * 5s审核页面 .
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_5sSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		FiveSModel model = new FiveSModel();
		HashMap<String, String> map = new HashMap<String, String>();

		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();

		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());

		String pk = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		PjpySzyqxyZhszcpService.formToGBK(myForm);
		BeanUtils.copyProperties(model, myForm);
		if (StringUtils.isNotNull(pk)) {
			map = service.dao_5sXsInfo(pk);
		}
		if ("save".equals(doType)) {
			String shzt = request.getParameter("shzt");
			String[] key = model.getCheckVal();
			String[] arrFz = service.getFzsxForMkdm("wsmk");
			if (arrFz != null && arrFz.length == 2) {

				String jcf = Base.isNull(arrFz[0]) ? "0" : arrFz[0];
				String zgf = Base.isNull(arrFz[1]) ? "0" : arrFz[1];
				String kjf = Float.parseFloat(zgf) - Float.parseFloat(jcf) < 0 ? "0"
						: String.valueOf(Float.parseFloat(zgf)
								- Float.parseFloat(jcf));

				boolean issh = true;
				if ("yes".equalsIgnoreCase(shzt)) {
					issh = service.dao_get5sfzList(key, pk, kjf);
				}

				if (!issh) {
					String msg = "本次5s基础分为" + jcf + "分,最高分为" + zgf
							+ "分,现已超过，请确认后重新审核！！";
					request.setAttribute("msg", msg);
				} else {
					boolean result = service.dao_sh5s(key, shzt);
					if (result) {
						request.setAttribute("pk", pk);
					}
					request.setAttribute("result", result);
					return new ActionForward(
							"/pjpyszyqwh.do?method=szyc_5sSh&pkValue=" + pk
									+ "&doType=", false);
				}
			}
		}
		ArrayList<String[]> rsList = service.dao_5sList(pk);
		request.setAttribute("rsNum", rsList != null ? rsList.size() : 0);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("pk", pk);
		request.setAttribute("rsList", rsList);
		request.setAttribute("rs", map);
		request.setAttribute("doType", "add");
		return mapping.findForward("5sSh");
	}

	/**
	 * 5s增加页面 .
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_5sAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		FiveSModel model = new FiveSModel();
		HashMap<String, String> map = new HashMap<String, String>();

		String xh = request.getParameter("xh");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();

		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());

		String doType = request.getParameter("doType");
		PjpySzyqxyZhszcpService.formToGBK(myForm);
		BeanUtils.copyProperties(model, myForm);
		if (StringUtils.isNotNull(xh)) {
			map = service.getXsxxInfo(xh);
		}
		if ("save".equals(doType)) {
			String xn = request.getParameter("xn");
			if (!Base.isNull(xn)) {
				model.setXn(xn);
			} else {
				model.setXn(Base.currXn);
			}
			String xq = request.getParameter("xq");
			if (!Base.isNull(xq)) {
				model.setXq(xq);
			} else {
				model.setXq(Base.currXq);
			}
			boolean result = service.dao_add5s(model, request);
			if (result) {
				String pk = model.getXh()[0] + model.getXn() + model.getXq();
				request.setAttribute("pk", pk);
			}
			request.setAttribute("result", result);
		}
		map.put("xn", Base.currXn);
		map.put("xq", service.getXqmc(Base.currXq));

		appendProperties(request, xydm, zydm, nj);

		request.setAttribute("xnV", Base.currXn);
		request.setAttribute("xqV", Base.currXq);
		request.setAttribute("doType", "add");
		request.setAttribute("rs", map);
		request.setAttribute("yyList", service.getYyList());
		return mapping.findForward("5sAdd");
	}

	/**
	 * 5s班主任增加页面 .
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public ActionForward szyc_5sBzrAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userName = request.getSession().getAttribute("userName")
				.toString();
		String doType = request.getParameter("doType");
		String tableName = "szyc_5sb";

		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		PjpySzyqxyZhszcpService service = new PjpySzyqxyZhszcpService();
		FiveSModel model = new FiveSModel();

		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String xh = myForm.getXh();
		String xm = myForm.getXm();
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);

		String bjdm = myForm.getBjdm();
		
		String[] colList = new String[] { "xh", "xm", "xb", "bjmc" };
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"view_xsjbxx", colList, null);
		List<HashMap<String, String>> list = service.getBzrXsList(tableName,
				userName, Base.currXn, Base.currXq, nj, xh, xm,
				xydm, zydm, bjdm);

		BeanUtils.copyProperties(model, myForm);

		if ("save".equalsIgnoreCase(doType)) {
			String xn = myForm.getXn();
			String xq = myForm.getXq();
			String[] fivexh = myForm.getFivexh();
			String[] arrzd = new String[] { "xh", "fzxm", "jjf", "fz", "yy" };
			String[] onezd = new String[] { "xn", "xq" };
			String[] notnull = new String[] { "fz" };
			String pk = "id";
			String[] pkValue = null;
			if (fivexh != null && fivexh.length > 0) {
				pkValue = new String[fivexh.length];
				for (int i = 0; i < fivexh.length; i++) {
					pkValue[i] = fivexh[i] + xn + xq;
				}
			}

			SaveForm bzrForm = new SaveForm();
			bzrForm.setTableName(tableName);
			bzrForm.setArrzd(arrzd);
			bzrForm.setOnezd(onezd);
			bzrForm.setNotnull(notnull);
			bzrForm.setPk(pk);
			bzrForm.setPkValue(pkValue);
			model.setXh(fivexh);
			boolean result = service.saveBzrXs(bzrForm, model);
			request.setAttribute("result", result);
		}
		FormModleCommon.setNdXnXqList(request);
		appendProperties(request, xydm, zydm, nj);
		
		request.setAttribute("userName", userName);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsList", list);
		request.setAttribute("yyList", service.getYyList());
		request.setAttribute("bjList", service.getBjList(userName));
		
		return mapping.findForward("5sBzrAdd");
	}

	/**
	 * 5s分值修改页面
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_5sUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		FiveSModel model = new FiveSModel();
		String xh = request.getParameter("xhV");
		xh = Base.isNull(xh) ? "" : xh.trim();
		String pk = request.getParameter("pkValue");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		String doType = request.getParameter("doType");
		PjpySzyqxyZhszcpService.formToGBK(myForm);
		BeanUtils.copyProperties(model, myForm);
		appendProperties(request, xydm, zydm, nj);
		HashMap<String, String> map = service
				.dao_getStuInfo(xh, pk, "szyc_5sb");

		request.setAttribute("rs", map);
		request.setAttribute("doType", doType);
		request.setAttribute("pk", pk);
		request.setAttribute("xnV", map.get("xn"));
		request.setAttribute("xqV", map.get("xqdm"));
		request.setAttribute("yyList", service.getYyList());

		return mapping.findForward("5supdate");
	}

	/**
	 * 5s删除页面 .
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward szyc_5sDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		FiveSModel model = new FiveSModel();

		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());

		String doType = request.getParameter("doType");

		PjpySzyqxyZhszcpService.formToGBK(myForm);
		BeanUtils.copyProperties(model, myForm);
		if ("del".equals(doType)) {
			String pks = request.getParameter("pkstring");
			boolean flg = service.dao_del5s(pks);
			if (flg) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
			}
		}
		return szyc_5sView(mapping, form, request, response);
	}

	/**
	 * 5s报表
	 * 
	 * @return ActionForward
	 */
	public ActionForward szyc_5sbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
		.toString();
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;
		if (flg) {
			userType = "bzr";
		}

		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String xn = Base.currXn;
		String xq = Base.currXq;

		myForm.setXn(xn);
		myForm.setXq(xq);
		request.setAttribute("bjList", new ArrayList());
		if ("bzr".equalsIgnoreCase(userType)) {
			request.setAttribute("bjPList", service.getBzrBj(userName));
		} else if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			xydm = userDep;
			request.setAttribute("bjPList", new ArrayList());
		}
		else {
			request.setAttribute("bjPList", new ArrayList());
		}
		
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("yfList", service.dao_getYf(xq));
		request.setAttribute("zcList", service.dao_getZc());
		request.setAttribute("userType", userType);

		return mapping.findForward("5sbb");
	}

	/**
	 * 5s报表打印
	 * 
	 * @return ActionForward
	 */
	public ActionForward szyc_5sPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		String bblx = request.getParameter("bblx");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.print5sList(myForm, bblx, response.getOutputStream());
		return null;
	}

	/**
	 * 社会实践管理
	 * 
	 * @throws SQLException
	 */
	public ActionForward szyc_shsjManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		// String userName =
		// request.getSession().getAttribute("userName").toString();
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;

		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		if (flg) {
			userType = "bzr";
		}
		if ("bzr".equalsIgnoreCase(userType)) {// 班主任
			return new ActionForward("/pjpyszyqwh.do?method=szyc_shsjQuery",
					false);
		} else {
			return new ActionForward("/pjpyszyqwh.do?method=szyc_shsjChk",
					false);
		}
	}

	/**
	 * 社会实践管理维护查询
	 * 
	 * @throws Exception
	 */
	public ActionForward szyc_shsjQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;

		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		ShsjModel mymodel = new ShsjModel();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		ArrayList<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String go = request.getParameter("go");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		if (myForm.getXn() == null) {
			myForm.setXn(Base.currXn);
		}
		if (myForm.getXq() == null) {
			myForm.setXq(Base.currXq);
		}
		if (flg) {
			userType = "bzr";
		}
		if ("go".equals(go)) {
			BeanUtils.copyProperties(mymodel, myForm);
			rs = service.serv_shsjSearch(mymodel, userType, userName);
			topTr = service.getjxjTitle("szyc_shsjfzb");
		}
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("userType", userType);
		request.setAttribute("rs", rs);
		if(rs!=null&&rs.size()>0){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("topTr", topTr);
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		
		return mapping.findForward("shsjQuery");
	}

	/**
	 * 社会实践信息添加
	 * 
	 * @throws Exception
	 */
	public ActionForward szyc_shsjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		ShsjModel model = new ShsjModel();
		HashMap<String, String> map = new HashMap<String, String>();

		String xh = request.getParameter("xh");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();

		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());

		String doType = request.getParameter("doType");
		BeanUtils.copyProperties(model, myForm);
		if (StringUtils.isNotNull(xh)) {
			map = service.getXsxxInfo(xh);
		}
		if ("save".equals(doType)) {
			String xn = request.getParameter("xn");
			if (!Base.isNull(xn)) {
				model.setXn(xn);
			} else {
				model.setXn(Base.currXn);
			}
			String xq = request.getParameter("xq");
			if (!Base.isNull(xq)) {
				model.setXq(xq);
			} else {
				model.setXq(Base.currXq);
			}
			boolean result = service.serv_addShsj(model, request);
			if (result) {
				String pk = model.getXh()[0] + model.getXn() + model.getXq();
				request.setAttribute("pk", pk);
			}
			request.setAttribute("result", result);
		}
		map.put("xn", Base.currXn);
		map.put("xq", service.getXqmc(Base.currXq));
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("doType", "add");
		request.setAttribute("rs", map);
		return mapping.findForward("shsjAdd");
	}

	/**
	 * 社会实践班主任增加页面 .
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public ActionForward szyc_shsjBzrAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userName = request.getSession().getAttribute("userName")
				.toString();
		String doType = request.getParameter("doType");
		String tableName = "szyc_shsjfzb";

		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		PjpySzyqxyZhszcpService service = new PjpySzyqxyZhszcpService();
		ShsjModel model = new ShsjModel();

		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String xh = myForm.getXh();
		String xm = myForm.getXm();
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);	

		String bjdm = myForm.getBjdm();
		
		String[] colList = new String[] { "xh", "xm", "xb", "bjmc" };
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"view_xsjbxx", colList, null);
		List<HashMap<String, String>> list = service.getBzrXsList(tableName,
				userName, Base.currXn, Base.currXq, nj, xh, xm,
				xydm, zydm, bjdm);

		BeanUtils.copyProperties(model, myForm);

		if ("save".equalsIgnoreCase(doType)) {
			String xn = myForm.getXn();
			String xq = myForm.getXq();
			String[] hdxh = myForm.getHdxh();
			String[] arrzd = new String[] { "xh", "hdnr", "jjf", "shfz", "hddd","hdrq","hdsj" };
			String[] onezd = new String[] { "xn", "xq" };
			String[] notnull = new String[] { "shfz" };
			String pk = "id";
			String[] pkValue = null;
			if (hdxh != null && hdxh.length > 0) {
				pkValue = new String[hdxh.length];
				for (int i = 0; i < hdxh.length; i++) {
					pkValue[i] = hdxh[i] + xn + xq;
				}
			}

			SaveForm bzrForm = new SaveForm();
			bzrForm.setTableName(tableName);
			bzrForm.setArrzd(arrzd);
			bzrForm.setOnezd(onezd);
			bzrForm.setNotnull(notnull);
			bzrForm.setPk(pk);
			bzrForm.setPkValue(pkValue);
			model.setXh(hdxh);
			boolean result = service.saveBzrXs(bzrForm, model);
			request.setAttribute("result", result);
		}
		FormModleCommon.setNdXnXqList(request);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("userName", userName);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsList", list);
		request.setAttribute("yyList", service.getYyList());
		request.setAttribute("bjList", service.getBjList(userName));

		return mapping.findForward("shsjBzrAdd");
	}
	
	/**
	 * 社会实践分值修改页面
	 */
	public ActionForward szyc_shsjUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		ShsjModel model = new ShsjModel();
		String xh = request.getParameter("xh");
		xh = Base.isNull(xh) ? "" : xh.trim();
		String pk = request.getParameter("pkValue");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		String doType = request.getParameter("doType");
		PjpySzyqxyZhszcpService.formToGBK(myForm);
		BeanUtils.copyProperties(model, myForm);
		appendProperties(request, xydm, zydm, nj);
		HashMap<String, String> map = service.dao_getStuInfo(xh, pk,
				"szyc_shsjfzb");
		request.setAttribute("rs", map);
		request.setAttribute("doType", doType);
		request.setAttribute("pk", pk);
		request.setAttribute("xnV", map.get("xn"));
		request.setAttribute("xqV", map.get("xqdm"));
		return mapping.findForward("shsjAdd");
	}

	/**
	 * 社会实践活动审核查询页面
	 * 
	 * @throws Exception
	 */
	public ActionForward szyc_shsjChk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String doType = request.getParameter("doType");
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			xydm = userDep;
		}

		if (xn == null) {
			myForm.setXn(Base.currXn);
		}
		if (xq == null) {
			myForm.setXq(Base.currXq);
		}
		
		boolean result = false;
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] key = myForm.getCheckVal();
			String shzt = request.getParameter("sh");
			result = service.saveSh("szyc_shsjfzb", key, shzt);
			request.setAttribute("result", result);
		}
		
		if ("go".equalsIgnoreCase(request.getParameter("go"))||result) {
			ShsjModel model = new ShsjModel();
			BeanUtils.copyProperties(model, myForm);
			List<HashMap<String, String>> topTr = service.serv_zznlChkTitle("szyc_shsjfzb");
			ArrayList<String[]> rs = service.serv_shsjChkSearch(model);
			request.setAttribute("rsNum", rs != null ? rs.size() : 0);
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
		}
		appendProperties(request, xydm, zydm, nj);
		
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		
		return mapping.findForward("shsjChk");
	}

	/**
	 * 社会实践活动审核
	 * 
	 * @throws Exception
	 */
	public ActionForward szyc_shsjViewAndChk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String xh = DealString.toGBK(request.getParameter("xh")).trim();
		String doType = request.getParameter("doType");
		if ("save".equalsIgnoreCase(doType)) {
			String shType = request.getParameter("check");
			String pkVStr = request.getParameter("pkVStr");
			boolean done = service.serv_shsjChk(pkVStr, shType);
			request.setAttribute("done", done);
		}
		ArrayList<String[]> rs = service.serv_shsjViewAndChkSearch(xh, xn, xq);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		request.setAttribute("rsxs", service.serv_getXsInfo(xh));
		request.setAttribute("rs", rs);
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		request.setAttribute("xh", xh);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("pkValue", xh + xn + xq);
		return mapping.findForward("shsjViewAndChk");
	}

	/**
	 * 社会实践活动信息删除
	 * 
	 * @throws Exception
	 */
	public ActionForward serv_shsjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		String[] key = myForm.getCheckVal();
		service.serv_shsjDel(key);
		return szyc_shsjChk(mapping, form, request, response);
	}

	/**
	 * 分数设置
	 */
	public ActionForward szyc_pointSetting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		rs = service.getPointSetting();
		request.setAttribute("rs", rs);
		
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		
		return mapping.findForward("szyc_pointSetting");
	}

	/**
	 * 分数设置保存
	 */
	public ActionForward pointSetSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		FsszModel model = new FsszModel();
		BeanUtils.copyProperties(model, myForm);

		boolean updated = service.updatePointSetting(model);
		if (updated) {
			request.setAttribute("updated", "yes");
		} else {
			request.setAttribute("updated", "no");
		}
		return szyc_pointSetting(mapping, form, request, response);
	}

	/**
	 * 学团活动.审核查询页面
	 * 
	 * @throws Exception
	 */
	public ActionForward szyc_XthdChk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String doType = request.getParameter("doType");
		String tableName = "szyq_dshdjzb";
		String userType = request.getSession().getAttribute("userType")
				.toString();
		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		String xxk = request.getParameter("xxk");
		String forwardName;
		
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			xydm = userDep;
		}

		if (xn == null) {
			myForm.setXn(Base.currXn);
		}
		if (xq == null) {
			myForm.setXq(Base.currXq);
		}

		if ("ivlt".equals(xxk)) {
			forwardName = "ivltChk";
			tableName = "szyq_ivtltb";
		} else if ("wthd".equals(xxk)) {
			forwardName = "wthdChk";
			tableName = "szyq_xthddjb";
		} else if ("yybd".equals(xxk)) {
			forwardName = "yybdChk";
			tableName = "szyq_yybdjzb";
		} else {
			forwardName = "dshdChk";
			tableName = "szyq_dshdjzb";
		}

		boolean result = false;
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] key = myForm.getCheckVal();
			String shzt = request.getParameter("sh");
			result = service.saveSh(tableName, key, shzt);
			request.setAttribute("result", result);
		}

		if ("go".equalsIgnoreCase(request.getParameter("go")) || result) {
			ZhszcpModel model = new ZhszcpModel();
			BeanUtils.copyProperties(model, myForm);
			List<HashMap<String, String>> topTr = service
					.serv_zznlChkTitle(tableName);
			ArrayList<String[]> rs = service.serv_xthdChkSearch(model, xxk);
			request.setAttribute("rsNum", rs != null ? rs.size() : 0);
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
		}
		appendProperties(request, xydm, zydm, nj);
		
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);

		return mapping.findForward(forwardName);
	}

	/**
	 * 学团活动.审核查询页面
	 * 
	 * @throws Exception
	 */
	public ActionForward szyc_XthdChkcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String tableName = "szyq_dshdjzb";
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		String xxk = request.getParameter("xxk");
		String forwardName;

		if (xn == null) {
			myForm.setXn(Base.currXn);
		}
		if (xq == null) {
			myForm.setXq(Base.currXq);
		}

		if ("ivlt".equals(xxk)) {
			forwardName = "ivltChk";
			tableName = "szyq_ivtltb";
		} else if ("wthd".equals(xxk)) {
			forwardName = "wthdChk";
			tableName = "szyq_xthddjb";
		} else if ("yybd".equals(xxk)) {
			forwardName = "yybdChk";
			tableName = "szyq_yybdjzb";
		} else {
			forwardName = "dshdChk";
			tableName = "szyq_dshdjzb";
		}

		ZhszcpModel model = new ZhszcpModel();
		BeanUtils.copyProperties(model, myForm);
		List<HashMap<String, String>> topTr = service
				.serv_zznlChkTitle(tableName);
		ArrayList<String[]> rs = service.serv_xthdChkSearch(model, xxk);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);

		appendProperties(request, xydm, zydm, nj);

		return mapping.findForward(forwardName);
	}

	/**
	 * 学团活动.审核
	 * 
	 * @throws Exception
	 */
	public ActionForward szyc_xthdViewAndChk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		String xxk = request.getParameter("xxk");
		String forwardName;
		if ("save".equalsIgnoreCase(doType)) {
			String shType = request.getParameter("check");
			String pkVStr = request.getParameter("pkVStr");
			boolean done = service.serv_wthdChk(pkVStr, shType, xxk);
			request.setAttribute("done", done);
		}
		ArrayList<String[]> rs = service.serv_xthdlViewAndChkSearch(xh, xn, xq,
				xxk);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		request.setAttribute("rsxs", service.serv_getXsInfo(xh));
		request.setAttribute("rs", rs);
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		request.setAttribute("xh", xh);
		request.setAttribute("pkValue", xh + xn + xq);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		if ("ivlt".equals(xxk)) {
			forwardName = "ivltViewAndChk";
		} else if ("wthd".equals(xxk)) {
			forwardName = "wthdViewAndChk";
		} else if ("yybd".equals(xxk)) {
			forwardName = "yybdViewAndChk";
		} else {
			forwardName = "dshdViewAndChk";
		}
		return mapping.findForward(forwardName);
	}

	/**
	 * 社会实践汇总表
	 */
	public ActionForward shsjHzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		String userType = "";
		String userName = request.getSession().getAttribute("userName").toString();;
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;
		if (flg) {
			userType = "bzr";
		}
		
		ShsjModel model = new ShsjModel();
		BeanUtils.copyProperties(model, myForm);
		String xymc = DealString.toGBK(request.getParameter("xy"));
		String zymc = DealString.toGBK(request.getParameter("zy"));
		String bjmc = DealString.toGBK(request.getParameter("bj"));
		String xqmc = DealString.toGBK(request.getParameter("xqmc"));
		String printType = request.getParameter("printType");
		model.setXymc(xymc);
		model.setZymc(zymc);
		model.setBjmc(bjmc);
		model.setXqmc(xqmc);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.printXxFfList(model, response.getOutputStream(), printType,userType,userName);
		return mapping.findForward("expdata");
	}

	
	/**
	 * 学团活动.汇总
	 * 
	 * @throws Exception
	 */
	public ActionForward szyc_xthdBbhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		String userType = "";
		String userName = request.getSession().getAttribute("userName").toString();;
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;
		if (flg) {
			userType = "bzr";
		}
		
		XthdModel model = new XthdModel();
		BeanUtils.copyProperties(model, myForm);
		String xxk = request.getParameter("xxk");
		String xymc = DealString.toGBK(request.getParameter("xy"));
		String zymc = DealString.toGBK(request.getParameter("zy"));
		String bjmc = DealString.toGBK(request.getParameter("bj"));
		String xqmc = DealString.toGBK(request.getParameter("xqmc"));
		model.setXymc(xymc);
		model.setZymc(zymc);
		model.setBjmc(bjmc);
		model.setXqmc(xqmc);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.serv_exportDataXthd(model, xxk, userType,userName,response.getOutputStream());
		return mapping.findForward("expdata");
	}
	

	public ActionForward szyc_zhszcphzManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;
		if (flg) {
			userType = "bzr";
		}
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			xydm = userDep;
		}

		if (xn == null) {
			myForm.setXn(Base.currXn);
		}
		if (xq == null) {
			myForm.setXq(Base.currXq);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			ZhszcpModel model = new ZhszcpModel();
			BeanUtils.copyProperties(model, myForm);
			List<HashMap<String, String>> topTr = service.getZhszfCjTitle();
			ArrayList<String[]> rs = service.getZhszfCjList(model, userType,
					userName);
			request.setAttribute("rsNum", rs != null ? rs.size() : 0);
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
		}
		appendProperties(request, xydm, zydm, nj);
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		
		return mapping.findForward("szyc_zhszcphzManage");
	}

	public ActionForward zhszfhzAutoAccount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		
		String xn = Base.currXn;
		String xq = Base.currXq;
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String bjdm = myForm.getBjdm();
		
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		
		boolean update = service.autoAccount(xn, xq, xydm, zydm, bjdm);
		if (update) {
			request.setAttribute("message", "计算成功！");
		} else {
			request.setAttribute("message", "计算失败！");
		}
	
		return mapping.findForward("jsfsUpdate");
	}
	
	/**
	 * 计算分数筛选页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jsfsUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PjpySzyqxyZhszcpActionForm myForm = (PjpySzyqxyZhszcpActionForm) form;
		
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		
		return mapping.findForward("jsfsUpdate");
	}

	public ActionForward zhszcpfbjhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhszcpModel model = new ZhszcpModel();

		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;
		if (flg) {
			userType = "bzr";
		}
		model.setBjdm(request.getParameter("bj"));
		model.setXn(request.getParameter("xn"));
		model.setXq(request.getParameter("xq"));
		String modelPath = "";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		modelPath = servlet.getServletContext().getRealPath("")
				+ "/print/szgyyq/zhszcp.xls";
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		service.zhszcphzPrint(wwb, model, userType, userName);
		return mapping.findForward("");
	}

//	public static void main(String[] args) throws Exception {
//		// DAO dao = DAO.getInstance();
//		// TestModel model = new TestModel();
//		// SaveForm bzrForm = new SaveForm();
//		// bzrForm.setTableName("testTable");
//		// bzrForm.setArrzd(new String[] { "zd", "zdlx", "zdz" });
//		// bzrForm.setOnezd(new String[] { "xh", "xn", "xq" });
//		//
//		// String xh = "luo";
//		// String xn = "2099-2100";
//		// String xq = "01";
//		// String[] zd = new String[] { "a", "b", "c", "d", "e", "f" };
//		// String[] zdz = new String[] { "aa", "bb", "cc", "dd", "ee", "ff" };
//		//		
//		// model.setXh(xh);
//		// model.setXn(xn);
//		// model.setXq(xq);
//		// model.setZd(zd);
//		// model.setZdz(zdz);
//		// model.setZd(zd);
//		// dao.saveBzrXs(bzrForm, bzrForm);
//	}
}
