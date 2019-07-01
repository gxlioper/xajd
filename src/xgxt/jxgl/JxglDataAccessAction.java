package xgxt.jxgl;

import java.util.Vector;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Check_Input_Value;

public class JxglDataAccessAction {
	////获取军训日程信息
	public static Vector<Object> GetCalendarInfo(String[] setpara, String[] getpara, String pk){
		DAO dao = DAO.getInstance();
		Vector<Object> vector = new Vector<Object>();
		String sql = "select " + pk + " 主键,rownum 行号,nj,nd,kssj,jssj,rczy from jxgl_jxrcxxb where nj like ? and nd like ?";
		vector.addAll(dao.rsToVator(sql, setpara, getpara));
		return vector;
	}
	///获取军训日程详细
	public static HashMap<String, String> getCalendarDetail(String doType, String pk, String pkValue){
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String [] getpara = {"nj","nd","jtrr","kssj","kssjH","kssjM","kssjS","jssj","jssjH","jssjM","jssjS","apnr","bz","rczy"};
		String sql = "select nj,nd,jtrr,substr(kssj,1,10) kssj,substr(kssj,12,2) kssjH,substr(kssj,14,2) kssjM" +
				",substr(kssj,16,2) kssjS,substr(jssj,1,10) jssj,substr(jssj,12,2) jssjH,substr(jssj,14,2) jssjM" +
				",substr(jssj,16,2) jssjS,apnr,bz,rczy from jxgl_jxrcxxb where " + pk + "=?";
		if("add".equalsIgnoreCase(doType)){
			for(int i =0; i<getpara.length; i++){
				map.put(getpara[i], "");
			}
		}else if("modi".equalsIgnoreCase(doType)){
			map = dao.getMap(sql, new String[]{pkValue}, getpara);
		}
		return map;
	}
	//获取表头
	public static List getCalendarInfoTr(String[] getpara, String tableName){
		DAO dao = DAO.getInstance();
		String [] columnCN = dao.getColumnNameCN(getpara, tableName);
		List topTr = dao.arrayToList(getpara, columnCN);
		return topTr;
	}
	
	//获取军训信息
	public static Vector<Object> getArmyStuList(String pk,String querry,String tableName, String[]colList){
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		Vector<Object> vector = new Vector<Object>();
		String sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + " a" + querry;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) && tableName.equalsIgnoreCase("view_ddjsxx")){
			//宁波理工
//			sql = "select " + pk + " 主键, rownum 行号,(select xm from view_fdyxx b where a.jsdm=b.zgh)xm," +
//				  "(select bmmc from view_fdyxx b where a.jsdm=b.zgh) xymc,(select xb from view_fdyxx b where a.jsdm=b.zgh)xb," +
//				  " a.* from " + tableName + " a " + querry;
			sql = "select rownum 行号,b.* from (select xm, jsdm 主键, jsdm, xb, xymc, lxdh , bzmc from"
				+ " view_nblg_lsxx " + querry + ") b";
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)
				&& tableName.equalsIgnoreCase("view_nblg_jxwh")) {

			sql = "select rownum 行号,b.* from (select xm, jgbh 主键, jgbh, xb, mzmc, lxdh, (select * from (select a.bzmc"
					+ " from nblg_jxbzdmb a where a.bzdm = '305430101' and a.bzdj = '1' union select b.bzmc || a.bzmc"
					+ " from nblg_jxbzdmb a, nblg_jxbzdmb b where a.bzdm = '305430101' and a.bzdj = '2' and a.sjdm = b.bzdm"
					+ " union select b.bzmc || a.bzmc from (select b.bzmc || a.bzmc as bzmc, b.sjdm from nblg_jxbzdmb a,"
					+ " nblg_jxbzdmb b where a.bzdm = '305430101' and a.bzdj = '3' and a.sjdm = b.bzdm) a, nblg_jxbzdmb b"
					+ " where a.sjdm = b.bzdm union select b.bzmc || a.bzmc as bzmc from (select b.bzmc || a.bzmc as bzmc,"
					+ " b.sjdm from (select b.bzmc || a.bzmc as bzmc, b.sjdm from nblg_jxbzdmb a, nblg_jxbzdmb b"
					+ " where a.bzdm = '305430101' and a.bzdj = '4' and a.sjdm = b.bzdm) a, nblg_jxbzdmb b"
					+ " where a.sjdm = b.bzdm) a,nblg_jxbzdmb b where a.sjdm = b.bzdm))bzmc  from "
					+ " view_nblg_jxwh " + querry + ") b";
		}
		vector.addAll(dao.rsToVator(sql, new String[]{}, colList));
		return vector;
	}
	
