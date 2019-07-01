package xsgzgl.qgzx.jfgl;

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

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.message.MessageKey;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/**
 * �ڹ���ѧ-�ڹ����ѹ���-������Ϣ����
 * 
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJfglAjax extends BasicAction {
	/**
	 * ������Ϣ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jfxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_jfgl_jfxxgl.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getJfxx(myForm);
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}

	/**
	 * ������Ϣ���ӱ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		// ������������
		myForm.setBm(service.unicode2Gbk(myForm.getBm()));
		myForm.setHbsj(service.unicode2Gbk(myForm.getHbsj()));
		myForm.setHbje(service.unicode2Gbk(myForm.getHbje()));
		myForm.setBz(service.unicode2Gbk(myForm.getBz()));
		String message = service.jfxxBc(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @�������·ݳ�ʼ��
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��9�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		// ������������
		myForm.setBm(service.unicode2Gbk(myForm.getBm()));
		myForm.setHbsj(service.unicode2Gbk(myForm.getHbsj()));
		myForm.setHbje(service.unicode2Gbk(myForm.getHbje()));
		myForm.setBz(service.unicode2Gbk(myForm.getBz()));
		String message = service.jfxxInit(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����: ��ȡ������˵�λ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-22 ����11:05:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	public ActionForward getBM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String nd = request.getParameter("nd");
		List<HashMap<String, String>> list=null;
		if(StringUtils.isNull(nd)){
			list = service.getBms(xn,xq);
		}else{
			list=service.getBm(xn,nd);
		}
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}
	public ActionForward getGwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		String xn = request.getParameter("xn");
		String yrdwdm=request.getParameter("yrdwdm");
		String xq = request.getParameter("xq");
		HashMap<String, String> map = service.getGwxx(xn,yrdwdm,xq);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	
	public String getxnForNd(String nd){
		String xn="";
		 List<HashMap<String, String>> xnL=Base.getXnndList();
		 for(HashMap<String, String> hm:xnL){
			 if(StringUtils.isNotNull(hm.get("nd"))&&nd.equals(hm.get("nd"))){
				 xn=hm.get("xn");
			 }
		 }
		return xn;
	}
	/**
	 * ������Ϣ�޸ı���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		// ������������
		myForm.setBm(service.unicode2Gbk(myForm.getBm()));
		myForm.setHbsj(service.unicode2Gbk(myForm.getHbsj()));
		myForm.setHbje(service.unicode2Gbk(myForm.getHbje()));
		myForm.setBz(service.unicode2Gbk(myForm.getBz()));
		String message = service.jfxxXg(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * ��֤���ӵı�����Ϣ�Ƿ���ȷ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkBcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm model = (QgzxJfglForm) form;
		// ������������
		model.setBm(service.unicode2Gbk(model.getBm()));
		model.setHbsj(service.unicode2Gbk(model.getHbsj()));
		String message = service.checkBcInfo(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * ��֤�޸ĵı�����Ϣ�Ƿ���ȷ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkXgBcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		// ������������
		myForm.setBm(service.unicode2Gbk(myForm.getBm()));
		myForm.setHbje(service.unicode2Gbk(myForm.getHbje()));
		String message = service.checkXgBcInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
}
