package xsgzgl.pjpy.general.tjcx.jxjtj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;

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
 * Time:2012-7-28 下午14:19:22
 * </p>
 */

public class JxjtjService extends BasicService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	/**
	 * 奖学金统计首页头部
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
		String[] en = new String[] { "pjxn", "xy", "jxjmc", "rs", "je" };
		String[] cn = new String[] { "评奖学年", Base.YXPZXY_KEY, "奖学金名称", "人数", "金额" };
		return dao.arrayToList(en, cn);
	}

	/**
	 * 奖学金统计首页数据查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJxjtjCx(JxjtjForm myForm) throws Exception {
		JxjtjDAO dao = new JxjtjDAO();
		return dao.getJxjtjCx(myForm);
	}

	/**
	 * 奖学金统计首页表单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTML(JxjtjForm myForm, SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				for (int j = 0; j < rs.length; j++) {
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
	 * 奖学金统计首页数据导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void expJxjtj(ServletOutputStream os, JxjtjForm myForm) throws Exception {
		JxjtjDAO dao = new JxjtjDAO();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String title = "奖学金报表";
		// 固定列
		String[] gdName = new String[] { "评奖学年", Base.YXPZXY_KEY, "奖学金名称", "人数", "金额" };
		// 导出报表表头
		List<HashMap<String, String>> topTr = getTopList(gdName);
		// 导出报表的固定数据
		ArrayList<String[]> gdlist = dao.getJxjtjCx(myForm);

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 0, map.get("cn"), wcf2));
			}
		}
		// 填充内容
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
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