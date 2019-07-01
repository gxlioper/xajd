package xgxt.pjpy.jhzy.jcf;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.jhzy.JhzyPjpyDAO;

public class JcfDAO extends JhzyPjpyDAO {

	/**
	 * @author luo
	 * @describe ��ý��ͷ��б�
	 */
	public ArrayList<String[]> getJcList(String tableName, JcfModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		// ѧԺ����
		String xydm = DealString.toGBK(model.getXydm());
		// רҵ����
		String zydm = DealString.toGBK(model.getZydm());
		// �༶����
		String bjdm = DealString.toGBK(model.getBjdm());
		// �꼶
		String nj = DealString.toGBK(model.getNj());
		// ѧ��
		String xn = DealString.toGBK(model.getXn());
		// ѧ��
		String xq = DealString.toGBK(model.getXq());
		// ѧ��
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// ����
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		
		
		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='"+xq+ "'");
		query.append(" and xh like ?");
		query.append(" and xm like ?");
		String[] inPutList = new String[] {xh,xm};
		
		return commonQuery(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * ���潱�ͷ�
	 * 
	 * @throws SQLException
	 */
	public boolean saveJcf(JcfModel model, HttpServletRequest request)
			throws SQLException {
		boolean flg = false;
		DAO dao = DAO.getInstance();
		String[] sqlList = new String[model.getJfxm().length + 1];
		StringBuffer sql = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()).append(model.getXn()).append(model.getXq());
			StandardOperation.delete("jhzy_jcf", "xh||xn||xq", buff.toString(),
					request);
		} catch (Exception e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
		}
		for (int i = 0; i < model.getJfxm().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into jhzy_jcf (xh,xn,xq,jfxm,fz,jfly) values('");
			sql.append(model.getXh());
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getXq());
			sql.append("','");
			sql.append(DealString.toGBK(model.getJfxm()[i]));
			sql.append("','");
			sql.append(model.getFz()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getJfly()[i]).replace("'","��"));
			sql.append("')");
			sqlList[i] = sql.toString();
		}
		int[] res = dao.runBatch(sqlList);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * @author luo
	 * @describe ���ѧ���б�
	 */
	public List<HashMap<String, String>> getXqList() {
		return commonQueryforList("xqdzb", "", new String[] {}, new String[] {
				"xqmc", "xqdm" }, "");
	}
	
	/**
	 * @author luo
	 * @describe ���ѧ��������Ϣ
	 */
	public static HashMap<String, String> getStuInfo(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select xh, xm, xb,nj, xymc, zymc, bjmc,nj from view_xsjbxx where xh = ?";
		return dao.getMap(sql,new String[]{xh}, new String[] { "xh", "xm", "xb",
				"nj", "xymc", "zymc", "bjmc","nj" });
	}
	
	/**
	 * ���ѧ�����ͷ�ֵ
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getJcf(String pk) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select jfxm,fz,jfly from jhzy_jcf where xh||xn||xq=? order by jfxm";
		return dao.getList(sql, new String[] { pk }, new String[] { "jfxm",
				"fz", "jfly" });
	}
	
	/**
	 * ���ѧ��Υ�ʹ���
	 * 
	 * @throws SQLException
	 */
	public List<String> getWjcf(String xn,String xq,String xh
			) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select a.cflbmc || ':��'||b.cfkf||'��' cf from view_wjcf a, zjlg_wjkfb b"
				+ " where a.xn = b.xn and a.cflb = b.cfdm and a.xn = ? and a.xq=? and a.xh = ?";
		return dao.getList(sql, new String[]{xn,xq,xh}, "cf");
		
	}
	
	/**
	 * ɾ�����ͷ�
	 * @throws Exception 
	 */
	public boolean delJcf(String pk, HttpServletRequest request)
			throws Exception {
		return StandardOperation.delete("jhzy_jcf", "xh||xn||xq", pk, request);
	}

	/**
	 * ���ѧ������
	 * @throws Exception 
	 */
	public String getXqmc(String xqdm) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xqmc from xqdzb where xqdm=?", new String[]{xqdm}, "xqmc");
	}
	
	/**
	 * ���ѧ�ڴ���
	 * @throws Exception 
	 */
	public String getXqdm(String xqmc) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xqdm from xqdzb where xqmc=?", new String[]{xqmc}, "xqdm");
	}
	
	/**
	 * ����ۺ����ʷ�����
	 * 
	 * @throws Exception
	 */
	public String isZhfsx() {
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from jhzy_zhf_sx where xn=? and xq=?";
		return dao.getOneRs(sql, new String[] { Base.getJxjsqxn(), Base.getJxjsqxq() },
				"num");
	}
}
