package xsgzgl.pjpy.general.djbg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyDjbgInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʒ���_ͨ��_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyDjbgAction extends BasicAction {

	/**
	 * ��ӡ�ǼǱ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward printDjbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyDjbgInterface service = myService.getPjpyDjbgService(myForm);
		RequestForm rForm = new RequestForm();
		PjpyDjbgModel model = new PjpyDjbgModel();
		PjpyDjbgInit init = new PjpyDjbgInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============

		myService.getModelValue(model, request);

		// ---------------------����������ӡ�� begin---------------------

		String xn = request.getParameter("str_xn");
		String xq = request.getParameter("str_xq");

		if (Base.isNull(xn)) {
			model.setXn(jbszModel.getPjxn());
		}

		if (Base.isNull(xq)) {
			model.setXq(jbszModel.getPjxq());
		}
		// ---------------------����������ӡ�� end-----------------------

		init.init(rForm, myForm, model, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// �ǼǱ��
		String djbg = service.getDjbg(model);
		// ��ת·��
		String url = service.getPrintJspForward(model);

		if (Base.isNull(url)) {
			url = "/xsgzgl/pjpy/" + xxpymc + "/djbg/" + djbg + ".jsp";
		}
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm commModel = new CommForm();
		myService.setRequestValue(rForm, request);
		myService.setList(commModel, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
}
