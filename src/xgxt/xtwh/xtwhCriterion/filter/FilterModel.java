package xgxt.xtwh.xtwhCriterion.filter;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 过滤器model</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LUNING</p>
 * <p>Version: 1.0</p>
 * <p>Time:2011-4-28</p>
 */

public class FilterModel {
	
	String userName;	//用户名
	String fwlj; 		//访问路径
	String purview;	    //页面上保存的已经取出的拥有权限id
	String operateBound;//查询范围
	String substituteUser;//查询范围
	String title;//页面功能名称
	String userType;//用户类型
	String gnmkdm;//功能模块代码
	String userRoles;//用户角色的拼接字符串
	
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubstituteUser() {
		return substituteUser;
	}
	public void setSubstituteUser(String substituteUser) {
		this.substituteUser = substituteUser;
	}
	public String getOperateBound() {
		return operateBound;
	}
	public void setOperateBound(String operateBound) {
		this.operateBound = operateBound;
	}
	public String getFwlj() {
		return fwlj;
	}
	public void setFwlj(String fwlj) {
		this.fwlj = fwlj;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPurview() {
		return purview;
	}
	public void setPurview(String purview) {
		this.purview = purview;
	}
	public String getGnmkdm() {
		return gnmkdm;
	}
	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
	}
	public String getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}
}
