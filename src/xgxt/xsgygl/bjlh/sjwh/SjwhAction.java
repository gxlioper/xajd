package xgxt.xsgygl.bjlh.sjwh;

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
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.SearchUtils;
import xgxt.xsgygl.bjlh.BjlhGyglForm;

public class SjwhAction extends DispatchAction {

	/**
	 * 团委学生管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward twsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_twxs";
		String realTable = "bjlh_twxsb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 判断用户有无访问权限
		if (!("xx".equalsIgnoreCase(userType) || "admin"
				.equalsIgnoreCase(userType))) {
			if (!userDep.equalsIgnoreCase(service.getBmdm("团委"))) {
				String msg = "该模块只能由团委用户访问,请确认！";
				request.setAttribute("msg", msg);
			}
		}

		BeanUtils.copyProperties(model, myForm);

		boolean reslut = false;

		// 批量保存团委生
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] twsxh = myForm.getTwsxh();
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {

				String pk = "twsxh";
				String[] arrzd = new String[] { "twsxh", "sftws" };
				String[] sftws = new String[checkVal.length];

				for (int i = 0; i < checkVal.length; i++) {
					sftws[i] = "否";
					if (twsxh != null && twsxh.length > 0) {
						for (int j = 0; j < twsxh.length; j++) {
							if (twsxh[j].equalsIgnoreCase(checkVal[i])) {
								sftws[i] = "是";
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

				model.setTwsxh(checkVal);
				model.setSftws(sftws);

				reslut = service.saveGygl(saveForm, model);
				request.setAttribute("result", reslut);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| reslut) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "sftws" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "bjlh_gygl_tws.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// 设置列表值
		service.setList(myForm, request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("twsManage");
	}

	/**
	 * 体优学生管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward tysManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_tyxs";
		String realTable = "bjlh_tyxsb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 判断用户有无访问权限
		if (!("xx".equalsIgnoreCase(userType) || "admin"
				.equalsIgnoreCase(userType))) {
			if (!userDep.equalsIgnoreCase(service.getBmdm("体育教学部"))) {
				String msg = "该模块只能由体育教学部用户访问,请确认！";
				request.setAttribute("msg", msg);
			}
		}

		BeanUtils.copyProperties(model, myForm);

		boolean reslut = false;

		// 批量保存体优生
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] tysxh = myForm.getTysxh();
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {

				String pk = "tysxh";
				String[] arrzd = new String[] { "tysxh", "sftys" };
				String[] sftys = new String[checkVal.length];

				for (int i = 0; i < checkVal.length; i++) {
					sftys[i] = "否";
					if (tysxh != null && tysxh.length > 0) {
						for (int j = 0; j < tysxh.length; j++) {
							if (tysxh[j].equalsIgnoreCase(checkVal[i])) {
								sftys[i] = "是";
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

				model.setTysxh(checkVal);
				model.setSftys(sftys);

				reslut = service.saveGygl(saveForm, model);
				request.setAttribute("result", reslut);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| reslut) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "sftys" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "bjlh_gygl_tys.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// 设置列表值
		service.setList(myForm, request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("tysManage");
	}

	/**
	 * 研究生管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward yjsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_yjsxx";
		String realTable = "bjlh_fqrzxsb";
		String doType = request.getParameter("doType");
		String lx = "研究生";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 判断用户有无访问权限
		if (!("xx".equalsIgnoreCase(userType) || "admin"
				.equalsIgnoreCase(userType))) {
			if (!userDep.equalsIgnoreCase(service.getBmdm("科研处"))) {
				String msg = "该模块只能由科研处用户访问,请确认！";
				request.setAttribute("msg", msg);
			}
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		// 批量删除研究生
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "fqrzxh||lx";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delGygl(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "fqrzxh", "xm", "xb", "xymc",
					"zymc", "bjmc", "rxrq" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "bjlh_gygl_yjs.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// 设置列表值
		myForm.setLx(lx);
		service.setList(myForm, request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("yjsManage");
	}

	/**
	 * 研究生维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward yjsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "信息维护-研究生";
		String key = request.getParameter("pk");
		String userType = session.getAttribute("userType").toString();
		// String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String isCz = request.getParameter("isCz");
		String tableName = "view_bjlh_yjsxx";
		String realTable = "bjlh_fqrzxsb";
		String lx = "研究生";
		String url = "/xgxt/bjlh_sjwh.do?method=yjsUpdate&doType=update&isCz=yes&pk=";
		doType = Base.isNull(doType) ? "add" : doType;

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// 查看或修改所选数据
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			String pk = "fqrzxh||lx";
			String pkValue = key;
			String[] colList = new String[] { "fqrzxh", "lx", "xm", "xb", "xydm",
					"zydm", "bjdm", "mz", "zzmm", "csrq", "sfzh", "lxdh", "jg",
					"lydq", "sg", "tz", "rxrq", "xz", "bz", "nj" };
			rs = service.getGyglInfo(tableName, pk, pkValue, colList);
			
			//判断录入值是否已存在于数据库
			if ("yes".equalsIgnoreCase(isCz)) {
				doType = "add";
			}
		}

		// 保存研究生情况
		if ("save".equalsIgnoreCase(doType)) {

			String[] onezd = new String[] { "fqrzxh", "lx", "xm", "xb", "bjdm",
					"mz", "zzmm", "csrq", "sfzh", "lxdh", "jg", "lydq", "sg",
					"tz", "rxrq", "xz", "bz" };
			String pk = "fqrzxh||lx";
			String pkValue = myForm.getFqrzxh() + lx;

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			model.setLx(lx);

			boolean result = service.saveGygl(saveForm, model, request);
			request.setAttribute("result", result);
		}
		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("url", url);
		request.setAttribute("rs", rs);
		// 设置列表值
		myForm.setLx(lx);
		service.setList(myForm, request);
		FormModleCommon.requestSetList(new String[] { "mz", "zzmm" }, request);

		return mapping.findForward("yjsUpdate");
	}

	/**
	 * 成教生管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cjsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_cjsxx";
		String realTable = "bjlh_fqrzxsb";
		String doType = request.getParameter("doType");
		String lx = "成教生";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 判断用户有无访问权限
		if (!("xx".equalsIgnoreCase(userType) || "admin"
				.equalsIgnoreCase(userType))) {
			if (!userDep.equalsIgnoreCase(service.getBmdm("成人教育处"))) {
				String msg = "该模块只能由成人教育处用户访问,请确认！";
				request.setAttribute("msg", msg);
			}
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		// 批量删除成教生
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "fqrzxh||lx";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delGygl(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "fqrzxh", "xm", "xb", "xymc",
					"zymc", "bjmc", "rxrq" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "bjlh_gygl_cjs.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// 设置列表值
		myForm.setLx(lx);
		service.setList(myForm, request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("cjsManage");
	}

	/**
	 * 成教生维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cjsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "信息维护-成教生";
		String key = request.getParameter("pk");
		String userType = session.getAttribute("userType").toString();
		// String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String isCz = request.getParameter("isCz");
		String tableName = "view_bjlh_cjsxx";
		String realTable = "bjlh_fqrzxsb";
		String lx = "成教生";
		String url = "/xgxt/bjlh_sjwh.do?method=cjsUpdate&doType=update&isCz=yes&pk=";
		doType = Base.isNull(doType) ? "add" : doType;

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// 查看或修改所选数据
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			String pk = "fqrzxh||lx";
			String pkValue = key;
			String[] colList = new String[] { "fqrzxh", "lx", "xm", "xb", "xydm",
					"zydm", "bjdm", "mz", "zzmm", "csrq", "sfzh", "lxdh", "jg",
					"lydq", "sg", "tz", "rxrq", "xz", "bz", "nj" };
			rs = service.getGyglInfo(tableName, pk, pkValue, colList);
			
//			判断录入值是否已存在于数据库
			if ("yes".equalsIgnoreCase(isCz)) {
				doType = "add";
			}
		}

		// 保存研究生情况
		if ("save".equalsIgnoreCase(doType)) {

			String[] onezd = new String[] { "fqrzxh", "lx", "xm", "xb", "bjdm",
					"mz", "zzmm", "csrq", "sfzh", "lxdh", "jg", "lydq", "sg",
					"tz", "rxrq", "xz", "bz" };
			String pk = "fqrzxh||lx";
			String pkValue = myForm.getFqrzxh() + lx;

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			model.setLx("成教生");

			boolean result = service.saveGygl(saveForm, model, request);
			request.setAttribute("result", result);
		}
		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("url", url);
		request.setAttribute("rs", rs);
		// 设置列表值
		myForm.setLx(lx);
		service.setList(myForm, request);
		FormModleCommon.requestSetList(new String[] { "mz", "zzmm" }, request);

		return mapping.findForward("cjsUpdate");
	}

	/**
	 * 房源库管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fykManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_ssxx";
		String realTable = "bjlh_ssxxb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		// 批量删除房源库
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "lddm||cs||qsh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delGygl(saveForm, model);
				
				if (result) {
					service.createCwxx();
				}
				
				request.setAttribute("result", result);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xqmc", "ssbh", "zcs",
					"cs", "cws", "qsdh", "sfbz","fbbj" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "bjlh_gygl_fyk.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// 设置列表值
		service.setList(myForm,request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("fykManage");
	}

	/**
	 * 房源库维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fykUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "公寓管理 - 信息维护 - 房源库维护";
		String key = request.getParameter("pk");
		String userType = session.getAttribute("userType").toString();
		// String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String isCz = request.getParameter("isCz");
		String tableName = "view_bjlh_ssxx";
		String realTable = "bjlh_ssxxb";
		String url = "/xgxt/bjlh_sjwh.do?method=fykUpdate&doType=update&isCz=yes&pk=";
		doType = Base.isNull(doType) ? "add" : doType;

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// 查看或修改所选数据
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			String pk = "lddm||cs||qsh";
			String pkValue = key;
			String[] colList = new String[] { "lddm", "ldmc", "cs", "qsh",
					"cws", "fbbj", "qsdh", "sfbz", "bz", "xqdm", "xqmc" };
			rs = service.getGyglInfo(tableName, pk, pkValue, colList);
			myForm.setLddm(rs.get("lddm"));
			
			//	判断录入值是否已存在于数据库
			if ("yes".equalsIgnoreCase(isCz)) {
				doType = "add";
			}
		}

		// 保存房源库情况
		if ("save".equalsIgnoreCase(doType)) {

			String[] onezd = new String[] { "lddm", "cs", "qsh", "cws", "fbbj",
					"qsdh", "sfbz", "bz" };
			String pk = "lddm||cs||qsh";
			String pkValue = myForm.getLddm() + myForm.getCs() + myForm.getQsh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveGygl(saveForm, model, request);
			
			if (result) {
				service.createCwxx();
			}
			
			request.setAttribute("result", result);
		}
		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("url", url);
		request.setAttribute("rs", rs);
		// 设置列表值
		service.setList(myForm, request);

		return mapping.findForward("fykUpdate");
	}

	/**
	 * 学生住宿信息管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward zsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_xszsxx";
		String realTable = "bjlh_xszsxxb";
		String doType = request.getParameter("doType");
		@SuppressWarnings("unused")
		String lx = "";
		String isAdmin = "yes";
		String isXy = "no";
		String errlx = myForm.getErrlx();
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
				
  		// 判断用户权限
		if (!( "admin".equalsIgnoreCase(userType))) {
			lx = myForm.getLx();
			isAdmin = "no";
			if (userDep.equalsIgnoreCase(service.getBmdm("科研处"))) {
				lx = "研究生";	
			}else if (userDep.equalsIgnoreCase(service.getBmdm("成人教育处"))) {
				lx = "成教生";	
			}else if (userDep.equalsIgnoreCase(service.getBmdm("团委"))) {
				lx = "团委生";	
			}else if (userDep.equalsIgnoreCase(service.getBmdm("体育教学部"))) {
				lx = "体优生";	
			} else {
				if ("xx".equalsIgnoreCase(userType)) {	
					isAdmin = "yes";
				}else{
					lx = "全日制";
					isXy = "yes";
					myForm.setXydm(userDep);
				}
			}
			myForm.setLx(lx);
			
			if (!Base.isNull(lx) && !"全日制".equalsIgnoreCase(lx)) {
				isXy = "yes";
			}
		}
		
		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		// 批量退房处理
		if (!Base.isNull(doType) && "tf".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "id";
				String[] onezd = new String[] { "zzbj","tfrq" }; 
				
				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);
				saveForm.setOnezd(onezd);
				
				model.setZzbj("no");
				model.setTfrq(GetTime.getSystemTime().replace("-",""));
				myForm.setZzbj("no");
				
				result = service.updateGygl(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		//	整体退房
		if (!Base.isNull(doType) && "zttf".equalsIgnoreCase(doType)) {
			myForm.setZzbj("no");
			result = service.excuteZttf(model);
		}
		
		// 批量删除所选数据
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "id";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delGygl(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb",
					"ssbh","cwm", "xymc","zymc","bjmc","lx","rzrq","tfrq" };

			String sfyc = myForm.getSfyc();
			if (!Base.isNull(sfyc)) {
				colList = new String[] { "pk", "xh", "xm", "xb", "ssbh", "cwm",
						"xymc", "zymc", "bjmc", "lx", "err" };
				if ("lxbf".equalsIgnoreCase(errlx)) {
					tableName = "view_bjlh_gyerr_lxbf";
				}else if ("cwcf".equalsIgnoreCase(errlx)){
					tableName = "view_bjlh_gyerr_cwcf";
				}else if ("ssfp".equalsIgnoreCase(errlx)){
					tableName = "view_bjlh_gyerr_ssfp";
				}else if ("twty".equalsIgnoreCase(errlx)){
					tableName = "view_bjlh_gyerr_twty";
				}else if ("xbyc".equalsIgnoreCase(errlx)){
					tableName = "view_bjlh_gyerr_xbyc";
				}
			}
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "bjlh_gygl_zsxx.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("isXy", isXy);
		request.setAttribute("isAdmin", isAdmin);
		request.setAttribute("errlx", errlx);
		// 设置列表值
		service.setList(myForm,request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("zsxxManage");
	}

	/**
	 * 学生住宿信息维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward zsxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		
		String title = "公寓管理 - 信息维护 - 学生住宿信息维护";
		String key = request.getParameter("pk");
		String userType = session.getAttribute("userType").toString();
		// String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String tableName = "view_bjlh_xszsxx";
		String realTable = "bjlh_xszsxxb";
		String xh = request.getParameter("xh");
		String lx = request.getParameter("lx");
		doType = Base.isNull(doType) ? "add" : doType;

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();
		
		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// 查看或修改所选数据
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			String pk = "pk";
			String pkValue = key;
			String[] colList = new String[] { "xh", "xm", "nj", "xb", "xqdm",
					"lddm", "cs", "qsh", "cwh", "xydm", "xymc", "zymc", "bjmc",
					"lx", "rzrq","tfrq", "zzbj", "bz" };
			if (!Base.isNull(xh) && !Base.isNull(lx)) {
				pkValue = service.getId(xh, lx);
				doType = "add";
			}
			rs = service.getGyglInfo(tableName, pk, pkValue, colList);
			if (Base.isNull(rs.get("xh"))) {
				tableName = "view_bjlh_xsxx";
				pk = "xh||lx";
				pkValue = xh + lx;
				colList = new String[] { "xh", "xm", "nj", "xb", "xydm",
						"xymc", "zymc", "bjmc", "lx" };
				rs = service.getGyglInfo(tableName, pk, pkValue, colList);
			}
			rs.put("zzbj", Base.isNull(rs.get("zzbj")) ? "yes" : rs.get("zzbj"));
			myForm.setXydm(rs.get("xydm"));
			myForm.setXqdm(rs.get("xqdm"));
			myForm.setLddm(rs.get("lddm"));
			myForm.setCs(rs.get("cs"));
			myForm.setQsh(rs.get("qsh"));
			myForm.setXh(rs.get("xh"));
			myForm.setFplx(Base.isNull(rs.get("zzbj")) ? "yes" : rs.get("zzbj"));
			String bmdm = service.getBmdm(rs.get("lx"));
			
			if (SjwhService.TWDM.equalsIgnoreCase(bmdm)) {// 分配标记为团委
				myForm.setXydm(SjwhService.TWDM);
			} else if (SjwhService.TYDM.equalsIgnoreCase(bmdm)) {// 分配标记为体育教学部
				myForm.setXydm(SjwhService.TYDM);
			} else if (SjwhService.KYDM.equalsIgnoreCase(bmdm)) {// 分配标记为科研处
				myForm.setXydm(SjwhService.KYDM);
			} else if (SjwhService.CJDM.equalsIgnoreCase(bmdm)) {// 分配标记为成人教育部
				myForm.setXydm(SjwhService.CJDM);
			} 
		}

		// 保存学生住宿情况
		if ("save".equalsIgnoreCase(doType)) {

			service.updateTfxx(model);
			
			String[] onezd = new String[] { "xh", "lx", "lddm", "cs", "qsh",
					"cwh", "rzrq", "tfrq", "zzbj", "bz" };
			String pk = "xh||lx||rzrq";
			String pkValue = myForm.getXh() + myForm.getLx() + myForm.getRzrq();

			SaveForm saveForm = new SaveForm();
			
			saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			model.setZzbj(myForm.getZzbj());
			model.setTfrq("");
			boolean result = service.saveGygl(saveForm, model, request);
			request.setAttribute("result", result);
		}
		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("rs", rs);
		// 设置列表值
		service.setList(myForm, request);

		return mapping.findForward("zsxxUpdate");
	}
	
	/**
	 * 学生住宿信息查看
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward zsxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		HttpSession session = request.getSession();

//		String userType = session.getAttribute("userType").toString();
//		String userName = session.getAttribute("userName").toString();
//		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
//		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_xsxx";
		String title = "公寓管理 - 学生信息查看";
		String xh = request.getParameter("xh");
		
		model.setXh(xh);
		
		String[] colList = new String[] { "xh", "xm", "xb", "lx", "xymc",
				"zymc", "bjmc" };

		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName, colList, null);
		ArrayList<String[]> rsList = service.getGyglList(tableName, model, colList);
		
		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("topTr", topTr);
			request.setAttribute("rsList", rsList);
			request.setAttribute("rsNum", rsList.size());
		}
		
		request.setAttribute("title", title);
		
		return mapping.findForward("zsxxView");
	}
	
	/**
	 * 学生基本信息管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_xsxx";
		String realTable = "";
//		String doType = request.getParameter("doType");
		String lx = request.getParameter("lx");
		String xslx = "";
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		//判断查询学生类型
		if ("研究生".equalsIgnoreCase(lx) || "成教生".equalsIgnoreCase(lx)) {
			xslx = lx;
		} 
		myForm.setLx(xslx);
		
		BeanUtils.copyProperties(model, myForm);

		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			model.setLx(lx);
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc" };
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "bjlh_gygl_zsxx.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// 设置列表值
		request.setAttribute("lx", lx);
		request.setAttribute("xslx", xslx);
		service.setList(myForm,request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("xsxxManage");
	}
	
	/**
	 * 行李床位管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlcwManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_xlcw";
		String realTable = "bjlh_xlcwb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		// 批量删除行李床位
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "lddm||cs||qsh||cwh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delGygl(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xqmc", "ssbh", "zcs",
					"cs", "cwm" };
			String sfyc = myForm.getSfyc();
			if (!Base.isNull(sfyc)) {
				tableName = "view_bjlh_xlcw_err";
				colList = new String[] { "pk", "xqmc", "ssbh", "cwm", "err" };
			}
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "bjlh_gygl_xlcw.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// 设置列表值
		service.setList(myForm, request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("xlcwManage");
	}

}
