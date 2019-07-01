package xsgzgl.xtwh.general.flow;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_审核流_通用_Model
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

public class XtwhFlowModel {

	private String yhm;// 用户名
	private String zdm;// 组代码
	private String gwdm;// 岗位代码
	private String current;// 当前页
	private String init;// 初始化
	private String sfbzr;
	private String sffdy;
	private String yhszlx;

	private String[] spyh;// 审批用户

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String[] getSpyh() {
		return spyh;
	}

	public void setSpyh(String[] spyh) {
		this.spyh = spyh;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}

	public String getZdm() {
		return zdm;
	}

	public String getGwdm() {
		return gwdm;
	}

	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public void setSfbzr(String sfbzr) {
		this.sfbzr = sfbzr;
	}

	public String getSfbzr() {
		return sfbzr;
	}

	public void setSffdy(String sffdy) {
		this.sffdy = sffdy;
	}

	public String getSffdy() {
		return sffdy;
	}

	public String getYhszlx() {
		return yhszlx;
	}

	public void setYhszlx(String yhszlx) {
		this.yhszlx = yhszlx;
	}

}
