package xgxt.qgzx.zgkydx;

import xgxt.DAO.DAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国矿业大学勤工助学管理DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2009-01-04</p>
 */
public class QgzxZgkydxDAO extends DAO {
	
	/**
	 * 检测学生是否存在已经通过的岗位
	 * @param xh
	 * @param shdx
	 * @return boolean
	 * */
	public boolean checkPostPass(String xh, String shdx){
		boolean flag = false;
		String sql = "select count(*) num from view_xsgwxx where " + shdx + " ='通过' and xh=?";
		String count = getOneRs(sql, new String[]{xh}, "num");
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;
		flag = Integer.parseInt(count)>0 ? true : false;
		return flag;
	}

}
