package xgxt.szdw.utils;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class GetBmForDwr {
	DAO dao = DAO.getInstance();
	public List<HashMap<String, String>> getBmList(String bmmcT){
		String sql = "";
		sql = "select '--«Î—°‘Ò--' mc  from zxbz_xxbmdm where rownum='1' union all select bmmc mc from zxbz_xxbmdm where bmmc like '"+bmmcT+"%'";
		List<HashMap<String, String>> bmList = dao.getList(sql, new String[]{}, new String[]{"mc"});
		return bmList;
	}
}
