package xgxt.qgzx.hngydx.dao;

import java.util.List;

import xgxt.DAO.DAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ڹ���ѧģ����Ϲ�ҵ��λ����DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public class HngydxGwglDAO {
	/**
	 * ��ȡ���˵�λ�б�
	 * */
	public List getYrdwList(){
		DAO dao = DAO.getInstance();
		String sql = "select distinct yrdwdm,yrdwmc from yrdwdmb order by yrdwdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"yrdwdm","yrdwmc"});
	}
	
	/**
	 * ��ȡ��λ�����б�
	 * */
	public List getGwxzList(){
		DAO dao = DAO.getInstance();
		String sql = "select distinct gwxzdm,gwxzmc from gwxzdmb order by gwxzdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"gwxzdm","gwxzmc"});
	}
	
	/**
	 * ��ȡ��ǰ�ڹ���ѧѧ��
	 * */
	
	public String getCurrXq(){
		DAO dao = DAO.getInstance();
		String sql = "select xq from gwsqsjb";		
		return dao.getOneRs(sql, new String[]{}, "xq");
	}
	
	/**
	 * �ж�һ���û��Ƿ������˵�λ�û�
	 * 
	 * */
	public boolean checkIsYrdw(String userName){
		DAO dao = DAO.getInstance();
		String sql = "select count(*)count from yrdwdmb where dlm=?";
		return Integer.parseInt(dao.getOneRs(sql, new String[]{userName}, "count"))>0 ? true : false;		
	}
	
	/**
	 * �����û�����ȡ�û������ĵ�λ
	 * 
	 * */
	public String getYrdwdmByUser(String userName){
		DAO dao = DAO.getInstance();
		String sql = "select yrdwdm from yrdwdmb where dlm=?";
		
		return dao.getOneRs(sql, new String[]{userName}, "yrdwdm");
	}
}
