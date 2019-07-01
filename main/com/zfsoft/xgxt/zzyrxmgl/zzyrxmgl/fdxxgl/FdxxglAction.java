package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdxxgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdgl.FdglService;

/** 
 * @��������������������Ŀ����-������Ϣ����action
 * @author��Lu.Yao ��1271��
 * @date��2017-10-16 ����03:55:26 
 */
public class FdxxglAction extends SuperAction {
	
	private static final String url = "zzyrxmgl_fdxxgl.do";
	
	private FdxxglService service = new FdxxglService();

	
	/** 
	 * @description��������ҳ
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����11:24:43 
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
	public ActionForward fdxxglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		
		User user = getUser(request);
		
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "zzyrxmgl_fdxxgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fdxxglManage");
	}
	
	/** 
	 * @description������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����11:24:57 
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
	@SystemLog(description="���ʾ�ҵ����-��ҵ����-��ҵȥ��-�޸�XH:{xh}")
	public ActionForward addFdxxglpj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		FdglService fservice = new FdglService();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.addFdxxpj(model);//��������
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));				
			return null;
		}
		HashMap<String, String> updateForm = fservice.getModelMap2(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs",StringUtils.formatData(model));
		return mapping.findForward("addFdxxglpj");
	}
	
	/** 
	 * @description���鿴
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����11:25:04 
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
	public ActionForward viewFdxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		FdglService fservice = new FdglService();
		model.setFdlx("0");//��������д��¼
		List<HashMap<String,String>> fdjlList = fservice.getFdjlList(model);
		model.setFdlx("1");//����������д��¼
		List<HashMap<String,String>> bfdjlList = fservice.getFdjlList(model);
		HashMap<String, String> updateForm = fservice.getModelMap2(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs",StringUtils.formatData(model));
		request.setAttribute("fdjlList", fdjlList);
		request.setAttribute("bfdjlList", bfdjlList);
		return mapping.findForward("viewFdxxgl");
	}
	
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date 		��2017-12-19 ����02:04:41
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdxxshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		
		User user = getUser(request);
		
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String, String>> resultList = service.getShPageList(model, user);
			
			
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "zzyrxmgl_fdxxsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fdxxshManage");
	}
	
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date 		��2017-12-20 ����08:02:28
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		boolean canSave = true;
		boolean canCancel = true;
		FdglService fdglService = new FdglService();
		if("1".equals(model.getShzt())){//ͬ��ʱ�ж�
			canSave = fdglService.checkYtyfdrs(model);//�ж�ͬ�⸨�������Ƿ񳬳��޶�����			
		}
		if("0".equals(model.getShzt())){//��ͬ��ʱ�ж�
			canCancel = fdglService.checkCancancel(model);
		}
		if(canSave&&canCancel){
			boolean result = service.updateShzt(model,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));	
		}else if(!canSave){
			response.getWriter().print(getJsonMessage("�ѳ����޶������������޷�����ͬ�⸨����"));
		}else if(!(canCancel)){
			response.getWriter().print(getJsonMessage("���и�����¼���޷�ȡ��ͬ�⸨����"));
		}
		return null;
	}
	
	
	
	
}
