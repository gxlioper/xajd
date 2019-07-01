package xgxt.xsxx.comm.jbsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��������_DAO��
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

public class XsxxJbszDAO extends CommDAO {

	/**
	 * ��������ʾ������
	 * 
	 * @author ΰ�����
	 */
	public String getMaxXsqdm() {

		DAO dao = DAO.getInstance();

		String sql = "select max(xsqdm) maxXsq from xg_view_xsxx_xsqsz";

		String maxXsq = dao.getOneRs(sql, new String[] {}, "maxXsq");

		if (Base.isNull(maxXsq)) {

			maxXsq = "0";

		}
		return maxXsq;
	}

	/**
	 * �����ʾ��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean delXsq() throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "delete from xg_xsxx_xsqszb";

		boolean flag = dao.runUpdate(sql, new String[] {});

		return flag;
	}
	
	/**
	 * ��պϲ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean delHbh() throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "delete from xg_xsxx_xsqhbb";

		boolean flag = dao.runUpdate(sql, new String[] {});

		return flag;
	}
	
	/**
	 * ����ֶ�λ��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean delZdwz() throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "delete from xg_xsxx_xsqwzb";

		boolean flag = dao.runUpdate(sql, new String[] {});

		return flag;
	}

	/**
	 * ������ʾ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveXsqsz(XsxxJbszForm model, User user) throws Exception {

		// ��ʾ������
		String[] xsqdm = model.getXsqdm();
		// ��ʾ˳��
		String[] xssx = model.getXssx();
		// �Ƿ�չ��
		String[] sfzk = model.getSfzk();
		// �Ƿ��ö�
		String[] sfzd = model.getSfzd();
		// �Ƿ�����
		String[] sfqy = model.getSfqy();

		boolean flag = true;

		if (xsqdm != null && xsqdm.length > 0) {

			String[] sql = new String[xsqdm.length];

			for (int i = 0; i < xsqdm.length; i++) {

				StringBuilder updateSql = new StringBuilder();
				updateSql.append("update xg_xsxx_xsqszb set ");
				updateSql.append("xssx = '" + xssx[i] + "',");
				updateSql.append("sfzk = '" + sfzk[i] + "',");
				updateSql.append("sfzd = '" + sfzd[i] + "',");
				updateSql.append("sfqy = '" + sfqy[i] + "'");
				updateSql.append(" where xsqdm = '" + xsqdm[i] + "'");

				sql[i] = updateSql.toString();
			}

			flag = saveArrDate(sql);
		}

		return flag;
	}

	/**
	 * �����Ӧ�������ֶ��б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getYfpZdList(XsxxJbszForm model) {

		DAO dao = DAO.getInstance();

		// �ֶ�
		String[] zd = model.getZd();
		// �Ƿ�����
		String[] sfqy = model.getSfqy();

		ArrayList<String> zdList = new ArrayList<String>();
		if (zd != null && zd.length > 0) {
			for (int i = 0; i < zd.length; i++) {
				if ("��".equalsIgnoreCase(sfqy[i])) {
					zdList.add(zd[i]);
				}
			}
		}

		List<HashMap<String, String>> list = null;

		if (zdList != null && zdList.size() > 0) {
			StringBuilder sql = new StringBuilder();

			sql.append("select zd,xsmc from (select a.search_zd zd ");
			sql.append(",a.search_ymxs xsmc,decode(b.zd,'','��','��') sfqy ");
			sql.append("from xg_view_xsxx_zdszb a left join xg_xsxx_xsqwzb b ");
			sql.append("on a.search_zd = b.zd) where sfqy = '��' ");

			sql.append("and (");
			for (int i = 0; i < zdList.size(); i++) {
				if (i != 0) {
					sql.append("or ");
				}
				sql.append("zd=? ");
			}
			sql.append(")");

			list = dao.getList(sql.toString(), zdList.toArray(new String[] {}),
					new String[] { "zd", "xsmc" });
		}

		return list;
	}
}
