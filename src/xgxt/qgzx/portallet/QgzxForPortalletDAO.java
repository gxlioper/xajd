package xgxt.qgzx.portallet;


import java.util.List;

import xgxt.DAO.DAO;
import xgxt.qgzx.form.QgzxForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学提供Portal查询DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-04-08</p>
 */
public class QgzxForPortalletDAO extends DAO {
	/**
	 * 获取学生勤工助学信息
	 * @param model
	 * @return List
	 * */
	public List getXscjffxx(QgzxForm model){
		String[] inputValue = {model.getXh()};
		String[] outputValue = {"nd", "yf", "gwdm", "gzsj", "cjje"};
		String sql = "select a.* from (select nd,yf,gwdm,gzsj,cjje from xscjffb where xh=? order by nd desc)a where rownum<6";
		
		return  rsToVator(sql, inputValue, outputValue);
	}
}
