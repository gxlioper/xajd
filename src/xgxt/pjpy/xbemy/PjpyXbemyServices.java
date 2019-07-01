
package xgxt.pjpy.xbemy;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.action.base.BaseServicesUtil;
import xgxt.utils.Arrays2;


public class PjpyXbemyServices {
	private PjpyXbemyDAO dao = null;
	private String jsName = "zf01";//管理员
	/**
	 * @param pjpyModel 用于确定传入参数的model
	 * @return
	 */
	public ArrayList<String[]>  getSbSearchResult(String userName, String userType, PjpyXbemyXysbjxjModel pjpyModel){
		ArrayList<String[]> result = new ArrayList<String[]>();
		dao = new PjpyXbemyDAO();
		if (userType.equalsIgnoreCase("xy")){//学院用户查询结果
			result = dao.getXysbjxjSearch(userName,pjpyModel);
		}else{//学生处，财务处，教务处和管理员查询结果
			result = dao.getShSearch(userName, userType, pjpyModel);
		}
		return result;
	}
	
	/**
	 * @param pjpyModel 用于确定传入参数的model
	 * @return
	 */
	public ArrayList<HashMap<String, String>>  getSbSearchTitle(String userType, String userName){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		dao = new PjpyXbemyDAO();
		char js = "admin".equalsIgnoreCase(userType) && BaseServicesUtil.checkUserToGroup(userName, "学生处") && userName.equalsIgnoreCase(jsName)? 'a': 'o';
		switch(js){
		case 'a':{//管理员查询表头
			result = dao.getAdminShTitle();
			break;
		}
		case 'o':{//其它用户查询表头
			result = dao.getXysbjxjSearchTitle();
			break;
		}
		}
		return result;
	}
	
	/**
	 * 提交审核结果
	 * @param res
	 * @param userName
	 * @param model
	 * @return
	 */
	public boolean submitShResult(String res,String userName,String userType,PjpyXbemyShModel model) throws Exception {
		PjpyXbemyDAO dao                                   = new PjpyXbemyDAO();
		boolean result                                     = false;
		char js = BaseServicesUtil.checkUserToGroup(userName, "教务处")? 'a'               //角色是教务处
					:(BaseServicesUtil.checkUserToGroup(userName, "财务处")? 'b'           //角色是财务处
							: (BaseServicesUtil.checkUserToGroup(userName, "学生处") && !userName.equalsIgnoreCase(jsName)? 'c'  //角色是学生处
									: ("admin".equalsIgnoreCase(userType) && (BaseServicesUtil.checkUserToGroup(userName, "学生处")) && userName.equalsIgnoreCase(jsName)?'d'             //角色是管理员
											:'e')));									  //角色是学院
		String[] keys                                      = model.getCbv();
		String   jxjdm                                     = model.getXmdm();
		String   xn                                        = model.getXn();
		Arrays2.trim(keys);																  //去除keys中每个值的空格
		if("tg".equalsIgnoreCase(res)){//审核通过时
			switch(js){
			case 'a':{//角色是教务处
				dao.jwcshResult(xn, jxjdm, keys, res);
				break;
			}				      
			case 'b':{//角色是财务处
				dao.cwcshResult(xn, jxjdm, keys, res);
				break;
			}
			case 'c':{//角色是学生处
				dao.xscshResult(xn, jxjdm, keys, res);
				break;
			}
			case 'd':{//角色是管理员
				break;
			}
			case 'e':{//角色是学院
				dao.xyshResult(xn, jxjdm, keys, res);
				break;
			}
			}
		} else if ("btg".equalsIgnoreCase(res)){//审核不通过时
			switch(js){
			case 'a':{//角色是教务处
				dao.jwcshResult(xn, jxjdm, keys, res);
				break;
			}				      
			case 'b':{//角色是财务处
				dao.cwcshResult(xn, jxjdm, keys, res);
				break;
			}
			case 'c':{//角色是学生处
				dao.xscshResult(xn, jxjdm, keys, res);
				break;
			}
			case 'd':{//角色是管理员
				break;
			}
			case 'e':{//角色是学院
			    dao.xyshResult(xn, jxjdm, keys, res);	
				break;
			}
			}
		}
		return result;
	}
	
	/**
	 * 学院奖学金审核结果查询表头
	 * @return
	 */
	public ArrayList<HashMap<String, String>>  getJxjShJgSearchTitle(){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		dao = new PjpyXbemyDAO();
		result = dao.getAdminShTitle();//查询表头
		return result;
	}
	
	/**
	 * 学院奖学金审核结果查询
	 * @param pjpyModel
	 * @param jg
	 * @return
	 */
	public ArrayList<String[]>  getJxjShJgSearchResult(PjpyXbemyXysbjxjModel pjpyModel, String jg){
		ArrayList<String[]> result = new ArrayList<String[]>();
		dao = new PjpyXbemyDAO();
		result = dao.getJxjShJgSearch(pjpyModel, jg);//查询结果
		return result;
	}
	
	public ArrayList<String[]>  getJxjShJgExp(PjpyXbemyXysbjxjModel pjpyModel, String jg){
		ArrayList<String[]> result = new ArrayList<String[]>();
		dao = new PjpyXbemyDAO();
		result = dao.getJxjShJgExp(pjpyModel, jg);//查询结果
		return result;
	}
}
