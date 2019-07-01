package xsgzgl.pjpy.general.bjry;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;

public class BjryglForm extends CommForm{
	private static final long serialVersionUID = -9205711105806100577L;
	private User user;
	private String guid;
	private String xn;//学年
	private String xq;//学期
	private String xqmc;//学期名称
	private String bjdm;//班级代码
	private String rydm;//荣誉代码
	private String hdsj;//获奖时间
	private String bz;//备注
	private String nj;//年级
	private String xymc;//学院名称
	private String zymc;//专业名称
	private String bjmc;//班级名称
	private String mc;//荣誉名称
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getXn() {
		return xn;
	}
	public String setXn(String xn) {
		return this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getRydm() {
		return rydm;
	}
	public void setRydm(String rydm) {
		this.rydm = rydm;
	}
	public String getHdsj() {
		return hdsj;
	}
	public void setHdsj(String hdsj) {
		this.hdsj = hdsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
}