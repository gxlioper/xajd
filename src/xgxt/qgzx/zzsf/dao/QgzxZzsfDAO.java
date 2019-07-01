package xgxt.qgzx.zzsf.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 漳州师范学院勤工助学DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-06-24</p>
 */
public class QgzxZzsfDAO extends DAO {
	
	/**
	 * 获取可申请的岗位列表
	 * */
	public List getGwList(){
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where SHJG='通过' and gzjsrq>=to_char(sysdate,'yyyymmdd')";		
		return getList(sql, new String[]{}, new String[]{"gwdm", "gwdmgwsbsj"});
	}
	/**
	 * 基本信息查询
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getBaseInfo(String xh){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select xh,xm,xb,sfzh,nj,xymc,zymc,bjmc,kh,zzmmmc,ykth,yhkh from view_xsxxb where xh=?";
		map = getMap(sql, new String[]{xh},new String[]{"xh", "xm", "xb", "sfzh", "nj", "xymc", "zymc", "bjmc", "kh", "zzmmmc","yhkh","ykth"});		
		
		map.putAll(getQgzxcs());
		return map;
	}
	
	/**
	 * 勤工助学参数查询
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getQgzxcs(){
		String sql = "select * from gwsqsjb";
		return getMap(sql, new String[]{}, new String[]{"xn", "nd", "xq"});
	}
	
	/**
	 * 判断学生岗位是否存在
	 * @param xh
	 * @param pkV
	 * @return boolean
	 * */
	public boolean checkXsgwExists(String xh, String pkV){
		boolean result = false;
		String sql = "select count(*) num from xsgwxxb where xh=? and gwdm||'-'||gwsbsj=?";
		String num = getOneRs(sql, new String[]{xh, pkV},"num");
		
		num = num == null || "".equalsIgnoreCase(num) ? "0" : num;
		result = Integer.parseInt(num) > 0 ? true : false;
		return result; 
	}
	
	/**
	 * 获取学生岗位信息
	 * @param pkV
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsgwxx(String pkV){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql= "select a.*,a.gwdm||'-'||a.gwsbsj xmdm,xssq sqly,(select zzmmmc from view_xsxxb b where a.xh=b.xh)zzmmmc," +
				"(select kh from view_xsxxb b where a.xh=b.xh)kh,(select ykth from view_xsxxb b where a.xh=b.xh)ykth," +
				"(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh,(select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)sqdw," +
				"(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from view_xsgwxx a where xh||gwdm||sqsj=?";
		String[] output = {"xh","xn","nd","xq","xqmc","xmdm","xm","xb","nj","xymc","zymc","bjmc","gwdm", "gwsbsj", "xssq",
							"sqly", "gzjl", "bz","kh","zzmm","lxdh","sqdw","ykth","yhkh"};
		
		map = getMap(sql, new String[]{pkV}, output);
		return map;
	}
	
	/**
	 * 根据主键获取岗位信息
	 * @param pkV
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwxx(String pkV){
		String sql = "select * from view_gwxx where gwdm||'-'||gwsbsj=?";
		
		return getMap(sql, new String[]{pkV}, new String[]{"gwdm","gwsbsj"});
	}
}
