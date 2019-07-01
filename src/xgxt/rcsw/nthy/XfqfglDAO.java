package xgxt.rcsw.nthy;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class XfqfglDAO {
	
	DAO dao = DAO.getInstance();
	
	/**
	 * 下拉列表选项维护
	 * 
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("sfqf".equalsIgnoreCase(lx)) {
			dm = new String[] { "0","1" };
			mc = new String[] { "已交清" ,"未交清"};
		} 
		return dao.arrayToList(dm, mc);
	}

}
