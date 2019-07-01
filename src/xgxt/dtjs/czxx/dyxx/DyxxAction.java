package xgxt.dtjs.czxx.dyxx;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
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
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;

import com.zfsoft.basic.BasicAction;
import common.Globals;

public class DyxxAction extends BasicAction {

	/**
	 * 入党申请管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward rdsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();
		
		User user = getUser(request);
		
		String userType = user.getUserStatus();
		String userDep = user.getUserDep();
		String userName = user.getUserName();
		
		String doType = request.getParameter("doType");
		String tableName = "view_czxx_rdsq";
		String realTable = "czxx_rdsqb";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 学院用户访问
		if("stu".equalsIgnoreCase(userType)){
			myForm.setXh(userName);
		}else if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		BeanUtils.copyProperties(model, myForm);

		// 批量删除入党申请
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xh||sqsj";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// 入党申请--> 入党积极分子
		if ("zz".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			result = service.changeDylx(checkVal, "入党申请", request);
		}

		// 入党申请等级转移
		if ("zhdj".equalsIgnoreCase(doType)) {
			String[] pkValue = myForm.getCheckVal();
			String zhdj = myForm.getZhdj();
			String zhsj = request.getParameter("zhsj");
			String pk = "xh||sqsj";
			String bjmc = "入党申请";
			result = service.saveZhdj(pk, pkValue, bjmc, zhdj, zhsj);
			request.setAttribute("result", result);
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "sqsj" };

			if ("是".equalsIgnoreCase(myForm.getSfty())) {
				tableName = "view_czxx_yxtyrd";
			}
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		// 设置转换等级
		myForm.setZhdj("入党申请");
		// 初始化列表
		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_rdsq.do");
		request.setAttribute("userType", userType);

		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("rdsqManage");
	}

	/**
	 * 入党申请维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward rdsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "党团建设 - 支部管理 - 入党申请";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_czxx_rdsq";
		String realTable = "czxx_rdsqb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "mzmc", "sqsj",
					"bz", "rxrq" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
		}

		// 入党申请 --> 入党积极分子
		if ("zz".equalsIgnoreCase(doType)) {
			model.setLx("入党申请");
			boolean result = service.changeDylx(model, request);
			request.setAttribute("result", result);
		}

		// 保存入党申请
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xh", "sqsj", "bz" };
			pk = "xh||sqsj";
			pkValue = myForm.getXh() + myForm.getSqsj();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);
			// if (result) {
			// if (service.isYxty(model.getXh())) {
			// model.setLx("入党申请");
			// result = service.changeDylx(model, request);
			// }
			// }
			request.setAttribute("result", result);
		}

		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("rdsqUpdate");
	}

	/**
	 * 入党积极分子管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward jjfzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();
		
		User user = getUser(request);
		
		String userType = user.getUserStatus();
		String userDep = user.getUserDep();
		String userName = user.getUserName();
		String doType = request.getParameter("doType");
		String tableName = "view_sjxy_rdjjfzxx";
		String realTable = "rdjjfzxxb";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 学院用户访问
		if ("stu".equalsIgnoreCase(userType)){
			myForm.setXh(userName);
		}else if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		String zzzt = myForm.getZzzt();
		if (Base.isNull(zzzt)) {
			myForm.setZzzt("yes");
		} else if ("all".equalsIgnoreCase(zzzt)) {
			myForm.setZzzt("");
		}

		BeanUtils.copyProperties(model, myForm);

		// 批量删除入党积极分子
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||xh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// 入党积极分子 --> 发展对象
		if ("zz".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			result = service.changeDylx(checkVal, "积极分子", request);
		}

		// 积极分子等级转移
		if ("zhdj".equalsIgnoreCase(doType)) {
			String[] pkValue = myForm.getCheckVal();
			String zhdj = myForm.getZhdj();
			String zhsj = request.getParameter("zhsj");
			String pk = "xn||xq||xh";
			String bjmc = "积极分子";
			result = service.saveZhdj(pk, pkValue, bjmc, zhdj, zhsj);
			request.setAttribute("result", result);
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
					"xn", "xqmc", "kssj", "ztmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		// 设置转换等级
		myForm.setZhdj("积极分子");
		// 初始化列表
		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_jjfz.do");
		request.setAttribute("userType", userType);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("jjfzManage");
	}

	/**
	 * 入党积极分子维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward jjfzUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "党团建设 - 支部管理 - 入党积极分子";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_sjxy_rdjjfzxx";
		String realTable = "rdjjfzxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "mzmc", "xn", "xq",
					"nd", "kssj", "zbmc", "xsccdm", "bz", "zzzt", "lwjjfzsj" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
		}

		// 入党积极分子 --> 预备党员
		if ("zz".equalsIgnoreCase(doType)) {
			model.setLx("积极分子");
			boolean result = service.changeDylx(model, request);
			request.setAttribute("result", result);
		}

		// 保存入党积极分子
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
					"kssj", "bz", "lwjjfzsj" };
			if (!Base.isNull(myForm.getZzzt())) {
				onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
						"kssj", "bz", "zzzt", "lwjjfzsj" };
			}
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		// 设置党团详细信息(如培训次数等)
		setDtxxInfo(rs, request);

		myForm.setXydm(rs.get("xydm"));
		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("jjfzUpdate");
	}

	/**
	 * 发展对象管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward fzdxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		User user = getUser(request);
		
		String userType = user.getUserStatus();
		String userDep = user.getUserDep();
		
		String doType = request.getParameter("doType");
		String tableName = "view_czxx_fzdx";
		String realTable = "czxx_fzdxb";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 学院用户访问
		if("stu".equalsIgnoreCase(userType)){
			myForm.setXh(user.getUserName());
		}else if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		String zzzt = myForm.getZzzt();
		if (Base.isNull(zzzt)) {
			myForm.setZzzt("yes");
		} else if ("all".equalsIgnoreCase(zzzt)) {
			myForm.setZzzt("");
		}

		BeanUtils.copyProperties(model, myForm);

		// 批量删除预备党员
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||xh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// 发展对象 --> 预备党员
		if ("zz".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			result = service.changeDylx(checkVal, "发展对象", request);
		}

		// 发展对象等级转移
		if ("zhdj".equalsIgnoreCase(doType)) {
			String[] pkValue = myForm.getCheckVal();
			String zhdj = myForm.getZhdj();
			String zhsj = request.getParameter("zhsj");
			String pk = "xn||xq||xh";
			String bjmc = "发展对象";
			result = service.saveZhdj(pk, pkValue, bjmc, zhdj, zhsj);
			request.setAttribute("result", result);
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
					"xn", "xqmc", "kssj", "ztmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		// 设置转换等级
		myForm.setZhdj("发展对象");
		// 初始化列表
		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_fzdx.do");
		request.setAttribute("userType", userType);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("fzdxManage");
	}

	/**
	 * 发展对象维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward fzdxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "党团建设 - 发展对象";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_czxx_fzdx";
		String realTable = "czxx_fzdxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
			rs.put("jg", new CommService().getSydmc(rs.get("jg"), "/", "/"));
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "xn", "xq", "nd",
					"bz", "kssj", "jssj", "bz", "zzzt", "csrq", "jg", "mzmc" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
			rs.put("jg", new CommService().getSydmc(rs.get("jg"), "/", "/"));
		}

		// 发展对象--> 预备党员
		if ("zz".equalsIgnoreCase(doType)) {
			model.setLx("发展对象");
			boolean result = service.changeDylx(model, request);
			request.setAttribute("result", result);
		}

		// 保存预备党员
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "kssj",
					"jssj", "bz" };
			if (!Base.isNull(myForm.getZzzt())) {
				onezd = new String[] { "xn", "xq", "xh", "nd", "kssj", "jssj",
						"bz", "zzzt" };
			}
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		// 设置党团详细信息(如培训次数等)
		setDtxxInfo(rs, request);

		myForm.setXydm(rs.get("xydm"));
		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("fzdxUpdate");
	}

	/**
	 * 预备党员管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward ybdyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();
		User user = getUser(request);
		
		String userType = user.getUserStatus();
		String userDep = user.getUserDep();
		String doType = request.getParameter("doType");
		String tableName = "view_sjxy_ybdyxx";
		String realTable = "ybdyxxb";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 学院用户访问
		if("stu".equalsIgnoreCase(userType)){
			myForm.setXh(user.getUserName());
		}else if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		String zzzt = myForm.getZzzt();
		if (Base.isNull(zzzt)) {
			myForm.setZzzt("yes");
		} else if ("all".equalsIgnoreCase(zzzt)) {
			myForm.setZzzt("");
		}

		BeanUtils.copyProperties(model, myForm);

		// 批量删除预备党员
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||xh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// 预备党员 --> 正式党员
		if ("zz".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			result = service.changeDylx(checkVal, "预备党员", request);
		}

		// 预备党员等级转移
		if ("zhdj".equalsIgnoreCase(doType)) {
			String[] pkValue = myForm.getCheckVal();
			String zhdj = myForm.getZhdj();
			String zhsj = request.getParameter("zhsj");
			String pk = "xn||xq||xh";
			String bjmc = "预备党员";
			result = service.saveZhdj(pk, pkValue, bjmc, zhdj, zhsj);
			request.setAttribute("result", result);
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
					"xn", "xqmc", "kssj", "ztmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		// 设置转换等级
		myForm.setZhdj("预备党员");
		// 初始化列表
		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_ybdy.do");
		request.setAttribute("userType", userType);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("ybdyManage");
	}

	/**
	 * 预备党员维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward ybdyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "党团建设 - 支部管理 - 预备党员";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_sjxy_ybdyxx";
		String realTable = "ybdyxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "mzmc", "xn", "xq",
					"nd", "zbmc", "bz", "xsccdm", "kssj", "jssj", "zzlx", "bz",
					"zzzt","db","zb" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
		}

		// 预备党员 --> 正式党员
		if ("zz".equalsIgnoreCase(doType)) {
			model.setLx("预备党员");
			boolean result = service.changeDylx(model, request);
			request.setAttribute("result", result);
		}

		// 保存预备党员
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
					"kssj", "jssj", "zzlx", "bz","db","zb" };
			if (!Base.isNull(myForm.getZzzt())) {
				onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
						"kssj", "jssj", "zzlx", "bz", "zzzt","db","zb" };
			}
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		// 设置党团详细信息(如培训次数等)
		setDtxxInfo(rs, request);

		myForm.setXydm(rs.get("xydm"));
		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("ybdyUpdate");
	}

	/**
	 * 正式党员管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward zsdyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);// 用户对象

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String doType = request.getParameter("doType");
		// 用户身份
		String userType = user.getUserStatus();
		// 用户名
		String userName = user.getUserName();
		// 用户详
		String userDep = user.getUserDep();

		String tableName = "view_sjxy_dyxx";
		String realTable = "dyxxb";
		boolean result = false;

		String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc", "xn",
				"xqmc", "rdsj", "ztmc" };

		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);

		// 学院用户访问
		if ("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		} else if ("stu".equalsIgnoreCase(userType)) {
			myForm.setXh(userName);
		}

		String zzzt = myForm.getZzzt();
		if (Base.isNull(zzzt)) {
			myForm.setZzzt("yes");
		} else if ("all".equalsIgnoreCase(zzzt)) {
			myForm.setZzzt("");
		}

		BeanUtils.copyProperties(model, myForm);

		// 批量删除正式党员
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||xh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		} else if (!Base.isNull(doType) && "gxzjbxx".equalsIgnoreCase(doType)) {// 将党员信息更新至基本信息
			result = service.gxzJbxx();
			request.setAttribute("result", result);
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {

			// topTr = SearchUtils.getTopTr(tableName, colList, null);
			rsArrList = service.getDyxxList(tableName, model, colList);

			request.setAttribute("searchTj", myForm.getSearchModel());
		} else {
			myForm.getPages().setMaxPage(1);
		}

		// 初始化列表
		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_zsdy.do");
		request.setAttribute("userType", userType);
		FormModleCommon.commonRequestSet(request, tableName, realTable,
				rsArrList, topTr);

		RequestForm rForm = new RequestForm();

		// 分页
		Pages pages = myForm.getPages();

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// 显示的数量
		String showNum = "8";
		commSetting.setShowNum(showNum);
		// ===============通用设置 end ============

		rForm.setColList(colList);
		rForm.setCommSetting(commSetting);
		rForm.setPages(pages);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);

		CommService commService = new CommService();

		commService.setRequestValue(rForm, user, request);

		if (Globals.XXDM_GDTYZYJSXY.equalsIgnoreCase(Base.xxdm)) {// 是否现在-更新至基本信息
			request.setAttribute("sfxs_gxzjbxx", "是");
		}

		return mapping.findForward("zsdyManage");
	}

	/**
	 * 正式党员维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward zsdyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "党团建设 - 支部管理 - 正式党员";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_sjxy_dyxx";
		String realTable = "dyxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "mzmc", "xn", "xq",
					"nd", "zbmc", "bz", "xsccdm", "rdsj", "zzsj", "ybdykssj",
					"ybdyjssj", "bz", "zzdw", "zzlx", "zzzt","dnzw" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
		}

		// 保存正式党员
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
					"rdsj", "zzsj", "ybdykssj", "ybdyjssj", "bz", "zzdw",
					"zzlx","dnzw" };
			if (!Base.isNull(myForm.getZzzt())) {
				onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
						"rdsj", "zzsj", "ybdykssj", "ybdyjssj", "bz", "zzdw",
						"zzlx", "zzzt","dnzw" };
			}
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		// 设置党团详细信息(如培训次数等)
		setDtxxInfo(rs, request);

		myForm.setXydm(rs.get("xydm"));
		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("zsdyUpdate");
	}

	/**
	 * 培训信息管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward pxxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// HttpSession session = request.getSession();

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String doType = request.getParameter("doType");
		String tableName = "view_czxx_dkxx";
		String realTable = "czxx_dkpxb";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		// 批量删除正式党员
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "pxsj";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				if (result) {

				}
				request.setAttribute("result", result);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "pxmc", "pxsj", "pxdd",
					"cjrs" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_pxxx.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("pxxxManage");
	}

	/**
	 * 培训信息维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward pxxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "党团建设 - 支部管理 - 党课培训";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_dyxx";
		String realTable = "czxx_dkpxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType) || "update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {

			String pxsj = Base.isNull(pkValue) ? request.getParameter("gzkssj")
					: pkValue;
			model.setPxsj(pxsj);

			pk = "pxsj";
			pkValue = pxsj;
			tableName = "czxx_dkpxb";
			String[] colList = new String[] { "pxmc", "pxdd", "pxsj", "pxnr",
					"bz" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
			rs.put("pxsj", pxsj);
			if ("".equalsIgnoreCase(rs.get("pxmc")) || null == rs) {
				rs.put("pxmc", myForm.getPxmc());
				rs.put("pxdd", myForm.getPxdd());
				rs.put("pxnr", myForm.getPxnr());
				rs.put("bz", myForm.getBz());
			}

			if (!Base.isNull(pxsj)) {
				colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc", "dkcj" };
				tableName = "view_czxx_dksjb";
				List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
						tableName, colList, null);
				ArrayList<String[]> rsList = service.getDyxxList(tableName,
						model, colList);
				if (rsList != null && rsList.size() > 0) {
					request.setAttribute("topTr", topTr);
					request.setAttribute("rsList", rsList);
					request.setAttribute("rsNum", rsList.size());
				}
			}
		}

		// 保存正式党员
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "pxmc", "pxsj", "pxdd", "pxnr",
					"bz" };
			pk = "pxsj";
			pkValue = myForm.getPxsj();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);

			if (result) {
				String[] checkVal = myForm.getPxmdxh();
				String[] pxmdxh = new String[checkVal.length];
				String pxsj = myForm.getPxsj();

				if (checkVal != null && checkVal.length > 0) {

					realTable = "czxx_dkpxmdb";
					pk = "pxmdxh||pxsj";
					String[] arrzd = new String[] { "pxmdxh", "dkcj" };
					onezd = new String[] { "pxsj" };

					for (int i = 0; i < checkVal.length; i++) {
						pxmdxh[i] = checkVal[i];
						checkVal[i] = checkVal[i] + pxsj;
					}

					saveForm = new SaveForm();
					saveForm.setTableName(realTable);
					saveForm.setPk(pk);
					saveForm.setPkValue(checkVal);
					saveForm.setArrzd(arrzd);
					saveForm.setOnezd(onezd);

					model.setPxmdxh(pxmdxh);
					result = service.saveDyxx(saveForm, model);
				}
			}
			request.setAttribute("result", result);
		}

		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("pxxxUpdate");
	}

	/**
	 * 党课名单管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward dkmdManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String tableName = "view_czxx_dkpxmdb";
		String realTable = "czxx_dkpxmdb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		boolean reslut = false;

		// 保党课名单
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			String pxsj = request.getParameter("gzkssj");

			if (checkVal != null && checkVal.length > 0) {

				String pk = "pxmdxh||pxsj";
				String[] arrzd = new String[] { "pxmdxh" };
				String[] onezd = new String[] { "pxsj" };
				String[] pkValue = new String[checkVal.length];
				for (int i = 0; i < pkValue.length; i++) {
					pkValue[i] = checkVal[i] + pxsj;
				}

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(pkValue);
				saveForm.setArrzd(arrzd);
				saveForm.setOnezd(onezd);

				model.setPxmdxh(checkVal);
				model.setPxsj(pxsj);

				reslut = service.saveDyxx(saveForm, model);
				request.setAttribute("result", reslut);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| reslut) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "pxcs" };
			model.setPxsj(null);
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "dtjs_dkmd.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// 设置列表值
		service.setList(myForm, request);
		//tableName = "view_czxx_dkpxmdb";
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("dkmdManage");
	}

	/**
	 * 党课培训时间
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward dkmdUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String tableName = "view_czxx_dksjb";
		String realTable = "czxx_dkpxmdb";
		String title = "党团建设 - 党课时间";
		String xh = request.getParameter("pk");
		String doType = request.getParameter("doType");

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		model.setXh(xh);

		String[] colList = new String[] { "pk", "xm", "xb", "xymc", "zymc",
				"bjmc", "pxsj", "dkcj" };

		// 批量党课培训时间
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "id";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				boolean result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		ArrayList<String[]> rsList = service.getDyxxList(tableName, model,
				colList);

		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("topTr", topTr);
			request.setAttribute("rsList", rsList);
			request.setAttribute("rsNum", rsList.size());
		}

		request.setAttribute("title", title);
		request.setAttribute("pk", xh);

		return mapping.findForward("dkmdUpdate");
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

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String tableName = request.getParameter("tableName");
		String lx = request.getParameter("lx");
		String realTable = "";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);
		String zd = request.getParameter("zd");
		String va = request.getParameter("va");
		model.setZd(zd);
		model.setZdValue(va);

		if (userType.equalsIgnoreCase("xy")) {
			model.setXydm(userDep);
			myForm.setXydm(userDep);
		}
		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc" };
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "bjlh_gygl_zsxx.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("lx", lx);
		request.setAttribute("zd", zd);
		request.setAttribute("va", va);

		if (rs != null) {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		// 设置列表值
		service.setList(myForm, request);
		// if (session.getAttribute("fdyQx").equals(true)
		// || "true".equalsIgnoreCase(session.getAttribute("fdyQx")
		// .toString())) {
		// request.setAttribute("bjList", Fdypd.getFdybjList(userName));
		// }
		return mapping.findForward("xsxxManage");
	}

	/**
	 * 思想汇报管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward sxhbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String tableName = "view_czxx_sxhb";
		String realTable = "czxx_sxhbb";
		boolean result = false;

		// 判断是否学生用户
		if ("stu".equalsIgnoreCase(userType)) {
			myForm.setXh(userName);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		// 批量删除思想汇报
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "id";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "id", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "xn", "xqmc", "tjsj", "wjm", "scdz" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_sxhb.do");
		request.setAttribute("userType", userType);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("sxhbManage");
	}

	/**
	 * 思想汇报维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward sxhbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "党团建设 - 思想汇报";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_czxx_sxhb";
		String realTable = "czxx_sxhbb";
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {

			String xh = "";
			// 判断登陆者是否学生
			if ("stu".equalsIgnoreCase(userType)) {
				xh = userName;
			} else {
				xh = request.getParameter("xh");
			}
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "id";
			String[] colList = new String[] { "id", "xh", "xm", "xb", "nj",
					"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "xn", "xq",
					"tjsj", "wjm", "bz", "scdz" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
		}

		// 保存思想汇报
		if ("save".equalsIgnoreCase(doType)) {

			// 处理文件上传
			FormFile file = myForm.getUploadFile();
			String filePath = request.getParameter("scdz");
			String fName = "";
			if (file != null) {
				String dir = "/upload/dtjs";
				File f = new File(dir);
				if (!f.exists()) {
					f.mkdirs();
				}
				Timestamp date = new Timestamp(System.currentTimeMillis());
				String dateStr = date.toString().substring(0, 19);
				dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
						.replaceAll(":", "");
				int size = file.getFileSize();
				if (size < 10485760 && size != 0) {
					fName = dateStr + file.getFileName();
					InputStream in = file.getInputStream();
					filePath = dir + "/" + fName;
					OutputStream out = new FileOutputStream(filePath);
					int bytesRead = 0;
					byte[] buffer = new byte[size];
					while ((bytesRead = in.read(buffer, 0, size)) != -1) {
						out.write(buffer, 0, bytesRead);
					}
					out.close();
					in.close();
				} else {
					request.setAttribute("alert", "cannot");
				}
			}

			myForm.setScdz(filePath);
			myForm.setTjr(userName);

			BeanUtils.copyProperties(model, myForm);

			String[] onezd = new String[] { "xh", "xn", "xq", "tjr", "tjsj",
					"wjm", "scdz", "bz" };
			pk = "id";
			pkValue = Base.isNull(myForm.getId()) ? "" : myForm.getId();

			boolean result = true;

			if (!Base.isNull(pkValue)) {
				result = service.fileDel(realTable, "scdz", pk, pkValue);
			}

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			result = service.saveDyxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		myForm.setXydm(rs.get("xydm"));
		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("rs", rs);

		return mapping.findForward("sxhbUpdate");
	}

	/**
	 * @describe 下载所选择文件
	 * @author luojw
	 * @throws Exception
	 */
	public ActionForward downLoadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		if(!"10335".equals(Base.xxdm)){
		byte b[] = new byte[500];
		String dir = DealString.toGBK(request.getParameter("dir"));
		String filename = request.getParameter("fileName");

		if (!Base.isNull(filename)) {
			dir = servlet.getServletContext().getRealPath("WEB-INF/upLoad")
					+ "/" + filename;
			;
		} else {
			filename = dir.substring(27, dir.length());
		}

		File fileload = new File(dir);
		if(!fileload.exists()){
			String msg = "您所下载的文件不存在，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
			
		}else{
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ DealString.toUtf8String(filename));
			InputStream in;
			try {
				in = new FileInputStream(fileload);
				in = new BufferedInputStream(in);
				while ((in.read(b)) != -1) {
					response.getOutputStream().write(b);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		}else{
			return null;
		}
				
		return null;
	}

	/**
	 * @describe 删除所上传文件
	 * @author luojw
	 * @throws Exception
	 */
	public ActionForward fileDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();

		String doType = request.getParameter("doType");
		String pk = "id";
		String pkValue = myForm.getId();
		pkValue = Base.isNull(pkValue) ? request.getParameter("id") : pkValue;
		String forward = request.getParameter("forward");
		String realTable = "czxx_sxhbb";

		if (!Base.isNull(pkValue)) {
			if (!Base.isNull(doType)) {
				forward += "&doType=" + doType;
			}
			forward += "&pk=" + pkValue;
			service.fileDel(realTable, "scdz", pk, pkValue);
		}

		request.setAttribute("doType", doType);

		return new ActionForward(forward, false);
	}

