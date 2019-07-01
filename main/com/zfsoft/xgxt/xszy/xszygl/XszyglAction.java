/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-11 ����11:25:22 
 */
package com.zfsoft.xgxt.xszy.xszygl;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xszy.xsqshf.XszyQshfService;
import com.zfsoft.xgxt.xszy.xsxxgl.XszyXsxxForm;

import common.newp.StringUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-2-11 ����11:25:22
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XszyglAction extends SuperAction<XszyglForm, XszyglService> {
	private XszyglService service = new XszyglService();
	
	private static final String url = "xszy_xszygl.do";

	/**
	 * 
	 * @����:����֮����Ϣ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-11 ����11:50:34
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
	public ActionForward getXszyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyglForm model = (XszyglForm) form;
		User user = getUser(request);
		if (!"xy".equalsIgnoreCase(user.getUserStatus())&&!"xx".equalsIgnoreCase(user.getUserStatus())) {
			String msg = "��ģ�������Ժϵ��У���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
	    }
		if (null == model.getNj()) {
			model.setNj(Base.currNd);
		}
		if (QUERY.equalsIgnoreCase(model.getType())) {
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
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("nj", Base.currNd);
		String path = "xszy_xszygl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszyList");

	}

	/**
	 * 
	 * @����:����֮������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-11 ����03:45:44
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addXszy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		service.initParam(request, user);
		return mapping.findForward("addXszy");
	}

	/**
	 * 
	 * @����:����֮����Ϣ�޸�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-12 ����08:48:51
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editXszy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyglForm myForm = (XszyglForm) form;
		XszyglForm xszyForm = service.getModel(myForm.getId());
		if (null != xszyForm) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(xszyForm));
			request.setAttribute("xszyForm", xszyForm);
		}
		User user = getUser(request);
		service.initParam(request, user);
		return mapping.findForward("editXszy");
	}
	/**
	 * 
	 * @����:����֮����Ϣ�鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-12 ����09:43:56
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
	public ActionForward viewXszy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyglForm myForm = (XszyglForm) form;
		HashMap<String,String> rs = service.getXszy(myForm);
		request.setAttribute("rs", StringUtils.formatData(rs));
		User user = getUser(request);
		service.initParam(request, user);
		return mapping.findForward("viewXszy");
	}

	/**
	 * 
	 * @����:����֮��ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-12 ����09:18:33
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delXszy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	 * @����:����֮�ѱ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-11 ����04:41:41
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveXszy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyglForm myForm = (XszyglForm) form;
		if (service.isHaveXszy(myForm)) {
			String messageKey = MessageUtil.getText(MessageKey.XSZY_ZGH_REPEAT);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		boolean result = service.editXszy(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:��Ժϵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-12 ����11:04:26
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
	public ActionForward kyxbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyglForm myForm = (XszyglForm) form;
		if (SAVE.equals(myForm.getType())) {
			boolean result = service.kyxbj(myForm, request);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("kyxbj");
	}
	/**
	 * 
	 * @����:Ժϵ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-12 ����02:59:17
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
	public ActionForward fpyx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyglForm myForm = (XszyglForm) form;
		if (SAVE.equals(myForm.getType())) {
			boolean result = service.fpyx(myForm, request);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XszyQshfService qshfService = new XszyQshfService();
		List<HashMap<String, String>> xyList = qshfService.getXyList();
		request.setAttribute("xm", myForm.getXm());
		request.setAttribute("xyList", xyList);
		return mapping.findForward("fpyx");
	}
	/**
	 * 
	 * @����:����֮�ѵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-6 ����02:16:51
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
		XszyglForm model = (XszyglForm) form;

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//��ѯ�����м�¼������ҳ
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
