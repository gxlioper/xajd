package xgxt.dtjs.jhzy.yydx;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.pjpy.jhzy.JhzyPjpyDAO;

public class JhzyYydxService {
	
	JhzyYydxDAO dao= new JhzyYydxDAO();
    /**
	 * 日课程安排增加保存
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean serv_xkcapAdd(RckApModel model) throws Exception{
		JhzyYydxDAO dao = new JhzyYydxDAO();
		return dao.dao_xkcapAdd(model); 
	}
	/**
	 * 日课程安排查询表头
	 */
	public ArrayList<HashMap<String, String>> getkcapTitle() {
		JhzyYydxDAO dao = new JhzyYydxDAO();
		String[] colListCN = new String[] { "主键","学年", "学期", "院系", "课程摘要","党课届次","开始时间","结束时间"};
		return dao.dao_getSearchTitle(colListCN);
	}
	/**
	 * 日课程安排查询
	 */
	public ArrayList<String[]> serv_kcapSearch(RckApModel model){
		JhzyYydxDAO dao = new JhzyYydxDAO();
		return dao.dao_kcapSearch(model);
	}
	/**
	 * 日课程安排信息删除
	 * @throws SQLException 
	 */
	public boolean serv_kcapDel(String pks) throws SQLException{
		JhzyYydxDAO dao = new JhzyYydxDAO();
		return dao.dao_kcapDel(pks);
	}
	/**
	 * 日课程安排增加修改
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean serv_xkcapUpdate(RckApModel model,String pkValue) throws Exception{
		JhzyYydxDAO dao = new JhzyYydxDAO();
		return dao.dao_xkcapUpdate(model, pkValue);
	}	
	/**
	 * 获取相关信息
	 */
	public HashMap<String,String> serv_getXsInfo(String pkValue){
		JhzyYydxDAO dao = new JhzyYydxDAO();
		String querry = " where id='"+pkValue+"' ";
		return dao.dao_getInfo("yydxrckb", querry);
	}
	
	public HashMap<String, String> getXsSqxx(String xh) {
		// TODO 自动生成方法存根
		HashMap<String, String> rs = dao.getXsSqxx(xh);
		String djsqsj = dao.getDysqsj(xh);
		rs.put("djsqsj", djsqsj);
		return rs;
	}

	public boolean yydxUpdate(String pk, JhzyYydxModel myModel, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.yydxUpdate(pk, myModel, request);
	}
	
	/**
	 * 获取审核列表
	 */
	public List<HashMap<String, String>> serv_getChkList() {
		return dao.getChkList();
	}

	public List<HashMap<String, String>> getYydxTopTr() {
		// TODO 自动生成方法存根
		return dao.getYydxTopTr();
	}

	public ArrayList<String[]> getYydxList(JhzyYydxModel model, String userType) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
		return dao.getYydxList(model,userType);
	}

	public HashMap<String, String> getJhzyYydxForXh(String pkValue) {
		String querry = " where pk='"+pkValue+"' ";
		HashMap<String, String> rs =  dao.dao_getInfo("view_jhzy_yydxsq", querry);
		String djsqsj = dao.getDysqsj(rs.get("xh"));
		rs.put("djsqsj", djsqsj);
		return rs;
	}

	public boolean yydxCk(String pkVStr, String userType, String check) throws SQLException {
		return dao.yydxCk(pkVStr,userType,check);
	}

	public ArrayList<String[]> getYydxcjList(JhzyYydxModel model, String tableName) {
		return dao.getYydxcjList(model, tableName);
	}

	public List<HashMap<String, String>> getYydxcjTopTr(String tableName) {
		return dao.getYydxcjTopTr(tableName);
	}

	public boolean yydxcjSave(JhzyYydxModel model) throws SQLException {
		return dao.yydxcjSave(model);
	}

	public ArrayList<String[]> getYydxCxList(JhzyYydxModel model, String tableName) {
		return dao.getYydxCxList(model, tableName);
	}

	public List<HashMap<String, String>> getYydxCxTopTr(String tableName) {
		return dao.getYydxCxTopTr(tableName);
	}

	public boolean delYydx(String pkVStr) throws SQLException {
		// TODO 自动生成方法存根
		return dao.delYydx(pkVStr);
	}
	

	public boolean plzhSave(JhzyYydxModel model, String realTable, String zhsj) throws SQLException {
		//		 TODO 自动生成方法存根
		return dao.plzhSave(model, realTable,zhsj);
	}
	public Object getCjList(String xh, String xn) {
//		 TODO 自动生成方法存根
		JhzyPjpyDAO unitDAO = new JhzyPjpyDAO();
		return unitDAO.getFzPmList(xh, xn,"");
	}

}
