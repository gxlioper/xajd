package xsgzgl.pjpy.general.wdpj.pjtj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_��������_ͨ��_DAO��
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

public class WdpjPjtjDAO extends CommDAO {

	// ================ִ�в�ѯ���� begin===========================
	/**
	 * ��ѯ��Ŀʱ��
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getXmsjInfo(String xmdm) {

		DAO dao = DAO.getInstance();

		// ��Ŀ���ö���
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append("select sqkssj,sqjssj,sqkzkg, ");
		sql.append("shkssj,shjssj,shkzkg,bz, ");
		sql.append("to_char(sysdate,'yyyyMMdd') nowtime ");
		sql.append("from xg_pjpy_sjszb ");
		sql.append("where 1=1 ");
		sql.append("and pjxn=? ");
		sql.append("and pjxq=? ");
		sql.append("and pjnd=? ");
		sql.append("and xmdm=? ");

		return dao.getMapNotOut(sql.toString(), new String[] { pjxn, pjxq,
				pjnd, xmdm });
	}

	/**
	 * �����������
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getPjtjList(String xmdm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.tjdm,a.tjfw,a.gx,a.tjz, ");
		sql.append("b.tjlx,b.tablename,b.zd,b.tsgs, ");
		sql.append("b.condition,b.xn,b.xq,b.nd,b.bzd, ");
		sql.append("b.tjms,b.tjmc,b.pjxm ");
		sql.append("from xg_pjpy_tjszb a,xg_pjpy_pjtjkb b ");
		sql.append("where a.tjdm = b.tjdm ");
		sql.append("and a.xmdm = ? ");
		sql.append("order by a.tjfw desc ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm }, new String[] { "tjdm", "tjfw", "tjms",
						"tjmc", "gx", "tjz", "tjlx", "tablename", "zd", "tsgs",
						"condition", "xn", "xq", "nd", "bzd", "pjxm" });

		return list;
	}

	/**
	 * ��ñȽ�ֵ
	 * 
	 * @author ΰ�����
	 */
	public String getBjz(String xh, Map<String, String> map, String lx) {

		// �û�����
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

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
		// ��Ŀ����
		String xmdm = map.get("xmdm");
		// ������Ŀ
		String pjxm = map.get("pjxm");
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
		inputV.add(xh);

		StringBuilder sql = new StringBuilder();

		sql.append(" select bjz from (");
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
		sql.append(" and xh = ? ");
		// ��������
		sql.append(Base.isNull(condition) ? "" : condition);

		// ��Ҫ����ѧ��
		if ("pjxn".equalsIgnoreCase(xn)) {
			sql.append(" and " + bzd + "xn = ? ");
			inputV.add(pjxn);
		} else if ("dqxn".equalsIgnoreCase(xn)) {
			sql.append(" and " + bzd + "xn = ? ");
			inputV.add(dqxn);
		}

		// ��Ҫ����ѧ��
		if ("pjxq".equalsIgnoreCase(xq)) {
			sql.append(" and " + bzd + "xq = ? ");
			inputV.add(pjxq);
		} else if ("dqxq".equalsIgnoreCase(xq)) {
			sql.append(" and " + bzd + "xq = ? ");
			inputV.add(dqxq);
		}

		// ��Ҫ�������
		if ("pjnd".equalsIgnoreCase(nd)) {
			sql.append(" and " + bzd + "nd = ? ");
			inputV.add(pjnd);
		} else if ("dqnd".equalsIgnoreCase(nd)) {
			sql.append(" and " + bzd + "nd = ? ");
			inputV.add(dqnd);
		}

		// ��Ҫ����������Ŀ
		if ("yes".equalsIgnoreCase(pjxm)) {
			sql.append(" and xmdm = ? ");
			inputV.add(xmdm);
		}
		
		sql.append(" ) where rownum = 1 ");

		DAO dao = DAO.getInstance();

		String bjz = dao.getOneRs(sql.toString(), inputV
				.toArray(new String[] {}), "bjz");

		return bjz;
	}
	
