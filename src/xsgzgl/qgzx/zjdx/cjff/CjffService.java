/**
 * @部门:学工产品事业部
 * @日期：2016-12-20 下午03:15:35 
 */  
package xsgzgl.qgzx.zjdx.cjff;

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

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zjly.zhf.zhfdr.ZhfDrDao;
import com.zfsoft.xgxt.zjly.zhf.zhfdr.ZhfDrForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-12-20 下午03:15:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CjffService extends SuperServiceImpl<CjffForm, CjffDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 
	 * @描述: 用人单位List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 上午10:11:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYrdwList(){
		return dao.getYrdwList();
	}
	
	/**
	 * 
	 * @描述: 用人单位List权限控制
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2016-12-21 上午10:11:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYrdwyList(User user){
		return dao.getYrdwyList(user);
	}
	
	/**
	 * 
	 * @描述:岗位性质List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 上午10:14:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGwxzList(){
		return dao.getGwxzList();
	}
	
	/**
	 * 
	 * @描述: 岗位类别List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 上午10:15:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGwlbList(){
		return dao.getGwlbList();
	}
	
	/**
	 *
	 * @描述:校区List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 上午10:16:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXqList(){
		return dao.getXqList();
	}
	
	/**
	 * 
	 * @描述: 参数设置信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 上午10:47:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCsszMap(){
		return dao.getCsszMap();
	}
	
	/**
	 * 
	 * @描述: 构造发放月份List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 上午11:12:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> createList(String ksyf,String jsyf){

		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		//先进行空判断
		if(StringUtils.isNull(ksyf) && StringUtils.isNull(jsyf)){
			return null;
		}else if(StringUtils.isNull(ksyf) || StringUtils.isNull(jsyf)){
			String yfz =  StringUtils.isNull(ksyf) ? jsyf :ksyf;
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("yf", yfz);
			yfList.add(map);
			return yfList;
		}else {
			yfList = dao.getYfList(ksyf, jsyf);
		}
		return yfList;
	}
	
	/**
	 * 
	 * @描述: 验证是否在酬金发放开放时间段内
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 下午02:22:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsInKfsjd(int kssj,int jssj){
		return dao.checkIsInKfsjd(kssj, jssj);
	}
	
	/**
	 * 
	 * @描述: 验证数据是否有重复，唯一键(xh,ffndyf,yrdwdm)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 下午04:21:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param ffndyf
	 * @param yrdwdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExists(String xh,String ffndyf,String yrdwdm,String id){
		return dao.checkIsNotExists(xh, ffndyf, yrdwdm, id);
	}
	
	/**
	 * 
	 * @描述: 获取学生基本信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 下午04:56:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXsxxck(String id){
		return dao.getXsxxck(id);
	}
	
	/**
	 * 
	 * @描述: 业务表单信息查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 下午05:44:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getYwbdxxCk(String id){
		return dao.getYwbdxxCk(id);
	}
	
	/**
	 * 
	 * @描述: 验证学号，姓名是否正确
	 * 如果学号存在view_xsxxb中，则需验证view_xsxxb中的姓名与输入的xm是否相等;如果不存在，即得到的xm为空，直接返回true
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 下午04:43:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkXhXmIsTrue(String xh,String xm){
		return dao.checkXhXmIsTrue(xh, xm);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存表单
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-22 上午09:09:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveForm(CjffForm model) throws Exception{
		String type = model.getType();
		boolean result = true;
		if(type.indexOf("submit") != -1){
			model.setSftj("1");
		}
		if(StringUtils.isNull(model.getId())){
			model.setLrsj(GetTime.getTimeByFormat(DATE_FORMAT));
			result = dao.runInsert(model);
		}else{
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	/**
	 * @throws Exception 
	 *
	 * @描述: 提交
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-22 上午09:27:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submit(String[] id) throws Exception{
		return dao.submit(id);
	}
	
	/**
	 * 
	 * @描述:下载导入模板,绘制excel表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-23 上午09:30:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param os
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public  void createWwb(OutputStream os) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableCellFormat titlefont =  this.getFontStyle("title");
		/**
		 * sheet1
		 */
		WritableSheet ws = wwb.createSheet("数据导入模板", 0);
	    Label row1col1= new Label(0, 0, "学号",titlefont);
	    Label row1col2= new Label(1, 0, "姓名",titlefont);
	    Label row1col3= new Label(2, 0, "发放年度月份(如：2017-01)",titlefont);
