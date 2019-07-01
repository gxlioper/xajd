package xsgzgl.jcsj.bmdmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xsgzgl.jcsj.comm.JcsjService;

public class BmdmwhService extends JcsjService{
	
	private BmdmwhDao dao=new BmdmwhDao();
	
	/**
	 * ��ȡ��ͷ
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		return new String[]{"���Ŵ���","��������","����������","�����������","�����쳣ԭ��"};
	}
	
	/**
	 * ��ȡ������Ϣ���
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getBmdmList(BmdmwhForm model) throws Exception{
		return dao.getBmdmList(model);
	}
	
	/**
	 * ���沿����Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveBmdmInfo(BmdmwhForm model,String type) throws Exception{
		return dao.saveBmdmInfo(model, type);
	}
	
	/**
	 * ɾ��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String deleteBmdmInfo(BmdmwhForm model) throws Exception{
		boolean b=dao.deleteBmdmInfo(model);
		return b?"ɾ���ɹ���":"ɾ��ʧ�ܣ�";
	}
	
	/**
	 * ��ȡ��������б�
	 * @return
	 */
	public List<HashMap<String,String>> getBmlbList(){
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		
		HashMap<String,String> map1=new HashMap<String, String>();
		map1.put("dm", "5");
		map1.put("mc", "ѧԺ");
		list.add(map1);
		
		HashMap<String,String> map2=new HashMap<String, String>();
		map2.put("dm", "1");
		map2.put("mc", "����");
		list.add(map2);
		
		return list;
	}
	
	/**
	 * ����쳣����
	 * @return
	 * @throws Exception 
	 */
	public String checkExceptionData() throws Exception{
		return dao.checkExceptionData();
	}

}
