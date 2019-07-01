/**
 * @部门:学工产品事业部
 * @日期：2014-9-10 上午08:41:48 
 */  
package com.zfsoft.xgxt.base.log;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.comm.CommList;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.zjly.ylbx.YlbxForm;

/** 
 * @类功能描述: 操作日志
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-9-10 上午08:41:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LogAction extends SuperAction<LogInfo, LogService> {

	
	/**
	 * 
	 * @描述:日志查询
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-10 上午08:45:10
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
	public ActionForward logList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LogInfo myform = (LogInfo) form;
		CommForm model = new CommForm();
		RequestForm rForm = new RequestForm();
		CommList commList = new CommList();
		rForm.setGnmk("xtwh");
		rForm.setMenu("qxgl");
		commList.setList(model, rForm, request);
		request.setAttribute("path", "log.do?method=logList");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("logList");
	}
	
	
	/**
	 * 
	 * @描述:AJAX加载日志列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-10 上午08:45:20
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
	public ActionForward getLogList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LogInfo myform = (LogInfo) form;
		LogService service = getService();
		
		List<HashMap<String,String>> resultList = service.getPageList(myform);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LogInfo myform = (LogInfo) form;
		LogService service = getService();
		List<HashMap<String,String>> resultList = service.getAllList(myform);
		
		
		
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myform.getExportModel();
		exportModel.setZgh(getUser(request).getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh("log.do");// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
