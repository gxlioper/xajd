/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-10-8 下午01:34:04</p>
 */
package xgxt.xsgygl.jhzyjsxy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.xsgygl.jhzyjsxy.yxlcqsz.YxlcqszModel;


public class GyglJhzyService {
	/**
	 * 获取学生相关信息
	 */
	public HashMap<String,String> serv_getStuInfo(String xh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		String querry = " where xh='"+xh+"' ";
		String[] colList= new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","sfzh","syd","csrq","mzmc","zzmmmc","zw","jtdz"};
		return dao.dao_getInfo("view_xsxxb", colList,querry);
	}
	/**
	 * 获取楼栋
	 */
	public List<HashMap<String,String>> getSsLdList(){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getSsLdList();		
	}
	/**
	 * 获取楼层
	 */
	public List<HashMap<String,String>> getSsLcList(String lddm){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getSsLcList(lddm);		
	}
	/**
	 * 获取宿舍
	 */
	public List<HashMap<String,String>> getSsmList(String lddm,String lch,String sszt,String fdy,String xn,String xq){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getSsmList(lddm, lch,sszt,fdy,xn,xq);	
	}
	/**
	 * 公寓辅导员信息保存
	 */
	public boolean serv_GyfdyIntoSave(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		 return dao.dao_GyfdyIntoSave(model);
	 }
	/**
	 * 获取公寓辅导员用户相关信息
	 */
	public List<HashMap<String,String>> getUserInfoList(){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getUserInfoList();	
	}
	/**
	 * 获取行政公寓辅导员用户相关信息
	 */
	public List<HashMap<String,String>> getXzGyfdyInfoList(){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getXzGyfdyInfoList();	
	}
	/**
	 * 公寓辅导员用户信息查询
	 * @throws Exception 
	 */
	public List<String[]> ser_QueryGyfdyInfo(GyglGyfdyModel model,GyglJhzyForm myForm) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_QueryGyfdyInfo(model,myForm);	
	}
	/**
	 * 行政公寓辅导员用户信息查询
	 * @throws Exception 
	 */
	public List<String[]> ser_QueryXzGyfdyInfo(GyglGyfdyModel model,GyglJhzyForm myForm) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_QueryXzGyfdyInfo(model,myForm);	
	}
	/**
	 * 公寓辅导员用户信息
	 * @throws Exception 
	 */
	public HashMap<String,String> ser_GyfdyInfo(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_GyfdyInfo(model);	
	}
	/**
	 * 文明公寓楼用户信息
	 * @throws Exception 
	 */
	public HashMap<String,String> ser_wmgylInfo(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_wmgylInfo(model);	
	}
	/**
	 * 文明公寓楼用户信息
	 * @throws Exception 
	 */
	public HashMap<String,String> ser_wmgylInfosh(String id) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_wmgylInfosh(id);	
	}
	/**
	 * 公寓辅导员用户信息删除
	 * @throws Exception 
	 */
	public boolean ser_DelGyfdyInfo(String yhm,String xn,String xq,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_DelGyfdyInfo(yhm,xn,xq,request);	
	}
	/**
	 * @author luo
	 * @describe 获得表头
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * 公寓辅导员信息保存
	 */
	public boolean serv_selectIntoFdy(String dqxn,String dqxq,String xn,String xq,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_selectIntoFdy(dqxn, dqxq, xn, xq,request);
	 }
	/**
	 * 文明公寓楼保存
	 */
	public boolean serv_saveWmgyl(GyglGyfdyModel model,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_saveWmgyl(model,request);
	 }
	/**
	 * 文明公寓楼查询表头
	 */
	public List<HashMap<String, String>> getWmgyCxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","nj","lzm","qss","xss","xydm","wshgl","wsyxl","xjqsl","xxsh"}; 
		colListCN = new String[]{"pk","学年","年级","楼栋","寝室数","学生数",Base.YXPZXY_KEY,"卫生合格率","卫生优秀率","星级寝室率","学校审核"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * 文明公寓楼保存
	 */
	public boolean serv_isExists(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_isExists(model);
	 }
	/**
	 *文明公寓楼查询
	 */
	public  ArrayList<String[]> serv_getWmgylCxList(String yesNo,String userType,String userName, GyglGyfdyModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getWmgylCxList(yesNo, userType, userName, model);
	}	
	/**
	 *是否楼长
	 */
	public  HashMap<String, String> serv_isLz(String userName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_isLz(userName);
	}
	/**
	 *通用查询查询
	 */
	public  ArrayList<String[]> commonQuery(String yesNo,String userType,String userName, GyglGyfdyModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getWmgylCxList(yesNo, userType, userName, model);
	}
	/**
	 * 文明公寓楼修改
	 */
	public boolean serv_WmgylUpdate(GyglGyfdyModel model,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		 return dao.dao_WmgylUpdate(model,request);
	 }
	/**
	 * 文明公寓楼修改
	 */
	public String serv_WmgylDel(String pkStr,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		 return dao.dao_WmgylDel(pkStr,request);
	 }
	/**
	 * 文明公寓楼审核
	 */
	public String serv_WmgylSh(String pkStr,String str,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		 return dao.dao_WmgylSh(pkStr,str,request);
	 }
	/**
	 * 文明公寓楼单个审核
	 */
	public Boolean serv_WmgyldgSh(String pkStr,String str,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		 return dao.dao_WmgyldgSh(pkStr,str,request);
	 }
	 /**
	 * 获取辅导员入住信息
	 */
	public  HashMap<String,String> getFdyRzXx(String zgh){
		String querry = " where zgh='"+zgh+"' ";
		return CommonQueryDAO.dao_getInfo("view_fdyrzxx", null, querry);	
	}
	 /**
     * 辅导员入住信息保存 
     */
	public boolean serv_fdyRzXxSave(FdyRzxxModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_fdyRzXxSave(model);
	}
	/**
	 *辅导员入住信息查询表头
	 */
	public List<HashMap<String, String>> getFdyRzXxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"zgh","xm","xymc","ldmc","qsh","lxdh"}; 
		colListCN = new String[]{"pk","姓名","院系","楼栋","房间号","联系电话"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	 /**
     * 辅导员入住信息查询
     */
	public  ArrayList<String[]> serv_getFdyRzxxList(FdyRzxxModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getFdyRzxxList(model);
	}
	/**
	 * 辅导员入住信批量删除
	 */
	public boolean serv_fdyRzxxDel(String pkVStr) throws SQLException{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_fdyRzxxDel(pkVStr);
	}
	/**
	 * 公寓党建信息查询表头
	 */
	public List<HashMap<String, String>> getGydjXxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"guid","xh","xm","xymc","ldmc","qsh","cjhdsj","hdzt"}; 
		colListCN = new String[]{"pk","学号","姓名","院系","楼栋","房间号","参加活动时间","活动主题"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
     * 公寓党建信息查询
     */
	public ArrayList<String[]> serv_getGydjxxList(GydjglxxbModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getGydjxxList(model);
	}
	/**
     * 公寓党建信息保存 
     */
	public String[] serv_gydjXxSave(GydjglxxbModel model,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_gydjXxSave(model,request);
	}
	/**
	 * 获取公寓党建信息信息
	 */
	public  HashMap<String,String> getGydjXx(String guid){
		String querry = " where guid='"+guid+"' ";
		return CommonQueryDAO.dao_getInfo("view_gydjglxxb", null, querry);	
	}
	/**
	 * 公寓党建信息批量删除
	 */
	public boolean serv_gydjDel(String pkVStr) throws SQLException{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_gydjDel(pkVStr);
	}
	
	/**
	 * 获取寝室长相关信息
	 * @param xh 学号
	 * @return
	 */
	public  HashMap<String,String> getQszInfo(String pkValue){
		HashMap<String,String> map = new HashMap<String, String>();
		if(!Base.isNull(pkValue)){
			String querry = " where pk='"+pkValue+"' ";
			map= CommonQueryDAO.dao_getInfo("view_qszxx",null,querry);
		}
		return map;
	}
	
	/**
	 * 判断该学生是否已提交星级寝室申请和是否已审核
	 */
	public String isChecking_ser(String xh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.isChecking_db(xh);
	}
	/**
	 * 获得星级寝室情况
	 */
	public HashMap<String,String> getXszsqk_ser(String xh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getXszsqk_db(xh);
	}
	/**
	 * 获得星级寝室情况
	 */
	public HashMap<String,String> getXjqsqk_ser(String lddm,String cs,String qsh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getXjqsqk_db(lddm, cs, qsh);
	}
	/**
	 * 保存星级寝室
	 */
	public boolean saveXjqs_ser(YxlcqszModel model){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		try {
			return dao.saveXjqs_db(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 获得宿舍人数
	 */
	public String getSsrs_ser(String ssbh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getSsrs_db(ssbh);
	}
	
	/**
	 * 获得宿舍班级
	 */
	public String getSsbj_ser(String ssbh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getSsbj_db(ssbh);
	}
	
	/**
	 * 星级寝室查询
	 */
	public List<HashMap<String,String>>  queryXjqs_ser(YxlcqszModel model,String userType,String userName){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.queryXjqs_db(model, userType,userName);
	}
	
	/**
	 * 星级寝室批量审核
	 */
	public boolean xjqsPlsh_ser(String doType,String pkString,String userType){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		try {
			return dao.xjqsPlsh_db(doType, pkString,userType);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 星级寝室单个审核页面
	 */
	public HashMap<String,String> xjqsDgshPage_ser(String pkValue,String userType,String view){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		if("yes".equals(view)){
			return dao.xjqsview_db(pkValue);
		}else{
			return dao.xjqsDgshPage_db(pkValue,userType);
		}	
	}
	/**
	 * 星级寝室单个审核
	 */
	public boolean xjqsDgsh_ser(String pkValue,String dj,String shzt,String userType){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		try {
			return dao.xjqsDgsh_db(pkValue, dj, shzt, userType);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 星级寝室结果查询
	 */
	public List<HashMap<String,String>>  queryXjqsShjg_ser(YxlcqszModel model,String userType,String userName){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.queryXjqsShjg_db(model, userType,userName);
	}
	/**
	 * 获得宿舍信息
	 */
	public HashMap<String,String>  getSsxx_ser(String ssbh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getSsxx_db(ssbh);
	}
	/**
	 * 删除星级寝室
	 */
	public boolean delXjqs_ser(String pkStr){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		try {
			return dao.delXjqs_db(pkStr);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
     * 寝室长信息保存 
     */
	public boolean serv_QszXxSave(QszXxModel model,String pkValue) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_QszXxSave(model,pkValue);
	}
	/**
	 * 公寝室长信息查询表头
	 */
	public List<HashMap<String, String>> getQszXxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;		
		colListCN = new String[]{"pk","学号","姓名","性别","院系","班级","楼栋","房间号","联系电话","是否在职"}; 
		colList = new String[]{"pk","qsz","xm","xb","xymc","bjmc","ldmc","qsh","lxdh","sfzz"}; 
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	 /**
     * 寝室长信息查询
     */
	public  ArrayList<String[]> getQszXxCxList(QszXxModel model,String userType,String userName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getQszXxCxList(model,userType,userName);
	}
	/**
	 * 寝室长信息批量删除
	 */
	public boolean serv_qszDel(String pkVStr) throws SQLException{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_qszDel(pkVStr);
	}
	/**
     * 寝室卫生检查查询
     */
	public ArrayList<List> serv_qswsjcQuery(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_qswsjcQuery(model);
	}
	/**
     * 寝室卫生检查查询
     */
	public List<String[]> serv_qswsjcjgQuery(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_qswsjcjgQuery(model);
	}
	/**
	 * 寝室卫生检查 表头
	 */
	public List<HashMap<String, String>> getQswsjcTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"ldmc","cs","ssbh","fdy","xymc","wsdj","dm","bz"}; 
		colListCN = new String[]{"楼栋名称","楼层","寝室编号","辅导员","所属"+Base.YXPZXY_KEY,"卫生等级","电器","备注"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * 寝室卫生检查查询表头
	 */
	public List<HashMap<String, String>> getQswsjccxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"xn","xq","lddm","cs","qsh","xymc","xm","wsjc"}; 
		colListCN = new String[]{"学年","学期","楼栋","楼层","寝室号",Base.YXPZXY_KEY+"名称","辅导员姓名","卫生检查情况"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * 自管会部门List
	 */
	public List<HashMap<String, String>> GetZghbmList() {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.GetZghbmList();
	}
	/**
	 * 自管会工作人员查询表头
	 */
	public List<HashMap<String, String>> getZghryTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"xh","xm","xymc","bmmc","zw","lxdh","ldmc","cs","qsh"}; 
		colListCN = new String[]{"pk","姓名",Base.YXPZXY_KEY,"部门","职务","联系电话","楼栋","楼层","寝室号"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
     * 自管会工作人员信息查询
     */
	public ArrayList<String[]> serv_getZghryList(ZghryModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getZghryList(model);
	}
	/**
	 * 获取自管会工作人员信息
	 */
	public  HashMap<String,String> getZghryXx(String xh){
		String querry = " where xh='"+xh+"' ";
		return CommonQueryDAO.dao_getInfo("view_zghryxxb", null, querry);	
	}
	/**
     * 自管会工作人员保存 
     */
	public boolean serv_zghrySave(ZghryModel model,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_zghrySave(model,request);
	}
	/**
	 * 自管会工作人员批量删除
	 */
	public boolean serv_zghryDel(String pkVStr) throws SQLException{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_zghryDel(pkVStr);
	}
	/**
	 * 寝室卫生检查保存
	 * @throws SQLException 
	 */
	public boolean serv_qswsjcSave(GyglGyfdyModel model) throws SQLException{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_qswsjcSave(model);
	}
	/**
	 * 获取寝室卫生检查存在id
	 */
	public String serv_getqswsjcChecked(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getqswsjcChecked(model);
	}
	/**
	 * 获取相关公寓辅导员卫生检查对应扣分值信息
	 */
	public HashMap<String, String> serv_getWsjcXx(String userName,String xn,String xq,String yf){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getWsjcXx(userName, xn, xq, yf);
	}
	/**
	 * 自主息灯检查查询表头
	 */
	public List<HashMap<String, String>> getZzxdjcTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"ssbhT","ldmc","cs","qsh","fgfdy","xdqk"}; 
		colListCN = new String[]{"宿舍编号","楼栋","层数","寝室号","分管辅导员","熄灯情况"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
     * 自主息灯检查信息查询
     */
	public ArrayList<String[]> serv_getZzxdjcList(ZzxdglModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getZzxdjcList(model);
	}
	/**
     * 自主息灯检查保存
     */
	public void ser_saveZzxdjc(ZzxdglModel model) {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		dao.saveZzxdjc(model);
	}
	/**
	 * 自主息灯检查查询表头
	 */
	public List<HashMap<String, String>> getZzxdjcQueryTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"ldmc","cs","qsh","rq"}; 
		colListCN = new String[]{"楼栋","层数","寝室号","未熄灯日期"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
     * 自主息灯检查信息查询
     */
	public ArrayList<String[]> serv_getZzxdjcQueryList(ZzxdglModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getZzxdjcQueryList(model);
	}
	/**
     * 公寓辅导员考核保存 
     */
	public boolean serv_gyfdyCheckSave(GyfdyKhModel model,String pkValue) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		 return dao.dao_gyfdyCheckSave(model, pkValue);
	}
	/**
	 * 公寓辅导员考核信息
	 */
	public  HashMap<String,String> getGyfdyKhXx(String pkValue){
		String querry = " where xn||xq||yf||zgh='"+pkValue+"' ";
		return CommonQueryDAO.dao_getInfo("view_gyfdykhxx", null, querry);	
	}
	/**
	 * 自主息灯检查查询表头
	 */
	public List<HashMap<String, String>> getGyfdyKhTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xqmc","yf","zgh","xm","zpf"}; 
		colListCN = new String[]{"pk","学年","学期","月份","工号","姓名","总评分"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	 /**
     * 公寓辅导员考核信息查询
     */
	public  ArrayList<String[]> getgyfdyCheckList(GyfdyKhModel model,String userType,String userName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getgyfdyCheckList(model, userType, userName);
	}
	/**
	 *公寓辅导员考核批量删除
	 */
	public boolean serv_gyfdyCheckDel(String pkVStr) throws SQLException{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_gyfdyCheckDel(pkVStr);
	}
	/**
	 * 卫生检查达标信息
	 * @throws Exception 
	 */
	public HashMap<String,String>serv_wsjcDbInfo(String xn,String xq,String yf,String lddm,String lc,String qsh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.wsjcDbInfo(xn, xq, yf, lddm, lc, qsh);
	}
	/**
	 * 五佳楼长相关信息
	 */
	public HashMap<String,String> serv_getLzInfo(String xh){
		String querry = " where lz='"+xh+"' and sfzz='是' ";
		return CommonQueryDAO.dao_getInfo("view_lzxx", null,querry);
	}
	/**
	 * 星级文明寝室申请学生查询
	 */
	public  ArrayList<String[]> serv_getXjwmqsList_stu(String xh) {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getXjwmqsList_stu(xh);
	}
	/**
	 * 自主息灯检查统计表头
	 */
	public List<HashMap<String, String>> getZzxdjcTjTopTr(String tjlx) {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		if ("1".equalsIgnoreCase(tjlx)) {
			colList = new String[] { "pm", "dw", "qszs", "xds", "xdl" };
			colListCN = new String[] { "排名", Base.YXPZXY_KEY, "检查寝室总数", "熄灯寝室数", "熄灯率%" };
		} else if ("2".equalsIgnoreCase(tjlx)) {
			colList = new String[] { "pm", "dw", "qszs", "xds", "xdl" };
			colListCN = new String[] { "排名", "楼栋", "检查寝室总数", "熄灯寝室数", "熄灯率%" };
		} else if ("3".equalsIgnoreCase(tjlx)) {
			colList = new String[] { "pm", "dw", "qszs", "xds", "xdl" };
			colListCN = new String[] { "排名", "辅导员", "检查寝室总数", "熄灯寝室数", "熄灯率%" };
		}
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
     * 自主息灯检查统计
     */
	public ArrayList<String[]> serv_getZzxdjcTjList(ZzxdglModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getZzxdjcTjList(model);
	}
	public List<HashMap<String,String>>ser_getKhdxList(){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getKhdxList();
	}
	public List<HashMap<String,String>>ser_getGyzbList(){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getGyzbList();
	}
}
