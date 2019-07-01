package xgxt.pjpy.comm.pjpy.rssz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��������_DAO��
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

public class PjpyRsszDAO {

	/**
	 * �����Աȷ��4�ű������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRyqdList() {

		// ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		// ����ѧ����
		sql.append(" select count(1) num from xg_pjpy_xsb a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and a.pjxn = ? ");
		sql.append(" and a.pjxq = ? ");
		sql.append(" and a.pjnd = ? ");
		sql.append(" union all ");
		// ����ѧԺ��
		sql.append(" select count(1) num from xg_pjpy_xyb b ");
		sql.append(" where 1 = 1 ");
		sql.append(" and b.pjxn = ? ");
		sql.append(" and b.pjxq = ? ");
		sql.append(" and b.pjnd = ? ");
		sql.append(" union all ");
		// ����רҵ��
		sql.append(" select count(1) num from xg_pjpy_zyb c ");
		sql.append(" where 1 = 1 ");
		sql.append(" and c.pjxn = ? ");
		sql.append(" and c.pjxq = ? ");
		sql.append(" and c.pjnd = ? ");
		sql.append(" union all ");
		// �����༶��
		sql.append(" select count(1) num from xg_pjpy_bjb d ");
		sql.append(" where 1 = 1 ");
		sql.append(" and d.pjxn = ? ");
		sql.append(" and d.pjxq = ? ");
		sql.append(" and d.pjnd = ? ");

		DAO dao = DAO.getInstance();
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd, pjxn, pjxq, pjnd, pjxn, pjxq,
						pjnd, pjxn, pjxq, pjnd, }, new String[] { "num" });
		
		return list;
	}
}
