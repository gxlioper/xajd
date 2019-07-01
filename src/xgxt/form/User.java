package xgxt.form;

import java.util.HashMap;
import java.util.Map;

import xgxt.comm.xml.XMLReader;

public class User {

	// 应用角色维护
	private String userRolesApply = XMLReader.getFlowControl("comm",
			"userRolesApply");

	private String userName;

	private String ip;

	private String userMac;

	private String host;

	private String userType;

	private String isFdy;

	private String userDep;

	private String userSyDep;//用户书院

	private String userDepName;

	private String realName;

	// 辅导员权限
	private String fdyQx;

	// 班主任权限
	private String bzrQx;

	// 公寓管理员权限
	private String gyglyQx;

	// 用户身份
	private String userStatus;

	// 用户角色
	private String[] userRoles;

	// 不控制的身份
	private HashMap<String, String> notCtrlStatus = new HashMap<String, String>();
	private Map<String,String> authMap = null;
	
	//用户密码等级  用户密码等级状态【1：弱，2：中，3：高，4：超高】
	private String yhmmdj;

	//子女学号
	private String childId;

	// 书院权限
	private String syQx;


	public String getUserSyDep() {
		return userSyDep;
	}

	public void setUserSyDep(String userSyDep) {
		this.userSyDep = userSyDep;
	}

	/**
	 * @return the authMap
	 */
	public Map<String, String> getAuthMap() {
		return authMap;
	}

	/**
	 * @param authMap要设置的 authMap
	 */
	public void setAuthMap(Map<String, String> authMap) {
		this.authMap = authMap;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserDep() {
		return userDep;
	}

	public void setUserDep(String userDep) {
		this.userDep = userDep;
	}

	/**
	 * @return the userDepName
	 */
	public String getUserDepName() {
		return userDepName;
	}

	/**
	 * @param userDepName要设置的 userDepName
	 */
	public void setUserDepName(String userDepName) {
		this.userDepName = userDepName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIsFdy() {
		return isFdy;
	}

	public void setIsFdy(String isFdy) {
		this.isFdy = isFdy;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserMac() {
		return userMac;
	}

	public void setUserMac(String userMac) {
		this.userMac = userMac;
	}

	public String getBzrQx() {
		return bzrQx;
	}

	public void setBzrQx(String bzrQx) {
		this.bzrQx = bzrQx;
	}

	public String getFdyQx() {
		return fdyQx;
	}

	public void setFdyQx(String fdyQx) {
		this.fdyQx = fdyQx;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String[] getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String[] userRoles) {
		this.userRoles = userRoles;
	}

	public String getUserRolesApply() {
		return userRolesApply;
	}

	public void setUserRolesApply(String userRolesApply) {
		this.userRolesApply = userRolesApply;
	}

	public String getGyglyQx() {
		return gyglyQx;
	}

	public void setGyglyQx(String gyglyQx) {
		this.gyglyQx = gyglyQx;
	}

	public HashMap<String, String> getNotCtrlStatus() {
		return notCtrlStatus;
	}

	public void setNotCtrlStatus(HashMap<String, String> notCtrlStatus) {
		this.notCtrlStatus = notCtrlStatus;
	}
	
	/**
	 * 获取数据范围查询条件
	 * @author sjf
	 * @return
	 */
	public String getQueryCondition(){
		String condition = "";
		
		if("jd".equalsIgnoreCase(userStatus)){
			condition = " and (exists (select 1 from fdybjb b where b.bjdm=a.bjdm and b.zgh='"+userName+"')" + 
						" or exists (select 1 from bzrbbb b where b.bjdm=a.bjdm and b.zgh='"+userName+"'))";
		}else if("fdy".equalsIgnoreCase(userStatus)){
			condition = " and exists (select 1 from fdybjb b where b.bjdm=a.bjdm and b.zgh='"+userName+"') ";
		}else if("bzr".equalsIgnoreCase(userStatus)){
			condition = " and exists (select 1 from bzrbbb b where b.bjdm=a.bjdm and b.zgh='"+userName+"') ";
		}else if("xy".equalsIgnoreCase(userStatus)){
			condition = " and a.xydm= '" + userDep + "'";
		}else if("stu".equalsIgnoreCase(userStatus)){
			condition = " and a.xh='" + userName + "'";
		}
		
		return condition;
	}

	public String getYhmmdj() {
		return yhmmdj;
	}

	public void setYhmmdj(String yhmmdj) {
		this.yhmmdj = yhmmdj;
	}

	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	/**
	 * @return the syQx
	 */
	public String getSyQx() {
		return syQx;
	}

	/**
	 * @param syQx要设置的 syQx
	 */
	public void setSyQx(String syQx) {
		this.syQx = syQx;
	}
}
