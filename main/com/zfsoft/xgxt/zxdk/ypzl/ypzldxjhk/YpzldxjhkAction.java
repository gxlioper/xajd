/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-3 ����11:04:27 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.ypzldxjhk;

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
 * @ģ������: ��ƽ������ѧ�𻹿���Ϣά��
 * @�๦������: ��ƽ������ѧ�𻹿���Ϣά��
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-3 ����11:04:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YpzldxjhkAction extends SuperAction<YpzldxjhkForm, YpzldxjhkService>{
	private static final String url = "zxdk_ypzl_ypzldxjhk.do";
	private static final String HKXX = "hkxx";
	private YpzldxjhkService service = new YpzldxjhkService();
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(HKXX);
	}
	
	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-3 ����02:19:01
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
	public ActionForward getYpzldxjhkList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		YpzldxjhkForm model = (YpzldxjhkForm) form;
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
		String path = "zxdk_ypzl_ypzldxjhk.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("getYpzldxjhkList");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-3 ����03:22:05
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
	public ActionForward addhkxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		YpzldxjhkForm model = (YpzldxjhkForm) form;
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
		model.setHksj(GetTime.getTimeByFormat("yyyy-mm-dd"));
		String path = "ypzl_hk.do?method=addhkxx";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("addhkxx");
	}
	
	/**
	 * 
	 * @����: �޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-4 ����09:41:58
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
	public ActionForward edithkxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		YpzldxjhkForm myForm = (YpzldxjhkForm) form;
		YpzldxjhkForm model = service.getModel(myForm);
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
		request.setAttribute("xn", model.getXn());
		request.setAttribute("xq", service.getXqmc(model.getXq()));
		String path = "ypzl_hk.do?method=edithkxx";
		request.setAttribute("path", path);
		
		return mapping.findForward("edithkxx");
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-4 ����04:25:26
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
	public ActionForward delHk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-4 ����08:48:33
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
	public ActionForward saveHkjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		YpzldxjhkForm model = (YpzldxjhkForm) form;
		boolean result = false;
		User user = getUser(request);	
		if(model.getType().equals("save")){
 			if(service.isHaveRecord(model)){
 				String messageKey = MessageKey.ZXDK_JKHK_REPEAT;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
 			}
 			model.setXn(Base.currXn);
 			model.setXq(Base.currXq);
 			if(model.getType().equals("save")){
 				model.setSjly(user.getUserName());
 			}
			result = service.runInsert(model);
		}else if(model.getType().equals("update")){
			model.setXn(Base.currXn);
 			model.setXq(Base.currXq);
 			if(model.getType().equals("update")){
 				model.setSjly(user.getUserName());
 			}
 			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @����: ѧ�����Ӳ鿴
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-4 ����04:39:43
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
	public ActionForward viewHkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		YpzldxjhkForm model = (YpzldxjhkForm) form;
		request.setAttribute("jbxxList", jbxxList);
		YpzldxjhkForm viewForm = service.getModel(model);
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
		request.setAttribute("xq", service.getXqmc(model.getXq()));
		
		return mapping.findForward("viewHkxx");
		
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-4 ����04:58:41
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
		
		YpzldxjhkForm model = (YpzldxjhkForm) form;
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
