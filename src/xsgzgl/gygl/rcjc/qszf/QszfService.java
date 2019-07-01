package xsgzgl.gygl.rcjc.qszf;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-9 下午14:19:22
 * </p>
 */

public class QszfService extends BasicService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	/**
	 * 寝室走访首页面信息头部
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTr() {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xy", "bzrxm", "bj", "xsszld", "xsszqs", "xqsj" };
		String[] cn = new String[] { "", Base.YXPZXY_KEY, "班主任姓名", "班级", "学生所在楼栋", "学生所在寝室", "走访时间" };
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 查询走访老师页面头部
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTr2() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "zgh", "xm" };
		String[] cn = new String[] { "职工号", "走访老师姓名" };
		return dao.arrayToList(en, cn);
	}

	/**
	 * 寝室走访信息查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getQszfCx(QszfForm myForm) throws Exception {
		QszfDAO dao = new QszfDAO();
		return dao.getQszfCx(myForm);
	}

	/**
	 * 获取所有寝室走访老师
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getZfls(QszfForm myForm) throws Exception {
		QszfDAO dao = new QszfDAO();
		return dao.getZfls(myForm);
	}

	/**
	 * 创建寝室走访首页面信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTML(SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	/**
	 * 创建所有老师查询页面信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTML2(SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"sendLsgh();\" style=\"cursor:hand\">");
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length) + "%\" ");
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append("<input type=\"hidden\" value=\""+rs[j]+"\"/>");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * 级联查询-由走访老师职工号获取负责班级
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFzbjForZflsgh(String zflsgh) {
		QszfDAO dao = new QszfDAO();
		String[] inputValue = new String[] { zflsgh };
		String[] outputValue = new String[] { "bjmc", "bjdm" };
		return dao.getFzbjForZflsgh(inputValue, outputValue);
	}

	/**
	 * 级联查询-由走访老师职工号获取走访老师姓名
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZflsxmForZflsgh(QszfForm qszfForm, String zflsgh) {
		QszfDAO dao = new QszfDAO();
		return dao.getZflsxmForZflsgh(qszfForm, zflsgh);
	}

	/**
	 * 级联查询-由负责班级获取楼栋
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLdForFzbj(String fzbj) {
		QszfDAO dao = new QszfDAO();
		String[] inputValue = new String[] { fzbj };
		String[] outputValue = new String[] { "ldmc", "lddm" };
		return dao.getLdForFzbj(inputValue, outputValue);
	}

	/**
	 * 级联查询-由楼栋号获取寝室号
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQshForLd(String fzbj, String xsszldh) {
		QszfDAO dao = new QszfDAO();
		String[] inputValue = new String[] { fzbj, xsszldh };
		String[] outputValue = new String[] { "xsszqsh" };
		return dao.getQshForLd(inputValue, outputValue);
	}

	/**
	 * 寝室走访信息保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean qszfBc(QszfForm myForm, String username) throws Exception {
		QszfDAO dao = new QszfDAO();
		return dao.qszfBc(myForm, username);
	}

	/**
	 * 寝室走访信息删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean qszfSc(QszfForm myForm, String[] valArr, String username) throws SQLException {
		QszfDAO dao = new QszfDAO();
		return dao.qszfSc(myForm, valArr, username);
	}

	/**
	 * 获取学院下拉框集合
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object getXyList(HttpServletRequest request) {
		QszfDAO dao = new QszfDAO();
		return dao.getXyList(request);
	}

	/**
	 * 查看获取寝室走访信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getQszfMap(QszfForm qszfForm) {
		QszfDAO dao = new QszfDAO();
		return dao.getQszfMap(qszfForm);
	}

	/**
	 * 寝室走访信息修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean qszfXg(QszfForm myForm, String username) throws Exception {
		QszfDAO dao = new QszfDAO();
		return dao.qszfXg(myForm, username);
	}

	/**
	 * 走访次数统计导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void expXqtjb(QszfForm model, ServletOutputStream os, String xn, String yf, String xymc) throws Exception {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		QszfDAO dao = new QszfDAO();
		String title = "学工线领导、班主任走访次数统计(" + xn + "年" + yf + "月)";
		// 固定列
		String[] gdName = new String[] { Base.YXPZXY_KEY, "班主任", "班级", "学生所在寝室", "下寝的次数" };
		// 导出报表表头
		List<HashMap<String, String>> topTr = getTopList(gdName);
		// 导出报表的固定数据
		ArrayList<String[]> gdlist = dao.expXqtjbList(model, xn, yf, xymc);

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充表头
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 5, 800);// 标题
		
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, map.get("cn"), wcf2));
			}
		}
		// 填充内容
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 2, info[j], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}