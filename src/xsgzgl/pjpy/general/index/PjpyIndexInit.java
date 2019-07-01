package xsgzgl.pjpy.general.index;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyIndexInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ۺϲ���_ͨ��_Init��
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

public class PjpyIndexInit extends BasicInit{

	/**
	 * ������ҳ_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initIndex(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);
		
		// ����·��
		String path = "pjpy_general_index.do";
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
		// ��������
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// ��������
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// �۲�����
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ʽ·��
		String stylePath = request.getParameter("stylePath");
		// ������
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);
		
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
	
	/**
	 * ������������_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initPjpyEnd(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);
		
		// ����·��
		String path = "pjpy_general_index.do";
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
		// ��������
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// ��������
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// �۲�����
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq };
		
		List<HashMap<String,String>>bcpjtjInfo= service.getBcpjtjInfo(user);
		
		request.setAttribute("bcpjtjInfo", bcpjtjInfo);
		
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
	}
}
