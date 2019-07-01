
package xgxt.wjcf.gdby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政学院违纪处分Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-15</p>
 */
public class WjcfGdbyService {

	WjcfGdbyDAO dao = null;
	
	/**
	 * 获取处分类别列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCflbList() throws Exception {
		dao = new WjcfGdbyDAO();
		return dao.getCflbList();
	}
	
	/**
	 * 获取处分原因列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfyyList() throws Exception {
		dao = new WjcfGdbyDAO();
		return dao.getCfyyList();
	}
	
	/**
	 * 获取用户查询表头（学院，学校，学生处）
	 * @param userType
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfshTitle(String userType, String userName) throws Exception {
		dao = new WjcfGdbyDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		boolean bFlag = dao.chkUserExists(userName);
		if (StringUtils.isEqual(userType, "xy")) {//学院
			topList = dao.getWjcfshTitleByXy();
		}else if (StringUtils.isEqual(userType, "xx")) {
			if (bFlag) {//校领导
				topList = dao.getWjcfshTitleByXx();
			} else {//学生处
				topList = dao.getWjcfshTitleByXsc();
			}
		}else if (StringUtils.isEqual(userType, "admin")) {//管理员用户
			topList = dao.getWjcfshTitleByXsc();
		}
		if (StringUtils.isEqual(userName, "zf01")) {
			topList = dao.getAdminQryTitle();
		}
		return topList;
	}
	
	/**
	 * 获取用户查询结果（学院，学校，学生处）
	 * @param userType
	 * @param userName
	 * @param wjcfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWjcfshResult(String userType, String userName, WjcfShModel wjcfModel) throws Exception {
		dao = new WjcfGdbyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		boolean bFlag = dao.chkUserExists(userName);
		if (StringUtils.isEqual(userType, "xy")) {//学院
			resList = dao.getWjcfshResultByXy(wjcfModel);
		} else if (StringUtils.isEqual(userType, "xx")) {
			if (bFlag) {//校领导
				resList = dao.getWjcfshResultByXnd(wjcfModel);
			} else {//学生处
				resList = dao.getWjcfshResultByXsc(wjcfModel);
			}
		} else if (StringUtils.isEqual(userType, "admin")) {//管理员用户
			resList = dao.getWjcfshResultByXsc(wjcfModel);
		}
		if (StringUtils.isEqual(userName, "zf01")) {
			resList = dao.getAdminQryResult(wjcfModel);
		}
		return resList;
	}
	
	/**
	 * 违纪处分单个审核显示（学院，学校，学生处）
	 * @param userType
	 * @param userName
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWjcfShView(String userType, String userName, String pkValue) throws Exception {
		dao = new WjcfGdbyDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		boolean bFlag = dao.chkUserExists(userName);
		if (StringUtils.isEqual(userType, "xy")) {//学院
			resMap = dao.wjcfShViewByXy(pkValue);
		}
		if (StringUtils.isEqual(userType, "xx")) {
			if (bFlag) {//校领导
				resMap = dao.wjcfShViewByXnd(pkValue);
			} else {//学生处
				resMap = dao.wjcfShViewByXsc(pkValue);
			}
		}
		if (StringUtils.isEqual(userType, "admin") || StringUtils.isEqual(userName, "zf01")) {
			resMap = dao.wjcfShViewByXsc(pkValue);
		}
		return resMap;
	}
	
	/**
	 * 获取审核列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int type) throws Exception {
		dao = new WjcfGdbyDAO();
		return dao.getChkList(type);
	}
	
	/**
	 * 单个审核
	 * @param shModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean shResult(String userType, String userName, ShResultModel shModel, HttpServletRequest request) throws Exception {
		dao = new WjcfGdbyDAO();
		boolean bFlag = false;
		boolean bExists = dao.chkUserExists(userName);
		if (StringUtils.isEqual(userType, "xy")) {//学院
			bFlag = dao.shResultByXy(shModel, request);
		} else if (StringUtils.isEqual(userType, "xx")) {
			if (bExists) {//校领导
				bFlag = dao.shResultByXnd(shModel, request);
			} else {//学生处
				bFlag = dao.shResultByXsc(shModel, request);
			}
		} else if (StringUtils.isEqual(userType, "admin") || StringUtils.isEqual(userName, "zf01")) {//管理员用户不作任何修改
			bFlag = dao.shResultByXsc(shModel, request);
		}
		return bFlag;
	}
	
	/**
	 * 检查数据是否重复
	 * @param shModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataExists(ShResultModel shModel, String pkValue) throws Exception {
		dao = new WjcfGdbyDAO();
		return dao.chkDataExists(shModel, pkValue);
	}
	
	/**
	 * 获取班主任所带班级列表
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFdybjList(String userName)
			throws Exception {
		dao = new WjcfGdbyDAO();
		return dao.getFdybjList(userName);
	}
	
	public List<HashMap<String, String>> getJxjxdmList() throws Exception {
		dao = new WjcfGdbyDAO();
		return dao.getJxjxdmList();
	}
	
	public List<HashMap<String, String>> getZyList(String xydm) {
		dao = new WjcfGdbyDAO();
		return dao.getZyList(xydm);
	}
	
	public List<HashMap<String, String>> getBjList(String xydm, String zydm, String nj) {
		dao = new WjcfGdbyDAO();
		return dao.getBjList(xydm, zydm, nj);
	}
}
