
package xgxt.pjpy.xnmz;

import org.apache.struts.action.ActionForm;

public class XnmzForm extends ActionForm{
	
	private static final long serialVersionUID = 7776943933669827705L;
	private String xn;
	private String xq;
	private String cjbl;
	private String dybl;
	private String tybl;
	private String qtbl;
	private boolean result;
	
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getCjbl() {
		return cjbl;
	}
	public void setCjbl(String cjbl) {
		this.cjbl = cjbl;
	}
	public String getDybl() {
		return dybl;
	}
	public void setDybl(String dybl) {
		this.dybl = dybl;
	}
	public String getQtbl() {
		return qtbl;
	}
	public void setQtbl(String qtbl) {
		this.qtbl = qtbl;
	}
	public String getTybl() {
		return tybl;
	}
	public void setTybl(String tybl) {
		this.tybl = tybl;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
}
