/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-07-31 ����02:24:28 
 */
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.xmlbgl;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.stlbgl.StlbglService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-07-31 ����02:24:28
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XmlbglAction extends SuperAction {
	private StlbglService stlbService = new StlbglService();
	
	private static final String url = "stgl_jcsz_dmwh.do";
	
	/**
	 * 
	 * @����:��Ŀ����б�
	 * @���ߣ�xiaixa [���ţ�1104]
	 * @���ڣ�2015-07-31 ����02:38:34
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
		XmlbglForm model = (XmlbglForm) form;
		XmlbglService XmlbService = new XmlbglService();
		if (QUERY.equals(model.getType())) {
			String xmlbmc = request.getParameter("xmlbmc"); 
			xmlbmc = URLDecoder.decode(URLDecoder.decode(xmlbmc,"UTF-8"),"UTF-8"); 
			model.setXmlbmc(xmlbmc);
			String stlbmc = request.getParameter("stlbmc"); 
			stlbmc = URLDecoder.decode(URLDecoder.decode(stlbmc,"UTF-8"),"UTF-8");
			model.setStlbmc(stlbmc);
			List<HashMap<String, String>> resultList = XmlbService.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
			request.setAttribute("tabType", "xmlb");
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "stgl_jcsz_dmwh.do");
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXmlbList");

	}

	/**
	 * 
	 * @����:������Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-07-31 ����03:44:04
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
		XmlbglForm model = (XmlbglForm) form;
		XmlbglService XmlbService = new XmlbglService();
		if (SAVE.equals(model.getType())) {
			boolean result = XmlbService.addXmlb(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("stlbList", stlbService.getStlbList());
		return mapping.findForward("addXmlb");
	}

	/**
	 * 
	 * @����:�޸���Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-07-31 ����03:50:04
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
	public ActionForward updateXmlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmlbglForm myForm = (XmlbglForm) form;
		XmlbglService XmlbService = new XmlbglService();
		XmlbglForm model = XmlbService.getModel(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = XmlbService.updateXmlb(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("stlbList", stlbService.getStlbList());
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateXmlb");
	}

	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-07-31 ����04:03:00
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
	public ActionForward delXmlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmlbglService XmlbService = new XmlbglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//�ж���Ŀ����Ƿ�ռ��
			if (XmlbService.isUsed(values)) {
				String messageKey = MessageKey.STGL_JCSZ_XMLB_USED;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			String[] value=values.split(",");
			int num = XmlbService.runDelete(value);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xmsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmlbglForm myForm = (XmlbglForm) form;
		XmlbglService XmlbService = new XmlbglService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = XmlbService.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XmlbglForm model = XmlbService.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xstgl");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		request.setAttribute("xmmc", model.getXmlbmc());
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// �Ƿ�list
		request.setAttribute("kgList", isnotList);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		//FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsz");
	}
	
	//stlbdm��xmlbdm����
	public ActionForward getXmlblist(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String stlbdm = request.getParameter("stlbdm");
		XmlbglService service = new XmlbglService();
		List<HashMap<String, String>> xmlblist = service.getXmlbList(stlbdm);
		StringBuilder html = new StringBuilder();
		if(xmlblist != null){
			for (HashMap<String, String> hashMap : xmlblist) {
				html.append("<option value='"+hashMap.get("xmlbdm")+"'>"+hashMap.get("xmlbmc")+"</option>");
			}
		}
		Map<String,String> map = new HashMap<String, String>();
		map.put("html", html.toString());
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
}
