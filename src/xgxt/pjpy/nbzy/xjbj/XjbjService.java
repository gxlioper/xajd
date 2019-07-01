
package xgxt.pjpy.nbzy.xjbj;

import java.util.HashMap;

public class XjbjService {

	private XjbjDAO dao;
	
	public HashMap<String, String> xjbjPrint(String pkValue) {
		dao = new XjbjDAO();
		return dao.xjbjPrint(pkValue);
	}
}
