/**
 * @部门:学工产品事业部
 * @日期：2013-4-27 下午04:09:18 
 */  
package com.zfsoft.xgxt.gygl.gyjl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xsgzgl.gygl.gyjlxxglnew.GyjlxxglNewForm;

import com.zfsoft.xgxt.base.action.SuperAction;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理模块
 * @类功能描述: 公寓纪律信息Action
 * @作者： zhangjw
 * @时间： 2013-4-27 下午04:09:18 
 * @版本： V5.9.17
 * @修改记录:   
 */

public class GyjltjAction  extends SuperAction {
	
	private GyjltjService service = new GyjltjService();
	
	/**
	 * @描述:公寓纪律 上海戏剧学院 纪律汇总导出
	 * @作者：zhangjw
	 * @日期：2013-5-27 下午03:48:56
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
		GyjltjForm mm  = (GyjltjForm)form;
		List<List<String>> gylist = service.getGyjltj(user, mm.getSearchModel());
		response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
		response.setContentType("application/vnd.ms-excel");
		
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		try {
			for (int i = 0; i < gylist.size(); i++) {
				List<String> list = gylist.get(i);
				for (int j = 0; j < list.size(); j++) {
					ws.addCell(new Label(j,i,list.get(j)));
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
