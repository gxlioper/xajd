package xgxt.dtjs.ntzy.gqtgl;

import java.util.HashMap;

import xgxt.DAO.DAO;

public class NtzyWpjyDao {
	/**
	 * 获取登陆人姓名
	 */
	public HashMap<String,String> getDlrXm(String userName){
		
		DAO dao=DAO.getInstance();
		String sql="select xm from yhb where yhm=?";
		return dao.getMap(sql, new String[]{userName}, new String[]{"xm"});	
	}
	
	/**
	 * 获取学生姓名
	 */
	public HashMap<String,String> getXsXm(String userName){
		DAO dao=DAO.getInstance();
		String sql="select xm from xsxxb where xh=?";
		return dao.getMap(sql, new String[]{userName}, new String[]{"xm"});	
	}
	
	/**
	 * 删除物品表信息
	 * @throws Exception 
	 * 
	 */
	public void delWpbXx() throws Exception{
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append("delete  from ntzy_wpb a where not exists");
		sql.append("(select 1 from ntzy_wpjyb b where a.sqr=b.sqr and a.sqsj=b.sqsj and a.jyrq=b.jyrq)");
		dao.runUpdate(sql.toString(),new String[]{});
	}
	
	
}
