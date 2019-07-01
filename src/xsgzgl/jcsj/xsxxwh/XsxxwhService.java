package xsgzgl.jcsj.xsxxwh;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xsgzgl.jcsj.comm.JcsjService;

public class XsxxwhService extends JcsjService{
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	XsxxwhDao dao=new XsxxwhDao();
	
	public String[] getTopTr(){
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] topTr=new String[]{"ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","�����쳣ԭ��"};
		return topTr;
	}
	/**
	 * ��ȡѧ����Ϣ�б�
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getXsxxList(XsxxwhForm model) throws Exception{
		return dao.getXsxxList(model);
	}
	
	/**
	 * ɾ��ѧ����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String deleteXsxxInfo(XsxxwhForm model) throws Exception{
		boolean b=dao.deleteXsxxInfo(model);
		return b?"ɾ���ɹ���":"ɾ��ʧ�ܣ�";
	}
	
	/**
	 * ����쳣����
	 * @return
	 * @throws Exception 
	 */
	public String checkExceptionData() throws Exception{
		return dao.checkExceptionData();
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
	 * ��ȡרҵ�б�
	 * @return
	 */
	public List<HashMap<String,String>> getBjdmList(String zydm){
		return dao.getBjdmList(zydm);
	}
	
}
