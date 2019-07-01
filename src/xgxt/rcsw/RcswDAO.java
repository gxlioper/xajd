package xgxt.rcsw;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class RcswDAO extends CommonQueryDAO {

	/**
	 * 获得日常事务相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getRcswList(String tableName, Object model,
			String[] colList, String other_query, String[] orderBy)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] { "lyr", "lylx", "xydm", "zydm",
				"bjdm","lx","nj"};

		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();

		other_query = Base.isNull(other_query) ? "" : other_query;

		if (!Base.isNull(query)) {
			query += " " + other_query;
		} else {
			query = other_query;
		}

		if (orderBy != null && orderBy.length > 0) {
			StringBuffer orderSb = new StringBuffer(" order by ");
			for (int i = 0; i < orderBy.length; i++) {
				if (i == 0) {
					orderSb.append(orderBy[i]);
				} else {
					orderSb.append("," + orderBy[i]);
				}
			}
			query += orderSb.toString();
		}
		
		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}
	
	/**
	 * 获得日常事务相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getRcswInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	/**
	 * 修改日常事务信息（dwr）
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(修改字段)
	 * @param colList(修改字段值)
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean updateRcswDwr(String tableName, String pk, String pkValue,
			String[] colList, String[] value) throws Exception {
		
		DAO dao = DAO.getInstance();
		
		boolean flag = false;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("update " + tableName + " ");
		if (colList != null && colList.length > 0) {
			for (int i = 0; i < colList.length; i++) {
				if (i == 0) {
					sql.append("set " + colList[i] + "= ? ");
				} else {
					sql.append("," + colList[i] + "= ? ");
				}
			}
			sql.append(" where " + pk + "= '" + pkValue + "'");
		}
		
		flag = dao.runUpdate(sql.toString(), value);
		
		return flag;
	}
	
	
	
	/**
	 * 车次列表
	 * @author quph
	 */
	public List<HashMap<String, String>> getCcList() {
		DAO dao = DAO.getInstance();
		String sql = "select cc from hcccb";
		
		return dao.getList(sql, new String[] {}, new String[] { "cc" });
	}
	
	
	
	/**
	 * 车站列表
	 * @author quph
	 */
	public List<HashMap<String, String>> getCzList() {
		DAO dao = DAO.getInstance();
		String sql = "select czdm,czmc from czdmb";
		
		return dao.getList(sql, new String[] {}, new String[] {"czdm", "czmc" });
	}
	
	/**
	 * 获取心理咨询咨询师
	 */
	public List<HashMap<String,String>> getXljkZxs(){
		DAO dao= DAO.getInstance();
		String sql="select zgh dm,zxxxm||'('||xm||')' mc from  xljk_zxsxxb where  zgh is not null and xm is not null";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 *  根据查询条件查询心理咨询师
	 * 
	 */
	public List<HashMap<String,String>>getXlzxsByTj(String bmdm,String zgh,String xm){
		DAO dao= DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		StringBuilder query=new StringBuilder();
		if(!"".equalsIgnoreCase(bmdm) && bmdm!=null){
			query.append("  and b.bmdm='"+bmdm+"' ");
		}
		if(!"".equalsIgnoreCase(zgh) && zgh!=null ){
			query.append(" and a.zgh like '%"+zgh+"%' ");
		}
		if(!"".equalsIgnoreCase(xm) && xm!=null){
			query.append(" and a.xm like '%"+xm+"%' ");
		}
		sql.append(" select zgh dm,zxxxm||'('||xm||')' mc from xljk_zxsxxb a where exists");
		sql.append("(select 1  from  fdyxxb b where a.zgh=b.zgh "+query.toString()+") ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"dm","mc"});
	}
}	
