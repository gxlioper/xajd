
package xgxt.wjcf.gdby;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 广东白云学院违纪处分校审核MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-16</p>
 */
public class ShResultModel {

	private String cfsj;//处分时间
	private String cfwh;//处分文号
	private String sh;//审核
	private String clyj;//处理意见
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
