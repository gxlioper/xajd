package xgxt.rcgl;

import javax.servlet.http.HttpServletRequest;

import xgxt.daoActionLogic.StandardOperation;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��Ʊ����Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2008-12-17</p>
 */
public class TicketService {
	TicketDAO dao = new TicketDAO();
	
	/**
	 * ɾ��ĳ�����ȫ������
	 * @param tableName
	 * @param request
	 * @return boolean
	 * */
	public boolean deleteAllInfo(String tableName, HttpServletRequest request) throws Exception{
		return StandardOperation.delete("delete from " + tableName + " where 1=1", tableName, request);
	}
}
