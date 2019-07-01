package xsgzgl.pjpy.general.xmsz.sjsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_ʱ������_ͨ��_DAO��
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

public class XmszSjszDAO extends CommDAO {

	// ==================ִ�в�ѯ���� begin==============================
	/**
	 * �����Ŀʱ�������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getSjszInfo(String xmdm) {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();

		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();

		sql.append("select a.sqkssj,a.sqjssj,a.sqkzkg, ");
		sql.append("a.shkssj,a.shjssj,a.shkzkg,a.bz ");
		sql.append("from xg_pjpy_sjszb a ");
		sql.append("where 1=1 ");
		sql.append("and a.pjxn = ? ");
		sql.append("and a.pjxq = ? ");
		sql.append("and a.xmdm = ? ");

		// ====================SQLƴװ end================================

		String[] inputValue = new String[] { pjxn, pjxq, xmdm };
		String[] outputValue = new String[] { "sqkssj", "sqjssj", "sqkzkg",
				"shkssj", "shjssj", "shkzkg","bz" };

		HashMap<String, String> map = dao.getMap(sql.toString(), inputValue,
				outputValue);

		return map;
	}
	// ==================ִ�в�ѯ���� end==============================
	
	// ==================ִ���������� begin=============================
	/**
	 * �������ݣ�xg_pjpy_sjszb:ʱ�����ñ�
	 * 
	 * @table ʱ�����ñ�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean insertSjszb(String xmdm, String sqkzkg, String shkzkg,
			String sqkssj, String sqjssj, String shkssj, String shjssj,
			String bz, User user) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		// ����
		String tableName = "xg_pjpy_sjszb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_sjszb ");
		sql.append("(pjxn,pjxq,pjnd,xmdm,bz,");
		sql.append("sqkssj,sqjssj,sqkzkg,");
		sql.append("shkssj,shjssj,shkzkg)");
		sql.append("values");
		sql.append("(?,?,?,?,?,?,?,?,?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { pjxn, pjxq, pjnd, xmdm, bz, sqkssj, sqjssj,
				sqkzkg, shkssj, shjssj, shkzkg });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================ִ���������� end=============================
	
	// ==================ִ��ɾ������ begin=============================
	/**
	 * ɾ�����ݣ�xg_pjpy_sjszb:ʱ�����ñ�
	 * 
	 * @table ʱ�����ñ�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteSjszb(String xmdm, User user) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		
		// ����
		String tableName = "xg_pjpy_sjszb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_sjszb a ");
		sql.append("where 1=1 ");
		sql.append("and a.pjxn = ? ");
		sql.append("and a.pjxq = ? ");
		sql.append("and a.pjnd = ? ");
		sql.append("and a.xmdm = ? ");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { pjxn, pjxq, pjnd, xmdm });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================ִ��ɾ������ end =============================
}
