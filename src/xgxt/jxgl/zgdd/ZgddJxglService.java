package xgxt.jxgl.zgdd;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;

public class ZgddJxglService {
	ZgddJxglDAO zgddDao = new ZgddJxglDAO();

	/**
	 * @author luo
	 * @describe 是否国防生
	 */
	public boolean isGfs(String xh) throws SQLException {
		return zgddDao.isGfs(xh);
	}

	/**
	 * @author luo
	 * @describe 是否国防办
	 */
	public boolean isGfb(String zgh) throws SQLException {
		return zgddDao.isGfb(zgh);
	}

	public ArrayList<String[]> getJxjffList(GfsModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
		return zgddDao.getJxjffList(myModel,tableName);
	}

	public List<HashMap<String, String>> getJxjffTopTr(String tableName) {
		// TODO 自动生成方法存根
		return zgddDao.getJxjffTopTr(tableName);
	}

	public HashMap<String, String> getGfsjxjglOne(String pk, String xh) {
		// TODO 自动生成方法存根
		return zgddDao.getGfsjxjglOne(pk,xh);
	}

	public boolean GfsjxjglUpdate(String pk, GfsModel myModel, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return zgddDao.GfsjxjglUpdate(pk,myModel,request);
	}

	public boolean GfsjxjglDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return zgddDao.GfsjxjglDelete(pk,request);
	}
	
	
	/**
	 * @author luo
	 * @describe 获得表头
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}
	/**
	 * @author luo
	 * @describe 获得表头
	 */
	public List<HashMap<String, String>> queryTitle() throws Exception {
		String[] enList = new String[]{"xh","xm","xn","xq","bjmc","zymc","zkcs","gkms","yxms","pjf","jd","cpzf"};
		String[] cnList = new String[]{"学号","姓名", "班号","专业","学年","学期", "总课程数", 
				"挂科门数","优秀门数","平均分","学分绩点","班级综合测评"};
		return zgddDao.getTitle(enList, cnList);
	}
	/**
	 * @author luo
	 * @describe 获得全部学生信息
	 */
	public List<HashMap<String, String>> getXsList(GfsModel model,
			String[] colList) throws SQLException {
		return zgddDao.getXsList(model, colList);
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量保存国防生
	 */
	public boolean saveGfs(GfsModel model) throws Exception {
		return zgddDao.saveGfs(model);
	}
	
	/**
	 * 获取学相关信息
	 */
	public HashMap<String, String> getStuXxForXh(String xh) {				
		return  zgddDao.dao_getInfo("view_xsxxb", " where xh= '"+xh+"'");		
	}
	/**
	 * @author luo
	 * @describe 获得国防生卫生检查列表
	 */
	public List<HashMap<String, String>> getWsList(GfsModel model,
			String[] colList) throws SQLException {
		return zgddDao.getWsList(model, colList);
	}
	
	/**
	 * @author luo
	 * @describe 获得国防生卫生信息
	 */
	public HashMap<String, String> getWsListXx(String pk) throws SQLException {
		return zgddDao.getWsListXx(pk);
	}
	
	/**
	 * @author luo
	 * @describe 获得国防生卫生检查信息
	 */
	public HashMap<String, String> getWsXx(String pk) throws SQLException {
		return zgddDao.getWsXx(pk);
	}
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe 保存卫生检查信息
	 */
	boolean saveWs(GfsModel model, HttpServletRequest request) throws Exception {
		return zgddDao.saveWs(model, request);
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量删除国防生卫生信息
	 */
	public boolean delWs(String[] key) throws Exception {
		return zgddDao.delWs(key);
	}
	
	/**
	 * @author luo
	 * @describe 获得周数
	 */
	public List<HashMap<String, String>> getZsList(){
		return zgddDao.getZsList();
	}
	
	/**
	 * @author luo
	 * @describe 获得学期
	 */
	public String getXq(String xq){
		return zgddDao.getXq(xq);
	}
	
	/**
	 * @author luo
	 * @describe 打印卫生统计
	 */
	@SuppressWarnings("unchecked")
	public void printWstj(WritableWorkbook wwb, String xn, String xq,
			String zs, String xh, String nj, String xm, String xydm,
			String zydm, String bjdm, String lddm, String wsqk,String kssj,String jssj) {

		List<HashMap<String, String>> list = zgddDao.getWsList(xn, xq, zs, xh,
				nj, xm, xydm, zydm, bjdm, lddm, wsqk,kssj, jssj);

		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, "中国地质大学（武汉）国防生第" + zs + "周卫生检查统计表",
					wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			int rownum = 0;

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);
				String qk = map.get("wsqk");
				String nr = "√";

				ws.addCell(new Label(0, 4 + rownum, map.get("xh"), wcfTytle));
				ws.addCell(new Label(1, 4 + rownum, map.get("xm"), wcfTytle));
				ws.addCell(new Label(2, 4 + rownum, map.get("ldmc"), wcfTytle));
				ws.addCell(new Label(3, 4 + rownum, map.get("wsss"), wcfTytle));

				if ("优".equalsIgnoreCase(qk)) {
					ws.addCell(new Label(4, 4 + rownum, nr, wcfTytle));
					ws.addCell(new Label(5, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(6, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(7, 4 + rownum, "", wcfTytle));
				} else if ("良".equalsIgnoreCase(qk)) {
					ws.addCell(new Label(4, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(5, 4 + rownum, nr, wcfTytle));
					ws.addCell(new Label(6, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(7, 4 + rownum, "", wcfTytle));
				} else if ("中".equalsIgnoreCase(qk)) {
					ws.addCell(new Label(4, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(5, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(6, 4 + rownum, nr, wcfTytle));
					ws.addCell(new Label(7, 4 + rownum, "", wcfTytle));
				} else if ("差".equalsIgnoreCase(qk)) {
					ws.addCell(new Label(4, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(5, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(6, 4 + rownum, "", wcfTytle));
					ws.addCell(new Label(7, 4 + rownum, nr, wcfTytle));
				}

				ws.addCell(new Label(8, 4 + rownum, map.get("wsbz"), wcfTytle));

				rownum++;
			}
			
			System.out.println(rownum);
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			ws.mergeCells(0, 4+rownum, 8, 4+rownum);
			ws.addCell(new Label(0, 4 + rownum, "检查时间：" + zgddDao.getTime(kssj)
					+ "-" + zgddDao.getTime(jssj), wcfTytle));

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * @author 
	 * @describe 成绩统计
	 */
	public ArrayList<HashMap<String, String>> getCjtj(ZgddJxglForm myform,GfsModel model) throws SQLException {
		return zgddDao.getCjtj(myform,model);
	}
	/**
	 * 获取连队列表
	 */
	public List<HashMap<String, String>> serv_getLdList(){
		return zgddDao.getLdList();
	}
	/**
	 * 获取早操情况评分列表
	 */
	public List<HashMap<String, String>> serv_getZcPfList(){
		return zgddDao.getZcPfList();
	}
	
	/**
	 *  出勤信息删除
	 */
	public boolean ser_gfscqDelete(String delPk) throws Exception{
		return zgddDao.dao_gfscqDelete(delPk);
	}
	/**
	 * 获取学生相关信息
	 */
	public HashMap<String,String> serv_getXsInfo(String xh){
		String querry = " where xh='"+xh+"' ";
		return zgddDao.dao_getInfo("view_xsxxb", querry);
	}
	/**
	 * 获取学生相关出勤信息
	 */
	public HashMap<String,String> serv_getXsCqInfo(String xh){
		String querry = " where xh||jcsj='"+xh+"' ";
		return zgddDao.dao_getInfo("view_zgdzdx_gfscqxx", querry);
	}
	/**
	 * 出勤信息添加保存
	 */
	public boolean serv_gfscqAddSave(CqglModel model) throws Exception{
		return zgddDao.gfscqAddSave(model);
	}
	/**
	 * 出勤信息修改保存
	 */
	public boolean serv_gfscqModiSave(CqglModel model,String pkValue) throws Exception{
		return zgddDao.gfscqModiSave(model,pkValue);
	}
	/**
	 * 获取查询表头
	 */
	public ArrayList<HashMap<String, String>> dao_getSearchTitle( ) {
		return zgddDao.dao_getSearchTitle();
	}

	/**
	 * 出勤信息查询
	 */
	public ArrayList<String[]> serv_cqqkDefault(CqglModel model){
		return zgddDao.dao_cqqkDefault(model);
	}
	
	public void serv_expData(CqglModel model,WritableWorkbook wwb) throws Exception{
		WritableSheet ws = wwb.createSheet("国防生早操出勤统计表", 0);
		
		WritableCellFormat wcf1 = new WritableCellFormat(); // 构造单元格格式		
		wcf1 = ExcelMethods.getWcf(WritableFont.TIMES,20,true,Alignment.CENTRE,VerticalAlignment.CENTRE,true,Border.TOP);			

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式		
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,Border.ALL);			
		//填充标题
		ws.mergeCells(0, 0, 9, 2);
		ws.setRowView(0, 650); // 设置指定行高
		ws.addCell(new Label(0, 0,"中国地质大学（武汉）国防生早操出勤统计表" ,wcf1)); // 添加有指定格式的单元格值	
		//填充表头
		ws.mergeCells(0, 3, 0, 4);
		ws.addCell(new Label(0, 3,"学号" ,wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(1, 3, 1, 4);
		ws.addCell(new Label(1, 3,"姓名" ,wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(2, 3, 2, 4);
		ws.addCell(new Label(2, 3,"连队" ,wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(3, 3, 3, 4);
		ws.addCell(new Label(3, 3,"宿舍" ,wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(4, 3, 7, 3);
		ws.addCell(new Label(4, 3,"早操情况" ,wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(4, 4,"优" ,wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(5, 4,"良" ,wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(6, 4,"中" ,wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(7, 4,"差" ,wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(8, 3, 8, 4);
		ws.addCell(new Label(8, 3,"出勤情况" ,wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(9, 3, 9, 4);
		ws.addCell(new Label(9, 3,"备注" ,wcf2)); // 添加有指定格式的单元格值
		//填充内容								
		List<HashMap<String,String>> listV = zgddDao.dao_expData(model);
		if(listV.size()==0||listV==null){
			//填充标题
			ws.mergeCells(0, 5, 9, 5);
			ws.setRowView(0, 650); // 设置指定行高
			ws.addCell(new Label(0, 5,"未找到任何数据！" ,wcf1)); // 添加有指定格式的单元格值			
		}else{
			for(int i=0;i<listV.size();i++){
				ws.addCell(new Label(0,5+i ,listV.get(i).get("xh"),wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(1,5+i ,listV.get(i).get("xm"),wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(2,5+i ,listV.get(i).get("ldmc"),wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(3,5+i ,listV.get(i).get("szss"),wcf2)); // 添加有指定格式的单元格值
				if("优".equalsIgnoreCase(listV.get(i).get("zcqk"))){
					ws.addCell(new Label(4,5+i ,"√",wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(5,5+i ," ",wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(6,5+i ," ",wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(7,5+i ," ",wcf2)); // 添加有指定格式的单元格值
				}else if("良".equalsIgnoreCase(listV.get(i).get("zcqk"))){
					ws.addCell(new Label(4,5+i ,"",wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(5,5+i ,"√",wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(6,5+i ," ",wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(7,5+i ," ",wcf2)); // 添加有指定格式的单元格值
				}else if("中".equalsIgnoreCase(listV.get(i).get("zcqk"))){
					ws.addCell(new Label(4,5+i ," ",wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(5,5+i ," ",wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(6,5+i ,"√",wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(7,5+i ," ",wcf2)); // 添加有指定格式的单元格值
				}else{
					ws.addCell(new Label(4,5+i ," ",wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(5,5+i ," ",wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(6,5+i ," ",wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(7,5+i ,"√",wcf2)); // 添加有指定格式的单元格值
				}
				
				
				ws.addCell(new Label(8,5+i ,listV.get(i).get("cqqk"),wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(9,5+i ,listV.get(i).get("bz"),wcf2)); // 添加有指定格式的单元格值				
			}
		}
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String jcsj = "";
		if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
			jcsj = kssj+"--"+jssj;
		}else if(!Base.isNull(kssj)){
			jcsj = kssj;
		}else if(!Base.isNull(jssj)){
			jcsj = jssj;
		}
		ws.mergeCells(0,listV.size()+6, 9, listV.size()+6);
		ws.addCell(new Label(0, listV.size()+6,"检查时间："+jcsj )); // 添加有指定格式的单元格值
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * @author 
	 * @describe 成绩统计
	 */
	public ArrayList<HashMap<String, String>> getCjtjexp(ZgddJxglForm myform,GfsModel model) throws SQLException {
		return zgddDao.getCjtjexp(myform,model);
	}
	/**
	 * @author 
	 * @describe 成绩统计分页
	 */
	public String getCjtjfy(ZgddJxglForm myform,GfsModel model) throws SQLException {
		return zgddDao.getCjtjfy(myform,model);
	}
	/**
	 * @author 
	 * @throws Exception 
	 * @describe 打印
	 */
	public void cjPrint(WritableWorkbook wwb, ArrayList<HashMap<String, String>> list) throws Exception {

		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			int rownum = 0;

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);

				ws.addCell(new Label(0, 3 + rownum, map.get("xm"), wcfTytle));
				ws.addCell(new Label(1, 3 + rownum, map.get("xh"), wcfTytle));
				ws.addCell(new Label(2, 3 + rownum, map.get("bjmc"), wcfTytle));
				ws.addCell(new Label(3, 3 + rownum, map.get("zymc"), wcfTytle));
				ws.addCell(new Label(4, 3 + rownum, map.get("xn"), wcfTytle));
				ws.addCell(new Label(5, 3 + rownum, map.get("xq"), wcfTytle));
				ws.addCell(new Label(6, 3 + rownum, map.get("zkcs"), wcfTytle));
				ws.addCell(new Label(7, 3 + rownum, map.get("gkms"), wcfTytle));
				ws.addCell(new Label(8, 3 + rownum, map.get("yxms"), wcfTytle));
				ws.addCell(new Label(9, 3 + rownum, map.get("pjf"), wcfTytle));
				ws.addCell(new Label(10, 3 + rownum, map.get("jd"), wcfTytle));
				ws.addCell(new Label(11,3 + rownum, map.get("cpzf"), wcfTytle));
				
				rownum++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
