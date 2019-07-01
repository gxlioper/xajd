
package xgxt.xszz.commonN05;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: N05项目设置MODEL</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-10-16</p>
 */
public class XmszModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xmb;
	private String xmmc;
	private String shjb;
	private String sfkns;
	
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getSfkns() {
		return sfkns;
	}
	public void setSfkns(String sfkns) {
		this.sfkns = sfkns;
	}
	public String getShjb() {
		return shjb;
	}
	public void setShjb(String shjb) {
		this.shjb = shjb;
	}
	public String getXmb() {
		return xmb;
	}
	public void setXmb(String xmb) {
		this.xmb = xmb;
	}
}
