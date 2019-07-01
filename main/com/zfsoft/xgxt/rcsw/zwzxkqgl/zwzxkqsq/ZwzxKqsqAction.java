/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����02:37:19 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsq;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.cclxwh.CclxwhService;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.jcsz.JcszService;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.qqlxwh.QqlxwhService;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-26 ����02:37:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwzxKqsqAction extends SuperAction<ZwzxKqsqForm,ZwzxKqsqService>{
	private ZwzxKqsqService service = new ZwzxKqsqService();
	private ZwzxKqjgService kqjgService = new ZwzxKqjgService();
	private CclxwhService cclxwhService = new CclxwhService();
	private QqlxwhService qqlxwhService = new QqlxwhService();
	
	private static final String url = "rcsw_zwzxkq_kqsq.do";
	
	/**
	 * 
	 * @����:���������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����03:19:24
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
	public ActionForward getKqsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm model = (ZwzxKqsqForm) form;
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
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		JcszService  jcszService = new JcszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		String path = "rcsw_zwzxkq_kqsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		// =========== ���ݲ˵��Զ������ֶ����� begin =============
		String gnmkmc = (String) request.getAttribute("gnmkmc");
		String ccrqTitle = "�����";
		String cclxTitle = "�����";
		String jlrTitle = "���";
		if(gnmkmc.contains("����")){
			ccrqTitle = "�������";
			cclxTitle = "�������";
			jlrTitle = "��д��";
		}
		request.setAttribute("ccrqTitle", ccrqTitle);
		request.setAttribute("cclxTitle", cclxTitle);
		request.setAttribute("jlrTitle", jlrTitle);
		// =========== ���ݲ˵��Զ������ֶ����� end =============
		return mapping.findForward("kqsqList");
	}
	/**
	 * 
	 * @����:����Ա����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-3 ����01:50:28
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
	public ActionForward kqfkList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm model = (ZwzxKqsqForm) form;
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
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		JcszService  jcszService = new JcszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		String path = "rcsw_zwzxkq_fdyfk.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("kqfkList");
	}
	
	
	/**
	 * 
	 * @����:������д����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����06:33:18
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
	public ActionForward addKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("xqmc", kqjgService.getXqmc(Base.currXq));
		request.setAttribute("cclxList", cclxwhService.getCclxList());
		request.setAttribute("qqlxList", qqlxwhService.getQqlxList());
		request.setAttribute("path", "zwzxKqsq.do?method=addKqsq");
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("addKqsq");
	}
	/**
	 * 
	 * @����:������д�����޸�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����06:48:05
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
	public ActionForward editKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm myForm = (ZwzxKqsqForm) form;
		ZwzxKqsqForm model = service.getKqsq(myForm);
		if("2297".equals(Base.xxdm)){
			model.setYdrs(service.getYdrsSzly(model.getBjdm()));
		}
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			request.setAttribute("kqsqForm", model);
		}
		//��ѯȱ��ѧ����Ϣ
		List<HashMap<String,String>> qqxsList = kqjgService.getQqxsList(myForm.getSqid());
		request.setAttribute("cclxList", cclxwhService.getCclxList());
		request.setAttribute("qqxsList", qqxsList);
		request.setAttribute("qqlxList", qqlxwhService.getQqlxList());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("path", "zwzxkqKqsq.do?method=editKqsq");
		return mapping.findForward("editKqsq");
	}
	/**
	 * 
	 * @����:����Ա����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-3 ����02:39:54
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
	public ActionForward fdyfk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm myForm = (ZwzxKqsqForm) form;
		ZwzxKqsqForm model = service.getKqsq(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			request.setAttribute("kqsqForm", model);
		}
		//��ѯȱ��ѧ����Ϣ
		List<HashMap<String,String>> qqxsList = kqjgService.getQqxsList(myForm.getSqid());
		request.setAttribute("cclxList", cclxwhService.getCclxList());
		request.setAttribute("qqxsList", qqxsList);
		request.setAttribute("qqlxList", qqlxwhService.getQqlxList());
		request.setAttribute("path", "zwzxkqKqsq.do?method=fdyfk");
		return mapping.findForward("fdyfk");
	}
	/**
	 * 
	 * @����:���뱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����04:16:09
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
	@SystemLog(description = "�ճ�����-�㱨�����-�㱨����д-����CCRQ:{ccrq},CCLXDM:{cclxdm},BJMC:{bjmc},YDRS:{ydrs},SDRS:{sdrs}")
	@SuppressWarnings("unchecked")
	public ActionForward saveKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		}

		ZwzxKqsqForm model = (ZwzxKqsqForm) form;
		String objStr = request.getParameter("objStr");
		if (service.isHaveSqJl(model,"add")) {// ��֤
			model.setCclxmc(cclxwhService.getCclxById(model.getCclxdm()).get("lxmc"));
			String messageKey = MessageUtil.getText(MessageKey.RCSW_ZWZXKQ_KQSJ_EXIST, new String[] { model.getBjmc(), model.getCcrq(), model.getCclxmc() });
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		super.resetToken(request);
		List<ZwzxKqsqForm> qqxxList = JsonUtil.jsonArrToList(objStr,ZwzxKqsqForm.class);
		User user = getUser(request);
		model.setJlr(user.getUserName());
		boolean result = service.saveKqsq(model,qqxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:�����޸ı���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����07:06:20
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
	@SystemLog(description = "�ճ�����-�㱨�����-�㱨����д-�޸�SQID:{sqid},CCRQ:{ccrq},CCLXDM:{cclxdm},BJMC:{bjmc},YDRS:{ydrs},SDRS:{sdrs}")
	public ActionForward saveEditKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm model = (ZwzxKqsqForm) form;
		String objStr = request.getParameter("objStr");
		if (service.isHaveSqJl(model,"update")) {// ������
			model.setCclxmc(cclxwhService.getCclxById(model.getCclxdm()).get("lxmc"));
			String messageKey = MessageUtil.getText(MessageKey.RCSW_ZWZXKQ_KQSJ_EXIST, new String[] { model.getBjmc(), model.getCcrq(), model.getCclxmc() });
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		List<ZwzxKqsqForm> qqxxList = JsonUtil.jsonArrToList(objStr,ZwzxKqsqForm.class);
		boolean result = service.saveEditKqsq(model,qqxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:�����ύ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����08:01:16
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
	public ActionForward submitKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm model = (ZwzxKqsqForm) form;
		boolean result = service.submitKqsq(request,model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:��������ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����08:41:18
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
	@SystemLog(description = "�ճ�����-�㱨�����-�㱨����д-ɾ��VALUES:{values}")
	public ActionForward delKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// ɾ��
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			if(result){
				result = kqjgService.delQqxs(values.split(","));
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", num + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @����:���볷��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����08:41:44
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
	public ActionForward cancelKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			ZwzxKqsqForm model = new ZwzxKqsqForm();
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
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:��������鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-28 ����10:45:23
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
	public ActionForward viewKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm myForm = (ZwzxKqsqForm) form;
		ZwzxKqsqForm kqsqForm = service.getKqsq(myForm);
		/**
		 * ��������ְҵ���Ի�
		 */
		if("2297".equals(Base.xxdm)){
			String num = service.getYdrsSzly(kqsqForm.getBjdm());
			kqsqForm.setYdrs(num);
		}
		if(null!=kqsqForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(kqsqForm));
			request.setAttribute("kqsqForm", StringUtils.formatData(kqsqForm));
		}
		//��ѯȱ��ѧ����Ϣ
		List<HashMap<String,String>> qqxsList = kqjgService.getQqxsList(myForm.getSqid());
		request.setAttribute("qqxsList", qqxsList);
		request.setAttribute("path", "zwzxkqKqsq.do?method=viewKqsq");
		return mapping.findForward("viewKqsq");
	}
	/**
	 * 
	 * @����:�������뵼��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-28 ����10:45:04
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
		
		ZwzxKqsqForm model = (ZwzxKqsqForm) form;
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
	

}
