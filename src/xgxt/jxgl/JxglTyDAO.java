package xgxt.jxgl;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class JxglTyDAO extends CommonQueryDAO {

	/**
	 * ��þ�ѵ��������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getJxglList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", "xb" };
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
	 * ��þ�ѵ���������Ϣ
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getJxglInfo(String tableName, String pk,
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
	public List<HashMap<String, String>> getWhList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();

		return dao.getWhList(tableName, dm, mc, msg, pk, pkValue);
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
		} else if ("xblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "Ů" };
			mc = new String[] { "��", "Ů" };
		} else if ("shlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "δ���", "ͨ��", "��ͨ��" };
			mc = new String[] { "δ���", "ͨ��", "��ͨ��" };
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
	 * ���ݾ�ѵ���Ƽ�������
	 * @param pk
	 * @param pkValue
	 * @param objId
	 * @param jb
	 * @return
	 */
	public List<HashMap<String,String>>getJxjzList(String pk,String pkValue,String jb){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select ''"+jb+"dm ,''"+jb+"mc  from dual  union ");
		sql.append(" select distinct("+jb+"dm)"+jb+"dm,"+jb+"mc from xg_view_jxgl_jxbz where "+pk+"='"+pkValue+"' ");
		sql.append(" and xn=(select dqxn from xtszb where rownum=1) order by "+jb+"mc nulls first  ");
		System.out.println(sql);
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{jb+"dm",jb+"mc"});
	}
	/**
	 * ����ʦ��������������
	 * @return
	 * @throws Exception 
	 */
	public boolean scmd() throws Exception{
		boolean flag = false;
		DAO dao=DAO.getInstance();
		
		String sql = "delete from jxgl_jxmdb where xn = ? and nd= ?";
		String[] input = new String[]{Base.currXn,Base.currNd};
		flag = dao.runUpdate(sql, input);
		if(flag){
			sql = "insert into jxgl_jxmdb(xh,nd,ldbh,isxs,xn)select b.xh,?,a.sjdm,'0',? from (select a.bzdm,a.sjdm from jxbzdmb a where xn = ? and bzdj = '4') a,view_xsjbxx b where a.bzdm=b.bjdm and not exists(select 1 from jxgl_jxmdb where xh = b.xh)";
			input = new String[]{Base.currNd,Base.currXn,Base.currXn};
			flag = dao.runUpdate(sql, input);
		}
		return flag;
	}
}