	/**
	 * @describe 积极份子登记表管理
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @throws Exception
	 */
	public ActionForward jjfzdjbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkVal = request.getParameter("pk");
		String pk = "xn||xq||xh";
		DyxxService service = new DyxxService();
		HashMap<String, String> map = service.queryRdsqjbxx(pk, pkVal);
		int qsn = 1990;// 起始年
		int dqn = Integer.parseInt(GetTime.getNowYear());// 当前年
		String rxrq = map.get("rxrq");// 入学时间

		qsn = Integer.parseInt(Base.isNull(rxrq) ? map.get("nj") : rxrq
				.substring(0, 4));
		int flag = 0;
		String[] xqArr = { "01", "02" };
		int num = 0;
		while (qsn <= dqn && flag < 4) {
			String xn = qsn + "-" + (qsn + 1);
			qsn++;
			flag++;
			for (int i = 0; i < 2; i++) {
				request.setAttribute("xq" + (++num), service.queryXsxgxxByXq(
						map.get("xh"), xn, xqArr[i]));// 按学期查询学生所有相关信息
			}
		}

		map.put("pxsjjcj", service.getPxcjjsj(map.get("xh")));// 加入党校培训时间及成绩
		request.setAttribute("rs", map);
		return mapping.findForward("jjfzdjb");
	}

	/**
	 * 设置党团详细信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void setDtxxInfo(HashMap<String, String> rs,
			HttpServletRequest request) {

		String xh = rs.get("xh");
		String tableName = "";
		String xxdm = Base.xxdm;

		DyxxService service = new DyxxService();

		if (rs != null && rs.size() != 0) {

			// 团内推优
			String[] colList = new String[] { "xn", "xqmc" };
			String[] zd = new String[] { "xxsh", "xh" };
			String[] zdz = new String[] { "通过", xh };

			if (!Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
				zd = new String[] { "xh" };
				zdz = new String[] { xh };
			}

			tableName = "view_yxtyxxb";
			List<HashMap<String, String>> topTnty = SearchUtils.getTopTr(
					tableName, colList, null);
			ArrayList<String[]> tyList = service.getDyxxList(tableName, zd,
					zdz, colList);

			if (tyList != null && tyList.size() > 0) {
				request.setAttribute("topTnty", topTnty);
				request.setAttribute("tyList", tyList);
				request.setAttribute("tyNum", tyList.size());
			}

			// 思想汇报
			colList = new String[] { "xn", "xqmc", "wjm", "scdz" };
			zd = new String[] { "xh" };
			zdz = new String[] { xh };
			tableName = "view_czxx_sxhb";
			List<HashMap<String, String>> topSxhb = SearchUtils.getTopTr(
					tableName, colList, null);
			ArrayList<String[]> hbList = service.getDyxxList(tableName, zd,
					zdz, colList);

			if (hbList != null && hbList.size() > 0) {
				request.setAttribute("topSxhb", topSxhb);
				request.setAttribute("hbList", hbList);
				request.setAttribute("hbNum", hbList.size());
			}

			// 党课培训
			colList = new String[] { "pxmc", "pxsj", "dkcj" };
			zd = new String[] { "xh" };
			zdz = new String[] { xh };
			tableName = "view_czxx_dksjb";
			List<HashMap<String, String>> topDkpx = SearchUtils.getTopTr(
					tableName, colList, null);
			ArrayList<String[]> pxList = service.getDyxxList(tableName, zd,
					zdz, colList);

			if (pxList != null && pxList.size() > 0) {
				request.setAttribute("topDkpx", topDkpx);
				request.setAttribute("pxList", pxList);
				request.setAttribute("pxNum", pxList.size());
			}
		}
	}
}
