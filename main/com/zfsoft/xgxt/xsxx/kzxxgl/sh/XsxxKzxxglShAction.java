/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-19 ����09:32:32 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl.sh;

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

import com.zfsoft.extend.service.ExtendService;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgForm;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-19 ����09:32:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxKzxxglShAction extends SuperAction<XsxxKzxxglShForm, XsxxKzxxglShService> {

	private XsxxKzxxglShService service = new XsxxKzxxglShService();
	
	private ExtendService extendService = new ExtendService();
	
	private XsxxKzxxglJgService jgService = new XsxxKzxxglJgService();
	
	private static final String DATA_EXTEND_MODULE = "XSXX";
	
	private static final String url = "xsxx_kzxxgl_shgl.do";
	
	/**
	 * 
	 * @����:��������б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-4 ����04:44:38
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxKzxxglShForm model  = (XsxxKzxxglShForm) form;
		User user = getUser(request);
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if (QUERY.equalsIgnoreCase(model.getActionType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xsxx_kzxxgl_shgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("shManage");
	}
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-19 ����05:47:33
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
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxKzxxglShForm model  = (XsxxKzxxglShForm) form;
		XsxxKzxxglShForm dataModel = service.getModel(model.getSqid());
		if(null != dataModel){
			BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		}
		model.setExendDateModuleId(DATA_EXTEND_MODULE);
		return mapping.findForward("ck");
	} 
	
	/**
	 * 
	 * @����:���
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����01:38:49
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
	public ActionForward sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxKzxxglShForm model  = (XsxxKzxxglShForm) form;
		String xtgwid = model.getXtgwid();
		String shid = model.getShid();
		XsxxKzxxglShForm dataModel = service.getModel(model.getSqid());
		if(null != dataModel){
			BeanUtils.copyProperties(model, dataModel);
			model.setShid(shid);
			model.setXtgwid(xtgwid);
		}
		model.setExendDateModuleId(DATA_EXTEND_MODULE);
		return mapping.findForward("sh");
	}
	
	
	/**
	 * 
	 * @����:�ύ���
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����02:14:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward shAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxKzxxglShForm model  = (XsxxKzxxglShForm) form;
		User user = getUser(request);
		//�����������
		boolean result = service.saveSh(model,user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����03:22:24
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
	public ActionForward cancelShAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxKzxxglShForm model  = (XsxxKzxxglShForm) form;

		XsxxKzxxglShForm dataModel = service.getModel(model.getSqid());
		
		boolean isSuccess = false;
		
		if(dataModel != null){
			dataModel.setShzt(Constants.YW_SHZ);
			isSuccess = service.runUpdate(dataModel); //���� Model
			if(isSuccess){
				//ɾ�������¼
				XsxxKzxxglJgForm jgModel = jgService.getModelBySqid(dataModel.getSqid());
				if(jgModel != null){
					isSuccess = jgService.deleteJgBySqid(jgModel.getSqid());
					//ɾ������
					String jgid = jgModel.getJgid();
					isSuccess = extendService.deleteData(jgid, DATA_EXTEND_MODULE);
				}
			}
		}
		
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
	}
	
	
}
