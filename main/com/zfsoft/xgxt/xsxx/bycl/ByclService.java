package com.zfsoft.xgxt.xsxx.bycl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述:毕业处理
 * @作者： Qilm[工号:964]
 * @时间： 2013-12-5 下午03:31:54 
 * @版本： V1.0
 */
public class ByclService extends SuperServiceImpl<ByclForm, ByclDAO> {
	
	private ByclDAO dao = new ByclDAO();
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public ByclService() {
		super.setDao(dao);
		
	}

	/** 
	 * @描述: 查询取得所有人数
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-6 上午09:02:53
	 * @param myForm
	 * @param user
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int getCounts(ByclForm myForm, User user) throws Exception {
		return dao.getCounts(myForm, user);
	}
	
	/**
	 * 
	 * @描述: 更新毕业年月
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-6 上午10:30:31
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean runUpdate(ByclForm model, User user) throws Exception{
		
		boolean bolFlg = dao.runUpdate(model, user);
		if(bolFlg){
			new Thread(new Base.initialBj()).start();
		}
		return bolFlg;
	}

	/** 
	 * @描述: 取消毕业处理
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-6 下午01:36:02
	 * @param myForm
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean runDelete(ByclForm model, User user) throws Exception{

		boolean bolFlg = dao.runDelete(model, user);
		if(bolFlg){
			new Thread(new Base.initialBj()).start();
		}
		return bolFlg;
	}
	/**
	 * 空的list
	 * @param size
	 * @return
	 */
	public List<HashMap<String,String>> getBlankList(int size){
		
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(size);
		
		for (int i = 0 ; i < size ; i++){
			list.add(new HashMap<String, String>());
		}
		
		return list;
	}
	
