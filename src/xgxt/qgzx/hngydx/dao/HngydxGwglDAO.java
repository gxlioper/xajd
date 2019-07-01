package xgxt.qgzx.hngydx.dao;

import java.util.List;

import xgxt.DAO.DAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块湖南工业岗位管理DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public class HngydxGwglDAO {
	/**
	 * 获取用人单位列表
	 * */
	public List getYrdwList(){
		DAO dao = DAO.getInstance();
		String sql = "select distinct yrdwdm,yrdwmc from yrdwdmb order by yrdwdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"yrdwdm","yrdwmc"});
	}
	
	/**
	 * 获取岗位性质列表
	 * */
	public List getGwxzList(){
		DAO dao = DAO.getInstance();
		String sql = "select distinct gwxzdm,gwxzmc from gwxzdmb order by gwxzdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"gwxzdm","gwxzmc"});
	}
	
	/**
	 * 获取当前勤工助学学期
	 * */
	
	public String getCurrXq(){
		DAO dao = DAO.getInstance();
		String sql = "select xq from gwsqsjb";		
		return dao.getOneRs(sql, new String[]{}, "xq");
	}
	
	/**
	 * 判断一个用户是否是用人单位用户
	 * 
	 * */
	public boolean checkIsYrdw(String userName){
		DAO dao = DAO.getInstance();
		String sql = "select count(*)count from yrdwdmb where dlm=?";
		return Integer.parseInt(dao.getOneRs(sql, new String[]{userName}, "count"))>0 ? true : false;		
	}
	
	/**
	 * 根据用户名获取用户所属的单位
	 * 
	 * */
	public String getYrdwdmByUser(String userName){
		DAO dao = DAO.getInstance();
		String sql = "select yrdwdm from yrdwdmb where dlm=?";
		
		return dao.getOneRs(sql, new String[]{userName}, "yrdwdm");
	}
}