//	获取军训信息
	public static Vector<Object> getArmyStuList_Nblg(String pk, String querry,
			String tableName, String[] colList, JxglForm jxglform,
			HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		Vector<Object> vector = new Vector<Object>();
		String sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + " a" + querry;
		
		String jxnd = jxglform.getJxnd();
		String jgbh = jxglform.getJgbh();
		String xm = jxglform.getXm();
		String yjdm = jxglform.getYjdm();
		String ljdm = jxglform.getLjdm();
		String pjdm = jxglform.getPjdm();
		String bjdm = jxglform.getBjdm();
		String zdy = jxglform.getZdy();
		String dm = "";
		if ((bjdm != null && !"".equals(bjdm))
				|| (pjdm != null && !"".equals(pjdm))
				|| (ljdm != null && !"".equals(ljdm))
				|| (yjdm != null && !"".equals(yjdm))) {
			if (!"".equalsIgnoreCase(bjdm) && bjdm != null) {
				dm = bjdm;
			} else if (!"".equalsIgnoreCase(pjdm) && pjdm != null) {
				dm = pjdm;
			} else if (!"".equalsIgnoreCase(ljdm) && ljdm != null) {
				dm = ljdm;
			} else if (!"".equalsIgnoreCase(yjdm) && yjdm != null) {
				dm = yjdm;
			}
		}
				
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) && tableName.equalsIgnoreCase("view_jxxscxgb")){
			
			StringBuffer querrygb = new StringBuffer();
			String userDep = request.getSession().getAttribute("userDep")
					.toString();
			String userType = dao.getUserType(userDep);
			querrygb = getQuerryGb(jxglform, userDep, userType);
			
			sql = "select xh 主键, rownum 行号, a.xh, a.xm, a.xymc, a.bjmc, a.jxnd, b.fzldmc, a.zw"
					+ " from view_jxxscxgb a,(select a.bzdm,b.bzmc || a.bzmc fzldmc from "
					+ " nblg_jxbzdmb a, nblg_jxbzdmb b, jxxscxgbb c"
					+ " where a.sjdm = b.bzdm and a.bzdj = '2' and a.bzdm = c.fzlddm) b"
					+ querrygb + "and a.fzlddm = b.bzdm";
			if (jxnd != null && !"".equals(jxnd)) {
				sql += " and a.jxnd = '" + jxnd + "'";
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) && tableName.equalsIgnoreCase("view_nblg_lsxx")){
				sql = "select rownum 行号, b.* from ((select distinct(xm), jsdm 主键, jsdm, xb, xymc, lxdh ,"
					+ " c.bzmc  from view_nblg_lsxx a,(select bzmc, zdy from (select a.bzmc, a.zdy from nblg_jxbzdmb a"
					+ " where a.bzdj = '1' union select b.bzmc || a.bzmc, a.zdy from nblg_jxbzdmb a, nblg_jxbzdmb b"
					+ " where a.bzdj = '2' and a.sjdm = b.bzdm union select b.bzmc || a.bzmc, a.zdy from (select b.bzmc "
					+ " || a.bzmc as bzmc, a.zdy,b.bzdm, b.sjdm from nblg_jxbzdmb a, nblg_jxbzdmb b where a.bzdj = '3'"
					+ " and a.sjdm = b.bzdm) a, nblg_jxbzdmb b where a.sjdm = b.bzdm union select b.bzmc || a.bzmc "
					+ " as bzmc, a.zdy from (select b.bzmc || a.bzmc as bzmc, b.bzdm, b.sjdm,a.zdy from (select b.bzmc ||"
					+ " a.bzmc as bzmc, a.zdy,b.sjdm from nblg_jxbzdmb a, nblg_jxbzdmb b where a.bzdj = '4' and a.sjdm = "
					+ " b.bzdm) a, nblg_jxbzdmb b where a.sjdm = b.bzdm) a, nblg_jxbzdmb b where a.sjdm = b.bzdm )) c";
					
				if ("".equals(dm)) {
					sql += " where c.zdy = a.xm ) "
						+ "union (select distinct (a.xm), a.jsdm 主键, a.jsdm, a.xb, a.xymc, a.lxdh, a.bzmc from view_nblg_lsxx a"
						+ " where a.xm not in (select zdy from (select a.bzmc, a.zdy from nblg_jxbzdmb a where a.bzdj = '1' union"
						+ " select b.bzmc || a.bzmc, a.zdy from nblg_jxbzdmb a, nblg_jxbzdmb b where a.bzdj = '2' and a.sjdm = b.bzdm"
						+ " union select b.bzmc || a.bzmc, a.zdy from (select b.bzmc || a.bzmc as bzmc, a.zdy, b.bzdm, b.sjdm"
						+ " from nblg_jxbzdmb a, nblg_jxbzdmb b where a.bzdj = '3' and a.sjdm = b.bzdm) a, nblg_jxbzdmb b where a.sjdm = b.bzdm"
						+ " union select b.bzmc || a.bzmc as bzmc, a.zdy from (select b.bzmc || a.bzmc as bzmc, b.bzdm, b.sjdm, a.zdy"
						+ " from (select b.bzmc || a.bzmc as bzmc, a.zdy, b.sjdm from nblg_jxbzdmb a, nblg_jxbzdmb b where a.bzdj = '4' and a.sjdm = b.bzdm) a,"
						+ " nblg_jxbzdmb b where a.sjdm = b.bzdm) a, nblg_jxbzdmb b where a.sjdm = b.bzdm) c "
						+ querry + "and c.zdy = a.xm))) b";
			}else{
				sql+=querry+" and c.zdy = a.xm )) b";
			}
				sql += " where 1=1";
			if (jxnd != null && !"".equals(jxnd)) {
				sql += " and b.jxnd = '" + jxnd + "'";
			}
			if (zdy != null && !"".equals(zdy)) {
				sql += " and b.jsdm = '" + zdy + "'";
			}
			if (xm != null && !"".equals(xm)) {
				sql += " and b.xm like '%" + xm + "%'";
			}		
			//sql+=querry;

                    
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)
				&& tableName.equalsIgnoreCase("view_nblg_jxwh")) {
				sql = "select rownum 行号, b.* from ((select distinct (a.xm), a.jgbh 主键, a.jgbh, a.xb, a.mzmc, a.lxdh,"
					+ " a.jxnd, c.bzmc from view_nblg_jxwh a, (select bzmc, jgmc from (select a.bzmc, a.jgmc from nblg_jxbzdmb a"
					+ " where a.bzdj = '1' union select b.bzmc || a.bzmc, a.jgmc from nblg_jxbzdmb a, nblg_jxbzdmb b where a.bzdj = '2'"
					+ " and a.sjdm = b.bzdm union select b.bzmc || a.bzmc, a.jgmc from (select b.bzmc || a.bzmc as bzmc, a.jgmc,"
					+ " b.bzdm, b.sjdm from nblg_jxbzdmb a, nblg_jxbzdmb b where a.bzdj = '3' and a.sjdm = b.bzdm) a, nblg_jxbzdmb b"
					+ " where a.sjdm = b.bzdm union select b.bzmc || a.bzmc as bzmc, a.jgmc  from (select b.bzmc || a.bzmc as bzmc,"
					+ " b.bzdm, a.jgmc, b.sjdm from (select b.bzmc || a.bzmc as bzmc, a.jgmc,b.sjdm from nblg_jxbzdmb a, nblg_jxbzdmb b"
					+ " where a.bzdj = '4' and a.sjdm = b.bzdm) a, nblg_jxbzdmb b where a.sjdm = b.bzdm) a, nblg_jxbzdmb b where a.sjdm = b.bzdm)) c ";
					
				if ("".equals(dm)) {
					sql += " where c.jgmc = a.xm ) "
						+ " union (select  distinct (a.xm), a.jgbh 主键,a.jgbh, a.xb,a.mzmc,a.lxdh, a.jxnd,a.bzmc from view_nblg_jxwh a where a.jgbh not in("
						+ " select  distinct(a.jgbh) from view_nblg_jxwh a, (select bzmc, jgmc from (select a.bzmc, a.jgmc from nblg_jxbzdmb a"
						+ " where a.bzdj = '1' union select b.bzmc || a.bzmc, a.jgmc from nblg_jxbzdmb a, nblg_jxbzdmb b where a.bzdj = '2'"
						+ " and a.sjdm = b.bzdm union select b.bzmc || a.bzmc, a.jgmc from (select b.bzmc || a.bzmc as bzmc, a.jgmc,"
						+ " b.bzdm, b.sjdm from nblg_jxbzdmb a, nblg_jxbzdmb b where a.bzdj = '3' and a.sjdm = b.bzdm) a, nblg_jxbzdmb b"
						+ " where a.sjdm = b.bzdm union select b.bzmc || a.bzmc as bzmc, a.jgmc  from (select b.bzmc || a.bzmc as bzmc,"
						+ " b.bzdm, a.jgmc, b.sjdm from (select b.bzmc || a.bzmc as bzmc, a.jgmc,b.sjdm from nblg_jxbzdmb a, nblg_jxbzdmb b"
						+ " where a.bzdj = '4' and a.sjdm = b.bzdm) a, nblg_jxbzdmb b where a.sjdm = b.bzdm) a, nblg_jxbzdmb b where a.sjdm = b.bzdm)) c "
						+ querry + " and c.jgmc = a.xm ))) b";
			}else{
				sql+=querry+" and c.jgmc = a.xm )) b";
			}
				sql+=" where 1=1";
				if(jxnd !=null&&!"".equals(jxnd)){
					sql+= " and b.jxnd = '"+jxnd+"'";
				}
				if(jgbh !=null&&!"".equals(jgbh)){
					sql+= " and b.jgbh = '"+jgbh+"'";
				}
				if(xm !=null&&!"".equals(xm)){
					sql+= " and b.xm like '%"+xm+"%'";
				}			
		}
		vector.addAll(dao.rsToVator(sql, new String[]{}, colList));
		//TODO
		return vector;
	}
	
	private static StringBuffer getQuerryGb(JxglForm jxglform, String userDep,
			String userType) {
		String xydm = jxglform.getXydm();
		String zydm = jxglform.getZydm();
		String bjdm = jxglform.getBjdm();
		String nj = jxglform.getNj();
		String nd = jxglform.getNd();
		String xh = jxglform.getXh();
		String xm = jxglform.getXm();
		String ldbh = jxglform.getLdbh();
		String sfzld = jxglform.getSfzld();
		String ldmc = jxglform.getLdmc();
		String yjdm = jxglform.getYjdm();
		String ljdm = jxglform.getLjdm();
		String pjdm = jxglform.getPjdm();
		String jgbh = jxglform.getJgbh();
		
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1");
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		}
		if ("".equalsIgnoreCase(xydm) || xydm == null) {
			querry.append(" and 1=1");
		} else {
			querry.append(" and xydm='");
			querry.append(xydm);
			querry.append("'");
		}
		if ("".equalsIgnoreCase(zydm) || zydm == null) {
			querry.append(" and 1=1");
		} else {
			querry.append(" and zydm='");
			querry.append(zydm);
			querry.append("'");
		}
		if ("".equalsIgnoreCase(ldbh) || ldbh == null) {
			querry.append(" and 1=1");
		} else {
			querry.append(" and ldbh='");
			querry.append(ldbh);
			querry.append("'");
		}
		if ("".equalsIgnoreCase(bjdm) || bjdm == null) {
			querry.append(" and 1=1");
		} else {
			querry.append(" and bjdm='");
			querry.append(bjdm);
			querry.append("'");
		}
		if ("".equalsIgnoreCase(nj) || nj == null) {
			querry.append(" and 1=1");
		} else {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("'");
		}
		if ("".equalsIgnoreCase(nd) || nd == null) {
			querry.append(" and 1=1");
		} else {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("'");
		}
		if ("".equalsIgnoreCase(xh) || xh == null) {
			querry.append(" and 1=1");
		} else {
			if (Check_Input_Value.check2(xh)) {
				querry.append(" and xh='");
				querry.append(xh);
				querry.append("'");
			}
		}
		if ("".equalsIgnoreCase(xm) || xm == null) {
			querry.append(" and 1=1");
		} else {
			if (Check_Input_Value.check2(xm)) {
				querry.append(" and xm='");
				querry.append(xm);
				querry.append("'");
			}
		}
		if ("".equalsIgnoreCase(sfzld) || sfzld == null) {
			querry.append(" and 1=1");
		} else {
			if (Check_Input_Value.check2(sfzld)) {
				querry.append(" and sfzld='");
				querry.append(sfzld);
				querry.append("'");
			}
		}
		if ("".equalsIgnoreCase(ldmc) || sfzld == null) {
			querry.append(" and 1=1");
		} else {
			if (Check_Input_Value.check2(ldmc)) {
				querry.append(" and ldmc='");
				querry.append(ldmc);
				querry.append("'");
			}
		}
