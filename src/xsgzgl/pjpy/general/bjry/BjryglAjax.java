package xsgzgl.pjpy.general.bjry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import com.zfsoft.basic.BasicAction;

public class BjryglAjax extends BasicAction {
	
	/**
	 * �༶������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("pjpy_bjry_bjrygl.do");
		request.setAttribute("path", "pjpy_bjry_bjrygl.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getBjryglCx(myForm);
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
	 * ��ȡ�༶����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBjmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("pjpy_bjry_bjrygl.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr2();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getBjmc(myForm);
		// ���������
		String spHtml = service.createSearchHTML2(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ��ȡ�༶��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBjxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		String bjdm = request.getParameter("bjdm");
		//��ȡ�ɰ༶���뵽�༶��Ϣ�����
		List<HashMap<String, String>> bjxxRs = service.getBjxx(myForm,bjdm);
		JSONArray jsonArr = JSONArray.fromArray(bjxxRs.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	/**
	 * �༶�����޸ı���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglXgBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		String message = "";
		boolean flag = false;
		User user = getUser(request);
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String bjdm = request.getParameter("bjdm");
		String hdsj = request.getParameter("hdsj");
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		String username = user.getUserName();
		myForm.setXn(xn);
		myForm.setXq(xq);
		myForm.setBjdm(bjdm);
		myForm.setHdsj(hdsj);
		myForm.setBz(bz);

		flag = service.bjryglXgBc(myForm, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * �༶�������ӱ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglZjBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		String message = "";
		boolean flag = false;
		User user = getUser(request);
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String bjdm = request.getParameter("bjdm");
		String rydm = service.unicode2Gbk(request.getParameter("rydm"));
		String hdsj = request.getParameter("hdsj");
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		String guid = xn+"!@"+(xq.equals("")?"no":xq)+"!@"+bjdm;
		String username = user.getUserName();
		myForm.setXn(xn);
		//���ѧ��Ϊ�գ�������ѧ���ֶ�
		if(!xq.equals("")){
			myForm.setXq(xq);
		}
		myForm.setBjdm(bjdm);
		myForm.setRydm(rydm);
		myForm.setHdsj(hdsj);
		myForm.setBz(bz);
		myForm.setGuid(guid);
		HashMap<String, String> bjryglxx = service.getBjryglMap(myForm);
		//�ж���û���ڸ���������
		if(bjryglxx.isEmpty()){
			flag = service.bjryglZjBc(myForm, username);
			if (Base.isNull(message)) {
				message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
			}
		}else{
			message = "�ð༶�ڸ������Ѿ���������������ظ���ӣ�";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}