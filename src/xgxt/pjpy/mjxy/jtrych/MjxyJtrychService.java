package xgxt.pjpy.mjxy.jtrych;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.GetTime;

public class MjxyJtrychService {
	
	/**
	 * 集体荣誉称号
	 */
	public List<HashMap<String,String>>jtrychList(){
		MjxyJtrychDao jtrychDao=new MjxyJtrychDao();
		return jtrychDao.jtrychList();
	}
	
	/**
	 * 根据班级获取班级人数
	 */
	public String Bjrs(String bjdm){
		MjxyJtrychDao jtrychDao=new MjxyJtrychDao();
		List<HashMap<String,String>>list=jtrychDao.getBjrs(bjdm);
		String bjrs="0";
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			bjrs=hashMap.get("bjrs");
		}
		return bjrs;
	}
	
	/**
	 * 班级党员人数
	 */
	public String bjdyrs(String bjdm){
		MjxyJtrychDao jtrychDao=new MjxyJtrychDao();
		List<HashMap<String,String>>list=jtrychDao.getBjdyrs(bjdm);
		String bjdyrs="0";
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			bjdyrs=hashMap.get("dyrs");
		}
		return bjdyrs;
	}
	
	/**
	 * 获取班级干部(班长,团支书)
	 * 
	 */
	public String bjgbxx(String bjdm){
		MjxyJtrychDao jtrychDao=new MjxyJtrychDao();
		List<HashMap<String,String>>list=jtrychDao.getBjgbxx(bjdm);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<list.size();i++){
			HashMap<String,String>hashMap=list.get(i);
			sb.append(hashMap.get("bjgbmc"));
			sb.append(":");
			sb.append(hashMap.get("xm"));
			sb.append(" ");
		}
		return sb.toString();
	}
	
	/**
	 * 获取班级处分人数
	 */
	public String bjcfrs(String bjdm){
		MjxyJtrychDao jtrychDao=new MjxyJtrychDao();
		List<HashMap<String,String>>list=jtrychDao.getCfrs(bjdm);
		String bjcfrs="0";
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			bjcfrs=hashMap.get("bjcfrs");
		}
		return bjcfrs;
	}
	
	/**
	 * 审核字段，时间设置
	 * 
	 */
	public HashMap setShzd(String userType,String shjg){
		HashMap<String,String>valueMap=new HashMap<String,String>();
		String shzd="";
		String shsj="";
		if("xy".equalsIgnoreCase(userType)){
			shzd="xysh";
			shsj="xyshsj";
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			shsj="xxshsj";
			shzd="xxsh";
		}
		valueMap.put(shzd, shjg);
		valueMap.put(shsj, GetTime.getNowTime2());
		return valueMap;
	}
	
	/**
	 * disabled属性,学校访问权限
	 */
	public void disabled(HttpServletRequest request,String userType){
		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case when xxsh='通过' then 'disabled' else '' end)disabled,");
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns","('')disabled, " );
		}else{
			request.setAttribute("clientColumns", "(case when xysh='通过' then 'disabled' else '' end)disabled,");
		}
	}
	
	/**
	 * disabled属性,学校访问权限
	 */
	public void setDisabled(HttpServletRequest request,String userType){
		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case when xxsh='通过' then 'disabled' else '' end)disabled,");
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns","('')disabled, " );
			request.setAttribute("annexTerm", " and xysh='通过' ");
		}
	}
	
	/**
	 * 获取班级代码
	 * 
	 */
	public String getBjdm(String xh){
		String bjdm="";
		MjxyJtrychDao dao=new MjxyJtrychDao();
		List<HashMap<String,String>>list=dao.getBjdm(xh);
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			bjdm=hashMap.get("bjdm");
		}
		return bjdm;
	}
	
	/**
	 * 读写权限
	 * 
	 */
	public void writeRead(HttpServletRequest request,String userType){
		HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
		String write="yes";
		if("xy".equalsIgnoreCase(userType)){
			if("通过".equals(hashMap.get("xxsh"))){
				write="no";
			}
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			write="yes";
		}else {
			if("通过".equals(hashMap.get("xysh"))){
				write="no";
			}
		}
		request.setAttribute("write", write);
	}
	
	/**
	 * 结果查询 读写权限
	 * 
	 */
	public void writeReadCx(HttpServletRequest request,String userType,String doType){
		HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
		if("xy".equalsIgnoreCase(userType)){
			if("通过".equals(hashMap.get("xxsh"))){
				doType="view";
			}
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			doType="modi";
		}else {
			if("通过".equals(hashMap.get("xysh"))){
				doType="view";
			}
		}
		request.setAttribute("doType", doType);
	}
	
	
	public void getBjxx(HttpServletRequest request,String xh){
		MjxyJtrychDao dao=new MjxyJtrychDao();
		List<HashMap<String,String>>list=dao.getBjxx(xh);
		if(list.size()>0){
			request.setAttribute("bjxx",list.get(0));
		}
	}
	
	public String getNjByBjdm(String bjdm){
		MjxyJtrychDao dao=new MjxyJtrychDao();
		List<HashMap<String,String>>list= dao.getNjByBjdm(bjdm);
		String nj="";
		if(list.size()>0){
			HashMap<String,String>map=list.get(0);
			nj=map.get("nj");
		}
		return nj;
	}
}
