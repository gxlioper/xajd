
package xgxt.pjpy.zzdx;

import java.util.HashMap;
import java.util.List;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ݴ�ѧ��������Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-23</p>
 */
public class PjpyZzdxService {

	PjpyZzdxDAO dao = null;
	
	
	/**
	 * ֤���ӡλ��
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public String[] getTopLeftStr(String page) throws Exception {
		dao = new PjpyZzdxDAO();
		return dao.getTopLeftStr(page);
	}
	
	public List<String[]> result(PjpyZzdxModel model) throws Exception {
		dao = new PjpyZzdxDAO();
		return dao.result(model);
	}
	
	public List<HashMap<String, String>> title() throws Exception {
		dao = new PjpyZzdxDAO();
		return dao.title();
	}
}
