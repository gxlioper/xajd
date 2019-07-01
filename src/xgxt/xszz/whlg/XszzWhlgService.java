package xgxt.xszz.whlg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;

/**
 * Title: 学生工作管理系统 Description: 武汉理工大学学生资助Service Copyright: Copyright (c) 2008
 * Company: zfsoft Author: 周觅 Version: 1.0 Time: 2008-09-09
 */
public class XszzWhlgService {

	XszzWhlgDAO dao = null;// 数据操作DAO

	DAO comDao = new DAO();

	private static XszzWhlgService service = new XszzWhlgService();

	public static XszzWhlgService getInstance() {
		return service;
	}

	/**
	 * 删除家庭情况调查表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delJtqkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzWhlgDAO();
		dao.delJtqkxx(cbVal, request);
	}

	/**
	 * 家庭情况调查表查询表头 jt qkdctit ---- 家庭情况调查表表头
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJtqkdcTit() throws Exception {
		dao = new XszzWhlgDAO();
		return dao.getJtqkdcTit();
	}

	/**
	 * 家庭情况调查查询结果 jtqkdcres ---- 家庭情况调查结果
	 * 
	 * @param queryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJtqkdcRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzWhlgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getJtqkdcRes(queryModel, request);
		}
		return resList;
	}

	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzWhlgDAO();
		return dao.getStu(xh);
	}

	/**
	 * 获取家庭情况调查详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkdcxx(String pkVal) throws Exception {
		dao = new XszzWhlgDAO();
		return dao.getJtqkdcxx(pkVal);
	}

	/**
	 * 保存家庭情况调查信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveJtqkdcxx(JtqkdcModel jtqkdcModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzWhlgDAO();
		return dao.saveJtqkdcxx(jtqkdcModel, request);
	}

	/**
	 * 家庭情况调查导出 jtqkdcExp ---- 家庭情况调查导出
	 * 
	 * @param iType
	 *            根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public void getJtqkdcExp(QueryModel queryZxxsdkModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzWhlgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();
		rs = dao
				.getExpDate(queryZxxsdkModel, "view_xszz_whlg_jtqkdcb", request);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_whlg_jtqkdcb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ####################################武汉理工大学 困难生
	 * #######################################
	 */
	/**
	 * 保存学生的困难生申请信息 只有部分字段是可以由学生自己维护的
	 */
	// public boolean saveStuKnsSqInfo(XszzWhlgdxActionForm myForm,String
	// realTable,String pk) throws Exception{
	// myForm.setXn(Base.currXn); //设置学年
	// String[] columns = new
	// String[]{"xh","xn","jtrjnsr","ghlmkh","zxlxdh","is_shbzx","is_shjp","is_lszn",
	// "is_stcj","is_zrzh","is_pysq","is_dq","is_dznsx","is_fmxg","is_qtys"};
	// if(1== 2){ //判断是否已经被审批通过
	//			
	// }
	// String value = myForm.getXh() + myForm.getXn();
	// if(FormUtils.haveOneRecord(realTable, pk,value)){//已经有记录存在 delete
	// if(comDao.runUpdate(SQL_Util.getDelSqlWith_PK(realTable, pk), new
	// String[]{value})){ //delete
	// return comDao.runUpdate(FormUtils.getInsertSql(realTable,
	// columns),FormUtils.getProperties(columns,myForm));//insert
	// }
	// return false;
	// }else{ //insert
	// return comDao.runUpdate(FormUtils.getInsertSql(realTable,
	// columns),FormUtils.getProperties(columns,myForm));
	// }
	// }
	/**
	 * @param pk主键的值集合
	 *            用split【!!splitOne!!】隔开，从客户端获取
	 * @param realTable
	 *            表
	 * @param pkColumn
	 *            主键列
	 * @return
	 * @throws Exception
	 */
	public boolean batchDelRecord(String pk, String realTable, String pkColumn)
			throws Exception {
		String[] pkArray = pk.split("!!splitOne!!"); // split的方式 来自于jsp页面
		return comDao.runUpdate(SQL_Util.getDelSqlWith_InParam(realTable,
				pkColumn, pkArray.length), pkArray);
	}

