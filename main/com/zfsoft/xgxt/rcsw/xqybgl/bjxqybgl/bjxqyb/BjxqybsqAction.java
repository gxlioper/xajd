/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-24 ����08:54:02 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqyb;

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
import xgxt.utils.String.StringUtils;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybcssz.BjxqybcsszForm;
import com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybcssz.BjxqybcsszService;




/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ҽҩ_ѧ���±�����ģ��
 * @�๦������: TODO(������ҽҩ_ѧ���±�_�༶�±�) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-24 ����08:54:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class BjxqybsqAction extends SuperAction<BjxqybsqForm, BjxqybsqService> {
	
	
	private static final String url = "rcsw_xqybgl_bjxqybgl_bjxqybsq.do";
	
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-�б�)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-11 ����03:38:03
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
	public ActionForward bjxqybsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		BjxqybsqForm model = (BjxqybsqForm) form;
		BjxqybsqService service = new BjxqybsqService();		
		if (QUERY.equalsIgnoreCase(model.getType())){				
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
		BjxqybcsszService csszService = new BjxqybcsszService();
		BjxqybcsszForm csszModel = csszService.getModel();
		request.setAttribute("csszModel", csszModel);
		
			
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);	
		
		String path = "rcsw_xqybgl_bjxqybgl_bjxqybsq.do";
		request.setAttribute("path", path);
		
		FormModleCommon.commonRequestSet(request);	
		return mapping.findForward("bjxqybsqManage");
		
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±���������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-7 ����10:05:07
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
	@SystemLog(description="�����ճ�����-�༶ѧ���±�����-�༶�±�����-����")
	public ActionForward addBjxqybsq(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{		
		BjxqybsqForm model = (BjxqybsqForm) form;
		BjxqybsqService service = new BjxqybsqService();
		User user = getUser(request);		
		if(SAVE.equalsIgnoreCase(model.getType()) || SUBMIT.equalsIgnoreCase(model.getType())){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			model.setTxr(user.getUserName());
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			boolean isExist = service.isExistByBjxqybsq(model,SAVE);		
			if(!isExist){
				super.resetToken(request);
				String sqid = UniqID.getInstance().getUniqIDHash();
				model.setSqid(sqid);				
				boolean result = service.saveBjxqybsq(model,user) ;
				String messageKey = "";
				if(SAVE.equalsIgnoreCase(model.getType())){
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				}else{
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XQYB_BJYBSQ_REPEAT));
				return null;
			}
		}						
		request.setAttribute("txr", user.getRealName());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.getDqxqmc());
		this.saveToken(request);
		return  mapping.findForward("addBjxqybsq");
		
	}
	
	
    /**
     * 
     * @����:TODO(�༶ѧ���±�����-�༶�±�����-�޸�)
     * @���ߣ�������[���ţ�995]
     * @���ڣ�2016-4-7 ����02:50:47
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
	@SystemLog(description="�����ճ�����-�༶ѧ���±�����-�༶�±�����-�޸�SQID:{sqid}")
	public ActionForward updateBjxqybsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxqybsqForm model = (BjxqybsqForm) form;
		BjxqybsqService service = new BjxqybsqService();
		
        if (UPDATE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType())){
        	
	    	boolean isExist = service.isExistByBjxqybsq(model,model.getType());
	    	if(!isExist){
	    		boolean result = service.updateBjxqybsq(model);
				String messageKey = "";
	    		if(UPDATE.equalsIgnoreCase(model.getType())){
	    			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
	    		}else{
	    			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
	    		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
	    	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XQYB_BJYBSQ_REPEAT));
				return null;
        	}
			
		}
		BjxqybcsszService bjxqybcsszService = new BjxqybcsszService();
		BjxqybcsszForm jcszModel = bjxqybcsszService.getModel();
		request.setAttribute("jcszModel", jcszModel);		
		
		BjxqybsqForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));		 
		
		//��ȡ��Ϣ
		HashMap<String,String> infoList = service.getBjxqybsqInfo(model);
		request.setAttribute("infoList", StringUtils.formatData(infoList));
		return mapping.findForward("updateBjxqybsq");
	}
	

	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-�ύ)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-5 ����09:36:09
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
	@SystemLog(description="�����ճ�����-�༶ѧ���±�����-�༶�±�����-�ύVALUES:{values}")
	public ActionForward submitBjxqybsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjxqybsqForm model = (BjxqybsqForm) form;
		String sqid = request.getParameter("values");
		String bjdm = request.getParameter("bjdm");
		model.setSqid(sqid);	
		model.setBjdm(bjdm);		
		BjxqybsqService service = new BjxqybsqService();
		//�ж��ύʱ����Ƿ񿪷�
		boolean result = service.submitBjxqybsq(model);
		
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-8 ����09:35:23
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
	@SystemLog(description="�����ճ�����-�༶ѧ���±�����-�༶�±�����-����VALUES:{values}")
	public ActionForward cancelBjxqybsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxqybsqService service = new BjxqybsqService();
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid,lcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			BjxqybsqForm model = new BjxqybsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}			
			service.cancelBjxqybsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-ɾ��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-8 ����01:30:40
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
	@SystemLog(description="�����ճ�����-�༶ѧ���±�����-�༶�±�����-ɾ��VALUES:{values}")
	public ActionForward delBjxqybsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjxqybsqService service = new BjxqybsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteBjxqybsq(values.split(","));
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
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-�鿴)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-8 ����02:58:43
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
	@SystemLog(description="�����ճ�����-�༶ѧ���±�����-�༶�±�����-�鿴SQID:{sqid}")
	public ActionForward viewXqybsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjxqybsqForm model = (BjxqybsqForm) form;
		BjxqybsqService service = new BjxqybsqService();				
		HashMap<String,String> infoList = service.getBjxqybsqInfo(model);
		request.setAttribute("infoList", infoList);
		request.setAttribute("model", model);		
		return mapping.findForward("viewBjxqybsq");	
		
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-�Զ��嵼������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-11 ����03:35:30
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
		BjxqybsqForm model = (BjxqybsqForm) form;
		BjxqybsqService service = new BjxqybsqService();

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
	 * 
	 * @����:TODO(�༶ѧ���±�����-��ѯ�༶)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-13 ����03:14:19
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
	public ActionForward bjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjxqybsqForm model = (BjxqybsqForm) form;
		BjxqybsqService service = new BjxqybsqService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getBjList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		//rcsw_xqybgl_bjxqybgl_bjxqybcsszgl.do
		String path = "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=bjManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjManage");
	}
	
	
}