//		if (!"".equalsIgnoreCase(yjdm) && yjdm != null) {
//			querry.append(" and yjdm='");
//			querry.append(yjdm);
//			querry.append("'");
//		}
//		if (!"".equalsIgnoreCase(ljdm) && ljdm != null) {
//			querry.append(" and ljdm='");
//			querry.append(ljdm);
//			querry.append("'");
//		}
		if (!"".equalsIgnoreCase(pjdm) && pjdm != null) {
			querry.append(" and pjdm='");
			querry.append(pjdm);
			querry.append("'");
		}
		if (!"".equalsIgnoreCase(jgbh) && jgbh != null) {
			querry.append(" and jgbh='");
			querry.append(jgbh);
			querry.append("'");
		}
		
		String dm = "";
		
		if ((bjdm != null && !"".equals(bjdm))
				|| (pjdm != null && !"".equals(pjdm))
				|| (ljdm != null && !"".equals(ljdm))
				|| (yjdm != null && !"".equals(yjdm))) {
			if (!"".equalsIgnoreCase(bjdm) && bjdm != null) {
				querry.append(" and sfzld='");
				querry.append(bjdm);
				querry.append("'");
			} else {
				if (!"".equalsIgnoreCase(pjdm) && pjdm != null) {
					dm = pjdm;
				} else if (!"".equalsIgnoreCase(ljdm) && ljdm != null) {
					dm = ljdm;
				} else if (!"".equalsIgnoreCase(yjdm) && yjdm != null) {
					dm = yjdm;
				}
				String sql = "select * from ( select '"
						+ dm
						+ "' from dual union (select "
						+ "distinct (b.bzdm) from view_nblg_jxwh t, nblg_jxbzdmb b where t.sfzld = b.bzdm and b.sjdm = '"
						+ dm
						+ "') union (select bzdm from nblg_jxbzdmb"
						+ " where sjdm in (select distinct (b.bzdm) from view_nblg_jxwh t, nblg_jxbzdmb b where t.sfzld = b.bzdm"
						+ " and b.sjdm = '"
						+ dm
						+ "')) union (select bzdm from nblg_jxbzdmb where sjdm in (select bzdm"
						+ " from nblg_jxbzdmb where sjdm in (select distinct (b.bzdm) from view_nblg_jxwh t, nblg_jxbzdmb b"
						+ " where t.sfzld = b.bzdm and b.sjdm = '" + dm
						+ "'))))";
				querry.append(" and a.fzlddm in (");
				querry.append(sql);
				querry.append(")");
			}
		}
		return querry;
	}
	
	// 宁波理工获取军训信息
	public static Vector<Object> getArmyStuListOfNblg(String pk,String querry,String tableName, String[]colList){
		DAO dao = DAO.getInstance();
		Vector<Object> vector = new Vector<Object>();
		//是否取共享
		String sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + " a" + querry;//直接取学工数据
		vector.addAll(dao.rsToVator(sql, new String[]{}, colList));
		return vector;
	}
	
	//	宁波理工军训获取老师信息
	/**
	 * @return 老师信息
	 * @author luo
	 */
	public String[] getJsInfo(String zgh) {
		DAO dao = DAO.getInstance();
		String[] getPara = { "xm", "xb", "mz", "bmmc" };
		String sql = "select xm, xb, mz, bmmc from view_fdyxx where zgh = ?";
		String[] reVal = dao.getOneRs(sql, new String[] { zgh }, getPara);
		for(int i=0; i<reVal.length; i++){
			reVal[i] = reVal[i] == null ? "" : reVal[i];
		}
		return reVal;
	}
	
	public List<HashMap<String, String>> getNblgBjList(String nj,String xydm,
			String zydm) {
		DAO dao = DAO.getInstance();
		
		String[] inputSQL = new String[] { nj, nj };
		String sql = "select distinct a.bjdm dm,a.bjmc mc from view_njxyzybj a where a.nj=?";
		
		if (zydm != null && !"".equals(zydm)) {
			sql += " and zydm like ?";
			inputSQL = new String[] { nj, zydm, nj };
		}
		if (xydm != null && !"".equals(xydm)) {
			sql += " and xydm like ?";
			inputSQL = new String[] { nj, xydm, nj };
		}
		if ((xydm != null && !"".equals(xydm))
				&& (zydm != null && !"".equals(zydm))) {
			inputSQL = new String[] { nj, zydm, xydm, nj };
		}
		sql += " and not exists (select 1 from nblg_jxbzdmb b where a.bjdm = b.bzdm and b.nj = ? )";
		
		String[] outputSQL = new String[] { "dm", "mc" };

		List<HashMap<String, String>> bjList = dao.getList(sql, inputSQL,
				outputSQL);

		return bjList;
	}
	
	public List<HashMap<String, String>> getXbmzBjList(String nj,String xydm,
			String zydm, String xb) {
		DAO dao = DAO.getInstance();
		String[] inputSQL = new String[] { xb, nj, nj, xb };
		String sql = "select distinct a.bjdm dm,a.bjmc mc from view_njxyzybj a,(select t.bjdm, count(t.xh) num"
				+ " from view_xsjbxx t where t.xb = ? group by t.bjdm) c where a.nj=?";
		if (zydm != null && !"".equals(zydm)) {
			sql += " and zydm like ?";
			inputSQL = new String[] { xb, nj, zydm, nj, xb };
		}
		if (xydm != null && !"".equals(xydm)) {
			sql += " and xydm like ?";
			inputSQL = new String[] { xb, nj, xydm, nj, xb };
		}
		if ((xydm != null && !"".equals(xydm))
				&& (zydm != null && !"".equals(zydm))) {
			inputSQL = new String[] { xb, nj, zydm, xydm, nj, xb };
		}

		sql += " and not exists (select 1 from XBMZ_JXBZDMB b where a.bjdm = b.bzdm and b.nj = ? and b.xb=? ) and a.bjdm=c.bjdm";

		String[] outputSQL = new String[] { "dm", "mc" };

		List<HashMap<String, String>> bjList = dao.getList(sql, inputSQL,
				outputSQL);

		return bjList;
	}
	
	public List<HashMap<String, String>> getNblgBjTList(String sjdm,String nj) {
		DAO dao = DAO.getInstance();
		String[] inputSQL = new String[] { sjdm,  nj };
		String sql = "select bzdm dm,bzmc mc from nblg_jxbzdmb where sjdm=? and nj = ?";
		String[] outputSQL = new String[] { "dm", "mc" };
		List<HashMap<String, String>> bjList = dao.getList(sql, inputSQL,
				outputSQL);

		return bjList;
	}
	
	public boolean setNblgBjList(String bzdm) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "delete from nblg_jxbzdmb where bzdm=?";
		dao.runUpdate(sql, new String[] { bzdm });

		return true;
	}
	
	public boolean setXbmzBjList(String bzdm, String xb) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "delete from jxgl_jxmdb where ldbh in (select b.bzdm from XBMZ_JXBZDMB a"
				+ ", XBMZ_JXBZDMB b where a.sjdm = b.bzdm and a.bzdm = ? and a.xb=? )";
		dao.runUpdate(sql, new String[] { bzdm, xb });
		sql = "delete from XBMZ_JXBZDMB where bzdm=? and xb=?";
		dao.runUpdate(sql, new String[] { bzdm, xb });

		return true;
	}