	/**
	 * 返回中文字段数组
	 * 
	 * @param tableName
	 *            表或视图名
	 * @param outputValues
	 *            字段数组
	 * @return
	 */
	public List<HashMap<String, String>> getColumnCN(String tableName,
			String[] outputValues) {
		return comDao.arrayToList(outputValues, comDao.getColumnNameCN(
				outputValues, tableName));
	}

	/**
	 * 是否是贫困生
	 * 
	 * @param xh
	 * @return
	 */
	public boolean isPks(String xh) {
		return comDao.isKns(xh);
	}

	/**
	 * 返回身份提示信息 此方法无法公用 对于特定的主键设置才有用
	 * 
	 * @param userName
	 * @param userOnLine
	 * @return
	 */
	public String getSfTsxx(String userName, String userOnLine,
			XszzWhlgdxActionForm myForm, String tableName, String pk)
			throws Exception {
		String tsxx = "";
		if (userOnLine.equals("student")) {
			// if(!this.isPks(userName)){
			// tsxx = "对不起，您不是贫困生，不能申请";
			// }
			myForm.setPkVal(userName + Base.currXn); // Form 设定pkValue
			// 用于查询唯一的一条数据
			this.getCommon_PreStuAllInfo(myForm, tableName, pk);// 返回某个学生的完整记录
		} else if (userOnLine.endsWith("teacher")) {
			// if teacher then do data .................
		}
		return tsxx;
	}

