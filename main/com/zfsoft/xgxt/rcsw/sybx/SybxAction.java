/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-8 ����05:22:39 
 */  
package com.zfsoft.xgxt.rcsw.sybx;

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
import com.zfsoft.xgxt.base.log.SystemLog;
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
 * @ģ������: �ճ��������ģ��
 * @�๦������: TODO ��ҵ���չ���
 * @���ߣ� honglin 
 * @ʱ�䣺 2013-5-8 ����05:22:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SybxAction  extends SuperAction{

	private static final String SYBX = "sybx";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "rcsw_sybx_cx.do";
	
	/**
	 * 
	 * @����:��ҵ���ղ�ѯ
	 * @���ߣ�honglin
	 * @���ڣ�2013-05-09 ����09:08:33
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
	public ActionForward getSybxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SybxForm model = (SybxForm) form;
		SybxService service = new SybxService();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_sybx_cx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("sybxList");
	}
	
	/**
	 * ��ҵҽ�Ʊ���ά�� �Զ��嵼��
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
	public ActionForward syylbxwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SybxForm model = (SybxForm) form;
		SybxService service = new SybxService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = service.getAllList(model,user);
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
	
	/**
	 * 
	 * @����:������ҵ������Ϣ
	 * @���ߣ�honglin
	 * @���ڣ�2013-05-09 ����09:34:06
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
	@SystemLog(description="�����ճ�����-��ҵ����-��ҵ����ά��-����")
	public ActionForward addSybx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SybxForm model = (SybxForm) form;
		SybxService service = new SybxService();
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
	
        if (SAVE.equalsIgnoreCase(model.getType())){
        	//Ψһ���жϣ�ѧ�ţ�ѧ�꣩
        	boolean isExist=service.isExistBySybz(model,SAVE);
        	if(!isExist){
	        	//�����ҵ������Ϣ
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		
        		response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_SYBX_RESULT_REPEAT));
				return null;
        	}
		}
		//ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		//Ĭ�Ͻ�ѧ������Ϊϵͳ��ǰѧ��
		if(StringUtil.isNull(model.getXn())){
        	model.setXn(Base.currXn);
        }
        
		String path = "rcsw_sybx.do?method=addSybx";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", model);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(SYBX);
		request.setAttribute("jbxxList", jbxxList);
		
		List<HashMap<String,String>> zjyyList = service.getAllZjyyList();
		request.setAttribute("zjyyList", zjyyList);
		List<HashMap<String,String>> cbrylbList = service.getAllCbrylbList();
		request.setAttribute("cbrylbList", cbrylbList);
		List<HashMap<String,String>> jfrylbList = service.getAllJfrylbList();
		request.setAttribute("jfrylbList", jfrylbList);
		
		return mapping.findForward("addSybx");
	}
	
	/**
	 * 
	 * @����:�޸���ҵ������Ϣ
	 * @���ߣ�honglin
	 * @���ڣ�2013-05-09 ����09:34:06
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
	@SystemLog(description="�����ճ�����-��ҵ����-��ҵ����ά��-�޸�GUID:{guid}")
	public ActionForward updateSybx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SybxForm model = (SybxForm) form;
		SybxService service = new SybxService();
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
	
        if (UPDATE.equalsIgnoreCase(model.getType())){
        	//Ψһ���жϣ�ѧ�ţ�ѧ�꣩
        	boolean isExist=service.isExistBySybz(model,UPDATE);
        	if(!isExist){
				//�޸��������϶���Ϣ
	        	boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		response.getWriter().print(getJsonMessage(null));
				return null;
        		
        	}
		}
		//ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		
		request.setAttribute("mkxxForm", model);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(SYBX);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);
		
		List<HashMap<String,String>> zjyyList = service.getAllZjyyList();
		request.setAttribute("zjyyList", zjyyList);
		List<HashMap<String,String>> cbrylbList = service.getAllCbrylbList();
		request.setAttribute("cbrylbList", cbrylbList);
		List<HashMap<String,String>> jfrylbList = service.getAllJfrylbList();
		request.setAttribute("jfrylbList", jfrylbList);
		
		SybxForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		
		return mapping.findForward("updateSybx");
	}
	
	/**
	 * 
	 * @����:ɾ����ҵ������Ϣ
	 * @���ߣ�HongLin
	 * @���ڣ�2013-05-09 ����09:34:06
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
	@SystemLog(description="�����ճ�����-��ҵ����-��ҵ����ά��-ɾ��VALUES:{values}")
	public ActionForward deleteSybx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SybxService service = new SybxService();
		
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴��ҵ������Ϣ
	 * @���ߣ�honglin
	 * @���ڣ�2013-05-09 ����09:34:06
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
	public ActionForward viewSybx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SybxForm model = (SybxForm) form;
		SybxService service = new SybxService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//��õ���ѧ����ҵ������Ϣ
			request.setAttribute("rs", StringUtils.formatData(service.getOneSybxList(model.getGuid())));

			//ѧ��������Ϣ��ʾ����
			jbxxList = bdpzService.getJbxxpz(SYBX);
			request.setAttribute("jbxxList", jbxxList);
			return mapping.findForward("viewSybx");
		} else {
			return updateSybx(mapping, form, request, response);
		}
	}
	
}
