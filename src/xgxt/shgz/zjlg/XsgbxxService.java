package xgxt.shgz.zjlg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The Class XljkZjlgService.
 */
public class XsgbxxService {

	/** The dao. */
	XsgbxxDao dao = new XsgbxxDao();

	/**
	 * Ser_xlzxs add.学生干部信息
	 * @throws Exception 
	 */
	public Boolean ser_xlzxsAdd(XsgbxxModel model,String tableName,HttpServletRequest request) throws Exception {
		return dao.dao_xlzxsAdd(model,tableName,request);
	}
	/**
	 * Ser_xlzxs add.学生干部信息
	 */
	public ArrayList<String[]> ser_xlzxsQuery(XsgbxxModel model,String tableName) throws Exception {
		return dao.dao_xlzxsQuery(model,tableName);
	}
	/**
	 * Ser_xlzxs add.学生干部信息
	 */
	public List<HashMap<String,String>> getToptrTitle(String tableName) throws Exception {
		return dao.getToptrTitle(tableName);
	}
	/**
	 * Ser_xlzxs add.根据id查询信息
	 */
	public HashMap<String, String> ser_idforQuery(String pk,String tableName) throws Exception {
		return dao.dao_idforQuery(pk,tableName);
	}
	/**
	 * .批量删除
	 */
	public String dao_AllDelList(String pks,String tableName) throws Exception {
		return dao.dao_AllDelList(pks,tableName);
	}
	/**
	 * .数据导出
	 */
	public void dao_expData(String tabName,HttpServletResponse response,XsgbxxModel model) throws Exception {
		 dao.dao_expData(tabName,response,model);
	}
	/**
	 * Ser_xlzxs add.获得学生信息主席团考核人员
	 * @throws Exception 
	 */
	public HashMap<String, String> ser_getxsInfo(String xh) throws Exception {
		return dao.dao_getxsInfo(xh);
	}
	
	public boolean serv_isexistsxlwy(XsgbxxModel model) throws Exception {
		return dao.dao_isexistsxlwy(model);
	}
	/**
	 * Ser_xlzxs add.是否主席团考核人员
	 * @throws Exception 
	 */
	public String serv_iszxtkhry(String yhm,String userType) throws Exception {
		return dao.dao_iszxtkhry(yhm,userType);
	}
	/**
	 * Ser_xlzxs add.评定意见查询
	 */
	public ArrayList<String[]> ser_pdyjQuery(XsgbxxModel model,String tableName) throws Exception {
		return dao.dao_pdyjQuery(model,tableName);
	}
	/**
	 * Ser_xlzxs add.评定意见
	 * @throws Exception 
	 */
	public Boolean ser_pdyjAdd(XsgbxxModel model,String tableName,HttpServletRequest request) throws Exception {
		return dao.dao_pdyjAdd(model,tableName,request);
	}
	/**
	 * Ser_xlzxs add.是否存在照片上传照片
	 * @throws Exception 
	 */
	public String[] ser_ixexistxzp(String xh) throws Exception {
		return dao.dao_ixexistxzp(xh);
	}
	/**
	 * Ser_xlzxs add.增加照片上传照片
	 * @throws Exception 
	 */
	public boolean ser_addzp(String xh,String savepath,HttpServletRequest request) throws Exception {
		return dao.dao_addzp(xh,savepath,request);
	}
}
