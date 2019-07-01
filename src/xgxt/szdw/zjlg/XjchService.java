package xgxt.szdw.zjlg;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.pjpy.jhzy.JhzyPjpyDAO;
import xgxt.szdw.dao.common.CommonDAO;
import xgxt.utils.CommonQueryDAO;

public class XjchService {
	
	XjchDAO myDAO = new XjchDAO();

	public boolean xjchMffdySave(XjchModel model, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return myDAO.xjchMffdySave(model,request);
	}

	public HashMap<String,String> serv_getXsXjchInfo(String pk) {
		String querry = " where xh||xn||xq||xjchdm='"+pk+"' ";
		return CommonQueryDAO.dao_getInfo("view_xsxjchb", null,querry);
	}

	public  List<HashMap<String, String>> getCjList(String xh, String xn) {
		// TODO �Զ����ɷ������
		JhzyPjpyDAO unitDAO = new JhzyPjpyDAO();
		return unitDAO.getFzPmList(xh, xn,"");
	}

	public ArrayList<String[]> getXjchshList(XjchModel model, String xjchdm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return myDAO.getXjchshList(model,xjchdm); 
	}

	/**
	 * �Ƚ��ƺ���˱�ͷ
	 * @param xjchdm 
	 */
	public List<HashMap<String, String>> getXjchshTopTr(String xjchdm) {
		DAO dao = DAO.getInstance();
		String[] colList = new String[]{"xn||zgh","zgh","xm","bmmc","zwmc","lxdh","shzt"};
		String[] colListCN = new String[]{"���I","ְ����","����","��������","����ְ��","��ϵ�绰","���״̬"};
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	
	public HashMap<String,String> serv_getMffdyInfo(String pkValue){
		String querry = " where xn||zgh='"+pkValue+"' ";
		return CommonQueryDAO.dao_getInfo("view_mffdysqb", null,querry);
	}
	
	public HashMap<String,String> serv_getYxbzrInfo(String pkValue){
		String querry = " where xn||zgh='"+pkValue+"' ";
		return CommonQueryDAO.dao_getInfo("view_yxbzrb", null,querry);
	}
//	/**
//	 * ����ȼ��б�
//	 */
//	public List<HashMap<String,String>>serv_getShDjList(String xh){
//		return myDAO.getShDjList(xh);
//	}
//	
	/**
	 * ��ȡ�����������Ϣ
	 */
	public List<HashMap<String, String>>  serv_getChkList(){
		return myDAO.dao_getChkList();
	}
//	
	/**
	 * �Ƚ��ƺ��������
	 */
	public boolean serv_XjchCk(String check,String xjchdm,String pkVStr) throws SQLException{
		return myDAO.dao_XjchCk(check, xjchdm, pkVStr);
	}
	
	/**
	 * �Ƚ��ƺ��������
	 */
	public boolean serv_XxfkCk(String check,String xjchdm,String pkVStr) throws SQLException{
		return myDAO.dao_XxfkCk(check, xjchdm, pkVStr);
	}
//	
//	/**
//	 * �Ƚ��ƺŲ�ѯ�б�
//	 * @param xjchdm 
//	 */
//	public  ArrayList<String[]> serv_getXjchCxList(String userType,String userName,XjchModel model, String xjchdm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
//		return myDAO.getXjchCxList(userType, userName, model,xjchdm);
//	}
//
//	public boolean serv_xjchDel(String userType, String xjchdm, String pkVStr) throws SQLException {
//		return myDAO.dao_xjchDel(userType,xjchdm, pkVStr);
//	}
	
	public HashMap<String,String> serv_getFdyInfo(String zgh) {
		String querry = " where zgh='"+zgh+"' ";
		return CommonQueryDAO.dao_getInfo("view_fdyxx", null,querry);
	}

	public boolean xjchYxbzrSave(XjchModel model, HttpServletRequest request) throws Exception {
//		 TODO �Զ����ɷ������
		return myDAO.xjchYxbzrSave(model,request);
	}

	public List<HashMap<String, String>> getBmlist() {
		// TODO �Զ����ɷ������
		DAO dao = DAO.getInstance();
		return dao.getBmListAll();
	}

	public List<HashMap<String, String>> getFdylist(String bmdm) {
		// TODO �Զ����ɷ������
		CommonDAO commonDAO = new CommonDAO();
		return commonDAO.getFdyList(bmdm);
	}

	public boolean serv_XjchChk(String pkValue, String xjchdm, XjchModel myModel,  HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		myModel.setPk(pkValue);
		if(xjchdm.equalsIgnoreCase("mffdy")){
			return xjchMffdySave(myModel,request);
		}else{
			return xjchYxbzrSave(myModel,request);
		}
		
	}
	
	public boolean xjchPxxxfkSave(XjchModel model, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return myDAO.xjchPxxxfkSave(model,request);
	}
	
	public boolean xjchGzxxfkSave(XjchModel model, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return myDAO.xjchGzxxfkSave(model,request);
	}
	
	public HashMap<String,String> serv_getPxxxfkInfo(String pkValue){
		String querry = " where pk='"+pkValue+"' ";
		return CommonQueryDAO.dao_getInfo("view_fdypxxm", null,querry);
	}
	
	public HashMap<String,String> serv_getGzxxfkInfo(String pkValue){
		String querry = " where pk='"+pkValue+"' ";
		return CommonQueryDAO.dao_getInfo("view_fdygzxx", new String[]{"xxzt","jttr","jyyj","bz"},querry);
	}
	
	public ArrayList<String[]> getXxfkshList(XjchModel model, String xjchdm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return myDAO.getXxfkshList(model,xjchdm); 
	}

	/**
	 * ��Ϣ������˱�ͷ
	 * @param xjchdm 
	 */
	public List<HashMap<String, String>> getXxfkshTopTr(String xjchdm) {
		DAO dao = DAO.getInstance();
		String tableName = "";
		String[] colList=null;
		if(xjchdm.equalsIgnoreCase("pxxxfk")){
			tableName = "view_fdypxxm";
			colList = new String[]{"pk","zgh","xm","bmmc","zwmc","lxdh","pxxm","kssj","jssj","shzt"};
		}else if(xjchdm.equalsIgnoreCase("gzxxfk")){
			tableName = "view_fdygzxx";
			colList = new String[]{"pk","zgh","xm","bmmc","zwmc","lxdh","xxzt","shzt"};
		}
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	
	/**
	 * ��Ϣ������˱�ͷ
	 * @param xjchdm 
	 */
	public boolean serv_XxfkChk(String pkValue, String xjchdm, XjchModel myModel,  HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		myModel.setPk(pkValue);
		if(xjchdm.equalsIgnoreCase("pxxxfk")){
			return xjchPxxxfkSave(myModel,request);
		}else{
			return xjchGzxxfkSave(myModel,request);
		}
		
	}

}
