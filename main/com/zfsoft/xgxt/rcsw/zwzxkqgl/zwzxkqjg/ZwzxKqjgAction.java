/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-20 ����11:36:27 
 */
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.cclxwh.CclxwhService;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsq.ZwzxKqsqService;

import common.newp.StringUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ϰ���ڹ���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-20 ����11:36:27
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZwzxKqjgAction extends SuperAction<ZwzxKqjgForm, ZwzxKqjgService> {
	private ZwzxKqjgService service = new ZwzxKqjgService();
	private CclxwhService cclxwhService = new CclxwhService();

	private static final String url = "rcsw_zwzxkq_kqjg.do";
	
	/**
	 * 
	 * @����:��ѯ���ڽ���б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-20 ����01:54:11
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
	public ActionForward getKqjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqjgForm model = (ZwzxKqjgForm) form;
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
		String path = "rcsw_zwzxkq_kqjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		// =========== ���ݲ˵��Զ������ֶ����� begin =============
		String gnmkmc = (String) request.getAttribute("gnmkmc");
		String ccrqTitle = "�����";
		String cclxTitle = "�����";
		String jlrTitle = "���";
		boolean gnmkmcKq = gnmkmc.contains("����");
		if(gnmkmcKq){
			ccrqTitle = "�������";
			cclxTitle = "�������";
			jlrTitle = "��д��";
		}
		request.setAttribute("ccrqTitle", ccrqTitle);
		request.setAttribute("cclxTitle", cclxTitle);
		request.setAttribute("jlrTitle", jlrTitle);
		request.setAttribute("gnmkmcKq", gnmkmcKq);
		// =========== ���ݲ˵��Զ������ֶ����� end =============
		return mapping.findForward("kqjgList");
	}
	
	@SystemAuth(url = url)
	public ActionForward getQqxsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqjgForm model = (ZwzxKqjgForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getQqxsList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_zwzxkq_xskqxx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qqxsList");
	}
	/**
	 * 
	 * @����:���ڽ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-22 ����05:27:51
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
	public ActionForward addKqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("xqmc", service.getXqmc(Base.currXq));
		request.setAttribute("cclxList", cclxwhService.getCclxList());
		request.setAttribute("qqlxList", service.getQqlxList());
		request.setAttribute("path", "zwzxkqKqjg.do?method=addKqjg");
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("addKqjg");
	}
	/**
	 * 
	 * @����:�޸Ŀ��ڽ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����01:55:20
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
	public ActionForward editKqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqjgForm myForm = (ZwzxKqjgForm) form;
		ZwzxKqjgForm kqjgForm = service.getKqjg(myForm);
		if("2297".equals(Base.xxdm)){
			ZwzxKqsqService sqService = new ZwzxKqsqService();
			kqjgForm.setYdrs(sqService.getYdrsSzly(kqjgForm.getBjdm()));
		}
		if(null!=kqjgForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(kqjgForm));
			request.setAttribute("kqjgForm", kqjgForm);
		}
		//��ѯȱ��ѧ����Ϣ
		List<HashMap<String,String>> qqxsList = service.getQqxsList(myForm.getJgid());
		request.setAttribute("cclxList", cclxwhService.getCclxList());
		request.setAttribute("qqxsList", qqxsList);
		request.setAttribute("qqlxList", service.getQqlxList());
		request.setAttribute("path", "zwzxkqKqjg.do?method=editKqjg");
		return mapping.findForward("editKqjg");
	}
	/**
	 * 
	 * @����:�鿴���ڽ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����01:55:20
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
	public ActionForward viewKqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqjgForm myForm = (ZwzxKqjgForm) form;
		ZwzxKqjgForm kqjgForm = service.getKqjg(myForm);
		/**
		 * 
		 * ��������ְҵ���Ի�
		 */
		if("2297".equals(Base.xxdm)){
			ZwzxKqsqService sqService = new ZwzxKqsqService();
			String num = sqService.getYdrsSzly(kqjgForm.getBjdm());
			kqjgForm.setYdrs(num);
		}
		if(null!=kqjgForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(kqjgForm));
			request.setAttribute("kqjgForm", kqjgForm);
		}
		//��ѯȱ��ѧ����Ϣ
		List<HashMap<String,String>> qqxsList = service.getQqxsList(myForm.getJgid());
		request.setAttribute("qqxsList", qqxsList);
		request.setAttribute("path", "zwzxkqKqjg.do?method=viewKqjg");
		return mapping.findForward("viewKqjg");
	}
	/**
	 * 
	 * @����:ȱ��ѧ����Ϣ�鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����05:06:22
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
	public ActionForward qqxsView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqjgForm myForm = (ZwzxKqjgForm) form;
		//��ѯȱ��ѧ����Ϣ
		HashMap<String,String> result = service.getQqxsxx(myForm);
		request.setAttribute("rs", StringUtils.formatData(result));
		request.setAttribute("path", "zwzxkqKqjg.do?method=qqxsView");
		return mapping.findForward("qqxsView");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�ճ�����-�㱨�����-�㱨����-ɾ��VALUES:{values}")
	public ActionForward delKqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			if(result){
				result = service.delQqxs(ids);
			}
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
	 * @����:���ڽ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-22 ����05:28:12
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
	@SystemLog(description = "�ճ�����-�㱨�����-�㱨����-���ӻ��޸ı���CCRQ:{ccrq},JGID:{jgid},CCLXDM:{cclxdm},BJMC:{bjmc},YDRS:{ydrs},SDRS:{sdrs}")
	@SuppressWarnings("unchecked")
	public ActionForward saveKqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		}

		ZwzxKqjgForm myForm = (ZwzxKqjgForm) form;
		String objStr = request.getParameter("objStr");
		User user =getUser(request);
		if (service.isHaveKgjg(myForm)) {
			myForm.setCclxmc(cclxwhService.getCclxById(myForm.getCclxdm()).get("lxmc"));
			String messageKey = MessageUtil.getText(MessageKey.RCSW_ZWZXKQ_KQSJ_EXIST, new String[] { myForm.getBjmc(), myForm.getCcrq(), myForm.getCclxmc() });
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		super.resetToken(request);
		List<ZwzxKqjgForm> qqxxList = JsonUtil.jsonArrToList(objStr,ZwzxKqjgForm.class);
		myForm.setJlr(user.getUserName());
		boolean result = service.editKqjg(myForm,qqxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:��ʾ�༶�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-23 ����08:43:25
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
	public ActionForward showBjList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqjgForm myForm = (ZwzxKqjgForm) form;

		User user = getUser(request);// �û�����
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getBjListNew(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		request.setAttribute("gotoPath", gotoPath);
		String path = "zwzxkqKqjg.do?method=showBjList";
		request.setAttribute("path", path);
		return mapping.findForward("bjSelect");
	}
	
	@SystemAuth(url = url)
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ZwzxKqjgForm myForm = (ZwzxKqjgForm) form;
		String xhArr= request.getParameter("xhArr");
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			String bjdm = request.getParameter("bjdm");
			
			myForm.setBjdm(bjdm);
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user,request);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("xhArr", xhArr);
		String path = "zwzxkqKqjg.do?method=getStu";
		request.setAttribute("path", path);
		return mapping.findForward("getStu");
	}
	
	@SystemAuth(url = url)
	public ActionForward getQqlxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<HashMap<String,String>> sbflList = cclxwhService.getQqlxList();
		JSONArray dataList = JSONArray.fromObject(sbflList);
		response.getWriter().print(dataList);
		return null;
	}
	/**
	 * 
	 * @����:���ڽ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-28 ����10:28:18
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
		
		ZwzxKqjgForm model = (ZwzxKqjgForm) form;
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
	 * @����:ѧ��������Ϣ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-28 ����01:55:01
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
	public ActionForward qqxsExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwzxKqjgForm model = (ZwzxKqjgForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllQqxsList(model,
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
	 * @����:ȱ��ѧ����������ͬ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-30 ����04:20:04
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
	public ActionForward qqxsxxTb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		Boolean flag = service.qqxsxxTb();
		String messageKey = flag ? MessageKey.SYS_SYNC_SUCCESS : MessageKey.SYS_SYNC_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
}
