package xsgzgl.jxgl.general.jxxxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * ��ѵ����-������Ϣά��-��ѵ��Ϣά��
 * @author yeyipin
 * @since 2012.10.10
 */
public class JxglJxxxwhAjax extends BasicAction{
	
	/**
	 * ��ѵ��Ϣ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		jxglJxxxwhService.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jcxxwh_jxxxwh.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = jxglJxxxwhService.getTopTr("jxxx");
		// �����
		ArrayList<String[]> rsArrList = jxglJxxxwhService.jxxxCx(model);
		// ���������
		String spHtml = jxglJxxxwhService.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		jxglJxxxwhService.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	
	/**
	 * ��òμ��꼶
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCjnj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		List<HashMap<String, String>> list = jxglJxxxwhService.getCjnj();
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	/**
	 * ����֮ǰ��֤��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkJxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//������������
		model.setJxmc(jxglJxxxwhService.unicode2Gbk(model.getJxmc()));
		boolean result = jxglJxxxwhService.checkJxxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * ��ѵ��Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "���ʾ�ѵ����-������Ϣά��-��ѵ��Ϣά��-���ӻ��޸ı���JXMC:{jxmc},KSSJ:{kssj},JSSJ:{jssj},CJNJ:{cjnj},JXID:{jxid}")
	public ActionForward jxxxSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//������������
		model.setJxmc(jxglJxxxwhService.unicode2Gbk(model.getJxmc()));
		String message = jxglJxxxwhService.jxxxSave(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * ��ѵ��Ϣ�޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxxxXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		String jxid = request.getParameter("pkValue");
		model.setJxid(jxid);
		HashMap<String, String> map = jxglJxxxwhService.getJxxx(model);
		JSONObject jsonObj = JSONObject.fromObject(map);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonObj.toString());
		return null;
	}
	
	
	
	/**
	 * ɾ��֮ǰ��֤��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkScJxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//������������
		model.setPkValue(jxglJxxxwhService.unicode2Gbk(model.getPkValue()));
		boolean result = jxglJxxxwhService.checkScJxxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(result);
		return null;
	}
	
	
	/**
	 * ��ѵ��Ϣɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "���ʾ�ѵ����-������Ϣά��-��ѵ��Ϣά��-ɾ��PKVALUE:{pkValue}")
	public ActionForward jxxxSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//������������
		model.setPkValue(jxglJxxxwhService.unicode2Gbk(model.getPkValue()));
		String message = jxglJxxxwhService.jxxxSc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	

	
	/**
	 * ��ѵ��Ϣ ����(���У�ֹͣ);
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxxxCz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		String message = jxglJxxxwhService.jxxxCz(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	
	/**
	 * ��ѵ������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxmdCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		model.setJxid(request.getParameter("pkValue"));
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		jxglJxxxwhService.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jxxxwh.do?method=jxmdCx");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = jxglJxxxwhService.getTopTr("jxmd");
		// �����
		ArrayList<String[]> rsArrList = jxglJxxxwhService.jxmdCx(model,request);
		// ���������
		String spHtml = jxglJxxxwhService.createSearchHTML2(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		jxglJxxxwhService.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	
	/**
	 * ��þ�ѵ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJxrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		model.setCjnj(jxglJxxxwhService.unicode2Gbk(model.getCjnj()));
		HashMap<String, String> map = jxglJxxxwhService.getJxrs(model);
		JSONObject jsonObj = JSONObject.fromObject(map);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonObj.toString());
		return null;
	}
	
	
	/**
	 * ���ɾ�ѵ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward scJxmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//������������
		model.setCjnj(jxglJxxxwhService.unicode2Gbk(model.getCjnj()));
		String message = jxglJxxxwhService.scJxmd(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}    

	
	
	/**
	 * ��������֮ǰ��֤��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkScJxmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//������������
		model.setPkValue(jxglJxxxwhService.unicode2Gbk(model.getPkValue()));
		boolean result = jxglJxxxwhService.checkScJxmd(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * ��ѵ����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxmdSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//������������
		model.setPkValue(jxglJxxxwhService.unicode2Gbk(model.getPkValue()));
		String message = jxglJxxxwhService.jxmdSc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * ��ѵ�������(��ѵ����ѵ����ѵ)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxqkCz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//������������
		model.setPkValue(jxglJxxxwhService.unicode2Gbk(model.getPkValue()));
		String message = jxglJxxxwhService.cxqkCz(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
}
