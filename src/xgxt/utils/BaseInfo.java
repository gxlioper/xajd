
package xgxt.utils;

public class BaseInfo {
	private String userName;
	private String Ip;
	private String Mac;
	private String ClientDate;
	private String Host;
	private String userLoginType; 	//用户类型（学生、老师）
	private String userType;		//用户类型（xy、xx）
	private String userDep;
	private String userXm;
	
	public String getHost() {
		return Host;
	}
	public void setHost(String host) {
		Host = host;
	}
	public String getIp() {
		return Ip;
	}
	public void setIp(String ip) {
		Ip = ip;
	}
	public String getMac() {
		return Mac;
	}
	public void setMac(String mac) {
		Mac = mac;
	}
	public String getClientDate() {
		return ClientDate;
	}
	public void setClientDate(String clientDate) {
		ClientDate = clientDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDep() {
		return userDep;
	}
	public void setUserDep(String userDep) {
		this.userDep = userDep;
	}
	public String getUserLoginType() {
		return userLoginType;
	}
	public void setUserLoginType(String userLoginType) {
		this.userLoginType = userLoginType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserXm() {
		return userXm;
	}
	public void setUserXm(String userXm) {
		this.userXm = userXm;
	}

}
