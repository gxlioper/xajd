package xgxt.dtjs.gdby.dyxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.dtjs.czxx.dyxx.DyxxService;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.SaveForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;


/**
 * Title: 学生工作管理系统 Description:广东白云 党团建设 
 * Action Module:党团建设 - 党员信息 
 * Copyright:Copyright (c) 2010 
 * Company: zfsoft 
 * Author: sjf 
 * Version: 1.0 Time: 2010-7-30
 */
public class DyxxGdbyAction extends BasicExtendAction{
	
	/**
	 * 党课培训时间
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward dkmdUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String tableName = "view_gdby_dksjb";
		String realTable = "czxx_dkpxmdb";
		String title = "党团建设 - 党课时间";
		String xh = request.getParameter("pk");
		String doType = request.getParameter("doType");

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		model.setXh(xh);

		String[] colList = new String[] { "pk", "xm", "xb", "xymc", "zymc",
				"bjmc", "pxsj", "xxtd","xxjl","xxxg" };

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

		String tableName = "view_gdby_dkpxmdb";
		String realTable = "czxx_dkpxmdb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		boolean reslut = false;

		// 保存党课名单
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			String pxsj = request.getParameter("gzkssj");

			if (checkVal != null && checkVal.length > 0) {

				String pk = "pxmdxh||pxsj";
				String[] arrzd = new String[] { "pxmdxh" };
				String[] onezd = new String[] { "pxsj","xn" };
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
				model.setXn(Base.currXn);

				reslut = service.saveDyxx(saveForm, model);
				model.setXn(null);
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
		tableName = "view_czxx_dksjb";
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		
		return mapping.findForward("dkmdManage");
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

			if (!Base.isNull(pxsj)) {
				colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc", "xxtd","xxjl","xxxg" };
				tableName = "view_gdby_dksjb";
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
					String[] arrzd = new String[] { "pxmdxh", "xxtd","xxjl","xxxg" };
					onezd = new String[] { "pxsj","xn" };

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
		request.setAttribute("xn",Base.currXn);
		return mapping.findForward("pxxxUpdate");
	}
	
	/**
	 * 培训信息维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward pxxxCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		DtjsGdbyService service = new DtjsGdbyService();
		
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxModel model = new DyxxModel();
		BeanUtils.copyProperties(model, myForm);
		
		String xn = StringUtils.isNull(model.getXn()) ? Base.currXn : model.getXn();
		Map<String, String> map = service.getCjbl(xn);
		
		
		if("save".equalsIgnoreCase(doType)){
			boolean isflag = service.saveCjbl(model);
			request.setAttribute("result", isflag);
		}
		
		request.setAttribute("rs", map);
		request.setAttribute("xn", xn);
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("pxxxCssz");
	}
	
}
