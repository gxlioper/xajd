/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ɽ��ѧѧ����Ԣ����Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-10 ����02:11:17</p>
 */
package xgxt.xsgygl.jgsdx;

import java.util.ArrayList;
import java.util.HashMap;


public class GyglJgsdxServices {
	GyglJgsdxDAO DAO  = null;
	
	/**�걨��Ϣ����*/
	 public boolean sbXxSave(GyglJgsdxWmqssbModel Model) throws Exception{		 		 		 
		 DAO = new GyglJgsdxDAO();
		 return DAO.sbXxSave(Model);
	 }
	 /**�������������걨��Ϣ����*/ 
	 public HashMap<String,String> getWmQsSbXx(String pkValue){
		 DAO = new GyglJgsdxDAO();
		 return DAO.getWmQsSbXx(pkValue);		 
	 } 
	 /**���������걨��ѯ�����û���ѯ���*/
	 public ArrayList<String[]>  getwmQsSbSearchResult(String userName, String userType,GyglJgsdxWmqssbModel Model,String isFdy){
		 ArrayList<String[]> result = new ArrayList<String[]>();
         DAO = new GyglJgsdxDAO();
         if(isFdy.equalsIgnoreCase("true")){//����Ա�û�
        	 result = DAO.getwmQsSbFdyshRes(Model);//����Ա�û���ѯ���
         }
         if(userType.equalsIgnoreCase("xy")){
			 if(isFdy.equalsIgnoreCase("false")){//Ժϵ�û�
				 result = DAO.getwmQsSbXyshRes(Model);//Ժϵ�û���ѯ���
			 }else if(isFdy.equalsIgnoreCase("true")){//���Ǹ���Ա�û�����Ժϵ�û�//����Ա��ɫ����
				 result = DAO.getwmQsSbFdyshRes(Model);//����Ա�û���ѯ���
			 }
         } else{
        	 if(userType.equalsIgnoreCase("admin")
					 ||userType.equalsIgnoreCase("xx")){//ѧУ�û�
        		 result = DAO.getwmQsSbXxshRes(Model);//ѧУ�û���ѯ���
        	 }
         }	 
		 return result;
	 }
	 /**���������걨��ѯ�����ͷ*/
	 public ArrayList<HashMap<String, String>>  getwmQsSbSearchTitle(String userType,String isFdy){
		 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 DAO = new GyglJgsdxDAO();
		 if(isFdy.equalsIgnoreCase("true")){//����Ա�û�
			 result = DAO.getwmQsShFdyTitle();//����Ա�û���ѯ�����ͷ
		 }		 
		 if(userType.equalsIgnoreCase("xy")){
			 if(isFdy.equalsIgnoreCase("false")){//Ժϵ�û�
				 result = DAO.getwmQsShXyTitle();//Ժϵ�û���ѯ�����ͷ
			 }else if(isFdy.equalsIgnoreCase("true")){//���Ǹ���Ա�û�����Ժϵ�û�//����Ա��ɫ����
				 result = DAO.getwmQsShFdyTitle();//����Ա�û���ѯ�����ͷ
			 }
		 }else{
			 if(userType.equalsIgnoreCase("admin")
					 ||userType.equalsIgnoreCase("xx")){//ѧУ�û�
				 result = DAO.getwmQsShXxTitle();//ѧУ�û���ѯ�����ͷ
			 }
		 }
		 return result;
	 }
	 
	 /**���������걨�����Ϣ*/
	 public HashMap<String,String> getwmSbOneInfo(String pkValue,String userType,String isFdy){
		 HashMap<String,String> info = new HashMap<String,String>();
		 DAO = new GyglJgsdxDAO();
		 if(isFdy.equalsIgnoreCase("true")){//����Ա�û�
			 info = DAO.wmSbOneInfo(pkValue,"fdysh");
			 info.put("shType","����Ա");
		 }
		 if(userType.equalsIgnoreCase("xy")){
			 if(isFdy.equalsIgnoreCase("false")){//Ժϵ�û�
				 info = DAO.wmSbOneInfo(pkValue,"xysh");
				 info.put("shType","Ժϵ");
			 }else if(isFdy.equalsIgnoreCase("true")){//���Ǹ���Ա�û�����Ժϵ�û�//����Ա��ɫ����
				 info = DAO.wmSbOneInfo(pkValue,"fdysh");
				 info.put("shType","����Ա");
			 }
		 }else{
			 if(userType.equalsIgnoreCase("admin")
					 ||userType.equalsIgnoreCase("xx")){//ѧУ�û�
				 info = DAO.wmSbOneInfo(pkValue,"xxsh");
				 info.put("shType","ѧУ");
			 }
		 }	 
		 return info;
	 }
	 /**���������걨�����Ϣ����
	  * @throws Exception */
	 public boolean  wmSbShSave(String pkValue,String userType,String yesNo,String isFdy) throws Exception{
		 boolean done = false;
		 DAO = new GyglJgsdxDAO();
		 if("true".equalsIgnoreCase(isFdy)){//����Ա�û�
			 done = DAO.wmqsFdyShSave(pkValue, yesNo);//����Ա��˱���
		 }
		 if(userType.equalsIgnoreCase("xy")){
			 if(isFdy.equalsIgnoreCase("false")){//Ժϵ�û�
				 done = DAO.wmqsXyShSave(pkValue, yesNo);//Ժϵ�û���˱���
			 }else if(isFdy.equalsIgnoreCase("true")){//���Ǹ���Ա�û�����Ժϵ�û�//����Ա��ɫ����
				 done = DAO.wmqsFdyShSave(pkValue, yesNo);//����Ա��˱���
			 }
		 }else{
			 if(userType.equalsIgnoreCase("admin")
					 ||userType.equalsIgnoreCase("xx")){//ѧУ�û�
				done = DAO.wmqsXxShSave(pkValue, yesNo); //ѧУ�û���˱���
			 }
		 }	 
		 return done;
	 }
	 public String isUserReg(String pkValue,String userName){
		 DAO = new GyglJgsdxDAO();
		 return DAO.isUserReq(pkValue, userName);
	 }
}
