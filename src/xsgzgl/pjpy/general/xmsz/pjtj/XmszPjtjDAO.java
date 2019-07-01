package xsgzgl.pjpy.general.xmsz.pjtj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��������_ͨ��_DAO��
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

public class XmszPjtjDAO extends CommDAO {

	// ================ִ�в�ѯ����===========================
	/**
	 * �����������
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getPjtjList(String xmdm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.tjdm,a.tjfw,a.gx,a.tjz,b.tjz pjtjz, ");
		sql.append("b.tjlx,b.tablename,b.zd,b.tsgs, ");
		sql.append("b.condition,b.xn,b.xq,b.nd,b.bzd, ");
		sql.append("'' tjms ");
		sql.append("from xg_pjpy_tjszb a,xg_pjpy_pjtjkb b ");
		sql.append("where a.tjdm = b.tjdm ");
		sql.append("and a.xmdm = ? ");
		sql.append("and b.sfqy = 'yes' ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm }, new String[] { "tjdm", "tjfw", "tjms",
						"gx", "tjz", "tjlx", "tablename", "zd", "tsgs",
						"condition", "xn", "xq", "nd", "bzd", "pjtjz" });

		return list;
	}
	
	/**
	 * �����������
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getPjtjList() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.tjdm,a.tjmc ");
		sql.append("from xg_pjpy_pjtjkb a ");
		sql.append("where 1=1 ");
		sql.append("and a.sfqy = 'yes' ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "tjdm", "tjmc" });

		return list;
	}
	
	/**
	 * �����������
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getPjtjInfo(String tjdm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.tsgs,a.tjz ");
		sql.append("from xg_pjpy_pjtjkb a ");
		sql.append("where 1=1 ");
		sql.append("and a.tjdm = ? ");

		HashMap<String, String> map = dao.getMapNotOut(sql.toString(),
				new String[] { tjdm });

		return map;
	}
	
	/**
	 * ��ñȽ�ֵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	public List<HashMap<String, String>> getBjz(String xh[],
			Map<String, String> map, String lx) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// ��������
		String condition = map.get("condition");
		// ѧ������
		String xn = map.get("xn");
		// ѧ������
		String xq = map.get("xq");
		// �������
		String nd = map.get("nd");
		// ���ֶ�
		String bzd = map.get("bzd");
		bzd = Base.isNull(bzd) ? "" : bzd;

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		// ��ǰѧ��
		String dqxn = Base.currXn;
		// ��ǰѧ��
		String dqxq = Base.currXq;
		// ��ǰ���
		String dqnd = Base.currNd;

		// ��������ֵ
		ArrayList<String> inputV = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		for (int i = 0; i < xh.length; i++) {
			if (i != 0) {
				sql.append(" union all ");
			}
			sql.append(" select a.xh,nvl(b.bjz,0) bjz from ");
			sql.append(" (select '" + xh[i] + "' xh from dual) a left join ");
			sql.append(" (");
			sql.append(" select ");

			// ���ͷǿ�
			if (!Base.isNull(lx)) {
				sql.append(lx);
				sql.append("(");
				sql.append(zd);
				sql.append(")");
			} else {
				sql.append(zd);
			}
			sql.append(" bjz from ");
			sql.append(tablename);
			sql.append(" a ");
			sql.append(" where 1 = 1 ");
			sql.append(" and xh = '" + xh[i] + "' ");

			// ��������
			sql.append(Base.isNull(condition) ? "" : condition);

			// ��Ҫ����ѧ��
			if ("pjxn".equalsIgnoreCase(xn)) {
				sql.append(" and " + bzd + "xn = '" + pjxn + "' ");
			} else if ("dqxn".equalsIgnoreCase(xn)) {
				sql.append(" and " + bzd + "xn = '" + dqxn + "' ");
			}

			// ��Ҫ����ѧ��
			if ("pjxq".equalsIgnoreCase(xq)) {
				sql.append(" and " + bzd + "xq = '" + pjxq + "' ");
			} else if ("dqxq".equalsIgnoreCase(xq)) {
				sql.append(" and " + bzd + "xq = '" + dqxq + "' ");
			}

			// ��Ҫ�������
			if ("pjnd".equalsIgnoreCase(nd)) {
				sql.append(" and " + bzd + "nd = '" + pjnd + "' ");
			} else if ("dqnd".equalsIgnoreCase(nd)) {
				sql.append(" and " + bzd + "nd = '" + dqnd + "' ");
			}

			sql.append(" ) b on 1=1");
		}

		DAO dao = DAO.getInstance();

		// �Ƚ�ֵ
		List<HashMap<String, String>> list = dao.getList(sql.toString(), inputV
				.toArray(new String[] {}), new String[] { "xh", "bjz" });

		return list;
	}
	// ================ִ�в�ѯ���� end===========================
	
	// ==================ִ���������� =============================
	/**
	 * �������ݣ�xg_pjpy_tjszb:�������ñ�
	 * 
	 * @table �༶�����
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean insertTjszb(String xmdm, String[] tjdm, String[] tjfw,
			String[] gx, String[] tjz, User user) throws Exception {

		boolean flag = false;
		// ����
		String tableName = "xg_pjpy_tjszb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_tjszb ");
		sql.append("(xmdm,tjdm,tjfw,gx,tjz)");
		sql.append("values");
		sql.append("(?,?,?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		if (tjdm != null && tjdm.length > 0) {
			for (int i = 0; i < tjdm.length; i++) {
				String[] value = new String[] { xmdm, tjdm[i], tjfw[i], gx[i],
						tjz[i], };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	// ==================ִ���������� end==============================
	
	// ==================ִ��ɾ������ =============================
	/**
	 * ɾ�����ݣ�xg_pjpy_tjszb:�������ñ�
	 * 
	 * @table �������ñ�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteTjszb(String xmdm, User user) throws Exception {

		// ����
		String tableName = "xg_pjpy_tjszb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_tjszb ");
		sql.append("where 1=1 ");
		sql.append("and xmdm=? ");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { xmdm };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================ִ��ɾ������ end=============================
}
