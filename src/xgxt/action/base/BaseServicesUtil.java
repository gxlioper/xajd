
package xgxt.action.base;

import javax.servlet.http.HttpSession;

public class BaseServicesUtil {
	
	/**
	 * 判断一个用户(userName)是否属于某个组(groupName)
	 * @param userName
	 * @param groupName
	 * @return true:是，false:这个用户不属于这个组
	 */
	public static boolean checkUserToGroup(String userName,String groupName){
		boolean result = false;
		BaseDAO dao = new BaseDAO();
		result = dao.checkUserToGroup(userName, groupName);
		return result;
	}
	
	/**
	 * 判断用户是否是某个角色
	 * @param userName  用户名称
	 * @param js        角色名称
	 * @return
	 */
	public static boolean checkUserPower(String userName,String js,HttpSession session){
		boolean result = false;
		//判断用户的权限
		
		return result;
	}
}
