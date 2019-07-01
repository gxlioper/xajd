package xsgzgl.pjpy.general.pjsz.cpxz;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_参评小组_Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjszCpxzModel {

	private String[] bjdm;// 班级代码

	private String cpzdm;// 参评组代码

	private String cpzmc;// 参评组名称

	private String cpzgz;// 参评组规则

	public String[] getBjdm() {
		return bjdm;
	}

	public void setBjdm(String[] bjdm) {
		this.bjdm = bjdm;
	}

	public String getCpzdm() {
		return cpzdm;
	}

	public String getCpzgz() {
		return cpzgz;
	}

	public void setCpzgz(String cpzgz) {
		this.cpzgz = cpzgz;
	}

	public void setCpzdm(String cpzdm) {
		this.cpzdm = cpzdm;
	}

	public String getCpzmc() {
		return cpzmc;
	}

	public void setCpzmc(String cpzmc) {
		this.cpzmc = cpzmc;
	}
}
