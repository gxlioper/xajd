package xgxt.xsh.stgl.cdty;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class CdtyXsstService extends CommService{
	
	CdtyXsstDAO dao=new CdtyXsstDAO();
	/**
	 * ��ȡ������Ϣ�б�
	 * @return
	 */
	public List<HashMap<String,String>>getStxxList(CdtyXsstForm model){
		 
		return dao.getStxxList(model);
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<String[]> getStxxInfo(CdtyXsstForm model)
			throws Exception{
		
		return dao.getStxxInfo(model);
	}
	
	/**
	 * ��ȡ������ϸ��Ϣ
	 * 
	 * @return
	 */
	public HashMap<String, String> getStInfoList(CdtyXsstForm model) {

		return dao.getStInfoList(model);
	}
	
	/**
	 * �������ͻ�ȡ��ͷ
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getTopTr(String lx){
		
		DAO dao=DAO.getInstance();
		
		String[]en=null;
		String[]cn=null;
		
		//ѡ������
		if("xzst".equalsIgnoreCase(lx)){
			en=new String[]{"stdm", "stmc", "stxz", "stclsj", "zdls"};
			cn=new String[]{"���Ŵ���","��������","��������","���ų���ʱ��","ָ����ʦ"};
		//ѧ����ѯ
		}else if("sqcx".equalsIgnoreCase(lx)){
			en=new String[]{"pkValue","xh", "xm", "nj", "xymc", "zymc","bjmc","stmc","shzt"};
			cn=new String[]{"����","ѧ��","����","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","��������","���״̬"};
		}
		
		
		return dao.arrayToList(en, cn);
	}
	
	public boolean saveXsstInfo(CdtyXsstForm model) throws Exception{
		
		return dao.saveXsstInfo(model);
	}
	

	/**
	 * ��ȡѧ���걨������Ϣ
	 * 
	 * @return
	 */
	public ArrayList<String[]> getXsstInfo(CdtyXsstForm model)
			throws Exception {

		return dao.getXsstInfo(model);
	}
	
	/**
	 * ɾ��ѧ������������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean delXsstInfo(CdtyXsstForm model) throws Exception {
		
		return dao.delXsstInfo(model);
	}
	
	/**
	 * ��ȡѧ������������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getXsstMap(CdtyXsstForm model){
		return dao.getXsstMap(model);
	}
	
	public boolean updateShzt(CdtyXsstForm model) throws Exception {
		return dao.updateShzt(model);
	}
	
	/**
	 * ���Ÿɲ��б��ȡ
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getStgbList(CdtyXsstForm model){

		return dao.getStgbList(model);
	}
	
	/**
	 * ��ȡѧ������������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getStxsByXh(CdtyXsstForm model){

		return dao.getStxsByXh(model);
	}
	
	/**
	 * �����б�()
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getStByXh(CdtyXsstForm model){

		return dao.getStByXh(model);
	}
	
	public boolean saveXsstgb(CdtyXsstForm model) throws Exception{
		
		return dao.saveXsstgb(model);
	}
	
	/**
	 * ��ȡ���Ÿɲ���Ϣ
	 * 
	 * @return
	 */
	public ArrayList<String[]> getStgbInfo(CdtyXsstForm model)
			throws Exception {

		return dao.getStgbInfo(model);
	}
	
	public boolean checkStgb(CdtyXsstForm model) {

		String num = dao.checkStgb(model);

		boolean blog = true;
		if (!"0".equalsIgnoreCase(num)) {
			blog = false;
		}
		return blog;

	}
}
