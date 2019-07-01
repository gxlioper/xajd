package xgxt.pjpy.mjxy.jxj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.GetTime;

public class MjxyJxjService {
	public List<HashMap<String,String>>jxjList(){
		MjxyJxyDao mjxyJxyDao=new MjxyJxyDao();
		return mjxyJxyDao.jxjList();
	}
	
	/**
	 * 某学生某学年补考科目数
	 */
	public String getBkkms(String xn,String xh){
		MjxyJxyDao mjxyJxyDao=new MjxyJxyDao();
		String bjgms="";
		List<HashMap<String,String>>list=mjxyJxyDao.getBjgms(xn, xh);
		if(list.size()>0){
			HashMap<String,String> hashMap=list.get(0);
			bjgms=hashMap.get("bjgms");
		}else{
			//没有该学生的考试信息
			bjgms="0";
		}
		return bjgms;
	}
	
	/**
	 *某学生所在专业人数 
	 */
	public String getZyrs(String xh){
		MjxyJxyDao mjxyJxjDao=new MjxyJxyDao();
		List<HashMap<String,String>>list=mjxyJxjDao.getZyrs(xh);
		String zyrs="";
		if(list.size()>0){
			HashMap<String,String> hashMap=list.get(0);
			zyrs=hashMap.get("zyrs");
		}else{
			//没有该学生的考试信息
			zyrs="1";
		}
		return zyrs;
	}
	
	/**
	 * 当（上）学年综考门数
	 * 
	 */
	public String getZkms(String xn,String xh){
		MjxyJxyDao mjxyJxyDao=new MjxyJxyDao();
		String zkms="";
		List<HashMap<String,String>>list=mjxyJxyDao.getZkms(xn, xh);
		if(list.size()>0){
			HashMap<String,String> hashMap=list.get(0);
			zkms=hashMap.get("zkms");
		}else{
			//没有该学生的考试信息
			zkms="0";
		}
		return zkms;
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
	 * 设置CHECK BOX的disabled属性
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
	public void writeRead(MjxyJxjForm jxjForm,HttpServletRequest request,
			HashMap<String,String>hashMap,String userType){
		String write="yes";
		if("xy".equalsIgnoreCase(userType)){
			jxjForm.setSave_xysh(hashMap.get("xysh"));
			if("通过".equalsIgnoreCase(hashMap.get("xxsh"))){
				write="no";
			}
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			jxjForm.setSave_xxsh(hashMap.get("xxsh"));
			write="yes";
		}else{
			write="no";
		}
		request.setAttribute("write", write);
	}
	
}
