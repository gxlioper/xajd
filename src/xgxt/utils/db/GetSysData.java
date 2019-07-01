package xgxt.utils.db;

import xgxt.DAO.DAO;

public class GetSysData {

	
	/**
	 * ªÒ»°guid
	 * @return
	 */
	public static String getGuid(){
		DAO dao = DAO.getInstance();
		String sql = "select sys_guid() id from dual";
		
		return dao.getOneRs(sql, new String[]{}, "id");
	}
}
