
package xgxt.pjpy.hzy;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class DwrPjpyMethods {
	public List<HashMap<String, String>> getBjStus(String bjdm){
		DAO dao = DAO.getInstance();
		String sql = "select xh,xm from view_xsjbxx where bjdm=? order by xh";
		return dao.getList(sql, new String[]{bjdm}, new String[]{"xh","xm"});
	}
}
