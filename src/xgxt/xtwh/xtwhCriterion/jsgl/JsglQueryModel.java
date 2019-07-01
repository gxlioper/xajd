package xgxt.xtwh.xtwhCriterion.jsgl;

import xgxt.xtwh.xtwhCriterion.QueryModel;



/**
 * 查询类
 */
public class JsglQueryModel extends QueryModel{
	String jsczfwdm;//角色操作范围
	String sffp;//是否分配
	String gnmkdm;//功能模块代码
	String scgnmkdm;//2级功能模块代码
	String jslxdm;//角色类型代码
	String jscmdm;//角色层面代码
	String jsmc;//角色名称
	String sfqy;//是否启用
	public String getJsmc() {
		return jsmc;
	}
	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}
	public String getJscmdm() {
		return jscmdm;
	}
	public void setJscmdm(String jscmdm) {
		this.jscmdm = jscmdm;
	}
	public String getGnmkdm() {
		return gnmkdm;
	}
	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
	}
	public String getJsczfwdm() {
		return jsczfwdm;
	}
	public void setJsczfwdm(String jsczfwdm) {
		this.jsczfwdm = jsczfwdm;
	}
	public String getJslxdm() {
		return jslxdm;
	}
	public void setJslxdm(String jslxdm) {
		this.jslxdm = jslxdm;
	}
	public String getScgnmkdm() {
		return scgnmkdm;
	}
	public void setScgnmkdm(String scgnmkdm) {
		this.scgnmkdm = scgnmkdm;
	}
	public String getSffp() {
		return sffp;
	}
	public void setSffp(String sffp) {
		this.sffp = sffp;
	}
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	
}
