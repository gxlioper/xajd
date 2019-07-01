package xgxt.pjpy.mjxy.rych;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.GetTime;

public class MjxyRychService {
	public void insertRychxx(String xh,String brjl,String drzw,String zhpfmc){
		MjxyRychDao mjxyRychDao=new MjxyRychDao();
		mjxyRychDao.insertRychxx(xh, brjl,drzw,zhpfmc);
	}
	
	/**
	 * 审核字段设置
	 */
	public HashMap<String,String> setShzd(String userType,String shjg){
		HashMap<String,String>valueMap=new HashMap<String,String>();
		//审核时间
		String shsj="";
		String shzd="";
		if("xy".equalsIgnoreCase(userType)){
			shsj="xyshsj";
			shzd="xysh";
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			shsj="xxshsj";
			shzd="xxsh";
		}
		valueMap.put(shzd,shjg);
		valueMap.put(shsj, GetTime.getNowTime2());
		return valueMap;
	}
	
	/**
	 * 设置CHECK BOX的disabled属性
	 */
	public void setDisabled(HttpServletRequest request,String userType){
		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case when xxsh='通过' then 'disabled' else '' end)disabled,");
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "('')disabled,");
			request.setAttribute("annexTerm", " and xysh='通过' ");
		}
	}
	
	/**
	 * 设置CHECK BOX的disabled属性 查询页面
	 */
	public void setDisabledCx(HttpServletRequest request,String userType){
		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case when xxsh='通过' then 'disabled' else '' end)disabled,");
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "('')disabled,");
		}
	}
	
	/**
	 * 读写权
	 * 
	 */
	public void writeRead(MjxyRychForm form,HttpServletRequest request,
			HashMap<String,String>hashMap,String userType){
		String write="yes";
		if("xy".equalsIgnoreCase(userType)){
			form.setSave_xysh(hashMap.get("xysh"));
			if("通过".equalsIgnoreCase(hashMap.get("xxsh"))){
				write="no";
			}
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			form.setSave_xxsh(hashMap.get("xxsh"));
			write="yes";
		}else{
			write="no";
		}
		request.setAttribute("write", write);
	}
}
