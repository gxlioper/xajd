package xsgzgl.jxgl.hzsf.dmwh;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;
/**
 * 军训管理-基础设置-代码维护
 * @author yeyipin
 * @since 2012.7.16
 */
public class JxglDmwhForm extends CommForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String pkValue;//主键
	private String grrydm;
	private String grrymc;
	private String tdrydm;
	private String tdrymc;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getGrrydm() {
		return grrydm;
	}
	public void setGrrydm(String grrydm) {
		this.grrydm = grrydm;
	}
	public String getGrrymc() {
		return grrymc;
	}
	public void setGrrymc(String grrymc) {
		this.grrymc = grrymc;
	}
	public String getTdrydm() {
		return tdrydm;
	}
	public void setTdrydm(String tdrydm) {
		this.tdrydm = tdrydm;
	}
	public String getTdrymc() {
		return tdrymc;
	}
	public void setTdrymc(String tdrymc) {
		this.tdrymc = tdrymc;
	}
	

}
