package xsgzgl.xsxx.general.dljc;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.xsxx.general.XsxxGeneralForm;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��¼���_ͨ��_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ������
 * @version 1.0
 */
public class XsxxDljcDAO extends CommDAO {

	// ==================ִ�в�ѯ����begin =============================

	/**
	 * ��õ�¼�������
	 * 
	 * @date 2012-12-13
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getXsxxDljcList(XsxxGeneralForm myForm,
			XsxxDljcModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();

		sql
				.append("select rownum r,a.*,decode(a.xxws,'yes','��','no','��') xxwsmc ");
		sql.append("from (");
		sql.append("select ");
		sql.append("a.xh pk,a.xh,a.xm,a.nj,a.xymc,a.zymc,a.bjmc,");
		sql.append("nvl(b.xxws,'no') xxws,a.xydm,a.zydm,a.bjdm ");
		sql.append(" from view_xsjbxx a ");
		sql.append(" left join xg_xsxx_dljcb b ");
		sql.append(" on a.xh = b.xh ");
		sql.append(") a ");
		sql.append(query);
		sql.append(" order by a.xh ");

		// ====================SQLƴװ end================================

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageByPjQuery(myForm.getPages(), sql.toString(), inputV,
						new String[] { "pk", "xh", "xm", "nj", "xymc", "zymc",
								"bjmc", "xxwsmc" });

		return list;
	}

	/**
	 * ������������б�
	 * 
	 * @date 2012-12-18
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getSslxList() {

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct sslx ");
		sql.append("from xg_xsxx_dljczdb ");

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "sslx" });

		return list;
	}

	/**
	 * ����ֶ������б�
	 * 
	 * @date 2012-12-18
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getZdszList() {

		StringBuilder sql = new StringBuilder();
		sql.append("select a.zd,a.zdm,a.sslx,a.zdlx,a.checked,a.ssb,");
		sql.append("a.source_table,a.option_dm,a.option_mc,a.xssx, ");
		sql.append("case when b.zd is null then 'no' else 'yes' end flag ");
		sql.append("from xg_xsxx_dljczdb a ");
		sql.append("left join xg_xsxx_dljcszb b ");
		sql.append("on a.zd = b.zd ");
		sql.append("order by sslx,to_number(xssx)");

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "zd", "zdm", "sslx", "zdlx",
						"checked", "source_table", "option_dm", "option_mc",
						"ssb", "flag" });

		return list;
	}

	/**
	 * ����Ƿ���Ϣ����
	 * 
	 * @date 2012-12-19
	 * @author ΰ�����
	 */
	public String getXxws(String xh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select xxws ");
		sql.append("from xg_xsxx_dljcb ");
		sql.append("where xh = ? ");

		return dao.getOneRs(sql.toString(), new String[] { xh }, "xxws");
	}

	/**
	 * ��������ֶ��б�
	 * 
	 * @date 2012-12-20
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getWszdList() {

		StringBuilder sql = new StringBuilder();
		sql.append("select a.zd,a.zdm,a.sslx,a.zdlx,a.checked,a.ssb,");
		sql.append("a.source_table,a.option_dm,a.option_mc,a.xssx ");
		sql.append("from xg_xsxx_dljcszb b ");
		sql.append(",xg_xsxx_dljczdb a ");
		sql.append("where a.zd = b.zd ");
		sql.append("order by a.sslx,to_number(a.xssx)");

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "zd", "zdm", "sslx", "zdlx",
						"checked", "source_table", "option_dm", "option_mc",
						"ssb" });

		return list;
	}

	/**
	 * �������Դlist
	 * 
	 * @date 2012-12-20
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getListBySource(String tablename,
			String dm, String mc) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append(dm + " dm, ");
		sql.append(mc + " mc ");
		sql.append(" from ");
		sql.append(tablename);

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		return list;
	}

	// ==================ִ�в�ѯ���� end =============================

	// ==================ִ���������� begin =============================

	/**
	 * �������ݣ�xg_xsxx_dljcb:��¼����
	 * 
	 * @date 2012-12-18
	 * @table ��¼����
	 * @author ΰ�����
	 */
	public Boolean insertDljcb(String[] pkValue, User user) throws Exception {

		boolean flag = false;

		// ����
		String tableName = "xg_xsxx_dljcb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xsxx_dljcb ");
		sql.append("(xh)");
		sql.append("values");
		sql.append("(?)");

