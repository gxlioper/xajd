/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-30 ����11:36:27 
 */
package  xsgzgl.qgzx.kycxgl.kyxmgl;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;


/**
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: ������Ŀ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-11-30 ����11:36:27
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KyxmglAction extends SuperAction<KyxmglForm, KyxmglService> {
	private KyxmglService service = new KyxmglService();
	
	private static final String url = "qgzx_kycx_kygl_kyxmgl.do";

	
	/**
	 * 
	 * @����:��ѯ������Ŀ����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-30 ����01:54:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward getKyxmglList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmglForm model = (KyxmglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qgzx_kycx_kygl_kyxmgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getKyxmglList");
	}
	/**
	 * 
	 * @����:������Ŀ�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-30 ����05:27:51
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
	@SystemLog(description = "�����ڹ���ѧ-������Ŀ����-����-xmid:{xmid}")
	public ActionForward addKyxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmglForm model = (KyxmglForm) form;
		User user = getUser(request);
		String path = "qgzx_kycxkyxmgl.do?method=addKyxm";
		request.setAttribute("path", path);
		service.initParam(request, user);
		this.saveToken(request);
		return mapping.findForward("addKyxm");
	}
	
	public ActionForward showStudents(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		KyxmglForm myForm = (KyxmglForm) form;
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			String xmid = request.getParameter("xmid");
			myForm.setXmid(xmid);
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.showStudents(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qgzx_kycxkyxmgl.do?method=getStu";
		request.setAttribute("path", path);
		return mapping.findForward("showStudents");
	}
	/**
	 * 
	 * @����:�޸Ŀ�����Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-30 ����01:55:20
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
	@SystemLog(description = "�����ڹ���ѧ-������Ŀ����-�޸�-xmid:{xmid}")
	public ActionForward editKyxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmglForm myForm = (KyxmglForm) form;
		User user = getUser(request);
		KyxmglForm KyxmglForm = service.getModel(myForm);
		if(null!=KyxmglForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(KyxmglForm));
			request.setAttribute("KyxmglForm", StringUtils.formatData(KyxmglForm));
		}
		service.initParam(request, user);
		return mapping.findForward("editKyxm");
	}
	/**
	 * 
	 * @����:�鿴������Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-30 ����01:55:20
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
	public ActionForward viewKyxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmglForm myForm = (KyxmglForm) form;
		HashMap<String,String> kyxmMap = service.getKyxmgl(myForm);
		request.setAttribute("rs", StringUtils.formatData(kyxmMap));
		//��ȡ��Ա�б�
		List<HashMap<String, String>> cyList = service.getCyList(kyxmMap.get("xmid"));
		//��ȡ��ʦ�б�
		List<HashMap<String, String>> zdlsList = service.getTeaList(kyxmMap.get("xmid"));
		request.setAttribute("cyList", cyList);
		request.setAttribute("zdlsList", zdlsList);
		return mapping.findForward("viewKyxm");
	}
	
	/**
	 * 
	 * @����:������Ŀ�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-30 ����05:28:12
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
	@SuppressWarnings("unchecked")
	@SystemLog(description = "�����ڹ���ѧ-������Ŀ����-����-xmid:{xmid}")
	public ActionForward saveKyxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmglForm myForm = (KyxmglForm) form;
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		User user =getUser(request);
		if (service.isHaveKyxm(myForm)) {
			String message = MessageUtil.getText(MessageKey.KYCXGL_KYCXXM_KYCX_EXISTS);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editKyxmgl(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		this.saveToken(request);
		return null;
	}
	
	/**
	 * 
	 * @����:����ά������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-4 ����09:38:45
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
	@SuppressWarnings("unchecked")
	@SystemLog(description = "�����ڹ���ѧ-������Ŀ����-��Ŀ��������-xmid:{xmid}")
	public ActionForward saveFywh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmglForm myForm = (KyxmglForm) form;
		String fyxxStr = request.getParameter("fyxxStr");
		List<KyxmglXmfyForm> fyxxList = JsonUtil.jsonArrToList(fyxxStr,KyxmglXmfyForm.class);
		
		boolean result = service.editFywh(myForm,fyxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-������Ŀ����-ɾ��-xmid:{xmid}")
	public ActionForward delKyxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����:������Ŀ�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-30 ����10:28:18
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
		
		KyxmglForm model = (KyxmglForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ
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
	/**
	 * 
	 * @����:��Ŀ����ά��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ� ����10:32:13
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
	@SystemLog(description = "�����ڹ���ѧ-������Ŀ����-��Ŀ����ά��-xmid:{xmid}")
	public ActionForward kyxmFywh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmglForm myForm = (KyxmglForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		HashMap<String,String> kyxmMap = service.getKyxmgl(myForm);
		List<HashMap<String, String>> jfysList = service.getYsxxList(kyxmMap.get("xmid"));
		List<HashMap<String, String>> xmfyList = service.getXmfyList(kyxmMap.get("xmid"));
		KyxmglForm kyxmglForm = service.getModel(myForm);
		String sbjfhj = jfysList.size()==0?"0":jfysList.get(0).get("sbjfhj");
		kyxmglForm.setSbjfhj(sbjfhj);
		if(null!=kyxmglForm){
			request.setAttribute("myForm", StringUtils.formatData(kyxmglForm));
			BeanUtils.copyProperties(myForm, StringUtils.formatData(kyxmglForm));
		}
		
		request.setAttribute("rs", StringUtils.formatData(kyxmMap));
		request.setAttribute("jfysList", jfysList);
		request.setAttribute("xmfyList", xmfyList);
		List<HashMap<String, String>> fylxList = new OptionUtil().getOptions(OptionUtil.FYLX);// ��������
		request.setAttribute("fylxList", fylxList);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("kyxmFywh");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-������Ŀ����-����״̬����-xmid:{xmid}")
	public ActionForward ztsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmglForm myForm = (KyxmglForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.ztsz(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		KyxmglForm kyxmglForm = service.getModel(myForm);
		if(null!=kyxmglForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(kyxmglForm));
		}
		HashMap<String,String> kyxmMap = service.getKyxmgl(myForm);
		List<HashMap<String, String>> ztbgList = service.getBgztList(kyxmMap.get("xmid"));
		List<HashMap<String, String>> yxztList = service.getYxztList();
		request.setAttribute("rs", StringUtils.formatData(kyxmMap));
		request.setAttribute("yxztList", yxztList);
		request.setAttribute("ztbgList", ztbgList);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ztsz");
	}
}
