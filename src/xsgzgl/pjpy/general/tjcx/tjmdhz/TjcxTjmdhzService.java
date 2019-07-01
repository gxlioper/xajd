package xsgzgl.pjpy.general.tjcx.tjmdhz;

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
import xgxt.utils.date.MoneyFormat;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.tjcx.TjcxHjjehzInterface;
import xsgzgl.pjpy.general.inter.tjcx.TjcxTjmdhzInterface;

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

public class TjcxTjmdhzService extends CommService implements
		TjcxTjmdhzInterface {

	TjcxTjmdhzDAO dao = new TjcxTjmdhzDAO();

	/**
	 * 获得表头文件
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getTjmdhzTop(TjcxTjmdhzModel model,
			User user) {
		
		DAO dao = DAO.getInstance();
		
		String[] en =null;
		
		String[] cn =null;
		
		// 学院推荐名单汇总
		en = new String[] { "pjdj", "xm", "xh", "nj", "bjrs", "dycj",
					"zcf","zcfbjpm","pjfcj","tyzdf" };
		cn = new String[] { "评奖等级",  "姓名","学号","年级", "班级<人数>", "德育成绩", "综测成绩",
					"综测排名","平均成绩","体育最低分" };
		
		
		
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得结果集
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getTjmdhzList(PjpyGeneralForm myForm,
			TjcxTjmdhzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = dao.getTjmdhzList(myForm, model, user);

		return list;
	}

	/**
	 * 构建结果集
	 * 
	 * @author 伟大的骆
	 */
	public String createTjmdhzHTML(SearchRsModel rsModel,
			TjcxTjmdhzModel model, ArrayList<String[]> rsArrList, User user) {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 导出获奖金额汇总
	 * 
	 * @author qlj
	 */
	public void expTjmdhz(PjpyGeneralForm myForm,TjcxTjmdhzModel model,User user, OutputStream os)
			throws Exception {

		String xn = model.getXn();
		String[]xmmc = model.getXmmcArr();
		String[] nj = model.getNj();
		String[] xydm = model.getXydm();
		String[] zydm = model.getZydm();
		String[] bjdm = model.getBjdm();
		String nowTime = dao.getNowTime("YYYY年MM月DD日");// 获得系统时间
		
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
		title.append("学院奖学金推荐名单汇总表");
		
		model.setType("exp");
		List<String[]>tjmdhzList=dao.getTjmdhzList(myForm, model, user);

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet( "奖学金金额汇总表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 10, 0);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 650,
				Border.NONE);
		
		ws.mergeCells(0, 1, 5, 1);
		
		ws.addCell(new Label(0, 1, "系别：", wcf2));
		
		ws.mergeCells(8, 1, 10, 1);
		ws.addCell(new Label(8, 1, "制表时间：    年   月   日", wcf2));
		// 填充表头
		ws.addCell(new Label(0, 2, "序号", wcf2));
		ws.addCell(new Label(1, 2, "评奖等级", wcf2));
		ws.addCell(new Label(2, 2, "姓名", wcf2));
		ws.addCell(new Label(3, 2, "学号", wcf2));
		ws.addCell(new Label(4, 2, "年级", wcf2));
		ws.addCell(new Label(5, 2, "班级（人数）", wcf2));
		ws.addCell(new Label(6, 2, "品德行为测评成绩", wcf2));
		ws.addCell(new Label(7, 2, "学年综合测评成绩", wcf2));
		ws.addCell(new Label(8, 2, "学年综合测评成绩班级排名", wcf2));
		ws.addCell(new Label(9, 2, "必修、限选课平均分（不含体育）", wcf2));
		ws.addCell(new Label(10, 2, "体育课最低分", wcf2));
		
		
		for(int i=0;i<tjmdhzList.size();i++){
			
			String[]tjmdArr=tjmdhzList.get(i);
			
			for(int j=0;j<tjmdArr.length;j++){
				
				ws.addCell(new Label(j, 3+i, tjmdArr[j], wcf2));
				
			}
			
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

}