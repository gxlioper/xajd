package xsgzgl.pjpy.general.tjcx.shmddc;

import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.utils.ExcelMethods;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.tjcx.TjcxShmddcInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_审核名单导出_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 程强
 * @version 1.0
 */

public class TjcxShmddcService extends CommService implements
		TjcxShmddcInterface {

	TjcxShmddcDAO dao = new TjcxShmddcDAO();

	/**
	 * 导出获奖名单汇总
	 * 
	 * @author 程强
	 */
	public void expShmddc(TjcxShmddcModel model, OutputStream ops)
			throws Exception {
		
		//年级
		String arr_nj[] = model.getNj();
		//学院代码
		String arr_xydm[] = model.getXydm();
		//专业代码
		String arr_zydm[] = model.getZydm();
		//班级代码
		String arr_bjdm[] = model.getBjdm();
		//审核状态
		String arr_shzt[] = model.getShzt();
		//项目代码
		String xmdm = model.getXmdm();
		//审批岗位
		String spgw = model.getSpgw();
		//模糊查询条件值
		String mhcxz = model.getMhcxz();
		//模糊查询类型
		String mhcxlx = model.getMhcxlx();
		
		String xmmc = dao.getXmmc(xmdm);
		
		Label titleCell  =  new Label(0, 0, Base.xxmc+ xmmc+"推荐名单");
		WritableWorkbook wwb = Workbook.createWorkbook(ops);
		WritableSheet ws = wwb.createSheet("导出结果", 0);
		
		//创建第一行
		WritableCellFormat wcFormat = new WritableCellFormat();
		WritableFont font = new WritableFont(WritableFont.ARIAL, 15);
		font.setBoldStyle(WritableFont.BOLD);
		wcFormat.setFont(font);
		wcFormat.setAlignment(Alignment.CENTRE);
		wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		titleCell.setCellFormat(wcFormat);
		ws.mergeCells(0, 0, 25, 1);
		ws.addCell(titleCell);
		List<String[]>  list = dao.getShmddcList(model,arr_nj,arr_xydm,arr_zydm,arr_bjdm,arr_shzt,xmdm,spgw,mhcxz,mhcxlx);
		String[] sT = {"学院名称","班级名称","学号","姓名","性别","生源地","政治面貌","身份证号","名次","综测总分","曾获奖情况(已通过系统评选)","学年其他奖项(学生填写)",
				"等级考试成绩","申请项目名称","有无处分","能否按时修完教学全部计划","毕业设计(论文)进展情况及预期结果","申请理由","班主任审核状态","班主任审核意见","班主任审核人", 
				"学院学生工作办公室审核状态","学院学生工作办公室审核意见","学院学生工作办公室审核人","学生处审核状态","学生处审核意见","学生处审核人"};
		for (int i = 0; i < sT.length; i++) {
			titleCell = new Label(i, 2, sT[i]);
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL, 10);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
		}
		if(null!=list&&list.size()>0){
		int n = 1;
		for (String[] rs : list) {
			for (int i = 0; i < sT.length; i++) {
				titleCell = new Label(i, n+2, rs[i]);
				wcFormat = new WritableCellFormat();
				font = new WritableFont(WritableFont.ARIAL, 10);
				wcFormat.setFont(font);
				wcFormat.setAlignment(Alignment.CENTRE);
				wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				titleCell.setCellFormat(wcFormat);
				ws.addCell(titleCell);
				}
				n++;
			}
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
		
	}

	public void expShmddc(PjpyGeneralForm myForm, TjcxShmddcModel model,
			OutputStream os) throws Exception {
		// TODO 自动生成方法存根
		
	}
}