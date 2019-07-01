package xgxt.pjpy.xcxy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class PjpyXcxyService {

	private PjpyXcxyDAO dao = PjpyXcxyDAO.getInstance();
	
	private static PjpyXcxyService service = new PjpyXcxyService();
	
	public static PjpyXcxyService getInstance() {
		return service;
	}
	
	/**
	 * 查询操行分表头
	 * @param fs
	 * @return
	 */
	public List<HashMap<String, String>> queryCxfTitle(String fs) {
		return dao.queryCxfTitle(fs);
	}
	
	/**
	 * 查询操行分结果
	 * @param model
	 * @param fs
	 * @return
	 */
	public List<String[]> queryCxfResult(PjpyXcxyModel model, String fs, HttpServletRequest request, PjpyXcxyActionForm pjpyForm) {
		return dao.queryCxfResult(model, fs, request, pjpyForm);
	}
	
	/**
	 * 查询操行分记录数
	 * @param model
	 * @param fs
	 * @param request
	 * @return
	 */
	public int queryCxfNum(PjpyXcxyModel model, String fs, HttpServletRequest request) {
		return dao.queryCxfNum(model, fs, request);
	}
	
	/**
	 * 删除操行分信息
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteCxf(String[] keys) throws Exception{
		return dao.deleteCxf(keys);
	}
	
	/**
	 * 操行分增加保存
	 * @param param
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public boolean cxfAddSave(List<HashMap<String, String>> param,
			String xn, String xq) throws Exception {
		return dao.cxfAddSave(param, xn, xq);
	}
	
	/**
	 * 显示详细信息
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewCxf(String pkValue) {
		return dao.viewCxf(pkValue);
	}
	
	/**
	 * 修改操行分信息
	 * 
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateCxf(String pkValue, PjpyXcxyModel model,
			HttpServletRequest request) throws Exception {
		return dao.updateCxf(pkValue, model, request);
	}
	
	/**
	 * 评奖成绩自动计算
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public boolean autoAccount(String xydm, String xn, String xq) throws Exception{
		return dao.autoAccount(xydm,xn, xq);
	}
	
	/**
	 * 评奖成绩查询结果记录数(分页用)
	 * @param model
	 * @return
	 */
	public int queryPjcjResultNum(PjpyXcxyModel model) {
		return dao.queryPjcjResultNum(model);
	}
	
	/**
	 * 评奖成绩查询结果
	 * @param model
	 * @param pjpyForm
	 * @return
	 */
	public List<String[]> queryPjcjResult(PjpyXcxyModel model,
			PjpyXcxyActionForm pjpyForm) {
		return dao.queryPjcjResult(model, pjpyForm);
	}
	
	/**
	 * 评奖成绩查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryPjcjTitle() {
		return dao.queryPjcjTitle();
	}
	
	public HashMap<String, String> queryJxjshxx(String pkVal, String userType) {
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.queryXyJxjshxx(pkVal);
		}
		return dao.queryXxJxjshxx(pkVal);
	}

	
	public boolean updateJxjxx(String pkVal, String userType, HttpServletRequest request, PjpyXcxyModel model) throws Exception{
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.updateXyjxjsh(pkVal, model, request);
		}
		return dao.updateXxjxjsh(pkVal, model, request);
	}
	
	public void jxjfpbPrint(WritableWorkbook wwb,PjpyXcxyModel model) throws Exception{
		List<String[]> jxjffbList = dao.getJxjffbList(model);
		String jxjmc = dao.getJxjmc(model.getJxjdm());
		String title = Base.xxmc + Base.getJxjsqnd() + "年度普通高等学校" + jxjmc + "发放表";
		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;
	    tStyle.setAlignment(alignMent);
	    wcfStyle.setAlignment(alignMent);
	   // tStyle.setAlignment(Alignment.LEFT);
	    WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);//设置字体
		wfTytle.setBoldStyle(WritableFont.BOLD);//设置字体加粗
		wfTytle.setPointSize(15);//设置字体大小
		tStyle.setFont(wfTytle);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    ws.addCell(new Label(0, 0, title, tStyle));//输出表头
		if (jxjffbList != null && jxjffbList.size() > 0) {
		    for (int i=0;i<jxjffbList.size();i++) {
		    	String[] tempList = jxjffbList.get(i);//获取单行数据
		    	int k = 0;
		    	for (int j = 0; j < tempList.length; j++) { //输出每行数据
		    		ws.addCell(new Label(k,i+3,tempList[j],wcfStyle));
		    		k++;
		    	}
		    }
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
	
	public void yxxsjxjffbPrint(WritableWorkbook wwb,PjpyXcxyModel model) throws Exception{
		boolean result = dao.accountYxxsjxj(model.getXn(), model.getXq());//计算优秀学生奖学金人数及金额
		if (result) {
			List<String[]> rs = dao.getYxxsjxjList();
			List<String[]> je = dao.getJxjje();
			String td = "";
			String yd = "";
			String ed = "";
			String sd = "";
			if (je != null && je.size() >= 4) {
				td = je.get(0) != null && je.get(0).length >0 ? je.get(0)[0] : "600";
				yd = je.get(1) != null && je.get(1).length >0 ? je.get(1)[0] : "350";
				ed = je.get(2) != null && je.get(2).length >0 ? je.get(2)[0] : "250";
				sd = je.get(3) != null && je.get(3).length >0 ? je.get(3)[0] : "150";
			}
			String title = model.getXn() + "学年第" + Base.getJxjsqxqmc() + "学期优秀学生奖学金发放表";
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat tStyle = new WritableCellFormat();
	 	    WritableCellFormat wcfStyle = new WritableCellFormat();
		    Alignment alignMent = Alignment.CENTRE;
		    tStyle.setAlignment(alignMent);
		    wcfStyle.setAlignment(alignMent);
		    WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);//设置字体
			wfTytle.setBoldStyle(WritableFont.BOLD);//设置字体加粗
			wfTytle.setPointSize(15);//设置字体大小
			tStyle.setFont(wfTytle);
		    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		    ws.addCell(new Label(0, 0, title, tStyle));//输出表头
		    
		    if (rs != null && rs.size() > 0) {
		    	rs.add(new String[]{"注;特等"+td+"元，一等"+yd+"元，二等"+ed+"元，三等"+sd+"元","","","","","","","","","",""});
		    	for (int i=0;i<rs.size();i++) {
		    		String[] tempList = rs.get(i);//获取单行数据
			    	int k = 0;
			    	for (int j = 0; j < tempList.length; j++) { //输出每行数据
			    		ws.addCell(new Label(k,i+3,tempList[j],wcfStyle));
			    		k++;
			    	}
		    	}
		    }
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}

	public HashMap<String, String> getTjsz(String jxjdm) {
		// TODO 自动生成方法存根
		return dao.getTjsz(jxjdm);
	}

	public boolean updateTjsz(PjpyXcxyModel model, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.updateTjsz(model,request);
	}

	public void yxxsjxjffhzPrint(WritableWorkbook wwb, PjpyXcxyModel model) throws Exception {
		// 报表打印  优秀学生奖学金发放汇总表
		boolean result = dao.accountYxxsjxj(model.getXn(), model.getXq());//计算优秀学生奖学金人数及金额
		if (result) {
		String xydm = model.getXydm();
		String xn   = model.getXn();
		HashMap<String, String> rs = dao.getXyYxjxjTjList(xydm);
		List<String[]> je = dao.getJxjje();
		String td = "";
		String yd = "";
		String ed = "";
		String sd = "";
		if (je != null && je.size() >= 4) {
			td = je.get(0) != null && je.get(0).length >0 ? je.get(0)[0] : "600";
			yd = je.get(1) != null && je.get(1).length >0 ? je.get(1)[0] : "350";
			ed = je.get(2) != null && je.get(2).length >0 ? je.get(2)[0] : "250";
			sd = je.get(3) != null && je.get(3).length >0 ? je.get(3)[0] : "150";
		}
		String title = "西昌学院"+rs.get("xymc")+"系"+xn+"学年优秀学生奖学金发放汇总表";
		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;
	    tStyle.setAlignment(alignMent);
	    WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);//设置字体
		tStyle.setFont(wfTytle);
	    ws.addCell(new Label(0, 0, title, tStyle));//输出表头
	    
	    String title2 = "系部名称（盖章）:"+rs.get("xymc")+"                     制表时间"+GetTime.getNowTime()+"单位：人，元";
	    ws.addCell(new Label(0, 1, title2, tStyle));//输出表头
	    if (rs != null && rs.size() > 0) {
		    		ws.addCell(new Label(1,3,td,wcfStyle));
		    		ws.addCell(new Label(1,4,yd,wcfStyle));
		    		ws.addCell(new Label(1,5,ed,wcfStyle));
		    		ws.addCell(new Label(1,6,sd,wcfStyle));
		    		ws.addCell(new Label(2,3,rs.get("td"),wcfStyle));
		    		ws.addCell(new Label(2,4,rs.get("yd"),wcfStyle));
		    		ws.addCell(new Label(2,5,rs.get("ed"),wcfStyle));
		    		ws.addCell(new Label(2,6,rs.get("sd"),wcfStyle));
		    		ws.addCell(new Label(3,3,rs.get("tdje"),wcfStyle));
		    		ws.addCell(new Label(3,4,rs.get("ydje"),wcfStyle));
		    		ws.addCell(new Label(3,5,rs.get("edje"),wcfStyle));
		    		ws.addCell(new Label(3,6,rs.get("sdje"),wcfStyle));
		    		String zrs = ((Integer)(Integer.parseInt(rs.get("td"))+Integer.parseInt(rs.get("yd"))+Integer.parseInt(rs.get("ed"))+Integer.parseInt(rs.get("sd")))).toString();
 		    		ws.addCell(new Label(2,7,zrs,wcfStyle));
 		    		String zje = ((Integer)(Integer.parseInt(rs.get("tdje"))+Integer.parseInt(rs.get("ydje"))+Integer.parseInt(rs.get("edje"))+Integer.parseInt(rs.get("sdje")))).toString();
		    		ws.addCell(new Label(3,7,zje,wcfStyle));
	    }
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
	}

	public void yxxsjxjffPrint(WritableWorkbook wwb, PjpyXcxyModel model) throws WriteException {
		//报表打印  优秀学生奖学金发放表
		String xydm = model.getXydm();
		String xn   = model.getXn();
		List<String[]> rs = dao.getXyYxjxjFfList(xydm,xn);
		String  xymc = dao.getXymcById(xydm);
		String title = "西昌学院"+xymc+"系"+xn+"学年优秀学生奖学金发放表";
		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;
	    tStyle.setAlignment(alignMent);
	    WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);//设置字体
		tStyle.setFont(wfTytle);
	    ws.addCell(new Label(0, 0, title, tStyle));//输出表头
	    
	    String title2 = "系部名称（盖章）:"+xymc+"                     制表时间"+GetTime.getNowTime()+"单位： 元";
	    ws.addCell(new Label(0, 1, title2, tStyle));//输出表头
	    if (rs != null && rs.size() > 0) {
	    	int zje = 0;
	    	for (int i=0;i<rs.size();i++) {
	    		String[] tempList = rs.get(i);//获取单行数据
		    	int k = 0;
		    	for (int j = 0; j < tempList.length; j++) { //输出每行数据
		    		ws.addCell(new Label(k,i+3,tempList[j],wcfStyle));
		    		k++;
		    	}
		    	zje+=Integer.parseInt(tempList[4]);
	    	}
	    	ws.addCell(new Label(0,rs.size()+3,"合计",wcfStyle));
	    	ws.addCell(new Label(4,rs.size()+3,((Integer)(zje)).toString(),wcfStyle));
	    }
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}

	public void ndzyjxjffPrint(WritableWorkbook wwb, PjpyXcxyModel model) throws Exception {
		boolean result = dao.accountZyxsjxj(model.getXn(), model.getXq());//计算优秀学生奖学金人数及金额
		if (result) {
			List<String[]> rs = dao.getZyxsjxjList();
			List<String[]> je = dao.getMyZyJxjje();
			String yd = "";
			String ed = "";
			String sd = "";
			if (je != null && je.size() >= 3) {
				yd = je.get(0) != null && je.get(0).length >0 ? je.get(0)[0] : "450";
				ed = je.get(1) != null && je.get(1).length >0 ? je.get(1)[0] : "350";
				sd = je.get(2) != null && je.get(2).length >0 ? je.get(2)[0] : "225";
			}
			String title = "西昌学院"+model.getXn() + "学年专业奖学金发放表";
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat tStyle = new WritableCellFormat();
	 	    WritableCellFormat wcfStyle = new WritableCellFormat();
		    Alignment alignMent = Alignment.CENTRE;
		    tStyle.setAlignment(alignMent);
		    wcfStyle.setAlignment(alignMent);
		    WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);//设置字体
			wfTytle.setBoldStyle(WritableFont.BOLD);//设置字体加粗
			wfTytle.setPointSize(15);//设置字体大小
			tStyle.setFont(wfTytle);
		    ws.addCell(new Label(0, 0, title, tStyle));//输出表头
		    String title2 = "系部名称（盖章）:学生处                        制表时间"+GetTime.getNowTime()+"单位：人，元";
		    ws.addCell(new Label(0, 1, title2, tStyle));//输出表头
		    if (rs != null && rs.size() > 0) {
		    	int ydrs =0;
		    	int edrs =0;
		    	int sdrs =0;
		    	int ydje =0;
		    	int edje =0;
		    	int sdje =0;
		    	int zrs =0;
		    	int zje =0;
		    	for (int i=0;i<rs.size();i++) {
		    		String[] tempList = rs.get(i);//获取单行数据
			    	ws.addCell(new Label(0,i+4,tempList[0],wcfStyle));
			    	ws.addCell(new Label(1,i+4,tempList[1],wcfStyle));
			    	ws.addCell(new Label(2,i+4,yd,wcfStyle));
			    	ws.addCell(new Label(3,i+4,"5",wcfStyle));
			    	ws.addCell(new Label(4,i+4,tempList[2],wcfStyle));
			    	ws.addCell(new Label(5,i+4,tempList[3],wcfStyle));
			    	ws.addCell(new Label(6,i+4,ed,wcfStyle));
			    	ws.addCell(new Label(7,i+4,"5",wcfStyle));
			    	ws.addCell(new Label(8,i+4,tempList[4],wcfStyle));
			    	ws.addCell(new Label(9,i+4,tempList[5],wcfStyle));
			    	ws.addCell(new Label(10,i+4,sd,wcfStyle));
			    	ws.addCell(new Label(11,i+4,"5",wcfStyle));
			    	ws.addCell(new Label(12,i+4,tempList[6],wcfStyle));
			    	ws.addCell(new Label(13,i+4,tempList[7],wcfStyle));
			    	ws.addCell(new Label(14,i+4,tempList[8],wcfStyle));
			    	ydrs+=Integer.parseInt(tempList[1]);
			    	ydje+=Integer.parseInt(tempList[2]);
			    	edrs+=Integer.parseInt(tempList[3]);
			    	edje+=Integer.parseInt(tempList[4]);
			    	sdrs+=Integer.parseInt(tempList[5]);
			    	sdje+=Integer.parseInt(tempList[6]);
			    	zrs+=Integer.parseInt(tempList[7]);
			    	zje+=Integer.parseInt(tempList[8]);
			    	
		    	}
		    	ws.addCell(new Label(0,rs.size()+4,"小计",wcfStyle));
		    	ws.addCell(new Label(1,rs.size()+4,((Integer)(ydrs)).toString(),wcfStyle));
		    	ws.addCell(new Label(4,rs.size()+4,((Integer)(ydje)).toString(),wcfStyle));
		    	ws.addCell(new Label(5,rs.size()+4,((Integer)(edrs)).toString(),wcfStyle));
		    	ws.addCell(new Label(8,rs.size()+4,((Integer)(edje)).toString(),wcfStyle));
		    	ws.addCell(new Label(9,rs.size()+4,((Integer)(sdrs)).toString(),wcfStyle));
		    	ws.addCell(new Label(12,rs.size()+4,((Integer)(sdje)).toString(),wcfStyle));
		    	ws.addCell(new Label(13,rs.size()+4,((Integer)(zrs)).toString(),wcfStyle));
		    	ws.addCell(new Label(14,rs.size()+4,((Integer)(zje)).toString(),wcfStyle));
		    }
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}

	public void yxjxjffhzPrint(WritableWorkbook wwb, PjpyXcxyModel model) throws Exception {
		// TODO 自动生成方法存根
		boolean result = dao.accountYxxsjxj(model.getXn(), model.getXq());//计算优秀学生奖学金人数及金额
		if (result) {
			List<String[]> rs = dao.getYxjxjffhzList();
			List<String[]> je = dao.getJxjje();
			String td = "";
			String yd = "";
			String ed = "";
			String sd = "";
			if (je != null && je.size() >= 4) {
				td = je.get(0) != null && je.get(0).length >0 ? je.get(0)[0] : "600";
				yd = je.get(1) != null && je.get(1).length >0 ? je.get(1)[0] : "350";
				ed = je.get(2) != null && je.get(2).length >0 ? je.get(2)[0] : "250";
				sd = je.get(3) != null && je.get(3).length >0 ? je.get(3)[0] : "150";
			}
			String title = "西昌学院"+model.getXn() + "学年优秀学生奖学金发放表";
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat tStyle = new WritableCellFormat();
	 	    WritableCellFormat wcfStyle = new WritableCellFormat();
		    Alignment alignMent = Alignment.CENTRE;
		    tStyle.setAlignment(alignMent);
		    wcfStyle.setAlignment(alignMent);
		    WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);//设置字体
			wfTytle.setBoldStyle(WritableFont.BOLD);//设置字体加粗
			wfTytle.setPointSize(15);//设置字体大小
			tStyle.setFont(wfTytle);
		    ws.addCell(new Label(0, 0, title, tStyle));//输出表头
		    String title2 = "系部名称（盖章）:学生处                        制表时间"+GetTime.getNowTime()+"单位：人，元";
		    ws.addCell(new Label(0, 1, title2, tStyle));//输出表头
		    if (rs != null && rs.size() > 0) {
		    	int tdrs =0;
		    	int tdje =0;
		    	int ydrs =0;
		    	int edrs =0;
		    	int sdrs =0;
		    	int ydje =0;
		    	int edje =0;
		    	int sdje =0;
		    	int zrs =0;
		    	int zje =0;
		    	for (int i=0;i<rs.size();i++) {
		    		String[] tempList = rs.get(i);//获取单行数据
			    	ws.addCell(new Label(0,i+4,tempList[0],wcfStyle));
			    	ws.addCell(new Label(1,i+4,tempList[1],wcfStyle));
			    	ws.addCell(new Label(2,i+4,td,wcfStyle));
			    	ws.addCell(new Label(3,i+4,tempList[2],wcfStyle));
			    	ws.addCell(new Label(4,i+4,tempList[3],wcfStyle));
			    	ws.addCell(new Label(5,i+4,yd,wcfStyle));
			    	ws.addCell(new Label(6,i+4,tempList[4],wcfStyle));
			    	ws.addCell(new Label(7,i+4,tempList[5],wcfStyle));
			    	ws.addCell(new Label(8,i+4,ed,wcfStyle));
			    	ws.addCell(new Label(9,i+4,tempList[6],wcfStyle));
			    	ws.addCell(new Label(10,i+4,tempList[7],wcfStyle));
			    	ws.addCell(new Label(11,i+4,sd,wcfStyle));
			    	ws.addCell(new Label(12,i+4,tempList[8],wcfStyle));
			    	ws.addCell(new Label(13,i+4,tempList[9],wcfStyle));
			    	ws.addCell(new Label(14,i+4,tempList[10],wcfStyle));
			    	tdrs+=Integer.parseInt(tempList[1]);
			    	tdje+=Integer.parseInt(tempList[2]);
			    	ydrs+=Integer.parseInt(tempList[3]);
			    	ydje+=Integer.parseInt(tempList[4]);
			    	edrs+=Integer.parseInt(tempList[5]);
			    	edje+=Integer.parseInt(tempList[6]);
			    	sdrs+=Integer.parseInt(tempList[7]);
			    	sdje+=Integer.parseInt(tempList[8]);
			    	zrs+=Integer.parseInt(tempList[9]);
			    	zje+=Integer.parseInt(tempList[10]);
			    	
		    	}
		    	ws.addCell(new Label(0,rs.size()+4,"小计",wcfStyle));
		    	ws.addCell(new Label(1,rs.size()+4,((Integer)(tdrs)).toString(),wcfStyle));
		    	ws.addCell(new Label(3,rs.size()+4,((Integer)(tdje)).toString(),wcfStyle));
		    	ws.addCell(new Label(4,rs.size()+4,((Integer)(ydrs)).toString(),wcfStyle));
		    	ws.addCell(new Label(6,rs.size()+4,((Integer)(ydje)).toString(),wcfStyle));
		    	ws.addCell(new Label(7,rs.size()+4,((Integer)(edrs)).toString(),wcfStyle));
		    	ws.addCell(new Label(9,rs.size()+4,((Integer)(edje)).toString(),wcfStyle));
		    	ws.addCell(new Label(10,rs.size()+4,((Integer)(sdrs)).toString(),wcfStyle));
		    	ws.addCell(new Label(12,rs.size()+4,((Integer)(sdje)).toString(),wcfStyle));
		    	ws.addCell(new Label(13,rs.size()+4,((Integer)(zrs)).toString(),wcfStyle));
		    	ws.addCell(new Label(14,rs.size()+4,((Integer)(zje)).toString(),wcfStyle));
		    }
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}

	public void myzyjxjmxPrint(WritableWorkbook wwb, PjpyXcxyModel model) throws WriteException {
//		报表打印 专业学生奖学金发放表
		String xydm = model.getXydm();
		String xn   = model.getXn();
		String nd   = Base.getJxjsqnd();
		List<String[]> rs = dao.getXyZyjxjFfList(xydm,xn);
		String  xymc = dao.getXymcById(xydm);
		String title = "西昌学院"+xymc+"系"+nd+"年度    月专业奖学金发放表";
		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;
	    tStyle.setAlignment(alignMent);
	    WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);//设置字体
		tStyle.setFont(wfTytle);
	    ws.addCell(new Label(0, 0, title, tStyle));//输出表头
	    
	    String title2 = "系部名称（盖章）:"+xymc+"                     制表时间"+GetTime.getNowTime()+"单位：人，元";
	    ws.addCell(new Label(0, 1, title2, tStyle));//输出表头
	    if (rs != null && rs.size() > 0) {
	    	int zje = 0;
	    	for (int i=0;i<rs.size();i++) {
	    		String[] tempList = rs.get(i);//获取单行数据
		    	int k = 0;
		    	for (int j = 0; j < tempList.length; j++) { //输出每行数据
		    		ws.addCell(new Label(k,i+3,tempList[j],wcfStyle));
		    		k++;
		    	}
		    	zje+=Integer.parseInt(tempList[4]);
	    	}
	    	ws.addCell(new Label(0,rs.size()+3,"合计",wcfStyle));
	    	ws.addCell(new Label(4,rs.size()+3,((Integer)(zje)).toString(),wcfStyle));
	    }
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}

	public void myzyjxjhzPrint(WritableWorkbook wwb, PjpyXcxyModel model) throws WriteException {
		// TODO 自动生成方法存根
		String xydm = model.getXydm();
		String xn   = model.getXn();
		String nd   = Base.getJxjsqnd();
		List<String[]> rs = dao.getMyzyjxjhzList(xydm,xn);
		List<String[]> je = dao.getMyZyJxjje();
		String yd = "";
		String ed = "";
		String sd = "";
		String  xymc = dao.getXymcById(xydm);
		if (je != null && je.size() >= 3) {
			yd = je.get(0) != null && je.get(0).length >0 ? je.get(0)[0] : "90";
			ed = je.get(1) != null && je.get(1).length >0 ? je.get(1)[0] : "70";
			sd = je.get(2) != null && je.get(2).length >0 ? je.get(2)[0] : "45";
		}
		String title = "西昌学院"+xymc+"系"+nd+"年    月专业奖学金发放汇总表";
		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;
	    tStyle.setAlignment(alignMent);
	    wcfStyle.setAlignment(alignMent);
	    WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);//设置字体
		wfTytle.setBoldStyle(WritableFont.BOLD);//设置字体加粗
		wfTytle.setPointSize(15);//设置字体大小
		tStyle.setFont(wfTytle);
	    ws.addCell(new Label(0, 0, title, tStyle));//输出表头
	    String title2 = "系部名称（盖章）:学生处         制表时间"+GetTime.getNowTime()+"单位：人，元";
	    ws.addCell(new Label(0, 1, title2, tStyle));//输出表头
	    if (rs != null && rs.size() > 0) {
	    	int ydrs =0;
	    	int edrs =0;
	    	int sdrs =0;
	    	int ydje =0;
	    	int edje =0;
	    	int sdje =0;
	    	int zrs =0;
	    	int zje =0;
	    	for (int i=0;i<rs.size();i++) {
	    		String[] tempList = rs.get(i);//获取单行数据
		    	ws.addCell(new Label(0,i+4,tempList[0],wcfStyle));
		    	ws.addCell(new Label(1,i+4,tempList[1],wcfStyle));
		    	ws.addCell(new Label(2,i+4,yd,wcfStyle));
		    	ws.addCell(new Label(3,i+4,tempList[2],wcfStyle));
		    	ws.addCell(new Label(4,i+4,tempList[3],wcfStyle));
		    	ws.addCell(new Label(5,i+4,ed,wcfStyle));
		    	ws.addCell(new Label(6,i+4,tempList[4],wcfStyle));
		    	ws.addCell(new Label(7,i+4,tempList[5],wcfStyle));
		    	ws.addCell(new Label(8,i+4,sd,wcfStyle));
		    	ws.addCell(new Label(9,i+4,tempList[6],wcfStyle));
		    	ws.addCell(new Label(10,i+4,tempList[7],wcfStyle));
		    	ws.addCell(new Label(11,i+4,tempList[8],wcfStyle));
		    	ydrs+=Integer.parseInt(tempList[1]);
		    	ydje+=Integer.parseInt(tempList[2]);
		    	edrs+=Integer.parseInt(tempList[3]);
		    	edje+=Integer.parseInt(tempList[4]);
		    	sdrs+=Integer.parseInt(tempList[5]);
		    	sdje+=Integer.parseInt(tempList[6]);
		    	zrs+=Integer.parseInt(tempList[7]);
		    	zje+=Integer.parseInt(tempList[8]);
		    	
	    	}
	    	ws.addCell(new Label(0,rs.size()+4,"小计",wcfStyle));
	    	ws.addCell(new Label(1,rs.size()+4,((Integer)(ydrs)).toString(),wcfStyle));
	    	ws.addCell(new Label(3,rs.size()+4,((Integer)(ydje)).toString(),wcfStyle));
	    	ws.addCell(new Label(4,rs.size()+4,((Integer)(edrs)).toString(),wcfStyle));
	    	ws.addCell(new Label(6,rs.size()+4,((Integer)(edje)).toString(),wcfStyle));
	    	ws.addCell(new Label(7,rs.size()+4,((Integer)(sdrs)).toString(),wcfStyle));
	    	ws.addCell(new Label(9,rs.size()+4,((Integer)(sdje)).toString(),wcfStyle));
	    	ws.addCell(new Label(10,rs.size()+4,((Integer)(zrs)).toString(),wcfStyle));
	    	ws.addCell(new Label(11,rs.size()+4,((Integer)(zje)).toString(),wcfStyle));
	    }
	    ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
}
