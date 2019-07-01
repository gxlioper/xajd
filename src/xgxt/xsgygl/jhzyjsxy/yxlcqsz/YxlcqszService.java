/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-10-15 ����02:46:01</p>
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
	 * �ټ����ҳ����뱣��
	 * @throws Exception 
	 */
	public boolean serv_bjqszSqSave(YxlcqszModel model) throws Exception{
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.bjqszSqSave(model);
	}
	/**
	 * �ټ����ҳ������Ϣ
	 */
	public HashMap<String,String> serv_getBjqszInfo(String xh,String xn){	
		String querry = " where xh||xn='"+xh+xn+"' ";
		return CommonQueryDAO.dao_getInfo("view_bjqszxx", null,querry);
	}
	/**
	 * �ټ����ҳ������Ϣ
	 */
	public HashMap<String,String> serv_getBjqszInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return CommonQueryDAO.dao_getInfo("view_bjqszxx", null,querry);
	}
	/**
	 *�ټ����ҳ���ѯ
	 */
	public  ArrayList<String[]> serv_getBjqszCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getBjqszCxList(yesNo,userType, userName, model);
	}
	/**
	 * �ټ����ҳ���ѯ��ͷ
	 */
	public List<HashMap<String, String>> getBjqszCxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","ldmc","qsh","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","ѧ��","ѧ��","����","Ժϵ","�༶","¥��","����","����Ա���","Ժϵ���","ѧУ���"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 *�ټ����ҳ���˲�ѯ
	 */
	public  ArrayList<String[]> serv_getBjqszShCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getBjqszShCxList(yesNo,userType, userName, model);
	}
	/**
	 * ��ʮ�ѳ����뱣��
	 * @throws Exception 
	 */
	public boolean serv_esjczSqSave(YxlcqszModel model) throws Exception{
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.esjczSqSave(model);
	}	
	/**
	 * ��ʮ�Ѳ㳤��ѯ��ͷ
	 */
	public List<HashMap<String, String>> getEsjczCxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","ldmc","lc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","ѧ��","ѧ��","����","Ժϵ","�༶","¥��","¥��","����Ա���","Ժϵ���","ѧУ���"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 *��ʮ�Ѳ㳤��ѯ
	 */
	public  ArrayList<String[]> serv_getEsjczCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getEsjczCxList(yesNo, userType, userName, model);
	}	
	/**
	 *��ʮ�Ѳ㳤��˲�ѯ
	 */
	public  ArrayList<String[]> sersv_getEsjczShCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getEsjczShCxList(yesNo, userType, userName, model);
	}	
	/**
	 * ���¥�����뱣��
	 * @throws Exception 
	 */
	public boolean serv_wjlzSqSave(YxlcqszModel model) throws Exception{
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.wjlzSqSave(model);
	}	
	/**
	 * ���¥����ѯ��ͷ
	 */
	public List<HashMap<String, String>> getWjlzCxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","ldmc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","ѧ��","ѧ��","����","Ժϵ","�༶","¥��","����Ա���","Ժϵ���","ѧУ���"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 *���¥����ѯ
	 */
	public  ArrayList<String[]> serv_getWjlzCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getWjlzCxList(yesNo, userType, userName, model);
	}
	/**
	 *���¥����˲�ѯ
	 */
	public  ArrayList<String[]> serv_getWjlzShCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getWjlzShCxList(yesNo, userType, userName, model);
	}
	/**
	 *����¥�����ҳ�����ɾ��
	 */
	public boolean serv_yxlcqszDel(String userType,String xmk,String pkVStr) throws SQLException{
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.dao_yxlcqszDel(userType, xmk, pkVStr);
	}
	/**
	 * ����¥�����ҳ��������
	 */
	public boolean serv_yxlcqszCk(String userType,String check,String xmk,String pkVStr) throws SQLException{
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.dao_yxlcqszCk(userType, check, xmk, pkVStr);
	}
	/**
	 * ����¥�����ҳ��������
	 * @throws Exception 
	 */
	public boolean serv_yxlcqszChk(String userType,String pkValue,String xmk,String yesNo) throws Exception{
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.dao_yxlcqszChk(userType, pkValue, xmk, yesNo);
	}
	/**
	 * ��ʮ�Ѳ㳤�����Ϣ
	 */
	public HashMap<String,String> serv_getEsjczInfo(String xh,String xn){
		String querry = " where xh||xn='"+xh+xn+"' ";
		return CommonQueryDAO.dao_getInfo("view_esjczxx", null,querry);
	}
	/**
	 * ��ʮ�Ѳ㳤�����Ϣ
	 */
	public HashMap<String,String> serv_getEsjczInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return CommonQueryDAO.dao_getInfo("view_esjczxx", null,querry);
	}
	/**
	 * ���¥�������Ϣ
	 */
	public HashMap<String,String> serv_getWjlzInfo(String xh,String xn){
		String querry = " where xh||xn='"+xh+xn+"' ";
		return CommonQueryDAO.dao_getInfo("view_wjlzxx", null,querry);
	}
	/**
	 * ���¥�������Ϣ
	 */
	public HashMap<String,String> serv_getWjlzInfo(String pkValue){
		String querry = " where xh||xn='"+pkValue+"' ";
		return CommonQueryDAO.dao_getInfo("view_wjlzxx", null,querry);
	}
	/**
	 * �ж��Ƿ���¥�����㳤�����ҳ�
	 * @param type��"lz","cz","qsz" 
	 * @param xh
	 * @return
	 */
	public boolean  lcqszPd(String type,String xh){
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.lcqszPd(type, xh);
	}
	/**
	 * �ټ����ҳ�ѧ����ѯ
	 */
	public  ArrayList<String[]> serv_getBjqszList_stu(String xh) {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getBjqszList_stu(xh);
	}
	/**
	 * ��ʮ�Ѳ㳤ѧ��ѧ����ѯ
	 */
	public  ArrayList<String[]> serv_getEsjczList_stu(String xh) {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getEsjczList_stu(xh);
	}
	/**
	 * ���¥��ѧ����ѯ
	 */
	public  ArrayList<String[]> getWjlzList_stu(String xh) {
		YxlcqszDAO dao = new YxlcqszDAO();
		return dao.getWjlzList_stu(xh);
	}
}
