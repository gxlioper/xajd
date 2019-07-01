package xgxt.studentInfo.ynys;

import java.util.HashMap;
import java.util.List;

import xgxt.studentInfo.dao.StuInfoDAO;

public class XsxxYnysService {
	XsxxYnysDAO dao = new XsxxYnysDAO();
	
	public List<String[]> selectXsxx(XsxxYnysForm model){
		return dao.selectXsxx(model);
	}
	
	/**
	 * ��ȡѧ����Ϣ��ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsxxSearchTopTr(){
		String[] colList = dao.getCols();
		String[] colCNList = dao.getColumnNameCN(colList, "view_bks_xsxx");
		return dao.arrayToList(colList, colCNList);
	}
	
	/**
	 * ����Ƿ�����޸�ѧ����Ϣ
	 * */
	public boolean checkXsxxModify(){		
		return dao.checkModifyXsxx();
	}
	
	/**
	 * ��ȡ�����б�
	 * */
	public List<HashMap<String, String>> getMzList(){
		return dao.getMzList();
	}
	
	/**
	 * ��ȡ������ò�б�
	 * */
	public List<HashMap<String, String>> getZzmmList(){
		return dao.getZzmmList();
	}
	
	/**
	 * ��ȡʡ�б�
	 * */
	public List<HashMap<String, String>> getSsList(){
		StuInfoDAO stuDao = new StuInfoDAO();
		return stuDao.getSsList();
	}
	
	/**
	 * ��ȡ���б�
	 * */
	public HashMap<String, List<HashMap<String, String>>> getShiList(String jgStr){
		StuInfoDAO stuDao = new StuInfoDAO();
		return stuDao.getShiList(jgStr);
	}
	
	/**
	 * ����ʡ�����ȡ���б�
	 * @param String shenId
	 * @return HashMap<String, List>
	 * */
	public HashMap<String, List<HashMap<String, String>>> getShiListNew(String shenId){
		StuInfoDAO stuDao = new StuInfoDAO();
		return stuDao.getShiListNew(shenId);
	}
	
	/**
	 * ��ȡ�û��ļ�����
	 * */
	public String getSpeType(String userType){
		String result = "";
		if("xy".equalsIgnoreCase(userType)){
			result = "xy";
		}else if("student".equalsIgnoreCase(userType)){
			result = "student";
		}else{
			result = "xx";
		}
		return result;
	}
}
