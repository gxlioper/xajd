/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-12-5 下午03:04:00</p>
 */
package xgxt.xsgygl.zjjjzyjsxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





public class GyglZjjjzyService {
	public boolean serv_lzSave(LzModel model) throws Exception{
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_lzSave(model);
	}
	public HashMap<String,String> serv_getLzInfo(String xh){
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_getLzInfo(xh);
	}
	/**楼长查询结果表头*/
	public ArrayList<HashMap<String, String>>  getLzMTitle(){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		result  = dao.dao_getLzMTitle();
		return result;
	}
	/**楼长查询返回结果*/
	public ArrayList<String[]>  getLzMResult(LzModel model){
		ArrayList<String[]> result = new ArrayList<String[]>();
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		result =  dao.dao_getLzMResult(model);
		return result;
	}
	public HashMap<String,String> serv_lzXx(String pkValue) {
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_lzXx(pkValue);
	}
	public boolean serv_lzModi(LzModel model,String pkValue) throws Exception{
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_lzModi(model,pkValue);
	}
	public boolean serv_lzDel(String pkValue) throws Exception{
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_lzDel(pkValue);
	}
	public HashMap<String,String> serv_getCzInfo(String xh){
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_getCzInfo(xh);
	}
	public boolean serv_czSave(CzModel model) throws Exception{
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_czSave(model);
	}
	/**层长查询结果表头*/
	public ArrayList<HashMap<String, String>>  getCzMTitle(){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		result  = dao.dao_getCzMTitle();
		return result;
	}
	/**层长查询返回结果*/
	public ArrayList<String[]>  getCzMResult(CzModel model){
		ArrayList<String[]> result = new ArrayList<String[]>();
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		result =  dao.dao_getCzMResult(model);
		return result;
	}
	public boolean serv_czModi(CzModel model,String pkValue) throws Exception{
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_czModi(model,pkValue);
	}
 
	public HashMap<String,String> serv_czXx(String pkValue) {
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_czXx(pkValue);
	}
	public boolean serv_czDel(String pkValue) throws Exception{
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_czDel(pkValue);
	}
	public boolean serv_aqjbSave(String title,String content,String userName,String isModi,String documentId) throws Exception{
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_aqjbSave(title,content,userName,isModi,documentId);
	}
	public String serv_getJbTitle(String documentId){
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_getJbTitle(documentId);
	}
	public String serv_getJbContent(String documentId) throws SQLException{
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_getJbContent(documentId);
	}
	public List serv_getJbMResult(String userName){
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_getJbMResult(userName);
	}
	public ArrayList<HashMap<String, String>>  getMzjbTitle(){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		result  = dao.dao_getMzjbTitle();
		return result;
	}
	public String[] serv_aqjbShow(String documentId) throws SQLException{
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_aqjbShow(documentId);
	}
	public boolean serv_aqjbDel(String documentId) throws Exception{
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_aqjbDel(documentId);
	}
	public ArrayList<String[]>  getMzjbShResult(String yesNo,String rzrq,String lzrq,String title){
		ArrayList<String[]> result = new ArrayList<String[]>();
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		result =  dao.dao_getMzjbShResult(yesNo,rzrq,lzrq,title);
		return result;
	}
	public boolean serv_aqjbSh(String documentId,String shType) throws Exception{
		GyglZjjjzyDao dao = new GyglZjjjzyDao();
		return dao.dao_aqjbSh(documentId, shType);
	}
}
