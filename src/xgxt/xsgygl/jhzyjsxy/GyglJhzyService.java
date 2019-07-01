/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-10-8 ����01:34:04</p>
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
	 * ��ȡѧ�������Ϣ
	 */
	public HashMap<String,String> serv_getStuInfo(String xh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		String querry = " where xh='"+xh+"' ";
		String[] colList= new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","sfzh","syd","csrq","mzmc","zzmmmc","zw","jtdz"};
		return dao.dao_getInfo("view_xsxxb", colList,querry);
	}
	/**
	 * ��ȡ¥��
	 */
	public List<HashMap<String,String>> getSsLdList(){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getSsLdList();		
	}
	/**
	 * ��ȡ¥��
	 */
	public List<HashMap<String,String>> getSsLcList(String lddm){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getSsLcList(lddm);		
	}
	/**
	 * ��ȡ����
	 */
	public List<HashMap<String,String>> getSsmList(String lddm,String lch,String sszt,String fdy,String xn,String xq){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getSsmList(lddm, lch,sszt,fdy,xn,xq);	
	}
	/**
	 * ��Ԣ����Ա��Ϣ����
	 */
	public boolean serv_GyfdyIntoSave(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		 return dao.dao_GyfdyIntoSave(model);
	 }
	/**
	 * ��ȡ��Ԣ����Ա�û������Ϣ
	 */
	public List<HashMap<String,String>> getUserInfoList(){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getUserInfoList();	
	}
	/**
	 * ��ȡ������Ԣ����Ա�û������Ϣ
	 */
	public List<HashMap<String,String>> getXzGyfdyInfoList(){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getXzGyfdyInfoList();	
	}
	/**
	 * ��Ԣ����Ա�û���Ϣ��ѯ
	 * @throws Exception 
	 */
	public List<String[]> ser_QueryGyfdyInfo(GyglGyfdyModel model,GyglJhzyForm myForm) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_QueryGyfdyInfo(model,myForm);	
	}
	/**
	 * ������Ԣ����Ա�û���Ϣ��ѯ
	 * @throws Exception 
	 */
	public List<String[]> ser_QueryXzGyfdyInfo(GyglGyfdyModel model,GyglJhzyForm myForm) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_QueryXzGyfdyInfo(model,myForm);	
	}
	/**
	 * ��Ԣ����Ա�û���Ϣ
	 * @throws Exception 
	 */
	public HashMap<String,String> ser_GyfdyInfo(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_GyfdyInfo(model);	
	}
	/**
	 * ������Ԣ¥�û���Ϣ
	 * @throws Exception 
	 */
	public HashMap<String,String> ser_wmgylInfo(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_wmgylInfo(model);	
	}
	/**
	 * ������Ԣ¥�û���Ϣ
	 * @throws Exception 
	 */
	public HashMap<String,String> ser_wmgylInfosh(String id) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_wmgylInfosh(id);	
	}
	/**
	 * ��Ԣ����Ա�û���Ϣɾ��
	 * @throws Exception 
	 */
	public boolean ser_DelGyfdyInfo(String yhm,String xn,String xq,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_DelGyfdyInfo(yhm,xn,xq,request);	
	}
	/**
	 * @author luo
	 * @describe ��ñ�ͷ
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * ��Ԣ����Ա��Ϣ����
	 */
	public boolean serv_selectIntoFdy(String dqxn,String dqxq,String xn,String xq,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_selectIntoFdy(dqxn, dqxq, xn, xq,request);
	 }
	/**
	 * ������Ԣ¥����
	 */
	public boolean serv_saveWmgyl(GyglGyfdyModel model,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_saveWmgyl(model,request);
	 }
	/**
	 * ������Ԣ¥��ѯ��ͷ
	 */
	public List<HashMap<String, String>> getWmgyCxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","nj","lzm","qss","xss","xydm","wshgl","wsyxl","xjqsl","xxsh"}; 
		colListCN = new String[]{"pk","ѧ��","�꼶","¥��","������","ѧ����",Base.YXPZXY_KEY,"�����ϸ���","����������","�Ǽ�������","ѧУ���"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * ������Ԣ¥����
	 */
	public boolean serv_isExists(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_isExists(model);
	 }
	/**
	 *������Ԣ¥��ѯ
	 */
	public  ArrayList<String[]> serv_getWmgylCxList(String yesNo,String userType,String userName, GyglGyfdyModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getWmgylCxList(yesNo, userType, userName, model);
	}	
	/**
	 *�Ƿ�¥��
	 */
	public  HashMap<String, String> serv_isLz(String userName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_isLz(userName);
	}
	/**
	 *ͨ�ò�ѯ��ѯ
	 */
	public  ArrayList<String[]> commonQuery(String yesNo,String userType,String userName, GyglGyfdyModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getWmgylCxList(yesNo, userType, userName, model);
	}
	/**
	 * ������Ԣ¥�޸�
	 */
	public boolean serv_WmgylUpdate(GyglGyfdyModel model,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		 return dao.dao_WmgylUpdate(model,request);
	 }
	/**
	 * ������Ԣ¥�޸�
	 */
	public String serv_WmgylDel(String pkStr,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		 return dao.dao_WmgylDel(pkStr,request);
	 }
	/**
	 * ������Ԣ¥���
	 */
	public String serv_WmgylSh(String pkStr,String str,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		 return dao.dao_WmgylSh(pkStr,str,request);
	 }
	/**
	 * ������Ԣ¥�������
	 */
	public Boolean serv_WmgyldgSh(String pkStr,String str,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		 return dao.dao_WmgyldgSh(pkStr,str,request);
	 }
	 /**
	 * ��ȡ����Ա��ס��Ϣ
	 */
	public  HashMap<String,String> getFdyRzXx(String zgh){
		String querry = " where zgh='"+zgh+"' ";
		return CommonQueryDAO.dao_getInfo("view_fdyrzxx", null, querry);	
	}
	 /**
     * ����Ա��ס��Ϣ���� 
     */
	public boolean serv_fdyRzXxSave(FdyRzxxModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_fdyRzXxSave(model);
	}
	/**
	 *����Ա��ס��Ϣ��ѯ��ͷ
	 */
	public List<HashMap<String, String>> getFdyRzXxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"zgh","xm","xymc","ldmc","qsh","lxdh"}; 
		colListCN = new String[]{"pk","����","Ժϵ","¥��","�����","��ϵ�绰"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	 /**
     * ����Ա��ס��Ϣ��ѯ
     */
	public  ArrayList<String[]> serv_getFdyRzxxList(FdyRzxxModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getFdyRzxxList(model);
	}
	/**
	 * ����Ա��ס������ɾ��
	 */
	public boolean serv_fdyRzxxDel(String pkVStr) throws SQLException{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_fdyRzxxDel(pkVStr);
	}
	/**
	 * ��Ԣ������Ϣ��ѯ��ͷ
	 */
	public List<HashMap<String, String>> getGydjXxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"guid","xh","xm","xymc","ldmc","qsh","cjhdsj","hdzt"}; 
		colListCN = new String[]{"pk","ѧ��","����","Ժϵ","¥��","�����","�μӻʱ��","�����"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
     * ��Ԣ������Ϣ��ѯ
     */
	public ArrayList<String[]> serv_getGydjxxList(GydjglxxbModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getGydjxxList(model);
	}
	/**
     * ��Ԣ������Ϣ���� 
     */
	public String[] serv_gydjXxSave(GydjglxxbModel model,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_gydjXxSave(model,request);
	}
	/**
	 * ��ȡ��Ԣ������Ϣ��Ϣ
	 */
	public  HashMap<String,String> getGydjXx(String guid){
		String querry = " where guid='"+guid+"' ";
		return CommonQueryDAO.dao_getInfo("view_gydjglxxb", null, querry);	
	}
	/**
	 * ��Ԣ������Ϣ����ɾ��
	 */
	public boolean serv_gydjDel(String pkVStr) throws SQLException{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_gydjDel(pkVStr);
	}
	
	/**
	 * ��ȡ���ҳ������Ϣ
	 * @param xh ѧ��
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
	 * �жϸ�ѧ���Ƿ����ύ�Ǽ�����������Ƿ������
	 */
	public String isChecking_ser(String xh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.isChecking_db(xh);
	}
	/**
	 * ����Ǽ��������
	 */
	public HashMap<String,String> getXszsqk_ser(String xh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getXszsqk_db(xh);
	}
	/**
	 * ����Ǽ��������
	 */
	public HashMap<String,String> getXjqsqk_ser(String lddm,String cs,String qsh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getXjqsqk_db(lddm, cs, qsh);
	}
	/**
	 * �����Ǽ�����
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
	 * �����������
	 */
	public String getSsrs_ser(String ssbh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getSsrs_db(ssbh);
	}
	
	/**
	 * �������༶
	 */
	public String getSsbj_ser(String ssbh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getSsbj_db(ssbh);
	}
	
	/**
	 * �Ǽ����Ҳ�ѯ
	 */
	public List<HashMap<String,String>>  queryXjqs_ser(YxlcqszModel model,String userType,String userName){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.queryXjqs_db(model, userType,userName);
	}
	
	/**
	 * �Ǽ������������
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
	 * �Ǽ����ҵ������ҳ��
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
	 * �Ǽ����ҵ������
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
	 * �Ǽ����ҽ����ѯ
	 */
	public List<HashMap<String,String>>  queryXjqsShjg_ser(YxlcqszModel model,String userType,String userName){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.queryXjqsShjg_db(model, userType,userName);
	}
	/**
	 * ���������Ϣ
	 */
	public HashMap<String,String>  getSsxx_ser(String ssbh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getSsxx_db(ssbh);
	}
	/**
	 * ɾ���Ǽ�����
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
     * ���ҳ���Ϣ���� 
     */
	public boolean serv_QszXxSave(QszXxModel model,String pkValue) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_QszXxSave(model,pkValue);
	}
	/**
	 * �����ҳ���Ϣ��ѯ��ͷ
	 */
	public List<HashMap<String, String>> getQszXxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;		
		colListCN = new String[]{"pk","ѧ��","����","�Ա�","Ժϵ","�༶","¥��","�����","��ϵ�绰","�Ƿ���ְ"}; 
		colList = new String[]{"pk","qsz","xm","xb","xymc","bjmc","ldmc","qsh","lxdh","sfzz"}; 
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	 /**
     * ���ҳ���Ϣ��ѯ
     */
	public  ArrayList<String[]> getQszXxCxList(QszXxModel model,String userType,String userName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getQszXxCxList(model,userType,userName);
	}
	/**
	 * ���ҳ���Ϣ����ɾ��
	 */
	public boolean serv_qszDel(String pkVStr) throws SQLException{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_qszDel(pkVStr);
	}
	/**
     * ������������ѯ
     */
	public ArrayList<List> serv_qswsjcQuery(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_qswsjcQuery(model);
	}
	/**
     * ������������ѯ
     */
	public List<String[]> serv_qswsjcjgQuery(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_qswsjcjgQuery(model);
	}
	/**
	 * ����������� ��ͷ
	 */
	public List<HashMap<String, String>> getQswsjcTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"ldmc","cs","ssbh","fdy","xymc","wsdj","dm","bz"}; 
		colListCN = new String[]{"¥������","¥��","���ұ��","����Ա","����"+Base.YXPZXY_KEY,"�����ȼ�","����","��ע"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * ������������ѯ��ͷ
	 */
	public List<HashMap<String, String>> getQswsjccxTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"xn","xq","lddm","cs","qsh","xymc","xm","wsjc"}; 
		colListCN = new String[]{"ѧ��","ѧ��","¥��","¥��","���Һ�",Base.YXPZXY_KEY+"����","����Ա����","����������"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * �ԹܻᲿ��List
	 */
	public List<HashMap<String, String>> GetZghbmList() {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.GetZghbmList();
	}
	/**
	 * �ԹܻṤ����Ա��ѯ��ͷ
	 */
	public List<HashMap<String, String>> getZghryTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"xh","xm","xymc","bmmc","zw","lxdh","ldmc","cs","qsh"}; 
		colListCN = new String[]{"pk","����",Base.YXPZXY_KEY,"����","ְ��","��ϵ�绰","¥��","¥��","���Һ�"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
     * �ԹܻṤ����Ա��Ϣ��ѯ
     */
	public ArrayList<String[]> serv_getZghryList(ZghryModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getZghryList(model);
	}
	/**
	 * ��ȡ�ԹܻṤ����Ա��Ϣ
	 */
	public  HashMap<String,String> getZghryXx(String xh){
		String querry = " where xh='"+xh+"' ";
		return CommonQueryDAO.dao_getInfo("view_zghryxxb", null, querry);	
	}
	/**
     * �ԹܻṤ����Ա���� 
     */
	public boolean serv_zghrySave(ZghryModel model,HttpServletRequest request) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_zghrySave(model,request);
	}
	/**
	 * �ԹܻṤ����Ա����ɾ��
	 */
	public boolean serv_zghryDel(String pkVStr) throws SQLException{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_zghryDel(pkVStr);
	}
	/**
	 * ����������鱣��
	 * @throws SQLException 
	 */
	public boolean serv_qswsjcSave(GyglGyfdyModel model) throws SQLException{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_qswsjcSave(model);
	}
	/**
	 * ��ȡ��������������id
	 */
	public String serv_getqswsjcChecked(GyglGyfdyModel model) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getqswsjcChecked(model);
	}
	/**
	 * ��ȡ��ع�Ԣ����Ա��������Ӧ�۷�ֵ��Ϣ
	 */
	public HashMap<String, String> serv_getWsjcXx(String userName,String xn,String xq,String yf){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getWsjcXx(userName, xn, xq, yf);
	}
	/**
	 * ����Ϣ�Ƽ���ѯ��ͷ
	 */
	public List<HashMap<String, String>> getZzxdjcTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"ssbhT","ldmc","cs","qsh","fgfdy","xdqk"}; 
		colListCN = new String[]{"������","¥��","����","���Һ�","�ֹܸ���Ա","Ϩ�����"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
     * ����Ϣ�Ƽ����Ϣ��ѯ
     */
	public ArrayList<String[]> serv_getZzxdjcList(ZzxdglModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getZzxdjcList(model);
	}
	/**
     * ����Ϣ�Ƽ�鱣��
     */
	public void ser_saveZzxdjc(ZzxdglModel model) {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		dao.saveZzxdjc(model);
	}
	/**
	 * ����Ϣ�Ƽ���ѯ��ͷ
	 */
	public List<HashMap<String, String>> getZzxdjcQueryTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"ldmc","cs","qsh","rq"}; 
		colListCN = new String[]{"¥��","����","���Һ�","δϨ������"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
     * ����Ϣ�Ƽ����Ϣ��ѯ
     */
	public ArrayList<String[]> serv_getZzxdjcQueryList(ZzxdglModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getZzxdjcQueryList(model);
	}
	/**
     * ��Ԣ����Ա���˱��� 
     */
	public boolean serv_gyfdyCheckSave(GyfdyKhModel model,String pkValue) throws Exception{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		 return dao.dao_gyfdyCheckSave(model, pkValue);
	}
	/**
	 * ��Ԣ����Ա������Ϣ
	 */
	public  HashMap<String,String> getGyfdyKhXx(String pkValue){
		String querry = " where xn||xq||yf||zgh='"+pkValue+"' ";
		return CommonQueryDAO.dao_getInfo("view_gyfdykhxx", null, querry);	
	}
	/**
	 * ����Ϣ�Ƽ���ѯ��ͷ
	 */
	public List<HashMap<String, String>> getGyfdyKhTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xqmc","yf","zgh","xm","zpf"}; 
		colListCN = new String[]{"pk","ѧ��","ѧ��","�·�","����","����","������"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	 /**
     * ��Ԣ����Ա������Ϣ��ѯ
     */
	public  ArrayList<String[]> getgyfdyCheckList(GyfdyKhModel model,String userType,String userName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getgyfdyCheckList(model, userType, userName);
	}
	/**
	 *��Ԣ����Ա��������ɾ��
	 */
	public boolean serv_gyfdyCheckDel(String pkVStr) throws SQLException{
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.dao_gyfdyCheckDel(pkVStr);
	}
	/**
	 * �����������Ϣ
	 * @throws Exception 
	 */
	public HashMap<String,String>serv_wsjcDbInfo(String xn,String xq,String yf,String lddm,String lc,String qsh){
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.wsjcDbInfo(xn, xq, yf, lddm, lc, qsh);
	}
	/**
	 * ���¥�������Ϣ
	 */
	public HashMap<String,String> serv_getLzInfo(String xh){
		String querry = " where lz='"+xh+"' and sfzz='��' ";
		return CommonQueryDAO.dao_getInfo("view_lzxx", null,querry);
	}
	/**
	 * �Ǽ�������������ѧ����ѯ
	 */
	public  ArrayList<String[]> serv_getXjwmqsList_stu(String xh) {
		GyglJhzyDAO dao = new GyglJhzyDAO();
		return dao.getXjwmqsList_stu(xh);
	}
	/**
	 * ����Ϣ�Ƽ��ͳ�Ʊ�ͷ
	 */
	public List<HashMap<String, String>> getZzxdjcTjTopTr(String tjlx) {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		if ("1".equalsIgnoreCase(tjlx)) {
			colList = new String[] { "pm", "dw", "qszs", "xds", "xdl" };
			colListCN = new String[] { "����", Base.YXPZXY_KEY, "�����������", "Ϩ��������", "Ϩ����%" };
		} else if ("2".equalsIgnoreCase(tjlx)) {
			colList = new String[] { "pm", "dw", "qszs", "xds", "xdl" };
			colListCN = new String[] { "����", "¥��", "�����������", "Ϩ��������", "Ϩ����%" };
		} else if ("3".equalsIgnoreCase(tjlx)) {
			colList = new String[] { "pm", "dw", "qszs", "xds", "xdl" };
			colListCN = new String[] { "����", "����Ա", "�����������", "Ϩ��������", "Ϩ����%" };
		}
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
     * ����Ϣ�Ƽ��ͳ��
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
