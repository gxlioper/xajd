package xsgzgl.jcsj.bjdmwh;

import xsgzgl.jcsj.comm.JcsjForm;

public class BjdmwhForm extends JcsjForm{

	private static final long serialVersionUID = 1L;
	
	private String bjdm;//班级代码
	private String bjmc;//班级名称
	private String sszydm;//所属专业代码
	private String nj;//年级
	
	private String ssbmdm;///部门代码
	
	//查询条件
	private String query_nj;//年级
	private String query_ssbmdm;//所属部门代码
	private String query_sszydm;//所属专业代码
	
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getSszydm() {
		return sszydm;
	}
	public void setSszydm(String sszydm) {
		this.sszydm = sszydm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getSsbmdm() {
		return ssbmdm;
	}
	public void setSsbmdm(String ssbmdm) {
		this.ssbmdm = ssbmdm;
	}
	public String getQuery_ssbmdm() {
		return query_ssbmdm;
	}
	public void setQuery_ssbmdm(String querySsbmdm) {
		query_ssbmdm = querySsbmdm;
	}
	public String getQuery_sszydm() {
		return query_sszydm;
	}
	public void setQuery_sszydm(String querySszydm) {
		query_sszydm = querySszydm;
	}
	public String getQuery_nj() {
		return query_nj;
	}
	public void setQuery_nj(String queryNj) {
		query_nj = queryNj;
	}
	
	
}