//	// 宁波理工军训获取编制信息
//	/**
//	 * @return 编制信息
//	 * @author luo
//	 */
//	public static List<HashMap<String, String>> getBzInfo() {
//		DAO dao = DAO.getInstance();
//		// 获得带队编制信息
//		String sql = "select distinct bzdm,bzmc from nblg_jxbzdmb order by bzdm";
//		return dao.getList(sql, new String[] {}, new String[] { "bzdm", "bzmc" });
//	}
	
	// 宁波理工军训获取编制代码
	/**
	 * @return 获取编制代码
	 * @author luo
	 */
	public static String getJxbzdm() {
		DAO dao = DAO.getInstance();
		String sql = "select d.bzdm from (select rownum num, c.bzdm, c.tempbzdm"
				+ " from (select a.bzdm, (to_char(b.bzdm) - to_char(a.bzdm)) tempbzdm"
				+ " from (select rownum num, t.* from nblg_jxbzdmb t) a, (select rownum - 1 num, "
				+ " t.* from nblg_jxbzdmb t) b where a.num = b.num) c where c.tempbzdm > 1) d where d.num = 1";
		String bzdm1 = dao.getOneRs(sql, new String[] {}, "bzdm");
		int newDm = 0;
		if (bzdm1 != null && !"".equals(bzdm1)) {
			newDm = Integer.parseInt(bzdm1)+1;
		}
		sql = " select MAX(t.bzdm)+1 bzdm from nblg_jxbzdmb t";
		String bzdm2 = dao.getOneRs(sql, new String[] {}, "bzdm");
		if (bzdm2 == null || "".equals(bzdm2)) {
			newDm = 1;
		}
		if ((bzdm1 == null || "".equals(bzdm1))
				&& (bzdm2 != null && !"".equals(bzdm2))) {
			newDm = Integer.parseInt(bzdm2);
		}
		String str = String.valueOf(newDm);
		if (str.length() == 1) {
			str = "000" + str;
		} else if (str.length() == 2) {
			str = "00" + str;
		} else if (str.length() == 3) {
			str = "0" + str;
		}
		return str;
	}
	
	/**
	 * @return 获取编制代码
	 * @author luo
	 */
	public static String getXbmz_Jxbzdm() {
		DAO dao = DAO.getInstance();
		String sql = "select bzdm from (select rownum num, c.bzdm, c.tempbzdm"
				+ " from (select a.bzdm, (to_char(b.bzdm) - to_char(a.bzdm)) tempbzdm"
				+ " from (select rownum num, t.* from XBMZ_JXBZDMB t) a, (select rownum - 1 num, "
				+ " t.* from XBMZ_JXBZDMB t) b where a.num = b.num) c where c.tempbzdm > 1) d where num = 1";
		String bzdm1 = dao.getOneRs(sql, new String[] {}, "bzdm");
		int newDm = 0;
		if (bzdm1 != null && !"".equals(bzdm1)) {
			newDm = Integer.parseInt(bzdm1) + 1;
		}
		sql = " select MAX(t.bzdm)+1 bzdm from XBMZ_JXBZDMB t";
		String bzdm2 = dao.getOneRs(sql, new String[] {}, "bzdm");
		if (bzdm2 == null || "".equals(bzdm2)) {
			newDm = 1;
		}
		if ((bzdm1 == null || "".equals(bzdm1))
				&& (bzdm2 != null && !"".equals(bzdm2))) {
			newDm = Integer.parseInt(bzdm2);
		}
		String str = String.valueOf(newDm);
		if (str.length() == 1) {
			str = "000" + str;
		} else if (str.length() == 2) {
			str = "00" + str;
		} else if (str.length() == 3) {
			str = "0" + str;
		}
		return str;
	}
	
