package xsgzgl.pjpy.general.xmsz.pjtj;

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
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszPjtjInterface;
import xsgzgl.pjpy.general.pjsz.bjdl.PjszBjdlService;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��������_ͨ��_Init��
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

public class XmszPjtjInit extends BasicInit {

	/**
	 * ��������_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initPjtj(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszPjtjInterface service = myService.getXmszPjtjService(myForm);
		XmszPjtjService pjtjService = new XmszPjtjService();
		PjszBjdlService bjdlService = new PjszBjdlService();
		PjszPjxmInterface pjxmService = myService.getPjszPjxmService(myForm);
		
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
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		

		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);
		boolean checkXssq=pjxmService.checkXssq(pjxmModel, user);
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "xmdm","checkXssq" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, xmdm ,String.valueOf(checkXssq)};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		// ���������б�
		List<HashMap<String, String>> pjtjList = pjtjService.getPjtjList();
		request.setAttribute("pjtjList", pjtjList);

		// �༶�����б�
		List<HashMap<String, String>> bjdlList = bjdlService.getBjdlList();
		request.setAttribute("bjdlList", bjdlList);
	}
}
