package xgxt.wjdc.comm.wjgl;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class WjglForm extends ActionForm {
	/* 通用 */
	Pages pages = new Pages();
	
	private String tableName="xg_wjdc_wjxxb";
	
	private String wjid;//问卷id
	private String wjmc;//问卷名称
	private String wjzt;//问卷状态
	private String sfjf;//是否计分
	private String sffd;//是否复答
	private String wjkssj;//问卷开始时间
	private String wjjssj;//问卷结束时间
	private String jsy;//卷首语
	private String jwy;//卷尾语
	private String cjsj;//创建时间
	private String yhid;//用户id
	
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public String getJsy() {
		return jsy;
	}
	public void setJsy(String jsy) {
		this.jsy = jsy;
	}
	public String getJwy() {
		return jwy;
	}
	public void setJwy(String jwy) {
		this.jwy = jwy;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getSffd() {
		return sffd;
	}
	public void setSffd(String sffd) {
		this.sffd = sffd;
	}
	public String getSfjf() {
		return sfjf;
	}
	public void setSfjf(String sfjf) {
		this.sfjf = sfjf;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getWjid() {
		return wjid;
	}
	public void setWjid(String wjid) {
		this.wjid = wjid;
	}
	public String getWjjssj() {
		return wjjssj;
	}
	public void setWjjssj(String wjjssj) {
		this.wjjssj = wjjssj;
	}
	public String getWjkssj() {
		return wjkssj;
	}
	public void setWjkssj(String wjkssj) {
		this.wjkssj = wjkssj;
	}
	public String getWjmc() {
		return wjmc;
	}
	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}
	public String getWjzt() {
		return wjzt;
	}
	public void setWjzt(String wjzt) {
		this.wjzt = wjzt;
	}
	public String getYhid() {
		return yhid;
	}
	public void setYhid(String yhid) {
		this.yhid = yhid;
	}
	
	
}
