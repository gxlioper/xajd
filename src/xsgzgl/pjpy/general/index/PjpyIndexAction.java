package xsgzgl.pjpy.general.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyIndexInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��ҳ_ͨ��_Action��
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

public class PjpyIndexAction extends BasicAction {

	/**
	 * ��ʼ���Ѷ�����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultCustomPjlc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexInit init = new PjpyIndexInit();
		PjpyIndexModel model = new PjpyIndexModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initIndex(rForm, myForm, user, request);
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);
		//��ʽ��ַ
		model.setStylePath(rForm.getStylePath());
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.defaultCustomPjlc(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}

	/**
	 * ��ʼ����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultFreePjlc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexModel model = new PjpyIndexModel();
		PjpyIndexInit init = new PjpyIndexInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initIndex(rForm, myForm, user, request);
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.defaultFreePjlc(model, user, response);
		// ==================����ǰ̨ҳ�� end================

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
	public ActionForward savePjlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexModel model = new PjpyIndexModel();
		PjpyIndexInit init = new PjpyIndexInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initIndex(rForm, myForm, user, request);
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);

		// �����������
		String maxPjlc = request.getParameter("maxPjlc");
		model.setMaxPjlc(maxPjlc);

		// ���̴���
		String lcdm = request.getParameter("lcdm");
		if (!Base.isNull(lcdm)) {
			model.setLcdm(lcdm.split("!!@@!!"));
		}

		// ���̵ȼ�
		String lcdj = request.getParameter("lcdj");
		if (!Base.isNull(lcdj)) {
			model.setLcdj(lcdj.split("!!@@!!"));
		}
		// ============= end ============

		// ============= ���湦��ģ�� ============
		boolean flag = service.savePjlc(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ���濪ʼ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexModel model = new PjpyIndexModel();
		PjpyIndexInit init = new PjpyIndexInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initIndex(rForm, myForm, user, request);
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);

		String pjzq = request.getParameter("pjzq");// ��������
		model.setPjzq(pjzq);

		String pjxn = request.getParameter("pjxn");// ����ѧ��
		model.setPjxn(pjxn);

		String pjxq = request.getParameter("pjxq");// ����ѧ��
		pjxq = Base.isNull(pjxq) ? "no" : pjxq;
		model.setPjxq(pjxq);

		String cpz = request.getParameter("cpz");// ������
		model.setCpz(cpz);

		String zcpm = request.getParameter("zcpm");// �۲�����
		model.setZcpm(zcpm);

		String zypm = request.getParameter("zypm");// ��������
		model.setZypm(zypm);
		
		String start = request.getParameter("start");// �Ƿ�ʼ������
		model.setStart(start);
		// ============= end ============

		// ============= ���湦��ģ�� ============
		boolean flag = service.saveStart(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �ύ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitPjlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexInit init = new PjpyIndexInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initIndex(rForm, myForm, user, request);
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);

		String lcdj = request.getParameter("lcdj");// ���̵ȼ�
		// ============= end ============

		// ============= ���湦��ģ�� ============
		boolean flag = service.submitPjlc(lcdj, user);
		String message = flag ? "�ύ�ɹ�" : "�ύʧ�ܣ�����ϵ�����Ա";
		if ("3".equalsIgnoreCase(lcdj) && flag) {// ���Ĳ�
			StringBuilder msg = new StringBuilder();
			msg.append("�ύ�ɹ�!<br/>");
			msg.append("ע�������������<font color='blue'>������Ա��</font>����");
			msg.append("<font color='blue'>�۲���Ŀ</font>�Ļ���");
			msg.append("����<font color='blue'>�ۺϷֽ��</font>������ִ�м��㡣");
			message = msg.toString();
		} else if ("4".equalsIgnoreCase(lcdj) && flag) {// ���岽
			StringBuilder msg = new StringBuilder();
			msg.append("�ύ�ɹ�!<br/>");
			msg.append("ע�����������Ա���ۺϷ�δ���м���Ļ���");
			msg.append("ϵͳ�Ѿ��Զ����������˼��������");
			msg.append("����<font color='blue'>�ۺϷֽ��</font>��ȷ����ط�����");
			message = msg.toString();
		}
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ����������ת����ʷ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dataToHis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexInit init = new PjpyIndexInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initIndex(rForm, myForm, user, request);
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);

		// ============= end ============

		// ============= ���湦��ģ�� ============
		boolean flag=true;
		service.theEnd(user);
		String message = flag ? "�ύ�ɹ�" : "�ύʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

}
