/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-10 ����08:41:48 
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
 * @�๦������: ������־
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-9-10 ����08:41:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LogAction extends SuperAction<LogInfo, LogService> {

	
	/**
	 * 
	 * @����:��־��ѯ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-10 ����08:45:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
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
	 * @����:AJAX������־�б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-10 ����08:45:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
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
		
		
		
		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myform.getExportModel();
		exportModel.setZgh(getUser(request).getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh("log.do");// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
