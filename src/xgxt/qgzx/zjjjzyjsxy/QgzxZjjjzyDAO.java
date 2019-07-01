package xgxt.qgzx.zjjjzyjsxy;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.utils.GetTime;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块浙江经济职业技术学院DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-12-03</p>
 */
public class QgzxZjjjzyDAO extends DAO{
	
	/**
	 * 获取学生信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuInfo(String xh ){
		String[] outputValue = {"xh","xm","xb","nj","xymc","zymc","bjmc","jg","zzmmmc","mzmc","lxdh"};
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,jg,zzmmmc,mzmc,lxdh from view_xsxxb where xh=?";
		
		return getMap(sql, new String[]{xh}, outputValue);		
	}
	
	/**
	 * 获取当前时间
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getCurrTime(){
		HashMap<String, String> timeMap = new HashMap<String, String>();
		timeMap.put("year", GetTime.getNowYear()) ;
		timeMap.put("day", GetTime.getNowDay());
		timeMap.put("month", GetTime.getNowMonth());
		return timeMap;
	}
}
