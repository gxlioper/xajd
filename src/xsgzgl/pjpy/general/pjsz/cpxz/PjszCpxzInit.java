package xsgzgl.pjpy.general.pjsz.cpxz;

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
import xsgzgl.pjpy.general.inter.pjsz.PjszCpxzInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_����С��_ͨ��_Init��
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

public class PjszCpxzInit extends BasicInit {

	/**
	 * ����С��_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initCpxz(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);
		
		// ����·��
		String path = "pjpy_pjsz_cpxz.do";
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
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		List<HashMap<String, String>> cpzList = getCpzList();
		request.setAttribute("cpzList", cpzList);
	}

	/**
	 * ��ò������б�
	 * 
	 * @author ΰ�����
	 */
	private List<HashMap<String, String>> getCpzList() {

		DAO dao = DAO.getInstance();
		String sql = "select distinct cpzmc dm,cpzmc mc from xg_pjpy_cpzb order by cpzmc ";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {},
				new String[] { "dm", "mc" });
		return list;
	}
}
