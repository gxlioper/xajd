package xgxt.qgzx;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class QgzxTyDAO extends CommonQueryDAO {
	
	/**
	 * ����ڹ���ѧ����б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getQgzxListInfo(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", "lx", };

		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String query = myQuery.getQueryString();
		
		other_query = Base.isNull(other_query) ? "" : other_query;
		
		if(!Base.isNull(query)){
			query += " "+other_query;
		}else{
			query = other_query;
		}

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}
	
	/**
	 * ����ڹ���ѧ�����Ϣ
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getQgzxInfo(String tableName, String pk,
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
	public List<HashMap<String, String>> getQgzxList(String tableName,
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
		} else if ("bblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "zhbnoyj", "zhbyj", "zhblxy", "jxjjetj",
					"jxjhj", "rychhj", "jxjjehz" };
			mc = new String[] { "�ۺϲ�����(������Ӣ�������ȼ�)", "�ۺϲ�����(����Ӣ�������ȼ�)",
					Base.YXPZXY_KEY+"�ۺϲ���������", "��ѧ����ͳ��", "��ѧ�������", "�����ƺŻ�����", "��ѧ������ܱ�" };
		} else if ("wlk".equalsIgnoreCase(lx)) {
			dm = new String[] { "�Ŀ�", "���", "������" };
			mc = new String[] { "�Ŀ�", "���", "������" };
		} else if ("tjlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "", ">", ">=", "=", "<=", "<" };
			mc = new String[] { "-----��ѡ��-----", ">", ">=", "=", "<=", "<" };
		} else if ("ywlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "", "��", "��" };
			mc = new String[] { "-----��ѡ��-----", "��", "��" };
		} else if ("kglx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		} else if ("jxjorrych".equalsIgnoreCase(lx)) {
			dm = new String[] { "jxj", "rych" };
			mc = new String[] { "��ѧ��", "�����ƺ�" };
		}

		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk, String pkValue) {
		
		DAO dao = DAO.getInstance();

		return dao.getOneValue(tableName, dm, pk, pkValue);
	}

	/**
	 * ��ð༶����
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getBjRs(String bjdm) {
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "bjdm", "bjmc", "num" };
		String sql = "select bjdm,bjmc,count(xh) num from view_xsjbxx where bjdm = ? group by bjdm,bjmc";
		HashMap<String, String> map = dao.getMap(sql, new String[] { bjdm },
				colList);
		return map;
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
}