//	    Label row1col4= new Label(3, 0, "发放月份",titlefont);
	    Label row1col5= new Label(3, 0, "用人单位",titlefont);
		Label row1col6= new Label(4, 0, "校区",titlefont);
		Label row1col7= new Label(5, 0, "岗位类别",titlefont);
		Label row1col8= new Label(6, 0, "岗位性质",titlefont);
		Label row1col9= new Label(7, 0, "酬金(元)",titlefont);
		Label row1col10= new Label(8, 0, "工作内容",titlefont);
		Label row1col11= new Label(9, 0, "备注",titlefont);
		 
		ws.addCell(row1col1);
		ws.addCell(row1col2);
		ws.addCell(row1col3);
//		ws.addCell(row1col4);
		ws.addCell(row1col5);
		ws.addCell(row1col6);
		ws.addCell(row1col7);
		ws.addCell(row1col8);
		ws.addCell(row1col9);
		ws.addCell(row1col10);
	    ws.addCell(row1col11);
	    WritableCellFormat wcfF = new WritableCellFormat(
	    	      NumberFormats.TEXT); //
	    CellView cv = new CellView(); //定义一个列显示样式 
	    cv.setFormat(wcfF);//把定义的单元格格式初始化进去
	    cv.setSize(10*265);//设置列宽度（不设置的话是0，不会显示）
	    for (int i = 0; i < 10; i++) {
	    	ws.setColumnView(i, cv);
		}
	    //设置工作表中第n列的样式
	    /**
	     * sheet2 用人单位对照表
	     */
	    WritableSheet ws1 = wwb.createSheet("用人单位对照表", 1);
	    List<HashMap<String, String>> yrdwList = dao.getYrdwList();
	    Label ws1row1col1= new Label(0, 0, "用人单位名称",titlefont);
//	    Label ws1row1col2= new Label(1, 0, "用人单位名称",titlefont);
	    ws1.addCell(ws1row1col1);
//	    ws1.addCell(ws1row1col2);
	    for (int i = 0; i < yrdwList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, yrdwList.get(i).get("yrdwmc"),titlefont);
//	    	Label tempLabe2 = new Label(1, i+1, yrdwList.get(i).get("yrdwmc"),titlefont);
	    	 ws1.addCell(tempLabel);
//	 	     ws1.addCell(tempLabe2);
		}
	    /**
	     * sheet3 校区对照表
	     */
	    WritableSheet ws2 = wwb.createSheet("校区对照表", 2);
	    List<HashMap<String, String>> xqList = dao.getXqList();
	    Label ws2row1col1= new Label(0, 0, "校区名称",titlefont);
//	    Label ws2row1col2= new Label(1, 0, "校区名称",titlefont);
	    ws2.addCell(ws2row1col1);
//	    ws2.addCell(ws2row1col2);
	    for (int i = 0; i < xqList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, xqList.get(i).get("xqmc"),titlefont);
//	    	Label tempLabe2 = new Label(1, i+1, xqList.get(i).get("xqmc"),titlefont);
	    	 ws2.addCell(tempLabel);
//	 	     ws2.addCell(tempLabe2);
		}
	    /**
	     * shee4 岗位类别对照表
	     */
	    WritableSheet ws3 = wwb.createSheet("岗位类别对照表",3);
	    List<HashMap<String, String>> gwlbList = dao.getGwlbList();
	    Label ws3row1col1= new Label(0, 0, "岗位类别名称",titlefont);
//	    Label ws3row1col2= new Label(1, 0, "岗位类别名称",titlefont);
	    ws3.addCell(ws3row1col1);
//	    ws3.addCell(ws3row1col2);
	    for (int i = 0; i < gwlbList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, gwlbList.get(i).get("gwlbmc"),titlefont);
//	    	Label tempLabe2 = new Label(1, i+1, gwlbList.get(i).get("gwlbmc"),titlefont);
	    	 ws3.addCell(tempLabel);
//	 	     ws3.addCell(tempLabe2);
		}
	    /**
	     * shee5 岗位性质对照表
	     */
	    WritableSheet ws4 = wwb.createSheet("岗位性质对照表",4);
	    List<HashMap<String, String>> gwxzList = dao.getGwxzList();
	    Label ws4row1col1= new Label(0, 0, "岗位性质名称",titlefont);
