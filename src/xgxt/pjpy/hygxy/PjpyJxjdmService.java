
package xgxt.pjpy.hygxy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������ѧԺ�������Ž�ѧ�����Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-13</p>
 */
public class PjpyJxjdmService extends PjpyHygxyService{

	PjpyJxjdmDAO dao = null;
	
	/**
	 * ��ѧ�����
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjlbList() throws Exception {
		dao = new PjpyJxjdmDAO();
		return dao.getJxjlbList();
	}
	
	/**
	 * ��ѧ����뱣��
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjdmSave(PjpyJxjdmModel model, HttpServletRequest request) throws Exception {
		dao = new PjpyJxjdmDAO();
		return dao.jxjdmSave(model, request);
	}
	
	/**
	 * ��ѧ���޸���ʾ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewJxjdm(String pkValue) throws Exception {
		dao = new PjpyJxjdmDAO();
		return dao.viewJxjdm(pkValue);
	}
	
	public boolean updateJxjdm(PjpyJxjdmModel model, HttpServletRequest request) throws Exception {
		dao = new PjpyJxjdmDAO();
		return dao.updateJxjdm(model, request);
	}
}
