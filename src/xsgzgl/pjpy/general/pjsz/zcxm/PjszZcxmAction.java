package xsgzgl.pjpy.general.pjsz.zcxm;

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
import xsgzgl.pjpy.general.inter.pjsz.PjszZcxmInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_�۲���Ŀ_ͨ��_Action��
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

public class PjszZcxmAction extends BasicAction {
	
	/**
	 * �����۲���Ŀ(��һ����)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveNextZcxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszZcxmInit init = new PjszZcxmInit();
		PjszZcxmModel model = new PjszZcxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		init.initZcxm(rForm, myForm, user, request);
		PjszZcxmInterface service = myService.getPjszZcxmService(myForm);

		// �ϼ�����
		String sjdm = request.getParameter("sjdm");
		model.setSjdm(sjdm);

		//��Ŀ����
		String xmmc = request.getParameter("xmmc");
		if (!Base.isNull(xmmc)) {
			model.setXmmc(myService.unicode2Gbk(xmmc));
		}
		
		// �Ӽ���
		String jjf = request.getParameter("jjf");
		model.setJjf(jjf);

		// ¼������
		String lrly = request.getParameter("lrly");
		model.setLrly(lrly);
		
		// ��������
		String bldm = request.getParameter("bldm");
		if (!Base.isNull(bldm)) {
			model.setBldm(bldm.split("!!@@!!"));
		}
		
		// ����
		String bl = request.getParameter("bl");
		if (!Base.isNull(bl)) {
			model.setBl(bl.split("!!@@!!"));
		}
		// ============= end ============

		// ============= �����۲���Ŀ ============
		boolean flag = service.saveZcxm(model, "next", user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �����۲���Ŀ(�޸ı�����)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveEditZcxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszZcxmInit init = new PjszZcxmInit();
		PjszZcxmModel model = new PjszZcxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		init.initZcxm(rForm, myForm, user, request);
		PjszZcxmInterface service = myService.getPjszZcxmService(myForm);

		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);

		// ��Ŀ����
		String xmmc = request.getParameter("xmmc");
		if (!Base.isNull(xmmc)) {
			model.setXmmc(myService.unicode2Gbk(xmmc));
		}
		
		// �Ӽ���
		String jjf = request.getParameter("jjf");
		model.setJjf(jjf);

		// ¼������
		String lrly = request.getParameter("lrly");
		model.setLrly(lrly);
		
		// ��������
		String bldm = request.getParameter("bldm");
		if (!Base.isNull(bldm)) {
			model.setBldm(bldm.split("!!@@!!"));
		}
		
		// ����
		String bl = request.getParameter("bl");
		if (!Base.isNull(bl)) {
			model.setBl(bl.split("!!@@!!"));
		}
		// ============= end ============

		// ============= �����۲���Ŀ ============
		boolean flag = service.updateZcxm(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ɾ���۲���Ŀ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteZcxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszZcxmInit init = new PjszZcxmInit();
		PjszZcxmModel model = new PjszZcxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		init.initZcxm(rForm, myForm, user, request);
		PjszZcxmInterface service = myService.getPjszZcxmService(myForm);

		// �۲���Ŀ
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		// ============= end ============

		// ============= ɾ���۲���Ŀ ============
		boolean flag = service.deleteZcxm(model, user);
		String message = flag ? "ɾ���ɹ�" : "ɾ��ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
