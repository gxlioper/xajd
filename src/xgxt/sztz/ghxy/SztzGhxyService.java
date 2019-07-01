package xgxt.sztz.ghxy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class SztzGhxyService {
	
	/**
	 * ���� �û�����ȡ���Ŵ���
	 * @param userName
	 * @return String
	 */
	public String getBmdm(String userName){
		SztzGhxyDao dao=new SztzGhxyDao();
		List<HashMap<String,String>>list=dao.getBmdm(userName);
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			return hashMap.get("bmdm");
		}else{
			return "";
		}
	}
	
	/**
	 * ��ȡ�����б�
	 */
	public List<HashMap<String,String>> bmList(){
		SztzGhxyDao dao=new SztzGhxyDao();
		return dao.bmList();
	}
	
	/**
	 * ����ֶα���
	 * 
	 */
	public HashMap<String,String>setShzd(String shjg,String userType){
		HashMap<String,String>valueMap=new HashMap<String,String>();
		if("xy".equalsIgnoreCase(userType)){
			valueMap.put("bmsh", shjg);
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			valueMap.put("bmsh", shjg);
			valueMap.put("xxsh", shjg);
		}
		return valueMap;
	}
	
	/**
	 * ���ò鿴��disabled
	 * 
	 */
	public void setDisabled(HttpServletRequest request,String userType,
			String userName){
		SztzGhxyService service=new SztzGhxyService();
		String bmdm=service.getBmdm(userName);
		if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("annexTerm", " and (bmsh='ͨ��' or  sqbm= '"+bmdm+"')");
			request.setAttribute("clientColumns", "('')disabled,");
		}else{
			request.setAttribute("annexTerm", " and  sqbm= '"+bmdm+"' ");
			request.setAttribute("clientColumns", "(case when xxsh='ͨ��' then 'disabled' else '' end)disabled,");
			
			
		}
	}
}