	/**
	 * 
	 * @����:ȡ�ղ�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-10-12 ����09:39:11
	 * @�޸ļ�¼: 
	 * @param xh
	 * @param map
	 * @param lx
	 * @return
	 * String �������� 
	 * @throws
	 */
	public HashMap<String, String> getBjzData(String xh, Map<String, String> map, String lx) {

		// �û�����
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

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
		// ��Ŀ����
		String xmdm = map.get("xmdm");
		// ������Ŀ
		String pjxm = map.get("pjxm");
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
		inputV.add(xh);

		StringBuilder sql = new StringBuilder();

		sql.append(" select * from (");
		sql.append(" select ");
		sql.append(" * from ");
		sql.append(tablename);
		sql.append(" a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = ? ");
		// ��������
		sql.append(Base.isNull(condition) ? "" : condition);

		// ��Ҫ����ѧ��
		if ("pjxn".equalsIgnoreCase(xn)) {
			sql.append(" and " + bzd + "xn = ? ");
			inputV.add(pjxn);
		} else if ("dqxn".equalsIgnoreCase(xn)) {
			sql.append(" and " + bzd + "xn = ? ");
			inputV.add(dqxn);
		}

		// ��Ҫ����ѧ��
		if ("pjxq".equalsIgnoreCase(xq)) {
			sql.append(" and " + bzd + "xq = ? ");
			inputV.add(pjxq);
		} else if ("dqxq".equalsIgnoreCase(xq)) {
			sql.append(" and " + bzd + "xq = ? ");
			inputV.add(dqxq);
		}

		// ��Ҫ�������
		if ("pjnd".equalsIgnoreCase(nd)) {
			sql.append(" and " + bzd + "nd = ? ");
			inputV.add(pjnd);
		} else if ("dqnd".equalsIgnoreCase(nd)) {
			sql.append(" and " + bzd + "nd = ? ");
			inputV.add(dqnd);
		}

		// ��Ҫ����������Ŀ
		if ("yes".equalsIgnoreCase(pjxm)) {
			sql.append(" and xmdm = ? ");
			inputV.add(xmdm);
		}
		
		sql.append(" ) where rownum = 1 ");

		DAO dao = DAO.getInstance();
		
		HashMap<String, String> resultMap = dao.getMapNotOut(sql.toString(), inputV
				.toArray(new String[] {}));
		return resultMap;
	}
	
	

	/**
	 * �����Ŀ��������
	 * 
	 * @author ΰ�����
	 */
	public String getSzrs(String xh, String xmdm, String kzfw) {

		StringBuilder sql = new StringBuilder();

		sql.append(" select nvl(a.qdrs,0) qdrs ");
		sql.append(" from xg_pjpy_rsszb a ");
		sql.append(" where 1=1 ");
		sql.append(" and xmdm=? ");
		sql.append(" and a.szfw=? ");
		sql.append(" and exists (select 1 ");
		sql.append(" from xg_view_pjpy_pjryk b ");
		sql.append(" where b.xh = ? ");
		sql.append(" and a.bmdm=");
		if ("nj".equalsIgnoreCase(kzfw)) {// �꼶
			sql.append("b.nj");
		} else if ("xy".equalsIgnoreCase(kzfw)) {// ѧԺ
			sql.append("b.xydm");
		} else if ("njxy".equalsIgnoreCase(kzfw)) {// �꼶+ѧԺ
			sql.append("b.nj||b.xydm");
		} else if ("njzy".equalsIgnoreCase(kzfw)) {// �꼶+רҵ
			sql.append("b.nj||b.zydm");
		} else if ("cpz".equalsIgnoreCase(kzfw)) {// �꼶+רҵ
			sql.append("b.cpzdm");
		} else {// �༶
			sql.append("b.bjdm");
		}
		sql.append(" ) ");

		DAO dao = DAO.getInstance();

		String szrs = dao.getOneRs(sql.toString(), new String[] { xmdm, kzfw,
				xh }, "qdrs");

		return szrs;
	}

