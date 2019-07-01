package xgxt.jxgl.cdty.gfjy;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.gygl.tjfx.GyglTjfxForm;
import xgxt.sjdrdc.ExportAndImportModel;
import xgxt.sjdrdc.ImportDataDAO;
import xgxt.sjdrdc.ImportDataService;
import xgxt.sjdrdc.check.IimportCheck;
import xgxt.sjdrdc.check.NotNullAndLengthCheck;
import xgxt.utils.Arrays2;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class CdtyGfjyService extends CommService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	CdtyGfjyDAO dao = new CdtyGfjyDAO();

	/**
	 * 删除国防课程(2011.11.15 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delGfkc(CdtyGfjyForm myForm) throws Exception {

		return dao.delGfkc(myForm);
	}

	/**
	 * 获取表头(2011.11.15 qlj)
	 * 
	 * @param lx
	 * @param rForm
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String lx, RequestForm rForm,
			CdtyGfjyForm myForm) {

		DAO Cdao = DAO.getInstance();

		String[] en = null;
		String[] cn = null;
		if ("kfkc".equalsIgnoreCase(lx)) {
			en = new String[] { "guid", "nd", "kcmc", "bl" };
			cn = new String[] { "编号", "年度", "课程名称", "比例" };
		} else if ("kcf".equalsIgnoreCase(lx)) {

			List<HashMap<String, String>> kcList = dao.getKcList();
			List<String> enL = new ArrayList<String>();
			List<String> cnL = new ArrayList<String>();
			for (int i = 0; i < kcList.size(); i++) {
				HashMap<String, String> kcMap = kcList.get(i);
				enL.add("guid_kc_" + i);
				cnL.add(kcMap.get("kcmc"));
			}
			en = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc" };
			cn = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY, "专业", "班级" };
			en = Cdao.unionArray(en, enL.toArray(new String[] {}));
			cn = Cdao.unionArray(cn, cnL.toArray(new String[] {}));
		} else if ("zf".equalsIgnoreCase(lx)) {

			List<HashMap<String, String>> kcList = dao.getEverKcList(myForm);
			List<String> enL = new ArrayList<String>();
			List<String> cnL = new ArrayList<String>();
			for (int i = 0; i < kcList.size(); i++) {
				HashMap<String, String> kcMap = kcList.get(i);
				enL.add("guid_kc_" + i);
				cnL.add(kcMap.get("kcmc"));
			}
			enL.add("zf");
			cnL.add("总分");
			Base.YXPZXY_KEY = message.getMessage("lable.xb");
			en = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc" };
			cn = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY , "专业", "班级" };
			en = Cdao.unionArray(en, enL.toArray(new String[] {}));
			cn = Cdao.unionArray(cn, cnL.toArray(new String[] {}));
		}
		rForm.setColList(cn);

		return Cdao.arrayToList(en, cn);
	}

	/**
	 * 获取国防教育课程信息列表(2011.11.15 qlj)
	 * 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getGfkcList(CdtyGfjyForm myForm) throws Exception {

		return dao.getGfkcList(myForm);
	}

	/**
	 * 保存国防课程名称(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveKcxx(CdtyGfjyForm myForm) throws Exception {

		return dao.saveKcxx(myForm);
	}

	/**
	 * 修改课程信息(2011.11.15 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateKcxx(CdtyGfjyForm myForm) throws Exception {

		return dao.updateKcxx(myForm);
	}

	/**
	 * 获取课程信息信息
	 * 
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getOneKcmc(CdtyGfjyForm myForm) {
		return dao.getOneKcmc(myForm);
	}

	/**
	 * 获取本年度的国防教育课程列表
	 * 
	 * @return
	 */
	public ArrayList<String[]> getGfcjList(CdtyGfjyForm myForm)
			throws Exception {

		return dao.getGfcjList(myForm);
	}

	/**
	 * 获取本年度的国防教育课程列表
	 * 
	 * @return
	 */
	public boolean saveGfjyf(CdtyGfjyForm myForm) throws Exception {

		// 评奖项目审核表
		String tableName = "xg_jxgl_gfjyfb";
		// 主键
		String pk = "xh";
		// 主键值
		String[] pkValue = myForm.getXhArr();
		// 修改字段
		String[] arrzd = new String[] { "xh", "nj", "kcdm", "fs" };
		// /审核时间

		String[] xh = myForm.getXhArr();
		String[] nj = myForm.getNjArr();
		String[] fs = myForm.getFsArr();

		List<HashMap<String, String>> kcmList = dao.getKcList();
		String[] xhArr = new String[xh.length * (kcmList.size())];
		String[] njArr = new String[xh.length * (kcmList.size())];
		String[] kcdmArr = new String[xh.length * (kcmList.size())];
		String[] fsArr = new String[xh.length * (kcmList.size())];
		int n = 0;
		for (int j = 0; j < xh.length; j++) {
			for (int i = 0; i < kcmList.size(); i++) {
				HashMap<String, String> kcmMap = kcmList.get(i);
				xhArr[n] = xh[j];
				njArr[n] = nj[j];
				kcdmArr[n] = kcmMap.get("guid");
				fsArr[n] = fs[n];
				n++;
			}
		}

		CdtyGfjyModel model = new CdtyGfjyModel();
		model.setXh(xhArr);
		model.setNj(njArr);
		model.setKcdm(kcdmArr);
		model.setFs(fsArr);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);

		return this.saveInfoToDb(saveForm, model, myForm.getUser());

	}

	/**
	 * 获取本年度的国防教育课程列表
	 * 
	 * @return
	 */
	public List<String[]> getGfcj(CdtyGfjyForm myForm, String lx)
			throws Exception {

		return dao.getGfcj(myForm, lx);
	}

	/**
	 * Method printNjfbtj 宿舍年级分布情况统计导出表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * 
	 */
	public void printGfjyf(CdtyGfjyForm myForm, HttpServletRequest request,
			WritableWorkbook wwb) throws Exception {

		RequestForm rForm = new RequestForm();
		List<String[]> gfjyfList = getGfcj(myForm, "tj");
		getTopTr("zf", rForm,myForm);
		String[] topTr = rForm.getColList();

		try {
			// 创建xls中SHEET对象
			WritableSheet ws = wwb.createSheet("国防教育分", 0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// 设置对齐方式
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(0, 0, topTr.length-1, 0);
			ws.addCell(new Label(0, 0, "国防教育分", wcfTytle));

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
			wcfTytle.setWrap(true);
			// 设置表格边框
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			for (int i = 0; i < topTr.length; i++) {

				ws.addCell(new Label(i, 1, topTr[i], wcfTytle));
			}

			for (int i = 0; i < gfjyfList.size(); i++) {

				String[] gfjyfArr = gfjyfList.get(i);
				for (int j = 0; j < gfjyfArr.length; j++) {

					ws.addCell(new Label(j, i + 2, gfjyfArr[j], wcfTytle));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 导出传参数
	 * @param sql
	 * @param tabName
	 * @param inputList
	 * @param os
	 * @param sheetTitle
	 * @throws Exception
	 */
	public  void exportDataIn(OutputStream os,String drnj)
	throws Exception {
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("课程分导入", 0);
		
		List<HashMap<String,String>>drzdList=dao.getDrzdList( drnj);
		try {
			
			ws.addCell(new Label(0, 0, "年级"));
			ws.addCell(new Label(1, 0, "学号"));
			
			for (int i = 0; i < drzdList.size(); i++) {
				
				HashMap<String,String>drzdMap=drzdList.get(i);
				
				ws.addCell(new Label( i + 2,0, drzdMap.get("kcmc")));
				
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	
	public List<HashMap<String,String>> getImportZd(String drnj){
		
		List<HashMap<String,String>>zdList=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>drzdList=dao.getDrzdList(drnj);
		
		HashMap<String,String>zdMap=new HashMap<String,String>();
		zdMap.put("column_name", "nj");
		zdMap.put("comments", "年级");
		zdList.add(zdMap);
		
		zdMap=new HashMap<String,String>();
		zdMap.put("column_name", "xh");
		zdMap.put("comments", "学号");
		zdList.add(zdMap);
		
		for(int i=0;i<drzdList.size();i++){
			
			zdMap=new HashMap<String,String>();
			HashMap<String,String>drzdMap=drzdList.get(i);
			
			zdMap.put("column_name", drzdMap.get("guid"));
			zdMap.put("comments", drzdMap.get("kcmc"));
			zdList.add(zdMap);
			
		}
		
		return zdList;
		
	}
	
	
	/**
	 * 获取课程列表
	 * @return
	 */
	public List<HashMap<String,String>>getKcList(){
		return  dao.getKcList();
	}
	
	/**
	 * 把excel的数据保存到数据库,如果有违反唯一性约束的情况，就以excel格式返回
	 * @param path
	 * @param tableName
	 * @param mappingItems
	 * @param response         
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> saveExcel2Db(ExportAndImportModel model, HttpServletResponse response, String[] yzArr,String drnj){		
		String xxdm = Base.xxdm;
		String path = model.getFilePath();
		String tableName = model.getRealTable();
		String[][] splitMappingOne = getSplitMapping(model);
		ImportDataService importDataService = new ImportDataService();
		ImportDataDAO dao = new ImportDataDAO();

		List<String[]> list = null;
		try{			
			list = importDataService.checkExcelDataComm(model);//数据检测
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(path);
			ArrayList<String[]> excelData = new ArrayList<String[]>();//用于保存从excel获得的数据
			
			for(int rownum=1;rownum<sourceRowCount;rownum++){//每条记录
				boolean flag = true;
				for(int i = 0; i<list.size(); i++){
					String[] columns = (String[]) list.get(i);
					if(rownum==Integer.parseInt(columns[1])){
						flag = false;
						break;
					}
				}
				if(flag== true){
				//添加要保存记录   start
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				
				if(NotNullAndLengthCheck.checkNoValue(row)){
					continue;
				}
				
				String[] tmpRow = new String[row.length+1];
				tmpRow[0] = String.valueOf(rownum);		
				//去空格
				
				Arrays2.copy(row, tmpRow,row.length, 0, 1);
				tmpRow = trim(tmpRow);
				excelData.addAll(getImportValue(model,tmpRow,drnj));
                //添加要保存记录   end
				}
			}
			ArrayList<String[]> violateOnlyOne = null;				
			
			violateOnlyOne =  dao.saveData(excelData, splitMappingOne,tableName);
			
			if(violateOnlyOne != null && violateOnlyOne.size()>0){//如果有违反唯一性约束的数据就加入到有问题数据列表中
				list.addAll(violateOnlyOne);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	/**
	 * 去空格
	 * */
	public String[] trim(String[] args){
		for(int i=0; i<args.length; i++){
			args[i] = StringUtils.isNotNull(args[i]) ? args[i].trim() : args[i];
		}
		return args;
	}
	
	public String[][] getSplitMapping(ExportAndImportModel model){
		
		String mapping=model.getMappingItems();
		
		String[] mappingOne = mapping.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[4][2];
		
		int n=0;
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			
			String[]mappingTwo=mappingOne[mappingOneNum].split("!!SplitTwo!!");
			
			if(mappingTwo[1].equals("nj") || mappingTwo[1].equals("xh")){
				splitMappingOne[n] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
				n++;
			}
		}
		
		
		splitMappingOne[n][0]=String.valueOf(n);
		splitMappingOne[n][1]="kcdm";
		
		splitMappingOne[++n][0]=String.valueOf(n);
		splitMappingOne[n][1]="fs";
		
		return splitMappingOne;
	}
	
	public List<String[]> getImportValue(ExportAndImportModel model,String[] tmpRow,String drnj){
		
		DAO dao=DAO.getInstance();
		
		String mapping=model.getMappingItems();
		
		String[] mappingOne = mapping.split("!!SplitOne!!");
		
		List<String>checkedValue=new ArrayList<String>();
		
		List<String[]>importV=new ArrayList<String[]>();
		
		int n=0;
		checkedValue.add("0");
		
		boolean flag=false;
		
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
			
			String[]mappingTwo=mappingOne[mappingOneNum].split("!!SplitTwo!!");
			
			if(mappingTwo[1].equals("nj") ){
				
				checkedValue.add(tmpRow[mappingOneNum+1]);
				
				if(!Base.isNull(drnj) && drnj.equalsIgnoreCase(tmpRow[mappingOneNum+1])){
					flag=true;
				}
				
			}else if( mappingTwo[1].equals("xh")){
				
				checkedValue.add(tmpRow[mappingOneNum+1]);
				
			}
			
		}
		
		if(flag){
			for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//分割对应列信息
				
				String[]mappingTwo=mappingOne[mappingOneNum].split("!!SplitTwo!!");
				
				if(!mappingTwo[1].equals("nj") && !mappingTwo[1].equals("xh")){
					
					String[]valueArr=checkedValue.toArray(new String[]{});
					
					String kcdm = mappingOne[mappingOneNum].split("!!SplitTwo!!")[1];
					String fs = tmpRow[mappingOneNum+1];
					
					String[]kmfs=new String[]{kcdm,fs};
					
					importV.add(dao.unionArray(valueArr, kmfs));
				}
				
				n++;
			}
		}
	
		return importV;
	}
	
	/**
	 * 把excel的数据更新到数据库,如果有违反唯一性约束的情况，就以excel格式返回
	 * @param path
	 * @param tableName
	 * @param mappingItems
	 * @param response
	 * @param os         
	 * @return           是否导入成功，成功为：true 否则：false
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> updateExcel2Db(ExportAndImportModel model,HttpServletResponse response, String[] yzArr,String drnj){
		//boolean result = true;//导入成功
		String xxdm = Base.xxdm;
		String path = model.getFilePath();
		String tableName = model.getRealTable();
		
		ImportDataDAO dao = new ImportDataDAO();
		String[][] splitMappingOne =getSplitMapping(model);
		
		List<String[]> list = null;
		try{
			//list = checkExcelDataComm(model);//数据检测
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);			
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			ArrayList columnNum = ExcelMethods.getExcelColumns(path);
			ArrayList<String[]> excelData = new ArrayList<String[]>();//用于保存从excel获得的数据
			for(int rownum=1;rownum<sourceRowCount;rownum++){//每条记录
				//添加要保存记录   start
				String[] row = ExcelMethods.getOneRow(sourceSheet,rownum,columnNum.size());
				row=StringUtils.joinStrArr(new String[]{rownum+""},row);
				excelData.addAll(getImportValue(model,row,drnj));
                //添加要保存记录   end
			}
			ArrayList<String[]> violateOnlyOne = null;
			
			violateOnlyOne =  dao.updateData(excelData, splitMappingOne,tableName);				
			
			if(violateOnlyOne != null && violateOnlyOne.size()>0){//如果有数据库中不存在的数据就输出
				list.addAll(violateOnlyOne);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public  boolean write2SheetImport(String path,OutputStream os,String sheetTitle, List<String[]> contents){
		
		boolean result = false ;
		WritableWorkbook destwwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet destSheet = destwwb.createSheet(sheetTitle, 0);
		Sheet sourceSheet;
		try {
			sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			ErrorRowInfo(destSheet, sourceSheet, 0,0);//获取表单的列头
			int rownum = 1;
			for(int i=0;i<contents.size();i++){
				String[] oneRow =contents.get(i);
				ExcelMethods.writeACell(String.valueOf(i+1), destSheet, 1, rownum);
				for(int colnum=0;colnum<oneRow.length;colnum++){
					if(colnum!=1){
						ExcelMethods.writeACell(oneRow[colnum], destSheet, colnum, rownum);
					}
				}
				rownum++;
			}
			ExcelMethods.submitWritableWorkbookOperations(destwwb);
			result = true;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param dest   目标sheet
	 * @param sour   原sheet
	 * @param row    第几行 从0开始
	 * @param startIndex 从第几列开始 大于0
	 */
	public static void ErrorRowInfo(WritableSheet dest,Sheet sour,int row,int startIndex){
		
		int rcount = dest.getRows();
		dest.insertRow(rcount);
		try {
			
			dest.addCell(new Label(0,rcount,"错误信息"));
			dest.addCell(new Label(1,rcount,"序号"));
			dest.addCell(new Label(2,rcount,"年级"));
			dest.addCell(new Label(3,rcount,"学号"));
			dest.addCell(new Label(4,rcount,"课程代码"));
			dest.addCell(new Label(5,rcount,"分数"));
		
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
}	
