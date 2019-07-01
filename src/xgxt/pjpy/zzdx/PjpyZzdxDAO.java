
package xgxt.pjpy.zzdx;

import java.util.*;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ݴ�ѧ��������DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-23</p>
 */
public class PjpyZzdxDAO {

	DAO dao = DAO.getInstance();//���ݲ���ͨ��DAO
	ArrayList<String> values = null;
	/**
	 * ֤���ӡλ��
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public String[] getTopLeftStr(String page) throws Exception {
		String[] topleftList = new String[]{};
		String sql = "select top,left from printpage where page=? and rownum=1";
		topleftList = dao.getOneRs(sql, new String[]{page}, new String[]{"top", "left"});
		return topleftList;
	}
	
	public List<HashMap<String, String>> title() throws Exception {
		String[] enList = new String[]{"pk", "rownum", "xh", "xm", "xn","xq", "zymc", "bjdm", "rychmc"};
		String[] cnList = new String[]{"pk", "�к�", "ѧ��", "����", "ѧ��","ѧ��", "רҵ", "�༶", "�����ƺ�"};
		return dao.arrayToList(enList, cnList);
	}
	
	public StringBuffer getWhereSql(PjpyZzdxModel xybxfModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		values = new ArrayList<String>();
		if (!StringUtils.isNull(xybxfModel.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(xybxfModel.getBjdm());
		}
		if (!StringUtils.isNull(xybxfModel.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(xybxfModel.getXh());
		}
		if (!StringUtils.isNull(xybxfModel.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + xybxfModel.toString() + "%");
		}
		if (!StringUtils.isNull(xybxfModel.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(xybxfModel.getXn());
		}
		if (!StringUtils.isNull(xybxfModel.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(xybxfModel.getXydm());
		}
		if (!StringUtils.isNull(xybxfModel.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(xybxfModel.getZydm());
		}
		if (!StringUtils.isNull(xybxfModel.getRychdm())) {
			whereSql.append(" and rychdm = ?");
			values.add(xybxfModel.getRychdm());
		}
		if (!StringUtils.isNull(xybxfModel.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(xybxfModel.getXq());
		}
		return whereSql;
	}
	
	public List<String[]> result(PjpyZzdxModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn||nd||rychdm pk, rownum,xh,xm,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,zymc,bjmc,rychmc from view_xsrychb a where 1=1";
		String[] opList = new String[]{"pk", "rownum", "xh", "xm","xn", "xq","zymc", "bjmc", "rychmc"};
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
}
