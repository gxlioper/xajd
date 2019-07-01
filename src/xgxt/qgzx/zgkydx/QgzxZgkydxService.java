package xgxt.qgzx.zgkydx;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国矿业大学勤工助学管理Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2009-01-04</p>
 */
public class QgzxZgkydxService {
	QgzxZgkydxDAO dao = new QgzxZgkydxDAO();

	/**
	 * 检测学生是否存在已经通过的岗位 
	 * @param xh
	 * @param shdx
	 * @return boolean
	 */
	public boolean checkPostPass(String xh, String shdx) {
		boolean flag = false;
		flag = dao.checkPostPass(xh, shdx);
		return flag;
	}
}
