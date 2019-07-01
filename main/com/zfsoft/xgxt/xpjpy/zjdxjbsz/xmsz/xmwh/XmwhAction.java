/**
 * @����:ѧ����Ʒ1��
 * @���ڣ�2017-4-7 ����10:57:56 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq.XmsqService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������_��Ŀ����_��Ŀά��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-4-7 ����10:57:56 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmwhAction extends SuperAction {
	private static final String url = "xpjpy_jbsz_xmsz.do";
	
	/**
	 * @����: ������Ŀ����_��Ŀά���б�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-7 ����01:50:50
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
	@SystemLog(description = "��������������-��������-��Ŀ����-��ѯҳ��")
	public ActionForward getXmwhList (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	 	throws Exception {
		
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		User user = getUser(request);
		/*��ѯ��������*/
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*��ȡ����������Ϣ*/
		HashMap<String, String> csszMap = service.getCsszMap();
		request.setAttribute("pjzq", csszMap.get("xn"));
		/*��ȡ��Ŀ����*/
		List<HashMap<String, String>> xmlxList = service.getXmlx();
		request.setAttribute("xmlxList", xmlxList);
		/*��ȡ��Ŀ����*/
		List<HashMap<String, String>> xmxzList = service.getXmxz();
		request.setAttribute("xmxzList", xmxzList);
		/*����path*/
		String path = "xpjpy_jbsz_xmsz.do";
		request.setAttribute("path", path);
		/*����ϵͳ��ǰʱ��*/
		String dateFormat = "yyyy-MM-dd HH:mm";
		request.setAttribute("currDate", service.getCurrTime(dateFormat));
		/*����ҳ�����Ȩ�޼���ͷ*/
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhList");
	}
	
	/**
	 * @����: ��Ŀ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-10 ����11:06:34
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
	public ActionForward xmwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		XtwhShlcService shlcService = new XtwhShlcService();
		/*������������������б��ȡֵͨ�÷���*/
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("pjpy");
		request.setAttribute("shlcList", shlc);

		/*��ȡ��Ŀ����*/
		List<HashMap<String, String>> xmlxList = service.getXmlx();
		request.setAttribute("xmlxList", xmlxList);
		/*��ȡ��Ŀ����*/
		List<HashMap<String, String>> xmxzList = service.getXmxz();
		request.setAttribute("xmxzList", xmxzList);
		/*����path*/
		String path = "xpjpy_xmwh.do?method=xmwhAdd";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		if (model != null) {
			BeanUtils.copyProperties(model, StringUtils.formatData(model));
		}
		return mapping.findForward("xmwhAdd");
	}
	
	/**
	 * @����: ��Ŀ���ӱ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-10 ����02:07:25
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
	@SystemLog(description="��������������-��������-��Ŀ����-����-XZDM:{xzdm},LXDM:{lxdm},XMMC:{xmmc},YWMC:{ywmc},XMJE:{xmje},XSSX:{xssx},SHLC:{shlc},XMSM:{xmsm}")
	public ActionForward saveFormAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		String messageKey = null;
		/*��֤ͬһ��ѧ���Ƿ�����ʾ˳���ظ�*/
		if(service.isExistXssx(model)) {
			messageKey = MessageKey.PJPY_XSXH_EXIST;
			response.getWriter().print(getJsonResult(messageKey, false));
			return null;
		}
		/*��֤ͬһ��ѧ���Ƿ�����Ŀ�����ظ�*/
		if (service.isExistXmmc(model)) {
			messageKey = MessageKey.PJPY_XMMC_EXIST;
			response.getWriter().print(getJsonResult(messageKey, false));
			return null;
		}
		/*ÿ����ĿĬ�����ӵ���ѧ��*/
		model.setXn(service.getCsszMap().get("xn"));
		/*����� */
		boolean result = service.runInsert(model);
		messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: ��Ŀ�޸�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-11 ����05:05:36
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
	public ActionForward xmwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		XmwhForm updateForm = service.getModel(model);
		XtwhShlcService shlcService = new XtwhShlcService();
		/*������������������б��ȡֵͨ�÷���*/
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("pjpy");
		request.setAttribute("shlcList", shlc);
		/*��ȡ��Ŀ����*/
		List<HashMap<String, String>> xmlxList = service.getXmlx();
		request.setAttribute("xmlxList", xmlxList);
		/*��ȡ��Ŀ����*/
		List<HashMap<String, String>> xmxzList = service.getXmxz();
		request.setAttribute("xmxzList", xmxzList);
		/*�����Ƿ���ѧ���������Ŀ*/
		if(model.getXmdm() != null && !model.getXmdm().equals("")){
			XmsqService xmsqService = new XmsqService();
			boolean flag = xmsqService.isExistsFlowData(model.getXmdm());
			request.setAttribute("spzt", flag);
		}
		/*����path*/
		String path = "xpjpy_xmwh.do?method=xmwhUpdate";
		request.setAttribute("path", path);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("xmwhUpdate");
	}
	
	/**
	 * @����: ��Ŀ�޸ı���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-11 ����05:23:50
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
	@SystemLog(description="��������������-��������-��Ŀ����-�޸�-XZDM:{xzdm},LXDM:{lxdm},XMMC:{xmmc},YWMC:{ywmc},XMJE:{xmje},XSSX:{xssx},SHLC:{shlc},XMSM:{xmsm}")
	public ActionForward saveFormUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		String messageKey = null;
		/*��֤ͬһ��ѧ���Ƿ�����ʾ˳���ظ�*/
		if(service.isExistXssx(model)) {
			messageKey = MessageKey.PJPY_XSXH_EXIST;
			response.getWriter().print(getJsonResult(messageKey, false));
			return null;
		}
		/*��֤ͬһ��ѧ���Ƿ�����Ŀ�����ظ�*/
		if (service.isExistXmmc(model)) {
			messageKey = MessageKey.PJPY_XMMC_EXIST;
			response.getWriter().print(getJsonResult(messageKey, false));
			return null;
		}
		/*���±� */
		boolean result = service.runUpdate(model);
		messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: ɾ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-10 ����05:40:39
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
	@SystemLog(description="������������-��������-��Ŀ����-ɾ��-VALUES��{values}")
	public ActionForward xmwhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmwhService service = new XmwhService();
		String messageKey = null;
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			/*�ж���ѡɾ������Ŀ �Ƿ�����Ŀ����ʱ��ʹ��*/
			if (service.checkForXmsq(values)) {
				messageKey = MessageKey.PJPY_XM_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			if (result) {
				/*������Ҫɾ����Ŀ �Ĺ�����*/
				service.deleteRelationalTable(values);
			}
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_SUCCESS);
			response.getWriter().print(getJsonMessage(message));
		}else{
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;
	}
	
	/**
	 * @����: ���롢��˿���ҳ�淵��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-12 ����11:25:52
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
	public ActionForward xmwhSjkg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		XmwhForm sjkgForm = service.getModel(model);
		/*��ҳ������Ŀ����*/
		String xmdm = request.getParameter("xmdm");
		/*������Ŀ����*/
		String xmmc = service.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// �Ƿ�list
		request.setAttribute("kgList", isnotList);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		/*����path*/
		String path = "xpjpy_xmwh.do?method=getXmwhList";
		request.setAttribute("path", path);
		BeanUtils.copyProperties(model, StringUtils.formatData(sjkgForm));
		return mapping.findForward("xmwhSjkg");
	}
	
	/**
	 * @����: ʱ�俪�����ñ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-12 ����01:47:09
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
	@SystemLog(description="������������-��������-��Ŀ����-���롢��˿���-XMDM��{xmdm},SQKG:{sqkg},SQKSSJ:{sqkssj},SQJSSJ:{sqjssj},SHKG:{shkg},SHKSSJ:{shkssj},SHJSSJ:{shjssj},KGBZ:{kgbz}")
	public ActionForward saveFormSjkg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: �����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-12 ����03:57:05
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
	public ActionForward xmwhJxfz(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		if (QUERY.equals(model.getType())) {
			/*������Դ��������б�����*/
			List<HashMap<String, String>> jxfzList = service.getJxfz();
			JSONArray dataList = JSONArray.fromObject(jxfzList);
			response.getWriter().print(dataList);
			return null;
		}
		/*��ǰ��������(xn)*/
		String pjzq = service.getCsszMap().get("xn");
		request.setAttribute("pjzq", pjzq);
		/*����path*/
		String path = "xpjpy_xmwh.do?method=getXmwhList";
		request.setAttribute("path", path);
		return mapping.findForward("xmwhJxfz");
	}
	
	/**
	 * @����: ����Ʊ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-12 ����03:57:05
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
	public ActionForward saveFormCopy(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		XmwhService service = new XmwhService();
		String jxfznd = request.getParameter("jxfznd");
		boolean result = service.runJxfz(jxfznd);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
