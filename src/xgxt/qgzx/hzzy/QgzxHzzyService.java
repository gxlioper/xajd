package xgxt.qgzx.hzzy;

import java.util.HashMap;

public class QgzxHzzyService {

	QgzxHzzyDAO dao = null;
	
	public HashMap<String, String> getXnnd() throws Exception {
		dao = new QgzxHzzyDAO();
		return dao.getXnnd();
	}
}
