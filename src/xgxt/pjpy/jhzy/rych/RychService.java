/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-10-10 ����09:31:14</p>
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
	 * ��ȡѧ�������Ϣ
	 */
	public HashMap<String,String> serv_getStuInfo(String xh){
		String querry = " where xh='"+xh+"' ";
		String[] colList= new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","sfzh","syd","csrq","mzmc","zzmmmc","zw","jtdz"};
		return RychDAO.dao_getInfo("view_xsxxb", colList,querry);
	}
	/**
	 * ��ȡʡ�������ҵ�������ƺ������Ϣ
	 */
	public HashMap<String,String> serv_getSjyxbysInfo(String xh,String xn){
		String querry = " where xh||xn='"+xh+xn+"' ";
		return RychDAO.dao_getInfo("JHZY_SJYXBYSB", null,querry);
	}
	/**
	 * ��ȡʡ�������ҵ�������ƺ������Ϣ()
	 */
	public HashMap<String,String> serv_getSjyxbysInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return RychDAO.dao_getInfo("JHZY_SJYXBYSB", null,querry);
	}
	/**
	 * ʡ�������ҵ�������ƺ����뱣��
	 * @throws Exception 
	 */
	public boolean serv_sjyxbysSqSave(sjyxbysRychModel model) throws Exception{
		RychDAO dao = new RychDAO();
		return dao.sjyxbysSqSave(model);
	}
	/**
	 * ��ȡ����ѧ�������ƺ������Ϣ
	 */
	public HashMap<String,String> serv_getShsInfo(String xh,String xn){
		String querry = " where xh||xn='"+xh+xn+"' ";
		return RychDAO.dao_getInfo("JHZY_SHSB", null,querry);
	}
	/**
	 * ��ȡ����ѧ�������ƺ������Ϣ
	 */
	public HashMap<String,String> serv_getShsInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return RychDAO.dao_getInfo("JHZY_SHSB", null,querry);
	}
	/**
	 * ʡ�������ҵ�������ƺű�ͷ
	 */
	public List<HashMap<String, String>> getSjyxbysSHTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","ѧ��","ѧ��","����","Ժϵ","�༶","����Ա���","Ժϵ���","ѧУ���"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * ʡ�������ҵ����˲�ѯ
	 */
	public  ArrayList<String[]> serv_getSjyxbysList(String userType,String userName,sjyxbysRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		RychDAO dao = new RychDAO();
		return dao.getSjyxbysList(userType, userName, model);
	}
	/**
	 * �����ƺ��������
	 */
	public boolean serv_rychCk(String userType,String check,String xmk,String pkVStr) throws SQLException{
		RychDAO dao = new RychDAO();
		return dao.dao_rychCk(userType, check, xmk, pkVStr);
	}
	/**
	 * ʡ�������ҵ����ѯ
	 */
	public  ArrayList<String[]> serv_getSjyxbysCxList(String userType,String userName,sjyxbysRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		RychDAO dao = new RychDAO();
		return dao.getSjyxbysCxList(userType, userName, model);
	}
	/**
	 * �����ƺ�����ɾ��
	 */
	public boolean serv_rychDel(String userType,String xmk,String pkVStr) throws SQLException{
		RychDAO dao = new RychDAO();
		return dao.dao_rychDel(userType,xmk, pkVStr);
	}
	/**
	 * ��ȡ�����������Ϣ
	 */
	public List<HashMap<String, String>>  serv_getChkList(){
		RychDAO dao = new RychDAO();
		return dao.dao_getChkList();
	}
	/**
	 * �����ƺ����
	 * @throws Exception 
	 */
	public boolean serv_rychChk(String userType,String pkValue,String xmk,String fdyshyj,String xyshyj,String xxshyj,String yesNo) throws Exception{
		RychDAO dao = new RychDAO();
		return dao.dao_rychChk(userType, pkValue, xmk, fdyshyj, xyshyj, xxshyj, yesNo);
	}
	/**
	 * ����ѧ��ѧ����ѯ
	 */
	public  ArrayList<String[]> serv_getShxsList_stu(String xh) {
		RychDAO dao = new RychDAO();
		return dao.getShxsList_stu(xh);
	}
	/**
	 * ��������ѧ��ѧ����ѯ
	 */
	public  ArrayList<String[]> serv_getDxyxbysList_stu(String xh) {
		RychDAO dao = new RychDAO();
		return dao.getDxyxbysList_stu(xh);
	}
	
	/**
	 * ʡ�������ҵ��ѧ����ѯ
	 */
	public  ArrayList<String[]> serv_getSjyxbysList_stu(String xh){
		RychDAO dao = new RychDAO();
		return dao.getSjyxbysList_stu(xh);
	}
	/**
	 * У�������ҵ��ѧ����ѯ
	 */
	public  ArrayList<String[]> serv_getXjyxbysList_stu(String xh){
		RychDAO dao = new RychDAO();
		return dao.getXjyxbysList_stu(xh);
	}
	/**
	 * ��ȡУ�������ҵ�������ƺ������Ϣ
	 */
	public HashMap<String,String> serv_getXjyxbysInfo(String xh,String xn){
		String querry = " where xh||xn='"+xh+xn+"' ";
		return RychDAO.dao_getInfo("JHZY_XJYXBYSB", null,querry);
	}
	/**
	 * ��ȡУ�������ҵ�������ƺ������Ϣ(���ؼ�ֵ)
	 */
	public HashMap<String,String> serv_getXjyxbysInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return RychDAO.dao_getInfo("JHZY_XJYXBYSB", null,querry);
	}
	/**
	 * У�������ҵ�������ƺ����뱣��
	 * @throws Exception 
	 */
	public boolean serv_xjyxbysSqSave(XjyxbysRychModel model) throws Exception{
		RychDAO dao = new RychDAO();
		return dao.xjyxbysSqSave(model);
	}
	/**
	 * У�������ҵ�������ƺű�ͷ
	 */
	public List<HashMap<String, String>> getXjyxbysSHTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","ѧ��","ѧ��","����","Ժϵ","�༶","����Ա���","Ժϵ���","ѧУ���"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * У�������ҵ����ѯ
	 */
	public  ArrayList<String[]> serv_getXjyxbysCxList(String userType,String userName,XjyxbysRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		RychDAO dao = new RychDAO();
		return dao.getXjyxbysCxList(userType, userName, model);
	}	
	/**
	 * У�������ҵ����˲�ѯ
	 */
	public  ArrayList<String[]> serv_getXjyxbysList(String userType,String userName,XjyxbysRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		RychDAO dao = new RychDAO();
		return dao.getXjyxbysList(userType, userName, model);
	}
	/**
	 * ��ȡѧ���ɼ�������ݼ�
	 */
	public List<HashMap<String,String>> serv_getCjList(String xh){
		RychDAO dao = new RychDAO();
		return dao.dao_getCjList(xh);
	}
	/**
	 * �����������ƺ����뱣��
	 * @throws Exception 
	 */
	public boolean serv_shsSqSave(ShsRychModel model) throws Exception{
		RychDAO dao = new RychDAO();
		return dao.shsSqSave(model);
	}
	/**
	 * �����������ƺű�ͷ
	 */
	public List<HashMap<String, String>> getShsTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","ѧ��","ѧ��","����","Ժϵ","�༶","����Ա���","Ժϵ���","ѧУ���"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * ��������ѯ
	 */
	public  ArrayList<String[]> serv_getShsCxList(String userType,String userName, ShsRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException  {
		RychDAO dao = new RychDAO();
		return dao.getShsCxList(userType, userName, model);
	}
	/**
	 * ����ѧ����˲�ѯ
	 */
	public  ArrayList<String[]> serv_getShxsList(String userType,String userName, ShsRychModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		RychDAO dao = new RychDAO();
		return dao.getShxsList(userType, userName, model);
	}
	/**
	 * ��������ѧ�������ƺ������Ϣ
	 */
	public HashMap<String,String> serv_getDxyxsInfo(String xh,String xn){
		String querry = " where xh||xn='"+xh+xn+"' ";
		return RychDAO.dao_getInfo("jhzy_dxyxxsb", null,querry);
	}
	/**
	 * ��������ѧ�������ƺ������Ϣ
	 */
	public HashMap<String,String> serv_getDxyxsInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return RychDAO.dao_getInfo("jhzy_dxyxxsb", null,querry);
	}
	/**
	 *  ��������ѧ�������ƺ����뱣��
	 * @throws Exception 
	 */
	public boolean serv_dxyxxsSqSave(DxyxxsModel model) throws Exception{
		RychDAO dao = new RychDAO();
		return dao.dxyxxsSqSave(model);
	}
	/**
	 * ��������ѧ�������ƺű�ͷ
	 */
	public List<HashMap<String, String>> getDxyxxsTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","ѧ��","ѧ��","����","Ժϵ","�༶","����Ա���","Ժϵ���","ѧУ���"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * ��������ѧ����Ϣ��ѯ
	 */
	public  ArrayList<String[]> serv_getDxyxxsCxList(String userType,String userName, DxyxxsModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException  {
		RychDAO dao = new RychDAO();
		return dao.getDxyxxsCxList(userType, userName, model);
	}
	/**
	 * ��������ѧ����Ϣ��˲�ѯ
	 */
	public  ArrayList<String[]> serv_getDxyxxsList(String userType,String userName, DxyxxsModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		RychDAO dao = new RychDAO();
		return dao.getDxyxxsList(userType, userName, model);
	}
	/**
	 * ����ѧ���б�
	 */
	public List<HashMap<String,String>>serv_getShJxjList(String xh){
		RychDAO dao = new RychDAO();
		return dao.getShJxjList(xh);
	}
	/**
	 * ����ȼ��б�
	 */
	public List<HashMap<String,String>>serv_getShDjList(String xh){
		RychDAO dao = new RychDAO();
		return dao.getShDjList(xh);
	}

		
}
