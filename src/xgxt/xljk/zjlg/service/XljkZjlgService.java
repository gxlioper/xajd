package xgxt.xljk.zjlg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.xljk.zjlg.dao.XljkZjlgDao;
import xgxt.xljk.zjlg.model.XljkZjlgmodel;

// TODO: Auto-generated Javadoc
/**
 * The Class XljkZjlgService.
 */
public class XljkZjlgService {

	/** The dao. */
	XljkZjlgDao dao = new XljkZjlgDao();

	/**
	 * Ser_xlzxs add.心理咨询师增加
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * @param request the request
	 * 
	 * @return true, if ser_xlzxs add
	 * 
	 * @throws Exception the exception
	 */
	public Boolean ser_xlzxsAdd(XljkZjlgmodel model,String tableName,HttpServletRequest request) throws Exception {
		return dao.dao_xlzxsAdd(model,tableName,request);
	}
	
	/**
	 * Ser_xlzxs add.心理咨询师查询
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * 
	 * @return the array list< string[]>
	 * 
	 * @throws Exception the exception
	 */
	public ArrayList<String[]> ser_xlzxsQuery(XljkZjlgmodel model,String tableName) throws Exception {
		return dao.dao_xlzxsQuery(model,tableName);
	}
	
	/**
	 * Ser_xlzxs add.获取表头
	 * 
	 * @param tableName the table name
	 * 
	 * @return the toptr title
	 * 
	 * @throws Exception the exception
	 */
	public List<HashMap<String,String>> getToptrTitle(String tableName) throws Exception {
		return dao.getToptrTitle(tableName);
	}
	
	/**
	 * Ser_xlzxs add.根据id查询信息
	 * 
	 * @param pk the pk
	 * @param tableName the table name
	 * 
	 * @return the hash map< string, string>
	 * 
	 * @throws Exception the exception
	 */
	public HashMap<String, String> ser_idforQuery(String pk,String tableName) throws Exception {
		return dao.dao_idforQuery(pk,tableName);
	}
	
	/**
	 * .批量删除
	 * 
	 * @param pks the pks
	 * @param tableName the table name
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public String dao_AllDelList(String pks,String tableName) throws Exception {
		return dao.dao_AllDelList(pks,tableName);
	}
	
	/**
	 * .数据导出
	 * 
	 * @param tabName the tab name
	 * @param response the response
	 * @param model the model
	 * 
	 * @throws Exception the exception
	 */
	public void dao_expData(String tabName,HttpServletResponse response,XljkZjlgmodel model) throws Exception {
		 dao.dao_expData(tabName,response,model);
	}
	
	/**
	 * Ser_xlzxs add.心理普查增加
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * @param request the request
	 * 
	 * @return true, if ser_xlpc add
	 * 
	 * @throws Exception the exception
	 */
	public Boolean ser_xlpcAdd(XljkZjlgmodel model,String tableName,HttpServletRequest request) throws Exception {
		return dao.dao_xlpcAdd(model,tableName,request);
	}
	
	/**
	 * Ser_xlzxs add.获得学生信息
	 * 
	 * @param xh the xh
	 * 
	 * @return the hash map< string, string>
	 * 
	 * @throws Exception the exception
	 */
	public HashMap<String, String> ser_getxsInfo(String xh) throws Exception {
		return dao.dao_getxsInfo(xh);
	}
	
	/**
	 * Ser_xlzxs add.是否是心里委员
	 * 
	 * @param xh the xh
	 * 
	 * @return true, if serv_isexistsxlwy
	 * 
	 * @throws Exception the exception
	 */
	public boolean serv_isexistsxlwy(String xh) throws Exception {
		return dao.dao_isexistsxlwy(xh);
	}
	
	/**
	 * Dao_get xljkzdls.
	 * 
	 * @return the array list< hash map< string, string>>
	 * 
	 * @throws Exception the exception
	 */
	public ArrayList<HashMap<String, String>> dao_getXljkzdls() throws Exception {
		return dao.dao_getXljkzdls();
	}
	
	/**
	 * Ser_getxllbxlwtlx.心理测试类别、心理问题类型
	 * 
	 * @param pk the pk
	 * @param tableName the table name
	 * 
	 * @return the hash map< string, string>，心理测试类别--xlcslb、心理问题类型 --xlwtlx
	 * 
	 * @throws Exception the exception
	 */
	public HashMap<String, ArrayList<HashMap<String, String>>> ser_getxllbxlwtlx() throws Exception {
		return dao.dao_getxllbxlwtlx();
	}
}
