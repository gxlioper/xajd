package xgxt.gygl.zjjs.xszd;

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
import xgxt.form.User;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.utils.ExcelMethods;
import xgxt.utils.date.MoneyFormat;

/**
 * 深圳职业新评奖评优奖学金维护Service
 */
public class GyglXszdService extends CommService {

	GyglXszdDao dao = new GyglXszdDao();

	/**
	 * 保存走读申请
	 * 
	 * @author luojw
	 */
	public boolean saveXszdSq(GyglXszdForm model) {
		return dao.saveXszdSq(model);
	}

	/**
	 * 获得学生走读申请信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getXszdSqInfo(GyglXszdForm model) {

		String pk = (Base.isNull(model.getPk())) ? model.getXh()
				+ model.getSqsj() : model.getPk();

		return dao.getXszdSqInfo(pk);
	}

	/**
	 * 获得学生走读审核列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszdshList(GyglXszdForm model,
			String[] colList, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		ArrayList<String[]> list = dao.getXszdshList(model, colList, user);
		int page_size = model.getPages().getPageSize();
		
		int num = 0;
		
		if (list != null && list.size() > 0) {
			num = (page_size >= list.size()) ? page_size - list.size() : num;
		} else {
			num = page_size;
		}
		
		for(int i=0;i<num;i++){
			String[] rows = new String[colList.length];
			list.add(rows);
		}
		
		return list;
	}

	/**
	 * 保存学生走读审核状态
	 * 
	 * @author luojw
	 */
	public boolean saveXszdShzt(GyglXszdForm model,User user) {
		return dao.saveXszdShzt(model,user);
	}
	
	/**
	 * 批量保存学生走读核状态
	 * 
	 * @author luojw
	 */
	public boolean savePlXszdShzt(GyglXszdForm model, User user) {
		return dao.savePlXszdShzt(model, user);
	}

	/**
	 * 获得学生走读结果列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszdjgList(GyglXszdForm model,
			String[] colList, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		ArrayList<String[]> list = dao.getXszdjgList(model, colList, user);
		int page_size = model.getPages().getPageSize();
		
		int num = 0;
		
		if (list != null && list.size() > 0) {
			num = (page_size >= list.size()) ? page_size - list.size() : num;
		} else {
			num = page_size;
		}
		
		for(int i=0;i<num;i++){
			String[] rows = new String[colList.length];
			list.add(rows);
		}
		
		return list;
	}
	
	/**
	 * 打印奖学金金额统计表
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expXszdToExcle(GyglXszdForm model,User user , OutputStream os)
			throws Exception {
		
		String[] colList = new String[] { "xymc", "bjmc", "xh", "xm", "xb",
				"lxdh", "zsdd", "jtdz", "jtdh", "zdkssj", "zdjssj", "sqly" };

		List<String[]> list = dao.getXszdTjList(model, colList, user);
		
		// 设置标题
		StringBuffer title =new StringBuffer();
		title.append("走读学生统计");

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet ws = wwb.createSheet("走读统计", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 11, 0);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		// 填充表头
		ws.addCell(new Label(0, 1, "序号", wcf2));
		ws.addCell(new Label(1, 1, "系别", wcf2));
		ws.addCell(new Label(2, 1, "班级", wcf2));
		ws.addCell(new Label(3, 1, "学号", wcf2));
		ws.addCell(new Label(4, 1, "姓名", wcf2));
		ws.addCell(new Label(5, 1, "性别", wcf2));
		ws.addCell(new Label(6, 1, "学生联系电话", wcf2));
		ws.addCell(new Label(7, 1, "住宿地点", wcf2));
		ws.addCell(new Label(8, 1, "家庭居住地点", wcf2));
		ws.addCell(new Label(9, 1, "家庭联系电话", wcf2));
		ws.addCell(new Label(10, 1, "申请时间", wcf2));
		ws.addCell(new Label(11, 1, "申请理由", wcf2));
		
		ws.setColumnView(1, 20);
		ws.setColumnView(2, 20);
		ws.setColumnView(3, 20);
		ws.setColumnView(4, 20);
		ws.setColumnView(5, 20);
		ws.setColumnView(6, 20);
		ws.setColumnView(7, 20);
		ws.setColumnView(8, 20);
		ws.setColumnView(9, 20);
		ws.setColumnView(10, 40);
		ws.setColumnView(11, 20);
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String[] value = list.get(i);
				ws.addCell(new Label(0, 2 + i, String.valueOf(i+1), wcf2));
				ws.addCell(new Label(1, 2 + i, value[0], wcf2));
				ws.addCell(new Label(2, 2 + i, value[1], wcf2));
				ws.addCell(new Label(3, 2 + i, value[2], wcf2));
				ws.addCell(new Label(4, 2 + i, value[3], wcf2));
				ws.addCell(new Label(5, 2 + i, value[4], wcf2));
				ws.addCell(new Label(6, 2 + i, value[5], wcf2));

				ws.addCell(new Label(7, 2 + i, value[6], wcf2));
				ws.addCell(new Label(8, 2 + i, value[7], wcf2));
				ws.addCell(new Label(9, 2 + i, value[8], wcf2));
				String sqsj = "";
				sqsj += value[9].substring(0, 4)+"年"+value[9].substring(4, 6)+"月"+value[9].substring(6, 8)+"日";
				sqsj += "—";
				sqsj += value[10].substring(0, 4)+"年"+value[10].substring(4, 6)+"月"+value[10].substring(6, 8)+"日";
				ws.addCell(new Label(10, 2 + i, sqsj, wcf2));
				ws.addCell(new Label(11, 2 + i, value[11], wcf2));
			}
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
