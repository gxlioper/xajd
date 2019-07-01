/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-5-12 上午10:49:14</p>
 */
package xgxt.sztz.bjlhdx;

import java.util.ArrayList;
import java.util.HashMap;

public class SztzBjlhdxService {
	public boolean  serv_csfInit(QuerryModel model) throws Exception{
		SztzBjlhdxDAO myDao = new SztzBjlhdxDAO();
		return myDao.dao_csfInit(model);
	}
	public ArrayList<String[]> serv_csfInitQuerr(QuerryModel model) {
		SztzBjlhdxDAO myDao = new SztzBjlhdxDAO();
		return myDao.dao_csfInitQuerr(model);
	}
	public ArrayList<HashMap<String, String>>  getcsfStatTitle(){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		SztzBjlhdxDAO myDao = new SztzBjlhdxDAO();
		result  = myDao.dao_csfStatTitle();
		return result;
	}
	public ArrayList<String[]> serv_csfStatResult(QuerryModel model) {
		SztzBjlhdxDAO myDao = new SztzBjlhdxDAO();
		return myDao.dao_csfStatResult(model);
	}
	public boolean serv_kxfDel(String pkValue) throws Exception{
		SztzBjlhdxDAO myDao = new SztzBjlhdxDAO();
		return myDao.dao_kxfDel(pkValue);
	}
	public HashMap<String,String> srev_kxfXx(String pkValue) {
		SztzBjlhdxDAO myDao = new SztzBjlhdxDAO();
		return myDao.dao_kxfXx(pkValue);
	}
	public String serv_getXyCsf(String xh) {
		SztzBjlhdxDAO myDao = new SztzBjlhdxDAO();
		return myDao.dao_getXyCsf(xh);
	}
	public boolean serv_kfModiSave(String kfs,String pkValue) throws Exception{
	   SztzBjlhdxDAO myDao = new SztzBjlhdxDAO();
	   return 	myDao.dao_kfModiSave(kfs, pkValue);
	}
}
