package xgxt.qgzx.portallet;

import java.util.List;

import xgxt.qgzx.form.QgzxForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学提供Portal查询Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-04-08</p>
 */

public class QgzxForPortalletService {
	QgzxForPortalletDAO dao = new QgzxForPortalletDAO();
	
	/**
	 * 获取学生勤工助学信息
	 * @param model
	 * @return List
	 * */
	public List getXscjffxx(QgzxForm model){		
		return dao.getXscjffxx(model);
	}
	
	/**
	 * 获取学生酬金发放信息表头
	 * @return List
	 * */
	public List getXscjffTortr(){
		String[] col = {"nd", "yf", "gwdm", "gzsj", "cjje"};
		String[] colCN = {"年度", "月份", "岗位名称", "工作时间", "酬金金额"};
		return dao.arrayToList(col, colCN);
	}
}
