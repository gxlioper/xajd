package xgxt.rcgl;

import java.util.HashMap;

import xgxt.DAO.DAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 车票管理DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2008-12-17</p>
 */
public class TicketDAO extends DAO {
	
	/**
	 * 判断是否在乘车时间限制内
	 * @param ccrq
	 * @return boolean
	 * */
	public boolean ccrqCheck(String ccrq){		
		boolean flag = false;
		ccrq = ccrq == null || "".equalsIgnoreCase(ccrq) ? "0" : ccrq;
		ccrq = ccrq.trim();
		ccrq = ccrq + "235959";//23点59分59秒
		
		//获取设定的限制时间
		String sql = "select * from cpydsjb";
		String[] outputValue = {"cckssj", "ccjssj"};
		HashMap<String, String> map = new HashMap<String, String>();
		
		map = getMap(sql, new String[]{}, outputValue);
		//时间对比
		flag = Double.parseDouble(map.get("cckssj"))> Double.parseDouble(ccrq) || Double.parseDouble(map.get("ccjssj"))<Double.parseDouble(ccrq) ? false : true;
		
		return flag;
	}
}
