/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-13 ����08:55:52 
 */  
package com.zfsoft.xgxt.rwgl.rwtw;

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

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������ģ��
 * @�๦������: TODO����ѧ�Ѳ�������
 * @���ߣ�HongLin 
 * @ʱ�䣺 2013-5-13 ����08:55:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwxfbcglAction extends SuperAction {

	private static final String RWXFBC = "rwxfbc";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "rwgl_rwtwgl_rwxfbcgl.do";
	
	/**
	 * 
	 * @����: ����ѧ�Ѳ�����ѯ
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-14 ����09:12:05
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
	public ActionForward getRwxfbcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RwxfbcglForm model = (RwxfbcglForm) form;
		RwxfbcglService service = new RwxfbcglService();
		
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
		
		String path = "rwgl_rwtwgl_rwxfbcgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("rwxfbcList");
	}
	
	/**
	 * ����ѧ�Ѳ��������Զ��嵼��
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
	public ActionForward rwxfbcExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RwxfbcglForm model = (RwxfbcglForm) form;
		RwxfbcglService service = new RwxfbcglService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
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
	 * @����:TODO ���ӵ���ѧ������ѧ�Ѳ���
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-14 ����02:04:24
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
	public ActionForward addRwxfDgbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		RwxfbcglForm model = (RwxfbcglForm) form;
		RwxfbcglService service = new RwxfbcglService();
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//����ѧ��������Ϣ
			HashMap<String,String> rwxx = service.getOneRwxfbcList(model.getXh());
			request.setAttribute("rwxx", StringUtils.formatData(rwxx));
		}
		if (SAVE.equalsIgnoreCase(model.getType())){
        	//Ψһ���жϣ�ѧ�ţ�ѧ�꣩
			model.setGuid(null);
        	boolean isExist=service.isExistByRwxfbc(model,SAVE);
        	if(!isExist){
	        	//�����ҵ������Ϣ
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		response.getWriter().print(getJsonMessage(MessageKey.RCSW_SYBX_RESULT_REPEAT));
				return null;
        	}
		}
		//ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		//Ĭ�Ͻ�ѧ������Ϊϵͳ��ǰѧ��
		if(StringUtil.isNull(model.getXn())){
        	model.setXn(Base.currXn);
        }
		
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RWXFBC);
		request.setAttribute("jbxxList", jbxxList);
		String path = "rwgl_rwxfbcgl.do?method=rwxfDgbc";
		request.setAttribute("path", path);
		
		return mapping.findForward("rwxfDgbc");
	}
	
	/** 
	 * @����:TODO �޸ĵ���ѧ������ѧ�Ѳ���
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-14 ����02:04:24
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
	public ActionForward updateRwxfDgbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		RwxfbcglForm model = (RwxfbcglForm) form;
		RwxfbcglService service = new RwxfbcglService();
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//����ѧ��������Ϣ
			HashMap<String,String> rwxx = service.getOneRwxfbcList(model.getXh());
			request.setAttribute("rwxx", StringUtils.formatData(rwxx));
		}
		if (UPDATE.equalsIgnoreCase(model.getType())){
        	//Ψһ���жϣ�ѧ�ţ�ѧ�꣩
        	boolean isExist=service.isExistByRwxfbc(model,UPDATE);
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
		
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RWXFBC);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);
		
		RwxfbcglForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		
		return mapping.findForward("rwxfDgbc");
	}
	
	/** 
	 * @����:TODO ��õ���ѧ������ѧ�Ѳ�����Ϣ
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-14 ����02:08:14
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
	public ActionForward viewRwxfbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RwxfbcglForm model = (RwxfbcglForm) form;
		RwxfbcglService service = new RwxfbcglService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//��ѯ����ѧ������ѧ�Ѳ�����Ϣ���
			request.setAttribute("rs", StringUtils.formatData(service.getOneRwxfbcList(model.getXh())));

			//ѧ��������Ϣ��ʾ����
			jbxxList = bdpzService.getJbxxpz(RWXFBC);
			request.setAttribute("jbxxList", jbxxList);
			return mapping.findForward("viewRwxfbc");
		} else {
			if(null!=model && null!=model.getGuid() && !"null".equalsIgnoreCase(model.getGuid()) && !"".equalsIgnoreCase(model.getGuid())){
				return updateRwxfDgbc(mapping, form, request, response);
			}else{
				return addRwxfDgbc(mapping, form, request, response);
			}
		}
	}
	
	/**
	 * 
	 * @����: ����ѧ��ѧ�Ѳ���
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-14 ����03:41:14
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
	public ActionForward cancelRwxfbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RwxfbcglService service = new RwxfbcglService();
		
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			
			
			String message = result ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS,num) 
									: MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_CANCEL_NULL);
		}
		
		return null;
	}
	
	/** 
	 * @����: ����ѧ��ѧ�Ѳ���
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-14 ����06:46:56
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
	public ActionForward rwxfPlbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwxfbcglForm model = (RwxfbcglForm) form;
		//ѧ��list
		String[] ids = model.getId();
		String[] xhs = model.getXhs();
		String guid="";
		String xh="";
		if(null!=ids && ids.length>0){
			for (int i=0;i<ids.length;i++){
				if(i==(ids.length-1)){
					guid+=ids[i];
				}else{
					guid+=ids[i]+",";
				}
			}
		}
		if(null!=xhs && xhs.length>0){
			for (int i=0;i<xhs.length;i++){
				if(i==(xhs.length-1)){
					xh+=xhs[i];
				}else{
					xh+=xhs[i]+",";
				}
			}
		}
		model.setGuid(guid);
		model.setXh(xh);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		return mapping.findForward("rwxfPlbc");
	}
	
	/** 
	 * @����: ��������ѧ��ѧ�Ѳ���
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-14 ����06:46:56
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
	public ActionForward savePlbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RwxfbcglForm model = (RwxfbcglForm) form;
		RwxfbcglService service = new RwxfbcglService();
		
		User user = getUser(request);
		
		boolean result = service.savePlbc(model, user);
		String message = result ? MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS) 
				: MessageUtil.getText(MessageKey.SYS_PLBC_FAIL);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
}
