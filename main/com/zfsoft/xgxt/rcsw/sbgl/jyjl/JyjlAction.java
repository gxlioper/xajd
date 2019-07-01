/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:49:48 
 */  
package com.zfsoft.xgxt.rcsw.sbgl.jyjl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

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
import com.zfsoft.xgxt.rcsw.sbgl.sbfl.SbflModel;
import com.zfsoft.xgxt.rcsw.sbgl.sbfl.SbflService;
import com.zfsoft.xgxt.rcsw.sbgl.sbxx.SbxxModel;
import com.zfsoft.xgxt.rcsw.sbgl.sbxx.SbxxService;
import com.zfsoft.xgxt.szdw.jtff.FdyjtffService;

/** 
 * @�๦������:����ά��
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����09:49:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JyjlAction extends SuperAction<JyjlModel, JyjlService> {
	
	private static final String url = "rcsw_sbgl_jyjl.do";

	/**����ά���б�*/
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("now", GetTime.getNowTime4());
		request.setAttribute("path", "rcsw_sbgl_jyjl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("list");
	}
	
	/**����ά���б�*/
	@SystemAuth(url = url)
	public ActionForward getList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyjlService service = getService();
		JyjlModel model = (JyjlModel) form;
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JyjlModel model = (JyjlModel) form;
		//JyjlService service = getService();
		FdyjtffService service = new FdyjtffService();
		
		if (!StringUtil.isNull(model.getZgh())){
			//����f����Ա������Ϣ
			HashMap<String,String> jbxx = service.getFdyjbxx(model.getZgh());
			request.setAttribute("jbxx", jbxx);
		}
		
		SbflService sbflService = new SbflService();
		List<HashMap<String,String>> sbflList = sbflService.getAllList(new SbflModel());
		
		request.setAttribute("now", GetTime.getNowTime4());
		request.setAttribute("sbflList", sbflList);
		String path = "rcswSbglJyjl.do?method=add";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("add");
	}
	
	/**����ά���޸�*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyjlService service = getService();
		JyjlModel myForm = (JyjlModel) form;
		
		JyjlModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//����f����Ա������Ϣ
			HashMap<String,String> jbxx = new FdyjtffService().getFdyjbxx(model.getZgh());
			request.setAttribute("jbxx", jbxx);
		}
		
		SbxxModel sbxx = new SbxxModel();
		sbxx.setFldm(model.getFldm());
		
		List<HashMap<String,String>> sbxxList = new SbxxService().getAllList(sbxx);
		request.setAttribute("sbxxList", sbxxList);
		
		SbflService sbflService = new SbflService();
		List<HashMap<String,String>> sbflList = sbflService.getAllList(new SbflModel());
		request.setAttribute("sbflList", sbflList);
		return mapping.findForward("edit");
	}
	
	
	/**�鿴**/
	@SystemAuth(url = url)
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyjlService service = getService();
		JyjlModel myForm = (JyjlModel) form;
		
		JyjlModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//����f����Ա������Ϣ
			HashMap<String,String> jbxx = new FdyjtffService().getFdyjbxx(model.getZgh());
			request.setAttribute("jbxx", jbxx);
		}
		
		return mapping.findForward("view");
	}
	
	/**ɾ�����ü�¼*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-���ع���-�豸����-ɾ��IDS:{ids}")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		JyjlService service = getService();
		
		if (!StringUtil.isNull(ids)){
			int num = service.runDelete(ids.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.XYJD_DELETE_DMEXIST,"���ü�¼");
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}

	
	/**�豸�黹**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-���ع���-�豸����-�黹IDS:{ids}")
	public ActionForward sbgh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String ids = request.getParameter("ids");
		JyjlService service = getService();
		JyjlModel myForm = (JyjlModel) form;
		
		boolean result = service.ghsb(myForm, ids);
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		
		return null;
	}
	
	
	/**����***/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JyjlService service = getService();
		JyjlModel model = (JyjlModel) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model);// ��ѯ�����м�¼������ҳ

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
