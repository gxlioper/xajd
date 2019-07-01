
package xgxt.pjpy.tablesdao;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.pjpy.tablesmodel.PjpyModel;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 评奖评优DAO公用类，
 *                 包含增，删，,改,查方法</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public abstract class PjpyDAO {

	DAO dao = null;// 数据通用类
	
	/**
	 * 奖学金申请保存
	 * @param jxjsqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public abstract boolean insert(PjpyModel pjpyModel) throws Exception;
	
	public abstract boolean delete(String tableName, String pk, String pkValue, HttpServletRequest request) throws Exception;
	
	/**
	 * 检查数据是否重复
	 * @param tableName, pk, pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataExists(String tableName, String pk, String pkValue) throws Exception {
		dao = DAO.getInstance();
		boolean bFlag = false;
		String sql = "select count(*) num from " + tableName + " where " + pk +"=?";
		String[] resList = dao.getOneRs(sql, new String[]{pkValue}, new String[]{"num"});
		if (resList != null && resList.length==1) {
			bFlag = true;
		}
		return bFlag;
	}
}