	/**
	 * �����Ŀͨ������
	 * 
	 * @author ΰ�����
	 */
	public String getTgrs(String xh, String xmdm, String kzfw, String gwid) {

		// ��������Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		String bmdm = "";
		if ("nj".equalsIgnoreCase(kzfw)) {// �꼶
			bmdm = "b.nj";
		} else if ("xy".equalsIgnoreCase(kzfw)) {// ѧԺ
			bmdm = "b.xydm";
		} else if ("njxy".equalsIgnoreCase(kzfw)) {// �꼶+ѧԺ
			bmdm = "b.nj||b.xydm";
		} else if ("njzy".equalsIgnoreCase(kzfw)) {// �꼶+רҵ
			bmdm = "b.nj||b.zydm";
		} else {// �༶
			bmdm = "b.bjdm";
		}

		StringBuilder sql = new StringBuilder();

		sql.append(" select count(1) tgrs ");
		sql.append(" from (");
		sql.append(" select a.xh, ");
		sql.append(" " + bmdm + " bmdm ");
		sql.append(" from xg_pjpy_pjxmsqb a ");
		sql.append(" left join xg_view_pjpy_pjryk b");
		sql.append(" on a.xh=b.xh");
		sql.append(" where 1=1 ");
		sql.append(" and a.xmdm=? ");
		sql.append(" and a.pjxn=? ");
		sql.append(" and a.pjxq=? ");
		sql.append(" and a.pjnd=? ");
		sql.append(" and a.xh<>? ");
		sql.append(" and a.sqjg<>'btg' ");
		sql.append(" ) a ");
		sql.append(" where exists (select 1 ");
		sql.append(" from ( ");
		sql.append(" select ");
		sql.append(bmdm);
		sql.append(" bmdm from xg_view_pjpy_pjryk b ");
		sql.append(" where b.xh = ? ");
		sql.append(" ) b ");
		sql.append(" where a.bmdm=b.bmdm");
		sql.append(" ) ");

		DAO dao = DAO.getInstance();

		String szrs = dao.getOneRs(sql.toString(), new String[] { xmdm, pjxn,
				pjxq, pjnd, xh, xh }, "tgrs");

		return szrs;
	}

