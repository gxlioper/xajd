package xsgzgl.pjpy.general.xmsz.rssz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
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
 * Description: ��������_��Ŀ����_��������_ͨ��_Init��
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

public class XmszRsszInit extends BasicInit {

	/**
	 * ��������_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initRssz(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszRsszService service = new XmszRsszService();
		XmszRsszInterface rsszService = myService.getXmszRsszService(myForm);
		PjszPjxmInterface pjxmService = myService.getPjszPjxmService(myForm);
	
		// ����·��
		String path = "pjpy_xmsz_rssz.do";
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
		xmdm = Base.isNull(xmdm) ? myForm.getXmdm() : xmdm;
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);
		PjpyGeneralForm.setPjxmModel(pjxmModel);
		
		boolean checkXssq=pjxmService.checkXssq(pjxmModel, user);

		// ���Ʒ�Χ
		String szfw = pjxmModel.getKzfw();
		// ������Ⱥ
		String tsrq = pjxmModel.getTsrq();
		// ���Ʒ�Χ
		String kzfw = pjxmModel.getKzfw();
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "xmdm", "searchType","checkXssq" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, xmdm, kzfw,String.valueOf(checkXssq) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		// ����Ŀ�Ƿ��ʼ����
		boolean isExists = service.isExists("xg_pjpy_rsszb", "xmdm", xmdm);

		if (!isExists) {
			rsszService.initRsszb(xmdm, szfw, tsrq, user);
		}
	}
}
