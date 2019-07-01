package xgxt.pjpy.zjjj.dao;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.pjpy.zjjj.model.DycjModel;
import xgxt.utils.String.StringUtils;

import java.util.*;

public class ZjjjZhszcpDAO {

	DAO dao = DAO.getInstance();
	ArrayList<String> values = null;
	
	public static ZjjjZhszcpDAO myDao = new ZjjjZhszcpDAO();
	
	//DAO ����ģʽ
	public static ZjjjZhszcpDAO getInstance() {
		return myDao;
	}
	/**
	 * �����ȼ��б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> dycjList() throws Exception {
		String[] cnList = dao.getArray("select dymc cn from dycjdmb", new String[]{}, "cn");
		return dao.arrayToList(cnList, cnList);
	}
	
	/**
	 * ����Ա��ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> titleByfdy() throws Exception {
		String[] enList = new String[] { "pk", "dis", "xh", "xm", "bjmc", "xn",
				"xq", "dycj", "bjpm", "fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "pk", "dis", "ѧ��", "����", "�༶", "ѧ��",
				"ѧ��", "�����ɼ�", "����", "����Ա���", "ϵ�����", "ѧУ���" };
		return dao.arrayToList(enList, cnList);
	}
	
	public StringBuffer getWhereSql(DycjModel model) throws Exception {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm = ?");
			values.add(DealString.toGBK(model.getXm()));
		}
		if (!StringUtils.isNull(model.getDycj())) {
			whereSql.append(" and dycj = ?");
			values.add(DealString.toGBK(model.getDycj()));
		}
		return whereSql;
	}
	
	/**
	 * ����Ա��ѯ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> resultByfdy(DycjModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn||xq pk,";
		String[] opList = new String[] { "pk", "dis", "xh", "xm", "bjmc", "xn",
				"xq", "dycj", "bjpm", "fdysh", "xysh", "xxsh" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
}
