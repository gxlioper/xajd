/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��10�� ����8:40:46 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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

import common.newp.StringUtil;
import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�������ģ��
 * @�๦������: ־Ը������Action
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��10�� ����8:40:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwJgAction extends SuperAction<ZyfwJgForm,ZyfwJgService>{
	
	private final String ZYFW="zyfw";
	private ZyfwJgService zyfwJgService = new ZyfwJgService();
	private static final String url = "xsxx_zyfwgl_jg.do?method=zyfwJgList";
	
	/**
	 * @����:��ת��־Ը�������б�ҳ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��11�� ����5:34:34
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
	public ActionForward zyfwJgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zyfwJgList");
	}
	
	/**
	 * @����:��ȡ־Ը�������б�JSON����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��11�� ����5:34:34
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
	public ActionForward getZyfwJgListData(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zyfwJgForm.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = zyfwJgService.getPageList(zyfwJgForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����:־Ը�������Ĳ鿴
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
	public ActionForward zyfwJgShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		ZyfwJgForm model = zyfwJgService.getModel(zyfwJgForm.getFwid());
		if(StringUtils.isNotNull(model.getXh())){
			BeanUtils.copyProperties(zyfwJgForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("zyfwJgShow");
	}
	
	/**
	 * @����:����ѧ�Ų�ѯ����־Ը��������Ϣ�б�����JSON����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��12�� ����2:53:15
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
	public ActionForward getZyfwJgListDataByXh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		//������ʷ������Ϣ�б�
		List<HashMap<String,String>> zyfwJgList = zyfwJgService.getZyfwJgListByXh(zyfwJgForm.getXh());
		JSONArray dataList = JSONArray.fromObject(zyfwJgList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����:ת��־Ը��������д����ҳ��
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
	public ActionForward zyfwJgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			zyfwJgForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(zyfwJgForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zyfwJgForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ�� ѧ��list
		zyfwJgForm.setXn(Base.currXn);
		zyfwJgForm.setXq(Base.currXq);
		String path = "xsxx_zyfwgl_jg.do?method=zyfwJgAdd";
		request.setAttribute("path", path);
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);

		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("zyfwJgAdd");
	}
	
	/**
	 * @����:־Ը���������������ı���
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
	@SystemLog(description="����ѧ����Ϣ-־Ը�������-־Ը������-����XH:{xh},XN:{xn},XQ:{xq},"
			+ "FWKSSJ:{fwkssj},FWJSSJ:{fwjssj},FWDDSSX:{fwddssx},FWDD:{fwdd},JZR:{jzr},FWXSS:{fwxss},FWNR:{fwnr}")
	public ActionForward zyfwJgSaveForAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		 //�жϵ�ǰʱ���Ƿ��������¼
		boolean isRepeat = zyfwJgService.isRepeat(zyfwJgForm);
		if (isRepeat) {
			String message = MessageUtil.getText(MessageKey.XSXX_ZYFWSQ_REPEAT,new String[]{zyfwJgForm.getFwkssj(),zyfwJgForm.getFwjssj()});
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = zyfwJgService.runInsert(zyfwJgForm);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����:ת��־Ը�������޸ĵ���ҳ��
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
	public ActionForward zyfwJgEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		ZyfwJgForm model = zyfwJgService.getModel(zyfwJgForm);
		if (StringUtils.isNotNull(model.getXh())) {
			BeanUtils.copyProperties(zyfwJgForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		String path = "xsxx_zyfwgl_jg.do?method=zyfwJgEdit";
		request.setAttribute("path", path);
		return mapping.findForward("zyfwJgEdit");
	}
	
	/**
	 * @����:־Ը���������޸ģ��ı���
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
	@SystemLog(description="����ѧ����Ϣ-־Ը�������-־Ը������-�޸�FWID:{fwid},XH:{xh},XN:{xn},XQ:{xq},"
			+ "FWKSSJ:{fwkssj},FWJSSJ:{fwjssj},FWDDSSX:{fwddssx},FWDD:{fwdd},JZR:{jzr},FWXSS:{fwxss},FWNR:{fwnr}")
	public ActionForward zyfwJgSaveForEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		//�жϵ�ǰʱ���Ƿ��������¼
		boolean isRepeat = zyfwJgService.isRepeat(zyfwJgForm);
		if (isRepeat) {
			String message = MessageUtil.getText(MessageKey.XSXX_ZYFWSQ_REPEAT,new String[]{zyfwJgForm.getFwkssj(),zyfwJgForm.getFwkssj()});;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = zyfwJgService.runUpdate(zyfwJgForm);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����:־Ը��������ɾ������������
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
	@SystemLog(description="����ѧ����Ϣ-־Ը�������-־Ը������-ɾ��VALUES:{values}")
	public ActionForward zyfwJgDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = zyfwJgService.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @����:־Ը�������ĵ���
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
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		String dcclbh = request.getParameter("dcclbh");
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zyfwJgForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = zyfwJgService.getAllList(zyfwJgForm,user);// ��ѯ�����м�¼������ҳ
		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = zyfwJgForm.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(dcclbh);// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}

}
