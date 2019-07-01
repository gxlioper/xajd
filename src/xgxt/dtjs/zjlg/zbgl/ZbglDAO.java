package xgxt.dtjs.zjlg.zbgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.zjlg.ZjlgDtjsDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Fdypd;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class ZbglDAO extends ZjlgDtjsDAO {

	/**
	 * @describe 获得党支部列表
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZbList(String xydm) {
		DAO dao = new DAO();

		if(Base.isNull(xydm)){
			xydm="%";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select '' zbdm,'----请选择----'zbmc from dual union ");
		sql.append("select a.zbdm, a.zbmc from zjlg_zbmc a where a.ssxx like ?) ");
		sql.append("order by zbdm nulls first ");
				
		List<HashMap<String, String>> zbList = dao.getList(sql.toString(),
				new String[] {xydm}, new String[] { "zbdm", "zbmc" });
		return zbList;
	}

	/**
	 * @describe 获得党支部列表(包括无所属)
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZbWssList(String xydm) {
		DAO dao = new DAO();

		if(Base.isNull(xydm)){
			xydm="%";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select '' zbdm,'----请选择----'zbmc from dual union ");
		sql.append("select a.zbdm, a.zbmc from zjlg_zbmc a where a.ssxx like ? union ");
		sql.append("select '无所属'zbdm, '无所属'zbmc from dual) ");
		sql.append("order by zbdm nulls first ");
				
		List<HashMap<String, String>> zbList = dao.getList(sql.toString(),
				new String[] {xydm}, new String[] { "zbdm", "zbmc" });
		return zbList;
	}
	
	/**
	 * @describe 获得班级列表
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBjList(String xydm,String zydm) {
		DAO dao = new DAO();

		xydm = Base.isNull(xydm) ? "%" : xydm;
		zydm = Base.isNull(zydm) ? "%" : zydm;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select bjdm,bjmc from view_njxyzybj where xydm like ? "); 
		sql.append("and zydm like ? order by bjdm ");
				
		List<HashMap<String, String>> zbList = dao.getList(sql.toString(),
				new String[] {xydm,zydm}, new String[] { "bjdm", "bjmc" });
		return zbList;
	}

	/**
	 * @describe 获得支部成员列表
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZbcyList(String zbdm) {
		DAO dao = new DAO();

		StringBuffer sql = new StringBuffer();
		sql.append("select a.bjdm,b.bjmc from zjlg_zbfp a, view_njxyzybj b "); 
		sql.append("where a.bjdm = b.bjdm and a.zbdm = ? ");
				
		List<HashMap<String, String>> zbList = dao.getList(sql.toString(),
				new String[] {zbdm}, new String[] { "bjdm", "bjmc" });
		return zbList;
	}

	/**
	 * @describe 获得未分配班级列表
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWfpbjList(String xydm,String zydm) {
		
		DAO dao = new DAO();

		xydm = Base.isNull(xydm) ? "%" : xydm;
		zydm = Base.isNull(zydm) ? "%" : zydm;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.bjdm, a.bjmc from view_njxyzybj a where xydm like ? and zydm like ? "); 
		sql.append("and not exists (select 1 from zjlg_zbfp b where a.bjdm = b.bjdm) order by bjdm");
				
		List<HashMap<String, String>> bjList = dao.getList(sql.toString(),
				new String[] {xydm,zydm}, new String[] { "bjdm", "bjmc" });
		return bjList;
	}
	
	/**
	 * @author luo
	 * @throws SQLException
	 * @describe 保存党支部
	 */
	public boolean saveDzb(ZbglModel model) throws SQLException {

		DAO dao = new DAO();

		String zbdm = model.getZbdm();
		String[] bjdm = model.getZbcy();
		String[] actsql = new String[bjdm.length + 1];

		boolean flg = false;
		String sql = "";

		if (bjdm != null && bjdm.length > 0) {

			sql = "delete from zjlg_zbfp where zbdm='" + zbdm + "'";
			actsql[0] = sql;

			for (int i = 1; i <= bjdm.length; i++) {

				sql = "insert into zjlg_zbfp(zbdm,bjdm) values('" + zbdm
						+ "','" + bjdm[i - 1] + "')";
				actsql[i] = sql;
			}
		}

		int[] res = dao.runBatch(actsql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe 清空党支部下属班级
	 */
	public boolean clearDzb(ZbglModel model) throws Exception {

		DAO dao = DAO.getInstance();

		String zbdm = model.getZbdm();
		String sql = "delete from zjlg_zbfp where zbdm=?";
		return dao.runUpdate(sql, new String[] { zbdm });
	}
	
	/**
	 * @author luo
	 * @describe 获得支部名称
	 */
	public String getZbmc(String xh) {
		DAO dao = new DAO();

		StringBuffer sql = new StringBuffer();
		sql.append("select b.zbmc from zjlg_zbfp a,zjlg_zbmc  b where a.zbdm = b.zbdm and ");
		sql.append("a.bjdm = (select bjdm from view_xsjbxx where xh = ?)");

		String zbmc = dao.getOneRs(sql.toString(), new String[] { xh }, "zbmc");
		zbmc = Base.isNull(zbmc) ? "无所属" : zbmc;
		return zbmc;
	}
	
	/**
	 * 获得支部信息
	 */
	public ArrayList<String[]> getZbxxList(String tableName, ZbglModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "xydm", "zbdm" };
		String[] queryLikeList = new String[] {"zgh","fdyxm"};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		return CommonQueryDAO.commonQuery(tableName, myQuery.getQueryString(),
				myQuery.getInputList(), colList, "", model);
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存党支部
	 */
	public boolean saveZbmc(ZbglModel model, String pk,
			HttpServletRequest request) throws Exception {

		DAO dao = new DAO();

		boolean flg = true;

		String zbmc = model.getZbmc();
		String xydm = model.getXydm();
		String zgh = model.getZgh();

		if (Base.isNull(pk)) {
			String sql = "select count(*) num from zjlg_zbmc where ssxx||zbmc=?";
			String num = dao.getOneRs(sql, new String[] { xydm + zbmc }, "num");

			if ("0".equalsIgnoreCase(num)) {
				flg = StandardOperation.insert("zjlg_zbmc", new String[] {
						"ssxx", "zbmc", "zgh" },
						new String[] { xydm, zbmc, zgh }, request);
			}
		} else {
			flg = StandardOperation.update("zjlg_zbmc", new String[] { "zbmc",
					"zgh" }, new String[] { zbmc, zgh }, "zbdm", pk, request);
		}

		return flg;
	}
	
	/**
	 * 获得党支部信息
	 * 
	 * @author luo
	 */
	public HashMap<String, String> getZbmcInfo(String pk) {
		String[] colList = new String[] { "xydm","xymc", "zbmc","zgh","fdyxm" };
		return commonQueryOne("view_zjlg_zbmc", colList, "zbdm", pk);
	}
	
	/**
	 * 删除正式党员
	 * 
	 * @author luo
	 */
	public boolean delZbmc(String[] key) throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "";
		String pk = "";
		String[] delsql = new String[key.length];
		for (int i = 0; i < key.length; i++) {
			pk = key[i];
			sql = "delete from zjlg_zbmc where zbdm ='" + pk + "'";
			delsql[i] = sql;
		}

		int[] res = dao.runBatch(delsql);

		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * 删除支部党员（包括正式党员和预备党员）
	 * 
	 * @author luo
	 */
	public boolean delZbdy(String[] key) throws Exception {

		boolean flag = false;
		
		String[] exec = new String[key.length];

		if (key != null && key.length > 0) {
			for (int i = 0; i < key.length; i++) {

				String pk = "";
				String pkValue = "";

				if (key[i].split("!!@@!!") != null
						&& key[i].split("!!@@!!").length == 2) {
					pk = key[i].split("!!@@!!")[0];
					pkValue = key[i].split("!!@@!!")[1];
				}

				String tableName = "党员".equalsIgnoreCase(pk) ? "dyxxb"
						: "ybdyxxb";
				String sql = "delete from " + tableName
						+ " where xn||xq||xh = '" + pkValue + "'";
				exec[i] = sql;
			}
			flag = saveArrDate(exec);
		}
		
		return flag;
	}


	/**
	 * @param query
	 *            查询条件
	 * @return 返回专业列表
	 */
	public List<HashMap<String, String>> setZyList(String xydm, String nj,
			String userName, String isFdy, String isBzr) {

		DAO dao = DAO.getInstance();

		String sql = "";

		String querry = " 1=1 ";
		String query = "";
		if (xydm != null && !xydm.equalsIgnoreCase("")) {
			query += " and xydm='" + xydm + "' ";
		}
		if (StringUtils.isNotNull(nj)) {
			query += " and nj='" + nj + "' ";
		}

		if ("true".equalsIgnoreCase(isBzr) && "true".equalsIgnoreCase(isFdy)) {
			// 是班主任加辅导员
			querry += " and ( exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='"
					+ userName
					+ "') or exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"
					+ userName + "'))";
		} else if ("true".equalsIgnoreCase(isBzr)) {
			// 是班主任
			querry += " and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='"
					+ userName + "')";
		} else if ("true".equalsIgnoreCase(isFdy)) {
			// 是辅导员
			querry += " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"
					+ userName + "')";
		}
		// }
		sql = "select * from (select distinct a.zydm zydm,a.zymc zymc from view_njxyzybj a"
				+ " where 1=1 " + query + " and" + querry + " order by zydm)";

		List<HashMap<String, String>> zyList = dao.getList(sql,
				new String[] {}, new String[] { "zydm", "zymc" });

		// 用户是否支部负责人
		String zbdm = dao.getOneValue("zjlg_zbmc", "zbdm", "zgh", userName);
		boolean isZbfzr = Base.isNull(zbdm) ? false : true;

		if (isZbfzr) {
			List<HashMap<String, String>> zbZyList = Fdypd.getZbZyList(
					userName, nj, xydm);

			zyList = Fdypd.getZbXgList(zbZyList, zyList, "zydm");
		}else{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("zydm", "");
			map.put("zymc", "");
			zyList.add(0,map);
		}

		return zyList;
	}
	
	/**
	 * @param query
	 *            查询条件
	 * @return 返回班级列表
	 */
	public List<HashMap<String, String>> setBjList(String query,
			String userName, String isFdy, String isBzr) {

		DAO dao = DAO.getInstance();

		String[] setpara = query.split("!!-!!");
		String querry = " 1=1 ";

		String sql = "select * from (select distinct"
				+ " bjdm bjdm,bjmc bjmc from view_njxyzybj a where xydm like ? and zydm"
				+ "  like ? and nj like ? order by bjdm)";
		if ("true".equalsIgnoreCase(isFdy) && "true".equalsIgnoreCase(isBzr)) {// 是辅导员
			querry += " and (exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"
					+ userName
					+ "') or "
					+ "exists(select 1 from  bzrbbb b where a.bjdm=b.bjdm and b.zgh='"
					+ userName + "'))";
			sql = "select * from (select distinct bjdm bjdm,bjmc bjmc from view_njxyzybj a where xydm like ? and zydm like ? and nj like ? and "
					+ querry + " order by bjdm)";
		} else if ("true".equalsIgnoreCase(isFdy)) {// 是辅导员
			querry += " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"
					+ userName + "')";
			sql = "select * from (select distinct bjdm bjdm,bjmc bjmc from view_njxyzybj a where xydm like ? and zydm like ? and nj like ? and "
					+ querry + " order by bjdm)";
		} else if ("true".equalsIgnoreCase(isBzr)) {// 是班主任
			querry += " and exists(select 1 from  bzrbbb b where a.bjdm=b.bjdm and b.zgh='"
					+ userName + "')";
			sql = "select * from (select distinct bjdm bjdm,bjmc bjmc from view_njxyzybj a where xydm like ? and zydm like ? and nj like ? and "
					+ querry + " order by bjdm)";
		}

		List<HashMap<String, String>> bjList = dao.getList(sql, setpara,
				new String[] { "bjdm", "bjmc" });
		
		// 用户是否支部负责人
		String zbdm = dao.getOneValue("zjlg_zbmc", "zbdm", "zgh", userName);
		boolean isZbfzr = Base.isNull(zbdm) ? false : true;

		if (isZbfzr) {
			List<HashMap<String, String>> zbBjList = Fdypd.getZbBjList(
					userName, setpara[2], setpara[0], setpara[1]);

			bjList = Fdypd.getZbXgList(zbBjList, bjList, "bjdm");
		}else{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("bjdm", "");
			map.put("bjmc", "");
			bjList.add(0,map);
		}
		
		return bjList;
	}
}
	