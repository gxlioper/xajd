package xgxt.action.zgkd;

import java.util.HashMap;
import java.util.List;

public class SyltService {
	private static SyltService  syltService;
	//得到Service的实例变量
	public static SyltService getSyltService(){
		if(syltService == null){
			syltService = new SyltService();
		}
		return syltService;	
	}
	private SyltDao syltDao = SyltDao.getSyltDao();
	
	/**
	 * 得到各个论坛的信息
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getTalksInfo(){
		return syltDao.getTalksInfo();
	}
	
	/**
     * 返回论坛的中文注释
     * @return
     */
    public List<HashMap<String,String>> getTalksCN(){
    	return syltDao.getTalksCN();
    }
    
    /**
     * 返回用户的权限
     * @param userName
     * @return
     */
    public String checkUserPower(String userName){
    	return syltDao.checkUserPower(userName);
    }
    
    /**
     * 返回用户的操作权限 true 表示可以 false 不可以
     * @param userName
     * @param bkmc
     * @return boolean
     */
    public boolean checkUserOperatePower(String userName,String bkmc){
    	return syltDao.checkUserOperatePower(userName,bkmc);
    }
    
    /**
	 * 根据where的条件返回满足条件的数据量
	 * @param tableName
	 * @param columns
	 * @param values
	 * @return String
	 */
    public String getTotalRsNumByEqual(String tableName,String[] columns,String[] values){
    	return syltDao.getTotalRsNumByEqual(tableName, columns, values);
    }
    
    /**
     * 给帖子的浏览量或者是评论量加1
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
