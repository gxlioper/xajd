/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-10-15 下午02:46:01</p>
 */
package xgxt.xsgygl.jhzyjsxy.yxlcqsz;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;


public class YxlcqszService {
	
	/**
	 * 百佳寝室长申请保存
	 * @throws Exception 
	 */
	public boolean serv_bjqszSqSave(YxlcqszModel model) throws Exception{
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.bjqszSqSave(model);
	}
	/**
	 * 百佳寝室长相关信息
	 */
	public HashMap<String,String> serv_getBjqszInfo(String xh,String xn){	
		String querry = " where xh||xn='"+xh+xn+"' ";
		return CommonQueryDAO.dao_getInfo("view_bjqszxx", null,querry);
	}
	/**
	 * 百佳寝室长相关信息
	 */
	public HashMap<String,String> serv_getBjqszInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return CommonQueryDAO.dao_getInfo("view_bjqszxx", null,querry);
	}
	/**
	 *百佳寝室长查询
	 */
	public  ArrayList<String[]> serv_getBjqszCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getBjqszCxList(yesNo,userType, userName, model);
	}
	/**
	 * 百佳寝室长查询表头
	 */
	public List<HashMap<String, String>> getBjqszCxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","ldmc","qsh","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","学年","学号","姓名","院系","班级","楼栋","寝室","辅导员审核","院系审核","学校审核"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 *百佳寝室长审核查询
	 */
	public  ArrayList<String[]> serv_getBjqszShCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getBjqszShCxList(yesNo,userType, userName, model);
	}
	/**
	 * 二十佳长申请保存
	 * @throws Exception 
	 */
	public boolean serv_esjczSqSave(YxlcqszModel model) throws Exception{
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.esjczSqSave(model);
	}	
	/**
	 * 二十佳层长查询表头
	 */
	public List<HashMap<String, String>> getEsjczCxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","ldmc","lc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","学年","学号","姓名","院系","班级","楼栋","楼层","辅导员审核","院系审核","学校审核"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 *二十佳层长查询
	 */
	public  ArrayList<String[]> serv_getEsjczCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getEsjczCxList(yesNo, userType, userName, model);
	}	
	/**
	 *二十佳层长审核查询
	 */
	public  ArrayList<String[]> sersv_getEsjczShCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getEsjczShCxList(yesNo, userType, userName, model);
	}	
	/**
	 * 五佳楼长申请保存
	 * @throws Exception 
	 */
	public boolean serv_wjlzSqSave(YxlcqszModel model) throws Exception{
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.wjlzSqSave(model);
	}	
	/**
	 * 五佳楼长查询表头
	 */
	public List<HashMap<String, String>> getWjlzCxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","ldmc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","学年","学号","姓名","院系","班级","楼栋","辅导员审核","院系审核","学校审核"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 *五佳楼长查询
	 */
	public  ArrayList<String[]> serv_getWjlzCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getWjlzCxList(yesNo, userType, userName, model);
	}
	/**
	 *五佳楼长审核查询
	 */
	public  ArrayList<String[]> serv_getWjlzShCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getWjlzShCxList(yesNo, userType, userName, model);
	}
	/**
	 *优秀楼层寝室长批量删除
	 */
	public boolean serv_yxlcqszDel(String userType,String xmk,String pkVStr) throws SQLException{
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.dao_yxlcqszDel(userType, xmk, pkVStr);
	}
	/**
	 * 优秀楼层寝室长批量审核
	 */
	public boolean serv_yxlcqszCk(String userType,String check,String xmk,String pkVStr) throws SQLException{
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.dao_yxlcqszCk(userType, check, xmk, pkVStr);
	}
	/**
	 * 优秀楼层寝室长单个审核
	 * @throws Exception 
	 */
	public boolean serv_yxlcqszChk(String userType,String pkValue,String xmk,String yesNo) throws Exception{
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.dao_yxlcqszChk(userType, pkValue, xmk, yesNo);
	}
	/**
	 * 二十佳层长相关信息
	 */
	public HashMap<String,String> serv_getEsjczInfo(String xh,String xn){
		String querry = " where xh||xn='"+xh+xn+"' ";
		return CommonQueryDAO.dao_getInfo("view_esjczxx", null,querry);
	}
	/**
	 * 二十佳层长相关信息
	 */
	public HashMap<String,String> serv_getEsjczInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return CommonQueryDAO.dao_getInfo("view_esjczxx", null,querry);
	}
	/**
	 * 五佳楼长相关信息
	 */
	public HashMap<String,String> serv_getWjlzInfo(String xh,String xn){
		String querry = " where xh||xn='"+xh+xn+"' ";
		return CommonQueryDAO.dao_getInfo("view_wjlzxx", null,querry);
	}
	/**
	 * 五佳楼长相关信息
	 */
	public HashMap<String,String> serv_getWjlzInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return CommonQueryDAO.dao_getInfo("view_wjlzxx", null,querry);
	}
	/**
	 * 判断是否担任楼长，层长，寝室长
	 * @param type："lz","cz","qsz" 
	 * @param xh
	 * @return
	 */
	public boolean  lcqszPd(String type,String xh){
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.lcqszPd(type, xh);
	}
	/**
	 * 百佳寝室长学生查询
	 */
	public  ArrayList<String[]> serv_getBjqszList_stu(String xh) {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getBjqszList_stu(xh);
	}
	/**
	 * 二十佳层长学生学生查询
	 */
	public  ArrayList<String[]> serv_getEsjczList_stu(String xh) {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getEsjczList_stu(xh);
	}
	/**
	 * 五佳楼长学生查询
	 */
	public  ArrayList<String[]> getWjlzList_stu(String xh) {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getWjlzList_stu(xh);
	}
}
