package xsgzgl.xtwh.jhzy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
/**
 * ��ְҵϵͳά��
 * @author yeyipin
 */
public class XtwhService {
	private XtwhDAO myDao = new XtwhDAO();
	/**
	 * �����String
	 * @param type
	 * @return
	 */
	public String getGroupToSplit() {
		return myDao.getGroupToSplit();
	}
	/**
	 * �����LIST
	 * @param type
	 * @return
	 */
	public List<HashMap<String,String>> getGroupList(){
		return myDao.getGroupList();
	}
	/**
	 * ��õ�ǰ�û��µ����й��ܴ���ģ��String
	 * @param userName
	 * @return
	 */
	public String getPowerToSplit(String userName) {
		return myDao.getPowerToSplit(userName);
	}
	/**
	 * ����û���ϢLIST
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getUserxxList(String type) {
		if("fdy".equalsIgnoreCase(type)){
			return myDao.getFdyxxList();
		}else if("bzr".equalsIgnoreCase(type)){
			return myDao.getBzrxxList();
		}
		return null;
	}
	/**
	 * ��ù���Ȩ��List
	 * @param userID
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getPowerList(String userID,
			String userName,String type) {
		return myDao.getPowerList(userID,userName,type);
	}
	/**
	 * ���渨��Ա������
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveFdyssz(HttpServletRequest request) throws Exception {
		return myDao.saveFdyssz(request);
		
	}
	/**
	 * ���������������
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveBzrssz(HttpServletRequest request) throws Exception {
		return myDao.saveBzrssz(request);
		
	}
	/**
	 * ���渨��ԱȨ��
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveFdyqx(HttpServletRequest request) throws Exception {
		return myDao.saveFdyqx(request);
	}
	/**
	 * ���������Ȩ��
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveBzrqx(HttpServletRequest request) throws Exception {
		return myDao.saveBzrqx(request);
	}

}
