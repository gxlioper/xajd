
package xgxt.pjpy.hygxy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������ѧԺ�������Ž�ѧ��������SERVICE</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-13</p>
 */
public class PjpyHygxyJxjlbdmService {

	PjpyHygxyJxjlbdmDAO dao = null;
	
	/**
	 * ���潱ѧ��������
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjlbdm(JxjlbdmModel model, HttpServletRequest request)
			throws Exception {
		dao = new PjpyHygxyJxjlbdmDAO();
		return dao.saveJxjlbdm(model, request);
	}
	
	/**
	 * �޸Ľ�ѧ��������
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjlbdm(JxjlbdmModel model, HttpServletRequest request)
			throws Exception {
		dao = new PjpyHygxyJxjlbdmDAO();
		return dao.updateJxjlbdm(model, request);
	}
	
	/**
	 * ��ѧ���������޸���ʾ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjlbdmDetails(String pkValue)
			throws Exception {
		dao = new PjpyHygxyJxjlbdmDAO();
		return dao.getJxjlbdmDetails(pkValue);
	}
}
