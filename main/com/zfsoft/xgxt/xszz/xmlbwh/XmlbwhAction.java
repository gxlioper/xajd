/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmlbwh;

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
 * @ģ������: ѧ������
 * @�๦������: ��Ŀ���ά��
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmlbwhAction extends SuperAction {
	private String messageKey;
	
	private static final String url = "xszz_xmlbwh.do?method=xmlbwhCx";
	
	/**
	 * 
	 * @����:��ͨ��ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xmlbwhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmlbwhForm myForm = (XmlbwhForm) form;
		XmlbwhService service = new XmlbwhService();

		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service
					.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String path = "xszz_xmlbwh.do?method=xmlbwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("xmlbwhCx");
	}

	/**
	 * 
	 * @����:���ӷ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-����������-����LBMC:{lbmc}")
	public ActionForward xmlbwhZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmlbwhForm myForm = (XmlbwhForm) form;
		XmlbwhService service = new XmlbwhService();

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if(service.isRepeat(myForm)){//�����ظ���֤
				messageKey = MessageKey.XSZZ_XMLBMC_EXIST;
				response.getWriter().print(getJsonResult(messageKey,false));
				return null;
			}
			int newId = service.getNewId();
			myForm.setLbdm(newId + "");
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		return mapping.findForward("xmlbwhZj");
	}

	/**
	 * 
	 * @����:�޸ķ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-����������-�޸�LBDM:{lbdm},LBMC:{lbmc}")
	public ActionForward xmlbwhXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmlbwhForm myForm = (XmlbwhForm) form;
		XmlbwhService service = new XmlbwhService();

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if(service.isRepeat(myForm)){//�����ظ���֤
				messageKey = MessageKey.XSZZ_XMLBMC_EXIST;
				response.getWriter().print(getJsonResult(messageKey,false));
				return null;
			}
			if(service.isRalate(myForm)){//������
				messageKey = MessageKey.XSZZ_XMLB_NOTUPDATE;
				response.getWriter().print(getJsonResult(messageKey,false));
				return null;
			}
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		XmlbwhForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		return mapping.findForward("xmlbwhXg");
	}

	/**
	 * 
	 * @����:ɾ������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-����������-ɾ��VALUES:{values}")
	public ActionForward xmlbwhSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmlbwhService service = new XmlbwhService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// //�жϺϷ���////
			if(service.isRalate(values)){//������
				messageKey = MessageKey.XSZZ_XMLB_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey,false));
				return null;
			}
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_SUCCESS);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;

	}

}
