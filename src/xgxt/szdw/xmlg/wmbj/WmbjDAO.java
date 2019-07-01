package xgxt.szdw.xmlg.wmbj;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.szdw.xmlg.XmlgSzdwDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class WmbjDAO extends XmlgSzdwDAO {

	DAO dao = DAO.getInstance();

	/**
	 * @describe 获得文明班级活动字段列表
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @author luo
	 */
	public ArrayList<String[]> getCsszList(String tableName, WmbjModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "xn", "xq" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		return commonQuery(tableName, myQuery.getQueryString(), myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * @describe 保存文明班级活动字段设置
	 * @author luo
	 */
	public boolean saveCssz(WmbjModel myModel, HttpServletRequest request)
			throws Exception {
		String tableName = "xmlg_wmbj_hdbzd";
		String primaryKey = "xn||xq||zd";
		String primaryValue = myModel.getXn() + myModel.getXq()
				+ DealString.toGBK(myModel.getZd());

		String[] colList = new String[] { "xn", "xq", "zd", "zdm", "zdlx" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean flg = true;
		flg = StandardOperation.delete(tableName, primaryKey, primaryValue,
				request);
		if (flg) {
			flg = StandardOperation.insert(tableName, colList, inputList,
					request);
		}
		return flg;
	}

	/**
	 * @describe 获得文明班级活动字段
	 * @author luo
	 */
	public HashMap<String, String> getCssz(String tableName, String[] colList,
			String pk, String pkValue) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * @describe 删除文明班级活动字段
	 * @author luo
	 */
	public boolean delCssz(String pk, HttpServletRequest request)
			throws Exception {
		String tableName = "xmlg_wmbj_hdbzd";
		String primaryKey = "xn||xq||zd";

		boolean flg = StandardOperation.delete(tableName, primaryKey, pk,
				request);

		return flg;
	}

	/**
	 * @describe 判断是否班干部
	 * @author luo
	 */
	public boolean isBgb(String xh) {
		boolean flg = false;
		String sql = "select count(*) num from view_bjgbxx where xh=? and bjgbmc='班长'";
		String count = dao.getOneRs(sql, new String[] { xh }, "num");
		if (!"0".equalsIgnoreCase(count)) {
			flg = true;
		}
		return flg;
	}
	
	/**
	 * @describe 获得申报学生基本信息
	 * @author luo
	 */
	public HashMap<String,String> getSbrXx(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.xh, a.xm, a.bjdm, a.bjmc, ");
		sql.append("(select count(b.xh) num from view_xsjbxx b where a.bjdm = b.bjdm) bjrs, ");
		sql.append("(select c.xm from view_bjgbxx c where c.bjgbmc='班长' and a.bjdm = c.bjdm) bzxm, ");
		sql.append("(select d.xm from view_bjgbxx d where d.bjgbmc='团支部书记' and a.bjdm = d.bjdm) tzbsj, ");
		sql.append("(select e.xm from view_fdybjdz e where a.bjdm = e.bjdm) bzr ");
		sql.append("from view_xsjbxx a where a.xh = ?");
		return dao.getMap(sql.toString(), new String[]{xh}, new String[]{"xh","xm","bjdm","bjmc","bjrs","bzxm","tzbsj","bzr"});
	}
	
	/**
	 * @describe 保存文明班级申报内容
	 * @author luo
	 */
	public boolean saveWmbjsb(WmbjModel myModel, String[] zdyZd, String[] zdyZdz,
			HttpServletRequest request) throws Exception {

		String sql = "";

		String[] delsql = null;
		String[] inssql = null;

//		String[] zdyZd = myModel.getArrZd();
//		String[] zdyZdz= myModel.getArrZdz();
		
		String xh = myModel.getXh();
		String xn = myModel.getXn();
		String xq = myModel.getXq();
		String sbbj = myModel.getBjdm();

		boolean flg = false;

		if (zdyZd.length > 0) {

			delsql = new String[zdyZd.length];
			inssql = new String[zdyZd.length];

			for (int i = 0; i < zdyZd.length; i++) {
				sql = "delete xmlg_szdw_wmbjsb where xn||xq||sbbj||zd = '" + xn
						+ xq + sbbj + zdyZd[i] + "'";
				delsql[i] = sql;

				sql = "insert into xmlg_szdw_wmbjsb(xn,xq,sbr,sbbj,zd,zdz) values('"
						+ xn
						+ "','"
						+ xq
						+ "','"
						+ xh
						+ "','"
						+ sbbj
						+ "','"
						+ zdyZd[i] + "','" + zdyZdz[i] + "')";
				inssql[i] = sql;
			}
			int[] res = dao.runBatch(delsql);

			for (int i = 0; i < res.length; i++) {
				flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flg)
					break;
			}

			if (flg) {
				res = dao.runBatch(inssql);
				for (int i = 0; i < res.length; i++) {
					flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
					if (!flg)
						break;
				}
			}
		}

		if (flg) {
			String primaryKey = "xn||xq||sbbj";
			String pk = xn + xq + sbbj;
			flg = StandardOperation.delete("xmlg_szdw_wmbjsh", primaryKey, pk,
					request);
			flg = StandardOperation.insert("xmlg_szdw_wmbjsh", new String[] {
					"xn", "xq", "sbbj", "sbr" }, new String[] { xn, xq, sbbj,
					xh }, request);
		}
		return flg;
	}
	
	/**
	 * @describe 获得文明班级申报列表
	 * @author luo
	 */
	public ArrayList<String[]> getWmbjList(String tableName, WmbjModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "xn", "xq", "xydm", "zydm", "bjdm",
				"sbsj" };
		String[] queryLikeList = new String[] { "sbr", "sbrxm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		return CommonQueryDAO.commonQuery(tableName, myQuery.getQueryString(),
				myQuery.getInputList(), colList, "", model);
	}
	
	/**
	 * @describe 删除文明班级信息
	 * @author luo
	 */
	public boolean delWmbj(String[] key) throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "";
		String pk = "";
		String[] delsql = new String[key.length * 2];
		int n = 0;
		
		for (int i = 0; i < key.length; i++) {
			
			String[] pkValue = key[i].split("!!@@!!");
			if (pkValue != null && pkValue.length > 3) {
				String xn = pkValue[0];
				String xq = pkValue[1];
				String sbbj = pkValue[2];
				//String xh = pkValue[3];
				pk = xn + xq + sbbj;
			}

			sql = "delete from xmlg_szdw_wmbjsh where xn||xq||sbbj ='" + pk + "'";
			delsql[n] = sql;
			n++;
			sql = "delete from xmlg_szdw_wmbjsb where xn||xq||sbbj ='" + pk + "'";
			delsql[n] = sql;
			n++;
		}

		int[] res = dao.runBatch(delsql);

		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
}
