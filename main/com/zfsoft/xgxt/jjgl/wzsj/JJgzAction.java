/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-11 ����02:15:56 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-9-11 ����02:15:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JJgzAction extends SuperAction<JJgzForm, JJgzService> {
	/**
	 *  @���ԣ� PATH ·��
	 */
	private static final String PATH = "jjgl_jjgz.do";
	
	private static final String url = "jjgl_jjgz.do";
	
	/**
	 * 
	 * @����:��ѯҳ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-26 ����06:43:32
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
	public ActionForward jjgzCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻�ʻ����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jjgzCx");
	}
	
	
	/**
	 * 
	 * @����:�б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-27 ����09:43:49
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
	public ActionForward queryJJgzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JJgzForm model = (JJgzForm) form;
		
		//��ѯ
		List<HashMap<String,String>> resultList = getService().getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-9-12 ����10:37:39
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
	public ActionForward jjgzXz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("jjgzXz");
	}
	/**
	 * 
	 * @����:�����ύ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-9-12 ����10:37:39
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
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JJgzForm model = (JJgzForm) form;
		model.setJlsj(DateUtils.getCurrTime());
		model.setSid(UniqID.getInstance().getUniqIDHash().toUpperCase());
		boolean isSuccess = getService().runInsert(model);
		JSONObject message = null;
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @����:�޸�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-9-12 ����10:37:39
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
	public ActionForward jjgzXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JJgzForm model = (JJgzForm) form;
		if(StringUtils.isNotBlank(model.getSid())){
			JJgzForm dataModel = getService().getModel(model.getSid());
			BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(dataModel));
		}
		return mapping.findForward("jjgzXg");
	}
	/**
	 * 
	 * @����:�޸��ύ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-9-12 ����10:37:39
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
	public ActionForward submitXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JJgzForm model = (JJgzForm) form;
		model.setJlsj(DateUtils.getCurrTime());
		boolean isSuccess = getService().runUpdate(model);
		JSONObject message = null;
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-9-12 ����10:37:39
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
	public ActionForward jjgzCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JJgzForm model = (JJgzForm) form;
		
		if(StringUtils.isNotBlank(model.getSid())){
			JJgzForm modelMap = getService().getModel(model.getSid());
			request.setAttribute("modelMap", xgxt.utils.String.StringUtils.formatData(modelMap));
		}
		
		return mapping.findForward("jjgzCk");
	}
	
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-9-12 ����10:37:39
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
	public ActionForward jjgzSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JJgzForm model = (JJgzForm) form;
		if(StringUtils.isNotBlank(model.getSid())){
			boolean isSuccess = getService().runDelete(model.getSid().split(",")) > 0;
			JSONObject message = null;
			message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(message);
		}
		return null;
	}
}
