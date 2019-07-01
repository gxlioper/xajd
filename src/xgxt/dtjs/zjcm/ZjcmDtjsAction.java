package xgxt.dtjs.zjcm;

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
import xgxt.dtjs.DtjsForm;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.dtjs.czxx.dyxx.DyxxService;
import xgxt.form.SaveForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.xsgygl.GyglTyForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 浙江传媒学院学生信息管理党团建设-action类
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

public class ZjcmDtjsAction extends DispatchAction {

	/**
	 * 培训信息管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward pxxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjsForm myForm = (DtjsForm) form;
		ZjcmDtjsService service = new ZjcmDtjsService();
		ZjcmDtjsModel model = new ZjcmDtjsModel();

		String doType = request.getParameter("doType");
		String tableName = "view_xspxxx";
		String realTable = "xspxxxb";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
	
		BeanUtils.copyProperties(model, myForm);

		// 批量删除培训信息
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||xh||pxxmdm";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDtjs(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "nd", "xn", "xqmc", "xh",
					"xm", "xb", "bjmc", "pxxmmc", "pxkssj", "pxjssj", "pxjg",
					"zsyw" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDtjsList(tableName, model, colList,"");

		}
		// 初始化列表值
		service.setList(myForm, request, "pxxx");
		
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

		String title = "党团建设 - 数据维护 - 培训信息";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_xspxxx";
		String realTable = "xspxxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		DtjsForm myForm = (DtjsForm) form;
		ZjcmDtjsService service = new ZjcmDtjsService();
		ZjcmDtjsModel model = new ZjcmDtjsModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xn", "xq", "nd", "xh", "xm",
					"xb", "nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
					"pxxmdm", "pxkssj", "pxjssj", "pxjg", "zsyw", "bz" };
			rs = service.getDtjsInfo(tableName, pk, pkValue, colList);
		}
		
		// 保存培训信息
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "nd", "xn", "xq", "xh", "pxxmdm",
					"pxkssj", "pxjssj", "pxjg", "zsyw", "bz" };
			pk = "xn||xq||xh||pxxmdm";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh() + myForm.getPxxmdm();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDtjs(saveForm, model, request);
			request.setAttribute("result", result);
		}

		//	初始化列表值
		service.setList(myForm, request, "pxxx");

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("pxxxUpdate");
	}

	/**
	 * 发展对象管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fzdxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjsForm myForm = (DtjsForm) form;
		ZjcmDtjsService service = new ZjcmDtjsService();
		ZjcmDtjsModel model = new ZjcmDtjsModel();

		String doType = request.getParameter("doType");
		String tableName = "view_fzdx";
		String realTable = "fzdx";
		String[] checkVal = myForm.getCheckVal();
		String csh = request.getParameter("csh");
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
	
		BeanUtils.copyProperties(model, myForm);

		// 初始化数据，在职状态为yes
		if (Base.isNull(csh)) {
			myForm.setZzzt("yes");
			csh = "no";
		}
	
		// 批量删除发展对象
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||xh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDtjs(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		// 发展对象 --> 预备党员
		if (!Base.isNull(doType) && "zz".equalsIgnoreCase(doType)) {
			//判断申请者是否有人没有培训证书
			if(service.isPxzs(checkVal)){
				String message = "提交者中有人没有培训证书，请确定";
				result = true;
				request.setAttribute("message", message);
			}else{
				result = service.changeDylx(checkVal, "发展对象", request);
				request.setAttribute("result", result);
			}
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "nd", "xn", "xqmc", "xh",
					"xm", "xb", "bjmc", "kssj", "jssj" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getFzdxList(tableName, model, colList);

		}
		// 初始化列表值
		service.setList(myForm, request, "fzdx");
		
		request.setAttribute("path", "dtjs_fzdx.do");
		request.setAttribute("csh", csh);
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

		String title = "党团建设 - 数据维护 - 发展对象";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_fzdx";
		String realTable = "fzdx";
		doType = Base.isNull(doType) ? "add" : doType;

		DtjsForm myForm = (DtjsForm) form;
		ZjcmDtjsService service = new ZjcmDtjsService();
		ZjcmDtjsModel model = new ZjcmDtjsModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xn", "xq", "nd", "xh", "xm",
					"xb", "nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
					"kssj", "jssj", "bz" };
			rs = service.getDtjsInfo(tableName, pk, pkValue, colList);
		}
		
		// 保存发展对象
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "nd", "xn", "xq", "xh", "kssj",
					"jssj", "bz" };
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDtjs(saveForm, model, request);
			request.setAttribute("result", result);
		}

		//	初始化列表值
		service.setList(myForm, request, "fzdx");

		if(!Base.isNull(rs.get("xh"))){
			model= new ZjcmDtjsModel();
			model.setXh(rs.get("xh"));
			tableName="view_xspxxx";
			String[] colList = new String[] { "xn","xqmc","nd","pxxmmc","pxkssj","pxjssj","pxjg","zsyw" };
			ArrayList<String[]> rsList = service.getDtjsList(tableName, model, colList,"");
			if (rsList != null && rsList.size() > 0) {
				List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName, colList, null);
				request.setAttribute("topTr", topTr);
				request.setAttribute("rsList", rsList);
				request.setAttribute("rsNum", rsList.size());
			}
		}
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("fzdxUpdate");
	}
}
