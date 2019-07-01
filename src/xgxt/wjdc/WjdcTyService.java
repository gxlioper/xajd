package xgxt.wjdc;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import common.Globals;

import xgxt.DAO.DAO;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.xsgygl.GyglTyDAO;

public class WjdcTyService {

	WjdcTyDAO dao = new WjdcTyDAO();

	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(WjdcForm myForm, HttpServletRequest request, String menu)
			throws Exception {

		// =============通用================
		FormModleCommon.setNdXnXqList(request);// 年度学年学期
		FormModleCommon.requestSetList(new String[] { "bm", "zzmm" }, request);// 自定义(目前：部门代码,政治面貌)
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// 年级学院专业班级

		// 是否列表
		List<HashMap<String, String>> sfList = dao.getSelectList("sflx");
		request.setAttribute("sfList", sfList);

		// ===========================问卷调查 begin==============================
		if ("wjdc".equalsIgnoreCase(menu)) {

			String mklx = myForm.getQueryequals_mklx();
			
			// 试题类型列表
			List<HashMap<String, String>> stlxList = dao.getWjdcList(
					"wjdc_stlxb", "dm", "mc", "", "", "");
			request.setAttribute("stlxList", stlxList);

			// 有无列表
			List<HashMap<String, String>> ywlxList = dao.getSelectList("ywlx");
			request.setAttribute("ywlxList", ywlxList);

			// 试题类型列表
			List<HashMap<String, String>> wjList = dao.getWjdcList(
					"wjdc_wjxxb", "id", "wjmc", "", "mklx", mklx);
			request.setAttribute("wjList", wjList);

			// 试题所属
			List<HashMap<String, String>> stssList = dao.getWjdcList(
					"wjdc_stssb", "dm", "mc", "", "", "");
			request.setAttribute("stssList", stssList);

			// 题库列表
			List<HashMap<String, String>> tkList = dao.getWjdcList(
					"view_wjdc_stxx", "stbh", "xsmc", "", "", "");
			request.setAttribute("tkList", tkList);

			// 完成列表
			List<HashMap<String, String>> overList = dao.getSelectList("over");
			request.setAttribute("overList", overList);

			// 是否组卷
			List<HashMap<String, String>> zzList = dao.getSelectList("zz");
			request.setAttribute("zzList", zzList);

			// 性别列表
			List<HashMap<String, String>> xbList = dao.getSelectList("xblx");
			request.setAttribute("xbList", xbList);
			
			// 模块类型
			List<HashMap<String, String>> mklxList = dao.getSelectList("mklx");
			request.setAttribute("mklxList", mklxList);
		}
		// ============================问卷调查 begin==============================

	}

	/**
	 * 获得问卷调查信息（列表）
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getWjdcList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getWjdcListInfo(tableName, model, colList, other_query);
	}

	/**
	 * 获得问卷调查相关信息（详细）
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 */
	public HashMap<String, String> getWjdcInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getWjdcInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * 删除问卷调查信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @throws Exception
	 */
	public boolean delWjdc(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();

		return dao.delDate(form, model);
	}

