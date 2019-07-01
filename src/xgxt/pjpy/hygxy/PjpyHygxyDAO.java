
package xgxt.pjpy.hygxy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������ѧԺ��������DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-13</p>
 */
public class PjpyHygxyDAO {

	DAO dao = null;
	
	/**
	 * ��ѧ������ʱ��
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjsqsj() throws Exception {
		dao = DAO.getInstance();
		return dao.getMap("select jxjsqxn,(select xqmc from xqdzb where xtszb.jxjsqxq=xqdzb.xqdm) jxjsqxq,jxjsqnd from xtszb ",
				new String[] {},
				new String[] { "jxjsqxn", "jxjsqxq", "jxjsqnd" });
	}
	
	/**
	 * ��ѧ������ʱ��
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getPdsj() throws Exception {
		dao = DAO.getInstance();
		return dao.getMap("select jxjpdsj,rychpdsj from pjsjszb",
				new String[] {}, new String[] { "jxjpdsj", "rychpdsj" });
	}
	
	/**
	 * ���潱ѧ������ʱ��
	 * @param jxjpdsj
	 * @param rychpdsj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean savePdsj(String jxjpdsj, String rychpdsj,
			HttpServletRequest request) throws Exception {
		dao = DAO.getInstance();
		String[] jxjList = dao.getOneRs(
				"select jxjsqxn,jxjsqxq,jxjsqnd from xtszb", new String[] {},
				new String[] { "jxjsqxn", "jxjsqxq", "jxjsqnd" });
		dao.runUpdate("delete from pjsjszb", new String[]{});
		return StandardOperation.insert("pjsjszb", new String[] { "jxjpdxn",
				"jxjpdxq", "jxjpdnd", "jxjpdsj", "rychpdsj" }, new String[] {
				jxjList[0], jxjList[1], jxjList[2], jxjpdsj, rychpdsj },
				request);
	}
}
