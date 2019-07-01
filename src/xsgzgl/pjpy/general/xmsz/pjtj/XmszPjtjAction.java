package xsgzgl.pjpy.general.xmsz.pjtj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.xmsz.XmszPjtjInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszXmjdInterface;
import xsgzgl.pjpy.general.xmsz.xmjd.XmszXmjdInit;
import xsgzgl.pjpy.general.xmsz.xmjd.XmszXmjdModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��������_ͨ��_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XmszPjtjAction extends BasicAction {

	/**
	 * ��ʼ��������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultPjtjSetting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszPjtjInit init = new XmszPjtjInit();
		XmszPjtjModel model = new XmszPjtjModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============	
		//��Ŀ����
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		
		init.initPjtj(rForm, myForm, user, request);
		XmszPjtjInterface service = myService.getXmszPjtjService(myForm);
		
		// ���������б�
		List<HashMap<String, String>> pjtjList = (List<HashMap<String, String>>) request
				.getAttribute("pjtjList");
		model.setPjtjList(pjtjList);
		
		//�༶�����б�
		List<HashMap<String, String>> bjdlList = (List<HashMap<String, String>>) request
		.getAttribute("bjdlList");
		model.setBjdlList(bjdlList);
		
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.defaultPjtjSetting(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * ��ʼ��������Ŀ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultPjtjInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszPjtjInit init = new XmszPjtjInit();
		XmszPjtjModel model = new XmszPjtjModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initPjtj(rForm, myForm, user, request);
		XmszPjtjInterface service = myService.getXmszPjtjService(myForm);
		
		//��������
		String tjdm = request.getParameter("tjdm");
		model.setTjdm(tjdm);
		// =================== end ===================

		// ==================���������Ϣ====================
		HashMap<String, String> map = service.getPjtjInfo(model, user);
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		// ����ֵ
		String tjz = map.get("tjz");
		tjz = Base.isNull(tjz) ? "" : tjz;
		
		String message = tsgs + "!!luojw!!" + tjz + "!!luojw!!";
		// ==================���������Ϣ end================
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * ������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkPjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmszPjtjService service = new XmszPjtjService();
		XmszPjtjModel model = new XmszPjtjModel();

		User user = getUser(request);

		// ��request�е�ֵ��װ��model
		service.getModelValue(model, request);

		// �ж���������
		service.getPjtjMessage(model, user);

		return null;
	}

	/**
	 * ������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savePjtj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszPjtjInit init = new XmszPjtjInit();
		XmszPjtjModel model = new XmszPjtjModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		init.initPjtj(rForm, myForm, user, request);
		XmszPjtjInterface service = myService.getXmszPjtjService(myForm);
		myService.getModelValue(model, request);		
		// ============= end ============

		// ============= ������������ ============
		boolean flag = service.savePjtj(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deletePjtj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszPjtjInit init = new XmszPjtjInit();
		XmszPjtjModel model = new XmszPjtjModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		init.initPjtj(rForm, myForm, user, request);
		XmszPjtjInterface service = myService.getXmszPjtjService(myForm);
		myService.getModelValue(model, request);		
		// ============= end ============

		// ============= ������������ ============
		boolean flag = service.deletePjtj(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
