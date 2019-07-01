package xsgzgl.pjpy.general.pjsz.bjdl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
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
 * Description: ��������_��������_�༶����_ͨ��_DAO��
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

public class PjszBjdlDAO extends CommDAO {

	// ==================ִ�в�ѯ����==============================
	/**
	 * ��ý����(�༶����)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getPjszBjdlList(PjpyGeneralForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		//query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select distinct a.bjdm pk,a.nj,a.xydm,a.xymc,a.zydm,");
		tableSql.append("a.zymc,a.bjmc,a.bjdlmc ");
		tableSql.append("from (");
		tableSql.append("select a.nj,a.xydm,a.xymc,a.zydm, ");
		tableSql.append("a.zymc,a.bjdm,a.bjmc, ");
		tableSql.append("nvl(c.bjdlmc,'δ����') bjdlmc, ");
		tableSql.append("case when bjdlmc is null then '��' else '��' end sfsz ");
		tableSql.append("from view_njxyzybj a ");
		tableSql.append("left join (select c.bjdm, ");
		tableSql.append("(select d.bjdlmc from xg_pjpy_bjdldmb d where c.bjdlmc = d.bjdldm) bjdlmc ");
		tableSql.append("from xg_pjpy_bjdlb c) c ");
		tableSql.append("on a.bjdm = c.bjdm ");
		tableSql.append("where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(" ) a ");
		tableSql.append(query);
		tableSql.append(" order by a.nj desc,a.xydm,a.zydm,a.bjdm ) a ");
		tableSql.append("where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = new String[] { "pk", "nj", "xymc", "zymc", "bjmc",
				"bjdlmc" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	
	/**
	 * ��ѯ�༶�����б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getBjdlList() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select bjdldm dm ");
		sql.append(",bjdlmc mc ");
		sql.append("from xg_pjpy_bjdldmb ");
		sql.append("order by bjdldm ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		return list;
	}
	
	/**
	 * ��ѯĳѧ���İ༶����
	 * 
	 * @author ΰ�����
	 */
	public String getBjdl(String xh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select bjdlmc bjdl from xg_pjpy_bjdlb a ");
		sql.append("where exists (select 1 ");
		sql.append("from xg_pjpy_pjrykb b ");
		sql.append("where b.xh = ? ");
		sql.append("and a.bjdm = b.bjdm) ");

		String bjdl = dao.getOneRs(sql.toString(), new String[] { xh }, "bjdl");

		return bjdl;
	}
	// ==================ִ�в�ѯ���� end =============================	

	// ==================ִ���������� =============================
	/**
	 * �������ݣ�xg_pjpy_bjdlb:�༶�����
	 * 
	 * @table �༶�����
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean insertBjdlb(String[] bjdm, String bjdlmc, User user)
			throws Exception {

		boolean flag = false;

		// ����
		String tableName = "xg_pjpy_bjdlb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_bjdlb ");
		sql.append("(bjdm,bjdlmc)");
		sql.append("values");
		sql.append("(?,?)");

		List<String[]> params = new ArrayList<String[]>();
		if (bjdm != null && bjdm.length > 0) {
			for (int i = 0; i < bjdm.length; i++) {
				String[] value = new String[] { bjdm[i], bjdlmc };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}

	/**
	 * �������ݣ�xg_pjpy_bjdlb:�༶�����
	 * 
	 * @table �༶�����
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean insertBjdlb(String[] nj, String[] xydm, String[] zydm,
			String[] bjdm, String bjdlmc, User user) throws Exception {

		// ����
		String tableName = "xg_pjpy_bjdlb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_bjdlb ");
		sql.append("(bjdm,bjdlmc)");
		sql.append("select bjdm,'" + bjdlmc + "' bjdlmc ");
		sql.append("from view_njxyzybj b ");
		sql.append("where 1=? ");
		sql.append(getBmxxQuery(nj, xydm, zydm, bjdm, "b"));
		
		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);
		
		return flag;
	}
	// ==================ִ���������� end==============================
	
	// ==================ִ��ɾ������ =============================
	/**
	 * ɾ�����ݣ�xg_pjpy_bjdlb:�༶�����
	 * 
	 * @table �༶�����
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteBjdlb(String[] bjdm, User user) throws Exception {

		boolean flag = false;

		// ����
		String tableName = "xg_pjpy_bjdlb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_bjdlb ");
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
	 * ɾ�����ݣ�xg_pjpy_bjdlb:�༶�����
	 * 
	 * @table �������
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteBjdlb(String[] nj, String[] xydm, String[] zydm,
			String[] bjdm, User user) throws Exception {

		// ����
		String tableName = "xg_pjpy_bjdlb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_bjdlb a ");
		sql.append("where 1=? ");
		sql.append("and exists( ");
		sql.append("select 1 from view_njxyzybj b ");
		sql.append("where a.bjdm = b.bjdm ");
		sql.append(getBmxxQuery(nj, xydm, zydm, bjdm, "b"));
		sql.append(") ");
		
		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });
		System.out.println(sql);
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================ִ��ɾ������ end=============================
}
