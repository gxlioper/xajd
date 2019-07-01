package xgxt.xsh.stgl.dekthdqh;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class SjxyDektqhDao {
	public List<HashMap<String,String>>  getXshInfo(String userName){
			DAO dao = DAO.getInstance();
			String sql = "select stmc jbbm,fzr hdfzr,zdls from xsh_stglb where fzr= ?";
			return dao.getList(sql, new String[] {userName}, new String[] { "jbbm","hdfzr","zdls"});
	}
	
	public List<HashMap<String,String>>getXymc(String userDep){
			DAO dao=DAO.getInstance();
			String sql=" select xymc from view_njxyzybj where xydm =?";
			return dao.getList(sql, new String[]{userDep}, new String[]{"xymc"});
	}
}
