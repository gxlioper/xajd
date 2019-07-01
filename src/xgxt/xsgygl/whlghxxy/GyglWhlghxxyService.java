/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-6-1 下午02:24:58</p>
 */
package xgxt.xsgygl.whlghxxy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class GyglWhlghxxyService {
	GyglWhlghxxyDAO dao = null;
	public List<HashMap<String,String>> findLdList(){
		dao = new GyglWhlghxxyDAO();
		return dao.findLdList();
	}
	public List<HashMap<String,String>> findYfList(){
		dao = new GyglWhlghxxyDAO();
		return dao.findYfList();
	}
	public List<HashMap<String,String>> expDataList(){
		dao = new GyglWhlghxxyDAO();
		return dao.expDataList();
	}
	public List<HashMap<String,String>> findXnList(){
		dao = new GyglWhlghxxyDAO();
		return dao.findXnList();
	}
	public List<HashMap<String,String>> findXqList(){
		dao = new GyglWhlghxxyDAO();
		return dao.findXqList();
	}
	public String getLdmc(String lddm){
		dao = new GyglWhlghxxyDAO();
		return dao.getLdmc(lddm);
	}

	public List<HashMap<String, String>> getNwwsdj(){
		dao = new GyglWhlghxxyDAO();
		return dao.dao_Nwwsdj();
	}
	public boolean serv_createZXFdytjb(String nj,String xn,String xq,String zs) throws Exception{
		dao = new GyglWhlghxxyDAO();
		return dao.createZXFdytjb(nj,xn, xq, zs);
	}
	public String[] ser_getTjbCol(String tableName){
		dao = new GyglWhlghxxyDAO();
		return dao.getTjbCol(tableName);
	}
	public List<HashMap<String, String>> ser_getAzxfTjValue(){
		dao = new GyglWhlghxxyDAO();
        return dao.getAzxfTjValue();
	}
	public List<HashMap<String, String>> ser_getMergeCol(String tableName){
		dao = new GyglWhlghxxyDAO();
        return dao.getMergeCol(tableName);
	}
	public List<HashMap<String, String>> serv_Nwwsdj(){
		dao = new GyglWhlghxxyDAO();
        return dao.dao_Nwwsdj();
	}
	public boolean serv_createZXtjb(String xn,String xq,String zs) throws Exception{
		dao = new GyglWhlghxxyDAO();
		return dao.createZXtjb(xn,xq,zs);
	}
	public List<HashMap<String, String>> ser_getAzxTjValue(){
		dao = new GyglWhlghxxyDAO();
        return dao.getAzxTjValue();
	}
	public boolean ser_delTable(String tableName) throws Exception{
		dao = new GyglWhlghxxyDAO();
		return dao.delTable(tableName);
	}
	public String ser_getCws(){
		dao = new GyglWhlghxxyDAO();
		return dao.getCws();
	}
	public boolean serv_createAytjb(String lddm,String nd,String yf) throws Exception{
		dao = new GyglWhlghxxyDAO();
		return dao.createAytjb(lddm, nd, yf);
	}
	public List<HashMap<String, String>> serv_getAYTjValue(){
		dao = new GyglWhlghxxyDAO();		
        return dao.getAYTjValue();
	}
		public String getXqmc(String xqdm){
		dao = new GyglWhlghxxyDAO();
		return dao.getXqmc(xqdm);
	}
	public List<HashMap<String,String>> azExpDataList(GyglWhlghxxyForm form){
		dao = new GyglWhlghxxyDAO();
		return dao.azExpDataList(form);
	}
	public List<HashMap<String,String>> findCs(){
		dao = new GyglWhlghxxyDAO();
		return dao.findCs();
	}
	public List<HashMap<String,String>> findQsbj(GyglWhlghxxyForm form){
		dao = new GyglWhlghxxyDAO();
		return dao.findQsbj(form);
	}
	public boolean serv_createZtjb(String xn,String xq,String ld) throws Exception{
		dao = new GyglWhlghxxyDAO();
		return dao.createZtjb(xn,xq,ld);
	}
	public List<HashMap<String, String>> serv_getAZTjValue(){
		dao = new GyglWhlghxxyDAO();		
        return dao.getAZTjValue();
	}
	public List<HashMap<String, String>> serv_listvalue(GyglWhlghxxyForm form){
		dao = new GyglWhlghxxyDAO();		
		return dao.dao_listvalue(form);
	}
	public List<HashMap<String, String>> serv_getDjList(){
		dao = new GyglWhlghxxyDAO();	
		return dao.dao_getDjList();
	}
	public List<HashMap<String,String>> ser_getJcBmList(){
		dao = new GyglWhlghxxyDAO();
		return dao.dao_getJcBmList();
	}
	public boolean serv_inputSave(GyglWhlghxxyForm form) throws SQLException{
		dao = new GyglWhlghxxyDAO();
		return dao.dao_inputSave(form);
	}
}
