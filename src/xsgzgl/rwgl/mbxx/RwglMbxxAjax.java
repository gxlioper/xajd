package xsgzgl.rwgl.mbxx;

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

public class RwglMbxxAjax extends BasicExtendAction{
	/**
	 * �����Ϣ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mbxxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglMbxxService service  = new RwglMbxxService();
		RwglMbxxForm model = (RwglMbxxForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("rwgl_mbxxgl_mbxxgl.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// �����
		ArrayList<String[]> rsArrList = service.mbxxCx(model);
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
	 * �����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mbxxBc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglMbxxService service  = new RwglMbxxService();
		RwglMbxxForm model = (RwglMbxxForm) form;
		//������������
		model.setXm(service.unicode2Gbk(model.getXm()));
		model.setXb(service.unicode2Gbk(model.getXb()));
		model.setCsrq(service.unicode2Gbk(model.getCsrq()));
		model.setSfzh(service.unicode2Gbk(model.getSfzh()));
		model.setWhcd(service.unicode2Gbk(model.getWhcd()));
		model.setZy(service.unicode2Gbk(model.getZy()));
		model.setZc(service.unicode2Gbk(model.getZc()));
		model.setZw(service.unicode2Gbk(model.getZw()));
		model.setRdsj(service.unicode2Gbk(model.getRdsj()));
		model.setZzmmdm(service.unicode2Gbk(model.getZzmmdm()));
		model.setSftwjr(service.unicode2Gbk(model.getSftwjr()));
		model.setZygw(service.unicode2Gbk(model.getZygw()));
		model.setGzdw(service.unicode2Gbk(model.getGzdw()));
		model.setLxfs(service.unicode2Gbk(model.getLxfs()));
		model.setBgdh(service.unicode2Gbk(model.getBgdh()));
		model.setDh(service.unicode2Gbk(model.getDh()));
		model.setMbzw(service.unicode2Gbk(model.getMbzw()));
		model.setZzbx(service.unicode2Gbk(model.getZzbx()));
		model.setJsxl(service.unicode2Gbk(model.getJsxl()));
		model.setJtdz(service.unicode2Gbk(model.getJtdz()));
		String message = service.mbxxBc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	
	/**
	 * �����Ϣɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mbxxSc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglMbxxService service  = new RwglMbxxService();
		RwglMbxxForm model = (RwglMbxxForm) form;
		//������������
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message = service.mbxxSc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
}
