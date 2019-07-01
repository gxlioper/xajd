package xsgzgl.pjpy.general.pjsz.pjxm;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_������Ŀ_ͨ��_Init��
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

public class PjszPjxmInit extends BasicInit {

	/**
	 * ������Ŀ_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initPjxm(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);

		// ����·��
		String path = "pjpy_pjsz_pjxm.do";
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
		
		String xmdm=request.getParameter("pkValue");
		myForm.setXmdm(xmdm);
		
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "xmdm" ,"useCpz"};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq , xmdm,jbszModel.getCpz()};
		
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		// ��Ŀ�����б�
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> xmxzList = dao.getWhList("xg_pjpy_xmxzb",
				"xzdm", "xzmc", "", "", "", false);
		request.setAttribute("xmxzList", xmxzList);
	}
	
	/**
	 * ������Ŀ_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initPjxmUpdate(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);

		// ����·��
		String path = "pjpy_pjsz_pjxm.do";
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

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq" ,"userCpz" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq,jbszModel.getCpz() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
}
