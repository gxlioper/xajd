package xsgzgl.jxgl.hzsf.grry;

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
/**
 * ��ѵ����-��ѵ��-��������
 * @author yeyipin
 * @since 2012.7.27
 */
public class JxglGrryAjax extends BasicExtendAction{
	/**
	 * ���������񽱲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grryCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglGrryService service = new JxglGrryService();
		JxglGrryForm model = (JxglGrryForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jxhj_grry.do");
		// IE�汾
		String[] otherValue = request.getParameter("otherValue").split("!!@@!!");
		String ie = otherValue[0];
		model.setQuery(otherValue[1]);
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("grry");
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.GrryCx(model);
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
	 * ��ȡ���о�ѵѧ��������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJxxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglGrryService service = new JxglGrryService();
		JxglGrryForm model = (JxglGrryForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jxhj_grry.do");
		// IE�汾
		String[] otherValue = request.getParameter("otherValue").split("!!@@!!");
		String ie = otherValue[0];
		model.setQuery(otherValue[1]);
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("xsmd");
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getJxxs(model);
		// ���������
		String spHtml = service.createSearchHTML2(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	/**
	 * ��þ�ѵѧ����Ϣmap
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXsjx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglGrryService service = new JxglGrryService();
		JxglGrryForm model = (JxglGrryForm)form;
		HashMap<String, String> map = service.getJxxsMap(model);		
		List<HashMap<String, String>> list =new ArrayList<HashMap<String,String>>();
		list.add(map);
		response.setContentType("text/html;charset=gbk");
		//response.getWriter().write(jsonObj.toString());

		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}
	/**
	 * ������������ӱ���
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
		JxglGrryService service = new JxglGrryService();
		JxglGrryForm model = (JxglGrryForm)form;
		//�����������
		model.setBz(service.unicode2Gbk(model.getBz()));
		String message = service.grryBc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * �����������޸ı���
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
		JxglGrryService service = new JxglGrryService();
		JxglGrryForm model = (JxglGrryForm)form;
		//�����������
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		model.setBz(service.unicode2Gbk(model.getBz()));
		String message = service.grryXg(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * ����������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrySc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglGrryService service = new JxglGrryService();
		JxglGrryForm model = (JxglGrryForm)form;
		//�����������
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message = service.grrySc(model);
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
	public ActionForward getTuanYingLianList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglGrryService service = new JxglGrryService();
		JxglGrryForm model = (JxglGrryForm)form;
		List<HashMap<String, String>> list = service.getTuanYingLianList(model);
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
		JxglGrryService service = new JxglGrryService();
		JxglGrryForm model = (JxglGrryForm)form;
		//�����������
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message  = service.checkSaveInfo(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
}
