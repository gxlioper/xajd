package xgxt.pjpy.hntx.zycp;

import xgxt.DAO.DAO;

public class ZycpHndxService {
	
	/**
	 * ͬ��ƽ������
	 * @param xn
	 * @return
	 */
	public boolean tbsj(String xn){
		boolean flag = false;
		String sqlT = "{call pjpy_gdb_pjxfjd(?)}";
		DAO dao = DAO.getInstance();
		try {
			flag = dao.runProcuder(sqlT,new String[]{xn});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
