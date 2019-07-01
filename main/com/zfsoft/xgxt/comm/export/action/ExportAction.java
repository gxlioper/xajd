package com.zfsoft.xgxt.comm.export.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * 自定义导出
 * 
 */
public class ExportAction extends SuperAction {

	private static final long serialVersionUID = 1L;
	private String messageKey;
	private IExportService exportService = new ExportExcelImpl();

	public ActionForward exportConfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportModel model = (ExportModel) form;
		User user = getUser(request);
		model.setZgh(user.getUserName());
		// 查询导出列
		List<HashMap<String, String>> configList = exportService
				.getConfigList(model);

		request.setAttribute("configList", configList);
		request.setAttribute("dcclbh", request.getParameter("dcclbh"));
		return mapping.findForward("exportConfig");
	}

	public ActionForward saveCustomConfig(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExportModel model = (ExportModel) form;
		User user = getUser(request);
		model.setZgh(user.getUserName());
		boolean isSuccess = exportService.saveExportConfig(model);
		messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey, isSuccess));
		return null;
	}
}