package xgxt.wjdc;

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

public class WjdcTyDAO extends CommonQueryDAO {

	/**
	 * ����ʾ��������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getWjdcListInfo(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", };

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
	 * ����ʾ���������Ϣ
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getWjdcInfo(String tableName, String pk,
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
	public List<HashMap<String, String>> getWjdcList(String tableName,
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
		} else if ("kglx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		} else if ("over".equalsIgnoreCase(lx)) {
			dm = new String[] { "δ��", "����" };
			mc = new String[] { "δ��", "����" };
		} else if ("zz".equalsIgnoreCase(lx)) {
			dm = new String[] { "�����", "δ���" };
			mc = new String[] { "�����", "δ���" };
		} else if ("xblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "Ů" };
			mc = new String[] { "��", "Ů" };
		} else if ("fplx".equalsIgnoreCase(lx)) {
			dm = new String[] { "δ����", "�ѷ���" };
			mc = new String[] { "δ����", "�ѷ���" };
		} else if ("ywlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "", "��", "��" };
			mc = new String[] { "-----��ѡ��-----", "��", "��" };
		} else if ("mklx".equalsIgnoreCase(lx)) {
			dm = new String[] { GlobalsVariable.WJDC_XLPC,
					GlobalsVariable.WJDC_SXZK ,GlobalsVariable.WJDC_SXSZK};
			mc = new String[] { "�����ղ�", "ѧ��˼��״������","ʵϰ��״������" };
		} 

		return dao.arrayToList(dm, mc);
	}

	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {

		DAO dao = DAO.getInstance();

		return dao.getOneValue(tableName, dm, pk, pkValue);
	}

	/**
	 * �����ύ
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql) throws Exception {

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
	 * ����ʾ���������б�
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getTjList() {

		DAO dao = DAO.getInstance();

		String sql = "select zdmc dm, zdsm mc from jxjtjzdb order by lsh";

		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}

}
