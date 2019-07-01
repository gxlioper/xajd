/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��4�� ����2:21:52 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjcsz.ZyfwJcszService;
import common.newp.StringUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�������ģ��
 * @�๦������: ־Ը��������Action
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��4�� ����2:21:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwSqAction extends SuperAction<ZyfwSqForm,ZyfwSqService>{
	private final String ZYFW = "zyfw";	//����ѧ��������Ϣ��ʾ����
	private ZyfwJcszService zyfwJcszService = new ZyfwJcszService();
	private ZyfwSqService zyfwSqService = new ZyfwSqService();
	
	private static final String url = "xsxx_zyfwgl_sq.do?method=zyfwSqList";
	
	/**
	 * @����:ת��־Ը���������б�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��4�� ����5:04:07
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
	public ActionForward zyfwSqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		
		//��ȡ������˿�����Ϣ
		String[] sqshkg = zyfwJcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg[0]);
		request.setAttribute("path", url);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zyfwSqList");
	}
	
	/**
	 * @����:��ȡ־Ը���������б�JSON����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��4�� ����5:45:07
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
	public ActionForward getZyfwSqListData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zyfwSqForm.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = zyfwSqService.getPageList(zyfwSqForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����:־Ը��������Ĳ鿴
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��9�� ����4:43:20
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
	public ActionForward zyfwSqShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		ZyfwSqForm model = zyfwSqService.getModel(zyfwSqForm.getFwid());
		if(StringUtils.isNotNull(model.getXh())){
			BeanUtils.copyProperties(zyfwSqForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("zyfwSqShow");
	}
	
	/**
	 * @����:ת��־Ը�������뵯��ҳ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��5�� ����2:04:19
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
	public ActionForward zyfwSqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			zyfwSqForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(zyfwSqForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zyfwSqForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ�� ѧ��list
		zyfwSqForm.setXn(Base.currXn);
		zyfwSqForm.setXq(Base.currXq);
		String path = "xsxx_zyfwgl_sq.do?method=zyfwSqAdd";
		request.setAttribute("path", path);
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);

		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("zyfwSqAdd");
	}
	
	/**
	 * @����:־Ը�������루�������ı���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��5�� ����3:22:06
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
	@SystemLog(description="����ѧ����Ϣ-־Ը�������-־Ը��������-����XH:{xh},XN:{xn},XQ:{xq},"
			+ "FWKSSJ:{fwkssj},FWJSSJ:{fwjssj},FWDDSSX:{fwddssx},FWDD:{fwdd},JZR:{jzr},FWXSS:{fwxss},FWNR:{fwnr}")
	public ActionForward zyfwSqSaveForAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		 //�жϵ�ǰʱ���Ƿ��������¼
		boolean isRepeat = zyfwSqService.isRepeat(zyfwSqForm);
		if (isRepeat) {
			String message = MessageUtil.getText(MessageKey.XSXX_ZYFWSQ_REPEAT,new String[]{zyfwSqForm.getFwkssj(),zyfwSqForm.getFwjssj()});;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = zyfwSqService.zyfwSqSaveForAdd(zyfwSqForm);
		String messageKey = "submit".equals(zyfwSqForm.getType())?(result?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL)
																 :(result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����:ת��־Ը�����޸ĵ���ҳ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��5�� ����2:04:19
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
	public ActionForward zyfwSqEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		ZyfwSqForm model = zyfwSqService.getModel(zyfwSqForm);
		if (StringUtils.isNotNull(model.getXh())) {
			BeanUtils.copyProperties(zyfwSqForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xsxx_zyfwgl_sq.do?method=zyfwSqEdit";
		request.setAttribute("path", path);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("zyfwSqEdit");
	}
	
	/**
	 * @����:־Ը�������루�޸ģ��ı���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��5�� ����3:22:06
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
	@SystemLog(description="����ѧ����Ϣ-־Ը�������-־Ը��������-�޸�FWID:{fwid},XH:{xh},XN:{xn},XQ:{xq},"
			+ "FWKSSJ:{fwkssj},FWJSSJ:{fwjssj},FWDDSSX:{fwddssx},FWDD:{fwdd},JZR:{jzr},FWXSS:{fwxss},FWNR:{fwnr}")
	public ActionForward zyfwSqSaveForEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		//�жϵ�ǰʱ���Ƿ��������¼
		boolean isRepeat = zyfwSqService.isRepeat(zyfwSqForm);
		if (isRepeat) {
			String message = MessageUtil.getText(MessageKey.XSXX_ZYFWSQ_REPEAT,new String[]{zyfwSqForm.getFwkssj(),zyfwSqForm.getFwkssj()});;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = zyfwSqService.zyfwSqSaveForEdit(zyfwSqForm);
		String messageKey = "submit".equals(zyfwSqForm.getType())?(result?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL)
				 :(result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����:־Ը���������ɾ������������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��9�� ����9:50:05
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
	@SystemLog(description="����ѧ����Ϣ-־Ը�������-־Ը��������-ɾ��VALUES:{values}")
	public ActionForward zyfwSqDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = zyfwSqService.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @����:־Ը����������ύ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��9�� ����10:43:33
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
	@SystemLog(description="����ѧ����Ϣ-־Ը�������-־Ը��������-�ύVALUES:{values}")
	public ActionForward zyfwSqSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		boolean result = zyfwSqService.zyfwSqSubmit(zyfwSqForm);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����:־Ը��������ĳ���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��9�� ����11:53:01
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
	@SystemLog(description="����ѧ����Ϣ-־Ը�������-־Ը��������-����VALUES:{values},SPLCID:{splcid}")
	public ActionForward zyfwSqCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String fwid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = zyfwSqService.zyfwSqCancel(fwid, lcid);
		
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����:־Ը��������ĵ���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��9�� ����2:22:44
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		String dcclbh = request.getParameter("dcclbh");
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zyfwSqForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = zyfwSqService.getAllList(zyfwSqForm,user);// ��ѯ�����м�¼������ҳ
		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = zyfwSqForm.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(dcclbh);// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
