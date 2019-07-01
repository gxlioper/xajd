package xgxt.qgzx.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.qgzx.dao.QgzxCjffglDAO;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.form.QgzxForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 勤工助学学生岗位管理Service
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 李容
 * </p>
 * <p>
 * Version: 2.0
 * </p>
 * <p>
 * Time: 2009-12-02
 * </p>
 */
public class QgzxCjffglService {
	QgzxCjffglDAO dao = new QgzxCjffglDAO();

	/**
	 * 获取列的中文名称
	 * 
	 * @param String
	 *            [] colList
	 * @param String
	 *            tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] colList, String tableName) {
		return dao.getColumnNameCN(colList, tableName);
	}

	/**
	 * 查询剩余经费
	 * 
	 * @param HashMap
	 *            <String, String> paramMap
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> querySyjf(HashMap<String, String> paramMap) {
		return dao.selectCjffSyjf(paramMap);
	}

	/**
	 * 查询学生酬金发放信息
	 * 
	 * @param pkValue
	 * @return String[]
	 * */
	public List<HashMap<String, String>> queryXscjff(String pkValue,
			HashMap<String, String> paramMap) {
		String xxdm = paramMap.get("xxdm");
		List<HashMap<String, String>> ffList = dao.selectXscjff(pkValue,
				paramMap);
		// 岗位调整后，发放学生在调整前的岗位工资
		QgzxService service = new QgzxService();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
			// 浙江理工
			ffList.addAll(service.queryTzqxsgw(ffList, pkValue, paramMap
					.get("nd"), paramMap.get("yf")));
		} else if (Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)) {
			// 浙江科技，只有有工作考核记录的学生才可发放酬金
			QgzxDao qgzxDao = new QgzxDao();
			ffList.addAll(qgzxDao.queryTzqxsgwForZjkj(pkValue));
		} else {
			ffList
					.addAll(service.queryTzqxsgw(ffList, "gwdm||gwsbsj",
							pkValue));
		}
		return ffList;
	}

	/**
	 * 查询学生酬金发放信息导出
	 * 
	 * @param QgzxForm
	 *            model String[] output
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> queryXscjffForExport(QgzxForm model, String[] output)
			throws Exception {
		return dao.selectXscjffxx(model, output);
	}

	/**
	 * 中国地质大学查询学生酬金发放信息导出
	 * 
	 * @param QgzxForm
	 *            model String[] output
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> queryZgdzdxXscjffForExport(QgzxForm model,
			String[] output) throws Exception {
		List<String[]> list = new ArrayList<String[]>();
		String fflx = model.getFflx();
		// 中国地质大学月份前有0
		// String yf = model.getYf();
		// yf = StringUtils.isNull(yf) ? "0" : yf;
		// model.setYf(Integer.parseInt(yf) + "");

		if ("临时岗工资".equalsIgnoreCase(fflx)) {
			list = dao.selectXslsgzffxx(model, output);
		} else {
			list = dao.selectZgdzdxXscjffxx(model, output);
		}
		return list;
	}

	/**
	 * 保存学生酬金发放信息
	 * 
	 * @param QgzxForm
	 *            model
	 * @param HttpServletRequest
	 *            request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean saveXscjff(QgzxForm model, HttpServletRequest request)
			throws Exception {
		boolean flag = true;
		String tableName = "xscjffb";
		String pk = "xh||gwdm||'-'||sqsj||nd||yf";
		String gwdm = model.getGwdm();
		String gwsbsj = model.getGwsbsj();
		String xh = model.getXh();
		String nd = model.getNd();
		String yf = model.getYf();
		String gzsj = model.getGzsj();
		String cjje = model.getCjje();
		String yhkh = model.getYhkh();
		String yhmc = model.getYhmc();
		String bz = model.getBz();
		String pkValue = xh + gwdm + "-" + gwsbsj + nd + yf;

		if (dao.checkExists(tableName, pk, pkValue)) {// 检测记录是否存在,记录已经存在先将记录删除后重新插入
			flag = StandardOperation.delete(tableName, pk, pkValue, request);
		}
		if (flag) {// 记录插入
			String[] columns = { "xh", "gwdm", "sqsj", "nd", "yf", "gzsj",
					"cjje", "yhkh", "yhmc", "bz" };
			String[] values = { xh, gwdm, gwsbsj, nd, yf, gzsj, cjje, yhkh,
					yhmc, bz };
			flag = StandardOperation
					.insert(tableName, columns, values, request);
		}
		return flag;
	}

	/**
	 * 酬金发放信息批量修改
	 * 
	 * @param QgzxForm
	 *            model
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean modiXscjffBatch(QgzxForm model, HttpServletRequest request)
			throws Exception {
		boolean flag = true;
		String pk = "xh||gwdm||sqsj||nd||yf";
		String[] pkV = model.getPkV();
		String[] sqlArr = new String[pkV.length];

		for (int i = 0; i < pkV.length; i++) {
			StringBuffer sb = new StringBuffer();
			sb.append(Base.isNull(model.getGzsj()) ? ", gzsj " : ("'"
					+ model.getGzsj() + "'"));
			sb.append(Base.isNull(model.getCjje()) ? ", cjje " : ("'"
					+ model.getCjje() + "'"));
			sb.append(Base.isNull(model.getYhkh()) ? ", yhkh " : ("'"
					+ model.getYhkh() + "'"));
			sb.append(Base.isNull(model.getYhmc()) ? ", yhmc " : ("'"
					+ model.getYhmc() + "'"));
			sb.append(Base.isNull(model.getBz()) ? ", bz " : ("'"
					+ model.getBz() + "'"));

			sqlArr[i] = "insert into xscjffb (xh,gwdm,sqsj,xn,nd,xq,yf,gzsj,cjje,yhkh,yhmc,bz)(select xh,gwdm,sqsj,xn,'"
					+ model.getNd()
					+ "',xq,'"
					+ model.getYf()
					+ "'"
					+ sb
					+ " from xscjffb where " + pk + "='" + pkV[i] + "' )";
		}
		try {
			dao.runBatch(sqlArr);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 学生酬金发放信息批量审核
	 * 
	 * @param QgzxForm
	 *            model
	 * @return boolean
	 * */
	public boolean xscjffAudiBatch(QgzxForm model) {
		boolean flag = true;
		String[] pkV = model.getPkV();
		String pk = "xh||gwdm||sqsj||nd||yf||fflx";
		String[] sqlArr = new String[pkV.length];

		for (int i = 0; i < pkV.length; i++) {
			sqlArr[i] = "update xscjffb set xxsh='" + model.getShjg()
					+ "',shsj='" + GetTime.getNowTime2() + "' where " + pk
					+ "='" + pkV[i] + "'";
		}
		try {
			dao.runBatch(sqlArr);
		} catch (Exception ex) {
			ex.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 查询单条酬金发放信息
	 * 
	 * @param String
	 *            pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryXscjffOne(String pkValue) {
		return dao.selectXscjffOne(pkValue);
	}

	/**
	 * 学生酬金发放单个审核
	 * 
	 * @param QgzxForm
	 *            model
	 * @param HttpServletRequest
	 *            request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean xscjffAudiOne(QgzxForm model, HttpServletRequest request)
			throws Exception {
		return StandardOperation.update("xscjffb", new String[] { "xxsh",
				"shsj" },
				new String[] { model.getShjg(), GetTime.getNowTime2() },
				"xh||gwdm||sqsj||nd||yf||fflx", model.getPkValue(), request);
	}

	//
	public void printCjffhzb(QgzxForm model, User user, OutputStream os) {
		WritableWorkbook wwb = null;
		List<HashMap<String, String>> yrdwList = dao.getYrdwList(user);
		String xqmc = dao.getXqmc(model.getQueryequals_xueqi());
		try {
			wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("工资发放汇总表", 0);
			WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			WritableCellFormat wcfH = ExcelMethods.getWcf(WritableFont.ARIAL,
					10, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// 填充标题
			ExcelMB ex = new ExcelMB();
			ws.mergeCells(0, 0, 7, 1);
			ex.printToOneCell_multy(ws, model.getQueryequals_xn() + "学年" + xqmc
					+ "勤工助学工时统计汇总表", 0, 0, 20, true, Alignment.CENTRE,
					VerticalAlignment.CENTRE, true, 650, Border.NONE);
			ws.addCell(new Label(0, 2, "用工部门", wcfH));
			ws.addCell(new Label(1, 2, "用工岗位", wcfH));
			ws.addCell(new Label(2, 2, "学 号", wcfH));
			ws.addCell(new Label(3, 2, "姓 名", wcfH));
			ws.addCell(new Label(4, 2, "工 时", wcfH));
			ws.addCell(new Label(5, 2, "酬 金", wcfH));
			ws.addCell(new Label(6, 2, Base.YXPZXY_KEY, wcfH));
			ws.addCell(new Label(7, 2, "专业班", wcfH));

			int base = 3;
			float gzsjCount = 0;// 总工作时间
			float cjjeCount = 0;// 总酬金金额
			for (int i = 0; i < yrdwList.size(); i++) {
				model.setYrdwdm(yrdwList.get(i).get("yrdwdm"));// 用人单位代码
				List<HashMap<String, String>> xscjffList = dao
						.getXscjffList(model);
				float bmgzsjCount = 0;// 总工作时间
				float bmcjjeCount = 0;// 总酬金金额
				for (int j = 0; j < xscjffList.size(); j++) {
					// 写入学生酬金信息
					ws.addCell(new Label(0, base, xscjffList.get(j).get(
							"yrdwmc"), wcf));
					ws.addCell(new Label(1, base,
							xscjffList.get(j).get("gwdm"), wcf));
					ws.addCell(new Label(2, base, xscjffList.get(j).get("xh"),
							wcf));
					ws.addCell(new Label(3, base, xscjffList.get(j).get("xm"),
							wcf));
					ws.addCell(new Label(4, base,
							xscjffList.get(j).get("gzsj"), wcf));
					ws.addCell(new Label(5, base,
							xscjffList.get(j).get("cjje"), wcf));
					ws.addCell(new Label(6, base,
							xscjffList.get(j).get("xymc"), wcf));
					ws.addCell(new Label(7, base,
							xscjffList.get(j).get("bjmc"), wcf));
					base++;
					bmgzsjCount += Float.parseFloat(StringUtils
							.isNull(xscjffList.get(j).get("gzsj")) ? "0"
							: xscjffList.get(j).get("gzsj"));
					bmcjjeCount += Float.parseFloat(StringUtils
							.isNull(xscjffList.get(j).get("cjje")) ? "0"
							: xscjffList.get(j).get("cjje"));
				}
				if (xscjffList == null || xscjffList.size() < 1) {
					ws.addCell(new Label(0, base,
							yrdwList.get(i).get("yrdwmc"), wcf));
					ws.addCell(new Label(1, base, "", wcf));
					ws.addCell(new Label(2, base, "", wcf));
					ws.addCell(new Label(3, base, "", wcf));
					ws.addCell(new Label(4, base, "", wcf));
					ws.addCell(new Label(5, base, "", wcf));
					ws.addCell(new Label(6, base, "", wcf));
					ws.addCell(new Label(7, base, "", wcf));
					base++;
				}
				// 写入单位汇总信息
				ws.addCell(new Label(0, base, yrdwList.get(i).get("yrdwmc")
						+ "汇总", wcfH));
				ws.addCell(new Label(1, base, "", wcf));
				ws.addCell(new Label(2, base, "", wcf));
				ws.addCell(new Label(3, base, "", wcf));
				ws.addCell(new Label(4, base, bmgzsjCount + "", wcfH));
				ws.addCell(new Label(5, base, bmcjjeCount + "", wcfH));
				ws.addCell(new Label(6, base, "", wcf));
				ws.addCell(new Label(7, base, "", wcf));
				gzsjCount += bmgzsjCount;
				cjjeCount += bmcjjeCount;
				base++;
			}
			// 写入总汇总信息
			ws.addCell(new Label(0, base, "总汇总", wcfH));
			ws.addCell(new Label(1, base, "", wcf));
			ws.addCell(new Label(2, base, "", wcf));
			ws.addCell(new Label(3, base, "", wcf));
			ws.addCell(new Label(4, base, gzsjCount + "", wcfH));
			ws.addCell(new Label(5, base, cjjeCount + "", wcfH));
			ws.addCell(new Label(6, base, "", wcf));
			ws.addCell(new Label(7, base, "", wcf));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 写入到客户端
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	public void printGzqfb(QgzxForm model,OutputStream os) {

		List<String[]> data = dao.getGzffList(model);
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet(Base.YXPZXY_KEY+"工资签发表单", 0);

		try {
			excel.printTitle(ws, Base.YXPZXY_KEY+"工资签发表单", 7, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.RIGHT, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式

			ws.mergeCells(0, 1, 6, 1);
			ws.addCell(new Label(0, 1, "日期："+GetTime.getNowTime(), wcfTytle));

			wcfTytle = ExcelMB.getWritableCellFormat(8, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "系部名称", wcfTytle));
			ws.addCell(new Label(2, 2, "岗位", wcfTytle));
			ws.addCell(new Label(3, 2, "人数", wcfTytle));
			ws.addCell(new Label(4, 2, "金额", wcfTytle));
			ws.addCell(new Label(5, 2, "签收", wcfTytle));
			ws.addCell(new Label(6, 2, "备注", wcfTytle));
			
			if (null != data && data.size() > 0){
				for (int i = 0 ; i < data.size() ; i++){
					ws.addCell(new Label(0, 3+i, String.valueOf(i+1), wcfTytle));
					
					for (int j = 0 ; j < data.get(i).length ; j++){
						ws.addCell(new Label(1+j, 3+i, data.get(i)[j], wcfTytle));
					}
					
					ws.addCell(new Label(5, 3+i, "", wcfTytle));
					ws.addCell(new Label(6, 3+i, "", wcfTytle));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
