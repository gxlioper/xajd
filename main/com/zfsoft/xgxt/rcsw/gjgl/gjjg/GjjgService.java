/**
 * @部门:学工产品事业部
 * @日期：2016-11-29 下午04:18:53 
 */  
package com.zfsoft.xgxt.rcsw.gjgl.gjjg;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @作者： CP[工号:1352]
 * @时间： 2016-11-29 下午04:18:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GjjgService extends SuperServiceImpl<GjjgForm, GjjgDao> {
	private GjjgDao rd = new GjjgDao();

	public GjjgService() {
		setDao(rd);
	}
	
	public HashMap<String, String> getFdyxx(String zgh){
		return dao.getFdyxx(zgh);
	}

	/** 
	 * @描述:导入
	 * @作者：CP[工号：1352]
	 * @日期：2017-1-12 下午03:15:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param inputStream
	 * @param model
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,
			GjjgForm gjjgform) {
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap= this.DrExcelInfoCheck(is, gjjgform);
			//判断excel表格是否为空
			if("null".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){//如果无错误，插入数据库
				List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
				if( paralist == null ||paralist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
			  	 this.saveDrDataIntoDb(paralist);
			  	resultMap.put("zqts",paralist.size());//正确条数
			  	resultMap.put("cwts",0);//错误条数
			}else{//如果有错误数据，错的记录生成错误文件提供下载，正确数据插入数据库
				List<String[]> zqlist = (List<String[]>) resultMap.get("drlist");
				List<String[]> errorlist = (List<String[]>) resultMap.get("errorlist");
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),gjjgform.getFilepath());
				resultMap.put("gid", gid);
				this.saveDrDataIntoDb(zqlist);
				resultMap.put("zqts",zqlist.size());//正确条数
			  	resultMap.put("cwts",errorlist.size());//错误条数
				logger.info("导入失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 * @throws IOException  
	 * @描述:将错误excel表格写入服务器
	 * @作者：CP[工号：1352]
	 * @日期：2017-1-12 下午03:18:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @param filepath
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	private String uploadErrorExcel(List<String[]> errorlist, String filepath) throws IOException, RowsExceededException, WriteException {
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
            Label labelxh= new Label(0, 0, "学号");//表示第
            Label labelxm= new Label(1, 0, "姓名");
//            Label labelqsh= new Label(2, 0, "寝室号");
            Label labelzbffzr= new Label(2, 0, "主办方负责人");
            Label labelsy= new Label(3, 0, "事由");//表示第
            Label labelqjkssj= new Label(4, 0, "请假开始时间");
            Label labelqjjssj= new Label(5, 0, "请假结束时间");
            Label labelqjjc= new Label(6, 0, "请假节次");
            Label labelsfgq= new Label(7, 0, "是否归寝");//表示第
            Label labelbgqsj= new Label(8, 0, "不归寝时间");
            Label labelbz= new Label(9, 0, "备注");
            try {
            	ws.addCell(labelxh);
				ws.addCell(labelxm);
//				ws.addCell(labelqsh);
	            ws.addCell(labelzbffzr);
	            ws.addCell(labelsy);
				ws.addCell(labelqjkssj);
				ws.addCell(labelqjjssj);
	            ws.addCell(labelqjjc);
	            ws.addCell(labelsfgq);
				ws.addCell(labelbgqsj);
				ws.addCell(labelbz);
			} catch (RowsExceededException e1) {
				e1.printStackTrace();
			} catch (WriteException e1) {
				e1.printStackTrace();
			}
            
            for (int i = 0; i < errorlist.size(); i++) {
                 for(int j = 0;j<errorlist.get(i).length;j++){
                	 Label labelId_i= null;
                	 if(j<=9){
                		  labelId_i= new Label(j, i+1, errorlist.get(i)[j]+"");
                		  ws.addCell(labelId_i);
                	 }else{
                		 WritableCellFormat wcf = new WritableCellFormat();
         				WritableFont wf = new WritableFont(WritableFont.ARIAL);
         				try {
							wf.setColour(Colour.RED);
							wcf.setFont(wf);
							wcf.setAlignment(Alignment.CENTRE);
							labelId_i = new Label(j, i+1, errorlist.get(i)[j],wcf);
							ws.addCell(labelId_i);
						} catch (RowsExceededException e) {
							e.printStackTrace();
						} catch (WriteException e) {
							e.printStackTrace();
						}
         				
                	 }
                 }
            }
            
		}finally{
			 wwb.write();
			 try {
				wwb.close();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}

	/**
	 * @throws Exception 
	 * @return  
	 * @描述:正确记录写入数据库
	 * @作者：CP[工号：1352]
	 * @日期：2017-1-12 下午03:18:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paralist
	 * void 返回类型 
	 * @throws 
	 */
	private boolean saveDrDataIntoDb(List<String[]> paralist) throws Exception {
		if(paralist != null && paralist.size() > 0){
			return dao.saveDrDataIntoDb(paralist).length >0 ? true :false;
		}else{
			return false;
		}
		
	}

	
	
	
	 private int getIndex(String[] arr, String one) {  
	        for ( int i = 0; i < arr.length; i++) {  
	            if (one.equals(arr[i])) {  
	                return i;  
	            }  
	        }  
	        return -1;  //若数组中没有则返回-1  
	}  
	/** 
	 * @描述:导入信息验证
	 * @作者：CP[工号：1352]
	 * @日期：2017-1-12 下午03:18:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param zhfdrform
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	private HashMap<String, Object> DrExcelInfoCheck(InputStream is,
			GjjgForm gjjgform) {
		//导入记录数组
		List<String[]> drlist = new ArrayList<String[]>();
		//错误记录数组
		List<String[]> errorlist = new ArrayList<String[]>() ;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
		Workbook rwb = null;
		try {
			rwb = Workbook.getWorkbook(is);
			Sheet rs=rwb.getSheet(0);//取第一个工作表
		    int clos=rs.getColumns();//得到所有的列
	        int rows=rs.getRows();//得到所有的行
	        //判断excel表格是否为空
	        if(clos < 10  || rows < 2){
	        	resultMap.put("result", "null");
	        }else{
	        	  //判断excel表内有重复数据
	        	String [] flag = new String[rows];
	        	for (int i = 1; i < rows; i++) {
	        		//取出每行验证数据，塞入lsmap
        			HashMap<String, String>  lsmap = new HashMap<String, String>();
        			String xh = rs.getCell(0, i).getContents();
        			String xm = rs.getCell(1, i).getContents();
        			String zbffzr = rs.getCell(2, i).getContents();
        			String sy = rs.getCell(3, i).getContents();
        			String qjkssj = rs.getCell(4, i).getContents();
        			String qjjssj = rs.getCell(5, i).getContents();
        			String qjjc = rs.getCell(6, i).getContents();
        			String sfgq = rs.getCell(7, i).getContents();
        			String bgqsj = rs.getCell(8, i).getContents();
        			String bz = rs.getCell(9, i).getContents();
        			if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
        					StringUtils.isNull(zbffzr.trim())&& 
        					StringUtils.isNull(sy.trim()) && StringUtils.isNull(qjkssj.trim())&& 
        					StringUtils.isNull(qjjssj.trim()) && StringUtils.isNull(qjjc.trim())&& 
        					StringUtils.isNull(sfgq.trim())){
        				continue;
        			}
        			
        			String one = xh+qjkssj+qjjssj+qjjc;
        			int k = this.getIndex(flag,one);
        			if (k==-1) {
        				flag[i]=xh+qjkssj+qjjssj+qjjc;
					}else {//如果数组中存在，说明重复，这时候取到重复的学生信息，最后写到错误数据excel里
	        			lsmap.put("xh", xh);
	        			lsmap.put("xm", xm);
	        			lsmap.put("qsh", this.getQsh(xh));
	        			lsmap.put("zbffzr", zbffzr);
	        			lsmap.put("sy", sy);
	        			lsmap.put("qjkssj", qjkssj);
	        			lsmap.put("qjjssj", qjjssj);
	        			lsmap.put("qjjc", qjjc);
	        			lsmap.put("sfgq", sfgq);
	        			lsmap.put("bgqsj", bgqsj);
	        			lsmap.put("bz", bz);
	        			ArrayList<String> cflist = new ArrayList<String>();
	        			cflist.add(lsmap.get("xh"));
	        			cflist.add(lsmap.get("xm"));
	        			cflist.add(lsmap.get("zbffzr"));
	        			cflist.add(lsmap.get("sy"));
	        			cflist.add(lsmap.get("qjkssj"));
	        			cflist.add(lsmap.get("qjjssj"));
	        			cflist.add(lsmap.get("qjjc"));
	        			cflist.add(lsmap.get("sfgq"));
	        			cflist.add(lsmap.get("bgqsj"));
	        			cflist.add(lsmap.get("bz"));
	        			cflist.add("当前学生数据重复！");
	        			errorlist.add(cflist.toArray(new String[]{}));	
	        			resultMap.put("result", "sjcf");
					}
	        	}
	        	if(errorlist.size()==0){
	        		for (int i = 1; i < rows; i++) {
	        			//取出每行验证数据，塞入lsmap
	        			HashMap<String, String>  lsmap = new HashMap<String, String>();
	        			String xh = rs.getCell(0, i).getContents();
	        			String xm = rs.getCell(1, i).getContents();
	        			String zbffzr = rs.getCell(2, i).getContents();
	        			String sy = rs.getCell(3, i).getContents();
	        			String qjkssj = rs.getCell(4, i).getContents();
	        			String qjjssj = rs.getCell(5, i).getContents();
	        			String qjjc = rs.getCell(6, i).getContents();
	        			String sfgq = rs.getCell(7, i).getContents();
	        			String bgqsj = rs.getCell(8, i).getContents();
	        			String bz = rs.getCell(9, i).getContents();
	        			if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
	        					StringUtils.isNull(zbffzr.trim())&& 
	        					StringUtils.isNull(sy.trim()) && StringUtils.isNull(qjkssj.trim())&& 
	        					StringUtils.isNull(qjjssj.trim()) && StringUtils.isNull(qjjc.trim())&& 
	        					StringUtils.isNull(sfgq.trim())){
	        				continue;
	        			}
	        			
	        			lsmap.put("xh", xh);
	        			lsmap.put("xm", xm);
	        			lsmap.put("qsh", this.getQsh(xh));
	        			lsmap.put("zbffzr", zbffzr);
	        			lsmap.put("sy", sy);
	        			lsmap.put("qjkssj", qjkssj);
	        			lsmap.put("qjjssj", qjjssj);
	        			lsmap.put("qjjc", qjjc);
	        			lsmap.put("sfgq", sfgq);
	        			lsmap.put("bgqsj", bgqsj);
	        			lsmap.put("bz", bz);
	        			HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, gjjgform);//循环验证内容格式
	        			ArrayList<String> paralist = new ArrayList<String>();
	        			if("false".equals(resultmap.get("result"))){
	        				paralist.add(lsmap.get("xh"));
	        				paralist.add(lsmap.get("xm"));
	        				paralist.add(lsmap.get("zbffzr"));
	        				paralist.add(lsmap.get("sy"));
	        				paralist.add(lsmap.get("qjkssj"));
	        				paralist.add(lsmap.get("qjjssj"));
	        				paralist.add(lsmap.get("qjjc"));
	        				paralist.add(lsmap.get("sfgq"));
	        				paralist.add(lsmap.get("bgqsj"));
	        				paralist.add(lsmap.get("bz"));
	        				if(resultmap.get("resultmap") != null ){
	        					Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
	        					for (Map.Entry<String, String> entry : map.entrySet()){
	        						if(!entry.getKey().equals("xh") && !entry.getKey().equals("xm")
	        								&&	!entry.getKey().equals("qsh") 
	        								&& !entry.getKey().equals("zbffzr") && !entry.getKey().equals("sy")	
	        								&& !entry.getKey().equals("qjkssj") && !entry.getKey().equals("qjjssj")
	        								&& !entry.getKey().equals("qjjc") && !entry.getKey().equals("sfgq")
	        								&& !entry.getKey().equals("bgqsj") && !entry.getKey().equals("bz")){
	        							paralist.add(entry.getValue());
	        						}
	        					}
	        				}
	        				errorlist.add(paralist.toArray(new String[]{}));
	        				resultMap.put("result", "false");
	        			}else{
	        				paralist.add(lsmap.get("xh"));
	        				paralist.add(lsmap.get("xm"));
	        				paralist.add(lsmap.get("qsh"));
	        				paralist.add(lsmap.get("zbffzr"));
	        				paralist.add(lsmap.get("sy"));
	        				paralist.add(lsmap.get("qjkssj"));
	        				paralist.add(lsmap.get("qjjssj"));
	        				paralist.add(lsmap.get("qjjc"));
	        				paralist.add(lsmap.get("sfgq"));
	        				paralist.add(lsmap.get("bgqsj"));
	        				paralist.add(lsmap.get("bz"));
	        				drlist.add(paralist.toArray(new String[]{}));
	        			}
	        		}
	        	}
	        	
	        		resultMap.put("drlist", drlist);
	        		resultMap.put("errorlist", errorlist);
	        	
	        }
	      
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/** 
	 * @描述:取楼栋寝室号
	 * @throws 
	 */
	private String getQsh(String xh) {
		return dao.getQsh(xh);
	}

	/** 
	 * @描述:循环验证每行的记录
	 * @作者：CP[工号：1352]
	 * @日期：2017-1-12 下午03:39:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lsmap
	 * @param gjjgform
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws 
	 */
	private HashMap<String, Object> checkEveryRowRecord(
			HashMap<String, String> lsmap, GjjgForm gjjgform) {
		/**
		 * 验证规则
		 * 1.xh,xm联合验证是否存在xsxxb
		 * 2.导入记录老师的数据范围验证
		 * 3.请假时间 验证只支持yyyy-MM-dd格式导入
		 * 4.是否归寝限制输入 是或否
		 * 5.不归寝时，不归寝时间不可为空
		 * 6.重复导入验证
		 */
		String message = "true";
		GjjgDao gjjgDao = new GjjgDao();
		//为空验证
		if(StringUtils.isNull(lsmap.get("xh")) || StringUtils.isNull(lsmap.get("xm"))
				|| StringUtils.isNull(lsmap.get("zbffzr")) || StringUtils.isNull(lsmap.get("sy")) || StringUtils.isNull(lsmap.get("qjkssj"))
	|| StringUtils.isNull(lsmap.get("qjjssj")) || StringUtils.isNull(lsmap.get("qjjc")) || StringUtils.isNull(lsmap.get("sfgq"))){
				message = "false";
				lsmap.put("kyz", "学号，姓名，主办方负责人，事由，请假开始时间，请假结束时间，请假节次，是否归寝不可为空！");
		}else{
			//xh,xm联合验证是否存在xsxxb
			if(!gjjgDao.checkXhXmIsExist(lsmap.get("xh"),lsmap.get("xm"))){
				message = "false";
				lsmap.put("xhxmlhyz", "学号/姓名不对应或不存在学生信息表中！");
			}
			//重复导入验证
			if(gjjgDao.checkXhQjsjIsExist(lsmap.get("xh"),lsmap.get("xm"),lsmap.get("qjkssj"),lsmap.get("qjjssj"),lsmap.get("qjjc"))){
				message = "false";
				lsmap.put("cfdryz", "重复导入！");
			}
			//导入记录老师的 数据范围验证
			if(!gjjgDao.checkJsDrSjfw(lsmap.get("xh"), gjjgform)){
				message = "false";
				lsmap.put("sjfwyz", "没有权限导入该学生！");
			}
			//是否归寝 只能填写 是或否
			if (!"是".equals(lsmap.get("sfgq"))&&!"否".equals(lsmap.get("sfgq"))) {
				message = "false";
				lsmap.put("sfgqyz", "是否归寝只能填是/否！");
			}
			//是否归寝 填写否 以后，不归寝说明不能为空
			if ("否".equals(lsmap.get("sfgq"))) {
				if (lsmap.get("bgqsj").length()==0) {
					message = "false";
					lsmap.put("bgqsjyz", "不归寝时，不归寝时间不能为空！");
				}
			}
			try {
				DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
				Date date1 = (Date)formatter1.parse(lsmap.get("qjkssj"));
				if(!lsmap.get("qjkssj").equals(formatter1.format(date1))){
					message = "false";
					lsmap.put("qjkssjyz", "请假开始时间格式必须为yyyy-MM-dd！");
				}
			} catch (ParseException e) {
				message = "false";
				lsmap.put("qjkssjyz", "请假开始时间格式必须为yyyy-MM-dd！");
			}
			try {
				DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
				Date date2 = (Date)formatter1.parse(lsmap.get("qjjssj"));
				if(!lsmap.get("qjjssj").equals(formatter1.format(date2))){
					message = "false";
					lsmap.put("qjjssjyz", "请假结束时间格式必须为yyyy-MM-dd！");
				}
			} catch (ParseException e) {
				message = "false";
				lsmap.put("qjjssjyz", "请假结束时间格式必须为yyyy-MM-dd！");
			}
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	
	
}
