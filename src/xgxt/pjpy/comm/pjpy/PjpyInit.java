package xgxt.pjpy.comm.pjpy;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �������ų�ʼ����
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * 
 * @version 1.0
 */

public class PjpyInit {

	/**
	 * ��������_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getPjjbszRForm(RequestForm rForm, HttpServletRequest request) {

		// ����ģ��
		String gnmk = "pjpy";
		// ������������
		String menu = "pjjbsz";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "pjpy_jbsz_jbsz.do";
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}
	
	/**
	 * ��������_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getPjlcszRForm(RequestForm rForm, HttpServletRequest request) {

		// ����ģ��
		String gnmk = "pjpy";
		// ������������
		String menu = "pjlcsz";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "pjpy_jbsz_lcsz.do";
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}
}
