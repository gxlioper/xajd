/*
 * 创建日期 2007-11-21 zhoumi
 *
 */
package xgxt.DAO;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.sql.DataSource;

import common.Globals;

import xgxt.DAO.DBPool;
import xgxt.action.Base;
import xgxt.utils.CustomException;

public class XszzDao {

	private  DataSource db = null;

	private  Connection conn = null;

	private  PreparedStatement stmt = null;

	private  Statement stat = null;

	private  CallableStatement cstmt = null;

	private  ResultSet rs = null;

	private String ip = null;

	private String mac = null;

	private int rsCount = 0;

	private int pageCount = 0;

	private int pageSize = 0;

	private int currentPage = 0;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRsCount() {
		return rsCount;
	}

	public void setRsCount(int rsCount) {
		this.rsCount = rsCount;
	}

	public XszzDao() {
		// 构造函数初始化数据连接。;
		this.db = DBPool.getPool(); ////////Configuration.connMgr;
	}

	public XszzDao(String Ip) {
		// 构造函数，带有一个参数IP，初始化IP和MAC。供写入日志时使用。
		this();
		this.ip = Ip;
		this.mac = getMacAddressIP(ip);
	}

	public String getMacAddressIP(String remotePcIP) {
		// 通过IP获取MAC
		String str = "";
		String macAddress = "";
		try {
			Process pp = Runtime.getRuntime().exec("nbtstat -A " + remotePcIP);
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(
								str.indexOf("MAC Address") + 14, str.length());
					}
				}
			}
		} catch (IOException ex) {

		} finally {

		}
		return macAddress;
	}

	public void closeStmt() {
		// 通用模块，关闭所有事务
		try {
			if (rs != null) {
				this.rs.close();
			}
			if (stmt != null) {
				this.stmt.close();
			}
			if (stat != null) {
				this.stat.close();
			}
			if (cstmt != null) {
				this.cstmt.close();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeConn() {
		// 通用模块，关闭所有数据连接
		try {
			if (this.db != null) {
				this.conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.rs = null;
			this.stmt = null;
			this.stat = null;
			this.cstmt = null;
		}
	}


	/**
	 * 关掉连接
	 */
	public void closeConnection(){
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	/**
	 * 用于关闭数据库连接,必须保证已经使用完了ResultSet和Statement,
	 * 否则将出现使用closed ResultSet和closed Statement异常
	 * @param conn
	 */
	public void closeConnection(Connection conn){
		try{
			conn.close();
		} catch(Exception e){
			e.printStackTrace();
		} 
	}
	
	// 获取婚姻状况列表
	public synchronized List<HashMap<String, String>> getXszzHyzkList() throws SQLException {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select hyzk from zzsf_hyzkb";
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("hyzk", rs.getString("hyzk"));
				list.add(temmap);
			}

		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			rs.close();
			stmt.close();
			conn.close();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}
		return list;
	}
	
//	 获取学历列表
	public synchronized List<HashMap<String, String>> getXszzZgxlList() throws SQLException {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select zgxl from zzsf_zgxlb";
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("zgxl", rs.getString("zgxl"));
				list.add(temmap);
			}

		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			rs.close();
			stmt.close();
			conn.close();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}
		return list;
	}

	public synchronized List<HashMap<String, String>> getList(String sql,
			String[] inputValue, String[] outputValue) {
		// 返回List结构的结果集
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < outputValue.length; i++) {
					map.put(outputValue[i], rs.getString(outputValue[i]));
				}
				arrayList.add(map);
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到广东女子职业技术学院学生奖学金项目列表
	 */
	public synchronized List<HashMap<String, String>> getGdJxjList() throws Exception{
		String sql = "select jxjdm||'!!splitOne!!'||jlmc||'!!splitOne!!'||bjdw||'!!splitOne!!'||jlze||'!!splitOne!!'||jxjdj||'!!splitOne!!'||jlgrje jxjdm1,jlmc from GdNZZY_xszz_jzjdmb order by jxjdm";
		return this.getList(sql, new String[] {}, new String[] { "jxjdm1",
				"jlmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到广东女子职业技术学院学生助学金项目列表
	 */
	public synchronized List<HashMap<String, String>> getGdZxjList() throws Exception{
		String sql = "select zxjdm||'!!splitOne!!'||zzmc||'!!splitOne!!'||zzdw||'!!splitOne!!'||zzze||'!!splitOne!!'||zzgrje zxjdm1,zzmc from GdNZZY_xszz_zxjdmb order by zxjdm";
		return this.getList(sql, new String[] {}, new String[] { "zxjdm1",
				"zzmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到广东女子职业技术学院学生困难补助项目列表
	 */
	public synchronized List<HashMap<String, String>> getGdKnbzList() throws Exception{
		String sql = "select knbzdm||'!!splitOne!!'||knbzmc knbzdm1,knbzmc from GdNZZY_xszz_knbzdmb order by knbzdm";
		return this.getList(sql, new String[] {}, new String[] { "knbzdm1",
				"knbzmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到广东女子职业技术学院学生奖学金数据
	 */
	public synchronized ArrayList<String[]> getGdbzzyXsjxj(String xh, String jxjdm){
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String sql = "select nd,jlmc,ffje from view_gdnzzyjsxy_jxjsqxx where jxjdm='"+jxjdm+"' and xh='"+xh+"' and xxsh='通过' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] st = new String[]{rs.getString("nd"),rs.getString("jlmc"),rs.getString("ffje")};
				arrayList.add(st);
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到广东女子职业技术学院学生助学金数据
	 */
	public synchronized ArrayList<String[]> getGdbzzyXszxj(String xh, String zxjdm){
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String sql = "select nd,zzmc,ffje from view_gdnzzyjsxy_zxjsqxx where zxjdm='"+zxjdm+"' and xh='"+xh+"' and xxsh='通过' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] st = new String[]{rs.getString("nd"),rs.getString("zzmc"),rs.getString("ffje")};
				arrayList.add(st);
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到广东女子职业技术学院学生资助记录
	 */
	public synchronized ArrayList<String[]> getGdnzzyXszzJl(String xh){
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String sql = "select zzdl,nd,zzxldm,xh from gdnzzy_xszz_zzjlb where xh='"+xh+"'";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] st = new String[] { rs.getString("zzdl"),
						rs.getString("nd"), rs.getString("zzxldm"),
						rs.getString("xh") };
				arrayList.add(st);
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到广东女子职业技术学院学生困难补助数据
	 */
	public synchronized ArrayList<String[]> getGdbzzyXsknbz(String xh, String knbzdm){
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String sql = "select nd,knbzmc,ffje from view_gdnzzyjsxy_knbzsqxx where knbzdm='"+knbzdm+"' and xh='"+xh+"' and xxsh='通过' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] st = new String[]{rs.getString("nd"),rs.getString("knbzmc"),rs.getString("ffje")};
				arrayList.add(st);
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到广东女子职业技术学院学生助学贷款数据
	 */
	public synchronized ArrayList<String[]> getGdbzzyXszxdk(String xh, String hth){
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String sql = "select nd,hth,dkje from view_gdnzzy_zxdkxx where hth='"+hth+"' and xh='"+xh+"' and xxsh='通过' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] st = new String[]{rs.getString("nd"),rs.getString("hth"),rs.getString("dkje")};
				arrayList.add(st);
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到广东女子职业技术学院学生所受奖助学金，困难补助和助学贷款的记录
	 */
	public synchronized ArrayList<String> getXszzJL(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String[]> list = this.getGdnzzyXszzJl(xh);
		for (Iterator<String[]> it = list.iterator(); it.hasNext();) {
			String[] temp = it.next();
			if ("奖学金".equalsIgnoreCase(temp[0])) {
				ArrayList<String[]> tList = this.getGdbzzyXsjxj(xh, temp[2]);
				for (Iterator<String[]> its = tList.iterator(); its.hasNext();) {
					String[] st = its.next();
					String jl = st[0] + "年度  曾获得奖学金:" + st[1] + "，金额为:" + st[2]
							+ "元";
					arrayList.add(jl);
				}
			}
			if ("助学金".equalsIgnoreCase(temp[0])) {
				ArrayList<String[]> tList = this.getGdbzzyXszxj(xh, temp[2]);
				for (Iterator<String[]> its = tList.iterator(); its.hasNext();) {
					String[] st = its.next();
					String jl = st[0] + "年度  曾获得助学金:" + st[1] + "，金额为:" + st[2]
							+ "元";
					arrayList.add(jl);
				}
			}
			if ("困难补助".equalsIgnoreCase(temp[0])) {
				ArrayList<String[]> tList = this.getGdbzzyXsknbz(xh, temp[2]);
				for (Iterator<String[]> its = tList.iterator(); its.hasNext();) {
					String[] st = its.next();
					String jl = st[0] + "年度  曾获困难补助:" + st[1] + "，金额为:" + st[2]
							+ "元";
					arrayList.add(jl);
				}
			}
//			if ("助学贷款".equalsIgnoreCase(temp[0])) {
//				ArrayList<String[]> tList = this.getGdbzzyXsknbz(xh, temp[2]);
//				for (Iterator<String[]> its = tList.iterator(); its.hasNext();) {
//					String[] st = its.next();
//					String jl = st[0] + "年度  曾获助学贷款:" + st[2] + "元，合同号为:"
//							+ st[1];
//					arrayList.add(jl);
//				}
//			}
		}
		return arrayList;
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到资助汇总项目列表
	 */
	public synchronized List<HashMap<String, String>> getZzxmList(String xxdm)
			throws SQLException{
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		StringBuffer sql = new StringBuffer(
				"select hzxmb,hzxmmc from xszz_zzhzxmb where xxdm in ('null'");
		try {
			conn = db.getConnection();
			sql.append((xxdm == null || xxdm.trim().equals("")) ? ") "
					: ",'" + xxdm + "') ");
			sql.append(Globals.NotJudgeWhichSchoolQuery("hzxmb", xxdm));
			
			//==========================屏蔽助学贷款--sjf============================
			sql.append(" and hzxmmc!='助学贷款'");
			//======================================================================
			
			sql.append(" order by hzxmb");
			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("hzxmb", rs.getString("hzxmb"));
				temmap.put("hzxmmc", rs.getString("hzxmmc"));
				list.add(temmap);
			}

		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql.toString());
			rs.close();
			stmt.close();
			conn.close();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}
		return list;
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到资助汇总项目列表
	 */
	public synchronized List<HashMap<String, String>> getNewZzxmList(String xxdm)
			throws SQLException{
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		StringBuffer sql = new StringBuffer(
				"select hzxmb,hzxmmc from xszz_new_zzhzxmb where xxdm in ('null'");
		try {
			conn = db.getConnection();
			sql.append((xxdm == null || xxdm.trim().equals("")) ? ") "
					: ",'" + xxdm + "') ");
			sql.append(Globals.NotJudgeWhichSchoolQuery("hzxmb", xxdm));
			sql.append(" order by hzxmb");
			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("hzxmb", rs.getString("hzxmb"));
				temmap.put("hzxmmc", rs.getString("hzxmmc"));
				list.add(temmap);
			}

		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql.toString());
			rs.close();
			stmt.close();
			conn.close();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}
		return list;
	}
	
	/**
	 * @author ZhouMi
	 * @describe 
	 */
	public synchronized ArrayList<String> getArrayList(String sql, String out){
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				arrayList.add(rs.getString(out));
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	学生资助--通过学校代码得到学生详细信息中学生资助需要显示的资助项目表名
	public ArrayList<String> getStuDataByXxdm(String xxdm){
		ArrayList<String> tabName = new ArrayList<String>();
		DAO dao = DAO.getInstance();
		String zzbb = dao.getOneRs("select zzbb from xtszb", new String[]{}, "zzbb");
		String query = " and 1=1 ";
		String sql = "";
		if ("1".equalsIgnoreCase(zzbb)) {
			query += Globals.NotJudgeWhichSchoolQuery("cxbm", xxdm);
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				sql = "select cxbm from cxb where ssmk='assis' and xxmc='"
						+ xxdm + "' group by cxbm";
			} else if (Globals.JudgeWhichSchool(xxdm)) {
				sql = "(select cxbm from cxb where ssmk='assis' and xxmc is null "
						+ query
						+ " group by cxbm) union (select cxbm from cxb where ssmk='assis' and xxmc='"
						+ xxdm + "' group by cxbm)";
			} else {
				sql = "select cxbm from cxb where ssmk='assis' and xxmc is null group by cxbm";
			}
		} else if ("2".equalsIgnoreCase(zzbb)){
			query += Globals.NotJudgeWhichSchoolQuery("cxbm",xxdm);
			if (Globals.NewJudgeWhichSchool(xxdm)) {
				sql = "(select cxbm from cxb where ssmk='assisNew' and xxmc is null "
						+ query
						+ " group by cxbm) union (select cxbm from cxb where ssmk='assisNew' and xxmc='"
						+ xxdm + "' group by cxbm)";
			} else {
				sql = "select cxbm from cxb where ssmk='assisNew' and xxmc is null group by cxbm";
			}
		}
		tabName = getArrayList(sql, "cxbm");
		if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDXHXXY)){
			tabName.add("VIEW_ZGMSXY_GJZXDK");
		}
		return tabName;
	}
	
//	学生资助--通过学生详细信息中学生资助的资助项目表名得到需要显示的字段。
	public ArrayList<String> getStuDataPartByXxdm(String tabName){
		ArrayList<String> colName = new ArrayList<String>();
		String sql = "select lower(zdmc) zdmc from xszz_xsjbxx where xmmc=upper('"+tabName+"') order by xssx";
		colName = getArrayList(sql, "zdmc");
		return colName;
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到上海工程技术大学资助项目金额列表
	 */
	public synchronized List<HashMap<String, String>> getShgcZzjeList(String bbdm) throws Exception{
		String sql = "select zzje from xszz_shgc_zzjeb where bbdm=? order by zzje";
		return this.getList(sql, new String[] {bbdm}, new String[] { "zzje" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到学生资助资助项目金额列表
	 */
	public synchronized List<HashMap<String, String>> getXszzZzjeList(String xmdm) throws Exception{
		String sql = "select zzje from view_xszz_common_new_zzjeb where xmdm=? order by zzje";
		return this.getList(sql, new String[] {xmdm}, new String[] { "zzje" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到上海工程技术大学资助项目列表
	 */
	public synchronized List<HashMap<String, String>> getShgcZzxmList() throws Exception{
		String sql = "select bbdm,bbmc from xszz_shgc_zzbbdmb order by bbdm";
		return this.getList(sql, new String[] {}, new String[] { "bbdm",
				"bbmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到学生资助资助项目列表
	 */
	public synchronized List<HashMap<String, String>> getXszzZzxmList() throws Exception{
		String sql = "select xmdm,xmmc from XSZZ_COMMON_NEW_ZZXMDMB order by xmdm";
		return this.getList(sql, new String[] {}, new String[] { "xmdm",
				"xmmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到上海工程技术大学助学贷款项目列表
	 */
	public synchronized List<HashMap<String, String>> getShgcZxdkxmList() throws Exception{
		String sql = "select '困难生' xmmc from dual union select '国家助学贷款' xmmc from dual union select '还款协议' xmmc from dual";
		return this.getList(sql, new String[] {}, new String[] { "xmmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到学生资助助学贷款项目列表
	 */
	public synchronized List<HashMap<String, String>> getXszzZxdkxmList() throws Exception{
		String xxdm = Base.xxdm;
		String sql = "select '困难生' xmmc from dual union select '国家助学贷款' xmmc from dual";
		
		if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {
			sql += " union select '国家励志奖学金' xmmc from dual union select '国家奖学金' xmmc from dual";
		}
		return this.getList(sql, new String[] {}, new String[] { "xmmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到中国地质大学困难生项目列表
	 */
	public synchronized List<HashMap<String, String>> getZgdzdxKnsxmList() throws Exception{
		String sql = "select '家庭情况调查' xmmc from dual union select '困难生申请时间' xmmc from dual union select '困难生审核时间' xmmc from dual";
		return this.getList(sql, new String[] {}, new String[] { "xmmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到中国地质大学助学贷款项目列表
	 */
	public synchronized List<HashMap<String, String>> getZgdzdxZxdkxmList() throws Exception{
		String sql = "select '助学贷款申请' xmmc from dual union select '资料确认书' xmmc from dual union select '还款协议' xmmc from dual union select '展期协议' xmmc from dual union select '展期后还款协议' xmmc from dual";
		return this.getList(sql, new String[] {}, new String[] { "xmmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到中国美术学院助学贷款项目列表
	 */
	public synchronized List<HashMap<String, String>> getZgmsxyZxdkxmList() throws Exception{
		String sql = "select '助学贷款申请' xmmc from dual union select '毕业生信息采集' xmmc from dual";
		return this.getList(sql, new String[] {}, new String[] { "xmmc" });
	}
	
	public synchronized String[] getViewComm(String viewName, String notColName) throws SQLException {
		// 得到视图的字段注释语句
		DAO dao = DAO.getInstance();
		String[] arr = null;
		String sql = "select 'comment on column '||table_name||'.'||column_name||' is '||chr(39)||comments||chr(39) com "
				+ "from user_col_comments where table_name=upper('"
				+ viewName
				+ "')  and COLUMN_NAME<>upper('"
				+ notColName + "')";
		arr = dao.getArray(sql, new String[] {}, "com");
		return arr;
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到上海工程技术大学资助自定义字段详细信息列表
	 */
	public synchronized String getShgcZdyzdXxxxList(String bbdm) throws Exception{
		DAO dao = DAO.getInstance(); 
		StringBuffer sb = new StringBuffer("");
		String sql = "select bbdm||'!!OneSpile!!'||bbmc||'!!OneSpile!!'||zddm||'!!OneSpile!!'||zdmc||'!!OneSpile!!'||zdlx||'!!OneSpile!!'||zddx||'!!OneSpile!!'||bxwsz str from view_xszz_shgc_bbzdyzd where bbdm=?";
		String[] st = dao.getRs(sql, new String[]{bbdm}, "str");
		for(int i = 0; i < st.length; i++){
			sb.append(st[i]);
			sb.append("!!TwoSpile!!");
		}
		return sb.toString();
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到学生资助资助自定义字段详细信息列表
	 */
	public synchronized String getXszzZdyzdXxxxList(String xmdm) throws Exception{
		DAO dao = DAO.getInstance(); 
		StringBuffer sb = new StringBuffer("");
		String sql = "select xmdm||'!!OneSpile!!'||xmmc||'!!OneSpile!!'||zddm||'!!OneSpile!!'||zdmc||'!!OneSpile!!'||zdlx||'!!OneSpile!!'||zddx||'!!OneSpile!!'||bxwsz str from view_xszz_common_new_bbzdyzd where xmdm=?";
		String[] st = dao.getRs(sql, new String[]{xmdm}, "str");
		for(int i = 0; i < st.length; i++){
			sb.append(st[i]);
			sb.append("!!TwoSpile!!");
		}
		return sb.toString();
	}
	
	public synchronized List<String[]> getShgcZdyzdList(String bbdm) {
		// 返回List结构的结果集
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String sql = "select zddm,zdmc from view_xszz_shgc_bbzdyzd where bbdm='"+bbdm+"'";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] st = new String[] { rs.getString("zddm"),
						rs.getString("zdmc") };
				arrayList.add(st);
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
	public synchronized List<String[]> getXszzZdyzdList(String xmdm) {
		// 返回List结构的结果集
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String sql = "select zddm,zdmc from view_xszz_common_new_bbzdyzd where xmdm='"+xmdm+"'";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] st = new String[] { rs.getString("zddm"),
						rs.getString("zdmc") };
				arrayList.add(st);
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
	//根据学生学号得到学生奖学金获奖记录
	public synchronized List<String> getXsJxjjlList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select a.xn,a.nd,a.xq,c.xm,b.jxjmc,b.jlje from xsjxjb a,jxjdmb b,view_xsjbxx c where a.jxjdm=b.jxjdm and a.xh=c.xh and a.xxsh='通过' and a.xh='" + xh + "' order by a.xn,a.nd,a.xq";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("xn"));
				sb.append("学年 ");
				sb.append(rs.getString("nd"));
				sb.append("年度 ");
				sb.append(rs.getString("xq"));
				sb.append("学期, ");
				sb.append(rs.getString("xm"));
				sb.append(" 获得");
				sb.append(rs.getString("jxjmc"));
				sb.append(",金额为：");
				sb.append(rs.getString("jlje"));
				sb.append("元。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	根据学生学号得到上海工程学生其他奖学金获奖记录
	public synchronized List<String> getShgcXsQtJxjjlList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select a.nd,a.xq,c.xm,b.bbmc,a.xxpzje from pjpy_shgc_jxjbbxssqb a,pjpy_shgc_jxjbbdmb b,view_xsjbxx c where a.bbdm=b.bbdm and a.xh=c.xh and xxsh='通过' and a.xh='" + xh + "' order by a.nd,a.xq";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("年度 ");
				sb.append(rs.getString("xq"));
				sb.append("学期, ");
				sb.append(rs.getString("xm"));
				sb.append(" 获得");
				sb.append(rs.getString("bbmc"));
				sb.append(" ,金额为：");
				sb.append(rs.getString("xxpzje"));
				sb.append("元。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	根据学生学号得到浙江机电临时困难补助记录
	public synchronized List<String> getZjjdLsknbzList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select sqsj,xm,lsbzmc,lsbzje from view_xszz_zjjd_lsbz where xxsh='通过' and xh='"+xh+"' order by sqsj";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("sqsj"));
				sb.append("  ");
				sb.append(rs.getString("xm"));
				sb.append(" 获得");
				sb.append(rs.getString("lsbzmc"));
				sb.append(",金额为：");
				sb.append(rs.getString("lsbzje"));
				sb.append("元。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	根据学生学号得到浙江机电学费缓缴记录
	public synchronized List<String> getZjjdXfhjList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select nd,xm,hjje from view_xszz_zjjd_xfhj where xxsh='通过' and xh='"+xh+"' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("年度 ");
				sb.append(rs.getString("xm"));
				sb.append(" 缓缴学费");
				sb.append(rs.getString("hjje"));
				sb.append("元。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	根据学生学号得到浙江机电学费减免记录
	public synchronized List<String> getZjjdXfjmList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select nd,xm,xfjmmc,xfjmje from view_xszz_zjjd_xfjm where xxsh='通过' and xh='"+xh+"' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("年度 ");
				sb.append(rs.getString("xm"));
				sb.append(" 获得");
				sb.append(rs.getString("xfjmmc"));
				sb.append(",金额为：");
				sb.append(rs.getString("xfjmje"));
				sb.append("元。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	根据学生学号得到浙江机电国家助学金记录
	public synchronized List<String> getZjjdGjzxjList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select nd,xm,zxjdjmc,zxjje from view_zjjd_gjzxjsqb where xxsh='通过' and xh='"+xh+"' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("年度 ");
				sb.append(rs.getString("xm"));
				sb.append(" 获得");
				sb.append(rs.getString("zxjdjmc"));
				sb.append(",金额为：");
				sb.append(rs.getString("zxjje"));
				sb.append("元。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	根据学生学号得到浙江机电国家励志奖学金记录
	public synchronized List<String> getZjjdGjLzjxjList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select nd,xm from view_xszz_zjjd_gjlzjxj where xxsh='通过' and xh='"+xh+"' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("年度 ");
				sb.append(rs.getString("xm"));
				sb.append(" 获得国家励志奖学金。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	根据学生学号得到浙江机电国家助学贷款记录
	public synchronized List<String> getZjjdGjzxdkList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select nd,xm,xfdk from view_xszz_zjjd_gjzxdk where xxsh='通过' and xh='"+xh+"' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("年度 ");
				sb.append(rs.getString("xm"));
				sb.append(" 获得国家助学贷款,金额为：");
				sb.append(rs.getString("xfdk"));
				sb.append("元。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	根据学生学号得到上海工程学生优秀奖学金获奖记录
	public synchronized List<String> getShgcXsYxJxjjlList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select a.xn,a.xq,a.xm,a.jxjje from view_shgc_pjpy_yxjxj a where a.xh='" + xh + "' order by a.xn,a.xq";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("xn"));
				sb.append("学年 ");
				sb.append(rs.getString("xq"));
				sb.append("学期, ");
				sb.append(rs.getString("xm"));
				sb.append(" 获得优秀学生奖学金 ,金额为：");
				sb.append(rs.getString("jxjje"));
				sb.append("元。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	根据学生学号得到上海工程学生自强奖学金获奖记录
	public synchronized List<String> getShgcXsZqJxjjlList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select a.nd,a.xq,a.xm,a.jxjje from view_shgc_pjpy_zqjxj a where a.xh='" + xh + "' order by a.nd,a.xq";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("年度 ");
				sb.append(rs.getString("xq"));
				sb.append("学期, ");
				sb.append(rs.getString("xm"));
				sb.append(" 获得自强学生奖学金 ,金额为：");
				sb.append(rs.getString("jxjje"));
				sb.append("元。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	根据学生学号得到学生勤工助学酬金记录
	public synchronized List<String> getXsQgzuCjjlList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select a.xn,a.nd,a.xq,a.yf,b.xm,a.gwdm,a.cjje from xscjffb a,view_xsjbxx b where a.xh=b.xh and a.xh='" + xh + "' order by a.xn,a.nd,a.xq,a.yf";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("xn"));
				sb.append("学年 ");
				sb.append(rs.getString("nd"));
				sb.append("年度 ");
				sb.append(rs.getString("xq"));
				sb.append("学期 ");
				sb.append(rs.getString("yf"));
				sb.append("月份, ");
				sb.append(rs.getString("xm"));
				sb.append(" 因参加勤工助学的");
				sb.append(rs.getString("gwdm"));
				sb.append("工作,获得酬金：");
				sb.append(rs.getString("cjje"));
				sb.append("元。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	根据学生学号得到上海工程学生获得资助记录
	public synchronized List<String> getShgcXszzHdjeList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select a.nd,c.xm,b.bbmc,a.xxpzje from xszz_shgc_zzbbxssqb a,xszz_shgc_zzbbdmb b,view_xsjbxx c where a.bbdm=b.bbdm and a.xh=c.xh and xxsh='通过' and a.xh='" + xh + "' order by a.nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("年度, ");
				sb.append(rs.getString("xm"));
				sb.append(" 获得");
				sb.append(rs.getString("bbmc"));
				sb.append(" ,金额为：");
				sb.append(rs.getString("xxpzje"));
				sb.append("元。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	根据学生学号得到新版资助学生获得资助记录
	public synchronized List<String> getXszzHdjeList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select a.nd,c.xm,b.xmmc,a.xxpzje from xszz_common_new_zzbbxssqb a,XSZZ_COMMON_NEW_ZZXMDMB b,view_xsjbxx c where a.xmdm=b.xmdm and a.xh=c.xh and xxsh='通过' and a.xh='" + xh + "' order by a.nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("年度, ");
				sb.append(rs.getString("xm"));
				sb.append(" 获得");
				sb.append(rs.getString("xmmc"));
				sb.append(" ,金额为：");
				sb.append(rs.getString("xxpzje"));
				sb.append("元。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
//	根据学生学号得到上海工程学生获得包括勤工助学，奖学金，学生资助，助学贷款所有金额
	public synchronized String[] getShgcXshdZjeList(String xh, String xxdm) {
		DAO dao = DAO.getInstance();
		int jxjJe = 0;
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			int qtjxjJe = Integer
					.valueOf(dao
							.getOneRs(
									"select nvl(sum(ROUND(xxpzje)),0) jlje from pjpy_shgc_jxjbbxssqb where xxsh='通过' and xh=?",
									new String[] { xh }, "jlje"));
			int yxjxjJe = Integer
					.valueOf(dao
							.getOneRs(
									"select nvl(sum(ROUND(jxjje)),0) jlje from view_shgc_pjpy_yxjxj where xh=?",
									new String[] { xh }, "jlje"));
			int zqjxjJe = Integer
					.valueOf(dao
							.getOneRs(
									"select nvl(sum(ROUND(jxjje)),0) jlje from view_shgc_pjpy_zqjxj where xh=?",
									new String[] { xh }, "jlje"));
			jxjJe = qtjxjJe + yxjxjJe + zqjxjJe;
		} else {
			jxjJe = Integer
					.valueOf(dao
							.getOneRs(
									"select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb a,jxjdmb b where a.jxjdm=b.jxjdm and a.xxsh='通过' and a.xh=?",
									new String[] { xh }, "jlje"));
		}
		int qgzxJe = Integer.valueOf(dao.getOneRs(
				"select nvl(sum(ROUND(cjje)),0) cjje from xscjffb where xh=?",
				new String[] { xh }, "cjje"));
		int zzJe = Integer
				.valueOf(dao
						.getOneRs(
								"select nvl(sum(ROUND(xxpzje)),0) xxpzje from xszz_shgc_zzbbxssqb where xxsh='通过' and xh=?",
								new String[] { xh }, "xxpzje"));
		int zxdkJe = Integer
				.valueOf(dao
						.getOneRs(
								"select nvl(sum(ROUND(dkze)),0) dkze from zxdk_xssqb where xxsh='通过' and xh=?",
								new String[] { xh }, "dkze"));
		int zje = jxjJe + qgzxJe + zzJe + zxdkJe;
		return new String[] { String.valueOf(jxjJe), String.valueOf(qgzxJe),
				String.valueOf(zzJe), String.valueOf(zxdkJe),
				String.valueOf(zje) };
	}
	
//	根据学生学号得到学生获得包括勤工助学，奖学金，学生资助，助学贷款所有金额
	public synchronized String[] getXshdZjeList(String xh) {
		DAO dao = DAO.getInstance();
		int jxjJe = 0;
		jxjJe = Integer
					.valueOf(dao
							.getOneRs(
									"select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb a,jxjdmb b where a.jxjdm=b.jxjdm and a.xxsh='通过' and a.xh=?",
									new String[] { xh }, "jlje"));
		int qgzxJe = Integer.valueOf(dao.getOneRs(
				"select nvl(sum(ROUND(cjje)),0) cjje from xscjffb where xh=?",
				new String[] { xh }, "cjje"));
		int zzJe = Integer
				.valueOf(dao
						.getOneRs(
								"select nvl(sum(ROUND(xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb where xxsh='通过' and xh=?",
								new String[] { xh }, "xxpzje"));
		int zxdkJe = Integer
				.valueOf(dao
						.getOneRs(
								"select nvl(sum(ROUND(hj)),0) dkze from view_xszz_common_gjzxdk where xxsh='通过' and xh=?",
								new String[] { xh }, "dkze"));
		int zje = jxjJe + qgzxJe + zzJe + zxdkJe;
		return new String[] { String.valueOf(jxjJe), String.valueOf(qgzxJe),
				String.valueOf(zzJe), String.valueOf(zxdkJe),
				String.valueOf(zje) };
	}
	
	public List<HashMap<String, String>> xyshList() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String[] chkList = null;
		chkList = new String[]{ "未审核", "通过", "不通过" };
		int len = chkList.length;
		HashMap<String, String> map = null;
		for (int i = 0; i < len; i++) {
			map = new HashMap<String, String>();
			map.put("xysh", chkList[i]);
			map.put("xycn", chkList[i]);
			list.add(map);
		}
		return list;
	}
	
	public List<HashMap<String, String>> xxshList() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String[] chkList = null;
		chkList = new String[]{ "未审核", "通过", "不通过" };
		int len = chkList.length;
		HashMap<String, String> map = null;
		for (int i = 0; i < len; i++) {
			map = new HashMap<String, String>();
			map.put("xxsh", chkList[i]);
			map.put("xxcn", chkList[i]);
			list.add(map);
		}
		return list;
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到广东女子职业技术学院贷款类型列表
	 */
	public synchronized List<HashMap<String, String>> getGdnzDklxList() throws Exception{
		String sql = "select dklxdm,dklxmc from gdnzzyxy_dklxdmb order by dklxdm";
		return this.getList(sql, new String[] {}, new String[] { "dklxdm",
				"dklxmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到广东女子职业技术学院审核批表编号
	 */
	public synchronized String getGdnzSpbbhList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select spbbh from gnnzzy_gjzxdksqb";
		String[] spb = dao.getArray(sql, new String[]{}, "spbbh");
		StringBuffer sb = new StringBuffer("!!OneSpilt!!");
		for(int i = 0; i < spb.length; i++){
			sb.append(spb[i]);
			sb.append("!!OneSpilt!!");
		}
		return sb.toString();
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到广东女子职业技术学院合同号列表
	 */
	public synchronized List<HashMap<String, String>> getGdnzHthList(String xh) throws Exception{
		String sql = "select htbh hth from gnnzzy_gjzxdksqb where xh=? order by htbh";
		return this.getList(sql, new String[] { xh }, new String[] { "hth" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，得到广东女子职业技术学院合同信息(dwr)
	 */
	public synchronized String[] getGdnzHtxx(String hth) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select a.hth,a.htje,a.htzje,a.dkqx,a.byny||'-06-30' zfxjtrq,a.nll,a.hkzhlx,a.hkzhhm,case when a.byny<a.dqn then case when a.dqn<substr(a.dkqx,1,4) then a.dqn||'-10-23' when a.dqn>=substr(a.dkqx,1,4) then substr(a.dkqx,1,4)||'-10-23' end when a.byny>=a.dqn then case when a.byny<substr(a.dkqx,1,4) then a.byny||'-10-23' when a.byny>=substr(a.dkqx,1,4) then substr(a.dkqx,1,4)||'-10-23' end end hksj,a.gzdwmc,a.gzdwdz,a.gzdwyb,a.dwdh from (select a.htbh hth,a.dkje htje,(select sum(z.dkje) from gnnzzy_gjzxdksqb z where z.xh=a.xh and z.xxsh='通过') htzje,a.dkqx,(select (floor(substr(bk.rxny,1,4))+floor(NVL(bk.xz,'3'))) from bks_xsjbxx bk where bk.xh=a.xh) byny,a.nll,a.hkzhlx,a.hkzhhm,(select to_char(sysdate,'yyyy') from dual) dqn,(select z.gzdwmc from gdnzzy_hdjbxx z where z.hth=a.htbh) gzdwmc,(select z.gzdwdz from gdnzzy_hdjbxx z where z.hth=a.htbh) gzdwdz,(select z.gzdwyb from gdnzzy_hdjbxx z where z.hth=a.htbh) gzdwyb,(select z.dwdh from gdnzzy_hdjbxx z where z.hth=a.htbh) dwdh from view_gnnzzy_gjzxdksqb a where a.htbh=?) a";
		return dao.getOneRs(sql, new String[] { hth }, new String[] { "hth","htje","htzje","dkqx","zfxjtrq","nll","hkzhlx","hkzhhm","hksj","gzdwmc","gzdwdz","gzdwyb","dwdh" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 学生资助，北京联合 - 根据补助发放年月和学生学号判断学籍状态
	 */
	public synchronized String getBjlhXjztByBzffny(String bzffny, String xh) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select YDHXJZTM xjzt from view_xjydjbxx where ydxh in (select max(ydxh) from view_xjydjbxx a where ydrq in (select max(ydrq) from view_xjydjbxx where substr(ydrq,1,7)=?) and xh=?)";
		return dao.getOneRs(sql, new String[] { bzffny, xh }, "xjzt");
	}
	
	/**
	 * 获取奖助学金自定义字段列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized List<String[]> getJzxjZdyzdList(String xmdm) {
		// 返回List结构的结果集
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String sql = "select zddm,zdmc from view_jzxj_xmbbzdyzd where xmdm='"+xmdm+"'";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] st = new String[] { rs.getString("zddm"),
						rs.getString("zdmc") };
				arrayList.add(st);
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
	
	/**
	 * @author ZhouMi
	 * @describe 奖助学金，得到项目自定义字段详细信息列表
	 */
	public synchronized String getJzxjZdyzdXxxxList(String xmdm) throws Exception{
		DAO dao = DAO.getInstance(); 
		StringBuffer sb = new StringBuffer("");
		String sql = "select xmdm||'!!OneSpile!!'||xmmc||'!!OneSpile!!'||zddm||'!!OneSpile!!'||zdmc||'!!OneSpile!!'||zdlx||'!!OneSpile!!'||zddx||'!!OneSpile!!'||bxwsz str from view_jzxj_xmbbzdyzd where xmdm=?";
		String[] st = dao.getRs(sql, new String[]{xmdm}, "str");
		for(int i = 0; i < st.length; i++){
			sb.append(st[i]);
			sb.append("!!TwoSpile!!");
		}
		return sb.toString();
	}
	
	/**
	 * @author ZhouMi
	 * @describe 奖助学金，得到项目金额列表
	 */
	public synchronized List<HashMap<String, String>> getJzxjXmjeList(String xmdm) throws Exception{
		String sql = "select xmje from view_jzxj_xmjeb where xmdm=? order by xmje";
		return this.getList(sql, new String[] {xmdm}, new String[] { "xmje" });
	}
	
//	根据学生学号得到学生获得包括奖助学金、临时补助、其他资助所有金额
	public synchronized String[] getXsZjeList(String xh) {
		DAO dao = DAO.getInstance();
		int jzxjJe = Integer
				.valueOf(dao
						.getOneRs(
								"select nvl(sum(ROUND(xgpzje)),0) xxpzje from jzxj_xssqb where xgsh='通过' and xh=?",
								new String[] { xh }, "xxpzje"));
		int zje = jzxjJe;
		return new String[] { String.valueOf(jzxjJe), 
				String.valueOf(zje) };
	}
	
//	根据学生学号得到学生奖助学金获奖记录
	public synchronized List<String> getXsJzxjList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select a.xn,a.xq,a.xm,a.xmmc,a.xxpzje,a.xgpzje from view_jzxj_xssqb a where xgsh='通过' and a.xh='" + xh + "' order by a.xn,a.xq,a.xmdm";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("xn")); 
				sb.append("学年 ");
				sb.append(rs.getString("xq"));
				sb.append("学期,");
				sb.append(rs.getString("xm"));
				sb.append(" 获得");
				sb.append(rs.getString("xmmc"));
				sb.append(" ,金额为：");
				sb.append(rs.getString("xgpzje"));
				sb.append("元。");
				arrayList.add(sb.toString());
			}
			closeStmt();
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			arrayList = null;
			closeStmt();
			closeConn();
			closeConnection(conn);
		} finally {
			closeStmt();
			closeConn();
			closeConnection(conn);
		}
		return arrayList;
	}
}