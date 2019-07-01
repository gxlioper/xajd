package xgxt.rcsw.zjjs.rwzb;

import java.io.File;
import java.io.FileOutputStream;
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
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.comm.FileManage;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.dtjs.czxx.dyxx.DyxxService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;
import xsgzgl.pjpy.szgyyq.model.FivesModel;
import xsgzgl.pjpy.szgyyq.model.XsssModel;
import xsgzgl.pjpy.szgyyq.model.XstsModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjInit;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuService;

import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(老师)_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class ZjjsRwzbAction extends BasicAction {
	
	/**
	 * 正式党员管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward rwzbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);// 用户对象

		ZjjsRwzbForm myForm = (ZjjsRwzbForm) form;
		ZjjsRwzbService service = new ZjjsRwzbService();

		String doType = request.getParameter("doType");
		// 用户身份
		String userType = user.getUserStatus();
		// 用户名
		String userName = user.getUserName();
		// 用户详
		String userDep = user.getUserDep();

		String tableName = "xg_view_pjpy_zjjs_rwzb";
		String realTable = "xg_zjjs_rcsw_zprwwhb";
		boolean result = false;

		String[] colList = new String[] { "pk", "xh", "xm", "xb", 
				"nj","xymc","zymc","bjmc","sfjmxf", "bzje","rwlx" };

		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);

		// 批量删除正式党员
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delInfoInDb(saveForm, myForm, user);
				request.setAttribute("result", result);
			}
		}

		// 点击查询按钮进行查询
//		if (((request.getParameter("go") != null) && request.getParameter("go")
//				.equalsIgnoreCase("go"))
//				|| result) {

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rsArrList = service.getRwzbList(tableName, myForm, colList);

//		} else {
//			myForm.getPages().setMaxPage(1);
//		}

		request.setAttribute("path", "rcsw_zjjs_rwzb.do");
		request.setAttribute("userType", userType);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
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

		return mapping.findForward("rwzbManage");
	}
	
	public ActionForward rwzbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "日常事务 - 入伍征兵";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "xg_view_pjpy_zjjs_rwzb";
		String realTable = "xg_zjjs_rcsw_zprwwhb";
		doType = Base.isNull(doType) ? "add" : doType;

		ZjjsRwzbForm myForm = (ZjjsRwzbForm) form;
		ZjjsRwzbService service = new ZjjsRwzbService();

		HashMap<String, String> rs = new HashMap<String, String>();
		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "sfjmxf", "bzje",
					"bz" ,"rwlx"};
			rs = service.getRwzbInfo(tableName, pk, pkValue, colList);
		}

		// 保存正式党员
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xh", "bzje", "sfjmxf", "bz","rwlx" };
			
			pk = "xh";
			pkValue = myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveInfoToDb(saveForm, myForm, request);
			request.setAttribute("result", result);
			
		}

		myForm.setXydm(rs.get("xydm"));

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("rwzbUpdate");
	}
	
	/**
	 * 正式党员管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward rwzbFdyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);// 用户对象

		ZjjsRwzbForm myForm = (ZjjsRwzbForm) form;
		ZjjsRwzbService service = new ZjjsRwzbService();

		String doType = request.getParameter("doType");
		// 用户身份
		String userType = user.getUserStatus();
		// 用户名
		String userName = user.getUserName();
		// 用户详
		String userDep = user.getUserDep();

		String tableName = "xg_view_pjpy_zjjs_rwzb";
		String realTable = "xg_zjjs_rcsw_zprwwhb";
		boolean result = false;

		String[] colList = new String[] { "pk", "xh", "xm", "xb", "nj",
				"xymc","zymc","bjmc","sfjmxf", "bzje","rwlx" };

		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);

		// 批量删除正式党员
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delInfoInDb(saveForm, myForm, user);
				request.setAttribute("result", result);
			}
		}

		// 点击查询按钮进行查询
//		if (((request.getParameter("go") != null) && request.getParameter("go")
//				.equalsIgnoreCase("go"))
//				|| result) {

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rsArrList = service.getRwzbList(tableName, myForm, colList);

//		} else {
//			myForm.getPages().setMaxPage(1);
//		}

		request.setAttribute("path", "rcsw_zjjs_rwzb.do");
		request.setAttribute("userType", userType);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
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

		return mapping.findForward("rwzbFdyManage");
	}
	
	public ActionForward rwzbFdyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "日常事务 - 入伍征兵";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "xg_view_pjpy_zjjs_rwzb";
		String realTable = "xg_zjjs_rcsw_zprwwhb";
		doType = Base.isNull(doType) ? "add" : doType;

		ZjjsRwzbForm myForm = (ZjjsRwzbForm) form;
		ZjjsRwzbService service = new ZjjsRwzbService();

		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "sfjmxf", "bzje",
					"bz","rwlx" };
			rs = service.getRwzbInfo(tableName, pk, pkValue, colList);
		}

		// 保存正式党员
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xh", "bzje", "sfjmxf", "bz" };
			
			pk = "xh";
			pkValue = myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveInfoToDb(saveForm, myForm, request);
			request.setAttribute("result", result);
			
		}

		myForm.setXydm(rs.get("xydm"));

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("rwzbFdyUpdate");
	}
}
