package xgxt.szdw.dao.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.form.common.CommonForm;
import xgxt.utils.CommonQueryDAO;

/**
* <p>Title: 学工管理系统</p>
* <p>Description: 学生信息管理思政队伍通用DAO类</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: zfsoft</p>
* @author 鲁宁
* @version 1.0
*/

public class CommonDAO  {
	DAO dao = DAO.getInstance();
	public List<HashMap<String, String>> getCommonList(String tableName,String dm,String mc,String query) {
		// 获取列表通用方法
		// query这个String 如果有内容传过来时要加" " 
		StringBuffer sqlBuffer = new StringBuffer("select ");
		sqlBuffer.append(dm);
		sqlBuffer.append(",");
		sqlBuffer.append(mc);
		sqlBuffer.append(" from ");
		sqlBuffer.append(tableName);
		sqlBuffer.append(query);
		return dao.getList(sqlBuffer.toString(), new String[] {}, new String[] { dm, mc });
	}
	
	public List<HashMap<String, String>> getFdyList(String xydm) {
		// 获取职工列表
		if(xydm==null){
			xydm = "";
		}
		String sql = "select zgh,xm from view_fdyxx where bmdm = ? or ? = ' '  ";
		return dao.getList(sql, new String[] {xydm,xydm+" "}, new String[] { "zgh", "xm" });
	}
	
	public List<HashMap<String, String>> getFdyList2(String xydm,String zgh) {
		// 获取职工列表
		if(xydm==null){
			xydm = "";
		}
		String sql = "select zgh,xm from view_fdyxx where bmdm = ? or ? = ' ' and " +
				"exists (select bmdm from view_fdyxx where zgh = ? or ? = ' ')";
		return dao.getList(sql, new String[] {xydm,xydm+" ",zgh,zgh+" "}, new String[] { "zgh", "xm" });
	}

	public List<HashMap<String, String>> getTutorshippersonList(String userDep) {
		// 获取辅导员信息
		String sql = "select zgh,xm from fdyxxb where (bmdm = ? or ? = ' ') order by zgh";
		return dao.getList(sql, new String[] {userDep,userDep+" "}, new String[] { "zgh", "xm" });
	}

	public Object getClassDutyList() {
		//获取班干部职务种类
		String sql = "select bjgbdm,bjgbmc from sxjy_bjgbzlb order by bjgbdm";
		return dao.getList(sql, new String[] {}, new String[] { "bjgbdm", "bjgbmc" });
	}
	
