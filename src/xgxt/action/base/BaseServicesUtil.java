
package xgxt.action.base;

import javax.servlet.http.HttpSession;

public class BaseServicesUtil {
	
	/**
	 * �ж�һ���û�(userName)�Ƿ�����ĳ����(groupName)
	 * @param userName
	 * @param groupName
	 * @return true:�ǣ�false:����û������������
	 */
	public static boolean checkUserToGroup(String userName,String groupName){
		boolean result = false;
		BaseDAO dao = new BaseDAO();
		result = dao.checkUserToGroup(userName, groupName);
		return result;
	}
	
	/**
	 * �ж��û��Ƿ���ĳ����ɫ
	 * @param userName  �û�����
	 * @param js        ��ɫ����
	 * @return
	 */
	public static boolean checkUserPower(String userName,String js,HttpSession session){
		boolean result = false;
		//�ж��û���Ȩ��
		
		return result;
	}
}
