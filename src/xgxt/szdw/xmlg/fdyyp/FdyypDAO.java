package xgxt.szdw.xmlg.fdyyp;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.szdw.xmlg.XmlgSzdwDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class FdyypDAO extends XmlgSzdwDAO {

	DAO dao = DAO.getInstance();

	/**
	 * @describe 获得辅导员月评活动字段列表
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @author luo
	 */
	public ArrayList<String[]> getCsszList(String tableName, FdyypModel model,
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
	 * @describe 保存辅导员月评活动字段设置
	 * @author luo
	 */
	public boolean saveCssz(FdyypModel myModel, HttpServletRequest request)
			throws Exception {
		String tableName = "xmlg_fdyyp_hdbzd";
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
	 * @describe 获得辅导员月评活动字段
	 * @author luo
	 */
	public HashMap<String, String> getCssz(String tableName, String[] colList,
			String pk, String pkValue) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * @describe 删除辅导员月评活动字段
	 * @author luo
	 */
	public boolean delCssz(String pk, HttpServletRequest request)
			throws Exception {
		String tableName = "xmlg_fdyyp_hdbzd";
		String primaryKey = "xn||xq||zd";

		boolean flg = StandardOperation.delete(tableName, primaryKey, pk,
				request);

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
	 * @describe 保存辅导员月评申报内容
	 * @author luo
	 */
	public boolean saveFdyypsb(FdyypModel myModel, String[] zdyZd, String[] zdyZdz,
			HttpServletRequest request) throws Exception {

		String sql = "";

		String[] delsql = null;
		String[] inssql = null;

//		String[] zdyZd = myModel.getArrZd();
//		String[] zdyZdz= myModel.getArrZdz();
		
		String sbr = myModel.getSbr();
		String xn = myModel.getXn();
		String xq = myModel.getXq();
		String zgh = myModel.getZgh();
		String yf = myModel.getYf();
		String nd = myModel.getNd();
		String cpdf = myModel.getCpdf();
		String xyyj = DealString.toGBK(myModel.getXyyj());

		boolean flg = false;

		if (zdyZd.length > 0) {

			delsql = new String[zdyZd.length];
			inssql = new String[zdyZd.length];

			for (int i = 0; i < zdyZd.length; i++) {
				sql = "delete xmlg_szdw_fdyypsb where xn||xq||zgh||yf||zd = '"
						+ xn + xq + zgh + yf + zdyZd[i] + "'";
				delsql[i] = sql;

				sql = "insert into xmlg_szdw_fdyypsb(xn,xq,sbr,zgh,yf,zd,zdz) values('"
						+ xn
						+ "','"
						+ xq
						+ "','"
						+ sbr
						+ "','"
						+ zgh
						+ "','"
						+ yf + "','" + zdyZd[i] + "','" + zdyZdz[i] + "')";
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
			String primaryKey = "xn||xq||zgh||yf";
			String pk = xn + xq + zgh + yf;
			flg = StandardOperation.delete("xmlg_szdw_fdyypsh", primaryKey, pk,
					request);
			flg = StandardOperation.insert("xmlg_szdw_fdyypsh", new String[] {
					"xn", "xq", "nd", "zgh", "sbr", "yf", "cpdf", "xyyj" },
					new String[] { xn, xq, nd, zgh, sbr, yf, cpdf, xyyj },
					request);
		}
		return flg;
	}
	
	/**
	 * @describe 获得辅导员月评申报列表
	 * @author luo
	 */
	public ArrayList<String[]> getFdyypList(String tableName, FdyypModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "xn", "xq", "bmdm", "yf","sbsj"};
		String[] queryLikeList = new String[] { "zgh", "xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		return CommonQueryDAO.commonQuery(tableName, myQuery.getQueryString(),
				myQuery.getInputList(), colList, "", model);
	}
	
	/**
	 * @describe 删除辅导员月评信息
	 * @author luo
	 */
	public boolean delFdyyp(String[] key) throws Exception {

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
				String yf = pkValue[2];
				String zgh = pkValue[3];
				pk = xn+xq+yf+zgh;
			}

			sql = "delete from xmlg_szdw_fdyypsh where xn||xq||yf||zgh ='" + pk + "'";
			delsql[n] = sql;
			n++;
			sql = "delete from xmlg_szdw_fdyypsb where xn||xq||yf||zgh ='" + pk + "'";
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
	
	/**
	 * @describe 获得辅导员列表
	 * @author luo
	 */
	public ArrayList<String[]> getFdyList(String tableName, FdyypModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "bmdm", "xb" };
		String[] queryLikeList = new String[] { "zgh","xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		return CommonQueryDAO.commonQueryNotFy(tableName, myQuery.getQueryString(),
				myQuery.getInputList(), colList, "");
	}

	
	/**
	 * @describe 获得申报人信息
	 * @author luo
	 */
	public HashMap<String, String> getSbrxx(String zgh) {
		return CommonQueryDAO.commonQueryOne("view_yhxx", new String[] { "yhm",
				"xm" }, "yhm", zgh);
	}
	
	/**
	 * @describe 获得辅导员月评信息
	 * @author luo
	 */
	public HashMap<String, String> getFdyypXx(String pk) {
		return CommonQueryDAO.commonQueryOne("xmlg_szdw_fdyypsh", new String[] {
				"sbr", "cpdf", "xyyj", "sbsj","nd" }, "xn||xq||yf||zgh", pk);
	}

	/**
	 * @describe 获得辅导员月评汇总列表
	 * @author luo
	 */
	public List<HashMap<String, String>> getFdyypHzList(String nd,int kssj,int jssj) {
		int num = jssj-kssj;
		String[] month = {"","jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
		String[] colList = new String[num+5];
		colList[0] = "xm";
		colList[1] = "avgfs";
		colList[2] = "sumfs";
		colList[3] = "pm";
		
		StringBuffer sql = new StringBuffer("select zgh,xm ");
		for (int i = 0; i <= num; i++) {
			sql.append("," + month[kssj + i]);
			colList[4 + i] = month[kssj + i];
		}
		sql.append(",avgfs,sumfs,(rank() over(order by to_number(sumfs) desc nulls last)) pm from ");
		sql.append("(select zgh,xm");
		for(int i=0;i<=num;i++){
			sql.append("," + month[kssj + i]);
		}
		sql.append(",to_number(trim(to_char((");
		for (int i = 0; i <= num; i++) {
			if (i == 0) {
				sql.append(month[kssj + i]);
			} else {
				sql.append("+" + month[kssj + i]);
			}

		}
		sql.append(")/"+(num+1)+",99.99))) avgfs,(");
		for (int i = 0; i <= num; i++) {
			if (i == 0) {
				sql.append(month[kssj + i]);
			} else {
				sql.append("+" + month[kssj + i]);
			}
		}
		sql.append(") sumfs from (select zgh,xm");
		for (int i = 0; i <= num; i++) {
			sql.append(",sum(" + month[kssj + i] + ") " + month[kssj + i]);
		}
		sql.append(" from (select zgh,xm,");
		for (int i = 0; i <= num; i++) {
			String yf = "";
			if ((kssj + i) < 10) {
				yf = "0" + String.valueOf((kssj + i));
			} else {
				yf = String.valueOf((kssj + i));
			}
			if(i==0){
				sql.append("case when yf='" + yf + "' then cpdf else '0' end " + month[kssj + i]);
			}else{
				sql.append(",case when yf='" + yf + "' then cpdf else '0' end " + month[kssj + i]);
			}
		}
		sql.append(" from view_xmlg_fdyypsh where nd = '"+nd+"') group by zgh,xm)) ");
		//System.out.println(sql);
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[]{}, colList);
		return list;
	}

	/**
	 * @describe 获得辅导员过失列表
	 * @author luo
	 */
	public ArrayList<String[]> getFdyGsList(String tableName, FdyypModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "xn", "xq", "bmdm","sbsj"};
		String[] queryLikeList = new String[] { "zgh", "xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		return CommonQueryDAO.commonQuery(tableName, myQuery.getQueryString(),
				myQuery.getInputList(), colList, "", model);
	}
	
	/**
	 * @describe 保存辅导员重大过失
	 * @author luo
	 */
	public boolean saveFdyGs(FdyypModel myModel,String userName, HttpServletRequest request)
			throws Exception {

		String xn = myModel.getXn();
		String xq = myModel.getXq();
		String zgh = myModel.getZgh();
		String gsnr = myModel.getGsnr();
		String gssj = myModel.getGssj();
		String bz = myModel.getBz();

		boolean flg = StandardOperation.delete("xmlg_szdw_fdygsjl", "xn||xq||gssj", xn+xq+gssj,
					request);
		if (flg) {
			flg = StandardOperation.insert("xmlg_szdw_fdygsjl", new String[] {
					"xn", "xq", "zgh", "gsnr", "gssj", "bz", "lrr" },
					new String[] { xn, xq, zgh, gsnr, gssj, bz, userName },
					request);
		}

		return flg;
	}
	
	/**
	 * @describe 获得辅导员过失信息
	 * @author luo
	 */
	public HashMap<String, String> getFdyGsInfo(String pk) {
		String[] colList = new String[] { "xm", "xb", "xn", "xq", "zgh",
				"bmmc", "zwmc", "gsnr", "gssj", "bz", "lrr", "lrsj" };
		return commonQueryOne("view_xmlg_fdygsjl", colList, "pk", pk);
	}
}
