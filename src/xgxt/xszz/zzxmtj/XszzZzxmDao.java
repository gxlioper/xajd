package xgxt.xszz.zzxmtj;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class XszzZzxmDao {
	
	
	public List<HashMap<String,String>> getZzxmList(){
		DAO dao=DAO.getInstance();
		String sql="select xmdm dm,xmmc mc from xszz_zzxmb where kgzt<>'�ر�����' ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * ��ȡ��Ŀ�����б�
	 */
	public List<HashMap<String,String>>getXmdjList(String xmdm){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append("select fjmc dm,fjmc mc from xszz_xmfjqkb a where exists");
		sb.append(" (select * from xszz_zzxmb b where b.sffj='�ּ�' and  b.xmdm=? and a.xmdm=b.xmdm) ");
		return dao.getList(sb.toString(), new String[]{xmdm}, new String[]{"dm","mc"});
	}
	
	/**
	 * ��ȡ������Ŀ����
	 */
	public HashMap<String,String>getZzZq(String xmdm){
		DAO dao=DAO.getInstance();
		String sql="select sqzq from xszz_zzxmb where xmdm=?";
		return dao.getMap(sql, new String[]{xmdm}, new String[]{"sqzq"});
	}
	
	/**
	 * ��ȡ������Ŀ����
	 */
	public HashMap<String,String>getXmbName(String xmdm){
		DAO dao=DAO.getInstance();
		String sql="select xmb from xszz_zzxmb where xmdm=?";
		return dao.getMap(sql, new String[]{xmdm}, new String[]{"xmb"});
	}
	
	/**
	 * ��ȡ�����Ƿ��н��
	 */
	public HashMap<String,String>getSfje(String xmdm){
		DAO dao=DAO.getInstance();
		String sql="select sfje from xszz_zzxmb where xmdm = ?";
		return dao.getMap(sql, new String[]{xmdm}, new String[]{"sfje"});
	}
	
	/**
	 * ��ȡ�����Ƿ��н��
	 */
	public HashMap<String,String>checkSffj(String xmdm){
		DAO dao=DAO.getInstance();
		String sql="select sffj from xszz_zzxmb where xmdm = ?";
		return dao.getMap(sql, new String[]{xmdm}, new String[]{"sffj"});
	}
	
	/**
	 * �������������ȡ����
	 */
	public HashMap<String,String>getZzmc(String xmdm){
		DAO dao=DAO.getInstance();
		String sql="select xmmc from xszz_zzxmb where xmdm = ?";
		return dao.getMap(sql, new String[]{xmdm}, new String[]{"xmmc"});
	}
	
	/**
	 * ��ȡϵͳά�� 
	 * ��ά�������б�
	 */
	public HashMap<String,String> getSfxsG(String zmc){
		DAO dao=DAO.getInstance();
		String sql="select sfxs from  yhzb  where zdm=?";
		return dao.getMap(sql, new String[]{zmc}, new String[]{"sfxs"});
	}
}

