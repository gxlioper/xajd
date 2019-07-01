/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-29 ����11:40:29 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zdzc;

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
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import eu.bitwalker.useragentutils.UserAgent;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2015-5-29 ����11:40:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzcAction extends SuperAction {
	
	private static final String url = "pj_zdzcwh.do";
	
	/**
	 * 
	 * @����:�۲��������¼��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-29 ����11:56:47
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
	public ActionForward viewZdzcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZdzcService service = new ZdzcService();
		ZdzcForm zdzcForm = (ZdzcForm) form;
		
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		User user = getUser(request);
		
		if (QUERY.equals(zdzcForm.getDoType())){
		
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zdzcForm.setSearchModel(searchModel);
			
			//��ѯ
			List<HashMap<String,String>> resultList = service.getZcwhList(zdzcForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		
		//����û������Ŀ���۲���Ŀ
		List<HashMap<String,String>> zcxmList = service.getAllowEditZcfxm();
		
		ZcfsService zcfsService = new ZcfsService();
		List<HashMap<String, String>> djList = zcfsService.getDj();// �ȼ�
		
		request.setAttribute("djList", JSONArray.fromObject(djList));
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("cssz", csszModel);
		request.setAttribute("userStatus", user.getUserStatus());
		request.setAttribute("path", "pj_zdzcwh.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewZdzcList");
	}
	
	
	/**
	 * 
	 * @����:ȡ��������Ա״̬
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-1 ����11:23:25
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
	public ActionForward delCpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZdzcService service = new ZdzcService();
		
		String values = request.getParameter("values");
		User user = getUser(request);
		

		boolean result = service.qxcp(values,user);
		String messageKey = result ? MessageKey.SYS_QXCP_SUCCESS
				: MessageKey.SYS_QXCP_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;

	}
	
	
	/**
	 * 
	 * @����:��֤��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-5 ����05:32:53
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
		
		ZdzcService service = new ZdzcService();
		ZdzcForm zdzcForm = (ZdzcForm) form;
		User user = getUser(request);
		
		String num = request.getParameter("num");
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zdzcForm.setSearchModel(searchModel);
		
		//����дѹ��̫����Ҫ����
		zdzcForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.getZcwhList(zdzcForm,user);
		
		boolean isCanDownload = resultList.size()<=Integer.parseInt(num)? true:false;
		
		response.getWriter().print(isCanDownload);
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:�۲�ֵ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-8 ����10:29:08
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
	public ActionForward toImportZdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String jsonStr = request.getParameter("jsonStr");
		request.setAttribute("jsonStr", jsonStr);
		
		return mapping.findForward("toImportZdzc");
	}
	
	
	/**
	 * 
	 * @����:���ص���ģ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-8 ����05:24:41
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
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZdzcService service = new ZdzcService();
		ZdzcForm zdzcForm = (ZdzcForm) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zdzcForm.setSearchModel(searchModel);
		
		File file = service.createImportTemplate(zdzcForm, user);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	
	/**
	 * 
	 * @����:�۲��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-9 ����02:36:48
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
	@SystemLog(description="������������-�ۺϲ���-�۲�ά��-¼��-����ID��{id}")
	public ActionForward importZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZdzcService service = new ZdzcService();
		ZdzcForm zdzcForm = (ZdzcForm) form;
		
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zdzcForm.setSearchModel(searchModel);
		
		try {
			File file = service.importZcfs(zdzcForm,user);
			
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
		
		return toImportZdzc(mapping, zdzcForm, request, response);
		
	}
	
	/**
	 * 
	 * @����:�ύ������Ա״̬
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-9 ����04:19:51
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
		
		ZdzcService service = new ZdzcService();
		ZdzcForm zdzcForm = (ZdzcForm) form;
		
		String values = request.getParameter("values");
		String tjzt = request.getParameter("tjzt");
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zdzcForm.setSearchModel(searchModel);
		
		boolean result = service.tjCpxs(values,tjzt,zdzcForm,user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		//�ֶ���дsystem log	
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
	 * 
	 * @����:����Ƿ���ύ�۲��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-10 ����05:15:45
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
		
		ZdzcService service = new ZdzcService();
		ZdzcForm zdzcForm = (ZdzcForm) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zdzcForm.setSearchModel(searchModel);
		
		String values = request.getParameter("values");
		
		boolean isCanSubmit = service.isCanSubmit(values,zdzcForm,user);
		
		response.getWriter().print(isCanSubmit);
		
		return null;
	}

}
