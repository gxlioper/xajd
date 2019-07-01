package xsgzgl.xtwh.general.menu;

import java.sql.SQLException;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;

/**
 * <p>
 * ���ܲ˵�����
 * <p>
 * @author Penghui.Qu
 */
public class MenuDao extends CommDAO {

	DAO dao = DAO.getInstance();
	
	/**
	 * ���ӿ�ݹ��ܲ˵�
	 * @param yhm
	 * @param gnmkdm
	 * @return
	 * @throws SQLException
	 */
	boolean addKjfs(String yhm,String gnmkdm) throws SQLException{
		
		String sql = "insert into xg_xtwh_kjgnmkb(yhm,gnmkdm) values (?,?)";
		
		return dao.insert(sql, new String[]{yhm,gnmkdm});
	}
	
	
	/**
	 * ɾ����ݹ��ܲ˵� 
	 * @param yhm
	 * @param gnmkdm
	 * @return
	 * @throws Exception
	 */
	boolean removeKjfs(String yhm,String gnmkdm) throws Exception{
		String sql = "delete from xg_xtwh_kjgnmkb where yhm=? and gnmkdm=?";
		return dao.runUpdate(sql, new String[]{yhm,gnmkdm});
	}
	
	
	/**
	 * �����û���ݹ��ܲ˵�
	 * @param yhm
	 * @param gnmkdm
	 * @return
	 * @throws SQLException
	 */
	String[] getKjfs(String yhm,String gnmkdm) throws SQLException{
		
		String sql = "select * from xg_xtwh_kjgnmkb where yhm=? and gnmkdm like ?||'____' order by czsj";
		
		return dao.getArray(sql, new String[]{yhm,gnmkdm}, "gnmkdm");
	}
}
