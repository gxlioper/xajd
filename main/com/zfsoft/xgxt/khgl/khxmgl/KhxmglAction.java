/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-15 ����11:36:27 
 */
package com.zfsoft.xgxt.khgl.khxmgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
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
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.khgl.khbgl.KhbglService;
import com.zfsoft.xgxt.khgl.khdxgl.khdxgl.KhdxglService;
import com.zfsoft.xgxt.khgl.khdxgl.pfzgl.PfzglService;

/**
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: ������Ŀ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-15 ����11:36:27
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KhxmglAction extends SuperAction<KhxmglForm, KhxmglService> {
	private KhdxglService khdxService = new KhdxglService();
	private KhxmglService service = new KhxmglService();
	
	private static final String url = "khgl_khxmgl_khxmgl.do";

	/**
	 * 
	 * @����:��ѯ������Ŀ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����01:54:11
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
	public ActionForward getKhxmglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhxmglForm model = (KhxmglForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			List<HashMap<String, String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "khgl_khxmgl_khxmgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getKhxmglList");
	}

	/**
	 * 
	 * @����:������Ŀ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����05:27:51
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
	public ActionForward addKhxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhxmglForm model = (KhxmglForm) form;
		request.setAttribute("khdxList", khdxService.getKhdxList());
		return mapping.findForward("addKhxm");
	}

	/**
	 * 
	 * @����:�޸Ŀ�����Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����01:55:20
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
	public ActionForward updateKhxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhxmglForm myForm = (KhxmglForm) form;
		KhxmglForm KhxmglForm = service.getModel(myForm);
		if (null != KhxmglForm) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(KhxmglForm));
			request.setAttribute("KhxmglForm", KhxmglForm);
		}
		request.setAttribute("khdxList", khdxService.getKhdxList());
		return mapping.findForward("updateKhxm");
	}

	/**
	 * 
	 * @����:�鿴������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����01:55:20
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
	public ActionForward viewKhxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhxmglForm myForm = (KhxmglForm) form;
		HashMap<String, String> khxmMap = service.getKhxm(myForm.getXmid());
		List<HashMap<String,String>>  pfdxList = service.getpfdxXxList(myForm.getXmid());
		request.setAttribute("rs", StringUtils.formatData(khxmMap));
		request.setAttribute("pfdxList", pfdxList);
		return mapping.findForward("viewKhxm");
	}

	/**
	 * 
	 * @����:������Ŀ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����05:28:12
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
	@SystemLog(description = "���ʿ��˹���-������Ŀ����-������Ŀ����-���ӻ��޸ı���XMMC:{xmmc},KHDXID:{khdxid},KSSJ:{kssj},JSSJ:{jssj},XMID:{xmid}")
	public ActionForward saveKhxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhxmglForm myForm = (KhxmglForm) form;
		if (service.isHave(myForm)) {
			String message = MessageUtil
					.getText(MessageKey.KHGL_KHXMGL_KHXM_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editKhxm(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:ɾ��������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����04:08:06
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
	@SystemLog(description = "���ʿ��˹���-������Ŀ����-������Ŀ����-ɾ��VALUES:{values}")
	public ActionForward delKhxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String message = null;
		if (!StringUtil.isNull(values)) {
			//�жϿ��˱��Ƿ�ʹ��
			 message=service.isUsed(values);
			if (null!=message) {
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			String[] ids = values.split(",");
			boolean result = service.delKhxm(ids);
			 message = result ? MessageUtil.getText(
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
	 * @����:������Ŀѡ���б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����04:43:08
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
	public ActionForward showKhxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KhxmglForm myForm = (KhxmglForm) form;
		String forWardUrl=null;
		
			forWardUrl="showStu";
		return mapping.findForward(forWardUrl);
	}
	/**
	 * 
	 * @����:���ֶ�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����11:50:20
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
	public ActionForward pfdxSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			KhxmglForm model = (KhxmglForm) form;
			HashMap<String,String> khxmMap=service.getKhxm(model.getXmid());
			List<HashMap<String,String>>  pfdxList = service.getpfdxXxList(model.getXmid());
			List<HashMap<String,String>>  pfzList = new PfzglService().getPfzList(model.getKhdxid());
			for (int i = 0; i < pfdxList.size(); i++) {
				request.setAttribute("pffwList"+i, service.getPffwList(pfdxList.get(i).get("pfzid"),khxmMap.get("khlx")));
			}
			request.setAttribute("jsfsList", new OptionUtil().getOptions(OptionUtil.JSFS));
			request.setAttribute("pfzList",pfzList );
			request.setAttribute("khbList", new KhbglService().getKhbList(khxmMap.get("khdxid")));
			request.setAttribute("pffwList", service.getPffwList(pfzList.get(0).get("pfzid"),khxmMap.get("khlx")));
			
			request.setAttribute("pfdxList", pfdxList);
			request.setAttribute("rs", StringUtils.formatData(khxmMap));
			return mapping.findForward("pfdxSz");
	}
	/**
	 * 
	 * @����:�������ַ�Χ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����04:51:09
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
	public ActionForward getPffw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			KhxmglForm model = (KhxmglForm) form;
			String khlx = request.getParameter("khlx");
			List<HashMap<String,String>> sbflList = service.getPffwList(model.getPfzid(),khlx);
			JSONArray dataList = JSONArray.fromObject(sbflList);
			response.getWriter().print(dataList);
			return null;
	}
	/**
	 * 
	 * @����:���ֶ������ñ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����05:46:30
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
	public ActionForward savePfdxSz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KhxmglForm model = (KhxmglForm) form;
		String pfdxJson = request.getParameter("pfdxJson");
		
		List<KhxmglForm> pfdxList = JsonUtil.jsonArrToList(pfdxJson,KhxmglForm.class);
		boolean result = service.savePfdxSz(model,pfdxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
