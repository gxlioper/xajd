package xgxt.wjcf.nbcs;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.wjcf.wjcfutils.WjcfActionUtils;

public class WjcfNbcsAction extends WjcfActionUtils {
	
	/**
	 * Υ�ʹ�����֪ͨ��д�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] keys = request.getParameterValues("cbv");
		WjcfNbcsService service = new WjcfNbcsService();
		//�·��⴦��֪ͨ
		boolean result = service.updateXfcftz(keys);
		return new ActionForward("/stuPunishAudit.do?res=" + result);
	}

	/**
	 * ��д�⴦��֪ͨ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward writeNcftz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		//�������ѧ���û���û�ж�дȨ
		if (!"stu".equalsIgnoreCase(userType)) {
			request.setAttribute("jswrite", "no");
		} else {
			
			WjcfNbcsService service = new WjcfNbcsService();
			String xh = request.getSession().getAttribute("userName").toString();
			List<String[]> rs = service.queryNcftzBystu(xh);
			List<HashMap<String, String>> topTr = service.getNcfxxTitle();
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			//�⴦��֪ͨ��¼��
			request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		}
		return mapping.findForward("writeNcftz");
	}
	
	/**
	 * ��д����֪ͨ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward txcftzs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfNbcsActionForm nbcsForm = (WjcfNbcsActionForm) form;
		WjcfNbcsModel model = new WjcfNbcsModel();
		WjcfNbcsService service = new WjcfNbcsService();
		String pkValue = request.getParameter("pkValue");
		//�������ݲ���
		if ("save".equalsIgnoreCase(request.getParameter("operType"))) {
			BeanUtils.copyProperties(model, nbcsForm);
			boolean result = service.saveNcftzxx(model);
			appendResult(result, request);
		}
		//˫���鿴��ϸ��Ϣ
		HashMap<String, String> rs = service.viewNcftzxx(pkValue);
		if (rs != null) {
			nbcsForm.setSfsb(rs.get("sfsb"));
			nbcsForm.setSbsy(rs.get("sbsy"));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("txcftzs");
	}
	
	/**
	 * �����ص�ҳ��Ĳ��������ʾ��Ϣ
	 * @param result
	 * @param request
	 */
	public void appendResult(boolean result, HttpServletRequest request) {
		if (result) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
	}
	
	/**
	 * ֪ͨ���ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tzsPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		WjcfNbcsService service = new WjcfNbcsService();
		HashMap<String, String> rs = service.tzsPrint(pkValue);
		request.setAttribute("rs", rs);
		return mapping.findForward("tzsPrint");
	}
	
	/**
	 * ���ֳʱ����ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cbbprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String cfpk = request.getParameter("cfpk");
		String pkValue = request.getParameter("pkValue");
		WjcfNbcsService service = new WjcfNbcsService();
		HashMap<String, String> rs = service.cbbPrint(pkValue, cfpk);
		request.setAttribute("rs", rs);
		return mapping.findForward("cbbprint");
	}
}
