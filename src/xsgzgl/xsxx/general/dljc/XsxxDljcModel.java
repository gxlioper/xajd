package xsgzgl.xsxx.general.dljc;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_登录检测_通用_Model类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 偉大的駱
 * @version 1.0
 */

public class XsxxDljcModel {

	private String[] pkValue;// 主键
	private String checked;// 勾选与否
	private String[] nj;// 年级
	private String[] xy;// 学院
	private String[] zy;// 专业
	private String[] bj;// 班级
	private String[] zd;// 字段

	private boolean isXxws; // 是否信息完善

	public boolean isXxws() {
		return isXxws;
	}

	public void setXxws(boolean isXxws) {
		this.isXxws = isXxws;
	}

	public String[] getNj() {
		return nj;
	}

	public void setNj(String[] nj) {
		this.nj = nj;
	}

	public String[] getXy() {
		return xy;
	}

	public void setXy(String[] xy) {
		this.xy = xy;
	}

	public String[] getZy() {
		return zy;
	}

	public void setZy(String[] zy) {
		this.zy = zy;
	}

	public String[] getBj() {
		return bj;
	}

	public void setBj(String[] bj) {
		this.bj = bj;
	}

	public String[] getPkValue() {
		return pkValue;
	}

	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}

	public String[] getZd() {
		return zd;
	}

	public void setZd(String[] zd) {
		this.zd = zd;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
}
