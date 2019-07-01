package xgxt.utils;

import java.sql.SQLException;
import java.util.*;

import xgxt.DAO.DAO;
//辅导员判断相关的类
import xgxt.action.Base;

public class Fdypd {

	public static List<HashMap<String, String>> getFdybjList(String zgh) {
		// 返回辅导员负责班级
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.bjdm, a.bjmc from fdybjb b,bks_bjdm a where a.bjdm=b.bjdm and b.zgh=? order by bjmc";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "bjdm",
				"bjmc" });
	}

	public static List<HashMap<String, String>> getFdyAllbjList() {
		// 返回辅导员负责班级
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.bjdm, a.bjmc from fdybjb b,bks_bjdm a where a.bjdm=b.bjdm order by bjmc";
		return dao.getList(mySql, new String[] {}, new String[] { "bjdm",
				"bjmc" });
	}

	public static List<HashMap<String, String>> getFdyZyList(String zgh) {
		// 返回辅导员专业班级
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.zydm, a.zymc from fdybjb b,view_njxyzybj a where a.bjdm=b.bjdm and b.zgh=? order by zymc";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "zydm",
				"zymc" });
	}
	
	public static List<HashMap<String, String>> getFdyZyallList(String zgh) {
		// 返回辅导员专业班级
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.zydm, a.zymc from fdybjb b,view_njxyzybj_all a where a.bjdm=b.bjdm and b.zgh=? order by zymc";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "zydm",
				"zymc" });
	}

	public static List<HashMap<String, String>> getFdyXyList(String zgh) {
		// 返回辅导员学院
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.xydm, a.xymc from fdybjb b,view_njxyzybj a where a.bjdm=b.bjdm and b.zgh=? order by xydm";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "xydm",
				"xymc" });
	}
	
	public static List<HashMap<String, String>> getFdyXyallList(String zgh) {
		// 返回辅导员学院
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.xydm, a.xymc from fdybjb b,view_njxyzybj_all a where a.bjdm=b.bjdm and b.zgh=? order by xydm";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "xydm",
				"xymc" });
	}

	// 判断是否是辅导员
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

	// 判断辅导员是否只查看本班学生，true为查看
	public static boolean fdybjck(String zgh) {
		String jxjdmStr = Base.initProperties.get("fdyCkSbj");
		boolean isFdy = isFdy(zgh);
		if (isFdy && jxjdmStr.equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	// 判断班主任是否只查看本班学生，true为查看
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

	// 传入职工号和学院代码，返回学院代码如果只能查看本班学生，则返回"",否则返回原值
	public static String fdybjck(String zgh, String xydm) {
		boolean xydmPd = fdybjck(zgh);
		if (xydmPd) {
			return "";
		} else {
			return xydm;
		}
	}

	// 是否思政队伍
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
	 * 判断是否班主任
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
		// 返回班主任负责班级
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.bjdm, a.bjmc from bzrbbb b,bks_bjdm a where a.bjdm=b.bjdm and b.zgh=? order by bjmc";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "bjdm",
				"bjmc" });
	}

	public static List<HashMap<String, String>> getBzrAllbjList() {
		// 返回班主任负责班级
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.bjdm, a.bjmc from bzrbbb b,bks_bjdm a where a.bjdm=b.bjdm order by bjmc";
		return dao.getList(mySql, new String[] {}, new String[] { "bjdm",
				"bjmc" });
	}

	public static List<HashMap<String, String>> getBzrZyList(String zgh) {
		// 返回班主任专业班级
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.zydm, a.zymc from bzrbbb b,view_njxyzybj a where a.bjdm=b.bjdm and b.zgh=? order by zymc";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "zydm",
				"zymc" });
	}

	public static List<HashMap<String, String>> getBzrZyallList(String zgh) {
		// 返回班主任专业班级
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.zydm, a.zymc from bzrbbb b,view_njxyzybj_all a where a.bjdm=b.bjdm and b.zgh=? order by zymc";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "zydm",
				"zymc" });
	}
	
	public static List<HashMap<String, String>> getBzrXyList(String zgh) {
		// 返回班主任学院
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.xydm, a.xymc from bzrbbb b,view_njxyzybj a where a.bjdm=b.bjdm and b.zgh=? order by xydm";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "xydm",
				"xymc" });
	}
	
	public static List<HashMap<String, String>> getBzrXyallList(String zgh) {
		// 返回班主任学院
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.xydm, a.xymc from bzrbbb b,view_njxyzybj_all a where a.bjdm=b.bjdm and b.zgh=? order by xydm";
		return dao.getList(mySql, new String[] { zgh }, new String[] { "xydm",
				"xymc" });
	}

	public static List<HashMap<String, String>> getFdyBzrbjList(String zgh) {
		// 返回班主任辅导员负责班级
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.bjdm, a.bjmc from (select b.bjdm from bzrbbb b where b.zgh=? UNION ALL select "
				+ "a.bjdm from fdybjb a where a.zgh=?) b,bks_bjdm a where a.bjdm=b.bjdm order by bjmc";
		return dao.getList(mySql, new String[] { zgh, zgh }, new String[] {
				"bjdm", "bjmc" });
	}

	public static List<HashMap<String, String>> getFdyBzrZyList(String zgh) {
		// 返回班主任辅导员专业班级
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.zydm, a.zymc from (select b.bjdm from bzrbbb b where b.zgh=? UNION ALL select "
				+ "a.bjdm from fdybjb a where a.zgh=?) b,view_njxyzybj a where a.bjdm=b.bjdm order by zymc";
		return dao.getList(mySql, new String[] { zgh, zgh }, new String[] {
				"zydm", "zymc" });
	}
	
	public static List<HashMap<String, String>> getFdyBzrZyallList(String zgh) {
		// 返回班主任辅导员专业班级
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.zydm, a.zymc from (select b.bjdm from bzrbbb b where b.zgh=? UNION ALL select "
				+ "a.bjdm from fdybjb a where a.zgh=?) b,view_njxyzybj_all a where a.bjdm=b.bjdm order by zymc";
		return dao.getList(mySql, new String[] { zgh, zgh }, new String[] {
				"zydm", "zymc" });
	}

	public static List<HashMap<String, String>> getFdyBzrXyList(String zgh) {
		// 返回班主任辅导员学院
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.xydm, a.xymc from (select b.bjdm from bzrbbb b where b.zgh=? UNION ALL select "
				+ "a.bjdm from fdybjb a where a.zgh=?) b,view_njxyzybj a where a.bjdm=b.bjdm order by xydm";
		return dao.getList(mySql, new String[] { zgh, zgh }, new String[] {
				"xydm", "xymc" });
	}
	
	public static List<HashMap<String, String>> getFdyBzrXyallList(String zgh) {
		// 返回班主任辅导员学院
		DAO dao = DAO.getInstance();
		String mySql = "select distinct a.xydm, a.xymc from (select b.bjdm from bzrbbb b where b.zgh=? UNION ALL select "
				+ "a.bjdm from fdybjb a where a.zgh=?) b,view_njxyzybj_all a where a.bjdm=b.bjdm order by xydm";
		return dao.getList(mySql, new String[] { zgh, zgh }, new String[] {
				"xydm", "xymc" });
	}
	
	/**
	 * 获得支部负责人学院列表
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
	 * 获得支部负责人专业列表
	 * 
	 * @author luojw
	 */
	public static List<HashMap<String, String>> getZbZyList(String zgh,
			String nj, String xydm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		sql.append("select '' zydm, '--请选择--' zymc from dual union all ");
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
	 * 获得支部负责人班级列表
	 * 
	 * @author luojw
	 */
	public static List<HashMap<String, String>> getZbBjList(String zgh,
			String nj, String xydm, String zydm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select '' bjdm, '--请选择--' bjmc from dual union all ");
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
	 * 获得支部相关列表
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
