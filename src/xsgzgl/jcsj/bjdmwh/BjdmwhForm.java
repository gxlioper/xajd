package xsgzgl.jcsj.bjdmwh;

import xsgzgl.jcsj.comm.JcsjForm;

public class BjdmwhForm extends JcsjForm{

	private static final long serialVersionUID = 1L;
	
	private String bjdm;//�༶����
	private String bjmc;//�༶����
	private String sszydm;//����רҵ����
	private String nj;//�꼶
	
	private String ssbmdm;///���Ŵ���
	
	//��ѯ����
	private String query_nj;//�꼶
	private String query_ssbmdm;//�������Ŵ���
	private String query_sszydm;//����רҵ����
	
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
