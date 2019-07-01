/**
 * @部门:学工产品事业部
 * @日期：2016-6-20 上午10:14:29 
 */  
package com.zfsoft.xgxt.zjly.zhf.zhfdr;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-6-20 上午10:14:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfDrService extends SuperServiceImpl<ZhfDrForm, ZhfDrDao> {
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 
	 * @描述:获取辅导员信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-20 下午02:57:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getFdyxx(String zgh){
		return dao.getFdyxx(zgh);
	}
	
	/**
	 * 
	 * @描述:导入excel信息 保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-20 下午05:13:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,ZhfDrForm zhfdrform){
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap= this.DrExcelInfoCheck(is, zhfdrform);
			//判断excel表格是否为空
			if("null".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){
				List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
				if( paralist == null ||paralist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
				 
			  	 this.saveDrDataIntoDb(paralist);
			}else{
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),zhfdrform.getFilepath());
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
	 * @param zhfdrform
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,ZhfDrForm zhfdrform){
		//导入记录数组
		List<String[]> drlist = new ArrayList<String[]>();
		//错误记录数组
		List<String[]> errorlist = new ArrayList<String[]>() ;
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
	        if(clos < 4 || rows < 2){
	        	resultMap.put("result", "null");
	        }else{
	        	//行
	        	  for (int i = 1; i < rows; i++) {
	  	        	
	  	        		//取出每行验证数据，塞入lsmap
	  	        		HashMap<String, String>  lsmap = new HashMap<String, String>();
	  	        		String xh = rs.getCell(0, i).getContents();
	  	        		String xm = rs.getCell(1, i).getContents();
	  	        		String sxsm = rs.getCell(2, i).getContents();
	  	        		String cysj = rs.getCell(3, i).getContents();
	  	        	    if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
	  	        	    		StringUtils.isNull(sxsm.trim()) && StringUtils.isNull(cysj.trim())){
	  	        	    	continue;
	  	        	    }
	  	        		lsmap.put("xh", xh);
	  	        		lsmap.put("xm", xm);
	  	        		lsmap.put("sxsm", sxsm);
	  	        		lsmap.put("cysj", cysj);
	  	        		lsmap.put("xmmkdm", zhfdrform.getXmmkdm());
	  	        		lsmap.put("jfxmdm", zhfdrform.getJfxmdm());
	  	        		HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, zhfdrform);
	  	        		ArrayList<String> paralist = new ArrayList<String>();
	  	        		if("false".equals(resultmap.get("result"))){
	  	        			paralist.add(lsmap.get("xh"));
	  	        			paralist.add(lsmap.get("xm"));
	  	        			paralist.add(lsmap.get("sxsm"));
	  	        			paralist.add(lsmap.get("cysj"));
	  	        			if(resultmap.get("resultmap") != null ){
	  	        				Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
	  	        				for (Map.Entry<String, String> entry : map.entrySet()){
	  	        					if(!entry.getKey().equals("xh") && !entry.getKey().equals("xm")
		  	        					&&	!entry.getKey().equals("sxsm") && !entry.getKey().equals("cysj") && !entry.getKey().equals("cysj")	
		  	        					&& !entry.getKey().equals("jfxmdm") && !entry.getKey().equals("xmmkdm")){
	  	        						paralist.add(entry.getValue());
	  	        					}
	  	        					
	  	        				}
	  	        			}
	  	        			errorlist.add(paralist.toArray(new String[]{}));
	  	        			resultMap.put("result", "false");
	  	        		}else{
	  	        			paralist.add(lsmap.get("xh"));
	  	        			paralist.add(lsmap.get("sxsm"));
	  	        			paralist.add(lsmap.get("cysj"));
	  	        			paralist.add(lrsj);
	  	        			paralist.add(zhfdrform.getUser().getUserName());
	  	        			paralist.add(lsmap.get("xmmkdm"));
	  	        			paralist.add(lsmap.get("jfxmdm"));
	  	        			drlist.add(paralist.toArray(new String[]{}));
	  	        		}
	  			}
	        	  resultMap.put("drlist", drlist);
	        	  resultMap.put("errorlist", errorlist);
	        }
	      
//	      
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
	 * @描述:循环验证每行的记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-22 下午01:43:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap,ZhfDrForm zhfdrform){
		/**
		 * 验证规则
		 * 1.xh,xm联合验证是否存在xsxxb
		 * 2.xh,sxsm,cysj,jfxmdm主键验证
		 * 3.导入记录老师的数据范围验证
		 * 4.事项说明不能超过25个字符
		 * 5.cysj 验证10位格式 ，例如2015-08-08(2017/1/9日，改为支持yyyy-mm-dd,yyyy/MM/dd,yyyy年MM月dd日三种格式导入，cp)
		 * 验证方式采取整行数据全部验证完，错误数据塞入lsmap，最后塞入返回map，进行返回
		 * 如果有一条验证规则不满足，塞入返回结果map为字符串"false",如果全部正确，为字符串"true"
		 */
		String message = "true";
		ZhfDrDao zhfdrdao = new ZhfDrDao();
		//为空验证
		if(StringUtils.isNull(lsmap.get("xh")) || StringUtils.isNull(lsmap.get("xm")) || StringUtils.isNull(lsmap.get("sxsm"))
				|| StringUtils.isNull(lsmap.get("jfxmdm")) || StringUtils.isNull(lsmap.get("xmmkdm")) || StringUtils.isNull(lsmap.get("cysj"))){
				message = "false";
				lsmap.put("kyz", "学号，姓名,事项说明，计分项目，项目模块，参与时间不可为空！");
		}else{
			//xh,xm联合验证是否存在xsxxb
			if(!zhfdrdao.checkXhXmIsExist(lsmap.get("xh"),lsmap.get("xm"))){
				message = "false";
				lsmap.put("xhxmlhyz", "学号/姓名不对应或不存在学生信息表中！");
			}
			//xh,sxsm,cysj,jfxmdm主键验证
			if(!zhfdrdao.checkXhXmSxsmJfxmdmCf(lsmap.get("xh"), lsmap.get("cysj"), lsmap.get("jfxmdm"),lsmap.get("sxsm") )){
				message = "false";
				lsmap.put("zjyz", "学号，事项说明，参与时间，计分项目代码不能重复！");
			}
			//导入记录老师的数据范围验证
			if(!zhfdrdao.checkJsDrSjfw(lsmap.get("xh"), zhfdrform)){
				message = "false";
				lsmap.put("sjfwyz", "没有权限导入该学生！");
			}
			//事项说明不能超过25个字符
			if(lsmap.get("sxsm").length() > 25){
				message = "false";
				lsmap.put("sxsmyz", "事项说明请勿超过25个字！");
			}
			//cysj 验证10位格式 ，例如2015-08-08
//			if(StringUtils.isNull(lsmap.get("cysj")) || lsmap.get("cysj").length() != 10){
//				message = "false";
//				lsmap.put("cysjyz", "1参与时间格式必须为yyyy-mm-dd！");
//			} else{
				try {
						DateFormat formatter1 = new SimpleDateFormat("yyyy年MM月dd日");  
						 try {
							Date date1 = (Date)formatter1.parse(lsmap.get("cysj"));
							if(!lsmap.get("cysj").equals(formatter1.format(date1))){
								message = "false";
								lsmap.put("cysjyz", "参与时间格式必须为yyyy年MM月dd日！");
							}
						} catch (ParseException e) {
							DateFormat formatter2 = new SimpleDateFormat("yyyy/MM/dd");  
							 try {
									Date date2 = (Date)formatter2.parse(lsmap.get("cysj"));
									if(!lsmap.get("cysj").equals(formatter2.format(date2))){
										message = "false";
										lsmap.put("cysjyz", "参与时间格式必须为yyyy/MM/dd！");
									}
								} catch (ParseException e1) {
									DateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");  
									 try {
											Date date3 = (Date)formatter3.parse(lsmap.get("cysj"));
											if(!lsmap.get("cysj").equals(formatter3.format(date3))){
												message = "false";
												lsmap.put("cysjyz", "参与时间格式必须为yyyy-mm-dd！");
											}
										} catch (ParseException e2) {
											message = "false";
											lsmap.put("cysjyz", "参与时间格式必须为yyyy-mm-dd,yyyy/MM/dd,yyyy年MM月dd日之中的一种！");
										}  
								}  
						}  
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					message = "false";
					lsmap.put("cysjyz", "参与时间格式必须为yyyy-mm-dd,yyyy/MM/dd,yyyy年MM月dd日之中的一种！");
				}
//			}
				
		}
		
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	/**
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
	public String uploadErrorExcel(List<String[]> errorlist,String filepath) throws IOException{
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
            Label labelId= new Label(0, 0, "学号");//表示第
            Label labelName= new Label(1, 0, "姓名");
            Label labelSex= new Label(2, 0, "事项说明");
            Label labelNum= new Label(3, 0, "参与/获得时间");
            
            
            try {
            	ws.addCell(labelId);
				ws.addCell(labelName);
				ws.addCell(labelSex);
	            ws.addCell(labelNum);
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
                	 if(j<=3){
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
	
	public boolean saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		if(paralist != null && paralist.size() > 0){
			return dao.saveDrDataIntoDb(paralist).length >0 ? true :false;
		}else{
			return false;
		}
		
	}
	
	public boolean checkNotExists(ZhfDrForm t ){
		return dao.checkNotExists(t);
	}
	
	/**
	 * 
	 * @描述:查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-23 下午04:50:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> viewJg(String id){
		return dao.viewJg(id);
	}
	
	/**
	 * 
	 * @描述:将检验完的数据插入临时表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-24 下午04:16:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] 返回类型 
	 * @throws
	 */
	public int[] saveDrDataIntoDblsb(List<String[]> paralist) throws Exception{
 		return dao.saveDrDataIntoDblsb(paralist);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:删除临时表数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-24 下午04:17:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean dellsbsj() throws Exception{
		return dao.dellsbsj();
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述: 获得最后的数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-24 下午04:21:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String[] 返回类型 
	 * @throws
	 */
	public ArrayList<HashMap<String, String>> getLastDatasj(String username,String lrsj,String xmmkdm) throws Exception{
	    return dao.getLastDatasj(username, lrsj, xmmkdm);
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述: 获得最后的数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-24 下午04:21:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String[] 返回类型 
	 * @throws
	 */
	public ArrayList<HashMap<String, String>> getLastDatasjerror(String username,String lrsj,String xmmkdm) throws Exception{
	    return dao.getLastDatasjerror(username, lrsj, xmmkdm);
	}

	public void addlog(String time, User user, String[] ids) throws Exception {
		 dao.addlog(time,user,ids);
	}
	public void dellog(String[] ids) throws Exception {
		dao.dellog(ids);
	}

	/**
	 * @throws Exception  
	 * @描述:构建表头、内容数据
	 * @作者：CP[工号：1352]
	 * @日期：2017-2-27 下午05:54:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * File 返回类型 
	 * @throws 
	 */
	public File getZhfFile(ZhfDrForm form, User user) throws Exception {	
			//构建导出表头
			Map<String,String> map = new LinkedHashMap<String, String>();
			map.put("xh", "学号");
			map.put("xm", "姓名");
			map.put("nj", "年级");
			map.put("xymc", "系部");
			map.put("zymc", "专业");
			map.put("bjmc", "班级");
			map.put("xmmkmc", "模块");
			map.put("jfxmmc", "计分项目");
			map.put("sxsm", "事项");
			map.put("fs", "分数");
			map.put("cysj", "参与时间");
			map.put("lb", "类别");
			map.put("shrxm", "审核人");
			map.put("shsj", "审核时间");
			map.put("shztmc", "审核状态");
			//导出数据
			form.getPages().setPageSize(Integer.MAX_VALUE);
			List<HashMap<String,String>> dataList = dao.getAllList(form, user);
			IExportService export = new ExportExcelImpl();
			return export.getExportFile(map, dataList);
		}

	/**
	 * @throws Exception  
	 * @描述:详细事项list
	 * @作者：CP[工号：1352]
	 * @日期：2017-3-13 上午09:48:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXXsxList(ZhfDrForm t, User user) throws Exception {
		return dao.getXXsxList(t, user);
	}

	/**
	 * @throws Exception 
	 * @param user 
	 * @param t 
	 * @throws WriteException 
	 * @throws IOException  
	 * @描述:生成详细事项excel
	 * @作者：CP[工号：1352]
	 * @日期：2017-3-13 上午10:00:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param resultList
	 * @return
	 * File 返回类型 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	public File getZhfXxsx(List<HashMap<String, String>> list, ZhfDrForm t, User user) throws Exception {
		File file = new File(System.getProperty("java.io.tmpdir"),System.currentTimeMillis()+".xls");
		if(!file.exists()){
			file.createNewFile();
		}
		if(null != list && list.size()>0){	
			WritableWorkbook wwb = Workbook.createWorkbook(file);
			WritableSheet sheet = wwb.createSheet("sheet1", 0);
			WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);
			WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat head_cf = new WritableCellFormat(headFont);//设置正文表头字体
			WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
			head_cf.setAlignment(jxl.format.Alignment.CENTRE);
			head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			body_cf.setAlignment(jxl.format.Alignment.LEFT);
			body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			body_cf.setWrap(true);
			head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			//表头
			sheet.setColumnView(0, 14);
			sheet.setColumnView(1, 10);
			sheet.setColumnView(2, 8);
			sheet.setColumnView(3, 14);
			sheet.setColumnView(4, 16);
			sheet.setColumnView(5, 16);
			sheet.setColumnView(6, 14);
			sheet.setColumnView(7, 30);
			sheet.setColumnView(8, 30);
			sheet.setColumnView(9, 8);
			sheet.setColumnView(10, 12);
			sheet.setColumnView(11, 12);
			sheet.setColumnView(12, 12);
			sheet.setColumnView(13, 12);
			sheet.setColumnView(14, 12);
			Label label_title1 = new Label(0, 0,"学号",head_cf);
			Label label_title2 = new Label(1, 0,"姓名",head_cf);
			Label label_title3 = new Label(2, 0,"年级",head_cf);
			Label label_title4 = new Label(3, 0,"学院",head_cf);
			Label label_title5 = new Label(4, 0,"专业",head_cf);
			Label label_title6 = new Label(5, 0,"班级",head_cf);
			Label label_title7 = new Label(6, 0,"模块",head_cf);
			Label label_title8 = new Label(7, 0,"计分项目",head_cf);
			Label label_title9 = new Label(8, 0,"事项",head_cf);
			Label label_title10 = new Label(9, 0,"分数",head_cf);
			Label label_title11 = new Label(10, 0,"参与时间",head_cf);
			Label label_title12 = new Label(11, 0,"类别",head_cf);
			Label label_title13 = new Label(12, 0,"审核人",head_cf);
			Label label_title14 = new Label(13, 0,"审核时间",head_cf);
			Label label_title15 = new Label(14, 0,"审核状态",head_cf);
			sheet.addCell(label_title1);
			sheet.addCell(label_title2);
			sheet.addCell(label_title3);
			sheet.addCell(label_title4);
			sheet.addCell(label_title5);
			sheet.addCell(label_title6);
			sheet.addCell(label_title7);
			sheet.addCell(label_title8);
			sheet.addCell(label_title9);
			sheet.addCell(label_title10);
			sheet.addCell(label_title11);
			sheet.addCell(label_title12);
			sheet.addCell(label_title13);
			sheet.addCell(label_title14);
			sheet.addCell(label_title15);
		       //取到 xh :mkxmmc 对应条数
		       List<HashMap<String,String>> mktslist = dao.getMktsList(t, user);
		       HashMap<String, String> mkmap = new HashMap<String, String> ();
		       for (int i = 0; i < mktslist.size(); i++) {
		    	   mkmap.put(mktslist.get(i).get("xh"), mktslist.get(i).get("mkts"));
		       }
		       //取到 xh :jfxmmc 对应条数
		       List<HashMap<String,String>> xmtslist = dao.getXmtsList(t, user);
		       HashMap<String, String> xmmap = new HashMap<String, String> ();
		       for (int i = 0; i < xmtslist.size(); i++) {
		    	   xmmap.put(xmtslist.get(i).get("xh"), xmtslist.get(i).get("xmts"));
		       }
		       
		        String bjxh ="";//标记学号，避免同一学号多次去合并前几个字段
		        String bjmk ="";//标记模块，避免同一学号多次去合并前几个字段
		        String bjxm = "";//标记项目，避免同一学号多次去合并前几个字段
		        HashMap<String, String> mkmaps = new HashMap<String, String>();
		        HashMap<String, String> xmmaps = new HashMap<String, String>();
			for(int i = 0;i<list.size();i++){
				  Label xh = new Label(0, 1+i, list.get(i).get("xh"), body_cf);
				  Label xm = new Label(1, 1+i, list.get(i).get("xm"), body_cf);
				  Label nj = new Label(2, 1+i, list.get(i).get("nj"), body_cf);
				  Label xymc = new Label(3, 1+i, list.get(i).get("xymc"), body_cf);
				  Label zymc = new Label(4, 1+i, list.get(i).get("zymc"), body_cf);
				  Label bjmc = new Label(5, 1+i, list.get(i).get("bjmc"), body_cf);
				  Label xmmkmc = new Label(6, 1+i, list.get(i).get("xmmkmc"), body_cf);
				  Label jfxmmc = new Label(7, 1+i, list.get(i).get("jfxmmc"), body_cf);
				  Label sxsm = new Label(8, 1+i, list.get(i).get("sxsm"), body_cf);
				  Label fs = new Label(9, 1+i, list.get(i).get("fs"), body_cf);
				  Label cysj = new Label(10, 1+i, list.get(i).get("cysj"), body_cf);
				  Label lb = new Label(11, 1+i, list.get(i).get("lb"), body_cf);
				  Label shr = new Label(12, 1+i, list.get(i).get("shrxm"), body_cf);
				  Label shsj = new Label(13, 1+i, list.get(i).get("shsj"), body_cf);
				  Label shztmc = new Label(14, 1+i, list.get(i).get("shztmc"), body_cf);
				  
				String dqxh = list.get(i).get("xh");//取到当前1条的学号
				String dqmk = list.get(i).get("xmmkmc");//取到当前1条的模块
				String dqxm = list.get(i).get("jfxmmc");//取到当前1条的模块
				if (!bjxh.equals(dqxh)) {
					 mkmaps = new HashMap<String, String>();
				      xmmaps = new HashMap<String, String>();
				       bjxh ="";//标记学号，避免同一学号多次去合并前几个字段
				       bjmk ="";//标记模块
				       bjxm = "";//标记项目
				}
 				if (dqxh.equals(bjxh)&&dqmk.equals(bjmk)&&dqxm.equals(bjxm)) {//如果当前学号当前模块当前项目都不是第一次进入循环，直接插入
				 	sheet.addCell(xh);
					sheet.addCell(xm);
					sheet.addCell(nj);
					sheet.addCell(xymc);
					sheet.addCell(zymc);
					sheet.addCell(bjmc);
					sheet.addCell(xmmkmc);
					sheet.addCell(jfxmmc);
					sheet.addCell(sxsm);
					sheet.addCell(fs);
					sheet.addCell(cysj);
					sheet.addCell(lb);
					sheet.addCell(shr);
					sheet.addCell(shsj);
					sheet.addCell(shztmc);
					  bjxh =dqxh;//标记学号
					  bjmk = dqmk;//标记模块
					  bjxm =dqxm;//标记模块
				}else {
					 int a =0;//a为模块条数也为xh条数 ，记录前6列的 行数累加
					 int b =0; //记录第7行 模块列的累加
					 int c =0;//记录第8行 项目列的累加
					if (!dqxh.equals(bjxh)) { //此时，当前学号第一次出现，当前模块、项目肯定也是第一次出现，此时，要合并前6列基本信息和模块列，项目列
						//遍历模块map，1，取学号要合并的行数。2，取到 {xmmkmc: 条数}的mkmaps
						Iterator it = mkmap.keySet().iterator();  
						while (it.hasNext()) { 
							String key = it.next().toString();
							if (dqxh.equals(key)) {//当前xh在map里找到对应模块
								String []mkxx = mkmap.get(key).split(";");
								if (mkxx.length==1) {//只有一个模块，取到条数，   思想素质&3
									a = Integer.parseInt(mkxx[0].substring(mkxx[0].indexOf("&")+1,mkxx[0].length()));
									mkmaps.put(mkxx[0].substring(0,4),mkxx[0].substring(mkxx[0].lastIndexOf("&")+1,mkxx[0].length()));
								}else {//多个模块  个人荣誉&1;人文素质&1;思想素质&3 substring(5,)
									for (int j = 0; j < mkxx.length; j++) {
										a += Integer.parseInt(mkxx[j].substring(mkxx[j].lastIndexOf("&")+1,mkxx[j].length()));
										mkmaps.put(mkxx[j].substring(0,4), mkxx[j].substring(mkxx[j].lastIndexOf("&")+1,mkxx[j].length()));
									}
								}
								break;
							}
						}
						//遍历项目map 取到 {jfxmmc: 条数}的xmmaps
						Iterator it1 = xmmap.keySet().iterator();  
						while (it1.hasNext()) { 
							String key = it1.next().toString();
							if (dqxh.equals(key)) {//当前xh在map里找到对应模块
								String []xmxx = xmmap.get(key).split(";");
									for (int j = 0; j < xmxx.length; j++) {
										xmmaps.put(xmxx[j].substring(0,xmxx[j].lastIndexOf("&")), xmxx[j].substring(xmxx[j].length()-1));
									}
								break;
							}
						}
						//取到a后，前6列合并
					  	sheet.addCell(xh);
						sheet.addCell(xm);
						sheet.addCell(nj);
						sheet.addCell(xymc);
						sheet.addCell(zymc);
						sheet.addCell(bjmc);
						sheet.mergeCells(0,1+i,0,a+i);
						sheet.mergeCells(1,1+i,1,a+i);
						sheet.mergeCells(2,1+i,2,a+i);
						sheet.mergeCells(3,1+i,3,a+i);
						sheet.mergeCells(4,1+i,4,a+i);
						sheet.mergeCells(5,1+i,5,a+i);
						//第7列模块列合并列数获取
						 Iterator it2 = mkmaps.keySet().iterator();  
						  while (it2.hasNext()) {
							  String key = it2.next().toString();
							  if (list.get(i).get("xmmkmc").equals(key)) {//根据当前这条数据的项目模块
								  b=Integer.parseInt(mkmaps.get(key));
								  break;
							  }
						  }
						sheet.addCell(xmmkmc);
						sheet.mergeCells(6,1+i,6,b+i);
						/**
						 * mergeCells合并单元格，
						 * 第一个参数：要合并的单元格最左上角的列号
						 * 第二个参数：要合并的单元格最左上角的行号
						 * 第三个参数：要合并的单元格最右角的列号
						 * 第四个参数：要合并的单元格最右下角的行号
						 */
						//第8列项目列合并列数获取
						 Iterator it3 = xmmaps.keySet().iterator();  
						  while (it3.hasNext()) {
							  String key = it3.next().toString();
							  if (list.get(i).get("jfxmmc").equals(key)) {//根据当前这条数据的项目模块
								  c=Integer.parseInt(xmmaps.get(key));
								  break;
							  }
						  }
						
						sheet.addCell(jfxmmc);
						sheet.mergeCells(7,1+i,7,c+i); 
						
						sheet.addCell(sxsm);
						sheet.addCell(fs);
						sheet.addCell(cysj);
						sheet.addCell(lb);
						sheet.addCell(shr);
						sheet.addCell(shsj);
						sheet.addCell(shztmc);
					}else {//此时首先学号不是第一次出现
						if (!dqmk.equals(bjmk)){//如果当前模块是第一次，模块属下的项目肯定也是第一次，【要取到模块合并列数和项目合并列数】
							sheet.addCell(xh);
							sheet.addCell(xm);
							sheet.addCell(nj);
							sheet.addCell(xymc);
							sheet.addCell(zymc);
							sheet.addCell(bjmc);
							//第7列模块列合并列数获取
							 Iterator it1 = mkmaps.keySet().iterator();  
							  while (it1.hasNext()) {
								  String key = it1.next().toString();
								  if (list.get(i).get("xmmkmc").equals(key)) {
									  b=Integer.parseInt(mkmaps.get(key));
									  break;
								  }
								  
							  }
								sheet.addCell(xmmkmc);
								sheet.mergeCells(6,1+i,6,b+i); 
								//第8列项目列合并列数获取
							 Iterator it2 = xmmaps.keySet().iterator();  
							  while (it2.hasNext()) {
								  String key = it2.next().toString();
								  if (list.get(i).get("jfxmmc").equals(key)) {
									  c=Integer.parseInt(xmmaps.get(key));
								  }
								  
							  }
								sheet.addCell(jfxmmc);
								sheet.mergeCells(7,1+i,7,c+i); 
								sheet.addCell(sxsm);
								sheet.addCell(fs);
								sheet.addCell(cysj);
								sheet.addCell(lb);
								sheet.addCell(shr);
								sheet.addCell(shsj);
								sheet.addCell(shztmc);
						}else { //如果当前模块不是第一次,但
							if (!dqxm.equals(bjxm)) { //如果项目是第一次出现，此时要获取项目合并列数
								sheet.addCell(xh);
								sheet.addCell(xm);
								sheet.addCell(nj);
								sheet.addCell(xymc);
								sheet.addCell(zymc);
								sheet.addCell(bjmc);
								sheet.addCell(xmmkmc);
								//第8列项目列合并列数获取
								 Iterator it2 = xmmaps.keySet().iterator();  
								  while (it2.hasNext()) {
									  String key = it2.next().toString();
									  if (list.get(i).get("jfxmmc").equals(key)) {
										  c=Integer.parseInt(xmmaps.get(key));
									  }
								  }
								    sheet.addCell(jfxmmc);
									sheet.mergeCells(7,1+i,7,c+i); 
									sheet.addCell(sxsm);
									sheet.addCell(fs);
									sheet.addCell(cysj);
									sheet.addCell(lb);
									sheet.addCell(shr);
									sheet.addCell(shsj);
									sheet.addCell(shztmc);
							}
						}
						
					}
					  
					  bjxh =dqxh;//标记学号
					  bjmk = dqmk;//标记模块
					  bjxm =dqxm;//标记模块
				}
			}
			wwb.write();
			wwb.close();
		}
		return file;
	}

	/**
	 * @throws Exception  
	 * @描述:生成汇总事项信息的excel
	 * @作者：CP[工号：1352]
	 * @日期：2017-3-16 下午02:55:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param resultList
	 * @param model
	 * @param user
	 * @return
	 * File 返回类型 
	 * @throws 
	 */
	public File getZhfHzsx(List<HashMap<String, String>> list,
			ZhfDrForm t, User user) throws Exception {
		File file = new File(System.getProperty("java.io.tmpdir"),System.currentTimeMillis()+".xls");
		if(!file.exists()){
			file.createNewFile();
		}
		if(null != list && list.size()>0){	
			WritableWorkbook wwb = Workbook.createWorkbook(file);
			WritableSheet sheet = wwb.createSheet("sheet1", 0);
			WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);
			WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat head_cf = new WritableCellFormat(headFont);//设置正文表头字体
			WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
			head_cf.setAlignment(jxl.format.Alignment.CENTRE);
			head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			body_cf.setAlignment(jxl.format.Alignment.LEFT);
			body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			body_cf.setWrap(true);
			head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			//表头
			sheet.setColumnView(0, 14);
			sheet.setColumnView(1, 10);
			sheet.setColumnView(2, 8);
			sheet.setColumnView(3, 14);
			sheet.setColumnView(4, 16);
			sheet.setColumnView(5, 16);
			sheet.setColumnView(6, 14);
			sheet.setColumnView(7, 30);
			sheet.setColumnView(8, 30);
			sheet.setColumnView(9, 8);
			sheet.setColumnView(10, 12);
			sheet.setColumnView(11, 12);
			sheet.setColumnView(12, 12);
			sheet.setColumnView(13, 12);
			sheet.setColumnView(14, 12);
			sheet.setColumnView(15, 10);
			sheet.setColumnView(16, 8);
			sheet.setColumnView(14, 15);
			Label label_title1 = new Label(0, 0,"学号",head_cf);
			Label label_title2 = new Label(1, 0,"姓名",head_cf);
			Label label_title3 = new Label(2, 0,"年级",head_cf);
			Label label_title4 = new Label(3, 0,"学院",head_cf);
			Label label_title5 = new Label(4, 0,"专业",head_cf);
			Label label_title6 = new Label(5, 0,"班级",head_cf);
			Label label_title7 = new Label(6, 0,"模块",head_cf);
			Label label_title8 = new Label(7, 0,"计分项目",head_cf);
			Label label_title9 = new Label(8, 0,"事项",head_cf);
			Label label_title10 = new Label(9, 0,"事项分",head_cf);
			Label label_title11 = new Label(10, 0,"参与时间",head_cf);
			Label label_title12 = new Label(11, 0,"类别",head_cf);
			Label label_title13 = new Label(12, 0,"事项审核人",head_cf);
			Label label_title14 = new Label(13, 0,"审核时间",head_cf);
			Label label_title15 = new Label(14, 0,"事项审核状态",head_cf);
			Label label_title16 = new Label(15, 0,"模块得分",head_cf);
			Label label_title17 = new Label(16, 0,"总分",head_cf);
			Label label_title18 = new Label(17, 0,"总审核状态",head_cf);
			
			
			sheet.addCell(label_title1);
			sheet.addCell(label_title2);
			sheet.addCell(label_title3);
			sheet.addCell(label_title4);
			sheet.addCell(label_title5);
			sheet.addCell(label_title6);
			sheet.addCell(label_title7);
			sheet.addCell(label_title8);
			sheet.addCell(label_title9);
			sheet.addCell(label_title10);
			sheet.addCell(label_title11);
			sheet.addCell(label_title12);
			sheet.addCell(label_title13);
			sheet.addCell(label_title14);
			sheet.addCell(label_title15);
			sheet.addCell(label_title16);
			sheet.addCell(label_title17);
			sheet.addCell(label_title18);
		
		     //取到 xh :mkxmmc 对应条数
		       List<HashMap<String,String>> mktslist = dao.getMktsList(t, user);
		       HashMap<String, String> mkmap = new HashMap<String, String> ();
		       for (int i = 0; i < mktslist.size(); i++) {
		    	   mkmap.put(mktslist.get(i).get("xh"), mktslist.get(i).get("mkts"));
		       }
		       //取到 xh :jfxmmc 对应条数
		       List<HashMap<String,String>> xmtslist = dao.getXmtsList(t, user);
		       HashMap<String, String> xmmap = new HashMap<String, String> ();
		       for (int i = 0; i < xmtslist.size(); i++) {
		    	   xmmap.put(xmtslist.get(i).get("xh"), xmtslist.get(i).get("xmts"));
		       }
		       
		       //取到 xh :各个模块分数
		       List<HashMap<String,String>> mkfslist= dao.getMkfsList(t, user);
		       HashMap<String, String> mkfsmap = new HashMap<String, String> ();
		       for (int i = 0; i < mkfslist.size(); i++) {
		    	   mkfsmap.put(mkfslist.get(i).get("xh"), mkfslist.get(i).get("mkfs"));
		       }
			
		     //取到 xh :总审核状态
		       List<HashMap<String,String>> zshztlist= dao.getZshztList(t, user);
		       HashMap<String, String> zshztmap = new HashMap<String, String> ();
		       for (int i = 0; i < zshztlist.size(); i++) {
		    	   zshztmap.put(zshztlist.get(i).get("xh"), zshztlist.get(i).get("shztmc"));
		       }
			
		       
		       String bjxh ="";//标记学号，避免同一学号多次去合并前几个字段
		        String bjmk ="";//标记模块，避免同一学号多次去合并前几个字段
		        String bjxm = "";//标记项目，避免同一学号多次去合并前几个字段
		        HashMap<String, String> mkmaps = new HashMap<String, String>();
		        HashMap<String, String> xmmaps = new HashMap<String, String>();
		        HashMap<String, String> fsmaps = new HashMap<String, String>();
			for(int i = 0;i<list.size();i++){
				  //取当前条数据生成label
				  Label xh = new Label(0, 1+i, list.get(i).get("xh"), body_cf);
				  Label xm = new Label(1, 1+i, list.get(i).get("xm"), body_cf);
				  Label nj = new Label(2, 1+i, list.get(i).get("nj"), body_cf);
				  Label xymc = new Label(3, 1+i, list.get(i).get("xymc"), body_cf);
				  Label zymc = new Label(4, 1+i, list.get(i).get("zymc"), body_cf);
				  Label bjmc = new Label(5, 1+i, list.get(i).get("bjmc"), body_cf);
				  Label xmmkmc = new Label(6, 1+i, list.get(i).get("xmmkmc"), body_cf);
				  Label jfxmmc = new Label(7, 1+i, list.get(i).get("jfxmmc"), body_cf);
				  Label sxsm = new Label(8, 1+i, list.get(i).get("sxsm"), body_cf);
				  Label fs = new Label(9, 1+i, list.get(i).get("fs"), body_cf);
				  Label cysj = new Label(10, 1+i, list.get(i).get("cysj"), body_cf);
				  Label lb = new Label(11, 1+i, list.get(i).get("lb"), body_cf);
				  Label shr = new Label(12, 1+i, list.get(i).get("shrxm"), body_cf);
				  Label shsj = new Label(13, 1+i, list.get(i).get("shsj"), body_cf);
				  Label shztmc = new Label(14, 1+i, list.get(i).get("shztmc"), body_cf);
				  
				String dqxh = list.get(i).get("xh");//取到当前1条的学号
				String dqmk = list.get(i).get("xmmkmc");//取到当前1条的模块
				String dqxm = list.get(i).get("jfxmmc");//取到当前1条的模块
				if (!bjxh.equals(dqxh)) {
					 mkmaps = new HashMap<String, String>();
				      xmmaps = new HashMap<String, String>();
				      fsmaps = new HashMap<String, String>();
				       bjxh ="";//标记学号，避免同一学号多次去合并前几个字段
				       bjmk ="";//标记模块
				       bjxm = "";
				}
				if (dqxh.equals(bjxh)&&dqmk.equals(bjmk)&&dqxm.equals(bjxm)) {//如果当前学号当前模块当前项目都不是第一次进入循环，直接插入
				 	sheet.addCell(xh);
					sheet.addCell(xm);
					sheet.addCell(nj);
					sheet.addCell(xymc);
					sheet.addCell(zymc);
					sheet.addCell(bjmc);
					sheet.addCell(xmmkmc);
					sheet.addCell(jfxmmc);
					sheet.addCell(sxsm);
					sheet.addCell(fs);
					sheet.addCell(cysj);
					sheet.addCell(lb);
					sheet.addCell(shr);
					sheet.addCell(shsj);
					sheet.addCell(shztmc);
					  bjxh =dqxh;//标记学号
					  bjmk = dqmk;//标记模块
					  bjxm =dqxm;//标记模块
				}else {
					 int a =0;//a为模块条数也为xh条数 ，记录前6列的 行数累加
					 int b =0; //记录第7行 模块列的累加
					 int c =0;//记录第8行 项目列的累加
					 double d =0;//总分数
					 float e =0;//模块分数
					if (!dqxh.equals(bjxh)) { //此时，当前学号第一次出现，当前模块、项目肯定也是第一次出现，此时，要合并前6列基本信息和模块列，项目列
						//遍历模块map，1，取学号要合并的行数。2，取到 {xmmkmc: 条数}的mkmaps
						Iterator it = mkmap.keySet().iterator();  
						while (it.hasNext()) { 
							String key = it.next().toString();
							if (dqxh.equals(key)) {//当前xh在map里找到对应模块
								String []mkxx = mkmap.get(key).split(";");
								if (mkxx.length==1) {//只有一个模块，取到条数，   思想素质&3
									a = Integer.parseInt(mkxx[0].substring(mkxx[0].indexOf("&")+1,mkxx[0].length()));
									mkmaps.put(mkxx[0].substring(0,4),mkxx[0].substring(mkxx[0].lastIndexOf("&")+1,mkxx[0].length()));
								}else {//多个模块  个人荣誉&1;人文素质&1;思想素质&3 
									for (int j = 0; j < mkxx.length; j++) {
										a += Integer.parseInt(mkxx[j].substring(mkxx[j].lastIndexOf("&")+1,mkxx[j].length()));
										mkmaps.put(mkxx[j].substring(0,4), mkxx[j].substring(mkxx[j].lastIndexOf("&")+1,mkxx[j].length()));
									}
								}
								break;
							}
						}
						//遍历项目map 取到 {jfxmmc: 条数}的xmmaps
						Iterator it1 = xmmap.keySet().iterator();  
						while (it1.hasNext()) { 
							String key = it1.next().toString();
							if (dqxh.equals(key)) {//当前xh在map里找到对应模块
								String []xmxx = xmmap.get(key).split(";");
									for (int j = 0; j < xmxx.length; j++) {
										xmmaps.put(xmxx[j].substring(0,xmxx[j].lastIndexOf("&")), xmxx[j].substring(xmxx[j].length()-1));
									}
								break;
							}
						}
						Iterator mkf = mkfsmap.keySet().iterator();  
						while (mkf.hasNext()) { 
							String key = mkf.next().toString();
							if (dqxh.equals(key)) {//当前xh在map里找到对应模块
								String []mkxx = mkfsmap.get(key).split(";");
								if (mkxx.length==1) {//只有一个模块，思想素质$0.4 
									d=Double.parseDouble(mkxx[0].substring(mkxx[0].lastIndexOf("$")+1,mkxx[0].length()));//总分
									fsmaps.put(mkxx[0].substring(0,4),mkxx[0].substring(mkxx[0].lastIndexOf("$")+1,mkxx[0].length()));
								}else {//多个模块  个人荣誉&0.1;人文素质&0.11;思想素质&3 substring(5,)
									for (int j = 0; j < mkxx.length; j++) {
										double k = Double.parseDouble(mkxx[j].substring(mkxx[j].lastIndexOf("$")+1,mkxx[j].length()));
										BigDecimal b1=new BigDecimal(Double.toString(d));  
										 BigDecimal b2=new BigDecimal(Double.toString(k)); 
										 d=b1.add(b2).doubleValue();  
										fsmaps.put(mkxx[j].substring(0,4), mkxx[j].substring(mkxx[j].lastIndexOf("$")+1,mkxx[j].length()));
									}
									
								}
								break;
							}
						}
						//取到a后，前6列合并
					  	sheet.addCell(xh);
						sheet.addCell(xm);
						sheet.addCell(nj);
						sheet.addCell(xymc);
						sheet.addCell(zymc);
						sheet.addCell(bjmc);
						sheet.mergeCells(0,1+i,0,a+i);
						sheet.mergeCells(1,1+i,1,a+i);
						sheet.mergeCells(2,1+i,2,a+i);
						sheet.mergeCells(3,1+i,3,a+i);
						sheet.mergeCells(4,1+i,4,a+i);
						sheet.mergeCells(5,1+i,5,a+i);
						//第7列模块列合并列数获取
						 Iterator it2 = mkmaps.keySet().iterator();  
						  while (it2.hasNext()) {
							  String key = it2.next().toString();
							  if (list.get(i).get("xmmkmc").equals(key)) {//根据当前这条数据的项目模块
								  b=Integer.parseInt(mkmaps.get(key));
								  break;
							  }
						  }
						sheet.addCell(xmmkmc);
						sheet.mergeCells(6,1+i,6,b+i);
						/**
						 * mergeCells合并单元格，
						 * 第一个参数：要合并的单元格最左上角的列号
						 * 第二个参数：要合并的单元格最左上角的行号
						 * 第三个参数：要合并的单元格最右角的列号
						 * 第四个参数：要合并的单元格最右下角的行号
						 */
						//第8列项目列合并列数获取
						 Iterator it3 = xmmaps.keySet().iterator();  
						  while (it3.hasNext()) {
							  String key = it3.next().toString();
							  if (list.get(i).get("jfxmmc").equals(key)) {//根据当前这条数据的项目模块
								  c=Integer.parseInt(xmmaps.get(key));
								  break;
							  }
						  }
						
						sheet.addCell(jfxmmc);
						sheet.mergeCells(7,1+i,7,c+i); 
						
						sheet.addCell(sxsm);
						sheet.addCell(fs);
						sheet.addCell(cysj);
						sheet.addCell(lb);
						sheet.addCell(shr);
						sheet.addCell(shsj);
						sheet.addCell(shztmc);
						
						 Iterator it4 = fsmaps.keySet().iterator();  
						  while (it4.hasNext()) {
							  String key = it4.next().toString();
							  if (list.get(i).get("xmmkmc").equals(key)) {//根据当前这条数据的项目模块
								  e=Float.parseFloat(fsmaps.get(key));
								  break;
							  }
						  }
						  Label mkfs = new Label(15, 1+i, String.valueOf(e), body_cf);
						  Label zf = new Label(16, 1+i, String.valueOf(d), body_cf);
						  
						sheet.addCell(mkfs);//模块得分
						sheet.mergeCells(15,1+i,15,b+i); 
						sheet.addCell(zf);//得分
						sheet.mergeCells(16,1+i,16,a+i);
						
						Iterator it5 = zshztmap.keySet().iterator();  
						  while (it5.hasNext()) {
							  String key = it5.next().toString();
							  if (list.get(i).get("xh").equals(key)) {//根据当前这条数据的项目模块
								 String zt =zshztmap.get(key);
								  Label zshzt = new Label(17, 1+i, zt, body_cf);
								  sheet.addCell(zshzt);//得分
									sheet.mergeCells(17,1+i,17,a+i);
								  break;
							  }
						  }
					}else {//此时首先学号不是第一次出现
						if (!dqmk.equals(bjmk)){//如果当前模块是第一次，模块属下的项目肯定也是第一次，【要取到模块合并列数和项目合并列数】
							sheet.addCell(xh);
							sheet.addCell(xm);
							sheet.addCell(nj);
							sheet.addCell(xymc);
							sheet.addCell(zymc);
							sheet.addCell(bjmc);
							//第7列模块列合并列数获取
							 Iterator it1 = mkmaps.keySet().iterator();  
							  while (it1.hasNext()) {
								  String key = it1.next().toString();
								  if (list.get(i).get("xmmkmc").equals(key)) {
									  b=Integer.parseInt(mkmaps.get(key));
									  break;
								  }
								  
							  }
								sheet.addCell(xmmkmc);
								sheet.mergeCells(6,1+i,6,b+i); 
								//第8列项目列合并列数获取
							 Iterator it2 = xmmaps.keySet().iterator();  
							  while (it2.hasNext()) {
								  String key = it2.next().toString();
								  if (list.get(i).get("jfxmmc").equals(key)) {
									  c=Integer.parseInt(xmmaps.get(key));
								  }
								  
							  }
								sheet.addCell(jfxmmc);
								sheet.mergeCells(7,1+i,7,c+i); 
								sheet.addCell(sxsm);
								sheet.addCell(fs);
								sheet.addCell(cysj);
								sheet.addCell(lb);
								sheet.addCell(shr);
								sheet.addCell(shsj);
								sheet.addCell(shztmc);
								Iterator it4 = fsmaps.keySet().iterator();  
								  while (it4.hasNext()) {
									  String key = it4.next().toString();
									  if (list.get(i).get("xmmkmc").equals(key)) {//根据当前这条数据的项目模块
										  e=Float.parseFloat(fsmaps.get(key));
										  break;
									  }
								  }
								  Label mkfs = new Label(15, 1+i, String.valueOf(e), body_cf);
								  
								sheet.addCell(mkfs);//模块得分
								sheet.mergeCells(15,1+i,15,b+i); 
								
						}else { //如果当前模块不是第一次,但
							if (!dqxm.equals(bjxm)) { //如果项目是第一次出现，此时要获取项目合并列数
								sheet.addCell(xh);
								sheet.addCell(xm);
								sheet.addCell(nj);
								sheet.addCell(xymc);
								sheet.addCell(zymc);
								sheet.addCell(bjmc);
								sheet.addCell(xmmkmc);
								//第8列项目列合并列数获取
								 Iterator it2 = xmmaps.keySet().iterator();  
								  while (it2.hasNext()) {
									  String key = it2.next().toString();
									  if (list.get(i).get("jfxmmc").equals(key)) {
										  c=Integer.parseInt(xmmaps.get(key));
									  }
									  
								  }
								    sheet.addCell(jfxmmc);
									sheet.mergeCells(7,1+i,7,c+i); 
									sheet.addCell(sxsm);
									sheet.addCell(fs);
									sheet.addCell(cysj);
									sheet.addCell(lb);
									sheet.addCell(shr);
									sheet.addCell(shsj);
									sheet.addCell(shztmc);
							}
						}
					}
					  bjxh =dqxh;//标记学号
					  bjmk = dqmk;//标记模块
					  bjxm =dqxm;//标记模块
				}
			}
			wwb.write();
			wwb.close();
		}
		return file;
	}
}
