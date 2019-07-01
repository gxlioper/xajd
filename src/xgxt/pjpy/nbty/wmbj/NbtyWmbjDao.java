package xgxt.pjpy.nbty.wmbj;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class NbtyWmbjDao {
	
	
	public List<HashMap<String,String>> isBgb(String xh)
	{
		DAO dao = DAO.getInstance();
		String sql = "select bjdm from sxjy_bjgbxxb where xh= ?";
		return dao.getList(sql, new String[] {xh}, new String[] { "bjdm"});
	}
}
