package xgxt.studentInfo.ynys;

import java.util.HashMap;
import java.util.List;

import xgxt.studentInfo.dao.StuInfoDAO;

public class XsxxYnysService {
	XsxxYnysDAO dao = new XsxxYnysDAO();
	
	public List<String[]> selectXsxx(XsxxYnysForm model){
		return dao.selectXsxx(model);
	}
	
	/**
	 * 获取学生信息查询表头
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsxxSearchTopTr(){
		String[] colList = dao.getCols();
		String[] colCNList = dao.getColumnNameCN(colList, "view_bks_xsxx");
		return dao.arrayToList(colList, colCNList);
	}
	
	/**
	 * 检测是否可以修改学生信息
	 * */
	public boolean checkXsxxModify(){		
		return dao.checkModifyXsxx();
	}
	
	/**
	 * 获取民族列表
	 * */
	public List<HashMap<String, String>> getMzList(){
		return dao.getMzList();
	}
	
	/**
	 * 获取政治面貌列表
	 * */
	public List<HashMap<String, String>> getZzmmList(){
		return dao.getZzmmList();
	}
	
	/**
	 * 获取省列表
	 * */
	public List<HashMap<String, String>> getSsList(){
		StuInfoDAO stuDao = new StuInfoDAO();
		return stuDao.getSsList();
	}
	
	/**
	 * 获取市列表
	 * */
	public HashMap<String, List<HashMap<String, String>>> getShiList(String jgStr){
		StuInfoDAO stuDao = new StuInfoDAO();
		return stuDao.getShiList(jgStr);
	}
	
	/**
	 * 根据省代码获取市列表
	 * @param String shenId
	 * @return HashMap<String, List>
	 * */
	public HashMap<String, List<HashMap<String, String>>> getShiListNew(String shenId){
		StuInfoDAO stuDao = new StuInfoDAO();
		return stuDao.getShiListNew(shenId);
	}
	
	/**
	 * 获取用户的简单类型
	 * */
	public String getSpeType(String userType){
		String result = "";
		if("xy".equalsIgnoreCase(userType)){
			result = "xy";
		}else if("student".equalsIgnoreCase(userType)){
			result = "student";
		}else{
			result = "xx";
		}
		return result;
	}
}
