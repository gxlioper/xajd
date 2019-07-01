package xsgzgl.pjpy.general.pjsz.zcxm;

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
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszZcxmInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_�۲���Ŀ_ͨ��_Init��
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

public class PjszZcxmInit extends BasicInit {

	/**
	 * ������ҳ_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initZcxm(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszZcxmInterface service = myService.getPjszZcxmService(myForm);

		// ����·��
		String path = "pjpy_pjsz_zcxm.do";
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

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// �۲���Ŀ
		List<HashMap<String, String>> zcxmList = getZcxmList();
		request.setAttribute("zcxmList", zcxmList);
		// �۲���Ŀ����
		List<HashMap<String, String>> zcblList = getZcblList();
		request.setAttribute("zcblList", zcblList);
		// �ϼ�����
		List<HashMap<String, String>> sjdmList = getSjdmList();
		request.setAttribute("sjdmList", sjdmList);
		// ��������
		List<HashMap<String, String>> bldmList = getBldmList();
		request.setAttribute("bldmList", bldmList);
	}

	/**
	 * ����۲���Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	private List<HashMap<String, String>> getZcxmList() {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append(" select xmdm,xmmc,xmjb,sjdm,lyb, ");
		sql.append(" mrxm,jjf ");
		sql.append(" from xg_pjpy_zcxmb a ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");
		sql.append(" order by to_number(xmjb),a.xmdm ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd }, new String[] { "xmdm",
						"xmmc", "xmjb", "sjdm", "lyb", "mrxm", "jjf" });

		return list;
	}

	/**
	 * ����۲���Ŀ�����б�
	 * 
	 * @author ΰ�����
	 */
	private List<HashMap<String, String>> getZcblList() {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append(" select xn,xq,nd,xmdm,xmmc,xmjb,sjdm,bldm,blmc,bl ");
		sql.append(" from xg_view_pjpy_zcxm a ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");
		sql.append(" order by to_number(xmjb),a.xmdm ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd }, new String[] { "xn", "xq",
						"nd", "xmdm", "xmmc", "xmjb", "sjdm", "bldm", "blmc",
						"bl" });
		return list;
	}

	/**
	 * ��ȡ�ϼ������б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getSjdmList() {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		// sql
		StringBuilder sql = new StringBuilder();

		sql.append(" select distinct(sjdm)sjdm from xg_pjpy_zcxmb a ");
		sql.append(" where 1=1  ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");

		sql.append("  order by sjdm nulls first ");

		String[] colList = { "sjdm" };
		return dao.getList(sql.toString(), new String[] { pjxn, pjxq, pjnd },
				colList);
	}

	/**
	 * ����۲�����б�
	 * 
	 * @author ΰ�����
	 */
	private List<HashMap<String, String>> getBldmList() {
		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append("select bldm,a.blmc ");
		sql.append("from xg_pjpy_zcbldmb a ");
		sql.append("where 1=1 ");
		sql.append("and a.xn=? ");
		sql.append("and a.xq=? ");
		sql.append("and a.nd=? ");
		sql.append("order by to_number(bldm) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd }, new String[] { "bldm",
						"blmc" });
		return list;
	}
}
