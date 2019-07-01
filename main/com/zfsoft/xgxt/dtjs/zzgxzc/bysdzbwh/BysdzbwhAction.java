/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��2��4�� ����2:27:36 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.bysdzbwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xszz.zzkff.ZzkffForm;
import com.zfsoft.xgxt.xszz.zzkff.ZzkffService;

import net.sf.json.JSONArray;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���-��֯��ϵת������ģ��
 * @�๦������: ��ҵ����֧������ά��Action
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��2��4�� ����2:27:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BysdzbwhAction extends SuperAction<BysdzbwhForm,BysdzbwhService>{
	
	private static final String url = "dtjs_bysdzbwh.do?method=dzbwhList";
	
	/**
	 * @����:��ҵ����֧��ά����ѯ�б�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��4�� ����5:06:37
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
	public ActionForward dzbwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BysdzbwhForm bysdzbwhForm = (BysdzbwhForm) form;
		BysdzbwhService service = new BysdzbwhService();
		
		if (QUERY.equals(bysdzbwhForm.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(bysdzbwhForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("bysdzbwhList");
	}
	
	/**
	 * @����:����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��4�� ����5:28:28
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
	@SystemLog(description="���Ž���-��֯��ϵת��-��ҵ����֧��ά��-���ӵ�֧��DZBDM:{dzbdm},DZBMC:{dzbmc}")
	public ActionForward dzbwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BysdzbwhForm bysdzbwhForm = (BysdzbwhForm) form;
		BysdzbwhService service = new BysdzbwhService();
		
		if (SAVE.equalsIgnoreCase(bysdzbwhForm.getType())){
			//�жϵ�֧������������Ƿ����
			bysdzbwhForm.setDzbdm(bysdzbwhForm.getDzbdm().trim());
			bysdzbwhForm.setDzbmc(bysdzbwhForm.getDzbmc().trim());
			boolean isExist=service.isExist(bysdzbwhForm);
			
			if(!isExist){
				boolean result = service.runInsert(bysdzbwhForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				//��֧������������Ѿ�����
				response.getWriter().print(getJsonMessageByKey(MessageKey.DTJS_ZZGXZC_DZBWH_EXIST));
				return null;
				
			}
		}
		return mapping.findForward("bysdzbwhAdd");
	}
	
	/**
	 * @����:����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��4�� ����5:29:24
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
	@SystemLog(description="���Ž���-��֯��ϵת��-��ҵ����֧��ά��-�޸ĵ�֧��DZBDM:{dzbdm},DZBMC:{dzbmc}")
	public ActionForward dzbwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BysdzbwhForm bysdzbwhForm = (BysdzbwhForm) form;
		BysdzbwhService service = new BysdzbwhService();
		
		if (UPDATE.equalsIgnoreCase(bysdzbwhForm.getType())){
			
			bysdzbwhForm.setDzbdm(bysdzbwhForm.getDzbdm().trim());
			bysdzbwhForm.setDzbmc(bysdzbwhForm.getDzbmc().trim());
			
			boolean isExist = service.isExist(bysdzbwhForm);
			
			if(!isExist){
				boolean result = service.runUpdate(bysdzbwhForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				//��֧������������Ѿ�����
				response.getWriter().print(getJsonMessageByKey(MessageKey.DTJS_ZZGXZC_DZBWH_EXIST));
				return null;
				
			}
		}
		
		BysdzbwhForm model = service.getModel(bysdzbwhForm);
		BeanUtils.copyProperties(bysdzbwhForm, StringUtils.formatData(model));
		return mapping.findForward("bysdzbwhUpdate");
	}
	
	/**
	 * @����:ɾ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��4�� ����5:29:39
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
	@SystemLog(description="���Ž���-��֯��ϵת��-��ҵ����֧��ά��-ɾ����֧��VALUES:{values}")
	public ActionForward dzbwhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BysdzbwhForm bysdzbwhForm = (BysdzbwhForm) form;
		BysdzbwhService service = new BysdzbwhService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			String [] ids = values.split(",");
			//�жϵ�֧������������Ƿ��Ѿ���ʹ�ã������ʹ��������ɾ��
			HashMap<String,Object> r = service.isUsed(ids);
			boolean isUsed = (Boolean)r.get("isUsed");
			if(isUsed){
				//��֧�������Ѿ���ʹ��
				response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.DTJS_ZZGXZC_DZBWH_USED,r.get("dzbmc"))));
				return null;
			}
			
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @����:����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��4�� ����5:29:39
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
	public ActionForward dzbwhExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BysdzbwhForm bysdzbwhForm = (BysdzbwhForm) form;
		BysdzbwhService service = new BysdzbwhService();
		
		//���ɸ߼���ѯ����(����û��ʹ�ø߼���ѯ)		
//		CommService comService = new CommService();
//		SearchModel searchModel = comService.getSearchModel(request);
//		bysdzbwhForm.setSearchModel(searchModel);
		User user = getUser(request);
//		List<HashMap<String,String>> resultList = service.getAllList(bysdzbwhForm,user);//��ѯ�����м�¼������ҳ
		List<HashMap<String,String>> resultList = service.getAllList(bysdzbwhForm);//��ѯ�����м�¼������ҳ
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = bysdzbwhForm.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
