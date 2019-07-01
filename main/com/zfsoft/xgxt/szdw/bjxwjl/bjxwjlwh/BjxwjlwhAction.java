/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-14 ����02:09:18 
 */  
package com.zfsoft.xgxt.szdw.bjxwjl.bjxwjlwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @�๦������: �༶��Ϣ��¼ά��
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-14 ����02:09:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjxwjlwhAction extends SuperAction {

	/**
	 * path ·��
	 */
	private static final String PATH = "szdw_bjxwjlwh.do?method=bjxwjlwhManage";
	
	private static final String url = "szdw_bjxwjlwh.do?method=bjxwjlwhManage";
	
	/**
	 * servive
	 */
	private static BjxwjlwhService service = new BjxwjlwhService();
	
	/**
	 * �༶��Ϣ��¼ά������
	 */
	@SystemAuth(url = url)
	public ActionForward bjxwjlwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjxwjlwhManage");
	}
	
	/**
	 * 
	 * @����:���������б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:43:04
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
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjlwhForm model = (BjxwjlwhForm) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//��ѯ
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		//����Ա��Ϣ
		HashMap<String , String> fdyxx = service.getFdyxx(user.getUserName());
		//����Ա�༶�б�
		List<HashMap<String , String>> fdybjxxList = service.getFdyBjxxList(user.getUserName());
		//�����Ϣ
		List<HashMap<String , String>> lbxxList = service.getLbList();
		
		request.setAttribute("lbxxList", lbxxList); 
		request.setAttribute("fdybjxxList", fdybjxxList); 
		request.setAttribute("fdyxx", StringUtils.formatData(fdyxx));
		String path = "szdw_bjxwjlwh.do?method=sq";
		request.setAttribute("path", path);
		return mapping.findForward("sq");
	}
	
	/**
	 * 
	 * @����:����Action
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����˼������-�༶��Ϊ��¼-�༶��Ϊ��¼ά��-����XNDSDM:{xndsdm},JSONDATA:{jsondata}")
	public ActionForward sqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjlwhForm model = (BjxwjlwhForm) form;

		//������¼
		List<?> records = JsonUtil.jsonArrToList(model.getJsondata(), BjxwjlwhForm.Record.class);
		
		boolean isSuccess = service.saveBjjl(model.getXn(), model.getXqdm(), model.getJlr(), records);
		
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		
		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * 
	 * @����:����Action
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����˼������-�༶��Ϊ��¼-�༶��Ϊ��¼ά��-�޸�GUID:{guid},XNDSDM:{xndsdm},JLNR:{jlnr},JLR:{jlr},JLSJ:{jlsj}")
	public ActionForward updateAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjlwhForm model = (BjxwjlwhForm) form;

		boolean isSuccess = service.runUpdate(model);
		
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		
		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * 
	 * @����:ɾ��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����˼������-�༶��Ϊ��¼-�༶��Ϊ��¼ά��-ɾ��GUIDS:{guids}")
	public ActionForward deleteAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String sqids = request.getParameter("guids"); //��ɾ����sqids
		
		int isSuccess = service.runDelete(sqids.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @����:�޸�
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjlwhForm model = (BjxwjlwhForm) form;
		
		if(model.getGuid() != null){
			//����Ա�༶�б�
			List<HashMap<String , String>> fdybjxxList = service.getFdyBjxxList(service.getModel(model.getGuid()).getJlr());
			//�����Ϣ
			List<HashMap<String , String>> lbxxList = service.getLbList();
			
			request.setAttribute("lbxxList", lbxxList); 
			
			request.setAttribute("fdybjxxList", fdybjxxList); 
			
			request.setAttribute("bjxwjlxx", service.getModelMap(model.getGuid())); 
		}

		String path = "szdw_bjxwjlwh.do?method=updateSq";
		request.setAttribute("path", path);
		return mapping.findForward("updateSq");
	}
	
	/**
	 * 
	 * @����:�鿴
	 */
	@SystemAuth(url = url)
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjlwhForm model = (BjxwjlwhForm) form;
		request.setAttribute("bjxwjlxx", service.getModelMap(model.getGuid())); 
		String path = "szdw_bjxwjlwh.do?method=ck";
		request.setAttribute("path", path);
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @����:����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjlwhForm model = (BjxwjlwhForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
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
