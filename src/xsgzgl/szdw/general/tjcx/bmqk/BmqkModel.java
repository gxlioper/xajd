package xsgzgl.szdw.general.tjcx.bmqk;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_统计查询_部门情况_通用_Model
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

public class BmqkModel {

	private String[] bjdm;// 班级代码

	private String bjdldm;// 班级大类代码

	private String bjdlmc;// 班级大类名称

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
