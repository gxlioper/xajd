/**
 * @部门:学工产品(1)部
 * @日期：2018-1-31 下午04:14:16 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.zsdysj;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 证书打印
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-1-31 下午04:14:16 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZsdysjService extends SuperServiceImpl<ZsdysjForm,ZsdysjDao>{
	
	private ZsdysjDao dao = new ZsdysjDao();

	public ZsdysjService(){
		super.setDao(dao);
	}
	
	/**
	 * @描述: 表格下载
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-2 下午02:17:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @param response
	 * @param filePath
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void exportZsNew(ZsdysjForm t, User user, HttpServletResponse response, String filePath) 
		throws Exception{
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
		HSSFSheet sheet = workbook.getSheetAt(1);
		List<HashMap<String, String>> dataList = viewZsdyAll(t, user);
		
		//修改打印模板显示姓名
		HSSFSheet firstSheet = workbook.getSheetAt(0);
		//replace 学生姓名
		HSSFRow row4 = firstSheet.getRow(3);// 获得工作薄的第4行
		HSSFCell cell44 = row4.getCell(3);// 获得第4行的第4个单元格
		cell44.setCellValue(dataList.get(0).get("xm"));// 给单元格赋值
		//replace 学号
		HSSFCell cell46 = row4.getCell(5);// 获得第4行的第6个单元格
		cell46.setCellValue(dataList.get(0).get("xh"));// 给单元格赋值
		//replace 学年
		HSSFRow row5 = firstSheet.getRow(4);// 获得工作薄的第5行
		HSSFCell cell53 = row5.getCell(2);// 获得第5行的第3个单元格
		cell53.setCellValue(" 在 "+dataList.get(0).get("xn")+" 学 年 中 表 现 优 秀，现 授 予");// 给单元格赋值
		//replace 奖项
		HSSFRow row6 = firstSheet.getRow(5);// 获得工作薄的第6行
		HSSFCell cell64 = row6.getCell(3);// 获得第6行的第4个单元格
		cell64.setCellValue(dataList.get(0).get("xmmc"));// 给单元格赋值
		//replace 姓名拼音
		HSSFRow row9 = firstSheet.getRow(8);// 获得工作薄的第9行
		HSSFCell cell94 = row9.getCell(3);// 获得第9行的第4个单元格
		cell94.setCellValue(dataList.get(0).get("xmpy"));// 给单元格赋值
		//replace 奖项英文名称
		HSSFRow row10 = firstSheet.getRow(9);// 获得工作薄的第10行
		HSSFCell cell104 = row10.getCell(3);// 获得第10行的第4个单元格
		cell104.setCellValue(dataList.get(0).get("xmywmc"));// 给单元格赋值
		//replace 学年
		HSSFRow row11 = firstSheet.getRow(10);// 获得工作薄的第11行
		HSSFCell cell114 = row11.getCell(3);// 获得第11行的第4个单元格
		cell114.setCellValue("   Awarded on   "+dataList.get(0).get("xn"));// 给单元格赋值
		//replace 当前时间
		HSSFRow row19 = firstSheet.getRow(18);// 获得工作薄的第19行
		HSSFCell cell197 = row19.getCell(7);// 获得第19行的第7个单元格	
		cell197.setCellValue(DateUtils.getYear()+"/"+DateUtils.getMonth());// 给单元格赋值

		
		// 数据写入
		int row = 1;
		for (int m = 0; m < dataList.size(); m++) {
			HashMap<String, String> dataMap = dataList.get(m);
			HSSFRow hSSFRow = sheet.createRow(row);
			hSSFRow.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xm"));
			hSSFRow.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xh"));
			hSSFRow.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xmmc"));
			hSSFRow.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xmpy"));
			hSSFRow.createCell(4, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xmywmc"));
			row++;
		}
		OutputStream os = response.getOutputStream();
		workbook.write(os);  
		os.flush();
		os.close();
	}
	
	/**
	 * @描述: 证书打印（不分页）
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-2 下午02:35:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> viewZsdyAll(ZsdysjForm t, User user) throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getPageList(t, user);
	}
}
