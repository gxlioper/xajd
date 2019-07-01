package xsgzgl.jxgl.hzsf.grry;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;
/**
 * 军训管理-军训获奖-个人荣誉
 * @author yeyipin
 * @since 2012.7.27
 */
public class JxglGrryForm  extends CommForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String query; //
	private String pkValue;//主键
	private String xn;//学年
	private String nj;//年级
	private String grrydm;//个人荣誉代码
	private String xydm;//学院代码
	private String zydm;//专业代码
	private String bjdm;//班级代码
	private String bzdj;//编制等级
	private String sjdm;//上级代码
	private String tuandm;//团代码
	private String yingdm;//营代码
	private String liandm;//连代码
	private String xh;//学号
	private String xm;//姓名
	private String bz;//备注
	private String doType;//类型：add，update
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getGrrydm() {
		return grrydm;
	}
	public void setGrrydm(String grrydm) {
		this.grrydm = grrydm;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	
	public String getBzdj() {
		return bzdj;
	}
	public void setBzdj(String bzdj) {
		this.bzdj = bzdj;
	}
	public String getSjdm() {
		return sjdm;
	}
	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}
	public String getTuandm() {
		return tuandm;
	}
	public void setTuandm(String tuandm) {
		this.tuandm = tuandm;
	}
	public String getYingdm() {
		return yingdm;
	}
	public void setYingdm(String yingdm) {
		this.yingdm = yingdm;
	}
	public String getLiandm() {
		return liandm;
	}
	public void setLiandm(String liandm) {
		this.liandm = liandm;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getDoType() {
		return doType;
	}
	public void setDoType(String doType) {
		this.doType = doType;
	}
	
}
