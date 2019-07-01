package xgxt.pjpy.mjxy.jtrych;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class MjxyJtrychDao {
	
	/**
	 * ���������ƺ�
	 */
	public List<HashMap<String,String>>jtrychList(){
		DAO dao=DAO.getInstance();
		String sql="select jtrychdm dm,jtrychmc mc from jtrychdmb ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * ���ݰ༶��ȡ�༶����
	 * 
	 */
	public List<HashMap<String,String>> getBjrs(String bjdm){
		DAO dao=DAO.getInstance();
		String sql="select count(*)bjrs from xsxxb where bjdm=? ";
		return dao.getList(sql, new String[]{bjdm}, new String[]{"bjrs"});
	}
	
	/**
	 * �༶��Ա����
	 */
	public List<HashMap<String,String>>getBjdyrs(String bjdm){
		DAO dao=DAO.getInstance();
		String sql="select count(*)dyrs from  view_dyxx where bjdm= ? ";
		return dao.getList(sql, new String[]{bjdm}, new String[]{"dyrs"});
	}
	
	/**
	 * �༶�ɲ���Ϣ
	 * 
	 */
	public List<HashMap<String,String>>getBjgbxx(String bjdm){
		DAO dao=DAO.getInstance();
		String sql="select xm,bjgbmc from view_bjgbxx where (bjgbmc='�೤' or bjgbmc='��֧�����') and bjdm=?";
		return dao.getList(sql, new String[]{bjdm}, new String[]{"bjgbmc","xm"});
	}
	
	/**
	 * �༶Υ�ʹ�������
	 * 
	 */
	public List<HashMap<String,String>>getCfrs(String bjdm){
		DAO dao=DAO.getInstance();
		String sql="select count(*)bjcfrs from (select xh from  view_wjcf where bjdm=? group by xh)";
		return dao.getList(sql, new String[]{bjdm}, new String[]{"bjcfrs"});
	}
	
	/**
	 * �༶����
	 * 
	 */
	public List<HashMap<String,String>>getBjdm(String xh){
		DAO dao=DAO.getInstance();
		String sql="select bjdm from xsxxb where xh=?";
		return dao.getList(sql, new String[]{xh}, new String[]{"bjdm"});
	}
	
	public List<HashMap<String,String>>getBjxx(String xh){
		DAO dao=DAO.getInstance();
		String sql="select nj,bjmc,zymc,xy,bjdm,zydm,xydm from xsxxb where xh=?";
		return dao.getList(sql, new String[]{xh}, new String[]{"nj","bjmc","zymc","xy","zydm","bjdm","xydm"});
	}
	
	/**
	 * ��ȡ�꼶
	 */
	public List<HashMap<String,String>>getNjByBjdm(String bjdm){
		DAO dao=DAO.getInstance();
		String sql="select nj from view_njxyzybj where bjdm=?";
		return dao.getList(sql, new String[]{bjdm}, new String []{"nj"});
	}
}