	public List<HashMap<String, String>> getChkList(int type) {
		// 审核结果下拉框
		String[] chkList = null;
		if (type == 1) {
			chkList = new String[] { "已审核", "未审核" };
		} else if (type == 2) {
			chkList = new String[] { "是", "否" };
		} else if (type == 3) {
			chkList = new String[] { "未审核", "通过", "不通过" };
		} else if(type ==4) {
			chkList = new String[] { "成功", "失败" };
		} else if(type ==5) {
			chkList = new String[] { "未审核", "特困", "一般困难","不通过" };
		} else if(type ==6) {
			chkList = new String[] { "1", "2", "3","4","5","6","7","8","9","10","11","12" };
		}else if(type ==7) {
			chkList = new String[] { "讲座", "座谈", "征文","演讲","参观","其他" };
		}else if(type ==8) {
			chkList = new String[] { "面对面交流", "他人转告", "电话联系","信函","电子邮件","手机短信" };
		}else if(type ==9) {
			chkList = new String[] { "不用提醒","1个月之内", "2个月之内", "3个月之内","半年之内","一年之内","三年之内" };
		}else if(type ==10) {
			chkList = new String[] { ">", "=","<" };
		}
		return dao.arrayToList(chkList, chkList);
	}
	
	
	public String[] getFdyBjList(String pk){
    //    获得班级辅导员列表
		String sql = "select bjmc from fdybjb a,view_njxyzybj b where a.bjdm = b.bjdm and a.zgh=?";
		try {
			return dao.getArray(sql, new String[]{pk}, "bjmc");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] getZyBjList(String pk){
    //    获得班级辅导员所在专业
		String sql = "select distinct zymc from fdybjb a,view_njxyzybj b where a.bjdm = b.bjdm and a.zgh=?";
		try {
			return dao.getArray(sql, new String[]{pk}, "zymc");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Object getSfList() {
    //    获得省份列表
		String sql = "select sfdm,sfmc from sfdmdzb ";
		return dao.getList(sql,new String[]{},new String[]{"sfdm","sfmc"});
	}
	
	
	public ArrayList<String[]> sxjyQuery(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
    //    查询用 获得数组的通用方法
		ArrayList<String[]> rs = null;
		//取到colList的长度
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
	
	
	public List<HashMap<String, String>> sxjyQueryforList(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
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
	
	
	public HashMap<String, String> sxjyQueryOne(String tableName,String [] colList,String pk,String pkValue) {
    //    通过主键查询单个数据用 获得HashMap<String, String>形式的通用方法
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
			map = dao.getMap(sqlBuffer.toString(), new String []{ pkValue }, colList);
		}
		return map;
	}
	
	public ArrayList<String[]> sxjyQueryCx(String tableName,String query,String [] inPutList,
			String [] colList,String sql,CommonForm myForm,String[]colsList) throws Exception{
    //    查询用 获得数组的通用方法
		ArrayList<String[]> rs = null;
		//取到colList的长度
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select rownum r ,");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(query);
			rs = CommonQueryDAO.commonQuery(sqlBuffer.toString(), "", inPutList, colsList, myForm);
		}else{
			rs = CommonQueryDAO.commonQuery(sql, "", inPutList, colsList, myForm);
		}
		return rs;
	}
	
	public HashMap<String, String> sxjyQueryOne3(String tableName,String [] colList,String pk,
			String pkValue,HashMap<String, String> map,String sql) {
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
	
	public String[] sxjyQueryOne2(String tableName,String [] colList,String pk,String pkValue) {
    //    通过主键查询单个数据用 获得HashMapString[]形式的通用方法
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
	
	
	public boolean getSffdy(String zgh) throws SQLException {
    //    判断是否是教师的方法
		HashMap<String, String> map =sxjyQueryOne("fdyxxb", new String[]{"zgh"}, "zgh",zgh);
		if(map.get("zgh").equalsIgnoreCase("")){//如果是辅导员，则设置辅导员标志
			return false;
		} else {
			return true;
		}
	}
	
	public List getZwList() throws SQLException {
	//    获得职务列表的方法
		String sql = "select zwdm,zwmc from zwb";
		List zwList = dao.getList(sql, new String[] {}, new String[] { "zwdm","zwmc" });
		return zwList;
	}

	public Object getBjxsList(String bj) {
		// 获得该班级学生列表
		String sql = "select xh,xm from view_xsjbxx where bjdm = ?";
		List zwList = dao.getList(sql, new String[] {bj}, new String[] { "xh","xm" });
		return zwList;
	}

	public List getSercicesbxskjcglxList() {
		// 获得申报学术科技成果类型列表
		String sql = "select sbxskjcglxdm,sbxskjcglxmc from fzjy_sbxskjcglxdmb";
		List sercicesbxskjcglxList = dao.getList(sql, new String[] {}, new String[] { "sbxskjcglxdm","sbxskjcglxmc" });
		return sercicesbxskjcglxList;
	}

	public List getCgjbList() {
		// 获得申报学术科技成果级别代码
		String sql = "select cgjbdm,cgjbmc from fzjy_cgjb";
		List cgjbList = dao.getList(sql, new String[] {}, new String[] { "cgjbdm","cgjbmc" });
		return cgjbList;
	}
	
	public List getStlbList() {
		// 辅导员考核试题类型列表
		String sql = "select stlbdm,stlbmc from szdw_fdykhstdlb";
		List stlblist = dao.getList(sql, new String[] {}, new String[] { "stlbdm","stlbmc" });
		return stlblist;
	}

	public List getKhqzList() {
		// 考核所属群组列表
		String sql = "select qzdm,qzmc from szdw_fdykhqzb";
		List stlblist = dao.getList(sql, new String[] {}, new String[] { "qzdm","qzmc" });
		return stlblist;
	}
	
	public String [] getKhqzs() throws SQLException {
		// 考核所属群组列表
		String sql = "select qzmc from szdw_fdykhqzb";
		String [] stlblist = dao.getArray(sql,new String []{},"qzmc");
		return stlblist;
	}

	public boolean updataFdybjjcszSave(String fdybjsz, String bzrbjsz, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return StandardOperation.update("fdybjcsszb", new String[]{"fdybjsz","bzrbjsz"}, new String[]{fdybjsz,bzrbjsz}, "1", "1", request);
	}

	public boolean delAllFpbj(String type) throws Exception {
		// TODO 自动生成方法存根
		String sql = "";;
		if(type.equalsIgnoreCase("fdy")){
			sql = "delete from fdybjb";
		}else{
			sql = "delete from bzrbbb";
		}
		return dao.runUpdate(sql, new String[]{});
	}

	/**
	 * 取出班级里班级以上学生干部人数
	 * @return
	 * @throws Exception
	 */
	public String getBjgbRsForBjdm(String bjdm) {
		String sql = "select count(0) num from sxjy_bjgbxxb a where bjdm = ? and exists (select 1 from (select bjgbdm from "+
				"sxjy_bjgbzlb b where not exists (select 1 from bjgbzljbb c where jbmc = '班级' and b.gbzljb = c.jbdm)) d"+
				" where a.BJGBDM = d.BJGBDM)";
		return dao.getOneRs(sql, new String []{bjdm}, "num");
	}

	/**
	 * 处理辅导员历史记录
	 * @return
	 * @throws Exception 
	 */
	public boolean putFdyLsjl(){
		String xn = Base.currXn;
		String sql = "delete from fdybjlsb where xn = ?";
		boolean updata = false;
		try {
			updata = dao.runUpdate(sql,new String[]{xn});
		
			if(updata){
				sql = "insert into fdybjlsb select zgh,bjdm,'"+xn+"'from fdybjb";
				updata = dao.runUpdate(sql,new String[]{});
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return updata;
	}
	
	/**
	 * 处理班主任历史记录
	 * @return
	 */
	public boolean putBzrLsjl() {
		String xn = Base.currXn;
		String sql = "delete from bzrbjlsb where xn = ?";
		boolean updata = false;
		try {
			updata = dao.runUpdate(sql,new String[]{xn});
		
			if(updata){
				sql = "insert into bzrbjlsb select zgh,bjdm,'"+xn+"'from bzrbbb";
				updata = dao.runUpdate(sql,new String[]{});
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return updata;
	}
	
	/**
	 * 根据学生获得辅导员或班主任姓名
	 * @return
	 */
	public String[] getFdyBzrXmForXh(String xh,String type) {
		StringBuilder sqlBuilder = new StringBuilder("select xm from ");
		if(type.equalsIgnoreCase("fdy")){
			sqlBuilder.append(" view_fdybbj ");
		}else{
			sqlBuilder.append(" view_bzrbbj ");
		}
		sqlBuilder.append("a where exists (select 1 from view_xsjbxx b  where xh ");
		sqlBuilder.append(" = ? and a.bjdm = b.bjdm)");
		try {
			return dao.getArray(sqlBuilder.toString(),new String[]{xh},"xm");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
