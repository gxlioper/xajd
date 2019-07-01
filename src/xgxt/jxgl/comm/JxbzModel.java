package xgxt.jxgl.comm;

import java.util.List;

public class JxbzModel {

	private String xn;
	
	private String bzdm;
	
	private String bzmc;
	
	private String bzdj;
	
	private String sjdm;
	
	private List<JxbzModel> jxbzModels;

	public String getBzdj() {
		return bzdj;
	}

	public void setBzdj(String bzdj) {
		this.bzdj = bzdj;
	}

	public String getBzdm() {
		return bzdm;
	}

	public void setBzdm(String bzdm) {
		this.bzdm = bzdm;
	}

	public String getBzmc() {
		return bzmc;
	}

	public void setBzmc(String bzmc) {
		this.bzmc = bzmc;
	}

	public List<JxbzModel> getJxbzModels() {
		return jxbzModels;
	}

	public void setJxbzModels(List<JxbzModel> jxbzModels) {
		this.jxbzModels = jxbzModels;
	}

	public String getSjdm() {
		return sjdm;
	}

	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}
}
