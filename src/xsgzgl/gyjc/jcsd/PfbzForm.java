package xsgzgl.gyjc.jcsd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class PfbzForm extends ActionForm {
	 private String guid;
	 private String xydm;
	 private String fjid;
	 private String wsqkyq;
	 private String xh;
	 private String jjlx;
	 private String js;
	 private String type;
	 private String fjmc;
	 private String[] guids;
	 private String[] fjids;
	 public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	 public String[] getGuids() {
		return guids;
	}
	public void setGuids(String[] guids) {
		this.guids = guids;
	}
	public String[] getFjids() {
		return fjids;
	}
	public void setFjids(String[] fjids) {
		this.fjids = fjids;
	}
	private Pages Pages = new Pages();
	 public String getType() {
		return type;
	}
	public Pages getPages() {
		return Pages;
	}
	public void setPages(Pages pages) {
		Pages = pages;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getFjid() {
		return fjid;
	}
	public void setFjid(String fjid) {
		this.fjid = fjid;
	}
	public String getWsqkyq() {
		return wsqkyq;
	}
	public void setWsqkyq(String wsqkyq) {
		this.wsqkyq = wsqkyq;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getJjlx() {
		return jjlx;
	}
	public void setJjlx(String jjlx) {
		this.jjlx = jjlx;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
}
