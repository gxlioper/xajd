
package xgxt.pjpy.cqkjxy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 上海工程技术大学违纪处分Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-19</p>
 */
public class PjpyCqkjxyService {

	PjpyCqkjxyDAO dao = new PjpyCqkjxyDAO();
	
	/**
	 * 保存辅导员审核信息
	 * @param cqkjxyfdyshModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveFdyShXs(PjpyCqkjxyFdyshModel cqkjxyfdyshModel, String pk, String pkValue, HttpServletRequest request) throws Exception {
		
		return dao.saveFdyShXs(cqkjxyfdyshModel, pk, pkValue, request);
	}
	
	/**
	 * 根据主键获取查询出来的信息
	 * @return
	 * @throws Exception
	 */
	public String[] getFdyShQry(String pk, String pkVal, String[] colList) throws Exception {
		
		return dao.getFdyShQry(pk, pkVal, colList);
	}
	
	/**
	 * 通过TYPE返回不同的列表
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getChkList(int type) {
		DAO dao = DAO.getInstance();
		return dao.getChkList(type);
	}
}
