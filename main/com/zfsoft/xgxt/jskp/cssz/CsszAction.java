package com.zfsoft.xgxt.jskp.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;

public class CsszAction extends SuperAction<CsszForm, CsszService> {
	private CsszService service = new CsszService();
	private static final String url = "pjpy_jskp_cssz.do";//path·��
	/**
	 * 
	 * @����: �������ò�ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-4 ����04:11:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemLog(description = "���ʼ�ʵ����-��������")
	@SystemAuth(url = url)
	public ActionForward getCsszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("lxsplc", service.getSplc("lx"));
		request.setAttribute("sbsplc", service.getSplc("sb"));
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xspj");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("csszcx");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-4 ����04:36:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemLog(description = "��ʵ����-��������-����")
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszForm cssz = (CsszForm)form;
		String[] ids = request.getParameterValues("id");
		String[] lxs = request.getParameterValues("lx");
		String[] splcs = request.getParameterValues("splc");
		String sfsh = request.getParameter("sfsh");
		cssz.setIds(ids);
		cssz.setLxs(lxs);
		cssz.setSplcs(splcs);
		cssz.setSfsh(sfsh);
		boolean rs = service.saveData(cssz);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
