/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-22 ����10:53:31 
 */  
package xgxt.xsxx.dagl.dacx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.zfsoft.basic.BasicAction;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯҳ��
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-22 ����10:53:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxDaxxAction extends BasicAction{

	/**
	 * ������ѯ����ת��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryXsDaxx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward("daxxcx");
	}
	
	/**
	 * ������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryXsDaxxAction(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xm = request.getParameter("xm");
		String sfzh = request.getParameter("sfzh");
		XsxxDaxxService xsxxDaxxService = new XsxxDaxxService();
		HashMap<String, String> xsxxDaxx = xsxxDaxxService.getXsxxDaxx(StringUtils.trim(xm), StringUtils.trim(sfzh));
		
		JSONObject dataObject = JSONObject.fromObject(xsxxDaxx);
		response.getWriter().print(dataObject);
		return null;
	}
}
