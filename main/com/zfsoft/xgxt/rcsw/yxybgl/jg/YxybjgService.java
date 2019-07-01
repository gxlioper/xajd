/**
 * @部门:学工产品事业部
 * @日期：2016-3-24 下午05:26:45 
 */  
package com.zfsoft.xgxt.rcsw.yxybgl.jg;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-24 下午05:26:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YxybjgService extends SuperServiceImpl<YxybjgForm, YxybjgDao>{
	private YxybjgDao dao = new YxybjgDao();
	
	public boolean isHaveRecordForjg(YxybjgForm form){
		return dao.isHaveRecordForjg(form);
	}
	
	public boolean saveYxybjg(YxybjgForm model) throws Exception {
		boolean result = false;
		if ("save".equals(model.getType())) {
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	public Map<String,String> getJgxx(YxybjgForm form){
		return dao.getJgxx(form);
	}
	
	/** 
	 * @描述:获取合并导出list(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-30 下午04:58:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getHbdcList(YxybjgForm t, User user) throws Exception{
		return dao.getHbdcList(t, user);
	}
	
	/** 
	 * @描述:获取合并excel表格文件(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-30 下午04:59:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * @throws IOException
	 * @throws WriteException
	 * File 返回类型 
	 * @throws 
	 */
	public File getHbExecl(List<HashMap<String,String>> list) throws IOException, WriteException{
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
			sheet.setColumnView(0, 12);
			sheet.setColumnView(1, 12);
			sheet.setColumnView(2, 16);
			sheet.setColumnView(3, 12);
			sheet.setColumnView(4, 36);
			sheet.setColumnView(5, 36);
			sheet.setColumnView(6, 36);
			sheet.setColumnView(7, 36);
			sheet.setColumnView(8, 12);
			sheet.setColumnView(9, 16);
			Label label_title1 = new Label(0, 0,"学年",head_cf);
			Label label_title2 = new Label(1, 0,"学期",head_cf);
			Label label_title3 = new Label(2, 0,"学院",head_cf);
			Label label_title4 = new Label(3, 0,"月份",head_cf);
			Label label_title5 = new Label(4, 0,"本月工作开展情况",head_cf);
			Label label_title6 = new Label(5, 0,"学生关注热点",head_cf);
			Label label_title7 = new Label(6, 0,"学生思想动态",head_cf);
			Label label_title8 = new Label(7, 0,"学生诉求及工作建议",head_cf);
			Label label_title9 = new Label(8, 0,"填写人",head_cf);
			Label label_title10 = new Label(9, 0,"填写时间",head_cf);
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
			String xymcbz = null;//每次循环要比较的学院名称（上一条数据的学院名称，用于比较下一条数据的学院名称）
			String yfbz = null;
			StringBuilder xsgzsb = new StringBuilder();	
			int num = 1;//学院序号计数
			int mergRow=0;//学生工作热点合并列
			int yfMergRow = 0;//月份合并列
			for(int i = 0;i<list.size();i++){
				HashMap<String, String> map = list.get(i);
				if(i==0){
					xymcbz = map.get("xymc");
					yfbz = map.get("yf");
					xsgzsb.append(num+"、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));					
					mergRow+=1;
					yfMergRow+=1;
					if(i == list.size()-1){
						Label xsgzsbb = new Label(5, 1+i, map.get("xsgzrd").replaceAll("<br/>", "\r\n"), body_cf);
						sheet.addCell(xsgzsbb);
					}
				}else{
					if(!map.get("yf").equalsIgnoreCase(yfbz)){//如果月份不相等		
						xymcbz = map.get("xymc");
						sheet.mergeCells(5,mergRow,5,i);
						sheet.mergeCells(3, yfMergRow, 3, i);
						Label xsgzrd = new Label(5, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
						Label yf = new Label(3, yfMergRow, yfbz, body_cf);
						sheet.addCell(xsgzrd);
						sheet.addCell(yf);
						yfbz = map.get("yf");
						if(i != list.size()-1){//不为最后一行数据
							mergRow = i+1;
							yfMergRow = i+1;
							num=1;						
							xsgzsb = new StringBuilder();														
							xsgzsb.append(num+"、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));							
						}else{							
							Label xsgzrdk = new Label(5, i+1, "1、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"), body_cf);
							Label yff = new Label(3, i+1, map.get("yf"), body_cf);
							sheet.addCell(xsgzrdk);
							sheet.addCell(yff);
						}
					}else{//月份相等						
							num++;						
							xsgzsb.append("\r\n"+num+"、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));						
							if(i==list.size()-1){//如果为最后一行数据
								sheet.mergeCells(5,mergRow,5,i+1);
								sheet.mergeCells(3, yfMergRow, 3, i+1);
								Label xsgzrd = new Label(5, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
								Label yff = new Label(3, yfMergRow, map.get("yf"), body_cf);
								sheet.addCell(xsgzrd);
								sheet.addCell(yff);							
							}
					}
//					if(!map.get("xymc").equalsIgnoreCase(xymcbz)){
//						xymcbz = map.get("xymc");
//						sheet.mergeCells(5,mergRow,5,i);
//						Label xsgzrd = new Label(5, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);						
//						sheet.addCell(xsgzrd);						
//						if(i != list.size()-1){//不为最后一行数据
//							mergRow = i+1;
//							num=1;						
//							xsgzsb = new StringBuilder();														
//							xsgzsb.append(num+"、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));							
//						}else{							
//							Label xsgzrdk = new Label(5, i+1, "1、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"), body_cf);							
//							sheet.addCell(xsgzrdk);							
//						}
//					}else{
//						num++;						
//						xsgzsb.append("\r\n"+num+"、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));						
//						if(i==list.size()-1){//如果为最后一行数据
//							sheet.mergeCells(5,mergRow,5,i+1);														
//							Label xsgzrd = new Label(5, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);							
//							sheet.addCell(xsgzrd);							
//						}
//					}
				}
				Label xn = new Label(0, 1+i, map.get("xn"), body_cf);
				Label xqmc = new Label(1, 1+i, map.get("xqmc"), body_cf);
				Label xymc = new Label(2, 1+i, map.get("xymc"), body_cf);
				//Label yf = new Label(3, 1+i, map.get("yf"), body_cf);
				Label bygzkzqk = new Label(4, 1+i, map.get("bygzkzqk").replaceAll("<br/>", "\r\n"), body_cf);
				Label xssxdt = new Label(6, 1+i, map.get("xssxdt").replaceAll("<br/>", "\r\n"), body_cf);
				Label xstsjgzjy = new Label(7, 1+i, map.get("xstsjgzjy").replaceAll("<br/>", "\r\n"), body_cf);
				Label mz = new Label(8, 1+i, map.get("mz"), body_cf);
				Label txsj = new Label(9, 1+i, map.get("txsj"), body_cf);
				sheet.addCell(xn);
				sheet.addCell(xqmc);
				sheet.addCell(xymc);
				//sheet.addCell(yf);
				sheet.addCell(bygzkzqk);
				sheet.addCell(xssxdt);
				sheet.addCell(xstsjgzjy);
				sheet.addCell(mz);
				sheet.addCell(txsj);
			}
			wwb.write();
			wwb.close();
		}
		return file;
	}
}
