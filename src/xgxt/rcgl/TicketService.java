package xgxt.rcgl;

import javax.servlet.http.HttpServletRequest;

import xgxt.daoActionLogic.StandardOperation;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 车票管理Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2008-12-17</p>
 */
public class TicketService {
	TicketDAO dao = new TicketDAO();
	
	/**
	 * 删除某个表的全部数据
	 * @param tableName
	 * @param request
	 * @return boolean
	 * */
	public boolean deleteAllInfo(String tableName, HttpServletRequest request) throws Exception{
		return StandardOperation.delete("delete from " + tableName + " where 1=1", tableName, request);
	}
}
