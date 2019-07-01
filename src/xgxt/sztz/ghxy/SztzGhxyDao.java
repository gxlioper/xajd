package xgxt.sztz.ghxy;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class SztzGhxyDao {

	public List<HashMap<String,String>> getBmdm(String userName){
		String sql="select bmdm,bmmc from yhb a,ZXBZ_XXBMDM b where yhm=? and a.szbm=b.bmdm";
		DAO dao=DAO.getInstance();
		return dao.getList(sql, new String[]{userName}, new String[]{"bmdm","bmmc"});
	}
	
	/**
	 * 部门列表
	 */
	public List<HashMap<String,String>>bmList(){
		String sql="select bmdm,bmmc from ZXBZ_XXBMDM";
		DAO dao=DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"bmdm","bmmc"});
	}
}
