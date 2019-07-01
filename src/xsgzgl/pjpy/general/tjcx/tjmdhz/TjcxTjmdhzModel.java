package xsgzgl.pjpy.general.tjcx.tjmdhz;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_获奖金额汇总_通用_Model
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

public class TjcxTjmdhzModel {

	private String xn;// 学年

	private String xmmc;// 项目名称

	private String[] nj;// 年级

	private String[] xydm;// 学院代码

	private String[] zydm;// 专业代码

	private String[] bjdm;// 班级代码
	
	private String[]xmmcArr;// 项目名称
	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getXmmcArr() {
		return xmmcArr;
	}

	public void setXmmcArr(String[] xmmcArr) {
		this.xmmcArr = xmmcArr;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String[] getNj() {
		return nj;
	}

	public void setNj(String[] nj) {
		this.nj = nj;
	}

	public String[] getXydm() {
		return xydm;
	}

	public void setXydm(String[] xydm) {
		this.xydm = xydm;
	}

	public String[] getZydm() {
		return zydm;
	}

	public void setZydm(String[] zydm) {
		this.zydm = zydm;
	}

	public String[] getBjdm() {
		return bjdm;
	}

	public void setBjdm(String[] bjdm) {
		this.bjdm = bjdm;
	}
}
