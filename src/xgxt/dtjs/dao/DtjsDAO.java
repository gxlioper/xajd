package xgxt.dtjs.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.dtjs.sjxy.SjxyDtjsForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class DtjsDAO {
	public ArrayList<String[]> sxjyQuery(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
		DAO dao = DAO.getInstance();
    //    ��ѯ�� ��������ͨ�÷���
		ArrayList<String[]> rs = null;
		//ȡ��colList�ĳ���
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(query);
			rs = dao.rsToVator(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.rsToVator(sql, inPutList, colList);
		}
		return rs;
	}
	
	public HashMap<String, String[]> sxjyQueryForHashMap(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
		DAO dao = DAO.getInstance();
    //    ��ѯ�� ���HashMap<String, String[]>��ʽ��ͨ�÷���
		HashMap<String, String[]> rs = new HashMap<String, String[]>();
		//    ȡ��colList�ĳ���
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(query);
			rs = dao.getHashMapList(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.getHashMapList(sql, inPutList, colList);
		}
		return rs;
	}
	
	
	public List<HashMap<String, String>> sxjyQueryforList(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
		DAO dao = DAO.getInstance();
    //    ��ѯ�� ���List<HashMap<String, String>>��ʽ��ͨ�÷���
		List<HashMap<String, String>> rs;
		int size = colList.length-1;
        //    ȡ��colList�ĳ���
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(query);
			 rs = dao.getList(sqlBuffer.toString(), inPutList, colList);
		}else{
			 rs = dao.getList(sql, inPutList, colList);
		}
		return rs;
	}
	
	
	public HashMap<String, String> sxjyQueryOne(String tableName,String [] colList,String pk,String pkValue) {
    //    ͨ��������ѯ���������� ���HashMap<String, String>��ʽ��ͨ�÷���
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		int size = colList.length-1;
		if(pkValue.equalsIgnoreCase("")){
			for(int i=0;i<size;i++){
				map.put(colList[i], "");
			}
		}else{
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for(int i = 0; i<size;i++){
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(" where ");
			sqlBuffer.append(pk);
			sqlBuffer.append("=?");
			String [] rsTmp = dao.getOneRs(sqlBuffer.toString(), new String []{ pkValue }, colList);
			for(int i=0;i<=size;i++){
				map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
			}
		}
		return map;
	}
	
	
	public HashMap<String, String> sxjyQueryOne3(String tableName,String [] colList,String pk,
			String pkValue,HashMap<String, String> map,String sql) {
		DAO dao = DAO.getInstance();
    //    ͨ��������ѯ���������� ���HashMap<String, String>��ʽ,������֮ǰ�����HashMap���ֵ��ͨ�÷���
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
		if(pkValue.equalsIgnoreCase("")){
			for(int i=0;i<size;i++){
				map.put(colList[i], "");
			}
		}else{
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for(int i = 0; i<size;i++){
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(" where ");
			sqlBuffer.append(pk);
			sqlBuffer.append("=?");
			String [] rsTmp = dao.getOneRs(sqlBuffer.toString(), new String []{ pkValue }, colList);
			for(int i=0;i<=size;i++){
				map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
			}
		}
		}else{
			String [] rsTmp = dao.getOneRs(sql, new String []{ pkValue }, colList);
			for(int i=0;i<=size;i++){
				map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
			}
		}
		return map;
	}
	
	public List<HashMap<String, String>> getBjrsList(String userDep,String xydm,String zydm,String nj) {
		// ��ȡ�༶���༶������Ϣ
		DAO dao = DAO.getInstance();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");
		if(xydm != null && !("".equalsIgnoreCase(xydm.trim()))){
			query.append(" and a.xydm='");
			query.append(xydm);
			query.append("' ");
		}
		if(zydm != null && !("".equalsIgnoreCase(zydm.trim()))){
			query.append(" and a.zydm='");
			query.append(zydm);
			query.append("' ");
		}
		if(nj!= null && !("".equalsIgnoreCase(nj.trim()))){
			query.append(" and a.nj='");
			query.append(nj);
			query.append("' ");
		}
		String sql = "select a.bjdm,a.bjmc,b.bjrs from (select distinct bjdm,bjmc from view_njxyzybj) a left join (select count(*) bjrs,bjdm from view_xsjbxx group by bjdm) b on a.bjdm = b.bjdm";
		return dao.getList(sql+query.toString(), new String[] {}, new String[] { "bjdm", "bjmc","bjrs" });
	}
	
	public String[] sxjyQueryOne2(String tableName,String [] colList,String pk,String pkValue) {
	    //    ͨ��������ѯ���������� ���HashMapString[]��ʽ��ͨ�÷���
		DAO dao = DAO.getInstance();
			int size = colList.length-1;
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(" where ");
			sqlBuffer.append(pk);
			sqlBuffer.append("=?");
			String [] rsTmp = dao.getOneRs(sqlBuffer.toString(), new String []{ pkValue }, colList);
			return rsTmp;
		}
	
	public synchronized String[] getViewComm(String viewName) throws SQLException {
    //    �õ���ͼ���ֶ�ע�����
		DAO dao = DAO.getInstance();
		String[] arr = null;
		String sql = "select 'comment on column '||table_name||'.'||column_name||' is '||chr(39)||comments||" +
				"chr(39) com from user_col_comments where table_name=upper('"
				+ viewName
				+ "')";
		arr = dao.getArray(sql, new String[] {}, "com");
		return arr;
	}
	
	/**
	 * ��ѯ��Ա���������Ϣ
	 * @param String tableName
	 * @param DtjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * */
	public List<String[]> selectDyxgsjxxList(String tableName, SjxyDtjsForm model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "nj", "pycc" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		return CommonQueryDAO.commonQuery(tableName, myQuery.getQueryString(),
				myQuery.getInputList(), colList, "", model);
	}
	
	/**
	 * ��ѯ��Ա���������Ϣ����
	 * @param String tableName
	 * @param DtjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * */
	public List<String[]> selectDyxgsjtjForExport(String tableName, SjxyDtjsForm model,
			String[] colList) throws Exception {
		String[] queryList = new String[] { "nj", "pycc" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		return CommonQueryDAO.commonQueryNotFy(tableName, myQuery.getQueryString(),
				myQuery.getInputList(), colList, "", model);
	}
	
	/**
	 * ��ѯѧ����δ����
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getDtjsPyccSelectList(){
		DAO dao = DAO.getInstance();
		return dao.getList("select xsccdm dm,xsccmc mc from dtjs_xsccb order by xsccdm", new String[]{}, new String[]{"dm","mc"});
	}
}
