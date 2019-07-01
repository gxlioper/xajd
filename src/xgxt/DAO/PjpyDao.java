/*
 * �������� 2007-11-21 zhoumi
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
	 * @describe �������ţ��õ��Ϻ����̼�����ѧ��ѧ����Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getShgcJxjxmList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select bbdm,bbmc from PJPY_SHGC_JXJBBDMB order by bbdm";
		return dao.getList(sql, new String[] {}, new String[] { "bbdm",
				"bbmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe �������ţ��õ��Ϻ����̼�����ѧ��ѧ����Ŀ�б�(ȫ)
	 */
	public synchronized List<HashMap<String, String>> getShgcJxjxmListAll() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "SELECT bbdm,bbmc FROM (select bbdm,bbmc from PJPY_SHGC_JXJBBDMB UNION ALL SELECT '���㽱ѧ��' bbdm,'���㽱ѧ��' bbmc FROM dual UNION ALL SELECT '��ǿ��ѧ��' bbdm,'��ǿ��ѧ��' bbmc FROM dual) ORDER BY bbdm";
		return dao.getList(sql, new String[] {}, new String[] { "bbdm",
				"bbmc" });
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
	
	public synchronized List<Vector<String>> getShgcZdyzdList(String bbdm) {
		DAO dao = DAO.getInstance();
		String sql = "select zddm,zdmc from view_pjpy_shgc_bbzdyzd where bbdm=?";
		return dao.getVectorList(sql, new String[]{bbdm}, new String[]{"zddm","zdmc"});
	}
	
	/**
	 * @author ZhouMi
	 * @describe �������ţ��õ��Ϻ����̼�����ѧ��ѧ���Զ����ֶ���ϸ��Ϣ�б�
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
	 * @describe �������ţ��õ��Ϻ����̼�����ѧ��ѧ����Ŀ����б�
	 */
	public synchronized List<HashMap<String, String>> getShgcJxjjeList(String bbdm) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select jxjje from pjpy_shgc_jxjjeb where bbdm=? order by jxjje";
		return dao.getList(sql, new String[] {bbdm}, new String[] { "jxjje" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe �������ţ��õ��Ϻ����̼�����ѧ�������ǿ��ѧ����Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getShgcQtjxjxmList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select '����ѧ����ѧ��' xmmc from dual union select '��ǿ��ѧ��' xmmc from dual";
		return dao.getList(sql, new String[] {}, new String[] { "xmmc" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe �������ţ��õ��Ϻ����̼�����ѧ���㽱ѧ��ȼ��б�
	 */
	public synchronized List<HashMap<String, String>> getShgcYxJxjdjList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select '��' jxjdj from dual union select 'һ' jxjdj from dual union select '��' jxjdj from dual union select '��' jxjdj from dual";
		return dao.getList(sql, new String[] {}, new String[] { "jxjdj" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe �������ţ��õ��Ϻ����̼�����ѧ��ǿ��ѧ��ȼ��б�
	 */
	public synchronized List<HashMap<String, String>> getShgcZqJxjdjList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select '��' jxjdj from dual union select '��' jxjdj from dual";
		return dao.getList(sql, new String[] {}, new String[] { "jxjdj" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe �������ţ��õ��Ϻ����̼�����ѧ��ר���б�
	 */
	public synchronized List<HashMap<String, String>> getShgcBzkList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select '����' bzk from dual union select 'ר��' bzk from dual";
		return dao.getList(sql, new String[] {}, new String[] { "bzk" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe �������ţ��õ��Ϻ����̼�����ѧ���＾���б�
	 */
	public synchronized List<HashMap<String, String>> getShgcCqjbList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select '������' cqjb from dual union select '�＾��' cqjb from dual";
		return dao.getList(sql, new String[] {}, new String[] { "cqjb" });
	}
	
	/**
	 * @author ZhouMi
	 * @describe �������ţ��õ��Ϻ����̼�����ѧ�Ƿ��������б�
	 */
	public synchronized List<HashMap<String, String>> getShgcSfknsList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select '��' sfkns from dual union select '��' sfkns from dual";
		return dao.getList(sql, new String[] {}, new String[] { "sfkns" });
	}
}