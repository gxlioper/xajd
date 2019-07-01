/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description:安徽建筑工业学院公寓管理Servieces </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-7-1 下午01:20:32</p>
 */
package xgxt.xsgygl.ahjzgyxy;

import java.util.ArrayList;
import java.util.HashMap;

public class GyglAhjgServices {
	/**申报信息保存*/
	 public boolean sbXxSave(GyglAhjgWmqssbModel gaModel) throws Exception{		 		 		 
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 return gaDAO.sbXxSave(gaModel);
	 }
	/**返回文明寝室申报信息内容*/ 
	 public HashMap<String,String> getWmQsSbXx(String pkValue){
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 return gaDAO.getWmQsSbXx(pkValue);		 
	 }
	/**文明寝室申报查询返回用户查询结果*/
	 public ArrayList<String[]>  getwmQsSbSearchResult(String userName,  String userType,
			 GyglAhjgWmqssbModel Model){
		 ArrayList<String[]> result = new ArrayList<String[]>();
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();		
		 if(userType.equalsIgnoreCase("xy")){//学院用户
			 result =   gaDAO.getwmQsSbXyShSearch(Model);
		 } else if(userType.equalsIgnoreCase("xx")
				 ||userType.equalsIgnoreCase("admin")){ //学校或管理员用户
			 result =  gaDAO.getwmqsXXshSearch(Model);
		 } 
		 return result;
	 }
	/**文明寝室申报查询结果表头*/
	 public ArrayList<HashMap<String, String>>  getwmQsSbSearchTitle(String userType){
		 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 result  = gaDAO.getwmQsShTitle(userType);
		 return result;
	 }
	 /**文明寝室申报审核信息*/
	 public HashMap<String,String> getwmSbOneInfo(String pkValue,String userType){
		 HashMap<String,String> info = new HashMap<String,String>();
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 info = gaDAO.wmSbOneInfo(pkValue,userType);
		 return info;
	 }
	 /**返回申报文明寝室内学生违纪信息*/
	 public HashMap<String,String> getwmQsWjInfo(String pkValue){
		 HashMap<String,String> info = new HashMap<String,String>();
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 info = gaDAO.wmQsWjInfo(pkValue);
		 return info;
	 }
	 /**判断当前学年内文明寝室数:寝室总数是否在参数设置比例限制内，返回相关信息*/
	 public HashMap<String,String> getwmQsBlInf(){
		 HashMap<String,String> info = new HashMap<String,String>();
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 info = gaDAO.wmQsBlInf();
		 return info;
	 }
	 /**文明寝室申报审核信息保存
	 * @throws Exception */
	 public boolean  wmSbShSave(String pkValue,String userType,String yesNo) throws Exception{
		 boolean done = false;
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 if(userType.equalsIgnoreCase("xy")){//学院审核
			 done = gaDAO.wmqsXyShSave(pkValue, yesNo);
		 }else{//学校审核
			 done = gaDAO.wmqsXxShSave(pkValue, yesNo);
		 }
		 return done;
	 }
}
