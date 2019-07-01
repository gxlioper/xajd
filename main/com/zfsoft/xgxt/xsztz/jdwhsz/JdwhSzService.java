/**
 * @部门:学工产品事业部
 * @日期：2016-8-1 上午08:58:13 
 */  
package com.zfsoft.xgxt.xsztz.jdwhsz;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import xgxt.comm.search.SearchService;
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
 * @时间： 2016-8-1 上午08:58:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JdwhSzService extends SuperServiceImpl<JdwhSzForm, JdwhSzDao> {
	//时间格式
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	/**
	 * @throws Exception 
	 * 
	 * @描述:获取个人项目已维护入阶段维护表的数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午01:52:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jdid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrxmYwh(JdwhSzForm t, User user) throws Exception{
	    return dao.getGrxmYwh(t, user);
	}
	
	/**
	 * 
	 * @描述: 项目阶段维护信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午04:06:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jdid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXmJdwhxx(String jdid){
		return dao.getXmJdwhxx(jdid);
	}
	
	/**
	 * 
	 * @描述:个人项目阶段维护选择成员页面的查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午03:00:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrxmcyList(JdwhSzForm t, User user)
			throws Exception {
		return dao.getGrxmcyList(t, user);
	}
	
	/**
	 * 
	 * @描述:获取学号拼接字符串
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午04:57:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jdid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXhs(String jdid){
		return dao.getXhs(jdid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:批量添加阶段设置成员
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 上午09:50:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jdwhszform
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJdszCy(JdwhSzForm jdwhszform) throws Exception{
		String[] jdcys = jdwhszform.getJdcys();
		String xmdm = jdwhszform.getXmdm();
		String jdid = jdwhszform.getJdid();
		String jbf = jdwhszform.getJbf();
		String jdsj = GetTime.getTimeByFormat(DATE_FORMAT);
		List<String[]> param = new ArrayList<String[]>();
		for (int i = 0; i < jdcys.length; i++) {
			param.add(new String[]{xmdm,jdid,jdcys[i],jbf,jdsj});
		}
		return dao.saveJdszCy(param);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:获取团体项目已维护入阶段维护表的数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午01:52:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jdid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtxmYwh(JdwhSzForm t, User user) throws Exception{
	    return dao.getTtxmYwh(t, user);
	}
	
	/**
	 * 
	 * @描述:团体项目阶段维护选择成员页面的查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 上午11:35:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtxmcyList(JdwhSzForm t, User user)
	throws Exception {
		// TODO 自动生成方法存根
		return dao.getTtxmcyList(t, user);
	}
	
	/**
	 * 
	 * @描述:获取学号拼接字符串
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午04:57:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jdid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getDzXhs(String jdid){
		return dao.getDzXhs(jdid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 获取项目名称list集合
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 下午03:01:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmmcList(User user) throws Exception{
		return dao.getXmmcList(user);
	}
	
	/**
	 * 
	 * @描述: 获取项目名称MAP集合
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 下午03:01:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXmmcMap(String xmdm){
		return dao.getXmmcMap(xmdm);
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
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,JdwhSzForm zhfdrform){
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap= this.DrExcelInfoCheck(is, zhfdrform);
			//判断excel表格是否为空
			if("null".equals(resultMap.get("result")) || "excelrepeat".equals(resultMap.get("result"))){
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
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,JdwhSzForm zhfdrform){
		//导入记录数组
		List<String[]> drlist = new ArrayList<String[]>();
		//错误记录数组
		List<String[]> errorlist = new ArrayList<String[]>() ;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
	    String jdsj = GetTime.getTimeByFormat(DATE_FORMAT);
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
	        	//行
	        	  for (int i = 1; i < rows; i++) {
	  	        	
	  	        		//取出每行验证数据，塞入lsmap
	  	        		HashMap<String, String>  lsmap = new HashMap<String, String>();
	  	        		String jdmc = rs.getCell(0, i).getContents();
	  	        		String tdmc = rs.getCell(1, i).getContents();
	  	        		String jbf = rs.getCell(2, i).getContents();
	  	        		String hdsc = rs.getCell(3, i).getContents();
	  	        		String bz = rs.getCell(4, i).getContents();
	  	        	    if(StringUtils.isNull(jdmc.trim()) && StringUtils.isNull(tdmc.trim()) && 
	  	        	    		StringUtils.isNull(jbf.trim()) && StringUtils.isNull(hdsc.trim()) && 
	  	        	    		StringUtils.isNull(bz.trim())){
	  	        	    	continue;
	  	        	    }
	  	        		lsmap.put("jdmc", jdmc.trim());
	  	        		lsmap.put("tdmc", tdmc.trim());
	  	        		lsmap.put("jbf", jbf.trim());
	  	        		lsmap.put("hdsc", hdsc.trim());
	  	        		lsmap.put("bz", bz.trim());
	  	        		HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, zhfdrform);
	  	        		ArrayList<String> paralist = new ArrayList<String>();
	  	        		if("false".equals(resultmap.get("result"))){
	  	        			paralist.add(lsmap.get("jdmc"));
	  	        			paralist.add(lsmap.get("tdmc"));
	  	        			paralist.add(lsmap.get("jbf"));
	  	        			paralist.add(lsmap.get("hdsc"));
	  	        			paralist.add(lsmap.get("bz"));
	  	        			if(resultmap.get("resultmap") != null ){
	  	        				Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
	  	        				for (Map.Entry<String, String> entry : map.entrySet()){
	  	        					if(!entry.getKey().equals("jdmc") && !entry.getKey().equals("tdmc")
	  	        					&&	!entry.getKey().equals("jbf") && !entry.getKey().equals("hdsc") && !entry.getKey().equals("bz")	
	  	        					&& !entry.getKey().equals("jdf") && !entry.getKey().equals("jdid") && !entry.getKey().equals("jdcy")){
	  	        						paralist.add(entry.getValue());
	  	        					}
	  	        					
	  	        				}
	  	        			}
	  	        			errorlist.add(paralist.toArray(new String[]{}));
	  	        			resultMap.put("result", "false");
	  	        		}else{
	  	        			paralist.add(zhfdrform.getXmdm());
	  	        			paralist.add(lsmap.get("jdid"));
	  	        			paralist.add(lsmap.get("jdcy"));
	  	        			paralist.add(lsmap.get("jbf"));
	  	        			paralist.add(lsmap.get("hdsc"));
	  	        			paralist.add(lsmap.get("bz"));
	  	        			paralist.add(jdsj);
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
	 * 
	 * @描述:循环验证每行的记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-22 下午01:43:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap,JdwhSzForm zhfdrform){
		/**
		 * 验证规则
		 * 1.xh,xm联合验证是否存在xsxxb
		 * 2.xh,sxsm,cysj,jfxmdm主键验证
		 * 3.导入记录老师的数据范围验证
		 * 4.事项说明不能超过25个字符
		 * 5.cysj 验证10位格式 ，例如2015-08-08
		 * 验证方式采取整行数据全部验证完，错误数据塞入lsmap，最后塞入返回map，进行返回
		 * 如果有一条验证规则不满足，塞入返回结果map为字符串"false",如果全部正确，为字符串"true"
		 */
		String message = "true";
		List<HashMap<String, String>> jdyzlist = dao.getJdList(zhfdrform.getXmdm());
		HashMap<String, String> xmmcmap = dao.getXmmcMap(zhfdrform.getXmdm());
		String csms = xmmcmap.get("csms");
		HashMap<String, String> resultmap = new HashMap<String, String>();
		//获取导入数据是否在结果表当中
		if("1".equals(csms)){
			 resultmap = dao.checkGrIsExists(zhfdrform.getXmdm(),lsmap.get("tdmc"));
		}else{
			 resultmap = dao.checkTtIsExists(zhfdrform.getXmdm(),lsmap.get("tdmc"));
		}
		for (HashMap<String, String> hashMap : jdyzlist) {
			message = "false";
			if(lsmap.get("jdmc").equals(hashMap.get("jdmc"))){
				lsmap.put("jdid", hashMap.get("jdid"));
				lsmap.put("jdf", hashMap.get("jdf"));
				message = "true";
				break;
			}
		}
		if("false".equals(message)){
			lsmap.put("jdmcyz", "阶段名称不存在！");
		}
		
		//为空验证
		if(StringUtils.isNull(lsmap.get("jdmc")) || StringUtils.isNull(lsmap.get("tdmc")) || StringUtils.isNull(lsmap.get("jbf"))
				){
				message = "false";
				lsmap.put("kyz", "阶段名称，团队名称/成员学号,阶段分不可为空！");
		}else{
			//验证导入的成员是否存在
			
			if("false".equals(resultmap.get("flag"))){
				message = "false";
				lsmap.put("jdcyczyz", "该团队名称/成员学号不存在在结果表中！");
			
			}else{
				lsmap.put("jdcy", resultmap.get("jdcy"));
			}
			
			
			if(StringUtils.isNull(lsmap.get("jdmcyz")) && StringUtils.isNull(lsmap.get("jdcyczyz"))){
				//jdcy,xmdm,jdid主键验证
				if(!dao.checkZjIsRepeat(zhfdrform.getXmdm(),lsmap.get("jdid"), lsmap.get("jdcy"))){
					message = "false";
					lsmap.put("zjyz", "（阶段名称和团队名称/成员学号）为联合主键吗，不能重复！");
				}
				
			}
		
				
			
			//导入的阶段分验证
			if(StringUtils.isNotNull(lsmap.get("jbf"))){
				try {
					float num = Float.parseFloat(lsmap.get("jbf"));
					if(Float.isNaN(num)){
						message = "false";
						lsmap.put("jbfNaN", "阶段分只能填入数字！");
					}else{
						if(StringUtils.isNotNull(lsmap.get("jdf")) && num > Float.parseFloat(lsmap.get("jdf"))){
							message = "false";
							lsmap.put("jbfsxyz", "该阶段的阶段分上限为"+lsmap.get("jdf")+"！");
						}
					}
				} catch (NumberFormatException e) {
					// TODO 自动生成 catch 块
					message = "false";
					lsmap.put("jbfNaN", "阶段分只能填入数字！");
					e.printStackTrace();
				
				}
				if(lsmap.get("jbf").length() > 3){
					message = "false";
					lsmap.put("jbflengthyz", "阶段分请勿超过3个字符！");
				}
				
				
			}
			//导入的活动时长字符长度验证
			if(StringUtils.isNotNull(lsmap.get("hdsc"))){
				if(lsmap.get("hdsc").length() > 25){
					message = "false";
					lsmap.put("hdsclengthyz", "活动时长请勿超过25个字符！");
				}
			}
			
			//导入的活动时长字符长度验证
			if(StringUtils.isNotNull(lsmap.get("bz"))){
				if(lsmap.get("bz").length() > 500){
					message = "false";
					lsmap.put("bzlengthyz", "备注请勿超过25个字符！");
				}
			}
			
			
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
            Label labeljdmc= new Label(0, 0, "阶段名称");//表示第
            Label labelName= new Label(1, 0, "团队名称/成员学号");
            Label labelJdf= new Label(2, 0, "阶段分");
            Label labelHdsc= new Label(3, 0, "活动时长");
            Label labelbz= new Label(4, 0, "备注");
            
            
            try {
            	ws.addCell(labeljdmc);
				ws.addCell(labelName);
				ws.addCell(labelJdf);
	            ws.addCell(labelHdsc);
	            ws.addCell(labelbz);
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
                	 if(j<=4){
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
			String jdmc = rs.getCell(0, i).getContents();
			String tdmc = rs.getCell(1,i).getContents();
			String str = jdmc.trim() + tdmc.trim() ;
			if(StringUtils.isNull((str).trim())){
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
	 * @描述:验证完毕的数据插入数据库
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-3 上午09:28:25
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
	 * @throws Exception 
	 * 
	 * @描述:学生个人项目查询（只给学生）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-3 上午10:46:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXsGrxmCx(JdwhSzForm t, User user) throws Exception{
		return dao.getXsGrxmCx(t, user);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:学生团体项目查询（只给学生）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-3 上午10:48:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsTtxmCx(JdwhSzForm t, User user) throws Exception{
		return dao.getXsTtxmCx(t, user);
		
	}
}
