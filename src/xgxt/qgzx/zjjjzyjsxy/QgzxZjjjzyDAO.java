package xgxt.qgzx.zjjjzyjsxy;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.utils.GetTime;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ڹ���ѧģ���㽭����ְҵ����ѧԺDAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-12-03</p>
 */
public class QgzxZjjjzyDAO extends DAO{
	
	/**
	 * ��ȡѧ����Ϣ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuInfo(String xh ){
		String[] outputValue = {"xh","xm","xb","nj","xymc","zymc","bjmc","jg","zzmmmc","mzmc","lxdh"};
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,jg,zzmmmc,mzmc,lxdh from view_xsxxb where xh=?";
		
		return getMap(sql, new String[]{xh}, outputValue);		
	}
	
	/**
	 * ��ȡ��ǰʱ��
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
