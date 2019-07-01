package xgxt.action.zgkd;

import java.util.HashMap;
import java.util.List;

public class SyltService {
	private static SyltService  syltService;
	//�õ�Service��ʵ������
	public static SyltService getSyltService(){
		if(syltService == null){
			syltService = new SyltService();
		}
		return syltService;	
	}
	private SyltDao syltDao = SyltDao.getSyltDao();
	
	/**
	 * �õ�������̳����Ϣ
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getTalksInfo(){
		return syltDao.getTalksInfo();
	}
	
	/**
     * ������̳������ע��
     * @return
     */
    public List<HashMap<String,String>> getTalksCN(){
    	return syltDao.getTalksCN();
    }
    
    /**
     * �����û���Ȩ��
     * @param userName
     * @return
     */
    public String checkUserPower(String userName){
    	return syltDao.checkUserPower(userName);
    }
    
    /**
     * �����û��Ĳ���Ȩ�� true ��ʾ���� false ������
     * @param userName
     * @param bkmc
     * @return boolean
     */
    public boolean checkUserOperatePower(String userName,String bkmc){
    	return syltDao.checkUserOperatePower(userName,bkmc);
    }
    
    /**
	 * ����where��������������������������
	 * @param tableName
	 * @param columns
	 * @param values
	 * @return String
	 */
    public String getTotalRsNumByEqual(String tableName,String[] columns,String[] values){
    	return syltDao.getTotalRsNumByEqual(tableName, columns, values);
    }
    
    /**
     * �����ӵ��������������������1
     * @param tableName
     * @param updateCol
     * @param updateVal
     * @param pk
     * @param pkValue
     * @return boolean
     * @throws Exception
     */
    public boolean addLllOrPll(String tableName,String updateCol,String pk,String pkValue)
    throws Exception{
    	return syltDao.addLllOrPll(tableName, updateCol, pk, pkValue);
    }
}
