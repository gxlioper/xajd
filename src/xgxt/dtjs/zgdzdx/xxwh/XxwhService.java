package xgxt.dtjs.zgdzdx.xxwh;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.dtjs.zgdzdx.ZgdzdxDtjsForm;

public class XxwhService {
	
	XxwhDAO dao = new XxwhDAO();

	public ArrayList<String[]> getSxjyhdList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// 获得思想教育活动列表
		return dao.getSxjyhdList(myModel,tableName);
	}

	public List<HashMap<String, String>> getSxjyhdTopTr(String tableName) {
		// 获得思想教育活动列表表头
		return dao.getSxjyhdTopTr(tableName);
	}

	public HashMap<String, String> getSxjyhdOne(String pk) {
		// TODO 自动生成方法存根
		return dao.getSxjyhdOne(pk);
	}

	public boolean SxjyhdUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.SxjyhdUpdate(pk,myModel,request);
	}

	public boolean SxjyhdDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.SxjyhdDelete(pk,request);
	}

	public ArrayList<String[]> getFblwList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
		return dao.getFblwList(myModel,tableName);
	}

	public List<HashMap<String, String>> getFblwTopTr(String tableName) {
		// TODO 自动生成方法存根
		return dao.getFblwTopTr(tableName);
	}

	public HashMap<String, String> getFblwOne(String pk) {
		// TODO 自动生成方法存根
		return dao.getFblwOne(pk);
	}

	public boolean fblwUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.fblwUpdate(pk,myModel,request);
	}

	public boolean fblwDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.fblwDelete(pk,request);
	}

	public ArrayList<String[]> getKyxmList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
		return dao.getKyxmList(myModel,tableName);
	}

	public List<HashMap<String, String>> getKyxmTopTr(String tableName) {
		// TODO 自动生成方法存根
		return dao.getKyxmTopTr(tableName);
	}

	public HashMap<String, String> getKyxmOne(String pk) {
		// TODO 自动生成方法存根
		return dao.getKyxmOne(pk);
	}

	public boolean kyxmUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.kyxmUpdate(pk,myModel,request);
	}

	public boolean kyxmDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.kyxmDelete(pk,request);
	}

	public ArrayList<String[]> getFdyzzList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
		return dao.getFdyzzList(myModel,tableName);
	}

	public List<HashMap<String, String>> getFdyzzTopTr(String tableName) {
		// TODO 自动生成方法存根
		return dao.getFdyzzTopTr(tableName);
	}

	public HashMap<String, String> getFdyzzOne(String pk) {
		// TODO 自动生成方法存根
		return dao.getFdyzzOne(pk);
	}

	public boolean fdyzzUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.fdyzzUpdate(pk,myModel,request);
	}

	public boolean fdyzzDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.fdyzzDelete(pk,request);
	}

	public HashMap<String, String> getYjzlOne(String pk) {
		// TODO 自动生成方法存根
		return dao.getYjzlOne(pk);
	}

	public ArrayList<String[]> getYjzlList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
		return dao.getYjzlList(myModel,tableName);
	}

	public List<HashMap<String, String>> getYjzlTopTr(String tableName) {
		// TODO 自动生成方法存根
		return dao.getYjzlTopTr(tableName);
	}

	public boolean yjzlUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.yjzlUpdate(pk,myModel,request);
	}

	public boolean yjzlDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.yjzlDelete(pk,request);
	}

	public List<HashMap<String, String>> getBmList() {
		// TODO 自动生成方法存根
		return dao.getBmList();
	}

	public List<HashMap<String, String>> getFdyList(HashMap<String, String> rs) {
		// TODO 自动生成方法存根
		String bmdm = rs.get("bmdm");
		return dao.getFdyList(bmdm);
	}

	public List<HashMap<String, String>> getFblwlbList() {
		// TODO 自动生成方法存根
		return dao.getFblwlbList();
	}
	
	public List<HashMap<String, String>> getXmjbList() {
		// TODO 自动生成方法存根
		return dao.getXmjbList();
	}
	
		/**
	 * 党员活动室保存.
	 * 
	 * @param pk the pk
	 * @param request the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public boolean dyhdsgl_save(String pk,DyhdsglModel model,HttpServletRequest request,String czlx,String xxk) throws Exception {
		// TODO 自动生成方法存根
		return dao.dyhdsgl_save(pk,model,request,czlx,xxk);
	}
	/**
	 * 党员活动室保存.
	 * 
	 * @param pk the pk
	 * @param request the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public boolean dyhdsgl_sh(String pk,DyhdsglModel model,HttpServletRequest request,String czlx,String xxk) throws Exception {
		// TODO 自动生成方法存根
		return dao.dyhdsgl_sh(pk,model,request,czlx,xxk);
	}
	/**
	 * 党员活动室查询.
	 * 
	 * @param pk the pk
	 * @param request the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public ArrayList<String[]> dyhdsgl_query(String xxk,DyhdsglModel model,ZgdzdxDtjsForm form) throws Exception {
		// TODO 自动生成方法存根
		return dao.dyhdsgl_query(xxk,model,form);
	}

	public List<HashMap<String, String>> get_dyhdsglTopTr(String tableName) {
		// 获得党员活动室管理列表表头
		return dao.get_dyhdsglTopTr(tableName);
	}
	
	public int get_count(DyhdsglModel model,String table) {
		// 获得总记录数
		return dao.get_count(model,table);
	}
	public HashMap<String, String> get_viewRs(String pk,String table,String xxk) {
		return dao.get_viewRs(pk,table,xxk);
	}
	public String get_del(String pk,String table,HttpServletRequest request) throws SQLException {
		return dao.get_del(pk,table,request);
	}

	public ArrayList<String[]> getDyjhList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
		return dao.getDyjhList(myModel,tableName);
	}

	public List<HashMap<String, String>> getDyjhTopTr(String tableName) {
		// TODO 自动生成方法存根
		return dao.getDyjhTopTr(tableName);
	}

	public HashMap<String, String> getDyjhOne(String pk) {
		// TODO 自动生成方法存根
		return dao.getDyjhOne(pk);
	}

	public boolean dyjhUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.dyjhUpdate(pk,myModel,request);
	}

	public boolean dyjhDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.dyjhDelete(pk,request);
	}

	public ArrayList<String[]> getXgxxList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
		return dao.getXgxxList(myModel,tableName);
	}

	public List<HashMap<String, String>> getXgxxTopTr(String tableName) {
		// TODO 自动生成方法存根
		return dao.getXgxxTopTr(tableName);
	}

	public HashMap<String, String> getXgxxOne(String pk, HttpServletRequest request) {
		// TODO 自动生成方法存根
		return dao.getXgxxOne(pk,request);
	}

	public boolean xgxxUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		return dao.xgxxUpdate(pk, myModel, request);
	}

	public boolean xgxxDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.xgxxDelete(pk,request);
	}

	public List<HashMap<String, String>> getXgxxlbList() {
		// TODO 自动生成方法存根
		return dao.getXgxxlbList();
	}

	public ArrayList<String[]> getXgxxTjList(XxwhModel myModel) {
		// TODO 自动生成方法存根
		return dao.getXgxxTjList(myModel);
	}
}
