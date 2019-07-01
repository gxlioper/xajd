
package xgxt.pjpy.cqkjxy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �Ϻ����̼�����ѧΥ�ʹ���Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-19</p>
 */
public class PjpyCqkjxyService {

	PjpyCqkjxyDAO dao = new PjpyCqkjxyDAO();
	
	/**
	 * ���渨��Ա�����Ϣ
	 * @param cqkjxyfdyshModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveFdyShXs(PjpyCqkjxyFdyshModel cqkjxyfdyshModel, String pk, String pkValue, HttpServletRequest request) throws Exception {
		
		return dao.saveFdyShXs(cqkjxyfdyshModel, pk, pkValue, request);
	}
	
	/**
	 * ����������ȡ��ѯ��������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String[] getFdyShQry(String pk, String pkVal, String[] colList) throws Exception {
		
		return dao.getFdyShQry(pk, pkVal, colList);
	}
	
	/**
	 * ͨ��TYPE���ز�ͬ���б�
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getChkList(int type) {
		DAO dao = DAO.getInstance();
		return dao.getChkList(type);
	}
}
