package xsgzgl.jcsj.zydmwh;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.jcsj.comm.JcsjForm;

public class ZydmwhForm extends JcsjForm{

	private static final long serialVersionUID = 1L;
	
	private String zydm;//רҵ����
	private String zymc;//רҵ����
	private String ssbmdm;//�������Ŵ���
	private String xz;//ѧ��
	
	//��ѯ����
	private String query_ssbmdm;//�������Ŵ���
	
	
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getSsbmdm() {
		return ssbmdm;
	}
	public void setSsbmdm(String ssbmdm) {
		this.ssbmdm = ssbmdm;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getQuery_ssbmdm() {
		return query_ssbmdm;
	}
	public void setQuery_ssbmdm(String querySsbmdm) {
		query_ssbmdm = querySsbmdm;
	}
	
}
