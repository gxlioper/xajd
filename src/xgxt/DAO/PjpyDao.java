/*
 * 创建日期 2007-11-21 zhoumi
 *
 */
package xgxt.DAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class PjpyDao {
	
	/**
	 * @author ZhouMi
	 * @describe 评奖评优，得到上海工程技术大学奖学金项目列表
	 */
	public synchronized List<HashMap<String, String>> getShgcJxjxmList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select bbdm,bbmc from PJPY_SHGC_JXJBBDMB order by bbdm";
		return dao.getList(sql, new String[] {}, new String[] { "bbdm",
				"bbmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 评奖评优，得到上海工程技术大学奖学金项目列表(全)
	 */
	public synchronized List<HashMap<String, String>> getShgcJxjxmListAll() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "SELECT bbdm,bbmc FROM (select bbdm,bbmc from PJPY_SHGC_JXJBBDMB UNION ALL SELECT '优秀奖学金' bbdm,'优秀奖学金' bbmc FROM dual UNION ALL SELECT '自强奖学金' bbdm,'自强奖学金' bbmc FROM dual) ORDER BY bbdm";
		return dao.getList(sql, new String[] {}, new String[] { "bbdm",
				"bbmc" });
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
	
	public synchronized List<Vector<String>> getShgcZdyzdList(String bbdm) {
		DAO dao = DAO.getInstance();
		String sql = "select zddm,zdmc from view_pjpy_shgc_bbzdyzd where bbdm=?";
		return dao.getVectorList(sql, new String[]{bbdm}, new String[]{"zddm","zdmc"});
	}
	
	/**
	 * @author ZhouMi
	 * @describe 评奖评优，得到上海工程技术大学奖学金自定义字段详细信息列表
	 */
	public synchronized String getShgcZdyzdXxxxList(String bbdm) throws Exception{
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer("");
		String sql = "select bbdm||'!!OneSpile!!'||bbmc||'!!OneSpile!!'||zddm||'!!OneSpile!!'||zdmc||'!!OneSpile!!'||zdlx||'!!OneSpile!!'||zddx||'!!OneSpile!!'||bxwsz str from view_pjpy_shgc_bbzdyzd where bbdm=?";
		String[] st = dao.getRs(sql, new String[]{bbdm}, "str");
		for(int i = 0; i < st.length; i++){
			sb.append(st[i]);
			sb.append("!!TwoSpile!!");
		}
		return sb.toString();
	}
	
	/**
	 * @author ZhouMi
	 * @describe 评奖评优，得到上海工程技术大学奖学金项目金额列表
	 */
	public synchronized List<HashMap<String, String>> getShgcJxjjeList(String bbdm) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select jxjje from pjpy_shgc_jxjjeb where bbdm=? order by jxjje";
		return dao.getList(sql, new String[] {bbdm}, new String[] { "jxjje" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 评奖评优，得到上海工程技术大学优秀和自强奖学金项目列表
	 */
	public synchronized List<HashMap<String, String>> getShgcQtjxjxmList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select '优秀学生奖学金' xmmc from dual union select '自强奖学金' xmmc from dual";
		return dao.getList(sql, new String[] {}, new String[] { "xmmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 评奖评优，得到上海工程技术大学优秀奖学金等级列表
	 */
	public synchronized List<HashMap<String, String>> getShgcYxJxjdjList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select '特' jxjdj from dual union select '一' jxjdj from dual union select '二' jxjdj from dual union select '三' jxjdj from dual";
		return dao.getList(sql, new String[] {}, new String[] { "jxjdj" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 评奖评优，得到上海工程技术大学自强奖学金等级列表
	 */
	public synchronized List<HashMap<String, String>> getShgcZqJxjdjList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select '甲' jxjdj from dual union select '乙' jxjdj from dual";
		return dao.getList(sql, new String[] {}, new String[] { "jxjdj" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 评奖评优，得到上海工程技术大学本专科列表
	 */
	public synchronized List<HashMap<String, String>> getShgcBzkList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select '本科' bzk from dual union select '专科' bzk from dual";
		return dao.getList(sql, new String[] {}, new String[] { "bzk" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 评奖评优，得到上海工程技术大学春秋季班列表
	 */
	public synchronized List<HashMap<String, String>> getShgcCqjbList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select '春季班' cqjb from dual union select '秋季班' cqjb from dual";
		return dao.getList(sql, new String[] {}, new String[] { "cqjb" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe 评奖评优，得到上海工程技术大学是否困难生列表
	 */
	public synchronized List<HashMap<String, String>> getShgcSfknsList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select '是' sfkns from dual union select '否' sfkns from dual";
		return dao.getList(sql, new String[] {}, new String[] { "sfkns" });
	}
}