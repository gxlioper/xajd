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
		// ���˼�������б�
		return dao.getSxjyhdList(myModel,tableName);
	}

	public List<HashMap<String, String>> getSxjyhdTopTr(String tableName) {
		// ���˼�������б��ͷ
		return dao.getSxjyhdTopTr(tableName);
	}

	public HashMap<String, String> getSxjyhdOne(String pk) {
		// TODO �Զ����ɷ������
		return dao.getSxjyhdOne(pk);
	}

	public boolean SxjyhdUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.SxjyhdUpdate(pk,myModel,request);
	}

	public boolean SxjyhdDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.SxjyhdDelete(pk,request);
	}

	public ArrayList<String[]> getFblwList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO �Զ����ɷ������
		return dao.getFblwList(myModel,tableName);
	}

	public List<HashMap<String, String>> getFblwTopTr(String tableName) {
		// TODO �Զ����ɷ������
		return dao.getFblwTopTr(tableName);
	}

	public HashMap<String, String> getFblwOne(String pk) {
		// TODO �Զ����ɷ������
		return dao.getFblwOne(pk);
	}

	public boolean fblwUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.fblwUpdate(pk,myModel,request);
	}

	public boolean fblwDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.fblwDelete(pk,request);
	}

	public ArrayList<String[]> getKyxmList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO �Զ����ɷ������
		return dao.getKyxmList(myModel,tableName);
	}

	public List<HashMap<String, String>> getKyxmTopTr(String tableName) {
		// TODO �Զ����ɷ������
		return dao.getKyxmTopTr(tableName);
	}

	public HashMap<String, String> getKyxmOne(String pk) {
		// TODO �Զ����ɷ������
		return dao.getKyxmOne(pk);
	}

	public boolean kyxmUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.kyxmUpdate(pk,myModel,request);
	}

	public boolean kyxmDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.kyxmDelete(pk,request);
	}

	public ArrayList<String[]> getFdyzzList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO �Զ����ɷ������
		return dao.getFdyzzList(myModel,tableName);
	}

	public List<HashMap<String, String>> getFdyzzTopTr(String tableName) {
		// TODO �Զ����ɷ������
		return dao.getFdyzzTopTr(tableName);
	}

	public HashMap<String, String> getFdyzzOne(String pk) {
		// TODO �Զ����ɷ������
		return dao.getFdyzzOne(pk);
	}

	public boolean fdyzzUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.fdyzzUpdate(pk,myModel,request);
	}

	public boolean fdyzzDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.fdyzzDelete(pk,request);
	}

	public HashMap<String, String> getYjzlOne(String pk) {
		// TODO �Զ����ɷ������
		return dao.getYjzlOne(pk);
	}

	public ArrayList<String[]> getYjzlList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO �Զ����ɷ������
		return dao.getYjzlList(myModel,tableName);
	}

	public List<HashMap<String, String>> getYjzlTopTr(String tableName) {
		// TODO �Զ����ɷ������
		return dao.getYjzlTopTr(tableName);
	}

	public boolean yjzlUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.yjzlUpdate(pk,myModel,request);
	}

	public boolean yjzlDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.yjzlDelete(pk,request);
	}

	public List<HashMap<String, String>> getBmList() {
		// TODO �Զ����ɷ������
		return dao.getBmList();
	}

	public List<HashMap<String, String>> getFdyList(HashMap<String, String> rs) {
		// TODO �Զ����ɷ������
		String bmdm = rs.get("bmdm");
		return dao.getFdyList(bmdm);
	}

	public List<HashMap<String, String>> getFblwlbList() {
		// TODO �Զ����ɷ������
		return dao.getFblwlbList();
	}
	
	public List<HashMap<String, String>> getXmjbList() {
		// TODO �Զ����ɷ������
		return dao.getXmjbList();
	}
	
		/**
	 * ��Ա��ұ���.
	 * 
	 * @param pk the pk
	 * @param request the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public boolean dyhdsgl_save(String pk,DyhdsglModel model,HttpServletRequest request,String czlx,String xxk) throws Exception {
		// TODO �Զ����ɷ������
		return dao.dyhdsgl_save(pk,model,request,czlx,xxk);
	}
	/**
	 * ��Ա��ұ���.
	 * 
	 * @param pk the pk
	 * @param request the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public boolean dyhdsgl_sh(String pk,DyhdsglModel model,HttpServletRequest request,String czlx,String xxk) throws Exception {
		// TODO �Զ����ɷ������
		return dao.dyhdsgl_sh(pk,model,request,czlx,xxk);
	}
	/**
	 * ��Ա��Ҳ�ѯ.
	 * 
	 * @param pk the pk
	 * @param request the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public ArrayList<String[]> dyhdsgl_query(String xxk,DyhdsglModel model,ZgdzdxDtjsForm form) throws Exception {
		// TODO �Զ����ɷ������
		return dao.dyhdsgl_query(xxk,model,form);
	}

	public List<HashMap<String, String>> get_dyhdsglTopTr(String tableName) {
		// ��õ�Ա��ҹ����б��ͷ
		return dao.get_dyhdsglTopTr(tableName);
	}
	
	public int get_count(DyhdsglModel model,String table) {
		// ����ܼ�¼��
		return dao.get_count(model,table);
	}
	public HashMap<String, String> get_viewRs(String pk,String table,String xxk) {
		return dao.get_viewRs(pk,table,xxk);
	}
	public String get_del(String pk,String table,HttpServletRequest request) throws SQLException {
		return dao.get_del(pk,table,request);
	}

	public ArrayList<String[]> getDyjhList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO �Զ����ɷ������
		return dao.getDyjhList(myModel,tableName);
	}

	public List<HashMap<String, String>> getDyjhTopTr(String tableName) {
		// TODO �Զ����ɷ������
		return dao.getDyjhTopTr(tableName);
	}

	public HashMap<String, String> getDyjhOne(String pk) {
		// TODO �Զ����ɷ������
		return dao.getDyjhOne(pk);
	}

	public boolean dyjhUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.dyjhUpdate(pk,myModel,request);
	}

	public boolean dyjhDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.dyjhDelete(pk,request);
	}

	public ArrayList<String[]> getXgxxList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO �Զ����ɷ������
		return dao.getXgxxList(myModel,tableName);
	}

	public List<HashMap<String, String>> getXgxxTopTr(String tableName) {
		// TODO �Զ����ɷ������
		return dao.getXgxxTopTr(tableName);
	}

	public HashMap<String, String> getXgxxOne(String pk, HttpServletRequest request) {
		// TODO �Զ����ɷ������
		return dao.getXgxxOne(pk,request);
	}

	public boolean xgxxUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		return dao.xgxxUpdate(pk, myModel, request);
	}

	public boolean xgxxDelete(String pk, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.xgxxDelete(pk,request);
	}

	public List<HashMap<String, String>> getXgxxlbList() {
		// TODO �Զ����ɷ������
		return dao.getXgxxlbList();
	}

	public ArrayList<String[]> getXgxxTjList(XxwhModel myModel) {
		// TODO �Զ����ɷ������
		return dao.getXgxxTjList(myModel);
	}
}
