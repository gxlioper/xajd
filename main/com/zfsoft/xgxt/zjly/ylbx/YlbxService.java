/**
 * @部门:学工产品事业部
 * @日期：2016-4-19 上午10:04:07 
 */  
package com.zfsoft.xgxt.zjly.ylbx;

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
import com.zfsoft.xgxt.rcsw.gjgl.gjjg.GjjgDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-4-19 上午10:04:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YlbxService extends SuperServiceImpl<YlbxForm,YlbxDao> {
	private YlbxDao dao= new YlbxDao();
	
	public YlbxService(){
		 setDao(dao);
	 }
	 
	public boolean isExistSame(YlbxForm form) throws Exception {
		String num = dao.checkExist(form);
		return Integer.valueOf(num) > 0;
	}

	/** 
	 * @描述：获取该学生续保信息列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年10月17日 下午3:08:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getXbxxList(String xh) {
		return dao.getXbxxList(xh);
	}
	public Object getFdyxx(String zgh) {
		// TODO 自动生成方法存根
		return dao.getFdyxx(zgh);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-5-3 上午10:09:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param inputStream
	 * @param model
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,
			YlbxForm model) {
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap= this.DrExcelInfoCheck(is, model);
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
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),model.getFilepath());
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
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-5-3 上午10:28:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @param filepath
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	private String uploadErrorExcel(List<String[]> errorlist, String filepath) throws Exception {
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
            Label labelxn= new Label(0, 0, "学年");
            Label labelxh= new Label(1, 0, "学号");
            Label labelsfzh= new Label(2, 0, "身份证号");
            Label labelxm= new Label(3, 0, "姓名");
            Label labelxb= new Label(4, 0, "性别");
            Label labelcsrq= new Label(5, 0, "出生日期");
            Label labelrxsj= new Label(6, 0, "入学时间");
            Label labelzlbh= new Label(7, 0, "证历本号");
            Label labelcblx= new Label(8, 0, "参保类型");
            try {
            	ws.addCell(labelxn);
				ws.addCell(labelxh);
	            ws.addCell(labelsfzh);
	            ws.addCell(labelxm);
				ws.addCell(labelxb);
				ws.addCell(labelcsrq);
	            ws.addCell(labelrxsj);
	            ws.addCell(labelzlbh);
				ws.addCell(labelcblx);
			} catch (RowsExceededException e1) {
				e1.printStackTrace();
			} catch (WriteException e1) {
				e1.printStackTrace();
			}
            
            for (int i = 0; i < errorlist.size(); i++) {
                 for(int j = 0;j<errorlist.get(i).length;j++){
                	 Label labelId_i= null;
                	 if(j<=8){
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
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-5-3 上午10:10:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param model
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws 
	 */
	private HashMap<String, Object> DrExcelInfoCheck(InputStream is,
			YlbxForm model) {
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
	        if(clos < 9  || rows < 2){
	        	resultMap.put("result", "null");
	        }else{
	        	  //判断excel表内有重复数据
	        	String [] flag = new String[rows];
	        	for (int i = 1; i < rows; i++) {
	        		//取出每行验证数据，塞入lsmap
        			HashMap<String, String>  lsmap = new HashMap<String, String>();
        			String xn = rs.getCell(0, i).getContents();
        			String xh = rs.getCell(1, i).getContents();
        			String sfzh = rs.getCell(2, i).getContents();
        			String xm = rs.getCell(3, i).getContents();
        			String xb = rs.getCell(4, i).getContents();
        			String csrq = rs.getCell(5, i).getContents();
        			String rxsj = rs.getCell(6, i).getContents();
        			String zlbh = rs.getCell(7, i).getContents();
        			String cblb = rs.getCell(8, i).getContents();
        			if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
        					StringUtils.isNull(sfzh.trim())&& 
        					StringUtils.isNull(xn.trim()) && StringUtils.isNull(rxsj.trim())&& 
        					StringUtils.isNull(csrq.trim()) && StringUtils.isNull(zlbh.trim())&& 
        					StringUtils.isNull(cblb.trim())){
        				continue;
        			}
        			
        			String one = xh+xn+zlbh+cblb;
        			int k = this.getIndex(flag,one);
        			if (k==-1) {
        				flag[i]=xh+xn+zlbh+cblb;
					}else {//如果数组中存在，说明重复，这时候取到重复的学生信息，最后写到错误数据excel里
	        			lsmap.put("xn", xn);
	        			lsmap.put("xh", xh);
	        			lsmap.put("sfzh", sfzh);
	        			lsmap.put("xm", xm);
	        			lsmap.put("xb", xb);
	        			lsmap.put("csrq", csrq);
	        			lsmap.put("rxsj", rxsj);
	        			lsmap.put("zlbh", zlbh);
	        			lsmap.put("cblb", cblb);
	        			ArrayList<String> cflist = new ArrayList<String>();
	        			cflist.add(lsmap.get("xn"));
	        			cflist.add(lsmap.get("xh"));
	        			cflist.add(lsmap.get("sfzh"));
	        			cflist.add(lsmap.get("xm"));
	        			cflist.add(lsmap.get("xb"));
	        			cflist.add(lsmap.get("csrq"));
	        			cflist.add(lsmap.get("rxsj"));
	        			cflist.add(lsmap.get("zlbh"));
	        			cflist.add(lsmap.get("cblb"));
	        			cflist.add("当前学生数据重复！");
	        			errorlist.add(cflist.toArray(new String[]{}));	
	        			resultMap.put("result", "sjcf");
					}
	        	}
	        	if(errorlist.size()==0){
	        		for (int i = 1; i < rows; i++) {
	        			//取出每行验证数据，塞入lsmap
	        			HashMap<String, String>  lsmap = new HashMap<String, String>();
	        			String xn = rs.getCell(0, i).getContents();
	        			String xh = rs.getCell(1, i).getContents();
	        			String sfzh = rs.getCell(2, i).getContents();
	        			String xm = rs.getCell(3, i).getContents();
	        			String xb = rs.getCell(4, i).getContents();
	        			String csrq = rs.getCell(5, i).getContents();
	        			String rxsj = rs.getCell(6, i).getContents();
	        			String zlbh = rs.getCell(7, i).getContents();
	        			String cblb = rs.getCell(8, i).getContents();
	        			if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
	        					StringUtils.isNull(sfzh.trim())&& 
	        					StringUtils.isNull(xn.trim()) && StringUtils.isNull(rxsj.trim())&& 
	        					StringUtils.isNull(csrq.trim()) && StringUtils.isNull(zlbh.trim())&& 
	        					StringUtils.isNull(cblb.trim())){
	        				continue;
	        			}
	        			lsmap.put("xn", xn);
	        			lsmap.put("xh", xh);
	        			lsmap.put("sfzh", sfzh);
	        			lsmap.put("xm", xm);
	        			lsmap.put("xb", xb);
	        			lsmap.put("csrq", csrq);
	        			lsmap.put("rxsj", rxsj);
	        			lsmap.put("zlbh", zlbh);
	        			lsmap.put("cblb", cblb);
	        			HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, model);//循环验证内容格式
	        			ArrayList<String> paralist = new ArrayList<String>();
	        			if("false".equals(resultmap.get("result"))){
	        				paralist.add(lsmap.get("xn"));
	        				paralist.add(lsmap.get("xh"));
	        				paralist.add(lsmap.get("sfzh"));
	        				paralist.add(lsmap.get("xm"));
	        				paralist.add(lsmap.get("xb"));
	        				paralist.add(lsmap.get("csrq"));
	        				paralist.add(lsmap.get("rxsj"));
	        				paralist.add(lsmap.get("zlbh"));
	        				paralist.add(lsmap.get("cblb"));
	        				if(resultmap.get("resultmap") != null ){
	        					Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
	        					for (Map.Entry<String, String> entry : map.entrySet()){
	        						if(!entry.getKey().equals("xn") && !entry.getKey().equals("xh")
	        								&& !entry.getKey().equals("sfzh") && !entry.getKey().equals("xm")	
	        								&& !entry.getKey().equals("xb") && !entry.getKey().equals("csrq")
	        								&& !entry.getKey().equals("rxsj") && !entry.getKey().equals("zlbh")
	        								&& !entry.getKey().equals("cblb")){
	        							paralist.add(entry.getValue());
	        						}
	        					}
	        				}
	        				errorlist.add(paralist.toArray(new String[]{}));
	        				resultMap.put("result", "false");
	        			}else{
	        				paralist.add(lsmap.get("xn"));
	        				paralist.add(lsmap.get("xh"));
	        				paralist.add(lsmap.get("sfzh"));
	        				paralist.add(lsmap.get("xm"));
	        				paralist.add(lsmap.get("xb"));
	        				paralist.add(lsmap.get("csrq"));
	        				paralist.add(lsmap.get("rxsj"));
	        				paralist.add(lsmap.get("zlbh"));
	        				paralist.add(lsmap.get("cblb"));
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
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-5-3 上午10:30:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lsmap
	 * @param model
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws 
	 */
	private HashMap<String, Object> checkEveryRowRecord(
			HashMap<String, String> lsmap, YlbxForm model) {
		
		String message = "true";
		GjjgDao gjjgDao = new GjjgDao();
		YlbxDao ylbxDao = new YlbxDao();
		//为空验证
		if(StringUtils.isNull(lsmap.get("xn")) || StringUtils.isNull(lsmap.get("xh"))
				|| StringUtils.isNull(lsmap.get("sfzh")) || StringUtils.isNull(lsmap.get("xm")) || StringUtils.isNull(lsmap.get("xb"))
	|| StringUtils.isNull(lsmap.get("csrq")) || StringUtils.isNull(lsmap.get("rxsj")) || StringUtils.isNull(lsmap.get("zlbh"))|| StringUtils.isNull(lsmap.get("cblb"))){
				message = "false";
				lsmap.put("kyz", "学年，学号，身份证号，姓名，性别，出生日期，入学时间，证历本号，参保类别不可为空！");
		}else{
			//xh,xm联合验证是否存在xsxxb
			if(!gjjgDao.checkXhXmIsExist(lsmap.get("xh"),lsmap.get("xm"))){
				message = "false";
				lsmap.put("xhxmlhyz", "学号/姓名不对应或不存在学生信息表中！");
			}
			//重复导入验证
			if(ylbxDao.checkXhQjsjIsExist(lsmap.get("xn"),lsmap.get("xh"))){
				message = "false";
				lsmap.put("cfdryz", "当前学年学号医疗保险数据已存在！");
			}
			//导入记录老师的 数据范围验证
			if(!ylbxDao.checkJsDrSjfw(lsmap.get("xh"), model)){
				message = "false";
				lsmap.put("sjfwyz", "没有权限导入该学生！");
			}
			if (!"男".equals(lsmap.get("xb"))&&!"女".equals(lsmap.get("xb"))) {
				message = "false";
				lsmap.put("xbyz", "性别只能填写男/女！");
			}
			if (!"新参保".equals(lsmap.get("cblb"))&&!"续保".equals(lsmap.get("cblb"))) {
				message = "false";
				lsmap.put("cblbyz", "参保类别只能填写新参保/续保！");
			}
//			try {
//				DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
//				Date date1 = (Date)formatter1.parse(lsmap.get("rxsj"));
//				if(!lsmap.get("rxsj").equals(formatter1.format(date1))){
//					message = "false";
//					lsmap.put("rxsjyz", "入学时间格式必须为yyyy-MM-dd！");
//				}
//			} catch (ParseException e) {
//				message = "false";
//				lsmap.put("rxsjyz", "入学时间格式必须为yyyy-MM-dd！");
//			}
//			try {
//				DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
//				Date date2 = (Date)formatter1.parse(lsmap.get("csrq"));
//				if(!lsmap.get("csrq").equals(formatter1.format(date2))){
//					message = "false";
//					lsmap.put("csrqyz", "出生日期格式必须为yyyy-MM-dd！");
//				}
//			} catch (ParseException e) {
//				message = "false";
//				lsmap.put("csrqyz", "出生日期格式必须为yyyy-MM-dd！");
//			}
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-5-3 上午10:12:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param flag
	 * @param one
	 * @return
	 * int 返回类型 
	 * @throws 
	 * 
	 */
	private int getIndex(String[] arr, String one) {  
        for ( int i = 0; i < arr.length; i++) {  
            if (one.equals(arr[i])) {  
                return i;  
            }  
        }  
        return -1;  //若数组中没有则返回-1  
}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-5-3 上午10:10:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paralist
	 * void 返回类型 
	 * @throws 
	 */
	private boolean saveDrDataIntoDb(List<String[]> paralist)throws Exception {
		if(paralist != null && paralist.size() > 0){
			return dao.saveDrDataIntoDb(paralist).length >0 ? true :false;
		}else{
			return false;
		}
		
	}

	
} 
