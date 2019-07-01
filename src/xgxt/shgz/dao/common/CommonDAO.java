package xgxt.shgz.dao.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.DAO.DAO;

/**
* <p>Title: ѧ������ϵͳ</p>
* <p>Description: ѧ����Ϣ����˼������ͨ��DAO��</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: zfsoft</p>
* @author ³��
* @version 1.0
*/

public class CommonDAO  {
	DAO dao = DAO.getInstance();
	public List<HashMap<String, String>> getCommonList(String tableName,String dm,String mc,String query) {
		// ��ȡ�б�ͨ�÷���
		// query���String ��������ݴ�����ʱҪ��" " 
		StringBuffer sqlBuffer = new StringBuffer("select ");
		sqlBuffer.append(dm);
		sqlBuffer.append(",");
		sqlBuffer.append(mc);
		sqlBuffer.append(" from ");
		sqlBuffer.append(tableName);
		sqlBuffer.append(query);
		return dao.getList(sqlBuffer.toString(), new String[] {}, new String[] { dm, mc });
	}
	
	public List<HashMap<String, String>> getComboList() {
		// ��ȡ�����б�
		String sql = "select stdm,stmc from view_stsqdj where shzt = 'ͨ��'";
		return dao.getList(sql, new String[] {}, new String[] { "stdm", "stmc" });
	}

	public List<HashMap<String, String>> getTutorshippersonList(String userDep) {
		// ��ȡ����Ա��Ϣ
		String sql = "select zgh,xm from fdyxxb where (bmdm = ? or ? = ' ') order by zgh";
		return dao.getList(sql, new String[] {userDep,userDep+" "}, new String[] { "zgh", "xm" });
	}
	
	public List<HashMap<String, String>> getChkList(int type) {
		// ��˽��������
		String[] chkList = null;
		if (type == 1) {
			chkList = new String[] { "�����", "δ���" };
		} else if (type == 2) {
			chkList = new String[] { "��", "��" };
		} else if (type == 3) {
			chkList = new String[] { "δ���", "ͨ��", "��ͨ��" };
		} else if(type ==4) {
			chkList = new String[] { "�ɹ�", "ʧ��" };
		} else if(type ==5) {
			chkList = new String[] { "δ���", "����", "һ������","��ͨ��" };
		} else if(type ==6) {
			chkList = new String[] { "1", "2", "3","4","5","6","7","8","9","10","11","12" };
		}else if(type ==7) {
			chkList = new String[] { "����", "��̸", "����","�ݽ�","�ι�","����" };
		}else if(type ==8) {
			chkList = new String[] { "����潻��", "����ת��", "�绰��ϵ","�ź�","�����ʼ�","�ֻ�����" };
		}else if(type ==9) {
			chkList = new String[] { "��������","1����֮��", "2����֮��", "3����֮��","����֮��","һ��֮��","����֮��" };
		}else if(type ==10) {
			chkList = new String[] { ">", "=","<" };
		}
		return dao.arrayToList(chkList, chkList);
	}
	
	
	public String[] getFdyBjList(String pk){
    //    ��ð༶����Ա�б�
		String sql = "select bjmc from fdybjb a,view_njxyzybj b where a.bjdm = b.bjdm and a.zgh=?";
		try {
			return dao.getArray(sql, new String[]{pk}, "bjmc");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Object getSfList() {
    //    ���ʡ���б�
		String sql = "select sfdm,sfmc from sfdmdzb ";
		return dao.getList(sql,new String[]{},new String[]{"sfdm","sfmc"});
	}
	
	
	public ArrayList<String[]> sxjyQuery(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
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
	
	public String[] sxjyQueryOne2(String tableName,String [] colList,String pk,String pkValue) {
    //    ͨ��������ѯ���������� ���HashMapString[]��ʽ��ͨ�÷���
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
	
	
	public boolean getSffdy(String zgh) throws SQLException {
    //    �ж��Ƿ��ǽ�ʦ�ķ���
		HashMap<String, String> map =sxjyQueryOne("fdyxxb", new String[]{"zgh"}, "zgh",zgh);
		if(map.get("zgh").equalsIgnoreCase("")){//����Ǹ���Ա�������ø���Ա��־
			return false;
		} else {
			return true;
		}
	}
}
