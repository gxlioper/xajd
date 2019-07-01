/**
 * @部门:学工产品(1)部
 * @日期：2018-2-6 下午05:33:05 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.fwmddc;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xpjpy.tjcx.HjmdExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 发文名单导出
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-2-6 下午05:33:05 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FwmddcService extends SuperServiceImpl<FwmddcForm, FwmddcDao>{
	
	private FwmddcDao dao = new FwmddcDao();
	
	@SuppressWarnings("deprecation")
	public FwmddcService(){
		super.setDao(dao);
	}
	
	/**
	 * @描述: 导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-25 下午03:48:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void exportHjmdtj_10335(HjmdExportModel model , OutputStream os, User user) throws Exception{
		
		/**取学年*/
		String xn = model.getXn();
		/**取学期*/
		String xq = model.getXq();
		/**取学期名称*/
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for(HashMap<String,String> map : xqList){
			if(map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
		}
		/**项目类型*/
		String[] xmlx = model.getXmlx();
		/**学院代码*/
		String[] xydm = model.getXydm();
		/**项目性质*/
		String[] xmxz = model.getXmxz();
		/**项目名称*/
		String[] xmmc = model.getXmmc();
		
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(titleXmlx.toString() + "获奖名单", 0);
		ws.setColumnView(0, 2);
		ws.setRowView(0, 500);
		ws.setRowView(1, 500);
		ws.setRowView(2, 500);
		
		/**学院名称、项目名称、人数*/
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**项目名称、项目显示序号、人数*/
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**获奖姓名*/
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		/**项目类型名称*/
		List<String> xmlxmc = dao.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		/**奖学金名称样式*/
		WritableCellFormat titlFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.createFont("仿宋_GB2312"),16);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		titlFormat.setFont(jxjFont);
		titlFormat.setAlignment(Alignment.CENTRE);
		
		/**奖项+学院样式*/
		WritableCellFormat jxxyFormat = new WritableCellFormat();
		WritableFont jxxyFont = new WritableFont(WritableFont.createFont("仿宋_GB2312"),16);
		jxxyFont.setBoldStyle(WritableFont.BOLD);
		jxxyFormat.setFont(jxjFont);
		jxxyFormat.setAlignment(Alignment.LEFT);
		
		ws.addCell(new Label(1, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", lxmc, "发文名单"), titlFormat));
		ws.mergeCells(1, 0,3, 1);
		
		/**xmList 获奖名单*/
		/**jxjdmList 项目获奖人数*/
		/**xymcList 学院获奖人数*/
		/**输出第一级奖学金名称和获奖人数*/
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			properties.row =properties.row+2; //浙江大学开头空两行
			for (int i=0;i<jxjdmList.size();i++) {//一级开始---------------------------------------------------------
				/*将奖学金名称写入到EXCEL中*/
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc);
				resultCell.setCellFormat(jxxyFormat);
				ws.setRowView(properties.row, 500);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 3, properties.row);
				
				//二级 开始 输出第二级将学院名称写到EXCEL中---------------------------------------------------------
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0]: "";
						
						List<String> xmXhByXyList = new ArrayList<String>();//存放每个奖学金下面的每个学院的获奖名单
						List<String> xmByXyList = new ArrayList<String>();
						for (String[] xmArr : xmList) {//三级 开始 ---------------------------------------------------------
							if (xmArr != null && xmArr.length >= 4
									&& xy.equalsIgnoreCase(xmArr[0])
									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
								xmByXyList.add(xmArr[2]);
								xmXhByXyList.add(xmArr[3]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//输出学院名称
						++properties.row;
						xy+="("+xymcArr[2]+"人)";
						System.out.println("学院："+xy);
						Label xymcCell = new Label(1,properties.row,xy );
						xymcCell.setCellFormat(jxxyFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 3, properties.row);
						//输出第三级将学院下面的获奖名单输出
						writeHjmdhzExcel_10335(ws, properties, xmByXyList,xmXhByXyList,0);//将获奖名单写入到EXCEL中
					}
				}//二级 结束---------------------------------------------------------
			}//一级结束始---------------------------------------------------------
		}
		
		/**向客户端输出*/
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	private class JxjExportProperties{
		 int x_axis = 0;//X座标
		 int row = 1;//行标
		 int rowCellCount = 1;//每行的cell个数计数
		 int[] maxLength = {25,25,25,25,25,25,25};//默认的列宽
		public int getX_axis() {
			return x_axis;
		}
		@SuppressWarnings("unused")
		public void setX_axis(int x_axis) {
			this.x_axis = x_axis;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getRowCellCount() {
			return rowCellCount;
		}
		public void setRowCellCount(int rowCellCount) {
			this.rowCellCount = rowCellCount;
		}
		public int[] getMaxLength() {
			return maxLength;
		}
		public void setMaxLength(int[] maxLength) {
			this.maxLength = maxLength;
		}
	}
	
	private void writeHjmdhzExcel_10335(WritableSheet ws,JxjExportProperties properties, List<String> xmList,List<String> xmXhList,int rowCellCount) 
		throws Exception {
		properties.rowCellCount = 1;
		properties.row++;// 开始写姓名行
		properties.x_axis = rowCellCount;// 开始新奖学金的姓名
		//姓名样式
		WritableCellFormat xmFormat = new WritableCellFormat();
		WritableFont xmFont = new WritableFont(WritableFont.createFont("仿宋_GB2312"),16);
		xmFont.setBoldStyle(WritableFont.NO_BOLD);
		xmFormat.setFont(xmFont);
		xmFormat.setAlignment(Alignment.LEFT);
		
		if (xmList != null) {
			for (int index = 1; index < xmList.size() + 1; index++) {
				int nameLength = 4; //合并单元格字符串长度
				int Column = 0;
				String cellContent = xmXhList.get(index - 1);
				String xmContent = xmList.get(index - 1);
				int strLength = xmContent.replace(" ", "").length();
				if(strLength>nameLength){
					Column = 1;
				}
				
				properties.rowCellCount+=Column;
				
				if (properties.rowCellCount >= 4) {
					properties.row++;// 满3个就换行
					properties.x_axis = 0;
					properties.rowCellCount = 1;// 重新每行计数
				}
				
				Label cell = new Label(++properties.x_axis, properties.row,
						cellContent);
				cell.setCellFormat(xmFormat);
				ws.mergeCells(properties.x_axis, properties.row, properties.x_axis+Column, properties.row);
				ws.addCell(cell); 
				ws.setRowView(properties.row, 500);
				ws.setColumnView(properties.x_axis, 30);
				properties.rowCellCount++;
				properties.x_axis+=(Column);
			}
		}
		properties.row += 1;// 新奖学金换行
	}
	
	/**
	 * @描述: 浙大发文名单导出word
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-28 下午03:42:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param response
	 * @param model
	 * @param user
	 * @param path
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void createWord(HttpServletResponse response,HjmdExportModel model,User user,String path) throws Exception{
		
		Document document = new Document(PageSize.A4);
		/**建立一个书写器，与document对象关联  */
	    response.setHeader("Content-Disposition", "attachment;filename=\"" + new String("fwmddc_zjdx.doc".getBytes(), "GBK") + "\"");
	    RtfWriter2.getInstance(document, response.getOutputStream());  
        document.open();  
        /**设置中文字体 */
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        /**标题字体风格*/
        Font titleFont = new Font(bfChinese,16,Font.BOLD);  
        /**正文字体风格*/
        Font contextFont = new Font(bfChinese,16,Font.NORMAL);  
        Paragraph title = new Paragraph(); 
        /**学年*/
		String xn = model.getXn();
		/**学期*/
		String xq = model.getXq();
		/**学期名称*/
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		/**项目类型*/
		String[] xmlx = model.getXmlx();
		/**学院代码*/
		String[] xydm = model.getXydm();
		/**项目性质*/
		String[] xmxz = model.getXmxz();
		/**项目名称*/
		String[] xmmc = model.getXmmc();
		
		/**学院列表*/
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**各奖学金获奖人数*/
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**获奖姓名*/
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		/**项目类型名称*/
		List<String> xmlxmc = dao.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		/**过滤评奖周期（学期评奖才要打印出学期，否则不用）*/
		Phrase phrase = null;
		phrase = new Phrase(StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", lxmc, "发文名单"),titleFont);
		
		title.add(phrase);
        //设置标题格式对齐方式  
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        title.setIndentationLeft(5);
        title.setIndentationRight(5);
        document.add(title);  
    	if (!xmList.isEmpty()) {
    			Paragraph paraitra1 = null;
    			int propertiesrow =3;
    			for (int i=0;i<jxjdmList.size();i++) {//一级开始---------------------------------------------------------
    			
    				String[] jxjdmArr = jxjdmList.get(i);
    				String jxjmc = jxjdmArr[0];
    				paraitra1 = new Paragraph(jxjmc,titleFont);
    				paraitra1.setAlignment(Element.ALIGN_LEFT);
    				document.add(paraitra1);
    		      
    				//二级 开始 输出第二级将学院名称写到EXCEL中---------------------------------------------------------
    				Paragraph paraitra2 = null;
    				for (int index = 0; index < xymcList.size(); index++) {
    					String[] xymcArr = xymcList.get(index);
    					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
    						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0]: "";
    						
    						List<String> xmXhByXyList = new ArrayList<String>();//存放每个奖学金下面的每个学院的获奖名单
    						List<String> xmByXyList = new ArrayList<String>();
    						for (String[] xmArr : xmList) {//三级 开始 ---------------------------------------------------------
    							if (xmArr != null && xmArr.length >= 4
    									&& xy.equalsIgnoreCase(xmArr[0])
    									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
    								xmByXyList.add(xmArr[2]);
    								xmXhByXyList.add(xmArr[3]);
    							}
    						}
    						if (xmByXyList == null || xmByXyList.size() <= 0) {
    							continue;
    						}
    						//输出学院名称
    						++propertiesrow;
    						xy+="("+xymcArr[2]+"人)";
    						paraitra2 = new Paragraph(xy,titleFont);
    						paraitra2.setIndentationLeft(10);
    						document.add(paraitra2);
    						int len = xmXhByXyList.size();
    							Paragraph para3 = null;
    							Phrase phrase3 = null;
    							/*活动技术器变量用来循环*/
    							int count = 3;
    							/*三级循环，三个名字为一行*/
    							for (int j = 0; j < len; j=j+count) {
    								para3 = new Paragraph();
    								/*名单写入，姓名超过4个字符的单独为一行，如果第二个名字或者第三个名字超过4个字符，不写入，另起一行，再写入*/
    								for(int x=j;x<j+3;x++){
    									if(x<len){
    										if(x == j){
    		    								phrase3 = new Phrase(xmXhByXyList.get(x),contextFont);
    											if(xmByXyList.get(x).replaceAll(" ", "").length()>4){
    												para3.add(phrase3);
    												count=1;
    												break;
    											}else{
    												count=3;
    											}
    											
    										}else{
    											if(xmByXyList.get(x).replaceAll(" ", "").length()>4){
    												count=x-j;
    												break;
    											}else{	
    												phrase3 = new Phrase("   "+xmXhByXyList.get(x),contextFont);
    												count=3;
    											}
    										}
    										para3.add(phrase3);
	    									
	    								}
    								}

    								para3.setIndentationLeft(10);
    								document.add(para3);
								}
    							para3 = new Paragraph("  ");
    							document.add(para3);
    					}
    				}//二级 结束---------------------------------------------------------
    			}//一级结束始---------------------------------------------------------
    		  
    		}
		
		document.close();
	}
	
	/**
	 * @描述: 年鉴导出word
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-28 下午04:51:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param response
	 * @param model
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void createWordNjdc(HttpServletResponse response,HjmdExportModel model,User user) throws Exception{
		Document document = new Document(PageSize.A4);  
        //建立一个书写器，与document对象关联  
		//相应头设置
	    response.setHeader("Content-Disposition", "attachment;filename=\""
               + new String("njdc_zjdx.doc".getBytes(), "GBK") + "\"");
        RtfWriter2.getInstance(document, response.getOutputStream());
        //打开文档
        document.open();  
        //设置中文字体  
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);  
        //标题字体风格  
        Font titleFont = new Font(bfChinese,16,Font.BOLD);  
        //正文字体风格  
        Font contextFont = new Font(bfChinese,13,Font.NORMAL);  
        
		//学年
		String xn = model.getXn();
		//学期
		String xq = model.getXq();
		//学期名称
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		/**项目类型*/
		String[] xmlx = model.getXmlx();
		/**学院代码*/
		String[] xydm = model.getXydm();
		/**项目性质*/
		String[] xmxz = model.getXmxz();
		/**项目名称*/
		String[] xmmc = model.getXmmc();
		
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}
		/**学院列表*/
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**各奖学金获奖人数*/
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**获奖姓名*/
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		/**项目类型名称*/
		List<String> xmlxmc = dao.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		/**过滤评奖周期（学期评奖才要打印出学期，否则不用）*/
		Paragraph title = null;
		title = new Paragraph(StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", lxmc, "发文名单"),titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setIndentationLeft(5);
		title.setIndentationRight(5);
		document.add(title);
		if (!xmList.isEmpty()) {
			Paragraph paraitra1 = null;
			int propertiesrow =3;
			for (int i=0;i<jxjdmList.size();i++) {//一级开始---------------------------------------------------------
				
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[0];
				paraitra1 = new Paragraph(jxjmc,titleFont);
				paraitra1.setAlignment(Element.ALIGN_LEFT);
				document.add(paraitra1);
		      
				//二级 开始 输出第二级将学院名称写到EXCEL中---------------------------------------------------------
				Paragraph paraitra2 = null;
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0]: "";
						/*存放每个奖学金下面的每个学院的获奖名单*/
						List<String> xmXhByXyList = new ArrayList<String>();
						List<String> xmByXyList = new ArrayList<String>();
						for (String[] xmArr : xmList) {//三级 开始 ---------------------------------------------------------
							if (xmArr != null && xmArr.length >= 4
									&& xy.equalsIgnoreCase(xmArr[0])
									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
								xmByXyList.add(xmArr[2]);
								xmXhByXyList.add(xmArr[3]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//输出学院名称
						++propertiesrow;
						xy+="("+xymcArr[2]+"人)";
						paraitra2 = new Paragraph(xy,titleFont);
						paraitra2.setIndentationLeft(10);
						document.add(paraitra2);
						int len = xmXhByXyList.size();
							Paragraph para3 = null;
							Phrase phrase3 = null;
							/*活动技术器变量用来循环*/
							int count = 9;
							/*新加行标志*/
							/*三级循环，五个名字为一行*/
							for (int j = 0; j < len; j=j+count) {
								para3 = new Paragraph();
								int strlen = 35;
								int realtotallen = 0;
								int realeverylen = 0;
								/*名单写入，常规导出一行9个，如果出现姓名较长的按一行35个字符算，如果超出35个字符，就会另起一行进行填写。*/
								for(int x=j;x<j+9;x++){
									if(x<len){
										if(x == j){
		    								phrase3 = new Phrase(xmByXyList.get(x),contextFont);
		    								realeverylen = xmByXyList.get(x).length();
		    								realtotallen =realtotallen+realeverylen;
											count=9;
											
										}else{
											realeverylen = xmByXyList.get(x).length();
											realtotallen =realtotallen+realeverylen;
											if(realtotallen >strlen){
												count=x-j;
												break;
											}else{
												phrase3 = new Phrase(" "+xmByXyList.get(x),contextFont);
												count=9;
											}
										}
										para3.add(phrase3);
    									
    								}
								}
								
								para3.setIndentationLeft(10);
								document.add(para3);
							}
							para3 = new Paragraph("  ");
							document.add(para3);
					}
				}//二级 结束---------------------------------------------------------
			}//一级结束始---------------------------------------------------------
		  
		}
		//关闭文档
		document.close();
	}
	
	
	/**
	 * @描述: 年鉴导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 018-2-28 下午04:59:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void exportHjmdtj_10335_njdc(HjmdExportModel model , OutputStream os, User user) throws Exception{
		
		/**学年*/
		String xn = model.getXn();
		/**学期*/
		String xq = model.getXq();
		/**学期名称*/
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
		}
		/**项目类型*/
		String[] xmlx = model.getXmlx();
		/**学院代码*/
		String[] xydm = model.getXydm();
		/**项目性质*/
		String[] xmxz = model.getXmxz();
		/**项目名称*/
		String[] xmmc = model.getXmmc();
		
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(titleXmlx.toString() + "获奖名单", 0);
		ws.setColumnView(0, 2);
		ws.setRowView(0, 500);
		ws.setRowView(1, 500);
		ws.setRowView(2, 500);
		
		/**学院列表*/
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**各奖学金获奖人数*/
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**获奖姓名*/
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		/**项目类型名称*/
		List<String> xmlxmc = dao.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		/**奖学金名称样式*/
		WritableCellFormat titlFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.createFont("仿宋_GB2312"),16);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		titlFormat.setFont(jxjFont);
		titlFormat.setAlignment(Alignment.CENTRE);
		
		
		/**奖项+学院样式*/
		WritableCellFormat jxxyFormat = new WritableCellFormat();
		WritableFont jxxyFont = new WritableFont(WritableFont.createFont("仿宋_GB2312"),16);
		jxxyFont.setBoldStyle(WritableFont.BOLD);
		jxxyFormat.setFont(jxjFont);
		jxxyFormat.setAlignment(Alignment.LEFT);
		
		ws.addCell(new Label(1, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "学年", lxmc, "发文名单"), titlFormat));
		ws.mergeCells(1, 0,9, 1);
		
		/**xmList 获奖名单 */
		/**jxjdmList 项目获奖人数*/
		/**xymcList 学院获奖人数*/
		/**输出第一级奖学金名称和获奖人数*/
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			properties.row =properties.row+2; //浙江大学开头空两行
			for (int i=0;i<jxjdmList.size();i++) {//一级开始---------------------------------------------------------
				/**将奖学金名称写入到EXCEL中*/
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc);
				resultCell.setCellFormat(jxxyFormat);
				ws.setRowView(properties.row, 500);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 9, properties.row);
				
				//二级 开始 输出第二级将学院名称写到EXCEL中---------------------------------------------------------
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0]: "";
						
						List<String> xmXhByXyList = new ArrayList<String>();//存放每个奖学金下面的每个学院的获奖名单
						List<String> xmByXyList = new ArrayList<String>();
						for (String[] xmArr : xmList) {//三级 开始 ---------------------------------------------------------
							if (xmArr != null && xmArr.length >= 4
									&& xy.equalsIgnoreCase(xmArr[0])
									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
								xmByXyList.add(xmArr[2]);
								xmXhByXyList.add(xmArr[4]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//输出学院名称
						++properties.row;
						xy+="("+xymcArr[2]+"人)";
						System.out.println("学院："+xy);
						Label xymcCell = new Label(1,properties.row,xy );
						xymcCell.setCellFormat(jxxyFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 9, properties.row);
						//输出第三级将学院下面的获奖名单输出
						writeHjmdhzExcel_10335_njdc(ws, properties, xmByXyList,xmXhByXyList,0);//将获奖名单写入到EXCEL中
					}
				}//二级 结束---------------------------------------------------------
			}//一级结束始---------------------------------------------------------
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
		
	}
	
	/**
	 * @描述: 个性化年鉴导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-28 下午05:09:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ws
	 * @param properties
	 * @param xmList
	 * @param xmXhList
	 * @param rowCellCount
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	private void writeHjmdhzExcel_10335_njdc(WritableSheet ws,
			JxjExportProperties properties, List<String> xmList,List<String> xmXhList,
			int rowCellCount) throws Exception {
		properties.rowCellCount = 1;
		/**开始写姓名行*/
		properties.row++;
		/**开始新奖学金的姓名*/
		properties.x_axis = rowCellCount;
		/**姓名样式*/
		WritableCellFormat xmFormat = new WritableCellFormat();
		WritableFont xmFont = new WritableFont(WritableFont.createFont("仿宋_GB2312"),13);
		xmFont.setBoldStyle(WritableFont.NO_BOLD);
		xmFormat.setFont(xmFont);
		xmFormat.setAlignment(Alignment.LEFT);
		
		if (xmList != null) {
 			for (int index = 1; index < xmList.size() + 1; index++) {
 				/**合并单元格字符串长度*/
				int nameLength = 3;
				int Column = 0;
				String cellContent = xmXhList.get(index - 1);
				String xmContent = xmList.get(index - 1);
				int strLength = xmContent.replace(" ", "").length();
				if(strLength>nameLength){
					Column = 1;
				}
				
				properties.rowCellCount+=Column;
				
				if (properties.rowCellCount >= 10) {
					/**满9个就换行*/
					properties.row++;
					properties.x_axis = 0;
					/**重新每行计数*/
					properties.rowCellCount = 1;
				}
				
				/**两个字的姓名不在sql当中处理，在这里增加*/
				if(strLength>2){
					xmContent=xmContent.replace(" ", "").substring(0, 1)+" "+xmContent.replace(" ", "").substring(1, 2);
				}
				
				Label cell = new Label(++properties.x_axis, properties.row,cellContent);
				cell.setCellFormat(xmFormat);
				ws.mergeCells(properties.x_axis, properties.row, properties.x_axis+Column, properties.row);
				ws.addCell(cell); 
				ws.setRowView(properties.row, 500);
				ws.setColumnView(properties.x_axis, 9);
				properties.rowCellCount++;
				properties.x_axis+=(Column);
			}
		}
		/**新奖学金换行*/
		properties.row += 1;
	}
}