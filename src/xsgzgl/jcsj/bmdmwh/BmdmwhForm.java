package xsgzgl.jcsj.bmdmwh;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.jcsj.comm.JcsjForm;

public class BmdmwhForm extends JcsjForm{

	private static final long serialVersionUID = 1L;
	
	private String bmdm;//部门代码
	private String bmmc;//部门名称
	private String bmlb;//部门类别
	
	//查询条件
	private String query_bmlb;//查询部门类别
	
	
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public String getBmlb() {
		return bmlb;
	}
	public void setBmlb(String bmlb) {
		this.bmlb = bmlb;
	}
	public String getQuery_bmlb() {
		return query_bmlb;
	}
	public void setQuery_bmlb(String queryBmlb) {
		query_bmlb = queryBmlb;
	}
	
	
	
}
