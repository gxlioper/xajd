package xgxt.jygl.comman;

import xgxt.base.Encrypt;

public class Model {
	private String yhm;
	
	private String oldmm;
	
	private String mm;
	
	private String qyfr;
	
	private String lxdh;
	
	private String dwmc;
	
	private String da1;
	
	private String da2;
	
	private String tswt1;
	
	private String tswt2;
	
	private String dwlx;
	
	private String czlx;
	
	private String xh;
	
	private String hfxx;
	
	private String gwmc;
	
	private String gwfbsj;
	
	public String getGwfbsj() {
		return gwfbsj;
	}

	public void setGwfbsj(String gwfbsj) {
		this.gwfbsj = gwfbsj;
	}

	public String getGwmc() {
		return gwmc;
	}

	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}

	public String getCzlx() {
		return czlx;
	}

	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getHfxx() {
		return hfxx;
	}

	public void setHfxx(String hfxx) {
		this.hfxx = hfxx;
	}

	public Encrypt getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(Encrypt encrypt) {
		this.encrypt = encrypt;
	}

	private Encrypt encrypt = new Encrypt();
	
	public String getOldmm() {
		return encrypt.encrypt(oldmm);
	}

	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	public String getYhm() {
		return yhm;
	}
	
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	
	public String getMm() {
		return encrypt.encrypt(mm);
	}
	
	public String getQyfr() {
		return qyfr;
	}
	
	public void setQyfr(String qyfr) {
		this.qyfr = qyfr;
	}
	
	public String getLxdh() {
		return lxdh;
	}
	
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getDa1() {
		return da1;
	}

	public void setDa1(String da1) {
		this.da1 = da1;
	}

	public String getDa2() {
		return da2;
	}

	public void setDa2(String da2) {
		this.da2 = da2;
	}

	public String getTswt1() {
		return tswt1;
	}

	public void setTswt1(String tswt1) {
		this.tswt1 = tswt1;
	}

	public String getTswt2() {
		return tswt2;
	}

	public void setTswt2(String tswt2) {
		this.tswt2 = tswt2;
	}

	public String getDwlx() {
		return dwlx;
	}

	public void setDwlx(String dwlx) {
		this.dwlx = dwlx;
	}
	
	public void setOldmm(String oldmm) {
		this.oldmm = oldmm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}
}
