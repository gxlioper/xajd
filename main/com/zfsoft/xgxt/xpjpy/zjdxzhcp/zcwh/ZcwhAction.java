/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2017-6-22 ����09:41:07 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.zcwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.LogInfo;
import com.zfsoft.xgxt.base.log.LogService;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.IPRequest;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;

import eu.bitwalker.useragentutils.UserAgent;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �۲��ά��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-6-22 ����09:41:52 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcwhAction extends SuperAction{
	private static final String url = "xpjpy_zhcp_zcwh.do";
	private static final String num = "5000";
	private ZcwhService service = new ZcwhService();
	
	/**
	 * @����: ѧԺ�۲�����б�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-22 ����11:23:47
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
	@SystemLog(description = "��������������-�ۺ�����-����ά��-��ѯҳ��")
	public ActionForward getZcwhList (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	 	throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		User user = getUser(request);
		if (QUERY.equals(model.getDoType())){
			/*���ɸ߼���ѯ����*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			/*��ѯ*/
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*����������Ϣ*/
		request.setAttribute("cssz", new CsszService().getCszzInfo());
		/*����path*/
		String path = "xpjpy_zhcp_zcwh.do";
		request.setAttribute("path", path);
		/*����ҳ�����Ȩ�޼���ͷ*/
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zcwhList");
	}
	
	/**
	 * @����: �鿴�Ƿ���δ�ύ��¼
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-23 ����11:25:38
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
	public ActionForward checkSubmitInfo (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	 	throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		
		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		/*�û�*/
		User user = getUser(request);
		/*�ж��Ƿ���δ�ύ�ļ�¼*/
		boolean isCanSubmit = service.isSubmitInfo(model,user);
		response.getWriter().print(isCanSubmit);
		return null;
	}
	
	/**
	 * @����: �۲��������¼��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-23 ����02:40:36
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
	public ActionForward zcwhEdit (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	 	throws Exception {
		
		ZcwhForm zcwhForm = (ZcwhForm)form;
		/*�û�*/
		User user = getUser(request);
		
		/*���ÿ����¼���۲���Ŀ��������*/
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		request.setAttribute("zcxmdmForTop", zcxmdmForTop);
		
		if(QUERY.equals(zcwhForm.getDoType())){
			/*���ɸ߼���ѯ����*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcwhForm.setSearchModel(searchModel);
			
			/*��ѯ����*/
			List<HashMap<String,String>> resultList = service.getZcwhList(zcwhForm,user,zcxmdmForTop);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*�۲�fjdmΪtop����Ŀ�����ȡ��ص��۲�����*/
		List<HashMap<String,String>> zcxmList = service.getZcxmListByFjdmisTop(zcxmdmForTop);
		request.setAttribute("zcxmList", zcxmList);
		/*����������Ϣ*/
		CsszService csszService = new CsszService();
		HashMap<String,String> getCsszInfo = csszService.getCszzInfo();
		request.setAttribute("cssz", getCsszInfo);
		/*��õȼ�*/
		List<HashMap<String,String>> djList = service.getDj();
		request.setAttribute("djList", JSONArray.fromObject(djList));
		/*����path*/
		String path = "xpjpy_zhcp_zcwh.do";
		request.setAttribute("path", path);
		/*���ز���path���߼���ѯ�ã�*/
		String czpath = "xpjpy_zhcp_zcwh.do?method=zcwhEdit";
		request.setAttribute("czpath", czpath);
		/*�û����*/
		request.setAttribute("userStatus", user.getUserStatus());
		
		/*����ҳ�����Ȩ�޼���ͷ*/
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("zcwhEdit");
	}
	
	/**
	 * @����: ʵʱ����¼����۲��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-28 ����03:46:52
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
	@SystemLog(description="������������-�ۺϲ���-�۲�ά��-¼��-����ѧ��XH��{xh}")
	public ActionForward saveZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcwhForm zcwhForm = (ZcwhForm)form;
		User user = getUser(request);
		boolean result = service.saveZcfs(zcwhForm, user);
		if (!result){
			/*���ʧ�ܣ�����ʾ*/
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		return null;
	}
	
	/**
	 * @����: ��������ѧ������
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-30 ����05:51:37
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
	public ActionForward cpxsAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		
		if (QUERY.equalsIgnoreCase(model.getDoType())) {
			/*���ɸ߼���ѯ����*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			/*��ѯ*/
			List<HashMap<String, String>> resultList = service.getAddCpxsList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		/*�꼶��ѧԺ��רҵ���༶*/
		FormModleCommon.setAllNjXyZyBjList(request);
		/*����Path*/
		String path = "xpjpy_zhcp_zcwh.do?method=cpxsAdd";
		request.setAttribute("path", path);
		return mapping.findForward("cpxsAdd");
	}
	
	
	/**
	 * @����: �۲�ֵ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-10 ����11:46:10
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
	public ActionForward cpxsExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		/*����������Ϣ*/
		request.setAttribute("cssz", new CsszService().getCszzInfo());
		/*�û�*/
		User user = getUser(request);
		
		/*���ÿ����¼���۲���Ŀ��������*/
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		
		/*���ɸ߼���ѯ*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		/*�۲���Ŀ�б�*/
		List<HashMap<String,String>> zcxmList = service.getZcxmListByFjdmisTop(zcxmdmForTop);
		/*����ҳ*/
		model.getPages().setPageSize(Integer.MAX_VALUE);
		/*��ѯ����*/
		ZcwhDao zcwhDao = new ZcwhDao();
		List<HashMap<String,String>> resultList = zcwhDao.getCpxsExportList(model,zcxmList,user,zcxmdmForTop);
		/*�������ܴ���*/
		File file = service.createImportTemplateDc(resultList, zcxmList,user);
		FileUtil.outputExcel(response, file);
		
		return null;
	}
	
	/**
	 * @����: �۲�ֵ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-10 ����05:57:17
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
	public ActionForward checkDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		/*�û�*/
		User user = getUser(request);
		
		/*���ÿ����¼���۲���Ŀ��������*/
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		
		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		/*���*/
		boolean isCanDownload = service.isCanDownload(model,user, num,zcxmdmForTop);
		response.getWriter().print(isCanDownload);
		return null;
	}
	
	/**
	 * @����: �۲�ֵ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-7-11 ����11:34:46
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
	public ActionForward toImportZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String jsonStr = request.getParameter("jsonStr");
		request.setAttribute("jsonStr", jsonStr);
		
		/*���ÿ����¼���۲���Ŀ��������*/
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		request.setAttribute("zcxmdmForTop", zcxmdmForTop);
		
		return mapping.findForward("importZcf");
	}
	
	/**
	 * @����: �����۲�ֵ���ģ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-11 ����11:53:21
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
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		/*�û�*/
		User user = getUser(request);
		
		/*���ÿ����¼���۲���Ŀ��������*/
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		
		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		/*����������*/
		File file = service.createImportTemplate(model, user,zcxmdmForTop);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @����: �۲�ֵ��룬�����ϴ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-11 ����03:49:54
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
	@SystemLog(description="������������-�ۺϲ���-�۲�ά��-¼��-����ID��{id}")
	public ActionForward importZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZcwhForm model = (ZcwhForm)form;
		User user = getUser(request);
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		
		try {
			File file = service.importZcfs(model,user,zcxmdmForTop);
			
			if (file != null){
				FileUtil.outputExcel(response, file);
				return null;
			}
		
			request.setAttribute("result", true);
			request.setAttribute("message",MessageUtil.getText(MessageKey.SYS_IMPORT_SUCCESS));
		} catch (SystemException e) {
			request.setAttribute("result", false);
			request.setAttribute("message", e.getMessage());
		}
		
		return toImportZcfs(mapping, model, request, response);
	}
	
	
	/**
	 * @����: ȡ��������Ա�ʸ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-28 ����05:29:54
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
	@SystemLog(description="������������-�ۺϲ���-�۲�ά��-¼��-ɾ��ѧ��VALUES��{values}")
	public ActionForward cpxsDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*����ǰ̨������������*/
		String values = request.getParameter("values");
		/*�û�*/
		User user = getUser(request);
		boolean result = service.qxcp(values,user);
		String messageKey = result ? MessageKey.SYS_QXCP_SUCCESS : MessageKey.SYS_QXCP_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: ��������Ա��һ���༶��������һ���༶
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-5 ����02:35:25
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
	@SystemLog(description="������������-�ۺϲ���-���Ӳ���ѧ��-����BJDM��{bjdm},IDS:{ids}")
	public ActionForward updateCpbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String bjdm = request.getParameter("bjdm");
		String ids = request.getParameter("ids");
		User user = getUser(request);
		
		 if (!StringUtil.isNull(bjdm)){
			boolean result = service.bjtzs(bjdm,user,ids);
			String messageKey = result ? MessageKey.SYS_TZ_SUCCESS
					: MessageKey.SYS_TZ_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
						
		}
		return null;
	}
	
	/**
	 * @����: ����Ƿ���ύ�۲��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-5 ����05:02:13
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
	public ActionForward checkIsCanSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZcwhForm zcwhForm = (ZcwhForm)form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcwhForm.setSearchModel(searchModel);
		
		String values = request.getParameter("values");
		boolean isCanSubmit = service.isCanSubmit(values,zcwhForm,user);
		response.getWriter().print(isCanSubmit);
		return null;
	}
	
	/**
	 * @����: �۲���ύ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-6 ����09:11:34
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
	public ActionForward tjCpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZcwhForm zcwhForm = (ZcwhForm)form;
		String values = request.getParameter("values");
		String tjzt = request.getParameter("tjzt");
		User user = getUser(request);
		
		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcwhForm.setSearchModel(searchModel);
		
		boolean result = service.tjCpxs(values,tjzt,zcwhForm,user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		/*�ֶ�дLOGO*/
		LogInfo logInfo = null;
		UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));
		
		String tjztmc = new String();
		if(tjzt.equalsIgnoreCase("tj")) tjztmc="�ύ";
		else if(tjzt.equalsIgnoreCase("qxtj")) tjztmc="ȡ���ύ";
		
		String[] xhList = service.getXhArray(values);
		String xh = "{";
		for(int i=0;i<xhList.length-1;i++){
			xh+=xhList[i];
			xh+=",";
		}
		xh+=xhList[xhList.length-1];
		xh+="}";
		String description = "������������-�ۺϲ���-�۲�ά��-" + tjztmc + "-�Ķ�ѧ��ѧ�ţ�" + xh;

		logInfo = new LogInfo();
		logInfo.setOsName(userAgent.getOperatingSystem().getName());
		logInfo.setBrowserName(userAgent.getBrowser().getName());
		logInfo.setBrowserVersion(userAgent.getBrowserVersion().getVersion());
		logInfo.setIp(IPRequest.getIpAddress(request));
		logInfo.setDescription(description);
		logInfo.setClassName(mapping.getType());
		logInfo.setMethodName("tjCpxs");
		
		if (user != null){
			logInfo.setUsername(user.getUserName());
			logInfo.setUserType(user.getUserType());
		}
		
		if(result) logInfo.setStatus("success");
		else logInfo.setStatus("fail");
		new LogService().runInsert(logInfo);
		return null;
	}
	
	/**
	 * @����: ȡ���ύ�۲�ֲ�ѯ�б�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-7 ����04:28:20
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
	public ActionForward getQxtjzcfList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZcwhForm model = (ZcwhForm)form;
		
		if (QUERY.equals(model.getDoType())){
			/*���ɸ߼���ѯ����*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			/*��ѯ*/
			List<HashMap<String,String>> resultList = service.getZcfqxList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*����������Ϣ*/
		request.setAttribute("cssz", new CsszService().getCszzInfo());
		/*����path*/
		String path = "xpjpy_zhcp_qxtjzcf.do";
		request.setAttribute("path", path);
		/*���Ʊ�ͷ*/
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qxtjzcfList");
	}
	
	/**
	 * @����: ȡ���ύ�۲��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-7 ����05:58:58
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
	@SystemLog(description="������������-�ۺϲ���-ȡ���ύ�۲��-ȡ���ύID��{id}")
	public ActionForward cancelTj(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		
		if (UPDATE.equals(model.getDoType())){
			User user = getUser(request);
			boolean result = service.cancelTj(user,model);
			String messageKey = result ? MessageKey.SYS_QXCP_SUCCESS : MessageKey.SYS_QXCP_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
			return null;
		}
		return mapping.findForward("cancelTj");
	}
	
	/**
	 * @����: �۲���ύ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-7 ����05:58:58
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
	public ActionForward checkZcfSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZcwhForm model = (ZcwhForm)form;
		boolean isCanSubmit = service.checkZcfSubmit(model);
		response.getWriter().print(isCanSubmit);
		return null;
	}
	
	/**
	 * @����: �ύ�۲�ּ�������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-13 ����08:59:07
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
	@SystemLog(description="������������-�ۺϲ���-�۲�ά��-�ύ�۲��ID��{id}")
	public ActionForward submitXyzcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZcwhForm model = (ZcwhForm)form;
		/*�û�*/
		User user = getUser(request);
		boolean result = service.submitZcfs(model, user);
		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL));
		return null;
	}
	
	/**
	 * @����: ȡ���ύ
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-3 ����10:44:25
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
	@SystemLog(description="������������-�ۺϲ���-id��{id}")
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward zcwhCancelSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZcwhForm model = (ZcwhForm)form;
		boolean result = service.cancelSubmit(model);
		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL));
		return null;
	}
	
	/**
	 * @����: �۲�ֲ鿴
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-13 ����03:31:10
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
	public ActionForward zcwhView (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	 	throws Exception {
		
		ZcwhForm zcwhForm = (ZcwhForm)form;
		/*�û�*/
		User user = getUser(request);
		
		/*���ÿ����¼���۲���Ŀ��������*/
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		request.setAttribute("zcxmdmForTop", zcxmdmForTop);
		
		/*�۲�fjdmΪtop����Ŀ�����ȡ��ص��۲�����*/
		List<HashMap<String,String>> zcxmList = service.getZcxmListByFjdmisTop(zcxmdmForTop);
		
		if(QUERY.equals(zcwhForm.getDoType())){
			/*���ɸ߼���ѯ����*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcwhForm.setSearchModel(searchModel);
			/*��ѯ����*/
			List<HashMap<String,String>> resultList = service.getZcwhView(zcwhForm,zcxmList,user,zcxmdmForTop);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*ѧԺ��Ϣ*/
		HashMap<String,String> xyMap = service.getXyxxById(zcwhForm.getId());
		request.setAttribute("xyxxMap", xyMap);
		
		request.setAttribute("zcxmList", zcxmList);
		/*����������Ϣ*/
		request.setAttribute("cssz", new CsszService().getCszzInfo());
		return mapping.findForward("zcwhView");
	}
	
	/**
	 * @����: һ��ͬ���۲��(�������)
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-12-7 ����10:52:39
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
	public ActionForward dataSynchronization (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
 		throws Exception {
		boolean result = false;
		result = service.getIntefaceData();
		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_TB_SUCCESS : MessageKey.SYS_TB_FAIL));
		return null;
	}
}
