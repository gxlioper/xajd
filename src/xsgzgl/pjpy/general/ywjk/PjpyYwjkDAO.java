package xsgzgl.pjpy.general.ywjk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xsgzgl.pjpy.general.PjpyGeneralDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �u���u��_�I�սӿ�_ͨ��_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyYwjkDAO extends PjpyGeneralDAO {

	/**
	 * ��ÌW���u���б�
	 * 
	 * @date 2013-01-23
	 * @author ΰ�����
	 */
	public List<String[]> getJxjList(String xh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.*, a.xn || a.xqmc pjzq ");
		sql.append("from (select xn || 'ѧ��' xn, ");
		sql.append("xq, ");
		sql.append("xmmc, ");
		sql.append("xmje, ");
		sql.append("hdsj, ");
		sql.append("case ");
		sql.append("when xq = 'no' then ");
		sql.append("'' ");
		sql.append("else ");
		sql.append("(select b.xqmc from xqdzb b where a.xq = b.xqdm) || 'ѧ��' ");
		sql.append("end xqmc ");
		sql.append("from xg_pjpy_pjlsxxb a ");
		sql.append("where xmlx = '01' ");
		sql.append("and xh = ?) a ");
		sql.append("order by a.xn, a.xq ");

		return dao.rsToVator(sql.toString(), new String[] { xh }, new String[] {
				"pjzq", "xmmc", "xmje", "hdsj" });
	}

	/**
	 * ��������ƺ��б�
	 * 
	 * @date 2013-01-28
	 * @author ΰ�����
	 */
	public List<String[]> getRychList(String xh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.*, a.xn || a.xqmc pjzq ");
		sql.append("from (select xn || 'ѧ��' xn, ");
		sql.append("xq, ");
		sql.append("xmmc, ");
		sql.append("xmje, ");
		sql.append("hdsj, ");
		sql.append("case ");
		sql.append("when xq = 'no' then ");
		sql.append("'' ");
		sql.append("else ");
		sql.append("(select b.xqmc from xqdzb b where a.xq = b.xqdm) || 'ѧ��' ");
		sql.append("end xqmc ");
		sql.append("from xg_pjpy_pjlsxxb a ");
		sql.append("where xmlx = '02' ");
		sql.append("and xh = ?) a ");
		sql.append("order by a.xn, a.xq ");

		return dao.rsToVator(sql.toString(), new String[] { xh }, new String[] {
				"pjzq", "xmmc", "xmje", "hdsj" });
	}

	/**
	 * ����۲��ͷ
	 * 
	 * @date 2013-01-30
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getZhcpTop() {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String pjxn = jbszModel.getPjxn();

		String pjxq = jbszModel.getPjxq();

		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append(" select xmdm,xmmc from xg_pjpy_zcxmb ");
		sql.append(" where 1=1 ");
		sql.append(" and xn = ? ");
		sql.append(" and xq = ? ");
		sql.append(" and nd = ? ");
		sql.append(" order by xmjb desc,xmdm ");

		return dao.getList(sql.toString(), new String[] { pjxn, pjxq, pjnd },
				new String[] { "xmdm", "xmmc" });
	}

	/**
	 * �۲��ͷ--����ְҵ����ѧԺѧ����Ϣ��ʾ���Ի�
	 * 
	 * @date 2013-5-30
	 * @author ��ǿ
	 */
	public List<HashMap<String, String>> getCzzyTop() {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String pjxn = jbszModel.getPjxn();

		String pjxq = jbszModel.getPjxq();

		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append(" select xmdm,xmmc from xg_pjpy_zcxmb ");
		sql.append(" where 1=1 ");
		sql.append(" and xn = ? ");
		sql.append(" and xq = ? ");
		sql.append(" and nd = ? ");
		sql.append(" and xmdm<> 'zd1' ");
		sql.append(" order by xmjb desc,xmdm ");

		return dao.getList(sql.toString(), new String[] { pjxn, pjxq, pjnd },
				new String[] { "xmdm", "xmmc" });
	}
	/**
	 * ��������ƺ��б�
	 * 
	 * @date 2013-01-28
	 * @author ΰ�����
	 */
	public List<String[]> getZhcpList(String xh,
			List<HashMap<String, String>> zhcpTop) {

		DAO dao = DAO.getInstance();

		List<String> outValue = new ArrayList<String>();

		StringBuilder sql = new StringBuilder();
		outValue.add("zczq");

		sql.append("select a.*, a.xn || a.xqmc zczq ");
		sql.append("from (select a.xn || 'ѧ��' xn, ");
		sql.append("a.xq, ");
		sql.append("case when a.xq = 'no' then ");
		sql.append("'' ");
		sql.append("else ");
		sql.append("(select b.xqmc from xqdzb b where a.xq = b.xqdm) || 'ѧ��' ");
		sql.append("end xqmc ");
		if (zhcpTop != null && zhcpTop.size() > 0) {
			for (int i = 0; i < zhcpTop.size(); i++) {
				//if (i != 0) {
				//	sql.append(",");
				//}
				sql.append(",a.");
				sql.append(zhcpTop.get(i).get("xmdm"));
				outValue.add(zhcpTop.get(i).get("xmdm"));
			}
		}

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		String zcpm = jbszForm.getZcpm();
		if ("0".equalsIgnoreCase(zcpm)) {
			sql.append(",a.cpzpm ");
			outValue.add("cpzpm");
		}

		if ("1".equalsIgnoreCase(zcpm) || "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			sql.append(",zcfnjxypm");
			outValue.add("zcfnjxypm");
		}

		if ("2".equalsIgnoreCase(zcpm) || "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			sql.append(",zcfnjzypm");
			outValue.add("zcfnjzypm");
		}

		if ("3".equalsIgnoreCase(zcpm) || "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			sql.append(",zcfbjpm");
			outValue.add("zcfbjpm");
		}

		sql.append(" from xg_pjpy_zhcpb a ");
		sql.append(" where 1=1 ");
		sql.append(" and xh = ?) a ");
		sql.append(" order by a.xn, a.xq ");

		return dao.rsToVator(sql.toString(), new String[] { xh }, outValue
				.toArray(new String[] {}));
	}
	
	/**
	 * �۲���--����ְҵ����ѧԺѧ����Ϣ��ʾ���Ի�
	 * 
	 * @date 2013-01-28
	 * @author ΰ�����
	 */
	public List<String[]> getCzzyList(String xh,
			List<HashMap<String, String>> zhcpTop) {

		DAO dao = DAO.getInstance();

		List<String> outValue = new ArrayList<String>();

		StringBuilder sql = new StringBuilder();
		outValue.add("zczq");

		sql.append("select a.*, a.xn || a.xqmc zczq ");
		sql.append("from (select a.xn || 'ѧ��' xn, ");
		sql.append("a.xq, ");
		sql.append("case when a.xq = 'no' then ");
		sql.append("'' ");
		sql.append("else ");
		sql.append("(select b.xqmc from xqdzb b where a.xq = b.xqdm) || 'ѧ��' ");
		sql.append("end xqmc ");
		if (zhcpTop != null && zhcpTop.size() > 0) {
			for (int i = 0; i < zhcpTop.size(); i++) {
				//if (i != 0) {
				//	sql.append(",");
				//}
				sql.append(",a.");
				sql.append(zhcpTop.get(i).get("xmdm"));
				outValue.add(zhcpTop.get(i).get("xmdm"));
			}
		}
		
		sql.append(",a.zd20 ");
		outValue.add("zd20");
		sql.append(",a.zd21");
		outValue.add("zd21");
		sql.append(",a.zd28");
		outValue.add("zd28");
		sql.append(",a.zd30");
		outValue.add("zd30");
		sql.append(",a.zd22");
		outValue.add("zd22");
		sql.append(",a.zd23");
		outValue.add("zd23");
		sql.append(",a.zd27");
		outValue.add("zd27");
		sql.append(",a.zd29");
		outValue.add("zd29");
		
		
		sql.append(" from xg_pjpy_zhcpb a ");
		sql.append(" where 1=1 ");
		sql.append(" and xh = ?) a ");
		sql.append(" order by a.xn, a.xq ");

		return dao.rsToVator(sql.toString(), new String[] { xh }, outValue
				.toArray(new String[] {}));
	}
}
