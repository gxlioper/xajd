package xsgzgl.pjpy.bjlhdx.djbg;

import java.util.HashMap;
import java.util.List;
import xgxt.DAO.DAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ǼǱ��_�������ϴ�ѧ_DAO��
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

public class PjpyDjbgDAO extends xsgzgl.pjpy.general.djbg.PjpyDjbgDAO {

	/**
	 * ��ñ��������б���Ϣ
	 * 
	 * @date 2013-01-31
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getBcpjList(String[] xh, String xmmc) {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ��������
		String pjzq = jbszModel.getPjzq();
		
		String xmdm = getOneValue("xg_pjpy_pjxmwhb", "xmdm",
				"pjxn||pjxq||pjnd||xmmc", pjxn + pjxq + pjnd + xmmc);

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh,a.xm,b.sjhm,f.sqly ");
		sql.append(",decode(b.xb, '1', '��', '2', 'Ů', b.xb) xb ");
		sql.append(",(select c.zzmmmc from zzmmdmb c where c.zzmmdm = b.zzmm) zzmmmc ");
		sql.append(",(select c.mzmc from mzdmb c where c.mzdm = b.mz) mzmc ");
		sql.append(",'" + xmmc + "' xmmc ");
		sql.append(",'" + pjxn + "' pjxn ");
		sql.append(",a.zymc,a.xymc,a.bjmc ");
		sql.append(",d.zd1,d.zd2,d.zd3,d.zd4,d.zcfbjpm ");
		sql.append("from xg_view_pjpy_pjryk a ");
		sql.append("left join xsxxb b on a.xh = b.xh ");
		sql.append("left join (");
		sql.append("select xh,zd1,zd2,zd3,zd4,zcfbjpm ");
		sql.append("from xg_pjpy_zhcpb ");
		sql.append("where xn = '" + pjxn + "' ");
		sql.append("and xq = '" + pjxq + "' ");
		sql.append("and nd = '" + pjnd + "' ");
		sql.append(") d on a.xh = d.xh ");
		sql.append("left join xg_pjpy_pjxmsqb f  ");
		sql.append("on a.xh = f.xh ");
		sql.append("and f.xmdm = '"+xmdm+"' ");
		sql.append("and f.pjxn = '" + pjxn + "' ");
		sql.append("and f.pjxq = '" + pjxq + "' ");
		sql.append("and f.pjnd = '" + pjnd + "' ");
		sql.append("where 1=1 ");
		sql.append("and a.xh in (");
		for (int i = 0; i < xh.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");

		String[] outValue = new String[] { "xh", "xm", "xmmc", "xymc", "zymc",
				"bjmc", "xb", "zzmmmc", "mzmc", "pjxn", "sjhm", "zd1", "zd2",
				"zd3", "zd4", "zcfbjpm", "sqly" };

		return dao.getList(sql.toString(), xh, outValue);
	}

	/**
	 * ��ñ�������������
	 * 
	 * @date 2013-01-31
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getBcshList(String[] xh, String xmmc) {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ��������
		String pjzq = jbszModel.getPjzq();

		StringBuilder sql = new StringBuilder();
		sql.append(" select * from( ");
		sql.append(" select a.xh,a.shyj,a.shjb,a.sqly,b.xm ");
		sql.append(" from (");

		sql.append(" select a.*, b.xmmc,c.sqly,d.xh shjb ");
		sql.append(" from xg_pjpy_pjxmshb a, ");
		sql.append(" xg_pjpy_pjxmwhb b, ");
		sql.append(" xg_pjpy_pjxmsqb c, ");
		sql.append(" xg_xtwh_spbz d ");
		sql.append(" where a.xmdm = b.xmdm ");
		sql.append(" and a.xh = c.xh ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and a.xmdm = c.xmdm ");

		sql.append(" and b.lcid=d.splc ");
		sql.append(" and a.xtgwid=d.spgw ");
		sql.append(" and b.xmmc = '" + xmmc + "' ");
		sql.append(" and c.xh in (");
		for (int i = 0; i < xh.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");

		if ("xn".equalsIgnoreCase(pjzq)) {
			sql.append(" and b.pjxn=(select pjxn from xg_pjpy_xtszb) ");
		}
		if ("xq".equalsIgnoreCase(pjzq)) {
			sql.append(" and b.pjxn=(select pjxn from xg_pjpy_xtszb) ");
			sql.append(" and b.pjxq=(select pjxq from xg_pjpy_xtszb) ");
		}
		if ("nd".equalsIgnoreCase(pjzq)) {
			sql.append(" and pjnd=(select pjnd from xg_pjpy_xtszb) ");
		}

		sql.append(") a left join yhb b on a.shr=b.yhm  ");

		sql.append(" order by shjb desc )");

		return dao.getList(sql.toString(), xh, new String[] { "xh", "shyj",
				"shjb", "xm" });
	}

	/**
	 * �����ʷ�����б���Ϣ
	 * 
	 * @date 2013-02-04
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getLspjList(String[] xh, String pjxn,
			String pjxq, String pjnd, String xmmc) {

		DAO dao = DAO.getInstance();
		
		String xmdm = getOneValue("xg_pjpy_pjxmwhb", "xmdm",
				"pjxn||pjxq||pjnd||xmmc", pjxn + pjxq + pjnd + xmmc);

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh,a.xm,b.sjhm,f.sqly ");
		sql.append(",decode(b.xb, '1', '��', '2', 'Ů', b.xb) xb ");
		sql.append(",(select c.zzmmmc from zzmmdmb c where c.zzmmdm = b.zzmm) zzmmmc ");
		sql.append(",(select c.mzmc from mzdmb c where c.mzdm = b.mz) mzmc ");
		sql.append(",'" + xmmc + "' xmmc ");
		sql.append(",'" + pjxn + "' pjxn ");
		sql.append(",a.zymc,a.xymc,a.bjmc ");
		sql.append(",d.zd1,d.zd2,d.zd3,d.zd4,d.zcfbjpm ");
		sql.append("from xg_view_pjpy_pjryk a ");
		sql.append("left join xsxxb b on a.xh = b.xh ");
		sql.append("left join (");
		sql.append("select xh,zd1,zd2,zd3,zd4,zcfbjpm ");
		sql.append("from xg_pjpy_zhcpb ");
		sql.append("where xn = '" + pjxn + "' ");
		sql.append("and xq = '" + pjxq + "' ");
		sql.append("and nd = '" + pjnd + "' ");
		sql.append(") d on a.xh = d.xh ");
		sql.append("left join xg_pjpy_pjxmsqb_backup f  ");
		sql.append("on a.xh = f.xh ");
		sql.append("and f.xmdm = '"+xmdm+"' ");
		sql.append("and f.pjxn = '" + pjxn + "' ");
		sql.append("and f.pjxq = '" + pjxq + "' ");
		sql.append("and f.pjnd = '" + pjnd + "' ");
		sql.append("where 1=1 ");
		sql.append("and a.xh in (");
		for (int i = 0; i < xh.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");

		String[] outValue = new String[] { "xh", "xm", "xmmc", "xymc", "zymc",
				"bjmc", "xb", "zzmmmc", "mzmc", "pjxn", "sjhm", "zd1", "zd2",
				"zd3", "zd4", "zcfbjpm", "sqly" };

		return dao.getList(sql.toString(), xh, outValue);
	}
	
	/**
	 * �����ʷ����������
	 * 
	 * @date 2013-02-05
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getLsshList(String[] xh, String pjxn,
			String pjxq, String pjnd, String xmmc) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select * from( ");
		sql.append(" select a.xh,a.shyj,a.shjb,a.sqly,b.xm ");
		sql.append(" from (");

		sql.append(" select a.*, b.xmmc,c.sqly,d.xh shjb ");
		sql.append(" from xg_pjpy_pjxmshb_backup a, ");
		sql.append(" xg_pjpy_pjxmwhb b, ");
		sql.append(" xg_pjpy_pjxmsqb_backup c, ");
		sql.append(" xg_xtwh_spbz d ");
		sql.append(" where a.xmdm = b.xmdm ");
		sql.append(" and a.xh = c.xh ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and a.xmdm = c.xmdm ");

		sql.append(" and b.lcid=d.splc ");
		sql.append(" and a.xtgwid=d.spgw ");
		sql.append(" and b.xmmc = '" + xmmc + "' ");
		sql.append(" and c.xh in (");
		for (int i = 0; i < xh.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");
	
		sql.append(" and b.pjxn='" + pjxn + "' ");
		sql.append(" and b.pjxq='" + pjxq + "' ");
		sql.append(" and b.pjnd='" + pjnd + "' ");
		
		sql.append(") a left join yhb b on a.shr=b.yhm  ");

		sql.append(" order by shjb desc )");

		return dao.getList(sql.toString(), xh, new String[] { "xh", "shyj",
				"shjb", "xm" });
	}
}
