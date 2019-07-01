
package xgxt.pjpy.xbemy;

public class PjpyXbemyShModel {
	private String       xmdm;//奖学金代码
	private String       xn;  //学年 
	private String[]     cbv; //checkboxes
	private String jg;//通过结果
	
	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String[] getCbv() {
		return cbv;
	}

	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}
	
	
}
