package xsgzgl.gygl.xxtj;

import java.util.Map;

public class QsModel {
	private String qsh;
	private String zszt;
	private String cws;
	private String xydm;
	private String nj;
	private String yzr;
	private String xymc;
	private String sfhz;
	private String sfbl;
	private String qsxb;
	private String ch;
	private String ckqs;
	
	public QsModel(Map<String, String> map) {
		this.qsh = map.get("qsh");
		this.zszt = map.get("zszt");
		this.cws = map.get("cws");
		this.xydm = map.get("xydm");
		this.nj = map.get("nj");
		this.yzr = map.get("yzr");
		this.xymc = map.get("xymc");
		this.sfhz = map.get("sfhz");
		this.sfbl = map.get("sfbl");
		this.qsxb = map.get("qsxb");
		this.ch = map.get("ch");
		this.ckqs=map.get("ckqs");
	}
	
	public QsModel(){
		
	}
	
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getZszt() {
		return zszt;
	}
	public void setZszt(String zszt) {
		this.zszt = zszt;
	}
	public String getCws() {
		return cws;
	}
	public void setCws(String cws) {
		this.cws = cws;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getYzr() {
		return yzr;
	}
	public void setYzr(String yzr) {
		this.yzr = yzr;
	}
	
	public String getColor(){
		String color = "";
		if("Âú".equalsIgnoreCase(zszt)){
			color = "#FFAEB9";
		}else if("ÏÐ".equalsIgnoreCase(zszt)){
			color = "#C0FF3E";
		}else{
			color = "#FFFFFF";
		}
		
		return color;
	}
	public String getQsColor(){
		String color = "";
		if("ÊÇ".equalsIgnoreCase(ckqs)){
			color = "red";
		}else{
			color = "black";
		}
		
		return color;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getSfhz() {
		return sfhz;
	}

	public void setSfhz(String sfhz) {
		this.sfhz = sfhz;
	}

	public String getSfbl() {
		return sfbl;
	}

	public void setSfbl(String sfbl) {
		this.sfbl = sfbl;
	}

	public String getQsxb() {
		return qsxb;
	}

	public void setQsxb(String qsxb) {
		this.qsxb = qsxb;
	}

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}
}
