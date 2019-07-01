package xgxt.qgzx.hzzy;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;

public class QgzxHzzyDAO {

	DAO dao = DAO.getInstance();
	
	ArrayList<String> values = null;
	
	public HashMap<String, String> getXnnd() throws Exception {
		return dao.getMap("select dqxn xn,dqxq xq,dqnd nd from xtszb", new String[]{}, new String[]{"xn","xq","nd"});
	}
}
