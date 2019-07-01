/*
 * 创建日期 2007-2-6  bat_zzj
 *
 */
package xgxt.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.basic.BasicDAO;
import com.zfsoft.database.imp.DatabaseTableManipulateImpl;
import com.zfsoft.database.imp.DatabaseTables;
import com.zfsoft.database.model.ColumnModel;
import com.zfsoft.database.model.TableModel;

import common.Globals;

import xgxt.DAO.DAO;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.dao.gyglDao;
import xgxt.xsh.XshDAO;

public class GetDropDownList {

	//private DBConnectionManager db = null;

//	private Connection conn = null;

	private PreparedStatement stmt = null;

	private ResultSet rs = null;

	private DAO dao = DAO.getInstance();

	@SuppressWarnings("unused")
	private void closeStmt() {
		// 通用模块，关闭所有事务
		try {
			if (rs != null) {
				this.rs.close();
			}
			if (stmt != null) {
				this.stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

//	public GetDropDownList(){
//		this.db = Configuration.connMgr;
//	}
//	private void closeConn() {
//		// 通用模块，关闭所有数据连接
//		try {
//			if (this.db != null) {
//				this.db.freeConnection(Configuration.ConnectionPoolName, conn);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			this.rs = null;
//			this.stmt = null;
//			this.conn = null;
//		}
//	}
//
//	public List<HashMap<String, String>> getList(String sql, String[] inputValue, String[] outputValue) {
//		// 返回List结构的结果集
//		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
//		try {
//			this.conn = db.getConnection("zfconnpool");
//			stmt = conn.prepareStatement(sql);
//			for (int i = 0; i < inputValue.length; i++) {
//				stmt.setString(i + 1, inputValue[i]);
//			}
//			rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				HashMap<String, String> map = new HashMap<String, String>();
//				for (int i = 0; i < outputValue.length; i++) {
//					map.put(outputValue[i], rs.getString(outputValue[i]));
//				}
//				arrayList.add(map);
//			}
//			closeStmt();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			arrayList = null;
//		} finally {
//			closeConn();
//		}
//		return arrayList;
//	}

	//获取专业班级列表
	public List getXyzybjList(String setpara) {
		String []xyzymc = setpara.split("!!-!!");
		String query = "where 1=1";
		if("%".equalsIgnoreCase(xyzymc[0])){
			query = query + "and 1=1"; 
		}else{
			query = query + "and xydm= '" + xyzymc[0] + "'"; 
		}
		if("%".equalsIgnoreCase(xyzymc[1])){
			query = query + "and 1=1";
		}else{
			query = query + "and zydm = '" + xyzymc[1] + "'";
		}
		String sql = "select distinct zydm dm,zymc mc from view_njxyzybj " + query;
		List xyzybjList = dao.getList(sql, new String[]{}, new String[] { "dm",
		"mc" });
		return xyzybjList;
	}

	//获取学生宿舍信息列表
	public List getStuDormList(String query){
		String [] setpara = query.split("!!-!!");
		String sql = "select * from view_xsgygl_xsssfbqkb where nj like ? and xh like ? and xy like ? and zy like ? and bj like ?";
		List dormList = dao.getList(sql, setpara, new String [] {"xh","xm","xymc","zymc","bjmc","ssbh","fdyxm","sz","cz","lz","cwh"});
		return dormList;
	}

///////////////////////////////////////////////////////SQ　增加/////////////////////////////////////////    

	
	/**
	 * @return 返回年级列表
	 */
	public List<HashMap<String, String>> getNjList(){
		String sql = "select 'nj' dm,'--请选择--' mc from view_njxyzybj where rownum=1 union all select * from (select distinct to_char(nj) dm,to_char(nj) mc from view_njxyzybj order by dm)";
		List<HashMap<String, String>> NjList = dao.getList(sql, new String[] {}, new String[] { "dm","mc" });
		return NjList;
	}
	
	/**
	 * @return 返回年份列表
	 */
	public List<HashMap<String, String>> getNfList(){
		String sql = "select 'nf' dm,'--请选择--' mc from view_njxyzybj where rownum=1 union all select * from (select distinct to_char(nj) dm,to_char(nj) mc from view_njxyzybj order by dm)";
		List<HashMap<String, String>> NjList = dao.getList(sql, new String[] {}, new String[] { "dm","mc" });
		return NjList;
	}

	
	/**
	 * @return 返回学年列表
	 */
	public List<HashMap<String, String>> getXnList() {
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String xn = null;
//		String nd = null;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "xn");
		map.put("mc", "--请选择--");
		aList.add(map);
		// int currentNd = (new Date()).getYear() + 1900;
		int currentNd = Integer.parseInt(DealString.getDateTime().substring(0, 4));
		for (int i = currentNd - 5; i < currentNd + 5; i++) {
			map = new HashMap<String, String>();
//			nd = String.valueOf(i);
			xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
			map.put("dm", xn);
			map.put("mc", xn);
			aList.add(map);
		}
		return aList;
	}

	
	/**
	 * @return 返回学期列表
	 */
	public List<HashMap<String, String>> getXqList() {
		String sql = "select 'xq' dm,'--请选择--' mc from xqdzb where rownum=1 union all select * from (select xqdm dm,xqmc mc from xqdzb order by dm)";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}

	/**
	 * @return 返回年度列表
	 */
	public List<HashMap<String, String>> getNdList() {
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String nd = null;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "nd");
		map.put("mc", "--请选择--");
		aList.add(map);
		// int currentNd = (new Date()).getYear() + 1900;
		int currentNd = Integer.parseInt(DealString.getDateTime().substring(0, 4));
		for (int i = currentNd - 5; i < currentNd + 5; i++) {
			map = new HashMap<String, String>();
			nd = String.valueOf(i);
			map.put("dm", nd);
			map.put("mc", nd);
			aList.add(map);
		}
		return aList;
	}

	/**
	 * @describe 另外一中获取 学院基础代码表 的函数
	 */
	public List<HashMap<String, String>> getXydmList() {
		String sql = "select 'xydm' dm,'--请选择--' mc from view_njxyzybj where rownum=1 union all select * from (select distinct xydm dm,xymc mc from view_njxyzybj order by xydm)";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 获得某个学生的贷款合同号
	 */
	public List<HashMap<String, String>> getHthList(String xh) {
		String sql = "select 'hth' dm,'--请选择--' mc from dual union all select hth dm, hth mc from zxdk_htxx where xh=?";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/**
	 * @return 返回学院列表
	 */
	public List<HashMap<String, String>> getXyList() {
		String sql = "select 'xy' dm,'--请选择--' mc from view_njxyzybj where rownum=1 union all select * from (select distinct xydm dm,xymc mc from view_njxyzybj order by xydm)";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * @param xydm
	 * @param userName
	 * @param fdyQx
	 * @return 返回学院列表
	 */
	public List<HashMap<String, String>> getXyListByDm(String xydm, String userName, String fdyQx) {
		String sql = "select 'xy' dm,'--请选择--' mc from view_njxyzybj where rownum=1 union all select * from (select distinct xydm dm,xymc mc from view_njxyzybj order by xydm)";
		if(StringUtils.isNotNull(xydm)){//学院代码非空
			sql = "select 'xy' dm,'--请选择--' mc from view_njxyzybj where rownum=1 union all select * from (select distinct xydm dm,xymc mc from view_njxyzybj where xydm='" + DealString.replaceImmitStr(xydm)+ "' order by xydm)";
		}
		if(StringUtils.isNotNull(fdyQx) && "true".equalsIgnoreCase(fdyQx)){//辅导员
			sql = "select 'xy' dm,'--请选择--' mc from view_njxyzybj where rownum=1 union all select * from (select distinct xydm dm,xymc mc from view_njxyzybj a where exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh='" + DealString.replaceImmitStr(userName) + "') order by xydm)";
		}
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * @param query　查询条件
	 * @return 返回专业列表
	 */
	public List<HashMap<String, String>> getZyList(String xydm,String nj,String userName,String isFdy,String isBzr) {
		String sql = "";
//		String xxdm = dao.getXxdm();
		String querry = " 1=1 ";		
		String query = "";		
		if(xydm != null&&!xydm.equalsIgnoreCase("")){
		    query += " and xydm='"+xydm+"' ";
		}	
		if(StringUtils.isNotNull(nj)){
		    query += " and nj='"+nj+"' ";
		}
		
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX) //武汉理工
//				|| xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)//长沙民政
//				|| xxdm.equalsIgnoreCase(Globals.XXDM_SYGYDX)//沈阳工业
//				){
			//武汉理工 长沙民政
			if("true".equalsIgnoreCase(isBzr)&&"true".equalsIgnoreCase(isFdy)){
				//是班主任加辅导员
				querry += " and ( exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "') or exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "'))"  ;
			}else if("true".equalsIgnoreCase(isBzr)){
				//是班主任
				querry += " and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
			}else if("true".equalsIgnoreCase(isFdy)){
				//是辅导员
				querry += " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
			}
//		}
		sql = "select 'zy' dm,'--请选择--' mc from view_njxyzybj where rownum='1' union all select * from (select distinct a.zydm dm,a.zymc mc from view_njxyzybj a"
			+ " where 1=1 "+query+" and" + querry + " order by dm)";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * @param query　查询条件
	 * @return 返回专业列表
	 */
	public List<HashMap<String, String>> getZyallList(String xydm,String nj,String userName,String isFdy,String isBzr) {
		String sql = "";
//		String xxdm = dao.getXxdm();
		String querry = " 1=1 ";		
		String query = "";		
		if(xydm != null&&!xydm.equalsIgnoreCase("")){
		    query += " and xydm='"+xydm+"' ";
		}	
		if(StringUtils.isNotNull(nj)){
		    query += " and nj='"+nj+"' ";
		}
		
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX) //武汉理工
//				|| xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)//长沙民政
//				|| xxdm.equalsIgnoreCase(Globals.XXDM_SYGYDX)//沈阳工业
//				){
			//武汉理工 长沙民政
			if("true".equalsIgnoreCase(isBzr)&&"true".equalsIgnoreCase(isFdy)){
				//是班主任加辅导员
				querry += " and ( exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "') or exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "'))"  ;
			}else if("true".equalsIgnoreCase(isBzr)){
				//是班主任
				querry += " and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
			}else if("true".equalsIgnoreCase(isFdy)){
				//是辅导员
				querry += " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
			}
//		}
		sql = "select 'zy' dm,'--请选择--' mc from view_njxyzybj_all where rownum='1' union all select * from (select distinct a.zydm dm,a.zymc mc from view_njxyzybj_all a"
			+ " where 1=1 "+query+" and" + querry + " order by dm)";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * @param query　查询条件
	 * @return 返回专业列表
	 */
	public List<HashMap<String, String>> getZyListnoxy(String query) {
		String sql = "";
		String querry = " 1=1 ";		
		sql = "select 'zy' dm,'--请选择--' mc from view_njxyzybj where rownum='1' union all select * from (select distinct a.zydm dm,a.zymc mc from view_njxyzybj a"
			+ " where a.xydm=? and" + querry + " order by dm)";
		System.out.println(sql);
		return dao.getList(sql, new String[] {query}, new String[] { "dm", "mc" });
	}
	
	/**
	 * @param query 查询条件
	 * @return 返回班级列表
	 */
	public List<HashMap<String, String>> getBjList(String query,String userName,String isFdy,String isBzr) {
		String [] setpara = query.split("!!-!!");
		String querry = " 1=1 ";
		
		String sql = "select 'bj' dm, '--请选择--' mc from dual" +
		" union all select * from (select distinct" +
		" bjdm dm,bjmc mc from view_njxyzybj a where xydm like ? and zydm" +
		"  like ? and nj like ? order by dm)";
		if("true".equalsIgnoreCase(isFdy)&&"true".equalsIgnoreCase(isBzr)){//是辅导员
			querry += " and (exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "') or " +
					"exists(select 1 from  bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "'))"  ;
			sql = "select 'bj' dm, '--请选择--' mc from dual union all select * from (select distinct bjdm dm,bjmc mc from view_njxyzybj a where xydm like ? and zydm like ? and nj like ? and " + querry + " order by bjdm)";
		}else if("true".equalsIgnoreCase(isFdy)){//是辅导员
			querry += " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
			sql = "select 'bj' dm, '--请选择--' mc from dual union all select * from (select distinct bjdm dm,bjmc mc from view_njxyzybj a where xydm like ? and zydm like ? and nj like ? and " + querry + " order by bjdm)";
		}else if("true".equalsIgnoreCase(isBzr)){//是班主任
			querry += " and exists(select 1 from  bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
			sql = "select 'bj' dm, '--请选择--' mc from dual union all select * from (select distinct bjdm dm,bjmc mc from view_njxyzybj a where xydm like ? and zydm like ? and nj like ? and " + querry + " order by bjdm)";
		}		
		return dao.getList(sql, setpara , new String[] { "dm", "mc" });
	}
	
	/**
	 * @param query 查询条件
	 * @return 返回班级列表
	 */
	public List<HashMap<String, String>> getBjallList(String query,String userName,String isFdy,String isBzr) {
		String [] setpara = query.split("!!-!!");
		String querry = " 1=1 ";
		
		String sql = "select 'bj' dm, '--请选择--' mc from dual" +
		" union all select * from (select distinct" +
		" bjdm dm,bjmc mc from view_njxyzybj_all a where xydm like ? and zydm" +
		"  like ? and nj like ? order by dm)";
		if("true".equalsIgnoreCase(isFdy)&&"true".equalsIgnoreCase(isBzr)){//是辅导员
			querry += " and (exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "') or " +
					"exists(select 1 from  bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "'))"  ;
			sql = "select 'bj' dm, '--请选择--' mc from dual union all select * from (select distinct bjdm dm,bjmc mc from view_njxyzybj_all a where xydm like ? and zydm like ? and nj like ? and " + querry + " order by bjdm)";
		}else if("true".equalsIgnoreCase(isFdy)){//是辅导员
			querry += " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
			sql = "select 'bj' dm, '--请选择--' mc from dual union all select * from (select distinct bjdm dm,bjmc mc from view_njxyzybj_all a where xydm like ? and zydm like ? and nj like ? and " + querry + " order by bjdm)";
		}else if("true".equalsIgnoreCase(isBzr)){//是班主任
			querry += " and exists(select 1 from  bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
			sql = "select 'bj' dm, '--请选择--' mc from dual union all select * from (select distinct bjdm dm,bjmc mc from view_njxyzybj_all a where xydm like ? and zydm like ? and nj like ? and " + querry + " order by bjdm)";
		}		
		return dao.getList(sql, setpara , new String[] { "dm", "mc" });
	}
	
	/**
	 * @param query 查询条件
	 * @return 返回班级列表
	 */
	public List<HashMap<String, String>> getBjListBySzbb(String query,String userName,String isFdy,String isBzr,String fplx) {
		String [] setpara = query.split("!!-!!");
		String querry = " ";
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from (select distinct ");
		sql.append(" a.bjdm dm,a.bjmc mc from ");
		if("fdy".equalsIgnoreCase(fplx)){
			sql.append(" view_njxyzybj_fdy a ");
		} else {
			sql.append(" view_njxyzybj_bzr a ");
		}
		sql.append(" left join XG_XTWH_SYBJGLB b on a.bjdm = b.bjdm ");
		sql.append(" where a.xydm like ? and a.zydm");
		sql.append(" like ? and a.nj like ? and b.sydm like ? ");
		sql.append(" and exists(select 1 from   (  ");
		sql.append(" select a.bjdm,case when b.bjdm is null then '未分配' else '已分配' end fpqk  ");
		sql.append(" from bks_bjdm a  ");
		if("fdy".equalsIgnoreCase(fplx)){
			sql.append(" left join (select distinct bjdm from fdybjb ) b on a.bjdm=b.bjdm  ");
		}else {
			sql.append(" left join (select distinct bjdm from bzrbbb ) b on a.bjdm=b.bjdm  ");
		}
		
		sql.append(" )c where a.bjdm=c.bjdm and fpqk like ?) ");
		
		
		if("true".equalsIgnoreCase(isFdy)&&"true".equalsIgnoreCase(isBzr)){//是辅导员
			querry += " and (exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "') or " +
					"exists(select 1 from  bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "'))"  ;
			sql.append(querry);
		}else if("true".equalsIgnoreCase(isFdy)){//是辅导员
			querry += " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
			sql.append(querry);
		}else if("true".equalsIgnoreCase(isBzr)){//是班主任
			querry += " and exists(select 1 from  bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
			sql.append(querry);
		}		
		sql.append(" order by dm) ");
		
		return dao.getList(sql.toString(), setpara , new String[] { "dm", "mc" });
	}
	
	/**
	 * @param query 查询条件
	 * @return 返回班级列表
	 */
	public List<HashMap<String, String>> getBj(String query,String type) {
		DAO dao = DAO.getInstance();
		String [] setpara = query.split("!!-!!");
		String querry = " and 1=1 ";
		if(type!=null && type.equalsIgnoreCase("zx")){
			querry += " and exists(select 1 from view_xsxxb b where b.bjdm=a.bjdm and (b.sfyby<>'是' or b.sfyby is null))";
		}
		if(type!=null && type.equalsIgnoreCase("by")){
			querry += " and exists(select 1 from view_xsxxb b where b.bjdm=a.bjdm and b.sfyby='是') ";
		}
		String sql = "select 'bj' dm, '--请选择--' mc from dual" +
		" union all select * from (select distinct" +
		" bjdm dm,bjmc mc from view_njxyzybj a where xydm like ? and zydm" +
		"  like ? and nj like ? " + querry + "order by dm)";
		return dao.getList(sql, setpara , new String[] { "dm", "mc" });
	}
	
	/**
	 * @return 获取用户组列表
	 */
	public List<HashMap<String, String>>getYhzList(){
		String sql = "select 'zdm' dm,'--请选择--' mc from dual" +
		" union all select * from (select zdm dm,zmc mc from yhzb order by zdm)";
		return dao.getList(sql, new String[]{}, new String[] { "dm", "mc" });
	}
	/**
	 * @param zdm 组代码
	 * @return 返回用户列表
	 */
	public List<HashMap<String, String>>getYhList(String zdm){
		String sql = "select 'yhm' dm,'--请选择--' mc from dual" +
		" union all select * from (select yhm dm,yhm||'||'||xm||'||'||bmmc mc from view_yhxx where zdm like ? order by yhm)";
		return dao.getList(sql, new String[]{zdm}, new String[] { "dm", "mc" });
	}
	
	
	/**
	 * @param zdm 组代码
	 * @return 返回用户姓名
	 */
	public String getXm(String zdm){
		String sql = "select xm from view_yhxx where yhm=?";
		String xm = dao.getOneRs(sql, new String[]{ zdm }, "xm");
		return xm;
	}
	
	public String getFs(String dj){
		String sql = "select nwwsdjfs fs from nwwsdjdmb where nwwsdjdm=?";
		String fs = dao.getOneRs(sql, new String[]{ dj }, "fs");
		return fs;
	}
	
	/**
	 * @param lddm 楼栋代码
	 * @return 返回寝室号列表
	 */
	public List<HashMap<String,String>>GetQshList(String lddm){
		String sql = "select qsh dm,qsh mc from (select distinct qsh from ssxxb where lddm = ? order by qsh) order by dm nulls first";
		return dao.getList(sql, new String []{lddm}, new String []{"dm","mc"});
	}
	
	/**
	 * @param ssbh 宿舍编号
	 * @return　返回床位号列表
	 */
	public List<HashMap<String,String>>GetCwhList(String ssbh){
		String sql = "select '' dm,'' mc from dual" +
		" union all select cwh dm,cwh mc from (select distinct cwh from cwxxb a where ssbh like ? order by cwh)order by dm nulls first";//and not exists (select * from xszsxxb b where a.ssbh=b.ssbh and a.cwh=b.cwh)
		return dao.getList(sql, new String []{ssbh}, new String []{"dm","mc"});
	}

	/**
	 * @param objValue
	 * @return 返回调整后岗位申报时间列表
	 */
	public List<HashMap<String,String>>GetOneList(String objValue){
		String sql = "select 'tzhgwsbsj' dm,'--请选择--' mc from dual" +
		" union all select gwsbsj dm,substr(gwsbsj,1,4)||'-'||substr(gwsbsj,5,2)||'-'||substr(gwsbsj,7,2)||' '||" +
		"substr(gwsbsj,9,2)||':'||substr(gwsbsj,11,2)||':'||substr(gwsbsj,13,2) mc from (select distinct gwsbsj from view_gwxx where gwdm like ? order by gwsbsj)";
		return dao.getList(sql, new String []{objValue}, new String []{"dm","mc"});
	}
	
	
	/**
	 * @param objValue 
	 * @return 返回调整前岗位申报时间列表
	 */
	public List<HashMap<String,String>>GetOneList1(String objValue){
		String sql = "select 'tzqgwsbsj' dm,'--请选择--' mc from dual" +
		" union all select gwsbsj dm,substr(gwsbsj,1,4)||'-'||substr(gwsbsj,5,2)||'-'||substr(gwsbsj,7,2)||' '||" +
		"substr(gwsbsj,9,2)||':'||substr(gwsbsj,11,2)||':'||substr(gwsbsj,13,2) mc from (select distinct gwsbsj from view_gwxx where gwdm like ? order by gwsbsj)";
		return dao.getList(sql, new String []{objValue}, new String []{"dm","mc"});
	}
	
	/**
	 * @param objValue
	 * @return 返回岗位信息
	 */
	public String [] GetGwInfo(String objValue){
		String [] setpara = objValue.split("!!-!!");
		String sql = "select xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,kcjqgzxsj from view_xsgwxx a where xh like ? and gwdm like ? and gwsbsj like ?";
		return dao.getOneRs(sql, setpara, new String[]{"xn","nd","xq","xqmc","kcjqgzxsj"});
	}
	
	/**
	 * @param xh 学号
	 * @return 返回学生楼层长详细信息
	 */
	public String [] GetStuLczInfo(String xh){
		String sql = "select * from (select a.xh ghhxh,a.xm ghhxm,a.xb ghhxb,a.bjmc ghhbjmc,b.lddm ghhlddm,b.qsh ghhqsh,b.qsdh ghhqsdh from view_xsjbxx a left join view_xszsxx b on a.xh=b.xh) where ghhxh=?";
		return dao.getOneRs(sql, new String[]{xh}, new String[]{"ghhxh","ghhxm","ghhxb","ghhbjmc","ghhlddm","ghhqsh","ghhqsdh"});
	}
	
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
	
	/**
	 * @param query
	 * @return 返回学生德育考评详细信息
	 */
	
	public List getDykpDeInfo(String query){
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		String [] setpara = query.split("!!-!!");
		String sql = "";
		sql = "select ssbh,'奖励' nr,jlnr qk,ryjf jjf from xsjlb where xh = ? and sj = ?";
		map1 = dao.getMap(sql, new String[]{setpara[0],setpara[1]}, new String [] {"ssbh","nr","qk","jjf"});
		arrayList.add(map1);
		sql = "select ssbh,'处罚' nr,cfnr qk,rykf jjf from xscfb where xh = ? and sj = ?";
		map2 = dao.getMap(sql, new String[]{setpara[0],setpara[2]}, new String [] {"ssbh","nr","qk","jjf"});
		arrayList.add(map2);
		return arrayList;
	}
	
	/**
	 * @param query
	 * @return 返回学生德育考评详细信息
	 */
	public List getDykpInfo(String query){
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		String [] setpara = query.split("!!-!!");
		String tableName = "view_xsgydykp";
		String []colList = {"jlsj","cfsj"};
		String sql = "select jlsj,cfsj from " + tableName + " where xh like ? and xn like ? and xq like ? order by jlsj,cfsj";
		map1 = dao.getMap(sql, new String[]{setpara[0],setpara[1],setpara[2]}, colList);
		arrayList.add(map1);
     	return arrayList;
	}
	
	/**
	 * @param query
	 * @return 返回用户信息
	 */
	public List getYhInfo(String query){
		String xxdm = StandardOperation.getXxdm();
		String [] setpara = query.split("!!-!!");
		String sql = "select a.yhm,(a.yhm||'/'||nvl(a.xm,'-')||'/'||b.zmc||'/'||nvl(c.bmmc,a.szbm)||'/'||nvl(d.dwmc,a.dwdm))||'/'||(case a.qx when '1' then '启用' when '0' then '不启用' else a.qx end) xm,b.zmc from yhb a,yhzb b,ZXBZ_XXBMDM c,bks_dwdmb d where not exists(select 1 from view_yhxx e where a.yhm=e.yhm and e.zdm='0001' and e.szbm='010000') and a.zdm=b.zdm and a.szbm=c.bmdm and a.dwdm=d.dwdm "
			+ "and a.yhm like ? and a.xm like ? and a.zdm like ? and a.szbm like ? and a.qx like ? and yhm<>'zf01' order by a.zdm,a.yhm";
		if(Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){
			sql = "select a.yhm,(a.yhm||'/'||nvl(a.xm,'-')||'/'||b.zmc||'/'||nvl(c.bmmc,a.szbm))||'/'||(case a.qx when '1' then '启用' when '0' then '不启用' else a.qx end) xm,b.zmc from yhb a,yhzb b,ZXBZ_XXBMDM c where not exists(select 1 from view_yhxx e where a.yhm=e.yhm and e.zdm='0001' and e.szbm='010000') and a.zdm=b.zdm and a.szbm=c.bmdm "
				+ "and a.yhm like ? and a.xm like ? and a.zdm like ? and a.szbm like ? and a.qx like ? order by a.zdm,a.yhm";
		}
		List userList = dao.getList(sql, setpara, new String []{"yhm","xm"});
		return userList;
	}
	
	/**
	 * @param query
	 * @return 返回用户权限信息
	 */
	public List getUserPower(String query){
		dao = DAO.getInstance();
		String [] setpara = query.split("!!-!!");
		String sql = "select (nvl(dxq,'0')||gnmkdm) gnmkdm,(rpad(gnmkdm,7,'_')||' | '||replace(replace(nvl(dxq,'0'),'0','只读'),'1','可写')||' | '||gnmkmc) gnmkmc from view_yhqx where yhm=? and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?) order by gnmkdm";
		List UserPowerList = dao.getList(sql, setpara, new String []{ "gnmkdm", "gnmkmc" });
		return UserPowerList; 
	}

	
	/**
	 * @param query
	 * @return 返回未分配给用户的权限信息
	 */
	public List getUnAllotPower(String query){
		
		//==============2010.10.25 edit by luojw==================================
		String [] setpara = query.split("!!-!!");
		
		// 分配用户
		String fpyh = setpara[1];
		// 被分配用户
		String bfpyh = setpara[0];
		
		StringBuilder sql = new StringBuilder();

		sql.append(" select gnmkdm,(case length(gnmkdm) when 3 ");
		sql.append("then gnmkdm||'____ | '||(select distinct gnmkmc from gnmkdmb c ");
		sql.append("where a.gnmkdm=c.gnmkdm ) when 5 then gnmkdm||'__ | '||");
		sql.append("(select distinct gnmkmc from gnmkdmb c where a.gnmkdm=c.gnmkdm ) ");
		sql.append("else gnmkdm||' | '||(select distinct gnmkmc from gnmkdmb c ");
		sql.append("where a.gnmkdm=c.gnmkdm )end)gnmkmc from view_yhqx a  where yhm=?");
		if (!fpyh.equalsIgnoreCase(bfpyh)) {
			sql.append("and not exists(select gnmkdm from view_yhqx b where yhm='"+bfpyh+"' and a.gnmkdm=b.gnmkdm)");
		}
		sql.append("order by gnmkdm");
		
		List UserPowerList = dao.getList(sql.toString(), new String[]{fpyh}, new String []{ "gnmkdm", "gnmkmc" });
		return UserPowerList; 
	}
	
	/**
	 * @param query
	 * @return 返回子系统中未分配给用户的权限信息
	 */
	public List getUnAllotPowerById(String query, String gnmkdm){
		String [] setpara = query.split("!!-!!");
		String sql = "select gnmkdm,(case length(gnmkdm) when 3 then gnmkdm||'____ | '||(select distinct gnmkmc from gnmkdmb c where a.gnmkdm=c.gnmkdm ) when 5 then gnmkdm||'__ | '||(select distinct gnmkmc from gnmkdmb c where a.gnmkdm=c.gnmkdm ) else gnmkdm||' | '||(select distinct gnmkmc from gnmkdmb c where a.gnmkdm=c.gnmkdm )end)gnmkmc from view_yhqx a  where yhm=? and not exists(select gnmkdm from view_yhqx b where yhm=? and a.gnmkdm=b.gnmkdm) and gnmkdm like '%'||?||'%' order by gnmkdm";
		List UserPowerList = dao.getList(sql, new String[]{setpara[1],setpara[0],gnmkdm}, new String []{ "gnmkdm", "gnmkmc" });
		return UserPowerList; 
	}	
	
	
	/**
	 * @param query
	 * @return 返回子系统列表
	 */
	public List getSubList(String query){
		String [] setpara = query.split("!!-!!");		
		String sql = "select * from (select '' gnmkdm,'----全部----' gnmkmc from dual) union all select * from (select gnmkdm, gnmkdm||' | '||gnmkmc gnmkmc from view_yhqx a where yhm=? and length(gnmkdm)=3 order by gnmkdm)";
		List UserPowerList = dao.getList(sql, new String[]{setpara[1]}, new String []{ "gnmkdm", "gnmkmc" });
		return UserPowerList; 
	}
	
	/**
	 * @param query
	 * @return 返回未分配给用户的子功能
	 */
	public List getGroupSubPower(String query,String zdm){
		String [] setpara = query.split("!!-!!");		
		String sql = "select gnmkdm,(case length(gnmkdm) when 3 then gnmkdm||'____ | '||(select distinct gnmkmc from gnmkdmb c where a.gnmkdm=c.gnmkdm ) when 5 then gnmkdm||'__ | '||(select distinct gnmkmc from gnmkdmb c where a.gnmkdm=c.gnmkdm ) else gnmkdm||' | '||(select distinct gnmkmc from gnmkdmb c where a.gnmkdm=c.gnmkdm )end)gnmkmc from view_yhqx a where yhm=? and gnmkdm like '%'||?||'%' and not exists(select 1 from view_yhzqx b where b.zdm=? and a.gnmkdm=b.gnmkdm and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?)) order by gnmkdm";
		List UserPowerList = dao.getList(sql, new String[]{setpara[1],setpara[0],zdm,setpara[1]}, new String []{ "gnmkdm", "gnmkmc" });
		return UserPowerList; 
	}
	
	/** 返回指定校区公寓楼栋列表 不带参数*/
	public  List GetLdList(){
		DAO dao             = DAO.getInstance();
		String sql  = " select '' dm,'--请选择--' mc from dual union all  select dm,mc from (select lddm dm,ldmc mc from sslddmb order by lddm)  ";		
		List ldList = dao.getList(sql, new String[] {}, new String[] {"dm", "mc" });
		return ldList;
	}	
	/**
	 * @param lddm 楼栋代码
	 * @return 楼层列表
	 */
	public List<HashMap<String,String>>GetLcList(String lddm,String userName){
		String querry = "";
		String sql = "";
		if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(Base.xxdm)&&gyglDao.gyFdyPd(userName,Base.currXn,Base.currXq)){	//金华职业技术学院	
			return gyglDao.getGyFdyLcList(userName, Base.currXn,Base.currXq, lddm);
        }else{
        	querry = Base.isNull(lddm)?" 1=1 ":" lddm='"+lddm+"' ";
        	sql = "select '' dm,'--请选择--' mc from dual" +
        	" union all select lc dm,lc mc from (select distinct cs lc from ssxxb where "+querry+" order by to_number(lc))  ";
        	return dao.getList(sql, new String []{}, new String []{"dm","mc"});
        }		
	}
	
	/**
	 * @param lddm 楼栋代码
	 * @param lch 楼层号
	 * @return 寝室号列表
	 */
	public List<HashMap<String,String>>GetQshList2(String lddm, String lch,String userName){
		String querry = "";
		if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(Base.xxdm)&&gyglDao.gyFdyPd(userName,Base.currXn,Base.currXq)){	//金华职业技术学院
			return gyglDao.getGyFdyQshList(userName, Base.currXn,Base.currXq, lddm,lch);
		}else{
			querry = (Base.isNull(lddm)||lddm.equalsIgnoreCase("null"))?" 1=1 ":" lddm='"+lddm+"' ";
			querry += (Base.isNull(lch)||lch.equalsIgnoreCase("lc")||lch.equalsIgnoreCase("lc"))?" and 1=1 ":" and cs='"+lch+"' ";
			String sql = "select '' dm,'--请选择--' mc from dual" +
			" union all select qsh dm ,qsh mc from (select distinct qsh from ssxxb where "+querry+" order by qsh) order by dm nulls first";
			return dao.getList(sql, new String []{}, new String []{"dm","mc"});
        }
	}
	/**
	 * @param lddm 楼栋代码
	 * @param lch 楼层号
	 * @return 保留寝室号列表
	 */
	public List<HashMap<String,String>>GetBlQshList(String lddm, String lch){
		String querry = "";
		querry = (Base.isNull(lddm)||lddm.equalsIgnoreCase("null"))?" 1=1 ":" lddm='"+lddm+"' ";
		querry += (Base.isNull(lch)||lch.equalsIgnoreCase("lc")||lch.equalsIgnoreCase("lc"))?" and 1=1 ":" and cs='"+lch+"' ";
		querry +=" and fpbj='保留'";
		String sql = "select '' dm,'--请选择--' mc from dual" +
		" union all select qsh dm ,qsh mc from (select distinct qsh from ssxxb where "+querry+" order by qsh) order by dm nulls first";
		return dao.getList(sql, new String []{}, new String []{"dm","mc"});
	}    
	public List<HashMap<String,String>>GetGyJcList(String jcType){
		String sql = "";
		List<HashMap<String,String>> jcList = null;
		if(!Base.isNull(jcType)&&jcType.equalsIgnoreCase("jl")){//奖励
			sql = " select ''dm,'--请选择--'mc from dual union (select jldm dm,jlmc mc from xsjldmb) order by dm desc ";
			jcList = dao.getList(sql,new String[]{},new String[]{"dm","mc"});
		}else if(!Base.isNull(jcType)&&jcType.equalsIgnoreCase("cf")){//惩罚
			sql = " select ''dm,'--请选择--'mc from dual union (select cfdm dm,cfmc mc from xscfdmb) order by dm desc ";
			jcList = dao.getList(sql,new String[]{},new String[]{"dm","mc"});
		}
		return jcList;
	}
	/**返回入住某床位学生信息*/
	public String[] GetRzXsXx(String lddm,String qsh,String cwh){
		String[] tsrv = null;
		String sql = " select xh,'学号:'|| xh||'  姓名:'||xm||'  性别：'||xb||'  班级：'||bjmc rzxsxx from view_xszsxx where lddm=? and qsh=? and cwh=? ";
	    tsrv =  dao.getOneRs(sql,new String[]{lddm,qsh,cwh},new String[]{"xh","rzxsxx"});
		return 	tsrv;			
	}
	/**返回某房间入住学生信息
	 * @throws SQLException */
	public String[] GetQsRzXsXx(String ssbh) throws SQLException{		
		String sql = "  select (rownum  ||'、   '||rzxsxx) rzxsxx from (select '学号:'|| a.xh||' &nbsp;&nbsp; 姓名:'||a.xm||'  &nbsp;&nbsp;性别：'||a.xb||'  " +
				"&nbsp;&nbsp;专业：'||a.zymc||'  &nbsp;&nbsp;班级：'||a.bjmc||'  &nbsp;&nbsp;辅导员：'||b.xm rzxsxx,xh from view_xszsxx a,view_fdybbj b where a.bjdm = b.bjdm(+) and ssbh=? order by xh)";
		return dao.getArray(sql, new String[]{ssbh},"rzxsxx");
	}
	public String[] GetQsRzXsXxHH(String ssbh,String xh) throws SQLException{		
		String sql = " select (rownum  ||'、   '||rzxsxx) rzxsxx from (select '学号:'|| xh||' &nbsp;&nbsp; 姓名:'||xm||'  &nbsp;&nbsp;性别：'||xb||'  &nbsp;&nbsp;班级：'||bjmc rzxsxx,xh from view_xszsxx where ssbh=? and xh <> ? order by xh )";
		return dao.getArray(sql, new String[]{ssbh,xh},"rzxsxx");
	}
	/**返回某房间入住学生信息
	 * @throws SQLException */
	public List<HashMap<String, String>> GetQsRzXsXxList(String ssbh) throws SQLException{		
		String sql = "select xh, xm from view_xszsxx where ssbh =? order by xh";
		List<HashMap<String, String>> xhList = dao.getList(sql, new String[]{ssbh},
				new String[]{"xh","xm"});
		return xhList;
	}
	
	/**返回某房间入住学生信息
	 * @throws SQLException */
	public List<String> GetQsRzXsXxMsg(String ssbh,int rs, int zs,String pk) throws SQLException {
		List<String> msgList = new ArrayList<String>();
		String[] map = new String[]{};
		if(pk!=null&&!"".equals(pk)){
			map=pk.split("!!SplitSignOne!!");
		}
		int l = 1;
		String[][] zr = new String[rs][zs];
		for (int i = 0; i < rs; i++) {
			String msg = map[i] + " 值日生周时 ：";
			int k = 1;
			k = l;
			for (int j = 0; j < zs / rs; j++) {
				zr[i][j] =  String.valueOf(k);
				k += rs;
				msg += zr[i][j] + "周,";
			}
			if (k > zs) {
				msg=msg.substring(0, msg.length()-1);
				msgList.add(msg);
				l++;
				continue;
			}
			zr[i][zs / rs + zs % rs] =  String.valueOf(k);
			msg += zr[i][zs / rs + zs % rs] + "周";
			msgList.add(msg);
			l++;
		}
		return msgList;
	}
	
	public List<HashMap<String, String>> getColumnList(String bbxmdm) {
		String sql = "select '$'||a.mc||'$' dm,a.mc from ("
				+ "select column_name dm ,comments mc from user_col_comments "
				+ "where table_name=upper('XSZZ_COMMON_NEW_ZZBBXSSQB') and comments is not null order by rowid"
				+ ") a where not exists(select 1 from xszz_common_new_bbzdyzd b where a.dm='ZDY'||b.zddm and b.xmdm<>?)";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { bbxmdm }, new String[] { "dm", "mc" });
		return list;
	}
	
