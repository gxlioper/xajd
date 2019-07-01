package xgxt.xtwh.xtwhCriterion.jsgl;

import xgxt.xtwh.xtwhCriterion.RequestModel;



public class JsglRequestModel extends RequestModel{
	JsglQueryModel jsglQueryModel = new JsglQueryModel();
	private String jssm;     //角色说明
	private String jslxdm;     //角色类型
	private String sfqy;     //是否启用
	private String jsdm;     //角色代码
	private String jsmc;     //角色名称
	private String jscmdm;     //用户操作层面
	private String gnmkdm;     //用户操作层面
	private String cjr;		   //创建人
	
	public String getJscmdm() {
		return jscmdm;
	}

	public void setJscmdm(String jscmdm) {
		this.jscmdm = jscmdm;
	}

	public String getJsdm() {
		return jsdm;
	}

	public void setJsdm(String jsdm) {
		this.jsdm = jsdm;
	}

	public String getJslxdm() {
		return jslxdm;
	}

	public void setJslxdm(String jslxdm) {
		this.jslxdm = jslxdm;
	}

	public String getJsmc() {
		return jsmc;
	}

	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}

	public String getJssm() {
		return jssm;
	}

	public void setJssm(String jssm) {
		this.jssm = jssm;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public JsglQueryModel getJsglQueryModel() {
		return jsglQueryModel;
	}

	public void setJsglQueryModel(JsglQueryModel jsglQueryModel) {
		this.jsglQueryModel = jsglQueryModel;
	}

	public String getGnmkdm() {
		return gnmkdm;
	}

	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
}
