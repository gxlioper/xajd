
package xgxt.pjpy.hygxy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������ѧԺ��������Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-13</p>
 */
public class PjpyHygxyService {

	PjpyHygxyDAO dao = null;
	
	/**
	 * ��ѧ������ʱ��
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjsqsj() throws Exception {
		dao = new PjpyHygxyDAO();
		return dao.getJxjsqsj();
	}
	
	/**
	 * ��ѧ������ʱ��
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getPdsj() throws Exception {
		dao = new PjpyHygxyDAO();
		return dao.getPdsj();
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
		dao = new PjpyHygxyDAO();
		return dao.savePdsj(jxjpdsj, rychpdsj, request);
	}
}