	/**
	 * 将数据导出
	 * 
	 * @param queryModel
	 * @param response
	 * @param request
	 * @param tableName
	 *            表或视图名
	 * @throws Exception
	 */
	public void getCommonExp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String tableName) throws Exception {
		dao = new XszzWhlgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();
		rs = dao.getExpDate(queryModel, tableName, request);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit(tableName);
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ####################################武汉理工大学 困难生
	 * #######################################
	 */
	/**
	 * 保存学生的国家助学贷款申请信息 对申请时间sqsj去掉
	 * 
	 * @param myForm
	 * @param realTable
	 * @return
	 * @throws Exception
	 */
	public boolean saveCommonStuInfo(XszzWhlgdxActionForm myForm,
			String realTable, String pk) throws Exception {
		myForm.setXn(Base.currXn); // 设置学年
		String value = myForm.getXh() + myForm.getXn(); // 主键值

		/** 用与资助项目 其主键为xh||nd BEGIN */
		if (pk.contains("nd")) {
			myForm.setNd(Base.currNd);
			value = myForm.getXh() + myForm.getNd();
		}
		/** 用与资助项目 其主键为xh||nd END */

		String[] columns = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(realTable)));
		String[] removeColumns = new String[] { "sqsj" };
		columns = FormUtils.removeArrayElem(columns, removeColumns);// remove
		// sqsj
		// column
		// if(1== 2){ //判断是否已经被审批通过
		//			
		// }
		boolean flag = true;
		if (FormUtils.haveOneRecord(realTable, pk, value)) {// 已经有记录存在 delete
			flag = comDao.runUpdate(SQL_Util.getDelSqlByPKParam(realTable, pk),
					new String[] { value });
		}
		if (flag) {
			flag = comDao.runUpdate(FormUtils.getInsertSql(realTable, columns),
					FormUtils.getProperties(columns, myForm));
		}
		return flag;
	}

	/**
	 * 根据条件返回学生的列表信息 <font color='red'>为公用方法，对特殊的输出要求不适应</font>
	 * 
	 * @param myForm
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCommonStuList(
			XszzWhlgdxActionForm myForm, String tableName) throws Exception {
		String[] columns = new String[] { "xn", "nj", "xydm", "zydm", "bjdm",
				"xh", "xm", "nd" };
		String[] outputValues = new String[] { "xn", "nj", "xh", "xm", "xb",
				"xymc", "zymc", "bjmc", "sqsj" };
		myForm.setColumnCN(this.getColumnCN(tableName, outputValues)); // 返回对应的中文注释
		return comDao.getList(FormUtils.getNormalCondiSql(tableName, columns,
				FormUtils.getProperties(columns, myForm), outputValues),
				new String[] {}, outputValues);
	}

	/**
	 * 根据条件返回学生的列表信息 <font color='red'><br>
	 * 该方法可以使输出比较人性化，可以根据自己的需要定制不同的输出方式</font>
	 * 
	 * @param myForm
	 * @param tableName
	 * @return outputValues 是在页面上显示的字段
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCommonStuList(
			XszzWhlgdxActionForm myForm, String tableName, String[] outputValues)
			throws Exception {
		String[] columns = new String[] { "nj", "xydm", "zydm", "bjdm", "xh",
				"xm", "nd" };
		myForm.setColumnCN(this.getColumnCN(tableName, outputValues)); // 返回对应的中文注释
		return comDao.getList(FormUtils.getNormalCondiSql(tableName, columns,
				FormUtils.getProperties(columns, myForm), outputValues),
				new String[] {}, outputValues);
	}

	/**
	 * 返回一个学生的全部信息
	 * 
	 * @param myForm
	 * @param tableName
	 * @throws Exception
	 */
	public void getCommon_PreStuAllInfo(XszzWhlgdxActionForm myForm,
			String tableName, String pk) throws Exception {
		String[] columnNames = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(tableName))); // toLowerCase
		String[] rs = comDao.getOneRs(SQL_Util.getQuerySqlByPKParam(tableName,
				pk), new String[] { myForm.getPkVal() }, columnNames);
		if (rs != null) {
			FormUtils.setProperties(columnNames, rs, myForm);
		}
	}

	/** 暂时没有用 */
	public void xkxybbSpecialExp(HttpServletRequest request,
			XszzWhlgdxActionForm myForm, HttpServletResponse response) {
		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = Workbook.createWorkbook(response
					.getOutputStream());
			WritableSheet ws = wwb.createSheet("国家助学贷款信息", 0);
			WritableFont wf = new WritableFont(WritableFont.TAHOMA); // 构造字体格式
			wf.setBoldStyle(WritableFont.NO_BOLD); // 设置字体格式(粗体)
			wf.setColour(Colour.getInternalColour(20)); // 设置字体格式(红色)
			wf.setUnderlineStyle(UnderlineStyle.NO_UNDERLINE); // 设置字体格式(无下划线)
			wf.setPointSize(10); // 设置字体格式(大小)
			WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
			wcf.setFont(wf); // 设置指定字体格式的单元格格式
			wcf.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中
		    wcf.setWrap(true); // 指定格式设置自动换行
			// String[] columns = new
			// String[]{"合同号","学号","姓名","性别","身份证号","民族","院系","专业","入学年份"};
			// 毕业年份 家庭详细住址 邮政编码 家庭联系电话 父亲姓名 父亲工作单位 父亲联系电话 母亲姓名 母亲工作单位 母亲联系电话
			// 贷款总金额 贷款期限（月） 学杂费贷款总额 住宿费贷款总额 年度放款金额 校园一卡通号 工行联名卡卡号 借款学生实际获得贷款金额
			// 是否报考了研究生 毕业去向 有效联系方式（移动电话） 常用e-mail 工作单位 单位地址 单位邮编 单位电话}
			// "htbh"
			String[] columns = new String[] { "nj", "xydm", "zydm", "bjdm",
					"xh", "xm", "xn" };
			String[] outputValues = new String[] { "xh", "xm", "sfzh", "mzmc",
					"xymc", "zymc", "rxnf" };
			String tableName = "view_xszz_whlgdx_gjzxdk_sp";
			String[] columnsCN = comDao
					.getColumnNameCN(outputValues, tableName);
			for (int i = 0; i < outputValues.length; i++) {
				ws.setColumnView(i, 20);
				ws.setRowView(i, 15 * 20);
			}
			for (int m = 0; m < columnsCN.length; m++) {
				ws.addCell(new Label(m, 0, columnsCN[m], wcf));
			}
			List<HashMap<String, String>> rs = // get list
			comDao.getList(FormUtils.getNormalCondiSql(tableName, columns,
					FormUtils.getProperties(columns, myForm), outputValues),
					new String[] {}, outputValues);
			for (int i = 0; i < rs.size(); i++) {
				for (int j = 0; j < outputValues.length; j++) {
					ws.addCell(new Label(j, i + 1, rs.get(i).get(
							outputValues[j]), wcf));
				}
			}
			// if(i==0){// 设置1列2行起始纵向合并单元格
			// ws.mergeCells(0,1,0,Integer.parseInt(sscout_ald));
			// }else{//设置上次合并行为起点纵向合并单元格
			// ws.mergeCells(0,nextNumV1-Integer.parseInt(sscout_ald_last)+1,0,nextNumV1);
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
