package xgxt.pjpy.nbty.rych;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class RychDAO {
	public List<HashMap<String, String>> getRychList() {
		/**
		 * ��ȡ�����ƺ��б�
		 */
		DAO dao = DAO.getInstance();
		String sql = "select rychdm dm, rychmc mc from rychdmb";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	public List<HashMap<String, String>> getZzmmList() {
		/**
		 * ��ȡ�����ƺ��б�
		 */
		DAO dao = DAO.getInstance();
		String sql = "select zzmmdm dm, zzmm mc from dmk_zzmm";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}

}
