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
		// TODO �Զ����ɷ������
		return myDAO.getJxjXx(jxjdm);
	}

	public boolean JxjCommonSave(JxjsqshModel model, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return myDAO.JxjCommonSave(model,request);
	}

	/**
	 * ��ȡѧ�������Ϣ
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
		// TODO �Զ����ɷ������
		JhzyPjpyDAO unitDAO = new JhzyPjpyDAO();
		return unitDAO.getFzPmList(xh, xn,"");
	}

	public ArrayList<String[]> getJxjshList(String userType, String userName, JxjsqshModel model, String jxjdm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return myDAO.getJxjshList(userType, userName, model,jxjdm); 
	}

	/**
	 * ��ѧ����˱�ͷ
	 */
	public List<HashMap<String, String>> getJxjshTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 
		colListCN = new String[]{"pk","ѧ��","ѧ��","����","Ժϵ","�༶","����Ա���","Ժϵ���","ѧУ���"}; 		
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
	 * ����ȼ��б�
	 */
	public List<HashMap<String,String>>serv_getShDjList(String xh){
		RychDAO dao = new RychDAO();
		return dao.getShDjList(xh);
	}
	
	/**
	 * ����ѧ���б�
	 */
	public List<HashMap<String,String>>serv_getShJxjList(String xh){
		RychDAO dao = new RychDAO();
		return dao.getShJxjList(xh);
	}
	
	/**
	 * ��ȡѧ���ɼ�������ݼ�
	 */
	public List<HashMap<String,String>> serv_getCjList(String xh){
		RychDAO dao = new RychDAO();
		return dao.dao_getCjList(xh);
	}
	
	/**
	 * ��ȡ�����������Ϣ
	 */
	public List<HashMap<String, String>>  serv_getChkList(){
		return myDAO.dao_getChkList();
	}
	
	/**
	 * ��ѧ���������
	 */
	public boolean serv_JxjsqshCk(String userType,String check,String jxjdm,String pkVStr) throws SQLException{
		return myDAO.dao_JxjsqshCk(userType, check, jxjdm, pkVStr);
	}
	
	/**
	 * ��ѧ���ѯ�б�
	 * @param jxjdm 
	 */
	public  ArrayList<String[]> serv_getJxjCxList(String userType,String userName,JxjsqshModel model, String jxjdm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return myDAO.getJxjCxList(userType, userName, model,jxjdm);
	}

	public boolean serv_jxjDel(String userType, String jxjdm, String pkVStr) throws SQLException {
		return myDAO.dao_jxjDel(userType,jxjdm, pkVStr);
	}

}
