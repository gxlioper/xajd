/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-1-4 下午04:28:14</p>
 */
package xgxt.sztz.csmzzyjsxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class SztzCsmzService {
    public HashMap<String,String> getInfo(String table,String pk,String pkValue){
    	SztzCsmzDAO dao = new SztzCsmzDAO();	
    	return dao.dao_getInfo(table, pk, pkValue);
    }
    public ArrayList<String[]>  getxfSbResult(String userName,String bjdm){   	
    	SztzCsmzDAO dao = new SztzCsmzDAO();
    	return dao.dao_getxfSbResult(userName, bjdm);
    }
    public boolean serv_xfSbSave(SztzCsmzXfsbModel model) throws SQLException{
    	SztzCsmzDAO dao = new SztzCsmzDAO();
    	return dao.dao_xfSbSave(model);
    }
	public ArrayList<HashMap<String, String>>  getxfSbMTitle(){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		SztzCsmzDAO dao = new SztzCsmzDAO();
		result  = dao.dao_getxfSbMTitle();
		return result;
	}
	public ArrayList<String[]>  getCzMResult(SztzCsmzXfsbModel model,String yesNo,String userType){
		ArrayList<String[]> result = new ArrayList<String[]>();
		SztzCsmzDAO dao = new SztzCsmzDAO();
		result =  dao.dao_getxfSbMResult(model,yesNo,userType);
		return result;
	}
	public ArrayList<String []> getxfSbXx(String pkValue){
		SztzCsmzDAO dao = new SztzCsmzDAO();
		return dao.dao_getxfSbXx(pkValue);
	}
	public boolean serv_xfSbxxDel(String pkValue) throws Exception{
		SztzCsmzDAO dao = new SztzCsmzDAO();
		return dao.dao_xfSbxxDel(pkValue);
	}
	public boolean serv_xfsbSh(String pkValue,String shType,String userType) throws Exception{
		SztzCsmzDAO dao = new SztzCsmzDAO();
		return dao.dao_xfsbSh(pkValue, shType, userType);
	}
	public  boolean serv_plXfSh(String pkValue,String shType,String userType) throws Exception{
		SztzCsmzDAO dao = new SztzCsmzDAO();
		return dao.dao_plXfSh(pkValue, shType, userType);
	}
}
