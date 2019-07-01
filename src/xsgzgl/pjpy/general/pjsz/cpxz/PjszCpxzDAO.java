package xsgzgl.pjpy.general.pjsz.cpxz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_����С��_ͨ��_DAO��
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

public class PjszCpxzDAO extends CommDAO {

	// ==================ִ�в�ѯ����==============================
	/**
	 * ��ý����(����С��)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getPjszCpxzList(PjpyGeneralForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// �û�����
		String yhlx = model.getYhlx();

		// ====================��������===================================
		user.setUserStatus(yhlx);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		// query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.bjdm pk,a.nj,a.xymc,");
		tableSql.append("a.zymc,a.bjmc,a.cpzmc ");
		tableSql.append("from (");
		tableSql.append("select a.nj,a.xydm,a.xymc,a.zydm, ");
		tableSql.append("a.zymc,a.bjdm,a.bjmc, ");
		tableSql.append("nvl(c.cpzmc,'δ����') cpzmc, ");
		tableSql.append("case when c.cpzmc is null then '��' else '��' end sfsz ");
		tableSql.append("from view_njxyzybj a left join xg_pjpy_cpzb c ");
		tableSql.append("on a.bjdm = c.bjdm ");
		tableSql.append(searchTjByUser);
		tableSql.append(" order by a.nj desc,a.xydm,a.zydm,a.bjdm ) a ");
		tableSql.append(query);
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = new String[] { "pk", "nj", "xymc", "zymc", "bjmc",
				"cpzmc" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}

	/**
	 * ���δά��������İ༶��
	 * 
	 * @author ΰ�����
	 */
	public String getNoCpzNum(PjszCpxzModel model, User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num ");
		sql.append("from view_njxyzybj a ");
		sql.append("where not exists (select 1 from ");
		sql.append("xg_pjpy_cpzb b where a.bjdm = b.bjdm) ");

		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");

		return num;
	}
	// ==================ִ�в�ѯ���� end==============================

	// ==================ִ���������� begin=============================
	/**
	 * �������ݣ�xg_pjpy_cpzb:�������
	 * 
	 * @table �������
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean insertCpzb(String[] bjdm, String cpzmc, User user)
			throws Exception {

		boolean flag = false;

		// ����
		String tableName = "xg_pjpy_cpzb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_cpzb ");
		sql.append("(bjdm,cpzmc)");
		sql.append("values");
		sql.append("(?,?)");

		List<String[]> params = new ArrayList<String[]>();
		if (bjdm != null && bjdm.length > 0) {
			for (int i = 0; i < bjdm.length; i++) {
				String[] value = new String[] { bjdm[i], cpzmc };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}

	/**
	 * �������ݣ�xg_pjpy_cpzb:�������
	 * 
	 * @table �������
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean insertCpzb(String[] nj, String[] xydm, String[] zydm,
			String[] bjdm, String cpzmc, User user) throws Exception {

		// ����
		String tableName = "xg_pjpy_cpzb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_cpzb ");
		sql.append("(bjdm,cpzmc)");
		sql.append("select bjdm,'" + cpzmc + "' cpzmc ");
		sql.append("from view_njxyzybj b ");
		sql.append("where 1=? ");
		sql.append(getBmxxQuery(nj, xydm, zydm, bjdm, "b"));
		
		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);
		
		return flag;
	}
	

	/**
	 * �������ݣ�xg_pjpy_cpzb:�������
	 * 
	 * @table �������
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean insertCpzb(String cpzmc, User user) throws Exception {

		// ����
		String tableName = "xg_pjpy_cpzb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_cpzb (cpzmc, bjdm) ");
		sql.append("select " + cpzmc + ", bjdm ");
		sql.append("from view_njxyzybj a ");
		sql.append("where not exists (select 1 from xg_pjpy_cpzb b ");
		sql.append("where a.bjdm = b.bjdm) ");
		sql.append("and 1=? ");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================ִ���������� end==============================
	
	// ==================ִ��ɾ������ begin =============================
	/**
	 * ɾ�����ݣ�xg_pjpy_cpzb:�������
	 * 
	 * @table �������
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteCpzb(String[] bjdm, User user) throws Exception {

		boolean flag = false;

		// ����
		String tableName = "xg_pjpy_cpzb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_cpzb ");
		sql.append("where 1=1 ");
		sql.append("and bjdm=? ");

		List<String[]> params = new ArrayList<String[]>();
		if (bjdm != null && bjdm.length > 0) {
			for (int i = 0; i < bjdm.length; i++) {
				String[] value = new String[] { bjdm[i] };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	
	/**
	 * ɾ�����ݣ�xg_pjpy_cpzb:�������
	 * 
	 * @table �������
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteCpzb(String[] nj, String[] xydm, String[] zydm,
			String[] bjdm, User user) throws Exception {

		// ����
		String tableName = "xg_pjpy_cpzb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_cpzb a ");
		sql.append("where 1=? ");
		sql.append("and exists( ");
		sql.append("select 1 from view_njxyzybj b ");
		sql.append("where a.bjdm = b.bjdm ");
		sql.append(getBmxxQuery(nj, xydm, zydm, bjdm, "b"));
		sql.append(") ");
		
		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================ִ��ɾ������ end =============================	
}
