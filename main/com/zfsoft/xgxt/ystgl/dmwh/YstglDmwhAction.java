/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-01-07 ����02:24:28 
 */
package com.zfsoft.xgxt.ystgl.dmwh;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.xmlbgl.XmlbglService;
/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2016-01-07 ����02:24:28
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class YstglDmwhAction extends SuperAction {
	private YstglDmwhService service = new YstglDmwhService();
	private static final String url = "ystgl_jcsz_dmwh.do";
	
	/**
	 * 
	 * @����:����������б�
	 * @���ߣ�xiaixa [���ţ�1104]
	 * @���ڣ�2016-01-07 ����02:38:34
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
	@SystemAuth(url = url)
	public ActionForward getYstlbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm model = (YstglDmwhForm) form;
		if (QUERY.equals(model.getType())) {
			String ystlbmc = request.getParameter("ystlbmc"); 
			if(null!=ystlbmc){
			ystlbmc = URLDecoder.decode(URLDecoder.decode(ystlbmc,"UTF-8"),"UTF-8");
			model.setYstlbmc(ystlbmc);
			}
			List<HashMap<String, String>> resultList = service.getYstlbList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getYstlbList");
	}
	/**
	 * 
	 * @����:�ҿ���λ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-1-14 ����04:09:27
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
	public ActionForward getGkdwList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm model = (YstglDmwhForm) form;
		if (QUERY.equals(model.getType())) {
			String ystlbmc = request.getParameter("gkdwmc"); 
			if(null!=ystlbmc){
			ystlbmc = URLDecoder.decode(URLDecoder.decode(ystlbmc,"UTF-8"),"UTF-8");
			model.setYstlbmc(ystlbmc);
			}
			List<HashMap<String, String>> resultList = service.getGkdwList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getGkdwList");

	}

	/**
	 * 
	 * @����:�������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-01-07 ����03:44:04
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
	public ActionForward addYstlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm model = (YstglDmwhForm) form;
		if (SAVE.equals(model.getType())) {
			boolean result = service.addYstlb(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("addYstlb");
	}

	/**
	 * 
	 * @����:�޸����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-01-07 ����03:50:04
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
	@SystemLog(description="�����Ź���-����ά��-���������ά��-�޸�ystlb:{ystlbdm}")
	public ActionForward updateYstlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm myForm = (YstglDmwhForm) form;
		YstglDmwhForm model = service.getYstlb(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = service.updateYstlb(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateYstlb");
	}

	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-01-07 ����04:03:00
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
	@SystemLog(description="�����Ź���-����ά��-���������ά��-ɾ��values:{values}")
	public ActionForward delYstlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//�ж�����������Ƿ�ռ��
			if (service.isUsed(values)) {
				String messageKey = MessageKey.YSTGL_JCSZ_YSTLB_USED;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			int num = service.deleteYstlb(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}
	
	/**
	 * 
	 * @����:��Ŀ����б�
	 * @���ߣ�xiaixa [���ţ�1104]
	 * @���ڣ�2016-01-07 ����02:38:34
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
	@SystemAuth(url = url)
	public ActionForward getXmlbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm model = (YstglDmwhForm) form;
		if (QUERY.equals(model.getType())) {
			String Xmlbmc = request.getParameter("Xmlbmc"); 
			if(null!=Xmlbmc){
			Xmlbmc = URLDecoder.decode(URLDecoder.decode(Xmlbmc,"UTF-8"),"UTF-8");
			model.setXmlbmc(Xmlbmc);
			}
			List<HashMap<String, String>> resultList = service.getXmlbList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXmlbList");

	}

	/**
	 * 
	 * @����:������Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-01-07 ����03:44:04
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
	public ActionForward addXmlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm model = (YstglDmwhForm) form;
		if (SAVE.equals(model.getType())) {
			boolean result = service.addXmlb(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("ystlbList", service.getYstlbListAll());
		return mapping.findForward("addXmlb");
	}

	/**
	 * 
	 * @����:�޸���Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-01-07 ����03:50:04
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
	@SystemLog(description="�����Ź���-����ά��-��Ŀ���ά��-�޸�Xmlb:{Xmlbdm}")
	public ActionForward updateXmlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm myForm = (YstglDmwhForm) form;
		YstglDmwhForm model = service.getXmlb(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = service.updateXmlb(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("ystlbList", service.getYstlbListAll());
		return mapping.findForward("updateXmlb");
	}

	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-01-07 ����04:03:00
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
	@SystemLog(description="�����Ź���-����ά��-��Ŀ���ά��-ɾ��values:{values}")
	public ActionForward delXmlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//�ж���Ŀ����Ƿ�ռ��
			if (service.isExistsXmlbData(values)) {
				String messageKey = MessageKey.YSTGL_JCSZ_XMLB_USED;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			int num = service.deleteXmlb(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addGkdw(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm model = (YstglDmwhForm) form;
		if (SAVE.equals(model.getType())) {
			boolean result = service.addGkdw(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("addGkdw");
	}

	/**
	 * 
	 * @����:�޸���Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-01-14 ����03:50:04
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
	@SystemLog(description="�����Ź���-����ά��-�ҿ���λά��-�޸�Gkdw:{Gkdwdm}")
	public ActionForward updateGkdw(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm myForm = (YstglDmwhForm) form;
		YstglDmwhForm model = service.getGkdw(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = service.updateGkdw(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateGkdw");
	}

	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-01-14 ����04:03:00
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
	@SystemLog(description="�����Ź���-����ά��-�ҿ���λά��-ɾ��values:{values}")
	public ActionForward delGkdw(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//�жϹҿ���λ�Ƿ�ռ��
			if (service.isExistsGkdwData(values)) {
				String messageKey = MessageKey.YSTGL_JCSZ_GKDW_USED;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			int num = service.deleteGkdw(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	
}
