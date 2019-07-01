/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学学生公寓管理Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-10 下午02:11:17</p>
 */
package xgxt.xsgygl.jgsdx;

import java.util.ArrayList;
import java.util.HashMap;


public class GyglJgsdxServices {
	GyglJgsdxDAO DAO  = null;
	
	/**申报信息保存*/
	 public boolean sbXxSave(GyglJgsdxWmqssbModel Model) throws Exception{		 		 		 
		 DAO = new GyglJgsdxDAO();
		 return DAO.sbXxSave(Model);
	 }
	 /**返回文明寝室申报信息内容*/ 
	 public HashMap<String,String> getWmQsSbXx(String pkValue){
		 DAO = new GyglJgsdxDAO();
		 return DAO.getWmQsSbXx(pkValue);		 
	 } 
	 /**文明寝室申报查询返回用户查询结果*/
	 public ArrayList<String[]>  getwmQsSbSearchResult(String userName, String userType,GyglJgsdxWmqssbModel Model,String isFdy){
		 ArrayList<String[]> result = new ArrayList<String[]>();
         DAO = new GyglJgsdxDAO();
         if(isFdy.equalsIgnoreCase("true")){//辅导员用户
        	 result = DAO.getwmQsSbFdyshRes(Model);//辅导员用户查询结果
         }
         if(userType.equalsIgnoreCase("xy")){
			 if(isFdy.equalsIgnoreCase("false")){//院系用户
				 result = DAO.getwmQsSbXyshRes(Model);//院系用户查询结果
			 }else if(isFdy.equalsIgnoreCase("true")){//即是辅导员用户又是院系用户//辅导员角色优先
				 result = DAO.getwmQsSbFdyshRes(Model);//辅导员用户查询结果
			 }
         } else{
        	 if(userType.equalsIgnoreCase("admin")
					 ||userType.equalsIgnoreCase("xx")){//学校用户
        		 result = DAO.getwmQsSbXxshRes(Model);//学校用户查询结果
        	 }
         }	 
		 return result;
	 }
	 /**文明寝室申报查询结果表头*/
	 public ArrayList<HashMap<String, String>>  getwmQsSbSearchTitle(String userType,String isFdy){
		 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 DAO = new GyglJgsdxDAO();
		 if(isFdy.equalsIgnoreCase("true")){//辅导员用户
			 result = DAO.getwmQsShFdyTitle();//辅导员用户查询结果表头
		 }		 
		 if(userType.equalsIgnoreCase("xy")){
			 if(isFdy.equalsIgnoreCase("false")){//院系用户
				 result = DAO.getwmQsShXyTitle();//院系用户查询结果表头
			 }else if(isFdy.equalsIgnoreCase("true")){//即是辅导员用户又是院系用户//辅导员角色优先
				 result = DAO.getwmQsShFdyTitle();//辅导员用户查询结果表头
			 }
		 }else{
			 if(userType.equalsIgnoreCase("admin")
					 ||userType.equalsIgnoreCase("xx")){//学校用户
				 result = DAO.getwmQsShXxTitle();//学校用户查询结果表头
			 }
		 }
		 return result;
	 }
	 
	 /**文明寝室申报审核信息*/
	 public HashMap<String,String> getwmSbOneInfo(String pkValue,String userType,String isFdy){
		 HashMap<String,String> info = new HashMap<String,String>();
		 DAO = new GyglJgsdxDAO();
		 if(isFdy.equalsIgnoreCase("true")){//辅导员用户
			 info = DAO.wmSbOneInfo(pkValue,"fdysh");
			 info.put("shType","辅导员");
		 }
		 if(userType.equalsIgnoreCase("xy")){
			 if(isFdy.equalsIgnoreCase("false")){//院系用户
				 info = DAO.wmSbOneInfo(pkValue,"xysh");
				 info.put("shType","院系");
			 }else if(isFdy.equalsIgnoreCase("true")){//即是辅导员用户又是院系用户//辅导员角色优先
				 info = DAO.wmSbOneInfo(pkValue,"fdysh");
				 info.put("shType","辅导员");
			 }
		 }else{
			 if(userType.equalsIgnoreCase("admin")
					 ||userType.equalsIgnoreCase("xx")){//学校用户
				 info = DAO.wmSbOneInfo(pkValue,"xxsh");
				 info.put("shType","学校");
			 }
		 }	 
		 return info;
	 }
	 /**文明寝室申报审核信息保存
	  * @throws Exception */
	 public boolean  wmSbShSave(String pkValue,String userType,String yesNo,String isFdy) throws Exception{
		 boolean done = false;
		 DAO = new GyglJgsdxDAO();
		 if("true".equalsIgnoreCase(isFdy)){//辅导员用户
			 done = DAO.wmqsFdyShSave(pkValue, yesNo);//辅导员审核保存
		 }
		 if(userType.equalsIgnoreCase("xy")){
			 if(isFdy.equalsIgnoreCase("false")){//院系用户
				 done = DAO.wmqsXyShSave(pkValue, yesNo);//院系用户审核保存
			 }else if(isFdy.equalsIgnoreCase("true")){//即是辅导员用户又是院系用户//辅导员角色优先
				 done = DAO.wmqsFdyShSave(pkValue, yesNo);//辅导员审核保存
			 }
		 }else{
			 if(userType.equalsIgnoreCase("admin")
					 ||userType.equalsIgnoreCase("xx")){//学校用户
				done = DAO.wmqsXxShSave(pkValue, yesNo); //学校用户审核保存
			 }
		 }	 
		 return done;
	 }
	 public String isUserReg(String pkValue,String userName){
		 DAO = new GyglJgsdxDAO();
		 return DAO.isUserReq(pkValue, userName);
	 }
}
