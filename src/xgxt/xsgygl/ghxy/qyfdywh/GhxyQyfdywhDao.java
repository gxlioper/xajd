package xgxt.xsgygl.ghxy.qyfdywh;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class GhxyQyfdywhDao {
	/**
	 *��Ԣ԰��List
	 */
	public List<HashMap<String,String>> yqList(){
		DAO dao = DAO.getInstance();
		String sql="select yqdm yqqdm,yqmc yqqmc from yqdmb";
		return dao.getList(sql, new String[]{}, new String[]{"yqqdm","yqqmc"});
	}
	
	/**
	 * ����ְ���Ÿ����꼶���εĹ�Ͻ�꼶
	 */
	public List<HashMap<String,String>>getQyfdy(String zgh){
		DAO dao=DAO.getInstance();
		String sql="select yqqdm from ghxy_qyfdyb where zgh =?";
		return dao.getList(sql, new String[]{zgh}, new String[]{"yqqdm"});
	}
	
	/**
	 * ����ְ���Ÿ����꼶���εĹ�Ͻ�꼶
	 */
	public List<HashMap<String,String>>getQyfdyxx(String zgh){
		DAO dao=DAO.getInstance();
		String sql="select yqqdm,yqmc yqqmc from ghxy_qyfdyb a,yqdmb b where a.yqqdm=b.yqdm and zgh =?";
		return dao.getList(sql, new String[]{zgh}, new String[]{"yqqdm","yqqmc"});
	}
}
