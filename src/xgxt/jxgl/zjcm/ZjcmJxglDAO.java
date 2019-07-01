package xgxt.jxgl.zjcm;

import xgxt.DAO.DAO;
import xgxt.jxgl.JxglTyDAO;

public class ZjcmJxglDAO extends JxglTyDAO {

	/**
	 * @describe 同步军训成绩
	 * @author luo
	 */
	public boolean tbJxcj(String xn) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runProcuder("{call jxgl_jxcj_tb(?)}", new String[] { xn });
	}

}
