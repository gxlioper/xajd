package com.zfsoft.xgxt.xszy.xsxxgl;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.zdybd.util.ZdybdCommon;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����֮��
 * @�๦������: ������Ϣ����
 */
public class XszyXsxxAction extends SuperAction {
	
	XszyXsxxService service = new XszyXsxxService();
	
	private static final String url = "xszy_xsxxgl.do";

	/**
	 * @����: ������Ϣ��ѯ
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-15 ����11:16:00
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
	@SystemLog(description = "��������֮��-����֮��-������Ϣ����-��ѯҳ��")
	public ActionForward getXsxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XszyXsxxForm model = (XszyXsxxForm) form;
		if (null == model.getNj()) {
			model.setNj(Base.currNd);
		}
		User user = getUser(request);
		if (!"xy".equalsIgnoreCase(user.getUserStatus())&&!"xx".equalsIgnoreCase(user.getUserStatus())) {
			String msg = "��ģ�������Ժϵ��У���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ���
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xszy_xsxxgl.do";
		request.setAttribute("path", path);
		request.setAttribute("userType", getUser(request).getUserType());
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszyXsxxManage");
	}

	/**
	 * �޸�
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateXszyXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyXsxxForm model = (XszyXsxxForm) form;
		if (UPDATE.equalsIgnoreCase(model.getType())) {
			boolean result = service.updateXszyXsxx(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XszyXsxxForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs", model);
		
		ZdybdCommon zdybdCommon = new ZdybdCommon();
		request.setAttribute("ssxArr", zdybdCommon.getSsxJson());
		request.setAttribute("mzList", service.queryMzList());
		
		return mapping.findForward("updateXszyXsxx");
	}

	/**
	 * �鿴
	 */
	@SystemAuth(url = url)
	public ActionForward viewXszyXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyXsxxForm model = (XszyXsxxForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		XszyXsxxForm rs = service.getModel(model);
		request.setAttribute("rs", StringUtils.formatData(rs));
		return mapping.findForward("viewXszyXsxx");
	}
	
	/**
	 * ����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyXsxxForm model = (XszyXsxxForm) form;
		if (null == model.getNj()) {
			model.setNj(Base.currNd);
		}
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
