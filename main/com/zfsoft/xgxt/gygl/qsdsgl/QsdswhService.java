/**
 * @部门:学工产品事业部
 * @日期：2014-3-19 上午09:41:56 
 */  
package com.zfsoft.xgxt.gygl.qsdsgl;

import java.io.OutputStream;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;


import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.gygl.qsdsgl.qsdskh.QsdskhForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理
 * @类功能描述: 寝室导师维护
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-19 上午09:41:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QsdswhService extends SuperServiceImpl<QsdswhForm, QsdswhDao> {

	public QsdswhService(){
		setDao(new QsdswhDao());
	}
	/** 
	 * 查询寝室信息表头
	 */
	public String[] getQsxxTopTr(){
		String[] topTr = new String[]{"楼栋名称","寝室号","床位号","床位性别","学号","姓名","专业名称","班级"};
		return topTr;
	}
	
	/** 
	 * 查询寝室信息表头(温州大学)
	 */
	public String[] getQsxxTopTrForWzdx(){
		String[] topTr = new String[]{"学号","姓名","学院","专业","班级","床位号"};
		return topTr;
	}
	
	/** 
	 * 查询寝室信息结果
	 */
	public List<String[]> getQsxxList(String lddm, String qsh) throws Exception{
		return dao.getQsxxList(lddm, qsh);
	}
	
	public List<HashMap<String, String>> getxsxx(String lddm, String qsh) throws Exception{
		return dao.getxsxx(lddm, qsh);
	}

	/**
	 * 
	 * 获取寝室导师信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-21 上午09:39:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getQsdsxx(QsdswhForm model){
		
		return dao.getQsdsxx(model);
	}
	
	/**
	 * 保存更新新增寝室导师信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-21 上午10:00:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveQsdsxx(QsdswhForm model) throws Exception{
		return dao.saveQsdsxx(model);
	}
	/**
	 * @描述: 在增加中判定是否存在同样记录！
	 * @作者：孟威[工号：1186]
	 * @日期：2015-11-11 上午09:33:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistLddm(QsdswhForm form) 
	throws Exception {
			String num = dao.checkExistForSave(form);
			return Integer.valueOf(num) > 0;
	}
	/**
	 * 
	 * 删除信息批量
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-21 上午11:55:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ldxx
	 * @param qsh
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int[] deleteDsdsxxPl(List<String[]> pks) throws Exception{
		return dao.deleteDsdsxxPl(pks);
	}
	
	/**
	 * */
	public List<HashMap<String,String>> getQsdsxxListByXh(String xh){
		return dao.getQsdsxxListByXh(xh);
	}
	
	/**
	 * @描述 导师考核导出
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param os
	 * void 返回类型 
	 * @throws
	 */
	public void exportDskh(QsdswhForm model, User user, OutputStream os) throws Exception{
		// 宋体,12,CENTRE
		WritableCellFormat s12CentreFormat = new WritableCellFormat();
		WritableFont s12CentreFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12CentreFormat.setFont(s12CentreFont);
		s12CentreFormat.setAlignment(Alignment.CENTRE);
		// 住宿生信息
		List<HashMap<String,String>> stuList = dao.exportDskh(model, user);
		// 最大床位数
		int maxCws = 0;
		for (HashMap<String, String> stuMap : stuList) {
			maxCws = Math.max(maxCws, Integer.parseInt(stuMap.get("cws")));
		}
		int mergeCols = maxCws + 4;
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("导师考核", 0);
		// 设置【寝室长联系方式】列宽度
		ws.setColumnView(mergeCols + 1, 18); 
		// 设置【班级】列宽度
		ws.setColumnView(mergeCols + 2, 18); 
		// 设置【学院】列宽度
		ws.setColumnView(mergeCols + 3, 18); 
		// 设置【寝室导师】列宽度
		ws.setColumnView(mergeCols + 4, 13); 
		// 设置【导师联系方式】列宽度
		ws.setColumnView(mergeCols + 5, 18); 
		// 设置【导师所在单位】列宽度
		ws.setColumnView(mergeCols + 6, 18); 
		// 设置【学年】列宽度
		ws.setColumnView(mergeCols + 8, 11); 
		// 第1行 楷体,12,CENTRE
		WritableCellFormat k12WHITECenterFormat = new WritableCellFormat();
		WritableFont k12WHITECenterFont = new WritableFont(WritableFont.createFont("楷体"),12);
		k12WHITECenterFont.setColour(Colour.WHITE);
		k12WHITECenterFont.setBoldStyle(WritableFont.BOLD);
		k12WHITECenterFormat.setFont(k12WHITECenterFont);
		k12WHITECenterFormat.setAlignment(Alignment.CENTRE);
		k12WHITECenterFormat.setBackground(Colour.GREEN);
		ws.addCell(new Label(0, 0, "序号", k12WHITECenterFormat));
		ws.addCell(new Label(1, 0, "学区", k12WHITECenterFormat));
		ws.addCell(new Label(2, 0, "楼栋", k12WHITECenterFormat));
		ws.addCell(new Label(3, 0, "寝室号", k12WHITECenterFormat));
		ws.addCell(new Label(4, 0, "寝室成员", k12WHITECenterFormat));
		ws.mergeCells(4, 0, (mergeCols - 1), 0);
		ws.addCell(new Label(mergeCols, 0, "寝室长", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 1, 0, "寝室长联系方式", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 2, 0, "班级", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 3, 0, "学院", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 4, 0, "寝室导师", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 5, 0, "导师联系方式", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 6, 0, "导师所在单位", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 7, 0, "年度", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 8, 0, "学年", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 9, 0, "学期", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 10, 0, "考核成绩", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 11, 0, "任期开始时间", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 12, 0, "任期结束时间", k12WHITECenterFormat));
		String temp = "";
		int row = 0;
		int qsxsCol = 4; // 寝室学生起始列
		// 第2行开始
		for (int m = 0; m < stuList.size(); m++) {
			HashMap<String,String> stuMap = stuList.get(m);
			String pk = stuMap.get("lddm") + "@@" + stuMap.get("qsh");
			if(!temp.equals(pk)){
				row++;
				qsxsCol = 4; // 重置 寝室学生起始列
				ws.addCell(new Label(0, row, String.valueOf(row), s12CentreFormat));
				ws.addCell(new Label(1, row, stuMap.get("yqmc"), s12CentreFormat));
				ws.addCell(new Label(2, row, stuMap.get("ldmc"), s12CentreFormat));
				ws.addCell(new Label(3, row, stuMap.get("qsh"), s12CentreFormat));
				
				ws.addCell(new Label(mergeCols, row, stuMap.get("qszxm"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 1, row, stuMap.get("qszlxdh"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 2, row, stuMap.get("qszbjmc"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 3, row, stuMap.get("qszxymc"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 4, row, stuMap.get("dsxm"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 5, row, stuMap.get("lxdh"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 6, row, stuMap.get("dw"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 7, row, stuMap.get("nd"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 8, row, stuMap.get("xn"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 9, row, stuMap.get("xqmc"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 10, row, stuMap.get("cj"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 11, row, stuMap.get("rqkssj"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 12, row, stuMap.get("rqjssj"), s12CentreFormat));
			}
			if(m == 0){
				temp = pk;
			}
			if(temp.equals(pk)){
				ws.addCell(new Label(qsxsCol, row, stuMap.get("qxxsxm"), s12CentreFormat));
				qsxsCol++;
			}
			temp = pk;
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
	}
}
