package xsgzgl.wjcf.general;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.wjcf.general.cfjcgl.WjcfCfjcInit;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbInit;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfshModel;
import xsgzgl.wjcf.general.inter.WjcfCfjcshInterface;
import xsgzgl.wjcf.general.inter.WjcfCfshInterface;

import com.zfsoft.basic.BasicAction;

public class WjcfGeneralAction extends BasicAction{
	/**
	 * Υ�ʹ��ִ����ϱ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cfsbCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfGeneralService service = new WjcfGeneralService();
		WjcfCfsbInit init = new WjcfCfsbInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initCfsb(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/wjcf/" + xxpymc + "/cfsbgl/cfsbCx.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * Υ�ʹ��ִ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cfshCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfGeneralService service = new WjcfGeneralService();
		WjcfCfshInterface myservice = service.getWjcfCfshService(myForm);
		WjcfCfsbInit init = new WjcfCfsbInit();
		RequestForm rForm = new RequestForm();
		WjcfCfshModel formmodel = new WjcfCfshModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initCfsh(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/wjcf/" + xxpymc + "/cfsbgl/cfshCx.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		List<HashMap<String, String>> cflbList = myservice.getCflbList(formmodel,user);
		request.setAttribute("cflbList", cflbList);

		if(null==cflbList||cflbList.size()==0){
			String msg = "��ģ��û�и��û���������λ����ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
	}else{
		return new ActionForward(url, false);
	}
		
	}
	
	/**
	 * Υ�ʹ��ִ��ֽ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cfjcCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfGeneralService service = new WjcfGeneralService();
		WjcfCfjcInit init = new WjcfCfjcInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initCfjc(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/wjcf/" + xxpymc + "/cfjcgl/cfjcCx.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * Υ�ʹ��ִ��ֽ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cfjcshCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfGeneralService service = new WjcfGeneralService();
		WjcfCfjcInit init = new WjcfCfjcInit();
		RequestForm rForm = new RequestForm();
		WjcfCfjcshInterface myService = service.getWjcfCfjcshService(myForm);
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initCfjcsh(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/wjcf/" + xxpymc + "/cfjcgl/cfjcshCx.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		List<HashMap<String,String>> spgwList = myService.getSpgwList(user);
		request.setAttribute("spgwList", spgwList);
		if(null==spgwList||spgwList.size()==0){
			String msg = "��ģ��û�и��û���������λ����ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
	}else{
		return new ActionForward(url, false);
	}
	}
}
