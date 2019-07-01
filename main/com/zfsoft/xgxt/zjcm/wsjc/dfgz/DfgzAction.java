/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-2 ����09:00:55 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.dfgz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-3-2 ����09:00:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DfgzAction extends SuperAction<DfgzForm, DfgzService> {
	
	private DfgzService service = new DfgzService(); 
	private static final String url = "cjWsf_dfgz.do";  //
	
	/**
	 * 
	 * @����:��ֹ����ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-2 ����11:22:20
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
	public ActionForward getDfgzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DfgzForm model = (DfgzForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "cjWsf_dfgz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getDfgzList");
	}
	
	/** 
	 * @����:���ô�ֹ���
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-7 ����02:40:42
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
	public ActionForward addDfgz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("xqmc", service.getXpmc(Base.currXq));
		request.setAttribute("nyList", service.getYueFenByXn(Base.currXn));
		request.setAttribute("pfzList", service.getPfzList());
		String path = "cjWsfDfgz.do?method=addDfgz";
		request.setAttribute("path", path);
		//������Ϣ����
		return mapping.findForward("addDfgz");
	}
	
	
	/**
	 * 
	 * @����:�����ֹ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-14 ����08:52:41
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
	public ActionForward saveForm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DfgzForm model = (DfgzForm) form;
	    
		if ("save".equals(model.getType())&&service.wsfTj(model.getDfszid())) {
			response.getWriter().print(getJsonMessage("��������Ѵ���"));
			return null;
		}
		
		boolean result = service.editGz(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	} 
	
	/**
	 * 
	 * @����:���´�ֹ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-14 ����09:10:20
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
	public ActionForward updateDfgz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DfgzForm model = (DfgzForm) form;
		DfgzForm myForm = new DfgzForm();
		myForm = service.getModel(model);
		
		//��ȡѧ������
		List<HashMap<String,String>> xqList = Base.getXqList();
		
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(myForm.getXq())){
				myForm.setXqmc(map.get("xqmc"));
				break;
			}
		}
		
		request.setAttribute("rs", myForm);
		String path = "cjWsfDfgz.do?method=updateDfgz";
		request.setAttribute("path", path);
		//������Ϣ����
		return mapping.findForward("updateDfgz");
	}
	
	
	/**
	 * 
	 * @����:��ѯ���м�¼������json��ʽ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-10 ����05:15:12
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
	public ActionForward gzsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DfgzForm myForm = (DfgzForm) form;
		myForm = service.getAll(myForm);
		JSONObject json = JSONObject.fromBean(myForm);
		response.getWriter().print(json);
		return null;
	}
	
	
	/**
	 * 
	 * @����:�޸ķ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-11 ����03:29:14
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
	@SystemLog(description="���������ִ�ֹ���")
	public ActionForward dfgzXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DfgzForm myForm = (DfgzForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			
			//�����ύ�������֣��������޸Ĵ����
			if(service.wsfTj(myForm.getDfszid())){
				response.getWriter().print(getJsonMessage("�����������ύ���������޸ģ�"));
			}
			
			String pfzJson = request.getParameter("pfzJson");
			List<DfgzForm> pfzList = JsonUtil.jsonArrToList(pfzJson,DfgzForm.class);
			boolean result = service.saveDfgzSz(myForm,pfzList);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		
		boolean isExist = service.getExistCcsj(myForm);
		
		response.getWriter().print(isExist);
		
		return null;
	}
	
	/**
	 * 
	 * @����:�����趨
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-12 ����02:58:57
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
	public ActionForward gzsd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DfgzForm myForm = (DfgzForm) form;
		request.setAttribute("xqmc", service.getXpmc(Base.currXq));
		request.setAttribute("rs", service.getModel(myForm));
		
		return mapping.findForward("gzsd");
	}
	
	
	/**
	 * 
	 * @����:�������list
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-15 ����02:41:10
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
	public ActionForward getCcqsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DfgzForm model = (DfgzForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getCcqsList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "cjWsf_ccqs.do";
		request.setAttribute("path", path);
		request.setAttribute("tjzt", model.getTjzt());
		request.setAttribute("dfszid", model.getDfszid());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getCcqsList");
	}
	
	
	/**
	 * 
	 * @����:ɾ����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-15 ����03:14:16
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
	public ActionForward delGzsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			//ûɾ��Ӱ��Ҳ���Ǻܴ󣬾�û������
			service.delPfzQs(ids);
			service.delPfzSz(ids);
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
	 * @����:����鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-15 ����05:08:07
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
	public ActionForward viewGzsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DfgzForm model = (DfgzForm) form;
		DfgzForm myForm = new DfgzForm();
		myForm = service.getModel(model);
		
		//��ȡѧ������
		List<HashMap<String,String>> xqList = Base.getXqList();
		
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(myForm.getXq())){
				myForm.setXqmc(map.get("xqmc"));
				break;
			}
		}
		request.setAttribute("rs", myForm);
		request.setAttribute("pfzszList", service.pfzszList(myForm.getDfszid()));
		return mapping.findForward("viewGzsz");
	}

	
}
