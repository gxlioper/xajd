/**
 * @部门: 学工产品(1)部
 * @日期： 2018-7-24 下午04:09:45 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.jxjfp;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新评奖评优管理模块
 * @类功能描述: 奖学金名额分配一览表
 * @作者： MengWei[工号:1186]
 * @时间： 2018-7-24 下午04:08:47 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JxjfpService extends SuperServiceImpl<JxjfpForm,JxjfpDao>{
	
	private JxjfpDao dao = new JxjfpDao();
	
	public JxjfpService(){
		super.setDao(dao);
	}
	
	/**
	 * @描述: 需统计项目
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-24 下午05:40:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPjxmList(JxjfpForm model){
		return dao.getPjxmList(model);
	}
	
	/**
	 * @描述: 奖学金名额分配一览表查询列表
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-24 下午07:42:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxjmefpList(JxjfpForm model, User user)throws Exception{
		/*查询需要统计的评奖项目*/
		List<HashMap<String, String>> pjxmList = getPjxmList(model);
		return dao.getJxjmefpList(model,user,pjxmList);
	}
	
	/**
	 * @描述: 奖学金分配一览表数据导出
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-25 下午03:22:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public File getJxjfpFile(JxjfpForm model, User user) throws Exception{
		
		/*需统计项目*/
		List<HashMap<String, String>> pjxmList = getPjxmList(model);
		/*构建导出表头*/
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("bmmc", "部门名称");
		
		for (int i = 0 , j = pjxmList.size() ; i < j ; i++){
			map.put("jx"+i, pjxmList.get(i).get("xmmc"));
		}
		
		map.put("jje", "奖金额");
		map.put("bmtzje", "调整金额");
		map.put("zrs", "学生总数");
		
		/*不分页*/
		model.getPages().setPageSize(Integer.MAX_VALUE);
		/*数据输出*/
		List<HashMap<String,String>> dataList = dao.getJxjmefpList(model, user, pjxmList);
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}
	
	/**
	 * @描述: 发放汇总导出(就高原则)
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-25 下午05:47:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public File ffhzExport(JxjfpForm model, User user) throws Exception{
		
		//执行浙大存储过程
		dao.computeFfhz(model.getXn(), Base.currXn);
		
		//构建导出表头
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("xymc", "院系");
		map.put("xh", "学号");
		map.put("xm", "姓名");
		map.put("bcffje", "本次发放金额");
		map.put("jjze", "总金额");
		map.put("bz", "备注");
		
		//导出数据
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> dataList = dao.getFfhzList(model, user);
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}
	
	/**
	 * @描述: 国家奖学金汇总导出
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-11 下午03:04:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void exportGjjxjhz(JxjfpForm model,OutputStream os,User user)throws Exception {
		// 设置标题
		StringBuffer title = new StringBuffer();
		title.append(model.getXn());
		title.append("学年国家奖学金发放汇总");
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("汇总表", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		List<HashMap<String, String>> gjjxjList = dao.getGjjxjList(model);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.NONE);
		ws.mergeCells(0, 0, 3, 1);
		ws.addCell(new Label(0, 2, "序号", wcf2));
		ws.addCell(new Label(1, 2, "学号", wcf2));
		ws.addCell(new Label(2, 2, "姓名", wcf2));
		ws.addCell(new Label(3, 2, "发放金额(元)", wcf2));
		if (gjjxjList != null && gjjxjList.size() > 0) {

			for (int i = 0; i < gjjxjList.size(); i++) {
				HashMap<String, String> map = gjjxjList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 30);
				ws.setColumnView(2, 30);
				ws.setColumnView(3, 30);

				ws.addCell(new Label(0, 3 + i, i+1+"", wcf2));// 序号
				ws.addCell(new Label(1, 3 + i, map.get("xh"), wcf2));
				ws.addCell(new Label(2, 3 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("xmje"), wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
