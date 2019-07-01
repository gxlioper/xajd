package xgxt.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class CommonQueryDAO {
	public static ArrayList<String[]> commonQuery(String tableName,String query,String [] inPutList,
			String [] colList,String sql, Object model) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
    //    ��ѯ�� ��������ͨ�÷���
		ArrayList<String[]> rs = null;
		//ȡ��colList�ĳ���
		int size = colList.length-1;
		Class myClass = model.getClass();
		if (Base.isNull(query)) {
			query = "";
		}
		Pages pages = (Pages)myClass.getMethod("getPages",(Class[]) null).invoke(model,(Object[]) null);
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from "+tableName+ " a " + query.toString(), inPutList, "count")));
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select * from (select rownum r,");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName);
			sqlBuffer.append(" a "); 
			sqlBuffer.append(query);
			sqlBuffer.append(" ) where r > "); 
			sqlBuffer.append(pages.getStart());
			sqlBuffer.append(" and r <= "); 
			sqlBuffer.append((pages.getStart() + pages.getPageSize()));
			rs = dao.rsToVator(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.rsToVator(sql, inPutList, colList);
		}
		return rs;
	}
	
	public static List<String[]> commonQueryNotFy(String tableName,String query,String [] inPutList,
			String [] colList,String sql, Object model) throws Exception {
		DAO dao = DAO.getInstance();
    //    ��ѯ�� ��������ͨ�÷���
		ArrayList<String[]> rs = null;
		//ȡ��colList�ĳ���
		int size = colList.length-1;
		@SuppressWarnings("unused")
		Class myClass = model.getClass();
		if (Base.isNull(query)) {
			query = "";
		}
//		Pages pages = (Pages)myClass.getMethod("getPages",(Class[]) null).invoke(model,(Object[]) null);
//		pages.setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from "+tableName+query.toString(), inPutList, "count")));
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select * from (select rownum r,");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(" a "); 
			sqlBuffer.append(query);
			sqlBuffer.append(" ) "); 
			rs = dao.rsToVator(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.rsToVator(sql, inPutList, colList);
		}
		return rs;
	}
	
	public static HashMap<String, String[]> commonQueryForHashMap(String tableName,String query,String [] inPutList,
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
	
	public static List<HashMap<String, String>> commonQueryforList(String tableName,String query,String [] inPutList,
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
	
	public static HashMap<String, String> commonQueryOne(String tableName,String [] colList,String pk,String pkValue) {
    //    ͨ��������ѯ���������� ���HashMap<String, String>��ʽ��ͨ�÷���
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		int size = colList.length-1;
		if(pkValue==null||pkValue.equalsIgnoreCase("")){
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
	
	public static HashMap<String, String> commonQueryOne2(String tableName,String [] colList,String pk,String pkValue,String query) {
	    //    ͨ��������ѯ���������� ���HashMap<String, String>��ʽ��ͨ�÷���
			DAO dao = DAO.getInstance();
			HashMap<String, String> map = new HashMap<String, String>();
			int size = colList.length-1;
			if(pkValue==null||pkValue.equalsIgnoreCase("")){
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
				sqlBuffer.append(!Base.isNull(query) ? query : "");
				String [] rsTmp = dao.getOneRs(sqlBuffer.toString(), new String []{ pkValue }, colList);
				for(int i=0;i<=size;i++){
					map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
				}
			}
			return map;
		}
	
	public static HashMap<String, String> commonQueryOne3(String tableName,String [] colList,String pk,
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
	
	public static String[] commonQueryOne2(String tableName,String [] colList,String pk,String pkValue) {
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
	
	public static synchronized String[] getViewComm(String viewName) throws SQLException {
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
	
	public static List<HashMap<String, String>> getFdyList(String bmdm) {
		// ��ȡְ���б�
		DAO dao = DAO.getInstance();
		if(bmdm==null){
			bmdm = "";
		}
		String sql = "select zgh,xm from view_fdyxx where bmdm = ? or ? = ' '  ";
		return dao.getList(sql, new String[] {bmdm,bmdm+" "}, new String[] { "zgh", "xm" });
	}
	
	/**ͨ�÷���
	 * ��ȡ�����Ϣ���ɲο����� ����ȡѧ�������Ϣ�����ӣ�
	 * @param table ����
	 * @param colList ����ֶ�
	 * @param querry ��ѯ����
	 * @return
	 */
	public static HashMap<String,String> dao_getInfo(String table,String[]colList,String querry){
		DAO dao = DAO.getInstance();
		if(colList==null){
			colList =  dao.getColumnName("select * from "+table);
			for(int i=0;i<colList.length;i++){
				colList[i]=colList[i].toLowerCase();
			}
		}
		String sql = "select * from "+table;
		return dao.getMap(sql+querry.toString(),new String[]{},colList);
	}
	
	/**ͨ�÷���
	 * ��ȡ�����Ϣ���ɲο����� ����ȡѧ�������Ϣ�����ӣ�
	 * @param table ����
	 * @param colList ����ֶ�
	 * @param querry ��ѯ����
	 * @return
	 */
	public static List<HashMap<String,String>> dao_getInfotoList(String table,String[]colList,String querry){
		DAO dao = DAO.getInstance();
		if(colList==null){
			colList =  dao.getColumnName("select * from "+table);
			for(int i=0;i<colList.length;i++){
				colList[i]=colList[i].toLowerCase();
			}
		}
		
		String sql = "select rownum r,a.* from (select * from "+table+" "+querry.toString()+")a ";
		List<HashMap<String,String>> list = dao.getList(sql,new String[]{},colList);
		for(int i=0;i<list.size();i++){
			for(int j=0;j<colList.length;j++){
				String val=list.get(i).get(colList[j]);
				list.get(i).put(colList[j], Base.isNull(val)?"":val);
			}
		}
		return list;
	}
	
	/**
	 * ͨ�÷���
	 * ��ȡѧ�������Ϣ
	 * @param xh ѧ��
	 * @return
	 */
	public static HashMap<String, String> getStuInfo(String xh) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Base.isNull(xh)) {
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "sfzh", "syd",
					"csrq", "mzmc", "zzmmmc", "zw", "jtdh","jtdz","jtyb", "lxdh", "jg", "xz",
					"pycc", "zkzh", "yhmc", "yhkh", "rxrq" };
			map = commonQueryOne("view_xsxxb", colList, "xh",xh);
		}
		return map;
	}

	/**
	 * ͨ�÷���
	 * ��ȡѧ�������Ϣ
	 * @param xh ѧ��
	 * @return
	 */
	public static HashMap<String, String> getDetStuInfo(String xh) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Base.isNull(xh)) {
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "sfzh", "csrq",
					"mzmc", "zzmmmc", "lxdh", "xz", "pycc" };
			map = commonQueryOne("view_stu_details", colList, "xh",xh);
		}
		return map;
	}
	
	/**
	 * ͨ�÷���
	 * ��ȡѧ��ס�������Ϣ
	 * @param xh ѧ��
	 * @return
	 */
	public static HashMap<String,String> getStuZsInfo(String xh){
		HashMap<String,String> map = new HashMap<String, String>();
		if(!Base.isNull(xh)){
			DAO dao = DAO.getInstance();
			String sql = "select b.ssbh,b.ldmc ,b.lddm,b.cs lc,b.qsh from xszsxxb a,view_ssxx b where a.ssbh=b.ssbh and  a.xh=? ";
			String[] colList= new String[]{"ssbh","ldmc","lddm","lc","qsh"};
			map= dao.getMap(sql,new String[]{xh},colList);
		}
		return map;
	}
	
	/**
	 * ͨ�÷���
	 * ��ȡ��Ԣ���������Ϣ
	 * @param ssbh ������
	 * @return
	 */
	public static HashMap<String,String> getGySsInfo(String ssbh){
		HashMap<String,String> map = new HashMap<String, String>();
		if(!Base.isNull(ssbh)){		
			String querry = " where ssbh='"+ssbh+"' ";
			map= dao_getInfo("view_ssxx", null,querry);
		}
		return map;
	}

	public static String getBjrs(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select count(1) sum from view_xsjbxx a where bjdm = (select bjdm from view_xsjbxx b where xh = ?)";
		return dao.getOneRs(sql,new String[]{xh},"sum");
	}
	
	/**
	 * ͨ�÷���
	 * ��ȡѧ������
	 * @param ssbh ������
	 * @return
	 */
	public static String getXqMc(String xq){
		DAO dao = DAO.getInstance();
		String xqmc="";
		String sql = " select xqmc from xqdzb where xqdm=? ";
		xqmc= dao.getOneRs(sql, new String[]{xq}, "xqmc");		
		return xqmc;
	}
	
	//�Ƿ�ҳ
	public static ArrayList<String[]> commonQueryNotFy(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
		DAO dao = DAO.getInstance();
    //    ��ѯ�� ��������ͨ�÷���
		ArrayList<String[]> rs = null;
		//ȡ��colList�ĳ���
		if (Base.isNull(query)) {
			query = "";
		}
		int size = colList.length-1;
		if(null == sql || sql.equalsIgnoreCase("")){
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
	
	/**
	 * ͨ�õĲ�ѯ���ݽ��������ҳ�ģ� ע�⣺sql,colList,model ��������������Ϊ��
	 * 
	 * @param sql
	 *            �Զ����SQL��䣬����Ϊ��
	 * @param query
	 *            where��ѯ����
	 * @param inPutList
	 *            where��ѯ����ֵ
	 * @param colList
	 *            ����ֶ��б�     ����Ϊ��
	 * @param model
	 *            Ҫʹ�õ��ķ�ҳ���model ����Ϊ��
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static ArrayList<String[]> commonQuery(String sql, String query,
			String[] inPutList, String[] colList, Object model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = null;
		if (StringUtils.isNull(sql) || colList == null || model == null) {
			return rs;
		}
		query = StringUtils.isNull(query) ? "" : query;
		
		Class myClass = model.getClass();
		Pages pages = (Pages) myClass.getMethod("getPages", (Class[]) null).invoke(model, (Object[]) null);
		String maxRecord = dao.getOneRs("select count(1) count from (" + sql + query.toString() + ")",inPutList, "count");
		pages.setMaxRecord(Integer.parseInt(StringUtils.isNull(maxRecord)?"0":maxRecord));
		
		StringBuffer sqlBuffer = new StringBuffer("select * from (");
		sqlBuffer.append(sql);
		sqlBuffer.append(query);
		sqlBuffer.append(" ) where r > "); 
		sqlBuffer.append(pages.getStart());
		sqlBuffer.append(" and r <= "); 
		sqlBuffer.append((pages.getStart() + pages.getPageSize()));
		rs = dao.rsToVator(sqlBuffer.toString(), inPutList, colList);
		return rs;
	}
	
	
	public static ArrayList<String[]> xytjQuery(String sql, String query,
			String[] inPutList, String[] colList, Object model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = null;

		rs = dao.rsToVator(sql, inPutList, colList);
		return rs;
	}
	
	/**
	 * ͨ�õĲ�ѯ���ݽ����������ҳ�ģ� ע�⣺sql,colList,model ��������������Ϊ��
	 * 
	 * @param sql
	 *            �Զ����SQL��䣬����Ϊ��
	 * @param query
	 *            where��ѯ����
	 * @param inPutList
	 *            where��ѯ����ֵ
	 * @param colList
	 *            ����ֶ��б�     ����Ϊ��
	 * @param model
	 *            Ҫʹ�õ��ķ�ҳ���model ����Ϊ��
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static ArrayList<String[]> commonQueryNotFy(String sql, String query,
			String[] inPutList, String[] colList, Object model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = null;
		if (StringUtils.isNull(sql) || colList == null || model == null) {
			return rs;
		}
		query = StringUtils.isNull(query) ? "" : query;

		StringBuffer sqlBuffer = new StringBuffer("select * from (");
		sqlBuffer.append(sql);
		sqlBuffer.append(query);
		sqlBuffer.append(" )");
		rs = dao.rsToVator(sqlBuffer.toString(), inPutList, colList);
		return rs;
	}
	
	/**
	 * ��ѯ���ݽ��������ҳ�ģ�ע��sql�����Ҫ��rownum
	 * @param sql
	 * @param query
	 * @param inPutList
	 * @param colList
	 * @param model
	 * @return
	 * @author sjf
	 */
	public static List<HashMap<String, String>> commonQueryforList(String sql,String query,String [] inPutList,
			String [] colList,Object model) {
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> rs = null;
		
		if (StringUtils.isNull(sql) || colList == null || model == null) {
			return rs;
		}
		query = StringUtils.isNull(query) ? "" : query;
		
		Class myClass = model.getClass();
		Pages pages;
		try {
			pages = (Pages) myClass.getMethod("getPages", null).invoke(model, null);
			String maxRecord = dao.getOneRs("select count(*) count from (" + sql + query.toString() + ")",inPutList, "count");
			pages.setMaxRecord(Integer.parseInt(StringUtils.isNull(maxRecord)?"0":maxRecord));
			
			StringBuffer sqlBuffer = new StringBuffer("select * from (");
			sqlBuffer.append(sql);
			sqlBuffer.append(query);
			sqlBuffer.append(" ) where r > "); 
			sqlBuffer.append(pages.getStart());
			sqlBuffer.append(" and r <= "); 
			sqlBuffer.append((pages.getStart() + pages.getPageSize()));
			
			rs = dao.getList(sqlBuffer.toString(), inPutList, colList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public static String getUserType (String userDep){
		DAO dao = DAO.getInstance();
		return dao.getUserType(userDep);
	}
	
	/**
	 * ��ȡ�û������
	 * @param userDep
	 * @return
	 */
	public static String getUserZdm (String userName){
		DAO dao = DAO.getInstance();
		String sql = "select zdm from yhb where yhm = ?";
		return dao.getOneRs(sql, new String[]{userName}, "zdm");
	}
	
	/**
	 * ��ȡ���б�
	 */
	public static List getYhzList(){
		DAO dao = DAO.getInstance();
		List yhzList = null;
		String sql = "select zdm,zmc from yhzb a order by zdm";
		yhzList = dao.getList(sql, new String[]{}, new String[]{"zdm","zmc"});
		return yhzList;
	}
	
	/**
	 * ��ȡ�û����б�������
	 */
	public static List getYhzForSzdwList(){
		DAO dao = DAO.getInstance();
		List yhzList = null;
		String sql = "select zdm,zmc from yhzb a where zdm!='6727' order by zdm";
		yhzList = dao.getList(sql, new String[]{}, new String[]{"zdm","zmc"});
		return yhzList;
	}
	
	/**
	 * ��ȡ�û����б�������
	 */
	public static List getDwList(){
		DAO dao = DAO.getInstance();
		List yhzList = null;
		String sql = "select dwdm,dwmc from bks_dwdmb order by dwdm";
		yhzList = dao.getList(sql, new String[]{}, new String[]{"dwdm","dwmc"});
		return yhzList;
	}
	
	/**
	 * ѧ������б���������ѡ��
	 * @return
	 */
	public  static List<HashMap<String, String>> getFullXnlist() {
		List<HashMap<String, String>> result =  new ArrayList<HashMap<String,String>>();
		result.addAll(Base.getXnndList());
		if(result.size()!=0 && Base.isNull(result.get(0).get("xn"))){
			result.remove(0);
		}
		return result;
	}
	
	
	/**
	 * ��ȡ�������ֶ�
	 * @param tableName
	 * @return
	 * @throws SQLException 
	 */
	public static String[] GetPkCol(String tableName) throws SQLException{
		DAO dao = DAO.getInstance();
		String sql = "select COLUMN_NAME from user_constraints a,user_cons_columns b where " +
				"a.CONSTRAINT_NAME = b.CONSTRAINT_NAME and a.CONSTRAINT_TYPE = 'P' and " +
				"a.TABLE_NAME=upper(?) order by b.POSITION";
		return dao.getArray(sql, new String[]{tableName}, "COLUMN_NAME");
	}
	
	
	
	/**
	 * �����û���ȡ��ʵ����
	 * ����������ʽ��֤����,������Ϊ�ջ�NULLֱ�ӷ���null
	 * 1:���������������ֲ�ѯѧ��������Ϣ��ͼ
	 *   ������ֵΪ�ղ�ѯ�û���;
	 * 2���Ǵ����ֲ�ѯ�û���
	 * ��������������ؿ���return null
	 * 
	 * @param userName
	 * @return 
	 * @author quph
	 */
	public static String getRealName(String userName) {
		DAO dao = DAO.getInstance();
		
		String xhSql = "select xm  from view_xsjbxx where xh = ?";
		String yhSql = "select xm  from yhb where yhm = ?";
		String realName = null;
		
		if (Base.isNull(userName)) {
			return null;
		}
		
		if (Pattern.matches("^[0-9][0-9]*[0-9]$", userName)) {
			realName = dao.getOneRs(xhSql, new String[] {userName},"xm");
			
			if (Base.isNull(realName)) {
				realName = dao.getOneRs(yhSql, new String[] {userName},"xm");
			}
			
		} else {
			realName = dao.getOneRs(yhSql, new String[] {userName},"xm");
		}
		
		return realName;
	}
	
	/**ͨ�÷���
	 * ��ȡ�����Ϣ���ɲο����� ����ȡѧ�������Ϣ�����ӣ������Ƿ�ҳ��
	 * @param table ����
	 * @param colList ����ֶ�
	 * @param querry ��ѯ����
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public static List<HashMap<String,String>> dao_getInfotoListFy(String table,String[]colList,String querry, Object model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		DAO dao = DAO.getInstance();
		Class myClass = model.getClass();
		Pages pages = (Pages)myClass.getMethod("getPages",(Class[]) null).invoke(model,(Object[]) null);
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from "+table+ " a " + querry.toString(), new String[]{}, "count")));
		if(colList==null){
			colList =  dao.getColumnName("select * from "+table);
			for(int i=0;i<colList.length;i++){
				colList[i]=colList[i].toLowerCase();
			}
		}
		
		StringBuilder sql = new StringBuilder( "select * from (select rownum r,a.* from ");
		sql.append(table);
		sql.append(" a ");
		sql.append(querry.toString());
		sql.append(")");
		sql.append(" where r > "); 
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>(); 
		list = dao.getList(sql.toString(),new String[]{},colList);
		for(int i=0;i<list.size();i++){
			for(int j=0;j<colList.length;j++){
				String val=list.get(i).get(colList[j]);
				list.get(i).put(colList[j], Base.isNull(val)?"":val);
			}
		}
		return list;
	}
	
	public static ArrayList<String[]> commonQueryForPk(String tableName,String query,String [] inPutList,
			String [] colList,String sql, Object model) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
    //    ��ѯ�� ��������ͨ�÷���
		ArrayList<String[]> rs = null;
		//ȡ��colList�ĳ���
		int size = colList.length-1;
		Class myClass = model.getClass();
		if (Base.isNull(query)) {
			query = "";
		}
		Pages pages = (Pages)myClass.getMethod("getPages",(Class[]) null).invoke(model,(Object[]) null);
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from "+tableName+ " a " + query.toString(), inPutList, "count")));
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select * from (select a.*,rownum r from ( select ");
			sqlBuffer.append(colList[0]);
			sqlBuffer.append(" pk, ");
			for(int i = 1; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName);
			sqlBuffer.append(" a ");
			sqlBuffer.append(query);
			sqlBuffer.append(" order by ");
			sqlBuffer.append(colList[0]);
			sqlBuffer.append(" )a  ");
			sqlBuffer.append(" )a where r > ");
			sqlBuffer.append(pages.getStart());
			sqlBuffer.append(" and r <= ");
			sqlBuffer.append((pages.getStart() + pages.getPageSize()));
			colList[0] = "pk";
			rs = dao.rsToVator(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.rsToVator(sql, inPutList, colList);
		}
		return rs;
	}
	
	
	/**
	 * ͨ�ò�ѯ����
	 * @param model
	 * @param queryArr 
	 * @param queryLikeArr
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getQuerySQL(Object model,String[] queryArr,String[] queryLikeArr) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		
		Class c = model.getClass();
		
		for (int i = 0 ; i < queryArr.length ; i++){
			String methodName = "get"+queryArr[i].substring(0, 1).toUpperCase()+queryArr[i].substring(1);
			String value = (String) c.getMethod(methodName, null).invoke(model, null);
			
			if (StringUtils.isNotNull(value)){
				sql.append(" and ")
				   .append(queryArr[i])
				   .append("=?");
				input.add(value);
			}
		}
		
		//ģ����ѯ
		for (int i = 0 ; i < queryLikeArr.length ; i++){
			String methodName = "get"+queryLikeArr[i].substring(0, 1).toUpperCase()+queryLikeArr[i].substring(1);
			String value = (String) c.getMethod(methodName, null).invoke(model, null);
			
			if (StringUtils.isNotNull(value)){
				sql.append(" and ")
				   .append(queryLikeArr[i])
				   .append(" like ? ");
				input.add("%"+value+"%");
			}
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("sql", sql.toString());
		result.put("input", input.toArray(new String[]{}));
		
		return result;
	}
	
	
	/**
	 * ͨ�÷�ҳ��ѯ
	 * @param pages
	 * @param sql
	 * @param inputList
	 * @param colList
	 * @return
	 */
	public static List<String[]> commonPageQuery(Pages pages, String sql,String[] inputList,String[] colList) {
		DAO dao = DAO.getInstance();
		//��ѯ����¼��
		String maxRecordSql = "select count(1) count "+ sql.substring(sql.indexOf("from"));
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(maxRecordSql, inputList, "count")));
		
		//��ҳ��ѯ
		StringBuilder querySql = new StringBuilder();
		querySql.append("select * from (")
			    .append(sql)
			    .append(" and rownum <=")
			    .append((pages.getStart() + pages.getPageSize()))
			    .append(") where r > ")
			    .append(pages.getStart());
		return dao.rsToVator(querySql.toString(), inputList, colList);
	}
	/**
	 * ͨ�÷�ҳ��ѯ(�ⲿ��ҳ)By�������ϴ�ѧ
	 * @param pages
	 * @param sql
	 * @param inputList
	 * @param colList
	 * @return
	 */
	public static List<String[]> commonPageByPjQuery(Pages pages, String sql,String[] inputList,String[] colList) {
		DAO dao = DAO.getInstance();
		//��ѯ����¼��
		String maxRecordSql = "select count(1) count "+ sql.substring(sql.indexOf("from"));
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(maxRecordSql, inputList, "count")));
		
		//��ҳ��ѯ
		StringBuilder querySql = new StringBuilder();
		querySql.append("select * from (")
			    .append(sql)
			    .append(") where r > ")
			    .append(pages.getStart())
			    .append(" and r <=")
			    .append((pages.getStart() + pages.getPageSize()));
		return dao.rsToVator(querySql.toString(), inputList, colList);
	}
	
	
	
	/**
	 * ͨ�÷�ҳ��ѯ
	 * @param pages
	 * @param sql
	 * @param inputList
	 * @param colList
	 * @return
	 */
	public static List<HashMap<String,String>> commonPageQueryForMap(Pages pages, String sql,String[] inputList) {
		DAO dao = DAO.getInstance();
		//��ѯ����¼��
		String maxRecordSql = "select count(1) count "+ sql.substring(sql.indexOf("from"));
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(maxRecordSql, inputList, "count")));
		
		//��ҳ��ѯ
		StringBuilder querySql = new StringBuilder();
		querySql.append("select * from (")
			    .append(sql)
			    .append(" and rownum <=")
			    .append((pages.getStart() + pages.getPageSize()))
			    .append(") where r > ")
			    .append(pages.getStart());
		return dao.getListNotOut(querySql.toString(), inputList);
	}
	
	
	/**
	 * ͨ�÷�ҳ��ѯ
	 * @param pages
	 * @param sql
	 * @param inputList
	 * @param colList
	 * @return
	 */
	public static List<String[]> commonQuery(Pages pages, String sql,String[] inputList,String[] colList) {
		DAO dao = DAO.getInstance();
		//��ѯ����¼��
		String maxRecordSql = "select count(1) count "+ sql.substring(sql.indexOf("from"));
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(maxRecordSql, inputList, "count")));
		
		//��ҳ��ѯ
		StringBuilder querySql = new StringBuilder();
		querySql.append("select * from (")
			    .append(sql)
			    .append(" ) where r > ")
			    .append(pages.getStart())
				.append(" and r <= ")
				.append((pages.getStart() + pages.getPageSize()));
		return dao.rsToVator(querySql.toString(), inputList, colList);
	}
	/**
	 * �����û����͹��˲�ѯ��Χ
	 * @param user
	 * @param tableBm
	 * @param xydm
	 * @param bjdm
	 * @return
	 */
	public static String getQuerySqlByUser(User user, String tableBm,String xydm, String bjdm) {

		String userName = user.getUserName();// �û���
		String userType = user.getUserType();// �û�����
		String userDep = user.getUserDep();// �û����ڲ���

		// ����ԱȨ��
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// ������Ȩ��
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		boolean isXy = false;

		if ("xy".equalsIgnoreCase(userType) && !fdyqx && !bzrqx) {
			// ѧԺ�û�
			isXy = true;
		}
		
		StringBuilder query = new StringBuilder();
		
		if (isXy && !Base.isNull(xydm)) {// �����û�ΪѧԺ
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(xydm + " = '" + userDep + "' ");

		} else if (fdyqx && bzrqx && !Base.isNull(bjdm)) {// �����û�Ϊ����Ա�������

			query.append(" and (exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
			
			query.append(" or exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "')) ");

		} else if (fdyqx && !Base.isNull(bjdm)) {
			
			query.append(" and exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");

		} else if (bzrqx) {
			
			query.append(" and exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
			
		} 
		
		return query.toString();
	}
	
	/**
	 * �Զ��嵼���������ݣ�������ҳ�ģ�
	 * @param sql
	 * @param query
	 * @param inPutList
	 * @param colList
	 * @param model
	 * @return
	 * @author dlq
	 */
	@SuppressWarnings({  "unchecked", "rawtypes" })
	public static List<HashMap<String, String>> commonQueryforExportList(String sql,String query,String [] inPutList,
			String [] colList,Object model) {
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> rs = null;
		
		if (StringUtils.isNull(sql) || colList == null || model == null) {
			return rs;
		}
		query = StringUtils.isNull(query) ? "" : query;
		
		Class myClass = model.getClass();
		Pages pages;
		try {
			pages = (Pages) myClass.getMethod("getPages", null).invoke(model, null);
			String maxRecord = dao.getOneRs("select count(*) count from (" + sql + query.toString() + ")",inPutList, "count");
			pages.setMaxRecord(Integer.parseInt(StringUtils.isNull(maxRecord)?"0":maxRecord));
			
			
			StringBuffer sqlBuffer = new StringBuffer("select * from (");
			sqlBuffer.append(sql);
			sqlBuffer.append(query);
			sqlBuffer.append(" ) where r > ");
			sqlBuffer.append("0");
			sqlBuffer.append(" and r <= "); 
			sqlBuffer.append((0 + pages.getMaxRecord()));
			
			//rs = dao.getList(sqlBuffer.toString(), inPutList, colList);
			rs = dao.getListNotOut(sqlBuffer.toString(), inPutList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * �Զ��嵼����������,����ҳ��
	 * @param pages
	 * @param sql
	 * @param inputList
	 * @param colList
	 * @return
	 */

	public static List<HashMap<String,String>> commonPageQueryForExportMap(Pages pages, String sql,String[] inputList) {
		DAO dao = DAO.getInstance();
		//��ѯ����¼��
		String maxRecordSql = "select count(1) count "+ sql.substring(sql.indexOf("from"));
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(maxRecordSql, inputList, "count")));
		
		//��ҳ��ѯ
		StringBuilder querySql = new StringBuilder();
		querySql.append("select * from (")
			    .append(sql)
			    .append(" and rownum <=")
			    .append((0 + pages.getMaxRecord()))
			    .append(") where r > ")
			    .append("0");
		return dao.getListNotOut(querySql.toString(), inputList);
	}
	
	/**
	 * �Զ��嵼����������,������ҳ��
	 * @param tableName
	 * @param query
	 * @param inPutList
	 * @param colList
	 * @param sql
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public static List<HashMap<String,String>> commonQueryExportNotFy(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
		DAO dao = DAO.getInstance();
    //    ��ѯ�� ��������ͨ�÷���
		List<HashMap<String,String>> rs = null;
		//ȡ��colList�ĳ���
		if (Base.isNull(query)) {
			query = "";
		}
		int size = colList.length-1;
		if(null == sql || sql.equalsIgnoreCase("")){
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

}
