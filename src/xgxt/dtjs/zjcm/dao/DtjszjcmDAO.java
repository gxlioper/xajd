package xgxt.dtjs.zjcm.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class DtjszjcmDAO {

	public HashMap<String, String> swclQueryOne(String[] colList,
			String pkValue, HashMap<String, String> map, String sql) {
		DAO dao = DAO.getInstance();
		// 通过主键查询单个数据用 获得HashMap<String, String>形式,并覆盖之前传入的HashMap里的值的通用方法
		int size = colList.length - 1;

		String[] rsTmp = dao.getOneRs(sql, new String[] { pkValue }, colList);
		for (int i = 0; i <= size; i++) {
			map.put(colList[i], (rsTmp != null) ? rsTmp[i] : "");

		}

		return map;
	}

	public List<HashMap<String, String>> getClList() {
		DAO dao = DAO.getInstance();
		// 获取材料类型列表
		String sql = "select distinct cldm,clmc from cllx order by cldm";
		return dao.getList(sql, new String[] {},
				new String[] { "cldm", "clmc" });
	}
	
	public List<HashMap<String, String>> getJclxList() {
		DAO dao = DAO.getInstance();
		// 获取奖惩类型列表
		String sql = "select distinct lxdm,lxmc from zjcm_jclx order by lxdm";
		return dao.getList(sql, new String[] {},
				new String[] { "lxdm", "lxmc" });
	}
	
	public List<HashMap<String, String>> getJclyList() {
		DAO dao = DAO.getInstance();
		// 获取奖惩理由列表
		String sql = "select distinct lydm,lymc from zjcm_jcly order by lydm";
		return dao.getList(sql, new String[] {},
				new String[] { "lydm", "lymc" });
	}
}