	/**
	 * �����Ŀ�Ǽ����Ŀ
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getJdxmList(String xmdm) {

		StringBuilder sql = new StringBuilder();

		sql.append(" select fjddm ");
		sql.append(" from xg_pjpy_jdszb a ");
		sql.append(" where 1=1 ");
		sql.append(" and xmdm=? ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm }, new String[] { "fjddm" });

		return list;
	}

	/**
	 * ����Ѿ���õ���Ŀ
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getHdxmList(String xh, String gwid) {

		// ��������Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append(" select a.xmdm,b.xmmc ");
		sql.append(" from xg_pjpy_pjxmsqb a left join xg_pjpy_pjxmwhb b ");
		sql.append(" on a.xmdm=b.xmdm ");
		sql.append(" and a.pjxn=b.pjxn ");
		sql.append(" and a.pjxq=b.pjxq ");
		sql.append(" and a.pjnd=b.pjnd ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xh=? ");
		sql.append(" and a.pjxn=? ");
		sql.append(" and a.pjxq=? ");
		sql.append(" and a.pjnd=? ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh, pjxn, pjxq, pjnd }, new String[] { "xmdm",
						"xmmc" });

		return list;
	}

	/**
	 * �����Ŀ�������б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getBshrList(String[] xh) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.cpzdm,a.cpzmc, ");
		sql.append(" a.zymc,a.bjdm,a.bjmc from xg_view_pjpy_pjryk a ");
		sql.append(" where 1 = 1 ");

		if (xh != null && xh.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < xh.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.xh = ? ");
			}
			sql.append(" ) ");
		}

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(), xh,
				new String[] { "xh", "xm", "nj", "xydm", "zydm", "bjdm",
						"xymc", "zymc", "bjmc", "cpzdm", "cpzmc" });

		return list;
	}

	/**
	 * �����Ŀ���������ڲ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String[] getSqrbm(List<HashMap<String, String>> sqrList, String kzfw)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" select ");

		// ���Ʒ�Χ
		if ("nj".equalsIgnoreCase(kzfw)) {// �꼶
			sql.append(" distinct nj bmdm");
		} else if ("xy".equalsIgnoreCase(kzfw)) {// ѧԺ
			sql.append(" distinct xydm bmdm");
		} else if ("njxy".equalsIgnoreCase(kzfw)) {// �꼶+ѧԺ
			sql.append(" distinct nj||xydm bmdm ");
		} else if ("njzy".equalsIgnoreCase(kzfw)) {// �꼶+רҵ
			sql.append(" distinct nj||zydm bmdm ");
		} else {
			sql.append(" distinct bjdm bmdm ");
		}

		sql.append(" from view_njxyzybj_all a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and exists (select 1 from xg_view_pjpy_pjryk b ");
		sql.append(" where a.bjdm = b.bjdm  ");

		if (sqrList != null && sqrList.size() > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < sqrList.size(); i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" b.xh = '" + sqrList.get(i).get("xh") + "' ");
			}
			sql.append(" ) ");
		}
		sql.append(" ) ");

		DAO dao = DAO.getInstance();

		String[] bmdm = dao.getRs(sql.toString(), new String[] {}, "bmdm");

		return bmdm;
	}

	/**
	 * �����Ŀ���������ڲ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String[] getSqrCpz(List<HashMap<String, String>> sqrList)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct cpz ");
		sql.append(" from xg_pjpy_pjrykb a ");
		sql.append(" where 1 = 1 ");

		if (sqrList != null && sqrList.size() > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < sqrList.size(); i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.xh = '" + sqrList.get(i).get("xh") + "' ");
			}
			sql.append(" ) ");
		}

		DAO dao = DAO.getInstance();

		String[] cpz = dao.getRs(sql.toString(), new String[] {}, "cpz");

		return cpz;
	}
	
	/**
	 * �����Ŀͨ������
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getBmrsList(String xmdm, String gwid,
			String[] xh, String[] bmdm, String kzfw) {

		// ��������Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.bmdm, a.bmrs, nvl(b.ytgrs, 0) ytgrs from ");

		sql.append(" (select a.bmdm,nvl(a.qdrs,0) bmrs from xg_pjpy_rsszb a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and a.xmdm = ? ");
		sql.append(" and a.szfw = ? ");
		if (bmdm != null && bmdm.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < bmdm.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.bmdm = '" + bmdm[i] + "' ");
			}
			sql.append(" ) ");
		}
		sql.append(") a");

		// ���Ʒ�Χ
		String dm = "";
		if ("nj".equalsIgnoreCase(kzfw)) {// �꼶
			dm = "nj";
		} else if ("xy".equalsIgnoreCase(kzfw)) {// ѧԺ
			dm = "xydm";
		} else if ("njxy".equalsIgnoreCase(kzfw)) {// �꼶+ѧԺ
			dm = "nj||b.xydm";
		} else if ("njzy".equalsIgnoreCase(kzfw)) {// �꼶+רҵ
			dm = "nj||b.zydm";
		} else if ("cpz".equalsIgnoreCase(kzfw)) {// �꼶+רҵ
			dm = "cpzdm";
		} else {// �༶
			dm = "bjdm";
		}

		sql.append(" left join (select b." + dm + " bmdm, count(1) ytgrs ");
		sql.append(" from (");
		sql.append(" select c.xh,d.nj,d.xydm,d.zydm,d.bjdm,d.cpzdm ");
		sql.append(" from xg_pjpy_pjxmsqb c, ");
		sql.append(" xg_view_pjpy_pjryk d ");
		sql.append(" where c.xh=d.xh ");
		sql.append(" and c.xmdm = ? ");
		sql.append(" and c.pjxn = ? ");
		sql.append(" and c.pjxq = ? ");
		sql.append(" and c.pjnd = ? ");
		if (!Base.isNull(gwid)) {
			sql.append(" and exists (");
			sql.append(" select 1 from xg_pjpy_pjxmshb e ");
			sql.append(" where e.xmdm=c.xmdm ");
			sql.append(" and e.pjxn=c.pjxn ");
			sql.append(" and e.pjxq=c.pjxq ");
			sql.append(" and e.pjnd=c.pjnd ");
			sql.append(" and e.xh=c.xh ");
			sql.append(" and e.shzt='tg' ");
			sql.append(" and e.xtgwid='" + gwid + "' ");
			sql.append(" )");
		}
		sql.append(" ) b where 1 = 1 ");

		// �������
		if (xh != null && xh.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < xh.length; i++) {
				if (i != 0) {
					sql.append(" and ");
				}
				sql.append(" b.xh <> '" + xh[i] + "' ");
			}
			sql.append(" ) ");
		}
		sql.append(" group by b." + dm + ") b on a.bmdm = b.bmdm ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm, kzfw, xmdm, pjxn, pjxq, pjnd },
				new String[] { "bmdm", "bmrs", "ytgrs" });

		return list;
	}
	
	/**
	 * ��÷Ǽ����Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXmjdList(PjszPjxmModel pjxmModel) {

		// ��Ŀ����
		String xmdm = pjxmModel.getXmdm();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xmdm,a.fjddm,b.xmmc fjdmc from xg_pjpy_jdszb a ");
		sql.append(" ,xg_pjpy_pjxmwhb b where a.fjddm=b.xmdm and a.xmdm = ? ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm },
				new String[] { "xmdm", "fjddm", "fjdmc" });

		return list;
	}
	
	/**
	 * ����Ի�ý�ѧ���б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getYhdjxjList(PjszPjxmModel pjxmModel,
			List<HashMap<String, String>> bshrList) {
		
		// ��������Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ��Ŀ����
		String xmdm = pjxmModel.getXmdm();
		// ��ÿ��Ƹ�λID
		String gwid = pjxmModel.getJdkz();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.xh, c.xm, a.xmdm ");
		sql.append(" from xg_pjpy_pjxmsqb a, xg_pjpy_pjxmshb b, xg_pjpy_pjrykb c ");
		sql.append(" where 1 = 1 ");
		sql.append(" and a.xmdm <> ? ");
		sql.append(" and a.pjxn = ? ");
		sql.append(" and a.pjxq = ? ");
		sql.append(" and a.pjnd = ? ");
		sql.append(" and a.xmdm = b.xmdm ");
		sql.append(" and a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq ");
		sql.append(" and a.pjnd = b.pjnd ");
		sql.append(" and a.xh = c.xh ");
		sql.append(" and a.xh = b.xh ");
		sql.append(" and b.xtgwid = ? ");
		sql.append(" and b.shzt = 'tg' ");
		sql.append(" and a.sqjg<>'btg' ");
		// �������
		if (bshrList != null && bshrList.size() > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < bshrList.size(); i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.xh = '" + bshrList.get(i).get("xh") + "' ");
			}
			sql.append(" ) ");
		}

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm, pjxn, pjxq, pjnd, gwid }, new String[] {
						"xmdm", "xh", "xm" });

		return list;
	}
	// ================ִ�в�ѯ���� end===========================

	// ================���Ի����� begin===========================
	/**
	 * �ж�����ѧ���Ƿ���Υ�ʹ���
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public String checkWjcfByPjXn(HashMap<String, Object> map)
			throws SQLException {

		DAO dao = DAO.getInstance();

		// ��������
		String sqlx = (String) map.get("sqlx");
		// ��Ϣ
		String message = "";
		// ��������ϱ�ѧ��ѧ��
		String[] xhArr = (String[]) map.get("xh");

		StringBuilder sql = new StringBuilder();
		sql.append(" select xh from wjcfb ");
		sql
				.append(" where xn = (select pjxn from xg_pjpy_xtszb where rownum = 1) ");

		sql.append(" and xh in( ");
		for (int i = 0; i < xhArr.length; i++) {
			if (i != 0) {

				sql.append(",");
			}

			sql.append("'" + xhArr[i] + "'");

		}
		sql.append(" ) ");

		sql.append(" and xxsh = 'ͨ��'  ");
		sql.append(" group by xh  ");

		String[] nozgXh = dao.getArray(sql.toString(), new String[] {}, "xh");

		// ����˵��
		String tjms = (String) map.get("tjms");

		boolean flag = (nozgXh == null || nozgXh.length == 0 ? true : false);

		if (!flag) {
			if ("sq".equalsIgnoreCase(sqlx)) {
				// �����������Ļ�
				message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

			} else {

				message = nozgXh[0];
			}
		}
		return message;
	}

	/**
	 * �ж��Ƿ���Υ�ʹ���
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public String checkWjcf(HashMap<String, Object> map) throws SQLException {

		DAO dao = DAO.getInstance();

		// ��������
		String sqlx = (String) map.get("sqlx");
		// ��Ϣ
		String message = "";
		// ��������ϱ�ѧ��ѧ��
		String[] xhArr = (String[]) map.get("xh");

		StringBuilder sql = new StringBuilder();
		sql.append(" select xh from wjcfb ");
		sql.append(" where xh in( ");
		for (int i = 0; i < xhArr.length; i++) {
			if (i != 0) {

				sql.append(",");
			}

			sql.append("'" + xhArr[i] + "'");

		}
		sql.append(" ) ");

		sql.append(" and xxsh = 'ͨ��'  ");
		sql.append(" group by xh  ");

		String[] nozgXh = dao.getArray(sql.toString(), new String[] {}, "xh");

		// ����˵��
		String tjms = (String) map.get("tjms");

		boolean flag = (nozgXh == null || nozgXh.length == 0 ? true : false);

		if (!flag) {
			if ("sq".equalsIgnoreCase(sqlx)) {
				// �����������Ļ�
				message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

			} else {

				message = nozgXh[0];
			}
		}
		return message;
	}

	/**
	 * �Ƿ�������
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public String checkKns(HashMap<String, Object> map) throws SQLException {

		DAO dao = DAO.getInstance();

		// ��������
		String sqlx = (String) map.get("sqlx");
		// ��Ϣ
		String message = "";
		// ��������ϱ�ѧ��ѧ��
		String[] xhArr = (String[]) map.get("xh");

		StringBuilder sql = new StringBuilder();
		sql.append(" select xh from xszz_knsb ");
		sql.append(" where xh not in( ");
		for (int i = 0; i < xhArr.length; i++) {
			if (i != 0) {

				sql.append(",");
			}

			sql.append("'" + xhArr[i] + "'");

		}
		sql.append(" ) ");

		String[] nozgXh = dao.getArray(sql.toString(), new String[] {}, "xh");

		// ����˵��
		String tjms = (String) map.get("tjms");

		boolean flag = (nozgXh == null || nozgXh.length == 0 ? true : false);

		if (!flag) {
			if ("sq".equalsIgnoreCase(sqlx)) {
				// �����������Ļ�
				message = "�������Ŀ��Ҫ��" + tjms + ",�����߲���������������";

			} else {

				message = nozgXh[0];
			}
		}
		return message;
	}
	/**
	 * 
	 * ����ͨ������
	 * �㽭����
	 */
	public boolean isTgbk(String xh) {
		// TODO �Զ����ɷ������
		DAO dao=DAO.getInstance();
		boolean flag=true;
		String sql="select nvl(bkcj,'0') bkcj from view_bjgkc where xh=?";
		try {
			String[] bkcj=dao.getRs(sql, new String[]{xh}, "bkcj");
			for(int i=0;i<bkcj.length;i++){
				flag=Integer.parseInt(bkcj[i])>=60;
				if(!flag){
					break;
				}
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return flag;
	}
	
	// ================���Ի����� end===========================
	
	/**
	 * ��ȡ�������Ʒ�Χ
	 * Ϊ�༶��ȷ������
	 * @param xmdm
	 * @param bjdm
	 * @return
	 */
	public HashMap<String,String>getQdrsByBj(String xmdm,String bjdm){
		
		DAO dao=DAO.getInstance();
		// ��������Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		String pjxn=jbszModel.getPjxn();
		
		String pjxq=jbszModel.getPjxq();
		
		String pjnd=jbszModel.getPjnd();
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" select a.xmmc,b.qdrs from ( ");
		sql.append(" select * from xg_pjpy_pjxmwhb  ");
		sql.append(" where xmdm=? and pjxn=? and pjxq=? and pjnd=? ");
		sql.append(" )a left join  ");
		sql.append(" (select xmdm,nvl(qdrs,0)qdrs,bmdm,szfw from xg_pjpy_rsszb b )b ");
		sql.append(" on a.xmdm=b.xmdm and a.kzfw=b.szfw ");
		sql.append(" and b.bmdm=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,pjxn,pjxq,pjnd,bjdm});
	}
	
	/**
	 * ���ѧ���Ƿ�����Ա����
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String,String> checkRyk(String xh) {
		
		DAO dao=DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from xg_pjpy_pjrykb where xh=? and sfcp='yes' ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}


}
