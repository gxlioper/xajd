/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-4-3 上午09:38:17</p>
 */
package xgxt.sztz.zgkydx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class SztzZgkydxService {
    public HashMap<String, String> getXsInfo(String userName) {
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();	
    	return dao.dao_getInfo("view_xsxxb","xh",userName);
    }
    public HashMap<String, String> getTzXsInfo(String talbe,String pk,String pkValue) {
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();	
    	return dao.dao_getInfo(talbe,pk,pkValue);
    }
	public List<HashMap<String, String>> getTzKm(){
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();	
    	return dao.dao_getTzKm();
    }
    public String getTimeLim() throws SQLException{
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();	
    	return dao.dao_TimeLim();
    }
    public HashMap<String, String> getLimXnXq() throws SQLException{
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();	
    	return dao.dao_getLimXnSq();
    }
    public boolean ser_infoSave(StringBuffer delSqlV,StringBuffer inSqlV ) throws SQLException{
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();
    	return dao.dao_infoSave(delSqlV, inSqlV);
    }
    public Vector<Object> serv_infoInpQuer(String xn,String xq,String xh) {
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();
    	return dao.dao_infoInpQuer(xn, xq, xh);
    }
    public ArrayList<HashMap<String, String>> serv_infoTitle() {
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();
    	return dao.dao_infoTitle();
    }
    public ArrayList<String[]> serv_infoQuerry(TzInfoCxModel model){
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();
    	return dao.dao_infoQuerry(model);
    }
    public Vector<Object> serv_infoInpQuer(String pkValue) {
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();
    	return dao.dao_infoInpQuer(pkValue);
    }
    public boolean serv_infoDel(String pk,String pkValue) throws Exception{
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();
    	return dao.dao_infoDel(pk, pkValue);
    }
    public ArrayList<HashMap<String, String>> serv_infoChkTitle() {
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();
    	return dao.dao_infoChkTitle();
    }
    public ArrayList<String[]> serv_infoChkQuerry(TzInfoCxModel model,String userType){
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();
    	return dao.dao_infoChkQuerry(model,userType);
    }
	public boolean serv_infoChk(String pkValue,String userType,String check) throws SQLException{
	   	SztzZgkydxDAO dao = new SztzZgkydxDAO();
    	return dao.dao_infoChk(pkValue,userType,check);
 
	}
    public ArrayList<HashMap<String, String>> serv_infoPrintTitle() {
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();
    	return dao.dao_infoPrintTitle();
    }
    public ArrayList<String[]> serv_infoPrintQuerry(TzInfoCxModel model){
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();
    	return dao.dao_infoPrintQuerry(model);
    }

	// =============begin luojw 2009/6/4 =======================
	public List<HashMap<String, String>> getKmList() {
		SztzZgkydxDAO dao = new SztzZgkydxDAO();
		return dao.dao_getKmList();
	}
	public List<HashMap<String, String>> getHdList(String kmdm) {
		SztzZgkydxDAO dao = new SztzZgkydxDAO();
		return dao.dao_getHdList(kmdm);
	}
	public List<HashMap<String, String>> gethdnrmc(String kmdm) {
		SztzZgkydxDAO dao = new SztzZgkydxDAO();
		return dao.dao_gethdnrmc(kmdm);
	}
    public Vector<Object> serv_infoInpQuer1(String xn,String xq,String xh,String km) {
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();
    	return dao.dao_infoInpQuer1(xn, xq, xh,km);
    }
    public boolean addSztzInfo(String[] pk, String[] hdnrdm, String xh,
			String xn, String xq) throws Exception {
		SztzZgkydxDAO dao = new SztzZgkydxDAO();
		return dao.dao_addSztzInfo(pk, hdnrdm, xh, xn, xq);
	}
    public boolean editSztzInfo(String[] pk, String[] hdnrdm, String[] delHddm,String xh,
			String xn, String xq) throws Exception {
		SztzZgkydxDAO dao = new SztzZgkydxDAO();
		return dao.dao_editSztzInfo(pk, hdnrdm,delHddm, xh, xn, xq);
	}
    public Vector<Object> serv_infoInpQuer1(String pkValue) throws SQLException {
    	SztzZgkydxDAO dao = new SztzZgkydxDAO();
    	return dao.dao_infoInpQuer1(pkValue);
    }
	// =============end luojw 2009/6/4 =======================
}
