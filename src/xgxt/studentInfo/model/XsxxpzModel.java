package xgxt.studentInfo.model;


public class XsxxpzModel {
	private String[] ename;  // 英文名
	
	private String[] cname;  // 中文名
	
	private String[] sfxs;	 // 是否显示
	
	private String[] xssx;  // 显示顺序
	
	public String[] getEname() {
		return ename;
	}
	public void setEname(String[] ename) {
		this.ename = ename;
	}
	public String[] getCname() {
		return cname;
	}
	public void setCname(String[] cname) {
		this.cname = cname;
	}
	public String[] getSfxs() {
		return sfxs;
	}
	public void setSfxs(String[] sfxs) {
		this.sfxs = sfxs;
	}
	public String[] getXssx() {
		return xssx;
	}
	public void setXssx(String[] xssx) {
		this.xssx = xssx;
	}
}
