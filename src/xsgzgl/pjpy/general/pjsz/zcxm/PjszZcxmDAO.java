package xsgzgl.pjpy.general.pjsz.zcxm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_�۲���Ŀ_ͨ��_DAO��
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

public class PjszZcxmDAO extends CommDAO {

	// ==================ִ�в�ѯ����==============================
	/**
	 * �����Ŀ����
	 * 
	 * @author ΰ�����
	 */
	public int getXmjb(String xmdm) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xmjb from ");
		sql.append(" xg_pjpy_zcxmb a ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");
		sql.append(" and a.xmdm=? ");

		DAO dao = DAO.getInstance();

		String xmjb = dao.getOneRs(sql.toString(), new String[] { pjxn, pjxq,
				pjnd, xmdm }, "xmjb");

		return Integer.parseInt(xmjb);
	}

	/**
	 * ��������õ��۲���Ŀ
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getZcxmList() {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append("select xmdm,xmjb,mrxm  ");
		sql.append(" from xg_pjpy_zcxmb a ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");
		sql.append(" order by to_number(xmjb),xmdm ");

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd }, new String[] { "xmdm",
						"mrxm" });

		return list;
	}

	// ==================ִ�в�ѯ���� end ==========================

	// ==================ִ���������� =============================

	/**
	 * �������ݣ�xg_pjpy_zcxmb:�۲���Ŀ��
	 * 
	 * @table �۲���Ŀ��
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean insertZcxmb(String xmdm, String xmmc, String xmjb,
			String sjdm, String jjf, String lrly, User user) throws Exception {

		// ����
		String tableName = "xg_pjpy_zcxmb";

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_zcxmb ");
		sql.append("(xn,xq,nd,xmdm,xmmc,xmjb,sjdm,jjf,lrly)");
		sql.append("values");
		sql.append("(?,?,?,?,?,?,?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { pjxn, pjxq, pjnd, xmdm, xmmc, xmjb,
				sjdm, jjf, lrly };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * �������ݣ�xg_pjpy_zcblb:�۲������
	 * 
	 * @table �۲���Ŀ��
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean insertZcblb(String xmdm, String[] bldm, String[] bl,
			User user) throws Exception {

		// ����
		String tableName = "xg_pjpy_zcblb";

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_zcblb a ");
		sql.append("(xn,xq,nd,xmdm,bldm,bl)");
		sql.append("values");
		sql.append("(?,?,?,?,?,?)");

		boolean flag = false;

		List<String[]> params = new ArrayList<String[]>();
		if (bldm != null && bldm.length > 0) {
			for (int i = 0; i < bldm.length; i++) {
				String[] value = new String[] { pjxn, pjxq, pjnd, xmdm,
						bldm[i], bl[i] };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	// ==================ִ���������� end==============================

	// ==================ִ��ɾ������ =============================

	/**
	 * ɾ�����ݣ�xg_pjpy_zcxmb:�۲���Ŀ��
	 * 
	 * @table �۲���Ŀ��
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteZcxmb(String xmdm, User user) throws Exception {

		// ����
		String tableName = "xg_pjpy_zcxmb";

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_zcxmb a ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");
		sql.append(" and a.xmdm=? ");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { pjxn, pjxq, pjnd, xmdm };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * ɾ�����ݣ�xg_pjpy_zcxmb:�۲���Ŀ��
	 * 
	 * @table �۲���Ŀ��
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteZcxmb(String xmdm, String sjdm, User user)
			throws Exception {

		// ����
		String tableName = "xg_pjpy_zcxmb";

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_zcxmb a ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");
		sql.append(" and (a.xmdm=? or a.sjdm=?)");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { pjxn, pjxq, pjnd, xmdm, sjdm };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * ɾ�����ݣ�xg_pjpy_zcblb:�۲������
	 * 
	 * @table �۲���Ŀ��
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteZcblb(String xmdm, String[] bldm, User user)
			throws Exception {

		// ����
		String tableName = "xg_pjpy_zcblb";

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_zcblb a ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");
		sql.append(" and a.xmdm=? ");
		sql.append(" and a.bldm=? ");
		
		boolean flag = false;

		List<String[]> params = new ArrayList<String[]>();
		if (bldm != null && bldm.length > 0) {
			for (int i = 0; i < bldm.length; i++) {
				String[] value = new String[] { pjxn, pjxq, pjnd, xmdm, bldm[i] };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	
	/**
	 * ɾ�����ݣ�xg_pjpy_zcblb:�۲������
	 * 
	 * @table �۲���Ŀ��
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteZcblb(String xmdm, User user) throws Exception {

		// ����
		String tableName = "xg_pjpy_zcblb";

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_pjpy_zcblb a ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");
		sql.append(" and a.xmdm=? ");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { pjxn, pjxq, pjnd, xmdm };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	// ==================ִ��ɾ������ end =============================
	
	// ==================ִ���޸Ĳ��� =============================
	/**
	 * �޸����ݣ�xg_pjpy_zcxmb:�۲���Ŀ��
	 * 
	 * @table �۲���Ŀ��
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean updateZcxmb(String xmdm, String xmmc, String jjf,
			String lrly, User user) throws Exception {

		// ����
		String tableName = "xg_pjpy_zcxmb";

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_pjpy_zcxmb a ");
		sql.append(" set a.xmmc=? ");
		sql.append(" ,a.jjf=? ");
		sql.append(" ,a.lrly=? ");
		sql.append(" where a.xmdm=? ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { xmmc, jjf, lrly, xmdm, pjxn, pjxq, pjnd };
		params.add(value);
	
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);
		
		return flag;
	}
	// ==================ִ���޸Ĳ��� end =============================
}
