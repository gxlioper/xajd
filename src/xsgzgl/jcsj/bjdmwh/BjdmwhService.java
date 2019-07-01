package xsgzgl.jcsj.bjdmwh;

import java.util.HashMap;
import java.util.List;

import xsgzgl.jcsj.comm.JcsjService;

public class BjdmwhService extends JcsjService{
	
	private BjdmwhDao dao=new BjdmwhDao();
	
	/**
	 * ��ȡ��ͷ
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		return new String[]{"�༶����","�༶����","רҵ","ѧԺ","�꼶","�����쳣ԭ��"};
	}
	
	/**
	 * ��ȡ�༶��Ϣ���
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getBjdmList(BjdmwhForm model) throws Exception{
		return dao.getBjdmList(model);
	}
	
	/**
	 * ����༶��Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveBjdmInfo(BjdmwhForm model,String type) throws Exception{
		return dao.saveBjdmInfo(model, type);
	}
	
	/**
	 * ɾ���༶��Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String deleteBjdmInfo(BjdmwhForm model) throws Exception{
		boolean b=dao.deleteBjdmInfo(model);
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
	 * ��ȡרҵ�б�
	 * @return
	 */
	public List<HashMap<String,String>> getZydmList(String bmdm){
		return dao.getZydmList(bmdm);
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
