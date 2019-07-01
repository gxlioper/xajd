/**
 * @部门:学工产品事业部
 * @日期：2016-3-31 下午04:41:49 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybjg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
 * @模块名称: 北京中医药_学情月报管理模块
 * @类功能描述: TODO(班级学情月报管理-班级学情结果) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-31 下午04:41:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjxqybjgService extends
		SuperServiceImpl<BjxqybjgForm, BjxqybjgDao> {
	
	private BjxqybjgDao dao = new  BjxqybjgDao();
	public static String _BCZSCID="-1";
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级学情结果-审核通过，先删除旧数据)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-6 上午10:01:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteExist(BjxqybjgForm model) throws Exception {
		
		return dao.deleteExist(model);
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级学情结果-保存班级学情月报结果)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-6 上午10:01:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveBjxqybjg(BjxqybjgForm model,User user) throws Exception {		
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级学情结果-判断该班学情月报结果是否已经存在)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 下午01:52:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByBjxqybjg(BjxqybjgForm model,String type)
	throws Exception {
		boolean flag = false;
		
		if("save".equalsIgnoreCase(type)) {
			String num = dao.checkExistForBjxqybjgSave(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}else{
			String num = dao.checkExistForBjxqybjgUpdate(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}	
		return flag;
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级学情结果-修改班级学情月报结果)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-11 上午08:32:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateBjxqybjg(BjxqybjgForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级学情结果-单个学情月报结果信息)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-11 上午08:49:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getBjxqybjgInfo(BjxqybjgForm model){	
		return dao.getBjxqybjgInfo(model);
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级学情结果-删除班级学情月报结果 )
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-11 上午10:14:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteBjxqybjg(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getBjxqybjg(str);
				noDel.append(hm.get("bjdm"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("bjmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?bjxqybjgDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级学情结果-删除班级学情月报结果)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-11 上午10:14:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int bjxqybjgDelete(String[] ids) throws Exception {
		return runDelete(ids);
	}
	
	/**
	 * @throws WriteException 
	 * @throws IOException  
	 * @描述:获取合并excel表格文件(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-29 下午05:58:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
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
			sheet.setColumnView(2, 8);
			sheet.setColumnView(3, 16);
			sheet.setColumnView(4, 16);
			sheet.setColumnView(5, 16);
			sheet.setColumnView(6, 12);
			sheet.setColumnView(7, 36);
			sheet.setColumnView(8, 36);
			sheet.setColumnView(9, 36);
			sheet.setColumnView(10, 36);
			sheet.setColumnView(11, 20);
			sheet.setColumnView(12, 8);
			Label label_title1 = new Label(0, 0,"学年",head_cf);
			Label label_title2 = new Label(1, 0,"学期",head_cf);
			Label label_title3 = new Label(2, 0,"年级",head_cf);
			Label label_title4 = new Label(3, 0,"学院",head_cf);
			Label label_title5 = new Label(4, 0,"专业",head_cf);
			Label label_title6 = new Label(5, 0,"班级",head_cf);
			Label label_title7 = new Label(6, 0,"月份",head_cf);
			Label label_title8 = new Label(7, 0,"本月工作开展情况",head_cf);
			Label label_title9 = new Label(8, 0,"学生关注热点",head_cf);
			Label label_title10 = new Label(9, 0,"学生思想动态",head_cf);
			Label label_title11 = new Label(10, 0,"学生诉求及工作建议",head_cf);
			Label label_title12 = new Label(11, 0,"填写时间",head_cf);
			Label label_title13 = new Label(12, 0,"填写人",head_cf);
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
			String xymcbz = null;//每次循环要比较的学院名称（上一条数据的学院名称，用于比较下一条数据的学院名称）
			String yfbz = null;
			StringBuilder bysb = new StringBuilder();
			StringBuilder xsgzsb = new StringBuilder();
			StringBuilder xssxsb = new StringBuilder();
			int num = 1;//班级序号计数
			int mergRow=0;
			int yfMergRow = 0;
			for(int i = 0;i<list.size();i++){
				HashMap<String, String> map = list.get(i);
				if(i==0){
					xymcbz = map.get("xymc");
					yfbz = map.get("ny");
					bysb.append(num+"、"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"));
					xsgzsb.append(num+"、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));
					xssxsb.append(num+"、"+map.get("xssxdt").replaceAll("<br/>", "\r\n"));
					mergRow+=1;
					yfMergRow+=1;
				}else{
					if(!map.get("ny").equalsIgnoreCase(yfbz)){//月份不相等的情况下
						xymcbz = map.get("xymc");
						sheet.mergeCells(6, yfMergRow, 6, i);
						sheet.mergeCells(7,mergRow,7,i);
						sheet.mergeCells(8,mergRow,8,i);
						sheet.mergeCells(9,mergRow,9,i);
						Label ny = new Label(6, yfMergRow, yfbz, body_cf);
						Label bygzkzqk = new Label(7,mergRow,bysb.toString().replaceAll("<br/>", "\r\n"),body_cf);
						Label xsgzrd = new Label(8, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
						Label xssxdt = new Label(9, mergRow, xssxsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
						sheet.addCell(bygzkzqk);
						sheet.addCell(xsgzrd);
						sheet.addCell(xssxdt);
						sheet.addCell(ny);
						yfbz = map.get("ny");
						if(i != list.size()-1){//不为最后一行数据
							mergRow = i+1;//设置从第几行开始合并
							yfMergRow = i+1;
							num=1;
							bysb = new StringBuilder();
							xsgzsb = new StringBuilder();
							xssxsb = new StringBuilder();
							bysb.append(num+"、"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"));
							xsgzsb.append(num+"、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));
							xssxsb.append(num+"、"+map.get("xssxdt").replaceAll("<br/>", "\r\n"));
						}else{
							Label nyy = new Label(6, i+1, map.get("ny"), body_cf);
							Label bygzkzqkk = new Label(7,i+1,"1、"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"),body_cf);
							Label xsgzrdk = new Label(8, i+1, "1、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"), body_cf);
							Label xssxdtk = new Label(9, i+1, "1、"+map.get("xssxdt").replaceAll("<br/>", "\r\n"), body_cf);
							sheet.addCell(bygzkzqkk);
							sheet.addCell(xsgzrdk);
							sheet.addCell(xssxdtk);
							sheet.addCell(nyy);
						}
						
					}else{//月份相等的情况下
						if(!map.get("xymc").equalsIgnoreCase(xymcbz)){//学院名称不相等下的情况下，先合并以上的各个单元格，并填充数据
							xymcbz = map.get("xymc");
							sheet.mergeCells(7,mergRow,7,i);
							sheet.mergeCells(8,mergRow,8,i);
							sheet.mergeCells(9,mergRow,9,i);
							sheet.mergeCells(6, yfMergRow, 6, i);
							Label bygzkzqk = new Label(7,mergRow,bysb.toString().replaceAll("<br/>", "\r\n"),body_cf);
							Label xsgzrd = new Label(8, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
							Label xssxdt = new Label(9, mergRow, xssxsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
							Label yf = new Label(6,yfMergRow,yfbz,body_cf);
							sheet.addCell(bygzkzqk);
							sheet.addCell(xsgzrd);
							sheet.addCell(xssxdt);
							sheet.addCell(yf);
							if(i != list.size()-1){//不为最后一行数据
								mergRow = i+1;//设置从第几行开始合并
								yfMergRow = i+1;//设置月份从第几行开始合并
								num=1;
								bysb = new StringBuilder();
								xsgzsb = new StringBuilder();
								xssxsb = new StringBuilder();
								bysb.append(num+"、"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"));
								xsgzsb.append(num+"、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));
								xssxsb.append(num+"、"+map.get("xssxdt").replaceAll("<br/>", "\r\n"));
							}else{
								Label bygzkzqkk = new Label(7,i+1,"1、"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"),body_cf);
								Label xsgzrdk = new Label(8, i+1, "1、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"), body_cf);
								Label xssxdtk = new Label(9, i+1, "1、"+map.get("xssxdt").replaceAll("<br/>", "\r\n"), body_cf);
								Label nyy = new Label(6,i+1,map.get("ny"),body_cf);
								sheet.addCell(bygzkzqkk);
								sheet.addCell(xsgzrdk);
								sheet.addCell(xssxdtk);
								sheet.addCell(nyy);
							}
						}else{//学院名称相等情况下
							num++;
							bysb.append("\r\n"+num+"、"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"));
							xsgzsb.append("\r\n"+num+"、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));
							xssxsb.append("\r\n"+num+"、"+map.get("xssxdt").replaceAll("<br/>", "\r\n"));
							if(i==list.size()-1){//如果为最后一行数据
								sheet.mergeCells(6,yfMergRow,6,i+1);
								sheet.mergeCells(7,mergRow,7,i+1);
								sheet.mergeCells(8,mergRow,8,i+1);
								sheet.mergeCells(9,mergRow,9,i+1);
								Label bygzkzqk = new Label(7,mergRow,bysb.toString().replaceAll("<br/>", "\r\n"),body_cf);
								Label xsgzrd = new Label(8, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
								Label xssxdt = new Label(9, mergRow, xssxsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
								Label nyy = new Label(6,yfMergRow,map.get("ny"),body_cf);
								sheet.addCell(bygzkzqk);
								sheet.addCell(xsgzrd);
								sheet.addCell(xssxdt);
								sheet.addCell(nyy);
							}
						}
					}
//					if(!map.get("xymc").equalsIgnoreCase(xymcbz)){//学院名称不相等下的情况下，先合并以上的各个单元格，并填充数据
//						xymcbz = map.get("xymc");
//						sheet.mergeCells(7,mergRow,7,i);
//						sheet.mergeCells(8,mergRow,8,i);
//						sheet.mergeCells(9,mergRow,9,i);
//						Label bygzkzqk = new Label(7,mergRow,bysb.toString().replaceAll("<br/>", "\r\n"),body_cf);
//						Label xsgzrd = new Label(8, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
//						Label xssxdt = new Label(9, mergRow, xssxsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
//						sheet.addCell(bygzkzqk);
//						sheet.addCell(xsgzrd);
//						sheet.addCell(xssxdt);
//						if(i != list.size()-1){//不为最后一行数据
//							mergRow = i+1;//设置从第几行开始合并
//							num=1;
//							bysb = new StringBuilder();
//							xsgzsb = new StringBuilder();
//							xssxsb = new StringBuilder();
//							bysb.append(num+"、"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"));
//							xsgzsb.append(num+"、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));
//							xssxsb.append(num+"、"+map.get("xssxdt").replaceAll("<br/>", "\r\n"));
//						}else{
//							Label bygzkzqkk = new Label(7,i+1,"1、"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"),body_cf);
//							Label xsgzrdk = new Label(8, i+1, "1、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"), body_cf);
//							Label xssxdtk = new Label(9, i+1, "1、"+map.get("xssxdt").replaceAll("<br/>", "\r\n"), body_cf);
//							sheet.addCell(bygzkzqkk);
//							sheet.addCell(xsgzrdk);
//							sheet.addCell(xssxdtk);
//						}
//					}else{//学院名称相等情况下
//						num++;
//						bysb.append("\r\n"+num+"、"+map.get("bygzkzqk").replaceAll("<br/>", "\r\n"));
//						xsgzsb.append("\r\n"+num+"、"+map.get("xsgzrd").replaceAll("<br/>", "\r\n"));
//						xssxsb.append("\r\n"+num+"、"+map.get("xssxdt").replaceAll("<br/>", "\r\n"));
//						if(i==list.size()-1){//如果为最后一行数据
//							sheet.mergeCells(7,mergRow,7,i+1);
//							sheet.mergeCells(8,mergRow,8,i+1);
//							sheet.mergeCells(9,mergRow,9,i+1);
//							Label bygzkzqk = new Label(7,mergRow,bysb.toString().replaceAll("<br/>", "\r\n"),body_cf);
//							Label xsgzrd = new Label(8, mergRow, xsgzsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
//							Label xssxdt = new Label(9, mergRow, xssxsb.toString().replaceAll("<br/>", "\r\n"), body_cf);
//							sheet.addCell(bygzkzqk);
//							sheet.addCell(xsgzrd);
//							sheet.addCell(xssxdt);
//						}
//					}
				}
				Label xn = new Label(0, 1+i, map.get("xn"), body_cf);
				Label xqmc = new Label(1, 1+i, map.get("xqmc"), body_cf);
				Label nj = new Label(2, 1+i, map.get("nj"), body_cf);
				Label xymc = new Label(3, 1+i, map.get("xymc"), body_cf);
				Label zymc = new Label(4, 1+i, map.get("zymc"), body_cf);
				Label bjmc = new Label(5, 1+i, map.get("bjmc"), body_cf);
				//Label ny = new Label(6, 1+i, map.get("ny"), body_cf);
				Label xstsjgzjy = new Label(10, 1+i, map.get("xstsjgzjy").replaceAll("<br/>", "\r\n"), body_cf);
				Label txsj = new Label(11, 1+i, map.get("txsj"), body_cf);
				Label lrrxm = new Label(12, 1+i, map.get("lrrxm"), body_cf);
				sheet.addCell(xn);
				sheet.addCell(xqmc);
				sheet.addCell(nj);
				sheet.addCell(xymc);
				sheet.addCell(zymc);
				sheet.addCell(bjmc);
				//sheet.addCell(ny);
				sheet.addCell(xstsjgzjy);
				sheet.addCell(txsj);
				sheet.addCell(lrrxm);
			}
			wwb.write();
			wwb.close();
		}
		return file;
	}
	
	/** 
	 * @描述:获取合并导出list(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-30 上午09:36:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getHbdcList(BjxqybjgForm t, User user) throws Exception{
		return dao.getHbdcList(t, user);
	}
}
