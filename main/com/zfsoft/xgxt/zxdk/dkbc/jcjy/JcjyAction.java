/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-6 ����03:16:41 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.jcjy;

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
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgModel;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgService;
import com.zfsoft.xgxt.zxdk.xnwxjkhk.XnwxjkhkForm;
import com.zfsoft.xgxt.zxdk.ysjxj.ysjxj.YsjxjForm;
import com.zfsoft.xgxt.zxdk.ysjxj.ysjxj.YsjxjService;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����--�����ҵ
 * @�๦������: �����ҵ��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2016-12-6 ����03:16:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcjyAction extends SuperAction<JcjyModel, JcjyService>{
	private static final String url = "zxdk_jcjy_bcdc.do";
	private JcjyService service = new JcjyService();
	private static final String ZXDKJCJY = "zxdkjcjy";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(ZXDKJCJY);
	}
	
	/**
	 * @����: ��ѯҳ��
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-6 ����08:33:46
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
	public ActionForward bcdcList(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		JcjyModel model = (JcjyModel) form;
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
		String path = "zxdk_jcjy_bcdc.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bcdcList");
	}
	
	/**
	 * @����: ������Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-8 ����09:03:56
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
	public ActionForward jcjyAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JcjyModel model = (JcjyModel) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		request.setAttribute("jbxxList", jbxxList);
		//������ҵ�����롢��ҵ�������
		request.setAttribute("hylbList",service.getHylbList());
		request.setAttribute("path", "jcjy_bcdc.do?method=jcjyAdd");
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("jcjyAdd");
	}
	
	/**
	 * @����: �޸�
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-8 ����05:07:14
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
	public ActionForward jcjyUpdate(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		JcjyModel myForm = (JcjyModel) form;
		JcjyModel model = service.getModel(myForm);
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
		//������ҵ�����롢��ҵ�������
		request.setAttribute("hylbList",service.getHylbList());
		String path = "jcjy_bcdc.do?method=jcjyUpdate";
		request.setAttribute("path", path);
		request.setAttribute("dclb", model.getDclb());
		request.setAttribute("clsfqq", model.getClsfqq());
		request.setAttribute("sfwxzfsfz", model.getSfwxzfsfz());
		request.setAttribute("sfzzzg", model.getSfzzzg());
		request.setAttribute("lzsfzc", model.getLzsfzc());
		request.setAttribute("dksfwqch", model.getDksfwqch());
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("jcjyUpdate");
	}
	
	/**
	 * @����: ���ӡ��޸ı���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-8 ����09:04:38
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
	public ActionForward saveJcjy(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		JcjyModel model = (JcjyModel) form;
		boolean result = false;	
		if(model.getType().equals("save")){
 			if(service.isHaveRecord(model.getXh(),model.getDclb())){
 				String messageKey = MessageKey.JYGLNEW_JYGL_BYQX_EXISTS;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
 			}
			result = service.runInsert(model);
		}else if(model.getType().equals("update")){
 			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * @����: ɾ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-8 ����09:05:54
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
	public ActionForward delJcjy(ActionMapping mapping, ActionForm form, 
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
	 * @����: �鿴
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-8 ����04:36:38
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
	public ActionForward jcjyView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcjyModel myForm = (JcjyModel) form;
		JcjyModel model = service.getModel(myForm.getJuid());
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", model);
		request.setAttribute("hylbmc", service.getHylb(model.getXh()));
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("jcjyView");
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-8 ����04:41:53
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
		JcjyModel model = (JcjyModel) form;
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
