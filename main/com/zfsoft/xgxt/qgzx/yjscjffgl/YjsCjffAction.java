package com.zfsoft.xgxt.qgzx.yjscjffgl;

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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ѳ�����
 * @�๦������: �о�����𷢷�action
 * @���ߣ� xiaxia
 * @ʱ�䣺 2016-05-04 ����03:33:37
 * @�汾�� V5.7.15
 * @�޸ļ�¼:
 */
public class YjsCjffAction extends SuperAction {
	private static final String url = "qgzx_jfgl_yjscjff.do";

	/**
	 * @����: ��𷢷Ÿ߼�ģʽ��ѯ
	 * @���ߣ�xiaxia
	 * @���ڣ�2016-05-04 ����03:34:24
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��������
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward yjsCjffList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YjsCjffForm model = (YjsCjffForm) form;
		YjsCjffService service = new YjsCjffService();
		// ��ȡ��¼�û�
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("yjsCjffList");
	}
	

	/**
	 * @����:���ӳ�𷢷�
	 * @���ߣ�xiaxia
	 * @���ڣ�2016-5-04 ����03:35:01
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��������
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-���ѳ�����-�о�����𷢷�-����XH:{xh}")
	public ActionForward zjyjsCjff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YjsCjffForm myForm = (YjsCjffForm) form;
		YjsCjffService service = new YjsCjffService();
		User user = getUser(request);
		String disQg = request.getParameter("disQg");
		if (!StringUtils.isNull(disQg) || StringUtils.isNull(myForm.getYrbm())) {// ���Ϊ�������ڹ�����Ա
			myForm.setYrbm(user.getUserDep());
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("path", url);
		request.setAttribute("model", myForm);
		request.setAttribute("rs", service.getFfmrCs(request, myForm));

		return mapping.findForward("zjyjsCjff");
	}
	/**
	 * 
	 * @����: ����Ƿ񷢷Ź�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-05-04 ����02:42:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	public ActionForward checkFfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YjsCjffService service = new YjsCjffService();
		String guid=request.getParameter("guid");
		String xh=request.getParameter("xh");
		String xn=request.getParameter("xn");
		String yrdwdm=request.getParameter("yrdwdm");
		String gwmc=request.getParameter("gwmc");
		String ffny=request.getParameter("ffny");
		Boolean ish=service.isHaveFfxx(guid, xh, xn, yrdwdm, gwmc,ffny);
		response.getWriter().print(ish);
		return null;
	}

	/**
	 * @����:�޸ĳ�𷢷�
	 * @���ߣ�xiaxia
	 * @���ڣ�2016-05-04 ����03:35:25
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��������
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-���ѳ�����-�о�����𷢷�-�޸�guid:{guid},XH:{xh},XN:{xn},GWMC:{gwmc}")
	public ActionForward xgyjsCjff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YjsCjffForm myForm = (YjsCjffForm) form;
		YjsCjffService service = new YjsCjffService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			if(result){
				result=service.runUpdate(myForm);
			}
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		YjsCjffForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("path", url);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("rs", StringUtils.formatData(service.getFfmrCs(request, myForm)));
		return mapping.findForward("xgyjsCjff");
	}

	/**
	 * @����:ɾ����𷢷���Ϣ
	 * @���ߣ�xiaxia
	 * @���ڣ�2016-05-04 ����03:35:57
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��������
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-���ѳ�����-�о�����𷢷�-ɾ��ids:{ids}")
	public ActionForward scCjff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YjsCjffService service = new YjsCjffService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;

	}

	

	/**
	 * �ڹ����ά���Զ��嵼��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward qgjgwhExportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		YjsCjffForm model = (YjsCjffForm) form;
		YjsCjffService service = new YjsCjffService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);
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
