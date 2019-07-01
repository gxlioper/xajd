package xgxt.pjpy.mjxy.jtrych;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.GetTime;

public class MjxyJtrychService {
	
	/**
	 * ���������ƺ�
	 */
	public List<HashMap<String,String>>jtrychList(){
		MjxyJtrychDao jtrychDao=new MjxyJtrychDao();
		return jtrychDao.jtrychList();
	}
	
	/**
	 * ���ݰ༶��ȡ�༶����
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
	 * �༶��Ա����
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
	 * ��ȡ�༶�ɲ�(�೤,��֧��)
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
	 * ��ȡ�༶��������
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
	 * ����ֶΣ�ʱ������
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
	 * disabled����,ѧУ����Ȩ��
	 */
	public void disabled(HttpServletRequest request,String userType){
		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case when xxsh='ͨ��' then 'disabled' else '' end)disabled,");
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns","('')disabled, " );
		}else{
			request.setAttribute("clientColumns", "(case when xysh='ͨ��' then 'disabled' else '' end)disabled,");
		}
	}
	
	/**
	 * disabled����,ѧУ����Ȩ��
	 */
	public void setDisabled(HttpServletRequest request,String userType){
		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case when xxsh='ͨ��' then 'disabled' else '' end)disabled,");
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns","('')disabled, " );
			request.setAttribute("annexTerm", " and xysh='ͨ��' ");
		}
	}
	
	/**
	 * ��ȡ�༶����
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
	 * ��дȨ��
	 * 
	 */
	public void writeRead(HttpServletRequest request,String userType){
		HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
		String write="yes";
		if("xy".equalsIgnoreCase(userType)){
			if("ͨ��".equals(hashMap.get("xxsh"))){
				write="no";
			}
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			write="yes";
		}else {
			if("ͨ��".equals(hashMap.get("xysh"))){
				write="no";
			}
		}
		request.setAttribute("write", write);
	}
	
	/**
	 * �����ѯ ��дȨ��
	 * 
	 */
	public void writeReadCx(HttpServletRequest request,String userType,String doType){
		HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
		if("xy".equalsIgnoreCase(userType)){
			if("ͨ��".equals(hashMap.get("xxsh"))){
				doType="view";
			}
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			doType="modi";
		}else {
			if("ͨ��".equals(hashMap.get("xysh"))){
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
