/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:01:26 
 */
package com.zfsoft.xgxt.xsxx.xsxxcj;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(学生信息采集--天津职业大学)
 * @作者： cmj [工号：913]
 * @时间： 2013-7-30 下午04:01:26
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XsxxcjService extends SuperServiceImpl<XsxxcjForm, XsxxcjDao> {
	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	private XsxxcjDao dao = new XsxxcjDao();

	public XsxxcjService() {
		super.setDao(dao);
	}

	/** 
	 * @描述:TODO(获取学生列表)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-1 下午05:55:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsPageList(XsxxcjForm model,
			User user) throws Exception{
		// TODO 自动生成方法存根
		return dao.getXsPageList(model,user);
	}

	/** 
	 * @描述:TODO(查询学生基本信息统计“台账”)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-2 上午10:35:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<String[]> getXsjbxxtzList(XsxxcjForm model,
			User user) throws Exception{
		// TODO 自动生成方法存根
		return dao.getXsjbxxtzList(model,user);
	}

	/**
	 * @param title2 
	 * @param file  
	 * @描述:TODO(导出学生基本信息统计“台账”)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-2 上午11:22:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param response
	 * @param resultList
	 * void 返回类型 
	 * @throws 
	 */
	public File exportXsjbxxtz(HttpServletResponse response,
			List<String[]> resultList, File file, String[] title) throws Exception{
		// TODO 自动生成方法存根
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		response.reset();
		FileOutputStream stream = new FileOutputStream(file);
		WritableWorkbook wwb = Workbook.createWorkbook(stream);
		WritableSheet sheet = wwb.createSheet("导出数据", 0);
		sheet.mergeCells(0, 0, 0, 1);
		sheet.addCell(new Label(0,0,Base.YXPZXY_KEY));
		sheet.mergeCells(1, 0, 1, 1);
		sheet.addCell(new Label(1,0,"年级"));
		for(int i=0;i<title.length;i++){
			sheet.mergeCells(2+3*i, 0, 4+3*i, 0);
			sheet.addCell(new Label(2+3*i,0,title[i]));
		}
		for(int i=0;i<3*title.length;i++){
			sheet.addCell(new Label(2+i,1,"男"));
			sheet.addCell(new Label(3+i,1,"女"));
			sheet.addCell(new Label(4+i,1,"总数"));
			i+=2;
		}
		for(int i=0;i<resultList.size();i++){
			for(int j=0;j<resultList.get(i).length;j++){
				sheet.addCell(new Label(j,i+2,resultList.get(i)[j]));
			}
		}
		wwb.write();
		wwb.close();
		return file;
		
	}

	/** 
	 * @描述:TODO(查询学生困难信息统计“台账”)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-2 下午02:04:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<String[]> 返回类型 
	 * @throws 
	 */
	public List<String[]> exportXsknxxtz(XsxxcjForm model, User user) throws Exception{
		// TODO 自动生成方法存根
		return dao.exportXsknxxtz(model,user);
	}
}
