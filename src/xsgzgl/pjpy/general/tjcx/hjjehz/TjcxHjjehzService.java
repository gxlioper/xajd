package xsgzgl.pjpy.general.tjcx.hjjehz;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.tjcx.TjcxHjjehzInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_获奖金额汇总_通用_Service类
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

public class TjcxHjjehzService extends CommService implements
		TjcxHjjehzInterface {

	TjcxHjjehzDAO dao = new TjcxHjjehzDAO();

	/**
	 * 获得表头文件
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getHjjehzTop(TjcxHjjehzModel model,
			User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "xn", "xh", "xm", "nj", "bjmc", "xmlx",
				"xmmc", "hdsj", "xmje" };
		String[] cn = new String[] { "学年", "学号", "姓名", "年级", "班级名称", "项目类型",
				"项目名称", "获得时间", "项目金额" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得结果集
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getHjjehzList(PjpyGeneralForm myForm,
			TjcxHjjehzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = dao.getHjjehzList(myForm, model, user);

		return list;
	}

	/**
	 * 构建结果集
	 * 
	 * @author 伟大的骆
	 */
	public String createHjjehzHTML(SearchRsModel rsModel,
			TjcxHjjehzModel model, ArrayList<String[]> rsArrList, User user) {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 导出获奖金额汇总
	 * 
	 * @author 伟大的骆
	 */
	public void expHjjehz(TjcxHjjehzModel model, OutputStream os)
			throws Exception {

		String xn = model.getXn();
		String[]xmmc = model.getXmmcArr();
		String[] nj = model.getNj();
		String[] xydm = model.getXydm();
		String[] zydm = model.getZydm();
		String[] bjdm = model.getBjdm();
		String nowTime = dao.getNowTime("YYYY年MM月DD日");// 获得系统时间
		
		List<HashMap<String,String>>xmxzList=dao.getXmxzList();
		
		List<HashMap<String,String>>xmmcList=dao.getXmmcBylx("02", xn);
		
		// 学期名称
		String xqmc = getOneValue("xqdzb", "xqmc", "xqdm", "02");

		// 奖学金名称
		//String jxjmc = xmmc;

		// 获得银行名称
		String yhmc = "中国农业银行";

		// 设置标题
		StringBuffer title = new StringBuffer();
		title.append(xn);
		title.append("学年");
		title.append(xqmc);
		title.append("学期");
		//title.append(jxjmc);
		title.append("获奖学生发放金额汇总细表");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet( "奖学金金额汇总表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 6+xmxzList.size()+xmmcList.size(), 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 650,
				Border.NONE);

		// 填充表头
		ws.addCell(new Label(0, 2, "序号", wcf2));
		ws.addCell(new Label(1, 2, "学生姓名", wcf2));
		ws.addCell(new Label(2, 2, "院系", wcf2));
		ws.addCell(new Label(3, 2, "专业", wcf2));
		
		
		for(int i=0;i<xmxzList.size();i++){
			HashMap<String,String>xmxzMap=xmxzList.get(i);
			ws.addCell(new Label(4+i, 2, xmxzMap.get("xzmc")+"获得者", wcf2));
		}
		
		for(int i=0;i<xmmcList.size();i++){
			HashMap<String,String>xmmcMap=xmmcList.get(i);
			ws.addCell(new Label(4+xmxzList.size()+i, 2, xmmcMap.get("xmmc")+"获得者", wcf2));
		}
		
		ws.addCell(new Label(4+xmxzList.size()+xmmcList.size(), 2, "金额", wcf2));
		ws.addCell(new Label(5+xmxzList.size()+xmmcList.size(), 2, yhmc + "卡号", wcf2));
		ws.addCell(new Label(6+xmxzList.size()+xmmcList.size(), 2, "合计", wcf2));

		// 奖学金金额统计列表
		List<HashMap<String, String>> list = dao.getJxjjeList(xn, xmmc, nj,
				xydm, zydm, bjdm);

		int zjje = 0;// 总计金额

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				ws.addCell(new Label(0, 3 + i, String.valueOf(i + 1), wcf2));// 序号
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcf2));// 姓名
				ws.addCell(new Label(2, 3 + i, map.get("xymc"), wcf2));// 学院
				ws.addCell(new Label(3, 3 + i, map.get("zymc"), wcf2));// 专业
				
				for(int j=0;j<xmxzList.size();j++){
					HashMap<String,String>xmxzMap=xmxzList.get(j);
					ws.addCell(new Label(4+j, 3 + i, map.get("jxjmc_"+j), wcf2));
				}
				
				for(int j=0;j<xmmcList.size();j++){
					HashMap<String,String>xmxzMap=xmxzList.get(j);
					ws.addCell(new Label(4+xmxzList.size()+j, 3 + i, map.get("rychmc_"+j), wcf2));
				}
				
				ws.addCell(new Label(4+xmxzList.size()+xmmcList.size(), 3 + i, map.get("je"), wcf2));// 金额
				ws.addCell(new Label(5+xmxzList.size()+xmmcList.size(), 3 + i, map.get("yhkh"), wcf2));// 银行卡号
				ws.addCell(new Label(6+xmxzList.size()+xmmcList.size(), 3 + i, map.get("zj"), wcf2));// 合计

				// 累加金额，计算总计金额
				if (!Base.isNull(map.get("je"))) {
					zjje += Integer.parseInt(map.get("je"));
				}
			}

			// 表尾信息
			if(zjje==0){
				ws.addCell(new Label(0, list.size() + 3, "合计：零"));// 总计金额
				ws.mergeCells(0, list.size() + 3,6+xmxzList.size()+xmmcList.size(), list.size() + 3);
			}else{
				ws.addCell(new Label(0, list.size() + 3, "合计："
						+ MoneyFormat.format(""+zjje), wcf2));// 总计金额
				ws.mergeCells(0, list.size() + 3, 6+xmxzList.size()+xmmcList.size(), list.size() + 3);
			}

			WritableCellFormat wcf3 = new WritableCellFormat(); // 构造单元格格式
			wcf3 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.LEFT, VerticalAlignment.CENTRE, true, null);

			StringBuffer msg = new StringBuffer();
			msg.append("制表人：");
			msg.append("                         ");
			msg.append("证明人：");
			msg.append("                          ");
			msg.append("制表时间：" + nowTime);

			ws.addCell(new Label(0, list.size() + 5, msg.toString(), wcf3));// 总计金额,证明人,制表时间
			ws.mergeCells(0, list.size() + 5, 6+xmxzList.size()+xmmcList.size(), list.size() + 5);

			ws.addCell(new Label(0, list.size() + 7, "部门领导签字：", wcf3));// 部门领导签字
			ws.mergeCells(0, list.size() + 7, 6+xmxzList.size()+xmmcList.size(), list.size() + 7);

			ws.addCell(new Label(0, list.size() + 9, "校领导签字：", wcf3));// 校领导签字
			ws.mergeCells(0, list.size() + 9, 6+xmxzList.size()+xmmcList.size(), list.size() + 9);

			for (int i = 0; i < list.size(); i++) {
				WritableCell h = ws.getWritableCell(6+xmxzList.size()+xmmcList.size(), 3 + i);// 合计
				//System.out.println(i + "@@@" + h.getContents());
				
				String value = h.getContents();
			
				String rowspan = value.split("luojw")[1];
				String zj = value.split("luojw")[2];
				String row = list.get(i).get("num");
				
				if ("1".equalsIgnoreCase(row)) {
					ws.mergeCells(6+xmxzList.size()+xmmcList.size(), 3 + i, 6+xmxzList.size()+xmmcList.size(), 3 + i
							+ Integer.parseInt(rowspan) - 1);
					ws.addCell(new Label(6+xmxzList.size()+xmmcList.size(), 3 + i, zj, wcf2));// 合计
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 获得结果集
	 * 
	 * @author 程强
	 */
	public ArrayList<String[]> getCMHJMDHZList(PjpyGeneralForm myForm,
			TjcxHjjehzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = dao.getCMHJMDHZList(myForm, model, user);

		return list;
	}
	
	/**
	 * 获得表头文件
	 * 
	 * @author 程强
	 */
	public List<HashMap<String, String>> getCMHJMDHZTop(TjcxHjjehzModel model,
			User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "bjmc","xm","jxj","shxs","yxxsgb","dxjhdz",
				"ffje", "yhkh", "bz" };
		String[] cn = new String[] { "班级名称", "姓名", "院内奖学金", "三号学生", "优秀学生干部", "单项奖",
				"发放金额", "银行卡号", "备注" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}
	
	/**
	 * 导出获奖金额汇总
	 * 
	 * @author 程强
	 */
	public void expCmhjmchz(TjcxHjjehzModel model, OutputStream os)
			throws Exception {

		String xn = model.getXn();
		String[] nj = model.getNj();
		String[] xydm = model.getXydm();
		String[] zydm = model.getZydm();
		String[] bjdm = model.getBjdm();
		
		
		
		// 学期名称
		String xqmc = "第一";

		// 学校名称
		String xxmc = "浙江传媒学院";


		// 获奖名单统计列表
		List<HashMap<String, String>> list = dao.getCmhjmdList(xn, nj,
				xydm, zydm, bjdm);
		
		// 设置标题
		StringBuffer title = new StringBuffer();
		title.append(xxmc);
		title.append(xn);
		title.append("学年");
		title.append(xqmc);
		title.append("学期");
		title.append(list != null && list.size() > 0 && list.get(0)!=null && list.get(0).get("xymc") !=null ? list.get(0).get("xymc") :"");
		title.append("奖评优名单汇总表");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet( "奖学金金额汇总表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 9, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		// 填充表头
		ws.addCell(new Label(0, 2, "班级", wcf2));
		ws.addCell(new Label(1, 2, "姓名", wcf2));
		ws.addCell(new Label(2, 2, "院内奖学金获得者（一、二、三等）", wcf2));
		ws.addCell(new Label(3, 2, "三好学生获得者", wcf2));
		ws.addCell(new Label(4, 2, "优秀学生干部获得者", wcf2));
		ws.addCell(new Label(5, 2, "单项奖获得者", wcf2));
		ws.addCell(new Label(6, 2, "院外奖学金获得者", wcf2));
		ws.addCell(new Label(7, 2, "发放金额", wcf2));
		ws.addCell(new Label(8, 2, "学生建行卡号", wcf2));
		ws.addCell(new Label(9, 2, "备注", wcf2));
		
		

		int zjje = 0;// 总计金额

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				ws.addCell(new Label(0, 3 + i, map.get("bjmc"), wcf2));// 序号
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcf2));// 姓名
				ws.addCell(new Label(2, 3 + i, map.get("jxj"), wcf2));// 学院
				ws.addCell(new Label(3, 3 + i, map.get("shxs"), wcf2));// 专业
				ws.addCell(new Label(4, 3 + i, map.get("yxxsgb"), wcf2));// 学院
				ws.addCell(new Label(5, 3 + i, map.get("dxjhdz"), wcf2));// 学院
				ws.addCell(new Label(6, 3 + i, "", wcf2));// 学院
				ws.addCell(new Label(7, 3 + i, map.get("ffje"), wcf2));// 学院
				ws.addCell(new Label(8, 3 + i, map.get("yhkh"), wcf2));// 学院
				ws.addCell(new Label(9, 3 + i, map.get("bz"), wcf2));// 学院
				
				
				// 累加金额，计算总计金额
				if (!Base.isNull(map.get("ffje"))) {
					zjje += Integer.parseInt(map.get("ffje"));
				}
			}

		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

}