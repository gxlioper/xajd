/**
 * @部门:学工产品事业部
 * @日期：2013-5-16 下午05:05:25 
 */  
package com.zfsoft.xgxt.pjpy.tjcx;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.xgxt.base.action.SuperAction;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优 统计查询 学院获奖统计查询 
 * @作者： zhangjw 
 * @时间： 2013-5-16 下午05:09:00 
 * @版本： V5.8.16
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyhjtjAction   extends SuperAction {
	private XyhjtjService service = new XyhjtjService();
	/**
	 * @描述:学生获奖金额统计  到出功能
	 * @作者：zhangjw
	 * @日期：2013-5-17 上午10:52:15
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
	public ActionForward getGyjltj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		List<List<String>> gylist = service.shxjxyCxdc(user);
		response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		WritableCellFormat tempCellFormat = service.getTopCellStyle(ws);
		Label label = new Label(0,0,"学生获奖金额统计",tempCellFormat);
		ws.addCell(label);
		ws.mergeCells(0, 0, gylist.get(0).size()-1, 0);
		try {
			for (int i = 1; i < gylist.size()+1; i++) {
				List<String> list = gylist.get(i-1);
				for (int j = 0; j < list.size(); j++) {
					if(i==1){
						ws.setColumnView(j, 18);
						ws.addCell(new Label(j,i,list.get(j),tempCellFormat));
					}else{
						ws.addCell(new Label(j,i,list.get(j)));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			wwb.write();
			wwb.close();
		}
		return null;
	}
	
	
}