	/**
	 * 保存问卷调查相关信息（批量）
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param arrzd(批量字段)
	 * @param onezd(单一字段)
	 * @param notnull(非空字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveWjdc(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * 保存问卷调查相关信息（单条）
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveWjdc(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * 更新问卷调查相关信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean updateWjdc(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}

	/**
	 * @describe 删除所上传文件
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param dzzd(地址字段)
	 * 
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String dzzd, String pk,
			String pkValue) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.fileDel(tableName, dzzd, pk, pkValue);
	}

	/**
	 * 获得系统当前时间
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		DAO dao = DAO.getInstance();
		return dao.getNowTime(lx);
	}

	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		return dao.getOneValue(tableName, dm, pk, pkValue);
	}

	/**
	 * 设置Request 的值
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setRequestValue(RequestForm rForm, HttpServletRequest request) {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String writeAble = request.getParameter("writeAble");
		String title = rForm.getTitle();
		
		// 视图名
		String tableName = rForm.getTableName();
		if (!Base.isNull(tableName)) {
			request.setAttribute("tableName", tableName);
		}

		// 表名
		String realTable = rForm.getRealTable();
		if (!Base.isNull(realTable)) {
			request.setAttribute("realTable", realTable);
		}

		// 模块路径
		String path = rForm.getPath();
		if (!Base.isNull(path)) {
			request.setAttribute("path", path);
		}

		// 用户类型
		userType = Base.isNull(userType) ? rForm.getUserType() : userType;
		if (!Base.isNull(userType)) {
			request.setAttribute("userType", userType);
		}

		// 用户名
		userName = Base.isNull(userName) ? rForm.getUserName() : userName;
		if (!Base.isNull(path)) {
			request.setAttribute("userName", userName);
		}

		// 用户所在部门
		userDep = Base.isNull(userDep) ? rForm.getUserDep() : userDep;
		if (!Base.isNull(path)) {
			request.setAttribute("userDep", userDep);
		}

		// 操作类型
		doType = Base.isNull(doType) ? rForm.getDoType() : doType;
		if (!Base.isNull(doType)) {
			request.setAttribute("doType", doType);
		}

		// 读写权相关
		if (Base.isNull(writeAble)) {
			String[] message = FormModleCommon.getWriteAbleAndTitle(request);
			writeAble = message != null && message.length >= 1 ? message[0]
					: "";
			if (Base.isNull(title)) {
				title = message != null && message.length >= 2 ? message[1]
						: "";
			}
		}
		request.setAttribute("writeAble", writeAble);

		// 模块标题
		if (!Base.isNull(title)) {
			request.setAttribute("title", title);
		}

		// 主键
		String pk = rForm.getPk();
		if (!Base.isNull(pk)) {
			request.setAttribute("pk", pk);
		}

		// 提示信息
		String message = rForm.getMessage();
		if (!Base.isNull(message)) {
			request.setAttribute("message", message);
		}
		
		// 模塊類型
		String mklx = rForm.getMklx();
		if (!Base.isNull(mklx)) {
			request.setAttribute("mklx", mklx);
		}

		// 详细信息
		HashMap<String, String> rs = rForm.getRs();
		if (rs != null && rs.size() > 0) {
			request.setAttribute("rs", rs);
		}

		// 详细列表信息
		List<HashMap<String, String>> rsList = rForm.getRsList();
		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("rsList", rsList);
		}

		// 其他字段
		String[] qtzd = rForm.getQtzd();
		// 其他字段值
		String[] qtzdz = rForm.getQtzdz();

		if (qtzd != null && qtzdz != null && (qtzd.length == qtzdz.length)) {
			for (int i = 0; i < qtzd.length; i++) {
				request.setAttribute(qtzd[i], qtzdz[i]);
			}
		}

	}

	/**
	 * 获得问卷调查分页
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getResultList(ArrayList<String[]> list,
			WjdcForm model) {

		// 分页
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		if (list != null && list.size() > 0) {

			Pages pages = model.getPages();
			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}

		return rsList;
	}

	/**
	 * 获得问卷调查分页
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getResultList(
			List<HashMap<String, String>> list, WjdcForm model) {

		// 分页
		List<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();

		if (list != null && list.size() > 0) {

			Pages pages = model.getPages();
			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}

		return rsList;
	}

	/**
	 * 问卷调查导出
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expWjdcData(String title, List<HashMap<String, String>> topTr,
			ArrayList<String[]> list, OutputStream os) throws Exception {

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充表头
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 0, map.get("cn"), wcf2));
			}
		}

		// 填充内容
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				String[] info = list.get(i);

				if (info != null && info.length > 0) {

					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 1, info[j], wcf2));
					}
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
