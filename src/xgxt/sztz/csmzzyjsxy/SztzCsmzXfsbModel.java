/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-1-4 下午05:27:00</p>
 */
package xgxt.sztz.csmzzyjsxy;

public class SztzCsmzXfsbModel {
	private String xn;//学年
	private String[] xh;//学号
	private String[] bjdmV;//班级代码
	private String[] xm;//姓名
	private String[] sxddsy_xf;//思想道德素养学分
	private String[] zyfw_xf;//志愿服务学分
	private String[] kxjs_xf;//科技学术学分 
	private String[] whys_xf;//文化艺术学分
	private String[] shstgz_xf;//社团社会工作学分
	private String[] jnpx_xf;//技能培训学分
	private String[] xfhj;//合计学分
	private String nj;
	private String xydm;
	private String zydm;
	private String bjdm;
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String[] getJnpx_xf() {
		return jnpx_xf;
	}
	public void setJnpx_xf(String[] jnpx_xf) {
		this.jnpx_xf = jnpx_xf;
	}
	public String[] getKxjs_xf() {
		return kxjs_xf;
	}
	public void setKxjs_xf(String[] kxjs_xf) {
		this.kxjs_xf = kxjs_xf;
	}
	public String[] getShstgz_xf() {
		return shstgz_xf;
	}
	public void setShstgz_xf(String[] shstgz_xf) {
		this.shstgz_xf = shstgz_xf;
	}
	public String[] getSxddsy_xf() {
		return sxddsy_xf;
	}
	public void setSxddsy_xf(String[] sxddsy_xf) {
		this.sxddsy_xf = sxddsy_xf;
	}
	public String[] getWhys_xf() {
		return whys_xf;
	}
	public void setWhys_xf(String[] whys_xf) {
		this.whys_xf = whys_xf;
	}
	public String[] getXfhj() {
		return xfhj;
	}
	public void setXfhj(String[] xfhj) {
		this.xfhj = xfhj;
	}
	
	public String[] getBjdmV() {
		return bjdmV;
	}
	public void setBjdmV(String[] bjdmV) {
		this.bjdmV = bjdmV;
	}
	public String[] getXh() {
		return xh;
	}
	public void setXh(String[] xh) {
		this.xh = xh;
	}
	public String[] getXm() {
		return xm;
	}
	public void setXm(String[] xm) {
		this.xm = xm;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String[] getZyfw_xf() {
		return zyfw_xf;
	}
	public void setZyfw_xf(String[] zyfw_xf) {
		this.zyfw_xf = zyfw_xf;
	}

}
