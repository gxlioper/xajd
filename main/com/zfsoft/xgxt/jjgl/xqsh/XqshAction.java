/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-26 ����06:07:13 
 */  
package com.zfsoft.xgxt.jjgl.xqsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;

import com.zfsoft.xgxt.jjgl.jjxq.JjxqService;
import com.zfsoft.xgxt.jjgl.zcyh.ZcyhService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-26 ����06:07:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqshAction extends SuperAction<XqshForm, XqshService> {

	/**
	 *  @���ԣ� PATH ·��
	 */
	private static final String PATH = "jjgl_xqsh.do";
	
	private static final String url = "jjgl_xqsh.do";
	/**
	 * 
	 * @����:�������ҳ��
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
	public ActionForward xqshCx(ActionMapping mapping, ActionForm form,
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
		return mapping.findForward("xqshCx");
	}
	
	/**
	 * 
	 * @����:��ѯ�ҽ������б�
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
	public ActionForward queryXqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XqshForm model = (XqshForm) form;
		
		//��ѯ
		List<HashMap<String,String>> resultList = getService().getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-27 ����10:16:31
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
	public ActionForward xqDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqshForm model = (XqshForm) form;
		String xqid = model.getXqid();
		//�ҽ�������Ϣ
		if(StringUtils.isNotBlank(xqid)){
			HashMap<String , String> xqModelMap = new JjxqService().getModelMap(xqid);
			request.setAttribute("xqModelMap", xgxt.utils.String.StringUtils.formatData(xqModelMap));
			//��Ů��Ϣ
			if(xqModelMap != null && StringUtils.isNotBlank(xqModelMap.get("znid"))){
				HashMap<String , String> znxxMap = new ZcyhService().getZnxxMapById(xqModelMap.get("znid"));
				request.setAttribute("znxxMap", znxxMap);
			}
		}
		return mapping.findForward("xqDgsh2th");
	}
	
	/**
	 * 
	 * @����:���
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-27 ����11:20:05
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
	public ActionForward audit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqshForm model = (XqshForm) form;
		boolean result = false;
		JSONObject message = null;
		result = getService().audit(model);
		message = getJsonMessageByKey(result ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_ERROR );
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-27 ����11:20:05
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
	public ActionForward xqshck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqshForm model = (XqshForm) form;
		String xqid = model.getXqid();
		//�ҽ�������Ϣ
		if(StringUtils.isNotBlank(xqid)){
			HashMap<String , String> xqModelMap = new JjxqService().getModelMap(xqid);
			request.setAttribute("xqModelMap", xgxt.utils.String.StringUtils.formatData(xqModelMap));
			//��Ů��Ϣ
			if(xqModelMap != null && StringUtils.isNotBlank(xqModelMap.get("znid"))){
				HashMap<String , String> znxxMap = new ZcyhService().getZnxxMapById(xqModelMap.get("znid"));
				request.setAttribute("znxxMap", znxxMap);
			}
		}
		return mapping.findForward("xqshck");
	}
	
	
	/**
	 * 
	 * @����:��������޸�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-27 ����10:16:31
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
	public ActionForward xqshxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqshForm model = (XqshForm) form;
		String xqid = model.getXqid();
		
		if(StringUtils.isNotBlank(xqid)){
			HashMap<String , String> xqModelMap = new JjxqService().getModelMap(xqid);
			request.setAttribute("xqModelMap", xgxt.utils.String.StringUtils.formatData(xqModelMap));
		}
		
		return mapping.findForward("xqshxg");
	}
}
