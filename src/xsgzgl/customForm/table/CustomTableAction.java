package xsgzgl.customForm.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.customForm.model.TableContentModel;
import xsgzgl.customForm.model.TableModel;
import xsgzgl.customForm.model.TablePkContentModel;
import xsgzgl.customForm.model.TableSearchContentModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 自定义表带_Action类
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

public class CustomTableAction extends BasicAction {

	/**
	 * 评奖评优_我的评奖_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customTableManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		CustomTableInit init = new CustomTableInit();
		User user = getUser(request);// 用户对象
		HttpSession session = request.getSession();

		// ============= 初始化各变量的值 ============
		List<HashMap<String, String>> gnmkList = service.getGnmkList();
		request.setAttribute("gnmkList", gnmkList);
		// =================== end ===================

		return mapping.findForward("customTableManage");
	}

	/**
	 * 评奖评优_我的评奖_详细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customTableDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		CustomTableInit init = new CustomTableInit();
		User user = getUser(request);// 用户对象
		HashMap<String, String> rs = new HashMap<String, String>();

		// ============= 初始化各变量的值 ============
		// ID
		String id = request.getParameter("id");
		myForm.setId(id);
		// =================== end ===================

		// ============= 查看已维护好的表单 ============
		if (!Base.isNull(id)) {
			rs = service.getGnmkInfo(myForm, user);
		}
		// =================== end ===================

		request.setAttribute("rs", rs);

		return mapping.findForward("customTableDetail");
	}

	/**
	 * 评奖评优_我的评奖_维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customTableUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		CustomTableInit init = new CustomTableInit();
		User user = getUser(request);// 用户对象
		HttpSession session = request.getSession();

		// ============= 初始化各变量的值 ============
		String gnmk_id = request.getParameter("id");
		request.setAttribute("gnmk_id", gnmk_id);
		// =================== end ===================

		// ============= 根据用户类型判断跳转 ============

		// =================== end ===================

		return mapping.findForward("customTableUpdate");
	}

	/**
	 * 自定义表单_设置_步骤1
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customSettingStep1(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		CustomTableInit init = new CustomTableInit();
		User user = getUser(request);// 用户对象
		HttpSession session = request.getSession();

		// ============= 初始化各变量的值 ============
		String gnmk_id = request.getParameter("id");
		request.setAttribute("gnmk_id", gnmk_id);
		// =================== end ===================

		// ============= 根据用户类型判断跳转 ============

		// =================== end ===================

		return mapping.findForward("customSettingStep1");
	}

	/**
	 * 自定义表单_设置_步骤2
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customSettingStep2(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		CustomTableInit init = new CustomTableInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		String gnmk_id = request.getParameter("gnmk_id");
		request.setAttribute("gnmk_id", gnmk_id);
		// =================== end ===================

		// ============= 根据用户类型判断跳转 ============

		// =================== end ===================

		return mapping.findForward("customSettingStep2");
	}

	// =======================以下方法==============================

	// ===================查询================================

	/**
	 * 获得功能模块结果查询列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCustomGnmkList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		CustomTableInit init = new CustomTableInit();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// 功能模块代码
		String gnmkdm = otherValue[0];
		myForm.setGnmkdm(gnmkdm);

		// 功能模块名称
		String gnmkmc = otherValue[1];
		myForm.setGnmkmc(gnmkmc);

		// 审核状态
		String shzt = otherValue[2];
		myForm.setShzt(shzt);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		List<HashMap<String, String>> topTr = service.getCustomTableTop(myForm,
				user);// 标题
		ArrayList<String[]> rsArrList = service.getCustomGnmkList(myForm, user);
		String spHtml = service.getCustomGnmkHtml(rsModel, myForm, rsArrList,
				user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	// ===================查询 end================================

	// ===================功能模块详细 ================================
	/**
	 * 保存功能模块
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveGnmk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 功能模块代码
		String gnmkdm = request.getParameter("gnmkdm");
		myForm.setGnmkdm(gnmkdm);

		// 功能模块名称
		String gnmkmc = request.getParameter("gnmkmc");
		if (!Base.isNull(gnmkmc)) {
			gnmkmc = service.unicode2Gbk(gnmkmc);
		}
		myForm.setGnmkmc(gnmkmc);

		// 对应页面
		String dyym = request.getParameter("dyym");
		myForm.setDyym(dyym);

		// 审核状态
		String shzt = request.getParameter("shzt");
		if (!Base.isNull(shzt)) {
			shzt = service.unicode2Gbk(shzt);
		}
		myForm.setShzt(shzt);
		
		// 表名
		String tablename = request.getParameter("tablename");
		myForm.setTablename(tablename);
		// ============= end ============

		// ============= 保存功能模块 ============
		boolean flag = service.saveGnmk(myForm, user, request);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===================功能模块详细 end================================

	/**
	 * 获得我的审核列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showTableDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 申请ID
		String gnmk_id = request.getParameter("gnmk_id");
		// ============= 初始化各变量的值 end============

		// ==================构建前台页面========================
		service.createTable(myForm, gnmk_id, response);
		// ==================构建前台页面 end========================

		return null;
	}

	// ===================表单维护================================

	/**
	 * 检测表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getListBySource(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();

		// 表
		String tablename = request.getParameter("tablename");
		// 代码
		String dm = request.getParameter("dm");
		// 名称
		String mc = request.getParameter("mc");

		boolean flag = service.checkTableName(tablename, dm, mc, myForm);

		if (flag) {
			List<HashMap<String, String>> list = service.getListBySource(
					tablename, dm, mc, myForm);

			if (list != null && list.size() > 0) {

			} else {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("dm", "");
				map.put("mc", "");

				list.add(map);
			}
			
			response.setContentType("text/html;charset=gbk");

			response.getWriter().print(JSONArray.fromObject(list));
		} else {
			response.setContentType("text/html;charset=gbk");

			response.getWriter().print(flag);
		}

		return null;
	}

	/**
	 * 保存表单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		TableModel tableModel = new TableModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		String gnmk_id = request.getParameter("gnmk_id");
		tableModel.setGnmk_id(gnmk_id);

		// ID
		String[] id = request.getParameter("id").split("!!@@!!");
		tableModel.setId(id);

		// 标题
		String[] title = request.getParameter("title").split("!!@@!!");
		tableModel.setTitle(title);

		// 总行数
		String[] row_all = request.getParameter("row_all").split("!!@@!!");
		tableModel.setRow_all(row_all);

		myForm.setTableModel(tableModel);
		// ============= end ============

		// ============= 保存表单 ============
		boolean flag = service.saveTable(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 获取表单ID
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTableId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomTableService service = new CustomTableService();

		List<HashMap<String, String>> list = service.getTableIdList();

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * 保存表单内容
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTableContent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		TableContentModel tableContentModel = new TableContentModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// OLD ID
		String[] old_id = request.getParameter("old_id").split("!!@@!!");
		tableContentModel.setOld_id(old_id);
		// TABLE ID
		String[] table_id = request.getParameter("table_id").split("!!@@!!");
		tableContentModel.setTable_id(table_id);
		// 行
		String[] row_num = request.getParameter("row_num").split("!!@@!!");
		tableContentModel.setRow_num(row_num);
		// 列
		String[] column_num = request.getParameter("column_num")
				.split("!!@@!!");
		tableContentModel.setColumn_num(column_num);
		// 内容
		String[] content = request.getParameter("content").split("!!@@!!");
		tableContentModel.setContent(content);
		// 宽度
		String[] width = request.getParameter("width").split("!!@@!!");
		tableContentModel.setWidth(width);
		// 文本域行数
		String[] textarea_rows = request.getParameter("textarea_rows").split(
				"!!@@!!");
		tableContentModel.setTextarea_rows(textarea_rows);
		// 后缀
		String[] postfix = request.getParameter("postfix").split("!!@@!!");
		tableContentModel.setPostfix(postfix);
		// 数据表
		String[] source_table = request.getParameter("source_table").split(
				"!!@@!!");
		tableContentModel.setSource_table(source_table);
		// 代码
		String[] select_dm = request.getParameter("select_dm").split("!!@@!!");
		tableContentModel.setSelect_dm(select_dm);
		// 名称
		String[] select_mc = request.getParameter("select_mc").split("!!@@!!");
		tableContentModel.setSelect_mc(select_mc);
		// 验证
		String[] checked = request.getParameter("checked").split("!!@@!!");
		tableContentModel.setChecked(checked);
		
		myForm.setTableContentModel(tableContentModel);
		// ============= end ============

		// ============= 保存表单 ============
		boolean flag = service.saveTableContent(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===================功能模块设置================================

	/**
	 * 显示字段设置Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showZdxzDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();

		// ============= 初始化各变量的值 ============
		// 功能模块ID
		String gnmk_id = request.getParameter("gnmk_id");
		myForm.setId(gnmk_id);
		// =================== end ===================

		// ==================构建前台页面========================
		service.showZdxzDiv(myForm, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 显示字段设置Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showZjqrDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();

		// ============= 初始化各变量的值 ============
		// 功能模块ID
		String gnmk_id = request.getParameter("gnmk_id");
		myForm.setId(gnmk_id);
		// =================== end ===================

		// ==================构建前台页面========================
		service.showZjqrDiv(myForm, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 保存查询内容
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTableSearchContent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		TableSearchContentModel tableSearchContentModel = new TableSearchContentModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 功能模块ID
		String gnmk_id = request.getParameter("gnmk_id");
		tableSearchContentModel.setGnmk_id(gnmk_id);

		// 内容ID
		String[] content_id = request.getParameter("content_id")
				.split("!!@@!!");
		tableSearchContentModel.setContent_id(content_id);

		// 显示顺序
		String[] xssx = request.getParameter("xssx").split("!!@@!!");
		tableSearchContentModel.setXssx(xssx);

		myForm.setTableSearchContentModel(tableSearchContentModel);
		// ============= end ============

		// ============= 保存表单 ============
		boolean flag = service.saveTableSearchContent(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 保存主键内容
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTablePkContent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		TablePkContentModel tablePkContentModel = new TablePkContentModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 功能模块ID
		String gnmk_id = request.getParameter("gnmk_id");
		tablePkContentModel.setGnmk_id(gnmk_id);

		// 主键ID
		String[] pk_id = request.getParameter("pk_id").split("!!@@!!");
		tablePkContentModel.setPk_id(pk_id);

		// 显示顺序
		String[] xssx = request.getParameter("xssx").split("!!@@!!");
		tablePkContentModel.setXssx(xssx);

		myForm.setTablePkContentModel(tablePkContentModel);
		// ============= end ============

		// ============= 保存表单 ============
		boolean flag = service.saveTablePkContent(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===================功能模块设置 end============================

}
