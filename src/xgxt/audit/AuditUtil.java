package xgxt.audit;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class AuditUtil {

	
	/**
	 * 根据功能名称查询审批岗位
	 * @param gnmc
	 * @return
	 * @throws SQLException
	 */
	public static String[] getSpgwByGnmc(String gnmc) throws SQLException{
		DAO dao = DAO.getInstance();
		String sql = "select spgw from xg_xtwh_spbz where splc=(select lcid from xg_ty_shlcszb where gnmc=?)";
		
		return dao.getArray(sql, new String[]{gnmc}, "spgw");
	}
	
	
	/**
	 * 根据功能名称查询审批岗位名称
	 * @param gnmc
	 * @return
	 * @throws SQLException
	 */
	public static String[] getGwmcByGnmc(String gnmc) throws SQLException {
		DAO dao = DAO.getInstance();
		String lcmcSql = "select b.mc from xg_xtwh_spbz a,xg_xtwh_spgw b " +
				"where a.spgw=b.id and a.splc= (select lcid from xg_ty_shlcszb where gnmc=?) order by xh";
		
		return  dao.getArray(lcmcSql, new String[]{gnmc}, "mc");
	}
	
	
	/**
	 * 审批岗位名称
	 * @param gnmc
	 * @return
	 * @throws SQLException
	 */
	
	public static String[] getGwmcByGnmcAndUser(String gnmc,String userName) throws SQLException {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select b.mc from xg_xtwh_spbz a,xg_xtwh_spgw b where a.spgw=b.id")
		   .append(" and a.splc= (select lcid from xg_ty_shlcszb where gnmc=?) ")
		   .append(" and exists (select 1 from xg_xtwh_spgwyh c where c.spgw=a.spgw and c.spyh=?) order by xh");
		
		return dao.getArray(sql.toString(), new String[]{gnmc,userName}, "mc");
	}
	
	
	
	/**
	 * 根据审核流程查询审批岗位 
	 * @param shlcid
	 * @return
	 * @throws SQLException 
	 */
	public static String[] getSpgwById(String shlcid) throws SQLException{
		DAO dao = DAO.getInstance();
		String sql = "select spgw from xg_xtwh_spbz where splc=?";
		
		return dao.getArray(sql, new String[]{shlcid}, "spgw");
	}
	
	
	/**
	 * 根据审核流程ID获取审批岗位 
	 * @param shlcId
	 * @return
	 */
	public static List<HashMap<String,String>> getSpgw(String shlcId){
		DAO dao = DAO.getInstance();
		String sql = " select b.id,b.mc from xg_xtwh_spbz a,xg_xtwh_spgw b where a.spgw=b.id and a.splc= ?  order by a.xh";
		
		return dao.getListNotOut(sql, new String[]{shlcId});
	}
}
