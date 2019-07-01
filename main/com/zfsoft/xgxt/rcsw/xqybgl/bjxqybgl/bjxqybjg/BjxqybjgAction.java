/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-31 ����04:43:01 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybjg;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybcssz.BjxqybcsszForm;
import com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybcssz.BjxqybcsszService;



/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ҽҩ_�༶�±�����ģ��
 * @�๦������: TODO(������ҽҩ_�༶�±�����ģ��_�༶ѧ����) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-31 ����04:43:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class BjxqybjgAction extends SuperAction<BjxqybjgForm, BjxqybjgService> {
	
	private static final String url = "rcsw_xqybgl_bjxqybgl_bjxqybjg.do";
	
	/**
	 * 
	 * @����:TODO(������ҽҩ_�༶�±�_�༶ѧ����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����01:22:35
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
	public ActionForward bjxqybjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		BjxqybjgForm model = (BjxqybjgForm) form;
		BjxqybjgService service = new BjxqybjgService();
		
		if(QUERY.equals(model.getType())){
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);			
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;			
		}
		String path = "rcsw_xqybgl_bjxqybgl_bjxqybjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("bjxqybjgManage");
	}
	
	/**
	 * 
	 * @����:TODO(�༶�±�����ģ��_�༶ѧ����-���Ӱ༶ѧ���±����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-8 ����05:19:28
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
	@SystemLog(description="�����ճ�����-�༶�±�����ģ��_�༶ѧ����-����")
	public ActionForward addBjxqybjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxqybjgForm model = (BjxqybjgForm) form;
		BjxqybjgService service = new BjxqybjgService();
		User user = getUser(request);	
	
		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			model.setTxr(user.getUserName());
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			model.setTxsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
	    	boolean isExist = service.isExistByBjxqybjg(model,SAVE);
			if (!isExist) {
				super.resetToken(request);
				// ��Ӱ༶ѧ���±����
				boolean result = service.saveBjxqybjg(model,user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XQYB_BJYBJG_REPEAT));
				return null;
			}
		}				
		
		request.setAttribute("txr", user.getRealName());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.getDqxqmc());
		this.saveToken(request);
		return mapping.findForward("addBjxqybjg");
		
	}
	
	
	
	/**
	 * 
	 * @����:TODO(�༶�±�����ģ��_�༶ѧ����-����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����01:28:27
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
	@SystemLog(description="�����ճ�����-�༶�±�����ģ��_�༶ѧ����-����")
	public ActionForward updateBjxqybjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxqybjgForm model = (BjxqybjgForm) form;
		BjxqybjgService service = new BjxqybjgService();
		
        if (UPDATE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType())){
        	
	    	boolean isExist = service.isExistByBjxqybjg(model,model.getType());
	    	if(!isExist){
	    		boolean result = service.updateBjxqybjg(model);
				String messageKey = "";
	    		if(UPDATE.equalsIgnoreCase(model.getType())){
	    			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
	    		}else{
	    			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
	    		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
	    	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XQYB_BJYBJG_REPEAT));
				return null;
        	}
			
		}
        
		//BjxqybcsszAction
		BjxqybcsszService bjxqybcsszService = new BjxqybcsszService();
		BjxqybcsszForm jcszModel = bjxqybcsszService.getModel();
		request.setAttribute("jcszModel", jcszModel);		
		
		BjxqybjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));		 
		
		//��ȡ��Ϣ
		HashMap<String,String> infoList = service.getBjxqybjgInfo(model);
		request.setAttribute("infoList", StringUtils.formatData(infoList));
		return mapping.findForward("updateBjxqybjg");
	}
	
	/**
	 * 
	 * @����:TODO(�༶�±�����ģ��_�༶ѧ����-ɾ��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-11 ����10:19:02
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
	@SystemLog(description="�����ճ�����-�༶�±�����ģ��_�༶ѧ����-ɾ��")
	public ActionForward delBjxqybjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxqybjgService service = new BjxqybjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteBjxqybjg(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(�༶�±�����ģ��_�༶ѧ����-�Զ��嵼������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����01:31:17
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
		BjxqybjgForm model = (BjxqybjgForm) form;
		BjxqybjgService service = new BjxqybjgService();

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
	
	/** 
	 * @����:�ϲ�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-29 ����05:43:47
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
	public ActionForward hbdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxqybjgForm model = (BjxqybjgForm) form;
		BjxqybjgService service = new BjxqybjgService();

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getHbdcList(model, user);
		File file = service.getHbExecl(resultList);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/** 
	 * @����:�鿴�༶ѧ���±����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-1-12 ����04:27:03
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
	public ActionForward viewXqybjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjxqybjgForm model = (BjxqybjgForm) form;
		BjxqybjgService service = new BjxqybjgService();				
		HashMap<String,String> infoList = service.getBjxqybjgInfo(model);
		request.setAttribute("infoList", infoList);		
		return mapping.findForward("viewBjxqybjg");	
		
	}

}
