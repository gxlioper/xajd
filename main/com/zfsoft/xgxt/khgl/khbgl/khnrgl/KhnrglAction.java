/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-13 ����11:36:27 
 */
package com.zfsoft.xgxt.khgl.khbgl.khnrgl;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.khgl.khbgl.KhbglService;

/**
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: �������ݹ���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-13 ����11:36:27
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KhnrglAction extends SuperAction<KhnrglForm, KhnrglService> {
	private KhbglService khbService = new KhbglService();
	private KhnrglService service = new KhnrglService();
	
	private static final String url = "khgl_khbgl_khbgl.do";

	/**
	 * 
	 * @����:��ѯ���������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-13 ����01:54:11
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
	public ActionForward getKhnrglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhnrglForm model = (KhnrglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			List<HashMap<String, String>> resultList = service.getKhnrList(
					model.getKhbid());
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "khgl_khbgl_khbgl.do");
		FormModleCommon.commonRequestSet(request);
		//���˱���Ϣ
		HashMap<String,String> khbMap = khbService.getKhb(model.getKhbid());
		request.setAttribute("khbid", model.getKhbid());
		request.setAttribute("khbMap", StringUtils.formatData(khbMap));
		return mapping.findForward("getKhnrglList");
	}

	/**
	 * 
	 * @����:������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-13 ����05:27:51
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
	public ActionForward addKhnr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhnrglForm model = (KhnrglForm) form;
		//Ĭ�Ϸ�ֵ���ͣ�����
		model.setFzlx("0");
		List<HashMap<String, String>> fzlxList = new OptionUtil().getOptions(OptionUtil.FZLX);// ��ֵ����
		request.setAttribute("fzlxList", fzlxList);
		request.setAttribute("khbid", model.getKhbid());
		return mapping.findForward("addKhnr");
	}

	/**
	 * 
	 * @����:�޸Ŀ�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-13 ����01:55:20
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
	public ActionForward updateKhnr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhnrglForm myForm = (KhnrglForm) form;
		String sfypf=request.getParameter("sfypf");
		KhnrglForm KhnrglForm = service.getModel(myForm);
		if (null != KhnrglForm) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(KhnrglForm));
			request.setAttribute("KhnrglForm", KhnrglForm);
		}
		List<HashMap<String, String>> fzlxList = new OptionUtil().getOptions(OptionUtil.FZLX);// ��ֵ����
		
		HashMap<String, String> khnrMap = service.getKhnr(myForm);
		//�Ƿ������֣������ֿ��˱������ӡ�ɾ�����ܲ������޸ģ�ֻ�ܶ�һ��ָ�ꡢ����ָ�ꡢ���������޸ģ�
		request.setAttribute("sfypf", sfypf);
		request.setAttribute("fzlxList", fzlxList);
		request.setAttribute("rs", StringUtils.formatData(khnrMap));
		return mapping.findForward("updateKhnr");
	}

	/**
	 * 
	 * @����:�鿴��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-13 ����01:55:20
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
	public ActionForward viewKhnrgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhnrglForm myForm = (KhnrglForm) form;
		HashMap<String, String> sbjgMap = service.getKhnr(myForm);
		request.setAttribute("rs", StringUtils.formatData(sbjgMap));
		request.setAttribute("path", "xmsbKhnrgl.do?method=viewKhnrgl");
		return mapping.findForward("viewKhnrgl");
	}

	/**
	 * 
	 * @����:�������ݱ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-13 ����05:28:12
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
	public ActionForward saveKhnr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhnrglForm myForm = (KhnrglForm) form;
		if (service.isHave(myForm)) {
			String message = MessageUtil
					.getText(MessageKey.KHGL_KHBGL_KHNR_USED);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editKhnr(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:ɾ����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-13 ����04:08:06
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
	public ActionForward delKhnr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			String message = num>0 ? MessageUtil.getText(
					MessageKey.SYS_DEL_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	

	/**
	 * 
	 * @����:��������ά��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-8-13����10:32:13
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
	public ActionForward khnrwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			KhnrglForm model = (KhnrglForm) form;
			if (QUERY.equalsIgnoreCase(model.getType())) {
				List<HashMap<String, String>> resultList = service.getKhnrList(
						model.getKhbid());
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			String path = "khgl_Khnrgl_Khnrgl.do";
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("khnrwh");
	}
	
	
}
