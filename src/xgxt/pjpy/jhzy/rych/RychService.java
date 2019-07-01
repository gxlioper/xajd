/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-10-10 上午09:31:14</p>
 */
package xgxt.pjpy.jhzy.rych;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class RychService {
	/**
	 * 获取学生相关信息
	 */
	public HashMap<String,String> serv_getStuInfo(String xh){
		String querry = " where xh='"+xh+"' ";
		String[] colList= new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","sfzh","syd","csrq","mzmc","zzmmmc","zw","jtdz"};
		return RychDAO.dao_getInfo("view_xsxxb", colList,querry);
	}
	/**
	 * 获取省级优秀毕业生荣誉称号相关信息
	 */
	public HashMap<String,String> serv_getSjyxbysInfo(String xh,String xn){
		String querry = " where xh||xn='"+xh+xn+"' ";
		return RychDAO.dao_getInfo("JHZY_SJYXBYSB", null,querry);
	}
	/**
	 * 获取省级优秀毕业生荣誉称号相关信息()
	 */
	public HashMap<String,String> serv_getSjyxbysInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return RychDAO.dao_getInfo("JHZY_SJYXBYSB", null,querry);
	}
	/**
	 * 省级优秀毕业生荣誉称号申请保存
	 * @throws Exception 
	 */
	public boolean serv_sjyxbysSqSave(sjyxbysRychModel model) throws Exception{
		RychDAO dao = new RychDAO();
		return dao.sjyxbysSqSave(model);
	}
	/**
	 * 获取三好学生荣誉称号相关信息
	 */
	public HashMap<String,String> serv_getShsInfo(String xh,String xn){
		String querry = " where xh||xn='"+xh+xn+"' ";
		return RychDAO.dao_getInfo("JHZY_SHSB", null,querry);
	}
	/**
	 * 获取三好学生荣誉称号相关信息
	 */
	public HashMap<String,String> serv_getShsInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return RychDAO.dao_getInfo("JHZY_SHSB", null,querry);
	}
	/**
	 * 省级优秀毕业生荣誉称号表头
	 */
	public List<HashMap<String, String>> getSjyxbysSHTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","学年","学号","姓名","院系","班级","辅导员审核","院系审核","学校审核"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * 省级优秀毕业生审核查询
	 */
	public  ArrayList<String[]> serv_getSjyxbysList(String userType,String userName,sjyxbysRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		RychDAO dao = new RychDAO();
		return dao.getSjyxbysList(userType, userName, model);
	}
	/**
	 * 荣誉称号批量审核
	 */
	public boolean serv_rychCk(String userType,String check,String xmk,String pkVStr) throws SQLException{
		RychDAO dao = new RychDAO();
		return dao.dao_rychCk(userType, check, xmk, pkVStr);
	}
	/**
	 * 省级优秀毕业生查询
	 */
	public  ArrayList<String[]> serv_getSjyxbysCxList(String userType,String userName,sjyxbysRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		RychDAO dao = new RychDAO();
		return dao.getSjyxbysCxList(userType, userName, model);
	}
	/**
	 * 荣誉称号批量删除
	 */
	public boolean serv_rychDel(String userType,String xmk,String pkVStr) throws SQLException{
		RychDAO dao = new RychDAO();
		return dao.dao_rychDel(userType,xmk, pkVStr);
	}
	/**
	 * 获取审核下拉框信息
	 */
	public List<HashMap<String, String>>  serv_getChkList(){
		RychDAO dao = new RychDAO();
		return dao.dao_getChkList();
	}
	/**
	 * 荣誉称号审核
	 * @throws Exception 
	 */
	public boolean serv_rychChk(String userType,String pkValue,String xmk,String fdyshyj,String xyshyj,String xxshyj,String yesNo) throws Exception{
		RychDAO dao = new RychDAO();
		return dao.dao_rychChk(userType, pkValue, xmk, fdyshyj, xyshyj, xxshyj, yesNo);
	}
	/**
	 * 三好学生学生查询
	 */
	public  ArrayList<String[]> serv_getShxsList_stu(String xh) {
		RychDAO dao = new RychDAO();
		return dao.getShxsList_stu(xh);
	}
	/**
	 * 单项优秀学生学生查询
	 */
	public  ArrayList<String[]> serv_getDxyxbysList_stu(String xh) {
		RychDAO dao = new RychDAO();
		return dao.getDxyxbysList_stu(xh);
	}
	
	/**
	 * 省级优秀毕业生学生查询
	 */
	public  ArrayList<String[]> serv_getSjyxbysList_stu(String xh){
		RychDAO dao = new RychDAO();
		return dao.getSjyxbysList_stu(xh);
	}
	/**
	 * 校级优秀毕业生学生查询
	 */
	public  ArrayList<String[]> serv_getXjyxbysList_stu(String xh){
		RychDAO dao = new RychDAO();
		return dao.getXjyxbysList_stu(xh);
	}
	/**
	 * 获取校级优秀毕业生荣誉称号相关信息
	 */
	public HashMap<String,String> serv_getXjyxbysInfo(String xh,String xn){
		String querry = " where xh||xn='"+xh+xn+"' ";
		return RychDAO.dao_getInfo("JHZY_XJYXBYSB", null,querry);
	}
	/**
	 * 获取校级优秀毕业生荣誉称号相关信息(带关键值)
	 */
	public HashMap<String,String> serv_getXjyxbysInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return RychDAO.dao_getInfo("JHZY_XJYXBYSB", null,querry);
	}
	/**
	 * 校级优秀毕业生荣誉称号申请保存
	 * @throws Exception 
	 */
	public boolean serv_xjyxbysSqSave(XjyxbysRychModel model) throws Exception{
		RychDAO dao = new RychDAO();
		return dao.xjyxbysSqSave(model);
	}
	/**
	 * 校级优秀毕业生荣誉称号表头
	 */
	public List<HashMap<String, String>> getXjyxbysSHTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","学年","学号","姓名","院系","班级","辅导员审核","院系审核","学校审核"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * 校级优秀毕业生查询
	 */
	public  ArrayList<String[]> serv_getXjyxbysCxList(String userType,String userName,XjyxbysRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		RychDAO dao = new RychDAO();
		return dao.getXjyxbysCxList(userType, userName, model);
	}	
	/**
	 * 校级优秀毕业生审核查询
	 */
	public  ArrayList<String[]> serv_getXjyxbysList(String userType,String userName,XjyxbysRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		RychDAO dao = new RychDAO();
		return dao.getXjyxbysList(userType, userName, model);
	}
	/**
	 * 获取学生成绩相关数据集
	 */
	public List<HashMap<String,String>> serv_getCjList(String xh){
		RychDAO dao = new RychDAO();
		return dao.dao_getCjList(xh);
	}
	/**
	 * 三好生荣誉称号申请保存
	 * @throws Exception 
	 */
	public boolean serv_shsSqSave(ShsRychModel model) throws Exception{
		RychDAO dao = new RychDAO();
		return dao.shsSqSave(model);
	}
	/**
	 * 三好生荣誉称号表头
	 */
	public List<HashMap<String, String>> getShsTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","学年","学号","姓名","院系","班级","辅导员审核","院系审核","学校审核"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * 三好生查询
	 */
	public  ArrayList<String[]> serv_getShsCxList(String userType,String userName, ShsRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException  {
		RychDAO dao = new RychDAO();
		return dao.getShsCxList(userType, userName, model);
	}
	/**
	 * 三号学生审核查询
	 */
	public  ArrayList<String[]> serv_getShxsList(String userType,String userName, ShsRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		RychDAO dao = new RychDAO();
		return dao.getShxsList(userType, userName, model);
	}
	/**
	 * 单项优秀学生荣誉称号相关信息
	 */
	public HashMap<String,String> serv_getDxyxsInfo(String xh,String xn){
		String querry = " where xh||xn='"+xh+xn+"' ";
		return RychDAO.dao_getInfo("jhzy_dxyxxsb", null,querry);
	}
	/**
	 * 单项优秀学生荣誉称号相关信息
	 */
	public HashMap<String,String> serv_getDxyxsInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return RychDAO.dao_getInfo("jhzy_dxyxxsb", null,querry);
	}
	/**
	 *  单项优秀学生荣誉称号申请保存
	 * @throws Exception 
	 */
	public boolean serv_dxyxxsSqSave(DxyxxsModel model) throws Exception{
		RychDAO dao = new RychDAO();
		return dao.dxyxxsSqSave(model);
	}
	/**
	 * 单项优秀学生荣誉称号表头
	 */
	public List<HashMap<String, String>> getDxyxxsTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","学年","学号","姓名","院系","班级","辅导员审核","院系审核","学校审核"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * 单项优秀学生信息查询
	 */
	public  ArrayList<String[]> serv_getDxyxxsCxList(String userType,String userName, DxyxxsModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException  {
		RychDAO dao = new RychDAO();
		return dao.getDxyxxsCxList(userType, userName, model);
	}
	/**
	 * 单项优秀学生信息审核查询
	 */
	public  ArrayList<String[]> serv_getDxyxxsList(String userType,String userName, DxyxxsModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		RychDAO dao = new RychDAO();
		return dao.getDxyxxsList(userType, userName, model);
	}
	/**
	 * 所获奖学金列表
	 */
	public List<HashMap<String,String>>serv_getShJxjList(String xh){
		RychDAO dao = new RychDAO();
		return dao.getShJxjList(xh);
	}
	/**
	 * 所获等级列表
	 */
	public List<HashMap<String,String>>serv_getShDjList(String xh){
		RychDAO dao = new RychDAO();
		return dao.getShDjList(xh);
	}

		
}
