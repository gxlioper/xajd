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
	 * 根据 用户名获取部门代码
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
	 * 获取部门列表
	 */
	public List<HashMap<String,String>> bmList(){
		SztzGhxyDao dao=new SztzGhxyDao();
		return dao.bmList();
	}
	
	/**
	 * 审核字段保存
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
	 * 设置查看和disabled
	 * 
	 */
	public void setDisabled(HttpServletRequest request,String userType,
			String userName){
		SztzGhxyService service=new SztzGhxyService();
		String bmdm=service.getBmdm(userName);
		if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("annexTerm", " and (bmsh='通过' or  sqbm= '"+bmdm+"')");
			request.setAttribute("clientColumns", "('')disabled,");
		}else{
			request.setAttribute("annexTerm", " and  sqbm= '"+bmdm+"' ");
			request.setAttribute("clientColumns", "(case when xxsh='通过' then 'disabled' else '' end)disabled,");
			
			
		}
	}
}
