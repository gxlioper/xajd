
package xgxt.pjpy.csmz;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政学院评奖评优荣誉称号代码MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-21</p>
 */
public class RychdmModel {

	private String rychdm;//荣誉称号代码
	private String rychmc;//荣誉称号名称
	private String xydm;//学院代码
	private String rychlb;//荣誉称号类别 
	public String getRychdm() {
		return rychdm;
	}
	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}
	public String getRychlb() {
		return rychlb;
	}
	public void setRychlb(String rychlb) {
		this.rychlb = rychlb;
	}
	public String getRychmc() {
		return rychmc;
	}
	public void setRychmc(String rychmc) {
		this.rychmc = rychmc;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
}
