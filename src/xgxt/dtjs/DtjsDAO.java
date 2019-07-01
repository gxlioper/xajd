package xgxt.dtjs;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class DtjsDAO extends CommonQueryDAO {

	/**
	 * ��õ�Ա��Ϣ�����Ϣ
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDyxxInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * ��õ��Ž�������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDtjsList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", "pxxmdm", "zzzt", "lx","zbdm" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		other_query = Base.isNull(other_query) ? "" : other_query;

		if (!Base.isNull(query)) {
			query += " " + other_query;
		} else {
			query = other_query;
		}

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * ��õ��Ž��������Ϣ
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDtjsInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDtjsList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();

		if (Base.isNull(msg)) {
			msg = "----��ѡ��-----";
		}

		StringBuffer sql = new StringBuffer();
		sql.append("select '' dm, '" + msg + "'mc from dual union");
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" from " + tableName);
		if (!Base.isNull(pk)) {
			sql.append(" where " + pk + "= '" + pkValue + "'");
		}
		sql.append(" order by dm nulls first");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}

	/**
	 * �������ά��������ֵ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		} else if ("yesorno".equalsIgnoreCase(lx)) {
			dm = new String[] { "yes", "no" };
			mc = new String[] { "��", "��" };
		} else if ("ywlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		} else if ("bblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "������", "������", "�ϸ���", "���ϸ���" };
			mc = new String[] { "������", "������", "�ϸ���", "���ϸ���" };
		} else if ("zjlg_dylx".equalsIgnoreCase(lx)) {
			dm = new String[] { "Ԥ����Ա", "��Ա" };
			mc = new String[] { "Ԥ����Ա", "��Ա" };
		} else if ("xllx".equalsIgnoreCase(lx)) {// ѧ������
			dm = new String[] { "����","����", "ר��","����","˶ʿ","��ʿ" };
			mc = new String[] { "����","����", "ר��","����","˶ʿ","��ʿ" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * ɾ�����ϴ��ļ�
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String dzzd, String pk,
			String pkValue) throws Exception {
		DAO dao = new DAO();

		boolean flag = true;
		String sql = "select " + dzzd + " scdz from " + tableName + " where "
				+ pk + " = ?";
		String wjlj = dao.getOneRs(sql, new String[] { pkValue }, "scdz");
		if (wjlj != null) {
			File file = new File(wjlj);
			if (file.exists()) {
				file.delete();
				sql = "update " + tableName + " set " + dzzd + "='' where "
						+ pk + " = ?";
				flag = dao.runUpdate(sql, new String[] { pkValue });
			}
		}
		return flag;
	}

	/**
	 * ���ϵͳ��ǰʱ��
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		DAO dao = DAO.getInstance();
		String sql = "";
		if ("1".equalsIgnoreCase(lx)) {
			sql = "select to_char(sysdate, 'YYYY') || '��' || to_char(sysdate, 'MM') "
					+ "|| '��' || to_char(sysdate, 'DD') || '�� ' now from dual";
		} else if ("2".equalsIgnoreCase(lx)) {
			sql = "select to_char(sysdate, 'YYYY') ||  to_char(sysdate, 'MM') "
					+ "||  to_char(sysdate, 'DD') now from dual";
		}
		String now = dao.getOneRs(sql, new String[] {}, "now");
		return now;
	}

	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(dm);
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk + "='" + pkValue + "'");
		sql.append(" and rownum = 1 ");

		String value = dao.getOneRs(sql.toString(), new String[] {}, dm);
		return value;
	}
	
	/**
	 * �����ύ
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql)
			throws Exception {

		DAO dao = new DAO();
		boolean flag = true;
		int[] res = dao.runBatch(sql);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		return flag;
	}
	
	/**
	 * ��õ�Ա��Ϣ����
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public String getDyxxRs(String tableName,String pk,String pkValue,String query){
		DAO dao = new DAO();
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) rs from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk + " = '" + pkValue + "'");
		if(!Base.isNull(query)){
			sql.append(" and " + query);
		}
		String num = dao.getOneRs(sql.toString(), new String[] {}, "rs");
		return num;
	}
	
	/**
	 * ��õ�Ա��Ϣ
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDyxx(String[] key,
			String[] colList, String tableName, String pk, String distinct,String orderBy) {
		DAO dao = new DAO();

		StringBuffer sql = new StringBuffer();
		if (colList != null && colList.length > 0) {
			sql.append("select ");
			for (int i = 0; i < colList.length; i++) {
				if (i == 0) {
					if (!Base.isNull(distinct)) {
						sql.append("distinct(" + colList[i] + ")");
					} else {
						sql.append(colList[i]);
					}
				} else {
					sql.append("," + colList[i]);
				}
			}
			sql.append(" from " + tableName);
		}

		for (int i = 0; i < key.length; i++) {
			for (int j = i + 1; j < key.length; j++) {
				if (key[i].equalsIgnoreCase(key[j])) {
					key[j] = "";
				}
			}
		}

		StringBuffer query = new StringBuffer(" where ");
		for (int i = 0; i < key.length; i++) {
			if (i == 0) {
				query.append(pk + "='" + key[i] + "' ");
			} else {
				if (!Base.isNull(key[i])) {
					query.append(" or " + pk + "='" + key[i] + "' ");
				}
			}
		}
		query.append(" order by " + orderBy);
		
		List<HashMap<String, String>> list = dao.getList(
				sql + query.toString(), new String[] {}, colList);
		return list;
	}
	
	/**
	 * ��õ�Ա����
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDyxxRs(String[] key,
			String[] colList, String tableName, String pk, String distinct,String groupBy,String other_query) {
		DAO dao = new DAO();

		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) num");
		if (colList != null && colList.length > 0) {
			for (int i = 0; i < colList.length; i++) {
				if (!Base.isNull(distinct)) {
					sql.append(",distinct(" + colList[i] + ")");
				} else {
					sql.append("," + colList[i]);
				}
			}
		}
		sql.append(" from " + tableName);

		for (int i = 0; i < key.length; i++) {
			for (int j = i + 1; j < key.length; j++) {
				if (key[i].equalsIgnoreCase(key[j])) {
					key[j] = "";
				}
			}
		}

		StringBuffer query = new StringBuffer(" where (");
		for (int i = 0; i < key.length; i++) {
			if (i == 0) {
				query.append(pk + "='" + key[i] + "' ");
			} else {
				if (!Base.isNull(key[i])) {
					query.append(" or " + pk + "='" + key[i] + "' ");
				}
			}
		}
		query.append(")");
		query.append(other_query);
		query.append(" group by "+groupBy);
		
		String[] col = new String[colList.length+1];
		for(int i = 0;i<colList.length;i++){
			col[i]=colList[i];
		}
		col[colList.length] = "num";
		
		List<HashMap<String, String>> list = dao.getList(
				sql + query.toString(), new String[] {}, col);
		return list;
	}
	
	/**
	 * �޸ĵ�Ա����
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveOtherDylx(String[] tableName, String pk, String[] pkValue)
			throws Exception {
		boolean flag = true;
		int n = 0;

		if ((tableName != null && tableName.length > 0)
				&& (pkValue != null && pkValue.length > 0)) {
			String[] sql = new String[tableName.length * pkValue.length];
			for (int i = 0; i < tableName.length; i++) {
				for (int j = 0; j < pkValue.length; j++) {
					sql[n] = "update " + tableName[i] + " set zzzt='no' where "
							+ pk + "='" + pkValue[j] + "'";
					n++;
				}
			}
			flag = saveArrDate(sql);
		}
		return flag;
	}
	
	/**
	 * ��õ��Ž���ת���ȼ��б�
	 * 
	 * @param zhdj(�뵳����,�������ӣ���չ����Ԥ����Ա)
	 * @author luojw
	 */
	public List<HashMap<String, String>> getZhdjList(String zhdj) {

		DAO dao = DAO.getInstance();

		String[] inputValue = new String[] { zhdj };
		String[] outputValue = new String[] { "dm", "mc" };

		StringBuffer sql = new StringBuffer();

		sql.append("select jb dm, mc from dtjs_djdmb where jb >");
		sql.append("(select jb from dtjs_djdmb where mc = ?)");

		return dao.getList(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * �����Ҫת���ߵ�ѧ��
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public List<String> getZyInfoList(String tableName, String pk,
			String[] pkValue) throws SQLException {

		List<String> list = null;

		if (pkValue != null && pkValue.length > 0) {

			StringBuffer sql = new StringBuffer();
			sql.append("select distinct(xh) from ");
			sql.append(tableName);
			for (int i = 0; i < pkValue.length; i++) {
				if (i == 0) {
					sql.append(" where " + pk + "=? ");
				} else {
					sql.append(" or " + pk + "=? ");
				}
			}
			DAO dao = DAO.getInstance();
			list = dao.getList(sql.toString(), pkValue, "xh");
		}
		return list;
	}
	
	/**
	 * ���ѧ����Ա��Ϣ���
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDjList() {

		DAO dao = DAO.getInstance();

		// ��õ��ŵȼ��б�
		String[] inputValue = new String[] {};
		String[] colList = new String[] { "dm", "mc", "jb" ,"tablename"};
		StringBuffer sql = new StringBuffer();

		sql.append("select t.dm, t.mc, t.jb,case ");
		sql.append("when t.mc = '�뵳����' then '");
		sql.append(GlobalsVariable.DTJS_RDSQB);
		sql.append("'");
		sql.append("when t.mc = '��������' then '");
		sql.append(GlobalsVariable.DTJS_JJFZB);
		sql.append("'");
		sql.append("when t.mc = '��չ����' then '");
		sql.append(GlobalsVariable.DTJS_FZDXB);
		sql.append("'");
		sql.append("when t.mc = 'Ԥ����Ա' then '");
		sql.append(GlobalsVariable.DTJS_YBDYB);
		sql.append("'");
		sql.append("when t.mc = '��ʽ��Ա' then '");
		sql.append(GlobalsVariable.DTJS_DYXXB);
		sql.append("'");
		sql.append("end tablename from dtjs_djdmb t order by t.jb ");

		List<HashMap<String, String>> djList = dao.getList(sql.toString(),
				inputValue, colList);

		return djList;

	}
	
	/**
	 * ���ѧ����Ա��Ϣ���
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsDyInfoList(String[] zyxh) {
		
		DAO dao = DAO.getInstance();
		
		StringBuffer sql = new StringBuffer("select * from (");
		
		//ѧ��������Ϣ
		sql.append("select t.xh,t.xm,");
		sql.append("nvl(a." + GlobalsVariable.DTJS_RDSQB + ", 0) "+ GlobalsVariable.DTJS_RDSQB + ",");
		sql.append("nvl(b." + GlobalsVariable.DTJS_JJFZB + ", 0) "+ GlobalsVariable.DTJS_JJFZB + ",");
		sql.append("nvl(c." + GlobalsVariable.DTJS_FZDXB + ", 0) "+ GlobalsVariable.DTJS_FZDXB + ",");
		sql.append("nvl(d." + GlobalsVariable.DTJS_YBDYB + ", 0) "+ GlobalsVariable.DTJS_YBDYB + ",");
		sql.append("nvl(e." + GlobalsVariable.DTJS_DYXXB + ", 0) "+ GlobalsVariable.DTJS_DYXXB + " ");
		sql.append("from view_xsjbxx t ");
		
		//�뵳����
		sql.append("left join (select a.xh, count(a.xh) ");
		sql.append(GlobalsVariable.DTJS_RDSQB);
		sql.append(" from ");
		sql.append(GlobalsVariable.DTJS_RDSQB);
		sql.append(" a group by a.xh) a on t.xh = a.xh ");
		
		//��������
		sql.append("left join (select b.xh, count(b.xh) ");
		sql.append(GlobalsVariable.DTJS_JJFZB);
		sql.append(" from ");
		sql.append(GlobalsVariable.DTJS_JJFZB);
		sql.append(" b where b.zzzt = 'yes' group by b.xh) b on t.xh = b.xh ");
		
		//��չ����
		sql.append("left join (select c.xh, count(c.xh) ");
		sql.append(GlobalsVariable.DTJS_FZDXB);
		sql.append(" from ");
		sql.append(GlobalsVariable.DTJS_FZDXB);
		sql.append(" c where c.zzzt = 'yes' group by c.xh) c on t.xh = c.xh ");
		
		//Ԥ����Ա
		sql.append("left join (select d.xh, count(d.xh) ");
		sql.append(GlobalsVariable.DTJS_YBDYB);
		sql.append(" from ");
		sql.append(GlobalsVariable.DTJS_YBDYB);
		sql.append(" d where d.zzzt = 'yes' group by d.xh) d on t.xh = d.xh ");
		
		// ��ʽ��Ա
		sql.append("left join (select e.xh, count(e.xh) ");
		sql.append(GlobalsVariable.DTJS_DYXXB);
		sql.append(" from ");
		sql.append(GlobalsVariable.DTJS_DYXXB);
		sql.append(" e where e.zzzt = 'yes' group by e.xh) e on t.xh = e.xh ");
		
		// ��Ҫ��ѯѧ��
		sql.append(")");
		if (zyxh != null && zyxh.length > 0) {
			for (int i = 0; i < zyxh.length; i++) {
				if (i == 0) {
					sql.append(" where xh = ?");
				} else {
					sql.append(" or xh = ?");
				}
			}
		}
		
		//String[] inputValue = new String[] {};
		String[] colList = new String[] { "xh", "xm",
				GlobalsVariable.DTJS_RDSQB, GlobalsVariable.DTJS_JJFZB,
				GlobalsVariable.DTJS_FZDXB, GlobalsVariable.DTJS_YBDYB,
				GlobalsVariable.DTJS_DYXXB };
		
		List<HashMap<String, String>> xsdxInfoList = dao.getList(
				sql.toString(), zyxh, colList);
		
		return xsdxInfoList;
	}
	
	/**
	 * ���ѧ��������Ϣ
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public String[] getStuDefInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		
		DAO dao = DAO.getInstance();
		
		StringBuffer sql  = new StringBuffer();
		sql.append(" select * from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk);
		sql.append(" = ?");
		
		String[] rs = dao.getOneRs(sql.toString(), new String[]{pkValue}, colList);
		
		return rs;
	}
	
	/**
	 * ��ð༶��Ա����
	 * 
	 * @author luojw
	 */
	public String getBjtyRs(DtjsForm myForm) {

		DAO dao = DAO.getInstance();

		// �༶����
		String bjdm = myForm.getBjdm();

		StringBuffer sql = new StringBuffer();
		sql.append("select count(xh) tyrs from tyxxb a ");
		sql.append("where exists(select 1 from view_xsjbxx b ");
		sql.append("where a.xh = b.xh and b.bjdm = ? ) ");

		String[] inputValue = new String[] { bjdm };
		String outputValue = "tyrs";
		String tyrs = dao.getOneRs(sql.toString(), inputValue, outputValue);
		return tyrs;
	}
	
	/**
	 * ��ð༶��Ա����(����Ԥ����Ա,�뵳��������)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getBjDyRs(DtjsForm myForm) {

		DAO dao = DAO.getInstance();

		// �༶����
		String bjdm = myForm.getBjdm();
		// ѧ��
		String xn = myForm.getXn();

		StringBuffer sql = new StringBuffer();
		
		// ��Ա����
		sql.append("select a.dyrs, b.ybdyrs,c.jjfzrs from ( ");
		sql.append("select count(a.xh) dyrs, ? bjdm ");
		sql.append("from view_dyxx a where exists (select 1 ");
		sql.append("from view_xsjbxx d where d.xh = a.xh ");
		sql.append("and d.bjdm = ?) and a.zzzt = 'yes' ");
		sql.append("and a.xn = ?) a ");
		// Ԥ����Ա����
		sql.append("left join (select count(b.xh) ybdyrs, ? bjdm ");
		sql.append("from view_ybdyxx b where exists (select 1 ");
		sql.append("from view_xsjbxx d where d.xh = b.xh ");
		sql.append("and d.bjdm = ?) and b.zzzt = 'yes' ");
		sql.append("and b.xn = ?) b on a.bjdm = b.bjdm ");
		// ������������
		sql.append("left join (select count(c.xh) jjfzrs, ? bjdm ");
		sql.append("from view_rdjjfzxx c where exists (select 1 ");
		sql.append("from view_xsjbxx d where d.xh = c.xh ");
		sql.append("and d.bjdm = ?) and c.zzzt = 'yes' ");
		sql.append("and c.xn = ?) c on a.bjdm = c.bjdm ");
		
		String[] inputValue = new String[] { bjdm, bjdm, xn, bjdm, bjdm, xn,bjdm, bjdm, xn };
		String[] outputValue = new String[] { "dyrs", "ybdyrs","jjfzrs" };
		HashMap<String, String> map = dao.getMap(sql.toString(), inputValue,
				outputValue);
		return map;
	}
	
	/**
	 * ��ð༶����
	 * 
	 * @author luojw
	 */
	public String getBjRs(DtjsForm myForm) {
		
		DAO dao = DAO.getInstance();
		
		// �༶����
		String bjdm = myForm.getBjdm();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(t.xh) bjrs from view_xsjbxx t where t.bjdm = ?");
		
		String[] inputValue = new String[] { bjdm };
		String outputValue = "bjrs";
		String bjrs = dao.getOneRs(sql.toString(), inputValue, outputValue);
		return bjrs;
	}
	
	/**
	 * ����û����������������
	 * 
	 * @param map
	 * @author luojw
	 */
	public String getUserTypeQuery(HashMap<String, String> map) {

		StringBuilder query = new StringBuilder();

		String userName = map.get("userName");// �û���
		boolean fdy = Boolean.parseBoolean(map.get("fdy"));// ����Ա
		boolean bzr = Boolean.parseBoolean(map.get("bzr"));// ������
		boolean zbfzr = Boolean.parseBoolean(map.get("zbfzr"));// ֧��������

		query.append(" and (");

		// �Ǹ���Ա
		if (fdy) {
			query.append(" exists (select 1 from fdybjb b ");
			query.append(" where a.bjdm = b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
		}
		// �ǰ�����
		if (bzr) {
			if (fdy) {
				query.append(" or ");
			}
			query.append(" exists (select 1 from bzrbbb b ");
			query.append(" where a.bjdm = b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
		}
		// ��֧��������
		if (zbfzr) {
			if (fdy || bzr) {
				query.append(" or ");
			}
			query.append(" exists (select 1 from zjlg_zbmc b,zjlg_zbfp c ");
			query.append(" where a.bjdm = c.bjdm and b.zbdm = c.zbdm ");
			query.append(" and b.zgh = '" + userName + "') ");
		}

		if (!fdy && !bzr && !zbfzr) {
			query.append(" 1 = 1");
		}

		query.append(" )");

		return query.toString();
	}
}
