package xgxt.dtjs.sjxy.dyxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.dtjs.sjxy.SjxyDtjsForm;
import xgxt.form.SaveForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

public class DyxxAction extends DispatchAction {

	/**
	 * 入党申请管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward rdsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		HttpSession session = request.getSession();

		SjxyDtjsForm myForm = (SjxyDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String doType = request.getParameter("doType");
		String tableName = "view_rdsq";
		String realTable = "rdsqb";
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		boolean result = false;

		// 学院用户访问
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		// 批量删除入党申请
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||xh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				if (result) {
					model.setLx("入党申请");
					result = service.delSszb(model, checkVal);
				}
				request.setAttribute("result", result);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "djsqsj", "zbmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_rdsq.do");
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
		String tableName = "view_rdsq";
		String realTable = "rdsqb";
		doType = Base.isNull(doType) ? "add" : doType;

		SjxyDtjsForm myForm = (SjxyDtjsForm) form;
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
					"nd", "djsqsj", "zbmc", "bz","xsccdm" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
			rs.put("sszb", rs.get("zbmc"));
			myForm.setXydm(rs.get("xydm"));
		}

		// 保存党总支信息
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "djsqsj",
					"bz","xsccdm" };
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);

			if (result && !Base.isNull(model.getZbmc())) {
				model.setLx("入党申请");
				result = service.saveSszb(model, request);
			}
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

//		HttpSession session = request.getSession();

		SjxyDtjsForm myForm = (SjxyDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String doType = request.getParameter("doType");
		String tableName = "view_sjxy_rdjjfzxx";
		String realTable = "rdjjfzxxb";
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		boolean result = false;

		// 学院用户访问
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		String zzzt = myForm.getZzzt();
		if(Base.isNull(zzzt)){
			myForm.setZzzt("yes");
		}else if ("all".equalsIgnoreCase(zzzt)){
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
				if (result) {
					model.setLx("积极分子");
					result = service.delSszb(model, checkVal);
				}
				request.setAttribute("result", result);
			}
		}

		//	入党积极分子 --> 预备党员
		if ("zz".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			result = service.changeDylx(checkVal, "积极分子", request);
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
					"xn", "xqmc", "zbmc", "kssj", "ztmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_jjfz.do");
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

		SjxyDtjsForm myForm = (SjxyDtjsForm) form;
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
					"nd", "kssj", "zbmc", "xsccdm", "bz","zzzt" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
		}

		//	入党积极分子 --> 预备党员
		if ("zz".equalsIgnoreCase(doType)) {
			model.setLx("积极分子");
			boolean result = service.changeDylx(model, request);
			request.setAttribute("result", result);
		}
		
		// 保存入党积极分子
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
					"kssj", "bz" };
			if(!Base.isNull(myForm.getZzzt())){
				onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
						"kssj", "bz","zzzt" };
			}
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);

			if (result && !Base.isNull(model.getZbmc())) {
				model.setLx("积极分子");
				result = service.saveSszb(model, request);
			}
			request.setAttribute("result", result);
		}
		
		myForm.setXydm(rs.get("xydm"));
		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("jjfzUpdate");
	}

	/**
	 * 预备党员管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward ybdyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		HttpSession session = request.getSession();

		SjxyDtjsForm myForm = (SjxyDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String doType = request.getParameter("doType");
		String tableName = "view_sjxy_ybdyxx";
		String realTable = "ybdyxxb";
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		boolean result = false;

		// 学院用户访问
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		String zzzt = myForm.getZzzt();
		if(Base.isNull(zzzt)){
			myForm.setZzzt("yes");
		}else if ("all".equalsIgnoreCase(zzzt)){
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
				if (result) {
					model.setLx("预备党员");
					result = service.delSszb(model, checkVal);
				}
				request.setAttribute("result", result);
			}
		}

		//	入党积极分子 --> 预备党员
		if ("zz".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			result = service.changeDylx(checkVal, "预备党员", request);
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
					"xn", "xqmc", "zbmc", "kssj", "ztmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_ybdy.do");
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

		SjxyDtjsForm myForm = (SjxyDtjsForm) form;
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
					"nd", "zbmc", "bz", "xsccdm", "kssj", "jssj", "zzlx", "bz","zzzt" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
		}

		//	预备党员 --> 正式党员
		if ("zz".equalsIgnoreCase(doType)) {
			model.setLx("预备党员");
			boolean result = service.changeDylx(model, request);
			request.setAttribute("result", result);
		}
		
		// 保存预备党员
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
					"kssj", "jssj", "zzlx", "bz" };
			if(!Base.isNull(myForm.getZzzt())){
				onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
						"kssj", "jssj", "zzlx", "bz","zzzt" };
			}
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);

			if (result && !Base.isNull(model.getZbmc())) {
				model.setLx("预备党员");
				result = service.saveSszb(model, request);
			}
			request.setAttribute("result", result);
		}

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

//		HttpSession session = request.getSession();

		SjxyDtjsForm myForm = (SjxyDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String doType = request.getParameter("doType");
		String tableName = "view_sjxy_dyxx";
		String realTable = "dyxxb";
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		boolean result = false;

		// 学院用户访问
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		String zzzt = myForm.getZzzt();
		if(Base.isNull(zzzt)){
			myForm.setZzzt("yes");
		}else if ("all".equalsIgnoreCase(zzzt)){
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
				if (result) {
					model.setLx("正式党员");
					result = service.delSszb(model, checkVal);
				}
				request.setAttribute("result", result);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
					"xn", "xqmc", "zbmc", "rdsj", "ztmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_zsdy.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

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

		SjxyDtjsForm myForm = (SjxyDtjsForm) form;
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
					"ybdyjssj", "bz", "zzdw", "zzlx","zzzt" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
		}

		// 保存正式党员
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
					"rdsj", "zzsj", "ybdykssj", "ybdyjssj", "bz", "zzdw",
					"zzlx" };
			if (!Base.isNull(myForm.getZzzt())) {
				onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
						"rdsj", "zzsj", "ybdykssj", "ybdyjssj", "bz", "zzdw",
						"zzlx", "zzzt" };
			}
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);

			if (result && !Base.isNull(model.getZbmc())) {
				model.setLx("正式党员");
				result = service.saveSszb(model, request);
			}
			request.setAttribute("result", result);
		}

		myForm.setXydm(rs.get("xydm"));
		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("zsdyUpdate");
	}
}
