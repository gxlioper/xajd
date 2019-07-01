/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-7-13 上午11:02:36</p>
 */
package xgxt.sztz.nbzyjsxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class SztzNbzyService {
	public HashMap<String,String> serv_getStuInfo(String xh){
		SztzNbzyDAO myDao = new SztzNbzyDAO();
		return myDao.getStuInfo(xh);
	}
	public boolean serv_tzInfoAddSave(TzInfoAddModel model) throws Exception{
		SztzNbzyDAO myDao = new SztzNbzyDAO();
		return myDao.dao_tzInfoAddSave(model);		 
	}
	public ArrayList<HashMap<String, String>>  serv_getTzInfoTit(){
		SztzNbzyDAO myDao = new SztzNbzyDAO();
		return myDao.dao_getTzInfoTit();
	}
	public ArrayList<String[]> serv_tzInfoQuerry(TzInfoQuerryModel model){
		SztzNbzyDAO myDao = new SztzNbzyDAO();
		return myDao.dao_tzInfoQuerry(model);
	}
	public HashMap<String,String> serv_getTzxxInfo(String pkValue){
		SztzNbzyDAO myDao = new SztzNbzyDAO();
		return myDao.getTzxxInfo(pkValue);
	}
	public boolean serv_tzInfoModiSave(TzInfoAddModel model,String pkValue) throws Exception{
		SztzNbzyDAO myDao = new SztzNbzyDAO();
		return myDao.dao_tzInfoModiSave(model,pkValue);		 
	}	
	public boolean serv_tzInfoDel(String delPk) throws SQLException{
		SztzNbzyDAO myDao = new SztzNbzyDAO();
		return myDao.dao_tzInfoDel(delPk);
	}
	public ArrayList<HashMap<String, String>>  serv_getTzPrintTit(){
		SztzNbzyDAO myDao = new SztzNbzyDAO();
		return myDao.dao_getTzPrintTit();
	}
	public ArrayList<String[]> serv_tzPrintQuerry(TzInfoQuerryModel model){
		SztzNbzyDAO myDao = new SztzNbzyDAO();
		return myDao.dao_tzPrintQuerry(model);
	}
	public ArrayList<String[]> serv_tzJlxm(String xh){
		SztzNbzyDAO myDao = new SztzNbzyDAO();
		return myDao.dao_tzJlxm(xh);
	}
	public String[] serv_getKmdm() throws Exception{
		SztzNbzyDAO myDao = new SztzNbzyDAO();
		return myDao.getKmdm();
	}
	public Vector<Object> serv_getTzInfoListPrint(String xh) throws Exception{
		SztzNbzyDAO myDao = new SztzNbzyDAO();
		return myDao.dao_getTzInfoListPrint(xh);
	}
}
