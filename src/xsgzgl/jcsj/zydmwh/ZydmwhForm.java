package xsgzgl.jcsj.zydmwh;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.jcsj.comm.JcsjForm;

public class ZydmwhForm extends JcsjForm{

	private static final long serialVersionUID = 1L;
	
	private String zydm;//专业代码
	private String zymc;//专业名称
	private String ssbmdm;//所属部门代码
	private String xz;//学制
	
	//查询条件
	private String query_ssbmdm;//所属部门代码
	
	
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
