
package xgxt.wjcf.csmz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;


import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政学院违纪处分service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-05</p>
 */
public class WjcfCsmzService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	WjcfCsmzDAO dao = null;//数据操作DAO

	/**
	 * 根据用户类型来查询不同的表头
	 * @param userType
	 * @param userName
	 * @param isFdy
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle(String userType,
			String isFdy) throws Exception {
		dao = new WjcfCsmzDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校用户
			topList = dao.getSearchTitle(3);
		} else if ((StringUtils.isEqual(userType, "xy"))
				&& (StringUtils.isEqual(isFdy, "false"))) {//学院用户
			topList = dao.getSearchTitle(2);
		}//end if
		if (StringUtils.isEqual(isFdy, "true")) {//辅导员
			topList = dao.getSearchTitle(1);
		}//end if
		return topList;
	}
	
	/**
	 * 根据用户类型来查询不同的结果
	 * @param userType
	 * @param isFdy
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSearchResult (String userName, String userType, String isFdy, WjcfCsmzCxcfModel wjcfcsmzcxcfModel) throws Exception {
		dao = new WjcfCsmzDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
			
			if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校用户
				resList = dao.getSearchResult(userName, 3, wjcfcsmzcxcfModel);
			} else if ((StringUtils.isEqual(userType, "xy"))
					&& (StringUtils.isEqual(isFdy, "false"))) {//学院用户
				resList = dao.getSearchResult(userName, 2, wjcfcsmzcxcfModel);
			}
			if (StringUtils.isEqual(isFdy, "true")) {//辅导员
				resList = dao.getSearchResult(userName, 1, wjcfcsmzcxcfModel);
			}//end if
		}else{
			if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校用户
				resList = dao.getSearchResult(userName, 3, wjcfcsmzcxcfModel);
			} else if ((StringUtils.isEqual(userType, "xy"))
					&& (StringUtils.isEqual(isFdy, "false"))) {//学院用户
				resList = dao.getSearchResult(userName, 2, wjcfcsmzcxcfModel);
			}//end if
			if (StringUtils.isEqual(isFdy, "true")) {//辅导员
				resList = dao.getSearchResult(userName, 1, wjcfcsmzcxcfModel);
			}//end if
		}
		return resList;
	}
	
	/**
	 * 重庆工程职业
	 * @param userType
	 * @param isFdy 
	 * @param isBzr
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSearchResult (User user, WjcfCsmzCxcfModel wjcfcsmzcxcfModel) throws Exception {
		dao = new WjcfCsmzDAO();
		List<String[]> resList = new ArrayList<String[]>();
		
		String userType=user.getUserType();
		
		String userName=user.getUserName();
		
		String isFdy=user.getFdyQx();
		
		String isBzr=user.getBzrQx();
		
		wjcfcsmzcxcfModel.setUser(user);
		
		//学校用户
		if (StringUtils.isEqual(isFdy, "true")|| StringUtils.isEqual(isBzr, "true")) {//辅导员
			resList = dao.getSearchResult(userName, 1, wjcfcsmzcxcfModel);
		} else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {
			resList = dao.getSearchResult(userName, 3, wjcfcsmzcxcfModel);
		} else if (StringUtils.isEqual(userType, "xy")) {//学院用户
			resList = dao.getSearchResult(userName, 2, wjcfcsmzcxcfModel);
		}
	
		return resList;
	}
	
	/**
	 * 获取审核列表
	 * @param userType
	 * @param isFdy
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getList() throws Exception {
		dao = new WjcfCsmzDAO();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		list = dao.getList(4);
		return list;
	}
	
	/**
	 * 查询撤消处分信息
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCxcfQryRestlt(String userType, String isFdy, String pkVal) throws Exception {
		dao = new WjcfCsmzDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校用户
	
			resMap = dao.getCxcfQryResult(3, pkVal);
		} else if ((StringUtils.isEqual(userType, "xy"))
				&& (StringUtils.isEqual(isFdy, "false"))) {//学院用户
			resMap = dao.getCxcfQryResult(2, pkVal);
		}//end if
		if (StringUtils.isEqual(isFdy, "true")) {//辅导员
			resMap = dao.getCxcfQryResult(1, pkVal);
		}//end if
		
		return resMap;
	}
	
	/**
	 * 查询撤消处分信息
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCxcfQryRestlt(User user, String pkVal) throws Exception {
		dao = new WjcfCsmzDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		
		String userType=user.getUserType();
		String isFdy=user.getFdyQx();
		String isBzr=user.getBzrQx();
		
		if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
			if (StringUtils.isEqual(isFdy, "true") || StringUtils.isEqual(isBzr, "true")) {//辅导员
				
				resMap = dao.getCxcfQryResult(1, pkVal);
			
			}else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校用户
				
				resMap = dao.getCxcfQryResult(3, pkVal);
			
			}else  if (StringUtils.isEqual(userType, "xy")) {//学院用户
				resMap = dao.getCxcfQryResult(2, pkVal);
			}//end if
			//end if
		}
		return resMap;
	}
	
	/**
	 * 撤消处分审核信息保存
	 * @param userType
	 * @param isFdy
	 * @param pkVal
	 * @param wjcfsmzcxcfsaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfInfo(String userType, String isFdy, String pkVal,
			WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel,
			HttpServletRequest request) throws Exception {
		dao = new WjcfCsmzDAO();
		boolean bFlag = false;
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校用户
			bFlag = dao.saveCxcfInfo(3, wjcfsmzcxcfsaveModel, request, pkVal);
		} else if ((StringUtils.isEqual(userType, "xy"))
				&& (StringUtils.isEqual(isFdy, "false"))) {//学院用户
			bFlag = dao.saveCxcfInfo(2, wjcfsmzcxcfsaveModel, request, pkVal);
		}//end if
		if (StringUtils.isEqual(isFdy, "true")) {//辅导员
			bFlag = dao.saveCxcfInfo(1, wjcfsmzcxcfsaveModel, request, pkVal);
		}//end if
		return bFlag;
	}
	
	/**
	 * 撤消处分审核信息保存
	 * @param userType
	 * @param isFdy
	 * @param pkVal
	 * @param wjcfsmzcxcfsaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfInfo(User user, String pkVal,
			WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel,
			HttpServletRequest request) throws Exception {
		dao = new WjcfCsmzDAO();
		boolean bFlag = false;
		String userType=user.getUserType();
		String isFdy=user.getFdyQx();
		String isBzr=user.getBzrQx();
		
		if(StringUtils.isEqual(isFdy, "true") || StringUtils.isEqual(isBzr, "true")) {//辅导员
			
			bFlag = dao.saveCxcfInfo(1, wjcfsmzcxcfsaveModel, request, pkVal);
		
		}else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校用户
			
			bFlag = dao.saveCxcfInfo(3, wjcfsmzcxcfsaveModel, request, pkVal);
		
		}else if (StringUtils.isEqual(userType, "xy")) {//学院用户
			
			bFlag = dao.saveCxcfInfo(2, wjcfsmzcxcfsaveModel, request, pkVal);
		
		}//end if
		return bFlag;
	}
	
	
	/**
	 * 批量审核学生撤销处分信息
	 * @param userType
	 * @param isFdy
	 * @param pkVal
	 * @param wjcfsmzcxcfsaveModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfInfoPl(String userType, String isFdy, WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel) throws Exception {
		dao = new WjcfCsmzDAO();
		boolean bFlag = false;
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校用户
			bFlag = dao.saveCxcfInfoPl(3, wjcfsmzcxcfsaveModel);
		} else if ((StringUtils.isEqual(userType, "xy"))
				&& (StringUtils.isEqual(isFdy, "false"))) {//学院用户
			bFlag = dao.saveCxcfInfoPl(2, wjcfsmzcxcfsaveModel);
		}//end if
		if (StringUtils.isEqual(isFdy, "true")) {//辅导员
			bFlag = dao.saveCxcfInfoPl(1, wjcfsmzcxcfsaveModel);
		}//end if
		return bFlag;
	}
	
	/**
	 * 批量审核学生撤销处分信息
	 * @param userType
	 * @param isFdy
	 * @param pkVal
	 * @param wjcfsmzcxcfsaveModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfInfoByCqgc(String userType, String isFdy,String isBzr, WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel) throws Exception {
		dao = new WjcfCsmzDAO();
		boolean bFlag = false;
	
		if (StringUtils.isEqual(isFdy, "true") || StringUtils.isEqual(isBzr, "true")) {//辅导员
			
			bFlag = dao.saveCxcfInfoPl(1, wjcfsmzcxcfsaveModel);
		
		}else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校用户
			
			bFlag = dao.saveCxcfInfoPl(3, wjcfsmzcxcfsaveModel);
		
		}else if (StringUtils.isEqual(userType, "xy")) {//学院用户
			bFlag = dao.saveCxcfInfoPl(2, wjcfsmzcxcfsaveModel);
		}
		
		return bFlag;
	}
	
	/**
	 * 通过学号获取学生的相关信息（姓名，班级，专业，学院，年级，性别）
	 * 通过违纪文号获取学生违纪信息（时间，处分类别，处分原因，学年，学期）
	 * @param xh,pkval
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh, String pkVal) throws Exception {
		dao = new WjcfCsmzDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = dao.getStuInfo(xh, pkVal);
		return resMap;
 	}
	
	/**
	 * 保存撤消处分信息
	 * @param cxcfSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfSqlInfo(CxcfSqSaveModel cxcfSaveModel, HttpServletRequest request) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.saveCxcfSqlInfo(cxcfSaveModel, request);
	}
	
	/**
	 * 保存前检查学生处分时间是否满一年
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean chkStuTj(String cfsj) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.chkStuTj(cfsj);
	}
	
	/**
	 * 撤消处分查询表头
	 * cxcfqrytit -----撤消处分查询表头
	 * @param cxcfModel
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxcfQryTit(WjcfCsmzCxcfModel cxcfModel, String xxdm) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.getCxcfQryTit(cxcfModel, xxdm);
	}
	
	/**
	 * 撤消处分查询表头
	 * cxcfqryres -----撤消处分查询结果集
	 * @param cxcfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxcfQryRes(WjcfCsmzCxcfModel cxcfModel, String xxdm) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.getCxcfQryRes(cxcfModel, xxdm);
	}
	
	/**
	 * 批量删除撤消处分信息
	 * cxcfinfo----撤消处分信息
	 * @param cvb
	 * @return
	 * @throws Exception
	 */
	public String delCxcfInfo(String[] cvb) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.delCxcfInfo(cvb);
	}
	
	/**
	 * 撤消处分查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> stuCxcfTitle(String xxdm) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.stuCxcfTitle(xxdm);
	}
	
	/**
	 * 撤消处分查询结果
	 * @return
	 * @throws Exception
	 */
	public List<String[]> stuCxcfResult(String xh, String xxdm) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.stuCxcfResult(xh, xxdm);
	}
	
	/**
	 * 通过主键获取撤消处分信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> stuInfoByPk(String pkValue) throws Exception{
		dao = new WjcfCsmzDAO();
		return dao.stuInfoByPk(pkValue);
	}
	
	/**
	 * 添加撤销处分信息   重庆工程职业技术学院
	 * @return
	 */
	public boolean updateCxcfInfo(WjcfCsmzCxcfSaveModel model) throws Exception{
		String sfcx="是";
		if(model!=null && "通过".equals(model.getSh())){
			return dao.updateCxcfInfo(model, sfcx);
		}else{
			sfcx="否";
			model.setJcsj("");
			return dao.updateCxcfInfo(model, sfcx);
		}
	}
	
	public boolean update(String pkValue, String bz, HttpServletRequest request) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.update(pkValue, bz, request);
	}
	
	public HashMap<String, String> getcfInfo(String pkValue) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.getcfInfo(pkValue);
	}
}

