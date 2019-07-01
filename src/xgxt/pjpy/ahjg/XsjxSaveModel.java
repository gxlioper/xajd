
package xgxt.pjpy.ahjg;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 安徽建筑工业学院评奖评优学习竞赛保存MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-19</p>
 */
public class XsjxSaveModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xh;//学号
	private String xn;//学年
	private String nd;//年度
	private String xxjsdm;//学习竞赛代码
	private String hjsj;//获奖时间
	private String bz;//备注
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getHjsj() {
		return hjsj;
	}
	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXxjsdm() {
		return xxjsdm;
	}
	public void setXxjsdm(String xxjsdm) {
		this.xxjsdm = xxjsdm;
	}
}
