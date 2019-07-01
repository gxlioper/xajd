package xsgzgl.xtwh.jhzy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
/**
 * 金华职业系统维护
 * @author yeyipin
 */
public class XtwhService {
	private XtwhDAO myDao = new XtwhDAO();
	/**
	 * 获得组String
	 * @param type
	 * @return
	 */
	public String getGroupToSplit() {
		return myDao.getGroupToSplit();
	}
	/**
	 * 获得组LIST
	 * @param type
	 * @return
	 */
	public List<HashMap<String,String>> getGroupList(){
		return myDao.getGroupList();
	}
	/**
	 * 获得当前用户下的所有功能代码模块String
	 * @param userName
	 * @return
	 */
	public String getPowerToSplit(String userName) {
		return myDao.getPowerToSplit(userName);
	}
	/**
	 * 获得用户信息LIST
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
	 * 获得功能权限List
	 * @param userID
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getPowerList(String userID,
			String userName,String type) {
		return myDao.getPowerList(userID,userName,type);
	}
	/**
	 * 保存辅导员所属组
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveFdyssz(HttpServletRequest request) throws Exception {
		return myDao.saveFdyssz(request);
		
	}
	/**
	 * 保存班主任所属组
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveBzrssz(HttpServletRequest request) throws Exception {
		return myDao.saveBzrssz(request);
		
	}
	/**
	 * 保存辅导员权限
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveFdyqx(HttpServletRequest request) throws Exception {
		return myDao.saveFdyqx(request);
	}
	/**
	 * 保存班主任权限
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveBzrqx(HttpServletRequest request) throws Exception {
		return myDao.saveBzrqx(request);
	}

}
