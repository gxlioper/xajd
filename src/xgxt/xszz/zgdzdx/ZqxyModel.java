
package xgxt.xszz.zgdzdx;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国地质大学展期协议MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-12-10</p>
 */
public class ZqxyModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xh;
	private String zqnx;
	private String zqhhkrq;
	public String getZqhhkrq() {
		return zqhhkrq;
	}
	public void setZqhhkrq(String zqhhkrq) {
		this.zqhhkrq = zqhhkrq;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getZqnx() {
		return zqnx;
	}
	public void setZqnx(String zqnx) {
		this.zqnx = zqnx;
	}
}
