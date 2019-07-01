package xsgzgl.pjpy.general.pjsz.pjry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xsgzgl.comm.BasicInit;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_������Ա_ͨ��_Init��
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

public class PjszPjryInit extends BasicInit {

	/**
	 * ������Ա_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initPjry(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjryInterface service = myService.getPjszPjryService(myForm);

		// ����·��
		String path = "general_pjpy.do?method=pjszPjry";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// �û�����
		String userType = user.getUserType();
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ҳ����ת
		String forward = request.getParameter("forward");
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "userType", "forward" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, userType, forward };

		// ==================�ܷ������� begin==================
		String message = "";
		String operation = myService.getOperation("102");
		if ("later".equalsIgnoreCase(operation)) {
			message = "����������Ѿ�������û��ύ���������ٽ�����ز������������飬����ϵ����Ա��";
			request.setAttribute("operation", "no");
		}else if("early".equalsIgnoreCase(operation)){
			message = "�����������ǰһ����δ������û��ύ�������ܶ��������ز������������飬����ϵ����Ա��";
			request.setAttribute("operation", "no");
		}
		// =================�ܷ������� end ===================
		
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setUserType(userType);
	}
}
