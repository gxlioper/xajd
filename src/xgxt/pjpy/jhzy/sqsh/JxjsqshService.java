package xgxt.pjpy.jhzy.sqsh;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.pjpy.jhzy.JhzyPjpyDAO;
import xgxt.pjpy.jhzy.rych.RychDAO;

public class JxjsqshService {
	
	JxjsqshDAO myDAO = new JxjsqshDAO();

	public List<HashMap<String, String>> getJxjList() throws Exception {
		return myDAO.getJxjList();
	}

	public String[] getJxjXx(String jxjdm) {
		// TODO 自动生成方法存根
		return myDAO.getJxjXx(jxjdm);
	}

	public boolean JxjCommonSave(JxjsqshModel model, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return myDAO.JxjCommonSave(model,request);
	}

	/**
	 * 获取学生相关信息
	 */
	public HashMap<String,String> serv_getStuInfo(String xh){
		String querry = " where xh='"+xh+"' ";
		String[] colList= new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","sfzh","syd","csrq","mzmc","zzmmmc","jtdz","rxrq","jg","jtyb"};
		return JxjsqshDAO.dao_getInfo("view_xsxxb", colList,querry);
	}

	public HashMap<String,String> serv_getXsJxjInfo(String pk) {
		String querry = " where xh||xn||xq||jxjdm='"+pk+"' ";
		return JxjsqshDAO.dao_getInfo("view_xsjxjb", null,querry);
	}

	public boolean JxjKnbSave(JxjsqshModel model, HttpServletRequest request) throws Exception {
		return myDAO.JxjKnbSave(model,request);
	}

	public boolean JxjGjjxjSave(JxjsqshModel model, HttpServletRequest request) throws Exception {
		return myDAO.JxjGjjxjSave(model,request);
	}

	public  List<HashMap<String, String>> getCjList(String xh, String xn) {
		// TODO 自动生成方法存根
		JhzyPjpyDAO unitDAO = new JhzyPjpyDAO();
		return unitDAO.getFzPmList(xh, xn,"");
	}

	public ArrayList<String[]> getJxjshList(String userType, String userName, JxjsqshModel model, String jxjdm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return myDAO.getJxjshList(userType, userName, model,jxjdm); 
	}

	/**
	 * 奖学金审核表头
	 */
	public List<HashMap<String, String>> getJxjshTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","学年","学号","姓名","院系","班级","辅导员审核","院系审核","学校审核"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}

	public boolean serv_JxjsqshChk(String userType, String pkValue, String jxjdm, String fdyshyj, String xyshyj, String xxshyj, String yesNo)throws Exception{
			return myDAO.dao_JxjsqshChk(userType, pkValue, jxjdm, fdyshyj, xyshyj, xxshyj, yesNo);
	}
	
	public HashMap<String,String> serv_getJxjInfo(String pkValue){
		String querry = " where xh||xn||xq||jxjdm='"+pkValue+"' ";
		return JxjsqshDAO.dao_getInfo("xsjxjb", null,querry);
	}
	
	/**
	 * 所获等级列表
	 */
	public List<HashMap<String,String>>serv_getShDjList(String xh){
		RychDAO dao = new RychDAO();
		return dao.getShDjList(xh);
	}
	
	/**
	 * 所获奖学金列表
	 */
	public List<HashMap<String,String>>serv_getShJxjList(String xh){
		RychDAO dao = new RychDAO();
		return dao.getShJxjList(xh);
	}
	
	/**
	 * 获取学生成绩相关数据集
	 */
	public List<HashMap<String,String>> serv_getCjList(String xh){
		RychDAO dao = new RychDAO();
		return dao.dao_getCjList(xh);
	}
	
	/**
	 * 获取审核下拉框信息
	 */
	public List<HashMap<String, String>>  serv_getChkList(){
		return myDAO.dao_getChkList();
	}
	
	/**
	 * 奖学金批量审核
	 */
	public boolean serv_JxjsqshCk(String userType,String check,String jxjdm,String pkVStr) throws SQLException{
		return myDAO.dao_JxjsqshCk(userType, check, jxjdm, pkVStr);
	}
	
	/**
	 * 奖学金查询列表
	 * @param jxjdm 
	 */
	public  ArrayList<String[]> serv_getJxjCxList(String userType,String userName,JxjsqshModel model, String jxjdm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return myDAO.getJxjCxList(userType, userName, model,jxjdm);
	}

	public boolean serv_jxjDel(String userType, String jxjdm, String pkVStr) throws SQLException {
		return myDAO.dao_jxjDel(userType,jxjdm, pkVStr);
	}

}
