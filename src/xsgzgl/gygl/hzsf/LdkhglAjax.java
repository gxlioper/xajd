package xsgzgl.gygl.hzsf;

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

import com.zfsoft.xgxt.base.log.SystemLog;
/**
 * ��Ԣ����-����ʦ��-¥�����˹���
 * @author yeyipin
 * @since 2012.12.25 merry christmas
 */
public class LdkhglAjax extends BasicExtendAction{

	/**
	 * ¥�����˹���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ldkhgl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LdkhglService service = new LdkhglService();
		LdkhglForm model = (LdkhglForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("gygl_gypygl_gypywh.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("ldkhgl");
		// �����
		ArrayList<String[]> rsArrList = service.ldkhgl(model);
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
	 * ���˳ɼ�ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward khcjwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LdkhglService service = new LdkhglService();
		LdkhglForm model = (LdkhglForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("gygl_ldkhgl.do?method=khcjwh");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// �����
		String nd = model.getSearchModel().getSearch_tj_nd()[0];
		String yf = model.getSearchModel().getSearch_tj_yf()[0];
		model.setPkValue(nd+"-"+yf);
		ArrayList<String[]> rsArrList = service.khcjwh(model);
		// ���������
		String spHtml = service.createSearchHTMLByKhcj(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	
	/**
	 * ���ݵ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LdkhglService service = new LdkhglService();
		LdkhglForm model = (LdkhglForm)form;
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.exp(response.getOutputStream(),model);
		return null;
	}
	
	/**
	 * ���ݵ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expCj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LdkhglService service = new LdkhglService();
		LdkhglForm model = (LdkhglForm)form;
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expCj(response.getOutputStream(),model);
		return null;
	}
	
	
	/**
	 * ��λ��Ϣ�������ӱ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-¥�����˹���-¥�����˹��� -����PK:{pkValue}")
	public ActionForward khcjBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		LdkhglService service = new LdkhglService();
		LdkhglForm model = (LdkhglForm)form;
		//������������
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message = service.khcjBc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}
