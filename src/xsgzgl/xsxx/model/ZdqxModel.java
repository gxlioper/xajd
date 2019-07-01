package xsgzgl.xsxx.model;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_参数设置_字段权限_Model类
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

public class ZdqxModel {

	private String[] zd;// '字段';

	private String[] sslx;// '所属类型';

	private String[] zdm;// '字段名';

	private String[] checked;// '验证';

	private String[] zdlx;// '字段类型';

	private String[] source_table;// '数据源表';

	private String[] select_dm;// '数据源代码';

	private String[] select_mc;// '数据源名称';

	private String xsqx;// '学生权限';

	private String lsqx;// '老师权限';

	private String[] xssx;// '显示顺序';

	public String[] getChecked() {
		return checked;
	}

	public void setChecked(String[] checked) {
		this.checked = checked;
	}

	public String getLsqx() {
		return lsqx;
	}

	public void setLsqx(String lsqx) {
		this.lsqx = lsqx;
	}

	public String[] getSelect_dm() {
		return select_dm;
	}

	public void setSelect_dm(String[] select_dm) {
		this.select_dm = select_dm;
	}

	public String[] getSelect_mc() {
		return select_mc;
	}

	public void setSelect_mc(String[] select_mc) {
		this.select_mc = select_mc;
	}

	public String[] getSource_table() {
		return source_table;
	}

	public void setSource_table(String[] source_table) {
		this.source_table = source_table;
	}

	public String[] getSslx() {
		return sslx;
	}

	public void setSslx(String[] sslx) {
		this.sslx = sslx;
	}

	public String getXsqx() {
		return xsqx;
	}

	public void setXsqx(String xsqx) {
		this.xsqx = xsqx;
	}

	public String[] getXssx() {
		return xssx;
	}

	public void setXssx(String[] xssx) {
		this.xssx = xssx;
	}

	public String[] getZd() {
		return zd;
	}

	public void setZd(String[] zd) {
		this.zd = zd;
	}

	public String[] getZdlx() {
		return zdlx;
	}

	public void setZdlx(String[] zdlx) {
		this.zdlx = zdlx;
	}

	public String[] getZdm() {
		return zdm;
	}

	public void setZdm(String[] zdm) {
		this.zdm = zdm;
	}
}
