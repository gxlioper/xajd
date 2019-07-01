package xgxt.rcgl.zjjdzyjsxy.service;

import java.util.HashMap;

import xgxt.rcgl.zjjdzyjsxy.dao.RcswXfcjDAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江机电日常事务模块Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-02</p>
 */
public class RcswXfcjService {
	
	/**
	 * 学费催缴中取学费缓交的信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXfhjInfo(String xh){
		RcswXfcjDAO dao = new RcswXfcjDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getXfhjInfo(xh);
		return map;
	}
}
