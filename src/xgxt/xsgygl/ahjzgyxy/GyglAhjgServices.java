/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description:���ս�����ҵѧԺ��Ԣ����Servieces </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-7-1 ����01:20:32</p>
 */
package xgxt.xsgygl.ahjzgyxy;

import java.util.ArrayList;
import java.util.HashMap;

public class GyglAhjgServices {
	/**�걨��Ϣ����*/
	 public boolean sbXxSave(GyglAhjgWmqssbModel gaModel) throws Exception{		 		 		 
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 return gaDAO.sbXxSave(gaModel);
	 }
	/**�������������걨��Ϣ����*/ 
	 public HashMap<String,String> getWmQsSbXx(String pkValue){
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 return gaDAO.getWmQsSbXx(pkValue);		 
	 }
	/**���������걨��ѯ�����û���ѯ���*/
	 public ArrayList<String[]>  getwmQsSbSearchResult(String userName,  String userType,
			 GyglAhjgWmqssbModel Model){
		 ArrayList<String[]> result = new ArrayList<String[]>();
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();		
		 if(userType.equalsIgnoreCase("xy")){//ѧԺ�û�
			 result =   gaDAO.getwmQsSbXyShSearch(Model);
		 } else if(userType.equalsIgnoreCase("xx")
				 ||userType.equalsIgnoreCase("admin")){ //ѧУ�����Ա�û�
			 result =  gaDAO.getwmqsXXshSearch(Model);
		 } 
		 return result;
	 }
	/**���������걨��ѯ�����ͷ*/
	 public ArrayList<HashMap<String, String>>  getwmQsSbSearchTitle(String userType){
		 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 result  = gaDAO.getwmQsShTitle(userType);
		 return result;
	 }
	 /**���������걨�����Ϣ*/
	 public HashMap<String,String> getwmSbOneInfo(String pkValue,String userType){
		 HashMap<String,String> info = new HashMap<String,String>();
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 info = gaDAO.wmSbOneInfo(pkValue,userType);
		 return info;
	 }
	 /**�����걨����������ѧ��Υ����Ϣ*/
	 public HashMap<String,String> getwmQsWjInfo(String pkValue){
		 HashMap<String,String> info = new HashMap<String,String>();
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 info = gaDAO.wmQsWjInfo(pkValue);
		 return info;
	 }
	 /**�жϵ�ǰѧ��������������:���������Ƿ��ڲ������ñ��������ڣ����������Ϣ*/
	 public HashMap<String,String> getwmQsBlInf(){
		 HashMap<String,String> info = new HashMap<String,String>();
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 info = gaDAO.wmQsBlInf();
		 return info;
	 }
	 /**���������걨�����Ϣ����
	 * @throws Exception */
	 public boolean  wmSbShSave(String pkValue,String userType,String yesNo) throws Exception{
		 boolean done = false;
		 GyglAhjgDAO gaDAO = new GyglAhjgDAO();
		 if(userType.equalsIgnoreCase("xy")){//ѧԺ���
			 done = gaDAO.wmqsXyShSave(pkValue, yesNo);
		 }else{//ѧУ���
			 done = gaDAO.wmqsXxShSave(pkValue, yesNo);
		 }
		 return done;
	 }
}
