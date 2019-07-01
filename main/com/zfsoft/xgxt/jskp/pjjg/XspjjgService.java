/**
 * @部门:学工产品(1)部
 * @日期：2018-4-18 上午11:04:21 
 */  
package com.zfsoft.xgxt.jskp.pjjg;

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

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生评价管理模块
 * @类功能描述: 学生评价结果
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-18 上午11:04:21 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XspjjgService extends SuperServiceImpl<XspjjgForm,XspjjgDao>{
	
	/**
	 * @描述: 下载导入模板,绘制excel表
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 上午08:56:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param os
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void createWwb(OutputStream os) throws Exception{
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		/**sheet1*/
		WritableSheet ws = wwb.createSheet("数据导入模板", 0);
		
		/**设置表格抬头的格式*/
		WritableFont bodyFont = new WritableFont(WritableFont.createFont("仿宋"),10,WritableFont.NO_BOLD );
		/**单元格字体格式写入*/
		WritableCellFormat body = new WritableCellFormat(bodyFont);
		/**设置单元格对齐*/
		/*水平居中*/
		body.setAlignment(jxl.format.Alignment.CENTRE);
		/*垂直居中*/
		body.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		
		/**设置列宽*/
		ws.setColumnView(0, 16);/*项目名称*/
		ws.setColumnView(1, 16);/*指导部门*/
		ws.setColumnView(2, 16);/*项目类别*/
		ws.setColumnView(3, 34);/*参与时间*/
		ws.setColumnView(4, 14);/*学号*/
		ws.setColumnView(5, 28);/*学年*/
		ws.setColumnView(6, 24);/*短学期*/
		ws.setColumnView(7, 12);/*分值*/
		ws.setColumnView(8, 12);/*负责人*/
		ws.setColumnView(9, 18);/*负责人联系方式*/
		ws.setColumnView(10, 14);/*指导老师*/
		ws.setColumnView(11, 20);/*指导老师联系方式*/
		ws.setColumnView(12, 24);/*申请理由*/
		
		
		/*第一行抬头*/
		Label row1col1= new Label(0, 0, "项目名称(必填)",body);
		Label row1col2= new Label(1, 0, "指导部门(必填)",body);
		Label row1col3= new Label(2, 0, "项目类别(必填)",body);
		Label row1col4= new Label(3, 0, "参与时间(必填),例如：2018-01-01",body);
		Label row1col5= new Label(4, 0, "学号(必填)",body);
		Label row1col6= new Label(5, 0, "学年(必填),例如：2017-2018",body);
		Label row1col7= new Label(6, 0, "短学期(必填),例如：春",body);
		Label row1col8= new Label(7, 0, "分值(必填)",body);
		Label row1col9= new Label(8, 0, "负责人",body);
		Label row1col10= new Label(9, 0, "负责人联系方式",body);
		Label row1col11= new Label(10, 0, "指导老师",body);
		Label row1col12= new Label(11, 0, "指导老师联系方式",body);
		Label row1col13= new Label(12, 0, "申请理由",body);
	    ws.addCell(row1col1);
	    ws.addCell(row1col2);
	    ws.addCell(row1col3);
	    ws.addCell(row1col4);
	    ws.addCell(row1col5);
	    ws.addCell(row1col6);
	    ws.addCell(row1col7);
	    ws.addCell(row1col8);
	    ws.addCell(row1col9);
	    ws.addCell(row1col10);
	    ws.addCell(row1col11);
	    ws.addCell(row1col12);
	    ws.addCell(row1col13);
	    
	    /**sheet2：项目类别对照表*/
	    WritableSheet ws1 = wwb.createSheet("项目类别对照表", 1);
	    List<HashMap<String, String>> xmlbList = dao.getXmlbList();
	    Label ws1row1col1= new Label(0, 0, "项目类别名称",body);
	    ws1.addCell(ws1row1col1);
	    for (int i = 0; i < xmlbList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, xmlbList.get(i).get("xmlbmc"),body);
	    	 ws1.addCell(tempLabel);
		}
	    
	    /**sheet3：指导部门对照表*/
	    WritableSheet ws2 = wwb.createSheet("指导部门对照表", 2);
	    List<HashMap<String, String>> xqList = dao.getZdbmList();
	    Label ws2row1col1= new Label(0, 0, "指导部门",body);
	    ws2.addCell(ws2row1col1);
	    for (int i = 0; i < xqList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, xqList.get(i).get("zdbmmc"),body);
	    	 ws2.addCell(tempLabel);
		}
	    
	    /**sheet4：短学期代码表*/
	    WritableSheet ws3 = wwb.createSheet("短学期对照表", 3);
	    List<HashMap<String, String>> dxqList = dao.getDxqList();
	    Label ws3row1col1= new Label(0, 0, "短学期",body);
	    ws3.addCell(ws3row1col1);
	    for (int i = 0; i < dxqList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, dxqList.get(i).get("dxqmc"),body);
	    	ws3.addCell(tempLabel);
		}
		try{
			 wwb.write();
			 wwb.close();
		 }catch(Exception e){
			 
		 }
	}
	
	/**
	 * @描述: 导入信息保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午02:23:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param xspjjgForm
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,XspjjgForm xspjjgForm,User user){
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap = this.DrExcelInfoCheck(is, xspjjgForm,user);
			//判断excel表格是否为空
			if("null".equals(resultMap.get("result")) || resultMap.isEmpty() || "hbyzsb".equals(resultMap.get("result")) || "excelrepeat".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){
				List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
				if( paralist == null ||paralist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
				this.saveDrDataIntoDb(paralist);
			}else{
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),xspjjgForm.getFilepath());
				resultMap.put("gid", gid);
				this.saveDrDataIntoDb((List<String[]>) resultMap.get("drlist"));
				logger.info("导入失败！");
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @描述: 导入信息验证
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午02:27:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param xspjjgForm
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,XspjjgForm xspjjgForm,User user){
		
		//导入记录数组
		List<String[]> drlist = new ArrayList<String[]>();
		//错误记录数组
		List<String[]> errorlist = new ArrayList<String[]>();
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
		
		Workbook rwb = null;
		try{
			rwb = Workbook.getWorkbook(is);
			Sheet rs = rwb.getSheet(0);
			/*得到所有的列*/
		    int clos = rs.getColumns();
		    /*得到所有的行*/
	        int rows = rs.getRows();
	        if(rows < 2){
	        	resultMap.put("result", "null");
	        }else if(!this.checkExcelRepeat(rs, clos, rows)){//验证表格中是否有重复数据(项目名称、参与时间、学号不能重复)
	        	resultMap.put("result", "excelrepeat");
	        }else{
	        	for(int i = 1; i < rows; i++){
	        		/*取出每行验证数据，塞入lsmap*/
	        		HashMap<String,String> lsmap = new HashMap<String,String>();
	        		String xmmc = rs.getCell(0, i).getContents();
	        		String zdbmmc = rs.getCell(1, i).getContents();
	        		String xmlbmc = rs.getCell(2, i).getContents();
	        		String cysj = rs.getCell(3, i).getContents();
	        		String xh = rs.getCell(4, i).getContents();
	        		String xn = rs.getCell(5, i).getContents();
	        		String dxqmc = rs.getCell(6, i).getContents();
	        		String fz = rs.getCell(7, i).getContents();
	        		String fzrxm = rs.getCell(8, i).getContents();
	        		String fzrlxfs = rs.getCell(9, i).getContents();
	        		String zdlsxm = rs.getCell(10, i).getContents();
	        		String zdlslxfs = rs.getCell(11, i).getContents();
	        		String sqly = rs.getCell(12, i).getContents();
	        		lsmap.put("xmmc", xmmc);
	        		lsmap.put("zdbmmc", zdbmmc);
	        		lsmap.put("xmlbmc", xmlbmc);
	        		lsmap.put("cysj", cysj);
	        		lsmap.put("xh", xh);
	        		lsmap.put("xn", xn);
	        		lsmap.put("dxqmc", dxqmc);
	        		lsmap.put("fz", fz);
	        		lsmap.put("fzrxm", fzrxm);
	        		lsmap.put("fzrlxfs", fzrlxfs);
	        		lsmap.put("zdlsxm", zdlsxm);
	        		lsmap.put("zdlslxfs", zdlslxfs);
	        		lsmap.put("sqly", sqly);
	        		//空行判断
  	        	    if(this.checkNullRow(lsmap)){
  	        	    	continue;
  	        	    }
  	        	    HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap,xspjjgForm,user);
  	        	    ArrayList<String> paralist = new ArrayList<String>();
  	        	    if("false".equals(resultmap.get("result"))){
  	        	    	paralist.add(lsmap.get("xmmc"));
  	        	    	paralist.add(lsmap.get("zdbmmc"));
  	        	    	paralist.add(lsmap.get("xmlbmc"));
  	        	    	paralist.add(lsmap.get("cysj"));
  	        	    	paralist.add(lsmap.get("xh"));
  	        	    	paralist.add(lsmap.get("xn"));
  	        	    	paralist.add(lsmap.get("dxqmc"));
  	        	    	paralist.add(lsmap.get("fz"));
  	        	    	paralist.add(lsmap.get("fzrxm"));
  	        	    	paralist.add(lsmap.get("fzrlxfs"));
  	        	    	paralist.add(lsmap.get("zdlsxm"));
  	        	    	paralist.add(lsmap.get("zdlslxfs"));
  	        	    	paralist.add(lsmap.get("sqly"));
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
  	        	    	paralist.add(lsmap.get("xn"));
  	        	    	paralist.add(lsmap.get("fz"));
  	        	    	paralist.add(lsmap.get("dxqdm"));
  	        	    	paralist.add(lsmap.get("xmmc"));
  	        	    	paralist.add(lsmap.get("zdbmdm"));
  	        	    	paralist.add(lsmap.get("xmlbdm"));
  	        	    	paralist.add(lsmap.get("cysj"));
  	        	    	paralist.add(lsmap.get("fzrxm"));
  	        	    	paralist.add(lsmap.get("fzrlxfs"));
  	        	    	paralist.add(lsmap.get("zdlsxm"));
  	        	    	paralist.add(lsmap.get("zdlslxfs"));
  	        	    	paralist.add(lsmap.get("sqly"));
  	        	    	paralist.add(xspjjgForm.getSjlrr());
  	        	    	paralist.add(xspjjgForm.getSjly());
  	        	    	paralist.add(xspjjgForm.getSjlrsj());
  	        	    	drlist.add(paralist.toArray(new String[]{}));
  	        	    }
	        	}
	          resultMap.put("drlist", drlist);
        	  resultMap.put("errorlist", errorlist);
	        }
			
		}catch (BiffException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @描述: 验证表格中是否存在重复数据(项目名称、参与时间、学号不能重复)
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-4-20 下午02:33:45
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
			String xmmc = rs.getCell(0,i).getContents();
			String cysj = rs.getCell(3,i).getContents();
			String xh = rs.getCell(4, i).getContents();
			String str = "";
			if(StringUtils.isNotNull(xmmc)){
				str = str + xmmc.trim();
			}
			if(StringUtils.isNotNull(cysj)){
				str = str + cysj.trim();
			}
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
	 * @描述: 判断空行
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午02:52:48
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
	
	/**
	 * @描述: 验证每行记录
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午02:55:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lsmap
	 * @param xspjjgForm
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap, XspjjgForm xspjjgForm,User user){
		/**
		 * 验证规则
		 * 1、项目名称、指导部门、项目类别、参与时间、学号、学年、短学期、分值不可为空
		 * 2、验证指导部门是否存在
		 * 3、验证项目类别是否存在
		 * 4、验证参与时间格式是否正确
		 * 5、学号是否存在学生信息表
		 * 6、验证短学期是否存在
		 * 7、唯一性验证
		 */
		String message = "true";
		if(!this.checkNotNull(lsmap)){
			message = "false";
			lsmap.put("kyz", "项目名称、指导部门、项目类别、参与时间、学号、学年、短学期、分值不可为空！");
		}else{
			//验证知道部门是否存在
			String zdbmdm = dao.checkZdbm(lsmap.get("zdbmmc"));
			if(StringUtils.isNull(zdbmdm)){
				message = "false";
				lsmap.put("zdbmyz", "指导部门不存在！");
			}
			
			String xsbmmc = dao.getXsbmmc(lsmap.get("xh"));
			//验证本学院只能导出本学院数据
			if(!xsbmmc.equals(user.getUserDepName())){
				message = "false";
				lsmap.put("zdbmyz", "只能导入本学院(园)数据！");
			}
			
			//验证项目类别是否存在
			String xmlbdm = dao.checkXmlb(lsmap.get("xmlbmc"));
			if(StringUtils.isNull(xmlbdm)){
				message = "false";
				lsmap.put("xmlbyz", "项目类别不存在！");
			}
			
			//验证项目类别为能力素养的数据不能导入
			/*2018-07-09,因放开结果导入权限，所有能力素养的数据也是可以导入的*/
//			String xmlbdmForNlsy = dao.checkNlsy(lsmap.get("xmlbmc"));
//			if(StringUtils.isNull(xmlbdmForNlsy)){
//				message = "false";
//				lsmap.put("xmlbNlsyyz", "项目类别为能力素养的数据不能导入！");
//			}
			
			//验证参与时间格式是否正确
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try{
				Date date = (Date)formatter.parse(lsmap.get("cysj"));
				if(!lsmap.get("cysj").equals(formatter.format(date))){
					message = "false";
					lsmap.put("cysjgsyz", "参与时间格式必须为yyyy-MM-dd！");
				}
			}catch (ParseException e){
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			
			//验证学号是否存在学生信息表
			if(!dao.checkXhisTrue(lsmap.get("xh"))){
				message = "false";
				lsmap.put("xsxhyz", "学号不存在或有空格！");
			}
			
			//验证短学期是否存在
			String dxqdm = dao.checkDxq(lsmap.get("dxqmc"));
			if(StringUtils.isNull(dxqdm)){
				message = "false";
				lsmap.put("dxqyz", "短学期不存在！");
			}
			
			//主键验证
			if(!dao.checkIsNotExists(lsmap.get("xmmc"), lsmap.get("cysj"), lsmap.get("xh"), null)){
				message = "false";
				lsmap.put("zjyz", "同一个学生在同一时间申报过此项目，请确认！");
			}
			
			//备注验证
			if(lsmap.get("sqly").trim().length() > 200){
				message = "false";
				lsmap.put("sqlyyz", "备注不能超过200字！");
			}
			
			if("true".equals(message)){
				lsmap.put("zdbmdm", zdbmdm);
				lsmap.put("xmlbdm", xmlbdm);
				lsmap.put("dxqdm", dxqdm);
			}
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	/**
	 * @描述: 验证非空项
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午03:05:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yzMap
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkNotNull(HashMap<String, String> yzMap){
		boolean rs = true;
		for (String key : yzMap.keySet()) {
			if(StringUtils.isNull(yzMap.get(key)) && !("fzrxm").equals(key) && !("fzrlxfs").equals(key) && !("zdlsxm").equals(key) && !("zdlslxfs").equals(key) && !("sqly").equals(key)){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	/**
	 * @描述: 保存数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-4-20 下午04:32:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paralist
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		if(paralist != null && paralist.size() > 0){
			return dao.saveDrDataIntoDb(paralist).length >0 ? true :false;
		}else{
			return false;
		}
	}
	
	/**
	 * @描述: 将错误excel表格写入服务器
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-4-20 下午04:53:44
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
		 String path = filepath + fileName;
		 File file=new File(path);
         if (!file.exists()) {
            file.createNewFile();
         }
         WritableWorkbook  wwb = Workbook.createWorkbook(file);
		try {
			// 创建工作表
	        WritableSheet ws = wwb.createSheet("错误数据", 0);
	       	WritableCellFormat titlefont =  this.getFontStyle("title");
	   		/**
	   		 * sheet1
	   		 */
	   	    Label row1col1= new Label(0, 0, "项目名称",titlefont);
	   	    Label row1col2= new Label(1, 0, "指导部门",titlefont);
	   	    Label row1col3= new Label(2, 0, "项目类别",titlefont);
	   	    Label row1col4= new Label(3, 0, "参与时间",titlefont);
	   		Label row1col5= new Label(4, 0, "学号",titlefont);
	   		Label row1col6= new Label(5, 0, "学年",titlefont);
	   		Label row1col7= new Label(6, 0, "短学期",titlefont);
	   		Label row1col8= new Label(7, 0, "分值",titlefont);
	   		Label row1col9= new Label(8, 0, "负责人",titlefont);
	   		Label row1col10= new Label(9, 0, "负责人联系方式",titlefont);
	   		Label row1col11= new Label(10, 0, "指导老师",titlefont);
	   		Label row1col12= new Label(11, 0, "指导老师联系方式",titlefont);
	   		Label row1col13= new Label(12, 0, "申请理由",titlefont);
            try {
	           	ws.addCell(row1col1);
	       		ws.addCell(row1col2);
	       		ws.addCell(row1col3);
	       		ws.addCell(row1col4);
	       		ws.addCell(row1col5);
	       		ws.addCell(row1col6);
	       		ws.addCell(row1col7);
	       		ws.addCell(row1col8);
	       		ws.addCell(row1col9);
	       		ws.addCell(row1col10);
	       	    ws.addCell(row1col11);
	       	    ws.addCell(row1col12);
	       	    ws.addCell(row1col13);
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
	               	 if(j<=12){
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
	           		try{
						ws.addCell(labelId_i);
					}catch(RowsExceededException e) {
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
	
	/**
	 * @描述: excel字体
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午04:54:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paras
	 * @return
	 * @throws WriteException
	 * WritableCellFormat 返回类型 
	 * @throws
	 */
	public WritableCellFormat getFontStyle(String paras) throws WriteException{
		
		/** 
         * 定义单元格样式 
         */ 
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
	 * @描述: 保存数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-24 上午09:49:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveFormXspjjg(XspjjgForm model,User user) throws Exception{
		boolean rs = true;
		
		/*生成唯一标识符*/
		String guid = UniqID.getInstance().getUniqIDHash();
		
		/*判断唯一键，学号(xh),项目名称(xmmc),参与时间(cysj)*/
		if(!this.checkIsNotRepeat(model)){
			throw new SystemException(MessageKey.SZTZ_XMSB_REPEAT);
		}
		
		/*判断参与人(学号)是否存在xsxxb*/
		if(!dao.checkXh(model.getXh())){
			throw new SystemException(MessageKey.PJPY_CPFWH_XHNULL);
		}
		
		/*当前操作人员用户名塞入*/
		model.setSjlrr(user.getUserName());
		
		/*获取当前操作时间塞入表中，配合操作人工号防止老师耍赖*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		
		/*判断该数据是否为修改数据*/
		if(StringUtils.isNotNull(model.getGuid())){
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			/*塞入唯一ID*/
			model.setGuid(guid);
			/*1:申请审核、2:结果增加、3:导入*/
			model.setSjly("2");
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * @描述: 判断唯一键，学号(xh),项目名称(xmmc),参与时间(cysj)
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-4-24 上午09:55:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(XspjjgForm model){
		return dao.checkIsNotRepeat(model);
	}
	
	/**
	 * @描述: 根据guid查看个人信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-25 上午09:04:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getInfoByGuid(String guid) throws Exception{
		return dao.getInfoByGuid(guid);
	}
	
	/** 
	 * @描述: 获取单个人的学年汇总
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-8-23 下午02:32:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSingleSummary(String xh) {
		return dao.getSingleSummary(xh);
	}
	
	/**
	 * @描述: 评价结果数据统计
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-25 下午03:19:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @param SearchXn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDateForSearchXn (XspjjgForm model, User user, String SearchXn) throws Exception{
		return dao.getDateForSearchXn(model,user,SearchXn);
	}
	
	/**
	 * @描述: 评价结果评分统计Excle
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-25 下午05:24:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param resultList
	 * @param xn
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public File getDataStatisticsFile(List<HashMap<String,String>> resultList,String xn) throws Exception {
		
		/**文件名*/
		String fileName = "学生评价结果数据统计.xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);
		
		/**判断指定的路径或着指定的目录文件是否已经存在*/
		if(!file.exists()){
			file.createNewFile();
		}
		
		/**创建工作簿*/
		WritableWorkbook wwb = Workbook.createWorkbook(file);
		
		/**创建工作表（sheet1）*/
		WritableSheet sheet = wwb.createSheet("学生评价结果数据统计", 0);
		
		/**设置表格抬头的格式============begin============*/
		WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 26, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);
		WritableFont headOneFont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);
		WritableFont headTwoFont = new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE);
		WritableFont bodyFont = new WritableFont(WritableFont.createFont("仿宋"),9,WritableFont.NO_BOLD );
		
		/**单元格字体格式写入============begin============*/
		WritableCellFormat title = new WritableCellFormat(titleFont);
		WritableCellFormat headOne = new WritableCellFormat(headOneFont);
		WritableCellFormat headTwo = new WritableCellFormat(headTwoFont);
		WritableCellFormat body = new WritableCellFormat(bodyFont);
		
		/**设置单元格对齐============begin============*/
		/*水平居中*/
		title.setAlignment(jxl.format.Alignment.CENTRE);
		headOne.setAlignment(jxl.format.Alignment.CENTRE);
		headTwo.setAlignment(jxl.format.Alignment.CENTRE);
		body.setAlignment(jxl.format.Alignment.CENTRE);
		/*垂直居中*/
		title.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		headOne.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		headTwo.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		body.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		
		/**设置单元格边框============begin============*/
		title.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		headOne.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		headTwo.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		body.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		
		/**合并单元格============begin============*/
		sheet.mergeCells(0, 0, 30, 0);/*标题*/
		
		/**设置列宽============begin============*/
		sheet.setColumnView(0, 14);
		sheet.setColumnView(1, 20);
		sheet.setColumnView(2, 8);
		sheet.setColumnView(3, 28);
		sheet.setColumnView(4, 28);
		sheet.setColumnView(5, 28);
		sheet.setColumnView(6, 14);
		for(int i = 7; i <= 30; i++){
			sheet.setColumnView(i, 18);
		}
		
		/**画表格开始============begin============*/
		Label col1row1 = new Label(0, 0,xn+"学年学生评价总分统计",title);
		Label col1row2 = new Label(0, 1,"学号",headOne);
		Label col2row2 = new Label(1, 1,"姓名",headOne);
		Label col3row2 = new Label(2, 1,"年级",headOne);
		Label col4row2 = new Label(3, 1,"学院名称",headOne);
		Label col5row2 = new Label(4, 1,"专业名称",headOne);
		Label col6row2 = new Label(5, 1,"班级名称",headOne);
		Label col7row2 = new Label(6, 1,"总分",headOne);
		Label col8row2 = new Label(7, 1,"活动记实总分",headTwo);
		Label col9row2 = new Label(8, 1,"活动记实学院排名",headTwo);
		Label col10row2 = new Label(9, 1,"活动记实专业排名",headTwo);
		Label col11row2 = new Label(10, 1,"活动记实班级排名",headTwo);
		Label col12row2 = new Label(11, 1,"创新创业总分",headTwo);
		Label col13row2 = new Label(12, 1,"创新创业学院排名",headTwo);
		Label col14row2 = new Label(13, 1,"创新创业专业排名",headTwo);
		Label col15row2 = new Label(14, 1,"创新创业班级排名",headTwo);
		Label col16row2 = new Label(15, 1,"对外交流总分",headTwo);
		Label col17row2 = new Label(16, 1,"对外交流学院排名",headTwo);
		Label col18row2 = new Label(17, 1,"对外交流专业排名",headTwo);
		Label col19row2 = new Label(18, 1,"对外交流班级排名",headTwo);
		Label col20row2 = new Label(19, 1,"公益服务总分",headTwo);
		Label col21row2 = new Label(20, 1,"公益服务学院排名",headTwo);
		Label col22row2 = new Label(21, 1,"公益服务专业排名",headTwo);
		Label col23row2 = new Label(22, 1,"公益服务班级排名",headTwo);
		Label col24row2 = new Label(23, 1,"社会工作总分",headTwo);
		Label col25row2 = new Label(24, 1,"社会工作学院排名",headTwo);
		Label col26row2 = new Label(25, 1,"社会工作专业排名",headTwo);
		Label col27row2 = new Label(26, 1,"社会工作班级排名",headTwo);
		Label col28row2 = new Label(27, 1,"文体活动总分",headTwo);
		Label col29row2 = new Label(28, 1,"文体活动学院排名",headTwo);
		Label col30row2 = new Label(29, 1,"文体活动专业排名",headTwo);
		Label col31row2 = new Label(30, 1,"文体活动班级排名",headTwo);
		sheet.addCell(col1row1);
		sheet.addCell(col1row2);
		sheet.addCell(col2row2);
		sheet.addCell(col3row2);
		sheet.addCell(col4row2);
		sheet.addCell(col5row2);
		sheet.addCell(col6row2);
		sheet.addCell(col7row2);
		sheet.addCell(col8row2);
		sheet.addCell(col9row2);
		sheet.addCell(col10row2);
		sheet.addCell(col11row2);
		sheet.addCell(col12row2);
		sheet.addCell(col13row2);
		sheet.addCell(col14row2);
		sheet.addCell(col15row2);
		sheet.addCell(col16row2);
		sheet.addCell(col17row2);
		sheet.addCell(col18row2);
		sheet.addCell(col19row2);
		sheet.addCell(col20row2);
		sheet.addCell(col21row2);
		sheet.addCell(col22row2);
		sheet.addCell(col23row2);
		sheet.addCell(col24row2);
		sheet.addCell(col25row2);
		sheet.addCell(col26row2);
		sheet.addCell(col27row2);
		sheet.addCell(col28row2);
		sheet.addCell(col29row2);
		sheet.addCell(col30row2);
		sheet.addCell(col31row2);
		
		if(resultList.size()>0){
			for(int j = 0; j < resultList.size(); j++){
				Map<String,String> map = resultList.get(j);
				Label date_xh = new Label(0, 2+j, map.get("xh"), body);
				Label date_xm = new Label(1, 2+j, map.get("xm"), body);
				Label date_nj = new Label(2, 2+j, map.get("nj"), body);
				Label date_xymc = new Label(3, 2+j, map.get("xymc"), body);
				Label date_zymc = new Label(4, 2+j, map.get("zymc"), body);
				Label date_bjmc = new Label(5, 2+j, map.get("bjmc"), body);
				Label date_zf = new Label(6, 2+j, map.get("zf"), body);
				Label date_hdjszf = new Label(7, 2+j, map.get("hdjszf"), body);
				Label date_hdjsxypm = new Label(8, 2+j, map.get("hdjsxypm"), body);
				Label date_hdjszypm = new Label(9, 2+j, map.get("hdjszypm"), body);
				Label date_hdjsbjpm = new Label(10, 2+j, map.get("hdjsbjpm"), body);
				Label date_cxcyzf = new Label(11, 2+j, map.get("cxcyzf"), body);
				Label date_cxcyxypm = new Label(12, 2+j, map.get("cxcyxypm"), body);
				Label date_cxcyzypm = new Label(13, 2+j, map.get("cxcyzypm"), body);
				Label date_cxcybjpm = new Label(14, 2+j, map.get("cxcybjpm"), body);
				Label date_dwjlzf = new Label(15, 2+j, map.get("dwjlzf"), body);
				Label date_dwjlxypm = new Label(16, 2+j, map.get("dwjlxypm"), body);
				Label date_dwjlzypm = new Label(17, 2+j, map.get("dwjlzypm"), body);
				Label date_dwjlbjpm = new Label(18, 2+j, map.get("dwjlbjpm"), body);
				Label date_gyfwzf = new Label(19, 2+j, map.get("gyfwzf"), body);
				Label date_gyfwxypm = new Label(20, 2+j, map.get("gyfwxypm"), body);
				Label date_gyfwzypm = new Label(21, 2+j, map.get("gyfwzypm"), body);
				Label date_gyfwbjpm = new Label(22, 2+j, map.get("gyfwbjpm"), body);
				Label date_shgzzf = new Label(23, 2+j, map.get("shgzzf"), body);
				Label date_shgzxypm = new Label(24, 2+j, map.get("shgzxypm"), body);
				Label date_shgzzypm = new Label(25, 2+j, map.get("shgzzypm"), body);
				Label date_shgzbjpm = new Label(26, 2+j, map.get("shgzbjpm"), body);
				Label date_wthdzf = new Label(27, 2+j, map.get("wthdzf"), body);
				Label date_wthdxypm = new Label(28, 2+j, map.get("wthdxypm"), body);
				Label date_wthdzypm = new Label(29, 2+j, map.get("wthdzypm"), body);
				Label date_wthdbjpm = new Label(30, 2+j, map.get("wthdbjpm"), body);
				
				sheet.addCell(date_xh);
				sheet.addCell(date_xm);
				sheet.addCell(date_nj);
				sheet.addCell(date_xymc);
				sheet.addCell(date_zymc);
				sheet.addCell(date_bjmc);
				sheet.addCell(date_zf);
				sheet.addCell(date_hdjszf);
				sheet.addCell(date_hdjsxypm);
				sheet.addCell(date_hdjszypm);
				sheet.addCell(date_hdjsbjpm);
				sheet.addCell(date_cxcyzf);
				sheet.addCell(date_cxcyxypm);
				sheet.addCell(date_cxcyzypm);
				sheet.addCell(date_cxcybjpm);
				sheet.addCell(date_dwjlzf);
				sheet.addCell(date_dwjlxypm);
				sheet.addCell(date_dwjlzypm);
				sheet.addCell(date_dwjlbjpm);
				sheet.addCell(date_gyfwzf);
				sheet.addCell(date_gyfwxypm);
				sheet.addCell(date_gyfwzypm);
				sheet.addCell(date_gyfwbjpm);
				sheet.addCell(date_shgzzf);
				sheet.addCell(date_shgzxypm);
				sheet.addCell(date_shgzzypm);
				sheet.addCell(date_shgzbjpm);
				sheet.addCell(date_wthdzf);
				sheet.addCell(date_wthdxypm);
				sheet.addCell(date_wthdzypm);
				sheet.addCell(date_wthdbjpm);
			}
		}
		
		/**写入Excel表格中*/
		wwb.write();
		/**关闭流*/
		wwb.close();
		return file;
	}
}
