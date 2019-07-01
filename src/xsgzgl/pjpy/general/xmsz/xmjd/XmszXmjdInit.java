package xsgzgl.pjpy.general.xmsz.xmjd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszRsszInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��Ŀ���_ͨ��_Init��
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

public class XmszXmjdInit {

	/**
	 * ��Ŀ���_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initXmjd(RequestForm rForm, PjpyGeneralForm model, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		model.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		xmdm = Base.isNull(xmdm) ? model.getXmdm() : xmdm;
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
	}
}
