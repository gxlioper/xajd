package xsgzgl.szdw.general.szbb;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_思政编班_通用_Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SzbbModel {

	private String[] bjdm;// 班级代码

	private String bjdldm;// 班级大类代码

	private String bjdlmc;// 班级大类名称
	
	private String fplx;// 分配类型
	
	private String qqqh;//QQ群号

	public String getFplx() {
		return fplx;
	}

	public void setFplx(String fplx) {
		this.fplx = fplx;
	}

	public String[] getBjdm() {
		return bjdm;
	}

	public void setBjdm(String[] bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjdldm() {
		return bjdldm;
	}

	public void setBjdldm(String bjdldm) {
		this.bjdldm = bjdldm;
	}

	public String getBjdlmc() {
		return bjdlmc;
	}

	public void setBjdlmc(String bjdlmc) {
		this.bjdlmc = bjdlmc;
	}
}
