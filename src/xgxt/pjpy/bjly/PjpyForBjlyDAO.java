package xgxt.pjpy.bjly;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class PjpyForBjlyDAO  {
	//DAO dao = DAO.getInstance();
	public List<HashMap<String, String>> getjxjlbList() {
		// 获取奖金种类类别
		DAO dao = DAO.getInstance();
		String sql = "select lbdm,lbmc from pjpylbwhb order by lbdm";
		return dao.getList(sql, new String[] {}, new String[] { "lbdm","lbmc" });
	}
	
	public List<HashMap<String, String>> getjxjList() {
		// 获取奖学金类别
		DAO dao = DAO.getInstance();
		String sql = "select lbdm,lbmc from pjpylbwhb order by lbdm";sql = "select jxjdm,jxjmc from jxjdmb_tmp_bjly order by jxjdm";
		return dao.getList(sql, new String[] {}, new String[] {"jxjdm", "jxjmc" });
	}
	
	public boolean doDelete(String tableName,String query, String[] strings){
		DAO dao = DAO.getInstance();
		String sql = "delete from "+tableName+query;
		boolean rs = false;
		try{
			rs = dao.runUpdate(sql, strings);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}

}
