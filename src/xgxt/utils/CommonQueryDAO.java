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
    //    查询用 获得数组的通用方法
		ArrayList<String[]> rs = null;
		//取到colList的长度
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
    //    查询用 获得数组的通用方法
		ArrayList<String[]> rs = null;
		//取到colList的长度
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
    //    查询用 获得HashMap<String, String[]>形式的通用方法
		HashMap<String, String[]> rs = new HashMap<String, String[]>();
		//    取到colList的长度
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
    //    查询用 获得List<HashMap<String, String>>形式的通用方法
		List<HashMap<String, String>> rs;
		int size = colList.length-1;
        //    取到colList的长度
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
    //    通过主键查询单个数据用 获得HashMap<String, String>形式的通用方法
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
	    //    通过主键查询单个数据用 获得HashMap<String, String>形式的通用方法
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
    //    通过主键查询单个数据用 获得HashMap<String, String>形式,并覆盖之前传入的HashMap里的值的通用方法
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
	    //    通过主键查询单个数据用 获得HashMapString[]形式的通用方法
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
    //    得到视图的字段注释语句
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
		// 获取职工列表
		DAO dao = DAO.getInstance();
		if(bmdm==null){
			bmdm = "";
		}
		String sql = "select zgh,xm from view_fdyxx where bmdm = ? or ? = ' '  ";
		return dao.getList(sql, new String[] {bmdm,bmdm+" "}, new String[] { "zgh", "xm" });
	}
	
	/**通用方法
	 * 获取相关信息（可参考下面 “获取学生相关信息”例子）
	 * @param table 表名
	 * @param colList 输出字段
	 * @param querry 查询条件
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
	
	/**通用方法
	 * 获取相关信息（可参考下面 “获取学生相关信息”例子）
	 * @param table 表名
	 * @param colList 输出字段
	 * @param querry 查询条件
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
	 * 通用方法
	 * 获取学生相关信息
	 * @param xh 学号
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
	 * 通用方法
	 * 获取学生相关信息
	 * @param xh 学号
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
	 * 通用方法
	 * 获取学生住宿相关信息
	 * @param xh 学号
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
	 * 通用方法
	 * 获取公寓寝室相关信息
	 * @param ssbh 宿舍编号
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
	 * 通用方法
	 * 获取学期名称
	 * @param ssbh 宿舍编号
	 * @return
	 */
	public static String getXqMc(String xq){
		DAO dao = DAO.getInstance();
		String xqmc="";
		String sql = " select xqmc from xqdzb where xqdm=? ";
		xqmc= dao.getOneRs(sql, new String[]{xq}, "xqmc");		
		return xqmc;
	}
	
	//非分页
	public static ArrayList<String[]> commonQueryNotFy(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
		DAO dao = DAO.getInstance();
    //    查询用 获得数组的通用方法
		ArrayList<String[]> rs = null;
		//取到colList的长度
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
	 * 通用的查询数据结果，带分页的， 注意：sql,colList,model 这三个参数不能为空
	 * 
	 * @param sql
	 *            自定义的SQL语句，不能为空
	 * @param query
	 *            where查询条件
	 * @param inPutList
	 *            where查询条件值
	 * @param colList
	 *            输出字段列表     不能为空
	 * @param model
	 *            要使用到的分页面的model 不能为空
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
	 * 通用的查询数据结果，不带分页的， 注意：sql,colList,model 这三个参数不能为空
	 * 
	 * @param sql
	 *            自定义的SQL语句，不能为空
	 * @param query
	 *            where查询条件
	 * @param inPutList
	 *            where查询条件值
	 * @param colList
	 *            输出字段列表     不能为空
	 * @param model
	 *            要使用到的分页面的model 不能为空
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
	 * 查询数据结果，带分页的，注意sql语句中要带rownum
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
	 * 获取用户组代码
	 * @param userDep
	 * @return
	 */
	public static String getUserZdm (String userName){
		DAO dao = DAO.getInstance();
		String sql = "select zdm from yhb where yhm = ?";
		return dao.getOneRs(sql, new String[]{userName}, "zdm");
	}
	
	/**
	 * 获取组列表
	 */
	public static List getYhzList(){
		DAO dao = DAO.getInstance();
		List yhzList = null;
		String sql = "select zdm,zmc from yhzb a order by zdm";
		yhzList = dao.getList(sql, new String[]{}, new String[]{"zdm","zmc"});
		return yhzList;
	}
	
	/**
	 * 获取用户组列表，区别是
	 */
	public static List getYhzForSzdwList(){
		DAO dao = DAO.getInstance();
		List yhzList = null;
		String sql = "select zdm,zmc from yhzb a where zdm!='6727' order by zdm";
		yhzList = dao.getList(sql, new String[]{}, new String[]{"zdm","zmc"});
		return yhzList;
	}
	
	/**
	 * 获取用户组列表，区别是
	 */
	public static List getDwList(){
		DAO dao = DAO.getInstance();
		List yhzList = null;
		String sql = "select dwdm,dwmc from bks_dwdmb order by dwdm";
		yhzList = dao.getList(sql, new String[]{}, new String[]{"dwdm","dwmc"});
		return yhzList;
	}
	
	/**
	 * 学年年度列表，不包括空选项
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
	 * 获取表主键字段
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
	 * 根据用户名取真实姓名
	 * 采用正则表达式验证参数,若参数为空或NULL直接返回null
	 * 1:如果传入参数纯数字查询学生基本信息视图
	 *   若返回值为空查询用户表;
	 * 2：非纯数字查询用户表
	 * 若两种情况都返回空则return null
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
	
	/**通用方法
	 * 获取相关信息（可参考下面 “获取学生相关信息”例子）区别是分页了
	 * @param table 表名
	 * @param colList 输出字段
	 * @param querry 查询条件
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
    //    查询用 获得数组的通用方法
		ArrayList<String[]> rs = null;
		//取到colList的长度
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
	 * 通用查询条件
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
		
		//模糊查询
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
	 * 通用分页查询
	 * @param pages
	 * @param sql
	 * @param inputList
	 * @param colList
	 * @return
	 */
	public static List<String[]> commonPageQuery(Pages pages, String sql,String[] inputList,String[] colList) {
		DAO dao = DAO.getInstance();
		//查询最大记录数
		String maxRecordSql = "select count(1) count "+ sql.substring(sql.indexOf("from"));
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(maxRecordSql, inputList, "count")));
		
		//分页查询
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
	 * 通用分页查询(外部分页)By北京联合大学
	 * @param pages
	 * @param sql
	 * @param inputList
	 * @param colList
	 * @return
	 */
	public static List<String[]> commonPageByPjQuery(Pages pages, String sql,String[] inputList,String[] colList) {
		DAO dao = DAO.getInstance();
		//查询最大记录数
		String maxRecordSql = "select count(1) count "+ sql.substring(sql.indexOf("from"));
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(maxRecordSql, inputList, "count")));
		
		//分页查询
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
	 * 通用分页查询
	 * @param pages
	 * @param sql
	 * @param inputList
	 * @param colList
	 * @return
	 */
	public static List<HashMap<String,String>> commonPageQueryForMap(Pages pages, String sql,String[] inputList) {
		DAO dao = DAO.getInstance();
		//查询最大记录数
		String maxRecordSql = "select count(1) count "+ sql.substring(sql.indexOf("from"));
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(maxRecordSql, inputList, "count")));
		
		//分页查询
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
	 * 通用分页查询
	 * @param pages
	 * @param sql
	 * @param inputList
	 * @param colList
	 * @return
	 */
	public static List<String[]> commonQuery(Pages pages, String sql,String[] inputList,String[] colList) {
		DAO dao = DAO.getInstance();
		//查询最大记录数
		String maxRecordSql = "select count(1) count "+ sql.substring(sql.indexOf("from"));
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(maxRecordSql, inputList, "count")));
		
		//分页查询
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
	 * 根据用户类型过滤查询范围
	 * @param user
	 * @param tableBm
	 * @param xydm
	 * @param bjdm
	 * @return
	 */
	public static String getQuerySqlByUser(User user, String tableBm,String xydm, String bjdm) {

		String userName = user.getUserName();// 用户名
		String userType = user.getUserType();// 用户类型
		String userDep = user.getUserDep();// 用户所在部门

		// 辅导员权限
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// 班主任权限
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		boolean isXy = false;

		if ("xy".equalsIgnoreCase(userType) && !fdyqx && !bzrqx) {
			// 学院用户
			isXy = true;
		}
		
		StringBuilder query = new StringBuilder();
		
		if (isXy && !Base.isNull(xydm)) {// 访问用户为学院
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(xydm + " = '" + userDep + "' ");

		} else if (fdyqx && bzrqx && !Base.isNull(bjdm)) {// 访问用户为辅导员兼班主任

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
	 * 自定义导出设置数据，不带分页的，
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
	 * 自定义导出设置数据,带分页的
	 * @param pages
	 * @param sql
	 * @param inputList
	 * @param colList
	 * @return
	 */

	public static List<HashMap<String,String>> commonPageQueryForExportMap(Pages pages, String sql,String[] inputList) {
		DAO dao = DAO.getInstance();
		//查询最大记录数
		String maxRecordSql = "select count(1) count "+ sql.substring(sql.indexOf("from"));
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(maxRecordSql, inputList, "count")));
		
		//分页查询
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
	 * 自定义导出设置数据,不带分页的
	 * @param tableName
	 * @param query
	 * @param inPutList
	 * @param colList
	 * @param sql
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public static List<HashMap<String,String>> commonQueryExportNotFy(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
		DAO dao = DAO.getInstance();
    //    查询用 获得数组的通用方法
		List<HashMap<String,String>> rs = null;
		//取到colList的长度
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
