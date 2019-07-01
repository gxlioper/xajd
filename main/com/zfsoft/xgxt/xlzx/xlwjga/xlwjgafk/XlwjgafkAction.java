package com.zfsoft.xgxt.xlzx.xlwjga.xlwjgafk;


import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * 心理危机个案反馈
 */
public class XlwjgafkAction extends SuperAction {
	
	private static final String url = "xlzx_xlwjga_xlwjgafk.do";
	
	/**
	 * 心理危机个案反馈
	 */
	@SystemAuth(url = url)
	public ActionForward xlwjgafkManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjgafkForm model = (XlwjgafkForm) form;
		XlwjgafkService service = new XlwjgafkService();
		User user = getUser(request);
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_wjgabz(new String[]{ "1" });
		request.setAttribute("searchTj", searchModel);
		String path = "xlzx_xlwjga_xlwjgafk.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xlwjgafkManage");
	}
	
	/**
	 * 修改心理危机个案反馈 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问心理健康咨询-心理危机个案管理-心理危机个案反馈-反馈XH:{xh}")
	public ActionForward updateXlwjgafk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjgafkForm myForm = (XlwjgafkForm) form;
		XlwjgafkService service = new XlwjgafkService();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String xh = myForm.getXh();
		XlwjgafkForm model = service.getModelByXh(xh);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateXlwjgafk");
	}
	/**
	 * 自定义导出设置
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjgafkForm model = (XlwjgafkForm) form;
		XlwjgafkService service = new XlwjgafkService();
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
