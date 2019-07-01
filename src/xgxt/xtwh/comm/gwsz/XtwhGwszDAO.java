package xgxt.xtwh.comm.gwsz;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.form.User;

public class XtwhGwszDAO {
	
	/**
	 * 根据用户名查询出用户信息
	 * @param user
	 * @return HashMap<String,String>
	 * author qlj
	 */
	public HashMap<String,String>getYhInfo(User user){
		DAO dao=DAO.getInstance();
		String userName=user.getUserName();
		String sql=" select * from yhb where yhm=? ";
		return dao.getMap(sql, new String[]{userName}, new String[]{"yhm","zdm","xm","szbm","dwdm"});
	}
}
