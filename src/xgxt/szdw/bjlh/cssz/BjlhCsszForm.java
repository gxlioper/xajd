package xgxt.szdw.bjlh.cssz;

import org.apache.struts.action.ActionForm;

public class BjlhCsszForm extends ActionForm{
	private static final long serialVersionUID = -9205711105806100577L;
	private String xn;//学年
	private String khsfqd;//考核是否启动
	private String khkssj;//考核开始时间
	private String khjssj;//考核结束时间
	private String khlrjzsj;//考核录入截止时间
	private String jskhpfbl;//教师考核评分比例
	private String xskhpfbl;//学生考核评分比例
	private String xspfyxbl;//学生有效评分比例
	
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getKhsfqd() {
		return khsfqd;
	}
	public void setKhsfqd(String khsfqd) {
		this.khsfqd = khsfqd;
	}
	public String getKhkssj() {
		return khkssj;
	}
	public void setKhkssj(String khkssj) {
		this.khkssj = khkssj;
	}
	public String getKhjssj() {
		return khjssj;
	}
	public void setKhjssj(String khjssj) {
		this.khjssj = khjssj;
	}
	public String getKhlrjzsj() {
		return khlrjzsj;
	}
	public void setKhlrjzsj(String khlrjzsj) {
		this.khlrjzsj = khlrjzsj;
	}
	public String getJskhpfbl() {
		return jskhpfbl;
	}
	public void setJskhpfbl(String jskhpfbl) {
		this.jskhpfbl = jskhpfbl;
	}
	public String getXskhpfbl() {
		return xskhpfbl;
	}
	public void setXskhpfbl(String xskhpfbl) {
		this.xskhpfbl = xskhpfbl;
	}
	public String getXspfyxbl() {
		return xspfyxbl;
	}
	public void setXspfyxbl(String xspfyxbl) {
		this.xspfyxbl = xspfyxbl;
	}
	
	
	
}
