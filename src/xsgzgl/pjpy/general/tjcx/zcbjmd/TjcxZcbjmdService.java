package xsgzgl.pjpy.general.tjcx.zcbjmd;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmDao;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.tjcx.TjcxZcbjmdInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_综测班级名单_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class TjcxZcbjmdService extends CommService implements
		TjcxZcbjmdInterface {

	TjcxZcbjmdDAO dao = new TjcxZcbjmdDAO();

	/**
	 * 创建综测班级名单HTMl【有等级考试】
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public void createZcbjmdDjksHTML(PjpyGeneralForm myForm,
			TjcxZcbjmdModel model, User user, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		// ================前台以做非空判断，无需考虑参数null的情况=========================
		SearchModel searchModel = myForm.getSearchModel();
		// 学年
		String xn = searchModel.getSearch_tj_xn()[0];
		// 学期
		String xq = searchModel.getSearch_tj_xq()[0];
		// 班级代码
		String bjdm = searchModel.getSearch_tj_bjNew()[0];

		ZcxmDao zcxmDao = new ZcxmDao();
		//前两级综测项目
		List<HashMap<String,String>> zcxmList = zcxmDao.getFirstAndSecondZcxm();
		
		HashMap<String, Object> map = getZcbjmdInfoNew(xn,xq, bjdm,zcxmList);

		StringBuilder html = new StringBuilder();
		html.append("<table class=\"dateline\" width=\"100%\">");

		html.append("<thead>");
		// =================第一行===================
		html.append("<tr>");
		html.append("<td width=\"60%\" colspan=\"2\">");
		html.append("班级：");
		html.append(map.get("bjmc"));
		html.append("</td>");
		html.append("<td>");
		html.append("班级人数：");
		html.append(map.get("bjrs"));
		html.append("人");
		html.append("</td>");
		html.append("</tr>");

		// =================第二行===================
		html.append("<tr>");
		html.append("<td width=\"30%\">");
		html.append("一等奖学金：");
		html.append(map.get("ydjxjrs"));
		html.append("人");
		html.append("</td>");
		html.append("<td width=\"30%\">");
		html.append("二等奖学金：");
		html.append(map.get("edjxjrs"));
		html.append("人");
		html.append("</td>");
		html.append("<td>");
		html.append("三等奖学金：");
		html.append(map.get("sdjxjrs"));
		html.append("人");
		html.append("</td>");
		html.append("</tr>");

		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<td colspan=\"3\">");
		// ==========学生列表 begin==============
		html
				.append("<div style=\"width:750px;height:400px;overflow-x:auto;overflow-y:auto;\">");
		html.append("<table class=\"dateline\" width=\"100%\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<td>学号</td>");
		html.append("<td>姓名</td>");
		for (HashMap<String, String> hashMap : zcxmList) {
			html.append("<td>"+hashMap.get("xmmc")+"</td>");
			html.append("<td>排名</td>");
		}
//		html.append("<td>德育积分</td>");
//		html.append("<td>德育名次</td>");
//		html.append("<td>智育积分</td>");
//		html.append("<td>智育名次</td>");
//		html.append("<td>体育积分</td>");
//		html.append("<td>能力积分</td>");
//		html.append("<td>能力名次</td>");
//		html.append("<td>总分</td>");
//		html.append("<td>综合排名</td>");
		html.append("<td>奖学金及单项奖</td>");
		html.append("<td>三好学生及优干</td>");
		html.append("<td>获校外奖学金情况</td>");
		html.append("<td>学习成绩不及格门数</td>");
		html.append("<td>英语过级情况</td>");
		html.append("<td>计算机过级情况</td>");
		html.append("<td>有无拖欠学费</td>");
		html.append("<td>有无校级通报</td>");
		html.append("<td>处分情况</td>");
		html.append("<td>旷课节数</td>");
		html.append("</tr>");
		html.append("</thead>");

		// 综测信息列表
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");
		html.append("<tbody>");

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {
				HashMap<String, String> zcxx = zcxxList.get(i);
				html.append("<tr>");
				html.append("<td>" + zcxx.get("xh") + "</td>");
				html.append("<td>" + zcxx.get("xm") + "</td>");
				for(int j=0;j<zcxmList.size();j++){
					html.append("<td>" + zcxx.get("fs"+j) + "</td>");
					html.append("<td>" + zcxx.get("pm"+j) + "</td>");
				}
//				html.append("<td>" + zcxx.get("dyf") + "</td>");
//				html.append("<td>" + zcxx.get("dypm") + "</td>");
//				html.append("<td>" + zcxx.get("zyf") + "</td>");
//				html.append("<td>" + zcxx.get("zypm") + "</td>");
//				html.append("<td>" + zcxx.get("tyf") + "</td>");
//				html.append("<td>" + zcxx.get("nlf") + "</td>");
//				html.append("<td>" + zcxx.get("nlpm") + "</td>");
//				html.append("<td>" + zcxx.get("zcf") + "</td>");
//				html.append("<td>" + zcxx.get("zcpm") + "</td>");
				html.append("<td>" + zcxx.get("jxjmc") + "</td>");
				html.append("<td>" + zcxx.get("rychmc") + "</td>");
				html.append("<td></td>");
				html.append("<td>" + zcxx.get("bjgms") + "</td>");
				html.append("<td>" + zcxx.get("yydj") + "</td>");
				html.append("<td>" + zcxx.get("jsjdj") + "</td>");
				html.append("<td></td>");
				html.append("<td>" + zcxx.get("xjtb") + "</td>");
				html.append("<td>" + zcxx.get("wjcf") + "</td>");
				html.append("<td>" + zcxx.get("kkjs") + "</td>");
				html.append("</tr>");
			}
		}

		html.append("</tbody>");

		html.append("</table>");
		html.append("</div>");
		// ==========学生列表 end==============
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("</table>");
		response.getWriter().print(html.toString());
	}

	/**
	 * 创建综测班级名单HTMl【无等级考试】
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public void createZcbjmdNoDjksHTML(PjpyGeneralForm myForm,
			TjcxZcbjmdModel model, User user, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		// ================前台以做非空判断，无需考虑参数null的情况=========================
		SearchModel searchModel = myForm.getSearchModel();
		// 学年
		String xn = searchModel.getSearch_tj_xn()[0];
		// 班级代码
		String bjdm = searchModel.getSearch_tj_bj()[0];

		HashMap<String, Object> map = getZcbjmdInfo(xn, bjdm);

		StringBuilder html = new StringBuilder();
		html.append("<table class=\"dateline\" width=\"100%\">");

		html.append("<thead>");
		// =================第一行===================
		html.append("<tr>");
		html.append("<td width=\"60%\" colspan=\"2\">");
		html.append("班级：");
		html.append(map.get("bjmc"));
		html.append("</td>");
		html.append("<td>");
		html.append("班级人数：");
		html.append(map.get("bjrs"));
		html.append("人");
		html.append("</td>");
		html.append("</tr>");

		// =================第二行===================
		html.append("<tr>");
		html.append("<td width=\"30%\">");
		html.append("一等奖学金：");
		html.append(map.get("ydjxjrs"));
		html.append("人");
		html.append("</td>");
		html.append("<td width=\"30%\">");
		html.append("二等奖学金：");
		html.append(map.get("edjxjrs"));
		html.append("人");
		html.append("</td>");
		html.append("<td>");
		html.append("三等奖学金：");
		html.append(map.get("sdjxjrs"));
		html.append("人");
		html.append("</td>");
		html.append("</tr>");

		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<td colspan=\"3\">");
		// ==========学生列表 begin==============
		html
				.append("<div style=\"width:750px;height:400px;overflow-x:auto;overflow-y:auto;\">");
		html.append("<table class=\"dateline\" width=\"100%\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<td>学号</td>");
		html.append("<td>姓名</td>");
		html.append("<td>德育积分</td>");
		html.append("<td>德育名次</td>");
		html.append("<td>智育积分</td>");
		html.append("<td>智育名次</td>");
		html.append("<td>体育积分</td>");
		html.append("<td>能力积分</td>");
		html.append("<td>能力名次</td>");
		html.append("<td>总分</td>");
		html.append("<td>综合排名</td>");
		html.append("<td>奖学金及单项奖</td>");
		html.append("<td>三好学生及优干</td>");
		html.append("<td>获校外奖学金情况</td>");
		html.append("<td>学习成绩不及格门数</td>");
		// html.append("<td>英语过级情况</td>");
		// html.append("<td>计算机过级情况</td>");
		html.append("<td>有无拖欠学费</td>");
		html.append("<td>有无校级通报</td>");
		html.append("<td>处分情况</td>");
		html.append("<td>旷课节数</td>");
		html.append("</tr>");
		html.append("</thead>");

		// 综测信息列表
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");
		html.append("<tbody>");

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {
				HashMap<String, String> zcxx = zcxxList.get(i);
				html.append("<tr>");
				html.append("<td>" + zcxx.get("xh") + "</td>");
				html.append("<td>" + zcxx.get("xm") + "</td>");
				html.append("<td>" + zcxx.get("dyf") + "</td>");
				html.append("<td>" + zcxx.get("dypm") + "</td>");
				html.append("<td>" + zcxx.get("zyf") + "</td>");
				html.append("<td>" + zcxx.get("zypm") + "</td>");
				html.append("<td>" + zcxx.get("tyf") + "</td>");
				html.append("<td>" + zcxx.get("nlf") + "</td>");
				html.append("<td>" + zcxx.get("nlpm") + "</td>");
				html.append("<td>" + zcxx.get("zcf") + "</td>");
				html.append("<td>" + zcxx.get("zcpm") + "</td>");
				html.append("<td>" + zcxx.get("jxjmc") + "</td>");
				html.append("<td>" + zcxx.get("rychmc") + "</td>");
				html.append("<td></td>");
				html.append("<td>" + zcxx.get("bjgms") + "</td>");
				// html.append("<td>" + zcxx.get("yydj") + "</td>");
				// html.append("<td>" + zcxx.get("jsjdj") + "</td>");
				html.append("<td></td>");
				html.append("<td>" + zcxx.get("xjtb") + "</td>");
				html.append("<td>" + zcxx.get("wjcf") + "</td>");
				html.append("<td>" + zcxx.get("kkjs") + "</td>");
				html.append("</tr>");
			}
		}

		html.append("</tbody>");

		html.append("</table>");
		html.append("</div>");
		// ==========学生列表 end==============
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("</table>");
		response.getWriter().print(html.toString());
	}

	/**
	 * 导出综测班级名单
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expZcbjmd(TjcxZcbjmdModel model, OutputStream os)
			throws Exception {

		// 学年
		String xn = model.getXn();
		// 学年
		String xq = model.getXq();
		// 班级
		String bjdm = model.getBjdm();
		// 类型
		String lx = model.getLx();

		// 判断报表是否需要英语，计算机成绩
		int num = ("djks".equalsIgnoreCase(lx)) ? 2 : 0;

		ZcxmDao zcxmDao = new ZcxmDao();
		//前两级综测项目
		List<HashMap<String,String>> zcxmList = zcxmDao.getFirstAndSecondZcxm();
		
		HashMap<String, Object> map = getZcbjmdInfoNew(xn,xq, bjdm,zcxmList);
		

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("综合素质测评表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		WritableCellFormat wcf3 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();

		// 设置标题
		StringBuffer title = new StringBuffer();
		title.append(xn);
		title.append("学年");
		// title.append(xqmc);
		// title.append("学期");
		// title.append(xymc);
		title.append("综合素质测评表");

		ws.mergeCells(0, 0, 18 + num, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 650,
				Border.NONE);

		// 填充表头
		// --------------第一行----------------
		ws.addCell(new Label(0, 2, "班级：" + map.get("bjmc"), wcf3));
		ws.mergeCells(0, 2, 10, 2);
		ws.addCell(new Label(11, 2, "班级人数：" + map.get("bjrs") + "人", wcf3));
		ws.mergeCells(11, 2, 18 + num, 2);
		// --------------第二行----------------
		ws.addCell(new Label(0, 3, "一等奖学金：" + map.get("ydjxjrs") + "人", wcf3));
		ws.mergeCells(0, 3, 5, 3);
		ws.addCell(new Label(6, 3, "二等奖学金：" + map.get("edjxjrs") + "人", wcf3));
		ws.mergeCells(6, 3, 10, 3);
		ws.addCell(new Label(11, 3, "三等奖学金：" + map.get("sdjxjrs") + "人", wcf3));
		ws.mergeCells(11, 3, 18 + num, 3);
		// --------------第三行----------------
		ws.addCell(new Label(11, 4, "班主任签名：", wcf3));
		ws.mergeCells(11, 4, 18 + num, 4);
		// --------------第四行----------------
		ws.addCell(new Label(0, 5, "学号", wcf2));
		ws.addCell(new Label(1, 5, "姓名", wcf2));
//		ws.addCell(new Label(2, 5, "德育积分", wcf2));
//		ws.addCell(new Label(3, 5, "德育名次", wcf2));
//		ws.addCell(new Label(4, 5, "智育积分", wcf2));
//		ws.addCell(new Label(5, 5, "智育名次", wcf2));
//		ws.addCell(new Label(6, 5, "体育积分", wcf2));
//		ws.addCell(new Label(7, 5, "能力积分", wcf2));
//		ws.addCell(new Label(8, 5, "能力名次", wcf2));
//		ws.addCell(new Label(9, 5, "总分", wcf2));
//		ws.addCell(new Label(10, 5, "综合排名", wcf2));
		int tmp=0;
		for (int i=0;i<zcxmList.size();i++) {
			HashMap<String, String> hashMap=zcxmList.get(i);
			ws.addCell(new Label(2*i+2, 5, hashMap.get("xmmc"), wcf2));
			ws.addCell(new Label(2*i+3, 5, "排名", wcf2));
			tmp=2*i+3;
		}
//		ws.addCell(new Label(11, 5, "奖学金及单项奖(因为其它原因不能评选的请注明)", wcf2));
//		ws.addCell(new Label(10, 5, "综合排名", wcf2));
		ws.addCell(new Label(tmp+1, 5, "奖学金及单项奖(因为其它原因不能评选的请注明)", wcf2));
		ws.addCell(new Label(tmp+2, 5, "三好学生及优干", wcf2));
		ws.addCell(new Label(tmp+3, 5, "获校外奖学金情况（包括当学期获国家奖学金情况）", wcf2));
		ws.addCell(new Label(tmp+4, 5, "学习成绩不及格门数", wcf2));
		if ("djks".equalsIgnoreCase(lx)) {
			ws.addCell(new Label(tmp+5, 5, "英语过级情况", wcf2));
			ws.addCell(new Label(tmp+6, 5, "计算机过级情况", wcf2));
		}
		ws.addCell(new Label(tmp+5 + num, 5, "有无拖欠学费(已贷款请注明)", wcf2));
		ws.addCell(new Label(tmp+6 + num, 5, "有无校级通报", wcf2));
		ws.addCell(new Label(tmp+7 + num, 5, "处分情况(还在留校察看期请注明)", wcf2));
		ws.addCell(new Label(tmp+8 + num, 5, "旷课节数", wcf2));

		// 综测信息列表
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {
				HashMap<String, String> zcxx = zcxxList.get(i);
				// 综测信息
				ws.addCell(new Label(0, 6 + i, zcxx.get("xh"), wcf2));
				ws.addCell(new Label(1, 6 + i, zcxx.get("xm"), wcf2));
				for(int j=0;j<zcxmList.size();j++){
					ws.addCell(new Label(2*j+2, 6 + i, zcxx.get("fs"+j), wcf2));
					ws.addCell(new Label(2*j+3, 6 + i, zcxx.get("pm"+j), wcf2));
				}
//				ws.addCell(new Label(2, 6 + i, zcxx.get("dyf"), wcf2));
//				ws.addCell(new Label(3, 6 + i, zcxx.get("dypm"), wcf2));
//				ws.addCell(new Label(4, 6 + i, zcxx.get("zyf"), wcf2));
//				ws.addCell(new Label(5, 6 + i, zcxx.get("zypm"), wcf2));
//				ws.addCell(new Label(6, 6 + i, zcxx.get("tyf"), wcf2));
//				ws.addCell(new Label(7, 6 + i, zcxx.get("nlf"), wcf2));
//				ws.addCell(new Label(8, 6 + i, zcxx.get("nlpm"), wcf2));
//				ws.addCell(new Label(9, 6 + i, zcxx.get("zcf"), wcf2));
//				ws.addCell(new Label(10, 6 + i, zcxx.get("zcpm"), wcf2));
				// 评奖信息
				ws.addCell(new Label(tmp+1, 6 + i, zcxx.get("jxjmc"), wcf2));
				ws.addCell(new Label(tmp+2, 6 + i, zcxx.get("rychmc"), wcf2));
				ws.addCell(new Label(tmp+3, 6 + i, "", wcf2));
				// 成绩信息
				ws.addCell(new Label(tmp+4, 6 + i, zcxx.get("bjgs"), wcf2));
				if ("djks".equalsIgnoreCase(lx)) {
					ws.addCell(new Label(tmp+5, 6 + i, zcxx.get("yydj"), wcf2));
					ws.addCell(new Label(tmp+6, 6 + i, zcxx.get("jsjdj"), wcf2));
				}
				ws.addCell(new Label(tmp+5 + num, 6 + i, "", wcf2));
				// 违纪信息
				ws.addCell(new Label(tmp+6 + num, 6 + i, zcxx.get("xjtb"), wcf2));
				ws.addCell(new Label(tmp+7 + num, 6 + i, zcxx.get("wjcf"), wcf2));
				ws.addCell(new Label(tmp+8 + num, 6 + i, zcxx.get("kkjs"), wcf2));
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 获得综测名单信息（新评奖）
	 * 
	 * @author 
	 */
	private HashMap<String, Object> getZcbjmdInfoNew(String xn,String xq, String bjdm,List<HashMap<String, String>> zcxmList) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		
		// 综测信息
		List<HashMap<String, String>> zcxxList = dao.getZcxxListNew(xn,xq, bjdm,zcxmList);

		// 学院名称
		String xymc = "";
		// 班级名称
		String bjmc = "";
		// 班级人数
		String bjrs = "0";

		map.put("xn", xn);
		map.put("xq", xq);
		map.put("zcxxList", zcxxList);

		if (zcxxList != null && zcxxList.size() > 0) {
			xymc = zcxxList.get(0).get("xymc");
			bjmc = zcxxList.get(0).get("bjmc");
			bjrs = String.valueOf(zcxxList.size());

//			// 加载评奖信息
//			setPjxx(map);
//			// 加载其他信息
			setPjxxNew(map);
			setQtxxNew(map);
		}

		map.put("bjmc", bjmc);
		map.put("bjrs", bjrs);

		return map;
	}

	/**
	 * 获得综测班级名单信息
	 * 
	 * @author luojw
	 */
	private HashMap<String, Object> getZcbjmdInfo(String xn, String bjdm) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		// 综测信息
		List<HashMap<String, String>> zcxxList = dao.getZcxxList(xn, bjdm);

		// 学院名称
		String xymc = "";
		// 班级名称
		String bjmc = "";
		// 班级人数
		String bjrs = "0";

		map.put("xn", xn);
		map.put("zcxxList", zcxxList);

		if (zcxxList != null && zcxxList.size() > 0) {
			xymc = zcxxList.get(0).get("xymc");
			bjmc = zcxxList.get(0).get("bjmc");
			bjrs = String.valueOf(zcxxList.size());

			// 加载评奖信息
			setPjxx(map);
			// 加载其他信息
			setQtxx(map);
		}

		map.put("bjmc", bjmc);
		map.put("bjrs", bjrs);

		return map;
	}
	
	/**
	 * 加载评奖信息(新评奖)
	 * 
	 * @author cmj
	 */
	@SuppressWarnings("unchecked")
	private void setPjxxNew(HashMap<String, Object> map) {

		// 学年
		String xn = (String) map.get("xn");
		String xq = (String) map.get("xq");
		// 综测信息列表
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");

		// 评奖信息列表
		List<HashMap<String, String>> pjxxList = dao.getPjxxListNew(zcxxList, xn,xq);
		// 结果值
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// 一等奖学金人数
		int ydjxjrs = 0;
		// 二等奖学金人数
		int edjxjrs = 0;
		// 三等奖学金人数
		int sdjxjrs = 0;

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {

				HashMap<String, String> zcxx = zcxxList.get(i);

				// 奖学金名称
				String jxjmc = "";
				// 荣誉称号名称
				String rychmc = "";

				if (pjxxList != null && pjxxList.size() > 0) {
					for (int j = 0; j < pjxxList.size(); j++) {
						if (zcxx.get("xh").equalsIgnoreCase(
								pjxxList.get(j).get("xh"))) {
							String xmmc = pjxxList.get(j).get("xmmc");
							String xmlx = pjxxList.get(j).get("xmlx");

							if ("01".equalsIgnoreCase(xmlx)) {// 奖学金
								if ("一等奖学金".equalsIgnoreCase(xmmc)) {
									ydjxjrs++;
								} else if ("二等奖学金".equalsIgnoreCase(xmmc)) {
									edjxjrs++;
								} else if ("三等奖学金".equalsIgnoreCase(xmmc)) {
									sdjxjrs++;
								}
								jxjmc = Base.isNull(jxjmc) ? xmmc : (jxjmc
										+ "、" + xmmc);
							} else if ("02".equalsIgnoreCase(xmlx)) {// 荣誉称号
								rychmc = Base.isNull(rychmc) ? xmmc : (rychmc
										+ "、" + xmmc);
							}
						}
					}
				}

				zcxx.put("jxjmc", jxjmc);
				zcxx.put("rychmc", rychmc);

				list.add(zcxx);
			}
		}

		map.put("ydjxjrs", ydjxjrs);
		map.put("edjxjrs", edjxjrs);
		map.put("sdjxjrs", sdjxjrs);
		map.put("zcxxList", list);
	}

	/**
	 * 加载评奖信息
	 * 
	 * @author luojw
	 */
	@SuppressWarnings("unchecked")
	private void setPjxx(HashMap<String, Object> map) {

		// 学年
		String xn = (String) map.get("xn");
		// 综测信息列表
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");

		// 评奖信息列表
		List<HashMap<String, String>> pjxxList = dao.getPjxxList(zcxxList, xn);
		// 结果值
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// 一等奖学金人数
		int ydjxjrs = 0;
		// 二等奖学金人数
		int edjxjrs = 0;
		// 三等奖学金人数
		int sdjxjrs = 0;

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {

				HashMap<String, String> zcxx = zcxxList.get(i);

				// 奖学金名称
				String jxjmc = "";
				// 荣誉称号名称
				String rychmc = "";

				if (pjxxList != null && pjxxList.size() > 0) {
					for (int j = 0; j < pjxxList.size(); j++) {
						if (zcxx.get("xh").equalsIgnoreCase(
								pjxxList.get(j).get("xh"))) {
							String xmmc = pjxxList.get(j).get("xmmc");
							String xmlx = pjxxList.get(j).get("xmlx");

							if ("01".equalsIgnoreCase(xmlx)) {// 奖学金
								if ("一等奖学金".equalsIgnoreCase(xmmc)) {
									ydjxjrs++;
								} else if ("二等奖学金".equalsIgnoreCase(xmmc)) {
									edjxjrs++;
								} else if ("三等奖学金".equalsIgnoreCase(xmmc)) {
									sdjxjrs++;
								}
								jxjmc = Base.isNull(jxjmc) ? xmmc : (jxjmc
										+ "、" + xmmc);
							} else if ("02".equalsIgnoreCase(xmlx)) {// 荣誉称号
								rychmc = Base.isNull(rychmc) ? xmmc : (rychmc
										+ "、" + xmmc);
							}
						}
					}
				}

				zcxx.put("jxjmc", jxjmc);
				zcxx.put("rychmc", rychmc);

				list.add(zcxx);
			}
		}

		map.put("ydjxjrs", ydjxjrs);
		map.put("edjxjrs", edjxjrs);
		map.put("sdjxjrs", sdjxjrs);
		map.put("zcxxList", list);
	}

	/**
	 * 加载其他信息
	 * 
	 * @author luojw
	 */
	@SuppressWarnings("unchecked")
	private void setQtxx(HashMap<String, Object> map) {
		// 学年
		String xn = (String) map.get("xn");
		// 综测信息列表
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");

		// 其他信息列表
		List<HashMap<String, String>> qtxxList = dao.getQtxxList(zcxxList, xn);
		// 结果值
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {

				HashMap<String, String> zcxx = zcxxList.get(i);

				// 不及格门数
				String bjgms = "";
				// 英语过级情况
				String yydj = "";
				// 计算机过级情况
				String jsjdj = "";
				// 校级通报
				String xjtb = "";
				// 旷课
				String kkjs = "";
				// 违纪处分
				String wjcf = "";

				if (qtxxList != null && qtxxList.size() > 0) {
					for (int j = 0; j < qtxxList.size(); j++) {
						if (zcxx.get("xh").equalsIgnoreCase(
								qtxxList.get(j).get("xh"))) {
							String mc = qtxxList.get(j).get("mc");
							String lx = qtxxList.get(j).get("lx");

							if ("bjg".equalsIgnoreCase(lx)) {// 不及格科目
								bjgms = mc;
							} else if ("yy3j".equalsIgnoreCase(lx)) {// CET3
								yydj = Base.isNull(yydj) ? ("CET3:" + mc)
										: (("CET3:" + mc) + "、" + yydj);
							} else if ("yy4j".equalsIgnoreCase(lx)) {// CET4
								yydj = Base.isNull(yydj) ? ("CET4:" + mc)
										: (("CET4:" + mc) + "、" + yydj);
							} else if ("yy6j".equalsIgnoreCase(lx)) {// CET6
								yydj = Base.isNull(yydj) ? ("CET6:" + mc)
										: (("CET6:" + mc) + "、" + yydj);
							} else if ("jsj1j".equalsIgnoreCase(lx)) {// 计算机1级
								jsjdj = Base.isNull(jsjdj) ? ("计算机一级:" + mc)
										: (("计算机一级:" + mc) + "、" + jsjdj);
							} else if ("jsj2j".equalsIgnoreCase(lx)) {// 计算机2级
								jsjdj = Base.isNull(jsjdj) ? ("计算机二级:" + mc)
										: (("计算机二级:" + mc) + "、" + jsjdj);
							} else if ("jsj3j".equalsIgnoreCase(lx)) {// 计算机3级
								jsjdj = Base.isNull(jsjdj) ? ("计算机三级:" + mc)
										: (("计算机三级:" + mc) + "、" + jsjdj);
							} else if ("xjtb".equalsIgnoreCase(lx)) {// 校级通报
								if (!Base.isNull(mc)) {
									xjtb = "有";
								}
							} else if ("kkjs".equalsIgnoreCase(lx)) {// 校级通报
								kkjs = mc;
							} else if ("wjcf".equalsIgnoreCase(lx)) {// 违纪处分
								wjcf = Base.isNull(wjcf) ? mc
										: (mc + "、" + wjcf);
							}
						}
					}
				}

				zcxx.put("bjgms", bjgms);
				zcxx.put("yydj", yydj);
				zcxx.put("jsjdj", jsjdj);
				zcxx.put("xjtb", xjtb);
				zcxx.put("kkjs", kkjs);
				zcxx.put("wjcf", wjcf);
				list.add(zcxx);
			}
		}

		map.put("zcxxList", list);
	}
	/**
	 * 加载其他信息(新评奖)
	 * 
	 * @author cmj
	 */
	@SuppressWarnings("unchecked")
	private void setQtxxNew(HashMap<String, Object> map) {
		// 学年
		String xn = (String) map.get("xn");
		String xq = (String) map.get("xq");
		// 综测信息列表
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");

		// 其他信息列表
		List<HashMap<String, String>> qtxxList = dao.getQtxxListNew(zcxxList, xn,xq);
		// 结果值
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {

				HashMap<String, String> zcxx = zcxxList.get(i);

				// 不及格门数
				String bjgms = "";
				// 英语过级情况
				String yydj = "";
				// 计算机过级情况
				String jsjdj = "";
				// 校级通报
				String xjtb = "";
				// 旷课
				String kkjs = "";
				// 违纪处分
				String wjcf = "";

				if (qtxxList != null && qtxxList.size() > 0) {
					for (int j = 0; j < qtxxList.size(); j++) {
						if (zcxx.get("xh").equalsIgnoreCase(
								qtxxList.get(j).get("xh"))) {
							String mc = qtxxList.get(j).get("mc");
							String lx = qtxxList.get(j).get("lx");

							if ("bjg".equalsIgnoreCase(lx)) {// 不及格科目
								bjgms = mc;
							} else if ("yy3j".equalsIgnoreCase(lx)) {// CET3
								yydj = Base.isNull(yydj) ? ("CET3:" + mc)
										: (("CET3:" + mc) + "、" + yydj);
							} else if ("yy4j".equalsIgnoreCase(lx)) {// CET4
								yydj = Base.isNull(yydj) ? ("CET4:" + mc)
										: (("CET4:" + mc) + "、" + yydj);
							} else if ("yy6j".equalsIgnoreCase(lx)) {// CET6
								yydj = Base.isNull(yydj) ? ("CET6:" + mc)
										: (("CET6:" + mc) + "、" + yydj);
							} else if ("jsj1j".equalsIgnoreCase(lx)) {// 计算机1级
								jsjdj = Base.isNull(jsjdj) ? ("计算机一级:" + mc)
										: (("计算机一级:" + mc) + "、" + jsjdj);
							} else if ("jsj2j".equalsIgnoreCase(lx)) {// 计算机2级
								jsjdj = Base.isNull(jsjdj) ? ("计算机二级:" + mc)
										: (("计算机二级:" + mc) + "、" + jsjdj);
							} else if ("jsj3j".equalsIgnoreCase(lx)) {// 计算机3级
								jsjdj = Base.isNull(jsjdj) ? ("计算机三级:" + mc)
										: (("计算机三级:" + mc) + "、" + jsjdj);
							} else if ("xjtb".equalsIgnoreCase(lx)) {// 校级通报
								if (!Base.isNull(mc)) {
									xjtb = "有";
								}
							} else if ("kkjs".equalsIgnoreCase(lx)) {// 校级通报
								kkjs = mc;
							} else if ("wjcf".equalsIgnoreCase(lx)) {// 违纪处分
								wjcf = Base.isNull(wjcf) ? mc
										: (mc + "、" + wjcf);
							}
						}
					}
				}

				zcxx.put("bjgms", bjgms);
				zcxx.put("yydj", yydj);
				zcxx.put("jsjdj", jsjdj);
				zcxx.put("xjtb", xjtb);
				zcxx.put("kkjs", kkjs);
				zcxx.put("wjcf", wjcf);
				list.add(zcxx);
			}
		}

		map.put("zcxxList", list);
	}
}