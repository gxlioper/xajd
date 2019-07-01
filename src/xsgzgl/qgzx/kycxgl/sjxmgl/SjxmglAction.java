/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-30 ����11:36:27 
 */
package  xsgzgl.qgzx.kycxgl.sjxmgl;

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
 * @ģ������: ʵ����Ŀ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-11-30 ����11:36:27
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class SjxmglAction extends SuperAction<SjxmglForm, SjxmglService> {
	private SjxmglService service = new SjxmglService();
	
	private static final String url = "qgzx_kycx_sjgl_sjxmgl.do";

	
	/**
	 * 
	 * @����:��ѯʵ����Ŀ����б�
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
	public ActionForward getSjxmglList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmglForm model = (SjxmglForm) form;
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
		String path = url;
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getSjxmglList");
	}
	/**
	 * 
	 * @����:ʵ����Ŀ�������
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
	@SystemLog(description = "�����ڹ���ѧ-ʵ����Ŀ����-����-xmid:{xmid}")
	public ActionForward addSjxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmglForm model = (SjxmglForm) form;
		User user = getUser(request);
		String path = "qgzx_kycxsjxmgl.do?method=addSjxm";
		request.setAttribute("path", path);
		
		
		service.initParam(request, user);
		this.saveToken(request);
		return mapping.findForward("addSjxm");
	}
	/**
	 * 
	 * @����:�޸�ʵ����Ŀ���
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
	@SystemLog(description = "�����ڹ���ѧ-ʵ����Ŀ����-�޸�-xmid:{xmid}")
	public ActionForward editSjxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmglForm myForm = (SjxmglForm) form;
		User user = getUser(request);
		SjxmglForm SjxmglForm = service.getModel(myForm);
		if(null!=SjxmglForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(SjxmglForm));
			request.setAttribute("SjxmglForm", StringUtils.formatData(SjxmglForm));
		}
		List<HashMap<String,String>> gwxxList = service.getGwxxList(myForm.getXmid());
		request.setAttribute("xmgwList", gwxxList);
		String path = "qgzx_kycxsjxmgl.do?method=editSjxm";
		request.setAttribute("path", path);
		service.initParam(request, user);
		return mapping.findForward("editSjxm");
	}
	/**
	 * 
	 * @����:�鿴ʵ����Ŀ���
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
	public ActionForward viewSjxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmglForm myForm = (SjxmglForm) form;
		HashMap<String,String> sjxmMap = service.getSjxmgl(myForm.getXmid(),"");
		request.setAttribute("rs", StringUtils.formatData(sjxmMap));
		//��ȡ��Ա�б�
		List<HashMap<String, String>> cyList = service.getCyList(sjxmMap.get("xmid"));
		//��ȡ��ʦ�б�
		List<HashMap<String, String>> zdlsList = service.getTeaList(sjxmMap.get("xmid"));
		request.setAttribute("cyList", cyList);
		request.setAttribute("zdlsList", zdlsList);
		return mapping.findForward("viewSjxm");
	}
	
	/**
	 * 
	 * @����:ʵ����Ŀ�������
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
	@SystemLog(description = "�����ڹ���ѧ-ʵ����Ŀ����-����-xmid:{xmid}")
	public ActionForward saveSjxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		SjxmglForm myForm = (SjxmglForm) form;
		
		User user =getUser(request);
		if (service.isHaveSbjg(myForm)) {
			String message = MessageUtil.getText(MessageKey.SZTZ_XMSB_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		String gwxxStr  = request.getParameter("gwxxStr");
		List<SjxmglGwxxForm> gwxxList = JsonUtil.jsonArrToList(gwxxStr,SjxmglGwxxForm.class);
		boolean result = service.editSjxmgl(myForm,gwxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-ʵ����Ŀ����-ɾ��-xmid:{xmid}")
	public ActionForward delSjxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	 * @����:ʵ����Ŀ�������
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
		
		SjxmglForm model = (SjxmglForm) form;
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
	@SystemLog(description = "�����ڹ���ѧ-ʵ����Ŀ����-��Ŀ����ά��-xmid:{xmid}")
	public ActionForward SjxmFywh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmglForm myForm = (SjxmglForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		HashMap<String,String> SjxmMap = service.getSjxmgl(myForm.getXmid(),"");
		List<HashMap<String, String>> xmfyList = service.getXmfyList(SjxmMap.get("xmid"));
		SjxmglForm SjxmglForm = service.getModel(myForm);
		if(null!=SjxmglForm){
			request.setAttribute("myForm", StringUtils.formatData(SjxmglForm));
			BeanUtils.copyProperties(myForm, StringUtils.formatData(SjxmglForm));
		}
		
		request.setAttribute("rs", StringUtils.formatData(SjxmMap));
		request.setAttribute("xmfyList", xmfyList);
		List<HashMap<String, String>> fylxList = new OptionUtil().getOptions(OptionUtil.FYLX);// ��������
		request.setAttribute("fylxList", fylxList);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("SjxmFywh");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-ʵ����Ŀ����-��Ŀ����-xmid:{xmid}")
	public ActionForward ztsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmglForm myForm = (SjxmglForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.ztsz(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		SjxmglForm SjxmglForm = service.getModel(myForm);
		if(null!=SjxmglForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(SjxmglForm));
		}
		HashMap<String,String> SjxmMap = service.getSjxmgl(myForm.getXmid(),"");
		List<HashMap<String, String>> ztbgList = service.getBgztList(SjxmMap.get("xmid"));
		List<HashMap<String, String>> yxztList = service.getYxztList();
		request.setAttribute("rs", StringUtils.formatData(SjxmMap));
		request.setAttribute("yxztList", yxztList);
		request.setAttribute("ztbgList", ztbgList);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ztsz");
	}
}