//	    Label ws4row1col2= new Label(1, 0, "岗位性质名称",titlefont);
	    ws4.addCell(ws4row1col1);
//	    ws4.addCell(ws4row1col2);
	    for (int i = 0; i < gwxzList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, gwxzList.get(i).get("gwxzmc"),titlefont);
//	    	Label tempLabe2 = new Label(1, i+1, gwxzList.get(i).get("gwxzmc"),titlefont);
	    	ws4.addCell(tempLabel);
//	 	    ws4.addCell(tempLabe2);
		}
	    try{
			 wwb.write();
			 wwb.close();
		 }catch(Exception e){
				
		 }
	}
	
	/**
	 * 
	 * @描述: excel字体
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-23 上午09:47:25
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
//			  wcf_table.setShrinkToFit(true);
	          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
		}else if("errorinfo".equals(paras)){
			WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
	                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                jxl.format.Colour.RED);   
		  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//		  wcf_table.setShrinkToFit(true);
          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
          return wcf_table;
		}
     
        return null;
	}
    
	/**
	 * 
	 * @描述: 导入信息保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-23 上午11:17:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param zhfdrform
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,CjffForm cjffForm){
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap= this.DrExcelInfoCheck(is, cjffForm);
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
				
			
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),cjffForm.getFilepath());
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
	 * 
	 * @描述:导入信息验证
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-20 下午05:41:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param cjffForm
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,CjffForm cjffForm){
		//导入记录数组
		List<String[]> drlist = new ArrayList<String[]>();
		//错误记录数组
		List<String[]> errorlist = new ArrayList<String[]>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
	    String lrsj = GetTime.getTimeByFormat(DATE_FORMAT);
		Workbook rwb = null;
		try {
			rwb = Workbook.getWorkbook(is);
			Sheet rs=rwb.getSheet(0);
		    int clos=rs.getColumns();//得到所有的列
	        int rows=rs.getRows();//得到所有的行
//	        System.out.println(clos+" rows:"+rows);
//	        logger.info(clos+" rows:"+rows);
//	        logger.info(rs.getCell(0, 0).getContents());
//	        logger.info(rs.getCell(0,1).getContents());
//	        logger.info(rs.getCell(3,1).getContents());
//	        logger.info(rs.getCell(3,3).getContents());
//	        logger.info(rs.getCell(3,3).getContents());
	        //行
	        //判断excel表格是否为空
	        if(rows < 2){
	        	resultMap.put("result", "null");
	        }else if(!this.checkExcelRepeat(rs, clos, rows)){
	        	resultMap.put("result", "excelrepeat");
	        }else{
	          HashMap<String, String> hbMap = this.checkHbyz(rs, clos, rows, cjffForm.getFilepath());
	          if(!hbMap.get("message").equals("true")){
	        	  resultMap.put("result", hbMap.get("message"));
	        	  resultMap.put("gid", hbMap.get("gid"));
	        	  return resultMap;
	          }
	        	//行
	        	  for (int i = 1; i < rows; i++) {
	  	        	
	  	        		//取出每行验证数据，塞入lsmap
	  	        		HashMap<String, String>  lsmap = new HashMap<String, String>();
	  	        		String xh = rs.getCell(0, i).getContents();
	  	        		String xm = rs.getCell(1, i).getContents();
	  	        		String ffndyf = rs.getCell(2, i).getContents();
	  	        		String yrdwmc = rs.getCell(3, i).getContents();
	  	        		String xqmc = rs.getCell(4, i).getContents();
	  	        		String gwlbmc = rs.getCell(5, i).getContents();
	  	        		String gwxzmc = rs.getCell(6, i).getContents();
	  	        		String bcje = rs.getCell(7, i).getContents();
	  	        		String gznr = rs.getCell(8, i).getContents();
	  	        		String bz = rs.getCell(9, i).getContents();
	  	        		lsmap.put("xh", xh);
	  	        		lsmap.put("xm", xm);
	  	        		lsmap.put("ffndyf", ffndyf);
	  	        		lsmap.put("yrdwmc", yrdwmc);
	  	        		lsmap.put("xqmc", xqmc);
	  	        		lsmap.put("gwlbmc",gwlbmc);
	  	        		lsmap.put("gwxzmc",gwxzmc);
	  	        		lsmap.put("bcje", bcje);
	  	        		lsmap.put("gznr", gznr);
	  	        		lsmap.put("bz", bz);
	  	        		//空行判断
	  	        	    if(this.checkNullRow(lsmap)){
	  	        	    	continue;
	  	        	    }
	  	        		
	  	        		HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, cjffForm);
	  	        		ArrayList<String> paralist = new ArrayList<String>();
	  	        		if("false".equals(resultmap.get("result"))){
	  	        			paralist.add(lsmap.get("xh"));
	  	        			paralist.add(lsmap.get("xm"));
	  	        			paralist.add(lsmap.get("ffndyf"));
	  	        			paralist.add(lsmap.get("yrdwmc"));
	  	        			paralist.add(lsmap.get("xqmc"));
	  	        			paralist.add(lsmap.get("gwlbmc"));
	  	        			paralist.add(lsmap.get("gwxzmc"));
	  	        			paralist.add(lsmap.get("bcje"));
	  	        			paralist.add(lsmap.get("gznr"));
	  	        			paralist.add(lsmap.get("bz"));
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
	  	        			paralist.add(lsmap.get("xm"));
	  	        			paralist.add(lsmap.get("ffndyf"));
	  	        			paralist.add(lsmap.get("yrdwdm"));
	  	        			paralist.add(lsmap.get("xqdm"));
	  	        			paralist.add(lsmap.get("gwlbdm"));
	  	        			paralist.add(lsmap.get("gwxzdm"));
	  	        			paralist.add(lsmap.get("gss"));
	  	        			paralist.add(lsmap.get("bcje"));
	  	        			paralist.add(lsmap.get("gznr"));
	  	        			paralist.add(lsmap.get("bz"));
	  	        			paralist.add("0");
	  	        			paralist.add(cjffForm.getLrr());
	  	        			paralist.add(lrsj);
	  	        			drlist.add(paralist.toArray(new String[]{}));
	  	        		}
	  			}
//	        	  if(null == drlist && null == errorlist ){
//	        		  
//	        		  return resultMap;
//	        	  }
	        	  
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
	 * 
	 * @描述: 验证每行的记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-23 下午02:11:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lsmap
	 * @param cjffForm
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap,CjffForm cjffForm){
		/**
		 * 验证规则
		 * 1.xh存在的情况下，验证学号和姓名是否匹配
		 * 2.xh,ffndyf,yrdwdm主键验证
		 * 3.酬金超出上限要求必须输入备注
		 * 验证方式采取整行数据全部验证完，错误数据塞入lsmap，最后塞入返回map，进行返回
		 * 如果有一条验证规则不满足，塞入返回结果map为字符串"false",如果全部正确，为字符串"true"
		 */
		String message = "true";
		//为空验证
		if(!this.checkNotNull(lsmap)){
				message = "false";
				lsmap.put("kyz", "学号、姓名、发放年度、发放月份、用人单位、校区、岗位类别、岗位性质、报酬金额、工作内容不可为空！");
		}else{
			//xh,xm联合验证是否正确，学号存在，验证姓名是否与导入的姓名一致，如果不存在验证通过
			if(!dao.checkXhXmIsTrueDr(lsmap.get("xh"),lsmap.get("xm"))){
				message = "false";
				lsmap.put("xhxmlhyz", "学号、姓名不对应或有空格");
			}
			//发放年度月份格式检查，要符合xxxx-xx 比如2016-12
			try {
				{
					DateFormat formatter = new SimpleDateFormat("yyyy-MM");  
					 try {
						Date date = (Date)formatter.parse(lsmap.get("ffndyf"));
						if(!lsmap.get("ffndyf").equals(formatter.format(date))){
							message = "false";
							lsmap.put("ffndyfyz", "发放年度月份格式必须为yyyy-mm！");
						}
					} catch (ParseException e) {
						// TODO 自动生成 catch 块
						message = "false";
						lsmap.put("ffndyfyz", "发放年度月份格式必须为yyyy-mm！");
					}  
				}
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				message = "false";
				lsmap.put("cysjyz", "发放年度月份格式必须为yyyy-mm！");
			}
			List<HashMap<String, String>> yfList = cjffForm.getYfList();
			boolean flag = false;
			for (HashMap<String, String> hashMap : yfList) {
				if(lsmap.get("ffndyf").trim().equals(hashMap.get("yf"))){
					flag = true;
				}
			}
			if(!flag){
				message = "false";
				lsmap.put("yfhfxyz", "发放年度月份不合法，请参照参数设置！");
			}
			//用人单位是否存在 
			String yrdwdm = dao.getYrdwdm(lsmap.get("yrdwmc"),null);
			if(StringUtils.isNull(yrdwdm)){
				message = "false";
				lsmap.put("yrdwyz", "用人单位不存在！");
			}else{
				//是否有权限导入该用人单位的勤工数据
				yrdwdm = dao.getYrdwdm(lsmap.get("yrdwmc"),cjffForm.getUser());
				if(StringUtils.isNull(yrdwdm)){
					message = "false";
					lsmap.put("yrdwyz", "只能导入本部门酬金发放数据！");
				}
			}
			
			//校区是否存在
			String xqdm = dao.getXqdm(lsmap.get("xqmc"));
			if(StringUtils.isNull(xqdm)){
				message = "false";
				lsmap.put("xqdmyz", "校区不存在！");
			}
			//岗位类别是否存在
			String gwlbdm = dao.getGwlbdm(lsmap.get("gwlbmc"));
			if(StringUtils.isNull(gwlbdm)){
				message = "false";
				lsmap.put("gwlbyz", "岗位类别不存在！");
			}
			//岗位性质是否存在
			String gwxzdm = dao.getGwxzdm(lsmap.get("gwxzmc"));
			if(StringUtils.isNull(gwxzdm)){
				message = "false";
				lsmap.put("gwxzyz","岗位性质不存在！");
			}
			//xh,ffndyf,yrdwdm主键验证
			if(!dao.checkIsNotExists(lsmap.get("xh"), lsmap.get("ffndyf"), yrdwdm, null)){
				message = "false";
				lsmap.put("zjyz", "存在相同学号相同用人单位相同发放年月的记录，请确认！");
			}
			
			//事项说明不能超过25个字符
			float bcje = 0;
			try {
				 bcje = Float.parseFloat(lsmap.get("bcje"));
				if(lsmap.get("bcje").length() > 6){
					message = "false";
					lsmap.put("bcjeyz", "酬金请勿超过6位数字！");
				}else{
					String[] bcjeArr = (bcje +"").split("\\.");
					if(bcjeArr.length == 2 && bcjeArr[1].length() > 1 ){
						message = "false";
						lsmap.put("bcjeyz", "酬金需输入整数或一位小数！");
					}
				}
				
			} catch (NumberFormatException e) {
				// TODO 自动生成 catch 块
				message = "false";
				lsmap.put("bcjeyz", "酬金需输入整数或一位小数！");
			}
			
			//工作内容字数验证
			if(lsmap.get("gznr").trim().length() > 500){
				message = "false";
				lsmap.put("gznryz", "工作内容不能超过500字！");
			}
			HashMap<String, String> csszMap = dao.getCsszMap();
			
			/*参数设置是否允许超过酬金上限验证========begin========*/
			/*获取参数设置里【是否允许超过酬金上限】的值*/
			String sfyxcgcjsx = csszMap.get("sfyxcgcjsx");
			float sxsz = Float.parseFloat(csszMap.get("sxsz"));
			if("否".equals(sfyxcgcjsx)){
				if(bcje > sxsz){
					message = "false";
					lsmap.put("bzyz", "本月发放金额不能大于酬金上限！");
				}
			}else{
				if((bcje > sxsz) && ("".equals(lsmap.get("bz"))|| StringUtils.isNull(lsmap.get("bz")))){
					message = "false";
					lsmap.put("bzyz", "本月发放金额大于酬金上限，必须填写备注！");
				}
			}
			/*参数设置是否允许超过酬金上限验证========end========*/
			
			//备注验证
			if(lsmap.get("bz").trim().length() > 500){
				message = "false";
				lsmap.put("bzyz", "备注不能超过500字！");
			}
			String gss = "";
			if("true".equals(message)){
				double jsjg = Double.parseDouble(lsmap.get("bcje"))/Double.parseDouble(csszMap.get("cjbz"));
				java.text.DecimalFormat df =new java.text.DecimalFormat("#.0");
				gss = df.format(jsjg);
				if(gss.indexOf(".") == 0){
					gss = "0"+gss;				
				}
				lsmap.put("gss", gss);
				lsmap.put("yrdwdm", yrdwdm);
				lsmap.put("xqdm", xqdm);
				lsmap.put("gwlbdm", gwlbdm);
				lsmap.put("gwxzdm", gwxzdm);
				lsmap.put("bcje", lsmap.get("bcje"));
			}
				
		}
		
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	/**
	 * @throws WriteException 
	 * 
	 * @描述:将错误excel表格写入服务器
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-22 下午05:30:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param errorlist
	 * @return
	 * @throws IOException
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
			
			  // 创建工作表
            WritableSheet ws = wwb.createSheet("错误数据", 0);
        	WritableCellFormat titlefont =  this.getFontStyle("title");
    		/**
    		 * sheet1
    		 */
    	    Label row1col1= new Label(0, 0, "学号",titlefont);
    	    Label row1col2= new Label(1, 0, "姓名",titlefont);
    	    Label row1col3= new Label(2, 0, "发放年度月份",titlefont);
