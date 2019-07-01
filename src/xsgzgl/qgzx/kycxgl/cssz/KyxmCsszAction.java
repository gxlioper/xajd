/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����04:28:30 
 */  
package  xsgzgl.qgzx.kycxgl.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-9 ����04:28:30 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KyxmCsszAction extends SuperAction<KyxmCsszForm, KyxmCsszService> {
	private static final String KGZT_CLOSE = "0";
	private static final String KYCX_XMLB_KY = "kygl";
	private static final String KYCX_XMLB_SJ = "sjgl";
	
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-11-30 ����04:40:09
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
	public ActionForward cssz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmCsszForm myForm = (KyxmCsszForm) form;
		KyxmCsszService service = new KyxmCsszService();
		myForm.setXmlb(KYCX_XMLB_KY);
		KyxmCsszForm model = service.getModel(myForm);
		if (model != null) {
			BeanUtils.copyProperties(myForm, model);
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("qgzx");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		request.setAttribute("path", "qgzx_kycx_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	public ActionForward sjcssz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmCsszForm myForm = (KyxmCsszForm) form;
		KyxmCsszService service = new KyxmCsszService();
		myForm.setXmlb(KYCX_XMLB_SJ);
		KyxmCsszForm model = service.getModel(myForm);
		if (model != null) {
			BeanUtils.copyProperties(myForm, model);
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("qgzx");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		request.setAttribute("path", "qgzx_kycx_sjcssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sjcssz");
	}
	@SystemLog(description = "�����ڹ���ѧ-��������-����-xmlb:{xmlb}")
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmCsszForm model = (KyxmCsszForm) form;
		KyxmCsszService service = new KyxmCsszService();
		boolean result = false;
		service.deleteJcsz(model);
		result = service.runInsert(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
