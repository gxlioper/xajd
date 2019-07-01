/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-31 ����01:56:05 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybsh;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
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




/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ҽҩ��ѧ_ѧ���±�����ģ��
 * @�๦������: TODO(������ҽҩ��ѧ_ѧ���±�_�༶ѧ�����) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-31 ����01:56:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class BjxqybshAction extends SuperAction<BjxqybshForm, BjxqybshService> {

	private static final String url = "rcsw_xqybgl_bjxqybgl_bjxqybsh.do";
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����02:06:51
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
	public ActionForward bjxqybshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		BjxqybshForm model = (BjxqybshForm) form;
		BjxqybshService service = new BjxqybshService();
		User user = getUser(request);
		if(QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ�������
			List<HashMap<String,String>> resultList = null;
			resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
			
		}
		String path = "rcsw_xqybgl_bjxqybgl_bjxqybsh.do";
		request.setAttribute("path",path);
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});		
		request.setAttribute("searchTj", searchModel);		
		FormModleCommon.commonRequestSet(request);						
		return mapping.findForward("bjxqybshManage");
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����02:07:03
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
	public ActionForward bjxqybDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BjxqybshForm model =  (BjxqybshForm)form;
		BjxqybshService service = new BjxqybshService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){	
			User user = getUser(request);
			//���浥�����
			boolean result = service.saveSh(model,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		User user = getUser(request);
		model.setSqid(request.getParameter("sqid"));
		//���������Ϣ
		HashMap<String,String> infoList = service.getBjxqybshInfo(user,model);
		request.setAttribute("infoList", infoList);
		
		//BjxqybshForm updateForm = service.getModel(model);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		//BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		
		
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("bjxqybDgsh");
		
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����_�༶ѧ����ˡ�����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����02:05:01
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
	@SystemLog(description="ѧ���±�_�༶ѧ���±�����_�༶ѧ����ˡ�����")
	public ActionForward cancelBjxqybsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjxqybshForm model = (BjxqybshForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		
		BjxqybshService service = new BjxqybshService();
		//�����𳵳˳�������ˣ����һ����
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����_�༶ѧ����ˡ��������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-11 ����04:56:49
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
	public ActionForward bjxqybPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjxqybshForm model = (BjxqybshForm) form;
		BjxqybshService service = new BjxqybshService();
		User user = getUser(request);
		
		if("SAVE".equalsIgnoreCase(model.getType())){		
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("bjxqybPlsh");
	}
}
