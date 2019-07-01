/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-28 ����09:12:41 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.ysjxj;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����-Ժ�轱ѧ��
 * @�๦������: Ժ�轱ѧ��)
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2016-7-28 ����09:12:41 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YsjxjAction  extends SuperAction<YsjxjForm, YsjxjService>{
	private static final String url = "zxdk_ysjxj_ysjxjwh.do";
	private static final String HKXX = "hkxx";
	private YsjxjService service = new YsjxjService();
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(HKXX);
	}
	/**
	 * @����: ��ѯ
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����09:17:41
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
	public ActionForward getYsjxjwh(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		YsjxjForm model = (YsjxjForm) form;
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
		request.setAttribute("searchTj", searchModel);
		String path = "zxdk_ysjxj_ysjxjwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("listYsjxj");
	}
	
	/**
	 * @����: ����
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����11:54:02
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
	public ActionForward addYsjxj(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		YsjxjForm model = (YsjxjForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ�� ѧ��list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", service.getXqmc(Base.currXq));
		model.setFfsj(GetTime.getTimeByFormat("yyyy-mm-dd"));
		String path = "ysjxj_ysjxjwh.do?method=addYsjxj";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zjlyList", service.getZjlyList());//�ʽ���Դ�б�
		return mapping.findForward("addYsjxj");
	}
	
	/**
	 * @����: �����޸�  ���淽��
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����02:18:59
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
	public ActionForward saveYsjxj(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		YsjxjForm model = (YsjxjForm) form;
		String xn = request.getParameter("xn");
		String xq = Base.currXq;
		boolean result = false;	
		if(model.getType().equals("save")){
 			if(service.isHaveRecord(model.getXh(),xn,xq,model.getXmmc())){
 				String messageKey = MessageKey.ZXDK_YSJXJ_ERROR;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
 			}
 			model.setXn(Base.currXn);
 			model.setXq(Base.currXq);
			result = service.runInsert(model);
		}else if(model.getType().equals("update")){
			if(service.updateCheck(model.getXh(),model.getXn(),model.getXq(),model.getXmmc(),model.getJuid())){
 				String messageKey = MessageKey.ZXDK_YSJXJ_ERROR;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
 			}
 			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * @����: ɾ��
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����02:20:18
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
	public ActionForward delYsjxj(ActionMapping mapping, ActionForm form, 
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
	 * @����: �޸�
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����03:16:56
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
	public ActionForward editYsjxj(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		YsjxjForm myForm = (YsjxjForm) form;
		YsjxjForm model = service.getModel(myForm);
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zjlyList", service.getZjlyList());//�ʽ���Դ�б�
		request.setAttribute("xqmc", service.getXqmc(model.getXq()));
		String path = "ysjxj_ysjxjwh.do?method=editYsjxj";
		request.setAttribute("path", path);
		return mapping.findForward("editYsjxj");
	}
	
	/**
	 * @����: �鿴
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����03:17:13
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
	public ActionForward viewYsjxj(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		YsjxjForm model = (YsjxjForm) form;
		request.setAttribute("jbxxList", jbxxList);
		YsjxjForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("rs", model);
		request.setAttribute("xn", model.getXn());
		request.setAttribute("zjlymc",service.getZjlymc(model.getZjly()));
		request.setAttribute("xq", service.getXqmc(model.getXq()));
		return mapping.findForward("viewYsjxj");
	}
	
	/**
	 * @����: ����
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����03:17:35
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YsjxjForm model = (YsjxjForm) form;
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
