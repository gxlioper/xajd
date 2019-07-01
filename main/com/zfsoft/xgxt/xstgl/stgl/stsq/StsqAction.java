/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����05:02:12 
 */
package com.zfsoft.xgxt.xstgl.stgl.stsq;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.stlbgl.StlbglService;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.xmlbgl.XmlbglService;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgForm;

import common.newp.StringUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-9 ����05:02:12
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class StsqAction extends SuperAction<StsqForm, StsqService> {

	private StsqService service = new StsqService();
	private StlbglService stlbService = new StlbglService();
	private XmlbglService xmlbService = new XmlbglService();
	
	private static final String url = "stgl_stgl_stsq.do";

	/**
	 * 
	 * @����:���������б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-8-3 ����04:23:38
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
	public ActionForward getStsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm model = (StsqForm) form;
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
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		// String[] sqshkg = csszService.getSqShKg();
		// request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg[0]);
		String path = "stgl_stgl_stsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getStsqList");
	}

	/**
	 * 
	 * @����:������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-3 ����04:06:41
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
	public ActionForward addStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm model = (StsqForm) form;
		User user = getUser(request);
		// ѧ�� ѧ��
		model.setXn(Base.currXn);
		List<HashMap<String, String>> stlbList = stlbService.getStlbList();
		request.setAttribute("stlbList", stlbList);
		String stlbdm = stlbList.get(0).get("stlbdm");
		request.setAttribute("xmlbList", xmlbService.getXmlbList(stlbdm));
		if("12872".equals(Base.xxdm)) {
			request.setAttribute("gkdwList", service.getBbdmlist());
			List<HashMap<String, String>> stxjList = service.getstxjList();
			request.setAttribute("stxjList",stxjList);
		}
		initParam(request, user);
		return mapping.findForward("addStsq");
	}

	/**
	 * 
	 * @����:���������޸�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-3 ����04:07:06
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
	public ActionForward editStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm myForm = (StsqForm) form;
		User user = getUser(request);
		StsqForm model = service.getModel(myForm);
		HashMap<String, String> stsqxx = service.getSqxx(model);
		request.setAttribute("stfzrxm", stsqxx.get("stfzrxm"));
		request.setAttribute("zdlsxm", stsqxx.get("zdlsxm"));
		request.setAttribute("fzrlb", stsqxx.get("fzrlb"));
		request.setAttribute("fzrbj", stsqxx.get("fzrbj"));
		request.setAttribute("fzrxy", stsqxx.get("fzrxy"));
		List<HashMap<String, String>> stlbList = stlbService.getStlbList();
		request.setAttribute("stlbList", stlbList);
		request.setAttribute("xmlbList", xmlbService.getXmlbList(model.getStlbdm()));
		//ָ����ʦ��Ϣ
		List<HashMap<String,String>> ZdlsInfoList=service.getZdlsInfo(model);
		request.setAttribute("ZdlsInfoList",ZdlsInfoList);
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		if("12872".equals(Base.xxdm)) {
			request.setAttribute("gkdwList", service.getBbdmlist());
			List<HashMap<String, String>> stxjList = service.getstxjList();
			request.setAttribute("stxjList",stxjList);
		}
		String path = "stglStsq.do?method=editStsq";
		request.setAttribute("path", path);
		initParam(request, user);
		return mapping.findForward("editStsq");
	}

	/**
	 * 
	 * @����:��������鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-3 ����11:32:02
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
	
	public ActionForward viewStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm myForm = (StsqForm) form;
		StsqForm model = service.getModel(myForm);
		HashMap<String,String> sqxxMap = service.getSqxx(model);
		//ָ����ʦ��Ϣ
		List<HashMap<String,String>> ZdlsInfoList=service.getZdlsInfo(model);
		request.setAttribute("ZdlsInfoList",ZdlsInfoList);
		request.setAttribute("rs", StringUtils.formatData(sqxxMap));
		return mapping.findForward("viewStsq");
	}

	/**
	 * 
	 * @����:�������뱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-3 ����10:18:49
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
	public ActionForward saveStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm model = (StsqForm) form;
		// �жϵ�ǰʱ���Ƿ��������¼
		boolean isExist = service.isHaveSqJl(model);
		if (isExist) {
			String message = MessageUtil.getText(MessageKey.STGL_STGL_ST_REPEAT, new String[] {
					model.getKssj(), model.getJssj() });
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.saveStsq(model);
		String messageKey = "";
		if("submit".equals(model.getType())){
			 messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
		}
		else{
			 messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:���������޸ı���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-3 ����02:33:27
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
	public ActionForward saveEditStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm model = (StsqForm) form;
		// �жϵ�ǰʱ���Ƿ��������¼
		boolean isExist = service.isHaveSqJl(model);
		if (isExist) {
			String message = MessageUtil.getText(MessageKey.STGL_STGL_ST_REPEAT, new String[] {
					model.getJssj(), model.getKssj() });
			;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		String messageKey = service.saveEditStsq(model);
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-3 ����02:35:38
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
	public ActionForward delStsq(ActionMapping mapping, ActionForm form,
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
	 * @����:��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-3 ����03:03:21
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
	public ActionForward cancelStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			StsqForm model = new StsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:�ύ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-8-3 ����10:26:05
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
	public ActionForward submitStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm model = (StsqForm) form;
		String values = request.getParameter("values");
		String xmlbdm = request.getParameter("xmlbdm");
		model.setSqid(values);
		model.setXmlbdm(xmlbdm);
		boolean result = service.submitStsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:���ݵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-3 ����11:18:26
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm model = (StsqForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getsqAll(model,
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
     * @����:��ʼ�������б�
     * @���ߣ�����[���ţ�1104]
     * @���ڣ�2015-8-3 ����10:59:11
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param request
     * @param user
     * @throws Exception
     * void �������� 
     * @throws
     */
	private void initParam(HttpServletRequest request,User user) throws Exception{
		List<HashMap<String, String>> bmList = new OptionUtil().getOptions("gkdw");
		request.setAttribute("jtrxm", user.getRealName());
		request.setAttribute("jtr", user.getUserName());
		/*�����汾��Ĭ������ʱ���Ϊyyyy-MM-dd��ԭ��Ϊyyyy-MM-dd hh24:mm:ss*/
		request.setAttribute("sqsj", GetTime.getTimeByFormat("yyyy-MM-dd"));
		request.setAttribute("bmList", bmList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("zclist", service.getZclblist());
		/*Ĭ����Чѧ��*/
		request.setAttribute("mryxxn", Base.currXn);
		/*�����汾������������*/
		request.setAttribute("ssbmlist", service.getBbdmlist());
	}
	
	//�����������ӻ�ȡѧ���б�
	@SystemAuth(url = url)
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		StsqForm myForm = (StsqForm) form;
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "stglStsq.do?method=getStu";
		request.setAttribute("path", path);
		return mapping.findForward("getStu");
	}
	
	//�����������ӻ�ȡ��ʦ�б�
	
	public ActionForward getTea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		StsqForm myForm = (StsqForm) form;
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getTeaxxList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		if(request.getParameter("flag")!=null && request.getParameter("flag").equals("selzdls")){
			request.setAttribute("flag", request.getParameter("flag"));
		}else{
			request.setAttribute("flag", "selstfzr");
		}
		String path = "stglStsq.do?method=getTea";
		request.setAttribute("path", path);
		return mapping.findForward("getTea");
	}

}
