package xgxt.jxgl.zjcm;

import xgxt.DAO.DAO;
import xgxt.jxgl.JxglTyDAO;

public class ZjcmJxglDAO extends JxglTyDAO {

	/**
	 * @describe ͬ����ѵ�ɼ�
	 * @author luo
	 */
	public boolean tbJxcj(String xn) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runProcuder("{call jxgl_jxcj_tb(?)}", new String[] { xn });
	}

}
