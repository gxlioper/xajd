package xsgzgl.xtwh.general.qxgl.yhzgl;

import xsgzgl.comm.form.CommForm;

public class YhzglNewForm extends CommForm{

	private String zdm;			//组代码
	private String zmc;			//组名称
	
	private String pkValue;

	private String yhzgnqx;
	
	public String getZdm() {
		return zdm;
	}
	public void setZdm(String zdm) {
		this.zdm = zdm;
	}
	public String getZmc() {
		return zmc;
	}
	public void setZmc(String zmc) {
		this.zmc = zmc;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getYhzgnqx() {
		return yhzgnqx;
	}

	public void setYhzgnqx(String yhzgnqx) {
		this.yhzgnqx = yhzgnqx;
	}
}
