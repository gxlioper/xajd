package xgxt.pjpy.comm.zhcp.jbsz;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_��������_init��
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

public class ZhcpJbszInit {

	/**
	 * ������������_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getDypfszRForm(RequestForm rForm, ZhcpJbszForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// ����ģ��
		String gnmk = "pjpy";
		// ������������
		String menu = "dypfsz";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "zhcp_jbsz_dypf.do";

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ����ѧ������
		String pjxqmc = jbszModel.getPjxqmc();
		// �۲�����
		String zczq = model.getZczq();
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc", "zczq" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc, zczq };

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}

	/**
	 * �۲�ӷ�����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getZcjfszRForm(RequestForm rForm, ZhcpJbszForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// ����ģ��
		String gnmk = "pjpy";
		// ������������
		String menu = "zcjfsz";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "zhcp_jbsz_zcjf.do";

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ����ѧ������
		String pjxqmc = jbszModel.getPjxqmc();
		// �۲�����
		String zczq = model.getZczq();
		// �۲�ӷ�����
		String zcjflx = model.getZcjflx();
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc",
				"zczq", "zcjflx" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc, zczq, zcjflx };

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}
}
