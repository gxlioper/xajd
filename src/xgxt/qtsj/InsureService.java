package xgxt.qtsj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.qtsj.dao.InsureDAO;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���չ���ģ��Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-12-15</p>
 */
public class InsureService {
	InsureDAO dao = new InsureDAO();//���ݿ������
	
	/**
	 * ѧ��Ͷ�������������
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean batchAuditing(CommanForm model, HttpServletRequest request){
		boolean flag = false;
		String yesNo = model.getYesNo();
		String pkValue = model.getPkValue();//����ֵ�ַ���
		String[] pkV = Base.isNull(pkValue) ? model.getPkV() : pkValue
				.split("!!"); 
		String pkString = "xh||nd||tbgsdm||tbxzdm";// �����ֶ�
		String userType = model.getUserType();//�û�����
		String[] columns = {"xxsh"};
		String[] values = null;
		String mes = "";//��ʾ��Ϣ
		
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			columns = new String[]{"xysh"};
		}
		
		//��˽��
		if(yesNo != null && "pass".equalsIgnoreCase(yesNo)){
			values = new String[]{"ͨ��"};
		}else{
			values = new String[]{"��ͨ��"};
		}
		
		for(int i=0; i<pkV.length; i++){
			try {
				flag = StandardOperation.update("xsbxb", columns, values, pkString, pkV[i], request);
			} catch (Exception e) {//����ʧ��
				flag = false;
				mes += "��" + (i+1) + "�����ݲ���ʧ�ܣ�\n" ;
				e.printStackTrace();
			}
			
		}		
		request.setAttribute("mes", mes);
		return flag;
	}
	
	/**
	 * ��ȡͶ�������б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getTbxzList(){
		return dao.getTbxzdmList();
	}
	
	/**
	 * ��ȡ���չ�˾�����
	 * @return List
	 * */
	public List<HashMap<String, String>> getBxgsList(){
		return dao.getBxgsList();
	}
	
	/**
	 * ��ȡ���յ��δ����
	 * @return List
	 * */
	public List<HashMap<String, String>> getBxdcList(){
		return dao.getBxdcList();
	}
	
	
	
	/**
	 * ���淢����Ϣ
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean savePubData(InsureForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tableName = "bxglxxb";
		String pk = "title";
		String keys = model.getTitle();
		if(dao.checkExists(tableName, pk, keys)){
			//update
			flag = StandardOperation.update(tableName, new String[]{"content"}, new String[]{model.getContent()}, "title", model.getTitle(), request);
			
		}else{
			//insert
			flag = StandardOperation.insert(tableName, new String[]{"title", "content"}, new String[]{model.getTitle(), model.getContent()}, request);
		}
		return flag;
	}
	
	/**
	 * ������Ϣ��ѯ
	 * @param title
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getDataPubInfo(String title){
		HashMap<String, String> map = dao.getDataPubInfo(title);
		return map;
	}
	
	
	
	/**
	 * ������Ŀ�б� 
	 * @return
	 */
	public List<HashMap<String, String>> getLpxmList() {
		
		return dao.getWhList("bxxx_lpxm", "dm", "mc", "", "", "");
	}
	
	
	
	/**
	 * �����б�
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getList(int type) {
		return dao.getChkList(type);
	}
	
	
	
	/**
	 * ѧ����Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}
}
