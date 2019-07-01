/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����04:47:08 
 */  
package xsgzgl.gygl.gyyggl;

import java.io.File;
import java.io.IOException;
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

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xucy [���ţ�754]
 * @ʱ�䣺 2013-7-30 ����04:47:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyygxxglAction extends SuperAction{
	
	private static final String url = "gygl_gyygxxgl.do";
	
	//�б�
	@SystemAuth(url = url)
	public ActionForward gyygxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyygxxglForm myForm = (GyygxxglForm) form;
		GyygxxglService service = new GyygxxglService();
		
		SearchModel searchModel=new SearchModel();
		searchModel.setPath("gygl_gyygxxgl.do");
		request.setAttribute("searchTj", searchModel);
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "gygl_gyygxxgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gyygxxManage");
	}
	
	/**
	 * 
	 * @����:TODO����Ա��
	 * @���ߣ�xucy [���ţ�754]
	 * @���ڣ�2013-8-1 ����10:54:23
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
	@SystemLog(description="���ʹ�Ԣ����-��ԢԱ������-Ա������-����YGBH:{ygbh},XM:{xm},XB:{xb},NL:{nl},ZWDM:{zwdm},SFZH:{sfzh},LXDH:{lxdh},ZGZT:{zgzt}")
	public ActionForward zjYg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		GyygxxglForm myForm = (GyygxxglForm) form;
		GyygxxglService service = new GyygxxglService();
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		List<HashMap<String, String>> zwdmlist = service.getZwdmList();
		request.setAttribute("zwdmList", zwdmlist);
		return mapping.findForward("zjYg");
	}
	
	/**
	 * �޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="���ʹ�Ԣ����-��ԢԱ������-Ա������-�޸�PK:{ygbh},XM:{xm},XB:{xb},NL:{nl},ZWDM:{zwdm},SFZH:{sfzh},LXDH:{lxdh},ZGZT:{zgzt}")
	public ActionForward xgYg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyygxxglForm myForm = (GyygxxglForm) form;
		GyygxxglService service = new GyygxxglService();
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		List<HashMap<String, String>> zwdmlist = service.getZwdmList();
		request.setAttribute("zwdmList", zwdmlist);
		GyygxxglForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("xgYg");
	}
	
	/**
	 * �鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward ckYg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyygxxglForm myForm = (GyygxxglForm) form;
		GyygxxglService service = new GyygxxglService();
		HashMap<String, String> yhxx = service.getYgxxmap(myForm.getYgbh());
		request.setAttribute("yhxx", StringUtils.formatData(yhxx));
		return mapping.findForward("ckYg");
	}
	
	/**
	 * ɾ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="���ʹ�Ԣ����-��ԢԱ������-Ա������-ɾ��VALUES:{values}")
	public ActionForward scYg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyygxxglService service = new GyygxxglService();
		
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	
	/**
	 * ���Ա������Ƿ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkYgbh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ygbh =  request.getParameter("ygbh"); 
		GyygxxglService service = new GyygxxglService();
		String checkjg = service.checkYgbh(ygbh);
		try {
			response.getWriter().print(checkjg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * ����ά���Զ��嵼��
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
	public ActionForward sjkwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyygxxglService service = new GyygxxglService();
		GyygxxglForm model = (GyygxxglForm) form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = (List<HashMap<String,String>>) service.getAllList(model);
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
