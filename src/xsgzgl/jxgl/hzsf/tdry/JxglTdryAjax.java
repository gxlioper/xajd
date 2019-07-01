package xsgzgl.jxgl.hzsf.tdry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;

public class JxglTdryAjax extends BasicExtendAction{
	/**
	 * �Ŷ������񽱲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tdryCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglTdryService service = new JxglTdryService();
		JxglTdryForm model = (JxglTdryForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jxhj_tdry.do");
		// IE�汾
		String[] otherValue = request.getParameter("otherValue").split("!!@@!!");
		String ie = otherValue[0];
		model.setQuery(otherValue[1]);
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.tdryCx(model);
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
	 * �Ŷ���������ӱ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglTdryService service = new JxglTdryService();
		JxglTdryForm model = (JxglTdryForm)form;
		//�����������
		model.setBz(service.unicode2Gbk(model.getBz()));
		String message = service.tdryBc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * �Ŷ��������޸ı���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglTdryService service = new JxglTdryService();
		JxglTdryForm model = (JxglTdryForm)form;
		//�����������
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		model.setBz(service.unicode2Gbk(model.getBz()));
		String message = service.tdryXg(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * �Ŷ�������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tdrySc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglTdryService service = new JxglTdryService();
		JxglTdryForm model = (JxglTdryForm)form;
		//�����������
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message = service.tdrySc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * ��ʼ����Ӫ���б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBzdmList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglTdryService service = new JxglTdryService();
		JxglTdryForm model = (JxglTdryForm)form;
		List<HashMap<String, String>> list = service.getBzdmList(model);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	/**
	 * ��֤������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkSaveInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglTdryService service = new JxglTdryService();
		JxglTdryForm model = (JxglTdryForm)form;
		//�����������
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message  = service.checkSaveInfo(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}
