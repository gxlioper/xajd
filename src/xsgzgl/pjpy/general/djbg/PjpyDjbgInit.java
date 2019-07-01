package xsgzgl.pjpy.general.djbg;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyDjbgInterface;
import xsgzgl.pjpy.general.inter.tjcx.TjcxZcbjmdInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʒ���_ͨ��_Init��
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

public class PjpyDjbgInit extends BasicInit {

	/**
	 * ��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void init(RequestForm rForm, PjpyGeneralForm myForm,
			PjpyDjbgModel model, User user, HttpServletRequest request)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyDjbgInterface service = myService.getPjpyDjbgService(myForm);

		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ѧ��
		String[] xh = model.getPrint_xh();

		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		// �ǼǱ������
		if (xh != null && xh.length > 0) {
			List<HashMap<String, String>> list = service.getDjbgInfoList(model);
			request.setAttribute("rsList", list);
		} else {
			HashMap<String, Object> map = service.getDjbgInfo(model);
			request.setAttribute("rs", map);
		}

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
	}
}