//    	    Label row1col4= new Label(3, 0, "发放月份",titlefont);
    	    Label row1col5= new Label(3, 0, "用人单位",titlefont);
    		Label row1col6= new Label(4, 0, "校区",titlefont);
    		Label row1col7= new Label(5, 0, "岗位类别",titlefont);
    		Label row1col8= new Label(6, 0, "岗位性质",titlefont);
    		Label row1col9= new Label(7, 0, "酬金(元)",titlefont);
    		Label row1col10= new Label(8, 0, "工作内容",titlefont);
    		Label row1col11= new Label(9, 0, "备注",titlefont);
    		 
    		
            
            try {
            	ws.addCell(row1col1);
        		ws.addCell(row1col2);
        		ws.addCell(row1col3);
//        		ws.addCell(row1col4);
        		ws.addCell(row1col5);
        		ws.addCell(row1col6);
        		ws.addCell(row1col7);
        		ws.addCell(row1col8);
        		ws.addCell(row1col9);
        		ws.addCell(row1col10);
        	    ws.addCell(row1col11);
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
                	 if(j<=9){
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
	 * 
	 * @描述: 批量保存导入数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-23 下午04:49:29
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
	 * 
	 * @描述:excel表格中是否有重复数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 下午04:25:09
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
			String ffndyf = rs.getCell(2,i).getContents();
			String yrdwdm = rs.getCell(3,i).getContents();
			String str = "";
			if(StringUtils.isNotNull(xh)){
				str = str + xh.trim();
			}
			if(StringUtils.isNotNull(ffndyf)){
				str = str + ffndyf.trim();
			}
			if(StringUtils.isNotNull(yrdwdm)){
				str = str + yrdwdm.trim();
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
	 * 
	 * @描述: 检查空行
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-23 下午01:59:20
	 * @修改记录: 修改者名字-修改日期-修改内容
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
	 * 
	 * @描述: 验证非空项
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-23 下午02:17:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkNotNull(HashMap<String, String> yzMap){
		boolean rs = true;
		for (String key : yzMap.keySet()) {
			if(StringUtils.isNull(yzMap.get(key)) && !("bz").equals(key)){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	/**
	 * 
	 * @描述: 经费划拨表验证
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-6 上午10:13:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nd
	 * @param yrdwdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public HashMap<String, String> checkIsFhJfhb(String nd,String yrdwdm,String bcje,String id){
		return dao.checkIsFhJfhb(nd, yrdwdm, bcje,id);
	}
	
	/**
	 * 
	 * @描述: 合并验证
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-6 上午11:54:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paraList
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
			String ffndyf = rs.getCell(2, i).getContents();
			String yrdwdm = rs.getCell(3,i).getContents();
			String bcje = rs.getCell(7, i).getContents();
			if(StringUtils.isNull(ffndyf) || ffndyf.length() <4 || StringUtils.isNull(yrdwdm) || StringUtils.isNull(bcje)){
				continue;
			}
			float sumje = 0;
			try {
				 sumje = Float.parseFloat(bcje);
			} catch (Exception e) {
				// TODO: handle exception
				continue;
			}
			String ffnd = ffndyf.substring(0, 4);
			
				for (int j = i+1; j < rows; j++) {
					String ffndyfcompare = rs.getCell(2,j).getContents();
					String yrdwdmcompare = rs.getCell(3,j).getContents();
					String bcjecompare =  rs.getCell(7,j).getContents();
					if(StringUtils.isNull(ffndyfcompare) || ffndyfcompare.length() <4  || StringUtils.isNull(yrdwdmcompare) || StringUtils.isNull(bcjecompare)){
						continue;
					}
					String ffndcompare = ffndyfcompare.substring(0, 4);
					if(ffnd.trim().equals(ffndcompare.trim()) && yrdwdm.trim().equals(yrdwdmcompare.trim())){
						containCfList.add(j+"");
						float ljje = 0;
					    try {
							 ljje = Float.parseFloat(bcjecompare);
							 sumje = sumje + ljje;
						} catch (Exception e) {
							// TODO: handle exception
							continue;
						}
					}
				}
				HashMap<String, String> dataMap = this.checkIsFhJfhbDr(ffnd, yrdwdm, sumje+"");
				if(("false").equals(dataMap.get("rs"))){
					result = false;
					dataMap.put("ffnd",ffnd);
					dataMap.put("yrdwmc",yrdwdm);
					dataMap.put("bcje",sumje+"");
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
	 * 
	 * @描述: 经费划拨表验证导入
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-6 上午10:13:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nd
	 * @param yrdwdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public HashMap<String, String> checkIsFhJfhbDr(String nd,String yrdwmc,String bcje){
		return dao.checkIsFhJfhbDr(nd, yrdwmc, bcje);
	}
	
	/**
	 * 
	 * @描述: 上传合并验证错误excel
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-6 下午03:17:15
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
			
			  // 创建工作表
           WritableSheet ws = wwb.createSheet("错误数据", 0);
       	WritableCellFormat titlefont =  this.getFontStyle("title");
   		/**
   		 * sheet1
   		 */
   	    Label row1col1= new Label(0, 0, "年度",titlefont);
   	    Label row1col2= new Label(1, 0, "用人单位",titlefont);
   	    Label row1col3= new Label(2, 0, "用人单位剩金额",titlefont);
//   	    Label row1col4= new Label(3, 0, "发放月份",titlefont);
   	    Label row1col5= new Label(3, 0, "导入总金额",titlefont);
   		 
   		
           
           try {
           	ws.addCell(row1col1);
       		ws.addCell(row1col2);
       		ws.addCell(row1col3);
//       		ws.addCell(row1col4);
       		ws.addCell(row1col5);
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
    				Label labelId_0 = new Label(0, i+1,errorlist.get(i).get("ffnd") ,wcf);
    				Label labelId_1 = new Label(1, i+1,errorlist.get(i).get("yrdwmc") ,wcf);
    				Label labelId_2 = new Label(2, i+1,errorlist.get(i).get("syje") ,wcf);
    				Label labelId_3 = new Label(3, i+1,errorlist.get(i).get("bcje") ,wcf);
               		try {
							ws.addCell(labelId_0);
							ws.addCell(labelId_1);
							ws.addCell(labelId_2);
							ws.addCell(labelId_3);
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
	 * @throws Exception 
	 * 
	 * @描述: 取消提交记录
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-6 下午04:54:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelTjjl(String[] ids) throws Exception{
		return dao.cancelTjjl(ids);
	}
	
	/**
	 * 
	 * @描述: 酬金发放提示信息
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-9 上午09:21:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nd
	 * @param sftj
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> cjffCxTitleXx(String nd,	User user){
		HashMap<String, String> dataMap = new HashMap<String, String>();
		String sumhbje = dao.getSumHbje(nd,user);
		if("0".equals(sumhbje)){
			dataMap.put("hbje", sumhbje);
			dataMap.put("syje", "0");
			dataMap.put("tjje", "0");
			dataMap.put("wtjje", "0");
		}else{
			String sumytjje = dao.getYtjWtjje(nd, "1",user);
			String sumwtjje = dao.getYtjWtjje(nd, "0",user);
			float syje = Float.parseFloat(sumhbje)- Float.parseFloat(sumytjje);
			dataMap.put("hbje", sumhbje);
			dataMap.put("syje", syje+"");
			dataMap.put("tjje", sumytjje);
			dataMap.put("wtjje", sumwtjje);
		}
		return dataMap;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 更新未提交数据
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-13 下午05:02:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateWtjsj() throws Exception{
		return dao.updateWtjsj();
	}
 }
