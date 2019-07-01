/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-1 ����09:03:40 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcrc;

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
import com.zfsoft.xgxt.gygl.wsjc.jcxm.JcxmModel;
import com.zfsoft.xgxt.gygl.wsjc.jcxm.JcxmService;

/** 
 * @�๦������: ����ճ�
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-1 ����09:03:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcrcAction extends SuperAction<JcrcModel, JcrcService> {

	private static final String url = "gygl_wsjc_jcrc.do";
	
	/**�������-- ����ճ�**/
	@SystemAuth(url = url)
	public ActionForward jcrcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "gygl_wsjc_jcrc.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jcrcList");
	}
	
	
	/**�������--����ճ�**/
	@SystemAuth(url = url)
	public ActionForward getJcrcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JcrcService service = getService();
		JcrcModel model = (JcrcModel) form;
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**�������--����**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JcxmService jcxmService = new JcxmService();
		
		request.setAttribute("jcxmList", jcxmService.getAllList(new JcxmModel()));
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		return mapping.findForward("add");
	}
	
	
	/**�������--�޸�**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JcrcService service = getService();
		JcrcModel myForm = (JcrcModel) form;
		JcxmService jcxmService = new JcxmService();
		
		JcrcModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		
		String[] rcxmArr = service.getRcxmArr(model.getId());
		request.setAttribute("rcxmArr", rcxmArr);
		request.setAttribute("jcxmList", jcxmService.getAllList(new JcxmModel()));
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("edit");
	}
	
	
	
	/**ɾ������ճ� */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʹ�Ԣ����-�������-����ճ�-ɾ��ID:{ids}")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		JcrcService service = getService();
		
		if (!StringUtil.isNull(ids)){
			int num = service.runDelete(ids.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.XYJD_DELETE_DMEXIST,"����ճ�");
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	
	/**����ճ�--�ύ**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String ids = request.getParameter("ids");
		JcrcService service = getService();
		
		if (!StringUtil.isNull(ids)){
			boolean result = service.runSubmit(ids.split(","));
			if(result) {
				service.saveSubmit(ids.split(","));
			}
			String message = result ? MessageUtil.getText(MessageKey.SYS_SUBMIT_SUCCESS) 
									: MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	
	
	/**����ճ�--ȡ���ύ**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String ids = request.getParameter("ids");
		JcrcService service = getService();
		
		if (!StringUtil.isNull(ids)){
			boolean result = service.runCancel(ids.split(","));
			if(result) {
				service.delCancel(ids.split(","));
			}	
			String message = result ? MessageUtil.getText(MessageKey.SYS_QXCP_SUCCESS) 
									: MessageUtil.getText(MessageKey.SYS_QXCP_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
}
