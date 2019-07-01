package xgxt.xljk.whlgdx.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
//import xgxt.utils.New_Random_ID;

public class Xljk_xskhdxwhDao {
	/** get Dao instance*/
	private DAO dao = DAO.getInstance();
	/**get Xljk_xskhdxwhDao instance ,for service object to invoke*/
	private static Xljk_xskhdxwhDao mydao = new Xljk_xskhdxwhDao();
	/** return static instance*/
	public static Xljk_xskhdxwhDao getInstance(){
		return mydao;
	}
	
	/**
	 * @param sql
	 * @param columns
	 * @param primaryKey
	 * @return  ����ѧ���������Ϣ
	 */
	public HashMap<String,String> getStuInfo(String sql,String[] columns,String primaryKey){
		return dao.getList(sql, new String[]{primaryKey}, columns).get(0);
	}
	
	/**
	 * @param sql
	 * @param values
	 * @return �����µ�ѧ������
	 * @throws Exception
	 */
	public boolean saveStuInfo (String sql,String[] values) throws Exception{
		return dao.insert(sql, values);
	}
	
	/**
	 * @param sql
	 * @param xn_id  <font color='blue'>��������</font>
	 * @return ɾ��ѧ����һ����¼
	 * @throws Exception
	 */
	public boolean delStuInfo(String sql,String xn_id) throws Exception{
		return dao.runUpdate(sql, new String[]{xn_id});
	}
	
	/**
	 * @param sql
	 * @param input <font color='blue'>Ҫ�޸ĵ�����</font>
	 * @return  �޸�ѧ��������
	 * @throws Exception
	 */
	public boolean modiStuInfo(String sql,String[] input) throws Exception{
		return dao.runUpdate(sql, input);
	}
	
	/**
	 * @param sql
	 * @param outputValue
	 * @return  ��������������Ӧ��ѧ������Ϣ
	 */
	public List<HashMap<String,String>> getStusByCondi(String sql,String[] outputValue){
		return dao.getList(sql, new String[]{}, outputValue);
	}	
}
