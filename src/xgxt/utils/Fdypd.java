package xgxt.utils;

import java.sql.SQLException;
import java.util.*;

import xgxt.DAO.DAO;
//����Ա�ж���ص���
import xgxt.action.Base;

public class Fdypd {

	public static List<HashMap<String, String>> getFdybjList(String zgh) {
		// ���ظ���Ա����༶
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.bjdm, a.bjmc from fdybjb b,bks_bjdm a where a.bjdm=b.bjdm and b.zgh=? order by bjmc";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "bjdm",
				"bjmc" });
	}

	public static List<HashMap<String, String>> getFdyAllbjList() {
		// ���ظ���Ա����༶
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.bjdm, a.bjmc from fdybjb b,bks_bjdm a where a.bjdm=b.bjdm order by bjmc";
		return dao.getList(mySql, new String[] {}, new String[] { "bjdm",
				"bjmc" });
	}

	public static List<HashMap<String, String>> getFdyZyList(String zgh) {
		// ���ظ���Աרҵ�༶
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.zydm, a.zymc from fdybjb b,view_njxyzybj a where a.bjdm=b.bjdm and b.zgh=? order by zymc";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "zydm",
				"zymc" });
	}
	
	public static List<HashMap<String, String>> getFdyZyallList(String zgh) {
		// ���ظ���Աרҵ�༶
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.zydm, a.zymc from fdybjb b,view_njxyzybj_all a where a.bjdm=b.bjdm and b.zgh=? order by zymc";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "zydm",
				"zymc" });
	}

	public static List<HashMap<String, String>> getFdyXyList(String zgh) {
		// ���ظ���ԱѧԺ
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.xydm, a.xymc from fdybjb b,view_njxyzybj a where a.bjdm=b.bjdm and b.zgh=? order by xydm";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "xydm",
				"xymc" });
	}
	
	public static List<HashMap<String, String>> getFdyXyallList(String zgh) {
		// ���ظ���ԱѧԺ
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.xydm, a.xymc from fdybjb b,view_njxyzybj_all a where a.bjdm=b.bjdm and b.zgh=? order by xydm";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "xydm",
				"xymc" });
	}

	// �ж��Ƿ��Ǹ���Ա
	public static boolean isFdy(String zgh) {
		DAO dao = DAO.getInstance();
		String sql = "select count(1) cout from fdybjb where zgh=?";
		String result = dao.getOneRs(sql, new String[] { zgh }, "cout");
		if ("0".equalsIgnoreCase(result)) {
			return false;
		} else {
			return true;
		}
	}

	// �жϸ���Ա�Ƿ�ֻ�鿴����ѧ����trueΪ�鿴
	public static boolean fdybjck(String zgh) {
		String jxjdmStr = Base.initProperties.get("fdyCkSbj");
		boolean isFdy = isFdy(zgh);
		if (isFdy && jxjdmStr.equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	// �жϰ������Ƿ�ֻ�鿴����ѧ����trueΪ�鿴
	public static boolean bzrbjck(String zgh) {
		String check = Base.initProperties.get("bzrCkSbj");
		boolean isBzr = false;
		isBzr = isBzr(zgh, "");
		if (isBzr && check.equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	// ����ְ���ź�ѧԺ���룬����ѧԺ�������ֻ�ܲ鿴����ѧ�����򷵻�"",���򷵻�ԭֵ
	public static String fdybjck(String zgh, String xydm) {
		boolean xydmPd = fdybjck(zgh);
		if (xydmPd) {
			return "";
		} else {
			return xydm;
		}
	}

	// �Ƿ�˼������
	public static boolean isSzdw(String zgh) {
		DAO dao = DAO.getInstance();
		String sql = "select count(1) cout from fdyxxb where zgh=?";
		String result = dao.getOneRs(sql, new String[] { zgh }, "cout");
		if (result != null && !result.trim().equals("")
				&& Integer.parseInt(result) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �ж��Ƿ������
	 * 
	 * @param userName
	 * @return
	 * @throws SQLException
	 */
	public static Boolean isBzr(String userName, String tableName) {
		DAO dao = DAO.getInstance();
		String realTable = "";
		if (Base.isNull(tableName)) {
			realTable = "bzrbbb";
		} else {
			realTable = tableName;
		}
		String sql = "select count(bjdm)cout from " + realTable
				+ " where zgh=?";
		String cout = dao.getOneRs(sql, new String[] { userName }, "cout");
		if ("0".equalsIgnoreCase(cout)) {
			return false;
		} else {
			return true;
		}
	}

	public static List<HashMap<String, String>> getBzrbjList(String zgh) {
		// ���ذ����θ���༶
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.bjdm, a.bjmc from bzrbbb b,bks_bjdm a where a.bjdm=b.bjdm and b.zgh=? order by bjmc";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "bjdm",
				"bjmc" });
	}

	public static List<HashMap<String, String>> getBzrAllbjList() {
		// ���ذ����θ���༶
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.bjdm, a.bjmc from bzrbbb b,bks_bjdm a where a.bjdm=b.bjdm order by bjmc";
		return dao.getList(mySql, new String[] {}, new String[] { "bjdm",
				"bjmc" });
	}

	public static List<HashMap<String, String>> getBzrZyList(String zgh) {
		// ���ذ�����רҵ�༶
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.zydm, a.zymc from bzrbbb b,view_njxyzybj a where a.bjdm=b.bjdm and b.zgh=? order by zymc";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "zydm",
				"zymc" });
	}

	public static List<HashMap<String, String>> getBzrZyallList(String zgh) {
		// ���ذ�����רҵ�༶
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.zydm, a.zymc from bzrbbb b,view_njxyzybj_all a where a.bjdm=b.bjdm and b.zgh=? order by zymc";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "zydm",
				"zymc" });
	}
	
	public static List<HashMap<String, String>> getBzrXyList(String zgh) {
		// ���ذ�����ѧԺ
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.xydm, a.xymc from bzrbbb b,view_njxyzybj a where a.bjdm=b.bjdm and b.zgh=? order by xydm";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "xydm",
				"xymc" });
	}
	
	public static List<HashMap<String, String>> getBzrXyallList(String zgh) {
		// ���ذ�����ѧԺ
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.xydm, a.xymc from bzrbbb b,view_njxyzybj_all a where a.bjdm=b.bjdm and b.zgh=? order by xydm";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "xydm",
				"xymc" });
	}

	public static List<HashMap<String, String>> getFdyBzrbjList(String zgh) {
		// ���ذ����θ���Ա����༶
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.bjdm, a.bjmc from (select b.bjdm from bzrbbb b where b.zgh=? UNION ALL select "
				+ "a.bjdm from fdybjb a where a.zgh=?) b,bks_bjdm a where a.bjdm=b.bjdm order by bjmc";
		return dao.getList(mySql, new String[] { zgh, zgh }, new String[] {
				"bjdm", "bjmc" });
	}

	public static List<HashMap<String, String>> getFdyBzrZyList(String zgh) {
		// ���ذ����θ���Աרҵ�༶
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.zydm, a.zymc from (select b.bjdm from bzrbbb b where b.zgh=? UNION ALL select "
				+ "a.bjdm from fdybjb a where a.zgh=?) b,view_njxyzybj a where a.bjdm=b.bjdm order by zymc";
		return dao.getList(mySql, new String[] { zgh, zgh }, new String[] {
				"zydm", "zymc" });
	}
	
	public static List<HashMap<String, String>> getFdyBzrZyallList(String zgh) {
		// ���ذ����θ���Աרҵ�༶
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.zydm, a.zymc from (select b.bjdm from bzrbbb b where b.zgh=? UNION ALL select "
				+ "a.bjdm from fdybjb a where a.zgh=?) b,view_njxyzybj_all a where a.bjdm=b.bjdm order by zymc";
		return dao.getList(mySql, new String[] { zgh, zgh }, new String[] {
				"zydm", "zymc" });
	}

	public static List<HashMap<String, String>> getFdyBzrXyList(String zgh) {
		// ���ذ����θ���ԱѧԺ
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.xydm, a.xymc from (select b.bjdm from bzrbbb b where b.zgh=? UNION ALL select "
				+ "a.bjdm from fdybjb a where a.zgh=?) b,view_njxyzybj a where a.bjdm=b.bjdm order by xydm";
		return dao.getList(mySql, new String[] { zgh, zgh }, new String[] {
				"xydm", "xymc" });
	}
	
	public static List<HashMap<String, String>> getFdyBzrXyallList(String zgh) {
		// ���ذ����θ���ԱѧԺ
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.xydm, a.xymc from (select b.bjdm from bzrbbb b where b.zgh=? UNION ALL select "
				+ "a.bjdm from fdybjb a where a.zgh=?) b,view_njxyzybj_all a where a.bjdm=b.bjdm order by xydm";
		return dao.getList(mySql, new String[] { zgh, zgh }, new String[] {
				"xydm", "xymc" });
	}
	
	/**
	 * ���֧��������ѧԺ�б�
	 * 
	 * @author luojw
	 */
	public static List<HashMap<String, String>> getZbXyList(String zgh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select distinct a.xydm, a.xymc  from view_njxyzybj a ");
		sql.append("where exists (select 1 from zjlg_zbfp b, view_zjlg_zbmc c ");
		sql.append("where a.bjdm = b.bjdm and b.zbdm = c.zbdm and c.zgh = ?) ");
		sql.append("order by a.xydm");

		return dao.getList(sql.toString(), new String[] { zgh }, new String[] {
				"xydm", "xymc" });
	}
	
	/**
	 * ���֧��������רҵ�б�
	 * 
	 * @author luojw
	 */
	public static List<HashMap<String, String>> getZbZyList(String zgh,
			String nj, String xydm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		sql.append("select '' zydm, '--��ѡ��--' zymc from dual union all ");
		sql.append("select distinct a.zydm, a.zymc  from view_njxyzybj a ");
		sql.append("where exists (select 1 from zjlg_zbfp b, view_zjlg_zbmc c ");
		sql.append("where a.bjdm = b.bjdm and b.zbdm = c.zbdm and c.zgh = ?) ");
		sql.append(" and a.nj like ? ");
		sql.append(" and a.xydm like ? ");
		sql.append("order by zydm nulls first");

		return dao.getList(sql.toString(), new String[] { zgh, nj, xydm },
				new String[] { "zydm", "zymc" });
	}
	
	/**
	 * ���֧�������˰༶�б�
	 * 
	 * @author luojw
	 */
	public static List<HashMap<String, String>> getZbBjList(String zgh,
			String nj, String xydm, String zydm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select '' bjdm, '--��ѡ��--' bjmc from dual union all ");
		sql.append("select distinct a.bjdm, a.bjmc  from view_njxyzybj a ");
		sql.append("where exists (select 1 from zjlg_zbfp b, view_zjlg_zbmc c ");
		sql.append("where a.bjdm = b.bjdm and b.zbdm = c.zbdm and c.zgh = ?) ");
		sql.append(" and a.nj like ? ");
		sql.append(" and a.xydm like ? ");
		sql.append(" and a.zydm like ? ");
		sql.append("order by bjdm nulls first");

		return dao.getList(sql.toString(), new String[] { zgh,nj,xydm,zydm }, new String[] {
				"bjdm", "bjmc" });
	}
	
	/**
	 * ���֧������б�
	 * 
	 * @author luojw
	 */
	public static List<HashMap<String, String>> getZbXgList(
			List<HashMap<String, String>> zbList,
			List<HashMap<String, String>> qtList, String zd) {

		if (qtList != null && qtList.size() > 0) {

			for (int i = 0; i < qtList.size(); i++) {

				HashMap<String, String> map = qtList.get(i);
				
				String xydm = map.get(zd);

				boolean flag = true;

				if (zbList != null && zbList.size() > 0) {

					for (int j = 0; j < zbList.size(); j++) {

						HashMap<String, String> zbMap = zbList.get(j);

						String xy = zbMap.get(zd);

						if (xydm.equalsIgnoreCase(xy)) {
							flag = false;
							break;
						}
					}
				}

				if (flag) {
					zbList.add(map);
				}
			}
		}

		return zbList;
	}
	
	/**
	 * @param zgh
	 * @return
	 */
	public static HashMap<String,String>checkSfjrXy(String zgh){
		
		DAO dao = DAO.getInstance();
		
		StringBuilder sql =new StringBuilder();
		
		sql.append(" select   sfjryx  from yhb a left join fdyxxb b on a.yhm=b.zgh where a.yhm=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}
}