	/**
	 * @描述: 创建模板毕业学生信息导入
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-10 下午03:33:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param os
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void createWwb(OutputStream os) throws Exception{
		/*得到已存在Excel表格的工作簿*/
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableCellFormat titlefont =  this.getFontStyle("title");
		/*Sheet1内容*/
		WritableSheet ws = wwb.createSheet("数据导入模板", 0);
		Label row1col1= new Label(0, 0, "学号",titlefont);
		ws.addCell(row1col1);
		WritableCellFormat wcfF = new WritableCellFormat(NumberFormats.TEXT); 
		/*定义一个列显示样式*/
	    CellView cv = new CellView();
	    /*把定义的单元格格式初始化进去*/
	    cv.setFormat(wcfF);
	    /*设置列宽度（不设置的话是0，不会显示）*/
	    cv.setSize(20*265);
	    for (int i = 0; i < 10; i++) {
	    	ws.setColumnView(i, cv);
		}
	    try{
		 wwb.write();
		 wwb.close();
		}catch(Exception e){
				
		}
	}
	
	/**
	 * @描述: 定义毕业学生信息导入模板Excle模板
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-10 下午03:36:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paras
	 * @return
	 * @throws WriteException
	 * WritableCellFormat 返回类型 
	 * @throws
	 */
	public WritableCellFormat getFontStyle(String paras) throws WriteException{
		/*定义单元格样式*/ 
		if("title".equals(paras)){
			  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
		                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK);  
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);  
	          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
		}else if("errorinfo".equals(paras)){
			WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
	                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                jxl.format.Colour.RED);   
		  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
          return wcf_table;
		}
        return null;
	}
	
	/**
	 * @描述: 信息导入保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-12 上午11:37:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param byclForm
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,ByclForm byclForm){
		HashMap<String, Object> resultMap= null;
		try{
			resultMap= this.DrExcelInfoCheck(is, byclForm);
			/*判断excel表格是否为空*/
			if("null".equals(resultMap.get("result")) || resultMap.isEmpty() || "hbyzsb".equals(resultMap.get("result")) || "excelrepeat".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){
				List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
				if( paralist == null ||paralist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
				 
			  	 dao.runUpdateForDr(paralist,byclForm);
			}else{
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),byclForm.getFilepath());
				resultMap.put("gid", gid);
				List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
				 dao.runUpdateForDr(paralist,byclForm);
				logger.info("导入失败！");
			}
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @描述: 导入信息检查
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-12 上午11:37:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param byclForm
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,ByclForm byclForm){
		/*导入记录数组*/
		List<String[]> drlist = new ArrayList<String[]>();
		/*错误记录数组*/
		List<String[]> errorlist = new ArrayList<String[]>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
		Workbook rwb = null;
		try {
			rwb = Workbook.getWorkbook(is);
			Sheet rs=rwb.getSheet(0);
			/*得到所有的列*/
		    int clos=rs.getColumns();
		    /*得到所有的行*/
	        int rows=rs.getRows();
	        /*判断excel表格是否为空*/
	        if(rows < 2){
	        	resultMap.put("result", "null");
	        }else if(!this.checkExcelRepeat(rs, clos, rows)){/*验证重复数据，实质只要验证学号即可*/
	        	resultMap.put("result", "excelrepeat");
	        }else{
//	          HashMap<String, String> hbMap = this.checkHbyz(rs, clos, rows, byclForm.getFilepath());
//	          if(!hbMap.get("message").equals("true")){
//	        	  resultMap.put("result", hbMap.get("message"));
//	        	  resultMap.put("gid", hbMap.get("gid"));
//	        	  return resultMap;
//	          }
	        	  for (int i = 1; i < rows; i++) {
	  	        		/*取出每行验证数据，塞入lsmap*/
	  	        		HashMap<String, String>  lsmap = new HashMap<String, String>();
	  	        		String xh = rs.getCell(0, i).getContents();
	  	        		lsmap.put("xh",xh);
	  	        		/*空行判断*/
	  	        	    if(this.checkNullRow(lsmap)){
	  	        	    	continue;
	  	        	    }
	  	        		
	  	        		HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, byclForm);
	  	        		ArrayList<String> paralist = new ArrayList<String>();
	  	        		if("false".equals(resultmap.get("result"))){
	  	        			paralist.add(lsmap.get("xh"));
	  	        			if(resultmap.get("resultmap") != null ){
	  	        				Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
	  	        				for (Map.Entry<String, String> entry : map.entrySet()){
	  	        					if(entry.getKey().indexOf("yz") != -1){
	  	        						paralist.add(entry.getValue());
	  	        					}
	  	        					
	  	        				}
	  	        			}
	  	        			errorlist.add(paralist.toArray(new String[]{}));
	  	        			resultMap.put("result", "false");
	  	        		}else{
	  	        			paralist.add(lsmap.get("xh"));
	  	        			paralist.add("0");
	  	        			drlist.add(paralist.toArray(new String[]{}));
	  	        		}
	  			}
	        	  resultMap.put("drlist", drlist);
	        	  resultMap.put("errorlist", errorlist);
	        }
		} catch (BiffException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return resultMap;
	}

	/**
	 * @描述: 检查表格中是否有重复数据（重复的学号）
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-12 上午11:39:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rs
	 * @param cols
	 * @param rows
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkExcelRepeat(Sheet rs,int cols,int rows){
		ArrayList<String> compareList = new ArrayList<String>();
		boolean flag = true;
		for (int i = 0; i < rows; i++) {
			String xh = rs.getCell(0, i).getContents();
			String str = "";
			if(StringUtils.isNotNull(xh)){
				str = str + xh.trim();
			}
			if(StringUtils.isNull(str)){
				continue;
			}
			compareList.add(str);
		}
	    for (int i = 0; i < compareList.size(); i++) {
			for (int j = i+1; j < compareList.size(); j++) {
				if(compareList.get(i).equals(compareList.get(j))){
					flag = false;
					break;
				}
			}
			if(!flag){
				break;
			}
		}
		return flag;
	}
	
	/**
	 * @描述: 数据验证
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-12 上午11:39:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rs
	 * @param cols
	 * @param rows
	 * @param filepath
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> checkHbyz(Sheet rs,int cols,int rows,String filepath){
		boolean result = true;
		List<String> containCfList = new ArrayList<String>();
		List<HashMap<String,String>> yzList = new ArrayList<HashMap<String,String>>();
		for (int i = 1; i < rows; i++) {
		if(!containCfList.contains(i+"")){
			String xh = rs.getCell(0, i).getContents();
			if(StringUtils.isNull(xh)){
				continue;
			}
				HashMap<String, String> dataMap = this.checkIsFhJfhbDr(xh);
				if(("false").equals(dataMap.get("rs"))){
					result = false;
					dataMap.put("xh",xh);
					yzList.add(dataMap);
				}
			}
		}
		String gid = "";
		String message = "true";
		if(!result){
			message = "hbyzsb";
			try {
				 gid = this.uploadErrorExcelHb(yzList, filepath);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		HashMap<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("gid", gid);
		resultMap.put("message", message);
		return resultMap;
	}
	
	/**
	 * @描述: 空行验证
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-11 上午10:21:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yzMap
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkNullRow(HashMap<String, String> yzMap){
		boolean rs = true;
		for (String key : yzMap.keySet()) {
			if(StringUtils.isNotNull(yzMap.get(key))){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	public HashMap<String, String> checkIsFhJfhbDr(String xh){
		return dao.checkIsFhJfhbDr(xh);
	}
	
	/**
	 * @描述: 每行数据验证
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2017-10-11 上午11:54:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lsmap
	 * @param byclForm
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap,ByclForm byclForm){
		String message = "true";
		/*验证学号是否符合要求*/
		if(!this.checkNotNull(lsmap)){
			message = "false";
			lsmap.put("kyz", "学号");
		}else{
			/*验证学号是否存在学生信息表*/
			String xhyz = dao.sfczXsxxb(lsmap.get("xh"));
			if(!"1".equals(xhyz)){
				message = "false";
				lsmap.put("xhbczXsxxbyz", "不存在此学号！");//学生不存在学生信息表
			}
			
			/*验证学号是否为非毕业生*/
//			String xhfby = dao.xhfby(lsmap.get("xh"));
//			if(!"0".equals(xhfby)){
//				message = "false";
//				lsmap.put("xhsBysyz", "此学生已经是毕业生！");//学生是毕业生
//			}
		}
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	/**
	 * @描述: 验证非空项
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-11 上午11:56:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yzMap
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkNotNull(HashMap<String, String> yzMap){
		boolean rs = true;
		for (String key : yzMap.keySet()) {
			if(StringUtils.isNull(yzMap.get(key))){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	/**
	 * @描述: 合并验证
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2017-10-12 上午11:41:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param errorlist
	 * @param filepath
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String uploadErrorExcelHb(List<HashMap<String, String>> errorlist,String filepath) throws Exception{
		String fileName = System.currentTimeMillis()+"error.xls";
		String path = filepath+fileName;
		File file=new File(path);
		
        if (!file.exists()) {
           file.createNewFile();
        }
        
        WritableWorkbook  wwb = Workbook.createWorkbook(file);
		try {
		  /*创建工作表*/
          WritableSheet ws = wwb.createSheet("错误数据", 0);
      	  WritableCellFormat titlefont =  this.getFontStyle("title");
      	  /*sheet1*/
      	  Label row1col1= new Label(0, 0, "学号",titlefont);
          
          try {
          	ws.addCell(row1col1);
			} catch (RowsExceededException e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			}
          
          for (int i = 0; i < errorlist.size(); i++) {
        	  WritableCellFormat wcf = new WritableCellFormat();
				WritableFont wf = new WritableFont(WritableFont.ARIAL);
				wf.setColour(Colour.RED);
				wcf.setFont(wf);
				wcf.setAlignment(Alignment.CENTRE);
				Label labelId_0 = new Label(0, i+1,errorlist.get(i).get("xh") ,wcf);
          		try {
						ws.addCell(labelId_0);
					} catch (RowsExceededException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
          }
		}finally{
			 wwb.write();
			 try {
				wwb.close();
			} catch (WriteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return fileName;
	}
	
	/**
	 * @描述: 错误信息Excle
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-12 上午11:41:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param errorlist
	 * @param filepath
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String uploadErrorExcel(List<String[]> errorlist,String filepath) throws Exception{
		 String fileName = System.currentTimeMillis()+"error.xls";
		 String path = filepath+fileName;
        File file=new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        WritableWorkbook  wwb = Workbook.createWorkbook(file);
		try {
		  /*创建工作表*/
          WritableSheet ws = wwb.createSheet("错误数据", 0);
          WritableCellFormat titlefont =  this.getFontStyle("title");
   		  /*sheet1*/
   	      Label row1col1= new Label(0, 0, "学号",titlefont);
           try {
           	ws.addCell(row1col1);
			} catch (RowsExceededException e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			}
           
           for (int i = 0; i < errorlist.size(); i++) {
                for(int j = 0;j<errorlist.get(i).length;j++){
               	 Label labelId_i= null;
               	 if(j<=0){
               		  labelId_i= new Label(j, i+1, errorlist.get(i)[j]+"");
               	 }else{
               		 WritableCellFormat wcf = new WritableCellFormat();
        				WritableFont wf = new WritableFont(WritableFont.ARIAL);
        				try {
							wf.setColour(Colour.RED);
							wcf.setFont(wf);
							wcf.setAlignment(Alignment.CENTRE);
							labelId_i = new Label(j, i+1, errorlist.get(i)[j],wcf);
						} catch (RowsExceededException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						} catch (WriteException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						}
               	 }
               		try {
							ws.addCell(labelId_i);
						} catch (RowsExceededException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						} catch (WriteException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						}
                }
           }
		}finally{
			 wwb.write();
			 try {
				wwb.close();
			} catch (WriteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return fileName;
	}
}
