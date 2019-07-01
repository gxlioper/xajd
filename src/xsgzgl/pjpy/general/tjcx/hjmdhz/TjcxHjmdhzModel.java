package xsgzgl.pjpy.general.tjcx.hjmdhz;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_获奖名单汇总_通用_Model
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

public class TjcxHjmdhzModel {

	private String xn;// 学年

	private String xmlx;// 项目类型

	private String[] xydm;// 学院代码

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String[] getXydm() {
		return xydm;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	public void setXydm(String[] xydm) {
		this.xydm = xydm;
	}
}
