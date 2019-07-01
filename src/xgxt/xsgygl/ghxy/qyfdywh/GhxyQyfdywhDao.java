package xgxt.xsgygl.ghxy.qyfdywh;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class GhxyQyfdywhDao {
	/**
	 *公寓园区List
	 */
	public List<HashMap<String,String>> yqList(){
		DAO dao = DAO.getInstance();
		String sql="select yqdm yqqdm,yqmc yqqmc from yqdmb";
		return dao.getList(sql, new String[]{}, new String[]{"yqqdm","yqqmc"});
	}
	
	/**
	 * 根据职工号给出年级主任的管辖年级
	 */
	public List<HashMap<String,String>>getQyfdy(String zgh){
		DAO dao=DAO.getInstance();
		String sql="select yqqdm from ghxy_qyfdyb where zgh =?";
		return dao.getList(sql, new String[]{zgh}, new String[]{"yqqdm"});
	}
	
	/**
	 * 根据职工号给出年级主任的管辖年级
	 */
	public List<HashMap<String,String>>getQyfdyxx(String zgh){
		DAO dao=DAO.getInstance();
		String sql="select yqqdm,yqmc yqqmc from ghxy_qyfdyb a,yqdmb b where a.yqqdm=b.yqdm and zgh =?";
		return dao.getList(sql, new String[]{zgh}, new String[]{"yqqdm","yqqmc"});
	}
}
