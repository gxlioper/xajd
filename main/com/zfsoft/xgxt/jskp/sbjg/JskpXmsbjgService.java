package com.zfsoft.xgxt.jskp.sbjg;

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

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.jskp.lxsq.LxsqDao;

public class JskpXmsbjgService extends SuperServiceImpl<JskpXmsbjgForm, JskpXmsbjgDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	JskpXmsbjgDao dao = new JskpXmsbjgDao();
	


	// 保存
	public boolean saveXmsbjg(JskpXmsbjgForm model, User user) throws Exception {
		if ("save".equalsIgnoreCase(model.getType())) {
			String id = UniqID.getInstance().getUniqIDHash();
			model.setJgid(id);
			model.setSbsj(GetTime.getTimeByFormat(DATE_FORMAT));
			return dao.runInsert(model);
		}else{
			return dao.runUpdate(model);
		}
		 
		
	}



	/** 
	 * @描述:获取单个人的学年汇总
	 * @作者：cq [工号：785]
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
	 * @描述: 当参数设置为0时，结果查看方法另外写
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-22 下午04:49:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXxckForJgid(String jgid) throws Exception{
		return dao.getXxckForJgid(jgid);
	}
	
	/**
	 * @描述: 纪实考评数据统计
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-27 下午02:43:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param SearchXn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDateForSearchXn (JskpXmsbjgForm model, User user, String SearchXn) throws Exception{
		return dao.getDateForSearchXn(model,user,SearchXn);
	}
	
	/**
	 * @描述: 纪实考评分统计Excle
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-27 下午02:41:50
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
		String fileName = "纪实考评数据统计.xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);
		
		/**判断指定的路径或着指定的目录文件是否已经存在*/
		if(!file.exists()){
			file.createNewFile();
		}
		
		/**创建工作簿*/
		WritableWorkbook wwb = Workbook.createWorkbook(file);
		
		/**创建工作表（sheet1）*/
		WritableSheet sheet = wwb.createSheet("纪实考评数据统计", 0);
		
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
		sheet.mergeCells(0, 0, 26, 0);/*标题*/
		
		/**设置列宽============begin============*/
		sheet.setColumnView(0, 14);
		sheet.setColumnView(1, 20);
		sheet.setColumnView(2, 14);
		sheet.setColumnView(3, 8);
		sheet.setColumnView(4, 28);
		sheet.setColumnView(5, 28);
		sheet.setColumnView(6, 28);
		for(int i = 7; i < 27; i++){
			sheet.setColumnView(i, 18);
		}
		
		/**画表格开始============begin============*/
		Label col1row1 = new Label(0, 0,xn+"学年纪实考评分统计",title);
		Label col1row2 = new Label(0, 1,"学号",headOne);
		Label col2row2 = new Label(1, 1,"姓名",headOne);
		Label col3row2 = new Label(2, 1,"学年总分",headOne);
		Label col4row2 = new Label(3, 1,"年级",headOne);
		Label col5row2 = new Label(4, 1,"学院名称",headOne);
		Label col6row2 = new Label(5, 1,"专业名称",headOne);
		Label col7row2 = new Label(6, 1,"班级名称",headOne);
		Label col8row2 = new Label(7, 1,"创新创业总分",headTwo);
		Label col9row2 = new Label(8, 1,"创新创业学院排名",headTwo);
		Label col10row2 = new Label(9, 1,"创新创业专业排名",headTwo);
		Label col11row2 = new Label(10, 1,"创新创业班级排名",headTwo);
		Label col12row2 = new Label(11, 1,"对外交流总分",headTwo);
		Label col13row2 = new Label(12, 1,"对外交流学院排名",headTwo);
		Label col14row2 = new Label(13, 1,"对外交流专业排名",headTwo);
		Label col15row2 = new Label(14, 1,"对外交流班级排名",headTwo);
		Label col16row2 = new Label(15, 1,"公益服务总分",headTwo);
		Label col17row2 = new Label(16, 1,"公益服务学院排名",headTwo);
		Label col18row2 = new Label(17, 1,"公益服务专业排名",headTwo);
		Label col19row2 = new Label(18, 1,"公益服务班级排名",headTwo);
		Label col20row2 = new Label(19, 1,"社会工作总分",headTwo);
		Label col21row2 = new Label(20, 1,"社会工作学院排名",headTwo);
		Label col22row2 = new Label(21, 1,"社会工作专业排名",headTwo);
		Label col23row2 = new Label(22, 1,"社会工作班级排名",headTwo);
		Label col24row2 = new Label(23, 1,"文体活动总分",headTwo);
		Label col25row2 = new Label(24, 1,"文体活动学院排名",headTwo);
		Label col26row2 = new Label(25, 1,"文体活动专业排名",headTwo);
		Label col27row2 = new Label(26, 1,"文体活动班级排名",headTwo);
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
		
		if(resultList.size()>0){
			for(int j = 0; j < resultList.size(); j++){
				Map<String,String> map = resultList.get(j);
				Label date_xh = new Label(0, 2+j, map.get("xh"), body);
				Label date_xm = new Label(1, 2+j, map.get("xm"), body);
				Label date_zf = new Label(2, 2+j, map.get("zf"), body);
				Label date_nj = new Label(3, 2+j, map.get("nj"), body);
				Label date_xymc = new Label(4, 2+j, map.get("xymc"), body);
				Label date_zymc = new Label(5, 2+j, map.get("zymc"), body);
				Label date_bjmc = new Label(6, 2+j, map.get("bjmc"), body);
				Label date_cxzf = new Label(7, 2+j, map.get("cxzf"), body);
				Label date_cxzfxypm = new Label(8, 2+j, map.get("cxzfxypm"), body);
				Label date_cxzfzypm = new Label(9, 2+j, map.get("cxzfzypm"), body);
				Label date_cxzfbjpm = new Label(10, 2+j, map.get("cxzfbjpm"), body);
				Label date_dwzf = new Label(11, 2+j, map.get("dwzf"), body);
				Label date_dwzfxypm = new Label(12, 2+j, map.get("dwzfxypm"), body);
				Label date_dwzfzypm = new Label(13, 2+j, map.get("dwzfzypm"), body);
				Label date_dwzfbjpm = new Label(14, 2+j, map.get("dwzfbjpm"), body);
				Label date_gyzf = new Label(15, 2+j, map.get("gyzf"), body);
				Label date_gyzfxypm = new Label(16, 2+j, map.get("gyzfxypm"), body);
				Label date_gyzfzypm = new Label(17, 2+j, map.get("gyzfzypm"), body);
				Label date_gyzfbjpm = new Label(18, 2+j, map.get("gyzfbjpm"), body);
				Label date_shzf = new Label(19, 2+j, map.get("shzf"), body);
				Label date_shzfxypm = new Label(20, 2+j, map.get("shzfxypm"), body);
				Label date_shzfzypm = new Label(21, 2+j, map.get("shzfzypm"), body);
				Label date_shzfbjpm = new Label(22, 2+j, map.get("shzfbjpm"), body);
				Label date_wtzf = new Label(23, 2+j, map.get("wtzf"), body);
				Label date_wtzfxypm = new Label(24, 2+j, map.get("wtzfxypm"), body);
				Label date_wtzfzypm = new Label(25, 2+j, map.get("wtzfzypm"), body);
				Label date_wtzfbjpm = new Label(26, 2+j, map.get("wtzfbjpm"), body);
				
				sheet.addCell(date_xh);
				sheet.addCell(date_xm);
				sheet.addCell(date_zf);
				sheet.addCell(date_nj);
				sheet.addCell(date_xymc);
				sheet.addCell(date_zymc);
				sheet.addCell(date_bjmc);
				sheet.addCell(date_cxzf);
				sheet.addCell(date_cxzfxypm);
				sheet.addCell(date_cxzfzypm);
				sheet.addCell(date_cxzfbjpm);
				sheet.addCell(date_dwzf);
				sheet.addCell(date_dwzfxypm);
				sheet.addCell(date_dwzfzypm);
				sheet.addCell(date_dwzfbjpm);
				sheet.addCell(date_gyzf);
				sheet.addCell(date_gyzfxypm);
				sheet.addCell(date_gyzfzypm);
				sheet.addCell(date_gyzfbjpm);
				sheet.addCell(date_shzf);
				sheet.addCell(date_shzfxypm);
				sheet.addCell(date_shzfzypm);
				sheet.addCell(date_shzfbjpm);
				sheet.addCell(date_wtzf);
				sheet.addCell(date_wtzfxypm);
				sheet.addCell(date_wtzfzypm);
				sheet.addCell(date_wtzfbjpm);
			}
		}
		/**画表格结束=============end=============*/
		
		/**写入Excel表格中*/
		wwb.write();
		/**关闭流*/
		wwb.close();
		return file;
	}
	
	/**
	 * @描述: 思政素质结果列表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-3-13 上午09:33:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @param SearchXn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getSzszList (JskpXmsbjgForm model, User user) throws Exception{
		return dao.getSzszList(model,user);
	}
	
	/**
	 * @描述: 下载导入模板,绘制excel表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-3-14 上午10:21:38
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
		WritableFont bodyFont = new WritableFont(WritableFont.createFont("仿宋"),11,WritableFont.NO_BOLD );
		
		/**单元格字体格式写入*/
		WritableCellFormat body = new WritableCellFormat(bodyFont);
		
		/**设置单元格对齐*/
		/*水平居中*/
		body.setAlignment(jxl.format.Alignment.CENTRE);
		/*垂直居中*/
		body.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		
		/**设置列宽*/
		ws.setColumnView(0, 11);/*学号*/
		ws.setColumnView(1, 16);/*项目名称*/
		ws.setColumnView(2, 14);/*指导部门*/
		ws.setColumnView(3, 20);/*项目类别*/
		ws.setColumnView(4, 14);/*负责人*/
		ws.setColumnView(5, 16);/*负责人联系方式*/
		ws.setColumnView(6, 14);/*指导老师*/
		ws.setColumnView(7, 18);/*指导老师联系方式*/
		ws.setColumnView(8, 22);/*立项理由*/
		ws.setColumnView(9, 10);/*分数*/
		ws.setColumnView(10, 26);/*学年(例如：2017-2018)*/
		ws.setColumnView(11, 32);/*立项时间(例如：2017-03-01)*/
	    
	    
	    /*第一行抬头*/
		Label row1col1= new Label(0, 0, "学号",body);
		Label row1col2= new Label(1, 0, "项目名称",body);
		Label row1col3= new Label(2, 0, "指导部门",body);/*表格里填写部门名称，数据库存代码*/
		Label row1col4= new Label(3, 0, "项目类别",body);/*表格里填写类别名称，数据库存代码*/
		Label row1col5= new Label(4, 0, "负责人",body);/*负责人填写名字，数据库存工号*/
		Label row1col6= new Label(5, 0, "负责人联系方式",body);
		Label row1col7= new Label(6, 0, "指导老师",body);
		Label row1col8= new Label(7, 0, "指导老师联系方式",body);
		Label row1col9= new Label(8, 0, "立项理由",body);
		Label row1col10= new Label(9, 0, "分数",body);
		Label row1col11= new Label(10, 0, "学年(例如：2017-2018)",body);
		Label row1col12= new Label(11, 0, "立项时间(例如：2017-03-01)",body);
		Label row1col13= new Label(12, 0, "短学期",body);
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
	    LxsqDao lxsqDao = new LxsqDao();
	    List<HashMap<String, String>> xmlbList = lxsqDao.getXmlbListNotLikeNl();
	    Label ws1row1col1= new Label(0, 0, "项目类别名称",body);
	    ws1.addCell(ws1row1col1);
	    for (int i = 0; i < xmlbList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, xmlbList.get(i).get("xmlbmc"),body);
	    	ws1.addCell(tempLabel);
		}
	    
	    /**sheet3：指导部门对照表*/
	    WritableSheet ws2 = wwb.createSheet("指导部门对照表", 2);
	    
	    List<HashMap<String, String>> zdbmList = lxsqDao.getZdbmList();
	    Label ws2row1col1= new Label(0, 0, "指导部门",body);
	    ws2.addCell(ws2row1col1);
	    for (int i = 0; i < zdbmList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, zdbmList.get(i).get("zdbmmc"),body);
	    	 ws2.addCell(tempLabel);
		}
	    
	    /**sheet4:短学期对照表*/
	    WritableSheet ws3 = wwb.createSheet("短学期对照表", 3);
	    
	    List<HashMap<String, String>> dxqList = lxsqDao.getDxqList();
	    Label ws3row1col1= new Label(0, 0, "短学期名称",body);
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
	 * @描述: 表格中的数据保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-3-14 下午05:18:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param jskpXmsbjgForm
	 * @param user
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,JskpXmsbjgForm jskpXmsbjgForm,User user){
		HashMap<String, Object> resultMap = null;
		try{
			/*导入信息验证*/
			resultMap = this.DrExcelInfoCheck(is, jskpXmsbjgForm, user);
			/**判断Excle表格是否为空*/
			if("null".equals(resultMap.get("result")) || resultMap.isEmpty() || "hbyzsb".equals(resultMap.get("result")) || "excelrepeat".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){
				/**把数据导入思想政治素质结果表*/
				List<String[]> paraSzszlist = (List<String[]>) resultMap.get("drSzszjglist");
				if( paraSzszlist == null ||paraSzszlist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
			  	 this.saveDrJgbData(paraSzszlist);
			}else{
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),jskpXmsbjgForm.getExclePath());
				resultMap.put("gid", gid);
				this.saveDrJgbData((List<String[]>) resultMap.get("drSzszjglist"));
				logger.info("导入失败！");
			}
		}catch(Exception e){
			//TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @描述: 导入信息验证
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-3-14 下午06:00:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,JskpXmsbjgForm jskpXmsbjgForm,User user){
		
		/**导入结果表数组*/
		List<String[]> drSzszjglist = new ArrayList<String[]>();
		
		/*错误记录数组*/
		List<String[]> errorlist = new ArrayList<String[]>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
		Workbook rwb = null;
		
		try {
			rwb = Workbook.getWorkbook(is);
			Sheet rs = rwb.getSheet(0);
			/*得到所有的列*/
		    int clos = rs.getColumns();
		    /*得到所有的行*/
	        int rows = rs.getRows();
	        if(rows < 2){
	        	resultMap.put("result", "null");
	        }else if(!this.checkExcelRepeat(rs, clos, rows)){
	        	resultMap.put("result", "excelrepeat");
	        }else{
	        	/*行*/
	        	for(int i = 1; i < rows; i++){
	        		/*取出每行验证数据，塞入lsmap*/
					HashMap<String, String>  lsmap = new HashMap<String, String>();
	        		String xh = rs.getCell(0,i).getContents();
	        		String xmmc = rs.getCell(1,i).getContents();
	        		String zdbmmc = rs.getCell(2,i).getContents();
	        		String xmlbmc = rs.getCell(3,i).getContents();
	        		String fzrxm = rs.getCell(4,i).getContents();
	        		String fzrlxfs = rs.getCell(5,i).getContents();
	        		String zdls = rs.getCell(6,i).getContents();
	        		String zdlslxfs = rs.getCell(7,i).getContents();
	        		String lxly = rs.getCell(8,i).getContents();
	        		String fs = rs.getCell(9,i).getContents();
	        		String xn = rs.getCell(10,i).getContents();
	        		String lxsj = rs.getCell(11,i).getContents();
	        		String dxqmc = rs.getCell(12,i).getContents();
	        		lsmap.put("xh", xh);
	        		lsmap.put("xmmc", xmmc);
	        		lsmap.put("zdbmmc", zdbmmc);
	        		lsmap.put("xmlbmc", xmlbmc);
	        		lsmap.put("fzrxm", fzrxm);
	        		lsmap.put("fzrlxfs", fzrlxfs);
	        		lsmap.put("zdls", zdls);
	        		lsmap.put("zdlslxfs", zdlslxfs);
	        		lsmap.put("lxly", lxly);
	        		lsmap.put("fs", fs);
	        		lsmap.put("xn", xn);
	        		lsmap.put("lxsj", lxsj);
	        		lsmap.put("dxqmc", dxqmc);
	        		
	        		/*空行判断*/
					if(this.checkNullRow(lsmap)){
						continue;
					}
					
					HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, jskpXmsbjgForm);
					ArrayList<String> paralist = new ArrayList<String>();
					
					if("false".equals(resultmap.get("result"))){
						paralist.add(lsmap.get("xh"));
						paralist.add(lsmap.get("xmmc"));
						paralist.add(lsmap.get("zdbmmc"));
						paralist.add(lsmap.get("xmlbmc"));
						paralist.add(lsmap.get("fzrxm"));
						paralist.add(lsmap.get("fzrlxfs"));
						paralist.add(lsmap.get("zdls"));
						paralist.add(lsmap.get("zdlslxfs"));
						paralist.add(lsmap.get("lxly"));
						paralist.add(lsmap.get("fs"));
						paralist.add(lsmap.get("xn"));
						paralist.add(lsmap.get("lxsj"));
						paralist.add(lsmap.get("dxqmc"));
						
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
						/*编辑一个id供数据插入*/
  	        			String jgid = UniqID.getInstance().getUniqIDHash().toUpperCase();
  	        			paralist.add(jgid);                   /*每次循环前先生成一次sqid*/
  	        			paralist.add(lsmap.get("xh"));        /*学号*/
  	        			paralist.add(lsmap.get("xmmc"));      /*项目名称*/
  	        			paralist.add(lsmap.get("zdbm"));      /*指导部门*/
  	        			paralist.add(lsmap.get("xmlb"));      /*项目类别*/
  	        			paralist.add(lsmap.get("fzr"));       /*负责人*/
  	        			paralist.add(lsmap.get("fzrlxfs"));   /*负责人联系方式*/
  	        			paralist.add(lsmap.get("zdls"));      /*指导老师*/
  	        			paralist.add(lsmap.get("zdlslxfs"));  /*指导老师联系方式*/
  	        			paralist.add(lsmap.get("lxly"));      /*立项理由*/
  	        			paralist.add(lsmap.get("fs"));        /*分数*/
  	        			paralist.add(lsmap.get("xn"));        /*学年*/
  	        			paralist.add(lsmap.get("lxsj"));      /*立项时间*/
  	        			paralist.add(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));/*系统当前时间，上报时间*/
  	        			paralist.add(user.getUserName());     /*导入人工号*/
  	        			paralist.add(lsmap.get("dxq"));       /*短学期*/
  	        			drSzszjglist.add(paralist.toArray(new String[]{}));
					}
	        	}
	        	 resultMap.put("drSzszjglist", drSzszjglist);
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
	 * @描述: 验证表格中是否有重复数据（参与人、参与时间、参与项目名称）
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-3-15 上午09:38:43
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
			/*项目名称*/
			String xmmc = rs.getCell(1, i).getContents();
			/*立项时间（参与时间）*/
			String lxsj = rs.getCell(11,i).getContents();
			/*学号（参与人）*/
			String xh = rs.getCell(0,i).getContents();
			String str = "";
			if(StringUtils.isNotNull(xh)){
				str = str + xh.trim();
			}
			if(StringUtils.isNotNull(xmmc)){
				str = str + xmmc.trim();
			}
			if(StringUtils.isNotNull(lxsj)){
				str = str + lxsj.trim();
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
	 * @描述: 检查空行
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-03-15 上午11:49:02
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
	 * @描述: 验证每行的记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-3-15 上午10:55:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lsmap
	 * @param jskpXmsbjgForm
	 * @param user
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap,JskpXmsbjgForm jskpXmsbjgForm){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String message = "true";
		
		/**验证学号、项目名称、指导部门、项目类别、负责人、分数、学年、立项时间、短学期不可为空*/
		if(!this.checkNotNull(lsmap)){
			message = "false";
			lsmap.put("kyz", "学号、项目名称、指导部门、项目类别、负责人、分数、学年、立项时间、短学期不可为空！");
		}
		
		/**1、验证学号是否存在学生信息表中*/
		String xh = dao.getXhforXxb(lsmap.get("xh"));
		if(StringUtils.isNull(xh)){
			message = "false";
			lsmap.put("xhyz", "此学号不存在，请核对！");
		}
		
		/**2、指导部门是否存在*/
		String zdbm = dao.getZdbm(lsmap.get("zdbmmc"));
		if(StringUtils.isNull(zdbm)){
			message = "false";
			lsmap.put("zdbmyz", "指导部门不存在或存在空格，请核对！");
		}
		
		/**3、验证项目类别是否存在*/
		String xmlb = dao.getXmlb(lsmap.get("xmlbmc"));
		if(StringUtils.isNull(xmlb)){
			message = "false";
			lsmap.put("xmlbyz", "项目类别不存在或存在空格，请核对！");
		}
		
		/**4、验证负责人是否存在*/
		String fzr = dao.getFzr(lsmap.get("fzrxm"));
		if(StringUtils.isNull(fzr)){
			message = "false";
			lsmap.put("fzryz", "负责人不存在或者存在空格，请核对！");
		}
		
		/**5、验证立项时间是否正确*/
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = (Date)formatter.parse(lsmap.get("lxsj"));
			if(!lsmap.get("lxsj").equals(formatter.format(date))){
				message = "false";
				lsmap.put("lxsjyz", "参与时间格式必须为yyyy-mm-dd！");
			}
		} catch (ParseException e) {
			message = "false";
			lsmap.put("lxsjyz", "参与时间格式必须为yyyy-mm-dd！");
		}
		
		/**6、主键验证，同一学生、同一项目、同一时间不能存在2条及2条以上记录*/
		if(!dao.checkIsNotExists(lsmap.get("xh"), lsmap.get("xmmc"), lsmap.get("lxsj"))){
			message = "false";
		    lsmap.put("zjyz", "学生在同一日期已经申请过该项目，请检查！");
		}
		
		/**7、验证短学期是否存在*/
		String dxq = dao.getDxqdm(lsmap.get("dxqmc"));
		if(StringUtils.isNull(dxq)){
			message = "false";
			lsmap.put("dxqyz", "短学期不存在，请核对！");
		}
		
		/**当所有验证都没有问题时，*/
		if("true".equals(message)){
			lsmap.put("zdbm", zdbm);/*指导部门代码*/
			lsmap.put("xmlb", xmlb);/*项目类别代码*/
			lsmap.put("fzr", fzr);/*负责人工号*/
			lsmap.put("dxq", dxq);/*短学期*/
		}
		
		/**输出数据*/
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	/**
	 * @描述: 验证非空项
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-03-08 下午02:28:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yzMap
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkNotNull(HashMap<String,String> yzMap){
		
		boolean rs = true;
		for(String key : yzMap.keySet()){
			if(StringUtils.isNull(yzMap.get(key)) && !("lxly").equals(key) && !("zdlslxfs").equals(key) && !("zdls").equals(key) && !("fzrlxfs").equals(key)){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	/**
	 * @描述: 批量导入
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-3-15 下午04:10:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paraSqblist
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveDrJgbData(List<String[]> drSzszjglist) 
		throws Exception{
		if(drSzszjglist != null && drSzszjglist.size() > 0){
			return dao.saveDrJgbData(drSzszjglist).length > 0 ? true :false;
		}else{
			return false;
		}
	}
	
	/**
	 * @描述: 将错误excel表格写入服务器
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-3-15 下午05:00:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param errorlist
	 * @param filepath
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String uploadErrorExcel(List<String[]> errorlist,String filepath)
		throws Exception{
		
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
   		/**
   		 * sheet1
   		 */
   		/*第一行抬头*/
		Label row1col1= new Label(0, 0, "学号",titlefont);
		Label row1col2= new Label(1, 0, "项目名称",titlefont);
		Label row1col3= new Label(2, 0, "指导部门",titlefont);/*表格里填写部门名称，数据库存代码*/
		Label row1col4= new Label(3, 0, "项目类别",titlefont);/*表格里填写类别名称，数据库存代码*/
		Label row1col5= new Label(4, 0, "负责人",titlefont);/*负责人填写名字，数据库存工号*/
		Label row1col6= new Label(5, 0, "负责人联系方式",titlefont);
		Label row1col7= new Label(6, 0, "指导老师",titlefont);
		Label row1col8= new Label(7, 0, "指导老师联系方式",titlefont);
		Label row1col9= new Label(8, 0, "立项理由",titlefont);
		Label row1col10= new Label(9, 0, "分数",titlefont);
		Label row1col11= new Label(10, 0, "学年(例如：2017-2018)",titlefont);
		Label row1col12= new Label(11, 0, "立项时间(例如：2017-03-01)",titlefont);
		Label row1col13= new Label(12, 0, "短学期",titlefont);
   		
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
               	 if(j<=11){
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
	
	/**
	 * @描述: 错误excle导出字体样式
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-3-15 下午05:01:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paras
	 * @return
	 * @throws WriteException
	 * WritableCellFormat 返回类型 
	 * @throws
	 */
	public WritableCellFormat getFontStyle(String paras)
		throws WriteException{

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
}
