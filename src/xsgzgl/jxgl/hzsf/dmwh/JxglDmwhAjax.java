package xsgzgl.jxgl.hzsf.dmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;

/**
 * ��ѵ����-��������-����ά��
 * @author yeyipin
 * @since 2012.7.16
 */
public class JxglDmwhAjax extends BasicExtendAction{
	/**
	 * ��������ά��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grryWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("grry");
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getGrryList(model);
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	/**
	 * �Ŷ�����ά��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tdryWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("tdry");
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getTdryList(model);
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	/**
	 * ��������ά������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grryBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		String doType = request.getParameter("doType");
		//�����������
		model.setGrrymc(service.unicode2Gbk(model.getGrrymc()));
		String message = service.grryBc(model,doType);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * �Ŷ�����ά������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tdryBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		String doType = request.getParameter("doType");
		//�����������
		model.setTdrymc(service.unicode2Gbk(model.getTdrymc()));
		String message = service.tdryBc(model,doType);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * ��������ά��ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrySc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		String message  = service.grrySc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * �Ŷ�����ά��ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tdrySc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		String message  = service.tdrySc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * ��֤��������ά����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkGrry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		String message  = service.checkGrry(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * ��֤�Ŷ�����ά����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkTdry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		String message  = service.checkTdry(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}
