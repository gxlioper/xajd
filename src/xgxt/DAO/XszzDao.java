/*
 * �������� 2007-11-21 zhoumi
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
		// ���캯����ʼ���������ӡ�;
		this.db = DBPool.getPool(); ////////Configuration.connMgr;
	}

	public XszzDao(String Ip) {
		// ���캯��������һ������IP����ʼ��IP��MAC����д����־ʱʹ�á�
		this();
		this.ip = Ip;
		this.mac = getMacAddressIP(ip);
	}

	public String getMacAddressIP(String remotePcIP) {
		// ͨ��IP��ȡMAC
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
		// ͨ��ģ�飬�ر���������
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
		// ͨ��ģ�飬�ر�������������
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
	 * �ص�����
	 */
	public void closeConnection(){
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	/**
	 * ���ڹر����ݿ�����,���뱣֤�Ѿ�ʹ������ResultSet��Statement,
	 * ���򽫳���ʹ��closed ResultSet��closed Statement�쳣
	 * @param conn
	 */
	public void closeConnection(Connection conn){
		try{
			conn.close();
		} catch(Exception e){
			e.printStackTrace();
		} 
	}
	
	// ��ȡ����״���б�
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
	
//	 ��ȡѧ���б�
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
		// ����List�ṹ�Ľ����
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
	 * @describe ѧ���������õ��㶫Ů��ְҵ����ѧԺѧ����ѧ����Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getGdJxjList() throws Exception{
		String sql = "select jxjdm||'!!splitOne!!'||jlmc||'!!splitOne!!'||bjdw||'!!splitOne!!'||jlze||'!!splitOne!!'||jxjdj||'!!splitOne!!'||jlgrje jxjdm1,jlmc from GdNZZY_xszz_jzjdmb order by jxjdm";
		return this.getList(sql, new String[] {}, new String[] { "jxjdm1",
				"jlmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ��㶫Ů��ְҵ����ѧԺѧ����ѧ����Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getGdZxjList() throws Exception{
		String sql = "select zxjdm||'!!splitOne!!'||zzmc||'!!splitOne!!'||zzdw||'!!splitOne!!'||zzze||'!!splitOne!!'||zzgrje zxjdm1,zzmc from GdNZZY_xszz_zxjdmb order by zxjdm";
		return this.getList(sql, new String[] {}, new String[] { "zxjdm1",
				"zzmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ��㶫Ů��ְҵ����ѧԺѧ�����Ѳ�����Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getGdKnbzList() throws Exception{
		String sql = "select knbzdm||'!!splitOne!!'||knbzmc knbzdm1,knbzmc from GdNZZY_xszz_knbzdmb order by knbzdm";
		return this.getList(sql, new String[] {}, new String[] { "knbzdm1",
				"knbzmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ��㶫Ů��ְҵ����ѧԺѧ����ѧ������
	 */
	public synchronized ArrayList<String[]> getGdbzzyXsjxj(String xh, String jxjdm){
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String sql = "select nd,jlmc,ffje from view_gdnzzyjsxy_jxjsqxx where jxjdm='"+jxjdm+"' and xh='"+xh+"' and xxsh='ͨ��' order by nd";
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
	 * @describe ѧ���������õ��㶫Ů��ְҵ����ѧԺѧ����ѧ������
	 */
	public synchronized ArrayList<String[]> getGdbzzyXszxj(String xh, String zxjdm){
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String sql = "select nd,zzmc,ffje from view_gdnzzyjsxy_zxjsqxx where zxjdm='"+zxjdm+"' and xh='"+xh+"' and xxsh='ͨ��' order by nd";
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
	 * @describe ѧ���������õ��㶫Ů��ְҵ����ѧԺѧ��������¼
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
	 * @describe ѧ���������õ��㶫Ů��ְҵ����ѧԺѧ�����Ѳ�������
	 */
	public synchronized ArrayList<String[]> getGdbzzyXsknbz(String xh, String knbzdm){
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String sql = "select nd,knbzmc,ffje from view_gdnzzyjsxy_knbzsqxx where knbzdm='"+knbzdm+"' and xh='"+xh+"' and xxsh='ͨ��' order by nd";
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
	 * @describe ѧ���������õ��㶫Ů��ְҵ����ѧԺѧ����ѧ��������
	 */
	public synchronized ArrayList<String[]> getGdbzzyXszxdk(String xh, String hth){
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String sql = "select nd,hth,dkje from view_gdnzzy_zxdkxx where hth='"+hth+"' and xh='"+xh+"' and xxsh='ͨ��' order by nd";
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
	 * @describe ѧ���������õ��㶫Ů��ְҵ����ѧԺѧ�����ܽ���ѧ�����Ѳ�������ѧ����ļ�¼
	 */
	public synchronized ArrayList<String> getXszzJL(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String[]> list = this.getGdnzzyXszzJl(xh);
		for (Iterator<String[]> it = list.iterator(); it.hasNext();) {
			String[] temp = it.next();
			if ("��ѧ��".equalsIgnoreCase(temp[0])) {
				ArrayList<String[]> tList = this.getGdbzzyXsjxj(xh, temp[2]);
				for (Iterator<String[]> its = tList.iterator(); its.hasNext();) {
					String[] st = its.next();
					String jl = st[0] + "���  ����ý�ѧ��:" + st[1] + "�����Ϊ:" + st[2]
							+ "Ԫ";
					arrayList.add(jl);
				}
			}
			if ("��ѧ��".equalsIgnoreCase(temp[0])) {
				ArrayList<String[]> tList = this.getGdbzzyXszxj(xh, temp[2]);
				for (Iterator<String[]> its = tList.iterator(); its.hasNext();) {
					String[] st = its.next();
					String jl = st[0] + "���  �������ѧ��:" + st[1] + "�����Ϊ:" + st[2]
							+ "Ԫ";
					arrayList.add(jl);
				}
			}
			if ("���Ѳ���".equalsIgnoreCase(temp[0])) {
				ArrayList<String[]> tList = this.getGdbzzyXsknbz(xh, temp[2]);
				for (Iterator<String[]> its = tList.iterator(); its.hasNext();) {
					String[] st = its.next();
					String jl = st[0] + "���  �������Ѳ���:" + st[1] + "�����Ϊ:" + st[2]
							+ "Ԫ";
					arrayList.add(jl);
				}
			}
//			if ("��ѧ����".equalsIgnoreCase(temp[0])) {
//				ArrayList<String[]> tList = this.getGdbzzyXsknbz(xh, temp[2]);
//				for (Iterator<String[]> its = tList.iterator(); its.hasNext();) {
//					String[] st = its.next();
//					String jl = st[0] + "���  ������ѧ����:" + st[2] + "Ԫ����ͬ��Ϊ:"
//							+ st[1];
//					arrayList.add(jl);
//				}
//			}
		}
		return arrayList;
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ�����������Ŀ�б�
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
			
			//==========================������ѧ����--sjf============================
			sql.append(" and hzxmmc!='��ѧ����'");
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
	 * @describe ѧ���������õ�����������Ŀ�б�
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
	
//	ѧ������--ͨ��ѧУ����õ�ѧ����ϸ��Ϣ��ѧ��������Ҫ��ʾ��������Ŀ����
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
	
//	ѧ������--ͨ��ѧ����ϸ��Ϣ��ѧ��������������Ŀ�����õ���Ҫ��ʾ���ֶΡ�
	public ArrayList<String> getStuDataPartByXxdm(String tabName){
		ArrayList<String> colName = new ArrayList<String>();
		String sql = "select lower(zdmc) zdmc from xszz_xsjbxx where xmmc=upper('"+tabName+"') order by xssx";
		colName = getArrayList(sql, "zdmc");
		return colName;
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ��Ϻ����̼�����ѧ������Ŀ����б�
	 */
	public synchronized List<HashMap<String, String>> getShgcZzjeList(String bbdm) throws Exception{
		String sql = "select zzje from xszz_shgc_zzjeb where bbdm=? order by zzje";
		return this.getList(sql, new String[] {bbdm}, new String[] { "zzje" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ�ѧ������������Ŀ����б�
	 */
	public synchronized List<HashMap<String, String>> getXszzZzjeList(String xmdm) throws Exception{
		String sql = "select zzje from view_xszz_common_new_zzjeb where xmdm=? order by zzje";
		return this.getList(sql, new String[] {xmdm}, new String[] { "zzje" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ��Ϻ����̼�����ѧ������Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getShgcZzxmList() throws Exception{
		String sql = "select bbdm,bbmc from xszz_shgc_zzbbdmb order by bbdm";
		return this.getList(sql, new String[] {}, new String[] { "bbdm",
				"bbmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ�ѧ������������Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getXszzZzxmList() throws Exception{
		String sql = "select xmdm,xmmc from XSZZ_COMMON_NEW_ZZXMDMB order by xmdm";
		return this.getList(sql, new String[] {}, new String[] { "xmdm",
				"xmmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ��Ϻ����̼�����ѧ��ѧ������Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getShgcZxdkxmList() throws Exception{
		String sql = "select '������' xmmc from dual union select '������ѧ����' xmmc from dual union select '����Э��' xmmc from dual";
		return this.getList(sql, new String[] {}, new String[] { "xmmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ�ѧ��������ѧ������Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getXszzZxdkxmList() throws Exception{
		String xxdm = Base.xxdm;
		String sql = "select '������' xmmc from dual union select '������ѧ����' xmmc from dual";
		
		if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {
			sql += " union select '������־��ѧ��' xmmc from dual union select '���ҽ�ѧ��' xmmc from dual";
		}
		return this.getList(sql, new String[] {}, new String[] { "xmmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ��й����ʴ�ѧ��������Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getZgdzdxKnsxmList() throws Exception{
		String sql = "select '��ͥ�������' xmmc from dual union select '����������ʱ��' xmmc from dual union select '���������ʱ��' xmmc from dual";
		return this.getList(sql, new String[] {}, new String[] { "xmmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ��й����ʴ�ѧ��ѧ������Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getZgdzdxZxdkxmList() throws Exception{
		String sql = "select '��ѧ��������' xmmc from dual union select '����ȷ����' xmmc from dual union select '����Э��' xmmc from dual union select 'չ��Э��' xmmc from dual union select 'չ�ں󻹿�Э��' xmmc from dual";
		return this.getList(sql, new String[] {}, new String[] { "xmmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ��й�����ѧԺ��ѧ������Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getZgmsxyZxdkxmList() throws Exception{
		String sql = "select '��ѧ��������' xmmc from dual union select '��ҵ����Ϣ�ɼ�' xmmc from dual";
		return this.getList(sql, new String[] {}, new String[] { "xmmc" });
	}
	
	public synchronized String[] getViewComm(String viewName, String notColName) throws SQLException {
		// �õ���ͼ���ֶ�ע�����
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
	 * @describe ѧ���������õ��Ϻ����̼�����ѧ�����Զ����ֶ���ϸ��Ϣ�б�
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
	 * @describe ѧ���������õ�ѧ�����������Զ����ֶ���ϸ��Ϣ�б�
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
		// ����List�ṹ�Ľ����
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
		// ����List�ṹ�Ľ����
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
	
	//����ѧ��ѧ�ŵõ�ѧ����ѧ��񽱼�¼
	public synchronized List<String> getXsJxjjlList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select a.xn,a.nd,a.xq,c.xm,b.jxjmc,b.jlje from xsjxjb a,jxjdmb b,view_xsjbxx c where a.jxjdm=b.jxjdm and a.xh=c.xh and a.xxsh='ͨ��' and a.xh='" + xh + "' order by a.xn,a.nd,a.xq";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("xn"));
				sb.append("ѧ�� ");
				sb.append(rs.getString("nd"));
				sb.append("��� ");
				sb.append(rs.getString("xq"));
				sb.append("ѧ��, ");
				sb.append(rs.getString("xm"));
				sb.append(" ���");
				sb.append(rs.getString("jxjmc"));
				sb.append(",���Ϊ��");
				sb.append(rs.getString("jlje"));
				sb.append("Ԫ��");
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
	
//	����ѧ��ѧ�ŵõ��Ϻ�����ѧ��������ѧ��񽱼�¼
	public synchronized List<String> getShgcXsQtJxjjlList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select a.nd,a.xq,c.xm,b.bbmc,a.xxpzje from pjpy_shgc_jxjbbxssqb a,pjpy_shgc_jxjbbdmb b,view_xsjbxx c where a.bbdm=b.bbdm and a.xh=c.xh and xxsh='ͨ��' and a.xh='" + xh + "' order by a.nd,a.xq";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("��� ");
				sb.append(rs.getString("xq"));
				sb.append("ѧ��, ");
				sb.append(rs.getString("xm"));
				sb.append(" ���");
				sb.append(rs.getString("bbmc"));
				sb.append(" ,���Ϊ��");
				sb.append(rs.getString("xxpzje"));
				sb.append("Ԫ��");
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
	
//	����ѧ��ѧ�ŵõ��㽭������ʱ���Ѳ�����¼
	public synchronized List<String> getZjjdLsknbzList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select sqsj,xm,lsbzmc,lsbzje from view_xszz_zjjd_lsbz where xxsh='ͨ��' and xh='"+xh+"' order by sqsj";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("sqsj"));
				sb.append("  ");
				sb.append(rs.getString("xm"));
				sb.append(" ���");
				sb.append(rs.getString("lsbzmc"));
				sb.append(",���Ϊ��");
				sb.append(rs.getString("lsbzje"));
				sb.append("Ԫ��");
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
	
//	����ѧ��ѧ�ŵõ��㽭����ѧ�ѻ��ɼ�¼
	public synchronized List<String> getZjjdXfhjList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select nd,xm,hjje from view_xszz_zjjd_xfhj where xxsh='ͨ��' and xh='"+xh+"' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("��� ");
				sb.append(rs.getString("xm"));
				sb.append(" ����ѧ��");
				sb.append(rs.getString("hjje"));
				sb.append("Ԫ��");
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
	
//	����ѧ��ѧ�ŵõ��㽭����ѧ�Ѽ����¼
	public synchronized List<String> getZjjdXfjmList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select nd,xm,xfjmmc,xfjmje from view_xszz_zjjd_xfjm where xxsh='ͨ��' and xh='"+xh+"' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("��� ");
				sb.append(rs.getString("xm"));
				sb.append(" ���");
				sb.append(rs.getString("xfjmmc"));
				sb.append(",���Ϊ��");
				sb.append(rs.getString("xfjmje"));
				sb.append("Ԫ��");
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
	
//	����ѧ��ѧ�ŵõ��㽭���������ѧ���¼
	public synchronized List<String> getZjjdGjzxjList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select nd,xm,zxjdjmc,zxjje from view_zjjd_gjzxjsqb where xxsh='ͨ��' and xh='"+xh+"' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("��� ");
				sb.append(rs.getString("xm"));
				sb.append(" ���");
				sb.append(rs.getString("zxjdjmc"));
				sb.append(",���Ϊ��");
				sb.append(rs.getString("zxjje"));
				sb.append("Ԫ��");
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
	
//	����ѧ��ѧ�ŵõ��㽭���������־��ѧ���¼
	public synchronized List<String> getZjjdGjLzjxjList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select nd,xm from view_xszz_zjjd_gjlzjxj where xxsh='ͨ��' and xh='"+xh+"' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("��� ");
				sb.append(rs.getString("xm"));
				sb.append(" ��ù�����־��ѧ��");
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
	
//	����ѧ��ѧ�ŵõ��㽭���������ѧ�����¼
	public synchronized List<String> getZjjdGjzxdkList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select nd,xm,xfdk from view_xszz_zjjd_gjzxdk where xxsh='ͨ��' and xh='"+xh+"' order by nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("��� ");
				sb.append(rs.getString("xm"));
				sb.append(" ��ù�����ѧ����,���Ϊ��");
				sb.append(rs.getString("xfdk"));
				sb.append("Ԫ��");
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
	
//	����ѧ��ѧ�ŵõ��Ϻ�����ѧ�����㽱ѧ��񽱼�¼
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
				sb.append("ѧ�� ");
				sb.append(rs.getString("xq"));
				sb.append("ѧ��, ");
				sb.append(rs.getString("xm"));
				sb.append(" �������ѧ����ѧ�� ,���Ϊ��");
				sb.append(rs.getString("jxjje"));
				sb.append("Ԫ��");
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
	
//	����ѧ��ѧ�ŵõ��Ϻ�����ѧ����ǿ��ѧ��񽱼�¼
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
				sb.append("��� ");
				sb.append(rs.getString("xq"));
				sb.append("ѧ��, ");
				sb.append(rs.getString("xm"));
				sb.append(" �����ǿѧ����ѧ�� ,���Ϊ��");
				sb.append(rs.getString("jxjje"));
				sb.append("Ԫ��");
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
	
//	����ѧ��ѧ�ŵõ�ѧ���ڹ���ѧ����¼
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
				sb.append("ѧ�� ");
				sb.append(rs.getString("nd"));
				sb.append("��� ");
				sb.append(rs.getString("xq"));
				sb.append("ѧ�� ");
				sb.append(rs.getString("yf"));
				sb.append("�·�, ");
				sb.append(rs.getString("xm"));
				sb.append(" ��μ��ڹ���ѧ��");
				sb.append(rs.getString("gwdm"));
				sb.append("����,��ó��");
				sb.append(rs.getString("cjje"));
				sb.append("Ԫ��");
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
	
//	����ѧ��ѧ�ŵõ��Ϻ�����ѧ�����������¼
	public synchronized List<String> getShgcXszzHdjeList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select a.nd,c.xm,b.bbmc,a.xxpzje from xszz_shgc_zzbbxssqb a,xszz_shgc_zzbbdmb b,view_xsjbxx c where a.bbdm=b.bbdm and a.xh=c.xh and xxsh='ͨ��' and a.xh='" + xh + "' order by a.nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("���, ");
				sb.append(rs.getString("xm"));
				sb.append(" ���");
				sb.append(rs.getString("bbmc"));
				sb.append(" ,���Ϊ��");
				sb.append(rs.getString("xxpzje"));
				sb.append("Ԫ��");
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
	
//	����ѧ��ѧ�ŵõ��°�����ѧ�����������¼
	public synchronized List<String> getXszzHdjeList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select a.nd,c.xm,b.xmmc,a.xxpzje from xszz_common_new_zzbbxssqb a,XSZZ_COMMON_NEW_ZZXMDMB b,view_xsjbxx c where a.xmdm=b.xmdm and a.xh=c.xh and xxsh='ͨ��' and a.xh='" + xh + "' order by a.nd";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("nd"));
				sb.append("���, ");
				sb.append(rs.getString("xm"));
				sb.append(" ���");
				sb.append(rs.getString("xmmc"));
				sb.append(" ,���Ϊ��");
				sb.append(rs.getString("xxpzje"));
				sb.append("Ԫ��");
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
	
//	����ѧ��ѧ�ŵõ��Ϻ�����ѧ����ð����ڹ���ѧ����ѧ��ѧ����������ѧ�������н��
	public synchronized String[] getShgcXshdZjeList(String xh, String xxdm) {
		DAO dao = DAO.getInstance();
		int jxjJe = 0;
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			int qtjxjJe = Integer
					.valueOf(dao
							.getOneRs(
									"select nvl(sum(ROUND(xxpzje)),0) jlje from pjpy_shgc_jxjbbxssqb where xxsh='ͨ��' and xh=?",
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
									"select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb a,jxjdmb b where a.jxjdm=b.jxjdm and a.xxsh='ͨ��' and a.xh=?",
									new String[] { xh }, "jlje"));
		}
		int qgzxJe = Integer.valueOf(dao.getOneRs(
				"select nvl(sum(ROUND(cjje)),0) cjje from xscjffb where xh=?",
				new String[] { xh }, "cjje"));
		int zzJe = Integer
				.valueOf(dao
						.getOneRs(
								"select nvl(sum(ROUND(xxpzje)),0) xxpzje from xszz_shgc_zzbbxssqb where xxsh='ͨ��' and xh=?",
								new String[] { xh }, "xxpzje"));
		int zxdkJe = Integer
				.valueOf(dao
						.getOneRs(
								"select nvl(sum(ROUND(dkze)),0) dkze from zxdk_xssqb where xxsh='ͨ��' and xh=?",
								new String[] { xh }, "dkze"));
		int zje = jxjJe + qgzxJe + zzJe + zxdkJe;
		return new String[] { String.valueOf(jxjJe), String.valueOf(qgzxJe),
				String.valueOf(zzJe), String.valueOf(zxdkJe),
				String.valueOf(zje) };
	}
	
//	����ѧ��ѧ�ŵõ�ѧ����ð����ڹ���ѧ����ѧ��ѧ����������ѧ�������н��
	public synchronized String[] getXshdZjeList(String xh) {
		DAO dao = DAO.getInstance();
		int jxjJe = 0;
		jxjJe = Integer
					.valueOf(dao
							.getOneRs(
									"select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb a,jxjdmb b where a.jxjdm=b.jxjdm and a.xxsh='ͨ��' and a.xh=?",
									new String[] { xh }, "jlje"));
		int qgzxJe = Integer.valueOf(dao.getOneRs(
				"select nvl(sum(ROUND(cjje)),0) cjje from xscjffb where xh=?",
				new String[] { xh }, "cjje"));
		int zzJe = Integer
				.valueOf(dao
						.getOneRs(
								"select nvl(sum(ROUND(xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb where xxsh='ͨ��' and xh=?",
								new String[] { xh }, "xxpzje"));
		int zxdkJe = Integer
				.valueOf(dao
						.getOneRs(
								"select nvl(sum(ROUND(hj)),0) dkze from view_xszz_common_gjzxdk where xxsh='ͨ��' and xh=?",
								new String[] { xh }, "dkze"));
		int zje = jxjJe + qgzxJe + zzJe + zxdkJe;
		return new String[] { String.valueOf(jxjJe), String.valueOf(qgzxJe),
				String.valueOf(zzJe), String.valueOf(zxdkJe),
				String.valueOf(zje) };
	}
	
	public List<HashMap<String, String>> xyshList() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String[] chkList = null;
		chkList = new String[]{ "δ���", "ͨ��", "��ͨ��" };
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
		chkList = new String[]{ "δ���", "ͨ��", "��ͨ��" };
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
	 * @describe ѧ���������õ��㶫Ů��ְҵ����ѧԺ���������б�
	 */
	public synchronized List<HashMap<String, String>> getGdnzDklxList() throws Exception{
		String sql = "select dklxdm,dklxmc from gdnzzyxy_dklxdmb order by dklxdm";
		return this.getList(sql, new String[] {}, new String[] { "dklxdm",
				"dklxmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ��㶫Ů��ְҵ����ѧԺ���������
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
	 * @describe ѧ���������õ��㶫Ů��ְҵ����ѧԺ��ͬ���б�
	 */
	public synchronized List<HashMap<String, String>> getGdnzHthList(String xh) throws Exception{
		String sql = "select htbh hth from gnnzzy_gjzxdksqb where xh=? order by htbh";
		return this.getList(sql, new String[] { xh }, new String[] { "hth" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������õ��㶫Ů��ְҵ����ѧԺ��ͬ��Ϣ(dwr)
	 */
	public synchronized String[] getGdnzHtxx(String hth) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select a.hth,a.htje,a.htzje,a.dkqx,a.byny||'-06-30' zfxjtrq,a.nll,a.hkzhlx,a.hkzhhm,case when a.byny<a.dqn then case when a.dqn<substr(a.dkqx,1,4) then a.dqn||'-10-23' when a.dqn>=substr(a.dkqx,1,4) then substr(a.dkqx,1,4)||'-10-23' end when a.byny>=a.dqn then case when a.byny<substr(a.dkqx,1,4) then a.byny||'-10-23' when a.byny>=substr(a.dkqx,1,4) then substr(a.dkqx,1,4)||'-10-23' end end hksj,a.gzdwmc,a.gzdwdz,a.gzdwyb,a.dwdh from (select a.htbh hth,a.dkje htje,(select sum(z.dkje) from gnnzzy_gjzxdksqb z where z.xh=a.xh and z.xxsh='ͨ��') htzje,a.dkqx,(select (floor(substr(bk.rxny,1,4))+floor(NVL(bk.xz,'3'))) from bks_xsjbxx bk where bk.xh=a.xh) byny,a.nll,a.hkzhlx,a.hkzhhm,(select to_char(sysdate,'yyyy') from dual) dqn,(select z.gzdwmc from gdnzzy_hdjbxx z where z.hth=a.htbh) gzdwmc,(select z.gzdwdz from gdnzzy_hdjbxx z where z.hth=a.htbh) gzdwdz,(select z.gzdwyb from gdnzzy_hdjbxx z where z.hth=a.htbh) gzdwyb,(select z.dwdh from gdnzzy_hdjbxx z where z.hth=a.htbh) dwdh from view_gnnzzy_gjzxdksqb a where a.htbh=?) a";
		return dao.getOneRs(sql, new String[] { hth }, new String[] { "hth","htje","htzje","dkqx","zfxjtrq","nll","hkzhlx","hkzhhm","hksj","gzdwmc","gzdwdz","gzdwyb","dwdh" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe ѧ���������������� - ���ݲ����������º�ѧ��ѧ���ж�ѧ��״̬
	 */
	public synchronized String getBjlhXjztByBzffny(String bzffny, String xh) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select YDHXJZTM xjzt from view_xjydjbxx where ydxh in (select max(ydxh) from view_xjydjbxx a where ydrq in (select max(ydrq) from view_xjydjbxx where substr(ydrq,1,7)=?) and xh=?)";
		return dao.getOneRs(sql, new String[] { bzffny, xh }, "xjzt");
	}
	
	/**
	 * ��ȡ����ѧ���Զ����ֶ��б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized List<String[]> getJzxjZdyzdList(String xmdm) {
		// ����List�ṹ�Ľ����
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
	 * @describe ����ѧ�𣬵õ���Ŀ�Զ����ֶ���ϸ��Ϣ�б�
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
	 * @describe ����ѧ�𣬵õ���Ŀ����б�
	 */
	public synchronized List<HashMap<String, String>> getJzxjXmjeList(String xmdm) throws Exception{
		String sql = "select xmje from view_jzxj_xmjeb where xmdm=? order by xmje";
		return this.getList(sql, new String[] {xmdm}, new String[] { "xmje" });
	}
	
//	����ѧ��ѧ�ŵõ�ѧ����ð�������ѧ����ʱ�����������������н��
	public synchronized String[] getXsZjeList(String xh) {
		DAO dao = DAO.getInstance();
		int jzxjJe = Integer
				.valueOf(dao
						.getOneRs(
								"select nvl(sum(ROUND(xgpzje)),0) xxpzje from jzxj_xssqb where xgsh='ͨ��' and xh=?",
								new String[] { xh }, "xxpzje"));
		int zje = jzxjJe;
		return new String[] { String.valueOf(jzxjJe), 
				String.valueOf(zje) };
	}
	
//	����ѧ��ѧ�ŵõ�ѧ������ѧ��񽱼�¼
	public synchronized List<String> getXsJzxjList(String xh) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "select a.xn,a.xq,a.xm,a.xmmc,a.xxpzje,a.xgpzje from view_jzxj_xssqb a where xgsh='ͨ��' and a.xh='" + xh + "' order by a.xn,a.xq,a.xmdm";
		try {
			this.conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer(" ");
				sb.append(rs.getString("xn")); 
				sb.append("ѧ�� ");
				sb.append(rs.getString("xq"));
				sb.append("ѧ��,");
				sb.append(rs.getString("xm"));
				sb.append(" ���");
				sb.append(rs.getString("xmmc"));
				sb.append(" ,���Ϊ��");
				sb.append(rs.getString("xgpzje"));
				sb.append("Ԫ��");
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