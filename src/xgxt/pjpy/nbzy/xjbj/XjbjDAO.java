
package xgxt.pjpy.nbzy.xjbj;

import java.util.*;

import xgxt.DAO.DAO;

public class XjbjDAO {

	DAO dao = DAO.getInstance();
	
	public HashMap<String, String> xjbjPrint(String pkValue) {
		return dao
				.getMapNotOut(
						"select xymc,bjmc,xn,bzr,bzxm,tzs,bhgqs,ywcf,bjry,zysj,yxdzbyj,xyyj,xxyj from view_pjpy_xjbjandwmsq where xn||xq||rychdm||bjdm = ?",
						new String[] { pkValue });
	}
}
