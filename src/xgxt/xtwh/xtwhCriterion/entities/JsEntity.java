package xgxt.xtwh.xtwhCriterion.entities;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_用户管理_用户实体类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author luning
 * @version 1.0
 */
public class JsEntity extends Entitry{
	
	private String jsdm; 	// 角色代码
	private String jsmc; 	// 角色名
	private String jslxmc;		// 密码
	private String jscmmc;	// 所在部门
	private String jssm; 		// 姓名
	
	@Override
	public String getMappingTable() {
		return  "xg_view_xtwh_jswh";
	}

	@Override
	public String getPk() {
		return "jsmc";
	}

	@Override
	public String getPkValue() {
		return "jsmc";
	}

	public String getJscmmc() {
		return jscmmc;
	}

	public void setJscmmc(String jscmmc) {
		this.jscmmc = jscmmc;
	}

	public String getJslxmc() {
		return jslxmc;
	}

	public void setJslxmc(String jslxmc) {
		this.jslxmc = jslxmc;
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

	public String getJsdm() {
		return jsdm;
	}

	public void setJsdm(String jsdm) {
		this.jsdm = jsdm;
	}
	
}
