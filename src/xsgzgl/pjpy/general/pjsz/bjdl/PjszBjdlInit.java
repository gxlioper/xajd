package xsgzgl.pjpy.general.pjsz.bjdl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xsgzgl.comm.BasicInit;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszBjdlInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_�༶����_ͨ��_Init��
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

public class PjszBjdlInit extends BasicInit {

	/**
	 * �༶����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initBjdl(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszBjdlInterface service = myService.getPjszBjdlService(myForm);

		// ����·��
		String path = "general_pjpy.do?method=pjszBjdl";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
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
		String[] qtzd = new String[] { "pjxn", "pjxq", "forward" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, forward };

		// ==================�ܷ������� begin==================
		String message = "";
		String operation = myService.getOperation("115");
		if ("later".equalsIgnoreCase(operation)) {
			message = "����������Ѿ�������û��ύ���������ٽ�����ز������������飬����ϵ����Ա��";
			request.setAttribute("operation", "no");
		} else if ("early".equalsIgnoreCase(operation)) {
			message = "�����������ǰһ����δ������û��ύ�������ܶ��������ز������������飬����ϵ����Ա��";
			request.setAttribute("operation", "no");
		}
		// =================�ܷ������� end ===================

		// �༶�����б�
		List<HashMap<String, String>> bjdlList = getBjdlList();
		request.setAttribute("bjdlList", bjdlList);

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMessage(message);
	}

	/**
	 * ��ò������б�
	 * 
	 * @author ΰ�����
	 */
	private List<HashMap<String, String>> getBjdlList() {

		DAO dao = DAO.getInstance();
		String sql = "select bjdldm dm,bjdlmc mc from xg_pjpy_bjdldmb order by bjdldm ";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {},
				new String[] { "dm", "mc" });
		return list;
	}
}
