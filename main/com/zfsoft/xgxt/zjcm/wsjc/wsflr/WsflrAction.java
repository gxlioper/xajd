/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����01:55:49 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsflr;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.IPRequest;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.zjcm.wsjc.wsftj.WsftjForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-8 ����01:55:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsflrAction extends SuperAction<WsflrForm, WsflrService>{
	private WsflrService service = new WsflrService();
	private static final String url = "cjWsf_wsflr.do";
	
	/** 
	 * @����:��ѯ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-8 ����01:59:37
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
	@SystemAuth(url = url)
	public ActionForward getWsflrList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsflrForm model = (WsflrForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "cjWsf_wsflr.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getWsflrList");
	}
	
	
	/** 
	 * @����:������¼��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-9 ����08:57:13
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addWsflr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsflrForm model = (WsflrForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			 User user = getUser(request);
			 // ��ѯ
				List<HashMap<String, String>> resultList = service.getPageListForLr(
						model, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
		}	   
		String path = "cjWsflr.do?method=addWsflr";
		request.setAttribute("path", path);
		request.setAttribute("ccny", model.getCcny());
		//������Ϣ����
		return mapping.findForward("addWsflrList");
	}
	
	/** 
	 * @����:�������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-8 ����05:41:25
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveWsf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsflrForm model = (WsflrForm) form;
		User user = getUser(request);
		String host = IPRequest.getIpAddress(request);
		model.setCcrip(host);
		boolean result = service.saveWsf(model, user);		
		if (!result){
			//���ʧ�ܣ�����ʾ
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		return null;
	}
	
	/** 
	 * @����:�ύ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-9 ����10:04:38
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward tj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsflrForm model = (WsflrForm) form;
		User user = getUser(request);
		String host = IPRequest.getIpAddress(request);
		model.setCcrip(host);
		String message = service.tj(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/** 
	 * @����:�Ƿ���ύ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-9 ����10:17:45
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
	public ActionForward checkIsCanSubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsflrForm model = (WsflrForm) form;
		boolean result = service.checkIsCanSubmit(model);
		response.getWriter().print(result);
		return null;
	}
	
	/** 
	 * @����:�鿴
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-10 ����02:05:41
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
	@SystemAuth(url = url)
	public ActionForward viewqs(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		throws Exception{
		WsflrForm model = (WsflrForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getCcqsList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "cjWsf_ccqs.do";
		request.setAttribute("path", path);
		request.setAttribute("tjzt", model.getTjzt());
		request.setAttribute("ccny", model.getCcny());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewqs");		
	}
	
	/** 
	 * @����:������¼�뵼��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-11 ����11:04:50
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WsflrForm model = (WsflrForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getWsflrDc(model, user);//��ѯ�����м�¼������ҳ
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;	
	}
}
