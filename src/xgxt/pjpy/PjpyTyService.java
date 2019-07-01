package xgxt.pjpy;

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

public class PjpyTyService {

	PjpyTyDAO dao = new PjpyTyDAO();

	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(PjpyTyForm myForm, HttpServletRequest request,
			String menu) throws Exception {

		//参数
		String xn =myForm.getQueryequals_xn();
		String xq =myForm.getQueryequals_xq();
		String nd =myForm.getQueryequals_nd();
		String pk = "";
		String pkValue = "";
		
		// =============通用================
		FormModleCommon.setNdXnXqList(request);// 年度学年学期
		FormModleCommon.requestSetList(new String[] { "bm", "zzmm" }, request);// 自定义(目前：部门代码,政治面貌)
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// 年级学院专业班级

		// 是否列表
		List<HashMap<String, String>> sfList = dao.getSelectList("sflx");
		request.setAttribute("sfList", sfList);

		// ============= 智育分维护================
		if ("zyf".equalsIgnoreCase(menu)) {

			// 培养层次
			List<HashMap<String, String>> pyccList = dao.getPjpyList(
					"view_xsxxb", "pycc", "pycc", "", "", "");
			request.setAttribute("pyccList", pyccList);
		}
		// ============= 报表统计================
		else if ("bbtj".equalsIgnoreCase(menu)) {

			// 报表类型
			List<HashMap<String, String>> bblxList = dao.getSelectList("bblx");
			request.setAttribute("bblxList", bblxList);

			// 奖学金类别
			List<HashMap<String, String>> jxjlbList = dao.getPjpyList(
					"jxjlbdmb", "jxjlbdm", "jxjlbmc", "", "", "");
			request.setAttribute("jxjlbList", jxjlbList);

			// 荣誉称号
			List<HashMap<String, String>> rychList = dao.getPjpyList("rychdmb",
					"rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);

			// 银行列表
			List<HashMap<String, String>> yhList = dao.getPjpyList("dmk_yh",
					"yhdm", "yhmc", "", "", "");
			request.setAttribute("yhList", yhList);

		}
		// =============文理科维护================
		else if ("wlk".equalsIgnoreCase(menu)) {

			// 文理科类型
			List<HashMap<String, String>> wlkList = dao.getPjpyList(
					"zjcm_wlkdmb", "dm", "mc", "", "", "");
			request.setAttribute("wlkList", wlkList);
		}
		// =============条件设置================
		else if ("tjsz".equalsIgnoreCase(menu)) {

			if (Globals.XXDM_ZJCMXY.equals(Base.xxdm)) {
				// 校内奖学金列表
				List<HashMap<String, String>> jxjList = dao.getJxjList("校内");
				request.setAttribute("jxjList", jxjList);
				request.setAttribute("all", "no");
			} else {
				List<HashMap<String, String>> jxjList = dao.getPjpyList(
						"jxjdmb", "jxjdm", "jxjmc", "", "", "");
				request.setAttribute("jxjList", jxjList);
				request.setAttribute("all", "yes");
			}

			// 荣誉称号列表
			List<HashMap<String, String>> rychList = dao.getPjpyList("rychdmb",
					"rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);

			// 条件列表
			List<HashMap<String, String>> tjList = dao.getTjList();
			// dao.getPjpyList("jxjtjzdb","zdmc", "zdsm", "", "", "");
			request.setAttribute("tjList", tjList);

			// 条件类型列表
			List<HashMap<String, String>> tjlxList = dao.getSelectList("tjlx");
			request.setAttribute("tjlxList", tjlxList);

			// 文理科类型
			List<HashMap<String, String>> wlkList = dao.getPjpyList(
					"zjcm_wlkdmb", "dm", "mc", "", "", "");
			request.setAttribute("wlkList", wlkList);

		}
		// =============测评小组================
		else if ("cpxz".equalsIgnoreCase(menu)) {

			// 开关列表
			List<HashMap<String, String>> kgList = dao.getSelectList("kglx");
			request.setAttribute("kgList", kgList);

			// 职务列表
			List<HashMap<String, String>> zwList = dao.getPjpyList(
					"sxjy_bjgbzlb", "bjgbdm", "bjgbmc", "", "", "");
			request.setAttribute("zwList", zwList);

		}
		// =============校内奖学金================
		else if ("jxjxn".equalsIgnoreCase(menu)) {

			// 校内奖学金列表
			List<HashMap<String, String>> jxjList = dao.getJxjList("校内");
			request.setAttribute("jxjList", jxjList);

			// 校外奖学金列表
			List<HashMap<String, String>> xwjxjList = dao.getJxjList("校外");
			request.setAttribute("xwjxjList", xwjxjList);

			// 培养层次
			List<HashMap<String, String>> pyccList = dao.getPjpyList(
					"view_xsxxb", "pycc", "pycc", "", "", "");
			request.setAttribute("pyccList", pyccList);

			// 文理科类型
			List<HashMap<String, String>> wlkList = dao.getPjpyList(
					"zjcm_wlkdmb", "dm", "mc", "", "", "");
			request.setAttribute("wlkList", wlkList);

		}
		// =============校外奖学金================
		else if ("jxjxw".equalsIgnoreCase(menu)) {

			// 校内奖学金列表
			List<HashMap<String, String>> jxjList = dao.getJxjList("校外");
			request.setAttribute("jxjList", jxjList);

		}
		// =============荣誉称号================
		else if ("rych".equalsIgnoreCase(menu)) {

			// 荣誉称号列表
			List<HashMap<String, String>> rychList = dao.getPjpyList("rychdmb",
					"rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);

		}
		// =============奖学金================
		else if ("jxj".equalsIgnoreCase(menu)) {

			// 奖学金列表
			List<HashMap<String, String>> jxjList = dao.getPjpyList("jxjdmb",
					"jxjdm", "jxjmc", "", "", "");			
			request.setAttribute("jxjList", jxjList);

		}
		// =============申报结果================
		else if ("sbjg".equalsIgnoreCase(menu)) {

			// 荣誉称号列表
			List<HashMap<String, String>> rychList = dao.getPjpyList("rychdmb",
					"rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);

			// 校内奖学金列表
			List<HashMap<String, String>> jxjList = dao.getPjpyList("jxjdmb",
					"jxjdm", "jxjmc", "", "", "");
			request.setAttribute("jxjList", jxjList);
		}
		// =============兼得设置================
		else if ("jdsz".equalsIgnoreCase(menu)) {

			// 荣誉称号列表
			List<HashMap<String, String>> rychList = dao.getPjpyList("rychdmb",
					"rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);

			// 奖学金列表
			List<HashMap<String, String>> jxjList = dao.getPjpyList("jxjdmb",
					"jxjdm", "jxjmc", "", "", "");
			request.setAttribute("jxjList", jxjList);

			// 类型列表（奖学金或者荣誉称号）
			List<HashMap<String, String>> lxList = dao
					.getSelectList("jxjorrych");
			request.setAttribute("lxList", lxList);

		}
		// ===========================操行分 begin==============================
		else if ("cxf".equalsIgnoreCase(menu)) {

			GyglTyDAO gyDao = new GyglTyDAO();
			String lddm = myForm.getLddm();
			String cs = myForm.getCs();

			// 校区列表
			List<HashMap<String, String>> xqdmList = gyDao.getGyglList(
					"dm_zju_xq", "dm", "xqmc", "", "", "");
			request.setAttribute("xqdmList", xqdmList);

			// 楼栋列表
			List<HashMap<String, String>> ldList = gyDao.getGyglList("sslddmb",
					"lddm", "ldmc", "", "", "");
			request.setAttribute("ldList", ldList);

			// 层数列表
			List<HashMap<String, String>> csList = gyDao.getCsList(lddm);
			request.setAttribute("csList", csList);

			// 寝室列表
			List<HashMap<String, String>> qsList = gyDao
					.getQsList(lddm, cs, "");
			request.setAttribute("qsList", qsList);

			// 区域列表
			List<HashMap<String, String>> qyList = dao.getPjpyList(
					"zjjt_cxf_dj1", "dm", "mc", "", "", "");
			request.setAttribute("qyList", qyList);

			// 类别列表
			List<HashMap<String, String>> lbList = dao.getPjpyList(
					"zjjt_cxf_dj2", "dm", "mc", "", "", "");
			request.setAttribute("lbList", lbList);

			// 细项列表
			String lb = myForm.getLb();
			String ejdm = !Base.isNull(lb) ? "" : "ejdm";
			List<HashMap<String, String>> xxList = dao.getPjpyList(
					"zjjt_cxf_dj3", "dm", "mc", "", ejdm, lb);
			request.setAttribute("xxList", xxList);
		}
		// ===========================操行分 end==============================

		// ===========================人数设置 begin==============================
		else if ("rssz".equalsIgnoreCase(menu)) {

			// 类型
			List<HashMap<String, String>> lxList = dao
					.getSelectList("jxjorrych");
			request.setAttribute("lxList", lxList);

			// 奖学金列表
			List<HashMap<String, String>> jxjList = dao.getPjpyList("jxjdmb",
					"jxjdm", "jxjmc", "", "", "");
			request.setAttribute("jxjList", jxjList);

			// 荣誉称号列表
			List<HashMap<String, String>> rychList = dao.getPjpyList("rychdmb",
					"rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);
		}
		// ===========================人数设置 end==============================

		// ===========================问卷调查 begin==============================
		else if ("wjdc".equalsIgnoreCase(menu)) {

			// 试题类型列表
			List<HashMap<String, String>> stlxList = dao.getPjpyList(
					"wjdc_stlxb", "dm", "mc", "", "", "");
			request.setAttribute("stlxList", stlxList);

			// 有无列表
			List<HashMap<String, String>> ywlxList = dao.getSelectList("ywlx");
			request.setAttribute("ywlxList", ywlxList);

			// 试题类型列表
			if (!Base.isNull(xn) && !Base.isNull(xq) && !Base.isNull(nd)) {
				pk = "xn||xq||nd";
				pkValue = xn + xq + nd;
			}
			List<HashMap<String, String>> wjList = dao.getPjpyList(
					"wjdc_wjxxb", "id", "wjmc", "", pk, pkValue);
			pk = "";
			pkValue = "";
			request.setAttribute("wjList", wjList);

			// 试题所属
			List<HashMap<String, String>> stssList = dao.getPjpyList(
					"wjdc_stssb", "dm", "mc", "", "", "");
			request.setAttribute("stssList", stssList);

			// 题库列表
			List<HashMap<String, String>> tkList = dao.getPjpyList(
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
		}
		// ============================问卷调查 begin==============================

		// ===========================品行评价 begin==============================
		else if ("pxpj".equalsIgnoreCase(menu)) {
			// 试题类型列表
			List<HashMap<String, String>> wjList = dao.getPjpyList(
					"wjdc_wjxxb", "id", "wjmc", "", "", "");
			request.setAttribute("wjList", wjList);
			// 性别列表
			List<HashMap<String, String>> fpList = dao.getSelectList("fplx");
			request.setAttribute("fpList", fpList);
		}
		// ============================品行评价 begin==============================
	}

	/**
	 * 获得学生信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * 获得学生信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDetStuInfo(String xh) {
		return CommonQueryDAO.getDetStuInfo(xh);
	}

	/**
	 * 获得评奖评优信息（列表）
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getPjpyList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getPjpyListInfo(tableName, model, colList, other_query);
	}
	
	/**
	 * 获得评奖评优信息（列表）
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getPjpyList(PjpyTyForm model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getPjpyListInfo(model, colList, other_query);
	}

	/**
	 * 获得评奖评优相关信息（详细）
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 */
	public HashMap<String, String> getPjpyInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getPjpyInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * 删除评奖评优信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @throws Exception
	 */
	public boolean delPjpy(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();

		return dao.delDate(form, model);
	}

	/**
	 * 保存评奖评优相关信息（批量）
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
	public boolean savePjpy(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * 保存评奖评优相关信息（单条）
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
	public boolean savePjpy(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * 更新评奖评优相关信息
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
	public boolean updatePjpy(SaveForm form, Object model) throws Exception {
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
	 * 检查两数组重复数据，删去重复部分,返回不重复部分
	 * 
	 * @author luojw
	 */
	public String[] checkCfsj(String[] first, String[] second) {

		if (first != null && first.length > 0 && second != null
				&& second.length > 0) {
			String[] arr = null;
			List<String> fir = new ArrayList<String>(Arrays.asList(first));
			List<String> sec = Arrays.asList(second);

			fir.removeAll(sec);

			List<String> list = new ArrayList<String>(fir);

			if (list != null && list.size() > 0) {
				arr = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					arr[i] = list.get(i);
				}
			}
			return arr;
		} else {
			return first;
		}
		// int n = 0;
		// String[] arr = null;
		// if (first != null && first.length > 0 && second != null
		// && second.length > 0) {
		// for (int i = 0; i < first.length; i++) {
		// if (Base.isNull(first[i])) {
		// n++;
		// } else {
		// for (int j = 0; j < second.length; j++) {
		// if (first[i].equalsIgnoreCase(second[j])) {
		// first[i] = "";
		// n++;
		// }
		// }
		// }
		// }
		// arr = new String[first.length - n];
		// n = 0;
		// for (int i = 0; i < first.length; i++) {
		// if (!Base.isNull(first[i])) {
		// arr[n] = first[i];
		// n++;
		// }
		// }
		// }
	}

	/**
	 * 获得指定学院（专业，班级）学生学号
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public String[] getZdXh(String lx, String dm) throws SQLException {
		return dao.getZdXh(lx, dm);
	}

	/**
	 * 查询银行类别列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> queryYhlxList() {
		return dao.queryYhlxList();
	}

	/**
	 * 查询银行类别列表
	 * 
	 * @return
	 */
	public Boolean getNeedXq(String xxdm) {

		boolean flag = false;

		// 评奖需要学期的学校
		// 目前包括：
		// 传媒学院
		String[] needXq = new String[] { Globals.XXDM_ZJCMXY,Globals.XXDM_NTZYDX };

		if (needXq != null && needXq.length > 0) {
			for (int i = 0; i < needXq.length; i++) {
				if (xxdm.equalsIgnoreCase(needXq[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
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
	 * 获得评奖评优分页
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getResultList(ArrayList<String[]> list,
			PjpyTyForm model) {

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
	 * 获得评奖评优分页
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getResultList(
			List<HashMap<String, String>> list, PjpyTyForm model) {

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
	 * 评奖评优导出
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expPjpyData(String title, List<HashMap<String, String>> topTr,
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
	
	/**
	 * 获取评奖周期
	 * @return String
	 * */
	public String getPjpySqzq (){
		DAO dao = DAO.getInstance();
		String sqzq = "";//申请周期
		String sql = "select * from pjpy_pjzqb ";
		HashMap<String, String> map = dao.getMapNotOut(sql, new String[]{});
		if("checked".equalsIgnoreCase(map.get("xq"))){
			sqzq = "xq";
		}else if("checked".equalsIgnoreCase(map.get("nd"))){
			sqzq = "nd";
		}else if("checked".equalsIgnoreCase(map.get("xn"))){
			sqzq = "xn";
		}
		return sqzq;
	}
	
	/**
	 * 获取综合测评周期
	 * @return String
	 * */
	public String getZhcpSqzq (){
		DAO dao = DAO.getInstance();
		String sqzq = "";//申请周期
		String sql = "select * from pjpy_pjzqb ";
		HashMap<String, String> map = dao.getMapNotOut(sql, new String[]{});
		if("checked".equalsIgnoreCase(map.get("zcxq"))){
			sqzq = "xq";
		}else if("checked".equalsIgnoreCase(map.get("zcnd"))){
			sqzq = "nd";
		}else if("checked".equalsIgnoreCase(map.get("zcxn"))){
			sqzq = "xn";
		}
		return sqzq;
	}
	
	/**
	 * 获取评奖周期字段
	 * @param pjzq
	 * @return String[]
	 * */
	public String[] getPjzqzd(String pjzq){
		String[] pjzqzd = {};
		if("xn".equalsIgnoreCase(pjzq)){
			pjzqzd = new String[]{"xn"};
		}else if("nd".equalsIgnoreCase(pjzq)){
			pjzqzd = new String[]{"nd"};
		}else if("xq".equalsIgnoreCase(pjzq)){
			pjzqzd = new String[]{"xn","xqmc"};
		}
		
		return pjzqzd;
	}
	
	/**
	 * 判断功能是否判断开关限制
	 * @return boolean
	 * */
	public boolean checkKgflag(){
		boolean flag = false;
		if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			//浙江科技
			flag = true;
		}else if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			//南通职业
			flag = true;
		}
		
		return flag;
	}
}
