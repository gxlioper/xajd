package xgxt.xtwh.xtwhCriterion;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * 
 */
public class QxwhForm  extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4813758947802910758L;
	private String tableName;
	private String realTable;
	private String jsczfwdm;
	private String sffp;
	private String jsmc;
	private String jslxdm;
	private String doType;
	private String pk;
	private String pkValue;
	private String jssm;     //角色说明
	private String sfqy;     //是否启用
	private String jsdm;     //角色代码
	private String jscmdm;     //用户操作层面
	private String go;     //是否查询
	private String [] checkPkValue; 
	private String gnmkdm;  
	private Pages pages = new Pages();
	private String mk = "yhjscx";
	private String yhm;
	private String szbm;
	
	//查询条件类
	QueryModel queryModel;
	
	public QueryModel getQueryModel() {
		return queryModel;
	}

	public void setQueryModel(QueryModel queryModel) {
		this.queryModel = queryModel;
	}

	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
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
	public String getJsmc() {
		return jsmc;
	}
	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}
	public String getSffp() {
		return sffp;
	}
	public void setSffp(String sffp) {
		this.sffp = sffp;
	}
	public String getRealTable() {
		return realTable;
	}
	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

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

	public String getJssm() {
		return jssm;
	}

	public void setJssm(String jssm) {
		this.jssm = jssm;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public String getGo() {
		return go;
	}

	public void setGo(String go) {
		this.go = go;
	}

	public String[] getCheckPkValue() {
		return checkPkValue;
	}

	public void setCheckPkValue(String[] checkPkValue) {
		this.checkPkValue = checkPkValue;
	}

	public String getGnmkdm() {
		return gnmkdm;
	}

	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
	}

	public String getMk() {
		return mk;
	}

	public void setMk(String mk) {
		this.mk = mk;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getSzbm() {
		return szbm;
	}

	public void setSzbm(String szbm) {
		this.szbm = szbm;
	}
	
}
