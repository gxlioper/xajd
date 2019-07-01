package xgxt.xszz.ynys;

/**
 * Title: 学生工作管理系统
 * Description: 云南艺术学院学生资助Service
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: 梁超
 * Version: 1.0
 * Time: 2008年10月9日
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.utils.String.StringUtils;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;

public class XszzYnysService {
	private static DAO comDao = DAO.getInstance();
	
	XszzYnysDAO dao = null;

	private static XszzYnysService service = new XszzYnysService();

	public static XszzYnysService getInstance() {
		return service;
	}

	private XszzYnysService() {
	}

	private static final String[] nullArray = {};

	/** save or modi pre data */
	public boolean saveCommonInfo(XszzYnysActionForm myForm, String realTable,
			String pk) throws Exception {
		this.setCommonVar(myForm);
		boolean flag = true;
		String value = myForm.getNd() + myForm.getXh(); // nd||xh
		// 得到主键值，用于在修改保存的时候用
		String[] columns = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(realTable)));
		String[] removeColumns = new String[] { "sqsj", "xysh", "xxsh" }; // 申请时间
		// 学院审核
		// 学校审核
		// 默认值
		if (FormUtils.haveOneRecord(realTable, pk, value)) {// 已经有记录存在 delete
			removeColumns = new String[] { "sqsj" }; // 学校审核和学院审核是要根据页面传值,不能设置为默认值
			flag = comDao.runUpdate(SQL_Util.getDelSqlByPKParam(realTable, pk),
					new String[] { value });
		}
		columns = FormUtils.removeArrayElem(columns, removeColumns); // Remove
		// Elements
		if (flag) {
			flag = comDao.runUpdate(FormUtils.getInsertSql(realTable, columns),
					FormUtils.getProperties(columns, myForm));
		}
		return flag;
	}

	public void getCommonAllInfo(XszzYnysActionForm myForm, String tableName,
			String pk) throws Exception {
		String[] columnNames = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(tableName)));
		String[] rs = comDao.getOneRs(SQL_Util.getQuerySqlByPKParam(tableName,
				pk), new String[] { myForm.getPkVal() }, columnNames);
		FormUtils.setProperties(columnNames, rs, myForm);
	}

	/** <font color='red'>根据条件返回数据集</font> */
	public List<HashMap<String, String>> getCommonList(
			XszzYnysActionForm myForm, String tableName, String[] condiColumns,
			String[] outputValues) throws Exception {
		myForm.setColumnCN(comDao.arrayToList(outputValues, comDao
				.getColumnNameCN(outputValues, tableName))); // 返回对应的中文注释
		return comDao.getList(FormUtils.getNormalCondiSql(tableName,
				condiColumns, FormUtils.getProperties(condiColumns, myForm),
				outputValues), nullArray, outputValues);
	}

	/**
	 * <font color='red'>List<String[]> 这样在显示的时候比较方便，就算要加一个字段显示，不要动用jsp页面</font>
	 * 
	 * @param myForm
	 * @param tableName
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCommonVector(XszzYnysActionForm myForm,
			String tableName, String userType) throws Exception {
		String[] columns = null;
		String[] outputValues = null;
		String shlb = this.getUserTypeShZd(userType);
		if (StringUtils.isNotNull(myForm.getSqms())) { // 表明是申请的模式
			columns = new String[] { "xn", "nj", "xydm", "zydm", "bjdm", "xh",
					"xm", "nd", "xysh", "xxsh" };
			outputValues = new String[] { "nd", "xh", "nj", "xm", "xb", "xymc",
					"zymc", "bjmc", "sqsj", "xysh", "xxsh" };
			// 返回对应的中文注释
			myForm.setColumnCN(comDao.arrayToList(outputValues, comDao
					.getColumnNameCN(outputValues, tableName)));
			return comDao.rsToVator3(FormUtils.getNormalCondiSql(tableName,
					columns, FormUtils.getProperties(columns, myForm),
					outputValues), nullArray, outputValues);
		} else { // 审核模式
			// 有审核级别要在这里加上
			FormUtils.setOneProperty(shlb, myForm.getShlb(), myForm); // 将审核的值放到myForm中
			columns = new String[] { "xn", "nj", "xydm", "zydm", "bjdm", "xh",
					"xm", "nd", shlb };
			outputValues = new String[] { "nd", "xh", "nj", "xm", "xb", "xymc",
					"zymc", "bjmc", "sqsj", shlb };
			myForm.setColumnCN(comDao.arrayToList(outputValues, comDao
					.getColumnNameCN(outputValues, tableName)));
			return comDao.rsToVator3(
					// 二级审核 有颜色区分
					// FormUtils.get_SH_NormalCondiSql
					SQL_Util
							.get_SH2_CondiSql(tableName, columns, FormUtils
									.getProperties(columns, myForm),
									outputValues, shlb), nullArray,
					FormUtils.arrayCopy(outputValues,
							new String[] { "bgcolor" }));
		}
	}

	/** <font color='red'>返回用于数据导出的数据，导出字段为全部字段 </font> */
	public List<String[]> getCommonVector_Exp(XszzYnysActionForm myForm,
			String tableName, String userType) throws Exception {
		String shlb = this.getUserTypeShZd(userType);
		FormUtils.setOneProperty(shlb, myForm.getShlb(), myForm); // 将审核的值放到myForm中
		String[] columnNames = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(tableName)));
		String[] columns = new String[] { "xn", "nj", "xydm", "zydm", "bjdm",
				"xh", "xm", "nd", shlb };
		return comDao.rsToVator3(FormUtils.get_SH_NormalCondiSql(tableName,
				columns, FormUtils.getProperties(columns, myForm), null, shlb),
				nullArray, columnNames);
	}

	/** 批量删除 */
	public boolean batchDelRecord(String pk, String realTable, String pkColumn)
			throws Exception {
		String[] pkArray = pk.split("!!splitOne!!"); // split的方式 来自于jsp页面
		return comDao.runUpdate(SQL_Util.getDelSqlWith_InParam(realTable,
				pkColumn, pkArray.length), pkArray);
	}

	/** 返回唯一的数据集 全表扫描 */
	public List<HashMap<String, String>> getSpecialDisList(String tableName,
			String[] outputValues) {
		return comDao.getList(FormUtils.getNormalCondiSql_Distin(tableName,
				nullArray, nullArray, outputValues), nullArray, outputValues);
	}

	/** 返回学校的名称 */
	public String getXXmc() {
		return comDao.getOneRs("select xxmc from dmk_xx where xxdm=?",
				new String[] { comDao.getXxdm() }, new String[] { "xxmc" })[0];
	}

	/** 导出数据 */
	public void getCommonExp(XszzYnysActionForm myForm,
			HttpServletResponse response, HttpServletRequest request,
			String tableName, String userType) throws Exception {
		ArrayList<Object> rs = new ArrayList<Object>();
		rs.addAll(this.getCommonVector_Exp(myForm, tableName, userType));
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = comDao.getColumnNameCN(comDao
				.getColumnName(SQL_Util.getNoResultSql(tableName)), tableName);
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/** 返回条件信息 如果是学生返回Null */
	public List<HashMap<String, String>> getCheckList(String userType, int type) {
		if (!userType.equalsIgnoreCase("stu")) { // 不是学生用户
			return comDao.getChkList(type);
		}
		return null;
	}

	/** private method */
	private void setCommonVar(XszzYnysActionForm myForm) {
		myForm.setXn(Base.currXn); // 学年
		myForm.setNd(Base.currNd); // 年度
		// myForm.setXq(Base.currXq); //学期
	}

	/**
	 * 返回用户级别的审核字段 For Example : <font color='red'>例如，学院用户为xysh 学校用户为xxsh</font>
	 */
	private String getUserTypeShZd(String userType) {
		if (userType.equals("xx") || userType.equals("admin")) {
			return "xxsh";
		} else if (userType.equals("xy")) {
			return "xysh";
		}
		return "xysh";
	}
	
	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzYnysDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * 获取学费缓缴信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhjxx(String pkVal) throws Exception {
		dao = new XszzYnysDAO();
		return dao.getXfhjxx(pkVal);
	}
	
	/**
	 * 得到学费缓缴申请权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getXfhjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzYnysDAO();
		return dao.getXfhjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * 保存学费缓缴申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjSqxx(XfhjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzYnysDAO();
		return dao.saveXfhjSqxx(model, request);
	}
	
	/**
	 * 得到学费缓缴申请表打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhjSqb(XfhjModel model,HttpServletRequest request) throws Exception {
		dao = new XszzYnysDAO();
		return dao.getXfhjSqb(model,request);
	}
	
	/**
	 * 删除学费缓缴信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfhjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzYnysDAO();
		dao.delXfhjxx(cbVal, request);
	}
	
	/**
	 * 批量修改学费缓缴审核结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfhjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzYnysDAO();
		dao.modXfhjxx(cbVal, shjg, request);
	}
	
	/**
	 * 学费缓缴查询表头 xfhjtit ---- 学费缓缴查询表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhjTit() throws Exception {
		dao = new XszzYnysDAO();
		return dao.getXfhjTit();
	}
	
	/**
	 * 学费缓缴查询结果 xfhjres ---- 学费缓缴
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzYnysDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfhjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 学费缓缴导出 xfhjExp ---- 学费缓缴导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getXfhjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzYnysDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_ynys_xfhj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_ynys_xfhj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * 保存学费缓缴审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjShxx(XfhjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzYnysDAO();
		return dao.saveXfhjShxx(model, request);
	}
	
	/**
	 * 获取困难生等级列表
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getKnsdjList(){
		dao = new XszzYnysDAO();
		return dao.selectKnsdjList();
	}
}
