/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-19 ����02:00:30 
 */  
package com.zfsoft.xgxt.gygl.ggwpgl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.mchange.v2.beans.BeansUtils;
import com.zfsoft.utils.StringUtil;
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
import com.zfsoft.xgxt.gygl.lkxxgl.LkxxForm;
import com.zfsoft.xgxt.rcsw.sbgl.jyjl.JyjlModel;
import com.zfsoft.xgxt.rcsw.sbgl.jyjl.JyjlService;
import com.zfsoft.xgxt.rcsw.sbgl.sbfl.SbflModel;
import com.zfsoft.xgxt.rcsw.sbgl.sbfl.SbflService;
import com.zfsoft.xgxt.rcsw.sbgl.sbxx.SbxxModel;
import com.zfsoft.xgxt.rcsw.sbgl.sbxx.SbxxService;
import com.zfsoft.xgxt.szdw.jtff.FdyjtffService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-4-19 ����02:00:30 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GgwpjyAction extends SuperAction<GgwpjyForm, GgwpjyService>{
	private static final String GGWPJYGL = "ggwpjygl";
	private GgwpjyService service = new GgwpjyService();
	private static final String url = "xgygl_ggwpgl_jydj.do";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(GGWPJYGL);
	}
	
	/** 
	 * @����:��ѯ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-19 ����02:05:09
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
	public ActionForward getGgwpjyList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GgwpjyForm model = (GgwpjyForm) form;
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
		//searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		//searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);		
		String path = "xgygl_ggwpgl_jydj.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getGgwpjyList");
	}
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-19 ����07:17:35
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
	public ActionForward addGgwpjy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GgwpjyForm model = (GgwpjyForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}		
		SbflService sbflService = new SbflService();
		List<HashMap<String,String>> sbflList = sbflService.getAllList(new SbflModel());		
		request.setAttribute("now", GetTime.getNowTime4());
		request.setAttribute("sbflList", sbflList);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xgygl_ggwpjydj.do?method=addGgwpjy";
		request.setAttribute("path", path);
		return mapping.findForward("addGgwpjy");
	}
	
	/** 
	 * @����:����Ǽ�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-20 ����10:41:45
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
	public ActionForward saveDj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GgwpjyForm model = (GgwpjyForm) form;						
		if(!service.sfgh(model)){
			String messageKey = MessageKey.GYGL_GGWPJYGL_WGH;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}else{
			boolean result = service.runInsert(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
	}
	
	/** 
	 * @����:�޸�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-20 ����11:22:24
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
	public ActionForward editGgwpjy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GgwpjyForm myForm = (GgwpjyForm) form;		
		GgwpjyForm model = service.getModel(myForm);		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}		
		SbxxModel sbxx = new SbxxModel();
		sbxx.setFldm(model.getFldm());
		
		List<HashMap<String,String>> sbxxList = new SbxxService().getAllList(sbxx);
		request.setAttribute("sbxxList", sbxxList);
		
		SbflService sbflService = new SbflService();
		List<HashMap<String,String>> sbflList = sbflService.getAllList(new SbflModel());
		request.setAttribute("sbflList", sbflList);
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("editGgwpjy");
	}
	
	/** 
	 * @����:�޸ı���
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-20 ����11:32:02
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
	public ActionForward saveDjForUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GgwpjyForm model = (GgwpjyForm) form;						
		if(!service.sfgh(model)){
			String messageKey = MessageKey.GYGL_GGWPJYGL_WGH;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}else{
			boolean result = service.runUpdate(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
	}
	
	
	/** 
	 * @����:�黹
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-20 ����02:01:52
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
	public ActionForward sbgh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		String ids = request.getParameter("ids");
		GgwpjyForm myForm = (GgwpjyForm) form;		
		boolean result = service.ghsb(myForm, ids);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);		
		return null;
	}
	
	/** 
	 * @����:ɾ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-20 ����02:32:01
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
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");	
		if (!StringUtil.isNull(ids)){
			int num = service.runDelete(ids.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.XYJD_DELETE_DMEXIST,"���ü�¼");
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	/** 
	 * @����:�鿴
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-21 ����08:54:32
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
	public ActionForward viewGgwpjy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GgwpjyForm myForm = (GgwpjyForm) form;		
		GgwpjyForm model = service.getModel(myForm);				
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rs", myForm);
		return mapping.findForward("viewGgwpjy");
	}
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-22 ����09:48:36
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
		GgwpjyForm model = (GgwpjyForm) form;
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
