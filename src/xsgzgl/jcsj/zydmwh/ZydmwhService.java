package xsgzgl.jcsj.zydmwh;

import java.util.HashMap;
import java.util.List;

import xsgzgl.jcsj.comm.JcsjService;

public class ZydmwhService extends JcsjService{
	
	private ZydmwhDao dao=new ZydmwhDao();
	
	/**
	 * ��ȡ��ͷ
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		return new String[]{"רҵ����","רҵ����","����","ѧ��","�����쳣ԭ��"};
	}
	
	/**
	 * ��ȡרҵ��Ϣ���
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getZydmList(ZydmwhForm model) throws Exception{
		return dao.getZydmList(model);
	}
	
	/**
	 * ����רҵ��Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveZydmInfo(ZydmwhForm model,String type) throws Exception{
		return dao.saveZydmInfo(model, type);
	}
	
	/**
	 * ɾ��רҵ��Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String deleteZydmInfo(ZydmwhForm model) throws Exception{
		boolean b=dao.deleteZydmInfo(model);
		return b?"ɾ���ɹ���":"ɾ��ʧ�ܣ�";
	}
	
	/**
	 * ��ȡ�����б�
	 * @return
	 */
	public List<HashMap<String,String>> getBmdmList(){
		return dao.getBmdmList();
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
