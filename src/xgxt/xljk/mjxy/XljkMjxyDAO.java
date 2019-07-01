package xgxt.xljk.mjxy;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class XljkMjxyDAO {
	
	/**
	 * ��ȡ���Ͳ����б�
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getBsbmList(){
		String sql = " select dm,mc from xljk_mjxy_bsbmdmb ";
		DAO dao = DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	
	/**
	 * ��ȡ����״̬�б�
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getXlztList(){
		String sql = " select dm,mc from xljk_mjxy_jlztdmb ";
		DAO dao = DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	public HashMap<String,String>getXh(String pkValue){
		String sql=" select xh from xljk_mjxy_xlwjyjb where xh||sbsj=?";
		DAO dao=DAO.getInstance();
		return dao.getMap(sql, new String[]{pkValue}, new String[]{"xh"});
	}
	
	public boolean deleteSql(String xh) throws Exception{
		DAO dao=DAO.getInstance();
		String sql="delete from XLJK_MJXY_XLKHZXJLB where xh=?";
		return dao.runUpdate(sql, new String[]{xh});
	}
	
	/**
	 * ɾ��û�����������������
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteFbxx() throws Exception{
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append("delete  from XLJK_MJXY_XLKHZXJLB a where not exists");
		sql.append("(select 1 from xljk_mjxy_xlkhxsb b where a.xh=b.xh)");
		return dao.runUpdate(sql.toString(),new String[]{});
	}
	
	/**
	 * ��ѯͳ����Ϣ
	 */
	public List<HashMap<String,String>> getTjList(String[] input ,String sb){
		DAO dao=DAO.getInstance();
		String sql=" select * from xljk_mjxy_xlkhzxjlb  b where exists (select * from view_xljk_mjxy_xlkhxsb a  "+sb+" and a.xh=b.xh )";
		return dao.getList(sql,input, new String[]{"xh","zxcx","zxsj","zxls","zxjl","sfcxzx"});
	}
	
	public List<HashMap<String,String>> getKhxsjlList(String xh){
		DAO dao=DAO.getInstance();
		String sql=" select * from xljk_mjxy_xlkhzxjlb where xh=?";
		return dao.getList(sql,new String[]{xh}, new String[]{"zxcx","zxsj","zxls","zxjl","sfcxzx"});
	} 
}
