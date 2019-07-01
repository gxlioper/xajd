package xgxt.sxjy.action;

import java.sql.SQLException;
import java.util.HashMap;

import xgxt.DAO.DAO;

public class SxjyService {
	DAO dao = DAO.getInstance();
	public String[] getXymcz() throws SQLException {
		// 获取学院组名称
		String sql = "select distinct xymc,xydm from view_njxyzybj order by xydm";
		return dao.getArray(sql, new String[]{}, "xymc");
	}
	public HashMap<String, String[]> getSjrq(String nd, String xqsj) {
		String sql = "";
		if(xqsj.equalsIgnoreCase("上半年")){
			sql = "select a.lrrq,a.xydm,b.bmmc||a.yf pk from sxjy_hdchb a left join zxbz_xxbmdm b on a.xydm = b.bmdm where nd = ? and yf < 7 and yf > 2 order by yf,xydm";
		}else{
			sql = "select a.lrrq,a.xydm,b.bmmc||a.yf pk from sxjy_hdchb a left join zxbz_xxbmdm b on a.xydm = b.bmdm where nd = ? and yf > 7 and yf < 13 order by yf,xydm";
		}
		return dao.getHashMapList(sql, new String []{nd}, new String []{"pk","lrrq"});
	}
	public HashMap<String, String[]> getSjzjrq(String nd, String xqsj) {
		String sql = "";
		if(xqsj.equalsIgnoreCase("上半年")){
			sql = "select a.lrrq,a.xydm,b.bmmc||a.yf pk from sxjy_hdzjb a left join zxbz_xxbmdm b on a.xydm = b.bmdm where nd = ? and yf < 7 and yf > 2 order by yf,xydm";
		}else{
			sql = "select a.lrrq,a.xydm,b.bmmc||a.yf pk from sxjy_hdzjb a left join zxbz_xxbmdm b on a.xydm = b.bmdm where nd = ? and yf > 7 and yf < 13 order by yf,xydm";
		}
		return dao.getHashMapList(sql, new String []{nd}, new String []{"pk","lrrq"});
	}

}
