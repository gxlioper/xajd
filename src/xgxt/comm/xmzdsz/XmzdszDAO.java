package xgxt.comm.xmzdsz;

import java.sql.SQLException;
import java.util.*;

import xgxt.DAO.DAO;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class XmzdszDAO extends DAO {
	
	
	
	/**
	 * 返回项目列表
	 * @param map
	 * @return
	 */
	public List<HashMap<String,String>> getXmList(HashMap<String,String> map){
		
		StringBuilder sql = new StringBuilder();
		
		if ("xszz_zzxmb".equals(map.get("xmdmb"))) {
			sql.append("select * from ").append(map.get("xmdmb")).append(" where mrxm='是'");
		} else {
			sql.append("select * from ").append(map.get("xmdmb"));
		}
		
		
		return getList(sql.toString(), new String[] {}, new String[] {map.get("xmdmzd"),map.get("xmmczd")});
	}
	
	
	
	/**
	 * 获取模块项目保存字段表
	 * @param mkmc
	 * @return
	 */
	public List<HashMap<String,String>> getMkxmbzzdList(String mkmc){
		
		String sql = "select * from xg_ty_mkxmbzzdb where mkmc=?";
		
		return getList(sql.toString(), new String[] {mkmc}, new String[] {"dyzd","zwmc"});
	}
	
	
	
	/**
	 * 获取模块项目保存字段
	 * @param mkmc
	 * @return
	 * @throws SQLException 
	 */
	public String[] getMkxmbzzd(String mkmc) throws SQLException{
	
		String sql = "select * from xg_ty_mkxmbzzdb where mkmc=?";
		
		return getArray(sql, new String[] {mkmc}, "dyzd");
	}
	
	
	
	/**
	 * 返回数据源
	 * @param tableName
	 * @param lybzd
	 * @return
	 * @throws SQLException
	 */
	public String[] getSjy(String xmdm,String tableName,String lybzd) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select distinct lower(")
		   .append(lybzd).append(") ")
		   .append(lybzd).append( " from ")
		   .append(tableName)
		   .append(" where xmdm=?");
		
		return getArray(sql.toString(), new String[] {xmdm}, lybzd);
	}
	
	
	
	/**
	 * 已经保存的项目字段列表
	 * @param tableName
	 * @param xmdm
	 * @param colList
	 * @return
	 */
	public List<HashMap<String,String>> getXmzdList(String xmnrzdb,String[] sjlyb,String xmdm,String[] colList,XmzdszForm model) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.lyb||a.zd pkValue,'checked' checked,lower(a.lyb) lyb,a.lybmc,")
		   .append("lower(a.zd) zd,a.zdmc,a.zdm,a.zdlx,a.lrxz,a.bxlr,a.zdsx from ")
		   .append(xmnrzdb)
		   .append(" a where xmdm=? and lyb in (");
		
		if (null != sjlyb && sjlyb.length>0) {
			
			for (int i = 0 ; i < sjlyb.length ; i++) {
				sql.append("'").append(sjlyb[i].trim().toLowerCase().replace(",", "")).append("'");
				if (i != sjlyb.length-1) {
					sql.append(",");
				}
			}
		} else {
			sql.append("''");
		}
		
		sql.append(") union ");
		sql.append("select lower(a.table_name||a.column_name) pkValue,'' checked,lower(a.table_name) lyb,");
		sql.append("(select nvl(comments,a.table_name) from user_tab_comments where table_name= upper(a.table_name)) lybmc,");
		sql.append("lower(a.column_name) zd,nvl(a.comments,a.column_name) zdmc,");
		sql.append("'' zdm,'' zdlx,'' lrxz,'' bxlr,'' zdsx ");
		sql.append("from user_col_comments a where table_name in ( ");
		
		if (null != sjlyb && sjlyb.length>0) {
		
			for (int i = 0 ; i < sjlyb.length ; i++) {
				sql.append("upper('").append(sjlyb[i].trim().replace(",", "")).append("')");
				
				if (i != sjlyb.length-1) {
					sql.append(",");
				}
			}
		} else {
			sql.append("''");
		}	
		
		sql.append(") and not exists (select 1 from ");
		sql.append(xmnrzdb);
		sql.append(" where zd=lower(a.column_name)) order by checked");
		
		Pages pages = model.getPages();
		
		String coutSql = "select count(*) count from ("+sql+")";
		pages.setMaxRecord(Integer.valueOf(getOneRs(coutSql,  new String[] {xmdm,}, "count")));
		
		StringBuilder rsSql = new StringBuilder();
		
		rsSql.append("select * from (select a.*,rownum r from (");
		rsSql.append(sql);
		rsSql.append(") a ) where r > ");
		rsSql.append(pages.getStart());
		rsSql.append(" and r <= ");
		rsSql.append(pages.getStart()+ pages.getPageSize());
		
		
		return getList(rsSql.toString(), new String[] {xmdm,}, colList);
	}
	
	
	
	/**
	 * 获取指定项目的表名
	 * @param xmdm
	 * @param xmdmb
	 * @param xmbzd
	 * @return
	 */
	public String getXmbmc(String xmdm,String xmdmb,String xmbzd) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select ").append(xmbzd).append(" from ").append(xmdmb).append(" where xmdm=?");
		return getOneRs(sql.toString(), new String[] {xmdm}, xmbzd);
	}
	
	
	
	/**
	 * 保存设置字段
	 * @param sjy
	 * @param xmdm
	 * @param xmnrzdb
	 * @param pkValue
	 * @param flg
	 * @param lyb
	 * @param zd
	 * @param zdm
	 * @param bxlr
	 * @param zdlx
	 * @param lrxz
	 * @param zdsx
	 * @return
	 * @throws SQLException
	 */
	public boolean bcszzd(String[] sjy,String xmdm,String xmnrzdb, String[] pkValue, String[] flg,
							String[] lyb, String[] zd, String[] zdm, String[] bxlr,
							String[] zdlx, String[] lrxz, String[] zdsx) throws SQLException {

		String[] sqlArr = new String[flg.length*3+1]; 
		
		int n = 0;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from ").append(xmnrzdb).append(" where lyb not in (");
		for (int i = 0 ; i < sjy.length ; i++) {
			sql.append("'").append(sjy[i]).append("'");
			
			if (i != sjy.length-1) {
				sql.append(",''");
			}
		}
		sql.append(") and xmdm='").append(xmdm).append("'");
		
		sqlArr[n] = sql.toString();
		n++;
		
		for (int i = 0 ; i < flg.length ; i++) {
			
			StringBuilder delSql = new StringBuilder();
			
			delSql.append("delete from ")
				  .append(xmnrzdb)
				  .append(" where xmdm||lyb||zd = '")
				  .append(xmdm)
				  .append(lyb[i].toLowerCase())
				  .append(zd[i].toLowerCase())
				  .append("'");
			sqlArr[n] = delSql.toString();
			
			n++;
			
			if (StringUtils.stringExistArray(flg[i], pkValue)) {
				StringBuilder insSql = new StringBuilder();
				
				insSql.append("insert into ")
					  .append(xmnrzdb)
					  .append("(xmdm,lyb,zd,zdm,bxlr,zdlx,lrxz,zdsx)")
					  .append(" values ('").append(xmdm).append("','")
					  .append(lyb[i].toLowerCase()) .append("','")
					  .append(zd[i].toLowerCase()).append("','")
					  .append(zdm[i]).append("','")
					  .append(bxlr[i]).append("','").append(zdlx[i]).append("','")
					  .append(lrxz[i]).append("','").append(zdsx[i]).append("')");
				
				sqlArr[n] = insSql.toString();
				n++;
				
			}
			
			
		}
		
		int[] result = runBatch(sqlArr);
		return checkBatch(result);
	}
	
	
	
	
	/**
	 * 返回某项目是否默认项目
	 * @param xmdm
	 * @return
	 */
	public String getIsMrxm(String xmdm) {
		
		String sql = "select mrxm from xszz_zzxmb where xmdm=?";
		
		return getOneRs(sql, new String[] {xmdm}, "mrxm");
	}
}