		List<String[]> params = new ArrayList<String[]>();
		if (pkValue != null && pkValue.length > 0) {
			for (int i = 0; i < pkValue.length; i++) {
				String[] value = new String[] { pkValue[i] };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}

	/**
	 * �������ݣ�xg_xsxx_dljcb:��¼����
	 * 
	 * @date 2012-12-18
	 * @table ��¼����
	 * @author ΰ�����
	 */
	public Boolean insertDljcb(String[] nj, String[] xy, String[] zy,
			String[] bj, User user) throws Exception {

		// ����
		String tableName = "xg_xsxx_dljcb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xsxx_dljcb ");
		sql.append("(xh)");
		sql.append("select xh ");
		sql.append("from view_xsjbxx b ");
		sql.append("where 1=? ");
		sql.append(getBmxxQuery(nj, xy, zy, bj, "b"));

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * �������ݣ�xg_xsxx_dljcszb:��¼������ñ�
	 * 
	 * @date 2012-12-19
	 * @table ��¼������ñ�
	 * @author ΰ�����
	 */
	public Boolean insertDljcszb(String[] zd, User user) throws Exception {

		boolean flag = false;

		// ����
		String tableName = "xg_xsxx_dljcszb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xsxx_dljcszb ");
		sql.append("(zd)");
		sql.append("values");
		sql.append("(?)");

		List<String[]> params = new ArrayList<String[]>();
		if (zd != null && zd.length > 0) {
			for (int i = 0; i < zd.length; i++) {
				String[] value = new String[] { zd[i] };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}

	// ==================ִ���������� end =============================

	// ==================ִ�и��²��� begin =============================

	/**
	 * �������ݣ�xsxxb:ѧ����Ϣ��
	 * 
	 * @date 2012-12-20
	 * @table ѧ����Ϣ��
	 * @author ΰ�����
	 */
	public Boolean updateXsxxb(String[] column, String[] value, String xh,
			User user) throws Exception {

		// ����
		String tableName = "xsxxb";

		StringBuilder sql = new StringBuilder();
		sql.append("update xsxxb ");
		for (int i = 0; i < column.length; i++) {
			if (i == 0) {
				sql.append(" set ");
			} else {
				sql.append(" ,");
			}
			sql.append(column[i]);
			sql.append(" = ? ");
		}

		sql.append("where xh = '" + xh + "'");

		List<String[]> params = new ArrayList<String[]>();
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * �������ݣ�xsfzxxb:ѧ��������Ϣ��
	 * 
	 * @date 2012-12-20
	 * @table ѧ��������Ϣ��
	 * @author ΰ�����
	 */
	public Boolean updateXsfzxxb(String[] column, String[] value, String xh,
			User user) throws Exception {

		// ����
		String tableName = "xsfzxxb";

		StringBuilder sql = new StringBuilder();
		sql.append("update xsfzxxb ");
		for (int i = 0; i < column.length; i++) {
			if (i == 0) {
				sql.append(" set ");
			} else {
				sql.append(" ,");
			}
			sql.append(column[i]);
			sql.append(" = ? ");
		}

		sql.append("where xh = '" + xh + "'");

		List<String[]> params = new ArrayList<String[]>();
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * �������ݣ�xg_xsxx_dljcb:��¼����
	 * 
	 * @date 2012-12-20
	 * @table ��¼����
	 * @author ΰ�����
	 */
	public Boolean updateDljcb(String xh, User user) throws Exception {

		// ����
		String tableName = "xg_xsxx_dljcb";

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_dljcb ");
		sql.append("set xxws = 'yes'");
		sql.append("where xh = ? ");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { xh });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	// ==================ִ�и��²��� end=============================

	// ==================ִ��ɾ������ begin=============================

	/**
	 * ɾ�����ݣ�xg_xsxx_dljcb:��¼����
	 * 
	 * @date 2012-12-18
	 * @table ��¼����
	 * @author ΰ�����
	 */
	public Boolean deleteDljcb(String[] pkValue, User user) throws Exception {

		boolean flag = false;

		// ����
		String tableName = "xg_xsxx_dljcb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xsxx_dljcb ");
		sql.append("where 1=1 ");
		sql.append("and xh=? ");

		List<String[]> params = new ArrayList<String[]>();
		if (pkValue != null && pkValue.length > 0) {
			for (int i = 0; i < pkValue.length; i++) {
				String[] value = new String[] { pkValue[i] };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}

	/**
	 * ɾ�����ݣ�xg_xsxx_dljcb:��¼����
	 * 
	 * @date 2012-12-18
	 * @table ��¼����
	 * @author ΰ�����
	 */
	public Boolean deleteDljcb(String[] nj, String[] xy, String[] zy,
			String[] bj, User user) throws Exception {

		// ����
		String tableName = "xg_xsxx_dljcb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xsxx_dljcb a ");
		sql.append("where 1=? ");
		sql.append("and exists( ");
		sql.append("select 1 from view_xsjbxx b ");
		sql.append("where a.xh = b.xh ");
		sql.append(getBmxxQuery(nj, xy, zy, bj, "b"));
		sql.append(") ");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });
		System.out.println(sql);
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * ɾ�����ݣ�xg_xsxx_dljcszb:��¼������ñ�
	 * 
	 * @date 2012-12-19
	 * @table ��¼������ñ�
	 * @author ΰ�����
	 */
	public Boolean deleteDljcszb(String[] zd, User user) throws Exception {

		// ����
		String tableName = "xg_xsxx_dljcszb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xsxx_dljcszb ");
		sql.append("where 1=? ");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { "1" };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	// ==================ִ��ɾ������ end =============================
	
	// ==================ִ�г�ʼ������ begin=============================
	
	/**
	 * �������ݣ�xsfzxxb:ѧ��������Ϣ��
	 * 
	 * @date 2012-12-20
	 * @table ѧ��������Ϣ��
	 * @author ΰ�����
	 */
	public Boolean initXsfzxxb(User user) throws Exception {

		// ����
		String tableName = "xsfzxxb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xsfzxxb(xh) ");
		sql.append("select xh from xsxxb a ");
		sql.append("where not exists(");
		sql.append("select 1 from xsfzxxb b ");
		sql.append("where a.xh=b.xh ");
		sql.append("and 1=? ");
		sql.append(")");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	// ==================ִ�г�ʼ������ end=============================
	
}
