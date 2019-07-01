package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;

public class XlzxSbAction extends SuperAction<XlzxSbJcszForm,XlzxSbService> {
	private XlzxSbService service = new XlzxSbService();
	/**
	 * @���� ����������
	 */
	private XtwhShlcService shlcService = new XtwhShlcService();
	private final String url = "xg_xlzxnew_cssz.do";
	/**
	 *
	 * @����: �������ò�ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-28 ����04:42:43
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
	public ActionForward getJcsszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XlzxSbJcszForm myForm = (XlzxSbJcszForm)form;
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getPageList(myForm, user);
		String ybsplc = "";
		String zbsplc = "";
		if(resultList != null && !resultList.isEmpty()){
			for (int i = 0; i < resultList.size(); i++) {
				if("zb".equals(resultList.get(i).get("lx"))){
					zbsplc = resultList.get(i).get("splc");
				}else{
					ybsplc = resultList.get(i).get("splc");
				}
			}
		}
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("xlzx"));
		request.setAttribute("ybsplc", ybsplc);
		request.setAttribute("zbsplc", zbsplc);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("csszcx");
	}
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-28 ����05:16:41
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
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] splcs = request.getParameterValues("splc");
		String[] lxs = request.getParameterValues("lx");
		boolean rs = service.saveJcsz(splcs,lxs);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
