
package xgxt.wjcf.gdby;

import org.apache.struts.action.ActionForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政学院违纪处分ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-05</p>
 */
public class WjcfGdbyActionForm extends ActionForm {

	/**
	 * 自动生成版本ID
	 */
	private static final long serialVersionUID = 1L;

	private String xn;//学年
	private String xq;//学期
	private String nd;//年度
	private String nj;//年级
	private String xydm;//学院代码
	private String zydm;//专业代码
	private String bjdm;//班级代码
	private String sh;//审核
	private String clyj;//处理意见
	private String cfsj;//处分时间
	private String cfwh;//处分文号
	private String cflb;//处分类别
	private String cfyy;//处分原因
	private String pkValue;
	private String xh;//学号
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getCflb() {
		return cflb;
	}
	public void setCflb(String cflb) {
		this.cflb = cflb;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	public String getCfwh() {
		return cfwh;
	}
	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}
	public String getCfyy() {
		return cfyy;
	}
	public void setCfyy(String cfyy) {
		this.cfyy = cfyy;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
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
	public String getClyj() {
		return clyj;
	}
	public void setClyj(String clyj) {
		this.clyj = clyj;
	}
	public String getSh() {
		return sh;
	}
	public void setSh(String sh) {
		this.sh = sh;
	}
}