// 获取军训学生详细
	public static HashMap<String, String> getArmyStuDetail(String doType, String pk, String pkValue, String[] colList, String realTable){
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from " + realTable + " where " + pk + "=?";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) && realTable.equalsIgnoreCase("view_ddjsxx")){
			//宁波理工学院
			sql = "select jsdm,(select xm from view_fdyxx b where a.jsdm=b.zgh) xm,(select xb from view_fdyxx b where a.jsdm=b.zgh) xb," +
				  "(select bmdm from view_fdyxx b where a.jsdm=b.zgh) xydm,(select mz from view_fdyxx b where a.jsdm=b.zgh) mzdm,jxnd,sfzld," +
				  "zw,bz,lxdh,zzdh,sjhm,ddcs from " + realTable + " a where " + pk + "=?";
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)
				&& realTable.equalsIgnoreCase("view_nblg_jxwh")) {
			sql = "select distinct(xm) xm, jgbh, xb, mzdm, lxdh ,ddcs,jxnd,zw,bz from"
					+ " view_nblg_jxwh where jgbh = ?";		
		}
		if("add".equalsIgnoreCase(doType)){
			for(int i =0; i<colList.length; i++){
				map.put(colList[i], "");
			}
		}else if("modi".equalsIgnoreCase(doType)){
			map = dao.getMap(sql, new String[]{pkValue}, colList);
		}
		return map;
	}
	
	//获取军训学生详细
	public static HashMap<String, String> getArmyStuDetail(String doType, String pk, String pkValue, String[] colList, String xh, String realTable){
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from " + realTable + " where " + pk + "=?";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) && realTable.equalsIgnoreCase("view_ddjsxx")){
			//宁波理工学院
			sql = "select jsdm,(select xm from view_fdyxx b where a.jsdm=b.zgh) xm,(select xb from view_fdyxx b where a.jsdm=b.zgh) xb," +
				  "(select bmdm from view_fdyxx b where a.jsdm=b.zgh) xydm,(select mz from view_fdyxx b where a.jsdm=b.zgh) mzdm,jxnd,sfzld," +
				  "zw,bz,lxdh,zzdh,sjhm,ddcs from " + realTable + " a where " + pk + "=?";
		}
		if("add".equalsIgnoreCase(doType)){
			if (!"".equalsIgnoreCase(xh)){
				map = dao.getMap("select xh,xb,xm,(select dqnd from xtszb) nd,nj,xymc,zymc,bjmc,'' ldbh,'' sfbx,'' jxbx,'' jxbz,'' jxcf from view_xsjbxx where xh=?", new String[]{xh}, colList);
			}
		}else if("modi".equalsIgnoreCase(doType)){
			map = dao.getMap(sql, new String[]{pkValue}, colList);
		}
		return map;
	}
	
	// 获得学生详细
	public static HashMap<String, String> getStuDetail(String xh,
			String[] outputValue) {
		DAO dao = DAO.getInstance();
		String sql = "select xh, xb, xm, nj, xymc, zymc,bjmc from view_xsjbxx where xh=?";
		return dao.getMap(sql, new String[] { xh }, outputValue);
	}
}
