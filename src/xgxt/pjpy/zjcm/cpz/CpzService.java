package xgxt.pjpy.zjcm.cpz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class CpzService {

	CpzDAO dao = new CpzDAO();

	/**
	 * @describe 获得学生职务列表
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZwList(String xn, String xq,
			String xydm) {
		return dao.getZwList(xn, xq, xydm);
	}

	/**
	 * @describe 获得学生职务列表
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCpZwList(String xn, String xq,
			String xydm) {
		return dao.getCpZwList(xn, xq, xydm);
	}

	/**
	 * @author luo
	 * @throws SQLException
	 * @describe 保存测评组
	 */
	public boolean saveCpz(CpzModel model) throws SQLException {
		return dao.saveCpz(model);
	}

	/**
	 * @author luo
	 * @describe 获得学期列表
	 */
	public List<HashMap<String, String>> getXqList() {
		return dao.getXqList();
	}
}
