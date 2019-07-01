package xgxt.qtsj.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���չ���ģ��DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-12-15</p>
 */
public class InsureDAO extends DAO {
	
	/**
	 * ��ȡͶ�������б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getTbxzdmList(){
		String sql = "select bxxzdm,bxxzmc from bxxzb";
		List<HashMap<String, String>> tbxzdmList = getList(sql, new String[] {}, new String[] {"bxxzdm", "bxxzmc" });
		return tbxzdmList;
	}
	
	/**
	 * ��ȡ���չ�˾�б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getBxgsList(){
		String sql = "select bxgsdm,bxgsmc from bxgsdmb order by bxgsdm";
		List<HashMap<String, String>> bxgsdmList = getList(sql, 
				                                           new String[] {}, 
				                                           new String[] {"bxgsdm", "bxgsmc" });
		return bxgsdmList;
	}
	
	/**
	 * ��ȡ���յ����б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getBxdcList(){
		String sql = "select dcdm,dcmc from gdnzzy_bxdcdmb order by dcdm";
		List<HashMap<String, String>> bcdcList = getList(sql, 
				                                           new String[] {}, 
				                                           new String[] {"dcdm", "dcmc" });
		return bcdcList;
	}
	
	
	
	
	/**
	 * ����¼�Ƿ�����
	 * @param tableName
	 * @param key
	 * @param value
	 * @return boolean
	 * */
	public boolean checkExists(String tableName,String key, String value){
		String sql = "select count(*) num from " + tableName + " where " + key + "=?";
		String result = getOneRs(sql, new String[]{value}, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		
		return Integer.parseInt(result)>0 ? true : false;
	}
	
	/**
	 * ������Ϣ��ѯ
	 * @param title
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getDataPubInfo(String title){
		String sql = "select title, content from bxglxxb where title=?";
		return getMap(sql, new String[]{title}, new String[]{"title", "content"});
	}
	
}
