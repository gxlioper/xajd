/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 大学生生涯论坛注册Model</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-4 下午04:49:41</p>
 */
package xgxt.action.zgkd.userReg;

public class UserRegModel {
	private String yhm;//用户名
	private String nc;//昵称
	private String grqm;//个人签名
	private String xm;//姓名
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getGrqm() {
		return grqm;
	}
	public void setGrqm(String grqm) {
		this.grqm = grqm;
	}
	public String getNc() {
		return nc;
	}
	public void setNc(String nc) {
		this.nc = nc;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	
}
