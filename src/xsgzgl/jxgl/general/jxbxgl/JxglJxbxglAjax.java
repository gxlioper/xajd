package xsgzgl.jxgl.general.jxbxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

public class JxglJxbxglAjax extends BasicAction{

	/**
	 * ��ѵ���ֲ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxbxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxbxglService JxglJxbxglService = new JxglJxbxglService();
		JxglJxbxglForm model = (JxglJxbxglForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		JxglJxbxglService.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jxkhgl_jxbxgl.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = JxglJxbxglService.getTopTr("jxbx");
		// �����
		ArrayList<String[]> rsArrList = JxglJxbxglService.jxbxCx(model);
		// ���������
		String spHtml = JxglJxbxglService.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		JxglJxbxglService.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
		
	}
	

	/**
	 * ����������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bxmdCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxbxglService JxglJxbxglService = new JxglJxbxglService();
		JxglJxbxglForm model = (JxglJxbxglForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		// ============= ��ʼ����������ֵ ============
		JxglJxbxglService.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jxbxgl.do?method=bxmdCx");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = JxglJxbxglService.getTopTr("bxmd");
		// �����
		ArrayList<String[]> rsArrList = JxglJxbxglService.bxmdCx(model,request);
		// ���������
		String spHtml = JxglJxbxglService.createSearchHTML2(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		JxglJxbxglService.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
		
	}
	
	
	/**
	 * ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bxmdCz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxbxglService JxglJxbxglService = new JxglJxbxglService();
		JxglJxbxglForm model = (JxglJxbxglForm)form;
		//������������
		model.setPkValue(JxglJxbxglService.unicode2Gbk(model.getPkValue()));
		model.setXh(JxglJxbxglService.unicode2Gbk(model.getXh()));
		String message = JxglJxbxglService.bxmdCz(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	
}
