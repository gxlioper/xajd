package xgxt.pjpy.mjxy.jxj;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class MjxyJxyDao {
	//评选奖，评优列表
	public List<HashMap<String,String>>jxjList(){
		DAO dao=DAO.getInstance();
		return dao.getList("select jxjdm,jxjmc from jxjdmb", new String[]{}, new String[]{"jxjdm", "jxjmc"});
	}
	
	/**
	 * 不及格科目数
	 */
	public List<HashMap<String,String>>getBjgms(String xn,String xh){
		DAO dao=DAO.getInstance();
		String sql="select xh,count(*)bjgms from cjb where xn=? and xh=? and bkcj is not null group by xh";
		return dao.getList(sql, new String[]{xn,xh}, new String[]{"bjgms"});
	}
	
	/**
	 * 综考门数
	 */
	public List<HashMap<String,String>>getZkms(String xn,String xh){
		DAO dao=DAO.getInstance();
		String sql="select xh,count(*)zkms from cjb where xn=? and xh=? group by xh";
		return dao.getList(sql, new String[]{xn,xh}, new String[]{"zkms"});
	}
	
	/**
	 * 该学生所在专业的人数
	 */
	public List<HashMap<String,String>>getZyrs(String xh){
		DAO dao=DAO.getInstance();
		String sql="select count(b.xh)zyrs from xsxxb a  inner join  xsxxb b on a.zydm=b.zydm where a.xh=? ";
		return dao.getList(sql, new String[]{xh}, new String[]{"zyrs"});
	}
}
