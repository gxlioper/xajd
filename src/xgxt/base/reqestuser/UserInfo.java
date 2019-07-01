
package xgxt.base.reqestuser;

import javax.servlet.http.HttpServletRequest;


public class UserInfo {
//	ip= request.getRemoteAddr();
//	clienttime=new Long(request.getDateHeader("Date")).toString();
//	host=request.getRemoteHost();
//	mac=dao.getMacAddressIP(ip);
//	yhm=session.getAttribute("userName").toString();
	
	private String ip;
	private String mac;
	private String yhm;
	private String time;
	private String host;
	
	public UserInfo(){}
	
	public UserInfo(HttpServletRequest req){
		
	}
	
	public UserInfo(String ip,String mac){
		this.ip = ip;
		this.mac = mac;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
}
