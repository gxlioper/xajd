/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-14 ����01:46:17 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.xslxwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-5-14 ����01:46:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XslxwhAction extends SuperAction{
	private XslxwhService service = new XslxwhService();
	
	private static final String url = "tsxsgl_xslxwh.do";
	
	/**
	 * 
	 * @����:ѧ�������б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-15 ����12:58:56
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
	public ActionForward xslxwhManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XslxwhForm model = (XslxwhForm) form;
		if (QUERY.equals(model.getType())) {
		List<HashMap<String, String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	request.setAttribute("path", "tsxsgl_xslxwh.do");
	FormModleCommon.commonRequestSet(request);
	return mapping.findForward("xslxManage");
	}
	/**
	 * 
	 * @����:��ȡѧ����������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-14 ����09:23:09
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
	public ActionForward getXslxmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XslxwhForm myForm = (XslxwhForm) form;
		String xslxdms = myForm.getXslxdm();
		String xslxmc = "";
		if(xslxdms!=null){
			xslxmc =service.getXslxMc(xslxdms);
		}
		response.getWriter().print(xslxmc);
		return null;
	}
	/**
	 * 
	 * @����:����ѧ������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-15 ����04:30:36
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
	@SystemLog(description="����ѧ����Ϣ-����ѧ������-ѧ������ά��-����XSLXDM:{xslxdm},XSLXMC:{xslxmc}")
	public ActionForward addXslx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XslxwhForm model = (XslxwhForm) form;
		
		if (SAVE.equals(model.getType())) {
			boolean result = service.addXslx(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("addXslx");
	}

	/**
	 * 
	 * @����:�޸�ѧ�����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-2 ����03:50:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-����ѧ������-ѧ������ά��-�޸�XSLXDM:{xslxdm},XSLXMC:{xslxmc}")
	public ActionForward updateXslx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XslxwhForm myForm = (XslxwhForm) form;
		
		XslxwhForm model = service.getModel(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = service.updateXslx(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateXslx");
	}

	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-2 ����04:03:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-����ѧ������-ѧ������ά��-ɾ��XSLXDM:{values}")
	public ActionForward delXslx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		String[] dms=values.split(",");
		if (!StringUtil.isNull(values)) {
			//�ж�ѧ������Ƿ�ռ��
			if (service.isUsed(values)) {
				String messageKey = "��ѧ������ѱ�ʹ�ã�������ɾ����";
				response.getWriter().print(getJsonMessageResult(messageKey, false));
				return null;
			}
			int num = service.runDelete(dms);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}

}
