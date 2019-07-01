/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-4 ����04:56:01 
 */  
package com.zfsoft.xgxt.szdw.fdyrz;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
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
import com.zfsoft.xgxt.base.message.MessageKey;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ�� 
 * @�๦������: TODO ����Ա��ְ���� ��ְ���
 * @���ߣ� zhangjw 
 * @ʱ�䣺 2013-6-4 ����04:56:01 
 * @�汾�� V5.8.16
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdyrzshAction  extends SuperAction{
	
	private static final String url = "szdw_fdyrz_sh.do?method=gjcxRzsh";

	/**
	 * @����:����Ա��ְ��˸߼�ģʽ��ѯ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-18 ����04:22:47
	 * @�޸ļ�¼: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward �������� 
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward gjcxRzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdyrzsqForm myForm = (FdyrzsqForm) form;
		FdyrzshService service = new FdyrzshService();
		
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "szdw_fdyrz_sh.do?method=gjcxRzsh";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("gjcxFdyrzsh");
	}
	/**
	 * @����:����Ա��ְ���
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-5 ����10:39:42
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
	public ActionForward fdyrzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdyrzsqForm myForm = (FdyrzsqForm) form;
		FdyrzshService service = new FdyrzshService();
		FdyrzsqService sqservice = new FdyrzsqService();
		User user = getUser(request);
		FdyrzsqForm model = sqservice.getModel(myForm);
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			myForm.setSplc(model.getSplc());
			boolean result = service.fdyrzsh(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		model.setShid(myForm.getShid());
		HashMap<String,String> map = sqservice.getFdyjbxx(model.getZgh());
		request.setAttribute("rs", StringUtils.formatData(map));
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("fdyrzsh");
	}
	
	/**
	 * 
	 * @����:TODO(��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-10 ����04:23:59
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
	public ActionForward cancelFdyrzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyrzsqForm model = (FdyrzsqForm) form;
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		FdyrzshService service = new FdyrzshService();
		//�����ճ���Ϊ��ˣ����һ����
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ������˱���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-29 ����02:17:49
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
	public ActionForward fdyrzPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdyrzsqForm model = (FdyrzsqForm) form;
		FdyrzshService service = new FdyrzshService();
		
		User user = getUser(request);
		
		if("save".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model,user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		
		return mapping.findForward("fdyrzPlsh");
	}
	
}