	/**
	 * @param
	 * @return 返回宁波理工军训建制营级列表
	 */
	public List<HashMap<String, String>> getNblgJxYjbzList(String nj) {
		if ("".equalsIgnoreCase(nj) || null == nj){
			nj = "%";
		}
		return dao.getList("select 'yjdm' dm,'--请选择--' mc from dual union all select bzdm dm,bzmc mc from nblg_jxbzdmb where bzdj='1' and nj like ?", new String[] {nj}, new String[] { "dm", "mc" });
	}
	
	/**
	 * @param
	 * @return 返回宁波理工军训建制连级列表
	 */
	public List<HashMap<String, String>> getNblgJxLjbzList(String yjdm,String nj) {
		if ("".equalsIgnoreCase(nj) || null == nj){
			nj = "%";
		}
		if ("".equalsIgnoreCase(yjdm) || null == yjdm){
			yjdm = "%";
		}
		return dao.getList("select 'ljdm' dm,'--请选择--' mc from dual union all select bzdm dm,bzmc mc from nblg_jxbzdmb where sjdm like ? and nj like ? and bzdj='2'", new String[] {yjdm,nj}, new String[] { "dm", "mc" });
	}
	
	/**
	 * @param
	 * @return 返回宁波理工军训建制排级列表
	 */
	public List<HashMap<String, String>> getNblgJxPjbzList(String yjdm,String ljdm,String nj) {
		StringBuffer sb = new StringBuffer("select 'pjdm' dm,'--请选择--' mc from dual union all select bzdm dm,bzmc mc from nblg_jxbzdmb a where bzdj='3' ");
		if (!"".equalsIgnoreCase(nj) && null != nj){
			sb.append(" and nj='");
			sb.append(nj);
			sb.append("'");
		}
		if (!"".equalsIgnoreCase(yjdm) && null != yjdm){
			sb.append(" and exists (select 1 from nblg_jxbzdmb b where b.sjdm='");
			sb.append(yjdm);
			sb.append("' and b.bzdm=a.sjdm)");
		}
		if (!"".equalsIgnoreCase(ljdm) && null != ljdm){
			sb.append(" and sjdm='");
			sb.append(ljdm);
			sb.append("'");
		}
		return dao.getList(sb.toString(), new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * @param query 查询条件
	 * @return 返回宁波理工军训建制班级列表
	 */
	public List<HashMap<String, String>> getNblgJxBjList(String query,String yjdm,String ljdm,String pjdm,String userName) {
		boolean b = false;
		StringBuffer sb = new StringBuffer(" and exists (select 1 from nblg_jxbzdmb b where a.bjdm=b.bzdm");

		if (!"".equalsIgnoreCase(yjdm) && null != yjdm) {
			b = true;
			sb.append(" and exists (select 1 from nblg_jxbzdmb c where exists (select 1 from nblg_jxbzdmb d where d.sjdm='");
			sb.append(yjdm);
			sb.append("' and c.sjdm=d.bzdm) and b.sjdm=c.bzdm)");
		}
		if (!"".equalsIgnoreCase(ljdm) && null != ljdm) {
			b = true;
			sb.append(" and exists (select 1 from nblg_jxbzdmb c where c.sjdm='");
			sb.append(ljdm);
			sb.append("' and c.bzdm=b.sjdm)");
		}
		if (!"".equalsIgnoreCase(pjdm) && null != pjdm) {
			b = true;
			sb.append(" and b.sjdm='");
			sb.append(pjdm);
			sb.append("'");
		}
		sb.append(")");
		
		if (!b){
			sb = new StringBuffer("");
		}
		
		String [] setpara = query.split("!!-!!");
		String querry = " 1=1 ";
		String sql = "select 'bj' dm, '--请选择--' mc from dual" +
		" union all select * from (select distinct" +
		" bjdm dm,bjmc mc from view_njxyzybj a where xydm like ? and zydm" +
		"  like ? and nj like ? and " + querry + sb.toString() + " order by dm)";
		return dao.getList(sql, setpara , new String[] { "dm", "mc" });
	}
	
	/**
	 * @param
	 * @return 心理健康试题列表
	 */
	public List<HashMap<String, String>> getStList(String stlxdm,String stxsbj,String stnr) {
		DAO mydao = DAO.getInstance();
		String sql = "select stlsh || '/' || stlxdm || '/' || stxsbj dm,(case when length(stnr) > 12 then "+
         " substr(stnr, 0, 12)||'...' else stnr end) || '/' || stlxmc || '/' || stxsbj mc from view_st a "+
         "where not exists (select stlsh from yzstb where stlsh = a.stlsh) ";
		if(!Base.isNull(stlxdm)){
			sql += " and a.stlxdm='"+stlxdm+"' ";
		}
		if(!Base.isNull(stxsbj)){
			sql += " and a.stxsbj='"+DealString.toGBK(stxsbj)+"'";
		}
		if(!Base.isNull(stnr)){
			sql += " and a.stnr like '%"+DealString.toGBK(stnr)+"%'";
		}
		sql += "order by a.stlxdm";
		List<HashMap<String,String>> list = mydao.getList(sql, new String[]{}, new String[]{"dm","mc"});
		return list;
	}
	public String getStrInfo(String table,String outValue,String pk,String pkValue){
		DAO dao = DAO.getInstance();
		String sql = "select nvl("+outValue+",'0') outV from "+table+" where "+pk+"= ?";
		return dao.getOneRs(sql,new String[]{pkValue},"outV");	
	}

	public String getJxjdm(String jxjdm){
		DAO dao = DAO.getInstance();
		String sql = "select jxjlbmc from jxjdmb a,jxjlbdmb b where a.jxjlb=b.jxjlbdm and jxjdm='"+jxjdm+"'";
		return dao.getOneRs(sql,new String[]{},"jxjlbmc");	
	}
	public String getJxjje(String xmdm,String xmmc){
		DAO dao = DAO.getInstance();
		StringBuffer buff = new StringBuffer();
		buff.append(xmdm).append(xmmc);
		String sql = "select * from dzdxZxjXmjbb where jbmc=? and xmdm=?";
		return dao.getOneRs(sql,new String[]{xmmc,xmdm},"jbje");	
	}
	public String getZs(String sj){
		String zs = "";
		try {
			zs = String.valueOf(GetTime.getDqzs(sj));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zs;
	}
	
	public List<HashMap<String, String>> getZlb(String userType,String zlblb){
		String sql = "";
		
		if(!Base.isNull(zlblb)){
			sql = "select distinct gdzldm,zlbmc gdzlmc from stu_gdzlb where zlblb=?";
			return dao.getList(sql, new String[]{zlblb}, new String[]{"gdzldm","gdzlmc"});
		}else{
			String query2 = " where ";
			if("fdy".equals(userType)){
				query2 += " zlblb in (select dm from gdzllbb where czr='辅导员') ";
			}else if("xy".equals(userType)){
				query2 += " zlblb in (select dm from gdzllbb where czr='学院') ";
			}else if("xx".equals(userType) || "admin".equals(userType)){
				query2 += " zlblb in (select dm from gdzllbb where czr='学校') ";
			}else{
				query2 += " 1=2 ";
			}
			sql = "select distinct gdzldm,zlbmc gdzlmc from stu_gdzlb"+query2;
			return dao.getList(sql, new String[] {}, new String[] {
					"gdzldm", "gdzlmc" });
		}	
	}
	
	
	/**
	 * 根据用人单位代码取相关信息
	 * @param yrdwdm
	 * @return
	 */
	public HashMap<String, String> getYrdw(String yrdwdm) {
		String sql = "select * from view_dmk_yrdw a where dm=?" ;
		
		return dao.getMap(sql, new String[] {yrdwdm}, 
					new String[] {"dm","mc","dwxzdm","hyfldm","dwlxr","dwdh","dwszd","dwszdmc","dwyb"});
	}


	
	/**
	 * 获取学院、专业、班级
	 * @param flg
	 * @return
	 */
	public List<HashMap<String, String>> getXyZyBj(String flg,String userType,String userDep) {
		StringBuilder  sql = new StringBuilder();
		if ("xy".equals(flg)) {
			sql.append("select distinct xydm dm,xymc mc from view_njxyzybj");
		} else if ("zy".equals(flg)) {
			sql.append("select distinct zydm dm,zymc mc from view_njxyzybj");
		} else {
			sql.append("select distinct bjdm dm,bjmc mc from view_njxyzybj");
		}
		
		if ("xy".equals(userType)) {
			sql.append(" where xydm='");
			sql.append(userDep);
			sql.append("'");
		}
		return dao.getList(sql.toString(), new String[] {}, new String[] {"dm","mc"});
	}


	
	/**
	 * 
	 * 干部列表
	 * @param text
	 * @return
	 */
	public List<HashMap<String, String>> getGbList(String text) {
		XshDAO dao = new XshDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		
		
		if ("学生会学校无".equals(text)) {
			map.put("gblx", "003");
		} else {
			map.put("gblx", "002");
			map.put("isst", "yes");
			if (!Base.isNull(text)) {
				map.put("stmc", text);
			} else {
				map.put("stmc", "ty");
			}
		}
		
		
		return dao.getGbList(map);
	}


	/**
	 * 理赔材料
	 * @param text
	 * @return
	 */
	public List<HashMap<String, String>> getLpcl(String text) {
		String sql = "select dm,mc from bxxx_clxm where lpxmdm=?";
		return dao.getList(sql, new String[] {text}, new String[] {"dm","mc"});
	}
	
	/**
	 * 获取视图的字段列表
	 * @param tableName
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTableColForExp(String tableName){
		String xxdm = Base.xxdm;		
		BasicDAO dao = new BasicDAO();
		TableModel tModel = new TableModel();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		//判断dcb中是否有数据,有取导出表的字段，没有取表的全部字段
		if(dao.checkExists("dcb", "zdssb||xxdm", tableName+xxdm)){
			String sql = "select zdmc dm,zdsm mc from dcb where xxdm=? and zdssb=?";
			list = dao.getList(sql, new String[]{xxdm, tableName}, new String[]{"dm", "mc"});
		}else if(dao.checkExists("dcb", "zdssb||xxdm", tableName+"public")){
			String sql = "select zdmc dm,zdsm mc from dcb where xxdm=? and zdssb=?";
			String xxdmStr = "public";			
			list = dao.getList(sql, new String[]{xxdmStr, tableName}, new String[]{"dm", "mc"});
		}else{
			tModel = DatabaseTables.getTable(tableName);
			if(tModel == null){
				DatabaseTableManipulateImpl dtmi = new DatabaseTableManipulateImpl(); 
				dtmi.getTableColumns(dao,tableName);
				tModel = DatabaseTables.getTable(tableName);
			}
			List<ColumnModel> cList = tModel.getColumns();			
			HashMap<String, String> map = null;
			
			for(ColumnModel cModel : cList){
				map = new HashMap<String, String>();
				map.put("dm", cModel.getName());
				map.put("mc", cModel.getComments());
				list.add(map);
			}
		}
		
		//如果是模块放入家庭信息导出
//		if("new".equalsIgnoreCase(Base.initProperties.get("edition"))){
//			String sql = "select zdmc dm,zdsm mc from dcb where xxdm='?' and zdssb='?'";
//			list.addAll(dao.getList(sql, new String[]{"public_xsfzxxb", "xsfzxxb"}, new String[]{"dm", "mc"}));
//			
//		}
		return list;
	}
	
	/**
	 * 获取政治面貌列表
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getZZmmList(){
		DAO dao = DAO.getInstance();
		String sql = "select distinct zzmmdm dm, zzmmmc mc from zzmmdmb order by to_number(zzmmdm)";
		return dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});
	}

	//=====================金华职业 begin========================================
	/**
	 * 金华职业
	 * @param query
	 * @author yeyipin
	 * @return 返回用户权限信息
	 */
	public List getUserPowerForJhzy(String query){
		String [] setpara = query.split("!!-!!");
		String sql = "select (nvl(b.dxq,'0')||a.gnmkdm) gnmkdm,(rpad(a.gnmkdm,7,'_')||' | '||replace(replace(nvl(a.dxq,'0'),'0','只读'),'1','可写')||' | '||b.gnmkmc) gnmkmc from yhqxb a left join gnmkdmb b on a.gnmkdm = b.gnmkdm where yhm=? and substr(a.gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?) order by a.gnmkdm";
		List UserPowerList = dao.getList(sql, setpara, new String []{ "gnmkdm", "gnmkmc" });
		return UserPowerList; 
	}
	/**
	 * 金华职业
	 * @param query
	 * @author yeyipin
	 * @return 返回子系统列表
	 */
	public List getSubListForJhzy(String query){
		String [] setpara = query.split("!!-!!");		
		String sql = "select * from (select '' gnmkdm,'----全部----' gnmkmc from dual) union all select * from (select gnmkdm, gnmkdm||' | '||gnmkmc gnmkmc from view_yhqx a where yhm=? and length(gnmkdm)=3 order by gnmkdm)";
		List UserPowerList = dao.getList(sql, new String[]{setpara[1]}, new String []{ "gnmkdm", "gnmkmc" });
		return UserPowerList; 
	}
	/**
	 * 金华职业
	 * @param query
	 * @author yeyipin
	 * @return 返回未分配给用户的权限信息
	 */
	public List getUnAllotPowerForJhzy(String query){
		
		//==============2010.10.25 edit by luojw==================================
		String [] setpara = query.split("!!-!!");
		
		// 分配用户
		String fpyh = setpara[1];
		// 被分配用户
		String bfpyh = setpara[0];
		
		StringBuilder sql = new StringBuilder();

		sql.append(" select gnmkdm,(case length(gnmkdm) when 3 ");
		sql.append("then gnmkdm||'____ | '||(select distinct gnmkmc from gnmkdmb c ");
		sql.append("where a.gnmkdm=c.gnmkdm ) when 5 then gnmkdm||'__ | '||");
		sql.append("(select distinct gnmkmc from gnmkdmb c where a.gnmkdm=c.gnmkdm ) ");
		sql.append("else gnmkdm||' | '||(select distinct gnmkmc from gnmkdmb c ");
		sql.append("where a.gnmkdm=c.gnmkdm )end)gnmkmc from view_yhqx a  where yhm=?");
		if (!fpyh.equalsIgnoreCase(bfpyh)) {
			sql.append("and not exists(select gnmkdm from yhqxb b where yhm='"+bfpyh+"' and a.gnmkdm=b.gnmkdm)");
		}
		sql.append("order by gnmkdm");
		
		List UserPowerList = dao.getList(sql.toString(), new String[]{fpyh}, new String []{ "gnmkdm", "gnmkmc" });
		return UserPowerList; 
	}
	/**
	 * @param query
	 * @return 返回辅导员信息
	 */
	public List getfdyInfo(String query){
		String [] setpara = query.split("!!-!!");
		String sql = " select a.yhm,(a.yhm||'/'||nvl(a.xm,'-')||'/'||nvl(d.zmc,'-')) xm " +
		"from (select a.zgh yhm,a.xm,c.yhzdm from(select a.zgh,b.xm from (select distinct zgh from fdybjb) a " +
		"left join fdyxxb b on a.zgh = b.zgh) a left join xg_xtwh_yhsszb c on (a.zgh||'_fdy') = c.yhm) a  " +
		"left join yhzb d on a.yhzdm = d.zdm "+query+" order by zdm,yhm";
		List userList = dao.getList(sql, new String[]{}, new String []{"yhm","xm"});
		return userList;
	}
	/**
	 * @param query
	 * @return 返回班主任信息
	 */
	public List getbzrInfo(String query){
		String [] setpara = query.split("!!-!!");
		String sql = " select a.yhm,(a.yhm||'/'||nvl(a.xm,'-')||'/'||nvl(d.zmc,'-')) xm " +
		"from (select a.zgh yhm,a.xm,c.yhzdm from(select a.zgh,b.xm from (select distinct zgh from bzrbbb) a " +
		"left join fdyxxb b on a.zgh = b.zgh) a left join xg_xtwh_yhsszb c on (a.zgh||'_bzr') = c.yhm) a  " +
		"left join yhzb d on a.yhzdm = d.zdm "+query+" order by zdm,yhm";
		List userList = dao.getList(sql, new String[]{}, new String []{"yhm","xm"});
		return userList;
	}
	
	/**
	 * @param query　查询条件
	 * @return 返回部门代码，名称列表
	 */
	public List<HashMap<String, String>> getBmListByLb(String bmlb) {
		// 获得部门代码，名称列表
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select ' ' bmdm, '' bmqc, ' ' bmjc, ' ' bmmc from dual) union all select * from ");
		sql.append("(select bmdm,bmpy||bmmc bmqc,bmpy||bmsx bmjc, bmmc from (");
		sql.append("select bmdm,bmmc,");
		sql.append("case when length(bmmc)>6 then substr(bmmc,0,6)||'..' ");
		sql.append("else bmmc end bmsx, ");
		sql.append("F_PINYIN(substr(bmmc,0,1)) bmpy  ");
		sql.append("from zxbz_xxbmdm ");
		if(null!=bmlb&&!"".equals(bmlb)){
			sql.append("where bmjb = '1' and bmlb='"+bmlb+"'");
		}else{
			sql.append("where bmjb = '1'");
		}
		
		sql.append(") order by bmmc)");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {"bmdm", "bmmc", "bmqc", "bmjc"});
	}
	
	//=====================金华职业 end==========================================
	
	/**
	 * @description	：西安科技大学学年列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-28 下午07:33:13
	 * @return
	 */
	public List<HashMap<String, String>> getXnListForXakj() {
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String xn = null;
		HashMap<String, String> map = new HashMap<String, String>();
		int currentNd = Integer.parseInt(DealString.getDateTime().substring(0, 4));
		for (int i = currentNd - 5; i < currentNd + 5; i++) {
			map = new HashMap<String, String>();
			xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
			map.put("dm", xn);
			map.put("mc", xn);
			aList.add(map);
		}
		return aList;
	}
}

