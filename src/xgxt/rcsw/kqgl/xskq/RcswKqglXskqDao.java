package xgxt.rcsw.kqgl.xskq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class RcswKqglXskqDao {
	
	
	/**
	 * 获取请假类型列表
	 */
	public List<HashMap<String, String>> getQjlxList() {
		DAO dao = DAO.getInstance();
		String sql = "select qjlxdm dm, qjlxmc mc from kqgl_xskqdmb";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	public  List<HashMap<String, String>> getGyGly(String xqdm,String lddm) {
		DAO dao = DAO.getInstance();
		String sql = "select yhm gygly from gygl_lzxxb  where xqdm =? and lddm=?";
		return  dao.getArrayList(sql, new String[]{xqdm,lddm}, new String[]{"gygly"});
	}
	
	
	public  boolean isGyGly(String userName) {
		DAO dao = DAO.getInstance();
		String sql = "select count(*)num from gygl_lzxxb  where yhm =?";
		String num = dao.getOneRs(sql, new String[]{userName}, new String("num"));
		if(!"0".equalsIgnoreCase(num)){
			return true;
		}else{
			return false;
		}
	}
	
	public List<HashMap<String,String>>getQsglStu(String xqdm,String lddm){
		DAO dao = DAO.getInstance();
		String sql = "select pk,xh,xm,xb,xymc,zymc,bjmc,xysh,xxsh,fdysh from view_rcsw_kqgl_xskq a where a.xh in (select xh from view_xszsxx where xqdm=? and lddm=?)";
		return dao.getArrayList(sql, new String[]{xqdm,lddm}, new String[]{"pk","xh","xm","xb","xymc","zymc","bjmc","xysh","xxsh","fdysh"});
	}
	
	public List<HashMap<String,String>> getLdByGygly(String userName){
		DAO dao=DAO.getInstance();
		String sql = "select lddm,xqdm from gygl_lzxxb  where yhm =?";
		return dao.getList(sql, new String[]{userName} ,new String[]{"lddm","xqdm"});
	}
	
	
	public List<HashMap<String,String>>getXszsxx(String xh){
		DAO dao = DAO.getInstance();
		String sql="select lddm,xqdm from view_xszsxx where xh=?";
		return dao.getArrayList(sql, new String[]{xh}, new String[]{"lddm","xqdm"});
	}
	
	public List<HashMap<String,String>>getGyglyXm(String gygly){
		DAO dao = DAO.getInstance();
		String sql="select xm gygly from yhb where yhm=?";
		return dao.getArrayList(sql, new String[]{gygly}, new String[]{"gygly"});
	}
	
	
	public List<HashMap<String,String>>getTjList(String sql,String[]inputValue){
		DAO dao = DAO.getInstance();
		return dao.getArrayList(sql, inputValue, new String[]{"xm","xqmc","xymc","zymc","bjmc","xn","xh","qjsj"});
	}
	
}
