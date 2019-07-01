/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-23 ����08:36:58 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.jcsz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������� 
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-23 ����08:36:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcszAction extends SuperAction {
	
	private static final String url = "xljk_xlwygl_jcsz.do";

	/**
	 * @���� ����������
	 */
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	/**
	 * 
	 * @����:��ѯ������������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-3 ����03:34:13
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
	public ActionForward cxJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		JcszService service = new JcszService();
		
		JcszForm model = (JcszForm)	form;

		JcszForm jcsz = service.getJcsz();
		
		if(null != jcsz){
			request.setAttribute("code", "1");
			BeanUtils.copyProperties(model, jcsz);
		}else{
			request.setAttribute("code", "0");
		}
		
		// ����Ϊ����������
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("xlzx"));
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "xljk_xlwygl_jcsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxJcsz");
	}
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-4 ����11:46:06
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
	public ActionForward bcJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcszService service = new JcszService();
		JcszForm model = (JcszForm)	form;
		boolean result = false;
		JSONObject message = null;

		result = service.saveJcsz(model);
		
		message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);

		response.getWriter().print(message);
		
		return null;
	}
}
