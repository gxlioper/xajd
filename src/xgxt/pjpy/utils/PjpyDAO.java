package xgxt.pjpy.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class PjpyDAO {

	@SuppressWarnings("unused")
	private DAO dao = DAO.getInstance();
	
	public static PjpyDAO mydao = new PjpyDAO();
	
	public static PjpyDAO getInstance() {
		return mydao;
	}
	
	/**
	 * 传入两个数组返回查询表头,英文在前,中文在后
	 * @param enList
	 * @param cnList
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQryTitle(String[] enList,
			String[] cnList) throws Exception {
		List<HashMap<String, String>> titList = new ArrayList<HashMap<String, String>>();
		if (enList != null && cnList != null && enList.length == cnList.length) {
			for (int i = 0; i < enList.length; i++) {
				HashMap<String, String> tmpMap = new HashMap<String, String>();
				tmpMap.put("en", enList[i]);
				tmpMap.put("cn", cnList[i]);
				titList.add(tmpMap);
			}
		}
		return titList;
	}
}
