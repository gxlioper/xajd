/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-10-27 ����03:14:54</p>
 */
package xgxt.xsgygl.nblgxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class GyglNblgxyServices {
	/**���������걨��ѯ�����ͷ*/
	 public ArrayList<HashMap<String, String>>  getwmQsSbSearchTitle(String userType){
		 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 GyglNblgxyDAO gaDAO = new GyglNblgxyDAO();
		result  = gaDAO.getwmQsShTitle(userType);
		 return result;
	 }
	 /**���������걨��ѯ�����û���ѯ���*/
	 public ArrayList<String[]>  getwmQsSbSearchResult(String userType,GyglNblgxyWmqsModel Model){
		 ArrayList<String[]> result = new ArrayList<String[]>();
		 GyglNblgxyDAO gaDAO = new GyglNblgxyDAO();
		 if(userType.equalsIgnoreCase("xx")
				 ||userType.equalsIgnoreCase("admin")){ //ѧУ�����Ա�û�
			 result =  gaDAO.getwmQsSbXxShSearch(Model);
		 } 
		 return result;
	 }
	 /**���������걨�����Ϣ*/
	 public HashMap<String,String> getwmSbOneInfo(String pkValue,String userType){
		 HashMap<String,String> info = new HashMap<String,String>();
		 GyglNblgxyDAO gaDAO = new GyglNblgxyDAO();
		 info = gaDAO.wmSbOneInfo(pkValue,userType);
		 return info;
	 }
	 /**�����걨����������ѧ��Υ����Ϣ*/
	 public HashMap<String,String> getwmQsWjInfo(String pkValue){
		 HashMap<String,String> info = new HashMap<String,String>();
		 GyglNblgxyDAO gaDAO = new GyglNblgxyDAO();
		 info = gaDAO.wmQsWjInfo(pkValue);
		 return info;
	 }
	 /**�жϵ�ǰѧ��������������:���������Ƿ��ڲ������ñ��������ڣ����������Ϣ*/
	 public HashMap<String,String> getwmQsBlInf(){
		 HashMap<String,String> info = new HashMap<String,String>();
		 GyglNblgxyDAO gaDAO = new GyglNblgxyDAO();
		 info = gaDAO.wmQsBlInf();
		 return info;
	 }
	 /**���������걨�����Ϣ����
	  * @throws Exception */
	 public boolean  wmSbShSave(String pkValue,String userType,String yesNo) throws Exception{
		 boolean done = false;
		 GyglNblgxyDAO gaDAO = new GyglNblgxyDAO();		
		 done = gaDAO.wmqsXxShSave(pkValue, yesNo); 		 
		 return done;
	 }

	 /**����Ա�±���д����
	  * @throws Exception */
	 public boolean  serv_ybAdd(GyglNblgxyYbModel model,String userName) throws Exception{
		 boolean done       = false;
		 GyglNblgxyDAO gaDAO = new GyglNblgxyDAO();		
		 done                = gaDAO.dao_ybAdd(model, userName);		 
		 return done;
	 }
	 
	 public ArrayList<HashMap<String, String>> serv_getybMTitle(){
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();
		 return dao.getybMTitle();
	 }
	 public ArrayList<String[]>  serv_getybMResult(String nf,String yf,String userName,String userType){
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();
		 return dao.getybMResult(nf,yf,userName,userType);
	 }
	 public HashMap<String,String>serv_getYbInfo(String pkValue){
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();
		 return dao.getYbInfo(pkValue);
	 }
	 /**����Ա�±��޸ı���
	  * @throws Exception */
	 public boolean  serv_ybModi(GyglNblgxyYbModel model,String pkValue) throws Exception{
		 boolean done       = false;
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();		
		 done                = dao.dao_ybModi(model, pkValue);		 
		 return done;
	 }
	 public boolean serv_ybDel(String pkValue) throws Exception{
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.dao_ybDel(pkValue);
	 }
	 
	 public boolean serv_zrrAddSave(GyglFzqLxrModel model) throws Exception{
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.dao_zrrAddSave(model);
	 }
	 public ArrayList<HashMap<String, String>> serv_getZrrTitle(){
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.dao_getZrrTitle();
	 }
	 public ArrayList<String[]> serv_getZrrResult(GyglFzqLxrModel model) {
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.dao_getZrrResult(model);
	 }
	 public boolean ser_zrrDel(String pkValue) throws Exception{
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.dao_zrrDel(pkValue);
	 }
	 public  HashMap<String,String> serv_zrrInfo(String pkValue){
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.zrrInfo(pkValue);
	 }
	 public boolean serv_zrrModiSave(GyglFzqLxrModel model,String pkValue) throws Exception{
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.dao_zrrModiSave(model,pkValue);		 
	 }
	 public boolean serv_tsxsAddSave(GyglNblgTsxsModel model) throws Exception{
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.dao_tsxsAddSave(model);
	 }
	 public ArrayList<HashMap<String, String>> serv_getTsxsTitle() {
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.dao_getTsxsTitle();
	 }
	 public ArrayList<String[]> serv_getTsxsResult(GyglNblgTsxsModel model) {
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.dao_getTsxsResult(model);
	 }
	 public  HashMap<String,String> serv_tsxsInfo(String pkValue){
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.tsxsInfo(pkValue);
	 }
	 public boolean serv_tsxsModiSave(GyglNblgTsxsModel model,String pkValue) throws Exception{
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.dao_tsxsModiSave(model,pkValue);
	 }
	 public boolean ser_tsxsDel(String pkValue) throws Exception{
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.dao_tsxsDel(pkValue);
	 }
	 public boolean serv_getsjxzInfo() throws SQLException{
		 GyglNblgxyDAO dao = new GyglNblgxyDAO();	
		 return dao.dao_getsjxzInfo();
	 }
}
