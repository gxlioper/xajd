/**
 * @部门:学工产品事业部
 * @日期：2016-12-26 上午09:42:00 
 */  
package xsgzgl.qgzx.zjdx.tjcx;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-12-26 上午09:42:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjcxService extends SuperServiceImpl<TjcxForm, TjcxDAO> {
	/**
	 * @throws Exception 
	 * 
	 * @描述: 校区报酬发放统计查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-26 上午09:54:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXqbcFfTj(TjcxForm t) throws Exception{
		return dao.getXqbcFfTj(t);
	}
	
	/**
	 * 
	 * @描述:用人单位发放统计查询（横向按月展开）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-26 下午04:44:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYrdwFfTj(TjcxForm t) throws Exception{
		return dao.getYrdwFfTj(t);
	}
	
	/**
	 * 
	 * @描述: 用人单位最后一行合计
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-27 上午11:36:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getYrdwFfTjSum(TjcxForm t)throws Exception{
		return dao.getYrdwFfTjSum(t);
	}
	
	/**
	 * 
	 * @描述: 校区导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-28 上午10:26:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param os
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public  void createWwbXqDc(OutputStream os,TjcxForm model,List<HashMap<String, String>> dataList) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableCellFormat titlefont =  this.getFontStyle("title");
		WritableCellFormat titlethead =  this.getFontStyle("titlethead");
		WritableCellFormat titlebody =  this.getFontStyle("titlebody");
		/**
		 * sheet1
		 */
		WritableSheet ws = wwb.createSheet("校区导出", 0);
		String titlename = "";
		if(StringUtils.isNotNull(model.getYf())){
			titlename = model.getNd()+"年度"+model.getYf()+"月"+"各校区报酬发放统计导出";
		}else{
			titlename = model.getNd()+"年度"+"各校区报酬发放统计导出";
		}
		Label TitleLabel = new Label(0, 0, titlename, titlefont);
		ws.addCell(TitleLabel);
		ws.mergeCells(0, 0, 2, 0);
		
		
	    Label row2col1= new Label(0, 1, "校区",titlethead);
	    Label row2col2= new Label(1, 1, "勤工助学人次",titlethead);
	    Label row2col3= new Label(2, 1, "金额总计(元)",titlethead);
		 
		ws.addCell(row2col1);
		ws.addCell(row2col2);
		ws.addCell(row2col3);
		if(!dataList.isEmpty()){
			for (int i = 0; i < dataList.size(); i++) {
				Label temp1= new Label(0, 2+i, dataList.get(i).get("xqmc"),titlebody);
			    Label temp2= new Label(1, 2+i, dataList.get(i).get("rc"),titlebody);
			    Label temp3= new Label(2, 2+i, dataList.get(i).get("bcje"),titlebody);
			    ws.addCell(temp1);
				ws.addCell(temp2);
				ws.addCell(temp3);
			}
			int num = dataList.size();
			HashMap<String, String> dataMap = dao.getXqbcFfTjSum(model);
			Label temphj1= new Label(0, 2+num, "总计",titlebody);
		    Label temphj2= new Label(1, 2+num, dataMap.get("rc"),titlebody);
		    Label temphj3= new Label(2, 2+num, dataMap.get("bcje"),titlebody);
		    ws.addCell(temphj1);
			ws.addCell(temphj2);
			ws.addCell(temphj3);
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
			  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 14,  
		                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK);   
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//			  wcf_table.setShrinkToFit(true);
	          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
		}else if("titlethead".equals(paras)){
			WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 12,  
	                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                jxl.format.Colour.BLACK);   
		  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//		  wcf_table.setShrinkToFit(true);
          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
          return wcf_table;
		}else {
			WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 12,  
	                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                jxl.format.Colour.BLACK);   
		  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//		  wcf_table.setShrinkToFit(true);
          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
          return wcf_table;
		}
     
	}
    
	/**
	 * 
	 * @描述: 校区统计最后一行合计
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-28 上午11:17:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXqbcFfTjSum(TjcxForm t){
		return dao.getXqbcFfTjSum(t);
	}
	
	/**
	 * 
	 * @描述: 获取用人部门List集合
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-28 下午01:40:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
//	 */
	public List<HashMap<String, String>> getYrbmList(String bmlb){
		return dao.getYrbmList(bmlb);
	}
	
	/**
	 * 
	 * @描述: 校区导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-28 上午10:26:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param os
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public  void createWwbYrdwDc(OutputStream os,TjcxForm model,List<HashMap<String, String>> dataList) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableCellFormat titlefont =  this.getFontStyle("title");
		WritableCellFormat titlethead =  this.getFontStyle("titlethead");
		WritableCellFormat titlebody =  this.getFontStyle("titlebody");
		
		HashMap<String, String> yfDzMap = new HashMap<String, String>();
		/**
		 * 月份对应
		 */
		yfDzMap.put("01", "jan");
		yfDzMap.put("02", "feb");
		yfDzMap.put("03", "march");
		yfDzMap.put("04", "apr");
		yfDzMap.put("05", "may");
		yfDzMap.put("06", "jun");
		yfDzMap.put("07", "jul");
		yfDzMap.put("08", "aug");
		yfDzMap.put("09", "sep");
		yfDzMap.put("10", "oct");
		yfDzMap.put("11", "nov");
		yfDzMap.put("12", "decm");
		WritableSheet ws = wwb.createSheet("用人单位导出", 0);
		String titlename = "";
		if(StringUtils.isNotNull(model.getYf())){
			titlename = model.getNd()+"年度"+model.getYf()+"月"+"用人单位报酬发放统计导出";
		}else{
			titlename = model.getNd()+"年度"+"用人单位报酬发放统计导出";
		}
		Label TitleLabel = new Label(0, 0, titlename, titlefont);
		ws.addCell(TitleLabel);
		if(StringUtils.isNotNull(model.getYf())){
			ws.mergeCells(0, 0, 2, 0);
		}else{
			ws.mergeCells(0, 0, 26, 0);
		}
		
		
		
	    Label row2col1= new Label(0, 1, "用人单位",titlethead);
	    ws.addCell(row2col1);
		ws.mergeCells(0, 1, 0, 2);
		if(StringUtils.isNotNull(model.getYf())){
			Label row2col2 = new Label(1, 1, model.getYf().replace("0", "")+"月", titlethead);
			ws.addCell(row2col2);
			ws.mergeCells(1, 1, 2, 1);
			Label row3col2 = new Label(1, 2, "人次", titlethead);
			ws.addCell(row3col2);
			Label row3col3 = new Label(2, 2, "金额", titlethead);
			ws.addCell(row3col3);
		}else{
			for (int i = 1; i <= 24; i++) {
				if(i <= 12){
					Label tempyf = new Label(1+(i-1)*2, 1, i+"月", titlethead);
					ws.addCell(tempyf);
					ws.mergeCells(1+(i-1)*2, 1, 1+(i-1)*2+1, 1);
				}
				if(i%2 == 0){
					Label temprc = new Label(i, 2, "金额", titlethead);
					ws.addCell(temprc);
				}else{
					Label temprc = new Label(i, 2, "人次", titlethead);
					ws.addCell(temprc);
				}
				
			}
			Label hj = new Label(25, 1, "合计", titlethead);
			ws.addCell(hj);
			ws.mergeCells(25, 1, 26, 1);
			Label hjrc = new Label(25, 2, "人次", titlethead);
			ws.addCell(hjrc);
			Label hjje = new Label(26, 2, "金额", titlethead);
			ws.addCell(hjje);
		}
		 
		
		if(!dataList.isEmpty()){
			HashMap<String, String> dataMap = dao.getYrdwFfTjSum(model);
			if(StringUtils.isNull(model.getYf())){
				for (int i = 0; i < dataList.size(); i++) {
					Label labelbmmc = new Label(0, 3+i, dataList.get(i).get("bmmc"),titlebody);
					ws.addCell(labelbmmc);
					String key = "";
					for(int j=1;j<=24;j++){
						if(j%2 == 1){
							String jstr = (j+1)/2 < 10 ? "0"+(j+1)/2 : (j+1)/2+"";
							 key = yfDzMap.get(jstr);
						}else{
							String jstr = (j)/2 < 10 ? "0"+(j)/2 : (j)/2+"";
							 key = yfDzMap.get(jstr);
						}
						if(j%2 == 0){
							Label temp2= new Label(j, 3+i, dataList.get(i).get(key+"je"),titlebody);
							ws.addCell(temp2);
						}else{
							Label temp1= new Label(j, 3+i, dataList.get(i).get(key+"rc"),titlebody);
							ws.addCell(temp1);
						}
						
						
					}
					Label hjrc = new Label(25, 3+i, dataList.get(i).get("rowrc"),titlebody);
					Label hjje = new Label(26, 3+i, dataList.get(i).get("rowje"),titlebody);
					ws.addCell(hjrc);
					ws.addCell(hjje);
				}
				int num = dataList.size();
				Label labelhjmc = new Label(0, 3+num, "合计",titlebody);
				ws.addCell(labelhjmc);
				String key = "";
				for(int j=1;j<=24;j++){
					if(j%2 == 1){
						String jstr = (j+1)/2 < 10 ? "0"+(j+1)/2 : (j+1)/2+"";
						 key = yfDzMap.get(jstr);
					}else{
						String jstr = (j)/2 < 10 ? "0"+(j)/2 : (j)/2+"";
						 key = yfDzMap.get(jstr);
					}
					
					if(j%2 == 0){
						Label temp2= new Label(j, 3+num, dataMap.get(key+"je"),titlebody);
						ws.addCell(temp2);
					}else{
						Label temp1= new Label(j, 3+num, dataMap.get(key+"rc"),titlebody);
						ws.addCell(temp1);
					}
					
					
				}
				Label hjrc = new Label(25, 3+num, dataMap.get("rowrc"),titlebody);
				Label hjje = new Label(26, 3+num, dataMap.get("rowje"),titlebody);
				ws.addCell(hjrc);
				ws.addCell(hjje);
			}else{
				String key = yfDzMap.get(model.getYf());
				for (int i = 0; i < dataList.size(); i++) {
					Label labelbmmc = new Label(0, 3+i, dataList.get(i).get("bmmc"),titlebody);
					ws.addCell(labelbmmc);
					Label rcLabel = new Label(1, 3+i, dataList.get(i).get(key+"rc"), titlebody);
					Label jeLabel = new Label(2, 3+i, dataList.get(i).get(key+"je"), titlebody);
					ws.addCell(rcLabel);
					ws.addCell(jeLabel);
				}
				int num = dataList.size();
				Label labelhjmc = new Label(0, 3+num, "合计",titlebody);
				ws.addCell(labelhjmc);
				Label hjrc = new Label(1, 3+num, dataMap.get(key+"rc"), titlebody);
				Label hjje = new Label(2, 3+num, dataMap.get(key+"je"), titlebody);
				ws.addCell(hjrc);
				ws.addCell(hjje);
			}
			
		}
		
	  
	    try{
			 wwb.write();
			 wwb.close();
		 }catch(Exception e){
				
		 }
	}
	
	/**
	 * @throws Exception 
	 * @描述: 学生勤工明细统计
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-29 上午10:50:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getStudentQgDetailTjCx(TjcxForm t,User user) throws Exception{
	    return dao.getStudentQgDetailTjCx(t, user);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 学生个人勤工明细合计
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-29 上午11:27:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getStudentQgDetailTjCxSum(TjcxForm t,User user) throws Exception{
		return dao.getStudentQgDetailTjCxSum(t, user);
	}
	
	/**
	 * @描述: 个人报酬发放统计查询页面
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-1-18 上午11:02:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getStucjffgrtj(TjcxForm t,User user) throws Exception{
	    return dao.getStucjffgrtj(t, user);
	}
	
	/**
	 * @描述: 个人报酬发放统计根据高级查询条件计算报酬发放总数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-1-18 上午11:08:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getStucjffgrtjSum(TjcxForm t,User user) throws Exception{
		return dao.getStucjffgrtjSum(t, user);
	}
	
	/**
	 * @描述: 个人报酬发放统计导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-1-18 上午11:23:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> exportDataStucjffgrtj(TjcxForm t,User user) throws Exception{
	    return dao.exportDataStucjffgrtj(t, user);
	}
}
