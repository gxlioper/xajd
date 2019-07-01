package xgxt.studentInfo.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.studentInfo.model.StudentInfoForm;

public class XsxxTjszDAO extends CommDAO{

	/**
	 * 获得数据库配置字段信息
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDbPzList(StudentInfoForm model) {

		DAO dao = DAO.getInstance();

		String sql = "select zd,zdm,zdz,xsmc from xsxx_tjsz_sznr order by to_number(sx)";

		List<HashMap<String, String>> list = dao.getList(sql, new String[] {},
				new String[] { "zd", "zdm", "zdz","xsmc" });

		return list;
	}
}
